package org.example.data;

import org.example.entity.Ticket;

import java.util.List;

public interface TicketDao {

    public Ticket insert(Ticket ticket);// implants registered records into database

    public List<Ticket> getTicket(String Username);

    public List<Ticket> getTicketByStatus(String Status);

    public Ticket getTicketById(int Id);

    public Ticket update(Ticket ticket);






}
