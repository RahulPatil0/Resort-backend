//package com.resortbooking.application.controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.resortbooking.application.dto.HotelPolicyDTO;
//import com.resortbooking.application.dto.ResortBookingResponse;
//import com.resortbooking.application.exception.ResortBookingException;
//import com.resortbooking.application.services.HotelPolicyService;
//
//@RestController
//@RequestMapping("/api/policies")
//@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
//public class HotelPolicyController {
//
//    @Autowired
//    private HotelPolicyService hotelPolicyService;
//
//    @PostMapping
//    public ResponseEntity<ResortBookingResponse> addPolicy(@RequestBody HotelPolicyDTO policyDTO) throws ResortBookingException {
//        hotelPolicyService.addPolicy(policyDTO);
//        return new ResponseEntity<>(
//                new ResortBookingResponse("Policy created successfully", HttpStatus.CREATED),
//                HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<HotelPolicyDTO> getPolicyById(@PathVariable Long id) throws ResortBookingException {
//        return ResponseEntity.ok(hotelPolicyService.getPolicyById(id));
//    }
//
//    @GetMapping("/hotel/{hotelId}")
//    public ResponseEntity<List<HotelPolicyDTO>> getPoliciesByHotelId(@PathVariable Long hotelId) throws ResortBookingException {
//        return ResponseEntity.ok(hotelPolicyService.getPoliciesByHotelId(hotelId));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ResortBookingResponse> updatePolicy(
//            @PathVariable Long id,
//            @RequestBody HotelPolicyDTO policyDTO) throws ResortBookingException {
//
//        hotelPolicyService.updatePolicy(id, policyDTO);
//        return ResponseEntity.ok(new ResortBookingResponse("Policy updated successfully", HttpStatus.OK));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ResortBookingResponse> deletePolicy(@PathVariable Long id) throws ResortBookingException {
//        hotelPolicyService.deletePolicy(id);
//        return ResponseEntity.ok(new ResortBookingResponse("Policy deleted successfully", HttpStatus.OK));
//    }
//}
