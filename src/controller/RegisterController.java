package controller;

import helper.Alerts;
import helper.SceneChanger;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import model.User;
import repository.UserRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

public class RegisterController {

    public TextField username;
    public TextField password;
    public TextField confirmPassword;
    UserRepository userRepository = new UserRepository();

    public void registerUser(ActionEvent actionEvent){

        if(username.getText().isEmpty() || password.getText().isEmpty() || confirmPassword.getText().isEmpty()){
            Alerts.showWarning("Please fill all the fields");
        }
        else{
            if(password.getText().equals(confirmPassword.getText())) {
                User user = new User(username.getText(), password.getText());
                try {
                    userRepository.addUser(user);
                    Alerts.showConfirmation("User successfully registered");
                } catch (SQLException e) {
                    Alerts.showError(e.getMessage());
                }
            }
            else{
                Alerts.showError("Passwords do not match");
            }
        }
    }

    public void goToLogin(ActionEvent actionEvent) throws IOException {
        SceneChanger sceneChanger = new SceneChanger();
        sceneChanger.changeScene("/view/Login.fxml","Login",actionEvent);
    }

}
