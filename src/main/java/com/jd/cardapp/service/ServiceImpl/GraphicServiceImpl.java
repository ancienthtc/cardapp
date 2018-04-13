package com.jd.cardapp.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jd.cardapp.config.MyCustomConfig;
import com.jd.cardapp.dao.GraphicMapper;
import com.jd.cardapp.model.Graphic;
import com.jd.cardapp.service.GraphicService;
import com.jd.cardapp.service.ImageService;
import com.jd.cardapp.util.StringUtil.GenerateString;
import com.jd.cardapp.util.date.DateExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GraphicServiceImpl implements GraphicService {

    @Resource
    private GraphicMapper graphicMapper;

    @Autowired
    private ImageService imageService;

    @Autowired
    private MyCustomConfig customConfig;

    @Override
    public PageInfo<Graphic> graphicList(String keys, Integer pageNo, Integer pageSize, String begin, String end) {
        pageNo = pageNo == null ? 1:pageNo;
        pageSize = pageSize == null ? 10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        String[] key=null;
        if(keys!=null)
        {
            key=keys.split("\\s+");
        }


        List<Graphic> graphicList = graphicMapper.getGraphicList(key,begin,end);
        PageInfo<Graphic> result = new PageInfo<>(graphicList);
        return result;
    }

    @Override
    public Graphic getGraphic(Integer id) {
        return graphicMapper.selectByPrimaryKey(id);
    }

    @Override
    public int graphicAdd(Graphic graphic, MultipartFile file) {
        //检验
        if(file.getSize() == 0)
        {
            return 0;
        }
        if(graphic.getContributor()==null || graphic.getTitle()==null)
        {
            return 0;
        }
        if( !(graphic.getPay()>0) )
        {
            return 0;
        }
        if( graphic.getPay()<1 || graphic.getScore()>5 || graphic.getScore()<0 )
        {
            return 0;
        }

        String filename = new GenerateString().getFileName("report")+getOriginal(file);
        String path = customConfig.getUpload() + customConfig.getGraphicfolder();
        boolean b1;
        if( b1 = imageService.picAdd(path,filename,file) )
        {
            graphic.setFilename(filename);
            graphic.setFolder(path);
        }
        if( b1 )
        {
            graphic.setCreatetime(DateExample.getNowTimeByDate());
            return graphicMapper.insertSelective(graphic);
        }
        return 0;
    }

    @Override
    public int graphicDel(Integer id) {
        Graphic graphic = graphicMapper.selectByPrimaryKey(id);
        if(imageService.fileDel(graphic.getFolder()+graphic.getFilename()))
        {
            return graphicMapper.deleteByPrimaryKey(id);
        }
        return 0;
    }

    public String getOriginal(MultipartFile file)
    {
        return  "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
    }
}
