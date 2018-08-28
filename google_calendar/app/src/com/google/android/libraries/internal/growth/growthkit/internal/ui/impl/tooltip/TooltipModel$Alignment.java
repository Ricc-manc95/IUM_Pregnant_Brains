// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip;


public final class  extends Enum
{

    private static final END $VALUES[];
    public static final END CENTER;
    public static final END END;
    public static final END START;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        START = new <init>("START", 0);
        CENTER = new <init>("CENTER", 1);
        END = new <init>("END", 2);
        $VALUES = (new .VALUES[] {
            START, CENTER, END
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
