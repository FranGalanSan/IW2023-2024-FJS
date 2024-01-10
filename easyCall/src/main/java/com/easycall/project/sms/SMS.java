package com.easycall.project.sms;
import com.easycall.project.data.user.User;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class SMS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String contenidoSMS;
    private String numeroDestinatario;

    public String getNumeroDestinatario() {
        return numeroDestinatario;
    }

    public void setNumeroDestinatario(String numeroDestinatario) {
        this.numeroDestinatario = numeroDestinatario;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContenidoSMS() {
        return contenidoSMS;
    }

    public void setContenidoSMS(String contenidoSMS) {
        this.contenidoSMS = contenidoSMS;
    }
}
