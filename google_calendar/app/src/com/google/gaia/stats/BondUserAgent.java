// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.gaia.stats;

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

public final class BondUserAgent extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class App extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final App $VALUES[];
        private static final App ADSENSE_APP;
        private static final App ADWORDS_APP;
        private static final App ALLO_APP;
        private static final App ANDROID_BROWSER;
        private static final App ANDROID_DEVICE_MANAGER_APP;
        private static final App ANDROID_EMAIL_APP;
        private static final App APPLE_NATIVE_APP;
        private static final App ARTS_AND_CULTURE_APP;
        private static final App ASSISTANT_APP;
        private static final App BIS_APP;
        private static final App BOXER_APP;
        private static final App CALENDAR_APP;
        private static final App CHROME;
        private static final App CHROME_SYNC_APP;
        private static final App CHROME_WEBVIEW_APP;
        private static final App CLASSROOM_APP;
        private static final App COC_COC_BROWSER;
        private static final App DATALLY_APP;
        private static final App DEPRECATED_IOS_MAIL_APP;
        private static final App DEPRECATED_MAC_OSX_MAIL_APP;
        private static final App DOCS_APP;
        private static final App DRIVE_APP;
        private static final App DRIVE_SYNC_APP;
        private static final App DUO_APP;
        private static final App EARTH_APP;
        private static final App EDGE;
        private static final App EXPRESS_APP;
        private static final App FAMILY_LINK_APP;
        private static final App FILES_GO_APP;
        private static final App FIREFOX;
        private static final App GBOARD_APP;
        private static final App GMAIL_APP;
        private static final App GMAIL_INBOX_APP;
        private static final App GMM_APP;
        private static final App GOOGLE_ANALYTICS_APP;
        private static final App GOOGLE_GO_APP;
        private static final App GOOGLE_MY_BUSINESS_APP;
        private static final App GOOGLE_PAY_APP;
        private static final App GSA_APP;
        private static final App HANGOUTS_APP;
        private static final App HOME_APP;
        private static final App KEEP_APP;
        private static final App LIMILABS_MAIL_DLL;
        private static final App LOGDOG_APP;
        private static final App MOTO_EMAIL_APP;
        private static final App MSIE;
        private static final App MSIE_COMPATIBILITY;
        private static final App NEWS_APP;
        private static final App OPERA;
        private static final App OPINION_REWARDS_APP;
        private static final App OTHER_APP;
        private static final App OTHER_BROWSER;
        private static final App OUTLOOK_MAIL_APP;
        private static final App PHOTOS_APP;
        private static final App PLAY_APP;
        private static final App PLAY_BOOKS_APP;
        private static final App PLAY_GAMES_APP;
        private static final App PLAY_MOVIES_APP;
        private static final App PLAY_MUSIC_APP;
        private static final App PLAY_NEWSSTAND_APP;
        private static final App PLUS_APP;
        private static final App POKEMON_GO_APP;
        private static final App SAFARI;
        private static final App SAFARI_WEBVIEW_APP;
        private static final App SAMSUNG_BROWSER;
        private static final App SAMSUNG_MAIL_APP;
        private static final App SHEETS_APP;
        private static final App SILK_BROWSER;
        private static final App SLIDES_APP;
        private static final App STREET_VIEW_APP;
        private static final App TEZ_APP;
        private static final App TRANSLATE_APP;
        private static final App TRIPS_APP;
        private static final App UC_BROWSER;
        private static final App VOICE_APP;
        private static final App WALLET_APP;
        private static final App WAZE_APP;
        private static final App WEAR_APP;
        private static final App WHATS_APP_IN_DRIVE_APP;
        private static final App WIFI_APP;
        private static final App YANDEX_BROWSER;
        private static final App YOUTUBE_APP;
        private static final App YOUTUBE_CAPTURE_APP;
        private static final App YOUTUBE_CREATOR_APP;
        private static final App YOUTUBE_GAMING_APP;
        private static final App YOUTUBE_GO_APP;
        private static final App YOUTUBE_KIDS_APP;
        private static final App YOUTUBE_MUSIC_APP;
        private static final App YOUTUBE_TV_APP;
        private static final App YOUTUBE_VR_APP;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        private final int value;

        public static App forNumber(int i)
        {
            switch (i)
            {
            case 7: // '\007'
            case 16: // '\020'
            case 17: // '\021'
            case 18: // '\022'
            case 19: // '\023'
            case 20: // '\024'
            case 21: // '\025'
            case 22: // '\026'
            case 23: // '\027'
            case 24: // '\030'
            case 25: // '\031'
            case 26: // '\032'
            case 27: // '\033'
            case 28: // '\034'
            case 29: // '\035'
            case 30: // '\036'
            case 31: // '\037'
            case 32: // ' '
            case 33: // '!'
            case 34: // '"'
            case 35: // '#'
            case 36: // '$'
            case 37: // '%'
            case 38: // '&'
            case 39: // '\''
            case 40: // '('
            case 41: // ')'
            case 42: // '*'
            case 43: // '+'
            case 44: // ','
            case 45: // '-'
            case 46: // '.'
            case 47: // '/'
            case 48: // '0'
            case 49: // '1'
            case 50: // '2'
            case 51: // '3'
            case 52: // '4'
            case 53: // '5'
            case 54: // '6'
            case 55: // '7'
            case 56: // '8'
            case 57: // '9'
            case 58: // ':'
            case 59: // ';'
            case 60: // '<'
            case 61: // '='
            case 62: // '>'
            case 63: // '?'
            case 64: // '@'
            case 65: // 'A'
            case 66: // 'B'
            case 67: // 'C'
            case 68: // 'D'
            case 69: // 'E'
            case 70: // 'F'
            case 71: // 'G'
            case 72: // 'H'
            case 73: // 'I'
            case 74: // 'J'
            case 75: // 'K'
            case 76: // 'L'
            case 77: // 'M'
            case 78: // 'N'
            case 79: // 'O'
            case 80: // 'P'
            case 81: // 'Q'
            case 82: // 'R'
            case 83: // 'S'
            case 84: // 'T'
            case 85: // 'U'
            case 86: // 'V'
            case 87: // 'W'
            case 88: // 'X'
            case 89: // 'Y'
            case 90: // 'Z'
            case 91: // '['
            case 92: // '\\'
            case 93: // ']'
            case 94: // '^'
            case 95: // '_'
            case 96: // '`'
            case 97: // 'a'
            case 98: // 'b'
            case 99: // 'c'
            case 100: // 'd'
            default:
                return null;

            case 0: // '\0'
                return OTHER_APP;

            case 1: // '\001'
                return CHROME;

            case 2: // '\002'
                return FIREFOX;

            case 3: // '\003'
                return MSIE;

            case 4: // '\004'
                return SAFARI;

            case 6: // '\006'
                return OPERA;

            case 8: // '\b'
                return EDGE;

            case 9: // '\t'
                return MSIE_COMPATIBILITY;

            case 5: // '\005'
                return OTHER_BROWSER;

            case 10: // '\n'
                return SAMSUNG_BROWSER;

            case 11: // '\013'
                return UC_BROWSER;

            case 12: // '\f'
                return ANDROID_BROWSER;

            case 13: // '\r'
                return YANDEX_BROWSER;

            case 14: // '\016'
                return SILK_BROWSER;

            case 15: // '\017'
                return COC_COC_BROWSER;

            case 107: // 'k'
                return GMAIL_APP;

            case 113: // 'q'
                return GMAIL_INBOX_APP;

            case 133: 
                return ANDROID_EMAIL_APP;

            case 136: 
                return SAMSUNG_MAIL_APP;

            case 139: 
                return MOTO_EMAIL_APP;

            case 140: 
                return BOXER_APP;

            case 141: 
                return LIMILABS_MAIL_DLL;

            case 142: 
                return BIS_APP;

            case 137: 
                return OUTLOOK_MAIL_APP;

            case 138: 
                return APPLE_NATIVE_APP;

            case 112: // 'p'
                return CHROME_WEBVIEW_APP;

            case 122: // 'z'
                return SAFARI_WEBVIEW_APP;

            case 105: // 'i'
                return CHROME_SYNC_APP;

            case 101: // 'e'
                return GSA_APP;

            case 103: // 'g'
                return GMM_APP;

            case 108: // 'l'
                return CALENDAR_APP;

            case 109: // 'm'
                return PLUS_APP;

            case 110: // 'n'
                return HANGOUTS_APP;

            case 111: // 'o'
                return VOICE_APP;

            case 114: // 'r'
                return PHOTOS_APP;

            case 106: // 'j'
                return DRIVE_SYNC_APP;

            case 116: // 't'
                return DRIVE_APP;

            case 115: // 's'
                return DOCS_APP;

            case 117: // 'u'
                return SHEETS_APP;

            case 120: // 'x'
                return SLIDES_APP;

            case 118: // 'v'
                return KEEP_APP;

            case 121: // 'y'
                return WHATS_APP_IN_DRIVE_APP;

            case 119: // 'w'
                return TRANSLATE_APP;

            case 104: // 'h'
                return YOUTUBE_APP;

            case 123: // '{'
                return YOUTUBE_MUSIC_APP;

            case 124: // '|'
                return YOUTUBE_GAMING_APP;

            case 125: // '}'
                return YOUTUBE_KIDS_APP;

            case 126: // '~'
                return YOUTUBE_CAPTURE_APP;

            case 127: // '\177'
                return YOUTUBE_CREATOR_APP;

            case 150: 
                return YOUTUBE_GO_APP;

            case 151: 
                return YOUTUBE_TV_APP;

            case 157: 
                return YOUTUBE_VR_APP;

            case 102: // 'f'
                return PLAY_APP;

            case 128: 
                return PLAY_MUSIC_APP;

            case 129: 
                return PLAY_BOOKS_APP;

            case 130: 
                return PLAY_MOVIES_APP;

            case 131: 
                return PLAY_NEWSSTAND_APP;

            case 132: 
                return PLAY_GAMES_APP;

            case 143: 
                return POKEMON_GO_APP;

            case 144: 
                return ALLO_APP;

            case 145: 
                return DUO_APP;

            case 147: 
                return CLASSROOM_APP;

            case 148: 
                return TRIPS_APP;

            case 149: 
                return GOOGLE_PAY_APP;

            case 152: 
                return WAZE_APP;

            case 153: 
                return ASSISTANT_APP;

            case 154: 
                return GBOARD_APP;

            case 155: 
                return NEWS_APP;

            case 156: 
                return HOME_APP;

            case 158: 
                return EARTH_APP;

            case 159: 
                return STREET_VIEW_APP;

            case 160: 
                return TEZ_APP;

            case 161: 
                return GOOGLE_ANALYTICS_APP;

            case 162: 
                return ADSENSE_APP;

            case 163: 
                return ADWORDS_APP;

            case 164: 
                return EXPRESS_APP;

            case 165: 
                return WEAR_APP;

            case 166: 
                return GOOGLE_MY_BUSINESS_APP;

            case 167: 
                return FAMILY_LINK_APP;

            case 168: 
                return OPINION_REWARDS_APP;

            case 169: 
                return WALLET_APP;

            case 170: 
                return ARTS_AND_CULTURE_APP;

            case 171: 
                return ANDROID_DEVICE_MANAGER_APP;

            case 172: 
                return GOOGLE_GO_APP;

            case 173: 
                return FILES_GO_APP;

            case 174: 
                return DATALLY_APP;

            case 175: 
                return WIFI_APP;

            case 146: 
                return LOGDOG_APP;

            case 134: 
                return DEPRECATED_MAC_OSX_MAIL_APP;

            case 135: 
                return DEPRECATED_IOS_MAIL_APP;
            }
        }

        public static App[] values()
        {
            return (App[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            OTHER_APP = new App("OTHER_APP", 0, 0);
            CHROME = new App("CHROME", 1, 1);
            FIREFOX = new App("FIREFOX", 2, 2);
            MSIE = new App("MSIE", 3, 3);
            SAFARI = new App("SAFARI", 4, 4);
            OPERA = new App("OPERA", 5, 6);
            EDGE = new App("EDGE", 6, 8);
            MSIE_COMPATIBILITY = new App("MSIE_COMPATIBILITY", 7, 9);
            OTHER_BROWSER = new App("OTHER_BROWSER", 8, 5);
            SAMSUNG_BROWSER = new App("SAMSUNG_BROWSER", 9, 10);
            UC_BROWSER = new App("UC_BROWSER", 10, 11);
            ANDROID_BROWSER = new App("ANDROID_BROWSER", 11, 12);
            YANDEX_BROWSER = new App("YANDEX_BROWSER", 12, 13);
            SILK_BROWSER = new App("SILK_BROWSER", 13, 14);
            COC_COC_BROWSER = new App("COC_COC_BROWSER", 14, 15);
            GMAIL_APP = new App("GMAIL_APP", 15, 107);
            GMAIL_INBOX_APP = new App("GMAIL_INBOX_APP", 16, 113);
            ANDROID_EMAIL_APP = new App("ANDROID_EMAIL_APP", 17, 133);
            SAMSUNG_MAIL_APP = new App("SAMSUNG_MAIL_APP", 18, 136);
            MOTO_EMAIL_APP = new App("MOTO_EMAIL_APP", 19, 139);
            BOXER_APP = new App("BOXER_APP", 20, 140);
            LIMILABS_MAIL_DLL = new App("LIMILABS_MAIL_DLL", 21, 141);
            BIS_APP = new App("BIS_APP", 22, 142);
            OUTLOOK_MAIL_APP = new App("OUTLOOK_MAIL_APP", 23, 137);
            APPLE_NATIVE_APP = new App("APPLE_NATIVE_APP", 24, 138);
            CHROME_WEBVIEW_APP = new App("CHROME_WEBVIEW_APP", 25, 112);
            SAFARI_WEBVIEW_APP = new App("SAFARI_WEBVIEW_APP", 26, 122);
            CHROME_SYNC_APP = new App("CHROME_SYNC_APP", 27, 105);
            GSA_APP = new App("GSA_APP", 28, 101);
            GMM_APP = new App("GMM_APP", 29, 103);
            CALENDAR_APP = new App("CALENDAR_APP", 30, 108);
            PLUS_APP = new App("PLUS_APP", 31, 109);
            HANGOUTS_APP = new App("HANGOUTS_APP", 32, 110);
            VOICE_APP = new App("VOICE_APP", 33, 111);
            PHOTOS_APP = new App("PHOTOS_APP", 34, 114);
            DRIVE_SYNC_APP = new App("DRIVE_SYNC_APP", 35, 106);
            DRIVE_APP = new App("DRIVE_APP", 36, 116);
            DOCS_APP = new App("DOCS_APP", 37, 115);
            SHEETS_APP = new App("SHEETS_APP", 38, 117);
            SLIDES_APP = new App("SLIDES_APP", 39, 120);
            KEEP_APP = new App("KEEP_APP", 40, 118);
            WHATS_APP_IN_DRIVE_APP = new App("WHATS_APP_IN_DRIVE_APP", 41, 121);
            TRANSLATE_APP = new App("TRANSLATE_APP", 42, 119);
            YOUTUBE_APP = new App("YOUTUBE_APP", 43, 104);
            YOUTUBE_MUSIC_APP = new App("YOUTUBE_MUSIC_APP", 44, 123);
            YOUTUBE_GAMING_APP = new App("YOUTUBE_GAMING_APP", 45, 124);
            YOUTUBE_KIDS_APP = new App("YOUTUBE_KIDS_APP", 46, 125);
            YOUTUBE_CAPTURE_APP = new App("YOUTUBE_CAPTURE_APP", 47, 126);
            YOUTUBE_CREATOR_APP = new App("YOUTUBE_CREATOR_APP", 48, 127);
            YOUTUBE_GO_APP = new App("YOUTUBE_GO_APP", 49, 150);
            YOUTUBE_TV_APP = new App("YOUTUBE_TV_APP", 50, 151);
            YOUTUBE_VR_APP = new App("YOUTUBE_VR_APP", 51, 157);
            PLAY_APP = new App("PLAY_APP", 52, 102);
            PLAY_MUSIC_APP = new App("PLAY_MUSIC_APP", 53, 128);
            PLAY_BOOKS_APP = new App("PLAY_BOOKS_APP", 54, 129);
            PLAY_MOVIES_APP = new App("PLAY_MOVIES_APP", 55, 130);
            PLAY_NEWSSTAND_APP = new App("PLAY_NEWSSTAND_APP", 56, 131);
            PLAY_GAMES_APP = new App("PLAY_GAMES_APP", 57, 132);
            POKEMON_GO_APP = new App("POKEMON_GO_APP", 58, 143);
            ALLO_APP = new App("ALLO_APP", 59, 144);
            DUO_APP = new App("DUO_APP", 60, 145);
            CLASSROOM_APP = new App("CLASSROOM_APP", 61, 147);
            TRIPS_APP = new App("TRIPS_APP", 62, 148);
            GOOGLE_PAY_APP = new App("GOOGLE_PAY_APP", 63, 149);
            WAZE_APP = new App("WAZE_APP", 64, 152);
            ASSISTANT_APP = new App("ASSISTANT_APP", 65, 153);
            GBOARD_APP = new App("GBOARD_APP", 66, 154);
            NEWS_APP = new App("NEWS_APP", 67, 155);
            HOME_APP = new App("HOME_APP", 68, 156);
            EARTH_APP = new App("EARTH_APP", 69, 158);
            STREET_VIEW_APP = new App("STREET_VIEW_APP", 70, 159);
            TEZ_APP = new App("TEZ_APP", 71, 160);
            GOOGLE_ANALYTICS_APP = new App("GOOGLE_ANALYTICS_APP", 72, 161);
            ADSENSE_APP = new App("ADSENSE_APP", 73, 162);
            ADWORDS_APP = new App("ADWORDS_APP", 74, 163);
            EXPRESS_APP = new App("EXPRESS_APP", 75, 164);
            WEAR_APP = new App("WEAR_APP", 76, 165);
            GOOGLE_MY_BUSINESS_APP = new App("GOOGLE_MY_BUSINESS_APP", 77, 166);
            FAMILY_LINK_APP = new App("FAMILY_LINK_APP", 78, 167);
            OPINION_REWARDS_APP = new App("OPINION_REWARDS_APP", 79, 168);
            WALLET_APP = new App("WALLET_APP", 80, 169);
            ARTS_AND_CULTURE_APP = new App("ARTS_AND_CULTURE_APP", 81, 170);
            ANDROID_DEVICE_MANAGER_APP = new App("ANDROID_DEVICE_MANAGER_APP", 82, 171);
            GOOGLE_GO_APP = new App("GOOGLE_GO_APP", 83, 172);
            FILES_GO_APP = new App("FILES_GO_APP", 84, 173);
            DATALLY_APP = new App("DATALLY_APP", 85, 174);
            WIFI_APP = new App("WIFI_APP", 86, 175);
            LOGDOG_APP = new App("LOGDOG_APP", 87, 146);
            DEPRECATED_MAC_OSX_MAIL_APP = new App("DEPRECATED_MAC_OSX_MAIL_APP", 88, 134);
            DEPRECATED_IOS_MAIL_APP = new App("DEPRECATED_IOS_MAIL_APP", 89, 135);
            $VALUES = (new App[] {
                OTHER_APP, CHROME, FIREFOX, MSIE, SAFARI, OPERA, EDGE, MSIE_COMPATIBILITY, OTHER_BROWSER, SAMSUNG_BROWSER, 
                UC_BROWSER, ANDROID_BROWSER, YANDEX_BROWSER, SILK_BROWSER, COC_COC_BROWSER, GMAIL_APP, GMAIL_INBOX_APP, ANDROID_EMAIL_APP, SAMSUNG_MAIL_APP, MOTO_EMAIL_APP, 
                BOXER_APP, LIMILABS_MAIL_DLL, BIS_APP, OUTLOOK_MAIL_APP, APPLE_NATIVE_APP, CHROME_WEBVIEW_APP, SAFARI_WEBVIEW_APP, CHROME_SYNC_APP, GSA_APP, GMM_APP, 
                CALENDAR_APP, PLUS_APP, HANGOUTS_APP, VOICE_APP, PHOTOS_APP, DRIVE_SYNC_APP, DRIVE_APP, DOCS_APP, SHEETS_APP, SLIDES_APP, 
                KEEP_APP, WHATS_APP_IN_DRIVE_APP, TRANSLATE_APP, YOUTUBE_APP, YOUTUBE_MUSIC_APP, YOUTUBE_GAMING_APP, YOUTUBE_KIDS_APP, YOUTUBE_CAPTURE_APP, YOUTUBE_CREATOR_APP, YOUTUBE_GO_APP, 
                YOUTUBE_TV_APP, YOUTUBE_VR_APP, PLAY_APP, PLAY_MUSIC_APP, PLAY_BOOKS_APP, PLAY_MOVIES_APP, PLAY_NEWSSTAND_APP, PLAY_GAMES_APP, POKEMON_GO_APP, ALLO_APP, 
                DUO_APP, CLASSROOM_APP, TRIPS_APP, GOOGLE_PAY_APP, WAZE_APP, ASSISTANT_APP, GBOARD_APP, NEWS_APP, HOME_APP, EARTH_APP, 
                STREET_VIEW_APP, TEZ_APP, GOOGLE_ANALYTICS_APP, ADSENSE_APP, ADWORDS_APP, EXPRESS_APP, WEAR_APP, GOOGLE_MY_BUSINESS_APP, FAMILY_LINK_APP, OPINION_REWARDS_APP, 
                WALLET_APP, ARTS_AND_CULTURE_APP, ANDROID_DEVICE_MANAGER_APP, GOOGLE_GO_APP, FILES_GO_APP, DATALLY_APP, WIFI_APP, LOGDOG_APP, DEPRECATED_MAC_OSX_MAIL_APP, DEPRECATED_IOS_MAIL_APP
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return App.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private App(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(BondUserAgent.DEFAULT_INSTANCE);
        }
    }

    public static final class Os extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final Os $VALUES[];
        public static final Os ANDROID_OS;
        private static final Os ANDROID_THINGS_OS;
        private static final Os APPLE_TV_OS;
        private static final Os BLACKBERRY_OS;
        private static final Os CAST_OS;
        private static final Os CHROME_OS;
        private static final Os FIRE_OS;
        private static final Os IOS_OS;
        private static final Os KAI_OS;
        private static final Os LINUX_OS;
        private static final Os MAC_OS;
        private static final Os MAX_MOBILE_OS_VALUE;
        private static final Os PLAYSTATION_OS;
        private static final Os TIZEN_OS;
        private static final Os UNKNOWN_OS;
        private static final Os WINDOWS_OS;
        private static final Os WIN_PHONE_OS;
        private static final Os XBOX_OS;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static Os forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return UNKNOWN_OS;

            case 1: // '\001'
                return ANDROID_OS;

            case 2: // '\002'
                return IOS_OS;

            case 3: // '\003'
                return BLACKBERRY_OS;

            case 4: // '\004'
                return WIN_PHONE_OS;

            case 5: // '\005'
                return FIRE_OS;

            case 99: // 'c'
                return MAX_MOBILE_OS_VALUE;

            case 101: // 'e'
                return WINDOWS_OS;

            case 102: // 'f'
                return LINUX_OS;

            case 103: // 'g'
                return MAC_OS;

            case 104: // 'h'
                return CHROME_OS;

            case 110: // 'n'
                return PLAYSTATION_OS;

            case 111: // 'o'
                return XBOX_OS;

            case 112: // 'p'
                return TIZEN_OS;

            case 113: // 'q'
                return APPLE_TV_OS;

            case 114: // 'r'
                return KAI_OS;

            case 115: // 's'
                return ANDROID_THINGS_OS;

            case 116: // 't'
                return CAST_OS;
            }
        }

        public static Os[] values()
        {
            return (Os[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            UNKNOWN_OS = new Os("UNKNOWN_OS", 0, 0);
            ANDROID_OS = new Os("ANDROID_OS", 1, 1);
            IOS_OS = new Os("IOS_OS", 2, 2);
            BLACKBERRY_OS = new Os("BLACKBERRY_OS", 3, 3);
            WIN_PHONE_OS = new Os("WIN_PHONE_OS", 4, 4);
            FIRE_OS = new Os("FIRE_OS", 5, 5);
            MAX_MOBILE_OS_VALUE = new Os("MAX_MOBILE_OS_VALUE", 6, 99);
            WINDOWS_OS = new Os("WINDOWS_OS", 7, 101);
            LINUX_OS = new Os("LINUX_OS", 8, 102);
            MAC_OS = new Os("MAC_OS", 9, 103);
            CHROME_OS = new Os("CHROME_OS", 10, 104);
            PLAYSTATION_OS = new Os("PLAYSTATION_OS", 11, 110);
            XBOX_OS = new Os("XBOX_OS", 12, 111);
            TIZEN_OS = new Os("TIZEN_OS", 13, 112);
            APPLE_TV_OS = new Os("APPLE_TV_OS", 14, 113);
            KAI_OS = new Os("KAI_OS", 15, 114);
            ANDROID_THINGS_OS = new Os("ANDROID_THINGS_OS", 16, 115);
            CAST_OS = new Os("CAST_OS", 17, 116);
            $VALUES = (new Os[] {
                UNKNOWN_OS, ANDROID_OS, IOS_OS, BLACKBERRY_OS, WIN_PHONE_OS, FIRE_OS, MAX_MOBILE_OS_VALUE, WINDOWS_OS, LINUX_OS, MAC_OS, 
                CHROME_OS, PLAYSTATION_OS, XBOX_OS, TIZEN_OS, APPLE_TV_OS, KAI_OS, ANDROID_THINGS_OS, CAST_OS
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return Os.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private Os(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }


    public static final BondUserAgent DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    private byte memoizedIsInitialized;
    public com.google.common.net.useragent.UserAgentPb.UserAgentProto userAgentInternal_;

    private BondUserAgent()
    {
        memoizedIsInitialized = 2;
    }

    public static BondUserAgent parseFrom(InputStream inputstream)
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
        return (BondUserAgent)obj;
    }

    protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
    {
        boolean flag = false;
        i - 1;
        JVM INSTR tableswitch 0 6: default 48
    //                   0 154
    //                   1 162
    //                   2 74
    //                   3 56
    //                   4 66
    //                   5 101
    //                   6 105;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        throw new UnsupportedOperationException();
_L5:
        obj = new BondUserAgent();
_L10:
        return obj;
_L6:
        return new Builder();
_L4:
        return new RawMessageInfo(DEFAULT_INSTANCE, "\001\001\000\001\001\001\001\000\000\001\001\u0409\0", new Object[] {
            "bitField0_", "userAgentInternal_"
        });
_L7:
        return DEFAULT_INSTANCE;
_L8:
        Parser parser;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L10; else goto _L9
_L9:
        com/google/gaia/stats/BondUserAgent;
        JVM INSTR monitorenter ;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L12; else goto _L11
_L11:
        obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
        PARSER = ((Parser) (obj));
_L12:
        com/google/gaia/stats/BondUserAgent;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        com/google/gaia/stats/BondUserAgent;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
        return Byte.valueOf(memoizedIsInitialized);
_L3:
        if (obj == null)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 1;
        }
        memoizedIsInitialized = (byte)i;
        return null;
    }

    static 
    {
        DEFAULT_INSTANCE = new BondUserAgent();
        BondUserAgent bonduseragent = DEFAULT_INSTANCE;
        GeneratedMessageLite.defaultInstanceMap.put(com/google/gaia/stats/BondUserAgent, bonduseragent);
    }
}
