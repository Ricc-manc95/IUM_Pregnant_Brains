// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.proto;


// Referenced classes of package com.google.identity.growth.proto:
//            Promotion

public static final class value extends Enum
    implements com.google.protobuf.i.TargetCase
{

    private static final TARGET_NOT_SET $VALUES[];
    public static final TARGET_NOT_SET ELEMENT_NAME;
    public static final TARGET_NOT_SET ELEMENT_TAG;
    private static final TARGET_NOT_SET TARGET_NOT_SET;
    public static final TARGET_NOT_SET VISUAL_ELEMENT_ID;
    private final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 1: // '\001'
            return ELEMENT_NAME;

        case 8: // '\b'
            return ELEMENT_TAG;

        case 9: // '\t'
            return VISUAL_ELEMENT_ID;

        case 0: // '\0'
            return TARGET_NOT_SET;
        }
    }

    public static TARGET_NOT_SET[] values()
    {
        return (TARGET_NOT_SET[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        ELEMENT_NAME = new <init>("ELEMENT_NAME", 0, 1);
        ELEMENT_TAG = new <init>("ELEMENT_TAG", 1, 8);
        VISUAL_ELEMENT_ID = new <init>("VISUAL_ELEMENT_ID", 2, 9);
        TARGET_NOT_SET = new <init>("TARGET_NOT_SET", 3, 0);
        $VALUES = (new .VALUES[] {
            ELEMENT_NAME, ELEMENT_TAG, VISUAL_ELEMENT_ID, TARGET_NOT_SET
        });
    }

    private (String s, int i, int j)
    {
        super(s, i);
        value = j;
    }
}
