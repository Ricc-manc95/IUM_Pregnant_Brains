// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.provider;

import android.content.ContentProviderResult;
import android.content.ContentUris;

public final class results
{

    public final int operationCount;
    public final ContentProviderResult results[];

    public final Long getLocalId(int i)
    {
        android.net.Uri uri = results[i].uri;
        long l;
        if (uri != null)
        {
            if ((l = ContentUris.parseId(uri)) != -1L)
            {
                return Long.valueOf(l);
            }
        }
        return null;
    }

    public final boolean hasAnyRowChanged()
    {
        ContentProviderResult acontentproviderresult[] = results;
        int j = acontentproviderresult.length;
        boolean flag;
        for (int i = 0; i < j; i++)
        {
            ContentProviderResult contentproviderresult = acontentproviderresult[i];
            if (contentproviderresult.uri != null || contentproviderresult.count.intValue() > 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return true;
            }
        }

        return false;
    }

    (int i, ContentProviderResult acontentproviderresult[])
    {
        operationCount = i;
        results = acontentproviderresult;
    }
}
