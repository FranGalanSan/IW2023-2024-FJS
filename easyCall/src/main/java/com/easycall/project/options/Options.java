package com.easycall.project.options;


import com.easycall.project.data.user.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "options")
public class Options {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int megasDisponibles;
    private boolean roaming;
    private boolean bloquearNumeros905;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> numerosBloqueados;

    @OneToOne(mappedBy = "options")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMegasDisponibles() {
        return megasDisponibles;
    }

    public void setMegasDisponibles(int megasDisponibles) {
        this.megasDisponibles = megasDisponibles;
    }

    public boolean isRoaming() {
        return roaming;
    }

    public void setRoaming(boolean roaming) {
        this.roaming = roaming;
    }

    public boolean isBloquearNumeros905() {
        return bloquearNumeros905;
    }

    public void setBloquearNumeros905(boolean bloquearNumeros905) {
        this.bloquearNumeros905 = bloquearNumeros905;
    }

    public List<String> getNumerosBloqueados() {
        return numerosBloqueados;
    }

    public void setNumerosBloqueados(List<String> numerosBloqueados) {
        this.numerosBloqueados = numerosBloqueados;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
