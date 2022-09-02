package org.example.data;

public class DaoFactory {

    private static EmployeeDao employeeDao = null;
    private static TicketDao ticketDao = null;

    private DaoFactory(){

    }

    public static EmployeeDao getEmployeeDao(){

        if(employeeDao == null){

            System.out.println("run once");

            employeeDao = new EmployeeDaoImpl();
        }
        return employeeDao;
    }

    public static TicketDao getTicketDao(){

        if(ticketDao == null){

            System.out.println("run once");

            ticketDao = new TicketDaoImpl();
        }
        return ticketDao;
    }


}
