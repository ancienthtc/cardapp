package com.jd.cardapp.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jd.cardapp.dao.MessageMapper;
import com.jd.cardapp.model.Message;
import com.jd.cardapp.model.MessageExample;
import com.jd.cardapp.service.MessageService;
import com.jd.cardapp.util.date.DateExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    @Override
    public int MessageAdd(Message message) {
        message.setCreatetime(DateExample.getNowTimeByDate());
        return messageMapper.insertSelective(message);
    }

    @Override
    public PageInfo<Message> userMessageList(Integer pageNo , Integer pageSize, String name, String tel) {
        pageNo = pageNo == null ? 1:pageNo;
        pageSize = pageSize == null ? 10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andTelEqualTo(tel);
        messageExample.setOrderByClause("createtime desc");
        List<Message> messageList = messageMapper.selectByExample(messageExample);
        PageInfo<Message> result = new PageInfo<>(messageList);
        return result;
    }

    /**
     * @param keys
     * @param pageNo
     * @param pageSize
     * @param begin
     * @param end
     * @param type 0:未回复 1:已回复
     * @return
     */
    @Override
    public PageInfo<Message> allMessageList(String keys, Integer pageNo, Integer pageSize, String begin, String end, Integer type) {
        pageNo = pageNo == null ? 1:pageNo;
        pageSize = pageSize == null ? 10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        String[] key=keys.split("\\s+");
        if( !(type==0 || type==1) )
        {
            return null;
        }
        List<Message> messageList = messageMapper.messageList(key,begin,end,type);
        PageInfo<Message> result = new PageInfo<>(messageList);
        return result;
    }

    @Override
    public Message getMessage(Integer id) {
        return messageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int messageReply(Message message) {
        if(message.getReply()==null || message.getId() == null)
        {
            return 0;
        }
        message.setReplytime(DateExample.getNowTimeByDate());
        message.setUpdatetime(DateExample.getNowTimeByDate());

        return messageMapper.updateByPrimaryKeySelective(message);
    }

    @Override
    public int messageDel(Integer id) {
        return messageMapper.deleteByPrimaryKey(id);
    }
}
