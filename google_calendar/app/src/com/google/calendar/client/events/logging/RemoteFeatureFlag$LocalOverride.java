// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.client.events.logging;


// Referenced classes of package com.google.calendar.client.events.logging:
//            RemoteFeatureFlag

public static final class value extends Enum
    implements com.google.protobuf.ureFlag.LocalOverride
{

    private static final forNumber $VALUES[];
    public static final forNumber DISABLE;
    public static final forNumber ENABLE;
    private static final forNumber UNSET;
    public static final com.google.protobuf.ureFlag.LocalOverride internalVerifier = new _cls2();
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return UNSET;

        case 1: // '\001'
            return ENABLE;

        case 2: // '\002'
            return DISABLE;
        }
    }

    public static DISABLE[] values()
    {
        return (DISABLE[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNSET = new <init>("UNSET", 0, 0);
        ENABLE = new <init>("ENABLE", 1, 1);
        DISABLE = new <init>("DISABLE", 2, 2);
        $VALUES = (new .VALUES[] {
            UNSET, ENABLE, DISABLE
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return RemoteFeatureFlag.LocalOverride.forNumber(i) != null;
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
