// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;


// Referenced classes of package com.google.android.calendar.api.event:
//            AutoValue_V2AEventDescriptor, V2AEventKey, V2AEventDescriptor

public final class Q extends Q
{

    private V2AEventKey key;
    private Long originalStart;
    private Boolean recurringException;
    private Boolean recurringPhantom;

    final V2AEventDescriptor build()
    {
        String s1 = "";
        if (recurringException == null)
        {
            s1 = String.valueOf("").concat(" recurringException");
        }
        String s = s1;
        if (recurringPhantom == null)
        {
            s = String.valueOf(s1).concat(" recurringPhantom");
        }
        s1 = s;
        if (originalStart == null)
        {
            s1 = String.valueOf(s).concat(" originalStart");
        }
        s = s1;
        if (key == null)
        {
            s = String.valueOf(s1).concat(" key");
        }
        if (!s.isEmpty())
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Missing required properties:".concat(s);
            } else
            {
                s = new String("Missing required properties:");
            }
            throw new IllegalStateException(s);
        } else
        {
            return new AutoValue_V2AEventDescriptor(recurringException.booleanValue(), recurringPhantom.booleanValue(), originalStart.longValue(), key);
        }
    }

    final key key(V2AEventKey v2aeventkey)
    {
        if (v2aeventkey == null)
        {
            throw new NullPointerException("Null key");
        } else
        {
            key = v2aeventkey;
            return this;
        }
    }

    final key originalStart(long l)
    {
        originalStart = Long.valueOf(l);
        return this;
    }

    final originalStart recurringException(boolean flag)
    {
        recurringException = Boolean.valueOf(flag);
        return this;
    }

    final recurringException recurringPhantom(boolean flag)
    {
        recurringPhantom = Boolean.valueOf(flag);
        return this;
    }

    public Q()
    {
    }
}
