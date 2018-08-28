// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.suggest.v2;


// Referenced classes of package com.google.calendar.suggest.v2:
//            OmittedAttendee

public static final class value extends Enum
    implements com.google.protobuf.dee.Reason
{

    private static final _cls2 $VALUES[];
    public static final _cls2 FAILED;
    public static final _cls2 IGNORED;
    public static final _cls2 NOT_FOUND;
    public static final _cls2 UNRECOGNIZED;
    private final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return FAILED;

        case 1: // '\001'
            return IGNORED;

        case 2: // '\002'
            return NOT_FOUND;
        }
    }

    public static NOT_FOUND[] values()
    {
        return (NOT_FOUND[])$VALUES.clone();
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
        FAILED = new <init>("FAILED", 0, 0);
        IGNORED = new <init>("IGNORED", 1, 1);
        NOT_FOUND = new <init>("NOT_FOUND", 2, 2);
        UNRECOGNIZED = new <init>("UNRECOGNIZED", 3, -1);
        $VALUES = (new .VALUES[] {
            FAILED, IGNORED, NOT_FOUND, UNRECOGNIZED
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return OmittedAttendee.Reason.forNumber(i) != null;
            }

            _cls2()
            {
            }
        }

        new _cls2();
    }

    private _cls2(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }
}
