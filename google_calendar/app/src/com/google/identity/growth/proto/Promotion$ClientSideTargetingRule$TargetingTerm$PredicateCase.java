// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.proto;


// Referenced classes of package com.google.identity.growth.proto:
//            Promotion

public static final class value extends Enum
    implements com.google.protobuf.redicateCase
{

    private static final PREDICATE_NOT_SET $VALUES[];
    public static final PREDICATE_NOT_SET APP_STATE;
    public static final PREDICATE_NOT_SET EVENT_COUNT;
    private static final PREDICATE_NOT_SET PERMISSION;
    private static final PREDICATE_NOT_SET PREDICATE_NOT_SET;
    private final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        case 1: // '\001'
        default:
            return null;

        case 2: // '\002'
            return EVENT_COUNT;

        case 3: // '\003'
            return APP_STATE;

        case 4: // '\004'
            return PERMISSION;

        case 0: // '\0'
            return PREDICATE_NOT_SET;
        }
    }

    public static PREDICATE_NOT_SET[] values()
    {
        return (PREDICATE_NOT_SET[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        EVENT_COUNT = new <init>("EVENT_COUNT", 0, 2);
        APP_STATE = new <init>("APP_STATE", 1, 3);
        PERMISSION = new <init>("PERMISSION", 2, 4);
        PREDICATE_NOT_SET = new <init>("PREDICATE_NOT_SET", 3, 0);
        $VALUES = (new .VALUES[] {
            EVENT_COUNT, APP_STATE, PERMISSION, PREDICATE_NOT_SET
        });
    }

    private (String s, int i, int j)
    {
        super(s, i);
        value = j;
    }
}
