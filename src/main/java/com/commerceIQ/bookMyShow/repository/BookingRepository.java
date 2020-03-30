package com.commerceIQ.bookMyShow.repository;

        import com.commerceIQ.bookMyShow.model.Booking;
        import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Integer> {
}