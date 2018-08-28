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
import com.google.protobuf.ProtobufArrayList;
import com.google.protobuf.RawMessageInfo;
import com.google.protobuf.Schema;
import com.google.protobuf.UninitializedMessageException;
import com.google.type.Color;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

// Referenced classes of package com.google.identity.growth.proto:
//            Promotion

public static final class imageUrl_ extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class Action extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final Action DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int actionType_;
        public int bitField0_;
        public String buttonText_;
        public int targetCase_;
        public Object target_;
        public Color textColor_;

        public static Action parseFrom(InputStream inputstream)
            throws IOException
        {
            Object obj = DEFAULT_INSTANCE;
            if (inputstream == null)
            {
                inputstream = Internal.EMPTY_BYTE_ARRAY;
                inputstream = CodedInputStream.newInstance(inputstream, 0, inputstream.length, false);
            } else
            {
                inputstream = new com.google.protobuf.CodedInputStream.StreamDecoder(inputstream, 4096);
            }
            obj = GeneratedMessageLite.parsePartialFrom(((GeneratedMessageLite) (obj)), inputstream, ExtensionRegistryLite.getEmptyRegistry());
            if (obj != null)
            {
                boolean flag1 = Boolean.TRUE.booleanValue();
                byte byte0 = ((Byte)((GeneratedMessageLite) (obj)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).byteValue();
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
                        int i = android.support.v4.content.ModernAsyncTask.Status.SET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0;
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
            return (Action)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 186
        //                       1 191
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 133
        //                       6 137;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new Action();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            obj = ActionType.internalVerifier;
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\005\001\001\001\b\005\000\000\000\001\f\000\002\b\001\003;\000\006\t\002\b<\0", new Object[] {
                "target_", "targetCase_", "bitField0_", "actionType_", obj, "buttonText_", "textColor_", com/google/identity/growth/proto/Promotion$AndroidIntentTarget
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/proto/Promotion$GeneralPromptUi$Action;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/proto/Promotion$GeneralPromptUi$Action;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/proto/Promotion$GeneralPromptUi$Action;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new Action();
            Action action = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$GeneralPromptUi$Action, action);
        }

        private Action()
        {
            targetCase_ = 0;
            buttonText_ = "";
        }
    }

    public static final class Action.ActionType extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final Action.ActionType $VALUES[];
        public static final Action.ActionType ACTION_ACKNOWLEDGE;
        public static final Action.ActionType ACTION_DISMISS;
        public static final Action.ActionType ACTION_NEGATIVE;
        public static final Action.ActionType ACTION_POSITIVE;
        public static final Action.ActionType ACTION_UNKNOWN;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static Action.ActionType forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return ACTION_UNKNOWN;

            case 1: // '\001'
                return ACTION_POSITIVE;

            case 2: // '\002'
                return ACTION_NEGATIVE;

            case 3: // '\003'
                return ACTION_DISMISS;

            case 4: // '\004'
                return ACTION_ACKNOWLEDGE;
            }
        }

        public static Action.ActionType[] values()
        {
            return (Action.ActionType[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            ACTION_UNKNOWN = new Action.ActionType("ACTION_UNKNOWN", 0, 0);
            ACTION_POSITIVE = new Action.ActionType("ACTION_POSITIVE", 1, 1);
            ACTION_NEGATIVE = new Action.ActionType("ACTION_NEGATIVE", 2, 2);
            ACTION_DISMISS = new Action.ActionType("ACTION_DISMISS", 3, 3);
            ACTION_ACKNOWLEDGE = new Action.ActionType("ACTION_ACKNOWLEDGE", 4, 4);
            $VALUES = (new Action.ActionType[] {
                ACTION_UNKNOWN, ACTION_POSITIVE, ACTION_NEGATIVE, ACTION_DISMISS, ACTION_ACKNOWLEDGE
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return Action.ActionType.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private Action.ActionType(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class Action.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Action.Builder()
        {
            super(Action.DEFAULT_INSTANCE);
        }
    }

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(Promotion.GeneralPromptUi.DEFAULT_INSTANCE);
        }
    }

    public static final class Style extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final Style $VALUES[];
        public static final Style BLOCKING_BOTTOMSHEET;
        private static final Style BLUE_HEADER_DIALOG;
        public static final Style MATERIAL_ALERT_DIALOG;
        private static final Style NON_BLOCKING_BOTTOMSHEET;
        private static final Style RED_HEADER_DIALOG;
        private static final Style SYSTEM_DIALOG;
        public static final Style UNKNOWN_STYLE;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        private final int value;

        public static Style forNumber(int i)
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

        public static Style[] values()
        {
            return (Style[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            UNKNOWN_STYLE = new Style("UNKNOWN_STYLE", 0, 0);
            RED_HEADER_DIALOG = new Style("RED_HEADER_DIALOG", 1, 2);
            SYSTEM_DIALOG = new Style("SYSTEM_DIALOG", 2, 3);
            MATERIAL_ALERT_DIALOG = new Style("MATERIAL_ALERT_DIALOG", 3, 4);
            BLUE_HEADER_DIALOG = new Style("BLUE_HEADER_DIALOG", 4, 5);
            BLOCKING_BOTTOMSHEET = new Style("BLOCKING_BOTTOMSHEET", 5, 6);
            NON_BLOCKING_BOTTOMSHEET = new Style("NON_BLOCKING_BOTTOMSHEET", 6, 8);
            $VALUES = (new Style[] {
                UNKNOWN_STYLE, RED_HEADER_DIALOG, SYSTEM_DIALOG, MATERIAL_ALERT_DIALOG, BLUE_HEADER_DIALOG, BLOCKING_BOTTOMSHEET, NON_BLOCKING_BOTTOMSHEET
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return Style.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private Style(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }


    public static final  DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public String bodyText_;
    public String headlineText_;
    public String imageUrl_;
    public com.google.identity.boq.growth.common.proto.fier promotedApp_;
    public int style_;
    public com.google.protobuf.er userAction_;

    public static Style._cls2 parseFrom(InputStream inputstream)
        throws IOException
    {
        Object obj = DEFAULT_INSTANCE;
        if (inputstream == null)
        {
            inputstream = Internal.EMPTY_BYTE_ARRAY;
            inputstream = CodedInputStream.newInstance(inputstream, 0, inputstream.length, false);
        } else
        {
            inputstream = new com.google.protobuf.oder(inputstream, 4096);
        }
        obj = GeneratedMessageLite.parsePartialFrom(((GeneratedMessageLite) (obj)), inputstream, ExtensionRegistryLite.getEmptyRegistry());
        if (obj != null)
        {
            boolean flag1 = Boolean.TRUE.booleanValue();
            byte byte0 = ((Byte)((GeneratedMessageLite) (obj)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content._MEMOIZED_IS_INITIALIZED._fld9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).byteValue();
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
                    int i = android.support.v4.content._MEMOIZED_IS_INITIALIZED._fld9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0;
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
        return (e)obj;
    }

    protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
    {
        i - 1;
        JVM INSTR tableswitch 0 6: default 44
    //                   0 192
    //                   1 197
    //                   2 70
    //                   3 52
    //                   4 62
    //                   5 139
    //                   6 143;
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
        obj = Style.internalVerifier;
        return new RawMessageInfo(DEFAULT_INSTANCE, "\001\006\000\001\001\007\006\000\001\000\001\b\000\002\b\001\003\033\005\b\006\006\f\t\007\t\007", new Object[] {
            "bitField0_", "headlineText_", "bodyText_", "userAction_", com/google/identity/growth/proto/Promotion$GeneralPromptUi$Action, "imageUrl_", "style_", obj, "promotedApp_"
        });
_L7:
        return DEFAULT_INSTANCE;
_L8:
        Parser parser;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L10; else goto _L9
_L9:
        com/google/identity/growth/proto/Promotion$GeneralPromptUi;
        JVM INSTR monitorenter ;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L12; else goto _L11
_L11:
        obj = new com.google.protobuf.ltInstanceBasedParser(DEFAULT_INSTANCE);
        PARSER = ((Parser) (obj));
_L12:
        com/google/identity/growth/proto/Promotion$GeneralPromptUi;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        com/google/identity/growth/proto/Promotion$GeneralPromptUi;
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
        GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/proto/Promotion$GeneralPromptUi, parser);
    }

    private Style.value()
    {
        headlineText_ = "";
        bodyText_ = "";
        userAction_ = ProtobufArrayList.EMPTY_LIST;
        imageUrl_ = "";
    }
}
