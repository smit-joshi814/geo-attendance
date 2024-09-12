package com.geo.attendance.services;

import java.time.LocalDateTime;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.WKTWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geo.attendance.models.Attendance;
import com.geo.attendance.repositories.AttendanceRepository;

@Service
public class AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepository;

	public boolean markAttendance(Long userId, double userLat, double userLng, Long centerPointId) {
		// Use GeometryFactory to create a Point
		GeometryFactory geometryFactory = new GeometryFactory();
		Point userLocation = geometryFactory.createPoint(new Coordinate(userLng, userLat));
		userLocation.setSRID(4326);  // Set SRID

		WKTWriter writer = new WKTWriter();
		String userLocationWKT = writer.write(userLocation);
System.err.println(userLocationWKT);
		// Check if user is within 200 meters
		boolean withinRadius = attendanceRepository.isWithinRadius(centerPointId, userLocationWKT);

		if (withinRadius) {
			// If user is within the radius and has no active attendance, mark check-in
			Attendance attendance = new Attendance();
			attendance.setUserId(userId);
			attendance.setUserLocation(userLocation);
			attendance.setCheckInTime(LocalDateTime.now());

			attendanceRepository.save(attendance);
			return true; // Success
		} else {
			// If user leaves the radius, mark check-out time
			attendanceRepository.updateCheckOutTime(userId, LocalDateTime.now());
			return false; // User is outside the radius
		}
	}
}
