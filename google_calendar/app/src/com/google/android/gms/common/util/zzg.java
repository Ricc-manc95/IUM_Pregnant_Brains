// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.util;

import android.os.SystemClock;

// Referenced classes of package com.google.android.gms.common.util:
//            Clock

public final class zzg
    implements Clock
{

    public static zzg zzaTf = new zzg();

    private zzg()
    {
    }

    public final long currentTimeMillis()
    {
        return System.currentTimeMillis();
    }

    public final long elapsedRealtime()
    {
        return SystemClock.elapsedRealtime();
    }

}
