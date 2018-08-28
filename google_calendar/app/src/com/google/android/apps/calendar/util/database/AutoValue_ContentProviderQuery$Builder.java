// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.database;

import android.net.Uri;
import com.google.common.collect.ImmutableList;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.util.database:
//            AutoValue_ContentProviderQuery, ContentProviderQuery

public final class  extends 
{

    private ImmutableList projection;
    private String selection;
    private String sortOrder;
    private Uri uri;

    public final ContentProviderQuery build()
    {
        String s = "";
        if (uri == null)
        {
            s = String.valueOf("").concat(" uri");
        }
        String s2 = s;
        if (projection == null)
        {
            s2 = String.valueOf(s).concat(" projection");
        }
        if (!s2.isEmpty())
        {
            String s1 = String.valueOf(s2);
            if (s1.length() != 0)
            {
                s1 = "Missing required properties:".concat(s1);
            } else
            {
                s1 = new String("Missing required properties:");
            }
            throw new IllegalStateException(s1);
        } else
        {
            return new AutoValue_ContentProviderQuery(uri, projection, selection, null, sortOrder);
        }
    }

    public final sortOrder projection(List list)
    {
        projection = ImmutableList.copyOf(list);
        return this;
    }

    public final projection selection(String s)
    {
        selection = s;
        return this;
    }

    public final selection sortOrder(String s)
    {
        sortOrder = s;
        return this;
    }

    public final sortOrder uri(Uri uri1)
    {
        if (uri1 == null)
        {
            throw new NullPointerException("Null uri");
        } else
        {
            uri = uri1;
            return this;
        }
    }

    public ()
    {
    }
}
