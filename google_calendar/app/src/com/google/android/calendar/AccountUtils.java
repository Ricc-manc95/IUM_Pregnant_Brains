// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.accounts.Account;
import android.content.Context;
import android.content.SharedPreferences;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.utils.timely.TimelyUtils;
import com.google.common.collect.ImmutableList;

public class AccountUtils
{

    private static final ImmutableList SMART_MAIL_LEGACY_DOMAINS = ImmutableList.of("@gmail.com", "@googlemail.com", "@google.com");

    public AccountUtils()
    {
    }

    public static boolean doesAccountSupportSmartmail(Context context, Account account)
    {
        context = TimelyUtils.getVersionSharedPreferences(context);
        int i;
        if (isSmartmailEnabledByLegacy(account))
        {
            i = 3;
        } else
        {
            i = 2;
        }
        if (!AccountUtil.isGoogleAccount(account))
        {
            i = 1;
        } else
        {
            i = context.getInt(TimelyUtils.getSmartmailAckPrefKey(account), i);
        }
        return 3 == i || 4 == i;
    }

    public static int getSmartmailAck(SharedPreferences sharedpreferences, Account account, int i)
    {
        if (!AccountUtil.isGoogleAccount(account))
        {
            return 1;
        } else
        {
            return sharedpreferences.getInt(TimelyUtils.getSmartmailAckPrefKey(account), i);
        }
    }

    public static boolean isDasher(Settings settings)
    {
        if (settings instanceof GoogleSettings)
        {
            settings = (GoogleSettings)settings;
        } else
        {
            settings = null;
        }
        int i;
        if (settings != null)
        {
            if ((i = settings.getQualityOfService()) == 1 || i == 2)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isSmartmailEnabledByLegacy(Account account)
    {
        int i = 0;
        boolean flag;
        boolean flag1;
        for (flag = false; i < SMART_MAIL_LEGACY_DOMAINS.size(); flag = flag1)
        {
            String s = (String)SMART_MAIL_LEGACY_DOMAINS.get(i);
            flag1 = flag;
            if (account.name != null)
            {
                flag1 = flag;
                if (account.name.endsWith(s))
                {
                    flag1 = true;
                }
            }
            i++;
        }

        return flag;
    }

    static 
    {
        LogUtils.getLogTag(com/google/android/calendar/AccountUtils);
    }
}
