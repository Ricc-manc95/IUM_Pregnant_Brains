// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.net.Uri;
import com.google.android.calendar.api.event.Event;

final class event
{

    public final Event event;
    public final String ownerEmail;
    public final String serverCollectionId;
    public final String serverItemId;
    public final String subject;
    public final String uid;
    public final Uri uri;

    public (Uri uri1, String s, String s1, String s2, String s3, String s4, Event event1)
    {
        uri = uri1;
        subject = s;
        uid = s1;
        ownerEmail = s2;
        serverCollectionId = s3;
        serverItemId = s4;
        event = event1;
    }
}
