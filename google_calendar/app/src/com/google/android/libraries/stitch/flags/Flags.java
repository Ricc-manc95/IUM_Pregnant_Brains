// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.stitch.flags;

import com.google.android.libraries.stitch.util.SystemProperties;

// Referenced classes of package com.google.android.libraries.stitch.flags:
//            DebugFlag, Flag

public final class Flags
{

    public static boolean get(DebugFlag debugflag)
    {
        String s;
        if (debugflag.defaultDebugValue)
        {
            s = "true";
        } else
        {
            s = "false";
        }
        return "true".equals(SystemProperties.getString("debug.social", "true")) && "true".equals(SystemProperties.getString(((Flag) (debugflag)).name, s));
    }
}
