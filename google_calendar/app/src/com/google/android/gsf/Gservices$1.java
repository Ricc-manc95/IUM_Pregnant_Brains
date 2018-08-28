// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gsf;

import android.database.ContentObserver;
import android.os.Handler;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package com.google.android.gsf:
//            Gservices

final class nit> extends ContentObserver
{

    public final void onChange(boolean flag)
    {
        Gservices.sInvalidateCache.set(true);
    }

    oolean(Handler handler)
    {
        super(null);
    }
}
