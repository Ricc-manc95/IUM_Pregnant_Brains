// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.proto;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.Protobuf;
import com.google.protobuf.RawMessageInfo;
import com.google.protobuf.Schema;
import com.google.protobuf.UninitializedMessageException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

// Referenced classes of package com.google.identity.growth.proto:
//            Promotion

public static final class uiTemplateCase_ extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(Promotion.PromoUi.DEFAULT_INSTANCE);
        }
    }

    public static final class UiTemplateCase extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final UiTemplateCase $VALUES[];
        public static final UiTemplateCase NOTIFICATION_UI;
        public static final UiTemplateCase PERMISSION_UI;
        public static final UiTemplateCase RATING_PROMPT_UI;
        private static final UiTemplateCase TAP_TARGET_UI;
        public static final UiTemplateCase TOOLTIP_UI;
        private static final UiTemplateCase UITEMPLATE_NOT_SET;
        private final int value;

        public static UiTemplateCase forNumber(int i)
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

        public static UiTemplateCase[] values()
        {
            return (UiTemplateCase[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            RATING_PROMPT_UI = new UiTemplateCase("RATING_PROMPT_UI", 0, 2);
            TAP_TARGET_UI = new UiTemplateCase("TAP_TARGET_UI", 1, 3);
            NOTIFICATION_UI = new UiTemplateCase("NOTIFICATION_UI", 2, 4);
            TOOLTIP_UI = new UiTemplateCase("TOOLTIP_UI", 3, 5);
            PERMISSION_UI = new UiTemplateCase("PERMISSION_UI", 4, 6);
            UITEMPLATE_NOT_SET = new UiTemplateCase("UITEMPLATE_NOT_SET", 5, 0);
            $VALUES = (new UiTemplateCase[] {
                RATING_PROMPT_UI, TAP_TARGET_UI, NOTIFICATION_UI, TOOLTIP_UI, PERMISSION_UI, UITEMPLATE_NOT_SET
            });
        }

        private UiTemplateCase(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class UiType extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final UiType $VALUES[];
        public static final UiType UITYPE_DIALOG;
        public static final UiType UITYPE_DO_NOT_DISPLAY;
        private static final UiType UITYPE_GM_DIALOG;
        private static final UiType UITYPE_GM_TAP_TARGET;
        private static final UiType UITYPE_GM_TOOLTIP;
        public static final UiType UITYPE_NONE;
        private static final UiType UITYPE_NON_BLOCKING_BOTTOMSHEET;
        private static final UiType UITYPE_NOTIFICATION;
        private static final UiType UITYPE_PERMISSION;
        private static final UiType UITYPE_RATING_DEFAULT;
        public static final UiType UITYPE_RATING_MATERIAL_DIALOG;
        private static final UiType UITYPE_RATING_NATIVE_STORE_REVIEW_DIALOG;
        public static final UiType UITYPE_RATING_PREFERRED_BOTTOMSHEET;
        private static final UiType UITYPE_RATING_PREFERRED_DIALOG;
        private static final UiType UITYPE_RATING_SYSTEM_DIALOG;
        public static final UiType UITYPE_TAP_TARGET;
        public static final UiType UITYPE_TOOLTIP;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        private final int value;

        public static UiType forNumber(int i)
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

        public static UiType[] values()
        {
            return (UiType[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            UITYPE_NONE = new UiType("UITYPE_NONE", 0, 0);
            UITYPE_DO_NOT_DISPLAY = new UiType("UITYPE_DO_NOT_DISPLAY", 1, 1);
            UITYPE_RATING_DEFAULT = new UiType("UITYPE_RATING_DEFAULT", 2, 2);
            UITYPE_RATING_SYSTEM_DIALOG = new UiType("UITYPE_RATING_SYSTEM_DIALOG", 3, 3);
            UITYPE_RATING_MATERIAL_DIALOG = new UiType("UITYPE_RATING_MATERIAL_DIALOG", 4, 4);
            UITYPE_RATING_PREFERRED_DIALOG = new UiType("UITYPE_RATING_PREFERRED_DIALOG", 5, 5);
            UITYPE_RATING_PREFERRED_BOTTOMSHEET = new UiType("UITYPE_RATING_PREFERRED_BOTTOMSHEET", 6, 6);
            UITYPE_RATING_NATIVE_STORE_REVIEW_DIALOG = new UiType("UITYPE_RATING_NATIVE_STORE_REVIEW_DIALOG", 7, 7);
            UITYPE_NON_BLOCKING_BOTTOMSHEET = new UiType("UITYPE_NON_BLOCKING_BOTTOMSHEET", 8, 8);
            UITYPE_DIALOG = new UiType("UITYPE_DIALOG", 9, 12);
            UITYPE_TAP_TARGET = new UiType("UITYPE_TAP_TARGET", 10, 9);
            UITYPE_TOOLTIP = new UiType("UITYPE_TOOLTIP", 11, 11);
            UITYPE_NOTIFICATION = new UiType("UITYPE_NOTIFICATION", 12, 10);
            UITYPE_PERMISSION = new UiType("UITYPE_PERMISSION", 13, 13);
            UITYPE_GM_DIALOG = new UiType("UITYPE_GM_DIALOG", 14, 14);
            UITYPE_GM_TAP_TARGET = new UiType("UITYPE_GM_TAP_TARGET", 15, 15);
            UITYPE_GM_TOOLTIP = new UiType("UITYPE_GM_TOOLTIP", 16, 16);
            $VALUES = (new UiType[] {
                UITYPE_NONE, UITYPE_DO_NOT_DISPLAY, UITYPE_RATING_DEFAULT, UITYPE_RATING_SYSTEM_DIALOG, UITYPE_RATING_MATERIAL_DIALOG, UITYPE_RATING_PREFERRED_DIALOG, UITYPE_RATING_PREFERRED_BOTTOMSHEET, UITYPE_RATING_NATIVE_STORE_REVIEW_DIALOG, UITYPE_NON_BLOCKING_BOTTOMSHEET, UITYPE_DIALOG, 
                UITYPE_TAP_TARGET, UITYPE_TOOLTIP, UITYPE_NOTIFICATION, UITYPE_PERMISSION, UITYPE_GM_DIALOG, UITYPE_GM_TAP_TARGET, UITYPE_GM_TOOLTIP
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return UiType.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private UiType(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }


    public static final tanceMap DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public int uiTemplateCase_;
    public Object uiTemplate_;
    public int uiType_;

    public static UiType._cls2 parseFrom(InputStream inputstream)
        throws IOException
    {
        Object obj = DEFAULT_INSTANCE;
        if (inputstream == null)
        {
            inputstream = Internal.EMPTY_BYTE_ARRAY;
            inputstream = CodedInputStream.newInstance(inputstream, 0, inputstream.length, false);
        } else
        {
            inputstream = new com.google.protobuf.treamDecoder(inputstream, 4096);
        }
        obj = GeneratedMessageLite.parsePartialFrom(((GeneratedMessageLite) (obj)), inputstream, ExtensionRegistryLite.getEmptyRegistry());
        if (obj != null)
        {
            boolean flag1 = Boolean.TRUE.booleanValue();
            byte byte0 = ((Byte)((GeneratedMessageLite) (obj)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.atus.GET_MEMOIZED_IS_INITIALIZED._fld9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).byteValue();
            boolean flag;
            if (byte0 == 1)
            {
                flag = true;
            } else
            if (byte0 == 0)
            {
                flag = false;
            } else
            {
                flag = Protobuf.INSTANCE.schemaFor(obj.getClass()).isInitialized(obj);
                if (flag1)
                {
                    int i = android.support.v4.content.atus.SET_MEMOIZED_IS_INITIALIZED._fld9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0;
                    if (flag)
                    {
                        inputstream = ((InputStream) (obj));
                    } else
                    {
                        inputstream = null;
                    }
                    ((GeneratedMessageLite) (obj)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(i, inputstream);
                }
            }
            if (!flag)
            {
                inputstream = new InvalidProtocolBufferException((new UninitializedMessageException()).getMessage());
                if (inputstream == null)
                {
                    throw null;
                } else
                {
                    throw inputstream;
                }
            }
        }
        return (etMessage)obj;
    }

    protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
    {
        i - 1;
        JVM INSTR tableswitch 0 6: default 44
    //                   0 198
    //                   1 203
    //                   2 70
    //                   3 52
    //                   4 62
    //                   5 145
    //                   6 149;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        throw new UnsupportedOperationException();
_L5:
        obj = new <init>();
_L10:
        return obj;
_L6:
        return new Builder();
_L4:
        obj = UiType.internalVerifier;
        return new RawMessageInfo(DEFAULT_INSTANCE, "\001\006\001\001\001\006\006\000\000\000\001\f\000\002<\000\003<\000\004<\000\005<\000\006<\0", new Object[] {
            "uiTemplate_", "uiTemplateCase_", "bitField0_", "uiType_", obj, com/google/identity/growth/proto/Promotion$GeneralPromptUi, com/google/identity/growth/proto/Promotion$TapTargetUi, com/google/identity/growth/proto/Promotion$AndroidNotificationUi, com/google/identity/growth/proto/Promotion$TooltipUi, com/google/identity/growth/proto/Promotion$PermissionPromptUi
        });
_L7:
        return DEFAULT_INSTANCE;
_L8:
        Parser parser;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L10; else goto _L9
_L9:
        com/google/identity/growth/proto/Promotion$PromoUi;
        JVM INSTR monitorenter ;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L12; else goto _L11
_L11:
        obj = new com.google.protobuf.te.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
        PARSER = ((Parser) (obj));
_L12:
        com/google/identity/growth/proto/Promotion$PromoUi;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        com/google/identity/growth/proto/Promotion$PromoUi;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
        return Byte.valueOf((byte)1);
_L3:
        return null;
    }

    static 
    {
        DEFAULT_INSTANCE = new <init>();
        PARSER parser = DEFAULT_INSTANCE;
        GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$PromoUi, parser);
    }

    private UiType.value()
    {
        uiTemplateCase_ = 0;
    }
}
