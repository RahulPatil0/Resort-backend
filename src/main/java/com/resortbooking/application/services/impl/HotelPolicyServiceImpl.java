//package com.resortbooking.application.services.impl;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.resortbooking.application.dao.HotelPolicyRepository;
//import com.resortbooking.application.dao.HotelRepository;
//import com.resortbooking.application.dto.HotelPolicyDTO;
//import com.resortbooking.application.exception.ResortBookingException;
//import com.resortbooking.application.mappers.HotelPolicyMapper;
//import com.resortbooking.application.models.Hotel;
//import com.resortbooking.application.models.HotelPolicy;
//import com.resortbooking.application.services.HotelPolicyService;
//
//@Service
//public class HotelPolicyServiceImpl implements HotelPolicyService {
//
//    @Autowired
//    private HotelPolicyRepository policyRepo;
//
//    @Autowired
//    private HotelRepository hotelRepo;
//
//    @Override
//    public HotelPolicyDTO addPolicy(HotelPolicyDTO dto) throws ResortBookingException {
//        Hotel hotel = hotelRepo.findById(dto.getHotelId())
//                .orElseThrow(() -> new ResortBookingException("Hotel not found with ID: " + dto.getHotelId()));
//        HotelPolicy policy = HotelPolicyMapper.toEntity(dto, hotel);
//        return HotelPolicyMapper.toDTO(policyRepo.save(policy));
//    }
//
//    @Override
//    public List<HotelPolicyDTO> getAllPolicies() throws ResortBookingException {
//        try {
//            return policyRepo.findAll().stream()
//                    .map(HotelPolicyMapper::toDTO)
//                    .collect(Collectors.toList());
//        } catch (Exception e) {
//            throw new ResortBookingException("Error fetching all policies: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public HotelPolicyDTO getPolicyById(Long id) throws ResortBookingException {
//        return policyRepo.findById(id)
//                .map(HotelPolicyMapper::toDTO)
//                .orElseThrow(() -> new ResortBookingException("Policy not found with ID: " + id));
//    }
//
//    @Override
//    public HotelPolicyDTO updatePolicy(Long id, HotelPolicyDTO dto) throws ResortBookingException {
//        HotelPolicy existing = policyRepo.findById(id)
//                .orElseThrow(() -> new ResortBookingException("Policy not found with ID: " + id));
//
//        Hotel hotel = hotelRepo.findById(dto.getHotelId())
//                .orElseThrow(() -> new ResortBookingException("Hotel not found with ID: " + dto.getHotelId()));
//
//        existing.setPolicyType(dto.getPolicyType());
//        existing.setDescription(dto.getDescription());
//        existing.setHotel(hotel);
//
//        return HotelPolicyMapper.toDTO(policyRepo.save(existing));
//    }
//
//    @Override
//    public void deletePolicy(Long id) throws ResortBookingException {
//        if (!policyRepo.existsById(id)) {
//            throw new ResortBookingException("Policy not found with ID: " + id);
//        }
//        policyRepo.deleteById(id);
//    }
//
//    @Override
//    public List<HotelPolicyDTO> getPoliciesByHotelId(Long hotelId) throws ResortBookingException {
//        try {
//            Hotel hotel = hotelRepo.findById(hotelId)
//                    .orElseThrow(() -> new ResortBookingException("Hotel not found with ID: " + hotelId));
//
//            return policyRepo.findByHotel(hotel).stream()
//                    .map(HotelPolicyMapper::toDTO)
//                    .collect(Collectors.toList());
//        } catch (Exception e) {
//            throw new ResortBookingException("Error fetching policies for hotel ID: " + hotelId);
//        }
//    }
//
//}
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

    @Override
    public HotelPolicyDTO addPolicy(HotelPolicyDTO dto) throws ResortBookingException {
        // Ensure ID is null so Hibernate treats it as a new entity
        dto.setId(null);

        Hotel hotel = hotelRepo.findById(dto.getHotelId())
                .orElseThrow(() -> new ResortBookingException("Hotel not found with ID: " + dto.getHotelId()));

        HotelPolicy policy = HotelPolicyMapper.toEntity(dto, hotel);

        return HotelPolicyMapper.toDTO(policyRepo.save(policy));
    }

    @Override
    public List<HotelPolicyDTO> getAllPolicies() throws ResortBookingException {
        try {
            return policyRepo.findAll().stream()
                    .map(HotelPolicyMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResortBookingException("Error fetching all policies: " + e.getMessage());
        }
    }

    @Override
    public HotelPolicyDTO getPolicyById(Long id) throws ResortBookingException {
        return policyRepo.findById(id)
                .map(HotelPolicyMapper::toDTO)
                .orElseThrow(() -> new ResortBookingException("Policy not found with ID: " + id));
    }

    @Override
    public HotelPolicyDTO updatePolicy(Long id, HotelPolicyDTO dto) throws ResortBookingException {
        HotelPolicy existing = policyRepo.findById(id)
                .orElseThrow(() -> new ResortBookingException("Policy not found with ID: " + id));

        Hotel hotel = hotelRepo.findById(dto.getHotelId())
                .orElseThrow(() -> new ResortBookingException("Hotel not found with ID: " + dto.getHotelId()));

        existing.setPolicyType(dto.getPolicyType());
        existing.setDescription(dto.getDescription());
        existing.setHotel(hotel);

        return HotelPolicyMapper.toDTO(policyRepo.save(existing));
    }

    @Override
    public void deletePolicy(Long id) throws ResortBookingException {
        if (!policyRepo.existsById(id)) {
            throw new ResortBookingException("Policy not found with ID: " + id);
        }
        policyRepo.deleteById(id);
    }

    @Override
    public List<HotelPolicyDTO> getPoliciesByHotelId(Long hotelId) throws ResortBookingException {
        try {
            Hotel hotel = hotelRepo.findById(hotelId)
                    .orElseThrow(() -> new ResortBookingException("Hotel not found with ID: " + hotelId));

            return policyRepo.findByHotel(hotel).stream()
                    .map(HotelPolicyMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResortBookingException("Error fetching policies for hotel ID: " + hotelId);
        }
    }
}

