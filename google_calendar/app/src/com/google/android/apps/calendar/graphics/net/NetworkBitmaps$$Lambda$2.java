// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.graphics.net;

import com.google.common.util.concurrent.SettableFuture;

// Referenced classes of package com.google.android.apps.calendar.graphics.net:
//            NetworkBitmaps, NetworkBitmapId

final class arg._cls2
    implements com.android.volley.t.NetworkBitmaps..Lambda._cls2
{

    private final SettableFuture arg$1;
    private final NetworkBitmapId arg$2;

    public final void onResponse(Object obj)
    {
        NetworkBitmaps.lambda$directDiskOrNetworkBitmap$2$NetworkBitmaps(arg$1, arg$2, (byte[])obj);
    }

    (SettableFuture settablefuture, NetworkBitmapId networkbitmapid)
    {
        arg$1 = settablefuture;
        arg$2 = networkbitmapid;
    }
}
