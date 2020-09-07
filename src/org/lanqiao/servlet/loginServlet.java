package org.lanqiao.servlet;

import org.lanqiao.entity.User;
import org.lanqiao.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xiaoqaing
 * @date 2020/9/4
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(account, password);
        LoginService loginService = new LoginService();
        boolean result = loginService.isLoginSuccess(user);
        response.getWriter().print(result);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
