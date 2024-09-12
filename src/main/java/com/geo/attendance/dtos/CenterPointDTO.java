package com.geo.attendance.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CenterPointDTO {
	private Long id;
	private String centerLocation; // Use a String to store WKT representation of the Point
}
