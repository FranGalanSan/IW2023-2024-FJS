package com.easycall.project.usoDatos;
import com.easycall.project.data.user.User;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class UsoDatos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ElementCollection
    private List<Integer> datosDiarios; // Lista de 31 elementos para los datos consumidos cada día

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

    public List<Integer> getDatosDiarios() {
        return datosDiarios;
    }

    public void setDatosDiarios(List<Integer> datosDiarios) {
        this.datosDiarios = datosDiarios;
    }
}
