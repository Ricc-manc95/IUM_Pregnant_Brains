// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.logging.proto;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.IntArrayList;
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

// Referenced classes of package com.google.identity.growth.logging.proto:
//            Client

public static final class conditionsNotMet_ extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(Client.PromoEvent.DEFAULT_INSTANCE);
        }
    }

    public static final class ConditionsReason extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final ConditionsReason $VALUES[];
        public static final ConditionsReason CONDITION_BATTERY;
        public static final ConditionsReason CONDITION_INSTALLED_APP;
        private static final ConditionsReason CONDITION_KEYBOARD_PRESENT;
        public static final ConditionsReason CONDITION_LOCALE;
        public static final ConditionsReason CONDITION_NETWORK;
        private static final ConditionsReason CONDITION_NETWORK_NOT_READY;
        private static final ConditionsReason CONDITION_REGION;
        public static final ConditionsReason CONDITION_TIME_CONSTRAINT;
        public static final ConditionsReason CONDITION_UNKNOWN;
        private static final ConditionsReason CONDITION_VIEW_NOT_IN_SCREEN;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static ConditionsReason forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return CONDITION_UNKNOWN;

            case 1: // '\001'
                return CONDITION_NETWORK;

            case 2: // '\002'
                return CONDITION_NETWORK_NOT_READY;

            case 3: // '\003'
                return CONDITION_LOCALE;

            case 4: // '\004'
                return CONDITION_REGION;

            case 5: // '\005'
                return CONDITION_BATTERY;

            case 6: // '\006'
                return CONDITION_INSTALLED_APP;

            case 7: // '\007'
                return CONDITION_VIEW_NOT_IN_SCREEN;

            case 8: // '\b'
                return CONDITION_KEYBOARD_PRESENT;

            case 9: // '\t'
                return CONDITION_TIME_CONSTRAINT;
            }
        }

        public static ConditionsReason[] values()
        {
            return (ConditionsReason[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            CONDITION_UNKNOWN = new ConditionsReason("CONDITION_UNKNOWN", 0, 0);
            CONDITION_NETWORK = new ConditionsReason("CONDITION_NETWORK", 1, 1);
            CONDITION_NETWORK_NOT_READY = new ConditionsReason("CONDITION_NETWORK_NOT_READY", 2, 2);
            CONDITION_LOCALE = new ConditionsReason("CONDITION_LOCALE", 3, 3);
            CONDITION_REGION = new ConditionsReason("CONDITION_REGION", 4, 4);
            CONDITION_BATTERY = new ConditionsReason("CONDITION_BATTERY", 5, 5);
            CONDITION_INSTALLED_APP = new ConditionsReason("CONDITION_INSTALLED_APP", 6, 6);
            CONDITION_VIEW_NOT_IN_SCREEN = new ConditionsReason("CONDITION_VIEW_NOT_IN_SCREEN", 7, 7);
            CONDITION_KEYBOARD_PRESENT = new ConditionsReason("CONDITION_KEYBOARD_PRESENT", 8, 8);
            CONDITION_TIME_CONSTRAINT = new ConditionsReason("CONDITION_TIME_CONSTRAINT", 9, 9);
            $VALUES = (new ConditionsReason[] {
                CONDITION_UNKNOWN, CONDITION_NETWORK, CONDITION_NETWORK_NOT_READY, CONDITION_LOCALE, CONDITION_REGION, CONDITION_BATTERY, CONDITION_INSTALLED_APP, CONDITION_VIEW_NOT_IN_SCREEN, CONDITION_KEYBOARD_PRESENT, CONDITION_TIME_CONSTRAINT
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return ConditionsReason.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private ConditionsReason(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class DisplayBlockReason extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final DisplayBlockReason $VALUES[];
        public static final DisplayBlockReason DISPLAY_BLOCK_CLIENT_ERROR;
        public static final DisplayBlockReason DISPLAY_BLOCK_CLIENT_REJECT;
        public static final DisplayBlockReason DISPLAY_BLOCK_LEGACY_USER;
        private static final DisplayBlockReason DISPLAY_BLOCK_NEXT_LAUNCH;
        public static final DisplayBlockReason DISPLAY_BLOCK_TRY_AGAIN_LATER;
        public static final DisplayBlockReason DISPLAY_BLOCK_UNKNOWN;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static DisplayBlockReason forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return DISPLAY_BLOCK_UNKNOWN;

            case 1: // '\001'
                return DISPLAY_BLOCK_CLIENT_REJECT;

            case 2: // '\002'
                return DISPLAY_BLOCK_CLIENT_ERROR;

            case 3: // '\003'
                return DISPLAY_BLOCK_NEXT_LAUNCH;

            case 4: // '\004'
                return DISPLAY_BLOCK_TRY_AGAIN_LATER;

            case 5: // '\005'
                return DISPLAY_BLOCK_LEGACY_USER;
            }
        }

        public static DisplayBlockReason[] values()
        {
            return (DisplayBlockReason[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            DISPLAY_BLOCK_UNKNOWN = new DisplayBlockReason("DISPLAY_BLOCK_UNKNOWN", 0, 0);
            DISPLAY_BLOCK_CLIENT_REJECT = new DisplayBlockReason("DISPLAY_BLOCK_CLIENT_REJECT", 1, 1);
            DISPLAY_BLOCK_CLIENT_ERROR = new DisplayBlockReason("DISPLAY_BLOCK_CLIENT_ERROR", 2, 2);
            DISPLAY_BLOCK_NEXT_LAUNCH = new DisplayBlockReason("DISPLAY_BLOCK_NEXT_LAUNCH", 3, 3);
            DISPLAY_BLOCK_TRY_AGAIN_LATER = new DisplayBlockReason("DISPLAY_BLOCK_TRY_AGAIN_LATER", 4, 4);
            DISPLAY_BLOCK_LEGACY_USER = new DisplayBlockReason("DISPLAY_BLOCK_LEGACY_USER", 5, 5);
            $VALUES = (new DisplayBlockReason[] {
                DISPLAY_BLOCK_UNKNOWN, DISPLAY_BLOCK_CLIENT_REJECT, DISPLAY_BLOCK_CLIENT_ERROR, DISPLAY_BLOCK_NEXT_LAUNCH, DISPLAY_BLOCK_TRY_AGAIN_LATER, DISPLAY_BLOCK_LEGACY_USER
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return DisplayBlockReason.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private DisplayBlockReason(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class PromoNotShownReason extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final PromoNotShownReason $VALUES[];
        public static final PromoNotShownReason PROMO_NOT_SHOWN_CLIENT_BLOCK;
        public static final PromoNotShownReason PROMO_NOT_SHOWN_CONTROL_GROUP;
        private static final PromoNotShownReason PROMO_NOT_SHOWN_INTERNAL_ERROR;
        private static final PromoNotShownReason PROMO_NOT_SHOWN_KEYBOARD_PRESENT;
        private static final PromoNotShownReason PROMO_NOT_SHOWN_UNKNOWN;
        private static final PromoNotShownReason PROMO_NOT_SHOWN_VIEW_NOT_IN_SCREEN;
        private static final PromoNotShownReason PROMO_NOT_SHOWN_VOICE_OVER_ENABLED;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static PromoNotShownReason forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return PROMO_NOT_SHOWN_UNKNOWN;

            case 1: // '\001'
                return PROMO_NOT_SHOWN_INTERNAL_ERROR;

            case 2: // '\002'
                return PROMO_NOT_SHOWN_CLIENT_BLOCK;

            case 3: // '\003'
                return PROMO_NOT_SHOWN_CONTROL_GROUP;

            case 4: // '\004'
                return PROMO_NOT_SHOWN_VIEW_NOT_IN_SCREEN;

            case 5: // '\005'
                return PROMO_NOT_SHOWN_KEYBOARD_PRESENT;

            case 6: // '\006'
                return PROMO_NOT_SHOWN_VOICE_OVER_ENABLED;
            }
        }

        public static PromoNotShownReason[] values()
        {
            return (PromoNotShownReason[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            PROMO_NOT_SHOWN_UNKNOWN = new PromoNotShownReason("PROMO_NOT_SHOWN_UNKNOWN", 0, 0);
            PROMO_NOT_SHOWN_INTERNAL_ERROR = new PromoNotShownReason("PROMO_NOT_SHOWN_INTERNAL_ERROR", 1, 1);
            PROMO_NOT_SHOWN_CLIENT_BLOCK = new PromoNotShownReason("PROMO_NOT_SHOWN_CLIENT_BLOCK", 2, 2);
            PROMO_NOT_SHOWN_CONTROL_GROUP = new PromoNotShownReason("PROMO_NOT_SHOWN_CONTROL_GROUP", 3, 3);
            PROMO_NOT_SHOWN_VIEW_NOT_IN_SCREEN = new PromoNotShownReason("PROMO_NOT_SHOWN_VIEW_NOT_IN_SCREEN", 4, 4);
            PROMO_NOT_SHOWN_KEYBOARD_PRESENT = new PromoNotShownReason("PROMO_NOT_SHOWN_KEYBOARD_PRESENT", 5, 5);
            PROMO_NOT_SHOWN_VOICE_OVER_ENABLED = new PromoNotShownReason("PROMO_NOT_SHOWN_VOICE_OVER_ENABLED", 6, 6);
            $VALUES = (new PromoNotShownReason[] {
                PROMO_NOT_SHOWN_UNKNOWN, PROMO_NOT_SHOWN_INTERNAL_ERROR, PROMO_NOT_SHOWN_CLIENT_BLOCK, PROMO_NOT_SHOWN_CONTROL_GROUP, PROMO_NOT_SHOWN_VIEW_NOT_IN_SCREEN, PROMO_NOT_SHOWN_KEYBOARD_PRESENT, PROMO_NOT_SHOWN_VOICE_OVER_ENABLED
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return PromoNotShownReason.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private PromoNotShownReason(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class UserAction extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final UserAction $VALUES[];
        private static final UserAction ACTION_ACKNOWLEDGE;
        public static final UserAction ACTION_DISMISS;
        public static final UserAction ACTION_NEGATIVE;
        public static final UserAction ACTION_POSITIVE;
        public static final UserAction ACTION_UNKNOWN;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static UserAction forNumber(int i)
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

        public static UserAction[] values()
        {
            return (UserAction[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            ACTION_UNKNOWN = new UserAction("ACTION_UNKNOWN", 0, 0);
            ACTION_POSITIVE = new UserAction("ACTION_POSITIVE", 1, 1);
            ACTION_NEGATIVE = new UserAction("ACTION_NEGATIVE", 2, 2);
            ACTION_DISMISS = new UserAction("ACTION_DISMISS", 3, 3);
            ACTION_ACKNOWLEDGE = new UserAction("ACTION_ACKNOWLEDGE", 4, 4);
            $VALUES = (new UserAction[] {
                ACTION_UNKNOWN, ACTION_POSITIVE, ACTION_NEGATIVE, ACTION_DISMISS, ACTION_ACKNOWLEDGE
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return UserAction.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private UserAction(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }


    public static final  DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public com.google.protobuf.to.Client.PromoEvent conditionsNotMet_;
    public int displayBlockReason_;
    public int impressionCappingId_;
    public com.google.protobuf.nt mendelIds_;
    public int promoNotShownReason_;
    public boolean targetingRuleNotSatisfied_;
    public int triggeringEventIndex_;
    public int userAction_;

    public static UserAction._cls2 parseFrom(InputStream inputstream)
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
        return (e)obj;
    }

    protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
    {
        i - 1;
        JVM INSTR tableswitch 0 6: default 44
    //                   0 229
    //                   1 234
    //                   2 70
    //                   3 52
    //                   4 62
    //                   5 176
    //                   6 180;
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
        obj = ConditionsReason.internalVerifier;
        com.google.protobuf.ier ier = DisplayBlockReason.internalVerifier;
        com.google.protobuf.ier ier1 = UserAction.internalVerifier;
        com.google.protobuf.ier ier2 = PromoNotShownReason.internalVerifier;
        return new RawMessageInfo(DEFAULT_INSTANCE, "\001\b\000\001\001\t\b\000\002\000\001\026\002\004\000\003\004\001\004\007\002\005\036\006\f\004\007\f\005\t\f\003", new Object[] {
            "bitField0_", "mendelIds_", "impressionCappingId_", "triggeringEventIndex_", "targetingRuleNotSatisfied_", "conditionsNotMet_", obj, "displayBlockReason_", ier, "userAction_", 
            ier1, "promoNotShownReason_", ier2
        });
_L7:
        return DEFAULT_INSTANCE;
_L8:
        Parser parser;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L10; else goto _L9
_L9:
        com/google/identity/growth/logging/proto/Client$PromoEvent;
        JVM INSTR monitorenter ;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L12; else goto _L11
_L11:
        obj = new com.google.protobuf.te.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
        PARSER = ((Parser) (obj));
_L12:
        com/google/identity/growth/logging/proto/Client$PromoEvent;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        com/google/identity/growth/logging/proto/Client$PromoEvent;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
        return Byte.valueOf((byte)1);
_L3:
        return null;
    }

    static 
    {
        class _cls1
            implements com.google.protobuf.Internal.ListAdapter.Converter
        {

            public final Object convert(Object obj)
            {
                ConditionsReason conditionsreason = ConditionsReason.forNumber(((Integer)obj).intValue());
                obj = conditionsreason;
                if (conditionsreason == null)
                {
                    obj = ConditionsReason.CONDITION_UNKNOWN;
                }
                return obj;
            }

            _cls1()
            {
            }
        }

        new _cls1();
        DEFAULT_INSTANCE = new <init>();
        _cls1 _lcls1 = DEFAULT_INSTANCE;
        GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/logging/proto/Client$PromoEvent, _lcls1);
    }

    private UserAction.value()
    {
        mendelIds_ = IntArrayList.EMPTY_LIST;
        conditionsNotMet_ = IntArrayList.EMPTY_LIST;
    }
}
