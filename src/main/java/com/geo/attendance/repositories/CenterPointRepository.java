package com.geo.attendance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geo.attendance.models.CenterPoint;

public interface CenterPointRepository extends JpaRepository<CenterPoint, Long> {

}
