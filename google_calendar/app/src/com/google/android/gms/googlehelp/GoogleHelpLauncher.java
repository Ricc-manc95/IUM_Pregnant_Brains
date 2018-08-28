// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.googlehelp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.List;

// Referenced classes of package com.google.android.gms.googlehelp:
//            zzc, GoogleHelp

public final class GoogleHelpLauncher
{

    public final Activity mActivity;
    public final GoogleApiClient mApiClient;

    public GoogleHelpLauncher(Activity activity)
    {
        this(activity, (new com.google.android.gms.common.api.GoogleApiClient.Builder(activity)).addApi(zzc.API).build());
    }

    private GoogleHelpLauncher(Activity activity, GoogleApiClient googleapiclient)
    {
        activity.getCacheDir();
        mActivity = activity;
        mApiClient = googleapiclient;
    }

    public final void handlePlayServicesUnavailable(int i, Intent intent)
    {
        intent = (GoogleHelp)intent.getParcelableExtra("EXTRA_GOOGLE_HELP");
        intent = (new Intent("android.intent.action.VIEW")).setData(((GoogleHelp) (intent)).zzbwq);
        if (i != 7)
        {
            boolean flag;
            if (mActivity.getPackageManager().queryIntentActivities(intent, 0).size() > 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                mActivity.startActivity(intent);
                return;
            }
        }
        GooglePlayServicesUtil.showErrorDialogFragment(i, mActivity, 0);
    }
}
