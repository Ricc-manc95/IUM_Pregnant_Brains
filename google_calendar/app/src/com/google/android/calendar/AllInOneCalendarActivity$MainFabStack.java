// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.app.Activity;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.utils.SystemWindowInsetApplier;
import java.util.Map;
import java.util.Stack;

// Referenced classes of package com.google.android.calendar:
//            AllInOneCalendarActivity, CalendarController, FullScreenManager, SpeedDialLayout

public final class speedDialLayout extends speedDialLayout
{

    public SpeedDialLayout speedDialLayout;
    public final AllInOneCalendarActivity this$0;

    final View findCreateFab()
    {
        return findViewById(0x7f100146);
    }

    final Time getCreateFabDay()
    {
        Time time = new Time(timeZone);
        Time time1 = controller.time;
        time1.writeFieldsToImpl();
        long l = time1.impl.toMillis(false);
        time.impl.timezone = time.timezone;
        time.impl.set(l);
        time.impl.toMillis(true);
        time.copyFieldsFromImpl();
        return time;
    }

    public final SpeedDialLayout getSpeedDial()
    {
        if (super..isEmpty())
        {
            if (speedDialLayout == null)
            {
                AllInOneCalendarActivity allinonecalendaractivity = AllInOneCalendarActivity.this;
                class .Lambda._cls0
                    implements SpeedDialLayout.EndSpeedDialFadeOut
                {

                    private final AllInOneCalendarActivity.MainFabStack arg$1;

                    public final void onEndSpeedDialFadeOut()
                    {
                        arg$1.removeSpeedDial();
                    }

            .Lambda._cls0()
            {
                arg$1 = AllInOneCalendarActivity.MainFabStack.this;
            }
                }

                FullScreenManager fullscreenmanager;
                SpeedDialLayout speeddiallayout;
                if (FullScreenManager.fullScreenManager != null)
                {
                    fullscreenmanager = FullScreenManager.fullScreenManager;
                } else
                {
                    fullscreenmanager = new FullScreenManager(allinonecalendaractivity);
                    FullScreenManager.fullScreenManager = fullscreenmanager;
                }
                speedDialLayout = new SpeedDialLayout(allinonecalendaractivity);
                speedDialLayout.endSpeedDialFadeOutListener = new .Lambda._cls0();
                speeddiallayout = speedDialLayout;
                fullscreenmanager.systemWindowInsetApplier.addView(speeddiallayout, 2, 2);
                ViewCompat.setOnApplyWindowInsetsListener(speeddiallayout, fullscreenmanager.systemWindowInsetApplier);
                ViewCompat.requestApplyInsets(speeddiallayout);
                fullscreenmanager.layoutParams.tParams = allinonecalendaractivity.getWindow().getDecorView().getRootView().getWindowToken();
                fullscreenmanager.windowManager.addView(speeddiallayout, fullscreenmanager.layoutParams);
            }
            return speedDialLayout;
        } else
        {
            return super.eedDial();
        }
    }

    public final boolean isSpeedDialExpanded()
    {
        SpeedDialLayout speeddiallayout;
        if (super..isEmpty())
        {
            speeddiallayout = speedDialLayout;
        } else
        {
            speeddiallayout = super.eedDial();
        }
        return speeddiallayout != null && speeddiallayout.isExpanded;
    }

    final void removeSpeedDial()
    {
        Object obj = AllInOneCalendarActivity.this;
        SpeedDialLayout speeddiallayout = speedDialLayout;
        if (speeddiallayout != null && ViewCompat.isAttachedToWindow(speeddiallayout))
        {
            if (FullScreenManager.fullScreenManager != null)
            {
                obj = FullScreenManager.fullScreenManager;
            } else
            {
                obj = new FullScreenManager(((Activity) (obj)));
                FullScreenManager.fullScreenManager = ((FullScreenManager) (obj));
            }
            if (((FullScreenManager) (obj)).windowManager != null)
            {
                ((FullScreenManager) (obj)).windowManager.removeViewImmediate(speeddiallayout);
            }
            obj = ((FullScreenManager) (obj)).systemWindowInsetApplier;
            if (speeddiallayout == null)
            {
                throw new NullPointerException();
            }
            if (((SystemWindowInsetApplier) (obj)).customHandledViews.remove(speeddiallayout) == null)
            {
                com.google.android.calendar.utils.eViewRegistration eviewregistration = (com.google.android.calendar.utils.eViewRegistration)((SystemWindowInsetApplier) (obj)).views.remove(speeddiallayout);
                if (eviewregistration == null)
                {
                    LogUtils.wtf(SystemWindowInsetApplier.TAG, "Could not remove view %s in removeView. Items in mViews: %s", new Object[] {
                        speeddiallayout, ((SystemWindowInsetApplier) (obj)).views.keySet()
                    });
                } else
                if (eviewregistration.applicationStyle == 1)
                {
                    SystemWindowInsetApplier.applyMarginToView(eviewregistration, eviewregistration.oldLeftSpacing, eviewregistration.oldTopSpacing, eviewregistration.oldRightSpacing, eviewregistration.oldBottomSpacing);
                } else
                {
                    SystemWindowInsetApplier.applyPaddingToView(eviewregistration, eviewregistration.oldLeftSpacing, eviewregistration.oldTopSpacing, eviewregistration.oldRightSpacing, eviewregistration.oldBottomSpacing);
                }
            }
            ViewCompat.setOnApplyWindowInsetsListener(speeddiallayout, null);
        }
        speedDialLayout = null;
    }

    public nsitiveViewRegistration()
    {
        this$0 = AllInOneCalendarActivity.this;
        super();
        speedDialLayout = null;
    }
}
