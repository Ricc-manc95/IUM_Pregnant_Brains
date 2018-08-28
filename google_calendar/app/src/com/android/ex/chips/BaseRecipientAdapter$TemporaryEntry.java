// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.ex.chips;

import android.database.Cursor;

public final class lookupKey
{

    public final long contactId;
    public final long dataId;
    public final String destination;
    public final String destinationLabel;
    public final int destinationType;
    public final Long directoryId;
    public final String displayName;
    public final int displayNameSource;
    public final String lookupKey;
    public final String thumbnailUriString;

    public (Cursor cursor, Long long1)
    {
        displayName = cursor.getString(0);
        destination = cursor.getString(1);
        destinationType = cursor.getInt(2);
        destinationLabel = cursor.getString(3);
        contactId = cursor.getLong(4);
        directoryId = long1;
        dataId = cursor.getLong(5);
        thumbnailUriString = cursor.getString(6);
        displayNameSource = cursor.getInt(7);
        lookupKey = cursor.getString(8);
    }
}
