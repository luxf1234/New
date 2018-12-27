package com.hush.service;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	String fileUpload(MultipartFile uploadFile,String idcard,HttpSession httpSession);

}
