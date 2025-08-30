package com.RidgeRush.service;

import com.RidgeRush.dto.request.RiderUpdateDto;
import com.RidgeRush.dto.request.RidersSignUpDto;
import com.RidgeRush.dto.response.AppResponse;
import com.RidgeRush.dto.response.RiderDtoResponse;

import java.util.List;

public interface RiderService {
    AppResponse<String> signupRiders(RidersSignUpDto ridersSignUpDto);

    AppResponse<RiderDtoResponse> getRiderById(String id);

    AppResponse<List<RiderDtoResponse>> getAllRiders();

    AppResponse<RiderDtoResponse> updateRider(String id, RiderUpdateDto updateDto);
}
