import java.util.Date;
import java.util.List;

class Service {
    private int serviceID;
    private String serviceName;
    private String serviceType;
    private List<Rate> serviceRates;



    public Service(int serviceID,String serviceName,String serviceType,List<Rate> serviceRates) {
        this.serviceID=serviceID;
        this.serviceName=serviceName;
        this.serviceType=serviceType;
        this.serviceRates=serviceRates;
    }


    public void viewServiceDetails() {
        // Implementación para ver detalles del servicio
    }

    public void manageRates() {
        // Implementación para gestionar tarifas del servicio
    }

    public void configureOptions() {
        // Implementación para configurar opciones del servicio
    }

    public void customizeService() {
        // Implementación para personalizar el servicio
    }
}
