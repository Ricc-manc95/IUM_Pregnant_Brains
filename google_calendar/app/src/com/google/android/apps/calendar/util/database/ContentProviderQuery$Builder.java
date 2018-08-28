// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.database;

import android.net.Uri;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.util.database:
//            ContentProviderQuery

public abstract class Q
{

    public abstract ContentProviderQuery build();

    public abstract Q projection(List list);

    public abstract Q selection(String s);

    public abstract Q sortOrder(String s);

    public abstract Q uri(Uri uri1);

    public Q()
    {
    }
}
