// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar;

import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.calendar:
//            Calendar

public class CalendarRequest extends AbstractGoogleJsonClientRequest
{

    public String alt;
    public String fields;
    public String key;
    public String oauthToken;
    public Boolean prettyPrint;
    public String quotaUser;
    public String userIp;

    public CalendarRequest(Calendar calendar, String s, String s1, Object obj, Class class1)
    {
        super(calendar, s, s1, obj, class1);
    }

    public final AbstractGoogleClient getAbstractGoogleClient()
    {
        return (Calendar)super.getAbstractGoogleClient();
    }

    public final volatile AbstractGoogleJsonClient getAbstractGoogleClient()
    {
        return (Calendar)getAbstractGoogleClient();
    }

    public volatile AbstractGoogleClientRequest set(String s, Object obj)
    {
        return (CalendarRequest)set(s, obj);
    }

    public volatile AbstractGoogleJsonClientRequest set(String s, Object obj)
    {
        return (CalendarRequest)set(s, obj);
    }

    public volatile GenericData set(String s, Object obj)
    {
        return set(s, obj);
    }

    public CalendarRequest set(String s, Object obj)
    {
        return (CalendarRequest)super.set(s, obj);
    }

    public CalendarRequest setFields(String s)
    {
        fields = s;
        return this;
    }
}
