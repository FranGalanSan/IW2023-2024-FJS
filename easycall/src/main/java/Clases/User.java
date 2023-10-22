import java.util.Date;
import java.util.List;

class User {
    private int userID;
    private String userLogin;
    private String userPassword;
    private UserProfile userDetails;
    private List<Contract> contracts;
    private BillingData billingInfo;

    public User(int userID,String userLogin,String userPassword,UserProfile userDetails,List<Contract> contracts,BillingData billingInfo) {
        this.userID=userID;
        this.userLogin=userLogin;
        this.userPassword=userPassword;
        this.userDetails=userDetails;
        this.contracts=contracts;
        this.billingInfo=billingInfo;
    }

    public void viewUsageDetails() {
        // Implementación para ver detalles de uso
    }

    public void obtainInvoice() {
        // Implementación para obtener una factura
    }

    public void manageServiceOptions() {
        // Implementación para gestionar opciones de servicio
    }

    public void submitUserRequest() {
        // Implementación para enviar una solicitud de usuario
    }
}
