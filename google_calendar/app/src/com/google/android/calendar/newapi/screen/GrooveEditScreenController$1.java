// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.accounts.Account;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.alerts.HabitsIntentServiceHelper;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.habit.HabitModifications;
import com.google.android.calendar.groove.GrooveUtils;
import com.google.android.calendar.newapi.common.Loader;
import com.google.android.calendar.newapi.logging.GrooveEditLogMetrics;
import com.google.android.calendar.newapi.logging.LoggingUtils;
import com.google.android.calendar.newapi.model.BasicViewScreenModel;
import com.google.android.calendar.newapi.model.GrooveEditScreenModel;
import com.google.android.calendar.newapi.model.GrooveViewScreenModel;
import com.google.android.calendar.newapi.model.edit.BasicEditScreenModel;
import com.google.android.calendar.newapi.model.edit.EditScreenModel;
import com.google.android.calendar.utils.snackbar.SnackbarFeedbackUtils;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            EditScreenController, GrooveViewScreenController, ViewScreenController, ViewScreenOpenCloseHelper, 
//            GrooveEditScreenController

final class val.contractChanged
    implements FutureCallback
{

    private final GrooveEditScreenController this$0;
    private final boolean val$contractChanged;
    private final boolean val$timeChanged;

    public final void onFailure(Throwable throwable)
    {
        throwable = GrooveEditScreenController.this;
        boolean flag = val$timeChanged;
        flag = val$contractChanged;
        Context context = throwable.getContext();
        if (context != null)
        {
            Toast.makeText(context, 0x7f1301ab, 0).show();
            LoggingUtils.logSaveFailure(context, throwable.getModel(), 0);
        }
    }

    public final void onSuccess(Object obj)
    {
label0:
        {
            Object obj1;
            GrooveEditScreenController grooveeditscreencontroller;
            GrooveEditScreenModel grooveeditscreenmodel;
            boolean flag3;
            boolean flag4;
label1:
            {
                grooveeditscreencontroller = GrooveEditScreenController.this;
                flag3 = val$timeChanged;
                flag4 = val$contractChanged;
                obj = grooveeditscreencontroller.getContext();
                if (obj == null)
                {
                    break label0;
                }
                grooveeditscreencontroller.notifyEventSaved();
                if (!((GrooveEditScreenModel)grooveeditscreencontroller.getModel()).isNew())
                {
                    SnackbarFeedbackUtils.showSnackbarFeedback(((Context) (obj)), GrooveUtils.getGrooveFeedbackMessage(((Context) (obj)), GrooveUtils.hasContractChanges(((GrooveEditScreenModel)grooveeditscreencontroller.getModel()).habitModifications), flag3), false, null, 0);
                }
                if (((GrooveEditScreenModel)grooveeditscreencontroller.getModel()).habitModifications == null)
                {
                    break label1;
                }
                if (!flag3)
                {
                    obj1 = (GrooveEditScreenModel)grooveeditscreencontroller.getModel();
                    boolean flag1;
                    if (((GrooveEditScreenModel) (obj1)).habitModifications == null)
                    {
                        flag1 = false;
                    } else
                    {
                        flag1 = ((GrooveEditScreenModel) (obj1)).habitModifications.isRemindersModified();
                    }
                    if (!flag1)
                    {
                        obj1 = (GrooveEditScreenModel)grooveeditscreencontroller.getModel();
                        if (((GrooveEditScreenModel) (obj1)).habitModifications == null)
                        {
                            flag1 = false;
                        } else
                        {
                            flag1 = ((GrooveEditScreenModel) (obj1)).habitModifications.isFitIntegrationStatusModified();
                        }
                        if (!flag1)
                        {
                            break label1;
                        }
                    }
                }
                ((Context) (obj)).startService(HabitsIntentServiceHelper.createRefreshNotificationsIntent(((Context) (obj)), ((GrooveEditScreenModel)grooveeditscreencontroller.getModel()).habitModifications.getDescriptor()));
            }
            if (((Fragment) (grooveeditscreencontroller)).mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)((Fragment) (grooveeditscreencontroller)).mHost.mActivity;
            }
            grooveeditscreenmodel = (GrooveEditScreenModel)grooveeditscreencontroller.getModel();
            Object obj2;
            if (obj != null)
            {
                GrooveEditLogMetrics grooveeditlogmetrics = grooveeditscreenmodel.logMetrics;
                if (grooveeditscreenmodel.hasStartTimeChanges())
                {
                    grooveeditlogmetrics.timeModified = true;
                }
                if (grooveeditscreenmodel.habitModifications != null)
                {
                    if (grooveeditscreenmodel.habitModifications.isRemindersModified())
                    {
                        grooveeditlogmetrics.notificationModified = true;
                    }
                    grooveeditlogmetrics.titleEmpty = ((BasicEditScreenModel) (grooveeditscreenmodel)).eventModifications.getSummary().isEmpty();
                    if (grooveeditscreenmodel.habitModifications.isSummaryModified())
                    {
                        grooveeditlogmetrics.titleModified = true;
                    }
                }
                if (obj != null)
                {
                    obj1 = AnalyticsLoggerHolder.instance;
                    if (obj1 == null)
                    {
                        throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                    }
                    ((AnalyticsLogger)obj1).trackEvent(((Context) (obj)), "save_event", "groove");
                }
                obj1 = AnalyticsLoggerHolder.instance;
                if (obj1 == null)
                {
                    throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                }
                AnalyticsLogger analyticslogger = (AnalyticsLogger)obj1;
                grooveeditlogmetrics.fillDimensions(((Context) (obj)), grooveeditscreenmodel);
                boolean flag2;
                if (grooveeditscreenmodel.isNew())
                {
                    obj1 = "create";
                } else
                {
                    obj1 = "update";
                }
                analyticslogger.trackEvent(((Context) (obj)), "event", ((String) (obj1)));
                if (grooveeditscreenmodel.isNew())
                {
                    obj2 = com.google.android.calendar.oidCalendarExtensionProto.ActionType.CREATE_GOAL;
                } else
                {
                    obj2 = com.google.android.calendar.oidCalendarExtensionProto.ActionType.CHANGE_GOAL;
                }
                analyticslogger.logClearcutEvent(((com.google.android.calendar.oidCalendarExtensionProto.ActionType) (obj2)), grooveeditscreenmodel.getAccount().name);
                if (LogUtils.maxEnabledLogLevel > 3)
                {
                    flag2 = false;
                } else
                if (Log.isLoggable("EditAnalyticsLogger", 3))
                {
                    flag2 = true;
                } else
                {
                    flag2 = Log.isLoggable("EditAnalyticsLogger", 3);
                }
                if (flag2)
                {
                    LogUtils.d("EditAnalyticsLogger", "Logging event save: %s - %s", new Object[] {
                        "event", obj1
                    });
                }
                if (grooveeditlogmetrics.screenLoadingFinishedTimestamp != -1L)
                {
                    grooveeditlogmetrics.fillDimensions(((Context) (obj)), grooveeditscreenmodel);
                    long l = SystemClock.elapsedRealtime() - grooveeditlogmetrics.screenLoadingFinishedTimestamp;
                    LogUtils.d("EditAnalyticsLogger", "Logging event edit timing: %s/%s = %d", new Object[] {
                        "event", obj1, Long.valueOf(l)
                    });
                    analyticslogger.trackTiming(((Context) (obj)), "interaction", l, "event", ((String) (obj1)));
                } else
                {
                    LogUtils.e("EditAnalyticsLogger", "Saving a not loaded event", new Object[0]);
                }
            }
            if (((Fragment) (grooveeditscreencontroller)).mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)((Fragment) (grooveeditscreencontroller)).mHost.mActivity;
            }
            if (obj != null)
            {
                obj = ((FragmentActivity) (obj)).mFragments.mHost.mFragmentManager;
                if (!((FragmentManager) (obj)).isDestroyed())
                {
                    obj = ((FragmentManager) (obj)).findFragmentByTag("ViewScreenController");
                    if (obj instanceof GrooveViewScreenController)
                    {
                        obj = (GrooveViewScreenController)obj;
                        obj1 = ((GrooveEditScreenModel)grooveeditscreencontroller.getModel()).habitModifications;
                        obj2 = ((BasicEditScreenModel) ((GrooveEditScreenModel)grooveeditscreencontroller.getModel())).eventModifications;
                        boolean flag;
                        if (flag3 || flag4)
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (((ViewScreenController) (obj)).loader.isFinishedSuccessfully() && ((ViewScreenController) (obj)).segmentsCreated)
                        {
                            if (flag)
                            {
                                ((ViewScreenController) (obj)).animationHelper.animationData = null;
                                ((ViewScreenController) (obj)).closeViewScreen();
                            } else
                            {
                                GrooveViewScreenModel grooveviewscreenmodel = (GrooveViewScreenModel)((ViewScreenController) (obj)).getModel();
                                grooveviewscreenmodel.setHabit(((com.google.android.calendar.api.habit.Habit) (obj1)));
                                grooveviewscreenmodel.setEvent(((com.google.android.calendar.api.event.Event) (obj2)));
                                ((ViewScreenController) (obj)).updateSegments();
                            }
                        }
                    }
                }
            }
            (new Handler(Looper.getMainLooper())).post(new t>(grooveeditscreencontroller));
        }
    }

    ensionProto.ActionType()
    {
        this$0 = final_grooveeditscreencontroller;
        val$timeChanged = flag;
        val$contractChanged = Z.this;
        super();
    }
}
