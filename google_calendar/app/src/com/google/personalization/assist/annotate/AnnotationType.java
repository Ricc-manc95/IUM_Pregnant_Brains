// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.personalization.assist.annotate;


public final class AnnotationType extends Enum
    implements com.google.protobuf.Internal.EnumLite
{

    private static final AnnotationType $VALUES[];
    private static final AnnotationType ACTION;
    public static final AnnotationType ACTIVATE_BANK_CARD_ACTION;
    private static final AnnotationType AIRLINE;
    private static final AnnotationType AIRLINE_CODE;
    private static final AnnotationType AIRPORT;
    private static final AnnotationType AIRPORT_CODE;
    private static final AnnotationType AND;
    private static final AnnotationType ANNIVERSARY_EVENT;
    private static final AnnotationType APPLY_CITIZENSHIP_ACTION;
    private static final AnnotationType APPLY_DRIVERS_LICENSE_ACTION;
    private static final AnnotationType APPLY_LICENSE_ACTION;
    private static final AnnotationType APPLY_LOAN_ACTION;
    private static final AnnotationType APPLY_MARRIAGE_LICENSE_ACTION;
    private static final AnnotationType APPLY_PASSPORT_ACTION;
    private static final AnnotationType APPLY_VISA_ACTION;
    private static final AnnotationType APPOINTMENT;
    private static final AnnotationType APPOINTMENT_EVENT;
    public static final AnnotationType AT;
    private static final AnnotationType BABYSIT_EVENT;
    private static final AnnotationType BABY_SHOWER_EVENT;
    private static final AnnotationType BANK;
    private static final AnnotationType BANK_ACCOUNT;
    private static final AnnotationType BANK_CARD;
    private static final AnnotationType BANK_CARD_TYPE;
    private static final AnnotationType BATTERIES_HEARING_AID_BATTERIES_ACTION;
    private static final AnnotationType BATTERIES_RECHARGEABLE_BATTERIES_ACTION;
    private static final AnnotationType BILL;
    private static final AnnotationType BIRTHDAY_EVENT;
    private static final AnnotationType BIRTHDAY_EVENT_ADJ;
    private static final AnnotationType BOOK;
    private static final AnnotationType BOOKED_FLIGHT;
    private static final AnnotationType BOOKED_HOTEL;
    private static final AnnotationType BOOKED_RESTAURANT;
    private static final AnnotationType BOOK_FLIGHT_ACTION;
    private static final AnnotationType BOOK_HOTEL_ACTION;
    private static final AnnotationType BOOK_RESTAURANT_ACTION;
    private static final AnnotationType BRING_ITEM_ACTION;
    private static final AnnotationType BRING_ITEM_LOCATION;
    private static final AnnotationType BROUGHT_ITEM;
    private static final AnnotationType BUY_EVENT_TICKET_ACTION;
    private static final AnnotationType BUY_GROCERY_ACTION;
    private static final AnnotationType BUY_GROCERY_ITEM_ACTION;
    private static final AnnotationType BUY_PRODUCT_ACTION;
    private static final AnnotationType BUY_STOCK_ACTION;
    private static final AnnotationType CALL_CONTACT_ACTION;
    private static final AnnotationType CALL_LOCAL_ALIAS_ACTION;
    private static final AnnotationType CALL_LOCAL_BUSINESS_ACTION;
    private static final AnnotationType CALL_LOCAL_PROFESSIONAL_ACTION;
    private static final AnnotationType CALL_MEDICAL_PROFESSIONAL_ACTION;
    private static final AnnotationType CALL_ORGANIZATION_ACTION;
    private static final AnnotationType CALL_SERVICE_ACTION;
    private static final AnnotationType CANCEL_APPOINTMENT_ACTION;
    private static final AnnotationType CANCEL_BANK_ACCOUNT_ACTION;
    private static final AnnotationType CANCEL_BANK_CARD_ACTION;
    private static final AnnotationType CANCEL_FLIGHT_ACTION;
    private static final AnnotationType CANCEL_HOTEL_ACTION;
    private static final AnnotationType CANCEL_RESTAURANT_ACTION;
    private static final AnnotationType CANCEL_SERVICE_ACTION;
    private static final AnnotationType CANCEL_SUBSCRIPTION_ACTION;
    private static final AnnotationType CAR;
    private static final AnnotationType CAR_INSPECTION_AIR_FILTER_CHANGE_REPLACE_BUY_ACTION;
    private static final AnnotationType CAR_INSPECTION_BUY_TIRES_ACTION;
    private static final AnnotationType CAR_INSPECTION_FIND_OIL_CHANGE_ACTION;
    private static final AnnotationType CAR_INSPECTION_GET_AIR_FOR_TIRES_ACTION;
    private static final AnnotationType CAR_INSPECTION_GET_OIL_CHANGE_ACTION;
    private static final AnnotationType CAR_INSPECTION_GET_SMOG_CHECK_ACTION;
    private static final AnnotationType CAR_INSPECTION_SCHEDULE_OIL_CHANGE_ACTION;
    private static final AnnotationType CAR_INSPECTION_TIRE_PRESSURE_ACTION;
    private static final AnnotationType CAR_INSPECTION_TIRE_ROTATION_ACTION;
    private static final AnnotationType CAR_MAINTENANCE_CAR_WASH_ACTION;
    private static final AnnotationType CAR_MAINTENANCE_CAR_WAXING_ACTION;
    private static final AnnotationType CHECKIN_FLIGHT_ACTION;
    private static final AnnotationType CHECK_BANK_ACTION;
    private static final AnnotationType CITIZENSHIP;
    private static final AnnotationType CITIZENSHIP_TYPE;
    private static final AnnotationType CITY;
    private static final AnnotationType CITY_OR_AIRPORT_CODE;
    private static final AnnotationType CLEANABLE_HOUSEHOLD_ITEM;
    private static final AnnotationType CLEAN_CAR_ACTION;
    private static final AnnotationType CLEAN_HOUSEHOLD_ACTION;
    private static final AnnotationType COMPLETE_GOVERNMENT_FORM_ACTION;
    private static final AnnotationType CONNECTOR;
    public static final AnnotationType CONTACT;
    private static final AnnotationType CONTACTS;
    private static final AnnotationType CONTACT_SEPARATOR;
    private static final AnnotationType CONTACT_STATUS_EVENT_SUFFIX;
    private static final AnnotationType CONTACT_SUFFIX;
    private static final AnnotationType COPY_DOCUMENT_ACTION;
    private static final AnnotationType COPY_PASSPORT_ACTION;
    private static final AnnotationType COPY_VISA_ACTION;
    private static final AnnotationType COUNTRY;
    private static final AnnotationType CREDIT_CARD_SERVICE;
    private static final AnnotationType CREDIT_SCORE_PROVIDER;
    private static final AnnotationType DATE;
    private static final AnnotationType DATETIME;
    private static final AnnotationType DENTAL_CARE_PROVIDER;
    private static final AnnotationType DENTIST_FIND_DENTIST_ACTION;
    private static final AnnotationType DEPARTMENT_STORE;
    private static final AnnotationType DESTINATION;
    private static final AnnotationType DINNER_EVENT;
    private static final AnnotationType DMV_CALL_DMVACTION;
    private static final AnnotationType DMV_DRIVING_TEST_ACTION;
    private static final AnnotationType DMV_GO_TO_DMVACTION;
    private static final AnnotationType DOCTOR_CALL_ACTION;
    private static final AnnotationType DOCTOR_FIND_DOCTOR_ACTION;
    private static final AnnotationType DOCUMENT;
    private static final AnnotationType DRIVERS_LICENSE;
    private static final AnnotationType DRIVING_SCHOOL_FIND_SCHOOL_ACTION;
    private static final AnnotationType DROP_OFF_CONTACT_ACTION;
    private static final AnnotationType DROP_OFF_DRY_CLEANING_ACTION;
    private static final AnnotationType DROP_OFF_PASSPORT_ACTION;
    private static final AnnotationType DROP_OFF_PRESCRIPTION_ACTION;
    private static final AnnotationType DROP_OFF_VISA_ACTION;
    private static final AnnotationType DRY_CLEANING;
    private static final AnnotationType DUE_BILL;
    private static final AnnotationType EAT_ACTION;
    private static final AnnotationType EDIT_DOCUMENT_ACTION;
    private static final AnnotationType EMAIL_CONTACT_ACTION;
    private static final AnnotationType EVENT;
    private static final AnnotationType EVENT_TICKET;
    public static final AnnotationType EVENT_TIME;
    private static final AnnotationType EXAM;
    private static final AnnotationType EXERCISE_EVENT;
    private static final AnnotationType FILM;
    private static final AnnotationType FIND_BANK_ACCOUNT_ACTION;
    private static final AnnotationType FIND_BANK_CARD_ACTION;
    private static final AnnotationType FIND_JOB_ACTION;
    private static final AnnotationType FIND_LOCAL_PROFESSIONAL_ACTION;
    private static final AnnotationType FIND_MEDICAL_PROFESSIONAL_ACTION;
    private static final AnnotationType FIND_SUBSCRIPTION_ACTION;
    private static final AnnotationType FLEX_SCHEDULE;
    private static final AnnotationType FLEX_TIMESLOT;
    private static final AnnotationType FLEX_TIME_DURATION;
    private static final AnnotationType FLIGHT;
    private static final AnnotationType FLIGHTS_BOOK_FLIGHTS_TO_ACTION;
    private static final AnnotationType FLIGHT_CHECKIN_ACTION;
    private static final AnnotationType FLIGHT_DESTINATION;
    private static final AnnotationType FLIGHT_NUMBER;
    private static final AnnotationType FLIGHT_ORIGIN;
    private static final AnnotationType FLIGHT_STATS_ACTION;
    private static final AnnotationType FLIGHT_TICKET;
    private static final AnnotationType FLOWERS_CALL_FIND_FLORIST_ACTION;
    private static final AnnotationType FLOWERS_GET_FLOWERS_ACTION;
    private static final AnnotationType FLOWERS_GET_FLOWERS_BIRTHDAY_ACTION;
    private static final AnnotationType FLOWERS_GET_FLOWERS_MOM_ACTION;
    private static final AnnotationType FOOD_ITEM;
    private static final AnnotationType FOR;
    private static final AnnotationType FROM;
    private static final AnnotationType GIFT_EVENT;
    private static final AnnotationType GOVERNMENT_FORM;
    private static final AnnotationType GROCERY_ITEM;
    private static final AnnotationType HAIRCUT_GET_HAIRCUT_ACTION;
    private static final AnnotationType HEALTH_INSURANCE_PROVIDER;
    private static final AnnotationType HOLD_MAIL_ACTION;
    private static final AnnotationType HOLIDAY;
    private static final AnnotationType HOTEL;
    private static final AnnotationType HOTEL_DESTINATION;
    private static final AnnotationType HOUSE;
    public static final AnnotationType IN;
    private static final AnnotationType INDUSTRY;
    private static final AnnotationType ISPTELCO;
    private static final AnnotationType JOB;
    private static final AnnotationType LETTER;
    private static final AnnotationType LICENSE;
    private static final AnnotationType LISTEN_ALBUM_ACTION;
    private static final AnnotationType LISTEN_SONG_ACTION;
    private static final AnnotationType LOAN;
    private static final AnnotationType LOAN_PAYMENT;
    private static final AnnotationType LOAN_PAYMENT_ACTION;
    private static final AnnotationType LOCAL_ALIAS;
    public static final AnnotationType LOCAL_BUSINESS;
    private static final AnnotationType LOCAL_DEPARTMENT_STORE;
    private static final AnnotationType LOCAL_PROFESSIONAL;
    private static final AnnotationType LOCATION;
    private static final AnnotationType LOCKSMITH_BUY_ACCESSORIES_ACTION;
    private static final AnnotationType LOCKSMITH_FIND_LOCKSMITH_ACTION;
    private static final AnnotationType LOCKSMITH_LOCKSMITH_SERVICES_ACTION;
    private static final AnnotationType LOOKUP_WEB_QUERY_ACTION;
    private static final AnnotationType LUNCH_EVENT;
    private static final AnnotationType MAIL;
    private static final AnnotationType MAILING_STUFF_CHANGE_FORWARDING_ADDRESS_FOR_MAIL_ACTION;
    private static final AnnotationType MAILING_STUFF_CHANGE_MAILING_ADDRESS_ACTION;
    private static final AnnotationType MAIL_HOLD;
    private static final AnnotationType MARRIAGE_LICENSE;
    private static final AnnotationType MEDICAL_PROFESSIONAL;
    private static final AnnotationType MEDICINE;
    private static final AnnotationType MEETING_EVENT;
    private static final AnnotationType MEET_CONTACT_ACTION;
    private static final AnnotationType MONEY;
    private static final AnnotationType MONEY_WORD;
    private static final AnnotationType MOTORCYCLE;
    private static final AnnotationType MOVIE;
    private static final AnnotationType MOVING_VIOLATION;
    private static final AnnotationType MUSIC_ALBUM;
    private static final AnnotationType MY;
    private static final AnnotationType NON_PRESCRIPTION_DRUGS;
    private static final AnnotationType NOTARIZE_DOCUMENT_ACTION;
    private static final AnnotationType OCCUPATIONAL_COMMODITY_SERVICE_PROVIDER;
    private static final AnnotationType OCCUPATIONAL_NON_COMMODITY_SERVICE;
    private static final AnnotationType OCCUPATIONAL_NON_COMMODITY_SERVICE_PROVIDER;
    private static final AnnotationType OCCUPATIONAL_SERVICE_PROVIDER_CHIMNEY_SWEEP_ACTION;
    private static final AnnotationType OCCUPATIONAL_SERVICE_PROVIDER_CLEANING_ACTION;
    private static final AnnotationType OCCUPATIONAL_SERVICE_PROVIDER_FIND_COMMODITY_ACTION;
    private static final AnnotationType OCCUPATIONAL_SERVICE_PROVIDER_FIND_NON_COMMODITY_ACTION;
    private static final AnnotationType OCCUPATIONAL_SERVICE_PROVIDER_OPTOMETRIST_CANCEL_ACTION;
    private static final AnnotationType OCCUPATIONAL_SERVICE_PROVIDER_OPTOMETRIST_SCHEDULE_ACTION;
    private static final AnnotationType OCCUPATIONAL_SERVICE_PROVIDER_PAY_NON_COMMODITY_SERVICE_ACTION;
    private static final AnnotationType OCCUPATIONAL_SERVICE_PROVIDER_PEDICURE_ACTION;
    private static final AnnotationType OCCUPATIONAL_SERVICE_PROVIDER_PIANO_TUNER_ACTION;
    private static final AnnotationType OCCUPATIONAL_SERVICE_PROVIDER_SCHEDULE_COMMODITY_ACTION;
    private static final AnnotationType OCCUPATIONAL_SERVICE_PROVIDER_SCHEDULE_EXISTING_NON_COMMODITY_SERVICE_PROVIDER_ACTION;
    private static final AnnotationType OCCUPATIONAL_SERVICE_PROVIDER_SCHEDULE_NON_COMMODITY_ACTION;
    private static final AnnotationType OCCUPATIONAL_SERVICE_PROVIDER_SCHEDULE_PEDIATRICIAN_ACTION;
    public static final AnnotationType ON;
    private static final AnnotationType ORGANIZATION;
    private static final AnnotationType ORIGIN;
    private static final AnnotationType PACKAGE;
    private static final AnnotationType PACKING_LIST_ITEM;
    private static final AnnotationType PACK_FOR_PLACE_ACTION;
    private static final AnnotationType PACK_FOR_TRIP_ACTION;
    private static final AnnotationType PARKING_TICKET;
    private static final AnnotationType PARTY_EVENT;
    private static final AnnotationType PASSPORT;
    private static final AnnotationType PASSPORT_TYPE;
    private static final AnnotationType PAST_EVENT;
    private static final AnnotationType PAST_REMINDER;
    private static final AnnotationType PAY_BILL_ACTION;
    private static final AnnotationType PAY_CONTACT_ACTION;
    private static final AnnotationType PAY_LOAN_ACTION;
    private static final AnnotationType PAY_MONEY_CONTACT_ACTION;
    private static final AnnotationType PAY_MOVING_VIOLATION_ACTION;
    private static final AnnotationType PAY_PARKING_TICKET_ACTION;
    private static final AnnotationType PAY_RENT_ACTION;
    private static final AnnotationType PAY_TAX_ACTION;
    private static final AnnotationType PERFORM_ROUTINE_ACTION;
    private static final AnnotationType PHARMACY_GET_NON_PRESCRIPTION_DRUGS_ACTION;
    private static final AnnotationType PHARMACY_STORES;
    private static final AnnotationType PICK_UP_CONTACT_ACTION;
    private static final AnnotationType PICK_UP_DRY_CLEANING_ACTION;
    private static final AnnotationType PICK_UP_PASSPORT_ACTION;
    private static final AnnotationType PICK_UP_PRESCRIPTION_ACTION;
    private static final AnnotationType PICK_UP_PRESCRIPTION_FROM_STORE_ACTION;
    private static final AnnotationType PICK_UP_VISA_ACTION;
    private static final AnnotationType PREPARE_FOOD_ACTION;
    private static final AnnotationType PREPARE_FOOD_LOCATION;
    private static final AnnotationType PREPARE_FOOD_LOCATION_ACTION;
    private static final AnnotationType PRESCRIPTION;
    private static final AnnotationType PRODUCT;
    private static final AnnotationType PROFESSIONAL;
    private static final AnnotationType PURCHASED_PRODUCT;
    private static final AnnotationType READ_BOOK_ACTION;
    private static final AnnotationType REGISTER_CAR_ACTION;
    private static final AnnotationType REGISTER_EXAM_ACTION;
    private static final AnnotationType REGISTER_MOTORCYCLE_ACTION;
    private static final AnnotationType REGISTER_VEHICLE_ACTION;
    private static final AnnotationType REGISTER_VOTE_ACTION;
    private static final AnnotationType REGISTRATION;
    private static final AnnotationType REMINDER_EVENT;
    private static final AnnotationType RENEW_DRIVERS_LICENSE_ACTION;
    private static final AnnotationType RENEW_LICENSE_ACTION;
    private static final AnnotationType RENEW_PASSPORT_ACTION;
    private static final AnnotationType RENEW_VEHICLE_REGISTRATION_ACTION;
    private static final AnnotationType RENEW_VISA_ACTION;
    private static final AnnotationType RENT;
    private static final AnnotationType RENTALCAR_TAXI_BOOKTAXI_ACTION;
    private static final AnnotationType RENTALCAR_TAXI_BOOK_RENTAL_CAR_ACTION;
    private static final AnnotationType RENTALCAR_TAXI_CANCEL_RENTAL_CAR_ACTION;
    private static final AnnotationType RENTAL_CAR_SERVICE;
    private static final AnnotationType REPAIR_CAR_ACTION;
    private static final AnnotationType REPAIR_PRODUCT_ACTION;
    private static final AnnotationType RESTAURANT;
    private static final AnnotationType RESTAURANT_TYPE;
    private static final AnnotationType RETURN_PRODUCT_ACTION;
    private static final AnnotationType ROUTINE;
    private static final AnnotationType SATELLITE_PROVIDER;
    private static final AnnotationType SCHEDULE_ACTION;
    private static final AnnotationType SELL_STOCK_ACTION;
    private static final AnnotationType SEND_LETTER_ACTION;
    private static final AnnotationType SEND_PACKAGE_ACTION;
    private static final AnnotationType SERVICE;
    private static final AnnotationType SHIPPING_PROVIDER;
    private static final AnnotationType SHOW_EVENT;
    private static final AnnotationType SMS_CONTACT_ACTION;
    private static final AnnotationType SONG;
    private static final AnnotationType SPORTS_STORES;
    private static final AnnotationType STAMPS_OFFLINEOR_ONLINE_ACTION;
    private static final AnnotationType STAMPS_ONLINE_ACTION;
    private static final AnnotationType STOCK;
    private static final AnnotationType STUDY_ACTION;
    private static final AnnotationType STUDY_OBJECT;
    private static final AnnotationType STUDY_TASK;
    private static final AnnotationType SUBJECT_TO_STUDY;
    private static final AnnotationType SUBSCRIPTION;
    private static final AnnotationType TAKE_MEDICINE_ACTION;
    private static final AnnotationType TAKE_OUT_TRASH_ACTION;
    private static final AnnotationType TAX;
    private static final AnnotationType TAX_PROVIDER;
    private static final AnnotationType TECH_PROVIDER_SUPPORT;
    public static final AnnotationType TEXT;
    private static final AnnotationType THE;
    private static final AnnotationType TIME;
    public static final AnnotationType TO;
    private static final AnnotationType TO_DO;
    private static final AnnotationType TRACK_FLIGHT_ACTION;
    private static final AnnotationType TRACK_PACKAGE_ACTION;
    private static final AnnotationType TRAFFIC_TICKETS_PAY_PARKING_TICKET_ACTION;
    private static final AnnotationType TRAFFIC_TICKETS_PAY_SPEEDING_TICKET_ACTION;
    private static final AnnotationType TRASH;
    private static final AnnotationType TRAVEL_BROKER;
    private static final AnnotationType TRAVEL_EVENT;
    private static final AnnotationType TV_SHOW;
    private static final AnnotationType USER_HOME;
    private static final AnnotationType US_STATE;
    private static final AnnotationType VACCINATIONS_FLU_SHOT_ACTION;
    private static final AnnotationType VACCINATIONS_FLU_SHOT_AT_PHARMACY_ACTION;
    private static final AnnotationType VEHICLE;
    private static final AnnotationType VISA;
    private static final AnnotationType VISA_TYPE;
    private static final AnnotationType VISIT_EVENT;
    private static final AnnotationType VITAMINS_AND_SUPPLEMENTS_SUPPLEMENTS_ACTION;
    private static final AnnotationType VITAMINS_AND_SUPPLEMENTS_VITAMINS_ACTION;
    private static final AnnotationType VOTE;
    private static final AnnotationType WASTE_MANAGEMENT_PROVIDER;
    private static final AnnotationType WATCH_FILM_ACTION;
    private static final AnnotationType WATCH_MOVIE_ACTION;
    private static final AnnotationType WATCH_VIDEO_ACTION;
    private static final AnnotationType WEB_QUERY;
    private static final AnnotationType WEDDING_EVENT;
    public static final AnnotationType WITH;
    private static final AnnotationType WORK_EVENT;
    private static final AnnotationType YOUTUBE;
    public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
    private final int value;

    private AnnotationType(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }

    public static AnnotationType forNumber(int i)
    {
        switch (i)
        {
        case 238: 
        case 257: 
        case 293: 
        default:
            return null;

        case 211: 
            return ACTIVATE_BANK_CARD_ACTION;

        case 198: 
            return APPLY_CITIZENSHIP_ACTION;

        case 217: 
            return APPLY_DRIVERS_LICENSE_ACTION;

        case 248: 
            return APPLY_LICENSE_ACTION;

        case 186: 
            return APPLY_LOAN_ACTION;

        case 149: 
            return APPLY_MARRIAGE_LICENSE_ACTION;

        case 212: 
            return APPLY_PASSPORT_ACTION;

        case 162: 
            return APPLY_VISA_ACTION;

        case 100: // 'd'
            return BOOK_FLIGHT_ACTION;

        case 101: // 'e'
            return BOOK_HOTEL_ACTION;

        case 122: // 'z'
            return BOOK_RESTAURANT_ACTION;

        case 318: 
            return BRING_ITEM_ACTION;

        case 319: 
            return BRING_ITEM_LOCATION;

        case 320: 
            return BROUGHT_ITEM;

        case 269: 
            return BUY_EVENT_TICKET_ACTION;

        case 157: 
            return BUY_GROCERY_ITEM_ACTION;

        case 32: // ' '
            return BUY_PRODUCT_ACTION;

        case 207: 
            return BUY_STOCK_ACTION;

        case 28: // '\034'
            return CALL_CONTACT_ACTION;

        case 251: 
            return CALL_LOCAL_ALIAS_ACTION;

        case 112: // 'p'
            return CALL_LOCAL_BUSINESS_ACTION;

        case 127: // '\177'
            return CALL_MEDICAL_PROFESSIONAL_ACTION;

        case 213: 
            return CALL_ORGANIZATION_ACTION;

        case 252: 
            return CANCEL_APPOINTMENT_ACTION;

        case 239: 
            return CANCEL_BANK_ACCOUNT_ACTION;

        case 178: 
            return CANCEL_BANK_CARD_ACTION;

        case 116: // 't'
            return CANCEL_FLIGHT_ACTION;

        case 121: // 'y'
            return CANCEL_HOTEL_ACTION;

        case 126: // '~'
            return CANCEL_RESTAURANT_ACTION;

        case 179: 
            return CANCEL_SUBSCRIPTION_ACTION;

        case 240: 
            return CHECK_BANK_ACTION;

        case 173: 
            return CHECKIN_FLIGHT_ACTION;

        case 218: 
            return CLEAN_CAR_ACTION;

        case 189: 
            return CLEAN_HOUSEHOLD_ACTION;

        case 236: 
            return COMPLETE_GOVERNMENT_FORM_ACTION;

        case 164: 
            return COPY_DOCUMENT_ACTION;

        case 204: 
            return COPY_PASSPORT_ACTION;

        case 132: 
            return COPY_VISA_ACTION;

        case 147: 
            return DROP_OFF_CONTACT_ACTION;

        case 154: 
            return DROP_OFF_DRY_CLEANING_ACTION;

        case 205: 
            return DROP_OFF_PASSPORT_ACTION;

        case 214: 
            return DROP_OFF_PRESCRIPTION_ACTION;

        case 160: 
            return DROP_OFF_VISA_ACTION;

        case 321: 
            return EAT_ACTION;

        case 105: // 'i'
            return EDIT_DOCUMENT_ACTION;

        case 109: // 'm'
            return EMAIL_CONTACT_ACTION;

        case 241: 
            return FIND_BANK_ACCOUNT_ACTION;

        case 168: 
            return FIND_BANK_CARD_ACTION;

        case 274: 
            return FIND_JOB_ACTION;

        case 183: 
            return FIND_LOCAL_PROFESSIONAL_ACTION;

        case 129: 
            return FIND_MEDICAL_PROFESSIONAL_ACTION;

        case 145: 
            return FIND_SUBSCRIPTION_ACTION;

        case 322: 
            return FLEX_SCHEDULE;

        case 327: 
            return FLEX_TIMESLOT;

        case 315: 
            return FOOD_ITEM;

        case 270: 
            return HOLD_MAIL_ACTION;

        case 306: 
            return LOAN_PAYMENT_ACTION;

        case 216: 
            return LISTEN_ALBUM_ACTION;

        case 191: 
            return LISTEN_SONG_ACTION;

        case 29: // '\035'
            return MEET_CONTACT_ACTION;

        case 165: 
            return NOTARIZE_DOCUMENT_ACTION;

        case 169: 
            return PACK_FOR_PLACE_ACTION;

        case 166: 
            return PAY_BILL_ACTION;

        case 107: // 'k'
            return PAY_CONTACT_ACTION;

        case 285: 
            return PAY_MONEY_CONTACT_ACTION;

        case 188: 
            return PAY_LOAN_ACTION;

        case 197: 
            return PAY_MOVING_VIOLATION_ACTION;

        case 175: 
            return PAY_PARKING_TICKET_ACTION;

        case 253: 
            return PAY_RENT_ACTION;

        case 185: 
            return PAY_TAX_ACTION;

        case 292: 
            return PERFORM_ROUTINE_ACTION;

        case 146: 
            return PICK_UP_CONTACT_ACTION;

        case 156: 
            return PICK_UP_DRY_CLEANING_ACTION;

        case 206: 
            return PICK_UP_PASSPORT_ACTION;

        case 153: 
            return PICK_UP_PRESCRIPTION_ACTION;

        case 140: 
            return PICK_UP_VISA_ACTION;

        case 311: 
            return PREPARE_FOOD_ACTION;

        case 312: 
            return PREPARE_FOOD_LOCATION_ACTION;

        case 313: 
            return PREPARE_FOOD_LOCATION;

        case 194: 
            return READ_BOOK_ACTION;

        case 133: 
            return REPAIR_CAR_ACTION;

        case 234: 
            return REGISTER_EXAM_ACTION;

        case 134: 
            return REGISTER_MOTORCYCLE_ACTION;

        case 244: 
            return REGISTER_VEHICLE_ACTION;

        case 228: 
            return REGISTER_VOTE_ACTION;

        case 143: 
            return RENEW_DRIVERS_LICENSE_ACTION;

        case 243: 
            return RENEW_LICENSE_ACTION;

        case 203: 
            return RENEW_PASSPORT_ACTION;

        case 246: 
            return RENEW_VEHICLE_REGISTRATION_ACTION;

        case 135: 
            return RENEW_VISA_ACTION;

        case 142: 
            return REPAIR_PRODUCT_ACTION;

        case 104: // 'h'
            return RETURN_PRODUCT_ACTION;

        case 291: 
            return ROUTINE;

        case 232: 
            return SCHEDULE_ACTION;

        case 208: 
            return SELL_STOCK_ACTION;

        case 210: 
            return SEND_LETTER_ACTION;

        case 137: 
            return SEND_PACKAGE_ACTION;

        case 302: 
            return SMS_CONTACT_ACTION;

        case 299: 
            return STUDY_ACTION;

        case 307: 
            return TAKE_MEDICINE_ACTION;

        case 310: 
            return TAKE_OUT_TRASH_ACTION;

        case 119: // 'w'
            return TRACK_FLIGHT_ACTION;

        case 141: 
            return TRACK_PACKAGE_ACTION;

        case 229: 
            return US_STATE;

        case 174: 
            return WATCH_FILM_ACTION;

        case 130: 
            return WATCH_VIDEO_ACTION;

        case 262: 
            return ANNIVERSARY_EVENT;

        case 294: 
            return APPOINTMENT_EVENT;

        case 276: 
            return BABY_SHOWER_EVENT;

        case 277: 
            return BABYSIT_EVENT;

        case 261: 
            return BIRTHDAY_EVENT;

        case 288: 
            return CONTACT_STATUS_EVENT_SUFFIX;

        case 281: 
            return DINNER_EVENT;

        case 295: 
            return EXERCISE_EVENT;

        case 290: 
            return GIFT_EVENT;

        case 278: 
            return LUNCH_EVENT;

        case 287: 
            return MEETING_EVENT;

        case 283: 
            return PARTY_EVENT;

        case 286: 
            return REMINDER_EVENT;

        case 296: 
            return SHOW_EVENT;

        case 289: 
            return TRAVEL_EVENT;

        case 280: 
            return VISIT_EVENT;

        case 279: 
            return WEDDING_EVENT;

        case 284: 
            return WORK_EVENT;

        case 1: // '\001'
            return ACTION;

        case 6: // '\006'
            return AIRLINE;

        case 117: // 'u'
            return AIRLINE_CODE;

        case 215: 
            return AIRPORT;

        case 182: 
            return AIRPORT_CODE;

        case 264: 
            return AND;

        case 233: 
            return APPOINTMENT;

        case 123: // '{'
            return AT;

        case 242: 
            return BANK_ACCOUNT;

        case 167: 
            return BANK_CARD;

        case 267: 
            return BANK_CARD_TYPE;

        case 7: // '\007'
            return BANK;

        case 131: 
            return BILL;

        case 303: 
            return BIRTHDAY_EVENT_ADJ;

        case 195: 
            return BOOK;

        case 110: // 'n'
            return BOOKED_FLIGHT;

        case 120: // 'x'
            return BOOKED_HOTEL;

        case 125: // '}'
            return BOOKED_RESTAURANT;

        case 136: 
            return CAR;

        case 201: 
            return CITIZENSHIP;

        case 330: 
            return CITIZENSHIP_TYPE;

        case 181: 
            return CITY;

        case 231: 
            return CLEANABLE_HOUSEHOLD_ITEM;

        case 259: 
            return CONNECTOR;

        case 2: // '\002'
            return CONTACT;

        case 297: 
            return CONTACT_SEPARATOR;

        case 273: 
            return CONTACT_SUFFIX;

        case 263: 
            return CONTACTS;

        case 199: 
            return COUNTRY;

        case 316: 
            return DATE;

        case 298: 
            return DATETIME;

        case 96: // '`'
            return DOCUMENT;

        case 148: 
            return DRIVERS_LICENSE;

        case 155: 
            return DRY_CLEANING;

        case 256: 
            return DUE_BILL;

        case 235: 
            return EXAM;

        case 258: 
            return EVENT;

        case 268: 
            return EVENT_TICKET;

        case 266: 
            return EVENT_TIME;

        case 180: 
            return FILM;

        case 106: // 'j'
            return FLIGHT;

        case 170: 
            return FLIGHT_DESTINATION;

        case 118: // 'v'
            return FLIGHT_NUMBER;

        case 171: 
            return FLIGHT_ORIGIN;

        case 260: 
            return FLIGHT_TICKET;

        case 138: 
            return FOR;

        case 220: 
            return FROM;

        case 237: 
            return GOVERNMENT_FORM;

        case 3: // '\003'
            return GROCERY_ITEM;

        case 224: 
            return HOLIDAY;

        case 226: 
            return HOTEL;

        case 172: 
            return HOTEL_DESTINATION;

        case 190: 
            return HOUSE;

        case 219: 
            return IN;

        case 222: 
            return INDUSTRY;

        case 275: 
            return JOB;

        case 139: 
            return LETTER;

        case 249: 
            return LICENSE;

        case 187: 
            return LOAN;

        case 305: 
            return LOAN_PAYMENT;

        case 250: 
            return LOCAL_ALIAS;

        case 26: // '\032'
            return LOCAL_BUSINESS;

        case 159: 
            return LOCAL_PROFESSIONAL;

        case 103: // 'g'
            return LOCATION;

        case 271: 
            return MAIL;

        case 272: 
            return MAIL_HOLD;

        case 150: 
            return MARRIAGE_LICENSE;

        case 128: 
            return MEDICAL_PROFESSIONAL;

        case 308: 
            return MEDICINE;

        case 108: // 'l'
            return MONEY;

        case 282: 
            return MONEY_WORD;

        case 158: 
            return MOTORCYCLE;

        case 200: 
            return MOVING_VIOLATION;

        case 193: 
            return MUSIC_ALBUM;

        case 333: 
            return MY;

        case 223: 
            return ON;

        case 151: 
            return ORGANIZATION;

        case 176: 
            return PACKAGE;

        case 324: 
            return PACKING_LIST_ITEM;

        case 196: 
            return PARKING_TICKET;

        case 202: 
            return PASSPORT;

        case 329: 
            return PASSPORT_TYPE;

        case 314: 
            return PAST_EVENT;

        case 255: 
            return PAST_REMINDER;

        case 152: 
            return PRESCRIPTION;

        case 31: // '\037'
            return PRODUCT;

        case 230: 
            return PROFESSIONAL;

        case 97: // 'a'
            return PURCHASED_PRODUCT;

        case 247: 
            return REGISTRATION;

        case 254: 
            return RENT;

        case 18: // '\022'
            return RENTAL_CAR_SERVICE;

        case 124: // '|'
            return RESTAURANT;

        case 328: 
            return RESTAURANT_TYPE;

        case 4: // '\004'
            return SERVICE;

        case 192: 
            return SONG;

        case 209: 
            return STOCK;

        case 304: 
            return STUDY_OBJECT;

        case 301: 
            return STUDY_TASK;

        case 300: 
            return SUBJECT_TO_STUDY;

        case 184: 
            return SUBSCRIPTION;

        case 177: 
            return TAX;

        case 0: // '\0'
            return TEXT;

        case 332: 
            return THE;

        case 317: 
            return TIME;

        case 144: 
            return TO;

        case 325: 
            return TO_DO;

        case 309: 
            return TRASH;

        case 326: 
            return TV_SHOW;

        case 225: 
            return USER_HOME;

        case 245: 
            return VEHICLE;

        case 163: 
            return VISA;

        case 331: 
            return VISA_TYPE;

        case 227: 
            return VOTE;

        case 5: // '\005'
            return WEB_QUERY;

        case 265: 
            return WITH;

        case 115: // 's'
            return YOUTUBE;

        case 8: // '\b'
            return CREDIT_CARD_SERVICE;

        case 9: // '\t'
            return CREDIT_SCORE_PROVIDER;

        case 10: // '\n'
            return DENTAL_CARE_PROVIDER;

        case 11: // '\013'
            return HEALTH_INSURANCE_PROVIDER;

        case 12: // '\f'
            return ISPTELCO;

        case 13: // '\r'
            return LOCAL_DEPARTMENT_STORE;

        case 14: // '\016'
            return NON_PRESCRIPTION_DRUGS;

        case 15: // '\017'
            return OCCUPATIONAL_COMMODITY_SERVICE_PROVIDER;

        case 16: // '\020'
            return OCCUPATIONAL_NON_COMMODITY_SERVICE_PROVIDER;

        case 17: // '\021'
            return PHARMACY_STORES;

        case 19: // '\023'
            return SATELLITE_PROVIDER;

        case 20: // '\024'
            return SHIPPING_PROVIDER;

        case 21: // '\025'
            return SPORTS_STORES;

        case 22: // '\026'
            return TAX_PROVIDER;

        case 23: // '\027'
            return TECH_PROVIDER_SUPPORT;

        case 24: // '\030'
            return TRAVEL_BROKER;

        case 25: // '\031'
            return WASTE_MANAGEMENT_PROVIDER;

        case 113: // 'q'
            return MOVIE;

        case 221: 
            return CALL_LOCAL_PROFESSIONAL_ACTION;

        case 27: // '\033'
            return CALL_SERVICE_ACTION;

        case 94: // '^'
            return CANCEL_SERVICE_ACTION;

        case 30: // '\036'
            return BUY_GROCERY_ACTION;

        case 33: // '!'
            return LOOKUP_WEB_QUERY_ACTION;

        case 95: // '_'
            return FLIGHT_STATS_ACTION;

        case 102: // 'f'
            return PACK_FOR_TRIP_ACTION;

        case 111: // 'o'
            return FLIGHT_CHECKIN_ACTION;

        case 98: // 'b'
            return ORIGIN;

        case 99: // 'c'
            return DESTINATION;

        case 34: // '"'
            return BATTERIES_HEARING_AID_BATTERIES_ACTION;

        case 35: // '#'
            return BATTERIES_RECHARGEABLE_BATTERIES_ACTION;

        case 36: // '$'
            return CAR_INSPECTION_AIR_FILTER_CHANGE_REPLACE_BUY_ACTION;

        case 37: // '%'
            return CAR_INSPECTION_BUY_TIRES_ACTION;

        case 38: // '&'
            return CAR_INSPECTION_FIND_OIL_CHANGE_ACTION;

        case 39: // '\''
            return CAR_INSPECTION_GET_AIR_FOR_TIRES_ACTION;

        case 40: // '('
            return CAR_INSPECTION_GET_OIL_CHANGE_ACTION;

        case 41: // ')'
            return CAR_INSPECTION_GET_SMOG_CHECK_ACTION;

        case 42: // '*'
            return CAR_INSPECTION_SCHEDULE_OIL_CHANGE_ACTION;

        case 43: // '+'
            return CAR_INSPECTION_TIRE_PRESSURE_ACTION;

        case 44: // ','
            return CAR_INSPECTION_TIRE_ROTATION_ACTION;

        case 45: // '-'
            return CAR_MAINTENANCE_CAR_WASH_ACTION;

        case 46: // '.'
            return CAR_MAINTENANCE_CAR_WAXING_ACTION;

        case 47: // '/'
            return CITY_OR_AIRPORT_CODE;

        case 48: // '0'
            return DENTIST_FIND_DENTIST_ACTION;

        case 49: // '1'
            return DEPARTMENT_STORE;

        case 50: // '2'
            return DMV_CALL_DMVACTION;

        case 51: // '3'
            return DMV_DRIVING_TEST_ACTION;

        case 52: // '4'
            return DMV_GO_TO_DMVACTION;

        case 53: // '5'
            return DOCTOR_CALL_ACTION;

        case 54: // '6'
            return DOCTOR_FIND_DOCTOR_ACTION;

        case 55: // '7'
            return DRIVING_SCHOOL_FIND_SCHOOL_ACTION;

        case 323: 
            return FLEX_TIME_DURATION;

        case 56: // '8'
            return FLIGHTS_BOOK_FLIGHTS_TO_ACTION;

        case 57: // '9'
            return FLOWERS_CALL_FIND_FLORIST_ACTION;

        case 58: // ':'
            return FLOWERS_GET_FLOWERS_ACTION;

        case 59: // ';'
            return FLOWERS_GET_FLOWERS_BIRTHDAY_ACTION;

        case 60: // '<'
            return FLOWERS_GET_FLOWERS_MOM_ACTION;

        case 61: // '='
            return HAIRCUT_GET_HAIRCUT_ACTION;

        case 62: // '>'
            return LOCKSMITH_BUY_ACCESSORIES_ACTION;

        case 63: // '?'
            return LOCKSMITH_FIND_LOCKSMITH_ACTION;

        case 64: // '@'
            return LOCKSMITH_LOCKSMITH_SERVICES_ACTION;

        case 65: // 'A'
            return MAILING_STUFF_CHANGE_FORWARDING_ADDRESS_FOR_MAIL_ACTION;

        case 66: // 'B'
            return MAILING_STUFF_CHANGE_MAILING_ADDRESS_ACTION;

        case 67: // 'C'
            return OCCUPATIONAL_NON_COMMODITY_SERVICE;

        case 68: // 'D'
            return OCCUPATIONAL_SERVICE_PROVIDER_CHIMNEY_SWEEP_ACTION;

        case 69: // 'E'
            return OCCUPATIONAL_SERVICE_PROVIDER_CLEANING_ACTION;

        case 70: // 'F'
            return OCCUPATIONAL_SERVICE_PROVIDER_FIND_COMMODITY_ACTION;

        case 71: // 'G'
            return OCCUPATIONAL_SERVICE_PROVIDER_FIND_NON_COMMODITY_ACTION;

        case 72: // 'H'
            return OCCUPATIONAL_SERVICE_PROVIDER_OPTOMETRIST_CANCEL_ACTION;

        case 73: // 'I'
            return OCCUPATIONAL_SERVICE_PROVIDER_OPTOMETRIST_SCHEDULE_ACTION;

        case 74: // 'J'
            return OCCUPATIONAL_SERVICE_PROVIDER_PAY_NON_COMMODITY_SERVICE_ACTION;

        case 75: // 'K'
            return OCCUPATIONAL_SERVICE_PROVIDER_PEDICURE_ACTION;

        case 76: // 'L'
            return OCCUPATIONAL_SERVICE_PROVIDER_PIANO_TUNER_ACTION;

        case 77: // 'M'
            return OCCUPATIONAL_SERVICE_PROVIDER_SCHEDULE_COMMODITY_ACTION;

        case 78: // 'N'
            return OCCUPATIONAL_SERVICE_PROVIDER_SCHEDULE_EXISTING_NON_COMMODITY_SERVICE_PROVIDER_ACTION;

        case 79: // 'O'
            return OCCUPATIONAL_SERVICE_PROVIDER_SCHEDULE_NON_COMMODITY_ACTION;

        case 80: // 'P'
            return OCCUPATIONAL_SERVICE_PROVIDER_SCHEDULE_PEDIATRICIAN_ACTION;

        case 81: // 'Q'
            return PHARMACY_GET_NON_PRESCRIPTION_DRUGS_ACTION;

        case 82: // 'R'
            return PICK_UP_PRESCRIPTION_FROM_STORE_ACTION;

        case 161: 
            return REGISTER_CAR_ACTION;

        case 83: // 'S'
            return RENTALCAR_TAXI_BOOKTAXI_ACTION;

        case 84: // 'T'
            return RENTALCAR_TAXI_BOOK_RENTAL_CAR_ACTION;

        case 85: // 'U'
            return RENTALCAR_TAXI_CANCEL_RENTAL_CAR_ACTION;

        case 86: // 'V'
            return STAMPS_OFFLINEOR_ONLINE_ACTION;

        case 87: // 'W'
            return STAMPS_ONLINE_ACTION;

        case 88: // 'X'
            return TRAFFIC_TICKETS_PAY_PARKING_TICKET_ACTION;

        case 89: // 'Y'
            return TRAFFIC_TICKETS_PAY_SPEEDING_TICKET_ACTION;

        case 90: // 'Z'
            return VACCINATIONS_FLU_SHOT_ACTION;

        case 91: // '['
            return VACCINATIONS_FLU_SHOT_AT_PHARMACY_ACTION;

        case 92: // '\\'
            return VITAMINS_AND_SUPPLEMENTS_SUPPLEMENTS_ACTION;

        case 93: // ']'
            return VITAMINS_AND_SUPPLEMENTS_VITAMINS_ACTION;

        case 114: // 'r'
            return WATCH_MOVIE_ACTION;
        }
    }

    public static AnnotationType[] values()
    {
        return (AnnotationType[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        ACTIVATE_BANK_CARD_ACTION = new AnnotationType("ACTIVATE_BANK_CARD_ACTION", 0, 211);
        APPLY_CITIZENSHIP_ACTION = new AnnotationType("APPLY_CITIZENSHIP_ACTION", 1, 198);
        APPLY_DRIVERS_LICENSE_ACTION = new AnnotationType("APPLY_DRIVERS_LICENSE_ACTION", 2, 217);
        APPLY_LICENSE_ACTION = new AnnotationType("APPLY_LICENSE_ACTION", 3, 248);
        APPLY_LOAN_ACTION = new AnnotationType("APPLY_LOAN_ACTION", 4, 186);
        APPLY_MARRIAGE_LICENSE_ACTION = new AnnotationType("APPLY_MARRIAGE_LICENSE_ACTION", 5, 149);
        APPLY_PASSPORT_ACTION = new AnnotationType("APPLY_PASSPORT_ACTION", 6, 212);
        APPLY_VISA_ACTION = new AnnotationType("APPLY_VISA_ACTION", 7, 162);
        BOOK_FLIGHT_ACTION = new AnnotationType("BOOK_FLIGHT_ACTION", 8, 100);
        BOOK_HOTEL_ACTION = new AnnotationType("BOOK_HOTEL_ACTION", 9, 101);
        BOOK_RESTAURANT_ACTION = new AnnotationType("BOOK_RESTAURANT_ACTION", 10, 122);
        BRING_ITEM_ACTION = new AnnotationType("BRING_ITEM_ACTION", 11, 318);
        BRING_ITEM_LOCATION = new AnnotationType("BRING_ITEM_LOCATION", 12, 319);
        BROUGHT_ITEM = new AnnotationType("BROUGHT_ITEM", 13, 320);
        BUY_EVENT_TICKET_ACTION = new AnnotationType("BUY_EVENT_TICKET_ACTION", 14, 269);
        BUY_GROCERY_ITEM_ACTION = new AnnotationType("BUY_GROCERY_ITEM_ACTION", 15, 157);
        BUY_PRODUCT_ACTION = new AnnotationType("BUY_PRODUCT_ACTION", 16, 32);
        BUY_STOCK_ACTION = new AnnotationType("BUY_STOCK_ACTION", 17, 207);
        CALL_CONTACT_ACTION = new AnnotationType("CALL_CONTACT_ACTION", 18, 28);
        CALL_LOCAL_ALIAS_ACTION = new AnnotationType("CALL_LOCAL_ALIAS_ACTION", 19, 251);
        CALL_LOCAL_BUSINESS_ACTION = new AnnotationType("CALL_LOCAL_BUSINESS_ACTION", 20, 112);
        CALL_MEDICAL_PROFESSIONAL_ACTION = new AnnotationType("CALL_MEDICAL_PROFESSIONAL_ACTION", 21, 127);
        CALL_ORGANIZATION_ACTION = new AnnotationType("CALL_ORGANIZATION_ACTION", 22, 213);
        CANCEL_APPOINTMENT_ACTION = new AnnotationType("CANCEL_APPOINTMENT_ACTION", 23, 252);
        CANCEL_BANK_ACCOUNT_ACTION = new AnnotationType("CANCEL_BANK_ACCOUNT_ACTION", 24, 239);
        CANCEL_BANK_CARD_ACTION = new AnnotationType("CANCEL_BANK_CARD_ACTION", 25, 178);
        CANCEL_FLIGHT_ACTION = new AnnotationType("CANCEL_FLIGHT_ACTION", 26, 116);
        CANCEL_HOTEL_ACTION = new AnnotationType("CANCEL_HOTEL_ACTION", 27, 121);
        CANCEL_RESTAURANT_ACTION = new AnnotationType("CANCEL_RESTAURANT_ACTION", 28, 126);
        CANCEL_SUBSCRIPTION_ACTION = new AnnotationType("CANCEL_SUBSCRIPTION_ACTION", 29, 179);
        CHECK_BANK_ACTION = new AnnotationType("CHECK_BANK_ACTION", 30, 240);
        CHECKIN_FLIGHT_ACTION = new AnnotationType("CHECKIN_FLIGHT_ACTION", 31, 173);
        CLEAN_CAR_ACTION = new AnnotationType("CLEAN_CAR_ACTION", 32, 218);
        CLEAN_HOUSEHOLD_ACTION = new AnnotationType("CLEAN_HOUSEHOLD_ACTION", 33, 189);
        COMPLETE_GOVERNMENT_FORM_ACTION = new AnnotationType("COMPLETE_GOVERNMENT_FORM_ACTION", 34, 236);
        COPY_DOCUMENT_ACTION = new AnnotationType("COPY_DOCUMENT_ACTION", 35, 164);
        COPY_PASSPORT_ACTION = new AnnotationType("COPY_PASSPORT_ACTION", 36, 204);
        COPY_VISA_ACTION = new AnnotationType("COPY_VISA_ACTION", 37, 132);
        DROP_OFF_CONTACT_ACTION = new AnnotationType("DROP_OFF_CONTACT_ACTION", 38, 147);
        DROP_OFF_DRY_CLEANING_ACTION = new AnnotationType("DROP_OFF_DRY_CLEANING_ACTION", 39, 154);
        DROP_OFF_PASSPORT_ACTION = new AnnotationType("DROP_OFF_PASSPORT_ACTION", 40, 205);
        DROP_OFF_PRESCRIPTION_ACTION = new AnnotationType("DROP_OFF_PRESCRIPTION_ACTION", 41, 214);
        DROP_OFF_VISA_ACTION = new AnnotationType("DROP_OFF_VISA_ACTION", 42, 160);
        EAT_ACTION = new AnnotationType("EAT_ACTION", 43, 321);
        EDIT_DOCUMENT_ACTION = new AnnotationType("EDIT_DOCUMENT_ACTION", 44, 105);
        EMAIL_CONTACT_ACTION = new AnnotationType("EMAIL_CONTACT_ACTION", 45, 109);
        FIND_BANK_ACCOUNT_ACTION = new AnnotationType("FIND_BANK_ACCOUNT_ACTION", 46, 241);
        FIND_BANK_CARD_ACTION = new AnnotationType("FIND_BANK_CARD_ACTION", 47, 168);
        FIND_JOB_ACTION = new AnnotationType("FIND_JOB_ACTION", 48, 274);
        FIND_LOCAL_PROFESSIONAL_ACTION = new AnnotationType("FIND_LOCAL_PROFESSIONAL_ACTION", 49, 183);
        FIND_MEDICAL_PROFESSIONAL_ACTION = new AnnotationType("FIND_MEDICAL_PROFESSIONAL_ACTION", 50, 129);
        FIND_SUBSCRIPTION_ACTION = new AnnotationType("FIND_SUBSCRIPTION_ACTION", 51, 145);
        FLEX_SCHEDULE = new AnnotationType("FLEX_SCHEDULE", 52, 322);
        FLEX_TIMESLOT = new AnnotationType("FLEX_TIMESLOT", 53, 327);
        FOOD_ITEM = new AnnotationType("FOOD_ITEM", 54, 315);
        HOLD_MAIL_ACTION = new AnnotationType("HOLD_MAIL_ACTION", 55, 270);
        LOAN_PAYMENT_ACTION = new AnnotationType("LOAN_PAYMENT_ACTION", 56, 306);
        LISTEN_ALBUM_ACTION = new AnnotationType("LISTEN_ALBUM_ACTION", 57, 216);
        LISTEN_SONG_ACTION = new AnnotationType("LISTEN_SONG_ACTION", 58, 191);
        MEET_CONTACT_ACTION = new AnnotationType("MEET_CONTACT_ACTION", 59, 29);
        NOTARIZE_DOCUMENT_ACTION = new AnnotationType("NOTARIZE_DOCUMENT_ACTION", 60, 165);
        PACK_FOR_PLACE_ACTION = new AnnotationType("PACK_FOR_PLACE_ACTION", 61, 169);
        PAY_BILL_ACTION = new AnnotationType("PAY_BILL_ACTION", 62, 166);
        PAY_CONTACT_ACTION = new AnnotationType("PAY_CONTACT_ACTION", 63, 107);
        PAY_MONEY_CONTACT_ACTION = new AnnotationType("PAY_MONEY_CONTACT_ACTION", 64, 285);
        PAY_LOAN_ACTION = new AnnotationType("PAY_LOAN_ACTION", 65, 188);
        PAY_MOVING_VIOLATION_ACTION = new AnnotationType("PAY_MOVING_VIOLATION_ACTION", 66, 197);
        PAY_PARKING_TICKET_ACTION = new AnnotationType("PAY_PARKING_TICKET_ACTION", 67, 175);
        PAY_RENT_ACTION = new AnnotationType("PAY_RENT_ACTION", 68, 253);
        PAY_TAX_ACTION = new AnnotationType("PAY_TAX_ACTION", 69, 185);
        PERFORM_ROUTINE_ACTION = new AnnotationType("PERFORM_ROUTINE_ACTION", 70, 292);
        PICK_UP_CONTACT_ACTION = new AnnotationType("PICK_UP_CONTACT_ACTION", 71, 146);
        PICK_UP_DRY_CLEANING_ACTION = new AnnotationType("PICK_UP_DRY_CLEANING_ACTION", 72, 156);
        PICK_UP_PASSPORT_ACTION = new AnnotationType("PICK_UP_PASSPORT_ACTION", 73, 206);
        PICK_UP_PRESCRIPTION_ACTION = new AnnotationType("PICK_UP_PRESCRIPTION_ACTION", 74, 153);
        PICK_UP_VISA_ACTION = new AnnotationType("PICK_UP_VISA_ACTION", 75, 140);
        PREPARE_FOOD_ACTION = new AnnotationType("PREPARE_FOOD_ACTION", 76, 311);
        PREPARE_FOOD_LOCATION_ACTION = new AnnotationType("PREPARE_FOOD_LOCATION_ACTION", 77, 312);
        PREPARE_FOOD_LOCATION = new AnnotationType("PREPARE_FOOD_LOCATION", 78, 313);
        READ_BOOK_ACTION = new AnnotationType("READ_BOOK_ACTION", 79, 194);
        REPAIR_CAR_ACTION = new AnnotationType("REPAIR_CAR_ACTION", 80, 133);
        REGISTER_EXAM_ACTION = new AnnotationType("REGISTER_EXAM_ACTION", 81, 234);
        REGISTER_MOTORCYCLE_ACTION = new AnnotationType("REGISTER_MOTORCYCLE_ACTION", 82, 134);
        REGISTER_VEHICLE_ACTION = new AnnotationType("REGISTER_VEHICLE_ACTION", 83, 244);
        REGISTER_VOTE_ACTION = new AnnotationType("REGISTER_VOTE_ACTION", 84, 228);
        RENEW_DRIVERS_LICENSE_ACTION = new AnnotationType("RENEW_DRIVERS_LICENSE_ACTION", 85, 143);
        RENEW_LICENSE_ACTION = new AnnotationType("RENEW_LICENSE_ACTION", 86, 243);
        RENEW_PASSPORT_ACTION = new AnnotationType("RENEW_PASSPORT_ACTION", 87, 203);
        RENEW_VEHICLE_REGISTRATION_ACTION = new AnnotationType("RENEW_VEHICLE_REGISTRATION_ACTION", 88, 246);
        RENEW_VISA_ACTION = new AnnotationType("RENEW_VISA_ACTION", 89, 135);
        REPAIR_PRODUCT_ACTION = new AnnotationType("REPAIR_PRODUCT_ACTION", 90, 142);
        RETURN_PRODUCT_ACTION = new AnnotationType("RETURN_PRODUCT_ACTION", 91, 104);
        ROUTINE = new AnnotationType("ROUTINE", 92, 291);
        SCHEDULE_ACTION = new AnnotationType("SCHEDULE_ACTION", 93, 232);
        SELL_STOCK_ACTION = new AnnotationType("SELL_STOCK_ACTION", 94, 208);
        SEND_LETTER_ACTION = new AnnotationType("SEND_LETTER_ACTION", 95, 210);
        SEND_PACKAGE_ACTION = new AnnotationType("SEND_PACKAGE_ACTION", 96, 137);
        SMS_CONTACT_ACTION = new AnnotationType("SMS_CONTACT_ACTION", 97, 302);
        STUDY_ACTION = new AnnotationType("STUDY_ACTION", 98, 299);
        TAKE_MEDICINE_ACTION = new AnnotationType("TAKE_MEDICINE_ACTION", 99, 307);
        TAKE_OUT_TRASH_ACTION = new AnnotationType("TAKE_OUT_TRASH_ACTION", 100, 310);
        TRACK_FLIGHT_ACTION = new AnnotationType("TRACK_FLIGHT_ACTION", 101, 119);
        TRACK_PACKAGE_ACTION = new AnnotationType("TRACK_PACKAGE_ACTION", 102, 141);
        US_STATE = new AnnotationType("US_STATE", 103, 229);
        WATCH_FILM_ACTION = new AnnotationType("WATCH_FILM_ACTION", 104, 174);
        WATCH_VIDEO_ACTION = new AnnotationType("WATCH_VIDEO_ACTION", 105, 130);
        ANNIVERSARY_EVENT = new AnnotationType("ANNIVERSARY_EVENT", 106, 262);
        APPOINTMENT_EVENT = new AnnotationType("APPOINTMENT_EVENT", 107, 294);
        BABY_SHOWER_EVENT = new AnnotationType("BABY_SHOWER_EVENT", 108, 276);
        BABYSIT_EVENT = new AnnotationType("BABYSIT_EVENT", 109, 277);
        BIRTHDAY_EVENT = new AnnotationType("BIRTHDAY_EVENT", 110, 261);
        CONTACT_STATUS_EVENT_SUFFIX = new AnnotationType("CONTACT_STATUS_EVENT_SUFFIX", 111, 288);
        DINNER_EVENT = new AnnotationType("DINNER_EVENT", 112, 281);
        EXERCISE_EVENT = new AnnotationType("EXERCISE_EVENT", 113, 295);
        GIFT_EVENT = new AnnotationType("GIFT_EVENT", 114, 290);
        LUNCH_EVENT = new AnnotationType("LUNCH_EVENT", 115, 278);
        MEETING_EVENT = new AnnotationType("MEETING_EVENT", 116, 287);
        PARTY_EVENT = new AnnotationType("PARTY_EVENT", 117, 283);
        REMINDER_EVENT = new AnnotationType("REMINDER_EVENT", 118, 286);
        SHOW_EVENT = new AnnotationType("SHOW_EVENT", 119, 296);
        TRAVEL_EVENT = new AnnotationType("TRAVEL_EVENT", 120, 289);
        VISIT_EVENT = new AnnotationType("VISIT_EVENT", 121, 280);
        WEDDING_EVENT = new AnnotationType("WEDDING_EVENT", 122, 279);
        WORK_EVENT = new AnnotationType("WORK_EVENT", 123, 284);
        ACTION = new AnnotationType("ACTION", 124, 1);
        AIRLINE = new AnnotationType("AIRLINE", 125, 6);
        AIRLINE_CODE = new AnnotationType("AIRLINE_CODE", 126, 117);
        AIRPORT = new AnnotationType("AIRPORT", 127, 215);
        AIRPORT_CODE = new AnnotationType("AIRPORT_CODE", 128, 182);
        AND = new AnnotationType("AND", 129, 264);
        APPOINTMENT = new AnnotationType("APPOINTMENT", 130, 233);
        AT = new AnnotationType("AT", 131, 123);
        BANK_ACCOUNT = new AnnotationType("BANK_ACCOUNT", 132, 242);
        BANK_CARD = new AnnotationType("BANK_CARD", 133, 167);
        BANK_CARD_TYPE = new AnnotationType("BANK_CARD_TYPE", 134, 267);
        BANK = new AnnotationType("BANK", 135, 7);
        BILL = new AnnotationType("BILL", 136, 131);
        BIRTHDAY_EVENT_ADJ = new AnnotationType("BIRTHDAY_EVENT_ADJ", 137, 303);
        BOOK = new AnnotationType("BOOK", 138, 195);
        BOOKED_FLIGHT = new AnnotationType("BOOKED_FLIGHT", 139, 110);
        BOOKED_HOTEL = new AnnotationType("BOOKED_HOTEL", 140, 120);
        BOOKED_RESTAURANT = new AnnotationType("BOOKED_RESTAURANT", 141, 125);
        CAR = new AnnotationType("CAR", 142, 136);
        CITIZENSHIP = new AnnotationType("CITIZENSHIP", 143, 201);
        CITIZENSHIP_TYPE = new AnnotationType("CITIZENSHIP_TYPE", 144, 330);
        CITY = new AnnotationType("CITY", 145, 181);
        CLEANABLE_HOUSEHOLD_ITEM = new AnnotationType("CLEANABLE_HOUSEHOLD_ITEM", 146, 231);
        CONNECTOR = new AnnotationType("CONNECTOR", 147, 259);
        CONTACT = new AnnotationType("CONTACT", 148, 2);
        CONTACT_SEPARATOR = new AnnotationType("CONTACT_SEPARATOR", 149, 297);
        CONTACT_SUFFIX = new AnnotationType("CONTACT_SUFFIX", 150, 273);
        CONTACTS = new AnnotationType("CONTACTS", 151, 263);
        COUNTRY = new AnnotationType("COUNTRY", 152, 199);
        DATE = new AnnotationType("DATE", 153, 316);
        DATETIME = new AnnotationType("DATETIME", 154, 298);
        DOCUMENT = new AnnotationType("DOCUMENT", 155, 96);
        DRIVERS_LICENSE = new AnnotationType("DRIVERS_LICENSE", 156, 148);
        DRY_CLEANING = new AnnotationType("DRY_CLEANING", 157, 155);
        DUE_BILL = new AnnotationType("DUE_BILL", 158, 256);
        EXAM = new AnnotationType("EXAM", 159, 235);
        EVENT = new AnnotationType("EVENT", 160, 258);
        EVENT_TICKET = new AnnotationType("EVENT_TICKET", 161, 268);
        EVENT_TIME = new AnnotationType("EVENT_TIME", 162, 266);
        FILM = new AnnotationType("FILM", 163, 180);
        FLIGHT = new AnnotationType("FLIGHT", 164, 106);
        FLIGHT_DESTINATION = new AnnotationType("FLIGHT_DESTINATION", 165, 170);
        FLIGHT_NUMBER = new AnnotationType("FLIGHT_NUMBER", 166, 118);
        FLIGHT_ORIGIN = new AnnotationType("FLIGHT_ORIGIN", 167, 171);
        FLIGHT_TICKET = new AnnotationType("FLIGHT_TICKET", 168, 260);
        FOR = new AnnotationType("FOR", 169, 138);
        FROM = new AnnotationType("FROM", 170, 220);
        GOVERNMENT_FORM = new AnnotationType("GOVERNMENT_FORM", 171, 237);
        GROCERY_ITEM = new AnnotationType("GROCERY_ITEM", 172, 3);
        HOLIDAY = new AnnotationType("HOLIDAY", 173, 224);
        HOTEL = new AnnotationType("HOTEL", 174, 226);
        HOTEL_DESTINATION = new AnnotationType("HOTEL_DESTINATION", 175, 172);
        HOUSE = new AnnotationType("HOUSE", 176, 190);
        IN = new AnnotationType("IN", 177, 219);
        INDUSTRY = new AnnotationType("INDUSTRY", 178, 222);
        JOB = new AnnotationType("JOB", 179, 275);
        LETTER = new AnnotationType("LETTER", 180, 139);
        LICENSE = new AnnotationType("LICENSE", 181, 249);
        LOAN = new AnnotationType("LOAN", 182, 187);
        LOAN_PAYMENT = new AnnotationType("LOAN_PAYMENT", 183, 305);
        LOCAL_ALIAS = new AnnotationType("LOCAL_ALIAS", 184, 250);
        LOCAL_BUSINESS = new AnnotationType("LOCAL_BUSINESS", 185, 26);
        LOCAL_PROFESSIONAL = new AnnotationType("LOCAL_PROFESSIONAL", 186, 159);
        LOCATION = new AnnotationType("LOCATION", 187, 103);
        MAIL = new AnnotationType("MAIL", 188, 271);
        MAIL_HOLD = new AnnotationType("MAIL_HOLD", 189, 272);
        MARRIAGE_LICENSE = new AnnotationType("MARRIAGE_LICENSE", 190, 150);
        MEDICAL_PROFESSIONAL = new AnnotationType("MEDICAL_PROFESSIONAL", 191, 128);
        MEDICINE = new AnnotationType("MEDICINE", 192, 308);
        MONEY = new AnnotationType("MONEY", 193, 108);
        MONEY_WORD = new AnnotationType("MONEY_WORD", 194, 282);
        MOTORCYCLE = new AnnotationType("MOTORCYCLE", 195, 158);
        MOVING_VIOLATION = new AnnotationType("MOVING_VIOLATION", 196, 200);
        MUSIC_ALBUM = new AnnotationType("MUSIC_ALBUM", 197, 193);
        MY = new AnnotationType("MY", 198, 333);
        ON = new AnnotationType("ON", 199, 223);
        ORGANIZATION = new AnnotationType("ORGANIZATION", 200, 151);
        PACKAGE = new AnnotationType("PACKAGE", 201, 176);
        PACKING_LIST_ITEM = new AnnotationType("PACKING_LIST_ITEM", 202, 324);
        PARKING_TICKET = new AnnotationType("PARKING_TICKET", 203, 196);
        PASSPORT = new AnnotationType("PASSPORT", 204, 202);
        PASSPORT_TYPE = new AnnotationType("PASSPORT_TYPE", 205, 329);
        PAST_EVENT = new AnnotationType("PAST_EVENT", 206, 314);
        PAST_REMINDER = new AnnotationType("PAST_REMINDER", 207, 255);
        PRESCRIPTION = new AnnotationType("PRESCRIPTION", 208, 152);
        PRODUCT = new AnnotationType("PRODUCT", 209, 31);
        PROFESSIONAL = new AnnotationType("PROFESSIONAL", 210, 230);
        PURCHASED_PRODUCT = new AnnotationType("PURCHASED_PRODUCT", 211, 97);
        REGISTRATION = new AnnotationType("REGISTRATION", 212, 247);
        RENT = new AnnotationType("RENT", 213, 254);
        RENTAL_CAR_SERVICE = new AnnotationType("RENTAL_CAR_SERVICE", 214, 18);
        RESTAURANT = new AnnotationType("RESTAURANT", 215, 124);
        RESTAURANT_TYPE = new AnnotationType("RESTAURANT_TYPE", 216, 328);
        SERVICE = new AnnotationType("SERVICE", 217, 4);
        SONG = new AnnotationType("SONG", 218, 192);
        STOCK = new AnnotationType("STOCK", 219, 209);
        STUDY_OBJECT = new AnnotationType("STUDY_OBJECT", 220, 304);
        STUDY_TASK = new AnnotationType("STUDY_TASK", 221, 301);
        SUBJECT_TO_STUDY = new AnnotationType("SUBJECT_TO_STUDY", 222, 300);
        SUBSCRIPTION = new AnnotationType("SUBSCRIPTION", 223, 184);
        TAX = new AnnotationType("TAX", 224, 177);
        TEXT = new AnnotationType("TEXT", 225, 0);
        THE = new AnnotationType("THE", 226, 332);
        TIME = new AnnotationType("TIME", 227, 317);
        TO = new AnnotationType("TO", 228, 144);
        TO_DO = new AnnotationType("TO_DO", 229, 325);
        TRASH = new AnnotationType("TRASH", 230, 309);
        TV_SHOW = new AnnotationType("TV_SHOW", 231, 326);
        USER_HOME = new AnnotationType("USER_HOME", 232, 225);
        VEHICLE = new AnnotationType("VEHICLE", 233, 245);
        VISA = new AnnotationType("VISA", 234, 163);
        VISA_TYPE = new AnnotationType("VISA_TYPE", 235, 331);
        VOTE = new AnnotationType("VOTE", 236, 227);
        WEB_QUERY = new AnnotationType("WEB_QUERY", 237, 5);
        WITH = new AnnotationType("WITH", 238, 265);
        YOUTUBE = new AnnotationType("YOUTUBE", 239, 115);
        CREDIT_CARD_SERVICE = new AnnotationType("CREDIT_CARD_SERVICE", 240, 8);
        CREDIT_SCORE_PROVIDER = new AnnotationType("CREDIT_SCORE_PROVIDER", 241, 9);
        DENTAL_CARE_PROVIDER = new AnnotationType("DENTAL_CARE_PROVIDER", 242, 10);
        HEALTH_INSURANCE_PROVIDER = new AnnotationType("HEALTH_INSURANCE_PROVIDER", 243, 11);
        ISPTELCO = new AnnotationType("ISPTELCO", 244, 12);
        LOCAL_DEPARTMENT_STORE = new AnnotationType("LOCAL_DEPARTMENT_STORE", 245, 13);
        NON_PRESCRIPTION_DRUGS = new AnnotationType("NON_PRESCRIPTION_DRUGS", 246, 14);
        OCCUPATIONAL_COMMODITY_SERVICE_PROVIDER = new AnnotationType("OCCUPATIONAL_COMMODITY_SERVICE_PROVIDER", 247, 15);
        OCCUPATIONAL_NON_COMMODITY_SERVICE_PROVIDER = new AnnotationType("OCCUPATIONAL_NON_COMMODITY_SERVICE_PROVIDER", 248, 16);
        PHARMACY_STORES = new AnnotationType("PHARMACY_STORES", 249, 17);
        SATELLITE_PROVIDER = new AnnotationType("SATELLITE_PROVIDER", 250, 19);
        SHIPPING_PROVIDER = new AnnotationType("SHIPPING_PROVIDER", 251, 20);
        SPORTS_STORES = new AnnotationType("SPORTS_STORES", 252, 21);
        TAX_PROVIDER = new AnnotationType("TAX_PROVIDER", 253, 22);
        TECH_PROVIDER_SUPPORT = new AnnotationType("TECH_PROVIDER_SUPPORT", 254, 23);
        TRAVEL_BROKER = new AnnotationType("TRAVEL_BROKER", 255, 24);
        WASTE_MANAGEMENT_PROVIDER = new AnnotationType("WASTE_MANAGEMENT_PROVIDER", 256, 25);
        MOVIE = new AnnotationType("MOVIE", 257, 113);
        CALL_LOCAL_PROFESSIONAL_ACTION = new AnnotationType("CALL_LOCAL_PROFESSIONAL_ACTION", 258, 221);
        CALL_SERVICE_ACTION = new AnnotationType("CALL_SERVICE_ACTION", 259, 27);
        CANCEL_SERVICE_ACTION = new AnnotationType("CANCEL_SERVICE_ACTION", 260, 94);
        BUY_GROCERY_ACTION = new AnnotationType("BUY_GROCERY_ACTION", 261, 30);
        LOOKUP_WEB_QUERY_ACTION = new AnnotationType("LOOKUP_WEB_QUERY_ACTION", 262, 33);
        FLIGHT_STATS_ACTION = new AnnotationType("FLIGHT_STATS_ACTION", 263, 95);
        PACK_FOR_TRIP_ACTION = new AnnotationType("PACK_FOR_TRIP_ACTION", 264, 102);
        FLIGHT_CHECKIN_ACTION = new AnnotationType("FLIGHT_CHECKIN_ACTION", 265, 111);
        ORIGIN = new AnnotationType("ORIGIN", 266, 98);
        DESTINATION = new AnnotationType("DESTINATION", 267, 99);
        BATTERIES_HEARING_AID_BATTERIES_ACTION = new AnnotationType("BATTERIES_HEARING_AID_BATTERIES_ACTION", 268, 34);
        BATTERIES_RECHARGEABLE_BATTERIES_ACTION = new AnnotationType("BATTERIES_RECHARGEABLE_BATTERIES_ACTION", 269, 35);
        CAR_INSPECTION_AIR_FILTER_CHANGE_REPLACE_BUY_ACTION = new AnnotationType("CAR_INSPECTION_AIR_FILTER_CHANGE_REPLACE_BUY_ACTION", 270, 36);
        CAR_INSPECTION_BUY_TIRES_ACTION = new AnnotationType("CAR_INSPECTION_BUY_TIRES_ACTION", 271, 37);
        CAR_INSPECTION_FIND_OIL_CHANGE_ACTION = new AnnotationType("CAR_INSPECTION_FIND_OIL_CHANGE_ACTION", 272, 38);
        CAR_INSPECTION_GET_AIR_FOR_TIRES_ACTION = new AnnotationType("CAR_INSPECTION_GET_AIR_FOR_TIRES_ACTION", 273, 39);
        CAR_INSPECTION_GET_OIL_CHANGE_ACTION = new AnnotationType("CAR_INSPECTION_GET_OIL_CHANGE_ACTION", 274, 40);
        CAR_INSPECTION_GET_SMOG_CHECK_ACTION = new AnnotationType("CAR_INSPECTION_GET_SMOG_CHECK_ACTION", 275, 41);
        CAR_INSPECTION_SCHEDULE_OIL_CHANGE_ACTION = new AnnotationType("CAR_INSPECTION_SCHEDULE_OIL_CHANGE_ACTION", 276, 42);
        CAR_INSPECTION_TIRE_PRESSURE_ACTION = new AnnotationType("CAR_INSPECTION_TIRE_PRESSURE_ACTION", 277, 43);
        CAR_INSPECTION_TIRE_ROTATION_ACTION = new AnnotationType("CAR_INSPECTION_TIRE_ROTATION_ACTION", 278, 44);
        CAR_MAINTENANCE_CAR_WASH_ACTION = new AnnotationType("CAR_MAINTENANCE_CAR_WASH_ACTION", 279, 45);
        CAR_MAINTENANCE_CAR_WAXING_ACTION = new AnnotationType("CAR_MAINTENANCE_CAR_WAXING_ACTION", 280, 46);
        CITY_OR_AIRPORT_CODE = new AnnotationType("CITY_OR_AIRPORT_CODE", 281, 47);
        DENTIST_FIND_DENTIST_ACTION = new AnnotationType("DENTIST_FIND_DENTIST_ACTION", 282, 48);
        DEPARTMENT_STORE = new AnnotationType("DEPARTMENT_STORE", 283, 49);
        DMV_CALL_DMVACTION = new AnnotationType("DMV_CALL_DMVACTION", 284, 50);
        DMV_DRIVING_TEST_ACTION = new AnnotationType("DMV_DRIVING_TEST_ACTION", 285, 51);
        DMV_GO_TO_DMVACTION = new AnnotationType("DMV_GO_TO_DMVACTION", 286, 52);
        DOCTOR_CALL_ACTION = new AnnotationType("DOCTOR_CALL_ACTION", 287, 53);
        DOCTOR_FIND_DOCTOR_ACTION = new AnnotationType("DOCTOR_FIND_DOCTOR_ACTION", 288, 54);
        DRIVING_SCHOOL_FIND_SCHOOL_ACTION = new AnnotationType("DRIVING_SCHOOL_FIND_SCHOOL_ACTION", 289, 55);
        FLEX_TIME_DURATION = new AnnotationType("FLEX_TIME_DURATION", 290, 323);
        FLIGHTS_BOOK_FLIGHTS_TO_ACTION = new AnnotationType("FLIGHTS_BOOK_FLIGHTS_TO_ACTION", 291, 56);
        FLOWERS_CALL_FIND_FLORIST_ACTION = new AnnotationType("FLOWERS_CALL_FIND_FLORIST_ACTION", 292, 57);
        FLOWERS_GET_FLOWERS_ACTION = new AnnotationType("FLOWERS_GET_FLOWERS_ACTION", 293, 58);
        FLOWERS_GET_FLOWERS_BIRTHDAY_ACTION = new AnnotationType("FLOWERS_GET_FLOWERS_BIRTHDAY_ACTION", 294, 59);
        FLOWERS_GET_FLOWERS_MOM_ACTION = new AnnotationType("FLOWERS_GET_FLOWERS_MOM_ACTION", 295, 60);
        HAIRCUT_GET_HAIRCUT_ACTION = new AnnotationType("HAIRCUT_GET_HAIRCUT_ACTION", 296, 61);
        LOCKSMITH_BUY_ACCESSORIES_ACTION = new AnnotationType("LOCKSMITH_BUY_ACCESSORIES_ACTION", 297, 62);
        LOCKSMITH_FIND_LOCKSMITH_ACTION = new AnnotationType("LOCKSMITH_FIND_LOCKSMITH_ACTION", 298, 63);
        LOCKSMITH_LOCKSMITH_SERVICES_ACTION = new AnnotationType("LOCKSMITH_LOCKSMITH_SERVICES_ACTION", 299, 64);
        MAILING_STUFF_CHANGE_FORWARDING_ADDRESS_FOR_MAIL_ACTION = new AnnotationType("MAILING_STUFF_CHANGE_FORWARDING_ADDRESS_FOR_MAIL_ACTION", 300, 65);
        MAILING_STUFF_CHANGE_MAILING_ADDRESS_ACTION = new AnnotationType("MAILING_STUFF_CHANGE_MAILING_ADDRESS_ACTION", 301, 66);
        OCCUPATIONAL_NON_COMMODITY_SERVICE = new AnnotationType("OCCUPATIONAL_NON_COMMODITY_SERVICE", 302, 67);
        OCCUPATIONAL_SERVICE_PROVIDER_CHIMNEY_SWEEP_ACTION = new AnnotationType("OCCUPATIONAL_SERVICE_PROVIDER_CHIMNEY_SWEEP_ACTION", 303, 68);
        OCCUPATIONAL_SERVICE_PROVIDER_CLEANING_ACTION = new AnnotationType("OCCUPATIONAL_SERVICE_PROVIDER_CLEANING_ACTION", 304, 69);
        OCCUPATIONAL_SERVICE_PROVIDER_FIND_COMMODITY_ACTION = new AnnotationType("OCCUPATIONAL_SERVICE_PROVIDER_FIND_COMMODITY_ACTION", 305, 70);
        OCCUPATIONAL_SERVICE_PROVIDER_FIND_NON_COMMODITY_ACTION = new AnnotationType("OCCUPATIONAL_SERVICE_PROVIDER_FIND_NON_COMMODITY_ACTION", 306, 71);
        OCCUPATIONAL_SERVICE_PROVIDER_OPTOMETRIST_CANCEL_ACTION = new AnnotationType("OCCUPATIONAL_SERVICE_PROVIDER_OPTOMETRIST_CANCEL_ACTION", 307, 72);
        OCCUPATIONAL_SERVICE_PROVIDER_OPTOMETRIST_SCHEDULE_ACTION = new AnnotationType("OCCUPATIONAL_SERVICE_PROVIDER_OPTOMETRIST_SCHEDULE_ACTION", 308, 73);
        OCCUPATIONAL_SERVICE_PROVIDER_PAY_NON_COMMODITY_SERVICE_ACTION = new AnnotationType("OCCUPATIONAL_SERVICE_PROVIDER_PAY_NON_COMMODITY_SERVICE_ACTION", 309, 74);
        OCCUPATIONAL_SERVICE_PROVIDER_PEDICURE_ACTION = new AnnotationType("OCCUPATIONAL_SERVICE_PROVIDER_PEDICURE_ACTION", 310, 75);
        OCCUPATIONAL_SERVICE_PROVIDER_PIANO_TUNER_ACTION = new AnnotationType("OCCUPATIONAL_SERVICE_PROVIDER_PIANO_TUNER_ACTION", 311, 76);
        OCCUPATIONAL_SERVICE_PROVIDER_SCHEDULE_COMMODITY_ACTION = new AnnotationType("OCCUPATIONAL_SERVICE_PROVIDER_SCHEDULE_COMMODITY_ACTION", 312, 77);
        OCCUPATIONAL_SERVICE_PROVIDER_SCHEDULE_EXISTING_NON_COMMODITY_SERVICE_PROVIDER_ACTION = new AnnotationType("OCCUPATIONAL_SERVICE_PROVIDER_SCHEDULE_EXISTING_NON_COMMODITY_SERVICE_PROVIDER_ACTION", 313, 78);
        OCCUPATIONAL_SERVICE_PROVIDER_SCHEDULE_NON_COMMODITY_ACTION = new AnnotationType("OCCUPATIONAL_SERVICE_PROVIDER_SCHEDULE_NON_COMMODITY_ACTION", 314, 79);
        OCCUPATIONAL_SERVICE_PROVIDER_SCHEDULE_PEDIATRICIAN_ACTION = new AnnotationType("OCCUPATIONAL_SERVICE_PROVIDER_SCHEDULE_PEDIATRICIAN_ACTION", 315, 80);
        PHARMACY_GET_NON_PRESCRIPTION_DRUGS_ACTION = new AnnotationType("PHARMACY_GET_NON_PRESCRIPTION_DRUGS_ACTION", 316, 81);
        PICK_UP_PRESCRIPTION_FROM_STORE_ACTION = new AnnotationType("PICK_UP_PRESCRIPTION_FROM_STORE_ACTION", 317, 82);
        REGISTER_CAR_ACTION = new AnnotationType("REGISTER_CAR_ACTION", 318, 161);
        RENTALCAR_TAXI_BOOKTAXI_ACTION = new AnnotationType("RENTALCAR_TAXI_BOOKTAXI_ACTION", 319, 83);
        RENTALCAR_TAXI_BOOK_RENTAL_CAR_ACTION = new AnnotationType("RENTALCAR_TAXI_BOOK_RENTAL_CAR_ACTION", 320, 84);
        RENTALCAR_TAXI_CANCEL_RENTAL_CAR_ACTION = new AnnotationType("RENTALCAR_TAXI_CANCEL_RENTAL_CAR_ACTION", 321, 85);
        STAMPS_OFFLINEOR_ONLINE_ACTION = new AnnotationType("STAMPS_OFFLINEOR_ONLINE_ACTION", 322, 86);
        STAMPS_ONLINE_ACTION = new AnnotationType("STAMPS_ONLINE_ACTION", 323, 87);
        TRAFFIC_TICKETS_PAY_PARKING_TICKET_ACTION = new AnnotationType("TRAFFIC_TICKETS_PAY_PARKING_TICKET_ACTION", 324, 88);
        TRAFFIC_TICKETS_PAY_SPEEDING_TICKET_ACTION = new AnnotationType("TRAFFIC_TICKETS_PAY_SPEEDING_TICKET_ACTION", 325, 89);
        VACCINATIONS_FLU_SHOT_ACTION = new AnnotationType("VACCINATIONS_FLU_SHOT_ACTION", 326, 90);
        VACCINATIONS_FLU_SHOT_AT_PHARMACY_ACTION = new AnnotationType("VACCINATIONS_FLU_SHOT_AT_PHARMACY_ACTION", 327, 91);
        VITAMINS_AND_SUPPLEMENTS_SUPPLEMENTS_ACTION = new AnnotationType("VITAMINS_AND_SUPPLEMENTS_SUPPLEMENTS_ACTION", 328, 92);
        VITAMINS_AND_SUPPLEMENTS_VITAMINS_ACTION = new AnnotationType("VITAMINS_AND_SUPPLEMENTS_VITAMINS_ACTION", 329, 93);
        WATCH_MOVIE_ACTION = new AnnotationType("WATCH_MOVIE_ACTION", 330, 114);
        $VALUES = (new AnnotationType[] {
            ACTIVATE_BANK_CARD_ACTION, APPLY_CITIZENSHIP_ACTION, APPLY_DRIVERS_LICENSE_ACTION, APPLY_LICENSE_ACTION, APPLY_LOAN_ACTION, APPLY_MARRIAGE_LICENSE_ACTION, APPLY_PASSPORT_ACTION, APPLY_VISA_ACTION, BOOK_FLIGHT_ACTION, BOOK_HOTEL_ACTION, 
            BOOK_RESTAURANT_ACTION, BRING_ITEM_ACTION, BRING_ITEM_LOCATION, BROUGHT_ITEM, BUY_EVENT_TICKET_ACTION, BUY_GROCERY_ITEM_ACTION, BUY_PRODUCT_ACTION, BUY_STOCK_ACTION, CALL_CONTACT_ACTION, CALL_LOCAL_ALIAS_ACTION, 
            CALL_LOCAL_BUSINESS_ACTION, CALL_MEDICAL_PROFESSIONAL_ACTION, CALL_ORGANIZATION_ACTION, CANCEL_APPOINTMENT_ACTION, CANCEL_BANK_ACCOUNT_ACTION, CANCEL_BANK_CARD_ACTION, CANCEL_FLIGHT_ACTION, CANCEL_HOTEL_ACTION, CANCEL_RESTAURANT_ACTION, CANCEL_SUBSCRIPTION_ACTION, 
            CHECK_BANK_ACTION, CHECKIN_FLIGHT_ACTION, CLEAN_CAR_ACTION, CLEAN_HOUSEHOLD_ACTION, COMPLETE_GOVERNMENT_FORM_ACTION, COPY_DOCUMENT_ACTION, COPY_PASSPORT_ACTION, COPY_VISA_ACTION, DROP_OFF_CONTACT_ACTION, DROP_OFF_DRY_CLEANING_ACTION, 
            DROP_OFF_PASSPORT_ACTION, DROP_OFF_PRESCRIPTION_ACTION, DROP_OFF_VISA_ACTION, EAT_ACTION, EDIT_DOCUMENT_ACTION, EMAIL_CONTACT_ACTION, FIND_BANK_ACCOUNT_ACTION, FIND_BANK_CARD_ACTION, FIND_JOB_ACTION, FIND_LOCAL_PROFESSIONAL_ACTION, 
            FIND_MEDICAL_PROFESSIONAL_ACTION, FIND_SUBSCRIPTION_ACTION, FLEX_SCHEDULE, FLEX_TIMESLOT, FOOD_ITEM, HOLD_MAIL_ACTION, LOAN_PAYMENT_ACTION, LISTEN_ALBUM_ACTION, LISTEN_SONG_ACTION, MEET_CONTACT_ACTION, 
            NOTARIZE_DOCUMENT_ACTION, PACK_FOR_PLACE_ACTION, PAY_BILL_ACTION, PAY_CONTACT_ACTION, PAY_MONEY_CONTACT_ACTION, PAY_LOAN_ACTION, PAY_MOVING_VIOLATION_ACTION, PAY_PARKING_TICKET_ACTION, PAY_RENT_ACTION, PAY_TAX_ACTION, 
            PERFORM_ROUTINE_ACTION, PICK_UP_CONTACT_ACTION, PICK_UP_DRY_CLEANING_ACTION, PICK_UP_PASSPORT_ACTION, PICK_UP_PRESCRIPTION_ACTION, PICK_UP_VISA_ACTION, PREPARE_FOOD_ACTION, PREPARE_FOOD_LOCATION_ACTION, PREPARE_FOOD_LOCATION, READ_BOOK_ACTION, 
            REPAIR_CAR_ACTION, REGISTER_EXAM_ACTION, REGISTER_MOTORCYCLE_ACTION, REGISTER_VEHICLE_ACTION, REGISTER_VOTE_ACTION, RENEW_DRIVERS_LICENSE_ACTION, RENEW_LICENSE_ACTION, RENEW_PASSPORT_ACTION, RENEW_VEHICLE_REGISTRATION_ACTION, RENEW_VISA_ACTION, 
            REPAIR_PRODUCT_ACTION, RETURN_PRODUCT_ACTION, ROUTINE, SCHEDULE_ACTION, SELL_STOCK_ACTION, SEND_LETTER_ACTION, SEND_PACKAGE_ACTION, SMS_CONTACT_ACTION, STUDY_ACTION, TAKE_MEDICINE_ACTION, 
            TAKE_OUT_TRASH_ACTION, TRACK_FLIGHT_ACTION, TRACK_PACKAGE_ACTION, US_STATE, WATCH_FILM_ACTION, WATCH_VIDEO_ACTION, ANNIVERSARY_EVENT, APPOINTMENT_EVENT, BABY_SHOWER_EVENT, BABYSIT_EVENT, 
            BIRTHDAY_EVENT, CONTACT_STATUS_EVENT_SUFFIX, DINNER_EVENT, EXERCISE_EVENT, GIFT_EVENT, LUNCH_EVENT, MEETING_EVENT, PARTY_EVENT, REMINDER_EVENT, SHOW_EVENT, 
            TRAVEL_EVENT, VISIT_EVENT, WEDDING_EVENT, WORK_EVENT, ACTION, AIRLINE, AIRLINE_CODE, AIRPORT, AIRPORT_CODE, AND, 
            APPOINTMENT, AT, BANK_ACCOUNT, BANK_CARD, BANK_CARD_TYPE, BANK, BILL, BIRTHDAY_EVENT_ADJ, BOOK, BOOKED_FLIGHT, 
            BOOKED_HOTEL, BOOKED_RESTAURANT, CAR, CITIZENSHIP, CITIZENSHIP_TYPE, CITY, CLEANABLE_HOUSEHOLD_ITEM, CONNECTOR, CONTACT, CONTACT_SEPARATOR, 
            CONTACT_SUFFIX, CONTACTS, COUNTRY, DATE, DATETIME, DOCUMENT, DRIVERS_LICENSE, DRY_CLEANING, DUE_BILL, EXAM, 
            EVENT, EVENT_TICKET, EVENT_TIME, FILM, FLIGHT, FLIGHT_DESTINATION, FLIGHT_NUMBER, FLIGHT_ORIGIN, FLIGHT_TICKET, FOR, 
            FROM, GOVERNMENT_FORM, GROCERY_ITEM, HOLIDAY, HOTEL, HOTEL_DESTINATION, HOUSE, IN, INDUSTRY, JOB, 
            LETTER, LICENSE, LOAN, LOAN_PAYMENT, LOCAL_ALIAS, LOCAL_BUSINESS, LOCAL_PROFESSIONAL, LOCATION, MAIL, MAIL_HOLD, 
            MARRIAGE_LICENSE, MEDICAL_PROFESSIONAL, MEDICINE, MONEY, MONEY_WORD, MOTORCYCLE, MOVING_VIOLATION, MUSIC_ALBUM, MY, ON, 
            ORGANIZATION, PACKAGE, PACKING_LIST_ITEM, PARKING_TICKET, PASSPORT, PASSPORT_TYPE, PAST_EVENT, PAST_REMINDER, PRESCRIPTION, PRODUCT, 
            PROFESSIONAL, PURCHASED_PRODUCT, REGISTRATION, RENT, RENTAL_CAR_SERVICE, RESTAURANT, RESTAURANT_TYPE, SERVICE, SONG, STOCK, 
            STUDY_OBJECT, STUDY_TASK, SUBJECT_TO_STUDY, SUBSCRIPTION, TAX, TEXT, THE, TIME, TO, TO_DO, 
            TRASH, TV_SHOW, USER_HOME, VEHICLE, VISA, VISA_TYPE, VOTE, WEB_QUERY, WITH, YOUTUBE, 
            CREDIT_CARD_SERVICE, CREDIT_SCORE_PROVIDER, DENTAL_CARE_PROVIDER, HEALTH_INSURANCE_PROVIDER, ISPTELCO, LOCAL_DEPARTMENT_STORE, NON_PRESCRIPTION_DRUGS, OCCUPATIONAL_COMMODITY_SERVICE_PROVIDER, OCCUPATIONAL_NON_COMMODITY_SERVICE_PROVIDER, PHARMACY_STORES, 
            SATELLITE_PROVIDER, SHIPPING_PROVIDER, SPORTS_STORES, TAX_PROVIDER, TECH_PROVIDER_SUPPORT, TRAVEL_BROKER, WASTE_MANAGEMENT_PROVIDER, MOVIE, CALL_LOCAL_PROFESSIONAL_ACTION, CALL_SERVICE_ACTION, 
            CANCEL_SERVICE_ACTION, BUY_GROCERY_ACTION, LOOKUP_WEB_QUERY_ACTION, FLIGHT_STATS_ACTION, PACK_FOR_TRIP_ACTION, FLIGHT_CHECKIN_ACTION, ORIGIN, DESTINATION, BATTERIES_HEARING_AID_BATTERIES_ACTION, BATTERIES_RECHARGEABLE_BATTERIES_ACTION, 
            CAR_INSPECTION_AIR_FILTER_CHANGE_REPLACE_BUY_ACTION, CAR_INSPECTION_BUY_TIRES_ACTION, CAR_INSPECTION_FIND_OIL_CHANGE_ACTION, CAR_INSPECTION_GET_AIR_FOR_TIRES_ACTION, CAR_INSPECTION_GET_OIL_CHANGE_ACTION, CAR_INSPECTION_GET_SMOG_CHECK_ACTION, CAR_INSPECTION_SCHEDULE_OIL_CHANGE_ACTION, CAR_INSPECTION_TIRE_PRESSURE_ACTION, CAR_INSPECTION_TIRE_ROTATION_ACTION, CAR_MAINTENANCE_CAR_WASH_ACTION, 
            CAR_MAINTENANCE_CAR_WAXING_ACTION, CITY_OR_AIRPORT_CODE, DENTIST_FIND_DENTIST_ACTION, DEPARTMENT_STORE, DMV_CALL_DMVACTION, DMV_DRIVING_TEST_ACTION, DMV_GO_TO_DMVACTION, DOCTOR_CALL_ACTION, DOCTOR_FIND_DOCTOR_ACTION, DRIVING_SCHOOL_FIND_SCHOOL_ACTION, 
            FLEX_TIME_DURATION, FLIGHTS_BOOK_FLIGHTS_TO_ACTION, FLOWERS_CALL_FIND_FLORIST_ACTION, FLOWERS_GET_FLOWERS_ACTION, FLOWERS_GET_FLOWERS_BIRTHDAY_ACTION, FLOWERS_GET_FLOWERS_MOM_ACTION, HAIRCUT_GET_HAIRCUT_ACTION, LOCKSMITH_BUY_ACCESSORIES_ACTION, LOCKSMITH_FIND_LOCKSMITH_ACTION, LOCKSMITH_LOCKSMITH_SERVICES_ACTION, 
            MAILING_STUFF_CHANGE_FORWARDING_ADDRESS_FOR_MAIL_ACTION, MAILING_STUFF_CHANGE_MAILING_ADDRESS_ACTION, OCCUPATIONAL_NON_COMMODITY_SERVICE, OCCUPATIONAL_SERVICE_PROVIDER_CHIMNEY_SWEEP_ACTION, OCCUPATIONAL_SERVICE_PROVIDER_CLEANING_ACTION, OCCUPATIONAL_SERVICE_PROVIDER_FIND_COMMODITY_ACTION, OCCUPATIONAL_SERVICE_PROVIDER_FIND_NON_COMMODITY_ACTION, OCCUPATIONAL_SERVICE_PROVIDER_OPTOMETRIST_CANCEL_ACTION, OCCUPATIONAL_SERVICE_PROVIDER_OPTOMETRIST_SCHEDULE_ACTION, OCCUPATIONAL_SERVICE_PROVIDER_PAY_NON_COMMODITY_SERVICE_ACTION, 
            OCCUPATIONAL_SERVICE_PROVIDER_PEDICURE_ACTION, OCCUPATIONAL_SERVICE_PROVIDER_PIANO_TUNER_ACTION, OCCUPATIONAL_SERVICE_PROVIDER_SCHEDULE_COMMODITY_ACTION, OCCUPATIONAL_SERVICE_PROVIDER_SCHEDULE_EXISTING_NON_COMMODITY_SERVICE_PROVIDER_ACTION, OCCUPATIONAL_SERVICE_PROVIDER_SCHEDULE_NON_COMMODITY_ACTION, OCCUPATIONAL_SERVICE_PROVIDER_SCHEDULE_PEDIATRICIAN_ACTION, PHARMACY_GET_NON_PRESCRIPTION_DRUGS_ACTION, PICK_UP_PRESCRIPTION_FROM_STORE_ACTION, REGISTER_CAR_ACTION, RENTALCAR_TAXI_BOOKTAXI_ACTION, 
            RENTALCAR_TAXI_BOOK_RENTAL_CAR_ACTION, RENTALCAR_TAXI_CANCEL_RENTAL_CAR_ACTION, STAMPS_OFFLINEOR_ONLINE_ACTION, STAMPS_ONLINE_ACTION, TRAFFIC_TICKETS_PAY_PARKING_TICKET_ACTION, TRAFFIC_TICKETS_PAY_SPEEDING_TICKET_ACTION, VACCINATIONS_FLU_SHOT_ACTION, VACCINATIONS_FLU_SHOT_AT_PHARMACY_ACTION, VITAMINS_AND_SUPPLEMENTS_SUPPLEMENTS_ACTION, VITAMINS_AND_SUPPLEMENTS_VITAMINS_ACTION, 
            WATCH_MOVIE_ACTION
        });
    }

    private class _cls2
        implements com.google.protobuf.Internal.EnumVerifier
    {

        public final boolean isInRange(int i)
        {
            return AnnotationType.forNumber(i) != null;
        }

        _cls2()
        {
        }
    }

}
