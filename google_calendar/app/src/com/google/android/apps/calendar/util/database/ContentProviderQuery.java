// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.database;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

public abstract class ContentProviderQuery
{

    public ContentProviderQuery()
    {
    }

    public abstract ImmutableList projection();

    public final Cursor query(ContentResolver contentresolver)
    {
        Uri uri1 = uri();
        String as1[] = (String[])projection().toArray(new String[0]);
        String s = selection();
        String as[];
        if (selectionArgs() == null)
        {
            as = null;
        } else
        {
            as = (String[])selectionArgs().toArray(new String[0]);
        }
        return contentresolver.query(uri1, as1, s, as, sortOrder());
    }

    public abstract String selection();

    public abstract ImmutableList selectionArgs();

    public abstract String sortOrder();

    public abstract Uri uri();
}
