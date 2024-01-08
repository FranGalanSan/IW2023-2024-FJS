package com.easycall.project.invoice;

import com.easycall.project.data.user.User;
import com.easycall.project.service.Servicee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice createOrUpdateInvoice(User user, List<Servicee> newServices) throws Exception {
        int currentMonth = LocalDate.now().getMonthValue();
        int currentYear = LocalDate.now().getYear();

        Optional<Invoice> existingInvoice = invoiceRepository.findByUserAndMesAndAnno(user, currentMonth, currentYear);

        if (existingInvoice.isPresent()) {
            // Añadir servicios a la factura existente
            Invoice invoice = existingInvoice.get();
            Set<Servicee> existingServicesSet = new HashSet<>(invoice.getServices());

            // Verifica si alguno de los nuevos servicios ya está en la factura
            for (Servicee service : newServices) {
                if (existingServicesSet.contains(service)) {
                    throw new Exception("El servicio ya está incluido en la factura.");
                }
            }

            existingServicesSet.addAll(newServices);
            invoice.setServices(new ArrayList<>(existingServicesSet));
            return invoiceRepository.save(invoice);
        } else {
            // Crear una nueva factura con los nuevos servicios
            Invoice newInvoice = new Invoice();
            newInvoice.setUser(user);
            newInvoice.setServices(new ArrayList<>(new HashSet<>(newServices))); // Aquí se asume que no hay duplicados, ya que es una factura nueva
            newInvoice.setMes(currentMonth);
            newInvoice.setAnno(currentYear);
            return invoiceRepository.save(newInvoice);
        }
    }

    // Aquí puedes agregar otros métodos relacionados con Invoice si los necesitas
}
