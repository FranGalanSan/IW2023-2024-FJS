package com.easycall.project.complaints;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    public List<Complaint> findAllComplaints() {
        return complaintRepository.findAll();
    }

    public void updateComplaintResponse(int complaintId, String response) {
        Optional<Complaint> complaintOptional = complaintRepository.findById(complaintId);
        complaintOptional.ifPresent(complaint -> {
            complaint.setRespuesta(response);
            complaintRepository.save(complaint);
        });
    }

    public void toggleComplaintOpenStatus(int complaintId, boolean open) {
        Optional<Complaint> complaintOptional = complaintRepository.findById(complaintId);
        complaintOptional.ifPresent(complaint -> {
            complaint.setOpen(open);
            complaintRepository.save(complaint);
        });
    }

    public void createComplaint(Complaint complaint) {
        complaintRepository.save(complaint);
    }
}
