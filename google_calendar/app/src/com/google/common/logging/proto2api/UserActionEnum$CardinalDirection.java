// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.logging.proto2api;


public final class value extends Enum
    implements com.google.protobuf.rdinalDirection
{

    private static final forNumber $VALUES[];
    private static final forNumber DOWN;
    private static final forNumber LEFT;
    private static final forNumber RIGHT;
    private static final forNumber UNASSIGNED_DIRECTIONAL_MOVEMENT_ID;
    private static final forNumber UP;
    public static final com.google.protobuf.rdinalDirection internalVerifier = new _cls2();
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return UNASSIGNED_DIRECTIONAL_MOVEMENT_ID;

        case 1: // '\001'
            return LEFT;

        case 2: // '\002'
            return RIGHT;

        case 3: // '\003'
            return UP;

        case 4: // '\004'
            return DOWN;
        }
    }

    public static DOWN[] values()
    {
        return (DOWN[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNASSIGNED_DIRECTIONAL_MOVEMENT_ID = new <init>("UNASSIGNED_DIRECTIONAL_MOVEMENT_ID", 0, 0);
        LEFT = new <init>("LEFT", 1, 1);
        RIGHT = new <init>("RIGHT", 2, 2);
        UP = new <init>("UP", 3, 3);
        DOWN = new <init>("DOWN", 4, 4);
        $VALUES = (new .VALUES[] {
            UNASSIGNED_DIRECTIONAL_MOVEMENT_ID, LEFT, RIGHT, UP, DOWN
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return UserActionEnum.CardinalDirection.forNumber(i) != null;
            }

            _cls2()
            {
            }
        }

    }

    private _cls2(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }
}
