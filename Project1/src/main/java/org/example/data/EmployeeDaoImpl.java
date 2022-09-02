package org.example.data;

import org.example.entity.Employee;
import java.sql.*;

public class EmployeeDaoImpl implements EmployeeDao {

    Connection connection;

    public EmployeeDaoImpl() {

        connection = ConnectionFactory.getConnection();
    }


    @Override
    public Employee insert(Employee employee) {

        String sql = "insert into employees(id, username, password, manager) values (default, ?, ?, ?);";
        try {
            //System.out.println(Employee.toString());
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getUsername());
            preparedStatement.setString(2, employee.getPassword());
            preparedStatement.setBoolean(3, employee.isManager());

            //this inserts the statment into the database
            int count = preparedStatement.executeUpdate();

            // This whole block gets the id made by the database, returned values are stored into a resultset object
            if (count == 1) {
                System.out.println("Employee added successfully.");

                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                resultSet.next(); // ask what this does

                int generatedId = resultSet.getInt(1);
                employee.setId(generatedId);
            }

            else {

                System.out.println("Something went wrong with the insert!");
            }

        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("Something went wrong when preparing the statement!");
        }
        return employee;
    }

    @Override
    public Employee getByUsername(String Username) {

        String sql = "select * from employees where username = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Username);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                int idDb = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                boolean manager = resultSet.getBoolean("manager");
                Employee employee = new Employee(idDb,username, password,manager);

                return employee;
            } else {
                System.out.println("This employee might not exist");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to retrieve data.");
            e.printStackTrace();
        }
        return null;
    }
}



