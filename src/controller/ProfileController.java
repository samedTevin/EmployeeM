package controller;

import helper.Alerts;
import helper.SceneChanger;
import helper.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.User;
import repository.UserRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    public Label id;
    public Label userName;
    public Label password;
    public Label createdAt;
    public Label updatedAt;
    public TextField newPassword;
    public TextField confirmNewPassword;
    public TextArea dailyNotes;
    private final UserRepository userRepository = new UserRepository();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setText(String.valueOf(UserSession.id));
        userName.setText(UserSession.username);
        password.setText(UserSession.password);
        createdAt.setText(UserSession.created);
        updatedAt.setText(UserSession.updated);
        dailyNotes.setText(UserSession.dailyNotes);
    }

    public void changePassword()  {
        if(newPassword.getText().equals(confirmNewPassword.getText())){
            if(UserSession.password.equals(newPassword.getText())){
                Alerts.showWarning("Your new password cannot be the same!");
            }
            else{
                try {
                    userRepository.updateUserPassword(UserSession.id, newPassword.getText());
                    UserSession.password = newPassword.getText();
                    password.setText(UserSession.password);
                    refreshUpdatedTime();
                    Alerts.showInformation("Your new password has been changed!");
                } catch (SQLException e) {
                    Alerts.showError(e.getMessage());
                }
            }
        }
        else{
            Alerts.showError("Passwords don't match");
        }
    }

    public void removeAccount(ActionEvent actionEvent) {
        try {
            userRepository.deleteUser(UserSession.id);
            Alerts.showInformation("Account has been removed!");
            SceneChanger sceneChanger = new SceneChanger();
            sceneChanger.changeScene("/view/Login.fxml","Login",actionEvent);
        } catch (SQLException | IOException e) {
            Alerts.showError(e.getMessage());
        }
    }


    public void returnDashboard(ActionEvent actionEvent) throws IOException {
        SceneChanger sceneChanger = new SceneChanger();
        sceneChanger.changeScene("/view/Dashboard.fxml","Dashboard",actionEvent);
    }

    public void saveDailyNotes(){
        String notes = dailyNotes.getText();
        try{
            userRepository.addUserDailyNotes(notes,UserSession.id);
            refreshUpdatedTime();
            Alerts.showInformation("Notes has been saved!");

        }
        catch (NullPointerException npe){
            Alerts.showError("No daily notes!");
        }
        catch(SQLException e){
            Alerts.showError("Database error! " + e.getMessage());
        }
    }

    public void refreshUpdatedTime() throws SQLException {
        User newTime = userRepository.getUserDetails(UserSession.username);

        if(newTime != null){
            UserSession.updated = newTime.getUpdatedAt();
            updatedAt.setText(UserSession.updated);
        }
    }



}

