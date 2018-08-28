// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.proto;


// Referenced classes of package com.google.identity.growth.proto:
//            Promotion

public static final class value extends Enum
    implements com.google.protobuf.omoUi.UiType
{

    private static final forNumber $VALUES[];
    public static final forNumber UITYPE_DIALOG;
    public static final forNumber UITYPE_DO_NOT_DISPLAY;
    private static final forNumber UITYPE_GM_DIALOG;
    private static final forNumber UITYPE_GM_TAP_TARGET;
    private static final forNumber UITYPE_GM_TOOLTIP;
    public static final forNumber UITYPE_NONE;
    private static final forNumber UITYPE_NON_BLOCKING_BOTTOMSHEET;
    private static final forNumber UITYPE_NOTIFICATION;
    private static final forNumber UITYPE_PERMISSION;
    private static final forNumber UITYPE_RATING_DEFAULT;
    public static final forNumber UITYPE_RATING_MATERIAL_DIALOG;
    private static final forNumber UITYPE_RATING_NATIVE_STORE_REVIEW_DIALOG;
    public static final forNumber UITYPE_RATING_PREFERRED_BOTTOMSHEET;
    private static final forNumber UITYPE_RATING_PREFERRED_DIALOG;
    private static final forNumber UITYPE_RATING_SYSTEM_DIALOG;
    public static final forNumber UITYPE_TAP_TARGET;
    public static final forNumber UITYPE_TOOLTIP;
    public static final com.google.protobuf.omoUi.UiType internalVerifier = new _cls2();
    private final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return UITYPE_NONE;

        case 1: // '\001'
            return UITYPE_DO_NOT_DISPLAY;

        case 2: // '\002'
            return UITYPE_RATING_DEFAULT;

        case 3: // '\003'
            return UITYPE_RATING_SYSTEM_DIALOG;

        case 4: // '\004'
            return UITYPE_RATING_MATERIAL_DIALOG;

        case 5: // '\005'
            return UITYPE_RATING_PREFERRED_DIALOG;

        case 6: // '\006'
            return UITYPE_RATING_PREFERRED_BOTTOMSHEET;

        case 7: // '\007'
            return UITYPE_RATING_NATIVE_STORE_REVIEW_DIALOG;

        case 8: // '\b'
            return UITYPE_NON_BLOCKING_BOTTOMSHEET;

        case 12: // '\f'
            return UITYPE_DIALOG;

        case 9: // '\t'
            return UITYPE_TAP_TARGET;

        case 11: // '\013'
            return UITYPE_TOOLTIP;

        case 10: // '\n'
            return UITYPE_NOTIFICATION;

        case 13: // '\r'
            return UITYPE_PERMISSION;

        case 14: // '\016'
            return UITYPE_GM_DIALOG;

        case 15: // '\017'
            return UITYPE_GM_TAP_TARGET;

        case 16: // '\020'
            return UITYPE_GM_TOOLTIP;
        }
    }

    public static UITYPE_GM_TOOLTIP[] values()
    {
        return (UITYPE_GM_TOOLTIP[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UITYPE_NONE = new <init>("UITYPE_NONE", 0, 0);
        UITYPE_DO_NOT_DISPLAY = new <init>("UITYPE_DO_NOT_DISPLAY", 1, 1);
        UITYPE_RATING_DEFAULT = new <init>("UITYPE_RATING_DEFAULT", 2, 2);
        UITYPE_RATING_SYSTEM_DIALOG = new <init>("UITYPE_RATING_SYSTEM_DIALOG", 3, 3);
        UITYPE_RATING_MATERIAL_DIALOG = new <init>("UITYPE_RATING_MATERIAL_DIALOG", 4, 4);
        UITYPE_RATING_PREFERRED_DIALOG = new <init>("UITYPE_RATING_PREFERRED_DIALOG", 5, 5);
        UITYPE_RATING_PREFERRED_BOTTOMSHEET = new <init>("UITYPE_RATING_PREFERRED_BOTTOMSHEET", 6, 6);
        UITYPE_RATING_NATIVE_STORE_REVIEW_DIALOG = new <init>("UITYPE_RATING_NATIVE_STORE_REVIEW_DIALOG", 7, 7);
        UITYPE_NON_BLOCKING_BOTTOMSHEET = new <init>("UITYPE_NON_BLOCKING_BOTTOMSHEET", 8, 8);
        UITYPE_DIALOG = new <init>("UITYPE_DIALOG", 9, 12);
        UITYPE_TAP_TARGET = new <init>("UITYPE_TAP_TARGET", 10, 9);
        UITYPE_TOOLTIP = new <init>("UITYPE_TOOLTIP", 11, 11);
        UITYPE_NOTIFICATION = new <init>("UITYPE_NOTIFICATION", 12, 10);
        UITYPE_PERMISSION = new <init>("UITYPE_PERMISSION", 13, 13);
        UITYPE_GM_DIALOG = new <init>("UITYPE_GM_DIALOG", 14, 14);
        UITYPE_GM_TAP_TARGET = new <init>("UITYPE_GM_TAP_TARGET", 15, 15);
        UITYPE_GM_TOOLTIP = new <init>("UITYPE_GM_TOOLTIP", 16, 16);
        $VALUES = (new .VALUES[] {
            UITYPE_NONE, UITYPE_DO_NOT_DISPLAY, UITYPE_RATING_DEFAULT, UITYPE_RATING_SYSTEM_DIALOG, UITYPE_RATING_MATERIAL_DIALOG, UITYPE_RATING_PREFERRED_DIALOG, UITYPE_RATING_PREFERRED_BOTTOMSHEET, UITYPE_RATING_NATIVE_STORE_REVIEW_DIALOG, UITYPE_NON_BLOCKING_BOTTOMSHEET, UITYPE_DIALOG, 
            UITYPE_TAP_TARGET, UITYPE_TOOLTIP, UITYPE_NOTIFICATION, UITYPE_PERMISSION, UITYPE_GM_DIALOG, UITYPE_GM_TAP_TARGET, UITYPE_GM_TOOLTIP
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return Promotion.PromoUi.UiType.forNumber(i) != null;
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
