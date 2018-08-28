// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.stitch.flags;


// Referenced classes of package com.google.android.libraries.stitch.flags:
//            Flag

public final class DebugFlag extends Flag
{

    public boolean defaultDebugValue;

    public DebugFlag(String s)
    {
        this(s, true);
    }

    private DebugFlag(String s, boolean flag)
    {
        super(s);
        defaultDebugValue = true;
    }
}
