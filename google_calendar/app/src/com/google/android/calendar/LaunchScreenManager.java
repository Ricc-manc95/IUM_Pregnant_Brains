// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.os.Handler;
import android.os.Looper;
import android.os.Trace;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.latency.LatencyLogger;
import com.google.android.calendar.latency.LatencyLoggerHolder;
import com.google.android.calendar.timely.TaskBundleFragment;

// Referenced classes of package com.google.android.calendar:
//            AllInOneCalendarActivity

class LaunchScreenManager
    implements com.google.android.calendar.timely.RangedData.ViewUpdatePerformedListener
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/LaunchScreenManager);
    public static android.view.ViewTreeObserver.OnPreDrawListener drawPreventer;
    public final AllInOneCalendarActivity activity;
    public boolean dataLoadedAndViewsUpdated;
    public boolean forceDismiss;
    public boolean launchScreenIsConfigured;
    public boolean launchScreenIsDismissed;
    public boolean minTimeoutElapsed;
    public boolean shouldLaunchTaskBundle;
    public TaskBundleFragment taskBundleFragment;

    LaunchScreenManager(AllInOneCalendarActivity allinonecalendaractivity)
    {
        minTimeoutElapsed = false;
        dataLoadedAndViewsUpdated = false;
        forceDismiss = false;
        launchScreenIsDismissed = false;
        launchScreenIsConfigured = false;
        shouldLaunchTaskBundle = false;
        activity = allinonecalendaractivity;
    }

    static final boolean lambda$static$0$LaunchScreenManager()
    {
        return false;
    }

    final void hideLaunchScreen(boolean flag)
    {
        if (!launchScreenIsDismissed)
        {
            boolean flag1;
            if (forceDismiss || minTimeoutElapsed && dataLoadedAndViewsUpdated)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                activity.findViewById(0x1020002).getRootView().getViewTreeObserver().removeOnPreDrawListener(drawPreventer);
                launchScreenIsDismissed = true;
                Trace.beginSection("Coldstart Marked");
                LatencyLoggerHolder.get().markAt(2, "hideLaunchScreen");
                String s;
                Object obj;
                if (flag)
                {
                    s = "timeout";
                } else
                {
                    s = "loaded";
                }
                obj = activity;
                obj = Features.instance;
                if (obj == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                }
                ((FeatureConfig)obj).alternate_timeline();
                flag = RemoteFeatureConfig.ALTERNATE_TIMELINE.enabled();
                obj = Features.instance;
                if (obj == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                }
                ((FeatureConfig)obj).experimental_options();
                if (flag)
                {
                    AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
                    if (analyticslogger == null)
                    {
                        throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                    }
                    ((AnalyticsLogger)analyticslogger).trackEvent(activity, "loading", "ui_revealed", s, null);
                }
                Trace.endSection();
                LogUtils.d(TAG, "Launchscreen dismissed !", new Object[0]);
                if (shouldLaunchTaskBundle)
                {
                    shouldLaunchTaskBundle = false;
                    class .Lambda._cls2
                        implements Runnable
                    {

                        private final LaunchScreenManager arg$1;

                        public final void run()
                        {
                            LaunchScreenManager launchscreenmanager = arg$1;
                            if (!((FragmentActivity) (launchscreenmanager.activity)).mFragments.mHost.mFragmentManager.isDestroyed())
                            {
                                launchscreenmanager.activity.showOverlayFragment(TaskBundleFragment.TAG, launchscreenmanager.taskBundleFragment);
                                launchscreenmanager.taskBundleFragment = null;
                            }
                        }

            .Lambda._cls2()
            {
                arg$1 = LaunchScreenManager.this;
            }
                    }

                    (new Handler(Looper.getMainLooper())).post(new .Lambda._cls2());
                }
            }
        }
    }

    public final void postOnViewUpdatePerformed()
    {
        dataLoadedAndViewsUpdated = true;
        hideLaunchScreen(false);
    }

    static 
    {
        class .Lambda._cls3
            implements android.view.ViewTreeObserver.OnPreDrawListener
        {

            public static final android.view.ViewTreeObserver.OnPreDrawListener $instance = new .Lambda._cls3();

            public final boolean onPreDraw()
            {
                return LaunchScreenManager.lambda$static$0$LaunchScreenManager();
            }


            private .Lambda._cls3()
            {
            }
        }

        drawPreventer = .Lambda._cls3..instance;
    }
}
