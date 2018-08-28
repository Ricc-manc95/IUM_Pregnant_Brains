// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.drive;

import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.util.GenericData;

// Referenced classes of package com.google.api.services.drive:
//            Drive

public class DriveRequest extends AbstractGoogleJsonClientRequest
{

    public String alt;
    public String fields;
    public String key;
    public String oauthToken;
    public Boolean prettyPrint;
    public String quotaUser;
    public String userIp;

    public DriveRequest(Drive drive, String s, String s1, Object obj, Class class1)
    {
        super(drive, s, s1, obj, class1);
    }

    public final AbstractGoogleClient getAbstractGoogleClient()
    {
        return (Drive)super.getAbstractGoogleClient();
    }

    public final volatile AbstractGoogleJsonClient getAbstractGoogleClient()
    {
        return (Drive)getAbstractGoogleClient();
    }

    public volatile AbstractGoogleClientRequest set(String s, Object obj)
    {
        return (DriveRequest)set(s, obj);
    }

    public volatile AbstractGoogleJsonClientRequest set(String s, Object obj)
    {
        return (DriveRequest)set(s, obj);
    }

    public volatile GenericData set(String s, Object obj)
    {
        return set(s, obj);
    }

    public DriveRequest set(String s, Object obj)
    {
        return (DriveRequest)super.set(s, obj);
    }
}
