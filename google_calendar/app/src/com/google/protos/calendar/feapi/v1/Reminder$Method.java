// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protos.calendar.feapi.v1;


// Referenced classes of package com.google.protos.calendar.feapi.v1:
//            Reminder

public static final class value extends Enum
    implements com.google.protobuf.e
{

    private static final forNumber $VALUES[];
    public static final forNumber EMAIL;
    public static final forNumber POPUP;
    public static final forNumber SMS;
    public static final com.google.protobuf.ifier internalVerifier = new _cls2();
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return EMAIL;

        case 1: // '\001'
            return SMS;

        case 2: // '\002'
            return POPUP;
        }
    }

    public static POPUP[] values()
    {
        return (POPUP[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        EMAIL = new <init>("EMAIL", 0, 0);
        SMS = new <init>("SMS", 1, 1);
        POPUP = new <init>("POPUP", 2, 2);
        $VALUES = (new .VALUES[] {
            EMAIL, SMS, POPUP
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return Reminder.Method.forNumber(i) != null;
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
