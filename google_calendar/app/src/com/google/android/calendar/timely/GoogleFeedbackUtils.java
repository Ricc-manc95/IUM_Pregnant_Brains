// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.accounts.Account;
import android.app.Activity;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.text.format.Time;
import android.view.View;
import android.view.Window;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.experiments.ExperimentConfiguration;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.syncadapters.timely.sql.ColumnConstants;
import com.google.android.apps.calendar.usernotificationsframework.logging.NotificationLog;
import com.google.android.calendar.FragmentDialogStarter;
import com.google.android.calendar.PrivacyPolicyActivity;
import com.google.android.calendar.Utils;
import com.google.android.calendar.alerts.AlertUtils;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.android.calendar.v2a.UnifiedSyncUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.feedback.FileTeleporter;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.gms.googlehelp.internal.common.OverflowMenuItem;
import com.google.android.libraries.social.licenses.LicenseMenuActivity;
import com.google.android.syncadapters.calendar.CalendarSyncStateUtils;
import com.google.android.syncadapters.calendar.SyncLog;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.io.CharSource;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class GoogleFeedbackUtils
{

    private static final String INSTANCES_PROJECTION[] = {
        "begin", "end", "dtstart", "dtend", "duration", "allDay", "title", "_sync_id", "eventStatus", "organizer", 
        "isOrganizer", "selfAttendeeStatus", "_id", "event_id", "calendar_id", "ownerAccount", "eventTimezone", "eventEndTimezone", "original_id", "original_sync_id", 
        "originalInstanceTime", "originalAllDay", "dirty AS dirty", "mutators AS mutators", "sync_data1 AS uid", "sync_data2 AS sequence", "sync_data3 AS attendee_self_id", "sync_data4 AS etag", "sync_data5 AS modified", "sync_data8 AS habit_id_and_type", 
        "sync_data9 AS extras", "rrule", "rdate", "exrule", "exdate"
    };
    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/GoogleFeedbackUtils);
    private static final HashSet UNLOGGED_PREFS = new HashSet(Arrays.asList(new String[] {
        "preferences_quick_responses"
    }));
    public static GoogleApiClient apiClient;

    public GoogleFeedbackUtils()
    {
    }

    static void addDataToFeedback(com.google.android.gms.feedback.FeedbackOptions.Builder builder, String s, ListenableFuture listenablefuture)
    {
        try
        {
            listenablefuture = (String)listenablefuture.get();
            if (!TextUtils.isEmpty(listenablefuture))
            {
                listenablefuture = listenablefuture.getBytes();
                builder.mFileTeleporters.add(new FileTeleporter(listenablefuture, "text/plain", s));
            }
            return;
        }
        // Misplaced declaration of an exception variable
        catch (com.google.android.gms.feedback.FeedbackOptions.Builder builder)
        {
            LogUtils.e(TAG, builder, "Error obtaining %s.", new Object[] {
                s
            });
        }
    }

    public static void addEssentialDataToFeedback(com.google.android.gms.feedback.FeedbackOptions.Builder builder, Context context)
    {
        if (SyncLog.hasSyncLog(context))
        {
            byte abyte0[] = SyncLog.getCombinedSyncLogByteArray(context);
            builder.mFileTeleporters.add(new FileTeleporter(abyte0, "text/plain", "sync_log"));
        }
        if (SyncLog.hasSyncHistory(context))
        {
            byte abyte1[] = SyncLog.getCombinedSyncHistoryByteArray(context);
            builder.mFileTeleporters.add(new FileTeleporter(abyte1, "text/plain", "sync_history"));
        }
        addDataToFeedback(builder, "uss_sync_history", UnifiedSyncUtils.getSyncLogs(context, 0x400000L));
        addDataToFeedback(builder, "uss_sync_log", UnifiedSyncUtils.getSyncTraces(context, 0x400000L));
        Object obj = NotificationLog.getLogsDirectory(context).listFiles();
        if (obj != null) goto _L2; else goto _L1
_L1:
        obj = ImmutableList.of();
_L9:
        if (!(obj instanceof FluentIterable)) goto _L4; else goto _L3
_L3:
        obj = (FluentIterable)obj;
_L10:
        Object obj1;
        obj1 = com.google.calendar.v2a.android.util.files.FileUtils..Lambda._cls0.$instance;
        obj = (Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj);
        if (obj != null) goto _L6; else goto _L5
_L5:
        try
        {
            throw new NullPointerException();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            LogUtils.e(TAG, ((Throwable) (obj)), "Error obtaining notification logs.", new Object[0]);
        }
_L11:
        obj = ExperimentConfiguration.getActiveExperiments(context);
        context = RemoteFeatureConfig.getEnabledFeatures();
        obj = String.valueOf(obj);
        context = String.valueOf(context);
        Object obj2;
        int i;
        int j;
        long l;
        long l1;
        if (context.length() != 0)
        {
            context = ((String) (obj)).concat(context);
        } else
        {
            context = new String(((String) (obj)));
        }
        builder.mPsdBundle.putString("active_experiments", context);
        return;
_L2:
        Arrays.sort(((Object []) (obj)), com.google.calendar.v2a.android.util.files.FileUtils..Lambda._cls1.$instance);
        if (0x80000L <= 0L)
        {
            break MISSING_BLOCK_LABEL_327;
        }
        l = 0L;
        obj1 = ImmutableList.builder();
        j = obj.length;
        i = 0;
_L8:
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        obj2 = obj[i];
        l1 = ((File) (obj2)).length();
        if (l != 0L && l + l1 > 0x80000L)
        {
            break; /* Loop/switch isn't completed */
        }
        l += l1;
        obj2 = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj1)).add(obj2);
        i++;
        if (true) goto _L8; else goto _L7
