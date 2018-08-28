// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementAttacher;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementHolder;
import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.api.SettingsCache;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.android.calendar.prefs.PrefService;
import com.google.android.calendar.utils.account.PrimaryAccountSelector;
import com.google.android.calendar.utils.permission.PermissionsUtil;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.google.android.calendar:
//            CalendarApplication

final class this._cls0
    implements Runnable
{

    private final CalendarApplication this$0;

    public final void run()
    {
        if (runningActivityCount.decrementAndGet() == 0)
        {
            Object obj = CalendarApplication.this;
            if (PrefService.instance == null)
            {
                if (PrimaryAccountSelector.instance == null)
                {
                    PrimaryAccountSelector.instance = new PrimaryAccountSelector(((android.content.Context) (obj)));
                }
                PrefService.instance = new PrefService(PrimaryAccountSelector.instance);
            }
            obj = PrefService.instance;
            if (((PrefService) (obj)).settingsSubscription != null)
            {
                ((PrefService) (obj)).settingsSubscription.cancel(false);
                obj.settingsSubscription = null;
            }
            obj = VisualElementHolder.instance;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
            }
            ((VisualElementAttacher)obj).recordAppVisibilityEvent(CalendarApplication.this, false);
            obj = CalendarListEntryCache.instance;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("Not initialized"));
            }
            ((ListenableFutureCache)obj).stop();
            obj = SettingsCache.instance;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("Not initialized"));
            }
            ((ListenableFutureCache)obj).stop();
            obj = Features.instance;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            ((FeatureConfig)obj).uss();
            LogUtils.v(PermissionsUtil.TAG, "Clearing cached permissions", new Object[0]);
            PermissionsUtil.grantedPermissions.clear();
        }
    }

    ssionsUtil()
    {
        this$0 = CalendarApplication.this;
        super();
    }
}
