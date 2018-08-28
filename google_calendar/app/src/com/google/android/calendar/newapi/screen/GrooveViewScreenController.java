// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.gms.GmsFutures;
import com.google.android.calendar.alerts.HabitsIntentServiceHelper;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.api.habit.HabitClient;
import com.google.android.calendar.api.habit.HabitContractModifications;
import com.google.android.calendar.api.habit.HabitFactory;
import com.google.android.calendar.api.habit.HabitModifications;
import com.google.android.calendar.belong.BelongUtils;
import com.google.android.calendar.newapi.commandbar.BottomBarController;
import com.google.android.calendar.newapi.commandbar.GrooveCommandBarController;
import com.google.android.calendar.newapi.commandbar.MoreOptionsSheetController;
import com.google.android.calendar.newapi.common.GrooveDeleteDialog;
import com.google.android.calendar.newapi.common.Loader;
import com.google.android.calendar.newapi.logging.LoggingUtils;
import com.google.android.calendar.newapi.model.BasicViewScreenModel;
import com.google.android.calendar.newapi.model.GrooveViewScreenModel;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.newapi.overflow.GrooveOverflowMenuController;
import com.google.android.calendar.newapi.overflow.OverflowMenuController;
import com.google.android.calendar.newapi.segment.belong.BelongIntegrationViewSegment;
import com.google.android.calendar.newapi.segment.calendar.CalendarViewSegment;
import com.google.android.calendar.newapi.segment.contract.ContractViewSegment;
import com.google.android.calendar.newapi.segment.notification.GrooveNotificationViewSegment;
import com.google.android.calendar.newapi.segment.time.TimeViewSegment;
import com.google.android.calendar.newapi.segment.title.TitleViewSegment;
import com.google.android.calendar.newapi.segment.tracking.GrooveTrackingData;
import com.google.android.calendar.newapi.segment.tracking.TrackingIntervalData;
import com.google.android.calendar.newapi.segment.tracking.TrackingViewSegment;
import com.google.android.calendar.newapi.segment.visibility.VisibilityAvailabilityViewSegment;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineGroove;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.gms.common.api.Status;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            AbstractEventViewScreenController, GrooveViewScreenListener, ViewScreenController, GrooveViewScreenLoader, 
//            ViewScreen, ViewScreenOpenCloseHelper, GrooveEditScreenController, HostDialog

