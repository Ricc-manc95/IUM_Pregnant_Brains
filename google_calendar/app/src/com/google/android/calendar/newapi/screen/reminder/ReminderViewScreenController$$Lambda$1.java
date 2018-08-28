// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.reminder;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.newapi.logging.LoggingUtils;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.newapi.screen.ViewScreenController;
import com.google.android.calendar.timely.TimelineTask;
import com.google.android.calendar.utils.flow.Flow;
import com.google.android.calendar.utils.fragment.FragmentUtils;

// Referenced classes of package com.google.android.calendar.newapi.screen.reminder:
//            ReminderViewScreenModel, ReminderDeleteFlow, ReminderViewScreenController

final class arg._cls1
    implements com.google.android.calendar.newapi.overflow.
{

    private final ReminderViewScreenController arg$1;

    public final void onDeleteClicked()
    {
        ReminderViewScreenController reminderviewscreencontroller = arg$1;
        LoggingUtils.logDeleteClicked(reminderviewscreencontroller.getContext(), reminderviewscreencontroller.getModel());
        arg._cls1 _lcls1 = new nit>(((TimelineTask)((ViewScreenController) (reminderviewscreencontroller)).model.timelineItem).accountName, ((ReminderViewScreenModel)reminderviewscreencontroller.getModel()).task);
        Object obj;
        if (((Fragment) (reminderviewscreencontroller)).mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)((Fragment) (reminderviewscreencontroller)).mHost.mActivity;
        }
        obj = (Flow)FragmentUtils.attachFragment(((android.app.Activity) (obj)), ((Fragment) (reminderviewscreencontroller)).mFragmentManager, com/google/android/calendar/newapi/screen/reminder/ReminderDeleteFlow, reminderviewscreencontroller, null);
        if (obj != null)
        {
            _lcls1.accept(obj);
        }
    }

    (ReminderViewScreenController reminderviewscreencontroller)
    {
        arg$1 = reminderviewscreencontroller;
    }
}
