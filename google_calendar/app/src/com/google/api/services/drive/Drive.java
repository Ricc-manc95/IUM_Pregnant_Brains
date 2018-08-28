// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.drive;

import com.google.api.client.googleapis.GoogleUtils;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.common.base.Strings;

public final class Drive extends AbstractGoogleJsonClient
{

    public Drive(Builder builder)
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
            throw new IllegalStateException(Strings.lenientFormat("You are currently running with version %s of google-api-client. You need at least version 1.15 of google-api-client to run version 1.24.0-SNAPSHOT of the Drive API library.", new Object[] {
                s
            }));
        }
    }
}
