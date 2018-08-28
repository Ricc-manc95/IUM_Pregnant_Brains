// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.cp;

import android.database.Cursor;

final class 
    implements com.google.android.apps.calendar.util.database.
{

    public static final com.google.android.apps.calendar.util.database. $instance = new <init>();

    public final Object extract(Cursor cursor)
    {
        return Long.valueOf(cursor.getLong(0));
    }


    private ()
    {
    }
}
