import java.util.Date;
import java.util.List;



class Option {
    private int optionID;
    private String optionName;
    private String optionType;
    private float optionPrice;


    public Option(int optionID,String optionName,String optionType,float optionPrice) {
        this.optionID=optionID;
        this.optionName=optionName;
        this.optionType=optionType;
        this.optionPrice=optionPrice;
    }

    public void viewOptionDetails() {
        // Implementación para ver detalles de la opción
    }

    public void configureOption() {
        // Implementación para configurar la opción
    }

    public void activateOption() {
        // Implementación para activar la opción
    }

    public void deactivateOption() {
        // Implementación para desactivar la opción
    }
}
