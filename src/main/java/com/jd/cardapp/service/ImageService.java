package com.jd.cardapp.service;

import com.github.pagehelper.PageInfo;
import com.jd.cardapp.model.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    boolean picAdd(String folderpath , String filename, MultipartFile file);

    boolean fileDel(String absolutePath);

    boolean fileCopy(String sourcePath,String sourceFile,String targetPath,String targetFile);

    PageInfo<Picture> pictureList(String keys, Integer pageNo , Integer pageSize, String begin , String end,Integer type);

    Picture getPicture(Integer pid);

    int PictureAdd(Picture picture, MultipartFile file);

    int PictureDel(Integer pid);

    List<Picture> getPictureList(Integer type);

    List<Integer> getPictureListWithID(Integer type);
}
