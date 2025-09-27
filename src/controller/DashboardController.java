package controller;


import helper.SceneChanger;
import helper.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    public Label showName;
    SceneChanger sceneChanger = new SceneChanger();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showName.setText("Dear " + UserSession.username + ",");
    }

    public void goToEmployee(ActionEvent actionEvent) throws IOException {
        sceneChanger.changeScene("/view/Employee.fxml","Employee",actionEvent);
    }

    public void goToReports(ActionEvent actionEvent) throws IOException {
        sceneChanger.changeScene("/view/Report.fxml","Report",actionEvent);
    }

    public void goToProfile(ActionEvent actionEvent) throws IOException {
        sceneChanger.changeScene("/view/Profile.fxml","Profile",actionEvent);
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        sceneChanger.changeScene("/view/Login.fxml","Login",actionEvent);
    }
}
