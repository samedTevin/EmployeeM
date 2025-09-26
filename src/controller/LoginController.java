package controller;

import helper.Alerts;
import helper.SceneChanger;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import repository.UserRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

public class LoginController {

    public TextField username;
    public PasswordField password;
    UserRepository userRepository = new UserRepository();

    public void loginUser(ActionEvent event){
        User user = new User(username.getText(),password.getText());
        try {
            if(userRepository.findUser(user)){
                SceneChanger sceneChanger = new SceneChanger();
                sceneChanger.changeScene("/view/Dashboard.fxml","Dashboard",event);
            }
            else{
                Alerts.showError("Wrong username or password");
            }
        } catch (SQLException | IOException e) {
            Alerts.showError(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
    }
    public void goToRegister(ActionEvent event) throws IOException {
        SceneChanger sceneChanger = new SceneChanger();
        sceneChanger.changeScene("/view/Register.fxml","Register",event);
    }
}
