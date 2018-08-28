// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch;

import android.accounts.Account;
import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.AccountUtils;
import com.google.android.calendar.AllInOneCalendarActivity;
import com.google.android.calendar.StorageDisabledDialog;
import com.google.android.calendar.Utils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.common.activity.CalendarSupportActivity;
import com.google.android.calendar.event.EventInfoActivity;
import com.google.android.calendar.event.FindTimeActivity;
import com.google.android.calendar.ical.NewICalActivity;
import com.google.android.calendar.latency.LatencyLogger;
import com.google.android.calendar.latency.LatencyLoggerHolder;
import com.google.android.calendar.launch.oobe.DemoUserUtil;
import com.google.android.calendar.launch.oobe.WhatsNewFactory;
import com.google.android.calendar.launch.uri.AddCalendarUriHandler;
import com.google.android.calendar.launch.uri.BaseUriHandler;
import com.google.android.calendar.launch.uri.CatchAllCalendarUriHandler;
import com.google.android.calendar.launch.uri.EventTemplateUriHandler;
import com.google.android.calendar.launch.uri.GoToDateUriHandler;
import com.google.android.calendar.launch.uri.GoogleCalendarUriIntentFilter;
import com.google.android.calendar.launch.uri.ViewEventUriHandler;
import com.google.android.calendar.settings.SettingsActivity;
import com.google.android.calendar.timely.TimelineItemUtil;
import com.google.android.calendar.timely.TimelineTaskBundle;
import com.google.android.calendar.timely.settings.PreferencesUtils;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;
import com.google.android.calendar.utils.timely.TimelyUtils;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GoogleSignatureVerifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.launch:
//            LaunchIntentConstants, RequestPermissionsActivity

