// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.battery;

import android.content.Context;

// Referenced classes of package com.google.android.libraries.performance.primes.battery:
//            HashingNameSanitizer

public final class SystemHealthCapture
{

    public final Context context;
    public final HashingNameSanitizer hashingNameSanitizer = new HashingNameSanitizer();

    public SystemHealthCapture(Context context1)
    {
        context = context1;
    }
}
