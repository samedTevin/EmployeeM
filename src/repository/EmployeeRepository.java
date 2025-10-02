package repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import utils.Database;

import java.sql.*;


public class EmployeeRepository {

    Database database = new Database();
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;
    Connection connection;

    public void add(Employee employee) throws SQLException {
        try{
            connection = database.getConnection();
            String sql = "INSERT INTO employees(name,position,department,salary,status,rating) VALUES (?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getPosition());
            preparedStatement.setString(3, employee.getDepartment());
            preparedStatement.setDouble(4, employee.getSalary());
            preparedStatement.setString(5, employee.getStatus());
            preparedStatement.setDouble(6, employee.getRating());
            preparedStatement.execute();

        }
        catch(SQLException e){
            database.showError(e);
            throw (e);
        }
        finally{
            if (preparedStatement!=null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }

    public void remove(Employee employee) throws SQLException {
        try{
            connection = database.getConnection();
            String sql = "DELETE FROM employees WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.execute();
        }
        catch(SQLException e){
            database.showError(e);
            throw (e);
        }
        finally{
           if(connection != null) connection.close();
           if(preparedStatement != null) preparedStatement.close();
        }
    }

    public void update(Employee employee) throws SQLException {
        try{
            connection = database.getConnection();
            String sql = "UPDATE employees SET name = ?, position = ?, department = ?, salary = ?, status = ?, rating = ? WHERE id = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getPosition());
            preparedStatement.setString(3, employee.getDepartment());
            preparedStatement.setDouble(4, employee.getSalary());
            preparedStatement.setString(5, employee.getStatus());
            preparedStatement.setDouble(6, employee.getRating());
            preparedStatement.setInt(7, employee.getId());
            preparedStatement.execute();
        }
        catch(SQLException e){
            database.showError(e);
            throw(e);
        }
        finally{
            if(connection != null) connection.close();
            if(preparedStatement != null) preparedStatement.close();
        }

    }

    public ObservableList<Employee> show() throws SQLException {
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        try{
            connection = database.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM employees";
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Employee employee = new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("position"),
                        resultSet.getString("department"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("status"),
                        resultSet.getDouble("rating")
                );
                employees.add(employee);
            }
        }
        catch(SQLException e){
            database.showError(e);
            throw (e);
        }
        finally{
            if(connection != null) connection.close();
            if(statement != null) statement.close();
            if(resultSet != null) resultSet.close();
        }

        return employees;
    }

    public Employee search(int id) throws SQLException {
        Employee employee = null;
        try{
            connection = database.getConnection();
            String sql = "SELECT * FROM employees WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                 employee = new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("position"),
                        resultSet.getString("department"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("status"),
                        resultSet.getDouble("rating")
                );
            }
        }
        catch (SQLException e){
            database.showError(e);
            throw (e);
        }
        finally{
            if(connection != null) connection.close();
            if(statement != null) statement.close();
            if(resultSet != null) resultSet.close();
        }

        return employee;
    }

}
