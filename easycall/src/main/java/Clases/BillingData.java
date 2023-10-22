import java.util.Date;
import java.util.List;

class BillingData {
    private float accountBalance;
    private List<Invoice> userInvoices;

    public BillingData(float accountBalance,List<Invoice> userInvoices) {
        this.accountBalance=accountBalance;
        this.userInvoices=userInvoices;
    }


    public void viewBillingInfo() {
        // Implementación para ver información de facturación
    }

    public void addFunds() {
        // Implementación para agregar fondos a la cuenta
    }

    public void viewInvoices() {
        // Implementación para ver facturas
    }

    public void processPayment() {
        // Implementación para procesar el pago
    }
}