_L7:
        obj1.forceCopy = true;
        obj = ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (obj1)).contents, ((com.google.common.collect.ImmutableList.Builder) (obj1)).size).reverse();
          goto _L9
        obj = ImmutableList.copyOf(((Object []) (obj))).reverse();
          goto _L9
_L4:
        obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
          goto _L10
_L6:
        if (obj1 != null)
        {
            break MISSING_BLOCK_LABEL_363;
        }
        throw new NullPointerException();
        obj = new com.google.common.collect.Iterables._cls5(((Iterable) (obj)), ((com.google.common.base.Function) (obj1)));
        if (!(obj instanceof FluentIterable))
        {
            break MISSING_BLOCK_LABEL_435;
        }
        obj = (FluentIterable)obj;
_L12:
        obj = (new com.google.common.io.CharSource.ConcatenatedCharSource(((Iterable) (obj)))).read();
        if (!TextUtils.isEmpty(((CharSequence) (obj))))
        {
            obj = ((String) (obj)).getBytes();
            builder.mFileTeleporters.add(new FileTeleporter(((byte []) (obj)), "text/plain", "notification_log"));
        }
          goto _L11
        obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
          goto _L12
    }

    private static void dumpCursor(StringBuilder stringbuilder, String s, Cursor cursor)
    {
        if (cursor == null)
        {
            return;
        }
        stringbuilder.append('\n').append(String.format(s, new Object[] {
            Integer.valueOf(cursor.getCount())
        })).append('\n');
        int i = 0;
_L2:
        if (i >= cursor.getColumnCount())
        {
            break; /* Loop/switch isn't completed */
        }
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_63;
        }
        stringbuilder.append(',');
        stringbuilder.append(sanitizeCsvValue(cursor.getColumnName(i)));
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        stringbuilder.append('\n');
_L5:
        if (!cursor.moveToNext())
        {
            break MISSING_BLOCK_LABEL_165;
        }
        i = 0;
_L4:
        if (i >= cursor.getColumnCount())
        {
            break; /* Loop/switch isn't completed */
        }
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_124;
        }
        stringbuilder.append(',');
        stringbuilder.append(sanitizeCsvValue(cursor.getString(i)));
        i++;
        if (true) goto _L4; else goto _L3
