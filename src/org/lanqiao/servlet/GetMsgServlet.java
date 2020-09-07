package org.lanqiao.servlet;

import org.lanqiao.entity.Message;
import org.lanqiao.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author xiaoqaing
 * @date 2020/9/7
 */
@WebServlet("/get")
public class GetMsgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MessageService messageService = new MessageService();
        int pageNum = 1;
        if(request.getParameter("pageNum") != null){
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        }
        List<Message> messages = messageService.getMsgList(pageNum,5);
        request.setAttribute("messages", messages);
        request.setAttribute("maxPage", messageService.getMaxPage(5));

        request.getRequestDispatcher("message.jsp").forward(request,response);
    }
}
