package org.lanqiao.service;

import org.lanqiao.dao.MessageDao;
import org.lanqiao.dao.MessageDaoImpl;
import org.lanqiao.entity.Message;

import java.util.List;

/**
 * @author xiaoqaing
 * @date 2020/9/7
 */
public class MessageService {
    MessageDao messageDao = new MessageDaoImpl();
    public int insertMsg(Message message) {
        return messageDao.insertMsg(message);
    }

    public List<Message> getMsgList(int pageNum, int pageSize) {
        return messageDao.getMsgList(pageNum,pageSize);
    }
    public int getMaxPage(int pageSize){
        int count =  messageDao.getMsgCount();
        return (int) Math.ceil(count*1.0 / pageSize);
    }
}
