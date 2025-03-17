package com.HiddenSrilanka.RestAPI.domain.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="t_places")
public class PlaceManagementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "placeId")
    private int placeId;
    private String placeName;
    private String placeAddress;
    private String description;
    private String city;
    private String country;
    private Double latitude;
    private Double longitude;
    private String priceRange;

    // One-to-many relationship with PlaceImage
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "FkPlaceId",referencedColumnName = "placeId")
    private List<PlacesImagesEntity> placesImagesEntities;

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public List<PlacesImagesEntity> getPlacesImagesEntities() {
        return placesImagesEntities;
    }

    public void setPlacesImagesEntities(List<PlacesImagesEntity> placesImagesEntities) {
        this.placesImagesEntities = placesImagesEntities;
    }

}
