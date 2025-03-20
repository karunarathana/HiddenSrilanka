package com.HiddenSrilanka.RestAPI.domain.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name="t_hotel_images")
@Component
public class HotelImagesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imageId")
    private int imageId;
    @Lob
    @Column(name="hotelImages",length = 1000000)
    private byte[] hotelImageData;


    public byte[] getHotelImageData() {
        return hotelImageData;
    }

    public void setHotelImageData(byte[] hotelImageData) {
        this.hotelImageData = hotelImageData;
    }

}
