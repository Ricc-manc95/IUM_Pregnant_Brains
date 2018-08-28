// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.concurrent;


public final class  extends Enum
{

    private static final CLOSED $VALUES[];
    public static final CLOSED CLOSED;
    public static final CLOSED INITIAL;
    public static final CLOSED TRANSFORMED;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        INITIAL = new <init>("INITIAL", 0);
        TRANSFORMED = new <init>("TRANSFORMED", 1);
        CLOSED = new <init>("CLOSED", 2);
        $VALUES = (new .VALUES[] {
            INITIAL, TRANSFORMED, CLOSED
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
