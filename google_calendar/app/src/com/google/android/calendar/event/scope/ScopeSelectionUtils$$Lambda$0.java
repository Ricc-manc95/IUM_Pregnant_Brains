// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.scope;

import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.event.scope:
//            AutoValue_ScopeSelectionDialog_Scope

public final class _Scope
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        obj = (Integer)obj;
        ((Integer) (obj)).intValue();
        JVM INSTR tableswitch 0 2: default 36
    //                   0 46
    //                   1 62
    //                   2 68;
           goto _L1 _L2 _L3 _L4
_L1:
        throw new IllegalArgumentException("Unrecognized scope.");
_L2:
        int i = 0x7f13042c;
_L6:
        return new AutoValue_ScopeSelectionDialog_Scope(i, ((Integer) (obj)).intValue());
_L3:
        i = 0x7f13042a;
        continue; /* Loop/switch isn't completed */
_L4:
        i = 0x7f130429;
        if (true) goto _L6; else goto _L5
_L5:
    }


    private _Scope()
    {
    }
}
