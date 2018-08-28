// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protos.calendar.feapi.v1;


// Referenced classes of package com.google.protos.calendar.feapi.v1:
//            Event

public static final class value extends Enum
    implements com.google.protobuf.nt.Transparency
{

    private static final forNumber $VALUES[];
    public static final forNumber OPAQUE;
    public static final forNumber TRANSPARENT;
    public static final com.google.protobuf.er internalVerifier = new _cls2();
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return OPAQUE;

        case 1: // '\001'
            return TRANSPARENT;
        }
    }

    public static TRANSPARENT[] values()
    {
        return (TRANSPARENT[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        OPAQUE = new <init>("OPAQUE", 0, 0);
        TRANSPARENT = new <init>("TRANSPARENT", 1, 1);
        $VALUES = (new .VALUES[] {
            OPAQUE, TRANSPARENT
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return Event.Transparency.forNumber(i) != null;
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