_L3:
        stringbuilder.append('\n');
          goto _L5
        stringbuilder;
        cursor.close();
        throw stringbuilder;
        cursor.close();
        return;
    }

    static byte[] dumpStoreData(Context context)
    {
        ContentProviderClient contentproviderclient;
        Account aaccount[];
        StringBuilder stringbuilder;
        int i;
        int j;
        contentproviderclient = context.getContentResolver().acquireContentProviderClient("com.android.calendar");
        if (contentproviderclient == null)
        {
            return null;
        }
        aaccount = AccountsUtil.getGoogleAccounts(context);
        stringbuilder = new StringBuilder();
        j = aaccount.length;
        i = 0;
_L9:
        Account account;
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_344;
        }
        account = aaccount[i];
        context = new String[2];
        context[0] = account.name;
        context[1] = account.type;
        stringbuilder.append(String.format("===== BEGIN %s =====\n", new Object[] {
            account.name
        }));
        stringbuilder.append(String.format("\n=== Sync state: %s\n", new Object[] {
            CalendarSyncStateUtils.getIfAvailable(contentproviderclient, account)
        }));
        dumpCursor(stringbuilder, "=== Calendars: %d", contentproviderclient.query(android.provider.CalendarContract.Calendars.CONTENT_URI, null, ColumnConstants.WHERE_ACCOUNT_AND_TYPE, context, "ownerAccount"));
        if (Clock.mockedTimestamp <= 0L) goto _L2; else goto _L1
_L1:
        long l = Clock.mockedTimestamp;
_L5:
        long l1;
        l1 = l - 0x240c8400L;
        l = 0x240c8400L + l;
        Object obj;
        obj = android.provider.CalendarContract.Instances.CONTENT_URI.buildUpon();
        ContentUris.appendId(((android.net.Uri.Builder) (obj)), l1);
        ContentUris.appendId(((android.net.Uri.Builder) (obj)), l);
        obj = contentproviderclient.query(((android.net.Uri.Builder) (obj)).build(), INSTANCES_PROJECTION, ColumnConstants.WHERE_ACCOUNT_AND_TYPE, context, "begin,end");
        context = String.valueOf((new StringBuilder(45)).append("(").append(l1).append(" - ").append(l).append(")").toString());
        if (context.length() == 0) goto _L4; else goto _L3
_L3:
        context = "=== Instances: %d ".concat(context);
_L6:
        dumpCursor(stringbuilder, context, ((Cursor) (obj)));
_L7:
        stringbuilder.append(String.format("\n===== END %s =====\n\n\n", new Object[] {
            account.name
        }));
        i++;
        continue; /* Loop/switch isn't completed */
_L2:
        l = System.currentTimeMillis();
          goto _L5
_L4:
        context = new String("=== Instances: %d ");
          goto _L6
        context;
        ThrowableExtension.STRATEGY.printStackTrace(context);
          goto _L7
        contentproviderclient.release();
        return stringbuilder.toString().getBytes();
        if (true) goto _L9; else goto _L8
