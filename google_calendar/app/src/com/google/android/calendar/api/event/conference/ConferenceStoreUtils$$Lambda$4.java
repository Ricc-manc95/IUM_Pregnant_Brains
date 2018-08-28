// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;

import com.google.api.services.calendar.model.Conference;

public final class StoreSetter
    implements StoreSetter
{

    public static final StoreSetter $instance = new <init>();

    public final void setValue(Conference conference, Object obj)
    {
        conference.uri = (String)obj;
    }


    private StoreSetter()
    {
    }
}
