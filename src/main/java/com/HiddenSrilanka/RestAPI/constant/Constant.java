package com.HiddenSrilanka.RestAPI.constant;

public class Constant {
    public static final String API_ROOT = "api/com-hidden-srilanka";

    public static final String CREATE_USER = "/create-user";
    public static final String LOGIN_USER = "/login-user";
    public static final String VERIFY_OTP_CODE = "/otp";
    public static final String GET_ALL_USERS = "/get-all-user";
    public static final String UPDATE_USER_PASSWORD = "/update-user-password-by-email";
    public static final String FORGOT_USER_PASSWORD = "/forgot-user-password-by-email";
    public static final String FORGOT_USER_PASSWORD_UPDATE = "/forgot-user-password-update-by-email";

    public static final String CREATE_PLACE = "/create-place";
    public static final String GET_ALL_PLACES = "/get-all-places";
    public static final String GET_SINGLE_PLACE_BY_ID = "/get-place-details-by-id";
    public static final String DELETE_PLACE_BY_ID = "/delete-place-by-id";
    public static final String UPDATE_PLACE_STATUS = "/update-place-status";
    public static final String SEARCH_PLACE_BY_CITY = "/search-place";

    public static final String CREATE_HOTEL = "/create-hotel";
    public static final String GET_ALL_HOTELS = "/get-all-hotels";

    public static final String CREATE_ORDER= "/create-order";
    public static final String GET_ALL_ORDER_DETAILS_BROKER_ID= "/get-all-order-details-by-broker-id";
    public static final String GET_ALL_ORDER_DETAILS_USER_ID= "/get-all-order-details-by-user-id";
    public static final String UPDATE_ORDER_STATUS= "/update-order-status";
    public static final String DELETE_ORDER_DETAILS= "/delete-order-details";

    public static final String CREATE_PRODUCT= "/create-product";
    public static final String GET_ALL_PRODUCT = "/get-all-product";

}
