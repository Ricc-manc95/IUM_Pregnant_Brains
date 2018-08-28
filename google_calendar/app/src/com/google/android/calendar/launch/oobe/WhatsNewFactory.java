// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch.oobe;

import android.accounts.Account;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.calendar.AccountUtils;
import com.google.android.calendar.launch.LaunchInfoActivity;
import com.google.android.calendar.timely.InitializationAsyncTask;
import com.google.android.calendar.timely.settings.PreferencesUtils;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.utils.timely.TimelyUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.launch.oobe:
//            WhatsNewFullScreen

public class WhatsNewFactory
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/launch/oobe/WhatsNewFactory);
    public static final int WHATS_NEW_REQUEST_CODE = Math.abs(com/google/android/calendar/launch/oobe/WhatsNewFactory.getName().hashCode()) % 32767;
    public static WhatsNewFactory instance;

    public WhatsNewFactory()
    {
    }

    public static void enrollIntoTimely(Context context, List list)
    {
        LogUtils.d(TAG, "enrollIntoTimely: %s", new Object[] {
            list
        });
        Object obj = TimelyUtils.getVersionSharedPreferences(context);
        android.content.SharedPreferences.Editor editor = ((SharedPreferences) (obj)).edit();
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist1 = new ArrayList();
        list = list.iterator();
        do
        {
            if (!list.hasNext())
            {
                break;
            }
            Account account = (Account)list.next();
            if (!AccountUtil.isGoogleAccount(account))
            {
                throw new IllegalArgumentException();
            }
            editor.putBoolean(TimelyUtils.getClientUpgradedPrefKey(account), true);
            String s;
            int i;
            byte byte0;
            if (((SharedPreferences) (obj)).getString(TimelyUtils.getGoogleClientVersionPrefKey(account), "0").equals("0"))
            {
                arraylist.add(account);
                i = 1;
            } else
            {
                i = 0;
            }
            s = TimelyUtils.getSmartmailAckPrefKey(account);
            if (AccountUtils.isSmartmailEnabledByLegacy(account))
            {
                byte0 = 3;
            } else
            {
                byte0 = 5;
            }
            if (2 == ((SharedPreferences) (obj)).getInt(s, byte0))
            {
                editor.putInt(s, 3);
                i = 1;
            }
            if (i != 0)
            {
                arraylist1.add(account);
            }
        } while (true);
        editor.apply();
        for (i = 0; i < arraylist1.size(); i++)
        {
            context.getApplicationContext();
            list = (Account)arraylist1.get(i);
            obj = Features.instance;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            ((FeatureConfig)obj).uss();
            TimelyUtils.triggerSyncAdapterSyncWithExtras(list, "sync_extra_update_client_status", false, new Bundle());
        }

        if (arraylist.size() > 0)
        {
            LogUtils.v(TAG, "Initializing accoutns: %s", new Object[] {
                arraylist
            });
            list = new Account[arraylist.size()];
            arraylist.toArray(list);
            (new InitializationAsyncTask(context)).execute(list);
        }
    }

    public static void setUpgradedNonGoogle(android.content.SharedPreferences.Editor editor, Account account)
    {
        boolean flag;
        if (!AccountUtil.isGoogleAccount(account))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            editor.putBoolean(TimelyUtils.getClientUpgradedPrefKey(account), true);
            return;
        }
    }

    public static boolean shouldShowInitialWelcome(LaunchInfoActivity launchinfoactivity)
    {
        return !PreferencesUtils.hasOobeBeenSeen(launchinfoactivity);
    }

    public static void showFullScreenOobe(Activity activity)
    {
        Intent intent = new Intent();
        intent.setClass(activity, com/google/android/calendar/launch/oobe/WhatsNewFullScreen);
        activity.startActivityForResult(intent, WHATS_NEW_REQUEST_CODE);
    }

    public final void processAccountChanges(final Activity activity, Account aaccount[])
    {
        final Object googleAccountsToAckSmartmail;
        ArrayList arraylist;
        Object obj;
        ArrayList arraylist1;
        googleAccountsToAckSmartmail = TAG;
        SharedPreferences sharedpreferences;
        int i;
        int i1;
        boolean flag1;
        if (LogUtils.maxEnabledLogLevel > 2)
        {
            flag1 = false;
        } else
        if (Log.isLoggable(((String) (googleAccountsToAckSmartmail)), 2))
        {
            flag1 = true;
        } else
        {
            flag1 = Log.isLoggable(((String) (googleAccountsToAckSmartmail)), 2);
        }
        if (flag1)
        {
            LogUtils.v(TAG, "processAccountChanges: %s", new Object[] {
                Arrays.asList(aaccount)
            });
        }
        obj = new ArrayList();
        googleAccountsToAckSmartmail = new ArrayList();
        arraylist = new ArrayList();
        arraylist1 = new ArrayList();
        sharedpreferences = TimelyUtils.getVersionSharedPreferences(activity);
        i1 = aaccount.length;
        i = 0;
        while (i < i1) 
        {
            Account account1 = aaccount[i];
            flag1 = sharedpreferences.getBoolean(TimelyUtils.getClientUpgradedPrefKey(account1), false);
            if (!flag1 && !AccountUtil.isGoogleAccount(account1))
            {
                android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
                boolean flag;
                if (!AccountUtil.isGoogleAccount(account1))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    throw new IllegalArgumentException();
                }
                editor.putBoolean(TimelyUtils.getClientUpgradedPrefKey(account1), true);
                editor.apply();
            } else
            {
                String s = sharedpreferences.getString(TimelyUtils.getGoogleClientVersionPrefKey(account1), "0");
                byte byte0;
                int l;
                boolean flag2;
                if (AccountUtils.isSmartmailEnabledByLegacy(account1))
                {
                    byte0 = 3;
                } else
                {
                    byte0 = 5;
                }
                l = AccountUtils.getSmartmailAck(sharedpreferences, account1, byte0);
                if (2 == l)
                {
                    byte0 = 1;
                } else
                {
                    byte0 = 0;
                }
                if (3 == l)
                {
                    l = 1;
                } else
                {
                    l = 0;
                }
                if (byte0 != 0)
                {
                    ((ArrayList) (googleAccountsToAckSmartmail)).add(account1);
                    arraylist.add(account1.name);
                }
                flag2 = "0".equals(s);
                if (flag2 || l)
                {
                    arraylist1.add(account1);
                }
                if (!flag1 && flag2)
                {
                    ((ArrayList) (obj)).add(account1);
                }
            }
            i++;
        }
        if (arraylist.isEmpty()) goto _L2; else goto _L1
