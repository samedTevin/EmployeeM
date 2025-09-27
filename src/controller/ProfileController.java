package controller;

import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;


import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    public ProgressBar progressBar;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        progressBar.setVisible(true);
        progressBar.setProgress(-1);

        progressBar.setVisible(false);
    }
}
