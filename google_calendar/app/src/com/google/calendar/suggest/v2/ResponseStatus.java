// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.suggest.v2;


public final class ResponseStatus extends Enum
    implements com.google.protobuf.Internal.EnumLite
{

    private static final ResponseStatus $VALUES[];
    public static final ResponseStatus ACCEPTED;
    public static final ResponseStatus DECLINED;
    public static final ResponseStatus NEEDS_ACTION;
    public static final ResponseStatus TENTATIVE;
    public static final ResponseStatus UNRECOGNIZED;
    public final int value;

    private ResponseStatus(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }

    public static ResponseStatus forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return NEEDS_ACTION;

        case 1: // '\001'
            return DECLINED;

        case 2: // '\002'
            return TENTATIVE;

        case 3: // '\003'
            return ACCEPTED;
        }
    }

    public static ResponseStatus[] values()
    {
        return (ResponseStatus[])$VALUES.clone();
    }

    public final int getNumber()
    {
        if (this == UNRECOGNIZED)
        {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        } else
        {
            return value;
        }
    }

    static 
    {
        NEEDS_ACTION = new ResponseStatus("NEEDS_ACTION", 0, 0);
        DECLINED = new ResponseStatus("DECLINED", 1, 1);
        TENTATIVE = new ResponseStatus("TENTATIVE", 2, 2);
        ACCEPTED = new ResponseStatus("ACCEPTED", 3, 3);
        UNRECOGNIZED = new ResponseStatus("UNRECOGNIZED", 4, -1);
        $VALUES = (new ResponseStatus[] {
            NEEDS_ACTION, DECLINED, TENTATIVE, ACCEPTED, UNRECOGNIZED
        });
        new _cls2();
    }

    private class _cls2
        implements com.google.protobuf.Internal.EnumVerifier
    {

        public final boolean isInRange(int i)
        {
            return ResponseStatus.forNumber(i) != null;
        }

        _cls2()
        {
        }
    }

}
