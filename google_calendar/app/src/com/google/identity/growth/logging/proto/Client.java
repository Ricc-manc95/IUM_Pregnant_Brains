// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.logging.proto;

import com.google.protobuf.ByteString;
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

public final class Client
{
    public static final class GrowthMetricsLog extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final GrowthMetricsLog DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public String appName_;
        public int bitField0_;
        public ByteString encodedAdditionalData_;
        public int growthkitVersion_;
        public String language_;
        public PromoEvent promoEvent_;

        public static GrowthMetricsLog parseFrom(InputStream inputstream)
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
            return (GrowthMetricsLog)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 171
        //                       1 176
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 118
        //                       6 122;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new GrowthMetricsLog();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\005\000\001\001\031\005\000\000\000\001\b\000\022\004\002\024\t\026\030\b\003\031\n\004", new Object[] {
                "bitField0_", "appName_", "growthkitVersion_", "promoEvent_", "language_", "encodedAdditionalData_"
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/identity/growth/logging/proto/Client$GrowthMetricsLog;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/identity/growth/logging/proto/Client$GrowthMetricsLog;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/identity/growth/logging/proto/Client$GrowthMetricsLog;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new GrowthMetricsLog();
            GrowthMetricsLog growthmetricslog = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/logging/proto/Client$GrowthMetricsLog, growthmetricslog);
        }

        private GrowthMetricsLog()
        {
            appName_ = "";
            language_ = "";
            encodedAdditionalData_ = ByteString.EMPTY;
        }
    }

    public static final class GrowthMetricsLog.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        GrowthMetricsLog.Builder()
        {
            super(GrowthMetricsLog.DEFAULT_INSTANCE);
        }
    }

    public static final class GrowthMetricsLog.EventCodeType extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final GrowthMetricsLog.EventCodeType $VALUES[];
        private static final GrowthMetricsLog.EventCodeType ABOUT_SHOWN;
        private static final GrowthMetricsLog.EventCodeType ACCESSIBILITY_SETTING_CHANGED;
        private static final GrowthMetricsLog.EventCodeType ANDROID_APP_SET_AS_DEFAULT;
        private static final GrowthMetricsLog.EventCodeType ANDROID_ONBOARDING_EVENT;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_FAB_SHOWN;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_FAB_TAPPED;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_GROWTHKIT_DISABLED;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_OPEN_APP_DEFAULT_HANDLER;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_OPEN_APP_STORE;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_OPEN_EXTERNAL_APP;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_OPEN_EXTERNAL_APP_UNPROMPTED;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_OPEN_RECOMMENDED_APP;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_OPEN_RECOMMENDED_APP_UNPROMPTED;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_OPEN_SYSTEM_APP;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_OPEN_SYSTEM_APP_UNPROMPTED;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_PREFS_DISMISSED;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_PREFS_FEATURED_OPEN_APP_STORE;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_PREFS_INTENTS_OPEN_APP_STORE;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_PREFS_PREFERRED_APP_SET;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_PREFS_PROMPT_PREFERRED_APP_OFF;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_PREFS_PROMPT_PREFERRED_APP_ON;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_PREFS_RECOMMENDED_OPEN_APP_STORE;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_PREFS_SHOWN;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_RESET_TO_SYSTEM_APPS_CANCELLED;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_RESET_TO_SYSTEM_APPS_COMPLETED;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_RESET_TO_SYSTEM_APPS_PROMPTED;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_SHEET_DISMISSED;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_SHEET_PROMPT_PREFERRED_APP_OFF;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_SHEET_PROMPT_PREFERRED_APP_ON;
        private static final GrowthMetricsLog.EventCodeType APP_SWITCH_SHEET_SHOWN;
        private static final GrowthMetricsLog.EventCodeType CHANGE_USER_COMPLETE;
        private static final GrowthMetricsLog.EventCodeType CHANGE_USER_START;
        private static final GrowthMetricsLog.EventCodeType CLIENT_VIEW_DID_APPEAR;
        private static final GrowthMetricsLog.EventCodeType COLD_START;
        private static final GrowthMetricsLog.EventCodeType CREATE_USER_COMPLETE;
        private static final GrowthMetricsLog.EventCodeType CREATE_USER_START;
        private static final GrowthMetricsLog.EventCodeType DSEP_ACTED_ON;
        private static final GrowthMetricsLog.EventCodeType DSEP_DISMISSED;
        private static final GrowthMetricsLog.EventCodeType DSEP_SHOWN;
        private static final GrowthMetricsLog.EventCodeType ENTER_BACKGROUND;
        private static final GrowthMetricsLog.EventCodeType ENTER_FOREGROUND;
        private static final GrowthMetricsLog.EventCodeType ERROR;
        private static final GrowthMetricsLog.EventCodeType FEEDBACK_COMPLETED;
        private static final GrowthMetricsLog.EventCodeType FEEDBACK_DISMISSED;
        private static final GrowthMetricsLog.EventCodeType FEEDBACK_REQUESTED;
        private static final GrowthMetricsLog.EventCodeType FIRST_USE;
        private static final GrowthMetricsLog.EventCodeType GOOGLE_ACTIONS_BUTTON_SHOWN;
        private static final GrowthMetricsLog.EventCodeType GOOGLE_ACTIONS_BUTTON_TAPPED;
        private static final GrowthMetricsLog.EventCodeType GOOGLE_ACTIONS_INSTALL_PROMPT_DISMISSED;
        private static final GrowthMetricsLog.EventCodeType GOOGLE_ACTIONS_INSTALL_PROMPT_SHOWN;
        private static final GrowthMetricsLog.EventCodeType GOOGLE_ACTIONS_OPEN_APP_STORE;
        private static final GrowthMetricsLog.EventCodeType GOOGLE_ACTIONS_OPEN_BROWSER;
        private static final GrowthMetricsLog.EventCodeType GOOGLE_ACTIONS_OPEN_RECOMMENDED_APP_UNPROMPTED;
        private static final GrowthMetricsLog.EventCodeType GOOGLE_ACTIONS_OPTIONS_DISMISSED;
        private static final GrowthMetricsLog.EventCodeType GOOGLE_ACTIONS_OPTIONS_SHOWN;
        private static final GrowthMetricsLog.EventCodeType GOOGLE_ACTIONS_OPTION_SELECTED;
        private static final GrowthMetricsLog.EventCodeType GOOGLE_ACTIONS_USER_UNSUPPORTED_COUNTRY;
        private static final GrowthMetricsLog.EventCodeType GOOGLE_APPS_CURRENTLY_INSTALLED;
        private static final GrowthMetricsLog.EventCodeType GOOGLE_APP_INSTALLED;
        private static final GrowthMetricsLog.EventCodeType GOOGLE_USAGE_ID_RESET_CANCELLED;
        private static final GrowthMetricsLog.EventCodeType GOOGLE_USAGE_ID_RESET_COMPLETED;
        private static final GrowthMetricsLog.EventCodeType GOOGLE_USAGE_ID_RESET_PROMPTED;
        private static final GrowthMetricsLog.EventCodeType HELP_SHOWN;
        private static final GrowthMetricsLog.EventCodeType INTENT_CREATED;
        private static final GrowthMetricsLog.EventCodeType INTENT_EVALUATED;
        private static final GrowthMetricsLog.EventCodeType MENU_SHOWN;
        private static final GrowthMetricsLog.EventCodeType OPEN_URL;
        private static final GrowthMetricsLog.EventCodeType PRIVACY_POLICY_SHOWN;
        public static final GrowthMetricsLog.EventCodeType PROMO_CONDITIONS_EVALUATED;
        public static final GrowthMetricsLog.EventCodeType PROMO_NOT_SHOWN;
        public static final GrowthMetricsLog.EventCodeType PROMO_NOT_SHOWN_DEVICE_CAPPED;
        public static final GrowthMetricsLog.EventCodeType PROMO_SHOWN;
        private static final GrowthMetricsLog.EventCodeType PROMO_SYSTEM_DISMISSED;
        public static final GrowthMetricsLog.EventCodeType PROMO_TARGETING_EVALUATED;
        public static final GrowthMetricsLog.EventCodeType PROMO_TRIGGERED;
        public static final GrowthMetricsLog.EventCodeType PROMO_USER_ACTION;
        public static final GrowthMetricsLog.EventCodeType PROMO_USER_DISMISSED;
        private static final GrowthMetricsLog.EventCodeType REENGAGEMENT_BEGIN_APP_ENGAGEMENT;
        private static final GrowthMetricsLog.EventCodeType REENGAGEMENT_END_APP_ENGAGEMENT;
        private static final GrowthMetricsLog.EventCodeType REENGAGEMENT_NOTIFICATION_PREFERENCES_DISMISSED;
        private static final GrowthMetricsLog.EventCodeType REENGAGEMENT_NOTIFICATION_PREFERENCES_SELECTED;
        private static final GrowthMetricsLog.EventCodeType REENGAGEMENT_NOTIFICATION_PREFERENCES_SHOWN;
        private static final GrowthMetricsLog.EventCodeType REENGAGEMENT_SETTINGS_DISMISSED;
        private static final GrowthMetricsLog.EventCodeType REENGAGEMENT_SETTINGS_MODIFIED;
        private static final GrowthMetricsLog.EventCodeType REENGAGEMENT_SETTINGS_SHOWN;
        private static final GrowthMetricsLog.EventCodeType REENGAGEMENT_USER_RECEIVED_NOTIFICATION;
        private static final GrowthMetricsLog.EventCodeType REENGAGEMENT_USER_TAPPED_ON_NOTIFICATION;
        private static final GrowthMetricsLog.EventCodeType SCREEN_UPDATED;
        private static final GrowthMetricsLog.EventCodeType SETTINGS_MODIFIED;
        private static final GrowthMetricsLog.EventCodeType SETTINGS_SHOWN;
        private static final GrowthMetricsLog.EventCodeType SIGN_IN_COMPLETE;
        private static final GrowthMetricsLog.EventCodeType SIGN_IN_SKIPPED;
        private static final GrowthMetricsLog.EventCodeType SIGN_IN_START;
        private static final GrowthMetricsLog.EventCodeType SIGN_OUT;
        private static final GrowthMetricsLog.EventCodeType SMART_MAIL_CALENDAR_BUTTON_SHOWN;
        private static final GrowthMetricsLog.EventCodeType SMART_MAIL_CALENDAR_BUTTON_TAPPED;
        private static final GrowthMetricsLog.EventCodeType TRANSLATED_TEXT;
        private static final GrowthMetricsLog.EventCodeType UNKNOWN;
        private static final GrowthMetricsLog.EventCodeType USER_ACTION;
        private static final GrowthMetricsLog.EventCodeType USER_AUTHORIZED_PERMISSION;
        private static final GrowthMetricsLog.EventCodeType USER_DENIED_PERMISSION;
        private static final GrowthMetricsLog.EventCodeType VOICE_INTERACTION_COMPLETED;
        private static final GrowthMetricsLog.EventCodeType VOICE_INTERACTION_DISMISSED;
        private static final GrowthMetricsLog.EventCodeType VOICE_INTERACTION_STARTED;
        public final int value;

        public static GrowthMetricsLog.EventCodeType forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return UNKNOWN;

            case 1: // '\001'
                return ABOUT_SHOWN;

            case 2: // '\002'
                return ACCESSIBILITY_SETTING_CHANGED;

            case 3: // '\003'
                return ENTER_FOREGROUND;

            case 4: // '\004'
                return ENTER_BACKGROUND;

            case 73: // 'I'
                return ERROR;

            case 5: // '\005'
                return SCREEN_UPDATED;

            case 6: // '\006'
                return USER_ACTION;

            case 7: // '\007'
                return COLD_START;

            case 8: // '\b'
                return FIRST_USE;

            case 83: // 'S'
                return CLIENT_VIEW_DID_APPEAR;

            case 9: // '\t'
                return APP_SWITCH_SHEET_SHOWN;

            case 10: // '\n'
                return APP_SWITCH_SHEET_DISMISSED;

            case 11: // '\013'
                return APP_SWITCH_OPEN_APP_STORE;

            case 12: // '\f'
                return APP_SWITCH_OPEN_RECOMMENDED_APP;

            case 13: // '\r'
                return APP_SWITCH_OPEN_SYSTEM_APP;

            case 62: // '>'
                return APP_SWITCH_OPEN_EXTERNAL_APP;

            case 64: // '@'
                return APP_SWITCH_OPEN_APP_DEFAULT_HANDLER;

            case 55: // '7'
                return APP_SWITCH_OPEN_RECOMMENDED_APP_UNPROMPTED;

            case 56: // '8'
                return APP_SWITCH_OPEN_SYSTEM_APP_UNPROMPTED;

            case 63: // '?'
                return APP_SWITCH_OPEN_EXTERNAL_APP_UNPROMPTED;

            case 14: // '\016'
                return APP_SWITCH_PREFS_SHOWN;

            case 15: // '\017'
                return APP_SWITCH_PREFS_DISMISSED;

            case 16: // '\020'
                return APP_SWITCH_PREFS_PREFERRED_APP_SET;

            case 17: // '\021'
                return APP_SWITCH_PREFS_PROMPT_PREFERRED_APP_ON;

            case 18: // '\022'
                return APP_SWITCH_PREFS_PROMPT_PREFERRED_APP_OFF;

            case 98: // 'b'
                return APP_SWITCH_SHEET_PROMPT_PREFERRED_APP_ON;

            case 99: // 'c'
                return APP_SWITCH_SHEET_PROMPT_PREFERRED_APP_OFF;

            case 52: // '4'
                return APP_SWITCH_PREFS_RECOMMENDED_OPEN_APP_STORE;

            case 53: // '5'
                return APP_SWITCH_PREFS_FEATURED_OPEN_APP_STORE;

            case 54: // '6'
                return APP_SWITCH_PREFS_INTENTS_OPEN_APP_STORE;

            case 45: // '-'
                return APP_SWITCH_RESET_TO_SYSTEM_APPS_PROMPTED;

            case 46: // '.'
                return APP_SWITCH_RESET_TO_SYSTEM_APPS_CANCELLED;

            case 47: // '/'
                return APP_SWITCH_RESET_TO_SYSTEM_APPS_COMPLETED;

            case 80: // 'P'
                return APP_SWITCH_GROWTHKIT_DISABLED;

            case 81: // 'Q'
                return APP_SWITCH_FAB_SHOWN;

            case 82: // 'R'
                return APP_SWITCH_FAB_TAPPED;

            case 19: // '\023'
                return DSEP_SHOWN;

            case 20: // '\024'
                return DSEP_DISMISSED;

            case 21: // '\025'
                return DSEP_ACTED_ON;

            case 22: // '\026'
                return FEEDBACK_REQUESTED;

            case 23: // '\027'
                return FEEDBACK_DISMISSED;

            case 24: // '\030'
                return FEEDBACK_COMPLETED;

            case 57: // '9'
                return GOOGLE_ACTIONS_BUTTON_SHOWN;

            case 58: // ':'
                return GOOGLE_ACTIONS_BUTTON_TAPPED;

            case 59: // ';'
                return GOOGLE_ACTIONS_OPTIONS_SHOWN;

            case 60: // '<'
                return GOOGLE_ACTIONS_OPTION_SELECTED;

            case 61: // '='
                return GOOGLE_ACTIONS_OPTIONS_DISMISSED;

            case 74: // 'J'
                return GOOGLE_ACTIONS_USER_UNSUPPORTED_COUNTRY;

            case 75: // 'K'
                return GOOGLE_ACTIONS_INSTALL_PROMPT_SHOWN;

            case 76: // 'L'
                return GOOGLE_ACTIONS_OPEN_APP_STORE;

            case 77: // 'M'
                return GOOGLE_ACTIONS_OPEN_BROWSER;

            case 78: // 'N'
                return GOOGLE_ACTIONS_INSTALL_PROMPT_DISMISSED;

            case 79: // 'O'
                return GOOGLE_ACTIONS_OPEN_RECOMMENDED_APP_UNPROMPTED;

            case 25: // '\031'
                return GOOGLE_APP_INSTALLED;

            case 26: // '\032'
                return GOOGLE_APPS_CURRENTLY_INSTALLED;

            case 44: // ','
                return GOOGLE_USAGE_ID_RESET_PROMPTED;

            case 48: // '0'
                return GOOGLE_USAGE_ID_RESET_CANCELLED;

            case 49: // '1'
                return GOOGLE_USAGE_ID_RESET_COMPLETED;

            case 27: // '\033'
                return HELP_SHOWN;

            case 50: // '2'
                return INTENT_CREATED;

            case 51: // '3'
                return INTENT_EVALUATED;

            case 28: // '\034'
                return MENU_SHOWN;

            case 29: // '\035'
                return OPEN_URL;

            case 30: // '\036'
                return PRIVACY_POLICY_SHOWN;

            case 66: // 'B'
                return PROMO_TRIGGERED;

            case 95: // '_'
                return PROMO_TARGETING_EVALUATED;

            case 72: // 'H'
                return PROMO_CONDITIONS_EVALUATED;

            case 94: // '^'
                return PROMO_NOT_SHOWN_DEVICE_CAPPED;

            case 67: // 'C'
                return PROMO_NOT_SHOWN;

            case 68: // 'D'
                return PROMO_SHOWN;

            case 69: // 'E'
                return PROMO_USER_ACTION;

            case 70: // 'F'
                return PROMO_USER_DISMISSED;

            case 71: // 'G'
                return PROMO_SYSTEM_DISMISSED;

            case 84: // 'T'
                return REENGAGEMENT_SETTINGS_SHOWN;

            case 85: // 'U'
                return REENGAGEMENT_SETTINGS_DISMISSED;

            case 86: // 'V'
                return REENGAGEMENT_SETTINGS_MODIFIED;

            case 87: // 'W'
                return REENGAGEMENT_NOTIFICATION_PREFERENCES_SHOWN;

            case 88: // 'X'
                return REENGAGEMENT_NOTIFICATION_PREFERENCES_SELECTED;

            case 89: // 'Y'
                return REENGAGEMENT_NOTIFICATION_PREFERENCES_DISMISSED;

            case 90: // 'Z'
                return REENGAGEMENT_USER_TAPPED_ON_NOTIFICATION;

            case 91: // '['
                return REENGAGEMENT_USER_RECEIVED_NOTIFICATION;

            case 96: // '`'
                return REENGAGEMENT_BEGIN_APP_ENGAGEMENT;

            case 97: // 'a'
                return REENGAGEMENT_END_APP_ENGAGEMENT;

            case 31: // '\037'
                return SETTINGS_SHOWN;

            case 32: // ' '
                return SETTINGS_MODIFIED;

            case 33: // '!'
                return SIGN_IN_START;

            case 34: // '"'
                return SIGN_IN_COMPLETE;

            case 35: // '#'
                return SIGN_OUT;

            case 36: // '$'
                return CHANGE_USER_START;

            case 37: // '%'
                return CHANGE_USER_COMPLETE;

            case 38: // '&'
                return CREATE_USER_START;

            case 39: // '\''
                return CREATE_USER_COMPLETE;

            case 40: // '('
                return SIGN_IN_SKIPPED;

            case 93: // ']'
                return SMART_MAIL_CALENDAR_BUTTON_SHOWN;

            case 92: // '\\'
                return SMART_MAIL_CALENDAR_BUTTON_TAPPED;

            case 65: // 'A'
                return TRANSLATED_TEXT;

            case 100: // 'd'
                return USER_AUTHORIZED_PERMISSION;

            case 101: // 'e'
                return USER_DENIED_PERMISSION;

            case 41: // ')'
                return VOICE_INTERACTION_STARTED;

            case 42: // '*'
                return VOICE_INTERACTION_DISMISSED;

            case 43: // '+'
                return VOICE_INTERACTION_COMPLETED;

            case 102: // 'f'
                return ANDROID_APP_SET_AS_DEFAULT;

            case 103: // 'g'
                return ANDROID_ONBOARDING_EVENT;
            }
        }

        public static GrowthMetricsLog.EventCodeType[] values()
        {
            return (GrowthMetricsLog.EventCodeType[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            UNKNOWN = new GrowthMetricsLog.EventCodeType("UNKNOWN", 0, 0);
            ABOUT_SHOWN = new GrowthMetricsLog.EventCodeType("ABOUT_SHOWN", 1, 1);
            ACCESSIBILITY_SETTING_CHANGED = new GrowthMetricsLog.EventCodeType("ACCESSIBILITY_SETTING_CHANGED", 2, 2);
            ENTER_FOREGROUND = new GrowthMetricsLog.EventCodeType("ENTER_FOREGROUND", 3, 3);
            ENTER_BACKGROUND = new GrowthMetricsLog.EventCodeType("ENTER_BACKGROUND", 4, 4);
            ERROR = new GrowthMetricsLog.EventCodeType("ERROR", 5, 73);
            SCREEN_UPDATED = new GrowthMetricsLog.EventCodeType("SCREEN_UPDATED", 6, 5);
            USER_ACTION = new GrowthMetricsLog.EventCodeType("USER_ACTION", 7, 6);
            COLD_START = new GrowthMetricsLog.EventCodeType("COLD_START", 8, 7);
            FIRST_USE = new GrowthMetricsLog.EventCodeType("FIRST_USE", 9, 8);
            CLIENT_VIEW_DID_APPEAR = new GrowthMetricsLog.EventCodeType("CLIENT_VIEW_DID_APPEAR", 10, 83);
            APP_SWITCH_SHEET_SHOWN = new GrowthMetricsLog.EventCodeType("APP_SWITCH_SHEET_SHOWN", 11, 9);
            APP_SWITCH_SHEET_DISMISSED = new GrowthMetricsLog.EventCodeType("APP_SWITCH_SHEET_DISMISSED", 12, 10);
            APP_SWITCH_OPEN_APP_STORE = new GrowthMetricsLog.EventCodeType("APP_SWITCH_OPEN_APP_STORE", 13, 11);
            APP_SWITCH_OPEN_RECOMMENDED_APP = new GrowthMetricsLog.EventCodeType("APP_SWITCH_OPEN_RECOMMENDED_APP", 14, 12);
            APP_SWITCH_OPEN_SYSTEM_APP = new GrowthMetricsLog.EventCodeType("APP_SWITCH_OPEN_SYSTEM_APP", 15, 13);
            APP_SWITCH_OPEN_EXTERNAL_APP = new GrowthMetricsLog.EventCodeType("APP_SWITCH_OPEN_EXTERNAL_APP", 16, 62);
            APP_SWITCH_OPEN_APP_DEFAULT_HANDLER = new GrowthMetricsLog.EventCodeType("APP_SWITCH_OPEN_APP_DEFAULT_HANDLER", 17, 64);
            APP_SWITCH_OPEN_RECOMMENDED_APP_UNPROMPTED = new GrowthMetricsLog.EventCodeType("APP_SWITCH_OPEN_RECOMMENDED_APP_UNPROMPTED", 18, 55);
            APP_SWITCH_OPEN_SYSTEM_APP_UNPROMPTED = new GrowthMetricsLog.EventCodeType("APP_SWITCH_OPEN_SYSTEM_APP_UNPROMPTED", 19, 56);
            APP_SWITCH_OPEN_EXTERNAL_APP_UNPROMPTED = new GrowthMetricsLog.EventCodeType("APP_SWITCH_OPEN_EXTERNAL_APP_UNPROMPTED", 20, 63);
            APP_SWITCH_PREFS_SHOWN = new GrowthMetricsLog.EventCodeType("APP_SWITCH_PREFS_SHOWN", 21, 14);
            APP_SWITCH_PREFS_DISMISSED = new GrowthMetricsLog.EventCodeType("APP_SWITCH_PREFS_DISMISSED", 22, 15);
            APP_SWITCH_PREFS_PREFERRED_APP_SET = new GrowthMetricsLog.EventCodeType("APP_SWITCH_PREFS_PREFERRED_APP_SET", 23, 16);
            APP_SWITCH_PREFS_PROMPT_PREFERRED_APP_ON = new GrowthMetricsLog.EventCodeType("APP_SWITCH_PREFS_PROMPT_PREFERRED_APP_ON", 24, 17);
            APP_SWITCH_PREFS_PROMPT_PREFERRED_APP_OFF = new GrowthMetricsLog.EventCodeType("APP_SWITCH_PREFS_PROMPT_PREFERRED_APP_OFF", 25, 18);
            APP_SWITCH_SHEET_PROMPT_PREFERRED_APP_ON = new GrowthMetricsLog.EventCodeType("APP_SWITCH_SHEET_PROMPT_PREFERRED_APP_ON", 26, 98);
            APP_SWITCH_SHEET_PROMPT_PREFERRED_APP_OFF = new GrowthMetricsLog.EventCodeType("APP_SWITCH_SHEET_PROMPT_PREFERRED_APP_OFF", 27, 99);
            APP_SWITCH_PREFS_RECOMMENDED_OPEN_APP_STORE = new GrowthMetricsLog.EventCodeType("APP_SWITCH_PREFS_RECOMMENDED_OPEN_APP_STORE", 28, 52);
            APP_SWITCH_PREFS_FEATURED_OPEN_APP_STORE = new GrowthMetricsLog.EventCodeType("APP_SWITCH_PREFS_FEATURED_OPEN_APP_STORE", 29, 53);
            APP_SWITCH_PREFS_INTENTS_OPEN_APP_STORE = new GrowthMetricsLog.EventCodeType("APP_SWITCH_PREFS_INTENTS_OPEN_APP_STORE", 30, 54);
            APP_SWITCH_RESET_TO_SYSTEM_APPS_PROMPTED = new GrowthMetricsLog.EventCodeType("APP_SWITCH_RESET_TO_SYSTEM_APPS_PROMPTED", 31, 45);
            APP_SWITCH_RESET_TO_SYSTEM_APPS_CANCELLED = new GrowthMetricsLog.EventCodeType("APP_SWITCH_RESET_TO_SYSTEM_APPS_CANCELLED", 32, 46);
            APP_SWITCH_RESET_TO_SYSTEM_APPS_COMPLETED = new GrowthMetricsLog.EventCodeType("APP_SWITCH_RESET_TO_SYSTEM_APPS_COMPLETED", 33, 47);
            APP_SWITCH_GROWTHKIT_DISABLED = new GrowthMetricsLog.EventCodeType("APP_SWITCH_GROWTHKIT_DISABLED", 34, 80);
            APP_SWITCH_FAB_SHOWN = new GrowthMetricsLog.EventCodeType("APP_SWITCH_FAB_SHOWN", 35, 81);
            APP_SWITCH_FAB_TAPPED = new GrowthMetricsLog.EventCodeType("APP_SWITCH_FAB_TAPPED", 36, 82);
            DSEP_SHOWN = new GrowthMetricsLog.EventCodeType("DSEP_SHOWN", 37, 19);
            DSEP_DISMISSED = new GrowthMetricsLog.EventCodeType("DSEP_DISMISSED", 38, 20);
            DSEP_ACTED_ON = new GrowthMetricsLog.EventCodeType("DSEP_ACTED_ON", 39, 21);
            FEEDBACK_REQUESTED = new GrowthMetricsLog.EventCodeType("FEEDBACK_REQUESTED", 40, 22);
            FEEDBACK_DISMISSED = new GrowthMetricsLog.EventCodeType("FEEDBACK_DISMISSED", 41, 23);
            FEEDBACK_COMPLETED = new GrowthMetricsLog.EventCodeType("FEEDBACK_COMPLETED", 42, 24);
            GOOGLE_ACTIONS_BUTTON_SHOWN = new GrowthMetricsLog.EventCodeType("GOOGLE_ACTIONS_BUTTON_SHOWN", 43, 57);
            GOOGLE_ACTIONS_BUTTON_TAPPED = new GrowthMetricsLog.EventCodeType("GOOGLE_ACTIONS_BUTTON_TAPPED", 44, 58);
            GOOGLE_ACTIONS_OPTIONS_SHOWN = new GrowthMetricsLog.EventCodeType("GOOGLE_ACTIONS_OPTIONS_SHOWN", 45, 59);
            GOOGLE_ACTIONS_OPTION_SELECTED = new GrowthMetricsLog.EventCodeType("GOOGLE_ACTIONS_OPTION_SELECTED", 46, 60);
            GOOGLE_ACTIONS_OPTIONS_DISMISSED = new GrowthMetricsLog.EventCodeType("GOOGLE_ACTIONS_OPTIONS_DISMISSED", 47, 61);
            GOOGLE_ACTIONS_USER_UNSUPPORTED_COUNTRY = new GrowthMetricsLog.EventCodeType("GOOGLE_ACTIONS_USER_UNSUPPORTED_COUNTRY", 48, 74);
            GOOGLE_ACTIONS_INSTALL_PROMPT_SHOWN = new GrowthMetricsLog.EventCodeType("GOOGLE_ACTIONS_INSTALL_PROMPT_SHOWN", 49, 75);
            GOOGLE_ACTIONS_OPEN_APP_STORE = new GrowthMetricsLog.EventCodeType("GOOGLE_ACTIONS_OPEN_APP_STORE", 50, 76);
            GOOGLE_ACTIONS_OPEN_BROWSER = new GrowthMetricsLog.EventCodeType("GOOGLE_ACTIONS_OPEN_BROWSER", 51, 77);
            GOOGLE_ACTIONS_INSTALL_PROMPT_DISMISSED = new GrowthMetricsLog.EventCodeType("GOOGLE_ACTIONS_INSTALL_PROMPT_DISMISSED", 52, 78);
            GOOGLE_ACTIONS_OPEN_RECOMMENDED_APP_UNPROMPTED = new GrowthMetricsLog.EventCodeType("GOOGLE_ACTIONS_OPEN_RECOMMENDED_APP_UNPROMPTED", 53, 79);
            GOOGLE_APP_INSTALLED = new GrowthMetricsLog.EventCodeType("GOOGLE_APP_INSTALLED", 54, 25);
            GOOGLE_APPS_CURRENTLY_INSTALLED = new GrowthMetricsLog.EventCodeType("GOOGLE_APPS_CURRENTLY_INSTALLED", 55, 26);
            GOOGLE_USAGE_ID_RESET_PROMPTED = new GrowthMetricsLog.EventCodeType("GOOGLE_USAGE_ID_RESET_PROMPTED", 56, 44);
            GOOGLE_USAGE_ID_RESET_CANCELLED = new GrowthMetricsLog.EventCodeType("GOOGLE_USAGE_ID_RESET_CANCELLED", 57, 48);
            GOOGLE_USAGE_ID_RESET_COMPLETED = new GrowthMetricsLog.EventCodeType("GOOGLE_USAGE_ID_RESET_COMPLETED", 58, 49);
            HELP_SHOWN = new GrowthMetricsLog.EventCodeType("HELP_SHOWN", 59, 27);
            INTENT_CREATED = new GrowthMetricsLog.EventCodeType("INTENT_CREATED", 60, 50);
            INTENT_EVALUATED = new GrowthMetricsLog.EventCodeType("INTENT_EVALUATED", 61, 51);
            MENU_SHOWN = new GrowthMetricsLog.EventCodeType("MENU_SHOWN", 62, 28);
            OPEN_URL = new GrowthMetricsLog.EventCodeType("OPEN_URL", 63, 29);
            PRIVACY_POLICY_SHOWN = new GrowthMetricsLog.EventCodeType("PRIVACY_POLICY_SHOWN", 64, 30);
            PROMO_TRIGGERED = new GrowthMetricsLog.EventCodeType("PROMO_TRIGGERED", 65, 66);
            PROMO_TARGETING_EVALUATED = new GrowthMetricsLog.EventCodeType("PROMO_TARGETING_EVALUATED", 66, 95);
            PROMO_CONDITIONS_EVALUATED = new GrowthMetricsLog.EventCodeType("PROMO_CONDITIONS_EVALUATED", 67, 72);
            PROMO_NOT_SHOWN_DEVICE_CAPPED = new GrowthMetricsLog.EventCodeType("PROMO_NOT_SHOWN_DEVICE_CAPPED", 68, 94);
            PROMO_NOT_SHOWN = new GrowthMetricsLog.EventCodeType("PROMO_NOT_SHOWN", 69, 67);
            PROMO_SHOWN = new GrowthMetricsLog.EventCodeType("PROMO_SHOWN", 70, 68);
            PROMO_USER_ACTION = new GrowthMetricsLog.EventCodeType("PROMO_USER_ACTION", 71, 69);
            PROMO_USER_DISMISSED = new GrowthMetricsLog.EventCodeType("PROMO_USER_DISMISSED", 72, 70);
            PROMO_SYSTEM_DISMISSED = new GrowthMetricsLog.EventCodeType("PROMO_SYSTEM_DISMISSED", 73, 71);
            REENGAGEMENT_SETTINGS_SHOWN = new GrowthMetricsLog.EventCodeType("REENGAGEMENT_SETTINGS_SHOWN", 74, 84);
            REENGAGEMENT_SETTINGS_DISMISSED = new GrowthMetricsLog.EventCodeType("REENGAGEMENT_SETTINGS_DISMISSED", 75, 85);
            REENGAGEMENT_SETTINGS_MODIFIED = new GrowthMetricsLog.EventCodeType("REENGAGEMENT_SETTINGS_MODIFIED", 76, 86);
            REENGAGEMENT_NOTIFICATION_PREFERENCES_SHOWN = new GrowthMetricsLog.EventCodeType("REENGAGEMENT_NOTIFICATION_PREFERENCES_SHOWN", 77, 87);
            REENGAGEMENT_NOTIFICATION_PREFERENCES_SELECTED = new GrowthMetricsLog.EventCodeType("REENGAGEMENT_NOTIFICATION_PREFERENCES_SELECTED", 78, 88);
            REENGAGEMENT_NOTIFICATION_PREFERENCES_DISMISSED = new GrowthMetricsLog.EventCodeType("REENGAGEMENT_NOTIFICATION_PREFERENCES_DISMISSED", 79, 89);
            REENGAGEMENT_USER_TAPPED_ON_NOTIFICATION = new GrowthMetricsLog.EventCodeType("REENGAGEMENT_USER_TAPPED_ON_NOTIFICATION", 80, 90);
            REENGAGEMENT_USER_RECEIVED_NOTIFICATION = new GrowthMetricsLog.EventCodeType("REENGAGEMENT_USER_RECEIVED_NOTIFICATION", 81, 91);
            REENGAGEMENT_BEGIN_APP_ENGAGEMENT = new GrowthMetricsLog.EventCodeType("REENGAGEMENT_BEGIN_APP_ENGAGEMENT", 82, 96);
            REENGAGEMENT_END_APP_ENGAGEMENT = new GrowthMetricsLog.EventCodeType("REENGAGEMENT_END_APP_ENGAGEMENT", 83, 97);
            SETTINGS_SHOWN = new GrowthMetricsLog.EventCodeType("SETTINGS_SHOWN", 84, 31);
            SETTINGS_MODIFIED = new GrowthMetricsLog.EventCodeType("SETTINGS_MODIFIED", 85, 32);
            SIGN_IN_START = new GrowthMetricsLog.EventCodeType("SIGN_IN_START", 86, 33);
            SIGN_IN_COMPLETE = new GrowthMetricsLog.EventCodeType("SIGN_IN_COMPLETE", 87, 34);
            SIGN_OUT = new GrowthMetricsLog.EventCodeType("SIGN_OUT", 88, 35);
            CHANGE_USER_START = new GrowthMetricsLog.EventCodeType("CHANGE_USER_START", 89, 36);
            CHANGE_USER_COMPLETE = new GrowthMetricsLog.EventCodeType("CHANGE_USER_COMPLETE", 90, 37);
            CREATE_USER_START = new GrowthMetricsLog.EventCodeType("CREATE_USER_START", 91, 38);
            CREATE_USER_COMPLETE = new GrowthMetricsLog.EventCodeType("CREATE_USER_COMPLETE", 92, 39);
            SIGN_IN_SKIPPED = new GrowthMetricsLog.EventCodeType("SIGN_IN_SKIPPED", 93, 40);
            SMART_MAIL_CALENDAR_BUTTON_SHOWN = new GrowthMetricsLog.EventCodeType("SMART_MAIL_CALENDAR_BUTTON_SHOWN", 94, 93);
            SMART_MAIL_CALENDAR_BUTTON_TAPPED = new GrowthMetricsLog.EventCodeType("SMART_MAIL_CALENDAR_BUTTON_TAPPED", 95, 92);
            TRANSLATED_TEXT = new GrowthMetricsLog.EventCodeType("TRANSLATED_TEXT", 96, 65);
            USER_AUTHORIZED_PERMISSION = new GrowthMetricsLog.EventCodeType("USER_AUTHORIZED_PERMISSION", 97, 100);
            USER_DENIED_PERMISSION = new GrowthMetricsLog.EventCodeType("USER_DENIED_PERMISSION", 98, 101);
            VOICE_INTERACTION_STARTED = new GrowthMetricsLog.EventCodeType("VOICE_INTERACTION_STARTED", 99, 41);
            VOICE_INTERACTION_DISMISSED = new GrowthMetricsLog.EventCodeType("VOICE_INTERACTION_DISMISSED", 100, 42);
            VOICE_INTERACTION_COMPLETED = new GrowthMetricsLog.EventCodeType("VOICE_INTERACTION_COMPLETED", 101, 43);
            ANDROID_APP_SET_AS_DEFAULT = new GrowthMetricsLog.EventCodeType("ANDROID_APP_SET_AS_DEFAULT", 102, 102);
            ANDROID_ONBOARDING_EVENT = new GrowthMetricsLog.EventCodeType("ANDROID_ONBOARDING_EVENT", 103, 103);
            $VALUES = (new GrowthMetricsLog.EventCodeType[] {
                UNKNOWN, ABOUT_SHOWN, ACCESSIBILITY_SETTING_CHANGED, ENTER_FOREGROUND, ENTER_BACKGROUND, ERROR, SCREEN_UPDATED, USER_ACTION, COLD_START, FIRST_USE, 
                CLIENT_VIEW_DID_APPEAR, APP_SWITCH_SHEET_SHOWN, APP_SWITCH_SHEET_DISMISSED, APP_SWITCH_OPEN_APP_STORE, APP_SWITCH_OPEN_RECOMMENDED_APP, APP_SWITCH_OPEN_SYSTEM_APP, APP_SWITCH_OPEN_EXTERNAL_APP, APP_SWITCH_OPEN_APP_DEFAULT_HANDLER, APP_SWITCH_OPEN_RECOMMENDED_APP_UNPROMPTED, APP_SWITCH_OPEN_SYSTEM_APP_UNPROMPTED, 
                APP_SWITCH_OPEN_EXTERNAL_APP_UNPROMPTED, APP_SWITCH_PREFS_SHOWN, APP_SWITCH_PREFS_DISMISSED, APP_SWITCH_PREFS_PREFERRED_APP_SET, APP_SWITCH_PREFS_PROMPT_PREFERRED_APP_ON, APP_SWITCH_PREFS_PROMPT_PREFERRED_APP_OFF, APP_SWITCH_SHEET_PROMPT_PREFERRED_APP_ON, APP_SWITCH_SHEET_PROMPT_PREFERRED_APP_OFF, APP_SWITCH_PREFS_RECOMMENDED_OPEN_APP_STORE, APP_SWITCH_PREFS_FEATURED_OPEN_APP_STORE, 
                APP_SWITCH_PREFS_INTENTS_OPEN_APP_STORE, APP_SWITCH_RESET_TO_SYSTEM_APPS_PROMPTED, APP_SWITCH_RESET_TO_SYSTEM_APPS_CANCELLED, APP_SWITCH_RESET_TO_SYSTEM_APPS_COMPLETED, APP_SWITCH_GROWTHKIT_DISABLED, APP_SWITCH_FAB_SHOWN, APP_SWITCH_FAB_TAPPED, DSEP_SHOWN, DSEP_DISMISSED, DSEP_ACTED_ON, 
                FEEDBACK_REQUESTED, FEEDBACK_DISMISSED, FEEDBACK_COMPLETED, GOOGLE_ACTIONS_BUTTON_SHOWN, GOOGLE_ACTIONS_BUTTON_TAPPED, GOOGLE_ACTIONS_OPTIONS_SHOWN, GOOGLE_ACTIONS_OPTION_SELECTED, GOOGLE_ACTIONS_OPTIONS_DISMISSED, GOOGLE_ACTIONS_USER_UNSUPPORTED_COUNTRY, GOOGLE_ACTIONS_INSTALL_PROMPT_SHOWN, 
                GOOGLE_ACTIONS_OPEN_APP_STORE, GOOGLE_ACTIONS_OPEN_BROWSER, GOOGLE_ACTIONS_INSTALL_PROMPT_DISMISSED, GOOGLE_ACTIONS_OPEN_RECOMMENDED_APP_UNPROMPTED, GOOGLE_APP_INSTALLED, GOOGLE_APPS_CURRENTLY_INSTALLED, GOOGLE_USAGE_ID_RESET_PROMPTED, GOOGLE_USAGE_ID_RESET_CANCELLED, GOOGLE_USAGE_ID_RESET_COMPLETED, HELP_SHOWN, 
                INTENT_CREATED, INTENT_EVALUATED, MENU_SHOWN, OPEN_URL, PRIVACY_POLICY_SHOWN, PROMO_TRIGGERED, PROMO_TARGETING_EVALUATED, PROMO_CONDITIONS_EVALUATED, PROMO_NOT_SHOWN_DEVICE_CAPPED, PROMO_NOT_SHOWN, 
                PROMO_SHOWN, PROMO_USER_ACTION, PROMO_USER_DISMISSED, PROMO_SYSTEM_DISMISSED, REENGAGEMENT_SETTINGS_SHOWN, REENGAGEMENT_SETTINGS_DISMISSED, REENGAGEMENT_SETTINGS_MODIFIED, REENGAGEMENT_NOTIFICATION_PREFERENCES_SHOWN, REENGAGEMENT_NOTIFICATION_PREFERENCES_SELECTED, REENGAGEMENT_NOTIFICATION_PREFERENCES_DISMISSED, 
                REENGAGEMENT_USER_TAPPED_ON_NOTIFICATION, REENGAGEMENT_USER_RECEIVED_NOTIFICATION, REENGAGEMENT_BEGIN_APP_ENGAGEMENT, REENGAGEMENT_END_APP_ENGAGEMENT, SETTINGS_SHOWN, SETTINGS_MODIFIED, SIGN_IN_START, SIGN_IN_COMPLETE, SIGN_OUT, CHANGE_USER_START, 
                CHANGE_USER_COMPLETE, CREATE_USER_START, CREATE_USER_COMPLETE, SIGN_IN_SKIPPED, SMART_MAIL_CALENDAR_BUTTON_SHOWN, SMART_MAIL_CALENDAR_BUTTON_TAPPED, TRANSLATED_TEXT, USER_AUTHORIZED_PERMISSION, USER_DENIED_PERMISSION, VOICE_INTERACTION_STARTED, 
                VOICE_INTERACTION_DISMISSED, VOICE_INTERACTION_COMPLETED, ANDROID_APP_SET_AS_DEFAULT, ANDROID_ONBOARDING_EVENT
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return GrowthMetricsLog.EventCodeType.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

            new _cls2();
        }

        private GrowthMetricsLog.EventCodeType(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class PromoEvent extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final PromoEvent DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public com.google.protobuf.Internal.IntList conditionsNotMet_;
        public int displayBlockReason_;
        public int impressionCappingId_;
        public com.google.protobuf.Internal.IntList mendelIds_;
        public int promoNotShownReason_;
        public boolean targetingRuleNotSatisfied_;
        public int triggeringEventIndex_;
        public int userAction_;

        public static PromoEvent parseFrom(InputStream inputstream)
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
            return (PromoEvent)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 229
        //                       1 234
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 176
        //                       6 180;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new PromoEvent();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            obj = ConditionsReason.internalVerifier;
            com.google.protobuf.Internal.EnumVerifier enumverifier = DisplayBlockReason.internalVerifier;
            com.google.protobuf.Internal.EnumVerifier enumverifier1 = UserAction.internalVerifier;
            com.google.protobuf.Internal.EnumVerifier enumverifier2 = PromoNotShownReason.internalVerifier;
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\b\000\001\001\t\b\000\002\000\001\026\002\004\000\003\004\001\004\007\002\005\036\006\f\004\007\f\005\t\f\003", new Object[] {
                "bitField0_", "mendelIds_", "impressionCappingId_", "triggeringEventIndex_", "targetingRuleNotSatisfied_", "conditionsNotMet_", obj, "displayBlockReason_", enumverifier, "userAction_", 
                enumverifier1, "promoNotShownReason_", enumverifier2
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
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
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
            DEFAULT_INSTANCE = new PromoEvent();
            PromoEvent promoevent = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/identity/growth/logging/proto/Client$PromoEvent, promoevent);
        }

        private PromoEvent()
        {
            mendelIds_ = IntArrayList.EMPTY_LIST;
            conditionsNotMet_ = IntArrayList.EMPTY_LIST;
        }
    }

    public static final class PromoEvent.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        PromoEvent.Builder()
        {
            super(PromoEvent.DEFAULT_INSTANCE);
        }
    }

    public static final class PromoEvent.ConditionsReason extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final PromoEvent.ConditionsReason $VALUES[];
        public static final PromoEvent.ConditionsReason CONDITION_BATTERY;
        public static final PromoEvent.ConditionsReason CONDITION_INSTALLED_APP;
        private static final PromoEvent.ConditionsReason CONDITION_KEYBOARD_PRESENT;
        public static final PromoEvent.ConditionsReason CONDITION_LOCALE;
        public static final PromoEvent.ConditionsReason CONDITION_NETWORK;
        private static final PromoEvent.ConditionsReason CONDITION_NETWORK_NOT_READY;
        private static final PromoEvent.ConditionsReason CONDITION_REGION;
        public static final PromoEvent.ConditionsReason CONDITION_TIME_CONSTRAINT;
        public static final PromoEvent.ConditionsReason CONDITION_UNKNOWN;
        private static final PromoEvent.ConditionsReason CONDITION_VIEW_NOT_IN_SCREEN;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static PromoEvent.ConditionsReason forNumber(int i)
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

        public static PromoEvent.ConditionsReason[] values()
        {
            return (PromoEvent.ConditionsReason[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            CONDITION_UNKNOWN = new PromoEvent.ConditionsReason("CONDITION_UNKNOWN", 0, 0);
            CONDITION_NETWORK = new PromoEvent.ConditionsReason("CONDITION_NETWORK", 1, 1);
            CONDITION_NETWORK_NOT_READY = new PromoEvent.ConditionsReason("CONDITION_NETWORK_NOT_READY", 2, 2);
            CONDITION_LOCALE = new PromoEvent.ConditionsReason("CONDITION_LOCALE", 3, 3);
            CONDITION_REGION = new PromoEvent.ConditionsReason("CONDITION_REGION", 4, 4);
            CONDITION_BATTERY = new PromoEvent.ConditionsReason("CONDITION_BATTERY", 5, 5);
            CONDITION_INSTALLED_APP = new PromoEvent.ConditionsReason("CONDITION_INSTALLED_APP", 6, 6);
            CONDITION_VIEW_NOT_IN_SCREEN = new PromoEvent.ConditionsReason("CONDITION_VIEW_NOT_IN_SCREEN", 7, 7);
            CONDITION_KEYBOARD_PRESENT = new PromoEvent.ConditionsReason("CONDITION_KEYBOARD_PRESENT", 8, 8);
            CONDITION_TIME_CONSTRAINT = new PromoEvent.ConditionsReason("CONDITION_TIME_CONSTRAINT", 9, 9);
            $VALUES = (new PromoEvent.ConditionsReason[] {
                CONDITION_UNKNOWN, CONDITION_NETWORK, CONDITION_NETWORK_NOT_READY, CONDITION_LOCALE, CONDITION_REGION, CONDITION_BATTERY, CONDITION_INSTALLED_APP, CONDITION_VIEW_NOT_IN_SCREEN, CONDITION_KEYBOARD_PRESENT, CONDITION_TIME_CONSTRAINT
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return PromoEvent.ConditionsReason.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private PromoEvent.ConditionsReason(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class PromoEvent.DisplayBlockReason extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final PromoEvent.DisplayBlockReason $VALUES[];
        public static final PromoEvent.DisplayBlockReason DISPLAY_BLOCK_CLIENT_ERROR;
        public static final PromoEvent.DisplayBlockReason DISPLAY_BLOCK_CLIENT_REJECT;
        public static final PromoEvent.DisplayBlockReason DISPLAY_BLOCK_LEGACY_USER;
        private static final PromoEvent.DisplayBlockReason DISPLAY_BLOCK_NEXT_LAUNCH;
        public static final PromoEvent.DisplayBlockReason DISPLAY_BLOCK_TRY_AGAIN_LATER;
        public static final PromoEvent.DisplayBlockReason DISPLAY_BLOCK_UNKNOWN;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static PromoEvent.DisplayBlockReason forNumber(int i)
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

        public static PromoEvent.DisplayBlockReason[] values()
        {
            return (PromoEvent.DisplayBlockReason[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            DISPLAY_BLOCK_UNKNOWN = new PromoEvent.DisplayBlockReason("DISPLAY_BLOCK_UNKNOWN", 0, 0);
            DISPLAY_BLOCK_CLIENT_REJECT = new PromoEvent.DisplayBlockReason("DISPLAY_BLOCK_CLIENT_REJECT", 1, 1);
            DISPLAY_BLOCK_CLIENT_ERROR = new PromoEvent.DisplayBlockReason("DISPLAY_BLOCK_CLIENT_ERROR", 2, 2);
            DISPLAY_BLOCK_NEXT_LAUNCH = new PromoEvent.DisplayBlockReason("DISPLAY_BLOCK_NEXT_LAUNCH", 3, 3);
            DISPLAY_BLOCK_TRY_AGAIN_LATER = new PromoEvent.DisplayBlockReason("DISPLAY_BLOCK_TRY_AGAIN_LATER", 4, 4);
            DISPLAY_BLOCK_LEGACY_USER = new PromoEvent.DisplayBlockReason("DISPLAY_BLOCK_LEGACY_USER", 5, 5);
            $VALUES = (new PromoEvent.DisplayBlockReason[] {
                DISPLAY_BLOCK_UNKNOWN, DISPLAY_BLOCK_CLIENT_REJECT, DISPLAY_BLOCK_CLIENT_ERROR, DISPLAY_BLOCK_NEXT_LAUNCH, DISPLAY_BLOCK_TRY_AGAIN_LATER, DISPLAY_BLOCK_LEGACY_USER
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return PromoEvent.DisplayBlockReason.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private PromoEvent.DisplayBlockReason(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class PromoEvent.PromoNotShownReason extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final PromoEvent.PromoNotShownReason $VALUES[];
        public static final PromoEvent.PromoNotShownReason PROMO_NOT_SHOWN_CLIENT_BLOCK;
        public static final PromoEvent.PromoNotShownReason PROMO_NOT_SHOWN_CONTROL_GROUP;
        private static final PromoEvent.PromoNotShownReason PROMO_NOT_SHOWN_INTERNAL_ERROR;
        private static final PromoEvent.PromoNotShownReason PROMO_NOT_SHOWN_KEYBOARD_PRESENT;
        private static final PromoEvent.PromoNotShownReason PROMO_NOT_SHOWN_UNKNOWN;
        private static final PromoEvent.PromoNotShownReason PROMO_NOT_SHOWN_VIEW_NOT_IN_SCREEN;
        private static final PromoEvent.PromoNotShownReason PROMO_NOT_SHOWN_VOICE_OVER_ENABLED;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static PromoEvent.PromoNotShownReason forNumber(int i)
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

        public static PromoEvent.PromoNotShownReason[] values()
        {
            return (PromoEvent.PromoNotShownReason[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            PROMO_NOT_SHOWN_UNKNOWN = new PromoEvent.PromoNotShownReason("PROMO_NOT_SHOWN_UNKNOWN", 0, 0);
            PROMO_NOT_SHOWN_INTERNAL_ERROR = new PromoEvent.PromoNotShownReason("PROMO_NOT_SHOWN_INTERNAL_ERROR", 1, 1);
            PROMO_NOT_SHOWN_CLIENT_BLOCK = new PromoEvent.PromoNotShownReason("PROMO_NOT_SHOWN_CLIENT_BLOCK", 2, 2);
            PROMO_NOT_SHOWN_CONTROL_GROUP = new PromoEvent.PromoNotShownReason("PROMO_NOT_SHOWN_CONTROL_GROUP", 3, 3);
            PROMO_NOT_SHOWN_VIEW_NOT_IN_SCREEN = new PromoEvent.PromoNotShownReason("PROMO_NOT_SHOWN_VIEW_NOT_IN_SCREEN", 4, 4);
            PROMO_NOT_SHOWN_KEYBOARD_PRESENT = new PromoEvent.PromoNotShownReason("PROMO_NOT_SHOWN_KEYBOARD_PRESENT", 5, 5);
            PROMO_NOT_SHOWN_VOICE_OVER_ENABLED = new PromoEvent.PromoNotShownReason("PROMO_NOT_SHOWN_VOICE_OVER_ENABLED", 6, 6);
            $VALUES = (new PromoEvent.PromoNotShownReason[] {
                PROMO_NOT_SHOWN_UNKNOWN, PROMO_NOT_SHOWN_INTERNAL_ERROR, PROMO_NOT_SHOWN_CLIENT_BLOCK, PROMO_NOT_SHOWN_CONTROL_GROUP, PROMO_NOT_SHOWN_VIEW_NOT_IN_SCREEN, PROMO_NOT_SHOWN_KEYBOARD_PRESENT, PROMO_NOT_SHOWN_VOICE_OVER_ENABLED
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return PromoEvent.PromoNotShownReason.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private PromoEvent.PromoNotShownReason(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class PromoEvent.UserAction extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final PromoEvent.UserAction $VALUES[];
        private static final PromoEvent.UserAction ACTION_ACKNOWLEDGE;
        public static final PromoEvent.UserAction ACTION_DISMISS;
        public static final PromoEvent.UserAction ACTION_NEGATIVE;
        public static final PromoEvent.UserAction ACTION_POSITIVE;
        public static final PromoEvent.UserAction ACTION_UNKNOWN;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static PromoEvent.UserAction forNumber(int i)
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

        public static PromoEvent.UserAction[] values()
        {
            return (PromoEvent.UserAction[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            ACTION_UNKNOWN = new PromoEvent.UserAction("ACTION_UNKNOWN", 0, 0);
            ACTION_POSITIVE = new PromoEvent.UserAction("ACTION_POSITIVE", 1, 1);
            ACTION_NEGATIVE = new PromoEvent.UserAction("ACTION_NEGATIVE", 2, 2);
            ACTION_DISMISS = new PromoEvent.UserAction("ACTION_DISMISS", 3, 3);
            ACTION_ACKNOWLEDGE = new PromoEvent.UserAction("ACTION_ACKNOWLEDGE", 4, 4);
            $VALUES = (new PromoEvent.UserAction[] {
                ACTION_UNKNOWN, ACTION_POSITIVE, ACTION_NEGATIVE, ACTION_DISMISS, ACTION_ACKNOWLEDGE
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return PromoEvent.UserAction.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private PromoEvent.UserAction(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

}
