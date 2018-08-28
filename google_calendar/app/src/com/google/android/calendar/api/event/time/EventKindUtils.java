// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.time;

import android.database.Cursor;

public final class EventKindUtils
{

    public static int getEventKind(Cursor cursor)
    {
        long l = cursor.getLong(23);
        cursor = cursor.getString(10);
        if (l != 0L)
        {
            return 2;
        }
        return cursor == null ? 0 : 1;
    }
}
