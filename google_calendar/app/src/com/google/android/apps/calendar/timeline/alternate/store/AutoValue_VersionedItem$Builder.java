// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;


// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.store:
//            AutoValue_VersionedItem, VersionedItem

final class  extends 
{

    private Object item;
    private Integer version;

    public final VersionedItem build()
    {
        String s = "";
        if (item == null)
        {
            s = String.valueOf("").concat(" item");
        }
        String s2 = s;
        if (version == null)
        {
            s2 = String.valueOf(s).concat(" version");
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
            return new AutoValue_VersionedItem(item, version.intValue());
        }
    }

    public final version setItem(Object obj)
    {
        if (obj == null)
        {
            throw new NullPointerException("Null item");
        } else
        {
            item = obj;
            return this;
        }
    }

    public final item setVersion(int i)
    {
        version = Integer.valueOf(i);
        return this;
    }

    ()
    {
    }
}