public final class GrooveViewScreenController extends AbstractEventViewScreenController
    implements com.google.android.calendar.newapi.common.GrooveDeleteDialog.Callback, com.google.android.calendar.newapi.overflow.GrooveOverflowMenuController.Callback, GrooveViewScreenListener
{

    private EventClient eventClient;
    private HabitClient habitClient;
    private final Handler handler = new Handler();
    public Runnable markAsDoneRunnable;

    public GrooveViewScreenController()
    {
        habitClient = CalendarApi.Habits;
        eventClient = CalendarApi.Events;
    }

    static final Boolean lambda$onDeleteConfirmed$1$GrooveViewScreenController$5166KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T16URRCCLGMSEO_0()
    {
        return Boolean.valueOf(true);
    }

    static final Boolean lambda$onDeleteConfirmed$2$GrooveViewScreenController(com.google.android.calendar.api.habit.HabitClient.GenericResult genericresult)
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

    protected final void createBodySegments(ViewScreenModel viewscreenmodel, List list)
    {
        Object obj = null;
        GrooveViewScreenModel grooveviewscreenmodel = (GrooveViewScreenModel)viewscreenmodel;
        boolean flag = grooveviewscreenmodel.showSimplifiedScreen();
        if (super.mHost == null)
        {
            viewscreenmodel = null;
        } else
        {
            viewscreenmodel = (FragmentActivity)super.mHost.mActivity;
        }
        list.add(new TimeViewSegment(viewscreenmodel, grooveviewscreenmodel));
        if (!flag)
        {
            if (super.mHost == null)
            {
                viewscreenmodel = null;
            } else
            {
                viewscreenmodel = (FragmentActivity)super.mHost.mActivity;
            }
            list.add(new ContractViewSegment(viewscreenmodel, grooveviewscreenmodel));
            if (super.mHost == null)
            {
                viewscreenmodel = null;
            } else
            {
                viewscreenmodel = (FragmentActivity)super.mHost.mActivity;
            }
            list.add(new GrooveNotificationViewSegment(viewscreenmodel, grooveviewscreenmodel));
        }
        if (super.mHost == null)
        {
            viewscreenmodel = null;
        } else
        {
            viewscreenmodel = (FragmentActivity)super.mHost.mActivity;
        }
        list.add(new VisibilityAvailabilityViewSegment(viewscreenmodel, grooveviewscreenmodel));
        if (super.mHost == null)
        {
            viewscreenmodel = null;
        } else
        {
            viewscreenmodel = (FragmentActivity)super.mHost.mActivity;
        }
        list.add(new CalendarViewSegment(viewscreenmodel, grooveviewscreenmodel));
        if (!flag)
        {
            if (super.mHost == null)
            {
                viewscreenmodel = null;
            } else
            {
                viewscreenmodel = (FragmentActivity)super.mHost.mActivity;
            }
            list.add(new BelongIntegrationViewSegment(viewscreenmodel, grooveviewscreenmodel));
        }
        if (!flag)
        {
            if (super.mHost == null)
            {
                viewscreenmodel = obj;
            } else
            {
                viewscreenmodel = (FragmentActivity)super.mHost.mActivity;
            }
            list.add(new TrackingViewSegment(viewscreenmodel, grooveviewscreenmodel, this));
        }
    }

    protected final BottomBarController createCommandBarController(MoreOptionsSheetController moreoptionssheetcontroller)
    {
        return new GrooveCommandBarController(this);
    }

    protected final View createHeaderSegment(ViewScreenModel viewscreenmodel)
    {
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        return new TitleViewSegment(((Context) (obj)), viewscreenmodel);
    }

    public final Loader createLoader(boolean flag)
    {
        Context context = super.mView.getContext();
        TimelineGroove timelinegroove = (TimelineGroove)super.model.timelineItem;
        GrooveViewScreenModel grooveviewscreenmodel;
        if (flag)
        {
            grooveviewscreenmodel = (GrooveViewScreenModel)getModel();
        } else
        {
            grooveviewscreenmodel = null;
        }
        return new GrooveViewScreenLoader(context, timelinegroove, grooveviewscreenmodel);
    }

    public final ViewScreenModel createModel(TimelineItem timelineitem)
    {
        return new GrooveViewScreenModel((TimelineGroove)timelineitem);
    }

    protected final MoreOptionsSheetController createMoreOptionsSheetController()
    {
        return null;
    }

    protected final OverflowMenuController createOverflowMenuController()
    {
        return new GrooveOverflowMenuController(this);
    }

    protected final ViewScreen createViewScreen()
    {
        return new ViewScreen(super.mView.getContext());
    }

    protected final String getPrimesLogTag()
    {
        return "GrooveView";
    }

    public final String getTitle()
    {
        return requireContext().getResources().getString(0x7f1302c7);
    }

    public final void onContentProviderUpdated(Uri uri)
    {
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        bundle = (Bundle)getArguments().getParcelable("view_screen_extras");
        boolean flag;
        if (bundle == null)
        {
            flag = false;
        } else
        {
            flag = bundle.getBoolean("show_habits_post_creation_promo", false);
        }
        if (flag)
        {
            super.shouldPulseEditButton = true;
        }
    }

    public final void onDeferClicked()
    {
        Object obj1 = null;
        Object obj2 = (TimelineGroove)super.model.timelineItem;
        RemoteFeatureConfig.UNIFIED_SYNC_AND_STORE.enabled();
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj2 = HabitsIntentServiceHelper.createDeferIntent(((Context) (obj)), ((TimelineGroove) (obj2)).descriptor, ((TimelineEvent) (obj2)).eventKey, "info_bubble");
        if (super.mHost == null)
        {
            obj = obj1;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        ((FragmentActivity) (obj)).startService(((android.content.Intent) (obj2)));
        closeViewScreen();
    }

    public final void onDeleteClicked()
    {
        Object obj = ((BasicViewScreenModel) ((GrooveViewScreenModel)getModel())).event.getSummary();
        boolean flag = ((GrooveViewScreenModel)getModel()).showSimplifiedScreen();
        Bundle bundle = new Bundle();
        bundle.putString("ARG_GROOVE_TITLE", ((String) (obj)));
        bundle.putBoolean("ARG_GROOVE_SIMPLIFIED_OPTIONS", flag);
        obj = new GrooveDeleteDialog();
        ((Fragment) (obj)).setArguments(bundle);
        ((Fragment) (obj)).setTargetFragment(this, 0);
        super.mFragmentManager.beginTransaction().add(((Fragment) (obj)), "GrooveDeleteDialog").commitAllowingStateLoss();
        LoggingUtils.logDeleteClicked(getContext(), getModel());
    }

    public final void onDeleteConfirmed(boolean flag)
    {
        String s = null;
        Object obj1 = (TimelineGroove)super.model.timelineItem;
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj1 = HabitsIntentServiceHelper.createDismissIntent(((Context) (obj)), ((TimelineGroove) (obj1)).descriptor, ((TimelineEvent) (obj1)).eventKey);
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        ((FragmentActivity) (obj)).startService(((android.content.Intent) (obj1)));
        class .Lambda._cls3
            implements Runnable
        {

            private final GrooveViewScreenController arg$1;
            private final ListenableFuture arg$2;

            public final void run()
            {
                Context context1;
label0:
                {
                    GrooveViewScreenController grooveviewscreencontroller = arg$1;
                    ListenableFuture listenablefuture1 = arg$2;
                    context1 = grooveviewscreencontroller.getContext();
                    if (context1 != null)
                    {
                        if (!((Boolean)Futures.getUnchecked(listenablefuture1)).booleanValue())
                        {
                            break label0;
                        }
                        boolean flag1;
                        if (((Fragment) (grooveviewscreencontroller)).mHost != null && ((Fragment) (grooveviewscreencontroller)).mAdded)
                        {
                            flag1 = true;
                        } else
                        {
                            flag1 = false;
                        }
                        if (flag1)
                        {
                            ViewScreen viewscreen = ((ViewScreenController) (grooveviewscreencontroller)).viewScreen;
                            viewscreen.announceForAccessibility(viewscreen.getContext().getText(0x7f1302b0));
                        }
                        grooveviewscreencontroller.closeViewScreen();
                    }
                    return;
                }
                Toast.makeText(context1, 0x7f1301ab, 0).show();
            }

            .Lambda._cls3(ListenableFuture listenablefuture)
            {
                arg$1 = GrooveViewScreenController.this;
                arg$2 = listenablefuture;
            }
        }

        if (flag)
        {
            class .Lambda._cls1
                implements Function
            {

                public static final Function $instance = new .Lambda._cls1();

                public final Object apply(Object obj3)
                {
                    return GrooveViewScreenController.lambda$onDeleteConfirmed$1$GrooveViewScreenController$5166KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T16URRCCLGMSEO_0();
                }


            private .Lambda._cls1()
            {
            }
            }

            obj = AbstractTransformFuture.create(eventClient.delete(((BasicViewScreenModel) ((GrooveViewScreenModel)getModel())).event.getDescriptor(), 0, com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.UNDECIDED), .Lambda._cls1..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            Context context = getContext();
            s = ((GrooveViewScreenModel)getModel()).getCategory();
            if (context != null)
            {
                AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
                if (analyticslogger == null)
                {
                    throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                }
                ((AnalyticsLogger)analyticslogger).trackEvent(context, s, "deleted_instance");
            }
        } else
        {
            long l = ((BasicViewScreenModel) ((GrooveViewScreenModel)getModel())).event.getStartMillis();
            obj = CalendarApi.HabitFactory.modifyHabit(((GrooveViewScreenModel)getModel()).habit);
            ((HabitModifications) (obj)).getContractModifications().setUntilMillisUtc(l);
            obj = habitClient.update(((HabitModifications) (obj)));
            class .Lambda._cls2
                implements Function
            {

                public static final Function $instance = new .Lambda._cls2();

                public final Object apply(Object obj3)
                {
                    return GrooveViewScreenController.lambda$onDeleteConfirmed$2$GrooveViewScreenController((com.google.android.calendar.api.habit.HabitClient.GenericResult)obj3);
                }


            private .Lambda._cls2()
            {
            }
            }

            Object obj2 = .Lambda._cls2..instance;
            ListenableFuture listenablefuture = AbstractTransformFuture.create(GmsFutures.asFuture(((com.google.android.gms.common.api.PendingResult) (obj))), new com.google.android.apps.calendar.util.gms.GmsFutures..Lambda._cls0(((Function) (obj2))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            obj = getContext();
            obj2 = ((GrooveViewScreenModel)getModel()).getCategory();
            if (obj != null)
            {
                AnalyticsLogger analyticslogger1 = AnalyticsLoggerHolder.instance;
                if (analyticslogger1 == null)
                {
                    throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                }
                ((AnalyticsLogger)analyticslogger1).trackEvent(((Context) (obj)), ((String) (obj2)), "deleted_all_following");
            }
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            if (super.mHost == null)
            {
                obj2 = null;
            } else
            {
                obj2 = (FragmentActivity)super.mHost.mActivity;
            }
            ((FragmentActivity) (obj)).startService(HabitsIntentServiceHelper.createRefreshNotificationsIntent(((Context) (obj2)), ((GrooveViewScreenModel)getModel()).habit.getDescriptor()));
            if (((GrooveViewScreenModel)getModel()).habit.isFitIntegrationEnabled())
            {
                if (super.mHost == null)
                {
                    obj = s;
                } else
                {
                    obj = (FragmentActivity)super.mHost.mActivity;
                }
                BelongUtils.onIntegrationStatusChange(((Context) (obj)), false);
            }
            obj = listenablefuture;
        }
        ((ListenableFuture) (obj)).addListener(new .Lambda._cls3(((ListenableFuture) (obj))), CalendarExecutor.MAIN);
    }

    public final void onMarkAsDoneClicked()
    {
        TimelineGroove timelinegroove = (TimelineGroove)super.model.timelineItem;
        if (setDelayedAction(timelinegroove, 0))
        {
            closeViewScreen();
            return;
        }
        Object obj;
        Object obj2;
        com.google.android.calendar.api.event.EventKey eventkey;
        boolean flag;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj2 = timelinegroove.descriptor;
        eventkey = ((TimelineEvent) (timelinegroove)).eventKey;
        if (!timelinegroove.completed)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj = HabitsIntentServiceHelper.createCompleteIntent(((Context) (obj)), ((com.google.android.calendar.api.habit.HabitDescriptor) (obj2)), eventkey, flag, "info_bubble");
        super.animationHelper.animationData = null;
        if (!timelinegroove.completed)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        timelinegroove.completed = flag;
        obj2 = ((GrooveViewScreenModel)getModel()).trackingData;
        flag = timelinegroove.completed;
        if (!((GrooveTrackingData) (obj2)).intervalDataList.isEmpty())
        {
            Object obj1 = (TrackingIntervalData)((GrooveTrackingData) (obj2)).intervalDataList.get(0);
            int j = ((TrackingIntervalData) (obj1)).completedInstances;
            class .Lambda._cls0
                implements Runnable
            {

                private final GrooveViewScreenController arg$1;
                private final Intent arg$2;

                public final void run()
                {
                    GrooveViewScreenController grooveviewscreencontroller = arg$1;
                    Intent intent = arg$2;
                    grooveviewscreencontroller.markAsDoneRunnable = null;
                    FragmentActivity fragmentactivity;
                    if (((Fragment) (grooveviewscreencontroller)).mHost == null)
                    {
                        fragmentactivity = null;
                    } else
                    {
                        fragmentactivity = (FragmentActivity)((Fragment) (grooveviewscreencontroller)).mHost.mActivity;
                    }
                    if (fragmentactivity != null)
                    {
                        if (((Fragment) (grooveviewscreencontroller)).mHost == null)
                        {
                            fragmentactivity = null;
                        } else
                        {
                            fragmentactivity = (FragmentActivity)((Fragment) (grooveviewscreencontroller)).mHost.mActivity;
                        }
                        fragmentactivity.startService(intent);
                    }
                }

            .Lambda._cls0(Intent intent)
            {
                arg$1 = GrooveViewScreenController.this;
                arg$2 = intent;
            }
            }

            int i;
            if (flag)
            {
                i = 1;
            } else
            {
                i = -1;
            }
            obj1.completedInstances = i + j;
            obj2.shouldAnimateUpdate = true;
        }
        updateSegments();
        if (super.commandBarController != null)
        {
            obj1 = super.commandBarController;
            obj2 = getModel();
            obj1.model = obj2;
            ((BottomBarController) (obj1)).onModelChanged(obj2);
            super.viewScreen.adjustExtraCommandBarPadding();
        }
        if (markAsDoneRunnable != null)
        {
            handler.removeCallbacks(markAsDoneRunnable);
        }
        markAsDoneRunnable = new .Lambda._cls0(((Intent) (obj)));
        handler.postDelayed(markAsDoneRunnable, 1000L);
    }

    public final void onStop()
    {
        if (markAsDoneRunnable != null)
        {
            handler.removeCallbacks(markAsDoneRunnable);
            markAsDoneRunnable.run();
        }
        super.onStop();
    }

    public final boolean setDelayedAction(TimelineItem timelineitem, int i)
    {
        if (i == 0)
        {
            return false;
        } else
        {
            return super.setDelayedAction(timelineitem, i);
        }
    }

    protected final void showEditScreen()
    {
        EditScreenController editscreencontroller = GrooveEditScreenController.fromViewScreen(new GrooveEditScreenController(), (GrooveViewScreenModel)getModel());
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        HostDialog.showWithChildFragment(((android.app.Activity) (obj)), super.mFragmentManager, editscreencontroller, "EditScreenController");
    }
}
