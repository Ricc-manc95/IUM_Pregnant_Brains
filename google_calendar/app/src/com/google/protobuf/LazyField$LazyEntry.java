// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;


// Referenced classes of package com.google.protobuf:
//            LazyField, MessageLite, LazyFieldLite

final class entry
    implements java.util.LazyEntry
{

    public java.util.e entry;

    public final Object getKey()
    {
        return entry.entry();
    }

    public final Object getValue()
    {
        if ((LazyField)entry.entry() == null)
        {
            return null;
        } else
        {
            return LazyField.getValue();
        }
    }

    public final Object setValue(Object obj)
    {
        if (!(obj instanceof MessageLite))
        {
            throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
        } else
        {
            LazyField lazyfield = (LazyField)entry.entry();
            obj = (MessageLite)obj;
            MessageLite messagelite = ((LazyFieldLite) (lazyfield)).value;
            lazyfield.delayedBytes = null;
            lazyfield.memoizedBytes = null;
            lazyfield.value = ((MessageLite) (obj));
            return messagelite;
        }
    }

    (java.util.LazyEntry lazyentry)
    {
        entry = lazyentry;
    }
}