_L1:
        aaccount = activity.getResources();
        obj = TextUtils.join(aaccount.getString(0x7f1301c3), arraylist);
        aaccount = aaccount.getQuantityString(0x7f120047, arraylist.size(), new Object[] {
            obj
        });
        (new android.app.AlertDialog.Builder(activity)).setCancelable(false).setMessage(aaccount).setPositiveButton(0x7f130364, new _cls1()).create().show();
_L4:
        return;
_L2:
        if (!((ArrayList) (obj)).isEmpty())
        {
            enrollIntoTimely(activity, ((List) (obj)));
            return;
        }
        if (arraylist1.size() > 0)
        {
            aaccount = (ArrayList)arraylist1;
            int k = aaccount.size();
            int j = 0;
            while (j < k) 
            {
                Account account = (Account)aaccount.get(j);
                activity.getApplicationContext();
                FeatureConfig featureconfig = Features.instance;
                if (featureconfig == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                }
                ((FeatureConfig)featureconfig).uss();
                TimelyUtils.triggerSyncAdapterSyncWithExtras(account, "sync_extra_update_client_status", false, new Bundle());
                j++;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }


    private class _cls1
        implements android.content.DialogInterface.OnClickListener
    {

        private final WhatsNewFactory this$0;
        private final Activity val$activity;
        private final ArrayList val$googleAccountsToAckSmartmail;

        public final void onClick(DialogInterface dialoginterface, int i)
        {
            dialoginterface = WhatsNewFactory.this;
            WhatsNewFactory.enrollIntoTimely(activity, googleAccountsToAckSmartmail);
        }

        _cls1()
        {
            this$0 = WhatsNewFactory.this;
            activity = activity1;
            googleAccountsToAckSmartmail = arraylist;
            super();
        }
    }

}
