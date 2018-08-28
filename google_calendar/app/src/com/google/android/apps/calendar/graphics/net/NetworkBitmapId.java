// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.graphics.net;

import android.net.Uri;

// Referenced classes of package com.google.android.apps.calendar.graphics.net:
//            BitmapDecodeOptions

public abstract class NetworkBitmapId
{

    public NetworkBitmapId()
    {
    }

    public abstract BitmapDecodeOptions decodeOptions();

    public abstract Uri uri();
}
