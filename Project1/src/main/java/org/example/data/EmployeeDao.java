package org.example.data;

import org.example.entity.Employee;

public interface EmployeeDao {



    public Employee insert(Employee employee);// implants registered records into database

    public Employee getByUsername(String Username); // finds record based on username

}
