package com.easycall.project.usoDatos;
import org.springframework.stereotype.Service;
import com.easycall.project.usoDatos.UsoDatos;
import com.easycall.project.usoDatos.UsoDatosRepository;
import com.easycall.project.data.user.User;
import com.easycall.project.data.user.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UsoDatosService {
    private final UsoDatosRepository usoDatosRepository;
    private final UserRepository userRepository;

    public UsoDatosService(UsoDatosRepository usoDatosRepository, UserRepository userRepository) {
        this.usoDatosRepository = usoDatosRepository;
        this.userRepository = userRepository;
    }

    public void añadirUsoDatosAUsuario(Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            UsoDatos usoDatos = new UsoDatos();
            usoDatos.setUser(user);
            usoDatos.setDatosDiarios(generarDatosAleatorios());
            usoDatosRepository.save(usoDatos);
        }
    }

    private List<Integer> generarDatosAleatorios() {
        List<Integer> datos = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 31; i++) {
            datos.add(random.nextInt(2049)); // Números entre 0 y 2048
        }
        return datos;
    }

    public int obtenerConsumoDatosHoy(User user) {
        UsoDatos usoDatos = usoDatosRepository.findByUser(user);
        if (usoDatos != null && !usoDatos.getDatosDiarios().isEmpty()) {
            int diaHoy = LocalDate.now().getDayOfMonth();
            return usoDatos.getDatosDiarios().get(diaHoy - 1); // Obtiene el dato del día actual
        }
        return 0;
    }

    public int obtenerConsumoDatosMes(User user) {
        UsoDatos usoDatos = usoDatosRepository.findByUser(user);
        if (usoDatos != null && !usoDatos.getDatosDiarios().isEmpty()) {
            int diaHoy = LocalDate.now().getDayOfMonth();
            return usoDatos.getDatosDiarios().stream().limit(diaHoy).mapToInt(Integer::intValue).sum();
        }
        return 0;
    }

}
