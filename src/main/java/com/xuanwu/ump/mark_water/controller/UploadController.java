package com.xuanwu.ump.mark_water.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xuanwu.ump.mark_water.entity.ImageInfo;
import com.xuanwu.ump.mark_water.service.ImageUploadService;
import com.xuanwu.ump.mark_water.service.ImageWatermarkService;

@RestController
public class UploadController {

	@Autowired
	private ImageUploadService imageUploadService;

	@Autowired
	private ImageWatermarkService watermarkService;

	@RequestMapping(value = "/watermarktest", method = RequestMethod.POST)
	public ImageInfo watermarkTest(@RequestParam("file") MultipartFile image) {

		ImageInfo imgInfo = new ImageInfo();

		String uploadPath = "./static/images/"; // 服务器上上传文件的相对路径
		String physicalUploadPath = uploadPath; // 服务器上上传文件的物理路径

		String imageURL = imageUploadService.uploadImage(image, uploadPath, physicalUploadPath);
		File imageFile = new File(physicalUploadPath + image.getOriginalFilename());

		String watermarkAddImageURL = watermarkService.watermarkAdd(imageFile, image.getOriginalFilename(), uploadPath,
				physicalUploadPath);

		imgInfo.setImageUrl(imageURL);
		imgInfo.setLogoImageUrl(watermarkAddImageURL);
		return imgInfo;
	}

}
