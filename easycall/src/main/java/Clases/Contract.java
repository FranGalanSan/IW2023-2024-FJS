import java.util.Date;
import java.util.List;

class Contract {
    private int contractID;
    private Service service;
    private Date startDate;
    private Date endDate;
    private List<Option> options;
    private String status;


    public Contract(int contractID,Service service,Date startDate,Date endDate,List<Option> options,String status) {
        this.contractID=contractID;
        this.service=service;
        this.startDate=startDate;
        this.endDate=endDate;
        this.options=options;
        this.status=status;
    }


    public void viewContractDetails() {
        // Implementación para ver detalles del contrato
    }

    public void manageOptions() {
        // Implementación para gestionar opciones del contrato
    }

    public void renewContract() {
        // Implementación para renovar el contrato
    }

    public void submitUserRequest() {
        // Implementación para enviar una solicitud de usuario
    }
}
