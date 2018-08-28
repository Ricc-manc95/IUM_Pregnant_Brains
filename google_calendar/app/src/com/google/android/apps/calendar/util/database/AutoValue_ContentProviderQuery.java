// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.database;

import android.net.Uri;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.apps.calendar.util.database:
//            ContentProviderQuery

final class AutoValue_ContentProviderQuery extends ContentProviderQuery
{

    private final ImmutableList projection;
    private final String selection;
    private final ImmutableList selectionArgs;
    private final String sortOrder;
    private final Uri uri;

    AutoValue_ContentProviderQuery(Uri uri1, ImmutableList immutablelist, String s, ImmutableList immutablelist1, String s1)
    {
        uri = uri1;
        projection = immutablelist;
        selection = s;
        selectionArgs = immutablelist1;
        sortOrder = s1;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
label0:
        {
            if (!(obj instanceof ContentProviderQuery))
            {
                break MISSING_BLOCK_LABEL_141;
            }
            obj = (ContentProviderQuery)obj;
            if (uri.equals(((ContentProviderQuery) (obj)).uri()) && projection.equals(((ContentProviderQuery) (obj)).projection()) && (selection != null ? selection.equals(((ContentProviderQuery) (obj)).selection()) : ((ContentProviderQuery) (obj)).selection() == null) && (selectionArgs != null ? selectionArgs.equals(((ContentProviderQuery) (obj)).selectionArgs()) : ((ContentProviderQuery) (obj)).selectionArgs() == null))
            {
                break label0;
            } else
            {
                break; /* Loop/switch isn't completed */
            }
        }
        if (sortOrder != null) goto _L4; else goto _L3
_L3:
        if (((ContentProviderQuery) (obj)).sortOrder() == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!sortOrder.equals(((ContentProviderQuery) (obj)).sortOrder())) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    public final int hashCode()
    {
        int k = 0;
        int l = uri.hashCode();
        int i1 = projection.hashCode();
        int i;
        int j;
        if (selection == null)
        {
            i = 0;
        } else
        {
            i = selection.hashCode();
        }
        if (selectionArgs == null)
        {
            j = 0;
        } else
        {
            j = selectionArgs.hashCode();
        }
        if (sortOrder != null)
        {
            k = sortOrder.hashCode();
        }
        return (j ^ (i ^ ((l ^ 0xf4243) * 0xf4243 ^ i1) * 0xf4243) * 0xf4243) * 0xf4243 ^ k;
    }

    public final ImmutableList projection()
    {
        return projection;
    }

    public final String selection()
    {
        return selection;
    }

    public final ImmutableList selectionArgs()
    {
        return selectionArgs;
    }

    public final String sortOrder()
    {
        return sortOrder;
    }

    public final String toString()
    {
        String s = String.valueOf(uri);
        String s1 = String.valueOf(projection);
        String s2 = selection;
        String s3 = String.valueOf(selectionArgs);
        String s4 = sortOrder;
        return (new StringBuilder(String.valueOf(s).length() + 79 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length())).append("ContentProviderQuery{uri=").append(s).append(", projection=").append(s1).append(", selection=").append(s2).append(", selectionArgs=").append(s3).append(", sortOrder=").append(s4).append("}").toString();
    }

    public final Uri uri()
    {
        return uri;
    }
}
