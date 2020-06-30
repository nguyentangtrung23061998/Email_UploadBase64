package com.spring.file.springangularreadfile.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringAngularFileController {
	
	@Autowired
	ServletContext context;
	
	@GetMapping(value="/getImages")
	@CrossOrigin
	public ResponseEntity<List<String>> getImages(){
		List<String> images = new ArrayList<String>();
		String filesPath = context.getRealPath("/images");
		System.out.println("filePath: "+ filesPath);
		File fileFolder = new File(filesPath);
		if(fileFolder != null) {
			for(final File file : fileFolder.listFiles()) {
				System.out.println("file: " + file);
				if(!file.isDirectory()) {
					String encodeBase64 = null;
					try {
						String extension = FilenameUtils.getExtension(file.getName());
						FileInputStream fileInputStream = new FileInputStream(file);
						byte[] bytes = new byte[(int) file.length()];
						fileInputStream.read(bytes);
						encodeBase64 = Base64.getEncoder().encodeToString(bytes);
						images.add("data:image/"+extension+";base64,"+encodeBase64);
//						System.out.println("data:image/"+extension+";base64,"+encodeBase64);
						fileInputStream.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		return new ResponseEntity<List<String>>(images,HttpStatus.OK);
	}
	
}