_L8:
    }

    private static Intent getUriViewIntent(Activity activity, Uri uri)
    {
        uri = new Intent("android.intent.action.VIEW", uri);
        uri.putExtra("com.android.browser.application_id", activity.getPackageName());
        uri.addFlags(0x80000);
        return uri;
    }

    public static void launchGoogleFeedback(Activity activity, Bundle bundle)
    {
        launchGoogleFeedback(activity, bundle, false);
    }

    public static void launchGoogleFeedback(Activity activity, Bundle bundle, boolean flag)
    {
        Object obj;
        View view;
        if (activity instanceof FragmentDialogStarter)
        {
            obj = ((FragmentDialogStarter)activity).getFragmentView();
        } else
        {
            obj = null;
        }
        view = ((View) (obj));
        if (obj == null)
        {
            obj = activity.getWindow();
            if (obj != null)
            {
                obj = ((Window) (obj)).getDecorView();
            } else
            {
                obj = null;
            }
            if (obj != null)
            {
                view = ((View) (obj)).getRootView();
            } else
            {
                view = null;
            }
        }
        if (view != null)
        {
            view.setDrawingCacheEnabled(true);
            obj = view.getDrawingCache();
        } else
        {
            obj = null;
        }
        (new _cls1()).execute(new Void[0]);
    }

    public static void launchHelpAndFeedback(final Activity activity, final String screenshotBitmap, Integer integer)
    {
        logSettings(activity);
        Object obj = activity.getString(0x7f13015a);
        final Object googleHelp = screenshotBitmap;
        if (TextUtils.isEmpty(screenshotBitmap))
        {
            googleHelp = obj;
        }
        Object obj1;
        Intent intent;
        Object obj2;
        String s;
        String s1;
        String s2;
        String s3;
        if (TextUtils.equals(((CharSequence) (googleHelp)), ((CharSequence) (obj))))
        {
            screenshotBitmap = "android_app_help";
        } else
        {
            screenshotBitmap = ((String) (googleHelp));
        }
        obj1 = Locale.getDefault();
        obj = ((Locale) (obj1)).getLanguage();
        obj1 = ((Locale) (obj1)).getCountry().toLowerCase();
        obj2 = (new StringBuilder(String.valueOf(obj).length() + 1 + String.valueOf(obj1).length())).append(((String) (obj))).append("_").append(((String) (obj1))).toString();
        screenshotBitmap = Uri.parse("https://support.google.com/calendar/").buildUpon().appendQueryParameter("p", screenshotBitmap).appendQueryParameter("hl", ((String) (obj2))).build();
        obj = getUriViewIntent(activity, screenshotBitmap);
        obj1 = new Intent(activity, com/google/android/calendar/PrivacyPolicyActivity);
        intent = new Intent(activity, com/google/android/libraries/social/licenses/LicenseMenuActivity);
        obj2 = getUriViewIntent(activity, Uri.parse(activity.getString(0x7f130478, new Object[] {
            obj2
        })));
        s = activity.getString(0x7f130331);
        s1 = activity.getString(0x7f1303b6);
        s2 = activity.getString(0x7f130372);
        s3 = activity.getString(0x7f130477);
        googleHelp = new GoogleHelp(((String) (googleHelp)));
        googleHelp.zzbwq = screenshotBitmap;
        ((GoogleHelp) (googleHelp)).zzbwr.add(new OverflowMenuItem(0x7f10004f, s, ((Intent) (obj))));
        ((GoogleHelp) (googleHelp)).zzbwr.add(new OverflowMenuItem(0x7f10002d, s1, ((Intent) (obj1))));
        ((GoogleHelp) (googleHelp)).zzbwr.add(new OverflowMenuItem(0x7f10002b, s2, intent));
        ((GoogleHelp) (googleHelp)).zzbwr.add(new OverflowMenuItem(0x7f100041, s3, ((Intent) (obj2))));
        if (integer != null)
        {
            screenshotBitmap = activity.findViewById(integer.intValue());
            if (screenshotBitmap != null)
            {
                if (screenshotBitmap != null)
                {
                    screenshotBitmap.setDrawingCacheEnabled(true);
                    screenshotBitmap = screenshotBitmap.getDrawingCache();
                } else
                {
                    screenshotBitmap = null;
                }
            } else
            {
                screenshotBitmap = null;
            }
        } else
        {
            screenshotBitmap = null;
        }
        if (screenshotBitmap == null)
        {
            screenshotBitmap = GoogleHelp.getScreenshot(activity);
        }
        (new _cls2()).execute(new Void[0]);
    }

    static void logSettings(Context context)
    {
        LogUtils.i(TAG, "Menu option 'Send feedback' selected.  Dumping general SharedPreferences...", new Object[0]);
        logSharedPrefs(context.getSharedPreferences("com.google.android.calendar_preferences", 0));
        LogUtils.i(TAG, "Dumping calendar_alerts SharedPreferences...", new Object[0]);
        logSharedPrefs(AlertUtils.getFiredAlertsTable(context));
        LogUtils.i(TAG, "Device tz: %s", new Object[] {
            Time.getCurrentTimezone()
        });
        LogUtils.i(TAG, "Displayed tz: %s", new Object[] {
            Utils.getTimeZoneId(context, null)
        });
        LogUtils.i(TAG, "Dump complete", new Object[0]);
    }

    private static void logSharedPrefs(SharedPreferences sharedpreferences)
    {
        sharedpreferences = sharedpreferences.getAll().entrySet().iterator();
        do
        {
            if (!sharedpreferences.hasNext())
            {
                break;
            }
            Object obj1 = (java.util.Map.Entry)sharedpreferences.next();
            Object obj = ((java.util.Map.Entry) (obj1)).getKey();
            obj1 = ((java.util.Map.Entry) (obj1)).getValue();
            if (obj1 == null)
            {
                LogUtils.i(TAG, "%s: <null>", new Object[] {
                    obj
                });
            } else
            if (!UNLOGGED_PREFS.contains(obj))
            {
                LogUtils.i(TAG, "%s: %s (%s)", new Object[] {
                    obj, obj1, obj1.getClass().getSimpleName()
                });
            }
        } while (true);
    }

    private static String sanitizeCsvValue(String s)
    {
        String s1 = s;
        if (s != null)
        {
            s1 = s;
            if (s.contains(","))
            {
                s1 = (new StringBuilder(String.valueOf(s).length() + 2)).append("\"").append(s).append("\"").toString();
            }
        }
        return s1;
    }


    private class _cls1 extends AsyncTask
    {

        private final Activity val$activity;
        private final Context val$applicationContext;
        private final boolean val$isUss;
        private final Bundle val$params;
        private final Bitmap val$screenshot;

        protected final Object doInBackground(Object aobj[])
        {
            boolean flag1 = false;
            GoogleFeedbackUtils.logSettings(applicationContext);
            aobj = new com.google.android.gms.feedback.FeedbackOptions.Builder();
            aobj.zzbhc = screenshot;
            if (isUss)
            {
                aobj.mCategoryTag = "com.google.android.calendar.USS_FEEDBACK";
            }
            boolean flag = flag1;
            if (params != null)
            {
                flag = flag1;
                if (params.getBoolean("db_dump_from_drawer", false))
                {
                    flag = true;
                }
            }
            if (flag)
            {
                byte abyte0[] = GoogleFeedbackUtils.dumpStoreData(applicationContext);
                ((com.google.android.gms.feedback.FeedbackOptions.Builder) (aobj)).mFileTeleporters.add(new FileTeleporter(abyte0, "text/plain", "Dump store for all accounts"));
                GoogleFeedbackUtils.addDataToFeedback(((com.google.android.gms.feedback.FeedbackOptions.Builder) (aobj)), "USS store dump for all accounts", UnifiedSyncUtils.getDatabaseDump(applicationContext));
            }
            Activity activity1;
            if (isUss || flag)
            {
                activity1 = activity;
            }
            GoogleFeedbackUtils.addEssentialDataToFeedback(((com.google.android.gms.feedback.FeedbackOptions.Builder) (aobj)), activity);
            return ((com.google.android.gms.feedback.FeedbackOptions.Builder) (aobj)).build();
        }

        protected final void onPostExecute(Object obj)
        {
            obj = (FeedbackOptions)obj;
            Object obj1 = applicationContext;
            if (GoogleFeedbackUtils.apiClient == null)
            {
                obj1 = (new com.google.android.gms.common.api.GoogleApiClient.Builder(((Context) (obj1)).getApplicationContext())).addApi(Feedback.API);
                Object obj2 = new _cls4();
                if (obj2 == null)
                {
                    throw new NullPointerException(String.valueOf("Listener must not be null"));
                }
                ((com.google.android.gms.common.api.GoogleApiClient.Builder) (obj1)).zzaJl.add(obj2);
                obj2 = new _cls3();
                if (obj2 == null)
                {
                    throw new NullPointerException(String.valueOf("Listener must not be null"));
                }
                ((com.google.android.gms.common.api.GoogleApiClient.Builder) (obj1)).zzaJm.add(obj2);
                GoogleFeedbackUtils.apiClient = ((com.google.android.gms.common.api.GoogleApiClient.Builder) (obj1)).build();
            }
            if (!GoogleFeedbackUtils.apiClient.isConnected())
            {
                GoogleFeedbackUtils.apiClient.connect();
            }
            Feedback.startFeedback(GoogleFeedbackUtils.apiClient, ((FeedbackOptions) (obj))).setResultCallback(new _cls5());
        }

        _cls1()
        {
            applicationContext = context;
            screenshot = bitmap;
            isUss = flag;
            params = bundle;
            activity = activity1;
            super();
        }

        private class _cls4
            implements com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        {

            public final void onConnected(Bundle bundle)
            {
                LogUtils.i(GoogleFeedbackUtils.TAG, "Feedback API client connected", new Object[0]);
            }

            public final void onConnectionSuspended(int i)
            {
                LogUtils.i(GoogleFeedbackUtils.TAG, "Feedback API client disconnected: %d", new Object[] {
                    Integer.valueOf(i)
                });
            }

            _cls4()
            {
            }
        }


        private class _cls3
            implements com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
        {

            public final void onConnectionFailed(ConnectionResult connectionresult)
            {
                LogUtils.e(GoogleFeedbackUtils.TAG, "Feedback API client failed to connect: %d", new Object[] {
                    Integer.valueOf(connectionresult.zzaEP)
                });
            }

            _cls3()
            {
            }
        }


        private class _cls5
            implements ResultCallback
        {

            public final void onResult(Result result)
            {
                result = (Status)result;
                GoogleFeedbackUtils.apiClient.disconnect();
                boolean flag;
                if (((Status) (result)).zzaEP <= 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    LogUtils.e(GoogleFeedbackUtils.TAG, "Failed to execute feedback request in Google Play Services: %s", new Object[] {
                        result
                    });
                }
            }

            _cls5()
            {
            }
        }

    }


    private class _cls2 extends AsyncTask
    {

        private final Activity val$activity;
        private final Context val$applicationContext;
        private final GoogleHelp val$googleHelp;
        private final Bitmap val$screenshotBitmap;

        protected final Object doInBackground(Object aobj[])
        {
            aobj = new com.google.android.gms.feedback.FeedbackOptions.Builder();
            aobj.zzbhc = screenshotBitmap;
            GoogleFeedbackUtils.addEssentialDataToFeedback(((com.google.android.gms.feedback.FeedbackOptions.Builder) (aobj)), activity);
            return ((com.google.android.gms.feedback.FeedbackOptions.Builder) (aobj)).build();
        }

        protected final void onPostExecute(Object obj)
        {
            obj = (FeedbackOptions)obj;
            Object obj1 = googleHelp;
            obj1.zzbwv = zzaoc.zza(((FeedbackOptions) (obj)), applicationContext.getFilesDir());
            ((GoogleHelp) (obj1)).zzbwv.launcher = "GoogleHelp";
            obj = googleHelp;
            obj = (new Intent("com.google.android.gms.googlehelp.HELP")).setPackage("com.google.android.gms").putExtra("EXTRA_GOOGLE_HELP", ((android.os.Parcelable) (obj)));
            obj1 = new GoogleHelpLauncher(activity);
            if (!((Intent) (obj)).getAction().equals("com.google.android.gms.googlehelp.HELP") || !((Intent) (obj)).hasExtra("EXTRA_GOOGLE_HELP"))
            {
                throw new IllegalArgumentException("The intent you are trying to launch is not GoogleHelp intent! This class only supports GoogleHelp intents.");
            }
            int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(((GoogleHelpLauncher) (obj1)).mActivity);
            if (i == 0)
            {
                GoogleHelp googlehelp = (GoogleHelp)((Intent) (obj)).getParcelableExtra("EXTRA_GOOGLE_HELP");
                googlehelp.zzbwz = GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE;
                ((Intent) (obj)).putExtra("EXTRA_GOOGLE_HELP", googlehelp);
                zzc.zza(((GoogleHelpLauncher) (obj1)).mApiClient, new com.google.android.gms.googlehelp.GoogleHelpLauncher._cls1(((GoogleHelpLauncher) (obj1)), ((Intent) (obj))));
                return;
            } else
            {
                ((GoogleHelpLauncher) (obj1)).handlePlayServicesUnavailable(i, ((Intent) (obj)));
                return;
            }
        }

        _cls2()
        {
            screenshotBitmap = bitmap;
            activity = activity1;
            googleHelp = googlehelp;
            applicationContext = context;
            super();
        }
    }

}
