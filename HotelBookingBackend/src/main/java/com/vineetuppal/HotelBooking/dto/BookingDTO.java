package com.vineetuppal.HotelBooking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vineetuppal.HotelBooking.entity.Room;
import com.vineetuppal.HotelBooking.entity.User;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDTO {

    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numberOfAdults;
    private int numOfChildren;
    private int totalNumberOfGuest;
    private String bookingConfirmationCode;
    private UserDTO user;
    private RoomDTO room;
}
