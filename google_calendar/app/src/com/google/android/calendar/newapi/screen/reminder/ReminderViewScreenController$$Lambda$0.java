// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.reminder;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.newapi.screen.ViewScreenController;
import com.google.android.calendar.timely.TimelineTask;
import com.google.android.calendar.utils.flow.Flow;
import com.google.android.calendar.utils.fragment.FragmentUtils;
import com.google.android.gms.reminders.model.Task;

// Referenced classes of package com.google.android.calendar.newapi.screen.reminder:
//            ReminderViewScreenModel, ReminderMarkDoneFlow, ReminderViewScreenController

final class arg._cls1
    implements com.google.android.calendar.newapi.commandbar.da._cls0
{

    private final ReminderViewScreenController arg$1;

    public final void onMarkAsDoneClicked()
    {
        ReminderViewScreenController reminderviewscreencontroller = arg$1;
        android.content.Context context = reminderviewscreencontroller.getContext();
        Object obj = (ReminderViewScreenModel)reminderviewscreencontroller.getModel();
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        analyticslogger = (AnalyticsLogger)analyticslogger;
        obj = ((ReminderViewScreenModel) (obj)).task;
        if (Boolean.TRUE.equals(((Task) (obj)).getArchived()))
        {
            obj = "mark_not_done";
        } else
        {
            obj = "mark_done";
        }
        analyticslogger.trackEvent(context, "event_action", ((String) (obj)));
        obj = ((ReminderViewScreenModel)reminderviewscreencontroller.getModel()).task;
        if (!Boolean.TRUE.equals(((Task) (obj)).getArchived()) && reminderviewscreencontroller.setDelayedAction(((ViewScreenController) (reminderviewscreencontroller)).model.timelineItem, 0))
        {
            reminderviewscreencontroller.closeViewScreen();
        } else
        {
            arg._cls1 _lcls1 = new <init>(((TimelineTask)((ViewScreenModel) ((ReminderViewScreenModel)reminderviewscreencontroller.getModel())).timelineItem).accountName, ((ReminderViewScreenModel)reminderviewscreencontroller.getModel()).task);
            Object obj1;
            if (((Fragment) (reminderviewscreencontroller)).mHost == null)
            {
                obj1 = null;
            } else
            {
                obj1 = (FragmentActivity)((Fragment) (reminderviewscreencontroller)).mHost.mActivity;
            }
            obj1 = (Flow)FragmentUtils.attachFragment(((android.app.Activity) (obj1)), ((Fragment) (reminderviewscreencontroller)).mFragmentManager, com/google/android/calendar/newapi/screen/reminder/ReminderMarkDoneFlow, reminderviewscreencontroller, null);
            if (obj1 != null)
            {
                _lcls1.accept(obj1);
                return;
            }
        }
    }

    (ReminderViewScreenController reminderviewscreencontroller)
    {
        arg$1 = reminderviewscreencontroller;
    }
}
