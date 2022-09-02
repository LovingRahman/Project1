package org.example.entity;

public class Ticket {
    private int id;
    private String employeeUsername;
    private float amount;
    private String description;
    private String status = "pending";

    public Ticket() {

    }

    public Ticket(String status) {
        this.status = status;
    }

    public Ticket(int id, String employeeUsername, float amount, String description, String status) {
        this.id = id;
        this.employeeUsername = employeeUsername;
        this.amount = amount;
        this.description = description;
        this.status = status;
    }

    public Ticket(String employeeUsername, float amount, String description, String status) {
        this.employeeUsername = employeeUsername;
        this.amount = amount;
        this.description = description;
        this.status = status;
    }

    public Ticket(int id, String status) {
        this.id = id;
        this.status = status;
    }

    @Override
    public String toString(){
        return "Ticket{" +
                "id= " + id +
                ", employeeusername= " + employeeUsername +
                ", amount= " + amount +
                ", description= " + description +
                ", status= " + status +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public void setEmployeeUsername(String employeeUsername) {
        this.employeeUsername = employeeUsername;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}