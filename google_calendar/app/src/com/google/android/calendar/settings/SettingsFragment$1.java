// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings;

import android.support.v4.app.Fragment;
import android.util.Log;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.settings.home.HomeViewModel;
import com.google.common.util.concurrent.FutureCallback;
import java.util.concurrent.CancellationException;

// Referenced classes of package com.google.android.calendar.settings:
//            SettingsFragment

final class val.receiver
    implements FutureCallback
{

    private final SettingsFragment this$0;
    private final Consumer val$receiver;

    public final void onFailure(Throwable throwable)
    {
        if (!(throwable instanceof CancellationException))
        {
            Log.wtf(tag, "Unable to load preferences fragment.", throwable);
            throwable = getContext();
            if (throwable != null)
            {
                AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
                if (analyticslogger == null)
                {
                    throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                }
                ((AnalyticsLogger)analyticslogger).trackEvent(throwable, "settings_fragment_model_loaded_failure", analyticsCategory);
            }
        }
    }

    public final void onSuccess(Object obj)
    {
        obj = (HomeViewModel)obj;
        val$receiver.accept(obj);
        obj = getContext();
        if (obj != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).trackEvent(((android.content.Context) (obj)), "settings_fragment_model_loaded", analyticsCategory);
        }
    }

    der()
    {
        this$0 = final_settingsfragment;
        val$receiver = Consumer.this;
        super();
    }
}
