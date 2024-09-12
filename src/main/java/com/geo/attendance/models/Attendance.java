package com.geo.attendance.models;

import java.time.LocalDateTime;

import org.locationtech.jts.geom.Point;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attendance")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userId;

	@Column(name = "check_in_time", nullable = false)
	private LocalDateTime checkInTime;

	@Column(name = "check_out_time") // Add check-out time
	private LocalDateTime checkOutTime;

	@Column(name = "user_location")
//	@Convert(converter = PointConverter.class)
	private Point userLocation;

}
