package model;

public class DepartmentStats {

    private int totaEmployees;
    private double avgSalary;
    private double avgRating;
    private int activeEmployees;

    public DepartmentStats(){

    }

    public DepartmentStats(int totaEmployees, double avgSalary, double avgRating, int activeEmployees) {
        this.totaEmployees = totaEmployees;
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

    public int getTotaEmployees() {
        return totaEmployees;
    }

    public void setTotaEmployees(int totaEmployees) {
        this.totaEmployees = totaEmployees;
    }




}
