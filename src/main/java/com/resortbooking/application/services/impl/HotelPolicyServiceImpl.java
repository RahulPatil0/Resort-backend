package com.resortbooking.application.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resortbooking.application.dao.HotelPolicyRepository;
import com.resortbooking.application.dao.HotelRepository;
import com.resortbooking.application.dto.HotelPolicyDTO;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.mappers.HotelPolicyMapper;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.HotelPolicy;
import com.resortbooking.application.services.HotelPolicyService;

@Service
public class HotelPolicyServiceImpl implements HotelPolicyService {

    @Autowired
    private HotelPolicyRepository policyRepo;

    @Autowired
    private HotelRepository hotelRepo;

    @Autowired
    private HotelPolicyMapper mapper; // ✅ Fixed: This should be autowired

    @Override
    public HotelPolicyDTO addPolicy(HotelPolicyDTO dto) throws ResortBookingException {
        try {
            Hotel hotel = hotelRepo.findById(dto.getHotelId())
                    .orElseThrow(() -> new ResortBookingException("Hotel not found with ID: " + dto.getHotelId()));
            HotelPolicy policy = mapper.toEntity(dto, hotel);
            return mapper.toDTO(policyRepo.save(policy));
        } catch (Exception e) {
            throw new ResortBookingException("Error adding policy: " + e.getMessage());
        }
    }

    @Override
    public List<HotelPolicyDTO> getPoliciesByHotel(Long hotelId) throws ResortBookingException {
        try {
            return policyRepo.findByHotelId(hotelId).stream()
                    .map(mapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResortBookingException("Error fetching policies by hotel: " + e.getMessage());
        }
    }

    @Override
    public void deletePolicy(Long id) throws ResortBookingException {
        try {
            policyRepo.deleteById(id);
        } catch (Exception e) {
            throw new ResortBookingException("Error deleting policy: " + e.getMessage());
        }
    }

    @Override
    public HotelPolicyDTO updatePolicy(Long id, HotelPolicyDTO dto) throws ResortBookingException {
        try {
            HotelPolicy existing = policyRepo.findById(id)
                    .orElseThrow(() -> new ResortBookingException("Policy not found with ID: " + id));

            // Optional: validate new hotel
            Hotel hotel = hotelRepo.findById(dto.getHotelId())
                    .orElseThrow(() -> new ResortBookingException("Hotel not found with ID: " + dto.getHotelId()));

            existing.setPolicyType(dto.getPolicyType());
            existing.setDescription(dto.getDescription());
            existing.setHotel(hotel); // 🛠 set hotel entity instead of hotelId

            HotelPolicy saved = policyRepo.save(existing);
            return mapper.toDTO(saved);
        } catch (Exception e) {
            throw new ResortBookingException("Error updating policy: " + e.getMessage());
        }
    }
}
