package com.vineetuppal.HotelBooking.service.interfac;

import com.vineetuppal.HotelBooking.dto.LoginRequest;
import com.vineetuppal.HotelBooking.dto.Response;
import com.vineetuppal.HotelBooking.entity.User;

public interface IUserService {

    Response register(User user);

    Response login(LoginRequest loginRequest);

    Response getAllUsers();

    Response getUserBookingHistory(String userId);

    Response deleteUser(String userId);

    Response getUserById(String userId);

    Response getMyInfo(String email);

}
