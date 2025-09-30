package controller;

import helper.Alerts;
import helper.SceneChanger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;
import repository.EmployeeRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    public TextField search;
    public TextField name;
    public TextField position;
    public TextField salary;
    public ComboBox<String> department;
    public CheckBox status;
    public Slider rating;

    public TableView<Employee> employeeTable;
    public TableColumn<Employee, Integer> idColumn;
    public TableColumn<Employee, String> nameColumn;
    public TableColumn<Employee, String> positionColumn;
    public TableColumn<Employee, String> salaryColumn;
    public TableColumn<Employee, String> departmentColumn;
    public TableColumn<Employee, String> statusColumn;
    public TableColumn<Employee, Integer> ratingColumn;
    private ObservableList<Employee> employees = FXCollections.observableArrayList();
    EmployeeRepository employeeRepository = new EmployeeRepository();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        department.getItems().addAll("Management","Finance", "Production", "Engineering", "IT/Software","Legal");

        employeeTable.setItems(employees);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));

        refreshTable();
    }

    public void addEmployee(ActionEvent actionEvent) throws IOException { // OK
        try{
            Employee employee = new Employee(name.getText(),
                    position.getText(),
                    department.getValue(),
                    Double.parseDouble(salary.getText()),
                    status.isSelected() ? "Active" : "Inactive",
                    rating.getValue());

            employeeRepository.add(employee);
            refreshTable();

            Alerts.showInformation("Employee Added");

            name.clear();
            position.clear();
            salary.clear();
            department.getSelectionModel().clearSelection();
            status.setSelected(false);
            rating.setValue(0);

        }
        catch (SQLException e){
            Alerts.showError("Database Error:" + e.getMessage());
        }
        catch(NumberFormatException nfe){
            Alerts.showError("Salary must be a number!");
        }
        catch (Exception e){
            Alerts.showError(e.getMessage());
        }

        System.out.println(status.getText());

    }

    public void deleteEmployee(ActionEvent actionEvent) throws IOException {

        Employee selected = employeeTable.getSelectionModel().getSelectedItem();

        if(selected != null){
            try{
               employeeRepository.remove(selected);
               refreshTable();
               Alerts.showInformation("Employee Deleted");
            }
            catch(SQLException e){
                Alerts.showError("Database Error: " + e.getMessage());
            }
            catch(Exception e){
                Alerts.showError("Error: " + e.getMessage());
            }
        }
        else{
            Alerts.showError("Employee Not Selected");
        }

    }

    public void updateEmployee(ActionEvent actionEvent) throws IOException {

    }

    public void getAllEmployee(ActionEvent actionEvent) throws IOException {

    }
    public void searchEmployee(ActionEvent actionEvent) throws IOException {

    }

    public void refreshTable(){
        try {
            employees.setAll(employeeRepository.show());
        } catch (SQLException e) {
            Alerts.showError("Database Error:" + e.getMessage());
        }
    }

    public void goToDashboard(ActionEvent event) throws IOException {
        SceneChanger sceneChanger = new SceneChanger();
        sceneChanger.changeScene("/view/Dashboard.fxml","Dashboard",event);
    }


}
