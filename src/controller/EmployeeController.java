package controller;

import helper.Alerts;
import helper.SceneChanger;

import helper.TextFieldUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;
import repository.EmployeeRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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

        TextFieldUtils.setOnlyLetters(name);
        TextFieldUtils.setOnlyLetters(position);

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

    public void addEmployee(){

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

    }

    public void deleteEmployee(){

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

    public void updateEmployee() throws SQLException, IOException {

        Employee selected = employeeTable.getSelectionModel().getSelectedItem();
        if(selected != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpdateEmployeeForm.fxml"));
            DialogPane dialogPane = loader.load();

            UpdateEmployeeController controller = loader.getController();
            controller.setEmployee(selected);

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(dialogPane);
            dialog.setTitle("Update Employee");
            dialog.getDialogPane().setExpandableContent(null);
            Optional<ButtonType> result = dialog.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK){
                try{
                    Employee updated = controller.getUpdatedEmployee();
                    employeeRepository.update(updated);
                    Alerts.showInformation("Employee Updated");
                    refreshTable();
                }
                catch(NumberFormatException npe){
                    Alerts.showError("Salary must be a number!");
                }
            }
            else{
                Alerts.showError("Employee not updated");
            }
        }
        else{
            Alerts.showError("Employee Not Selected");
        }

    }

    public void getAllEmployee(){
        try{
            employees.setAll(employeeRepository.show());
        } catch (SQLException e) {
            Alerts.showError("Database Error: " + e.getMessage());
        }
    }

    public void searchEmployee(){
        try{
            int id = Integer.parseInt(search.getText());
            Employee employee = employeeRepository.search(id);
            if(employee != null){
               employees.setAll(employee);
                Alerts.showInformation("Employee Searched");
            }
            else{
                Alerts.showError("Employee Not Found");
                refreshTable();
            }

            search.clear();
        }
        catch(NumberFormatException nfe){
            Alerts.showError("Employee ID must be a number!");
        }
        catch(SQLException e){
            Alerts.showError("Database Error: " + e.getMessage());
        }
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
