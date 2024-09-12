package com.geo.attendance.repositories;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geo.attendance.models.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	@Query(value = "SELECT 1 FROM center_points center WHERE center.id = :centerPointId AND "
			+ "ST_Distance(center.center_location, ST_GeomFromText(:userLocation, 4326)) < 200", nativeQuery = true)
	boolean isWithinRadius(@Param("centerPointId") Long centerPointId, @Param("userLocation") String userLocation);

	@Modifying
	@Query("UPDATE Attendance a SET a.checkOutTime = :checkOutTime WHERE a.userId = :userId AND a.checkOutTime IS NULL")
	void updateCheckOutTime(@Param("userId") Long userId, @Param("checkOutTime") LocalDateTime checkOutTime);
}
