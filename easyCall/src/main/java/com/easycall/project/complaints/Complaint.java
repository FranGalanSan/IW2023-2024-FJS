package com.easycall.project.complaints;



import jakarta.persistence.*;

@Entity
@Table(name = "complaint")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idreclamacion;

    @Column(length = 100)
    private String issue;

    @Column(length = 400)
    private String text;

    private boolean open;

    @Column(length = 400)
    private String respuesta;
    public int getIdreclamacion() {
        return idreclamacion;
    }

    public void setIdreclamacion(int idreclamacion) {
        this.idreclamacion = idreclamacion;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
