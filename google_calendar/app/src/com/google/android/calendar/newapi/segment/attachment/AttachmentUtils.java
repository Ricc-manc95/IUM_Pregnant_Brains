// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attachment;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcel;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.accounts.api.AccountData;
import com.google.android.gms.identity.accounts.api.AccountDataUtil;
import com.google.android.gms.identity.accounts.api.zzb;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.List;

public class AttachmentUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/segment/attachment/AttachmentUtils);

    public AttachmentUtils()
    {
    }

    public static void openAttachment(Context context, String s, String s1)
    {
        if (s == null)
        {
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setFlags(0x10000000);
        intent.setData(Uri.parse(s));
        Context context1 = context.getApplicationContext();
        s = context1.getPackageManager().queryIntentActivities(intent, 0).iterator();
        do
        {
            if (!s.hasNext())
            {
                break;
            }
            String s3 = ((ResolveInfo)s.next()).activityInfo.packageName;
            if (!s3.contains("com.google.android.apps.docs"))
            {
                continue;
            }
            intent.setPackage(s3);
            break;
        } while (true);
        if (s1 != null)
        {
            AccountData accountdata = AccountData.forAccount(s1);
            zzb zzb1 = AccountDataUtil.zzbxL;
            if (context1 == null)
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
            if (s != null && zzb1.zzbxM.zzG(context1, s))
            {
                s = Parcel.obtain();
                accountdata.writeToParcel(s, 0);
                byte abyte0[] = s.marshall();
                s.recycle();
                intent.putExtra("com.google.android.gms.accounts.ACCOUNT_DATA", abyte0);
            }
            try
            {
                s = MessageDigest.getInstance("MD5");
                String s2 = Long.toString((new SecureRandom()).nextLong());
                s.update((new StringBuilder(String.valueOf(s2).length() + 10 + String.valueOf(s1).length())).append(s2).append(s1).append("com.google").toString().getBytes());
                intent.putExtra("salt", s2);
                intent.putExtra("digest", s.digest());
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                LogUtils.w(TAG, "Unable to load MD5 digest instance", new Object[0]);
            }
        }
        try
        {
            context.startActivity(intent);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            Toast.makeText(context, 0x7f1300d2, 0).show();
        }
    }

}
