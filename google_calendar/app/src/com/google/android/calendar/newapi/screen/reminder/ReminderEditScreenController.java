// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.reminder;

import android.accounts.Account;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.text.TextUtils;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.Optionals;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.Utils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.newapi.common.Loader;
import com.google.android.calendar.newapi.logging.LoggingUtils;
import com.google.android.calendar.newapi.logging.ReminderEditLogMetrics;
import com.google.android.calendar.newapi.model.edit.EditScreenModel;
import com.google.android.calendar.newapi.screen.EditScreenController;
import com.google.android.calendar.newapi.screen.HostedFragment;
import com.google.android.calendar.newapi.screen.SegmentMap;
import com.google.android.calendar.newapi.screen.SegmentViews;
import com.google.android.calendar.newapi.screen.ViewScreenController;
import com.google.android.calendar.newapi.segment.assist.AssistInformationEditSegmentController;
import com.google.android.calendar.newapi.segment.calendar.ReminderCalendarEditSegmentController;
import com.google.android.calendar.newapi.segment.recurrence.RecurrenceEditSegmentController;
import com.google.android.calendar.newapi.segment.time.ReminderTimeEditSegmentController;
import com.google.android.calendar.newapi.segment.title.ReminderTitleEditSegmentController;
import com.google.android.calendar.task.TaskUtils;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.utils.flow.Flow;
import com.google.android.calendar.utils.fragment.FragmentUtils;
import com.google.android.gms.reminders.model.DateTime;
import com.google.android.gms.reminders.model.Task;
import com.google.android.gms.reminders.model.TaskId;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Arrays;
import java.util.Iterator;

// Referenced classes of package com.google.android.calendar.newapi.screen.reminder:
//            ReminderEditSegmentProvider, ReminderEditScreenModel, ReminderEditScreenLoader, ReminderSaveFlow, 
//            ReminderViewScreenController, ReminderViewScreenModel

public final class ReminderEditScreenController extends EditScreenController
    implements ReminderSaveFlow.Listener
{

    private ReminderEditScreenLoader.Factory loaderFactory;
    private ReminderEditScreenModel.Factory modelFactory;
    private ReminderSaveFlow.Factory saveFlow;
    private ReminderEditSegmentProvider segmentProvider;

    public ReminderEditScreenController()
    {
        saveFlow = new ReminderSaveFlow.Factory();
        modelFactory = new ReminderEditScreenModel.Factory();
        segmentProvider = new ReminderEditSegmentProvider();
        loaderFactory = new ReminderEditScreenLoader.Factory();
    }

    protected final Loader createLoader()
    {
        boolean flag;
        if (getArguments() != null && getArguments().containsKey("ARGS_VIEW_SCREEN_MODEL"))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            android.content.Context context;
            if (((ReminderEditScreenModel)getModel()).task != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                break MISSING_BLOCK_LABEL_111;
            }
        }
        flag = true;
_L1:
        if (flag)
        {
            context = getContext();
            ReminderEditScreenModel remindereditscreenmodel = (ReminderEditScreenModel)getModel();
            String s;
            if (remindereditscreenmodel.task.getTaskId() == null)
            {
                s = null;
            } else
            {
                s = remindereditscreenmodel.task.getTaskId().getClientAssignedId();
            }
            return new ReminderEditScreenLoader(context, remindereditscreenmodel.getAccount(), s, remindereditscreenmodel, null);
        }
        break MISSING_BLOCK_LABEL_135;
        flag = false;
          goto _L1
        Bundle bundle;
        android.content.Context context1;
        CalendarProperties calendarproperties1;
        String s1;
        CalendarProperties calendarproperties;
        if (getArguments() != null)
        {
            bundle = getArguments().getBundle("ARG_EXTRAS");
        } else
        {
            bundle = null;
        }
        context1 = getContext();
        calendarproperties = CalendarProperties.instance;
        if (calendarproperties == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        }
        calendarproperties1 = (CalendarProperties)calendarproperties;
        calendarproperties = CalendarProperties.instance;
        if (calendarproperties == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        }
        s1 = (String)((CalendarProperties)calendarproperties).getPropertyValue(12);
        if (s1 == null) goto _L3; else goto _L2
_L2:
        UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)((ImmutableCollection)calendarproperties1.calendars.values()).iterator();
_L7:
        if (!unmodifiableiterator.hasNext()) goto _L5; else goto _L4
_L4:
        Account account = ((CalendarListEntry)unmodifiableiterator.next()).getDescriptor().account;
        if (!TextUtils.equals(account.name, s1) || !AccountUtil.isGoogleAccount(account)) goto _L7; else goto _L6
