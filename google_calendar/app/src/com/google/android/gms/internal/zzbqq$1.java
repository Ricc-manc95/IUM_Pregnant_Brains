// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.database.ContentObserver;
import android.os.Handler;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package com.google.android.gms.internal:
//            zzbqq

final class n extends ContentObserver
{

    public final void onChange(boolean flag)
    {
        zzbqq.bw.set(true);
    }

    n(Handler handler)
    {
        super(null);
    }
}
