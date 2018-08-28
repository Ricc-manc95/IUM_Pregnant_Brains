// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.reminder;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.view.View;
import com.google.android.calendar.newapi.commandbar.BottomBarController;
import com.google.android.calendar.newapi.commandbar.MoreOptionsSheetController;
import com.google.android.calendar.newapi.commandbar.ReminderCommandBarController;
import com.google.android.calendar.newapi.common.Loader;
import com.google.android.calendar.newapi.logging.LoggingUtils;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.newapi.overflow.OverflowMenuController;
import com.google.android.calendar.newapi.overflow.ReminderOverflowMenuController;
import com.google.android.calendar.newapi.screen.HostDialog;
import com.google.android.calendar.newapi.screen.ViewScreen;
import com.google.android.calendar.newapi.screen.ViewScreenController;
import com.google.android.calendar.newapi.segment.assist.AssistInformationViewSegment;
import com.google.android.calendar.newapi.segment.calendar.ReminderCalendarViewSegment;
import com.google.android.calendar.newapi.segment.reminder_link.ReminderLinkViewSegment;
import com.google.android.calendar.newapi.segment.time.TimeViewSegment;
import com.google.android.calendar.newapi.segment.title.TitleViewSegment;
import com.google.android.calendar.task.TaskDataFactory;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineTask;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.screen.reminder:
//            ReminderViewScreenModel, ReminderViewScreenLoader, ReminderEditScreenController

public class ReminderViewScreenController extends ViewScreenController
    implements ReminderDeleteFlow.Listener, ReminderMarkDoneFlow.Listener
{

    private ReminderDeleteFlow.Factory deleteFactory;
    private ReminderMarkDoneFlow.Factory doneFactory;

    public ReminderViewScreenController()
    {
        deleteFactory = new ReminderDeleteFlow.Factory();
        doneFactory = new ReminderMarkDoneFlow.Factory();
    }

    protected final void createBodySegments(ViewScreenModel viewscreenmodel, List list)
    {
        viewscreenmodel = (ReminderViewScreenModel)viewscreenmodel;
        list.add(new TimeViewSegment(getContext(), viewscreenmodel));
        list.add(new AssistInformationViewSegment(getContext(), viewscreenmodel));
        list.add(new ReminderLinkViewSegment(getContext(), viewscreenmodel));
        list.add(new ReminderCalendarViewSegment(getContext(), viewscreenmodel));
    }

    protected final BottomBarController createCommandBarController(MoreOptionsSheetController moreoptionssheetcontroller)
    {
        class .Lambda._cls0
            implements com.google.android.calendar.newapi.commandbar.ReminderCommandBarController.Listener
        {

            private final ReminderViewScreenController arg$1;

            public final void onMarkAsDoneClicked()
            {
                ReminderViewScreenController reminderviewscreencontroller = arg$1;
                Context context = reminderviewscreencontroller.getContext();
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
                    ReminderMarkDoneFlow.Factory..Lambda._cls0 _lcls0 = new ReminderMarkDoneFlow.Factory..Lambda._cls0(((TimelineTask)((ViewScreenModel) ((ReminderViewScreenModel)reminderviewscreencontroller.getModel())).timelineItem).accountName, ((ReminderViewScreenModel)reminderviewscreencontroller.getModel()).task);
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
                        _lcls0.accept(obj1);
                        return;
                    }
                }
            }

            .Lambda._cls0()
            {
                arg$1 = ReminderViewScreenController.this;
            }
        }

        return new ReminderCommandBarController(new .Lambda._cls0());
    }

    protected final View createHeaderSegment(ViewScreenModel viewscreenmodel)
    {
        return new TitleViewSegment(getContext(), viewscreenmodel);
    }

    public final Loader createLoader(boolean flag)
    {
        Context context = getContext();
        TimelineTask timelinetask = (TimelineTask)super.model.timelineItem;
        ReminderViewScreenModel reminderviewscreenmodel;
        if (flag)
        {
            reminderviewscreenmodel = (ReminderViewScreenModel)getModel();
        } else
        {
            reminderviewscreenmodel = null;
        }
        if (TaskDataFactory.instance == null)
        {
            TaskDataFactory.instance = new TaskDataFactory();
        }
        return new ReminderViewScreenLoader(context, timelinetask, reminderviewscreenmodel, TaskDataFactory.instance.getTaskConnection());
    }

    public final ViewScreenModel createModel(TimelineItem timelineitem)
    {
        return new ReminderViewScreenModel((TimelineTask)timelineitem);
    }

    protected final MoreOptionsSheetController createMoreOptionsSheetController()
    {
        return null;
    }

    protected final OverflowMenuController createOverflowMenuController()
    {
        class .Lambda._cls1
            implements com.google.android.calendar.newapi.overflow.ReminderOverflowMenuController.Callback
        {

            private final ReminderViewScreenController arg$1;

            public final void onDeleteClicked()
            {
                ReminderViewScreenController reminderviewscreencontroller = arg$1;
                LoggingUtils.logDeleteClicked(reminderviewscreencontroller.getContext(), reminderviewscreencontroller.getModel());
                ReminderDeleteFlow.Factory..Lambda._cls0 _lcls0 = new ReminderDeleteFlow.Factory..Lambda._cls0(((TimelineTask)((ViewScreenController) (reminderviewscreencontroller)).model.timelineItem).accountName, ((ReminderViewScreenModel)reminderviewscreencontroller.getModel()).task);
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
                    _lcls0.accept(obj);
                }
            }

            .Lambda._cls1()
            {
                arg$1 = ReminderViewScreenController.this;
            }
        }

        return new ReminderOverflowMenuController(new .Lambda._cls1());
    }

    protected final ViewScreen createViewScreen()
    {
        return new ViewScreen(getContext());
    }

    public final String getTitle()
    {
        return requireContext().getResources().getString(0x7f1303e1);
    }

    public final void onTaskDeleted(boolean flag, int i)
    {
        LoggingUtils.logDelete(getContext(), flag, getModel(), i);
        if (flag)
        {
            if (super.mHost != null && super.mAdded)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                ViewScreen viewscreen = super.viewScreen;
                viewscreen.announceForAccessibility(viewscreen.getContext().getText(0x7f130472));
            }
            closeViewScreen();
        }
    }

    public final void onTaskDoneStateChanged(boolean flag)
    {
        if (flag)
        {
            closeViewScreen();
        }
    }

    protected final void showEditScreen()
    {
        Object obj;
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        ReminderViewScreenModel reminderviewscreenmodel;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        fragmentmanagerimpl = super.mFragmentManager;
        reminderviewscreenmodel = (ReminderViewScreenModel)getModel();
        HostDialog.showWithChildFragment(((android.app.Activity) (obj)), fragmentmanagerimpl, (ReminderEditScreenController)ReminderEditScreenController.fromViewScreen(new ReminderEditScreenController(), reminderviewscreenmodel), null);
    }
}