_L6:
        return new ReminderEditScreenLoader(context1, account, null, null, bundle);
_L5:
        calendarproperties1.setDefaultTaskAccountValue(null, false);
_L3:
        account = null;
        if (true) goto _L6; else goto _L8
_L8:
    }

    protected final EditScreenModel createModel()
    {
        return new ReminderEditScreenModel();
    }

    protected final SegmentMap createSegments()
    {
        return SegmentMap.create(this, (ReminderEditScreenModel)getModel(), Arrays.asList(new Class[] {
            com/google/android/calendar/newapi/segment/title/ReminderTitleEditSegmentController, com/google/android/calendar/newapi/segment/time/ReminderTimeEditSegmentController, com/google/android/calendar/newapi/segment/calendar/ReminderCalendarEditSegmentController, com/google/android/calendar/newapi/segment/assist/AssistInformationEditSegmentController, com/google/android/calendar/newapi/segment/recurrence/RecurrenceEditSegmentController
        }));
    }

    protected final int getDiscardChangesMessage()
    {
        return !((ReminderEditScreenModel)getModel()).isNew() ? 0x7f130183 : 0x7f130185;
    }

    protected final SegmentViews getOrderedSegments(SegmentMap segmentmap)
    {
        SegmentViews segmentviews = new SegmentViews();
        Optionals.ifPresent(segmentmap.getSegmentView(com/google/android/calendar/newapi/segment/title/ReminderTitleEditSegmentController), new com.google.android.calendar.newapi.screen.SegmentViews..Lambda._cls0(segmentviews));
        Optionals.ifPresent(segmentmap.getSegmentView(com/google/android/calendar/newapi/segment/calendar/ReminderCalendarEditSegmentController), new com.google.android.calendar.newapi.screen.SegmentViews..Lambda._cls2(segmentviews));
        Optionals.ifPresent(segmentmap.getSegmentView(com/google/android/calendar/newapi/segment/assist/AssistInformationEditSegmentController), new com.google.android.calendar.newapi.screen.SegmentViews..Lambda._cls2(segmentviews));
        Optionals.ifPresent(segmentmap.getSegmentView(com/google/android/calendar/newapi/segment/time/ReminderTimeEditSegmentController), new com.google.android.calendar.newapi.screen.SegmentViews..Lambda._cls1(segmentviews));
        Optionals.ifPresent(segmentmap.getSegmentView(com/google/android/calendar/newapi/segment/recurrence/RecurrenceEditSegmentController), new com.google.android.calendar.newapi.screen.SegmentViews..Lambda._cls2(segmentviews));
        return segmentviews;
    }

    protected final void onLoadingCompleted(boolean flag)
    {
        super.onLoadingCompleted(flag);
        ReminderEditLogMetrics remindereditlogmetrics = ((ReminderEditScreenModel)getModel()).logMetrics;
        if (remindereditlogmetrics.loadedTime == -1L)
        {
            remindereditlogmetrics.loadedTime = SystemClock.elapsedRealtime();
        }
    }

    public final void onSaveClicked()
    {
        if (((ReminderEditScreenModel)getModel()).isModified()) goto _L2; else goto _L1
_L1:
        dismiss();
_L4:
        return;
_L2:
        Object obj;
label0:
        {
            obj = ((ReminderEditScreenModel)getModel()).task;
            if (TextUtils.isEmpty(((Task) (obj)).getTitle()))
            {
                Toast.makeText(getContext(), 0x7f130474, 0).show();
                return;
            }
            if (((Task) (obj)).getArchived().booleanValue())
            {
                obj = new com.google.android.gms.reminders.model.Task.Builder(((Task) (obj)));
                obj.zzcjg = Boolean.valueOf(false);
                obj = ((com.google.android.gms.reminders.model.Task.Builder) (obj)).build();
            }
            if (((Task) (obj)).getDueDate() == null)
            {
                break label0;
            }
            Object obj1 = getContext();
            DateTime datetime = ((Task) (obj)).getDueDate();
            long l1 = TaskUtils.dateTimeToMillis(Utils.getTimeZone(((android.content.Context) (obj1))), datetime);
            boolean flag;
            long l;
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            if (l1 > l)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                break label0;
            }
            obj1 = ((Task) (obj)).getDueDate();
            if (((DateTime) (obj1)).getAllDay().booleanValue() || ((DateTime) (obj1)).getTime() == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                android.content.Context context = getContext();
                DateTime datetime1 = ((Task) (obj)).getDueDate();
                if (Utils.getTodayJulianDay(context) == Utils.getJulianDay(datetime1.getYear().intValue(), datetime1.getMonth().intValue() - 1, datetime1.getDay().intValue()))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    break label0;
                }
            }
            Toast.makeText(getContext(), 0x7f13047d, 0).show();
            return;
        }
        ReminderSaveFlow.Factory..Lambda._cls0 _lcls0 = new ReminderSaveFlow.Factory..Lambda._cls0(((ReminderEditScreenModel)getModel()).getAccount().name, ((ReminderEditScreenModel)getModel()).original, ((Task) (obj)));
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = (Flow)FragmentUtils.attachFragment(((android.app.Activity) (obj)), super.mFragmentManager, com/google/android/calendar/newapi/screen/reminder/ReminderSaveFlow, this, null);
        if (obj != null)
        {
            _lcls0.accept(obj);
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void onTaskSaved(boolean flag, Task task, int i)
    {
        android.content.Context context;
        ReminderEditScreenModel remindereditscreenmodel;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_498;
        }
        context = getContext();
        remindereditscreenmodel = (ReminderEditScreenModel)getModel();
        if (context == null) goto _L2; else goto _L1
_L1:
        Object obj1;
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        obj1 = (AnalyticsLogger)analyticslogger;
        i;
        JVM INSTR tableswitch 1 2: default 80
    //                   1 127
    //                   2 135;
           goto _L3 _L4 _L5
_L3:
        Object obj = "only_this_instance";
_L7:
        ((AnalyticsLogger) (obj1)).setCustomDimension(context, 46, ((String) (obj)));
_L2:
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_161;
        }
        obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        break; /* Loop/switch isn't completed */
