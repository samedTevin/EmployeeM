package controller;

import helper.Alerts;
import helper.SceneChanger;
import helper.UserSession;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import repository.UserRepository;

import java.io.IOException;
import java.sql.SQLException;


public class LoginController {

    public TextField username;
    public PasswordField password;
    UserRepository userRepository = new UserRepository();

    public void loginUser(ActionEvent event){
        User user = new User(username.getText(),password.getText());
        try {
            if(userRepository.findUser(user)){
                User detailedUser = userRepository.getUserDetails(user.getUsername());
                if(detailedUser != null){
                    UserSession.id = detailedUser.getId();
                    UserSession.username = detailedUser.getUsername();
                    UserSession.password = detailedUser.getPassword();
                    UserSession.dailyNotes = detailedUser.getDailyNotes();
                    UserSession.created = detailedUser.getCreatedAt();
                    UserSession.updated = detailedUser.getUpdatedAt();
                }
                SceneChanger sceneChanger = new SceneChanger();
                sceneChanger.changeScene("/view/Dashboard.fxml","Dashboard",event);
            }
            else{
                Alerts.showError("Wrong username or password");
            }
        } catch (SQLException | IOException e) {
            Alerts.showError(e.getMessage());
        }
    }
    public void goToRegister(ActionEvent event) throws IOException {
        SceneChanger sceneChanger = new SceneChanger();
        sceneChanger.changeScene("/view/Register.fxml","Register",event);
    }
}