public class LaunchInfoActivity extends CalendarSupportActivity
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/launch/LaunchInfoActivity);
    private static boolean completedOobeThisRun;
    private Dialog gmsErrorDialog;
    private StorageDisabledDialog storageDisabledDialog;

    public LaunchInfoActivity()
    {
    }

    private final Intent fixTimeForIntentIfNeeded(Intent intent)
    {
        Uri uri = intent.getData();
        if (uri != null && uri.isHierarchical() && "time/epoch".equals(getContentResolver().getType(uri))) goto _L2; else goto _L1
_L1:
        return intent;
_L2:
        long l = -1L;
        long l1 = Long.valueOf(uri.getLastPathSegment()).longValue();
        l = l1;
_L4:
        if (l < 0L)
        {
            LogUtils.e(TAG, "Wrong millis in time intent, removing.", new Object[0]);
            intent.setData(null);
            return intent;
        }
        if (true) goto _L1; else goto _L3
_L3:
        NumberFormatException numberformatexception;
        numberformatexception;
          goto _L4
    }

    public static void handleCompleteFullOobe(Activity activity)
    {
        if (WhatsNewFactory.instance == null)
        {
            WhatsNewFactory.instance = new WhatsNewFactory();
        }
        Object obj = WhatsNewFactory.instance;
        Object obj1 = Arrays.asList(AccountsUtil.getGoogleAccounts(activity));
        LogUtils.d(WhatsNewFactory.TAG, "onCompleteOobe: %s", new Object[] {
            obj1
        });
        obj = new ArrayList(((List) (obj1)).size());
        SharedPreferences sharedpreferences = TimelyUtils.getVersionSharedPreferences(activity);
        android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
        PreferencesUtils.markOobeAsSeen(activity);
        for (obj1 = ((List) (obj1)).iterator(); ((Iterator) (obj1)).hasNext();)
        {
            Account account = (Account)((Iterator) (obj1)).next();
            String s = TimelyUtils.getSmartmailAckPrefKey(account);
            if (sharedpreferences.getInt(s, 5) == 5)
            {
                byte byte0;
                if (AccountUtils.isSmartmailEnabledByLegacy(account))
                {
                    byte0 = 3;
                } else
                {
                    byte0 = 6;
                }
                editor.putInt(s, byte0);
            }
            if (AccountUtil.isGoogleAccount(account))
            {
                ((List) (obj)).add(account);
            } else
            {
                WhatsNewFactory.setUpgradedNonGoogle(editor, account);
            }
        }

        editor.apply();
        WhatsNewFactory.enrollIntoTimely(activity, ((List) (obj)));
    }

    private final void launchAllInOne(Intent intent, String s)
    {
        intent = (Intent)intent.clone();
        intent.setAction(s);
        intent.setClass(this, com/google/android/calendar/AllInOneCalendarActivity);
        Utils.setThirdPartySourceIfNone(intent);
        startActivity(intent);
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if (i == WhatsNewFactory.WHATS_NEW_REQUEST_CODE && j == -1)
        {
            completedOobeThisRun = true;
        }
        if (j != -1)
        {
            finish();
        }
    }

    protected void onResume()
    {
        super.onResume();
        tryStartup();
    }

    final void tryStartup()
    {
        boolean flag3 = true;
        if (!AccountsUtil.getAccountsIssuePresent) goto _L2; else goto _L1
_L1:
        (new android.app.AlertDialog.Builder(this)).setMessage(0x7f1303fc).setPositiveButton(0x104000a, new _cls2()).setOnCancelListener(new _cls1()).show();
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        ((AnalyticsLogger)analyticslogger).trackEvent(this, "app_start_issue", "caller_unsigned_dialog", "displayed", null);
_L13:
        return;
_L2:
        final GoogleApiAvailability availability = getIntent();
        if (availability == null) goto _L4; else goto _L3
_L3:
        if (LaunchIntentConstants.findTimeAction == null)
        {
            LaunchIntentConstants.findTimeAction = String.valueOf(getPackageName()).concat(".FIND_TIME");
        }
        if (!LaunchIntentConstants.findTimeAction.equals(availability.getAction())) goto _L4; else goto _L5
_L5:
        boolean flag = true;
_L9:
        if (flag) goto _L7; else goto _L6
_L6:
        if (!(new DemoUserUtil(this)).isDemoUser() && android.provider.Settings.Secure.getInt(getContentResolver(), "skip_first_use_hints", 0) == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L8; else goto _L7
_L7:
        flag = true;
_L10:
        if (completedOobeThisRun || flag)
        {
            break MISSING_BLOCK_LABEL_287;
        }
        if (WhatsNewFactory.instance == null)
        {
            WhatsNewFactory.instance = new WhatsNewFactory();
        }
        if (!WhatsNewFactory.shouldShowInitialWelcome(this))
        {
            break MISSING_BLOCK_LABEL_287;
        }
        flag = true;
_L11:
        if (flag)
        {
            if (WhatsNewFactory.instance == null)
            {
                WhatsNewFactory.instance = new WhatsNewFactory();
            }
            availability = WhatsNewFactory.instance;
            if (!WhatsNewFactory.shouldShowInitialWelcome(this))
            {
                LogUtils.wtf(WhatsNewFactory.TAG, "showInitialWelcome called at a wrong time", new Object[0]);
                return;
            } else
            {
                WhatsNewFactory.showFullScreenOobe(this);
                return;
            }
        }
        break MISSING_BLOCK_LABEL_298;
_L4:
        flag = false;
          goto _L9
_L8:
        flag = false;
          goto _L10
        flag = false;
          goto _L11
        int i;
        availability = GoogleApiAvailability.zzaIm;
        i = availability.isGooglePlayServicesAvailable(this);
        if (!availability.isUserResolvableError(i))
        {
            break; /* Loop/switch isn't completed */
        }
        if (gmsErrorDialog == null)
        {
            _cls3 _lcls3 = new _cls3();
            gmsErrorDialog = GoogleApiAvailability.zza(this, i, new com.google.android.gms.common.internal.zzi._cls1(availability.getErrorResolutionIntent(this, i, "d"), this, 0), _lcls3);
        }
        if (!gmsErrorDialog.isShowing())
        {
            gmsErrorDialog.show();
            return;
        }
        if (true) goto _L13; else goto _L12
_L12:
        if (i != 0)
        {
            Toast.makeText(this, 0x7f130248, 1).show();
            LogUtils.e(TAG, "Not a user resolvable gms core error %d", new Object[] {
                Integer.valueOf(i)
            });
            finish();
            return;
        }
        boolean flag1;
        if (!AndroidPermissionUtils.hasMandatoryPermissions(this))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            startActivityForResult(RequestPermissionsActivity.createIntent(this), 1002);
            return;
        }
        boolean flag4 = Utils.isCalendarStorageEnabled(this);
        if (!flag4)
        {
            if (storageDisabledDialog == null)
            {
                setRequestedOrientation(4);
                storageDisabledDialog = new StorageDisabledDialog(this);
                storageDisabledDialog.setOnCancelListener(new _cls4());
                availability = AnalyticsLoggerHolder.instance;
                if (availability == null)
                {
                    throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                }
                ((AnalyticsLogger)availability).trackEvent(this, "sync_warnings", "storage_disabled_dialog", "displayed", null);
            }
            storageDisabledDialog.show();
        } else
        if (storageDisabledDialog != null)
        {
            if (storageDisabledDialog.isShowing())
            {
                storageDisabledDialog.dismiss();
            }
            storageDisabledDialog = null;
            availability = AnalyticsLoggerHolder.instance;
            if (availability == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)availability).trackEvent(this, "sync_warnings", "storage_disabled_dialog", "enabled", null);
        }
        if (!flag4) goto _L13; else goto _L14
