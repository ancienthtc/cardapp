package com.jd.cardapp.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jd.cardapp.config.MyCustomConfig;
import com.jd.cardapp.dao.PictureMapper;
import com.jd.cardapp.model.Picture;
import com.jd.cardapp.model.PictureExample;
import com.jd.cardapp.service.ImageService;
import com.jd.cardapp.util.StringUtil.GenerateString;
import com.jd.cardapp.util.date.DateExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Resource
    private PictureMapper pictureMapper;

    @Autowired
    private MyCustomConfig config;

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


    @Override
    public PageInfo<Picture> pictureList(String keys, Integer pageNo, Integer pageSize, String begin, String end, Integer type) {
        pageNo = pageNo == null ? 1:pageNo;
        pageSize = pageSize == null ? 5:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        String[] key=keys.split("\\s+");
//        if(type<0 || type>5)
//        {
//            return null;
//        }
        List<Picture> pictureList = pictureMapper.getPictureList(key,begin,end,type);
        PageInfo<Picture> result = new PageInfo<>(pictureList);
        return result;
    }

    @Override
    public Picture getPicture(Integer pid) {
        return pictureMapper.selectByPrimaryKey(pid);
    }

    @Override
    public int PictureAdd(Picture picture, MultipartFile file) {
        if(picture.getType()==null)
        {
            return 0;
        }
        PictureExample pictureExample = new PictureExample();
        PictureExample.Criteria criteria = pictureExample.createCriteria();
        criteria.andTypeEqualTo(picture.getType());
        int count = pictureMapper.countByExample(pictureExample);
        switch (picture.getType())
        {
            case 0:
                if(count>4)
                {
                    return 0;
                }
                break;
            case 1:
                if(count>4)
                {
                    return 0;
                }
                break;
            case 2:
                if(count>8)
                {
                    return 0;
                }
                break;
            case 3:
                if(count>20)
                {
                    return 0;
                }
                break;
            case 4:
                if(count>10)
                {
                    return 0;
                }
                break;
            default:
                return 0;
        }

        String folder = config.getUpload()+config.getImagefolder();
        String filename = new GenerateString().getFileName("image")+getOriginal(file);
        if(picAdd(folder,filename,file))
        {
            picture.setFilename(filename);
            picture.setFolder(folder);
            picture.setCreatetime(DateExample.getNowTimeByDate());
            return pictureMapper.insertSelective(picture);
        }
        return 0;
    }

    @Override
    public int PictureDel(Integer pid) {
        Picture picture = pictureMapper.selectByPrimaryKey(pid);
        if(fileDel(picture.getFolder()+picture.getFilename()))
        {
            return pictureMapper.deleteByPrimaryKey(pid);
        }
        return 0;
    }

    @Override
    public List<Picture> getPictureList(Integer type) {
        PictureExample pictureExample = new PictureExample();
        PictureExample.Criteria criteria = pictureExample.createCriteria();
        criteria.andTypeEqualTo(type);
        List<Picture> pictureList = pictureMapper.selectByExample(pictureExample);
        return pictureList;
    }

    @Override
    public List<Integer> getPictureListWithID(Integer type) {
        PictureExample pictureExample = new PictureExample();
        PictureExample.Criteria criteria = pictureExample.createCriteria();
        criteria.andTypeEqualTo(type);
        List<Picture> pictureList = pictureMapper.selectByExample(pictureExample);
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<pictureList.size();i++)
        {
            result.add(pictureList.get(i).getId());
        }
        return result;
    }

    public String getOriginal(MultipartFile file)
    {
        return  "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
    }


}
