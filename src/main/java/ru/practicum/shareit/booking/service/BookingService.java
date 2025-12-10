package ru.practicum.shareit.booking.service;

import ru.practicum.shareit.booking.dto.BookingDtoIn;
import ru.practicum.shareit.booking.dto.BookingDtoOut;

import java.util.List;

public interface BookingService {
    BookingDtoOut saveNewBooking(BookingDtoIn bookingDtoIn, Long userId);

    BookingDtoOut approve(Long bookingId, Boolean isApproved, Long userId);

    BookingDtoOut getBookingById(Long bookingId, Long userId);

    List<BookingDtoOut> getAllByBooker(String subState, Long bookerId);

    List<BookingDtoOut> getAllByOwner(Long ownerId, String state);
}
