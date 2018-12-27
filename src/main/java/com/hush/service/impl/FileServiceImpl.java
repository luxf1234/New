package com.hush.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hush.mapper.FileMapper;
import com.hush.mapper.MemberMapper;
import com.hush.mapper.UserMapper;
import com.hush.service.FileService;

@Service
public class FileServiceImpl implements FileService{

////3--	private String localPath = "E:/home/file/";
	
    private String localPath = "/home/file/";


	@Autowired
	private FileMapper fileMapper;
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public String fileUpload(MultipartFile uploadFile,String idcard,HttpSession httpSession) {
		String fileName=uploadFile.getOriginalFilename();
		File fileDir = new File(localPath);
		if(!fileDir.exists()){
			fileDir.mkdirs();
		};		
		String fileType = 		
				fileName.substring(fileName.lastIndexOf("."));
		
		Integer MemberId = memberMapper.findIdByIdcard(idcard);
		//文件名
		String realFileName = MemberId + fileType;
		
		//文件夹
		String dateDir = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		String localDirPath = localPath + dateDir;
		File fileDir1 = new File(localDirPath);
		if(!fileDir1.exists()){
			fileDir1.mkdirs();
		}
		//存储的数据
		String date=dateDir+"/"+realFileName;
		
		String localFilePath =localPath+date;
		
		
		httpSession.setAttribute("img_path", localFilePath);

		
		Integer imgnum=fileMapper.selectByMessageId(MemberId);
		String oldimg = fileMapper.selectImgByMessageId(MemberId);
		String oldPath = localPath+oldimg;
		File i=new File(oldPath);
		if(i.exists()){
			i.delete();
		};
		if (imgnum!=null) {

			try {
				uploadFile.transferTo(new File(localFilePath)); 
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				return "网络故障，请稍后重试";
			}
			
			fileMapper.update(MemberId,date);
		}else {
			try {
				uploadFile.transferTo(new File(localFilePath));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				return "网络故障，请稍后重试";
			}
			fileMapper.insert(MemberId,date);
		}

		return "图片上传成功";
		
	}

}
