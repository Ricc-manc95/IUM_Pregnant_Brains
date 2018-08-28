// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.personalization.assist.annotate;


public final class GrammarRuleType extends Enum
    implements com.google.protobuf.Internal.EnumLite
{

    private static final GrammarRuleType $VALUES[];
    private static final GrammarRuleType ACTIVATE_BANK_CARD;
    private static final GrammarRuleType ANNIVERSARY;
    private static final GrammarRuleType APPLY_CITIZENSHIP;
    private static final GrammarRuleType APPLY_DRIVERS_LICENSE;
    private static final GrammarRuleType APPLY_GREEN_CARD;
    public static final GrammarRuleType APPLY_LICENSE;
    private static final GrammarRuleType APPLY_LOAN;
    private static final GrammarRuleType APPLY_MARRIAGE_LICENSE;
    public static final GrammarRuleType APPLY_PASSPORT;
    private static final GrammarRuleType APPLY_VISA;
    private static final GrammarRuleType BABYSIT;
    private static final GrammarRuleType BABY_SHOWER;
    private static final GrammarRuleType BIRTHDAY;
    private static final GrammarRuleType BOOK_CAR_RENTAL;
    public static final GrammarRuleType BOOK_FLIGHT;
    public static final GrammarRuleType BOOK_HOTEL;
    private static final GrammarRuleType BOOK_RESTAURANT;
    private static final GrammarRuleType BRING_ITEM;
    private static final GrammarRuleType BUY_GROCERY_ITEM;
    private static final GrammarRuleType BUY_PRODUCT;
    public static final GrammarRuleType BUY_STOCK;
    private static final GrammarRuleType CALENDAR_APPOINTMENT;
    public static final GrammarRuleType CALL_CONTACT;
    private static final GrammarRuleType CALL_LOCAL_ALIAS;
    private static final GrammarRuleType CALL_LOCAL_BUSINESS;
    private static final GrammarRuleType CALL_LOCAL_PROFESSIONAL;
    private static final GrammarRuleType CALL_MEDICAL_PROFESSIONAL;
    private static final GrammarRuleType CALL_ORGANIZATION;
    private static final GrammarRuleType CANCEL_BANK_ACCOUNT;
    private static final GrammarRuleType CANCEL_BANK_CARD;
    private static final GrammarRuleType CANCEL_CAR_RENTAL;
    private static final GrammarRuleType CANCEL_FLIGHT;
    private static final GrammarRuleType CANCEL_HOTEL;
    private static final GrammarRuleType CANCEL_RESTAURANT;
    private static final GrammarRuleType CANCEL_SUBSCRIPTION;
    public static final GrammarRuleType CHECKIN_FLIGHT;
    private static final GrammarRuleType CHECK_BANK;
    private static final GrammarRuleType CLEAN_CAR;
    private static final GrammarRuleType CLEAN_HOUSEHOLD;
    public static final GrammarRuleType COMPLETE_GOVERNMENT_FORM;
    private static final GrammarRuleType CONTACT_STATUS;
    private static final GrammarRuleType COPY_DOCUMENT;
    private static final GrammarRuleType COPY_PASSPORT;
    private static final GrammarRuleType COPY_VISA;
    private static final GrammarRuleType DATETIME_GRAMMAR;
    private static final GrammarRuleType DEPOSIT_CHECK;
    private static final GrammarRuleType DINNER;
    private static final GrammarRuleType DROP_OFF_CASH;
    private static final GrammarRuleType DROP_OFF_CONTACT;
    private static final GrammarRuleType DROP_OFF_DRY_CLEANING;
    private static final GrammarRuleType DROP_OFF_PASSPORT;
    private static final GrammarRuleType DROP_OFF_PRESCRIPTION;
    private static final GrammarRuleType DROP_OFF_VISA;
    private static final GrammarRuleType EAT_FOOD;
    private static final GrammarRuleType EDIT_DOCUMENT;
    public static final GrammarRuleType EMAIL_CONTACT;
    private static final GrammarRuleType EVENT_CORPUS;
    private static final GrammarRuleType EXERCISE;
    private static final GrammarRuleType FATHERS_DAY;
    private static final GrammarRuleType FIND_BANK_CARD;
    private static final GrammarRuleType FIND_JOB;
    private static final GrammarRuleType FIND_LOCAL_PROFESSIONAL;
    private static final GrammarRuleType FIND_SUBSCRIPTION;
    private static final GrammarRuleType GIFT;
    private static final GrammarRuleType HOLD_MAIL;
    private static final GrammarRuleType HOLIDAY_DEADLINE;
    private static final GrammarRuleType HOLIDAY_MAIL_DEADLINE;
    private static final GrammarRuleType LISTEN_ALBUM;
    private static final GrammarRuleType LISTEN_SONG;
    private static final GrammarRuleType LUNCH;
    private static final GrammarRuleType MEETING;
    public static final GrammarRuleType MEET_CONTACT;
    private static final GrammarRuleType NOTARIZE_DOCUMENT;
    private static final GrammarRuleType OPEN_BANK_ACCOUNT;
    public static final GrammarRuleType PACK_FOR_PLACE;
    private static final GrammarRuleType PARTY;
    public static final GrammarRuleType PAY_BILL;
    public static final GrammarRuleType PAY_CONTACT;
    private static final GrammarRuleType PAY_LOAN;
    private static final GrammarRuleType PAY_MOVING_VIOLATION;
    private static final GrammarRuleType PAY_PARKING_TICKET;
    private static final GrammarRuleType PAY_RENT;
    public static final GrammarRuleType PAY_TAX;
    private static final GrammarRuleType PICK_UP_CASH;
    private static final GrammarRuleType PICK_UP_CONTACT;
    private static final GrammarRuleType PICK_UP_DRY_CLEANING;
    private static final GrammarRuleType PICK_UP_PASSPORT;
    private static final GrammarRuleType PICK_UP_PRESCRIPTION;
    private static final GrammarRuleType PICK_UP_VISA;
    private static final GrammarRuleType PREPARE_FOOD;
    private static final GrammarRuleType READ_BOOK;
    private static final GrammarRuleType REGISTER_CAR;
    private static final GrammarRuleType REGISTER_EXAM;
    private static final GrammarRuleType REGISTER_MOTORCYCLE;
    private static final GrammarRuleType REGISTER_VEHICLE;
    private static final GrammarRuleType REGISTER_VOTE;
    private static final GrammarRuleType REMIND_CONTACT;
    private static final GrammarRuleType RENEW_CAR_REGISTRATION;
    private static final GrammarRuleType RENEW_DRIVERS_LICENSE;
    public static final GrammarRuleType RENEW_GREEN_CARD;
    public static final GrammarRuleType RENEW_LICENSE;
    private static final GrammarRuleType RENEW_MOTORCYCLE_REGISTRATION;
    public static final GrammarRuleType RENEW_PASSPORT;
    private static final GrammarRuleType RENEW_VEHICLE_REGISTRATION;
    private static final GrammarRuleType RENEW_VISA;
    private static final GrammarRuleType REPAIR_CAR;
    private static final GrammarRuleType REPAIR_PRODUCT;
    public static final GrammarRuleType RETURN_PRODUCT;
    private static final GrammarRuleType ROUTINE_OCCURRENCE;
    public static final GrammarRuleType SELL_STOCK;
    private static final GrammarRuleType SEND_LETTER;
    private static final GrammarRuleType SEND_PACKAGE;
    private static final GrammarRuleType SHOW;
    private static final GrammarRuleType SMS_CONTACT;
    private static final GrammarRuleType STUDY_SUBJECT;
    private static final GrammarRuleType SUFFIX_CONTACT;
    private static final GrammarRuleType SUFFIX_EMAIL;
    private static final GrammarRuleType TAKE_MEDICINE;
    private static final GrammarRuleType TAKE_OUT_TRASH;
    private static final GrammarRuleType TRACK_FLIGHT;
    private static final GrammarRuleType TRACK_PACKAGE;
    private static final GrammarRuleType TRACK_SANTA;
    private static final GrammarRuleType TRAVEL;
    public static final GrammarRuleType UNKNOWN_RULE;
    private static final GrammarRuleType VISIT_CONTACT;
    public static final GrammarRuleType WATCH_FILM;
    private static final GrammarRuleType WATCH_TV_SHOW;
    public static final GrammarRuleType WATCH_VIDEO;
    private static final GrammarRuleType WEDDING;
    private static final GrammarRuleType WORK_FOR_CONTACT;
    public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
    public final int value;

    private GrammarRuleType(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }

    public static GrammarRuleType forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 85: // 'U'
            return UNKNOWN_RULE;

        case 24: // '\030'
            return ACTIVATE_BANK_CARD;

        case 101: // 'e'
            return ANNIVERSARY;

        case 47: // '/'
            return APPLY_CITIZENSHIP;

        case 73: // 'I'
            return APPLY_GREEN_CARD;

        case 95: // '_'
            return APPLY_LICENSE;

        case 39: // '\''
            return APPLY_LOAN;

        case 48: // '0'
            return APPLY_PASSPORT;

        case 55: // '7'
            return APPLY_VISA;

        case 105: // 'i'
            return BABY_SHOWER;

        case 104: // 'h'
            return BABYSIT;

        case 100: // 'd'
            return BIRTHDAY;

        case 74: // 'J'
            return BOOK_CAR_RENTAL;

        case 28: // '\034'
            return BOOK_FLIGHT;

        case 29: // '\035'
            return BOOK_HOTEL;

        case 67: // 'C'
            return BOOK_RESTAURANT;

        case 130: 
            return BRING_ITEM;

        case 16: // '\020'
            return BUY_GROCERY_ITEM;

        case 17: // '\021'
            return BUY_PRODUCT;

        case 53: // '5'
            return BUY_STOCK;

        case 120: // 'x'
            return CALENDAR_APPOINTMENT;

        case 1: // '\001'
            return CALL_CONTACT;

        case 96: // '`'
            return CALL_LOCAL_ALIAS;

        case 8: // '\b'
            return CALL_LOCAL_BUSINESS;

        case 7: // '\007'
            return CALL_ORGANIZATION;

        case 89: // 'Y'
            return CANCEL_BANK_ACCOUNT;

        case 26: // '\032'
            return CANCEL_BANK_CARD;

        case 75: // 'K'
            return CANCEL_CAR_RENTAL;

        case 32: // ' '
            return CANCEL_FLIGHT;

        case 30: // '\036'
            return CANCEL_HOTEL;

        case 68: // 'D'
            return CANCEL_RESTAURANT;

        case 36: // '$'
            return CANCEL_SUBSCRIPTION;

        case 90: // 'Z'
            return CHECK_BANK;

        case 31: // '\037'
            return CHECKIN_FLIGHT;

        case 66: // 'B'
            return CLEAN_CAR;

        case 41: // ')'
            return CLEAN_HOUSEHOLD;

        case 88: // 'X'
            return COMPLETE_GOVERNMENT_FORM;

        case 114: // 'r'
            return CONTACT_STATUS;

        case 21: // '\025'
            return COPY_DOCUMENT;

        case 50: // '2'
            return COPY_PASSPORT;

        case 57: // '9'
            return COPY_VISA;

        case 121: // 'y'
            return DATETIME_GRAMMAR;

        case 76: // 'L'
            return DEPOSIT_CHECK;

        case 109: // 'm'
            return DINNER;

        case 77: // 'M'
            return DROP_OFF_CASH;

        case 6: // '\006'
            return DROP_OFF_CONTACT;

        case 14: // '\016'
            return DROP_OFF_DRY_CLEANING;

        case 51: // '3'
            return DROP_OFF_PASSPORT;

        case 12: // '\f'
            return DROP_OFF_PRESCRIPTION;

        case 58: // ':'
            return DROP_OFF_VISA;

        case 129: 
            return EAT_FOOD;

        case 20: // '\024'
            return EDIT_DOCUMENT;

        case 80: // 'P'
            return EVENT_CORPUS;

        case 2: // '\002'
            return EMAIL_CONTACT;

        case 118: // 'v'
            return EXERCISE;

        case 78: // 'N'
            return FATHERS_DAY;

        case 25: // '\031'
            return FIND_BANK_CARD;

        case 103: // 'g'
            return FIND_JOB;

        case 71: // 'G'
            return FIND_LOCAL_PROFESSIONAL;

        case 37: // '%'
            return FIND_SUBSCRIPTION;

        case 116: // 't'
            return GIFT;

        case 102: // 'f'
            return HOLD_MAIL;

        case 72: // 'H'
            return HOLIDAY_DEADLINE;

        case 98: // 'b'
            return HOLIDAY_MAIL_DEADLINE;

        case 43: // '+'
            return LISTEN_ALBUM;

        case 42: // '*'
            return LISTEN_SONG;

        case 106: // 'j'
            return LUNCH;

        case 3: // '\003'
            return MEET_CONTACT;

        case 113: // 'q'
            return MEETING;

        case 22: // '\026'
            return NOTARIZE_DOCUMENT;

        case 91: // '['
            return OPEN_BANK_ACCOUNT;

        case 27: // '\033'
            return PACK_FOR_PLACE;

        case 110: // 'n'
            return PARTY;

        case 23: // '\027'
            return PAY_BILL;

        case 4: // '\004'
            return PAY_CONTACT;

        case 40: // '('
            return PAY_LOAN;

        case 46: // '.'
            return PAY_MOVING_VIOLATION;

        case 45: // '-'
            return PAY_PARKING_TICKET;

        case 97: // 'a'
            return PAY_RENT;

        case 38: // '&'
            return PAY_TAX;

        case 79: // 'O'
            return PICK_UP_CASH;

        case 5: // '\005'
            return PICK_UP_CONTACT;

        case 15: // '\017'
            return PICK_UP_DRY_CLEANING;

        case 52: // '4'
            return PICK_UP_PASSPORT;

        case 13: // '\r'
            return PICK_UP_PRESCRIPTION;

        case 59: // ';'
            return PICK_UP_VISA;

        case 126: // '~'
            return PREPARE_FOOD;

        case 44: // ','
            return READ_BOOK;

        case 87: // 'W'
            return REGISTER_EXAM;

        case 93: // ']'
            return REGISTER_VEHICLE;

        case 86: // 'V'
            return REGISTER_VOTE;

        case 112: // 'p'
            return REMIND_CONTACT;

        case 82: // 'R'
            return RENEW_GREEN_CARD;

        case 92: // '\\'
            return RENEW_LICENSE;

        case 49: // '1'
            return RENEW_PASSPORT;

        case 94: // '^'
            return RENEW_VEHICLE_REGISTRATION;

        case 56: // '8'
            return RENEW_VISA;

        case 63: // '?'
            return REPAIR_CAR;

        case 19: // '\023'
            return REPAIR_PRODUCT;

        case 18: // '\022'
            return RETURN_PRODUCT;

        case 117: // 'u'
            return ROUTINE_OCCURRENCE;

        case 54: // '6'
            return SELL_STOCK;

        case 60: // '<'
            return SEND_LETTER;

        case 61: // '='
            return SEND_PACKAGE;

        case 119: // 'w'
            return SHOW;

        case 123: // '{'
            return SMS_CONTACT;

        case 122: // 'z'
            return STUDY_SUBJECT;

        case 127: // '\177'
            return SUFFIX_CONTACT;

        case 128: 
            return SUFFIX_EMAIL;

        case 124: // '|'
            return TAKE_MEDICINE;

        case 125: // '}'
            return TAKE_OUT_TRASH;

        case 33: // '!'
            return TRACK_FLIGHT;

        case 62: // '>'
            return TRACK_PACKAGE;

        case 99: // 'c'
            return TRACK_SANTA;

        case 115: // 's'
            return TRAVEL;

        case 108: // 'l'
            return VISIT_CONTACT;

        case 34: // '"'
            return WATCH_FILM;

        case 84: // 'T'
            return WATCH_TV_SHOW;

        case 35: // '#'
            return WATCH_VIDEO;

        case 107: // 'k'
            return WEDDING;

        case 111: // 'o'
            return WORK_FOR_CONTACT;

        case 9: // '\t'
            return APPLY_DRIVERS_LICENSE;

        case 11: // '\013'
            return APPLY_MARRIAGE_LICENSE;

        case 69: // 'E'
            return CALL_MEDICAL_PROFESSIONAL;

        case 70: // 'F'
            return CALL_LOCAL_PROFESSIONAL;

        case 64: // '@'
            return REGISTER_CAR;

        case 65: // 'A'
            return REGISTER_MOTORCYCLE;

        case 81: // 'Q'
            return RENEW_CAR_REGISTRATION;

        case 10: // '\n'
            return RENEW_DRIVERS_LICENSE;

        case 83: // 'S'
            return RENEW_MOTORCYCLE_REGISTRATION;
        }
    }

    public static GrammarRuleType[] values()
    {
        return (GrammarRuleType[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNKNOWN_RULE = new GrammarRuleType("UNKNOWN_RULE", 0, 85);
        ACTIVATE_BANK_CARD = new GrammarRuleType("ACTIVATE_BANK_CARD", 1, 24);
        ANNIVERSARY = new GrammarRuleType("ANNIVERSARY", 2, 101);
        APPLY_CITIZENSHIP = new GrammarRuleType("APPLY_CITIZENSHIP", 3, 47);
        APPLY_GREEN_CARD = new GrammarRuleType("APPLY_GREEN_CARD", 4, 73);
        APPLY_LICENSE = new GrammarRuleType("APPLY_LICENSE", 5, 95);
        APPLY_LOAN = new GrammarRuleType("APPLY_LOAN", 6, 39);
        APPLY_PASSPORT = new GrammarRuleType("APPLY_PASSPORT", 7, 48);
        APPLY_VISA = new GrammarRuleType("APPLY_VISA", 8, 55);
        BABY_SHOWER = new GrammarRuleType("BABY_SHOWER", 9, 105);
        BABYSIT = new GrammarRuleType("BABYSIT", 10, 104);
        BIRTHDAY = new GrammarRuleType("BIRTHDAY", 11, 100);
        BOOK_CAR_RENTAL = new GrammarRuleType("BOOK_CAR_RENTAL", 12, 74);
        BOOK_FLIGHT = new GrammarRuleType("BOOK_FLIGHT", 13, 28);
        BOOK_HOTEL = new GrammarRuleType("BOOK_HOTEL", 14, 29);
        BOOK_RESTAURANT = new GrammarRuleType("BOOK_RESTAURANT", 15, 67);
        BRING_ITEM = new GrammarRuleType("BRING_ITEM", 16, 130);
        BUY_GROCERY_ITEM = new GrammarRuleType("BUY_GROCERY_ITEM", 17, 16);
        BUY_PRODUCT = new GrammarRuleType("BUY_PRODUCT", 18, 17);
        BUY_STOCK = new GrammarRuleType("BUY_STOCK", 19, 53);
        CALENDAR_APPOINTMENT = new GrammarRuleType("CALENDAR_APPOINTMENT", 20, 120);
        CALL_CONTACT = new GrammarRuleType("CALL_CONTACT", 21, 1);
        CALL_LOCAL_ALIAS = new GrammarRuleType("CALL_LOCAL_ALIAS", 22, 96);
        CALL_LOCAL_BUSINESS = new GrammarRuleType("CALL_LOCAL_BUSINESS", 23, 8);
        CALL_ORGANIZATION = new GrammarRuleType("CALL_ORGANIZATION", 24, 7);
        CANCEL_BANK_ACCOUNT = new GrammarRuleType("CANCEL_BANK_ACCOUNT", 25, 89);
        CANCEL_BANK_CARD = new GrammarRuleType("CANCEL_BANK_CARD", 26, 26);
        CANCEL_CAR_RENTAL = new GrammarRuleType("CANCEL_CAR_RENTAL", 27, 75);
        CANCEL_FLIGHT = new GrammarRuleType("CANCEL_FLIGHT", 28, 32);
        CANCEL_HOTEL = new GrammarRuleType("CANCEL_HOTEL", 29, 30);
        CANCEL_RESTAURANT = new GrammarRuleType("CANCEL_RESTAURANT", 30, 68);
        CANCEL_SUBSCRIPTION = new GrammarRuleType("CANCEL_SUBSCRIPTION", 31, 36);
        CHECK_BANK = new GrammarRuleType("CHECK_BANK", 32, 90);
        CHECKIN_FLIGHT = new GrammarRuleType("CHECKIN_FLIGHT", 33, 31);
        CLEAN_CAR = new GrammarRuleType("CLEAN_CAR", 34, 66);
        CLEAN_HOUSEHOLD = new GrammarRuleType("CLEAN_HOUSEHOLD", 35, 41);
        COMPLETE_GOVERNMENT_FORM = new GrammarRuleType("COMPLETE_GOVERNMENT_FORM", 36, 88);
        CONTACT_STATUS = new GrammarRuleType("CONTACT_STATUS", 37, 114);
        COPY_DOCUMENT = new GrammarRuleType("COPY_DOCUMENT", 38, 21);
        COPY_PASSPORT = new GrammarRuleType("COPY_PASSPORT", 39, 50);
        COPY_VISA = new GrammarRuleType("COPY_VISA", 40, 57);
        DATETIME_GRAMMAR = new GrammarRuleType("DATETIME_GRAMMAR", 41, 121);
        DEPOSIT_CHECK = new GrammarRuleType("DEPOSIT_CHECK", 42, 76);
        DINNER = new GrammarRuleType("DINNER", 43, 109);
        DROP_OFF_CASH = new GrammarRuleType("DROP_OFF_CASH", 44, 77);
        DROP_OFF_CONTACT = new GrammarRuleType("DROP_OFF_CONTACT", 45, 6);
        DROP_OFF_DRY_CLEANING = new GrammarRuleType("DROP_OFF_DRY_CLEANING", 46, 14);
        DROP_OFF_PASSPORT = new GrammarRuleType("DROP_OFF_PASSPORT", 47, 51);
        DROP_OFF_PRESCRIPTION = new GrammarRuleType("DROP_OFF_PRESCRIPTION", 48, 12);
        DROP_OFF_VISA = new GrammarRuleType("DROP_OFF_VISA", 49, 58);
        EAT_FOOD = new GrammarRuleType("EAT_FOOD", 50, 129);
        EDIT_DOCUMENT = new GrammarRuleType("EDIT_DOCUMENT", 51, 20);
        EVENT_CORPUS = new GrammarRuleType("EVENT_CORPUS", 52, 80);
        EMAIL_CONTACT = new GrammarRuleType("EMAIL_CONTACT", 53, 2);
        EXERCISE = new GrammarRuleType("EXERCISE", 54, 118);
        FATHERS_DAY = new GrammarRuleType("FATHERS_DAY", 55, 78);
        FIND_BANK_CARD = new GrammarRuleType("FIND_BANK_CARD", 56, 25);
        FIND_JOB = new GrammarRuleType("FIND_JOB", 57, 103);
        FIND_LOCAL_PROFESSIONAL = new GrammarRuleType("FIND_LOCAL_PROFESSIONAL", 58, 71);
        FIND_SUBSCRIPTION = new GrammarRuleType("FIND_SUBSCRIPTION", 59, 37);
        GIFT = new GrammarRuleType("GIFT", 60, 116);
        HOLD_MAIL = new GrammarRuleType("HOLD_MAIL", 61, 102);
        HOLIDAY_DEADLINE = new GrammarRuleType("HOLIDAY_DEADLINE", 62, 72);
        HOLIDAY_MAIL_DEADLINE = new GrammarRuleType("HOLIDAY_MAIL_DEADLINE", 63, 98);
        LISTEN_ALBUM = new GrammarRuleType("LISTEN_ALBUM", 64, 43);
        LISTEN_SONG = new GrammarRuleType("LISTEN_SONG", 65, 42);
        LUNCH = new GrammarRuleType("LUNCH", 66, 106);
        MEET_CONTACT = new GrammarRuleType("MEET_CONTACT", 67, 3);
        MEETING = new GrammarRuleType("MEETING", 68, 113);
        NOTARIZE_DOCUMENT = new GrammarRuleType("NOTARIZE_DOCUMENT", 69, 22);
        OPEN_BANK_ACCOUNT = new GrammarRuleType("OPEN_BANK_ACCOUNT", 70, 91);
        PACK_FOR_PLACE = new GrammarRuleType("PACK_FOR_PLACE", 71, 27);
        PARTY = new GrammarRuleType("PARTY", 72, 110);
        PAY_BILL = new GrammarRuleType("PAY_BILL", 73, 23);
        PAY_CONTACT = new GrammarRuleType("PAY_CONTACT", 74, 4);
        PAY_LOAN = new GrammarRuleType("PAY_LOAN", 75, 40);
        PAY_MOVING_VIOLATION = new GrammarRuleType("PAY_MOVING_VIOLATION", 76, 46);
        PAY_PARKING_TICKET = new GrammarRuleType("PAY_PARKING_TICKET", 77, 45);
        PAY_RENT = new GrammarRuleType("PAY_RENT", 78, 97);
        PAY_TAX = new GrammarRuleType("PAY_TAX", 79, 38);
        PICK_UP_CASH = new GrammarRuleType("PICK_UP_CASH", 80, 79);
        PICK_UP_CONTACT = new GrammarRuleType("PICK_UP_CONTACT", 81, 5);
        PICK_UP_DRY_CLEANING = new GrammarRuleType("PICK_UP_DRY_CLEANING", 82, 15);
        PICK_UP_PASSPORT = new GrammarRuleType("PICK_UP_PASSPORT", 83, 52);
        PICK_UP_PRESCRIPTION = new GrammarRuleType("PICK_UP_PRESCRIPTION", 84, 13);
        PICK_UP_VISA = new GrammarRuleType("PICK_UP_VISA", 85, 59);
        PREPARE_FOOD = new GrammarRuleType("PREPARE_FOOD", 86, 126);
        READ_BOOK = new GrammarRuleType("READ_BOOK", 87, 44);
        REGISTER_EXAM = new GrammarRuleType("REGISTER_EXAM", 88, 87);
        REGISTER_VEHICLE = new GrammarRuleType("REGISTER_VEHICLE", 89, 93);
        REGISTER_VOTE = new GrammarRuleType("REGISTER_VOTE", 90, 86);
        REMIND_CONTACT = new GrammarRuleType("REMIND_CONTACT", 91, 112);
        RENEW_GREEN_CARD = new GrammarRuleType("RENEW_GREEN_CARD", 92, 82);
        RENEW_LICENSE = new GrammarRuleType("RENEW_LICENSE", 93, 92);
        RENEW_PASSPORT = new GrammarRuleType("RENEW_PASSPORT", 94, 49);
        RENEW_VEHICLE_REGISTRATION = new GrammarRuleType("RENEW_VEHICLE_REGISTRATION", 95, 94);
        RENEW_VISA = new GrammarRuleType("RENEW_VISA", 96, 56);
        REPAIR_CAR = new GrammarRuleType("REPAIR_CAR", 97, 63);
        REPAIR_PRODUCT = new GrammarRuleType("REPAIR_PRODUCT", 98, 19);
        RETURN_PRODUCT = new GrammarRuleType("RETURN_PRODUCT", 99, 18);
        ROUTINE_OCCURRENCE = new GrammarRuleType("ROUTINE_OCCURRENCE", 100, 117);
        SELL_STOCK = new GrammarRuleType("SELL_STOCK", 101, 54);
        SEND_LETTER = new GrammarRuleType("SEND_LETTER", 102, 60);
        SEND_PACKAGE = new GrammarRuleType("SEND_PACKAGE", 103, 61);
        SHOW = new GrammarRuleType("SHOW", 104, 119);
        SMS_CONTACT = new GrammarRuleType("SMS_CONTACT", 105, 123);
        STUDY_SUBJECT = new GrammarRuleType("STUDY_SUBJECT", 106, 122);
        SUFFIX_CONTACT = new GrammarRuleType("SUFFIX_CONTACT", 107, 127);
        SUFFIX_EMAIL = new GrammarRuleType("SUFFIX_EMAIL", 108, 128);
        TAKE_MEDICINE = new GrammarRuleType("TAKE_MEDICINE", 109, 124);
        TAKE_OUT_TRASH = new GrammarRuleType("TAKE_OUT_TRASH", 110, 125);
        TRACK_FLIGHT = new GrammarRuleType("TRACK_FLIGHT", 111, 33);
        TRACK_PACKAGE = new GrammarRuleType("TRACK_PACKAGE", 112, 62);
        TRACK_SANTA = new GrammarRuleType("TRACK_SANTA", 113, 99);
        TRAVEL = new GrammarRuleType("TRAVEL", 114, 115);
        VISIT_CONTACT = new GrammarRuleType("VISIT_CONTACT", 115, 108);
        WATCH_FILM = new GrammarRuleType("WATCH_FILM", 116, 34);
        WATCH_TV_SHOW = new GrammarRuleType("WATCH_TV_SHOW", 117, 84);
        WATCH_VIDEO = new GrammarRuleType("WATCH_VIDEO", 118, 35);
        WEDDING = new GrammarRuleType("WEDDING", 119, 107);
        WORK_FOR_CONTACT = new GrammarRuleType("WORK_FOR_CONTACT", 120, 111);
        APPLY_DRIVERS_LICENSE = new GrammarRuleType("APPLY_DRIVERS_LICENSE", 121, 9);
        APPLY_MARRIAGE_LICENSE = new GrammarRuleType("APPLY_MARRIAGE_LICENSE", 122, 11);
        CALL_MEDICAL_PROFESSIONAL = new GrammarRuleType("CALL_MEDICAL_PROFESSIONAL", 123, 69);
        CALL_LOCAL_PROFESSIONAL = new GrammarRuleType("CALL_LOCAL_PROFESSIONAL", 124, 70);
        REGISTER_CAR = new GrammarRuleType("REGISTER_CAR", 125, 64);
        REGISTER_MOTORCYCLE = new GrammarRuleType("REGISTER_MOTORCYCLE", 126, 65);
        RENEW_CAR_REGISTRATION = new GrammarRuleType("RENEW_CAR_REGISTRATION", 127, 81);
        RENEW_DRIVERS_LICENSE = new GrammarRuleType("RENEW_DRIVERS_LICENSE", 128, 10);
        RENEW_MOTORCYCLE_REGISTRATION = new GrammarRuleType("RENEW_MOTORCYCLE_REGISTRATION", 129, 83);
        $VALUES = (new GrammarRuleType[] {
            UNKNOWN_RULE, ACTIVATE_BANK_CARD, ANNIVERSARY, APPLY_CITIZENSHIP, APPLY_GREEN_CARD, APPLY_LICENSE, APPLY_LOAN, APPLY_PASSPORT, APPLY_VISA, BABY_SHOWER, 
            BABYSIT, BIRTHDAY, BOOK_CAR_RENTAL, BOOK_FLIGHT, BOOK_HOTEL, BOOK_RESTAURANT, BRING_ITEM, BUY_GROCERY_ITEM, BUY_PRODUCT, BUY_STOCK, 
            CALENDAR_APPOINTMENT, CALL_CONTACT, CALL_LOCAL_ALIAS, CALL_LOCAL_BUSINESS, CALL_ORGANIZATION, CANCEL_BANK_ACCOUNT, CANCEL_BANK_CARD, CANCEL_CAR_RENTAL, CANCEL_FLIGHT, CANCEL_HOTEL, 
            CANCEL_RESTAURANT, CANCEL_SUBSCRIPTION, CHECK_BANK, CHECKIN_FLIGHT, CLEAN_CAR, CLEAN_HOUSEHOLD, COMPLETE_GOVERNMENT_FORM, CONTACT_STATUS, COPY_DOCUMENT, COPY_PASSPORT, 
            COPY_VISA, DATETIME_GRAMMAR, DEPOSIT_CHECK, DINNER, DROP_OFF_CASH, DROP_OFF_CONTACT, DROP_OFF_DRY_CLEANING, DROP_OFF_PASSPORT, DROP_OFF_PRESCRIPTION, DROP_OFF_VISA, 
            EAT_FOOD, EDIT_DOCUMENT, EVENT_CORPUS, EMAIL_CONTACT, EXERCISE, FATHERS_DAY, FIND_BANK_CARD, FIND_JOB, FIND_LOCAL_PROFESSIONAL, FIND_SUBSCRIPTION, 
            GIFT, HOLD_MAIL, HOLIDAY_DEADLINE, HOLIDAY_MAIL_DEADLINE, LISTEN_ALBUM, LISTEN_SONG, LUNCH, MEET_CONTACT, MEETING, NOTARIZE_DOCUMENT, 
            OPEN_BANK_ACCOUNT, PACK_FOR_PLACE, PARTY, PAY_BILL, PAY_CONTACT, PAY_LOAN, PAY_MOVING_VIOLATION, PAY_PARKING_TICKET, PAY_RENT, PAY_TAX, 
            PICK_UP_CASH, PICK_UP_CONTACT, PICK_UP_DRY_CLEANING, PICK_UP_PASSPORT, PICK_UP_PRESCRIPTION, PICK_UP_VISA, PREPARE_FOOD, READ_BOOK, REGISTER_EXAM, REGISTER_VEHICLE, 
            REGISTER_VOTE, REMIND_CONTACT, RENEW_GREEN_CARD, RENEW_LICENSE, RENEW_PASSPORT, RENEW_VEHICLE_REGISTRATION, RENEW_VISA, REPAIR_CAR, REPAIR_PRODUCT, RETURN_PRODUCT, 
            ROUTINE_OCCURRENCE, SELL_STOCK, SEND_LETTER, SEND_PACKAGE, SHOW, SMS_CONTACT, STUDY_SUBJECT, SUFFIX_CONTACT, SUFFIX_EMAIL, TAKE_MEDICINE, 
            TAKE_OUT_TRASH, TRACK_FLIGHT, TRACK_PACKAGE, TRACK_SANTA, TRAVEL, VISIT_CONTACT, WATCH_FILM, WATCH_TV_SHOW, WATCH_VIDEO, WEDDING, 
            WORK_FOR_CONTACT, APPLY_DRIVERS_LICENSE, APPLY_MARRIAGE_LICENSE, CALL_MEDICAL_PROFESSIONAL, CALL_LOCAL_PROFESSIONAL, REGISTER_CAR, REGISTER_MOTORCYCLE, RENEW_CAR_REGISTRATION, RENEW_DRIVERS_LICENSE, RENEW_MOTORCYCLE_REGISTRATION
        });
    }

    private class _cls2
        implements com.google.protobuf.Internal.EnumVerifier
    {

        public final boolean isInRange(int i)
        {
            return GrammarRuleType.forNumber(i) != null;
        }

        _cls2()
        {
        }
    }

}
