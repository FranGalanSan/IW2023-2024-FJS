
import java.util.Date;
import java.util.List;


class Invoice {
    private int invoiceID;
    private User relatedUser;
    private Date issueDate;
    private Date dueDate;
    private float invoiceAmount;
    private String invoiceStatus;

    public Invoice(int invoiceID,User relatedUser,Date issueDate,Date dueDate,float invoiceAmount,String invoiceStatus) {
        this.invoiceID=invoiceID;
        this.relatedUser=relatedUser;
        this.issueDate=issueDate;
        this.dueDate=dueDate;
        this.invoiceAmount=invoiceAmount;
        this.invoiceStatus=invoiceStatus;
    }

    public void viewInvoiceDetails() {
        // Implementación para ver detalles de la factura
    }

    public void generateInvoice() {
        // Implementación para generar la factura
    }

    public void sendInvoice() {
        // Implementación para enviar la factura
    }

    public void checkPaymentStatus() {
        // Implementación para verificar el estado de pago
    }
}