_L4:
        obj = "all_following_instances";
        continue; /* Loop/switch isn't completed */
_L5:
        obj = "all_instances";
        if (true) goto _L7; else goto _L6
_L6:
        ((AnalyticsLogger)obj).trackEvent(context, "save_event", "reminder");
        if (context == null) goto _L9; else goto _L8
_L8:
        obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        obj1 = (AnalyticsLogger)obj;
        i;
        JVM INSTR tableswitch 1 2: default 220
    //                   1 269
    //                   2 277;
           goto _L10 _L11 _L12
_L10:
        obj = "only_this_instance";
_L14:
        ((AnalyticsLogger) (obj1)).setCustomDimension(context, 46, ((String) (obj)));
_L9:
        obj1 = remindereditscreenmodel.logMetrics;
        obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        break; /* Loop/switch isn't completed */
_L11:
        obj = "all_following_instances";
        continue; /* Loop/switch isn't completed */
_L12:
        obj = "all_instances";
        if (true) goto _L14; else goto _L13
_L13:
        AnalyticsLogger analyticslogger1 = (AnalyticsLogger)obj;
        String s = ReminderEditLogMetrics.getAction(remindereditscreenmodel);
        ((ReminderEditLogMetrics) (obj1)).logSaveCustomDimensions(context, remindereditscreenmodel);
        analyticslogger1.trackEvent(context, "reminder", s);
        if (remindereditscreenmodel.isNew())
        {
            obj = com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType.CREATE_REMINDER;
        } else
        {
            obj = com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType.CHANGE_REMINDER;
        }
        analyticslogger1.logClearcutEvent(((com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType) (obj)), remindereditscreenmodel.getAccount().name);
        if (((ReminderEditLogMetrics) (obj1)).loadedTime != -1L)
        {
            ((ReminderEditLogMetrics) (obj1)).logSaveCustomDimensions(context, remindereditscreenmodel);
            analyticslogger1.trackTiming(context, "interaction", SystemClock.elapsedRealtime() - ((ReminderEditLogMetrics) (obj1)).loadedTime, "reminder", s);
        } else
        {
            LogUtils.e(ReminderEditLogMetrics.TAG, "Saving a not loaded task", new Object[0]);
        }
        obj = (ReminderViewScreenController)getViewScreenController(com/google/android/calendar/newapi/screen/reminder/ReminderViewScreenController);
        if (obj != null)
        {
            if (i == 0)
            {
                ((ReminderViewScreenModel)((ViewScreenController) (obj)).getModel()).setTask(task);
                ((ViewScreenController) (obj)).updateSegments();
                ((ViewScreenController) (obj)).updateCommandBar();
            } else
            {
                ((ViewScreenController) (obj)).closeViewScreen();
            }
        }
        (new Handler(Looper.getMainLooper())).post(new com.google.android.calendar.newapi.screen.HostedFragment..Lambda._cls0(this));
        return;
        LoggingUtils.logSaveFailure(getContext(), getModel(), i);
        return;
    }
}
