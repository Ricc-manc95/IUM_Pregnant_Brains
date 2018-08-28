// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip;


public final class  extends Enum
{

    private static final ANYWHERE $VALUES[];
    public static final ANYWHERE ANYWHERE;
    private static final ANYWHERE INTERNAL;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        INTERNAL = new <init>("INTERNAL", 0);
        ANYWHERE = new <init>("ANYWHERE", 1);
        $VALUES = (new .VALUES[] {
            INTERNAL, ANYWHERE
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
