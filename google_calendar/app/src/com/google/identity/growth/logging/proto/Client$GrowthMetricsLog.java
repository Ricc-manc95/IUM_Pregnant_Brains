// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.logging.proto;

import com.google.protobuf.ByteString;
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

// Referenced classes of package com.google.identity.growth.logging.proto:
//            Client

public static final class encodedAdditionalData_ extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(Client.GrowthMetricsLog.DEFAULT_INSTANCE);
        }
    }

    public static final class EventCodeType extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final EventCodeType $VALUES[];
        private static final EventCodeType ABOUT_SHOWN;
        private static final EventCodeType ACCESSIBILITY_SETTING_CHANGED;
        private static final EventCodeType ANDROID_APP_SET_AS_DEFAULT;
        private static final EventCodeType ANDROID_ONBOARDING_EVENT;
        private static final EventCodeType APP_SWITCH_FAB_SHOWN;
        private static final EventCodeType APP_SWITCH_FAB_TAPPED;
        private static final EventCodeType APP_SWITCH_GROWTHKIT_DISABLED;
        private static final EventCodeType APP_SWITCH_OPEN_APP_DEFAULT_HANDLER;
        private static final EventCodeType APP_SWITCH_OPEN_APP_STORE;
        private static final EventCodeType APP_SWITCH_OPEN_EXTERNAL_APP;
        private static final EventCodeType APP_SWITCH_OPEN_EXTERNAL_APP_UNPROMPTED;
        private static final EventCodeType APP_SWITCH_OPEN_RECOMMENDED_APP;
        private static final EventCodeType APP_SWITCH_OPEN_RECOMMENDED_APP_UNPROMPTED;
        private static final EventCodeType APP_SWITCH_OPEN_SYSTEM_APP;
        private static final EventCodeType APP_SWITCH_OPEN_SYSTEM_APP_UNPROMPTED;
        private static final EventCodeType APP_SWITCH_PREFS_DISMISSED;
        private static final EventCodeType APP_SWITCH_PREFS_FEATURED_OPEN_APP_STORE;
        private static final EventCodeType APP_SWITCH_PREFS_INTENTS_OPEN_APP_STORE;
        private static final EventCodeType APP_SWITCH_PREFS_PREFERRED_APP_SET;
        private static final EventCodeType APP_SWITCH_PREFS_PROMPT_PREFERRED_APP_OFF;
        private static final EventCodeType APP_SWITCH_PREFS_PROMPT_PREFERRED_APP_ON;
        private static final EventCodeType APP_SWITCH_PREFS_RECOMMENDED_OPEN_APP_STORE;
        private static final EventCodeType APP_SWITCH_PREFS_SHOWN;
        private static final EventCodeType APP_SWITCH_RESET_TO_SYSTEM_APPS_CANCELLED;
        private static final EventCodeType APP_SWITCH_RESET_TO_SYSTEM_APPS_COMPLETED;
        private static final EventCodeType APP_SWITCH_RESET_TO_SYSTEM_APPS_PROMPTED;
        private static final EventCodeType APP_SWITCH_SHEET_DISMISSED;
        private static final EventCodeType APP_SWITCH_SHEET_PROMPT_PREFERRED_APP_OFF;
        private static final EventCodeType APP_SWITCH_SHEET_PROMPT_PREFERRED_APP_ON;
        private static final EventCodeType APP_SWITCH_SHEET_SHOWN;
        private static final EventCodeType CHANGE_USER_COMPLETE;
        private static final EventCodeType CHANGE_USER_START;
        private static final EventCodeType CLIENT_VIEW_DID_APPEAR;
        private static final EventCodeType COLD_START;
        private static final EventCodeType CREATE_USER_COMPLETE;
        private static final EventCodeType CREATE_USER_START;
        private static final EventCodeType DSEP_ACTED_ON;
        private static final EventCodeType DSEP_DISMISSED;
        private static final EventCodeType DSEP_SHOWN;
        private static final EventCodeType ENTER_BACKGROUND;
        private static final EventCodeType ENTER_FOREGROUND;
        private static final EventCodeType ERROR;
        private static final EventCodeType FEEDBACK_COMPLETED;
        private static final EventCodeType FEEDBACK_DISMISSED;
        private static final EventCodeType FEEDBACK_REQUESTED;
        private static final EventCodeType FIRST_USE;
        private static final EventCodeType GOOGLE_ACTIONS_BUTTON_SHOWN;
        private static final EventCodeType GOOGLE_ACTIONS_BUTTON_TAPPED;
        private static final EventCodeType GOOGLE_ACTIONS_INSTALL_PROMPT_DISMISSED;
        private static final EventCodeType GOOGLE_ACTIONS_INSTALL_PROMPT_SHOWN;
        private static final EventCodeType GOOGLE_ACTIONS_OPEN_APP_STORE;
        private static final EventCodeType GOOGLE_ACTIONS_OPEN_BROWSER;
        private static final EventCodeType GOOGLE_ACTIONS_OPEN_RECOMMENDED_APP_UNPROMPTED;
        private static final EventCodeType GOOGLE_ACTIONS_OPTIONS_DISMISSED;
        private static final EventCodeType GOOGLE_ACTIONS_OPTIONS_SHOWN;
        private static final EventCodeType GOOGLE_ACTIONS_OPTION_SELECTED;
        private static final EventCodeType GOOGLE_ACTIONS_USER_UNSUPPORTED_COUNTRY;
        private static final EventCodeType GOOGLE_APPS_CURRENTLY_INSTALLED;
        private static final EventCodeType GOOGLE_APP_INSTALLED;
        private static final EventCodeType GOOGLE_USAGE_ID_RESET_CANCELLED;
        private static final EventCodeType GOOGLE_USAGE_ID_RESET_COMPLETED;
        private static final EventCodeType GOOGLE_USAGE_ID_RESET_PROMPTED;
        private static final EventCodeType HELP_SHOWN;
        private static final EventCodeType INTENT_CREATED;
        private static final EventCodeType INTENT_EVALUATED;
        private static final EventCodeType MENU_SHOWN;
        private static final EventCodeType OPEN_URL;
        private static final EventCodeType PRIVACY_POLICY_SHOWN;
        public static final EventCodeType PROMO_CONDITIONS_EVALUATED;
        public static final EventCodeType PROMO_NOT_SHOWN;
        public static final EventCodeType PROMO_NOT_SHOWN_DEVICE_CAPPED;
        public static final EventCodeType PROMO_SHOWN;
        private static final EventCodeType PROMO_SYSTEM_DISMISSED;
        public static final EventCodeType PROMO_TARGETING_EVALUATED;
        public static final EventCodeType PROMO_TRIGGERED;
        public static final EventCodeType PROMO_USER_ACTION;
        public static final EventCodeType PROMO_USER_DISMISSED;
        private static final EventCodeType REENGAGEMENT_BEGIN_APP_ENGAGEMENT;
        private static final EventCodeType REENGAGEMENT_END_APP_ENGAGEMENT;
        private static final EventCodeType REENGAGEMENT_NOTIFICATION_PREFERENCES_DISMISSED;
        private static final EventCodeType REENGAGEMENT_NOTIFICATION_PREFERENCES_SELECTED;
        private static final EventCodeType REENGAGEMENT_NOTIFICATION_PREFERENCES_SHOWN;
        private static final EventCodeType REENGAGEMENT_SETTINGS_DISMISSED;
        private static final EventCodeType REENGAGEMENT_SETTINGS_MODIFIED;
        private static final EventCodeType REENGAGEMENT_SETTINGS_SHOWN;
        private static final EventCodeType REENGAGEMENT_USER_RECEIVED_NOTIFICATION;
        private static final EventCodeType REENGAGEMENT_USER_TAPPED_ON_NOTIFICATION;
        private static final EventCodeType SCREEN_UPDATED;
        private static final EventCodeType SETTINGS_MODIFIED;
        private static final EventCodeType SETTINGS_SHOWN;
        private static final EventCodeType SIGN_IN_COMPLETE;
        private static final EventCodeType SIGN_IN_SKIPPED;
        private static final EventCodeType SIGN_IN_START;
        private static final EventCodeType SIGN_OUT;
        private static final EventCodeType SMART_MAIL_CALENDAR_BUTTON_SHOWN;
        private static final EventCodeType SMART_MAIL_CALENDAR_BUTTON_TAPPED;
        private static final EventCodeType TRANSLATED_TEXT;
        private static final EventCodeType UNKNOWN;
        private static final EventCodeType USER_ACTION;
        private static final EventCodeType USER_AUTHORIZED_PERMISSION;
        private static final EventCodeType USER_DENIED_PERMISSION;
        private static final EventCodeType VOICE_INTERACTION_COMPLETED;
        private static final EventCodeType VOICE_INTERACTION_DISMISSED;
        private static final EventCodeType VOICE_INTERACTION_STARTED;
        public final int value;

        public static EventCodeType forNumber(int i)
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

        public static EventCodeType[] values()
        {
            return (EventCodeType[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            UNKNOWN = new EventCodeType("UNKNOWN", 0, 0);
            ABOUT_SHOWN = new EventCodeType("ABOUT_SHOWN", 1, 1);
            ACCESSIBILITY_SETTING_CHANGED = new EventCodeType("ACCESSIBILITY_SETTING_CHANGED", 2, 2);
            ENTER_FOREGROUND = new EventCodeType("ENTER_FOREGROUND", 3, 3);
            ENTER_BACKGROUND = new EventCodeType("ENTER_BACKGROUND", 4, 4);
            ERROR = new EventCodeType("ERROR", 5, 73);
            SCREEN_UPDATED = new EventCodeType("SCREEN_UPDATED", 6, 5);
            USER_ACTION = new EventCodeType("USER_ACTION", 7, 6);
            COLD_START = new EventCodeType("COLD_START", 8, 7);
            FIRST_USE = new EventCodeType("FIRST_USE", 9, 8);
            CLIENT_VIEW_DID_APPEAR = new EventCodeType("CLIENT_VIEW_DID_APPEAR", 10, 83);
            APP_SWITCH_SHEET_SHOWN = new EventCodeType("APP_SWITCH_SHEET_SHOWN", 11, 9);
            APP_SWITCH_SHEET_DISMISSED = new EventCodeType("APP_SWITCH_SHEET_DISMISSED", 12, 10);
            APP_SWITCH_OPEN_APP_STORE = new EventCodeType("APP_SWITCH_OPEN_APP_STORE", 13, 11);
            APP_SWITCH_OPEN_RECOMMENDED_APP = new EventCodeType("APP_SWITCH_OPEN_RECOMMENDED_APP", 14, 12);
            APP_SWITCH_OPEN_SYSTEM_APP = new EventCodeType("APP_SWITCH_OPEN_SYSTEM_APP", 15, 13);
            APP_SWITCH_OPEN_EXTERNAL_APP = new EventCodeType("APP_SWITCH_OPEN_EXTERNAL_APP", 16, 62);
            APP_SWITCH_OPEN_APP_DEFAULT_HANDLER = new EventCodeType("APP_SWITCH_OPEN_APP_DEFAULT_HANDLER", 17, 64);
            APP_SWITCH_OPEN_RECOMMENDED_APP_UNPROMPTED = new EventCodeType("APP_SWITCH_OPEN_RECOMMENDED_APP_UNPROMPTED", 18, 55);
            APP_SWITCH_OPEN_SYSTEM_APP_UNPROMPTED = new EventCodeType("APP_SWITCH_OPEN_SYSTEM_APP_UNPROMPTED", 19, 56);
            APP_SWITCH_OPEN_EXTERNAL_APP_UNPROMPTED = new EventCodeType("APP_SWITCH_OPEN_EXTERNAL_APP_UNPROMPTED", 20, 63);
            APP_SWITCH_PREFS_SHOWN = new EventCodeType("APP_SWITCH_PREFS_SHOWN", 21, 14);
            APP_SWITCH_PREFS_DISMISSED = new EventCodeType("APP_SWITCH_PREFS_DISMISSED", 22, 15);
            APP_SWITCH_PREFS_PREFERRED_APP_SET = new EventCodeType("APP_SWITCH_PREFS_PREFERRED_APP_SET", 23, 16);
            APP_SWITCH_PREFS_PROMPT_PREFERRED_APP_ON = new EventCodeType("APP_SWITCH_PREFS_PROMPT_PREFERRED_APP_ON", 24, 17);
            APP_SWITCH_PREFS_PROMPT_PREFERRED_APP_OFF = new EventCodeType("APP_SWITCH_PREFS_PROMPT_PREFERRED_APP_OFF", 25, 18);
            APP_SWITCH_SHEET_PROMPT_PREFERRED_APP_ON = new EventCodeType("APP_SWITCH_SHEET_PROMPT_PREFERRED_APP_ON", 26, 98);
            APP_SWITCH_SHEET_PROMPT_PREFERRED_APP_OFF = new EventCodeType("APP_SWITCH_SHEET_PROMPT_PREFERRED_APP_OFF", 27, 99);
            APP_SWITCH_PREFS_RECOMMENDED_OPEN_APP_STORE = new EventCodeType("APP_SWITCH_PREFS_RECOMMENDED_OPEN_APP_STORE", 28, 52);
            APP_SWITCH_PREFS_FEATURED_OPEN_APP_STORE = new EventCodeType("APP_SWITCH_PREFS_FEATURED_OPEN_APP_STORE", 29, 53);
            APP_SWITCH_PREFS_INTENTS_OPEN_APP_STORE = new EventCodeType("APP_SWITCH_PREFS_INTENTS_OPEN_APP_STORE", 30, 54);
            APP_SWITCH_RESET_TO_SYSTEM_APPS_PROMPTED = new EventCodeType("APP_SWITCH_RESET_TO_SYSTEM_APPS_PROMPTED", 31, 45);
            APP_SWITCH_RESET_TO_SYSTEM_APPS_CANCELLED = new EventCodeType("APP_SWITCH_RESET_TO_SYSTEM_APPS_CANCELLED", 32, 46);
            APP_SWITCH_RESET_TO_SYSTEM_APPS_COMPLETED = new EventCodeType("APP_SWITCH_RESET_TO_SYSTEM_APPS_COMPLETED", 33, 47);
            APP_SWITCH_GROWTHKIT_DISABLED = new EventCodeType("APP_SWITCH_GROWTHKIT_DISABLED", 34, 80);
            APP_SWITCH_FAB_SHOWN = new EventCodeType("APP_SWITCH_FAB_SHOWN", 35, 81);
            APP_SWITCH_FAB_TAPPED = new EventCodeType("APP_SWITCH_FAB_TAPPED", 36, 82);
            DSEP_SHOWN = new EventCodeType("DSEP_SHOWN", 37, 19);
            DSEP_DISMISSED = new EventCodeType("DSEP_DISMISSED", 38, 20);
            DSEP_ACTED_ON = new EventCodeType("DSEP_ACTED_ON", 39, 21);
            FEEDBACK_REQUESTED = new EventCodeType("FEEDBACK_REQUESTED", 40, 22);
            FEEDBACK_DISMISSED = new EventCodeType("FEEDBACK_DISMISSED", 41, 23);
            FEEDBACK_COMPLETED = new EventCodeType("FEEDBACK_COMPLETED", 42, 24);
            GOOGLE_ACTIONS_BUTTON_SHOWN = new EventCodeType("GOOGLE_ACTIONS_BUTTON_SHOWN", 43, 57);
            GOOGLE_ACTIONS_BUTTON_TAPPED = new EventCodeType("GOOGLE_ACTIONS_BUTTON_TAPPED", 44, 58);
            GOOGLE_ACTIONS_OPTIONS_SHOWN = new EventCodeType("GOOGLE_ACTIONS_OPTIONS_SHOWN", 45, 59);
            GOOGLE_ACTIONS_OPTION_SELECTED = new EventCodeType("GOOGLE_ACTIONS_OPTION_SELECTED", 46, 60);
            GOOGLE_ACTIONS_OPTIONS_DISMISSED = new EventCodeType("GOOGLE_ACTIONS_OPTIONS_DISMISSED", 47, 61);
            GOOGLE_ACTIONS_USER_UNSUPPORTED_COUNTRY = new EventCodeType("GOOGLE_ACTIONS_USER_UNSUPPORTED_COUNTRY", 48, 74);
            GOOGLE_ACTIONS_INSTALL_PROMPT_SHOWN = new EventCodeType("GOOGLE_ACTIONS_INSTALL_PROMPT_SHOWN", 49, 75);
            GOOGLE_ACTIONS_OPEN_APP_STORE = new EventCodeType("GOOGLE_ACTIONS_OPEN_APP_STORE", 50, 76);
            GOOGLE_ACTIONS_OPEN_BROWSER = new EventCodeType("GOOGLE_ACTIONS_OPEN_BROWSER", 51, 77);
            GOOGLE_ACTIONS_INSTALL_PROMPT_DISMISSED = new EventCodeType("GOOGLE_ACTIONS_INSTALL_PROMPT_DISMISSED", 52, 78);
            GOOGLE_ACTIONS_OPEN_RECOMMENDED_APP_UNPROMPTED = new EventCodeType("GOOGLE_ACTIONS_OPEN_RECOMMENDED_APP_UNPROMPTED", 53, 79);
            GOOGLE_APP_INSTALLED = new EventCodeType("GOOGLE_APP_INSTALLED", 54, 25);
            GOOGLE_APPS_CURRENTLY_INSTALLED = new EventCodeType("GOOGLE_APPS_CURRENTLY_INSTALLED", 55, 26);
            GOOGLE_USAGE_ID_RESET_PROMPTED = new EventCodeType("GOOGLE_USAGE_ID_RESET_PROMPTED", 56, 44);
            GOOGLE_USAGE_ID_RESET_CANCELLED = new EventCodeType("GOOGLE_USAGE_ID_RESET_CANCELLED", 57, 48);
            GOOGLE_USAGE_ID_RESET_COMPLETED = new EventCodeType("GOOGLE_USAGE_ID_RESET_COMPLETED", 58, 49);
            HELP_SHOWN = new EventCodeType("HELP_SHOWN", 59, 27);
            INTENT_CREATED = new EventCodeType("INTENT_CREATED", 60, 50);
            INTENT_EVALUATED = new EventCodeType("INTENT_EVALUATED", 61, 51);
            MENU_SHOWN = new EventCodeType("MENU_SHOWN", 62, 28);
            OPEN_URL = new EventCodeType("OPEN_URL", 63, 29);
            PRIVACY_POLICY_SHOWN = new EventCodeType("PRIVACY_POLICY_SHOWN", 64, 30);
            PROMO_TRIGGERED = new EventCodeType("PROMO_TRIGGERED", 65, 66);
            PROMO_TARGETING_EVALUATED = new EventCodeType("PROMO_TARGETING_EVALUATED", 66, 95);
            PROMO_CONDITIONS_EVALUATED = new EventCodeType("PROMO_CONDITIONS_EVALUATED", 67, 72);
            PROMO_NOT_SHOWN_DEVICE_CAPPED = new EventCodeType("PROMO_NOT_SHOWN_DEVICE_CAPPED", 68, 94);
            PROMO_NOT_SHOWN = new EventCodeType("PROMO_NOT_SHOWN", 69, 67);
            PROMO_SHOWN = new EventCodeType("PROMO_SHOWN", 70, 68);
            PROMO_USER_ACTION = new EventCodeType("PROMO_USER_ACTION", 71, 69);
            PROMO_USER_DISMISSED = new EventCodeType("PROMO_USER_DISMISSED", 72, 70);
            PROMO_SYSTEM_DISMISSED = new EventCodeType("PROMO_SYSTEM_DISMISSED", 73, 71);
            REENGAGEMENT_SETTINGS_SHOWN = new EventCodeType("REENGAGEMENT_SETTINGS_SHOWN", 74, 84);
            REENGAGEMENT_SETTINGS_DISMISSED = new EventCodeType("REENGAGEMENT_SETTINGS_DISMISSED", 75, 85);
            REENGAGEMENT_SETTINGS_MODIFIED = new EventCodeType("REENGAGEMENT_SETTINGS_MODIFIED", 76, 86);
            REENGAGEMENT_NOTIFICATION_PREFERENCES_SHOWN = new EventCodeType("REENGAGEMENT_NOTIFICATION_PREFERENCES_SHOWN", 77, 87);
            REENGAGEMENT_NOTIFICATION_PREFERENCES_SELECTED = new EventCodeType("REENGAGEMENT_NOTIFICATION_PREFERENCES_SELECTED", 78, 88);
            REENGAGEMENT_NOTIFICATION_PREFERENCES_DISMISSED = new EventCodeType("REENGAGEMENT_NOTIFICATION_PREFERENCES_DISMISSED", 79, 89);
            REENGAGEMENT_USER_TAPPED_ON_NOTIFICATION = new EventCodeType("REENGAGEMENT_USER_TAPPED_ON_NOTIFICATION", 80, 90);
            REENGAGEMENT_USER_RECEIVED_NOTIFICATION = new EventCodeType("REENGAGEMENT_USER_RECEIVED_NOTIFICATION", 81, 91);
            REENGAGEMENT_BEGIN_APP_ENGAGEMENT = new EventCodeType("REENGAGEMENT_BEGIN_APP_ENGAGEMENT", 82, 96);
            REENGAGEMENT_END_APP_ENGAGEMENT = new EventCodeType("REENGAGEMENT_END_APP_ENGAGEMENT", 83, 97);
            SETTINGS_SHOWN = new EventCodeType("SETTINGS_SHOWN", 84, 31);
            SETTINGS_MODIFIED = new EventCodeType("SETTINGS_MODIFIED", 85, 32);
            SIGN_IN_START = new EventCodeType("SIGN_IN_START", 86, 33);
            SIGN_IN_COMPLETE = new EventCodeType("SIGN_IN_COMPLETE", 87, 34);
            SIGN_OUT = new EventCodeType("SIGN_OUT", 88, 35);
            CHANGE_USER_START = new EventCodeType("CHANGE_USER_START", 89, 36);
            CHANGE_USER_COMPLETE = new EventCodeType("CHANGE_USER_COMPLETE", 90, 37);
            CREATE_USER_START = new EventCodeType("CREATE_USER_START", 91, 38);
            CREATE_USER_COMPLETE = new EventCodeType("CREATE_USER_COMPLETE", 92, 39);
            SIGN_IN_SKIPPED = new EventCodeType("SIGN_IN_SKIPPED", 93, 40);
            SMART_MAIL_CALENDAR_BUTTON_SHOWN = new EventCodeType("SMART_MAIL_CALENDAR_BUTTON_SHOWN", 94, 93);
            SMART_MAIL_CALENDAR_BUTTON_TAPPED = new EventCodeType("SMART_MAIL_CALENDAR_BUTTON_TAPPED", 95, 92);
            TRANSLATED_TEXT = new EventCodeType("TRANSLATED_TEXT", 96, 65);
            USER_AUTHORIZED_PERMISSION = new EventCodeType("USER_AUTHORIZED_PERMISSION", 97, 100);
            USER_DENIED_PERMISSION = new EventCodeType("USER_DENIED_PERMISSION", 98, 101);
            VOICE_INTERACTION_STARTED = new EventCodeType("VOICE_INTERACTION_STARTED", 99, 41);
            VOICE_INTERACTION_DISMISSED = new EventCodeType("VOICE_INTERACTION_DISMISSED", 100, 42);
            VOICE_INTERACTION_COMPLETED = new EventCodeType("VOICE_INTERACTION_COMPLETED", 101, 43);
            ANDROID_APP_SET_AS_DEFAULT = new EventCodeType("ANDROID_APP_SET_AS_DEFAULT", 102, 102);
            ANDROID_ONBOARDING_EVENT = new EventCodeType("ANDROID_ONBOARDING_EVENT", 103, 103);
            $VALUES = (new EventCodeType[] {
                UNKNOWN, ABOUT_SHOWN, ACCESSIBILITY_SETTING_CHANGED, ENTER_FOREGROUND, ENTER_BACKGROUND, ERROR, SCREEN_UPDATED, USER_ACTION, COLD_START, FIRST_USE, 
                CLIENT_VIEW_DID_APPEAR, APP_SWITCH_SHEET_SHOWN, APP_SWITCH_SHEET_DISMISSED, APP_SWITCH_OPEN_APP_STORE, APP_SWITCH_OPEN_RECOMMENDED_APP, APP_SWITCH_OPEN_SYSTEM_APP, APP_SWITCH_OPEN_EXTERNAL_APP, APP_SWITCH_OPEN_APP_DEFAULT_HANDLER, APP_SWITCH_OPEN_RECOMMENDED_APP_UNPROMPTED, APP_SWITCH_OPEN_SYSTEM_APP_UNPROMPTED, 
                APP_SWITCH_OPEN_EXTERNAL_APP_UNPROMPTED, APP_SWITCH_PREFS_SHOWN, APP_SWITCH_PREFS_DISMISSED, APP_SWITCH_PREFS_PREFERRED_APP_SET, APP_SWITCH_PREFS_PROMPT_PREFERRED_APP_ON, APP_SWITCH_PREFS_PROMPT_PREFERRED_APP_OFF, APP_SWITCH_SHEET_PROMPT_PREFERRED_APP_ON, APP_SWITCH_SHEET_PROMPT_PREFERRED_APP_OFF, APP_SWITCH_PREFS_RECOMMENDED_OPEN_APP_STORE, APP_SWITCH_PREFS_FEATURED_OPEN_APP_STORE, 
                APP_SWITCH_PREFS_INTENTS_OPEN_APP_STORE, APP_SWI