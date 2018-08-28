// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.apps.calendar.util.gms.GmsFutures;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.habit.HabitClient;
import com.google.android.calendar.api.habit.HabitModifications;
import com.google.android.calendar.belong.BelongUtils;
import com.google.android.calendar.utils.viewpager.DisablableViewPager;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.groove:
//            CreateGrooveActivity, GrooveScheduleFragment, BackButtonView

final class arg._cls1
    implements Consumer
{

    private final CreateGrooveActivity arg$1;

    public final void accept(Object obj)
    {
        obj = arg$1;
        obj.confirmedScheduling = true;
        Object obj1 = ((CreateGrooveActivity) (obj)).scheduleFragment;
        ((GrooveScheduleFragment) (obj1)).backArrow.setIcon(1);
        ((GrooveScheduleFragment) (obj1)).viewPager.setEnabled(false);
        obj1 = CalendarApi.Habits.create(((CreateGrooveActivity) (obj)).habitModifications);
        Object obj2 = .instance;
        obj1 = AbstractTransformFuture.create(GmsFutures.asFuture(((com.google.android.gms.common.api.PendingResult) (obj1))), new com.google.android.apps.calendar.util.gms.ce(((com.google.common.base.Function) (obj2))), com.google.common.util.concurrent.STANCE);
        obj2 = LogUtils.newFailureLoggingCallback(new <init>(((CreateGrooveActivity) (obj))), CreateGrooveActivity.TAG, "Habit creation failed.", new Object[0]);
        CalendarExecutor calendarexecutor = CalendarExecutor.BACKGROUND;
        if (obj2 == null)
        {
            throw new NullPointerException();
        }
        ((ListenableFuture) (obj1)).addListener(new com.google.common.util.concurrent.(((java.util.concurrent.Future) (obj1)), ((com.google.common.util.concurrent.FutureCallback) (obj2))), calendarexecutor);
        if (((CreateGrooveActivity) (obj)).habitModifications.isFitIntegrationEnabled())
        {
            BelongUtils.onIntegrationStatusChange(((android.content.Context) (obj)), true);
        }
    }

    (CreateGrooveActivity creategrooveactivity)
    {
        arg$1 = creategrooveactivity;
    }
}
