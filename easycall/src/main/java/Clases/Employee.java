import java.util.Date;
import java.util.List;

class Employee {
    private int employeeID;
    private String employeeLogin;
    private String employeePassword;
    private String department;
    private List<Task> assignedTasks;

    public Employee(int employeeID,String employeeLogin,String employeePassword,String department,List<Task> assignedTasks) {
        this.employeeID=employeeID;
        this.employeeLogin=employeeLogin;
        this.employeePassword=employeePassword;
        this.department=department;
        this.assignedTasks=assignedTasks;
    }

    public void employeeLogIn() {
        // Implementación para el inicio de sesión del empleado
    }

    public void manageServiceFeatures() {
        // Implementación para gestionar características de servicio
    }

    public void manageContracts() {
        // Implementación para gestionar contratos
    }

    public void generateInvoices() {
        // Implementación para generar facturas
    }

    public void processUserRequests() {
        // Implementación para procesar solicitudes de usuario
    }
}