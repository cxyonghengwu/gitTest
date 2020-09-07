package org.lanqiao.servlet;

import org.lanqiao.entity.Message;
import org.lanqiao.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xiaoqaing
 * @date 2020/9/7
 */
@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String author = request.getParameter("author");
        String msg = request.getParameter("msg");
        Message message = new Message(author,msg);
        MessageService messageService = new MessageService();
        int ret = messageService.insertMsg(message);
        response.sendRedirect("/get");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
