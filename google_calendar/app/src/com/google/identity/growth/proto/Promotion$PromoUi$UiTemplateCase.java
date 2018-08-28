// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.proto;


// Referenced classes of package com.google.identity.growth.proto:
//            Promotion

public static final class value extends Enum
    implements com.google.protobuf.TemplateCase
{

    private static final UITEMPLATE_NOT_SET $VALUES[];
    public static final UITEMPLATE_NOT_SET NOTIFICATION_UI;
    public static final UITEMPLATE_NOT_SET PERMISSION_UI;
    public static final UITEMPLATE_NOT_SET RATING_PROMPT_UI;
    private static final UITEMPLATE_NOT_SET TAP_TARGET_UI;
    public static final UITEMPLATE_NOT_SET TOOLTIP_UI;
    private static final UITEMPLATE_NOT_SET UITEMPLATE_NOT_SET;
    private final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        case 1: // '\001'
        default:
            return null;

        case 2: // '\002'
            return RATING_PROMPT_UI;

        case 3: // '\003'
            return TAP_TARGET_UI;

        case 4: // '\004'
            return NOTIFICATION_UI;

        case 5: // '\005'
            return TOOLTIP_UI;

        case 6: // '\006'
            return PERMISSION_UI;

        case 0: // '\0'
            return UITEMPLATE_NOT_SET;
        }
    }

    public static UITEMPLATE_NOT_SET[] values()
    {
        return (UITEMPLATE_NOT_SET[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        RATING_PROMPT_UI = new <init>("RATING_PROMPT_UI", 0, 2);
        TAP_TARGET_UI = new <init>("TAP_TARGET_UI", 1, 3);
        NOTIFICATION_UI = new <init>("NOTIFICATION_UI", 2, 4);
        TOOLTIP_UI = new <init>("TOOLTIP_UI", 3, 5);
        PERMISSION_UI = new <init>("PERMISSION_UI", 4, 6);
        UITEMPLATE_NOT_SET = new <init>("UITEMPLATE_NOT_SET", 5, 0);
        $VALUES = (new .VALUES[] {
            RATING_PROMPT_UI, TAP_TARGET_UI, NOTIFICATION_UI, TOOLTIP_UI, PERMISSION_UI, UITEMPLATE_NOT_SET
        });
    }

    private (String s, int i, int j)
    {
        super(s, i);
        value = j;
    }
}
