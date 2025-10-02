package model;

public class DepartmentStats {

    private int totalEmployees;
    private double avgSalary;
    private double avgRating;
    private int activeEmployees;

    public DepartmentStats(){

    }

    public DepartmentStats(int totalEmployees, double avgSalary, double avgRating, int activeEmployees) {
        this.totalEmployees = totalEmployees;
        this.avgSalary = avgSalary;
        this.avgRating = avgRating;
        this.activeEmployees = activeEmployees;
    }

    public int getActiveEmployees() {
        return activeEmployees;
    }

    public void setActiveEmployees(int activeEmployees) {
        this.activeEmployees = activeEmployees;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public double getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(double avgSalary) {
        this.avgSalary = avgSalary;
    }

    public int getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
    }




}
