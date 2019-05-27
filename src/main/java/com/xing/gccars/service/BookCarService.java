package com.xing.gccars.service;

import com.xing.gccars.model.BorrowedDate;

import java.util.Calendar;
import java.util.List;

public interface BookCarService {

    boolean checkAvailabilityCarById(Calendar startDate, Calendar endDate, Long id);

    BorrowedDate addBorrowedDate(BorrowedDate borrowedDate);

    List<BorrowedDate> getBorrowedDates();

    List<BorrowedDate> getBorrowedDatesByUserId(Long userId);
}
