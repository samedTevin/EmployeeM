package controller;

import helper.SceneChanger;
import javafx.event.ActionEvent;
import java.io.IOException;

public class ReportController {

    public void goToDashboard(ActionEvent event) throws IOException {
        SceneChanger sceneChanger = new SceneChanger();
        sceneChanger.changeScene("/view/Dashboard.fxml","Dashboard",event);
    }
}
