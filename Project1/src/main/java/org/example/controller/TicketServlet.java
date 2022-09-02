package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.entity.Employee;
import org.example.entity.Ticket;
import org.example.service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TicketServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        TicketService ticketService = new TicketService();
        ObjectMapper mapper = new ObjectMapper();
        Ticket ticket;
        String user;

        try{
            ticket = mapper.readValue(req.getReader(), Ticket.class);
        }catch (Exception e){
            e.printStackTrace();
            resp.sendError(400, "Invalid Ticket format");
            return;
        }

        try{
            user = (String) req.getSession().getAttribute("username");
        } catch (Exception e) {
            resp.sendError(401, "Must be logged in to view tickets");
            return;
        }

        ticket.setEmployeeUsername(user); // takes in logged username to generate ticket object

        ticket = ticketService.insert(ticket); //takes in created ticket object and uses it to inject into sql

        out.println("Your ticket has been uploaded!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        TicketService ticketService = new TicketService();
        String user;
        String authType = req.getParameter("auth");
        boolean manager;
        String status = req.getParameter("status");

        try{
            user = (String) req.getSession().getAttribute("username");
        } catch (Exception e) {
            resp.sendError(401, "Must be logged in to view tickets");
            return;
        }

        try{
            manager = (boolean)req.getSession().getAttribute("manager");
        }catch (Exception e){
            e.printStackTrace();
            resp.sendError(400, "Something went wrong with the manager");
            return;
        }


        if(authType.equals("EmployeeTicket")) {
            List<Ticket> tickets = ticketService.getTicket(user);

            for (Ticket ticket : tickets) {
                out.println(ticket);
            }

        }


        if(authType.equals("ManagerPortal") && manager){

            List<Ticket> tickets = ticketService.getTicketByStatus(status);
            for (Ticket ticket1 : tickets) {
                out.println(ticket1);
            }

        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        TicketService ticketService = new TicketService();

        ObjectMapper mapper = new ObjectMapper();
        Ticket ticket;
        boolean manager;


        try{
            manager = (boolean)req.getSession().getAttribute("manager");
        }catch (Exception e){
            e.printStackTrace();
            resp.sendError(400, "Something went wrong with the manager");
            return;
        }

        try{
            ticket = mapper.readValue(req.getReader(), Ticket.class);
        }catch (Exception e){
            e.printStackTrace();
            resp.sendError(400, "Invalid Ticket update");
            return;
        }

        Ticket ticketDb = ticketService.getTicketById(ticket.getId());


//        if(ticketDb.getStatus().equals("pending")) {
//            ticket = ticketService.update(ticket);
//            out.println(ticket);
//        }
//
//        else {
//            out.println("This ticket cannot be updated");
//        }


        if(manager) {
            if(ticketDb.getStatus().equals("pending")) {
                ticket = ticketService.update(ticket);
                out.println(ticket);
            }

            else {
                out.println("This ticket cannot be updated");
            }

        }

        else if(!manager) {
            out.println("You are not a manager");
        }


    }


}
