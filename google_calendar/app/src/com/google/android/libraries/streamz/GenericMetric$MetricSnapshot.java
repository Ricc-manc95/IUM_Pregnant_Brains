// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.streamz;

import java.util.Map;

// Referenced classes of package com.google.android.libraries.streamz:
//            Field

final class fields
{

    public Map cellMap;
    public final Field fields[];
    public final String name;
    public int updates;

    transient (String s, Field afield[])
    {
        if (s == null)
        {
            throw new NullPointerException();
        } else
        {
            name = (String)s;
            fields = afield;
            return;
        }
    }
}
