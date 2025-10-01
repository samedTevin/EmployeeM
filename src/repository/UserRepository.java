package repository;

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
            String sql = "INSERT INTO users (username,password) VALUES (?,?)";
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
            String sql = "SELECT username,password FROM users";
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
    public User getUserDetails(String username) throws SQLException {
        try{
            connection = database.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setDailyNotes(resultSet.getString("dailyNotes"));
                user.setCreatedAt(resultSet.getString("created_at"));
                user.setUpdatedAt(resultSet.getString("updated_at"));
                return user;
            }
        }
        catch(SQLException e){
            database.showError(e);
            throw (e);
        }
        finally{
            if(resultSet != null){resultSet.close();}
            if(connection != null){connection.close();}
            if(preparedStatement != null){preparedStatement.close();}
        }
        return null;
    }

    public void deleteUser(int id) throws SQLException{
        try{
            connection = database.getConnection();
            String sql = "DELETE FROM users WHERE id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
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

    public void updateUserPassword(int id, String password) throws SQLException{
        try{
            connection = database.getConnection();
            String sql = "UPDATE users SET password = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
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

    public void addUserDailyNotes(String dailyNotes, int id) throws SQLException{
        try{
            connection = database.getConnection();
            String sql = "UPDATE users SET dailyNotes = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, dailyNotes);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            database.showError(e);
            throw (e);
        }
        finally {
            if(preparedStatement != null) preparedStatement.close();
            if(connection != null) connection.close();
        }
    }
}
