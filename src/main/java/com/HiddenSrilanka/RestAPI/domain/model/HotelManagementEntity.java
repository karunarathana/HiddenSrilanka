package com.HiddenSrilanka.RestAPI.domain.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="t_hotels")
public class HotelManagementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotelId")
    private int hotelId;
    private String hotelName;
    private String hotelAddress;
    private String description;
    private String city;
    private String country;
    private Double latitude;
    private Double longitude;
    private String priceRange;
    private String contactNumber;
    private String createUser;

    // One-to-many relationship with PlaceImage
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "Fk_hotel_id",referencedColumnName = "hotelId")
    private List<HotelImagesEntity> hotelImagesEntities;

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
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

    public List<HotelImagesEntity> getHotelImagesEntities() {
        return hotelImagesEntities;
    }

    public void setHotelImagesEntities(List<HotelImagesEntity> hotelImagesEntities) {
        this.hotelImagesEntities = hotelImagesEntities;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
