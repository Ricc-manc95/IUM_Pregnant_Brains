// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.api;


public final class  extends Enum
{

    private static final END $VALUES[];
    public static final END END;
    public static final END MOVE;
    public static final END START;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        MOVE = new <init>("MOVE", 0);
        START = new <init>("START", 1);
        END = new <init>("END", 2);
        $VALUES = (new .VALUES[] {
            MOVE, START, END
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
