package com.jd.cardapp.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    boolean picAdd(String folderpath , String filename, MultipartFile file);

    boolean fileDel(String absolutePath);

    boolean fileCopy(String sourcePath,String sourceFile,String targetPath,String targetFile);
}
