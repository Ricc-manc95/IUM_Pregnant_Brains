// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.feedback.ErrorReport;
import com.google.android.gms.feedback.FeedbackOptions;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.gms.googlehelp.GoogleHelpLauncher;
import com.google.android.gms.googlehelp.zzc;
import com.google.android.gms.internal.zzaoc;

// Referenced classes of package com.google.android.calendar.timely:
//            GoogleFeedbackUtils

final class val.applicationContext extends AsyncTask
{

    private final Activity val$activity;
    private final Context val$applicationContext;
    private final GoogleHelp val$googleHelp;
    private final Bitmap val$screenshotBitmap;

    protected final Object doInBackground(Object aobj[])
    {
        aobj = new com.google.android.gms.feedback.r();
        aobj.zzbhc = val$screenshotBitmap;
        GoogleFeedbackUtils.addEssentialDataToFeedback(((com.google.android.gms.feedback.r) (aobj)), val$activity);
        return ((com.google.android.gms.feedback.r) (aobj)).build();
    }

    protected final void onPostExecute(Object obj)
    {
        obj = (FeedbackOptions)obj;
        Object obj1 = val$googleHelp;
        obj1.zzbwv = zzaoc.zza(((FeedbackOptions) (obj)), val$applicationContext.getFilesDir());
        ((GoogleHelp) (obj1)).zzbwv.launcher = "GoogleHelp";
        obj = val$googleHelp;
        obj = (new Intent("com.google.android.gms.googlehelp.HELP")).setPackage("com.google.android.gms").putExtra("EXTRA_GOOGLE_HELP", ((android.os.Parcelable) (obj)));
        obj1 = new GoogleHelpLauncher(val$activity);
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
            zzc.zza(((GoogleHelpLauncher) (obj1)).mApiClient, new com.google.android.gms.googlehelp.init>(((GoogleHelpLauncher) (obj1)), ((Intent) (obj))));
            return;
        } else
        {
            ((GoogleHelpLauncher) (obj1)).handlePlayServicesUnavailable(i, ((Intent) (obj)));
            return;
        }
    }

    ()
    {
        val$screenshotBitmap = bitmap;
        val$activity = activity1;
        val$googleHelp = googlehelp;
        val$applicationContext = context;
        super();
    }
}
