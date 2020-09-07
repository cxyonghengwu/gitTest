package org.lanqiao.entity;

import java.util.Date;

/**
 * @author xiaoqaing
 * @date 2020/9/7
 */
public class Message {
    private int id;
    private String author;
    private String msg;
    private Date date;

    public Message() {
    }

    public Message(String author, String msg) {
        this.author = author;
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
