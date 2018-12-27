package com.hush.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Maps;
import com.hush.panduan.panduan;
import com.hush.pojo.Pic;
import com.hush.service.FileService;
import com.hush.service.PicService;

@Controller
@RequestMapping("/file")
public class ImgController {
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private PicService picService;

	
    private String localPath = "/home/file/";
    //private String imgUrl="http://121.43.186.83:8070/";
	
    private String imgUrl="https://wasu.minghuisama.com/";
///////1---	private String localPath = "file:///E:/home/file/";

    
	
	@RequestMapping(value = "/downLoadFile", method = RequestMethod.GET)
	@ResponseBody
	public String downLoadFile(@RequestParam("com")String com,
			@RequestParam("floatPeople")String floatPeople,
			@RequestParam("nah")String nah,
			@RequestParam("pah")String pah,
			@RequestParam("realtive")String realtive,
			@RequestParam("starttime")String starttime,
			@RequestParam("endtime")String endtime,
			HttpServletResponse response,
			HttpSession session,
			HttpServletRequest request) throws Exception {
		
		
		List<Pic> listAll=picService.download(com,floatPeople,nah,pah,realtive,
				starttime,endtime,response);
	    // 创建图片URL的地址
	    List<String> urls = new ArrayList<>();
	    //String lString=localPath;
	    //System.out.println(lString);
	    for (Pic p : listAll) {
	    	
	    	
	    	
       urls.add(imgUrl+localPath+p.getImg());
	    	 
 //////2--  	 urls.add(localPath+p.getImg());
	    	       
	    }
	    

	    //创建map用于存放从服务器下载的图片流文件
	    Map<String, InputStream> isMap = Maps.newHashMap();
	    urls.forEach(urlStr -> {
	        //分解图片文件名
	        String[] fileNameParts = urlStr.split("/");
	        String fileName1 = fileNameParts[fileNameParts.length - 1];
	        
	        String[] fileNames = fileName1.split("/");
			String fileName = 		
					fileNames[fileNames.length-1];
			//System.out.println(fileName);
	        
	        try {
	            //创建URL对象用于连接存放图片的服务器
	            URL url = new URL(urlStr);
	           // System.out.println(url);
	            //建立连接
	            URLConnection con = url.openConnection();
	            // 设置超时间为3秒
	            con.setConnectTimeout(3 * 1000);
	            // 防止屏蔽程序抓取而返回403错误
	            con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
	            //从服务器下载图片流文件
	            InputStream is = con.getInputStream();
	            //将图片流文件放入isMap中
	            isMap.put(fileName, is);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	       
	    });

	    //设置浏览器返回体的内容以及编码、文件名字
	    response.setContentType("application/octet-stream");
	    String filename = URLEncoder.encode("文件名字", "UTF-8");
	    response.setHeader("Content-Disposition", "attachment; filename=" + filename + ".zip");
	    //创建ZipOutputStream对象，先是获取到response对象的输出流对象，把它转成ZipOutputStream对象，然后给ZipOutputStream流里写入文件的信息，就会同步设置在response的输出流里了
	    ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
	    isMap.forEach((fileName, is) -> {
	        try {
	            addToZip(is, zipOut, fileName);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });
	    zipOut.flush();
	    zipOut.close();
	    return "ok";
	}

	private void addToZip(InputStream is, ZipOutputStream zipOut, String fileName) throws IOException {
	    fileName = URLEncoder.encode(fileName, "UTF-8");
	    ZipEntry entry = new ZipEntry(fileName);
	    zipOut.putNextEntry(entry);
	    int len;
	    byte[] buffer = new byte[1024];
	    while ((len = is.read(buffer)) > 0) {
	        zipOut.write(buffer, 0, len);
	    }
	    zipOut.closeEntry();
	    is.close();
	}
	
	@GetMapping("/download")
	@ResponseBody
	public String download(@RequestParam("com")String com,
								@RequestParam("floatPeople")String floatPeople,
								@RequestParam("nah")String nah,
								@RequestParam("pah")String pah,
								@RequestParam("realtive")String realtive,
								@RequestParam("starttime")String starttime,
								@RequestParam("endtime")String endtime,
								HttpServletResponse response,
								HttpServletRequest request,
								HttpSession session) throws Exception{
		
		
		List<Pic> listAll=picService.download(com,floatPeople,nah,pah,realtive,
				starttime,endtime,response);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

        String fileName = "people"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        String[] headers = { "姓名", "小区", "电话", "身份证号", 
        		"车牌号", "地址", "人员居住类型(0、人户一致1、有人无户2、有户无人3、流动人口)",
        		"与户主关系", "户籍所在地(0、本小区内1、杭州2、其他)",
        		"是否居住在本小区(0、是1、否)","修改时间","头像名"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (Pic p : listAll) {
            HSSFRow row1 = sheet.createRow(rowNum);
            
            String img = p.getImg();
            //System.out.println(img);
            
	        String[] imgs = img.split("/");
	        //System.out.println(imgs);
	        
			String picName = 		
					imgs[imgs.length-1];
			//System.out.println(picName);
			
            row1.createCell(0).setCellValue(p.getUserName());
            row1.createCell(1).setCellValue(p.getCom());
            row1.createCell(2).setCellValue(p.getTel());
            row1.createCell(3).setCellValue(p.getIdcard());
            row1.createCell(4).setCellValue(p.getCar());
            row1.createCell(5).setCellValue(p.getAddress());
            row1.createCell(6).setCellValue(p.getFloatPeople());
            row1.createCell(7).setCellValue(p.getRealtive());
            row1.createCell(8).setCellValue(p.getPah());
            row1.createCell(9).setCellValue(p.getNah());
            row1.createCell(10).setCellValue(p.getCreattime());
            row1.createCell(11).setCellValue(picName);
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
        return "ok";
        
	}
	
	@GetMapping("/imgFind")
	@ResponseBody
	public List<Pic> imgFind(@RequestParam("com")String com,
								@RequestParam("floatPeople")String floatPeople,
								@RequestParam("nah")String nah,
								@RequestParam("pah")String pah,
								@RequestParam("realtive")String realtive,
								@RequestParam("starttime")String starttime,
								@RequestParam("endtime")String endtime,
								HttpServletResponse response,
								HttpServletRequest request,
								HttpSession session) throws Exception{
		
		return picService.findImg(com,floatPeople,nah,pah,realtive,starttime,endtime);
	}

	@PostMapping("/upload")
	@ResponseBody
	public String file(@RequestParam("file") MultipartFile file,
			@RequestParam("idcard") String idcard,
			HttpSession httpSession) throws IllegalStateException, IOException{

		if(file.equals(null)) {
			return "您没有选择图片啊";
		}else if(!panduan.validate(idcard)) {
			return "您的身份证信息不正确啊";
		}
		fileService.fileUpload(file,idcard, httpSession);
		return "图片上传成功";
	}
	

}
