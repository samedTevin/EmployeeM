package helper;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class TextFieldUtils {

    public static void setOnlyLetters(TextField textField) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[a-zA-ZğüşöçıİĞÜŞÖÇ ]*")) {
                return change;
            }
            return null;
        };
        textField.setTextFormatter(new TextFormatter<>(filter));
    }

}
