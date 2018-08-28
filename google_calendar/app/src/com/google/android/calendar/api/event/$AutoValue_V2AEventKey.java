// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.calendar.v2a.shared.storage.proto.EventKey;
import com.google.protobuf.GeneratedMessageLite;

// Referenced classes of package com.google.android.calendar.api.event:
//            V2AEventKey

abstract class $AutoValue_V2AEventKey extends V2AEventKey
{

    private final EventKey v2Key;

    $AutoValue_V2AEventKey(EventKey eventkey)
    {
        if (eventkey == null)
        {
            throw new NullPointerException("Null v2Key");
        } else
        {
            v2Key = eventkey;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (obj instanceof V2AEventKey)
        {
            obj = (V2AEventKey)obj;
            return v2Key.equals(((V2AEventKey) (obj)).getV2Key());
        } else
        {
            return false;
        }
    }

    public final EventKey getV2Key()
    {
        return v2Key;
    }

    public int hashCode()
    {
        return 0xf4243 ^ v2Key.hashCode();
    }

    public String toString()
    {
        String s = String.valueOf(v2Key);
        return (new StringBuilder(String.valueOf(s).length() + 19)).append("V2AEventKey{v2Key=").append(s).append("}").toString();
    }
}
