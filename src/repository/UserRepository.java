package repository;

import helper.Alerts;
import model.User;
import utils.Database;

import java.sql.*;


public class UserRepository {
    Connection connection;
    Database database = new Database();
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;


    public void addUser(User user) throws SQLException{
        try{
            connection = database.getConnection();
            String sql = "INSERT INTO user (username,password) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.execute();
        }
        catch(SQLException e){
            database.showError(e);
            throw (e);
        }
        finally {
            if(preparedStatement != null) preparedStatement.close();
            if(connection != null) connection.close();
        }
    }

    public boolean findUser(User user) throws SQLException {
        boolean flag = false;
        try{
            connection = database.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT username,password FROM user";
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                if(user.getUsername().equals(resultSet.getString("username"))){
                    if(user.getPassword().equals(resultSet.getString("password"))){
                        flag = true;
                        break;
                    }
                }
            }
        }
        catch (SQLException e) {
            database.showError(e);
            throw (e);
        }
        finally{
            if(resultSet != null){resultSet.close();}
            if(connection != null){connection.close();}
            if(statement != null){statement.close();}
        }

        return flag;
    }
}
