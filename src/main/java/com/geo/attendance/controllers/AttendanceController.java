package com.geo.attendance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geo.attendance.services.AttendanceService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/mark")
    public ResponseEntity<String> markAttendance(@RequestParam Long userId,
                                                 @RequestParam double lat,
                                                 @RequestParam double lng,
			@RequestParam Long centerPointId) {
        boolean withinRadius = attendanceService.markAttendance(userId, lat, lng, centerPointId);

        if (withinRadius) {
            return ResponseEntity.ok("Attendance marked (check-in) successfully.");
        } else {
            return ResponseEntity.ok("Check-out recorded. You are outside the allowed radius.");
        }
    }
}
