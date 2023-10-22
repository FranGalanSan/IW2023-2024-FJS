import java.util.Date;
import java.util.List;

class Task {
    private int taskID;
    private String taskType;
    private String taskDescription;
    private String taskStatus;
    private Employee assignedTo;
    private User requestedBy;

    public Task(int taskID,String taskType,String taskDescription,String taskStatus,Employee assignedTo,User requestedBy) {
        this.taskID=taskID;
        this.taskType=taskType;
        this.taskDescription=taskDescription;
        this.taskStatus=taskStatus;
        this.assignedTo=assignedTo;
        this.requestedBy=requestedBy;
    }


    public void viewTaskDetails() {
        // Implementación para ver detalles de la tarea
    }

    public void updateTaskStatus() {
        // Implementación para actualizar el estado de la tarea
    }

    public void assignTask() {
        // Implementación para asignar la tarea
    }

    public void closeTask() {
        // Implementación para cerrar la tarea
    }
}
