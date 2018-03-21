package com.jd.cardapp.service.ServiceImpl;

import com.jd.cardapp.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public boolean picAdd(String folderpath , String filename, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // 文件夹不存在就创建
                File saveDir = new File(folderpath);
                if (!saveDir.exists() && !saveDir.isDirectory()) {
                    saveDir.mkdirs();
                }
                // 转存文件
                file.transferTo(new File(folderpath+filename));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        File file1 = new File(folderpath+filename);
        if(file1.isFile())
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean fileDel(String absolutePath) {
        //imagePath = request.getSession().getServletContext().getRealPath("/")+ "upload/" + pic;
        if(absolutePath==null)
        {
            return false;
        }
        File file = new File(absolutePath);
        if (file.exists()){
            file.delete();
            if(!file.exists())
            {
                return true;
            }
            else
            {
                return false;
            }
        }else{
            return true;
        }
    }

    @Override
    public boolean fileCopy(String sourcePath,String sourceFile, String targetPath,String targetFile) {
        if(sourcePath==null || targetPath==null || sourceFile==null || targetFile==null)
        {
            return false;
        }
        try {
            // 打开输入流
            FileInputStream fis = new FileInputStream(sourcePath+sourceFile);

            //判断文件夹
            File saveDir = new File(targetPath);
            if (!saveDir.exists() && !saveDir.isDirectory()) { // 文件夹不存在就创建
                saveDir.mkdir();
            }
            // 打开输出流
            FileOutputStream fos = new FileOutputStream(targetPath+targetFile);

            // 读取和写入信息
            int len = 0;
            // 创建一个字节数组，当做缓冲区
            byte[] b = new byte[1024];
            while ((len = fis.read(b)) != -1) {
                fos.write(b, 0, len);
            }

            // 关闭流  先开后关  后开先关
            fos.close(); // 后开先关
            fis.close(); // 先开后关

            File file = new File(targetPath+targetFile);
            if( file.isFile() )
            {
                return true;
            }
        }catch (IOException e)
        {
            return false;
        }

        return false;
    }
}
