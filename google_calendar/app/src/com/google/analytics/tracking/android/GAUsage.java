// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.analytics.tracking.android;

import java.util.SortedSet;
import java.util.TreeSet;

public final class GAUsage
{

    public static final GAUsage INSTANCE = new GAUsage();
    private boolean mDisableUsage;
    private StringBuilder mSequence;
    private SortedSet mUsedFields;

    private GAUsage()
    {
        mUsedFields = new TreeSet();
        mSequence = new StringBuilder();
        mDisableUsage = false;
    }

    public final String getAndClearSequence()
    {
        this;
        JVM INSTR monitorenter ;
        String s;
        if (mSequence.length() > 0)
        {
            mSequence.insert(0, ".");
        }
        s = mSequence.toString();
        mSequence = new StringBuilder();
        this;
        JVM INSTR monitorexit ;
        return s;
        Exception exception;
        exception;
        throw exception;
    }

    public final String getAndClearUsage()
    {
        this;
        JVM INSTR monitorenter ;
        Object obj = new StringBuilder();
        int i;
        int j;
        j = 6;
        i = 0;
_L4:
        Field field;
        int k;
        if (mUsedFields.size() <= 0)
        {
            break; /* Loop/switch isn't completed */
        }
        field = (Field)mUsedFields.first();
        mUsedFields.remove(field);
        k = field.ordinal();
_L2:
        if (k < j)
        {
            break; /* Loop/switch isn't completed */
        }
        ((StringBuilder) (obj)).append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(i));
        j += 6;
        i = 0;
        if (true) goto _L2; else goto _L1
_L1:
        i += 1 << field.ordinal() % 6;
        if (true) goto _L4; else goto _L3
_L3:
        if (i > 0)
        {
            break MISSING_BLOCK_LABEL_114;
        }
        if (((StringBuilder) (obj)).length() != 0)
        {
            break MISSING_BLOCK_LABEL_125;
        }
        ((StringBuilder) (obj)).append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(i));
        mUsedFields.clear();
        obj = ((StringBuilder) (obj)).toString();
        this;
        JVM INSTR monitorexit ;
        return ((String) (obj));
        Exception exception;
        exception;
        throw exception;
    }

    public final void setDisableUsage(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        mDisableUsage = flag;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void setUsage(Field field)
    {
        this;
        JVM INSTR monitorenter ;
        if (!mDisableUsage)
        {
            mUsedFields.add(field);
            mSequence.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(field.ordinal()));
        }
        this;
        JVM INSTR monitorexit ;
        return;
        field;
        throw field;
    }


    private class Field extends Enum
    {

        private static final Field $VALUES[];
        private static final Field BLANK_06;
        private static final Field BLANK_13;
        private static final Field BLANK_15;
        private static final Field BLANK_17;
        private static final Field BLANK_48;
        public static final Field CONSTRUCT_EVENT;
        private static final Field CONSTRUCT_EXCEPTION;
        private static final Field CONSTRUCT_ITEM;
        private static final Field CONSTRUCT_RAW_EXCEPTION;
        private static final Field CONSTRUCT_SOCIAL;
        public static final Field CONSTRUCT_TIMING;
        private static final Field CONSTRUCT_TRANSACTION;
        public static final Field DISPATCH;
        private static final Field GET;
        private static final Field GET_ANONYMIZE_IP;
        private static final Field GET_APP_ID;
        private static final Field GET_APP_INSTALLER_ID;
        private static final Field GET_DEBUG;
        private static final Field GET_DEFAULT_TRACKER;
        private static final Field GET_EXCEPTION_PARSER;
        private static final Field GET_SAMPLE_RATE;
        public static final Field GET_TRACKER;
        private static final Field GET_TRACKING_ID;
        private static final Field GET_USE_SECURE;
        private static final Field REPORT_UNCAUGHT_EXCEPTIONS;
        private static final Field REQUEST_APP_OPT_OUT;
        private static final Field SEND;
        private static final Field SET;
        private static final Field SET_ANONYMIZE_IP;
        private static final Field SET_APP_ID;
        private static final Field SET_APP_INSTALLER_ID;
        private static final Field SET_APP_NAME;
        private static final Field SET_APP_OPT_OUT;
        private static final Field SET_APP_SCREEN;
        private static final Field SET_APP_VERSION;
        private static final Field SET_AUTO_ACTIVITY_TRACKING;
        private static final Field SET_CAMPAIGN;
        private static final Field SET_DEBUG;
        private static final Field SET_DEFAULT_TRACKER;
        private static final Field SET_DISPATCH_PERIOD;
        private static final Field SET_EXCEPTION_PARSER;
        private static final Field SET_REFERRER;
        public static final Field SET_SAMPLE_RATE;
        private static final Field SET_SESSION_TIMEOUT;
        private static final Field SET_START_SESSION;
        private static final Field SET_USE_SECURE;
        public static final Field TRACK_EVENT;
        private static final Field TRACK_EXCEPTION_WITH_DESCRIPTION;
        private static final Field TRACK_EXCEPTION_WITH_THROWABLE;
        private static final Field TRACK_SOCIAL;
        public static final Field TRACK_TIMING;
        private static final Field TRACK_TRANSACTION;
        private static final Field TRACK_VIEW;
        public static final Field TRACK_VIEW_WITH_APPSCREEN;

        public static Field[] values()
        {
            return (Field[])$VALUES.clone();
        }

        static 
        {
            TRACK_VIEW = new Field("TRACK_VIEW", 0);
            TRACK_VIEW_WITH_APPSCREEN = new Field("TRACK_VIEW_WITH_APPSCREEN", 1);
            TRACK_EVENT = new Field("TRACK_EVENT", 2);
            TRACK_TRANSACTION = new Field("TRACK_TRANSACTION", 3);
            TRACK_EXCEPTION_WITH_DESCRIPTION = new Field("TRACK_EXCEPTION_WITH_DESCRIPTION", 4);
            TRACK_EXCEPTION_WITH_THROWABLE = new Field("TRACK_EXCEPTION_WITH_THROWABLE", 5);
            BLANK_06 = new Field("BLANK_06", 6);
            TRACK_TIMING = new Field("TRACK_TIMING", 7);
            TRACK_SOCIAL = new Field("TRACK_SOCIAL", 8);
            GET = new Field("GET", 9);
            SET = new Field("SET", 10);
            SEND = new Field("SEND", 11);
            SET_START_SESSION = new Field("SET_START_SESSION", 12);
            BLANK_13 = new Field("BLANK_13", 13);
            SET_APP_NAME = new Field("SET_APP_NAME", 14);
            BLANK_15 = new Field("BLANK_15", 15);
            SET_APP_VERSION = new Field("SET_APP_VERSION", 16);
            BLANK_17 = new Field("BLANK_17", 17);
            SET_APP_SCREEN = new Field("SET_APP_SCREEN", 18);
            GET_TRACKING_ID = new Field("GET_TRACKING_ID", 19);
            SET_ANONYMIZE_IP = new Field("SET_ANONYMIZE_IP", 20);
            GET_ANONYMIZE_IP = new Field("GET_ANONYMIZE_IP", 21);
            SET_SAMPLE_RATE = new Field("SET_SAMPLE_RATE", 22);
            GET_SAMPLE_RATE = new Field("GET_SAMPLE_RATE", 23);
            SET_USE_SECURE = new Field("SET_USE_SECURE", 24);
            GET_USE_SECURE = new Field("GET_USE_SECURE", 25);
            SET_REFERRER = new Field("SET_REFERRER", 26);
            SET_CAMPAIGN = new Field("SET_CAMPAIGN", 27);
            SET_APP_ID = new Field("SET_APP_ID", 28);
            GET_APP_ID = new Field("GET_APP_ID", 29);
            SET_EXCEPTION_PARSER = new Field("SET_EXCEPTION_PARSER", 30);
            GET_EXCEPTION_PARSER = new Field("GET_EXCEPTION_PARSER", 31);
            CONSTRUCT_TRANSACTION = new Field("CONSTRUCT_TRANSACTION", 32);
            CONSTRUCT_EXCEPTION = new Field("CONSTRUCT_EXCEPTION", 33);
            CONSTRUCT_RAW_EXCEPTION = new Field("CONSTRUCT_RAW_EXCEPTION", 34);
            CONSTRUCT_TIMING = new Field("CONSTRUCT_TIMING", 35);
            CONSTRUCT_SOCIAL = new Field("CONSTRUCT_SOCIAL", 36);
            SET_DEBUG = new Field("SET_DEBUG", 37);
            GET_DEBUG = new Field("GET_DEBUG", 38);
            GET_TRACKER = new Field("GET_TRACKER", 39);
            GET_DEFAULT_TRACKER = new Field("GET_DEFAULT_TRACKER", 40);
            SET_DEFAULT_TRACKER = new Field("SET_DEFAULT_TRACKER", 41);
            SET_APP_OPT_OUT = new Field("SET_APP_OPT_OUT", 42);
            REQUEST_APP_OPT_OUT = new Field("REQUEST_APP_OPT_OUT", 43);
            DISPATCH = new Field("DISPATCH", 44);
            SET_DISPATCH_PERIOD = new Field("SET_DISPATCH_PERIOD", 45);
            BLANK_48 = new Field("BLANK_48", 46);
            REPORT_UNCAUGHT_EXCEPTIONS = new Field("REPORT_UNCAUGHT_EXCEPTIONS", 47);
            SET_AUTO_ACTIVITY_TRACKING = new Field("SET_AUTO_ACTIVITY_TRACKING", 48);
            SET_SESSION_TIMEOUT = new Field("SET_SESSION_TIMEOUT", 49);
            CONSTRUCT_EVENT = new Field("CONSTRUCT_EVENT", 50);
            CONSTRUCT_ITEM = new Field("CONSTRUCT_ITEM", 51);
            SET_APP_INSTALLER_ID = new Field("SET_APP_INSTALLER_ID", 52);
            GET_APP_INSTALLER_ID = new Field("GET_APP_INSTALLER_ID", 53);
            $VALUES = (new Field[] {
                TRACK_VIEW, TRACK_VIEW_WITH_APPSCREEN, TRACK_EVENT, TRACK_TRANSACTION, TRACK_EXCEPTION_WITH_DESCRIPTION, TRACK_EXCEPTION_WITH_THROWABLE, BLANK_06, TRACK_TIMING, TRACK_SOCIAL, GET, 
                SET, SEND, SET_START_SESSION, BLANK_13, SET_APP_NAME, BLANK_15, SET_APP_VERSION, BLANK_17, SET_APP_SCREEN, GET_TRACKING_ID, 
                SET_ANONYMIZE_IP, GET_ANONYMIZE_IP, SET_SAMPLE_RATE, GET_SAMPLE_RATE, SET_USE_SECURE, GET_USE_SECURE, SET_REFERRER, SET_CAMPAIGN, SET_APP_ID, GET_APP_ID, 
                SET_EXCEPTION_PARSER, GET_EXCEPTION_PARSER, CONSTRUCT_TRANSACTION, CONSTRUCT_EXCEPTION, CONSTRUCT_RAW_EXCEPTION, CONSTRUCT_TIMING, CONSTRUCT_SOCIAL, SET_DEBUG, GET_DEBUG, GET_TRACKER, 
                GET_DEFAULT_TRACKER, SET_DEFAULT_TRACKER, SET_APP_OPT_OUT, REQUEST_APP_OPT_OUT, DISPATCH, SET_DISPATCH_PERIOD, BLANK_48, REPORT_UNCAUGHT_EXCEPTIONS, SET_AUTO_ACTIVITY_TRACKING, SET_SESSION_TIMEOUT, 
                CONSTRUCT_EVENT, CONSTRUCT_ITEM, SET_APP_INSTALLER_ID, GET_APP_INSTALLER_ID
            });
        }

        private Field(String s, int i)
        {
            super(s, i);
        }
    }

}
