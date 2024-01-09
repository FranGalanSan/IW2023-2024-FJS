package com.easycall.project.complaints;


import com.easycall.project.data.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "complaint")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idreclamacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 100)
    private String issue;

    @Column(length = 400)
    private String text;

    private boolean open;

    @Column(length = 400)
    private String respuesta;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
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
