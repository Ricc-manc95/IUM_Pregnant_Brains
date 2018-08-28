// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;


final class isList extends Enum
{

    private static final MAP $VALUES[];
    public static final MAP MAP;
    public static final MAP PACKED_VECTOR;
    public static final MAP SCALAR;
    public static final MAP VECTOR;
    public final boolean isList;

    public static isList[] values()
    {
        return (isList[])$VALUES.clone();
    }

    static 
    {
        SCALAR = new <init>("SCALAR", 0, false);
        VECTOR = new <init>("VECTOR", 1, true);
        PACKED_VECTOR = new <init>("PACKED_VECTOR", 2, true);
        MAP = new <init>("MAP", 3, false);
        $VALUES = (new .VALUES[] {
            SCALAR, VECTOR, PACKED_VECTOR, MAP
        });
    }

    private (String s, int i, boolean flag)
    {
        super(s, i);
        isList = flag;
    }
}
