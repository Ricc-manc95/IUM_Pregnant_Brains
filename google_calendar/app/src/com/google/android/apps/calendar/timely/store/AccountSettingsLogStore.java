// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timely.store;

import android.database.sqlite.SQLiteDatabase;

public final class AccountSettingsLogStore
{

    public final SQLiteDatabase database;

    public AccountSettingsLogStore(SQLiteDatabase sqlitedatabase)
    {
        database = sqlitedatabase;
    }
}
