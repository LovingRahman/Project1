package org.example.controller;

import org.example.entity.Employee;
import org.example.service.EmployeeService;

import com.fasterxml.jackson.databind.ObjectMapper; // not needed
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EmployeeLogin extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Employee employee = new Employee();
        EmployeeService employeeService = new EmployeeService();

        employee = employeeService.getByUsername(username); // test

//        System.out.println(employee);
//        System.out.println(username);
        if(username.equals(employee.getUsername()) && password.equals(employee.getPassword())) {

            out.println("You are logged in!");
//
//            Cookie cookie = new Cookie("username", username);
//            resp.addCookie(cookie);
//
//            RequestDispatcher rd = req.getRequestDispatcher("/welcome");
//
//            rd.include(req, resp);
//            rd.forward(req, resp);

        }

        else {

            out.println("You are not logged in!");
        }

    }


}
