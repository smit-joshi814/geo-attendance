package com.geo.attendance.services;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.WKTWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geo.attendance.dtos.CenterPointDTO;
import com.geo.attendance.models.CenterPoint;
import com.geo.attendance.repositories.CenterPointRepository;

@Service
public class CenterPointService {

	@Autowired
	private CenterPointRepository centerPointRepository;

	public CenterPointDTO setCenterPoint(double latitude, double longitude) {
		GeometryFactory geometryFactory = new GeometryFactory();
		Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));
		point.setSRID(4326);  // Set SRID
		CenterPoint centerPoint = CenterPoint.builder().centerLocation(point).build();
		centerPoint = centerPointRepository.save(centerPoint);
		if (centerPoint != null) {
			WKTWriter writer = new WKTWriter();
			String wkt = writer.write(centerPoint.getCenterLocation());
			return CenterPointDTO.builder().id(centerPoint.getId()).centerLocation(wkt).build();
		}
		return null;
	}
}
