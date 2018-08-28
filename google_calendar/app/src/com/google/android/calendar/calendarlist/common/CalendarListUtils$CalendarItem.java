// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.calendarlist.common;

import android.content.ContentUris;
import android.net.Uri;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.color.CalendarColor;

public class priority extends priority
{

    public CalendarDescriptor calendarDescriptor;
    public CalendarListEntry calendarListEntry;
    public int color;
    public String displayName;
    public boolean isPrimary;
    public boolean isSynced;
    public boolean isVisible;
    public String ownerAccount;
    public Uri uri;

    public final int getType()
    {
        return 1;
    }

    public final int getViewType()
    {
        return 2;
    }

    public ()
    {
    }

    public (CalendarListEntry calendarlistentry)
    {
        calendarListEntry = calendarlistentry;
        account = calendarlistentry.getDescriptor().account;
        uri = ContentUris.withAppendedId(android.provider.ENT_URI, calendarlistentry.getDescriptor().calendarKey.getLocalId());
        isVisible = calendarlistentry.isVisible();
        isSynced = calendarlistentry.isSyncEnabled();
        isPrimary = calendarlistentry.isPrimary();
        color = calendarlistentry.getColor().getValue();
        displayName = calendarlistentry.getDisplayName();
        ownerAccount = calendarlistentry.getDescriptor().calendarId;
        calendarDescriptor = calendarlistentry.getDescriptor();
        if (isPrimary)
        {
            priority = 1;
            return;
        } else
        {
            priority = 3;
            return;
        }
    }
}
