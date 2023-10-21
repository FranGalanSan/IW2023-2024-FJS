import java.util.Date;
import java.util.List;


class Rate {
    private int rateID;
    private String rateName;
    private float rateAmount;
    private String rateType;


    public Rate(int rateID,String rateName,float rateAmount,String rateType) {
        this.rateID=rateID;
        this.rateName=rateName;
        this.rateAmount=rateAmount;
        this.rateType=rateType;
    }

    public void viewRateDetails() {
        // Implementación para ver detalles de la tarifa
    }

    public void updateRate() {
        // Implementación para actualizar la tarifa
    }

    public void createRate() {
        // Implementación para crear una nueva tarifa
    }

    public void deleteRate() {
        // Implementación para eliminar una tarifa
    }
}
