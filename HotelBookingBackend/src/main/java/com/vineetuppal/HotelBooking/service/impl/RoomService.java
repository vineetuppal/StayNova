package com.vineetuppal.HotelBooking.service.impl;

import com.vineetuppal.HotelBooking.dto.Response;
import com.vineetuppal.HotelBooking.dto.RoomDTO;
import com.vineetuppal.HotelBooking.entity.Room;
import com.vineetuppal.HotelBooking.exception.OurException;
import com.vineetuppal.HotelBooking.repository.BookingRepo;
import com.vineetuppal.HotelBooking.repository.RoomRepo;
import com.vineetuppal.HotelBooking.service.AwsS3Service;
import com.vineetuppal.HotelBooking.service.interfac.IRoomService;
import com.vineetuppal.HotelBooking.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private AwsS3Service awsS3Service;


    @Override
    public Response addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice, String description) {
        Response response = new Response();

        try {
            String imageUrl = awsS3Service.saveImageToS3(photo);
            Room room = new Room();
            room.setRoomPhotoUrl(imageUrl);
            room.setRoomType(roomType);
            room.setRoomPrice(roomPrice);
            room.setRoomDescription(description);
            Room savedRoom = roomRepo.save(room);
            RoomDTO roomDTO = Utils.mapRoomEntityToRoomDTO(savedRoom);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setRoom(roomDTO);
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurred while adding room:-" + e.getMessage());
        }
        return response;
    }

    @Override
    public List<String> getAllRoomTypes() {
        return roomRepo.findDistinctRoomTypes();
    }

    @Override
    public Response getAllRooms() {
        Response response = new Response();
        try{
            List<Room> roomList = roomRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
            List<RoomDTO> roomDTOList = Utils.mapRoomListEntityToRoomListDTO(roomList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setRoomList(roomDTOList);
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurred while getting all the rooms:-" + e.getMessage());
        }
        return response;
    }

    @Override
    public Response deleteRoom(Long roomId) {
        Response response = new Response();
        try{
            roomRepo.findById(roomId).orElseThrow(() -> new OurException("Room not found"));
            roomRepo.deleteById(roomId);
            response.setStatusCode(200);
            response.setMessage("successful");

        }catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }
        catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurred while adding room:-" + e.getMessage());
        }
        return response;
    }

    @Override
    public Response updateRoom(Long roomId, String description, String roomType, BigDecimal roomPrice, MultipartFile photo) {
        Response response = new Response();
        try{
            String imageUrl = null;
            if(photo!=null && !photo.isEmpty())
                imageUrl = awsS3Service.saveImageToS3(photo);

            Room room = roomRepo.findById(roomId).orElseThrow(()-> new OurException("Room Not found"));

            if(roomType != null){
                room.setRoomType(roomType);
            }

            if(roomPrice != null){
                room.setRoomPrice(roomPrice);
            }

            if(description != null){
                room.setRoomDescription(description);
            }

            if(imageUrl != null){
                room.setRoomPhotoUrl(imageUrl);
            }

            Room updatedRoom = roomRepo.save(room);
            RoomDTO roomDTO = Utils.mapRoomEntityToRoomDTO(updatedRoom);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setRoom(roomDTO);

        }catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }
        catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurred while updating room:-" + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getRoomById(Long roomId) {
        Response response = new Response();
        try{
            Room room = roomRepo.findById(roomId).orElseThrow(() -> new OurException("Room not found"));
            RoomDTO roomDTO = Utils.mapRoomEntityToRoomDTOPlusBookings(room);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setRoom(roomDTO);

        }catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }
        catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurred while getting room by Id:-" + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAvailableRoomsByDateAndType(LocalDate checkInDate, LocalDate checkOutDate, String roomType) {
        Response response = new Response();
        try{
            List<Room> availableRooms = roomRepo.findAvailableRoomsByDatesAndTypes(checkInDate,checkOutDate,roomType);
            List<RoomDTO> roomDTOList = Utils.mapRoomListEntityToRoomListDTO(availableRooms);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setRoomList(roomDTOList);
        }
        catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurred while getting available rooms by date and type:-" + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAllAvailableRooms() {
        Response response = new Response();
        try{
            List<Room> roomList = roomRepo.getAllAvailableRooms();
            List<RoomDTO> roomDTOList = Utils.mapRoomListEntityToRoomListDTO(roomList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setRoomList(roomDTOList);
        }
        catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurred while getting all available rooms:-" + e.getMessage());
        }
        return response;
    }
}
