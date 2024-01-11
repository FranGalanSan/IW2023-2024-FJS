package com.easycall.project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<Servicee> findAllServices() {
        return serviceRepository.findAll();
    }

    public Servicee findServiceById(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }

    public void updateService(Servicee service) {
        Servicee existingService = serviceRepository.findById(service.getServiceId()).orElse(null);
        if (existingService != null) {
            existingService.setNombre(service.getNombre());
            existingService.setPrecio(service.getPrecio());
            existingService.setDescripcion(service.getDescripcion());
            serviceRepository.save(existingService);
        }
    }


    public Servicee saveService(Servicee service) {
        return serviceRepository.save(service);
    }


    public void deleteService(Long serviceId) {
        serviceRepository.deleteById(serviceId);
    }
}
