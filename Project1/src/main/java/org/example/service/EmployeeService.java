package org.example.service;

import org.example.data.DaoFactory;
import org.example.data.EmployeeDao;
import org.example.entity.Employee;

public class EmployeeService {

    public Employee insert(Employee employee){

        System.out.println("In the service layer, getting the DAO and calling the insert DAO method");
        EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
        return employeeDao.insert(employee);
    }


    public Employee getByUsername(String username){

        EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
        return employeeDao.getByUsername(username);
    }



}
