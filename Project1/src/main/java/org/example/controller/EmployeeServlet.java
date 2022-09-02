package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.entity.Employee;
import org.example.entity.Ticket;
import org.example.service.EmployeeService;
import org.example.service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class EmployeeServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        EmployeeService employeeService = new EmployeeService();
        ObjectMapper mapper = new ObjectMapper();
        Employee employee;
        String authType = req.getParameter("auth");


        try{

            employee = mapper.readValue(req.getReader(), Employee.class); // takes input from JSON and puts it in employee object
        } catch (Exception e){

            e.printStackTrace();
            resp.sendError(400, "Invalid Employee format");
            return;
        }

        if(authType.equals("register")){
            employee = employeeService.insert(employee); // inputs data from employee object into database, gets back generated id and inputs it into employee object.
            if(employee.getId() == 0){
                out.println("Account already exists");
            }
            else {
                //out.println(employee);
                out.println("Successfully registered!");
            }
        }

        else if(authType.equals("registerManager")){
            employee.setManager(true);
            employee = employeeService.insert(employee); // inputs data from employee object into database, gets back generated id and inputs it into employee object.
            if(employee.getId() == 0){
                out.println("Account already exists");
            }
            else {
                //out.println(employee);
                out.println("Successfully registered!");
            }
        }

        else if(authType.equals("login")){
            String username = employee.getUsername();
            String password = employee.getPassword();
            Employee employeeDb = employeeService.getByUsername(username);

            if(employeeDb == null){
                out.println("You do not have an account!");
            }

            else if (!employeeDb.getPassword().equals(password)){
                out.println("Incorrect password");
            }


            else if(employeeDb.getUsername().equals(username) && employeeDb.getPassword().equals(password)){
                out.println("You are logged in!");
            }

            req.getSession().setAttribute("username", employee.getUsername());
            req.getSession().setAttribute("manager", employeeDb.isManager());

        }
        // this creates the session based on login
//        req.getSession().setAttribute("username", employee.getUsername());
//        req.getSession().setAttribute("manager", employeeDb.isManager());
    }



}
