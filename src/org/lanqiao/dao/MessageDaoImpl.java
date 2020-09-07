package org.lanqiao.dao;

import org.lanqiao.entity.Message;

import java.util.List;

/**
 * @author xiaoqaing
 * @date 2020/9/7
 */
public class MessageDaoImpl extends BaseDao<Message> implements MessageDao{

    @Override
    public int insertMsg(Message message) {
        return executeUpdate("insert into message (author,msg) values(?,?)", new Object[]{message.getAuthor(),message.getMsg()});
    }

    @Override
    public List<Message> getMsgList(int pageNum, int pageSize) {
        return executeQuery("select * from message order by id desc limit ?,?", new Object[]{(pageNum - 1) * pageSize, pageSize});
    }

    @Override
    public int getMsgCount() {
        return executeCount("select count(id) from message");
    }
}
