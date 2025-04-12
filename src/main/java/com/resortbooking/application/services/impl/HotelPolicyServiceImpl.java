package com.resortbooking.application.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.resortbooking.application.dao.HotelPolicyRepository;
import com.resortbooking.application.dao.HotelRepository;
import com.resortbooking.application.dto.HotelPolicyDTO;
import com.resortbooking.application.mappers.HotelPolicyMapper;
import com.resortbooking.application.models.Hotel;
import com.resortbooking.application.models.HotelPolicy;
import com.resortbooking.application.services.HotelPolicyService;

@Service
public class HotelPolicyServiceImpl implements HotelPolicyService {

    private final HotelPolicyRepository policyRepo;
    private final HotelRepository hotelRepo;
    private final HotelPolicyMapper mapper;

    // Manual constructor instead of @RequiredArgsConstructor
    public HotelPolicyServiceImpl(
            HotelPolicyRepository policyRepo,
            HotelRepository hotelRepo,
            HotelPolicyMapper mapper) {
        this.policyRepo = policyRepo;
        this.hotelRepo = hotelRepo;
        this.mapper = mapper;
    }

    @Override
    public HotelPolicyDTO addPolicy(HotelPolicyDTO dto) {
        try {
            Hotel hotel = hotelRepo.findById(dto.getHotelId())
                    .orElseThrow(() -> new RuntimeException("Hotel not found"));
            HotelPolicy policy = mapper.toEntity(dto, hotel);
            return mapper.toDTO(policyRepo.save(policy));
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error adding policy: " + e.getMessage());
            throw new RuntimeException("Error adding policy: " + e.getMessage());
        }
    }

    @Override
    public List<HotelPolicyDTO> getPoliciesByHotel(Long hotelId) {
        try {
            return policyRepo.findByHotelId(hotelId).stream()
                    .map(mapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error fetching policies by hotel: " + e.getMessage());
            throw new RuntimeException("Error fetching policies by hotel: " + e.getMessage());
        }
    }

    @Override
    public void deletePolicy(Long id) {
        try {
            policyRepo.deleteById(id);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error deleting policy: " + e.getMessage());
            throw new RuntimeException("Error deleting policy: " + e.getMessage());
        }
    }
}
