// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.proto;


// Referenced classes of package com.google.identity.growth.proto:
//            Promotion

public static final class value extends Enum
    implements com.google.protobuf.nt.EventCase
{

    private static final EVENT_NOT_SET $VALUES[];
    public static final EVENT_NOT_SET CLEARCUT_EVENT;
    private static final EVENT_NOT_SET EVENT_NOT_SET;
    public static final EVENT_NOT_SET VE_EVENT;
    private final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 1: // '\001'
            return CLEARCUT_EVENT;

        case 2: // '\002'
            return VE_EVENT;

        case 0: // '\0'
            return EVENT_NOT_SET;
        }
    }

    public static EVENT_NOT_SET[] values()
    {
        return (EVENT_NOT_SET[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        CLEARCUT_EVENT = new <init>("CLEARCUT_EVENT", 0, 1);
        VE_EVENT = new <init>("VE_EVENT", 1, 2);
        EVENT_NOT_SET = new <init>("EVENT_NOT_SET", 2, 0);
        $VALUES = (new .VALUES[] {
            CLEARCUT_EVENT, VE_EVENT, EVENT_NOT_SET
        });
    }

    private (String s, int i, int j)
    {
        super(s, i);
        value = j;
    }
}
