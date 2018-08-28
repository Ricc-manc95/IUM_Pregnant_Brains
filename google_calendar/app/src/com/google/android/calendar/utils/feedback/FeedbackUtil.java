// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.feedback;

import android.accounts.Account;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.utils.customchooser.SendEmailChooserHelper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FeedbackUtil
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/utils/feedback/FeedbackUtil);

    public FeedbackUtil()
    {
    }

    public static void sendFeedback(Context context, String s, String s1, String s2, Account account)
    {
        String s3;
        StringBuilder stringbuilder;
        stringbuilder = new StringBuilder();
        stringbuilder.append("**** Debug Info ****\n");
        s3 = "Unknown";
        String s4 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        s3 = s4;
_L1:
        stringbuilder.append((new StringBuilder(String.valueOf(s3).length() + 10)).append("Version: ").append(s3).append("\n").toString());
        s3 = Build.FINGERPRINT;
        stringbuilder.append((new StringBuilder(String.valueOf(s3).length() + 16)).append("Android build: ").append(s3).append("\n").toString());
        stringbuilder.append("\n**** Comments ****\n");
        stringbuilder.append(s2);
        stringbuilder.append("\n\n");
        s2 = SimpleDateFormat.getInstance();
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        s2 = String.format("%s | %s", new Object[] {
            s1, s2.format(new Date(l))
        });
        s1 = new Intent("android.intent.action.SENDTO", Uri.parse("mailto:"));
        s1.putExtra("android.intent.extra.EMAIL", new String[] {
            s
        });
        s1.putExtra("android.intent.extra.SUBJECT", s2);
        s1.putExtra("android.intent.extra.TEXT", stringbuilder.toString());
        s2 = SendEmailChooserHelper.instance;
        s3 = context.getString(0x7f13018d);
        if (account == null)
        {
            s = null;
        } else
        {
            s = account.name;
        }
        s = s2.createCustomChooser(context, s3, s1, s);
        s.setFlags(0x10000000);
        try
        {
            context.startActivity(s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            LogUtils.e(TAG, context, "No activity found to handle ACTION_SEND...", new Object[0]);
        }
        break MISSING_BLOCK_LABEL_345;
        namenotfoundexception;
        LogUtils.e(TAG, namenotfoundexception, "Failed to find version name", new Object[0]);
          goto _L1
    }

}
