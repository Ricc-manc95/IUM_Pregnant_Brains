// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protos.calendar.feapi.v1;


// Referenced classes of package com.google.protos.calendar.feapi.v1:
//            ReminderInstance

public static final class _cls2 extends Enum
    implements com.google.protobuf.Instance.Method
{

    private static final forNumber $VALUES[];
    public static final forNumber POPUP;
    public static final com.google.protobuf.Instance.Method internalVerifier = new _cls2();
    public final int value = 2;

    public static _cls2 forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

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
        POPUP = new <init>("POPUP", 0, 2);
        $VALUES = (new .VALUES[] {
            POPUP
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return ReminderInstance.Method.forNumber(i) != null;
            }

            _cls2()
            {
            }
        }

    }

    private _cls2(String s, int i, int j)
    {
        super(s, 0);
    }
}
