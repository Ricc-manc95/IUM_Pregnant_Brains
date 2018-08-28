// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.smartmail;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import com.google.android.calendar.event.PackageUtils;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gsf.Gservices;

final class SourceMessageViewUtils
{

    private static final Viewer CANDIDATES[] = {
        new GmailViewer(), new GmailGoViewer(), new InboxViewer("com.google.android.apps.bigtop"), new InboxViewer("com.google.android.apps.inbox")
    };

    static Viewer getSourceEmailViewer(Context context)
    {
        boolean flag;
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        break MISSING_BLOCK_LABEL_10;
        if (flag && Gservices.getBoolean(context.getContentResolver(), "google_calendar_view_source_email", true))
        {
            Viewer aviewer[] = CANDIDATES;
            int j = aviewer.length;
            int i = 0;
            while (i < j) 
            {
                Viewer viewer = aviewer[i];
                PackageInfo packageinfo = PackageUtils.getPackageInfo(context, viewer.mPackageName, 0);
                boolean flag1;
                if (packageinfo == null)
                {
                    flag1 = false;
                } else
                if (viewer.minVersion != 0 && packageinfo.versionCode < viewer.minVersion)
                {
                    flag1 = false;
                } else
                {
                    ApplicationInfo applicationinfo = PackageUtils.getApplicationInfo(context, viewer.mPackageName, 0);
                    if (applicationinfo == null)
                    {
                        flag1 = false;
                    } else
                    if (!applicationinfo.enabled)
                    {
                        flag1 = false;
                    } else
                    {
                        flag1 = true;
                    }
                }
                if (flag1)
                {
                    return viewer;
                }
                i++;
            }
        }
        return null;
    }


    private class Viewer
    {

        public final String mPackageName;
        public final int minVersion;

        abstract Intent getIntent(Context context, String s, String s1, String s2);

        protected Viewer(String s, int i)
        {
            mPackageName = s;
            minVersion = i;
        }
    }


    private class GmailViewer extends Viewer
    {

        final Intent getIntent(Context context, String s, String s1, String s2)
        {
            Intent intent = new Intent("com.google.android.gm.intent.VIEW_PLID");
            intent.setPackage(mPackageName);
            intent.putExtra("permalink", s);
            intent.putExtra("plid", s1);
            s1 = AccountData.forAccount(s2);
            s2 = AccountDataUtil.zzbxL;
            if (context == null)
            {
                throw new NullPointerException(String.valueOf("Context must not be null."));
            }
            if (intent == null)
            {
                throw new NullPointerException(String.valueOf("Intent must not be null."));
            }
            if (s1 == null)
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
            if (s != null && ((zzb) (s2)).zzbxM.zzG(context, s))
            {
                context = Parcel.obtain();
                s1.writeToParcel(context, 0);
                s = context.marshall();
                context.recycle();
                intent.putExtra("com.google.android.gms.accounts.ACCOUNT_DATA", s);
            }
            return intent;
        }

        GmailViewer()
        {
            super("com.google.android.gm", 0x4c4d7a);
        }
    }


    private class GmailGoViewer extends Viewer
    {

        final Intent getIntent(Context context, String s, String s1, String s2)
        {
            Intent intent = new Intent("com.google.android.gm.intent.VIEW_PLID");
            intent.setPackage(mPackageName);
            intent.putExtra("plid", s1);
            s1 = AccountData.forAccount(s2);
            s2 = AccountDataUtil.zzbxL;
            if (context == null)
            {
                throw new NullPointerException(String.valueOf("Context must not be null."));
            }
            if (intent == null)
            {
                throw new NullPointerException(String.valueOf("Intent must not be null."));
            }
            if (s1 == null)
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
            if (s != null && ((zzb) (s2)).zzbxM.zzG(context, s))
            {
                context = Parcel.obtain();
                s1.writeToParcel(context, 0);
                s = context.marshall();
                context.recycle();
                intent.putExtra("com.google.android.gms.accounts.ACCOUNT_DATA", s);
            }
            return intent;
        }

        GmailGoViewer()
        {
            super("com.google.android.gm.lite", 0);
        }
    }


    private class InboxViewer extends Viewer
    {

        final Intent getIntent(Context context, String s, String s1, String s2)
        {
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(mPackageName);
            if (intent != null)
            {
                intent.setAction("com.google.android.apps.bigtop.intent.VIEW_EMAIL");
                intent.putExtra("plid", s1);
                s1 = AccountData.forAccount(s2);
                s2 = AccountDataUtil.zzbxL;
                if (context == null)
                {
                    throw new NullPointerException(String.valueOf("Context must not be null."));
                }
                if (intent == null)
                {
                    throw new NullPointerException(String.valueOf("Intent must not be null."));
                }
                if (s1 == null)
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
                if (s != null && ((zzb) (s2)).zzbxM.zzG(context, s))
                {
                    context = Parcel.obtain();
                    s1.writeToParcel(context, 0);
                    s = context.marshall();
                    context.recycle();
                    intent.putExtra("com.google.android.gms.accounts.ACCOUNT_DATA", s);
                }
                return intent;
            } else
            {
                return null;
            }
        }

        InboxViewer(String s)
        {
            super(s, 0);
        }
    }

}
