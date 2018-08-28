// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protos.calendar.feapi.v1;


// Referenced classes of package com.google.protos.calendar.feapi.v1:
//            HabitInstanceData

public static final class value extends Enum
    implements com.google.protobuf.anceData.Status
{

    private static final forNumber $VALUES[];
    public static final forNumber ACTIVE;
    public static final forNumber COMPLETE;
    public static final forNumber DEFERRAL_REQUESTED;
    public static final forNumber UNDEFERRABLE;
    public static final forNumber UNKNOWN;
    public static final com.google.protobuf.anceData.Status internalVerifier = new _cls2();
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return UNKNOWN;

        case 1: // '\001'
            return ACTIVE;

        case 2: // '\002'
            return DEFERRAL_REQUESTED;

        case 3: // '\003'
            return COMPLETE;

        case 4: // '\004'
            return UNDEFERRABLE;
        }
    }

    public static UNDEFERRABLE[] values()
    {
        return (UNDEFERRABLE[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNKNOWN = new <init>("UNKNOWN", 0, 0);
        ACTIVE = new <init>("ACTIVE", 1, 1);
        DEFERRAL_REQUESTED = new <init>("DEFERRAL_REQUESTED", 2, 2);
        COMPLETE = new <init>("COMPLETE", 3, 3);
        UNDEFERRABLE = new <init>("UNDEFERRABLE", 4, 4);
        $VALUES = (new .VALUES[] {
            UNKNOWN, ACTIVE, DEFERRAL_REQUESTED, COMPLETE, UNDEFERRABLE
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return HabitInstanceData.Status.forNumber(i) != null;
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
