package com.geo.attendance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geo.attendance.dtos.CenterPointDTO;
import com.geo.attendance.services.CenterPointService;

@RestController
@RequestMapping("/api/center-point")
public class CenterPointController {

	@Autowired
	private CenterPointService centerPointService;

	@PostMapping("/set")
	public ResponseEntity<CenterPointDTO> setCenterPoint(@RequestParam double latitude,
			@RequestParam double longitude) {
		CenterPointDTO centerPoint = centerPointService.setCenterPoint(latitude, longitude);
		return ResponseEntity.ok(centerPoint);
	}
}
