// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.intent;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.common.CalendarFeatureConfigDelegate;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.accounts.api.AccountData;
import com.google.android.gms.identity.accounts.api.AccountDataUtil;
import com.google.android.gms.identity.accounts.api.zzb;

public class IntentUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/utils/intent/IntentUtils);

    public IntentUtils()
    {
    }

    public static void addAccountDataToIntent(Context context, Intent intent, String s)
    {
        if (TextUtils.isEmpty(s))
        {
            LogUtils.wtf(TAG, "ownerAccount parameter can not be empty.", new Object[0]);
        } else
        {
            AccountData accountdata = AccountData.forAccount(s);
            zzb zzb1 = AccountDataUtil.zzbxL;
            if (context == null)
            {
                throw new NullPointerException(String.valueOf("Context must not be null."));
            }
            if (intent == null)
            {
                throw new NullPointerException(String.valueOf("Intent must not be null."));
            }
            if (accountdata == null)
            {
                throw new NullPointerException(String.valueOf("Account data must not be null."));
            }
            s = intent.getComponent();
            if (s != null)
            {
                s = s.getPackageName();
            } else
            {
                s = intent.getPackage();
            }
            if (s != null && zzb1.zzbxM.zzG(context, s))
            {
                context = Parcel.obtain();
                accountdata.writeToParcel(context, 0);
                s = context.marshall();
                context.recycle();
                intent.putExtra("com.google.android.gms.accounts.ACCOUNT_DATA", s);
                return;
            }
        }
    }

    public static Intent createProviderChangedIntent()
    {
        return new Intent("com.google.android.calendar.ACTION_CALENDAR_PROVIDER_CHANGED");
    }

    public static boolean hasProviderChanged(String s)
    {
        if (CalendarFeatureConfigDelegate.useJobs == null)
        {
            throw new NullPointerException(String.valueOf("The variable should be initialized before usage."));
        }
        if (CalendarFeatureConfigDelegate.useJobs.booleanValue())
        {
            return s.equals("com.google.android.calendar.ACTION_CALENDAR_PROVIDER_CHANGED");
        } else
        {
            return s.equals("android.intent.action.PROVIDER_CHANGED");
        }
    }

    public static boolean isSuperfluousProviderChange(String s)
    {
        if (s.equals("android.intent.action.PROVIDER_CHANGED"))
        {
            if (CalendarFeatureConfigDelegate.useJobs == null)
            {
                throw new NullPointerException(String.valueOf("The variable should be initialized before usage."));
            }
            if (CalendarFeatureConfigDelegate.useJobs.booleanValue())
            {
                return true;
            }
        }
        return false;
    }

}
