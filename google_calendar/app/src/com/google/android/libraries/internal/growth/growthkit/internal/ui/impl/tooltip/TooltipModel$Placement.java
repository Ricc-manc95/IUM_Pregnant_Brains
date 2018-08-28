// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip;


public final class  extends Enum
{

    private static final END $VALUES[];
    public static final END ABOVE;
    public static final END BELOW;
    public static final END END;
    public static final END START;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        ABOVE = new <init>("ABOVE", 0);
        BELOW = new <init>("BELOW", 1);
        START = new <init>("START", 2);
        END = new <init>("END", 3);
        $VALUES = (new .VALUES[] {
            ABOVE, BELOW, START, END
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
