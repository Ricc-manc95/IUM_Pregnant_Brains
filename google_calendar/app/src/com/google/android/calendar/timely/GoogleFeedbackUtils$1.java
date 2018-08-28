// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import com.google.android.calendar.v2a.UnifiedSyncUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.feedback.Feedback;
import com.google.android.gms.feedback.FeedbackOptions;
import com.google.android.gms.feedback.FileTeleporter;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely:
//            GoogleFeedbackUtils

final class val.activity extends AsyncTask
{

    private final Activity val$activity;
    private final Context val$applicationContext;
    private final boolean val$isUss;
    private final Bundle val$params;
    private final Bitmap val$screenshot;

    protected final Object doInBackground(Object aobj[])
    {
        boolean flag1 = false;
        GoogleFeedbackUtils.logSettings(val$applicationContext);
        aobj = new com.google.android.gms.feedback.r();
        aobj.zzbhc = val$screenshot;
        if (val$isUss)
        {
            aobj.mCategoryTag = "com.google.android.calendar.USS_FEEDBACK";
        }
        boolean flag = flag1;
        if (val$params != null)
        {
            flag = flag1;
            if (val$params.getBoolean("db_dump_from_drawer", false))
            {
                flag = true;
            }
        }
        if (flag)
        {
            byte abyte0[] = GoogleFeedbackUtils.dumpStoreData(val$applicationContext);
            ((com.google.android.gms.feedback.r) (aobj)).mFileTeleporters.add(new FileTeleporter(abyte0, "text/plain", "Dump store for all accounts"));
            GoogleFeedbackUtils.addDataToFeedback(((com.google.android.gms.feedback.r) (aobj)), "USS store dump for all accounts", UnifiedSyncUtils.getDatabaseDump(val$applicationContext));
        }
        Activity activity1;
        if (val$isUss || flag)
        {
            activity1 = val$activity;
        }
        GoogleFeedbackUtils.addEssentialDataToFeedback(((com.google.android.gms.feedback.r) (aobj)), val$activity);
        return ((com.google.android.gms.feedback.r) (aobj)).build();
    }

    protected final void onPostExecute(Object obj)
    {
        obj = (FeedbackOptions)obj;
        Object obj1 = val$applicationContext;
        if (GoogleFeedbackUtils.apiClient == null)
        {
            obj1 = (new com.google.android.gms.common.api.r(((Context) (obj1)).getApplicationContext())).addApi(Feedback.API);
            Object obj2 = new <init>();
            if (obj2 == null)
            {
                throw new NullPointerException(String.valueOf("Listener must not be null"));
            }
            ((com.google.android.gms.common.api.r) (obj1)).zzaJl.add(obj2);
            obj2 = new <init>();
            if (obj2 == null)
            {
                throw new NullPointerException(String.valueOf("Listener must not be null"));
            }
            ((com.google.android.gms.common.api.r) (obj1)).zzaJm.add(obj2);
            GoogleFeedbackUtils.apiClient = ((com.google.android.gms.common.api.r) (obj1)).build();
        }
        if (!GoogleFeedbackUtils.apiClient.isConnected())
        {
            GoogleFeedbackUtils.apiClient.connect();
        }
        Feedback.startFeedback(GoogleFeedbackUtils.apiClient, ((FeedbackOptions) (obj))).setResultCallback(new <init>());
    }

    ()
    {
        val$applicationContext = context;
        val$screenshot = bitmap;
        val$isUss = flag;
        val$params = bundle;
        val$activity = activity1;
        super();
    }
}
