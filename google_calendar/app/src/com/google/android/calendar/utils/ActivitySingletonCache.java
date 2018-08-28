// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.android.calendarcommon2.LogUtils;
import java.util.HashMap;

public abstract class ActivitySingletonCache
    implements android.app.Application.ActivityLifecycleCallbacks
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/utils/ActivitySingletonCache);
    private final HashMap instances = new HashMap();
    private boolean registeredLifecycleListener;

    public ActivitySingletonCache()
    {
        registeredLifecycleListener = false;
    }

    public abstract Object createInstance(Activity activity);

    public final Object getInstance(Activity activity)
    {
        Object obj;
        if (activity.isDestroyed())
        {
            obj = createInstance(activity);
            LogUtils.wtf(TAG, "Requesting singleton of class %s with activity %s, but activity is already destroyed.", new Object[] {
                obj.getClass().getName(), activity
            });
        } else
        {
            Object obj1 = instances.get(activity);
            obj = obj1;
            if (obj1 == null)
            {
                Object obj2 = createInstance(activity);
                instances.put(activity, obj2);
                obj = obj2;
                if (!registeredLifecycleListener)
                {
                    activity.getApplication().registerActivityLifecycleCallbacks(this);
                    registeredLifecycleListener = true;
                    return obj2;
                }
            }
        }
        return obj;
    }

    public void onActivityCreated(Activity activity, Bundle bundle)
    {
    }

    public void onActivityDestroyed(Activity activity)
    {
        instances.remove(activity);
    }

    public void onActivityPaused(Activity activity)
    {
    }

    public void onActivityResumed(Activity activity)
    {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle)
    {
    }

    public void onActivityStarted(Activity activity)
    {
    }

    public void onActivityStopped(Activity activity)
    {
    }

}
