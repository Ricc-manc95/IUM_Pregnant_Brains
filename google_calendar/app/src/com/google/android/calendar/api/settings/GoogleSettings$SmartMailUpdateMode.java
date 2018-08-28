// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;


// Referenced classes of package com.google.android.calendar.api.settings:
//            GoogleSettings

public static final class Q extends Enum
{

    private static final UPCOMING $VALUES[];
    public static final UPCOMING ALL;
    public static final UPCOMING NEW;
    public static final UPCOMING UPCOMING;

    public static Q[] values()
    {
        return (Q[])$VALUES.clone();
    }

    static 
    {
        ALL = new <init>("ALL", 0);
        NEW = new <init>("NEW", 1);
        UPCOMING = new <init>("UPCOMING", 2);
        $VALUES = (new .VALUES[] {
            ALL, NEW, UPCOMING
        });
    }

    private Q(String s, int i)
    {
        super(s, i);
    }
}
