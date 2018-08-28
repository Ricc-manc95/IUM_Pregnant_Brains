// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.Optionals;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.gms.GmsFutures;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.api.habit.HabitClient;
import com.google.android.calendar.api.habit.HabitContractModifications;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.api.habit.HabitModifications;
import com.google.android.calendar.belong.BelongUtils;
import com.google.android.calendar.latency.LatencyLogger;
import com.google.android.calendar.latency.LatencyLoggerHolder;
import com.google.android.calendar.newapi.common.ApiUtils;
import com.google.android.calendar.newapi.common.Loader;
import com.google.android.calendar.newapi.common.loader.GrooveEditScreenLoader;
import com.google.android.calendar.newapi.logging.GrooveEditLogMetrics;
import com.google.android.calendar.newapi.model.CalendarList;
import com.google.android.calendar.newapi.model.GrooveEditScreenModel;
import com.google.android.calendar.newapi.model.edit.BasicEditScreenModel;
import com.google.android.calendar.newapi.model.edit.EditScreenModel;
import com.google.android.calendar.newapi.screen.groove.GrooveEditSegmentProvider;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.title.GrooveTitleEditSegmentController;
import com.google.android.gms.common.api.Status;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            EditScreenController, SegmentMap, SegmentViews, GrooveEditScreenListener, 
//            HostedFragment

public final class GrooveEditScreenController extends EditScreenController
{

    private EventClient eventClient;
    private HabitClient habitClient;

    public GrooveEditScreenController()
    {
        habitClient = CalendarApi.Habits;
        eventClient = CalendarApi.Events;
    }

    public static GrooveEditScreenController createGroove(GrooveEditScreenController grooveeditscreencontroller, HabitModifications habitmodifications, boolean flag)
    {
        Object obj = grooveeditscreencontroller.getArguments();
        if (obj == null)
        {
            obj = Absent.INSTANCE;
        } else
        {
            obj = new Present(obj);
        }
        obj = (Bundle)((Optional) (obj)).or(new Bundle());
        ((Bundle) (obj)).putBoolean("ARG_RETURN_TO_ACTIVITY", true);
        ((Bundle) (obj)).putParcelable("ARG_HABIT_MODIFICATION", habitmodifications);
        grooveeditscreencontroller.setArguments(((Bundle) (obj)));
        return grooveeditscreencontroller;
    }