_L14:
        if (completedOobeThisRun)
        {
            handleCompleteFullOobe(this);
        }
        availability = getIntent();
        if (availability == null) goto _L16; else goto _L15
_L15:
        LogUtils.d(TAG, "launchApp intent: %s", new Object[] {
            availability
        });
        Object obj = TAG;
        boolean flag5;
        if (LogUtils.maxEnabledLogLevel > 3)
        {
            flag5 = false;
        } else
        if (Log.isLoggable(((String) (obj)), 3))
        {
            flag5 = true;
        } else
        {
            flag5 = Log.isLoggable(((String) (obj)), 3);
        }
        if (flag5)
        {
            obj = availability.getExtras();
            if (obj != null)
            {
                obj = ((Bundle) (obj)).getString("intent_source", "unknown");
                LogUtils.d(TAG, "Received %s from %s", new Object[] {
                    availability, obj
                });
            } else
            {
                LogUtils.d(TAG, "Received %s", new Object[] {
                    availability
                });
            }
        }
        availability.setFlags(availability.getFlags() & 0xff7fffff);
        if (!"com.google.android.calendar.ICalLauncher".equals(availability.getComponent().getClassName())) goto _L18; else goto _L17
_L17:
        availability = (Intent)availability.clone();
        availability.setClass(this, com/google/android/calendar/ical/NewICalActivity);
        startActivity(availability);
_L16:
        finish();
        return;
