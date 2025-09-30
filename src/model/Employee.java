package model;

public class Employee {


    private int id;
    private String name;
    private String position;
    private String department;
    private double salary;
    private String status;
    private double rating;


    public Employee(String name, String position, String department, double salary, String status, double rating) {
        this.name = name;
        this.position = position;
        this.department = department;
        this.salary = salary;
        this.status = status;
        this.rating = rating;
    }

    public Employee(int id, String name, String position, String department, double salary, String status, double rating) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.department = department;
        this.salary = salary;
        this.status = status;
        this.rating = rating;
    }


    public Employee(){

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

}
