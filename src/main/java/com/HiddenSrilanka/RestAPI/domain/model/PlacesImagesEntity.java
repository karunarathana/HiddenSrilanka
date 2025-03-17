package com.HiddenSrilanka.RestAPI.domain.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;


@Entity
@Table(name="t_place_images")
@Component
public class PlacesImagesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imageId")
    private int imageId;
    @Lob
    @Column(name="placeImages",length = 1000000)
    private byte[] placeImageData;


    public byte[] getPlaceImageData() {
        return placeImageData;
    }

    public void setPlaceImageData(byte[] placeImageData) {
        this.placeImageData = placeImageData;
    }


}
