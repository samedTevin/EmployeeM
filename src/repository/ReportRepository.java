package repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import model.DepartmentStats;
import utils.Database;

import java.sql.*;


public class ReportRepository {
    Database database = new Database();
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    public XYChart.Series<String, Number> avgSalaryByDepartment() throws SQLException {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        try{
            connection = database.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT department, AVG(salary) as avg_salary FROM employees GROUP BY department";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String department = resultSet.getString("department");
                double avgSalary = resultSet.getDouble("avg_salary");
                series.getData().add(new XYChart.Data<>(department, avgSalary));
            }
        }
        catch(SQLException e){
            database.showError(e);
            throw (e);
        }
        finally{
            if(connection != null){connection.close();}
            if(statement != null){statement.close();}
            if(resultSet != null){resultSet.close();}
        }

        return series;
    }

    public ObservableList<PieChart.Data> ratingCount() throws SQLException {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        try{
            connection = database.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT rating, COUNT(*) as total_count FROM employees GROUP BY rating";
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                int rating = resultSet.getInt("rating");
                int count = resultSet.getInt("total_count");
                String label = rating + " rating";
                data.add(new PieChart.Data(label, count));
            }

        }
        catch(SQLException e){
            database.showError(e);
            throw (e);
        }
        finally{
            if(statement != null){statement.close();}
            if(resultSet != null){resultSet.close();}
            if(connection != null){connection.close();}
        }

        return data;
    }

    public XYChart.Series<String, Number> activeEmployeesByDepartment() throws SQLException {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        try{
            connection = database.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT department, COUNT(*) as total_count FROM employees WHERE status='Active' GROUP BY department";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String department = resultSet.getString("department");
                double count = resultSet.getDouble("total_count");
                series.getData().add(new XYChart.Data<>(department, count));
            }
        }
        catch(SQLException e){
            database.showError(e);
            throw (e);
        }
        finally{
            if(connection != null){connection.close();}
            if(statement != null){statement.close();}
            if(resultSet != null){resultSet.close();}
        }

        return series;
    }

    public XYChart.Series<Number, Number> ratingVsSalary() throws SQLException {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        try{
            connection = database.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT rating,salary FROM employees";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Double rating = resultSet.getDouble("rating");
                Double salary = resultSet.getDouble("salary");
                series.getData().add(new XYChart.Data<>(rating, salary));
            }
        }
        catch(SQLException e){
            database.showError(e);
            throw (e);
        }
        finally{
            if(connection != null){connection.close();}
            if(statement != null){statement.close();}
            if(resultSet != null){resultSet.close();}
        }

        return series;
    }

    public DepartmentStats  getDepartmentStats() throws SQLException {
        DepartmentStats departmentStats = new DepartmentStats();
        try{
            connection = database.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT SUM(case WHEN status='Active' THEN 1 ELSE 0 END) as active_count, COUNT(*) as total_employee, AVG(salary) as avg_salary, AVG(rating) as avg_rating FROM employees";
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                int sumActiveEmployee =  resultSet.getInt("active_count");
                int totalEmployee = resultSet.getInt("total_employee");
                double avgRating = resultSet.getDouble("avg_rating");
                double avgSalary = resultSet.getDouble("avg_salary");

                departmentStats.setActiveEmployees(sumActiveEmployee);
                departmentStats.setTotaEmployees(totalEmployee);
                departmentStats.setAvgRating(avgRating);
                departmentStats.setAvgSalary(avgSalary);
            }
        }
        catch(SQLException e){
            database.showError(e);
            throw (e);
        }
        finally{
            if(connection != null){connection.close();}
            if(statement != null){statement.close();}
            if(resultSet != null){resultSet.close();}
        }

        return departmentStats;
    }
}
