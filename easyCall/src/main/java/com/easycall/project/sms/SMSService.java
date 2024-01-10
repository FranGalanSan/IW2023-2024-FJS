package com.easycall.project.sms;

import org.springframework.stereotype.Service;
import com.easycall.project.sms.SMS;
import com.easycall.project.data.user.User;

import java.util.List;

@Service
public class SMSService {

    private final SMSRepository smsRepository;

    public SMSService(SMSRepository smsRepository) {
        this.smsRepository = smsRepository;
    }

    public void enviarSMS(User user, String numeroDestinatario, String contenidoSMS) {
        SMS sms = new SMS();
        sms.setUser(user);
        sms.setNumeroDestinatario(numeroDestinatario);
        sms.setContenidoSMS(contenidoSMS);
        smsRepository.save(sms);
    }
    public List<SMS> obtenerSMSRecibidos(String numeroTelefono) {
        return smsRepository.findByNumeroDestinatario(numeroTelefono);
    }

    public List<SMS> obtenerSMSEnviados(User user) {
        return smsRepository.findByUser(user);
    }

}