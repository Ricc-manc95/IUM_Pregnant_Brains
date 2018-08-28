// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap;

import android.content.res.Resources;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package com.android.bitmap:
//            RequestKey

public final class ResourceRequestKey
    implements RequestKey
{

    private int resId;
    private Resources resources;

    public ResourceRequestKey(Resources resources1, int i)
    {
        resources = resources1;
        resId = i;
    }

    public final RequestKey.Cancelable createFileDescriptorFactoryOrByteArrayAsync$51666RRD5TGMSP3IDTKM8BR2D5Q6QOBG5T96ASBLCLPN8IR5F4TKOORFDKNM2RJ4E9NMIP1FC9KN8RB1E0NL4PBHELIN6T2BCLSI8GR1DHM64OB3DCTIIJ33DTMIUOBECHP6UQB45TH6IT3DC5O2UKJ5E5QMASRK9DINI923C5N66PBCC5H6OP9R0(RequestKey.Callback callback)
    {
        return null;
    }

    public final InputStream createInputStream()
        throws IOException
    {
        return resources.openRawResource(resId);
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            obj = (ResourceRequestKey)obj;
            if (resId != ((ResourceRequestKey) (obj)).resId)
            {
                return false;
            }
        }
        return true;
    }

    public final boolean hasOrientationExif()
        throws IOException
    {
        return false;
    }

    public final int hashCode()
    {
        return resId;
    }

    public final String toString()
    {
        return String.format("ResourceRequestKey: %d", new Object[] {
            Integer.valueOf(resId)
        });
    }
}
