// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.graphics.net;

import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.apps.calendar.graphics.net:
//            NetworkBitmaps, BitmapDecodeOptions

final class arg._cls2
    implements Callable
{

    private final BitmapDecodeOptions arg$1;
    private final byte arg$2[];

    public final Object call()
    {
        return NetworkBitmaps.lambda$decode$4$NetworkBitmaps(arg$1, arg$2);
    }

    (BitmapDecodeOptions bitmapdecodeoptions, byte abyte0[])
    {
        arg$1 = bitmapdecodeoptions;
        arg$2 = abyte0;
    }
}
