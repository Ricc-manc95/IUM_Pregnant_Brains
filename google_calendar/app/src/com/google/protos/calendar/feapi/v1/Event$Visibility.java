// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protos.calendar.feapi.v1;


// Referenced classes of package com.google.protos.calendar.feapi.v1:
//            Event

public static final class value extends Enum
    implements com.google.protobuf.
{

    private static final forNumber $VALUES[];
    public static final forNumber CONFIDENTIAL;
    public static final forNumber DEFAULT;
    public static final forNumber PRIVATE;
    public static final forNumber PUBLIC;
    public static final forNumber SECRET;
    public static final forNumber SHADOW;
    public static final com.google.protobuf.fier internalVerifier = new _cls2();
    public final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return DEFAULT;

        case 1: // '\001'
            return PUBLIC;

        case 2: // '\002'
            return PRIVATE;

        case 3: // '\003'
            return CONFIDENTIAL;

        case 4: // '\004'
            return SECRET;

        case 5: // '\005'
            return SHADOW;
        }
    }

    public static SHADOW[] values()
    {
        return (SHADOW[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        DEFAULT = new <init>("DEFAULT", 0, 0);
        PUBLIC = new <init>("PUBLIC", 1, 1);
        PRIVATE = new <init>("PRIVATE", 2, 2);
        CONFIDENTIAL = new <init>("CONFIDENTIAL", 3, 3);
        SECRET = new <init>("SECRET", 4, 4);
        SHADOW = new <init>("SHADOW", 5, 5);
        $VALUES = (new .VALUES[] {
            DEFAULT, PUBLIC, PRIVATE, CONFIDENTIAL, SECRET, SHADOW
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return Event.Visibility.forNumber(i) != null;
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
