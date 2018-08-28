// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.metric;


// Referenced classes of package com.google.calendar.v2a.android.util.metric:
//            MetricUtils

public static final class Q extends Enum
{

    private static final APP_INTERACTIVE $VALUES[];
    public static final APP_INTERACTIVE ACTIVITY_INIT;
    public static final APP_INTERACTIVE APP_INTERACTIVE;
    public Runnable action;

    public static Q[] values()
    {
        return (Q[])$VALUES.clone();
    }

    static 
    {
        ACTIVITY_INIT = new <init>("ACTIVITY_INIT", 0);
        APP_INTERACTIVE = new <init>("APP_INTERACTIVE", 1);
        $VALUES = (new .VALUES[] {
            ACTIVITY_INIT, APP_INTERACTIVE
        });
    }

    private Q(String s, int i)
    {
        super(s, i);
    }
}
