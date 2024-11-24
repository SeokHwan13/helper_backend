package com.example.helper.service;

import com.example.helper.entity.pharmacy;
import com.example.helper.repository.pharmacyrepository;
import org.locationtech.proj4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class pharmacyservice {

    @Autowired
    private pharmacyrepository pharmacyrepository;

    public List<pharmacy> getpharmacy(Integer id) {
        List<pharmacy> list = new ArrayList<>();

        list.add(pharmacyrepository.findById(id).get());
        list.add(pharmacyrepository.findById(19347).get());

        System.out.println(list);

        return list;
    }

    public List<pharmacy> getpharmacylist(Double lat, Double lon) {
        // EPSG:2097 (Korea 2000)와 WGS84 좌표계 초기화
        CRSFactory crsFactory = new CRSFactory();

        CoordinateReferenceSystem crs2097 = crsFactory.createFromName("EPSG:2097");
        CoordinateReferenceSystem crsWGS84 = crsFactory.createFromName("EPSG:4326");

        // 좌표 변환 객체 생성
        CoordinateTransformFactory transformFactory = new CoordinateTransformFactory();
        CoordinateTransform transform = transformFactory.createTransform(crsWGS84, crs2097);

        // 변환할 좌표 (동/서 좌표, 북/남 좌표)
        ProjCoordinate sourceCoord = new ProjCoordinate(lon, lat);
        ProjCoordinate targetCoord = new ProjCoordinate();

        // 변환 수행
        transform.transform(sourceCoord, targetCoord);

        // 결과 반환 (위도, 경도)
        //return new double[]{targetCoord.y, targetCoord.x};  // WGS84: (위도, 경도)

        double latMin = targetCoord.y - 800;
        double latMax = targetCoord.y + 800;
        double lonMin = targetCoord.x - 800;
        double lonMax = targetCoord.x + 800;

        System.out.println(lat);
        System.out.println(lon);


        System.out.println("WGS84 범위: ");
        System.out.println("latMin: " + latMin + ", latMax: " + latMax);
        System.out.println("lonMin: " + lonMin + ", lonMax: " + lonMax);
        List<pharmacy> a =  pharmacyrepository.findPharmaciesNearLocation(lonMin, lonMax, latMin, latMax);
        System.out.println(a);
        return  a;
    }
}
