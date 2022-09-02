package org.example.data;

import org.example.entity.Ticket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoImpl implements TicketDao {

    Connection connection;

    public TicketDaoImpl(){

        connection = ConnectionFactory.getConnection();
    }

    @Override
    public Ticket insert(Ticket ticket){

        String sql = "insert into tickets (id, employeeusername, amount, description, status) values (default, ?, ?, ?, 'pending');";
        try{

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, ticket.getEmployeeUsername());
            preparedStatement.setFloat(2, ticket.getAmount());
            preparedStatement.setString(3, ticket.getDescription());

            int count = preparedStatement.executeUpdate();

            if (count == 1) {
                System.out.println("Ticket added successfully.");

                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                resultSet.next();

                int generatedId = resultSet.getInt(1);
                ticket.setId(generatedId);
            }

            else{

                System.out.println("Something went wrong with the insert!");
            }

        } catch (SQLException e){

            e.printStackTrace();
            System.out.println("Something went wrong when preparing the statement!");
        }
        return ticket;
    }

    @Override
    public List<Ticket> getTicket(String Username){
        List<Ticket> tickets = new ArrayList<>();

        String sql = "select * from tickets where employeeusername = ?;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String employeeusername = resultSet.getString("employeeusername");
                float amount = resultSet.getFloat("amount");
                String description = resultSet.getString("description");
                String status = resultSet.getString("status");

                Ticket ticket = new Ticket(id, employeeusername, amount, description,status);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public List<Ticket> getTicketByStatus(String Status){
        List<Ticket> tickets = new ArrayList<>();

        String sql = "select * from tickets where status = ?;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Status);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String employeeusername = resultSet.getString("employeeusername");
                float amount = resultSet.getFloat("amount");
                String description = resultSet.getString("description");
                String status = resultSet.getString("status");

                Ticket ticket = new Ticket(id, employeeusername, amount, description,status);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public Ticket getTicketById(int Id){
        String sql = "select * from tickets where id = ?;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int idDb = resultSet.getInt("id");
                String status = resultSet.getString("status");

                Ticket ticket = new Ticket(idDb, status);
                return ticket;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Ticket update(Ticket ticket){
        String sql = "update tickets set status = ? where id = ?;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ticket.getStatus());
            preparedStatement.setInt(2, ticket.getId());

            int count = preparedStatement.executeUpdate();

            if(count == 1){
                System.out.println("Update successful!");
            }

            else {
                System.out.println("Something went wrong with the update");
                if(count == 0) {
                    System.out.println("No rows were affected");
                }
                else {
                    System.out.println("Many rows were affected");
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }


}
