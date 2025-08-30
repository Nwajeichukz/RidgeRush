package com.RidgeRush.controller;

import com.RidgeRush.dto.request.RiderUpdateDto;
import com.RidgeRush.dto.request.RidersSignUpDto;
import com.RidgeRush.dto.response.AppResponse;
import com.RidgeRush.dto.response.RiderDtoResponse;
import com.RidgeRush.service.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("rider")
public class RidersController {
    private final RiderService riderService;

    @PostMapping("/signup")
    ResponseEntity<AppResponse<String>> signupRiders (@RequestBody @Valid RidersSignUpDto ridersSignUpDto){
        return ResponseEntity.ok(riderService.signupRiders(ridersSignUpDto));
    }

    @GetMapping("/{id}")
    ResponseEntity<AppResponse<RiderDtoResponse>> getRider(@PathVariable String id) {
        return ResponseEntity.ok(riderService.getRiderById(id));
    }

    @GetMapping
    ResponseEntity<AppResponse<List<RiderDtoResponse>>> getAllRiders() {
        return ResponseEntity.ok(riderService.getAllRiders());
    }

    @PutMapping("/{id}")
    ResponseEntity<AppResponse<RiderDtoResponse>> updateRider(@PathVariable String id,
                                                           @RequestBody @Valid RiderUpdateDto updateDto) {
        return ResponseEntity.ok(riderService.updateRider(id, updateDto));
    }




}
