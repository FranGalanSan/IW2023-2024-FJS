package com.easycall.project.llamadas;
import com.easycall.project.llamadas.LlamadaRepository;
import org.springframework.stereotype.Service;
import com.easycall.project.data.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class LlamadaService {

    private final LlamadaRepository llamadaRepository;

    public LlamadaService(LlamadaRepository llamadaRepository) {
        this.llamadaRepository = llamadaRepository;
    }

    public void addNumeroALlamada(User user, String numero) {
        Llamada llamada = llamadaRepository.findFirstByUser(user);
        if (llamada == null) {
            llamada = new Llamada();
            llamada.setUser(user);
            llamada.setNumeros(new ArrayList<>());
            llamada.setFechaHora(LocalDateTime.now()); // Establece la fecha y hora actuales
        }
        llamada.getNumeros().add(numero);
        llamadaRepository.save(llamada);
    }
    public List<Llamada> getLlamadasPorUsuario(User user) {
        return llamadaRepository.findAllByUser(user);
    }

    public int contarLlamadasMesActual(User user) {
        LocalDateTime inicioMes = YearMonth.now().atDay(1).atStartOfDay();
        LocalDateTime finMes = YearMonth.now().atEndOfMonth().atTime(23, 59, 59);
        List<Llamada> llamadasMes = llamadaRepository.findByUserAndFechaHoraBetween(user, inicioMes, finMes);
        return llamadasMes.size();
    }
}