// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.timely;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.JsonReader;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.syncadapters.timely.type.CalendarType;
import com.google.android.apps.calendar.timely.contract.TimelyContract;
import com.google.android.apps.calendar.util.conscrypt.ConscryptInstallationException;
import com.google.android.apps.calendar.util.conscrypt.ConscryptUtils;
import com.google.android.common.http.Rule;
import com.google.android.common.http.RuleMatcher;
import com.google.android.common.http.UrlRules;
import com.google.common.base.Platform;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class TimelyUtils
{

    private static final String TAG = com/google/android/calendar/utils/timely/TimelyUtils.getSimpleName();

    private TimelyUtils()
    {
    }

    private static void callProviderCommand(Context context, com.google.android.apps.calendar.timely.contract.TimelyContract.ProviderCommand providercommand, Bundle bundle)
    {
        if (context == null)
        {
            LogUtils.e(TAG, "Null context, won't do %s", new Object[] {
                providercommand
            });
            return;
        }
        context = context.getContentResolver();
        if (context == null)
        {
            LogUtils.e(TAG, "Null resolver, won't do %s", new Object[] {
                providercommand
            });
            return;
        } else
        {
            context.call(TimelyContract.TIMELY_BASE_PROVIDER_URI, providercommand.name(), null, bundle);
            return;
        }
    }

    private static String encodeAddress(String s)
    {
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_28;
        }
        s = URLEncoder.encode(s, "UTF-8");
        return s;
        s;
        LogUtils.wtf(TAG, s, "Address encoding has failed.", new Object[0]);
        return "";
    }

    private static byte[] executeHttpRequest(Context context, Uri uri)
        throws IOException
    {
        byte abyte0[] = uri.toString();
        uri = context.getContentResolver();
        uri = com.google.android.common.http.UrlRules.UrlRuleFetcher.instance.getRules(uri);
        if (((UrlRules) (uri)).ruleMatcher == null)
        {
            uri.ruleMatcher = new RuleMatcher(((UrlRules) (uri)).rules);
        }
        Object obj = ((UrlRules) (uri)).ruleMatcher.match(abyte0);
        uri = ((Uri) (obj));
        if (obj == null)
        {
            uri = Rule.DEFAULT;
        }
        obj = uri.apply(abyte0);
        try
        {
            ConscryptUtils.installSecurityProvider(context);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new IOException("Failure to install Security provider", context);
        }
        if (obj == null)
        {
            context = String.valueOf(uri);
            throw new IOException((new StringBuilder(String.valueOf(context).length() + 17)).append("Blocked by rule: ").append(context).toString());
        }
        int i;
        if (!((String) (obj)).equals(abyte0))
        {
            context = ((Context) (obj));
        } else
        {
            context = abyte0;
        }
        context = (new URL(context)).openStream();
        uri = new ByteArrayOutputStream();
        abyte0 = new byte[1000];
_L1:
        i = context.read(abyte0);
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_198;
        }
        uri.write(abyte0, 0, i);
          goto _L1
        uri;
        context.close();
        throw uri;
        context.close();
        uri.flush();
        return uri.toByteArray();
    }

    public static JSONObject executeJsonRequest(Context context, Uri uri)
    {
        context = new JSONObject(new String(executeHttpRequest(context, uri)));
        return context;
        context;
        LogUtils.e(TAG, context, "Failed to execute Json request", new Object[0]);
_L2:
        return null;
        context;
        LogUtils.e(TAG, context, "Failed to execute Json request", new Object[0]);
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static String getClientUpgradedPrefKey(Account account)
    {
        int i = account.hashCode();
        return (new StringBuilder(20)).append("seenOOBE_").append(i).toString();
    }

    public static String getGoogleClientVersionPrefKey(Account account)
    {
        int i = account.hashCode();
        return (new StringBuilder(31)).append("googleClientVersion_").append(i).toString();
    }

    public static int getPlacesRadiusMeters()
    {
        return 30000;
    }

    public static String getSmartmailAckPrefKey(Account account)
    {
        int i = account.hashCode();
        return (new StringBuilder(24)).append("smartmailAck_").append(i).toString();
    }

    public static String getStaticMapsUrl(String s, String s1, String s2, int i, int j)
    {
        return "https://maps.googleapis.com/maps/api/staticmap?size=[WIDTH]x[HEIGHT]&maptype=roadmap&sensor=true&key=AIzaSyDwzOp5nlDuTO2MtXeMek6aD5e6rQs49Mk&visual_refresh=true&markers=color:red%7Clabel:dot%7C[LATITUDE],[LONGITUDE]&visible=[VISIBLE]".replace("[WIDTH]", Integer.valueOf(i).toString()).replace("[HEIGHT]", Integer.valueOf(j).toString()).replace("[LATITUDE]", s).replace("[LONGITUDE]", s1).replace("[VISIBLE]", encodeAddress(s2));
    }

    public static SharedPreferences getVersionSharedPreferences(Context context)
    {
        return context.getSharedPreferences("google_client_version_prefs", 0);
    }

    public static Map mapFromJson(String s)
    {
        HashMap hashmap;
        Object obj;
        if (Platform.stringIsNullOrEmpty(s))
        {
            return null;
        }
        hashmap = new HashMap();
        obj = new JsonReader(new StringReader(s));
        ((JsonReader) (obj)).beginObject();
_L1:
        String s1;
        String s2;
        if (!((JsonReader) (obj)).hasNext())
        {
            break MISSING_BLOCK_LABEL_112;
        }
        s1 = ((JsonReader) (obj)).nextName();
        s2 = ((JsonReader) (obj)).nextString();
        if (s1 != null && s2 != null)
        {
            try
            {
                hashmap.put(s1, s2);
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                LogUtils.i(TAG, "Error parsing JSON", new Object[0]);
                LogUtils.d(TAG, "JSON Data: %s", new Object[] {
                    s
                });
                return hashmap;
            }
        }
          goto _L1
        ((JsonReader) (obj)).endObject();
        ((JsonReader) (obj)).close();
        return hashmap;
    }

    public static void subscribeBirthdayCalendar(Context context, Account account, boolean flag, Bundle bundle)
    {
        subscribeCalendar(context, account, CalendarType.getBirthdayCalendarId(flag), bundle);
    }

    public static void subscribeCalendar(Context context, Account account, String s, Bundle bundle)
    {
        if (s == null)
        {
            LogUtils.wtf(TAG, "Attempt to subscribe to null calendar", new Object[0]);
        }
        Bundle bundle1 = bundle;
        if (bundle == null)
        {
            bundle1 = new Bundle();
        }
        bundle1.putString("ownerAccount", s);
        bundle1.putString("account_name", account.name);
        bundle1.putString("account_type", account.type);
        callProviderCommand(context, com.google.android.apps.calendar.timely.contract.TimelyContract.ProviderCommand.SUBSCRIBE_CALENDAR, bundle1);
    }

    public static void triggerSyncAdapterSyncWithExtras(Account account, String s, boolean flag, Bundle bundle)
    {
        bundle.putBoolean("force", true);
        if (s != null)
        {
            bundle.putBoolean(s, true);
        }
        if (flag)
        {
            bundle.putBoolean("upload", true);
        }
        LogUtils.d(TAG, "Requesting sync in TimelyUtils#triggerSyncAdapterSyncWithExtras", new Object[0]);
        ContentResolver.requestSync(account, android.provider.CalendarContract.Calendars.CONTENT_URI.getAuthority(), bundle);
    }

    public static void unsubscribeCalendar(Context context, Account account, String s)
    {
        Bundle bundle = new Bundle(3);
        bundle.putString("ownerAccount", s);
        bundle.putString("account_name", account.name);
        bundle.putString("account_type", account.type);
        callProviderCommand(context, com.google.android.apps.calendar.timely.contract.TimelyContract.ProviderCommand.UNSUBSCRIBE_CALENDAR, bundle);
    }

}
