package controller;

import helper.Alerts;
import helper.SceneChanger;
import helper.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setText(String.valueOf(UserSession.id));
        userName.setText(UserSession.username);
        password.setText(UserSession.password);
        createdAt.setText("NULL");
        updatedAt.setText("NULL");
    }

    public void changePassword(ActionEvent actionEvent) throws IOException {
        if(newPassword.getText().equals(confirmNewPassword.getText())){
            if(UserSession.password.equals(newPassword.getText())){
                Alerts.showWarning("Your new password cannot be the same!");
            }
            else{
                int num;
            }
        }
        else{
            Alerts.showError("Passwords don't match");
        }
    }

    public void removeAccount(ActionEvent actionEvent) throws IOException {
        Alerts.showInformation("Your account has been removed!");
    }

    public void returnDashboard(ActionEvent actionEvent) throws IOException {
        SceneChanger sceneChanger = new SceneChanger();
        sceneChanger.changeScene("/view/Dashboard.fxml","Dashboard",actionEvent);
    }

    public void saveDailyNotes(ActionEvent actionEvent) throws IOException {
        String notes = dailyNotes.getText();
    }


}

