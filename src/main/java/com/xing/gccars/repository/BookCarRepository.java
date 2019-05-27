package com.xing.gccars.repository;

import com.xing.gccars.model.BorrowedDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public interface BookCarRepository extends JpaRepository<BorrowedDate, Long> {

    @Query("select case when count(b) = 0 then true else false end " +
            "from BorrowedDate as b " +
            "where b.car.id = :carId and (" +
            ":startDate between b.startDate and b.endDate " +
            "or :endDate between b.startDate and b.endDate) ")
    boolean checkAvailabilityCarById(@Param("startDate") Calendar startDate,
                                     @Param("endDate") Calendar endDate,
                                     @Param("carId") Long id);

    List<BorrowedDate> findAllByUserId(Long userId);
}
