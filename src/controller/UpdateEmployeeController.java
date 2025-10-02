package controller;


import helper.TextFieldUtils;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import model.Employee;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateEmployeeController implements Initializable {


    public TextField name;
    public TextField position;
    public ComboBox<String> department;
    public TextField salary;
    public CheckBox status;
    public Slider rating;

    private Employee employee;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextFieldUtils.setOnlyLetters(name);
        TextFieldUtils.setOnlyLetters(position);
        department.getItems().addAll("Management","Finance", "Production", "Engineering", "IT/Software","Legal");
    }

    public void setEmployee(Employee employee) {
        this.employee =  employee;
        name.setText(employee.getName());
        position.setText(employee.getPosition());
        department.setValue(employee.getDepartment());
        salary.setText(Double.toString(employee.getSalary()));
        status.setSelected(employee.getStatus().equals("Active"));
        rating.setValue(employee.getRating());

    }

    public Employee getUpdatedEmployee(){

        String newName = name.getText().isEmpty() ? employee.getName() : name.getText();
        String newPosition =  position.getText().isEmpty() ? employee.getPosition() : position.getText();
        String newDepartment = department.getValue().isEmpty() ? employee.getDepartment() : department.getValue();
        double newSalary = salary.getText().isEmpty() ? employee.getSalary() : Double.parseDouble(salary.getText());
        String newStatus = status.isSelected() ? "Active" : "Inactive";
        double newRating = rating.getValue();

        employee.setName(newName);
        employee.setPosition(newPosition);
        employee.setDepartment(newDepartment);
        employee.setSalary(newSalary);
        employee.setStatus(newStatus);
        rating.setValue(newRating);

        return employee;
    }
}
