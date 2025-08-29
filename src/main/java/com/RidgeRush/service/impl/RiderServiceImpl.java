package com.RidgeRush.service.impl;

import com.RidgeRush.dto.request.RiderUpdateDto;
import com.RidgeRush.dto.request.RidersSignUpDto;
import com.RidgeRush.dto.response.AppResponse;
import com.RidgeRush.dto.response.RiderDtoResponse;
import com.RidgeRush.entity.Rider;
import com.RidgeRush.exception.ApiException;
import com.RidgeRush.repository.RidersRepository;
import com.RidgeRush.service.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {
    private final RidersRepository ridersRepository;


    @Override
    public AppResponse<RiderDtoResponse> signupRiders(RidersSignUpDto ridersSignUpDto) {
        boolean check = ridersRepository.existsByEmail(ridersSignUpDto.getEmail());

        if (check) throw new ApiException("user already exists");

        Rider rider = Rider.builder()
                .email(ridersSignUpDto.getEmail())
                .firstName(ridersSignUpDto.getFirstName())
                .lastName(ridersSignUpDto.getLastName())
                .build();

        Rider savedRider = ridersRepository.save(rider);


        return new AppResponse<>("successful", new RiderDtoResponse(savedRider)) ;
    }

    @Override
    public AppResponse<RiderDtoResponse> getRiderById(String id) {
        Rider rider = ridersRepository.findById(id)
                .orElseThrow(() -> new ApiException("Rider not found"));
        return new AppResponse<>("successful", new RiderDtoResponse(rider));
    }

    @Override
    public AppResponse<List<RiderDtoResponse>> getAllRiders() {
        List<RiderDtoResponse> riders = ridersRepository.findAll()
                .stream()
                .map(RiderDtoResponse::new)
                .collect(Collectors.toList());
        return new AppResponse<>("successful", riders);
    }

    @Override
    public AppResponse<RiderDtoResponse> updateRider(String id, RiderUpdateDto updateDto) {
        Rider rider = ridersRepository.findById(id)
                .orElseThrow(() -> new ApiException("Rider not found"));

        if (updateDto.getFirstName() != null) {
            rider.setFirstName(updateDto.getFirstName());
        }
        if (updateDto.getLastName() != null) {
            rider.setLastName(updateDto.getLastName());
        }
        if (updateDto.getEmail() != null) {
            rider.setEmail(updateDto.getEmail());
        }

        Rider updatedRider = ridersRepository.save(rider);
        return new AppResponse<>("updated successfully", new RiderDtoResponse(updatedRider));
    }
}
