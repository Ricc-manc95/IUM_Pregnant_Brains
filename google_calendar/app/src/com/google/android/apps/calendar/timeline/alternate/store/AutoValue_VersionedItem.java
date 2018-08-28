// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;


// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.store:
//            VersionedItem

final class AutoValue_VersionedItem extends VersionedItem
{

    private final Object item;
    private final int version;

    AutoValue_VersionedItem(Object obj, int i)
    {
        item = obj;
        version = i;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof VersionedItem)
            {
                if (!item.equals(((VersionedItem) (obj = (VersionedItem)obj)).getItem()) || version != ((VersionedItem) (obj)).getVersion())
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final Object getItem()
    {
        return item;
    }

    public final int getVersion()
    {
        return version;
    }

    public final int hashCode()
    {
        return (item.hashCode() ^ 0xf4243) * 0xf4243 ^ version;
    }

    public final String toString()
    {
        String s = String.valueOf(item);
        int i = version;
        return (new StringBuilder(String.valueOf(s).length() + 41)).append("VersionedItem{item=").append(s).append(", version=").append(i).append("}").toString();
    }
}
