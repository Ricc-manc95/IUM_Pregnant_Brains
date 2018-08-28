// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.hats.protos;


public final class value extends Enum
    implements com.google.protobuf.us
{

    private static final forNumber $VALUES[];
    public static final forNumber COMPLETE_ANSWER;
    public static final forNumber PARTIAL_ANSWER;
    public static final com.google.protobuf.us internalVerifier = new _cls2();
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 1: // '\001'
            return COMPLETE_ANSWER;

        case 2: // '\002'
            return PARTIAL_ANSWER;
        }
    }

    public static PARTIAL_ANSWER[] values()
    {
        return (PARTIAL_ANSWER[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        COMPLETE_ANSWER = new <init>("COMPLETE_ANSWER", 0, 1);
        PARTIAL_ANSWER = new <init>("PARTIAL_ANSWER", 1, 2);
        $VALUES = (new .VALUES[] {
            COMPLETE_ANSWER, PARTIAL_ANSWER
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return HatsSurveyData.ResponseStatus.forNumber(i) != null;
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
