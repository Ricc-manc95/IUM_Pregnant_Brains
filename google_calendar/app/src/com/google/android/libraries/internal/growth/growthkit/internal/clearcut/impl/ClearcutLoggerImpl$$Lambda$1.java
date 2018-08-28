// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.clearcut.impl;

import android.util.Log;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.common.base.Receiver;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.clearcut.impl:
//            ClearcutLoggerImpl

final class 
    implements Receiver
{

    public static final Receiver $instance = new <init>();

    public final void accept(Object obj)
    {
        Throwable throwable = (Throwable)obj;
        obj = ClearcutLoggerImpl.logger;
        String s = "Failed to write a clearcut log message";
        Object aobj[] = new Object[0];
        String s1 = ((Logger) (obj)).tag;
        obj = s;
        if (aobj != null)
        {
            obj = s;
            if (aobj.length > 0)
            {
                obj = String.format("Failed to write a clearcut log message", aobj);
            }
        }
        Log.w(s1, ((String) (obj)), throwable);
    }


    private ()
    {
    }
}
