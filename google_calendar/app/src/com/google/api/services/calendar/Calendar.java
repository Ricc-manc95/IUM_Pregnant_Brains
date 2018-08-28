// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar;

import com.google.api.client.googleapis.GoogleUtils;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.common.base.Strings;

public class Calendar extends AbstractGoogleJsonClient
{
    public final class Acl
    {

        public final Calendar this$0;

        public Acl()
        {
            this$0 = Calendar.this;
            super();
        }
    }

    public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder
    {

        public final com.google.api.client.googleapis.services.AbstractGoogleClient.Builder setApplicationName(String s)
        {
            return (Builder)super.setApplicationName(s);
        }

        public final volatile com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder setApplicationName(String s)
        {
            return (Builder)setApplicationName(s);
        }

        public final com.google.api.client.googleapis.services.AbstractGoogleClient.Builder setBatchPath(String s)
        {
            return (Builder)super.setBatchPath(s);
        }

        public final com.google.api.client.googleapis.services.AbstractGoogleClient.Builder setRootUrl(String s)
        {
            return (Builder)super.setRootUrl(s);
        }

        public final volatile com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder setRootUrl(String s)
        {
            return (Builder)setRootUrl(s);
        }

        public final com.google.api.client.googleapis.services.AbstractGoogleClient.Builder setServicePath(String s)
        {
            return (Builder)super.setServicePath(s);
        }

        public final volatile com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder setServicePath(String s)
        {
            return (Builder)setServicePath(s);
        }

        public Builder(HttpTransport httptransport, JsonFactory jsonfactory, HttpRequestInitializer httprequestinitializer)
        {
            super(httptransport, jsonfactory, "https://www.googleapis.com/", "calendar/v3internal/", httprequestinitializer, false);
            httptransport = (Builder)setBatchPath("batch");
        }
    }

    public final class CalendarList
    {

        public final Calendar this$0;

        public CalendarList()
        {
            this$0 = Calendar.this;
            super();
        }
    }

    public final class Calendars
    {

        public final Calendar this$0;

        public Calendars()
        {
            this$0 = Calendar.this;
            super();
        }
    }

    public final class Events
    {

        public final Calendar this$0;

        public Events()
        {
            this$0 = Calendar.this;
            super();
        }
    }

    public final class Habits
    {

        public final Calendar this$0;

        public Habits()
        {
            this$0 = Calendar.this;
            super();
        }
    }

    public final class Settings
    {

        public final Calendar this$0;

        public Settings()
        {
            this$0 = Calendar.this;
            super();
        }
    }


    public Calendar(HttpTransport httptransport, JsonFactory jsonfactory, HttpRequestInitializer httprequestinitializer)
    {
        this(new Builder(httptransport, jsonfactory, httprequestinitializer));
    }

    public Calendar(Builder builder)
    {
        super(builder);
    }

    static 
    {
        String s;
        boolean flag;
        if (GoogleUtils.MAJOR_VERSION.intValue() == 1 && GoogleUtils.MINOR_VERSION.intValue() >= 15)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        s = GoogleUtils.VERSION;
        if (!flag)
        {
            throw new IllegalStateException(Strings.lenientFormat("You are currently running with version %s of google-api-client. You need at least version 1.15 of google-api-client to run version 1.24.0-SNAPSHOT of the Calendar API library.", new Object[] {
                s
            }));
        }
    }
}
