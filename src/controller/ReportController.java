package controller;

import helper.Alerts;
import helper.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import model.DepartmentStats;
import repository.ReportRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReportController implements Initializable {

    ReportRepository report = new ReportRepository();
    public BarChart<String, Number> barChart;
    public CategoryAxis xAxis;
    public NumberAxis yAxis;
    public BarChart<String, Number> barChart2;
    public CategoryAxis xAxis2;
    public NumberAxis yAxis2;
    public PieChart ratingCount;
    public ScatterChart<Number, Number> scatter;
    public NumberAxis rating; // X axis
    public NumberAxis salary; // Y axis
    public Label totalEmployee;
    public Label avgSalary;
    public Label avgRating;
    public Label activeEmployee;


    public void goToDashboard(ActionEvent event) throws IOException {
        SceneChanger sceneChanger = new SceneChanger();
        sceneChanger.changeScene("/view/Dashboard.fxml","Dashboard",event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            refreshCharts();
        } catch (SQLException e) {
           Alerts.showError("Database Error: "+e.getMessage());
        }
    }

    public void refreshCharts() throws SQLException {
        DepartmentStats departmentStats = report.getDepartmentStats();
        totalEmployee.setText(String.valueOf(departmentStats.getTotalEmployees()));
        avgSalary.setText(String.format("%.2f", departmentStats.getAvgSalary()));
        avgRating.setText(String.format("%.1f", departmentStats.getAvgRating()));
        activeEmployee.setText(String.valueOf(departmentStats.getActiveEmployees()));
        barChart.getData().clear();
        barChart2.getData().clear();
        scatter.getData().clear();
        try {
            barChart.getData().add(report.avgSalaryByDepartment());
            barChart2.getData().add(report.activeEmployeesByDepartment());
            ratingCount.setData(report.ratingCount());
            scatter.getData().add(report.ratingVsSalary());
        } catch (SQLException e) {
            Alerts.showError("Database Error: " + e.getMessage());
        }
    }
}
