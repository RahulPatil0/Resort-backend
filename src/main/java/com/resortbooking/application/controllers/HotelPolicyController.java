//package com.resortbooking.application.controllers;
//
//import java.util.List;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.resortbooking.application.dto.HotelPolicyDTO;
//import com.resortbooking.application.services.HotelPolicyService;
//
//@RestController
//@RequestMapping("/api/hotel-policies")
//public class HotelPolicyController {
//
//    private final HotelPolicyService policyService;
//
//    public HotelPolicyController(HotelPolicyService policyService) {
//        this.policyService = policyService;
//    }
//
//    @PostMapping
//    public ResponseEntity<HotelPolicyDTO> addPolicy(@RequestBody HotelPolicyDTO dto) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(policyService.addPolicy(dto));
//    }
//
//    @GetMapping("/hotel/{hotelId}")
//    public ResponseEntity<List<HotelPolicyDTO>> getByHotel(@PathVariable Long hotelId) {
//        return ResponseEntity.ok(policyService.getPoliciesByHotel(hotelId));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletePolicy(@PathVariable Long id) {
//        policyService.deletePolicy(id);
//        return ResponseEntity.noContent().build();
//    }
//}

package com.resortbooking.application.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.resortbooking.application.dto.HotelPolicyDTO;
import com.resortbooking.application.services.HotelPolicyService;

@RestController
@RequestMapping("/api/hotel-policies")
public class HotelPolicyController {

    private final HotelPolicyService policyService;

    public HotelPolicyController(HotelPolicyService policyService) {
        this.policyService = policyService;
    }

    // 🔹 Add new policy
    @PostMapping
    public ResponseEntity<?> addPolicy(@RequestBody HotelPolicyDTO dto) {
        try {
            HotelPolicyDTO created = policyService.addPolicy(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add hotel policy: " + e.getMessage());
        }
    }

    // 🔹 Get policies by hotel ID
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<?> getByHotel(@PathVariable Long hotelId) {
        try {
            List<HotelPolicyDTO> policies = policyService.getPoliciesByHotel(hotelId);
            return ResponseEntity.ok(policies);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve policies: " + e.getMessage());
        }
    }

    // 🔹 Delete policy by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePolicy(@PathVariable Long id) {
        try {
            policyService.deletePolicy(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete policy: " + e.getMessage());
        }
    }
}
