// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.graphics.net;

import android.net.Uri;

// Referenced classes of package com.google.android.apps.calendar.graphics.net:
//            NetworkBitmapId, BitmapDecodeOptions

public final class AutoValue_NetworkBitmapId extends NetworkBitmapId
{

    private final BitmapDecodeOptions decodeOptions;
    private final Uri uri;

    public AutoValue_NetworkBitmapId(Uri uri1, BitmapDecodeOptions bitmapdecodeoptions)
    {
        if (uri1 == null)
        {
            throw new NullPointerException("Null uri");
        }
        uri = uri1;
        if (bitmapdecodeoptions == null)
        {
            throw new NullPointerException("Null decodeOptions");
        } else
        {
            decodeOptions = bitmapdecodeoptions;
            return;
        }
    }

    public final BitmapDecodeOptions decodeOptions()
    {
        return decodeOptions;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof NetworkBitmapId)
            {
                if (!uri.equals(((NetworkBitmapId) (obj = (NetworkBitmapId)obj)).uri()) || !decodeOptions.equals(((NetworkBitmapId) (obj)).decodeOptions()))
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

    public final int hashCode()
    {
        return (uri.hashCode() ^ 0xf4243) * 0xf4243 ^ decodeOptions.hashCode();
    }

    public final String toString()
    {
        String s = String.valueOf(uri);
        String s1 = String.valueOf(decodeOptions);
        return (new StringBuilder(String.valueOf(s).length() + 37 + String.valueOf(s1).length())).append("NetworkBitmapId{uri=").append(s).append(", decodeOptions=").append(s1).append("}").toString();
    }

    public final Uri uri()
    {
        return uri;
    }
}
