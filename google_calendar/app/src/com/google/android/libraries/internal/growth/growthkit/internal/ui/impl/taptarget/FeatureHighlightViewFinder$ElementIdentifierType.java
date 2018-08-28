// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.taptarget;


final class  extends Enum
{

    private static final VE_ID $VALUES[];
    public static final VE_ID ID;
    public static final VE_ID TAG;
    public static final VE_ID UNKNOWN;
    public static final VE_ID VE_ID;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        UNKNOWN = new <init>("UNKNOWN", 0);
        ID = new <init>("ID", 1);
        TAG = new <init>("TAG", 2);
        VE_ID = new <init>("VE_ID", 3);
        $VALUES = (new .VALUES[] {
            UNKNOWN, ID, TAG, VE_ID
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
