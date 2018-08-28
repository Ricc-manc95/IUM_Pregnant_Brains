// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.lifecycle;


public final class  extends Enum
{

    private static final INVALID $VALUES[];
    public static final INVALID INTEGER;
    public static final INVALID INVALID;
    public static final INVALID STRING_LIST;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        STRING_LIST = new <init>("STRING_LIST", 0);
        INTEGER = new <init>("INTEGER", 1);
        INVALID = new <init>("INVALID", 2);
        $VALUES = (new .VALUES[] {
            STRING_LIST, INTEGER, INVALID
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
