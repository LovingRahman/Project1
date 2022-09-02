package org.example.service;

import org.example.data.DaoFactory;
import org.example.data.EmployeeDao;
import org.example.data.TicketDao;
import org.example.entity.Employee;
import org.example.entity.Ticket;

import java.util.List;


public class TicketService {

    public Ticket insert(Ticket ticket) {
        TicketDao ticketdao = DaoFactory.getTicketDao();
        return ticketdao.insert(ticket);
    }

    public List<Ticket> getTicket(String Username){
        TicketDao ticketdao = DaoFactory.getTicketDao();
        return ticketdao.getTicket(Username);

    }

    public List<Ticket> getTicketByStatus(String Status){
        TicketDao ticketdao = DaoFactory.getTicketDao();
        return ticketdao.getTicketByStatus(Status);

    }

    public Ticket getTicketById(int Id){
        TicketDao ticketdao = DaoFactory.getTicketDao();
        return ticketdao.getTicketById(Id);
    }

    public Ticket update(Ticket ticket){
        TicketDao ticketdao = DaoFactory.getTicketDao();
        return ticketdao.update(ticket);
    }
}
