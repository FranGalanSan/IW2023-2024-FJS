package com.easycall.project.complaints;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    public List<Complaint> findAllComplaints() {
        return complaintRepository.findAll();
    }

    public void updateComplaintResponse(int id, String response) {
        Complaint complaint = complaintRepository.findById(id).orElseThrow();
        complaint.setRespuesta(response);
        complaintRepository.save(complaint);
    }
}
