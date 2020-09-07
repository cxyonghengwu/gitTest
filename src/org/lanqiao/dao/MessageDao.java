package org.lanqiao.dao;

import org.lanqiao.entity.Message;

import java.util.List;

/**
 * @author xiaoqaing
 * @date 2020/9/7
 */
public interface MessageDao {
    public int insertMsg(Message message);

    public List<Message> getMsgList(int pageNum, int pageSize);

    public int getMsgCount();
}