    static final Boolean lambda$onSaveClicked$0$GrooveEditScreenController(com.google.android.calendar.api.habit.HabitClient.ReadResult readresult)
    {
        boolean flag;
        if (readresult.getStatus().zzaEP <= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return Boolean.valueOf(flag);
    }

    static final Boolean lambda$onSaveClicked$1$GrooveEditScreenController(com.google.android.calendar.api.habit.HabitClient.GenericResult genericresult)
    {
        boolean flag;
        if (genericresult.getStatus().zzaEP <= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return Boolean.valueOf(flag);
    }

    protected final Loader createLoader()
    {
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        return new GrooveEditScreenLoader(((android.content.Context) (obj)), (GrooveEditScreenModel)getModel());
    }

    protected final EditScreenModel createModel()
    {
        return new GrooveEditScreenModel();
    }

    protected final SegmentMap createSegments()
    {
        GrooveEditScreenModel grooveeditscreenmodel = (GrooveEditScreenModel)getModel();
        return SegmentMap.create(this, grooveeditscreenmodel, GrooveEditSegmentProvider.getSupportedSegments(grooveeditscreenmodel));
    }

    protected final int getDiscardChangesMessage()
    {
        return 0x7f130188;
    }

    protected final SegmentViews getOrderedSegments(SegmentMap segmentmap)
    {
        GrooveEditScreenModel grooveeditscreenmodel = (GrooveEditScreenModel)getModel();
        SegmentViews segmentviews = new SegmentViews();
        for (Iterator iterator = GrooveEditSegmentProvider.getSupportedSegments(grooveeditscreenmodel).iterator(); iterator.hasNext();)
        {
            Object obj = (Class)iterator.next();
            if (obj == com/google/android/calendar/newapi/segment/title/GrooveTitleEditSegmentController)
            {
                obj = com/google/android/calendar/newapi/segment/title/GrooveTitleEditSegmentController.getSimpleName();
                EditSegmentController editsegmentcontroller = (EditSegmentController)segmentmap.segmentControllers.get(obj);
                if (editsegmentcontroller == null)
                {
                    LogUtils.wtf(SegmentMap.LOG_TAG, "Tried to access unsupported segment '%s'.", new Object[] {
                        obj
                    });
                }
                if (editsegmentcontroller == null)
                {
                    obj = Absent.INSTANCE;
                } else
                {
                    obj = new Present(editsegmentcontroller);
                }
                Optionals.ifPresent(((Optional) (obj)).transform(SegmentMap..Lambda._cls0.$instance), new SegmentViews..Lambda._cls0(segmentviews));
            } else
            {
                obj = ((Class) (obj)).getSimpleName();
                EditSegmentController editsegmentcontroller1 = (EditSegmentController)segmentmap.segmentControllers.get(obj);
                if (editsegmentcontroller1 == null)
                {
                    LogUtils.wtf(SegmentMap.LOG_TAG, "Tried to access unsupported segment '%s'.", new Object[] {
                        obj
                    });
                }
                if (editsegmentcontroller1 == null)
                {
                    obj = Absent.INSTANCE;
                } else
                {
                    obj = new Present(editsegmentcontroller1);
                }
                Optionals.ifPresent(((Optional) (obj)).transform(SegmentMap..Lambda._cls0.$instance), new SegmentViews..Lambda._cls2(segmentviews));
            }
        }

        return segmentviews;
    }

    protected final String getPrimesLogTag()
    {
        return "GrooveEdit";
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (getArguments() != null && getArguments().containsKey("ARG_HABIT_MODIFICATION"))
        {
            ((GrooveEditScreenModel)getModel()).habitModifications = (HabitModifications)getArguments().getParcelable("ARG_HABIT_MODIFICATION");
        }
    }

    public final void onDiscard()
    {
        boolean flag1 = false;
        super.onDiscard();
        boolean flag = flag1;
        if (getArguments() != null)
        {
            flag = flag1;
            if (getArguments().getBoolean("ARG_RETURN_TO_ACTIVITY", false))
            {
                flag = true;
            }
        }
        if (flag)
        {
            FragmentActivity fragmentactivity;
            if (super.mHost == null)
            {
                fragmentactivity = null;
            } else
            {
                fragmentactivity = (FragmentActivity)super.mHost.mActivity;
            }
            if (!(fragmentactivity instanceof GrooveEditScreenListener))
            {
                throw new IllegalStateException("Activity has to implement GrooveEditScreenListener");
            }
            ((GrooveEditScreenListener)fragmentactivity).onEditCancelled();
        }
    }

    protected final void onLoadingCompleted(boolean flag)
    {
        super.onLoadingCompleted(flag);
        GrooveEditLogMetrics grooveeditlogmetrics = ((GrooveEditScreenModel)getModel()).logMetrics;
        if (grooveeditlogmetrics.screenLoadingFinishedTimestamp == -1L)
        {
            grooveeditlogmetrics.screenLoadingFinishedTimestamp = SystemClock.elapsedRealtime();
        }
    }

    public final void onSaveClicked()
    {
        Object obj = null;
        boolean flag;
        if (getArguments() != null && getArguments().getBoolean("ARG_RETURN_TO_ACTIVITY", false))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            if (!(obj instanceof GrooveEditScreenListener))
            {
                throw new IllegalStateException("Activity has to implement GrooveEditScreenListener");
            } else
            {
                ((GrooveEditScreenListener)obj).onEditFinished(((GrooveEditScreenModel)getModel()).habitModifications);
                dismiss();
                return;
            }
        }
        LatencyLoggerHolder.get().markAt(4);
        final boolean timeChanged = ((GrooveEditScreenModel)getModel()).hasStartTimeChanges();
        Object obj2 = new ArrayList();
        Object obj1;
        final boolean contractChanged;
        if (((GrooveEditScreenModel)getModel()).isNew())
        {
            obj = ((GrooveEditScreenModel)getModel()).habitModifications;
            class .Lambda._cls0
                implements Function
            {

                public static final Function $instance = new .Lambda._cls0();

                public final Object apply(Object obj3)
                {
                    return GrooveEditScreenController.lambda$onSaveClicked$0$GrooveEditScreenController((com.google.android.calendar.api.habit.HabitClient.ReadResult)obj3);
                }


            private .Lambda._cls0()
            {
            }
            }

            if (((HabitModifications) (obj)).getOriginal() == null)
            {
                LogUtils.wtf("GrooveEditScreenController", "Trying to unwrap a habit without the original.", new Object[0]);
            } else
            {
                obj1 = (HabitModifications)((HabitModifications) (obj)).getOriginal();
                ((HabitModifications) (obj1)).applyModifications(((HabitModifications) (obj)));
                obj = obj1;
            }
            obj = habitClient.create(((HabitModifications) (obj)));
            obj1 = .Lambda._cls0..instance;
            ((List) (obj2)).add(AbstractTransformFuture.create(GmsFutures.asFuture(((com.google.android.gms.common.api.PendingResult) (obj))), new com.google.android.apps.calendar.util.gms.GmsFutures..Lambda._cls0(((Function) (obj1))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE));
            ApiUtils.setDefaultCalendar(((BasicEditScreenModel) ((GrooveEditScreenModel)getModel())).calendarList.findEntry(((GrooveEditScreenModel)getModel()).habitModifications.getDescriptor().calendar.calendarId));
            contractChanged = false;
        } else
        {
            if (((GrooveEditScreenModel)getModel()).habitModifications != null)
            {
                contractChanged = ((GrooveEditScreenModel)getModel()).habitModifications.getContractModifications().isModified();
                obj1 = habitClient.update(((GrooveEditScreenModel)getModel()).habitModifications);
                class .Lambda._cls1
                    implements Function
                {

                    public static final Function $instance = new .Lambda._cls1();

                    public final Object apply(Object obj3)
                    {
                        return GrooveEditScreenController.lambda$onSaveClicked$1$GrooveEditScreenController((com.google.android.calendar.api.habit.HabitClient.GenericResult)obj3);
                    }


            private .Lambda._cls1()
            {
            }
                }

                Function function = .Lambda._cls1..instance;
                ((List) (obj2)).add(AbstractTransformFuture.create(GmsFutures.asFuture(((com.google.android.gms.common.api.PendingResult) (obj1))), new com.google.android.apps.calendar.util.gms.GmsFutures..Lambda._cls0(function), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE));
                ApiUtils.setDefaultCalendar(((BasicEditScreenModel) ((GrooveEditScreenModel)getModel())).eventModifications.getCalendarListEntry());
                obj1 = ((GrooveEditScreenModel)getModel()).habitModifications;
                if (((HabitModifications) (obj1)).getOriginal() != null)
                {
                    int i = ((HabitModifications) (obj1)).getOriginal().getFitIntegrationStatus();
                    int j = ((HabitModifications) (obj1)).getFitIntegrationStatus();
                    if (i != j && (i != 0 || j != 10))
                    {
                        boolean flag1 = ((HabitModifications) (obj1)).isFitIntegrationEnabled();
                        if (super.mHost != null)
                        {
                            obj = (FragmentActivity)super.mHost.mActivity;
                        }
                        BelongUtils.onIntegrationStatusChange(((Context) (obj)), flag1);
                    }
                }
            } else
            {
                contractChanged = false;
            }
            if (timeChanged)
            {
                ((List) (obj2)).add(eventClient.update(((BasicEditScreenModel) ((GrooveEditScreenModel)getModel())).eventModifications, 0, com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.UNDECIDED));
            }
        }
        obj = new com.google.common.util.concurrent.CollectionFuture.ListFuture(ImmutableList.copyOf(((Iterable) (obj2))), true);
        obj1 = new _cls1();
        obj2 = CalendarExecutor.MAIN;
        if (obj1 == null)
        {
            throw new NullPointerException();
        } else
        {
            ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj)), ((FutureCallback) (obj1))), ((java.util.concurrent.Executor) (obj2)));
            return;
        }
    }

    private class _cls1
        implements FutureCallback
    {

        private final GrooveEditScreenController this$0;
        private final boolean val$contractChanged;
        private final boolean val$timeChanged;

        public final void onFailure(Throwable throwable)
        {
            throwable = GrooveEditScreenController.this;
            boolean flag = timeChanged;
            flag = contractChanged;
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
                    flag3 = timeChanged;
                    flag4 = contractChanged;
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
                        obj2 = com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType.CREATE_GOAL;
                    } else
                    {
                        obj2 = com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType.CHANGE_GOAL;
                    }
                    analyticslogger.logClearcutEvent(((com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType) (obj2)), grooveeditscreenmodel.getAccount().name);
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
                                    grooveviewscreenmodel.setHabit(((Habit) (obj1)));
                                    grooveviewscreenmodel.setEvent(((com.google.android.calendar.api.event.Event) (obj2)));
                                    ((ViewScreenController) (obj)).updateSegments();
                                }
                            }
                        }
                    }
                }
                (new Handler(Looper.getMainLooper())).post(new HostedFragment..Lambda._cls0(grooveeditscreencontroller));
            }
        }

        _cls1()
        {
            this$0 = GrooveEditScreenController.this;
            timeChanged = flag;
            contractChanged = flag1;
            super();
        }
    }

}