_L18:
        if ("com.google.android.calendar.timely.settings.CalendarPublicPreferenceActvity".equals(availability.getComponent().getClassName()))
        {
            availability = getIntent();
            availability.setFlags(availability.getFlags() & 0xff7fffff);
            availability.removeExtra(":android:show_fragment");
            availability.removeExtra(":android:show_fragment_args");
            availability.removeExtra(":android:show_fragment_short_title");
            availability.removeExtra(":android:show_fragment_title");
            availability.setClass(this, com/google/android/calendar/settings/SettingsActivity);
            if (availability.hasCategory("android.intent.category.NOTIFICATION_PREFERENCES"))
            {
                availability.putExtra(":android:show_fragment", "com.google.android.calendar.settings.general.GeneralPreferenceFragment");
                availability.putExtra(":android:show_fragment_title", getString(0x7f13032f));
            }
            startActivity(availability);
            continue; /* Loop/switch isn't completed */
        }
        Object obj1 = new GoogleCalendarUriIntentFilter(this, availability);
        EventTemplateUriHandler eventtemplateurihandler = new EventTemplateUriHandler(((GoogleCalendarUriIntentFilter) (obj1)).launchIntent);
        boolean flag2;
        if (eventtemplateurihandler.matches())
        {
            obj1 = ((GoogleCalendarUriIntentFilter) (obj1)).activity;
            Intent intent = (Intent)eventtemplateurihandler.mIntent.clone();
            if (LaunchIntentConstants.insertAction == null)
            {
                LaunchIntentConstants.insertAction = String.valueOf(((Context) (obj1)).getPackageName()).concat(".EVENT_INSERT");
            }
            intent.setAction(LaunchIntentConstants.insertAction);
            intent.setClass(((Context) (obj1)), com/google/android/calendar/AllInOneCalendarActivity);
            intent.setData(android.provider.CalendarContract.Events.CONTENT_URI);
            Utils.setThirdPartySourceIfNone(intent);
            EventTemplateUriHandler.setExtrasFromUri(((Context) (obj1)), eventtemplateurihandler.mIntent.getData(), intent);
            ((Activity) (obj1)).startActivity(intent);
            flag2 = flag3;
        } else
        {
            Object obj2 = new AddCalendarUriHandler(((GoogleCalendarUriIntentFilter) (obj1)).launchIntent);
            if (((BaseUriHandler) (obj2)).matches())
            {
                obj1 = ((GoogleCalendarUriIntentFilter) (obj1)).activity;
                obj2 = (Intent)((AddCalendarUriHandler) (obj2)).mIntent.clone();
                if (LaunchIntentConstants.openCalendarAndShowErrorToastAction == null)
                {
                    LaunchIntentConstants.openCalendarAndShowErrorToastAction = String.valueOf(((Context) (obj1)).getPackageName()).concat(".OPEN_CALENDAR_AND_SHOW_ERROR_TOAST");
                }
                ((Intent) (obj2)).setAction(LaunchIntentConstants.openCalendarAndShowErrorToastAction);
                ((Intent) (obj2)).setClass(((Context) (obj1)), com/google/android/calendar/AllInOneCalendarActivity);
                ((Intent) (obj2)).putExtra("error_message_extra", ((Activity) (obj1)).getString(0x7f1304a0));
                Utils.setThirdPartySourceIfNone(((Intent) (obj2)));
                ((Activity) (obj1)).startActivity(((Intent) (obj2)));
                flag2 = flag3;
            } else
            {
                Object obj3 = new GoToDateUriHandler(((GoogleCalendarUriIntentFilter) (obj1)).launchIntent);
                if (((BaseUriHandler) (obj3)).matches())
                {
                    obj1 = ((GoogleCalendarUriIntentFilter) (obj1)).activity;
                    obj3 = (Intent)((GoToDateUriHandler) (obj3)).mIntent.clone();
                    if (LaunchIntentConstants.openCalendarAction == null)
                    {
                        LaunchIntentConstants.openCalendarAction = String.valueOf(((Context) (obj1)).getPackageName()).concat(".OPEN_CALENDAR");
                    }
                    ((Intent) (obj3)).setAction(LaunchIntentConstants.openCalendarAction);
                    ((Intent) (obj3)).setClass(((Context) (obj1)), com/google/android/calendar/AllInOneCalendarActivity);
                    Utils.setThirdPartySourceIfNone(((Intent) (obj3)));
                    ((Activity) (obj1)).startActivity(((Intent) (obj3)));
                    flag2 = flag3;
                } else
                {
                    ViewEventUriHandler vieweventurihandler = new ViewEventUriHandler(((GoogleCalendarUriIntentFilter) (obj1)).launchIntent);
                    if (vieweventurihandler.matches())
                    {
                        if (vieweventurihandler.eidParts != null)
                        {
                            flag2 = true;
                        } else
                        {
                            flag2 = false;
                        }
                        if (flag2)
                        {
                            (new com.google.android.calendar.launch.uri.ViewEventUriHandler.ResolveSyncIdAndLaunchIntent(vieweventurihandler, ((GoogleCalendarUriIntentFilter) (obj1)).activity)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                            flag2 = flag3;
                        } else
                        {
                            flag2 = false;
                        }
                    } else
                    {
                        CatchAllCalendarUriHandler catchallcalendarurihandler = new CatchAllCalendarUriHandler(((GoogleCalendarUriIntentFilter) (obj1)).launchIntent, Arrays.asList(new String[] {
                            "http", "https"
                        }), Arrays.asList(new String[] {
                            "calendar.google.com"
                        }), Arrays.asList(new String[] {
                            ".*"
                        }));
                        if (catchallcalendarurihandler.matches())
                        {
                            catchallcalendarurihandler.handle(((GoogleCalendarUriIntentFilter) (obj1)).activity);
                            flag2 = flag3;
                        } else
                        {
                            CatchAllCalendarUriHandler catchallcalendarurihandler1 = new CatchAllCalendarUriHandler(((GoogleCalendarUriIntentFilter) (obj1)).launchIntent, Arrays.asList(new String[] {
                                "http", "https"
                            }), Arrays.asList(new String[] {
                                "www.google.com"
                            }), Arrays.asList(new String[] {
                                "/calendar.*"
                            }));
                            if (catchallcalendarurihandler1.matches())
                            {
                                catchallcalendarurihandler1.handle(((GoogleCalendarUriIntentFilter) (obj1)).activity);
                                flag2 = flag3;
                            } else
                            {
                                flag2 = false;
                            }
                        }
                    }
                }
            }
        }
        if (flag2)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj1 = availability.getAction();
        if ("android.intent.action.EDIT".equals(obj1))
        {
            if (LaunchIntentConstants.editAction == null)
            {
                LaunchIntentConstants.editAction = String.valueOf(getPackageName()).concat(".EVENT_EDIT");
            }
            launchAllInOne(availability, LaunchIntentConstants.editAction);
            continue; /* Loop/switch isn't completed */
        }
        if ("android.intent.action.INSERT".equals(obj1) || "android.intent.action.INSERT_OR_EDIT".equals(obj1))
        {
            if (LaunchIntentConstants.insertAction == null)
            {
                LaunchIntentConstants.insertAction = String.valueOf(getPackageName()).concat(".EVENT_INSERT");
            }
            launchAllInOne(availability, LaunchIntentConstants.insertAction);
            continue; /* Loop/switch isn't completed */
        }
        if ("android.intent.action.VIEW".equals(obj1))
        {
            Object obj4 = availability.getData();
            if (obj4 != null)
            {
                obj4 = getContentResolver().getType(((Uri) (obj4)));
                if ("vnd.android.cursor.item/event".equals(obj4))
                {
                    LatencyLoggerHolder.get().markAt(3);
                    if (Utils.isTargetAllInOne(availability))
                    {
                        availability = fixTimeForIntentIfNeeded(availability);
                        if (LaunchIntentConstants.viewAction == null)
                        {
                            LaunchIntentConstants.viewAction = String.valueOf(getPackageName()).concat(".EVENT_VIEW");
                        }
                        launchAllInOne(availability, LaunchIntentConstants.viewAction);
                        continue; /* Loop/switch isn't completed */
                    }
                    boolean flag6 = Utils.isInternalSource(availability);
                    if (!flag6 || !(TimelineItemUtil.readTimelineItemFromIntent(this, availability) instanceof TimelineTaskBundle))
                    {
                        availability = (Intent)availability.clone();
                        availability.setClass(this, com/google/android/calendar/event/EventInfoActivity);
                        availability.addFlags(32768);
                        if (!flag6)
                        {
                            Utils.setThirdPartySourceIfNone(availability);
                        }
                        startActivity(availability);
                        continue; /* Loop/switch isn't completed */
                    }
                } else
                if ("time/epoch".equals(obj4))
                {
                    launchAllInOne(fixTimeForIntentIfNeeded(availability), ((String) (obj1)));
                    continue; /* Loop/switch isn't completed */
                }
            }
            if (LaunchIntentConstants.viewAction == null)
            {
                LaunchIntentConstants.viewAction = String.valueOf(getPackageName()).concat(".EVENT_VIEW");
            }
            launchAllInOne(availability, LaunchIntentConstants.viewAction);
        } else
        {
            if (LaunchIntentConstants.findTimeAction == null)
            {
                LaunchIntentConstants.findTimeAction = String.valueOf(getPackageName()).concat(".FIND_TIME");
            }
            if (LaunchIntentConstants.findTimeAction.equals(obj1))
            {
                obj1 = GoogleSignatureVerifier.getInstance(this);
                android.content.pm.PackageManager packagemanager = getPackageManager();
                String s = getCallingPackage();
                if (!((GoogleSignatureVerifier) (obj1)).isPackageGoogleSigned(packagemanager, s))
                {
                    availability = String.valueOf(s);
                    if (availability.length() != 0)
                    {
                        availability = "Signature check failed for ".concat(availability);
                    } else
                    {
                        availability = new String("Signature check failed for ");
                    }
                    throw new SecurityException(availability);
                }
                availability = (Intent)availability.clone();
                availability.addFlags(0x2000000);
                availability.setClass(this, com/google/android/calendar/event/FindTimeActivity);
                startActivity(availability);
            } else
            {
                launchAllInOne(availability, ((String) (obj1)));
            }
        }
        if (true) goto _L16; else goto _L19
_L19:
    }


    private class _cls2
        implements android.content.DialogInterface.OnClickListener
    {

        private final LaunchInfoActivity this$0;

        public final void onClick(DialogInterface dialoginterface, int i)
        {
            finish();
        }

        _cls2()
        {
            this$0 = LaunchInfoActivity.this;
            super();
        }
    }


    private class _cls1
        implements android.content.DialogInterface.OnCancelListener
    {

        private final LaunchInfoActivity this$0;

        public final void onCancel(DialogInterface dialoginterface)
        {
            finish();
        }

        _cls1()
        {
            this$0 = LaunchInfoActivity.this;
            super();
        }
    }


    private class _cls3
        implements android.content.DialogInterface.OnCancelListener
    {

        private final LaunchInfoActivity this$0;
        private final GoogleApiAvailability val$availability;

        public final void onCancel(DialogInterface dialoginterface)
        {
            if (availability.isGooglePlayServicesAvailable(LaunchInfoActivity.this) != 0)
            {
                finish();
                return;
            } else
            {
                tryStartup();
                return;
            }
        }

        _cls3()
        {
            this$0 = LaunchInfoActivity.this;
            availability = googleapiavailability;
            super();
        }
    }


    private class _cls4
        implements android.content.DialogInterface.OnCancelListener
    {

        private final LaunchInfoActivity this$0;

        public final void onCancel(DialogInterface dialoginterface)
        {
            finish();
        }

        _cls4()
        {
            this$0 = LaunchInfoActivity.this;
            super();
        }
    }

}
