// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.loggers;

import android.app.ApplicationErrorReport;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.calendarcommon2.LogUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.feedback.Feedback;
import com.google.android.gms.feedback.FeedbackOptions;

public class SilentFeedbackReceiver extends BroadcastReceiver
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/loggers/SilentFeedbackReceiver);

    public SilentFeedbackReceiver()
    {
    }

    static FeedbackOptions buildCrashOptions(Intent intent)
    {
        com.google.android.gms.feedback.FeedbackOptions.CrashBuilder crashbuilder = new com.google.android.gms.feedback.FeedbackOptions.CrashBuilder();
        if (intent == null)
        {
            return crashbuilder.build();
        }
        crashbuilder.mDescription = " ";
        crashbuilder.mExcludePii = true;
        if (intent.hasExtra("exceptionClass"))
        {
            String s = intent.getStringExtra("exceptionClass");
            crashbuilder.mApplicationErrorReport.crashInfo.exceptionClassName = s;
        }
        if (intent.hasExtra("stackTrace"))
        {
            String s1 = intent.getStringExtra("stackTrace");
            crashbuilder.mApplicationErrorReport.crashInfo.stackTrace = s1;
        }
        if (intent.hasExtra("throwingClass"))
        {
            String s2 = intent.getStringExtra("throwingClass");
            crashbuilder.mApplicationErrorReport.crashInfo.throwClassName = s2;
        }
        if (intent.hasExtra("throwingFile"))
        {
            String s3 = intent.getStringExtra("throwingFile");
            crashbuilder.mApplicationErrorReport.crashInfo.throwFileName = s3;
        }
        if (intent.hasExtra("throwingLine"))
        {
            int i = intent.getIntExtra("throwingLine", -1);
            crashbuilder.mApplicationErrorReport.crashInfo.throwLineNumber = i;
        }
        if (intent.hasExtra("throwingMethod"))
        {
            String s4 = intent.getStringExtra("throwingMethod");
            crashbuilder.mApplicationErrorReport.crashInfo.throwMethodName = s4;
        }
        if (intent.hasExtra("categoryTag"))
        {
            crashbuilder.mCategoryTag = intent.getStringExtra("categoryTag");
        }
        return crashbuilder.build();
    }

    public static Intent createIntent(Context context, String s, String s1, String s2, String s3, int i, String s4, String s5)
    {
        context = new Intent(context, com/google/android/apps/calendar/loggers/SilentFeedbackReceiver);
        context.putExtra("exceptionClass", s);
        context.putExtra("stackTrace", s1);
        context.putExtra("throwingClass", s2);
        context.putExtra("throwingFile", s3);
        context.putExtra("throwingLine", i);
        context.putExtra("throwingMethod", s4);
        context.putExtra("categoryTag", s5);
        return context;
    }

    public void onReceive(final Context googleApiClient, final Intent intent)
    {
        LogUtils.d(TAG, "onReceive", new Object[0]);
        final .Lambda._cls0 afterAction = goAsync();
        afterAction.getClass();
        class .Lambda._cls0
            implements Runnable
        {

            private final android.content.BroadcastReceiver.PendingResult arg$1;

            public final void run()
            {
                arg$1.finish();
            }

            .Lambda._cls0(android.content.BroadcastReceiver.PendingResult pendingresult)
            {
                arg$1 = pendingresult;
            }
        }

        afterAction = new .Lambda._cls0(afterAction);
        googleApiClient = (new com.google.android.gms.common.api.GoogleApiClient.Builder(googleApiClient)).addApi(Feedback.API).build();
        googleApiClient.registerConnectionCallbacks(new _cls1());
        class .Lambda._cls1
            implements com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
        {

            private final Runnable arg$1;

            public final void onConnectionFailed(ConnectionResult connectionresult)
            {
                Runnable runnable = arg$1;
                LogUtils.e(SilentFeedbackReceiver.TAG, "GoogleApiClient silent feedback connection failed with result: %d", new Object[] {
                    Integer.valueOf(connectionresult.zzaEP)
                });
                runnable.run();
            }

            .Lambda._cls1(Runnable runnable)
            {
                arg$1 = runnable;
            }
        }

        googleApiClient.registerConnectionFailedListener(new .Lambda._cls1(afterAction));
        googleApiClient.connect();
    }


    private class _cls1
        implements com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    {

        private final Runnable val$afterAction;
        private final GoogleApiClient val$googleApiClient;
        private final Intent val$intent;

        public final void onConnected(Bundle bundle)
        {
            LogUtils.d(SilentFeedbackReceiver.TAG, "Sending silent feedback now.", new Object[0]);
            class .Lambda._cls0
                implements ResultCallback
            {

                private final GoogleApiClient arg$1;
                private final Runnable arg$2;

                public final void onResult(Result result)
                {
                    result = arg$1;
                    Runnable runnable = arg$2;
                    if (result.isConnected())
                    {
                        result.disconnect();
                    }
                    runnable.run();
                }

                .Lambda._cls0(GoogleApiClient googleapiclient, Runnable runnable)
                {
                    arg$1 = googleapiclient;
                    arg$2 = runnable;
                }
            }

            Feedback.silentSendFeedback(googleApiClient, SilentFeedbackReceiver.buildCrashOptions(intent)).setResultCallback(new .Lambda._cls0(googleApiClient, afterAction));
        }

        public final void onConnectionSuspended(int i)
        {
        }

        _cls1()
        {
            googleApiClient = googleapiclient;
            intent = intent1;
            afterAction = runnable;
            super();
        }
    }

}
