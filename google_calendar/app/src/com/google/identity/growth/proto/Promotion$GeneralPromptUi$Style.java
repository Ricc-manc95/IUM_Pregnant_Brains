// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.proto;


// Referenced classes of package com.google.identity.growth.proto:
//            Promotion

public static final class value extends Enum
    implements com.google.protobuf.omptUi.Style
{

    private static final forNumber $VALUES[];
    public static final forNumber BLOCKING_BOTTOMSHEET;
    private static final forNumber BLUE_HEADER_DIALOG;
    public static final forNumber MATERIAL_ALERT_DIALOG;
    private static final forNumber NON_BLOCKING_BOTTOMSHEET;
    private static final forNumber RED_HEADER_DIALOG;
    private static final forNumber SYSTEM_DIALOG;
    public static final forNumber UNKNOWN_STYLE;
    public static final com.google.protobuf.omptUi.Style internalVerifier = new _cls2();
    private final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        case 1: // '\001'
        case 7: // '\007'
        default:
            return null;

        case 0: // '\0'
            return UNKNOWN_STYLE;

        case 2: // '\002'
            return RED_HEADER_DIALOG;

        case 3: // '\003'
            return SYSTEM_DIALOG;

        case 4: // '\004'
            return MATERIAL_ALERT_DIALOG;

        case 5: // '\005'
            return BLUE_HEADER_DIALOG;

        case 6: // '\006'
            return BLOCKING_BOTTOMSHEET;

        case 8: // '\b'
            return NON_BLOCKING_BOTTOMSHEET;
        }
    }

    public static NON_BLOCKING_BOTTOMSHEET[] values()
    {
        return (NON_BLOCKING_BOTTOMSHEET[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNKNOWN_STYLE = new <init>("UNKNOWN_STYLE", 0, 0);
        RED_HEADER_DIALOG = new <init>("RED_HEADER_DIALOG", 1, 2);
        SYSTEM_DIALOG = new <init>("SYSTEM_DIALOG", 2, 3);
        MATERIAL_ALERT_DIALOG = new <init>("MATERIAL_ALERT_DIALOG", 3, 4);
        BLUE_HEADER_DIALOG = new <init>("BLUE_HEADER_DIALOG", 4, 5);
        BLOCKING_BOTTOMSHEET = new <init>("BLOCKING_BOTTOMSHEET", 5, 6);
        NON_BLOCKING_BOTTOMSHEET = new <init>("NON_BLOCKING_BOTTOMSHEET", 6, 8);
        $VALUES = (new .VALUES[] {
            UNKNOWN_STYLE, RED_HEADER_DIALOG, SYSTEM_DIALOG, MATERIAL_ALERT_DIALOG, BLUE_HEADER_DIALOG, BLOCKING_BOTTOMSHEET, NON_BLOCKING_BOTTOMSHEET
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return Promotion.GeneralPromptUi.Style.forNumber(i) != null;
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
