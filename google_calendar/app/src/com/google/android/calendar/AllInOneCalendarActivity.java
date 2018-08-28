// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.backup.BackupManager;
import android.arch.lifecycle.ViewModelStoreOwner;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ShortcutManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.Point;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Trace;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManagerImpl;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.util.SparseBooleanArray;
import android.view.Display;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.calendar.event.LaunchInfoActivity;
import com.android.calendarcommon2.LogUtils;
import com.android.volley.RequestQueue;
import com.google.android.apps.calendar.config.experiments.Experiment;
import com.google.android.apps.calendar.config.experiments.ExperimentConfiguration;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.flair.FlairAllocatorFactory;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementAttacher;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementHolder;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollector;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollectorHolder;
import com.google.android.apps.calendar.primes.exceptionhandler.CalendarUncaughtExceptionHandler;
import com.google.android.apps.calendar.timebox.EventsApiImpl;
import com.google.android.apps.calendar.timebox.TimeBoxUtil;
import com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment;
import com.google.android.apps.calendar.timeline.alternate.minimonth.api.MiniMonthController;
import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.android.apps.calendar.util.function.BiFunction;
import com.google.android.apps.calendar.util.string.StringUtils;
import com.google.android.calendar.alternatecalendar.AlternateCalendarUtils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarListClient;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.calendarlist.DrawerFragment;
import com.google.android.calendar.calendarlist.DrawerListAdapter;
import com.google.android.calendar.common.view.overlay.OverlayFragment;
import com.google.android.calendar.event.DelayedActionDescription;
import com.google.android.calendar.event.DelayedActionPerformer;
import com.google.android.calendar.event.EditHelper;
import com.google.android.calendar.event.OnInfoChangedListener;
import com.google.android.calendar.event.image.BitmapCacheHolder;
import com.google.android.calendar.groove.CreateGrooveActivity;
import com.google.android.calendar.groove.TimeZoneUpdateDialogLauncher;
import com.google.android.calendar.growthkit.GrowthKitCallback;
import com.google.android.calendar.hats.CalendarHatsService;
import com.google.android.calendar.latency.LatencyLogger;
import com.google.android.calendar.latency.LatencyLoggerHolder;
import com.google.android.calendar.launch.LaunchIntentConstants;
import com.google.android.calendar.launch.oobe.DemoUserUtil;
import com.google.android.calendar.launch.oobe.WhatsNewFactory;
import com.google.android.calendar.material.Material;
import com.google.android.calendar.minimonth.MiniMonthInteractionControllerImpl;
import com.google.android.calendar.newapi.screen.EventEditScreenController;
import com.google.android.calendar.newapi.screen.HostDialog;
import com.google.android.calendar.newapi.screen.reminder.ReminderEditScreenController;
import com.google.android.calendar.newevent.CreateNewEventView;
import com.google.android.calendar.newevent.NewEventTimeChangedListenerHolder;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.TimeUtils;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timebox.adapter.TimeBoxToTimelineAdapter;
import com.google.android.calendar.timely.BackgroundImageView;
import com.google.android.calendar.timely.BackgroundImagesFrame;
import com.google.android.calendar.timely.BaseCalendarFragment;
import com.google.android.calendar.timely.ConfidentialityDialog;
import com.google.android.calendar.timely.DataFactory;
import com.google.android.calendar.timely.DayPopUpFragment;
import com.google.android.calendar.timely.DualTimelineGridFragment;
import com.google.android.calendar.timely.EventRangedQueryHandler;
import com.google.android.calendar.timely.GoogleFeedbackUtils;
import com.google.android.calendar.timely.MonthData;
import com.google.android.calendar.timely.MonthFragment;
import com.google.android.calendar.timely.OnTimelineGestureListener;
import com.google.android.calendar.timely.OnTimelineModeChangedListener;
import com.google.android.calendar.timely.SyncOffNotification;
import com.google.android.calendar.timely.SyncOffNotificationsManager;
import com.google.android.calendar.timely.TaskBundleFragment;
import com.google.android.calendar.timely.ThreeDayViewFragment;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineExternalEvent;
import com.google.android.calendar.timely.TimelineGroove;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemUtil;
import com.google.android.calendar.timely.TimelineRecyclerView;
import com.google.android.calendar.timely.TimelineTask;
import com.google.android.calendar.timely.TimelineTaskBundle;
import com.google.android.calendar.timely.animations.EventInfoAnimationData;
import com.google.android.calendar.timely.callbacks.OnLaunchDetailsHandler;
import com.google.android.calendar.timely.gridviews.WeekFragment;
import com.google.android.calendar.timely.settings.PreferencesUtils;
import com.google.android.calendar.timely.settings.common.OnPropertyChangedListener;
import com.google.android.calendar.timely.settings.common.PreferencesConstants;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import com.google.android.calendar.utils.AccessibilityUtils;
import com.google.android.calendar.utils.ActivitySingletonCache;
import com.google.android.calendar.utils.SystemWindowInsetApplier;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.android.calendar.utils.animation.QuantumInterpolators;
import com.google.android.calendar.utils.notification.NotificationChannels;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;
import com.google.android.calendar.utils.rtl.RtlUtils;
import com.google.android.calendar.viewedit.callbacks.OnLaunchEdit;
import com.google.android.calendar.volley.VolleyQueueHolder;
import com.google.android.libraries.hats20.HatsController;
import com.google.android.libraries.hats20.HatsShowRequest;
import com.google.android.libraries.hats20.inject.HatsModule;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKit;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKitComponent;
import com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacksManager;
import com.google.android.syncadapters.calendar.PendingSyncTracker;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.collect.Cut;
import com.google.common.collect.Range;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;
import dagger.internal.Preconditions;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar:
//            EventFragmentHostActivity, FragmentDialogStarter, DateTimeFormatHelper, AlternateTimelineUtils, 
//            CalendarController, AlternateTimelineHolderManager, MiniMonthInteractionController, LaunchScreenManager, 
//            Utils, CreateFabFragment, NewViewScreenFactory, SpeedDialLayout, 
//            CalendarActivityPropertiesManager, UpgradeReceiver, RefreshUiManager, FullScreenManager, 
//            AnalyticsUtils, SearchActivity

public class AllInOneCalendarActivity extends EventFragmentHostActivity
    implements android.content.SharedPreferences.OnSharedPreferenceChangeListener, CalendarController.Command.Handler, CreateFabFragment.CreateFabInterface, FragmentDialogStarter, SpeedDialLayout.SpeedDialActivity, com.google.android.calendar.calendarlist.DrawerFragment.OnDrawerItemClickedListener, com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayListener, DelayedActionPerformer, OnInfoChangedListener, com.google.android.calendar.timely.MonthFragment.OnLaunchDayDetailsHandler, OnTimelineGestureListener, OnLaunchDetailsHandler, OnPropertyChangedListener, OnLaunchEdit, HasSupportFragmentInjector
{
    public final class MainFabStack extends CreateFabFragment.CreateFabStack
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
            if (super.stack.isEmpty())
            {
                if (speedDialLayout == null)
                {
                    AllInOneCalendarActivity allinonecalendaractivity = AllInOneCalendarActivity.this;
                    class .Lambda._cls0
                        implements SpeedDialLayout.EndSpeedDialFadeOut
                    {

                        private final MainFabStack arg$1;

                        public final void onEndSpeedDialFadeOut()
                        {
                            arg$1.removeSpeedDial();
                        }

                .Lambda._cls0()
                {
                    arg$1 = MainFabStack.this;
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
                    fullscreenmanager.layoutParams.token = allinonecalendaractivity.getWindow().getDecorView().getRootView().getWindowToken();
                    fullscreenmanager.windowManager.addView(speeddiallayout, fullscreenmanager.layoutParams);
                }
                return speedDialLayout;
            } else
            {
                return super.getSpeedDial();
            }
        }

        public final boolean isSpeedDialExpanded()
        {
            SpeedDialLayout speeddiallayout;
            if (super.stack.isEmpty())
            {
                speeddiallayout = speedDialLayout;
            } else
            {
                speeddiallayout = super.getSpeedDial();
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
                    com.google.android.calendar.utils.SystemWindowInsetApplier.InsetSensitiveViewRegistration insetsensitiveviewregistration = (com.google.android.calendar.utils.SystemWindowInsetApplier.InsetSensitiveViewRegistration)((SystemWindowInsetApplier) (obj)).views.remove(speeddiallayout);
                    if (insetsensitiveviewregistration == null)
                    {
                        LogUtils.wtf(SystemWindowInsetApplier.TAG, "Could not remove view %s in removeView. Items in mViews: %s", new Object[] {
                            speeddiallayout, ((SystemWindowInsetApplier) (obj)).views.keySet()
                        });
                    } else
                    if (insetsensitiveviewregistration.applicationStyle == 1)
                    {
                        SystemWindowInsetApplier.applyMarginToView(insetsensitiveviewregistration, insetsensitiveviewregistration.oldLeftSpacing, insetsensitiveviewregistration.oldTopSpacing, insetsensitiveviewregistration.oldRightSpacing, insetsensitiveviewregistration.oldBottomSpacing);
                    } else
                    {
                        SystemWindowInsetApplier.applyPaddingToView(insetsensitiveviewregistration, insetsensitiveviewregistration.oldLeftSpacing, insetsensitiveviewregistration.oldTopSpacing, insetsensitiveviewregistration.oldRightSpacing, insetsensitiveviewregistration.oldBottomSpacing);
                    }
                }
                ViewCompat.setOnApplyWindowInsetsListener(speeddiallayout, null);
            }
            speedDialLayout = null;
        }

        public MainFabStack()
        {
            this$0 = AllInOneCalendarActivity.this;
            super();
            speedDialLayout = null;
        }
    }

    final class OnMiniMonthDatePickerListener
        implements android.view.View.OnClickListener, android.view.View.OnKeyListener, android.view.View.OnTouchListener
    {

        public int actionBarHeight;
        private int activePointerId;
        private int activePointerIndex;
        private MiniMonthInteractionController controller;
        public ViewPager datePicker;
        private final int datePickerButtonId = 0x7f100100;
        public View datePickerContainer;
        private int dragDirection;
        public boolean isDraggable;
        private float startY;
        private GestureDetector tapDetector;
        public final AllInOneCalendarActivity this$0;
        private VelocityTracker velocityTracker;

        private final boolean completeMotion(MotionEvent motionevent)
        {
            boolean flag2 = false;
            if (controller != null && activePointerId != -1 && activePointerIndex < motionevent.getPointerCount())
            {
                float f = motionevent.getY(activePointerIndex);
                velocityTracker.computeCurrentVelocity(1);
                float f2 = velocityTracker.getYVelocity(activePointerId);
                float f1;
                boolean flag;
                if (dragDirection == 0)
                {
                    f -= actionBarHeight;
                    f1 = f / (float)controller.getCurrentMonthHeight();
                } else
                {
                    motionevent = ViewConfiguration.get(AllInOneCalendarActivity.this);
                    if (f2 == 0.0F && Math.abs(f - startY) <= (float)motionevent.getScaledTouchSlop())
                    {
                        activePointerId = -1;
                        startY = -1F;
                        dragDirection = -1;
                        return false;
                    }
                    f = startY - f;
                    f1 = 1.0F - f / (float)controller.getCurrentMonthHeight();
                }
                if (f2 == 0.0F)
                {
                    if ((double)f1 >= 0.5D)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                } else
                if (f2 > 0.0F)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (f1 > 0.0F && f1 <= 1.0F)
                {
                    f1 = ((float)controller.getCurrentMonthHeight() - f) / 300F;
                    int i = 300;
                    if (Math.abs(f2) >= f1 * 10F)
                    {
                        i = (int)((((float)controller.getCurrentMonthHeight() - f) / (float)controller.getCurrentMonthHeight()) * 300F);
                    }
                    setDatePickerVisible(flag, i);
                } else
                {
                    boolean flag1 = flag2;
                    if ((double)f1 >= 0.5D)
                    {
                        flag1 = true;
                    }
                    onDatePickerVisibilityChangeDone(flag1);
                }
            }
            activePointerId = -1;
            startY = -1F;
            dragDirection = -1;
            return true;
        }

        private final boolean tryInitialize(View view)
        {
            final Object controller = AllInOneCalendarActivity.this;
            Fragment fragment = ((AllInOneCalendarActivity) (controller)).getMainFragment();
            if (fragment instanceof MiniMonthInteractionController)
            {
                controller = (MiniMonthInteractionController)fragment;
            } else
            if (fragment instanceof CalendarFragment)
            {
                controller = ((AllInOneCalendarActivity) (controller)).miniMonthInteractionController;
            } else
            {
                controller = null;
            }
            if (controller == null || !((MiniMonthInteractionController) (controller)).isMiniMonthToggleable())
            {
                LogUtils.i("AllInOneCalendarAct", "Unable to find Timely Fragment for month drag listener.", new Object[0]);
                return false;
            }
            this.controller = ((MiniMonthInteractionController) (controller));
            datePickerContainer = ((MiniMonthInteractionController) (controller)).getDatePickerContainer();
            datePicker = ((MiniMonthInteractionController) (controller)).getDatePicker();
            actionBarHeight = getSupportActionBar().getHeight();
            class _cls2 extends android.support.v4.view.ViewPager.SimpleOnPageChangeListener
            {

                private float bottomDelta;
                private float bottomStart;
                private float positionDelta;
                private float positionStart;
                private int state;
                private final OnMiniMonthDatePickerListener this$1;
                private final MiniMonthInteractionController val$controller;

                public final void onPageScrollStateChanged(int i)
                {
                    if (datePickerOpen && i == 2)
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    state = i;
                }

                public final void onPageScrolled(int i, float f, int j)
                {
label0:
                    {
                        f = (float)i + f;
                        if (state == 1)
                        {
                            OnMiniMonthDatePickerListener onminimonthdatepickerlistener = OnMiniMonthDatePickerListener.this;
                            float f1 = onminimonthdatepickerlistener.actionBarHeight + onminimonthdatepickerlistener.datePickerContainer.getBottom();
                            bottomStart = onminimonthdatepickerlistener.datePickerContainer.getTranslationY() + f1;
                            bottomDelta = (float)(actionBarHeight + controller.getCurrentMonthHeight()) - bottomStart;
                            positionStart = f;
                            positionDelta = (float)datePicker.getCurrentItem() - positionStart;
                            if (bottomDelta != 0.0F)
                            {
                                i = 2;
                            } else
                            {
                                i = 0;
                            }
                            state = i;
                        }
                        if (state == 2)
                        {
                            if (f != (float)datePicker.getCurrentItem())
                            {
                                break label0;
                            }
                            setDatePickerBottom(bottomStart + bottomDelta, false);
                        }
                        return;
                    }
                    f = (bottomDelta * (f - positionStart)) / positionDelta;
                    setDatePickerBottom(f + bottomStart, false);
                }

                _cls2()
                {
                    this$1 = OnMiniMonthDatePickerListener.this;
                    controller = minimonthinteractioncontroller;
                    super();
                    state = 0;
                }
            }

            if (datePicker != null && orientation == 1)
            {
                datePicker.addOnPageChangeListener(new _cls2());
            }
            if (view != null && view.getId() == datePickerButtonId)
            {
                ((MiniMonthInteractionController) (controller)).getDragUpView().setOnTouchListener(miniMonthListener);
            }
            return true;
        }

        public final void onClick(View view)
        {
            toggleDatePicker();
        }

        final void onDatePickerVisibilityChangeDone(boolean flag)
        {
            controller.onMiniMonthVisibilityChanging(flag);
            if (flag != datePickerOpen)
            {
                datePickerOpen = flag;
                controller.onMiniMonthVisibilityChanged(datePickerOpen);
                Object obj = AllInOneCalendarActivity.this;
                obj = Features.instance;
                if (obj == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                }
                ((FeatureConfig)obj).alternate_timeline();
                boolean flag1 = RemoteFeatureConfig.ALTERNATE_TIMELINE.enabled();
                obj = Features.instance;
                if (obj == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                }
                ((FeatureConfig)obj).experimental_options();
                if (flag1 && !flag)
                {
                    onNewRange((Range)selectedRangeObservable.get());
                }
                setDatePickerContentDescription();
                obj = AllInOneCalendarActivity.this;
                float f;
                if (datePickerOpen)
                {
                    f = 1.0F;
                } else
                {
                    f = 0.0F;
                }
                ((AllInOneCalendarActivity) (obj)).setDatePickerArrow(true, f);
                if (AccessibilityUtils.isAccessibilityEnabled(AllInOneCalendarActivity.this) && !datePickerOpen)
                {
                    AccessibilityUtils.requestAccessibilityFocus(datePickerButton);
                }
            }
        }

        public final boolean onKey(View view, int i, KeyEvent keyevent)
        {
            i;
            JVM INSTR tableswitch 66 66: default 20
        //                       66 22;
               goto _L1 _L2
_L1:
            return false;
_L2:
            if (view.getId() == datePickerButtonId && keyevent.getAction() == 1)
            {
                toggleDatePicker();
                return true;
            }
            if (true) goto _L1; else goto _L3
_L3:
        }

        public final boolean onTouch(View view, MotionEvent motionevent)
        {
_L2:
            return false;
            if ((controller == null || !controller.isFragmentAttached()) && !tryInitialize(view) || tapDetector.onTouchEvent(motionevent)) goto _L2; else goto _L1
_L1:
            if (isDraggable)
            {
                break; /* Loop/switch isn't completed */
            }
            if (view.getId() == datePickerButtonId)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (datePickerOpen)
            {
                return true;
            }
            break; /* Loop/switch isn't completed */
            if (true) goto _L2; else goto _L3
_L3:
            motionevent.getAction();
            JVM INSTR tableswitch 0 6: default 116
        //                       0 134
        //                       1 405
        //                       2 320
        //                       3 405
        //                       4 116
        //                       5 116
        //                       6 415;
               goto _L4 _L5 _L6 _L7 _L6 _L4 _L4 _L8
_L4:
            boolean flag = true;
_L9:
            if (flag && dragDirection == 1)
            {
                return true;
            }
              goto _L2
_L5:
            activePointerIndex = motionevent.getActionIndex();
            activePointerId = motionevent.getPointerId(activePointerIndex);
            if (!datePickerOpen && view.getId() == datePickerButtonId)
            {
                dragDirection = 0;
                flag = true;
            } else
            if (datePickerOpen && view.getId() != datePickerButtonId)
            {
                dragDirection = 1;
                flag = true;
            } else
            {
                LogUtils.w("AllInOneCalendarAct", "Ignoring drag on other view %d", new Object[] {
                    Integer.valueOf(view.getId())
                });
                dragDirection = -1;
                flag = false;
            }
            if (dragDirection == 0)
            {
                setDatePickerVisible(false, 0);
            } else
            if (dragDirection == 1)
            {
                startY = motionevent.getY(activePointerIndex);
            }
            if (velocityTracker == null)
            {
                velocityTracker = VelocityTracker.obtain();
            } else
            {
                velocityTracker.clear();
            }
            velocityTracker.addMovement(motionevent);
              goto _L9
_L7:
            if (motionevent.getPointerId(motionevent.getActionIndex()) != activePointerId) goto _L4; else goto _L10
_L10:
            float f = motionevent.getY();
            if (dragDirection == 0)
            {
                setDatePickerBottom(f, true);
            } else
            if (dragDirection == 1)
            {
                setDatePickerBottom((float)(actionBarHeight + controller.getCurrentMonthHeight()) - (startY - f), true);
            }
            velocityTracker.addMovement(motionevent);
            flag = true;
              goto _L9
_L6:
            flag = completeMotion(motionevent);
              goto _L9
_L8:
            if (motionevent.getPointerId(motionevent.getActionIndex()) != activePointerId) goto _L4; else goto _L11
_L11:
            flag = completeMotion(motionevent);
              goto _L9
        }

        final void setDatePickerBottom(float f, boolean flag)
        {
            f -= actionBarHeight;
            MiniMonthInteractionController minimonthinteractioncontroller = controller;
            float f1;
            boolean flag1;
            if (f > 0.0F)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            minimonthinteractioncontroller.onMiniMonthVisibilityChanging(flag1);
            f = Math.min(Math.max(f, 0.0F), Math.max((int)((float)(actionBarHeight + datePickerContainer.getBottom()) + datePickerContainer.getTranslationY()) - actionBarHeight, controller.getCurrentMonthHeight()));
            f1 = f - (float)datePickerContainer.getHeight();
            datePicker.setTranslationY(-f1);
            datePickerContainer.setTranslationY(f1);
            if (backgroundImagesFrame != null)
            {
                backgroundImagesFrame.setClippingTranslationY(f);
            }
            controller.setViewTranslationY(f);
            if (flag)
            {
                f /= controller.getCurrentMonthHeight();
                setDatePickerArrow(true, f);
            }
        }

        final void setDatePickerRight(float f)
        {
            Object obj = controller;
            float f1;
            float f3;
            int i;
            boolean flag;
            if (f > 0.0F)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            ((MiniMonthInteractionController) (obj)).onMiniMonthVisibilityChanging(flag);
            if (isRtl)
            {
                i = -1;
            } else
            {
                i = 1;
            }
            f1 = datePickerContainer.getWidth();
            f1 = Math.min(f, f1) - f1;
            datePickerContainer.setTranslationX((float)i * f1);
            obj = datePicker;
            f3 = i;
            ((ViewPager) (obj)).setTranslationX(-f1 * f3);
            if (backgroundImagesFrame != null)
            {
                float f2 = f / (float)cardLeftMargin;
                float f4 = backgroundImagesFrame.backgroundOffsetFromTopLandscape;
                backgroundImagesFrame.setClippingTranslationY(f2 * f4);
            }
            controller.setViewTranslationX((float)i * f);
            f /= datePicker.getWidth();
            setDatePickerArrow(true, f);
        }

        final void setDatePickerVisible(final boolean visible, int i)
        {
            float f1 = 0.0F;
            if (datePicker == null && (getSupportActionBar() == null || !tryInitialize(null)))
            {
                return;
            }
            int k = actionBarHeight;
            float f;
            float f2;
            int j;
            if (!visible)
            {
                j = 0;
            } else
            {
                j = controller.getCurrentMonthHeight();
            }
            f2 = j + k;
            if (!visible)
            {
                f = 0.0F;
            } else
            {
                f = cardLeftMargin;
            }
            if (i == 0)
            {
                if (orientation == 1)
                {
                    setDatePickerBottom(f2, true);
                } else
                {
                    setDatePickerRight(f);
                }
                onDatePickerVisibilityChangeDone(visible);
                return;
            }
            class _cls3 extends AnimatorListenerAdapter
            {

                private final OnMiniMonthDatePickerListener this$1;
                private final boolean val$visible;

                public final void onAnimationEnd(Animator animator)
                {
                    animator = _fld0;
                    onDatePickerVisibilityChangeDone(visible);
                }

                _cls3()
                {
                    this$1 = OnMiniMonthDatePickerListener.this;
                    visible = flag;
                    super();
                }
            }

            ValueAnimator valueanimator;
            AllInOneCalendarActivity allinonecalendaractivity;
            if (orientation == 1)
            {
                valueanimator = ValueAnimator.ofFloat(new float[] {
                    (float)datePickerContainer.getBottom() + datePickerContainer.getTranslationY() + (float)actionBarHeight, f2
                }).setDuration(i);
                class .Lambda._cls0
                    implements android.animation.ValueAnimator.AnimatorUpdateListener
                {

                    private final OnMiniMonthDatePickerListener arg$1;

                    public final void onAnimationUpdate(ValueAnimator valueanimator1)
                    {
                        arg$1.setDatePickerBottom(((Float)valueanimator1.getAnimatedValue()).floatValue(), true);
                    }

                .Lambda._cls0()
                {
                    arg$1 = OnMiniMonthDatePickerListener.this;
                }
                }

                valueanimator.addUpdateListener(new .Lambda._cls0());
            } else
            {
                class .Lambda._cls1
                    implements android.animation.ValueAnimator.AnimatorUpdateListener
                {

                    private final OnMiniMonthDatePickerListener arg$1;

                    public final void onAnimationUpdate(ValueAnimator valueanimator1)
                    {
                        arg$1.setDatePickerRight(((Float)valueanimator1.getAnimatedValue()).floatValue());
                    }

                .Lambda._cls1()
                {
                    arg$1 = OnMiniMonthDatePickerListener.this;
                }
                }

                if (!visible)
                {
                    f1 = cardLeftMargin;
                }
                valueanimator = ValueAnimator.ofFloat(new float[] {
                    f1, f
                }).setDuration(i);
                valueanimator.addUpdateListener(new .Lambda._cls1());
            }
            allinonecalendaractivity = AllInOneCalendarActivity.this;
            valueanimator.setInterpolator(new DecelerateInterpolator());
            valueanimator.addListener(new _cls3());
            valueanimator.start();
        }

        final void toggleDatePicker()
        {
            if (datePicker == null && (getSupportActionBar() == null || !tryInitialize(null)))
            {
                return;
            }
            boolean flag;
            if (!datePickerOpen)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            setDatePickerVisible(flag, 300);
        }

        public OnMiniMonthDatePickerListener()
        {
            this$0 = AllInOneCalendarActivity.this;
            super();
            activePointerId = -1;
            activePointerIndex = -1;
            class _cls1 extends android.view.GestureDetector.SimpleOnGestureListener
            {

                public final boolean onSingleTapUp(MotionEvent motionevent)
                {
                    return true;
                }

                _cls1()
                {
                }
            }

            tapDetector = new GestureDetector(AllInOneCalendarActivity.this, new _cls1());
        }
    }


    private static boolean hasBeenRestarted = false;
    public BackgroundImagesFrame backgroundImagesFrame;
    private FrameLayout backgroundLayout;
    private int backgroundMonth;
    public Optional calendarContentStore;
    public Optional calendarFragmentFactory;
    public Optional calendarStoreInvalidator;
    private Subscription calendarsSubscription;
    public int cardLeftMargin;
    public CalendarController controller;
    public final MainFabStack createFabStack = new MainFabStack();
    private CalendarFragment currentCalendarFragment;
    private TextView datePickerAlternateMonthName;
    private ImageView datePickerArrow;
    public View datePickerButton;
    public boolean datePickerOpen;
    private TextView datePickerTextView;
    public DispatchingAndroidInjector dispatchingFragmentInjector;
    public DrawerFragment drawerFragment;
    public DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private int endJulianDay;
    private Subscription eventsChangedSubscription;
    private FeedbackUtils.FeedbackBroadcastReceiver feedbackReceiver;
    private final GrowthKitCallback growthKitCallback;
    public Handler handler;
    private final CalendarHatsService hatsService = new CalendarHatsService();
    public Animator infoFragmentDismiss;
    public boolean isRtl;
    private boolean isTabletConfig;
    public LaunchScreenManager launchScreenManager;
    private Animator liftFabAnimator;
    private Animator liftFabAnimatorButton;
    private Animator liftFabAnimatorDrawable;
    public Optional miniMonthController;
    public MiniMonthInteractionController miniMonthInteractionController;
    public OnMiniMonthDatePickerListener miniMonthListener;
    private Pair newIntentFragment;
    private final ContentObserver observer = new _cls2(new Handler());
    public boolean onSaveInstanceStateCalled;
    public int orientation;
    private boolean paused;
    public int pendingDrawerActionId;
    public CalendarActivityPropertiesManager preferencesManager;
    public boolean receivedFirstCalendar;
    public boolean restoreExpandedSpeedDial;
    private Integer selectedJulianDayInMonthViewWidget;
    public ObservableReference selectedRangeObservable;
    private boolean shouldCloseOverlays;
    public boolean speedDialSupported;
    private int startJulianDay;
    public Optional taskCacheInvalidator;
    private Subscription timeChangeSubscription;
    public final Runnable timeChangesUpdater = new _cls1();
    public String timeZone;
    private TimeZoneUpdateDialogLauncher timeZoneUpdateDialogLauncher;
    private boolean updateOnResume;
    private FluentFuture viewScreenFuture;
    public ObservableReference viewportLoaded;

    public AllInOneCalendarActivity()
    {
        GrowthKitCallback growthkitcallback = null;
        super();
        onSaveInstanceStateCalled = false;
        paused = true;
        updateOnResume = false;
        newIntentFragment = null;
        shouldCloseOverlays = false;
        selectedJulianDayInMonthViewWidget = null;
        handler = new Handler();
        pendingDrawerActionId = -1;
        orientation = 0;
        backgroundMonth = -1;
        receivedFirstCalendar = false;
        feedbackReceiver = new FeedbackUtils.FeedbackBroadcastReceiver(this);
        if (RemoteFeatureConfig.GROWTH_KIT.enabled())
        {
            growthkitcallback = new GrowthKitCallback(this);
        }
        growthKitCallback = growthkitcallback;
    }

    public static String computeMonthLabel(long l, Long long1, long l1)
    {
        DateTimeFormatHelper datetimeformathelper = DateTimeFormatHelper.instance;
        if (datetimeformathelper == null)
        {
            throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
        }
        datetimeformathelper = (DateTimeFormatHelper)datetimeformathelper;
        if (long1 == null)
        {
            long1 = Absent.INSTANCE;
        } else
        {
            long1 = new Present(long1);
        }
        return datetimeformathelper.getDateRangeText(l, ((Long)long1.or(Long.valueOf(l))).longValue(), (int)l1);
    }

    private final void configureDatePickerTextViews()
    {
        if (AlternateCalendarUtils.isAlternateCalendarEnabled(this))
        {
            datePickerTextView.setHeight(0x7f0e004c);
            datePickerTextView.setGravity(80);
            datePickerAlternateMonthName.setVisibility(0);
        } else
        {
            datePickerTextView.setHeight(0x7f0e004b);
            datePickerTextView.setGravity(16);
            datePickerAlternateMonthName.setVisibility(8);
        }
        setDatePickerContentDescription();
    }

    private final void configureDrawerLayout()
    {
        drawerLayout = (DrawerLayout)findViewById(0x7f100112);
        if (drawerToggle == null)
        {
            drawerToggle = new _cls3(this, drawerLayout, 0x7f130057, 0x7f130056);
            DrawerLayout drawerlayout = drawerLayout;
            ActionBarDrawerToggle actionbardrawertoggle1 = drawerToggle;
            if (drawerlayout.mListener != null)
            {
                android.support.v4.widget.DrawerLayout.DrawerListener drawerlistener = drawerlayout.mListener;
                if (drawerlistener != null && drawerlayout.mListeners != null)
                {
                    drawerlayout.mListeners.remove(drawerlistener);
                }
            }
            if (actionbardrawertoggle1 != null && actionbardrawertoggle1 != null)
            {
                if (drawerlayout.mListeners == null)
                {
                    drawerlayout.mListeners = new ArrayList();
                }
                drawerlayout.mListeners.add(actionbardrawertoggle1);
            }
            drawerlayout.mListener = actionbardrawertoggle1;
        }
        ActionBarDrawerToggle actionbardrawertoggle = drawerToggle;
        actionbardrawertoggle.mSlider = new _cls4(this);
        actionbardrawertoggle.syncState();
        drawerFragment = (DrawerFragment)super.mFragments.mHost.mFragmentManager.findFragmentById(0x7f100116);
        if (drawerFragment == null || ((Fragment) (drawerFragment)).mRemoving)
        {
            if (drawerFragment == null)
            {
                drawerFragment = new DrawerFragment();
            }
            FragmentTransaction fragmenttransaction = super.mFragments.mHost.mFragmentManager.beginTransaction();
            fragmenttransaction.replace(0x7f100116, drawerFragment);
            fragmenttransaction.commit();
        }
        drawerFragment.drawerItemClickedListener = this;
    }

    private static void dismissIfOpen(OverlayFragment overlayfragment)
    {
        if (overlayfragment == null) goto _L2; else goto _L1
_L1:
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = ((Fragment) (overlayfragment)).mFragmentManager;
        if (overlayfragment == null) goto _L4; else goto _L3
_L3:
        boolean flag;
        if (((Fragment) (overlayfragment)).mHost != null && ((Fragment) (overlayfragment)).mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L5; else goto _L4
_L4:
        flag = false;
_L7:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_43;
        }
        overlayfragment.dismissAllowingStateLoss();
        return;
_L5:
        FragmentActivity fragmentactivity;
        if (((Fragment) (overlayfragment)).mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)((Fragment) (overlayfragment)).mHost.mActivity;
        }
        if (fragmentactivity == null || fragmentactivity.isDestroyed() || fragmentactivity.isFinishing())
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if (fragmentmanagerimpl != null && !fragmentmanagerimpl.isDestroyed())
        {
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
_L2:
        flag = false;
        if (true) goto _L7; else goto _L6
_L6:
        overlayfragment;
    }

    private final transient OverlayFragment findOverlayFragment(String as[])
    {
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = super.mFragments.mHost.mFragmentManager;
        if (fragmentmanagerimpl.isDestroyed())
        {
            return null;
        }
        int j = as.length;
        for (int i = 0; i < j; i++)
        {
            Fragment fragment = fragmentmanagerimpl.findFragmentByTag(as[i]);
            if (fragment instanceof OverlayFragment)
            {
                return (OverlayFragment)fragment;
            }
        }

        return null;
    }

    private final Time getCreateFabDay()
    {
        MainFabStack mainfabstack = createFabStack;
        if (((CreateFabFragment.CreateFabStack) (mainfabstack)).stack.empty())
        {
            return mainfabstack.getCreateFabDay();
        } else
        {
            return ((CreateFabFragment.CreateFabStack.Scope)((CreateFabFragment.CreateFabStack) (mainfabstack)).stack.peek()).startDay.getCreateStartDay();
        }
    }

    private final DelayedActionPerformer getDelayedActionPerformer()
    {
        Object obj = (TaskBundleFragment)super.mFragments.mHost.mFragmentManager.findFragmentByTag(TaskBundleFragment.TAG);
        if (obj == null)
        {
            DayPopUpFragment daypopupfragment = (DayPopUpFragment)super.mFragments.mHost.mFragmentManager.findFragmentByTag(DayPopUpFragment.TAG);
            obj = daypopupfragment;
            if (daypopupfragment == null)
            {
                Fragment fragment = getMainFragment();
                if (fragment != null && (fragment instanceof DelayedActionPerformer))
                {
                    return (DelayedActionPerformer)fragment;
                } else
                {
                    return null;
                }
            }
        }
        return ((DelayedActionPerformer) (obj));
    }

    private final int getSelectedViewId()
    {
        Object obj1 = super.mFragments.mHost.mFragmentManager;
        Object obj = ((FragmentManager) (obj1)).findFragmentById(0x7f100115);
        if (obj == null)
        {
            obj = ((FragmentManager) (obj1)).findFragmentById(0x7f10011a);
        }
        if (obj instanceof DualTimelineGridFragment)
        {
            obj = (DualTimelineGridFragment)obj;
            obj1 = ((Fragment) (obj)).getArguments();
            if (((Fragment) (obj)).mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
            }
            return !((Bundle) (obj1)).getBoolean("args_in_grid_mode", ((Context) (obj)).getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preference_key_grid_mode", false)) ? 0x7f100004 : 0x7f100022;
        }
        if (obj instanceof ThreeDayViewFragment)
        {
            return 0x7f100026;
        }
        if (obj instanceof WeekFragment)
        {
            return 0x7f100050;
        }
        if (obj instanceof MonthFragment)
        {
            return 0x7f100027;
        }
        if (obj instanceof CalendarFragment)
        {
            return AlternateTimelineUtils.viewTypeToViewId(((CalendarFragment)obj).getViewType());
        } else
        {
            LogUtils.e("AllInOneCalendarAct", "Selected fragment is of unknown type: %s", new Object[] {
                obj.getClass().getSimpleName()
            });
            return 0;
        }
    }

    private final void initFragments(long l)
    {
        FragmentTransaction fragmenttransaction = super.mFragments.mHost.mFragmentManager.beginTransaction();
        if (onSaveInstanceStateCalled) goto _L2; else goto _L1
_L1:
        int j;
        boolean flag1;
        j = PreferencesUtils.getLastUsedView(this, isTabletConfig);
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)featureconfig).alternate_timeline();
        flag1 = RemoteFeatureConfig.ALTERNATE_TIMELINE.enabled();
        featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)featureconfig).experimental_options();
        if (flag1 && calendarFragmentFactory.isPresent()) goto _L4; else goto _L3
_L3:
        int i = 0;
_L18:
        if (i != 0) goto _L2; else goto _L5
_L5:
        final Object fragment;
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        fragmentmanagerimpl = super.mFragments.mHost.mFragmentManager;
        Fragment fragment1 = super.mFragments.mHost.mFragmentManager.findFragmentById(0x7f10011a);
        if (fragment1 != null)
        {
            fragmenttransaction.remove(fragment1);
        }
        fragment = fragmentmanagerimpl.findFragmentById(0x7f100115);
        if (isTabletConfig) goto _L7; else goto _L6
_L6:
        if (orientation != 2 && j != 0x7f100050) goto _L9; else goto _L8
_L4:
        class .Lambda._cls8
            implements Consumer
        {

            private final AllInOneCalendarActivity arg$1;

            public final void accept(Object obj3)
            {
                arg$1.onNewRange((Range)obj3);
            }

            .Lambda._cls8()
            {
                arg$1 = AllInOneCalendarActivity.this;
            }
        }

        selectedRangeObservable.subscribe(new .Lambda._cls8(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE, false);
        obj1 = AlternateTimelineUtils.viewIdToViewType(j);
        i = TimeBoxUtil.msToJulianDay(TimeZone.getTimeZone(timeZone), l);
        FlairAllocatorFactory.init();
        fragment = super.mFragments.mHost.mFragmentManager;
        obj = ((FragmentManager) (fragment)).findFragmentById(0x7f100115);
        if (obj == null)
        {
            obj = ((FragmentManager) (fragment)).findFragmentById(0x7f10011a);
        }
        if (obj instanceof CalendarFragment)
        {
            fragment = ((CalendarFragment)obj).onSwitchView(((com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType) (obj1)), i, false);
        } else
        {
            fragment = (CalendarFragment)((BiFunction)calendarFragmentFactory.get()).apply(obj1, Integer.valueOf(i));
        }
        currentCalendarFragment = ((CalendarFragment) (fragment));
        obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        obj2 = (AnalyticsLogger)obj;
        ((com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType) (obj1)).ordinal();
        JVM INSTR tableswitch 0 4: default 456
    //                   0 500
    //                   1 641
    //                   2 648
    //                   3 655
    //                   4 662;
           goto _L10 _L11 _L12 _L13 _L14 _L15
_L10:
        obj = String.valueOf(obj1);
        throw new IllegalArgumentException((new StringBuilder(String.valueOf(obj).length() + 19)).append("Unknown view type: ").append(((String) (obj))).toString());
_L11:
        obj = "day_grid";
_L17:
        ((AnalyticsLogger) (obj2)).trackView(this, ((String) (obj)));
        controller.registerHandler(0x7f100115, new _cls6());
        obj = (ViewGroup)findViewById(0x7f100115);
        if (((ViewGroup) (obj)).getChildCount() == 0)
        {
            getLayoutInflater().inflate(0x7f050022, ((ViewGroup) (obj)));
        }
        fragmenttransaction.replace(0x7f10011a, (Fragment)fragment);
        obj2 = super.mFragments.mHost.mFragmentManager.findFragmentById(0x7f100115);
        if (obj2 != null)
        {
            fragmenttransaction.remove(((Fragment) (obj2)));
        }
        AlternateTimelineHolderManager.adjustHolder((ViewGroup)((ViewGroup) (obj)).findViewById(0x7f100119), ((com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType) (obj1)));
        if (!miniMonthController.isPresent())
        {
            throw new IllegalStateException();
        }
        break; /* Loop/switch isn't completed */
_L12:
        obj = "nDay";
        continue; /* Loop/switch isn't completed */
_L13:
        obj = "week";
        continue; /* Loop/switch isn't completed */
_L14:
        obj = "agenda";
        continue; /* Loop/switch isn't completed */
_L15:
        obj = "month";
        if (true) goto _L17; else goto _L16
_L16:
        miniMonthInteractionController = new MiniMonthInteractionControllerImpl(((CalendarFragment) (fragment)), (MiniMonthController)miniMonthController.get(), ((ViewGroup) (obj)), controller, getSupportActionBar());
        refreshMiniMonthState();
        miniMonthInteractionController.pointTo(i);
        class .Lambda._cls9
            implements Consumer
        {

            private final AllInOneCalendarActivity arg$1;

            public final void accept(Object obj3)
            {
                AllInOneCalendarActivity allinonecalendaractivity = arg$1;
                if (((Boolean)obj3).booleanValue())
                {
                    obj3 = allinonecalendaractivity.launchScreenManager;
                    obj3.dataLoadedAndViewsUpdated = true;
                    ((LaunchScreenManager) (obj3)).hideLaunchScreen(false);
                }
            }

            .Lambda._cls9()
            {
                arg$1 = AllInOneCalendarActivity.this;
            }
        }

        viewportLoaded.subscribe(new .Lambda._cls9(), CalendarExecutor.MAIN, true);
        i = 1;
          goto _L18
_L8:
        Object obj;
        Object obj1;
        Object obj2;
        DualTimelineGridFragment dualtimelinegridfragment;
        boolean flag;
        boolean flag2;
        if (!(fragment instanceof WeekFragment))
        {
            obj1 = new WeekFragment();
            ((Fragment) (obj1)).setArguments(WeekFragment.createArgs(l));
        } else
        {
            obj1 = fragment;
        }
        obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        obj2 = (AnalyticsLogger)obj;
        ((AnalyticsLogger) (obj2)).trackView(this, "week");
        obj = obj1;
        if (j != 0x7f100050)
        {
            ((AnalyticsLogger) (obj2)).trackEvent(this, "menu_item", "week");
            obj = obj1;
        }
_L24:
        if (fragment == obj || fragment != null && fragment.equals(obj))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L20; else goto _L19
_L19:
        flag = false;
        if (fragmenttransaction == null)
        {
            flag = true;
            fragment = fragmentmanagerimpl.beginTransaction();
        } else
        {
            fragment = fragmenttransaction;
        }
        ((FragmentTransaction) (fragment)).replace(0x7f100115, ((Fragment) (obj)));
        if (flag)
        {
            LogUtils.d("AllInOneCalendarAct", "setMainPane AllInOne=%s finishing:%b", new Object[] {
                this, Boolean.valueOf(isFinishing())
            });
            ((FragmentTransaction) (fragment)).commit();
        }
        controller.registerHandler(0x7f100115, (CalendarController.Command.Handler)obj);
_L2:
        fragmenttransaction.commit();
        obj = new Time(timeZone);
        ((Time) (obj)).impl.timezone = ((Time) (obj)).timezone;
        ((Time) (obj)).impl.set(l);
        ((Time) (obj)).impl.toMillis(true);
        ((Time) (obj)).copyFieldsFromImpl();
        controller.goTo(this, ((Time) (obj)), 2L);
        return;
_L9:
        if (j == 0x7f100027)
        {
            if (fragment instanceof MonthFragment)
            {
                break; /* Loop/switch isn't completed */
            }
            obj = new MonthFragment();
            ((Fragment) (obj)).setArguments(MonthFragment.createArgs(l));
            obj1 = AnalyticsLoggerHolder.instance;
            if (obj1 == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)obj1).trackView(this, "month");
        } else
        if (j == 0x7f100004 || j == 0x7f100022)
        {
            if (!(fragment instanceof DualTimelineGridFragment))
            {
                obj1 = new DualTimelineGridFragment();
                if (j == 0x7f100022)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                ((Fragment) (obj1)).setArguments(DualTimelineGridFragment.createArgs(l, flag2));
                if (selectedJulianDayInMonthViewWidget != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    ((DualTimelineGridFragment)obj1).forceShowInitialDay = true;
                    selectedJulianDayInMonthViewWidget = null;
                }
                if (flag2)
                {
                    obj = "day_grid";
                } else
                {
                    obj = "agenda";
                }
                obj2 = AnalyticsLoggerHolder.instance;
                if (obj2 == null)
                {
                    throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                }
                ((AnalyticsLogger)obj2).trackView(this, ((String) (obj)));
            } else
            {
                obj1 = fragment;
            }
            if (selectedJulianDayInMonthViewWidget != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            obj = obj1;
            if (flag)
            {
                obj = new com.android.datetimepicker.date.MonthAdapter.CalendarDay();
                ((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (obj)).setJulianDay(selectedJulianDayInMonthViewWidget.intValue());
                ((DualTimelineGridFragment)obj1).onDayOfMonthSelected(((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (obj)));
                selectedJulianDayInMonthViewWidget = null;
                obj = obj1;
            }
        } else
        {
            if (fragment instanceof ThreeDayViewFragment)
            {
                break; /* Loop/switch isn't completed */
            }
            obj = new ThreeDayViewFragment();
            ((Fragment) (obj)).setArguments(ThreeDayViewFragment.createArgs(l));
            obj1 = AnalyticsLoggerHolder.instance;
            if (obj1 == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)obj1).trackView(this, "nDay");
        }
        continue; /* Loop/switch isn't completed */
_L7:
        if (fragment != null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (j == 0x7f100004 || j == 0x7f100022)
        {
            dualtimelinegridfragment = new DualTimelineGridFragment();
            if (0x7f100022 == j)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            obj1 = DualTimelineGridFragment.createArgs(l, flag2);
            if (flag2)
            {
                obj = "day_grid";
            } else
            {
                obj = "agenda";
            }
            obj2 = obj;
            obj = dualtimelinegridfragment;
        } else
        if (j == 0x7f100050)
        {
            obj = new WeekFragment();
            obj1 = WeekFragment.createArgs(l);
            obj2 = "week";
        } else
        if (j == 0x7f100027)
        {
            obj = new MonthFragment();
            obj1 = MonthFragment.createArgs(l);
            obj2 = "month";
        } else
        {
            LogUtils.wtf("AllInOneCalendarAct", "No default view available", new Object[0]);
            obj = fragment;
            obj2 = null;
            obj1 = null;
        }
        ((Fragment) (obj)).setArguments(((Bundle) (obj1)));
        if (obj2 != null)
        {
            obj1 = AnalyticsLoggerHolder.instance;
            if (obj1 == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)obj1).trackView(this, ((String) (obj2)));
        }
        continue; /* Loop/switch isn't completed */
_L20:
        datePickerOpen = false;
        if (obj instanceof MiniMonthInteractionController)
        {
            setDatePickerState((MiniMonthInteractionController)obj);
        }
        if (true) goto _L22; else goto _L21
_L22:
        break MISSING_BLOCK_LABEL_884;
_L21:
        obj = fragment;
        if (true) goto _L24; else goto _L23
_L23:
    }

    private final void launchFragmentsFromIntent()
    {
        Object obj1;
        obj1 = getIntent();
        break MISSING_BLOCK_LABEL_5;
_L10:
        do
        {
            return;
        } while (obj1 == null || (((Intent) (obj1)).getFlags() & 0x100000) != 0);
label0:
        {
            if (LaunchIntentConstants.editAction == null)
            {
                LaunchIntentConstants.editAction = String.valueOf(getPackageName()).concat(".EVENT_EDIT");
            }
            if (!LaunchIntentConstants.editAction.equals(((Intent) (obj1)).getAction()))
            {
                if (LaunchIntentConstants.insertAction == null)
                {
                    LaunchIntentConstants.insertAction = String.valueOf(getPackageName()).concat(".EVENT_INSERT");
                }
                if (!LaunchIntentConstants.insertAction.equals(((Intent) (obj1)).getAction()))
                {
                    break label0;
                }
            }
            dismissIfOpen(findOverlayFragment(new String[] {
                "ViewScreenController"
            }));
            dismissIfOpen(findOverlayFragment(new String[] {
                "HostDialog"
            }));
            onLaunchInsertOrEdit(((Intent) (obj1)).getExtras());
            replaceLaunchIntent(((Intent) (obj1)), ((Intent) (obj1)).getLongExtra("beginTime", 0L), ((Intent) (obj1)).getLongExtra("endTime", 0L));
            return;
        }
        if (LaunchIntentConstants.viewAction == null)
        {
            LaunchIntentConstants.viewAction = String.valueOf(getPackageName()).concat(".EVENT_VIEW");
        }
        if (!LaunchIntentConstants.viewAction.equals(((Intent) (obj1)).getAction())) goto _L2; else goto _L1
_L1:
        TimelineItem timelineitem;
        timelineitem = TimelineItemUtil.readTimelineItemFromIntent(this, ((Intent) (obj1)));
        if (timelineitem == null)
        {
            Toast.makeText(this, 0x7f1301d8, 0).show();
            return;
        }
        if (!(timelineitem instanceof TimelineTaskBundle)) goto _L4; else goto _L3
_L3:
        TaskBundleFragment taskbundlefragment;
        taskbundlefragment = new TaskBundleFragment();
        taskbundlefragment.setArguments(TaskBundleFragment.createArguments((TimelineTaskBundle)timelineitem, null));
        if (!isTabletConfig) goto _L6; else goto _L5
_L5:
        boolean flag;
        if (!launchScreenManager.launchScreenIsDismissed)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L6; else goto _L7
_L7:
        LaunchScreenManager launchscreenmanager = launchScreenManager;
        launchscreenmanager.shouldLaunchTaskBundle = true;
        launchscreenmanager.taskBundleFragment = taskbundlefragment;
_L8:
        replaceLaunchIntent(((Intent) (obj1)), timelineitem.getStartMillis(), timelineitem.getEndMillis());
        return;
_L6:
        showOverlayFragment(TaskBundleFragment.TAG, taskbundlefragment);
        continue; /* Loop/switch isn't completed */
_L4:
        launchInfoBubble(timelineitem, null, null);
        if (Utils.isTargetAllInOne(((Intent) (obj1))) && (timelineitem instanceof TimelineEvent) && ((EventKey)((TimelineEvent)timelineitem).eventKey instanceof com.google.android.calendar.api.event.EventKey.Persisted))
        {
            Object obj2 = (EventKey)((TimelineEvent)timelineitem).eventKey;
            obj2 = CalendarApi.Events.read(((EventKey) (obj2)));
            class .Lambda._cls10
                implements Consumer
            {

                private final AllInOneCalendarActivity arg$1;

                public final void accept(Object obj3)
                {
                    AllInOneCalendarActivity allinonecalendaractivity = arg$1;
                    obj3 = (Event)obj3;
                    Time time1 = new Time(allinonecalendaractivity.timeZone);
                    long l1 = ((Event) (obj3)).getStartMillis();
                    time1.impl.timezone = time1.timezone;
                    time1.impl.set(l1);
                    time1.impl.toMillis(true);
                    time1.copyFieldsFromImpl();
                    time1.normalizeSafe();
                    allinonecalendaractivity.controller.goTo(allinonecalendaractivity, time1, 0L);
                }

            .Lambda._cls10()
            {
                arg$1 = AllInOneCalendarActivity.this;
            }
            }

            com.google.common.util.concurrent.FutureCallback futurecallback = LogUtils.newFailureLoggingCallback(new .Lambda._cls10(), "AllInOneCalendarAct", "Unable to load event", new Object[0]);
            CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
            if (futurecallback == null)
            {
                throw new NullPointerException();
            }
            ((ListenableFuture) (obj2)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj2)), futurecallback), calendarexecutor);
        }
        if (true) goto _L8; else goto _L2
_L2:
        if (LaunchIntentConstants.dayViewAction == null)
        {
            LaunchIntentConstants.dayViewAction = String.valueOf(getPackageName()).concat(".DAY_VIEW");
        }
        if (LaunchIntentConstants.dayViewAction.equals(((Intent) (obj1)).getAction()))
        {
            int i = ((Intent) (obj1)).getIntExtra("julianDay", Utils.getTodayJulianDay(this));
            selectedJulianDayInMonthViewWidget = Integer.valueOf(i);
            obj1 = new com.android.datetimepicker.date.MonthAdapter.CalendarDay();
            ((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (obj1)).setJulianDay(i);
            if (orientation == 2 && !isTabletConfig)
            {
                CalendarController calendarcontroller = controller;
                int j = ((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (obj1)).year;
                int l = ((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (obj1)).month;
                int j1 = ((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (obj1)).day;
                obj1 = new Time(timeZone);
                ((Time) (obj1)).writeFieldsToImpl();
                ((Time) (obj1)).impl.set(j1, l, j);
                ((Time) (obj1)).copyFieldsFromImpl();
                ((Time) (obj1)).normalizeSafe();
                calendarcontroller.goTo(this, ((Time) (obj1)), 2L);
                return;
            } else
            {
                int k = ((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (obj1)).year;
                int i1 = ((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (obj1)).month;
                int k1 = ((com.android.datetimepicker.date.MonthAdapter.CalendarDay) (obj1)).day;
                Time time = new Time(timeZone);
                time.writeFieldsToImpl();
                time.impl.set(k1, i1, k);
                time.copyFieldsFromImpl();
                time.normalizeSafe();
                showDayOnScheduleOrDayView(time);
                return;
            }
        }
        if (LaunchIntentConstants.insertReminderAction == null)
        {
            LaunchIntentConstants.insertReminderAction = String.valueOf(getPackageName()).concat(".REMINDER_INSERT");
        }
        if (LaunchIntentConstants.insertReminderAction.equals(((Intent) (obj1)).getAction()))
        {
            if (AccountsUtil.getGoogleAccounts(this).length == 0)
            {
                Toast.makeText(this, 0x7f1303e4, 1).show();
                return;
            }
            Bundle bundle = ((Intent) (obj1)).getExtras();
            android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = super.mFragments.mHost.mFragmentManager;
            ReminderEditScreenController remindereditscreencontroller = new ReminderEditScreenController();
            Object obj = remindereditscreencontroller.getArguments();
            if (obj == null)
            {
                obj = Absent.INSTANCE;
            } else
            {
                obj = new Present(obj);
            }
            obj = (Bundle)((Optional) (obj)).or(new Bundle());
            ((Bundle) (obj)).putBundle("ARG_EXTRAS", bundle);
            remindereditscreencontroller.setArguments(((Bundle) (obj)));
            HostDialog.showWithChildFragment(this, fragmentmanagerimpl, remindereditscreencontroller);
            obj = createFabStack.getHideAnimatorCreateFab();
            if (obj != null)
            {
                ((ObjectAnimator) (obj)).start();
            }
            replaceLaunchIntent(((Intent) (obj1)), ((Intent) (obj1)).getLongExtra("beginTime", 0L), ((Intent) (obj1)).getLongExtra("endTime", 0L));
            return;
        }
        if (LaunchIntentConstants.openCalendarAction == null)
        {
            LaunchIntentConstants.openCalendarAction = String.valueOf(getPackageName()).concat(".OPEN_CALENDAR");
        }
        if (LaunchIntentConstants.openCalendarAction.equals(((Intent) (obj1)).getAction()))
        {
            replaceLaunchIntent(((Intent) (obj1)), ((Intent) (obj1)).getLongExtra("beginTime", 0L), ((Intent) (obj1)).getLongExtra("endTime", 0L));
            return;
        }
        if (LaunchIntentConstants.openCalendarAndShowErrorToastAction == null)
        {
            LaunchIntentConstants.openCalendarAndShowErrorToastAction = String.valueOf(getPackageName()).concat(".OPEN_CALENDAR_AND_SHOW_ERROR_TOAST");
        }
        if (LaunchIntentConstants.openCalendarAndShowErrorToastAction.equals(((Intent) (obj1)).getAction()))
        {
            replaceLaunchIntent(((Intent) (obj1)), ((Intent) (obj1)).getLongExtra("beginTime", 0L), ((Intent) (obj1)).getLongExtra("endTime", 0L));
            Toast.makeText(this, ((Intent) (obj1)).getStringExtra("error_message_extra"), 0).show();
            return;
        }
        if (true) goto _L10; else goto _L9
_L9:
    }

    private final void onLaunchInsertTask(Bundle bundle)
    {
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = super.mFragments.mHost.mFragmentManager;
        ReminderEditScreenController remindereditscreencontroller = new ReminderEditScreenController();
        Object obj = remindereditscreencontroller.getArguments();
        if (obj == null)
        {
            obj = Absent.INSTANCE;
        } else
        {
            obj = new Present(obj);
        }
        obj = (Bundle)((Optional) (obj)).or(new Bundle());
        ((Bundle) (obj)).putBundle("ARG_EXTRAS", bundle);
        remindereditscreencontroller.setArguments(((Bundle) (obj)));
        HostDialog.showWithChildFragment(this, fragmentmanagerimpl, remindereditscreencontroller);
        bundle = createFabStack.getHideAnimatorCreateFab();
        if (bundle != null)
        {
            bundle.start();
        }
    }

    private final void refreshMiniMonthState()
    {
        if (miniMonthInteractionController == null) goto _L2; else goto _L1
_L1:
        boolean flag;
        if (miniMonthInteractionController.hasMiniMonth() && !miniMonthInteractionController.isMiniMonthToggleable())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L4; else goto _L3
_L3:
        miniMonthListener.setDatePickerVisible(false, 0);
_L6:
        miniMonthInteractionController.refreshState();
        setDatePickerState(miniMonthInteractionController);
_L2:
        return;
_L4:
        if (datePickerOpen && !miniMonthInteractionController.hasMiniMonth())
        {
            miniMonthListener.setDatePickerVisible(false, 0);
        } else
        if (datePickerOpen)
        {
            miniMonthListener.toggleDatePicker();
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    private final void replaceLaunchIntent(Intent intent, long l, long l1)
    {
        Intent intent1 = new Intent(this, com/google/android/calendar/AllInOneCalendarActivity);
        intent1.setAction("android.intent.action.VIEW");
        intent = intent.getStringExtra("intent_source");
        if (intent != null)
        {
            intent1.putExtra("intent_source", intent);
        }
        if (l > 0L)
        {
            intent1.putExtra("beginTime", l);
        }
        if (l1 > 0L)
        {
            intent1.putExtra("endTime", l1);
        }
        setIntent(intent1);
    }

    private final void runOnGlobalLayout(final Runnable runnable)
    {
        final View view = findViewById(0x1020002);
        if (view != null)
        {
            ViewTreeObserver viewtreeobserver = view.getViewTreeObserver();
            if (viewtreeobserver != null)
            {
                viewtreeobserver.addOnGlobalLayoutListener(new _cls9());
            }
        }
    }

    private final void selectDayFromMonthWidget(DualTimelineGridFragment dualtimelinegridfragment)
    {
        boolean flag;
        if (selectedJulianDayInMonthViewWidget != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            com.android.datetimepicker.date.MonthAdapter.CalendarDay calendarday = new com.android.datetimepicker.date.MonthAdapter.CalendarDay();
            calendarday.setJulianDay(selectedJulianDayInMonthViewWidget.intValue());
            dualtimelinegridfragment.onDayOfMonthSelected(calendarday);
            selectedJulianDayInMonthViewWidget = null;
        }
    }

    private final void setAlternateMonthLabel(int i, int j)
    {
        startJulianDay = i;
        endJulianDay = j;
        i = PreferencesConstants.getAlternateCalendarPref(this);
        if (i != 0)
        {
            datePickerAlternateMonthName.setText(StringUtils.capitalizeStandalone(AlternateCalendarUtils.getAlternateYearMonthRangeString(startJulianDay, endJulianDay, getResources(), i), Locale.getDefault()));
            setDatePickerContentDescription();
        }
    }

    private final void setBackgroundMonth(int i)
    {
        backgroundMonth = i;
        if (backgroundImagesFrame != null)
        {
            BackgroundImagesFrame backgroundimagesframe = backgroundImagesFrame;
            i = backgroundMonth;
            int j = getSelectedViewId();
            if (backgroundimagesframe.backgroundMonth != i || backgroundimagesframe.selectedViewId != j)
            {
                if (backgroundimagesframe.selectedViewId != j)
                {
                    backgroundimagesframe.secondaryBackgroundImageView.setClippingTranslationY(0.0F);
                }
                backgroundimagesframe.backgroundMonth = i;
                backgroundimagesframe.selectedViewId = j;
                if (backgroundimagesframe.doingFade)
                {
                    backgroundimagesframe.pendingMonth = true;
                    return;
                } else
                {
                    backgroundimagesframe.loadSelectedMonth();
                    return;
                }
            }
        }
    }

    private final void setDatePickerState(MiniMonthInteractionController minimonthinteractioncontroller)
    {
        if (minimonthinteractioncontroller == null)
        {
            LogUtils.w("AllInOneCalendarAct", "No MiniMonthController", new Object[0]);
            return;
        } else
        {
            boolean flag = minimonthinteractioncontroller.isMiniMonthToggleable();
            datePickerButton.setEnabled(flag);
            setDatePickerArrow(flag, 0.0F);
            miniMonthListener.isDraggable = minimonthinteractioncontroller.isMiniMonthDraggable();
            return;
        }
    }

    private final void showDayOnScheduleOrDayView(Time time)
    {
        boolean flag = true;
        CalendarController calendarcontroller = controller;
        time.writeFieldsToImpl();
        long l = time.impl.toMillis(false);
        time = calendarcontroller.time;
        time.impl.timezone = time.timezone;
        time.impl.set(l);
        time.impl.toMillis(true);
        time.copyFieldsFromImpl();
        int i;
        if (PreferencesUtils.getPrefersGridMode(this))
        {
            i = 0x7f100022;
        } else
        {
            i = 0x7f100004;
        }
        if (selectedJulianDayInMonthViewWidget != null)
        {
            flag = false;
        }
        switchView(i, flag);
    }

    public final boolean areAnyBottomSheetsShown()
    {
        if (SyncOffNotificationsManager.instance == null)
        {
            SyncOffNotificationsManager.instance = new SyncOffNotificationsManager(this);
        }
        SyncOffNotificationsManager syncoffnotificationsmanager = SyncOffNotificationsManager.instance;
        return syncoffnotificationsmanager.isShowing || syncoffnotificationsmanager.shouldShowOnAppOpen() || hatsService.wasSurveyShown || growthKitCallback != null && growthKitCallback.isShowing;
    }

    public final void dismissOverlay(final OverlayFragment fragment, boolean flag)
    {
        Object obj;
        int i;
        obj = super.mFragments.mHost.mFragmentManager;
        if (infoFragmentDismiss != null)
        {
            infoFragmentDismiss.end();
            ((FragmentManager) (obj)).executePendingTransactions();
            infoFragmentDismiss = null;
        }
        i = getFirstBackStackEntry(((FragmentManager) (obj)), fragment);
        if (i <= 0) goto _L2; else goto _L1
_L1:
        String s = ((FragmentManager) (obj)).getBackStackEntryAt(i - 1).getName();
        if (TextUtils.isEmpty(s)) goto _L2; else goto _L3
_L3:
        final boolean shouldShowFab = ((FragmentManager) (obj)).findFragmentByTag(s) instanceof CreateFabFragment;
_L8:
        if (shouldShowFab)
        {
            obj = createFabStack.getShowAnimatorCreateFab();
        } else
        {
            obj = createFabStack.getHideAnimatorCreateFab();
        }
        if (!flag || obj == null) goto _L5; else goto _L4
_L4:
        infoFragmentDismiss = ((Animator) (obj));
        ((ObjectAnimator) (obj)).addListener(new _cls8());
_L6:
        if (obj != null)
        {
            ((ObjectAnimator) (obj)).setDuration(150L);
            if (shouldShowFab || flag)
            {
                ((ObjectAnimator) (obj)).setStartDelay(150L);
            }
            ((ObjectAnimator) (obj)).start();
        }
        return;
_L5:
        if (!onSaveInstanceStateCalled)
        {
            finishDismissOverlay(fragment);
        }
        if (true) goto _L6; else goto _L2
_L2:
        shouldShowFab = true;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final CreateFabFragment.CreateFabStack getCreateFabStack()
    {
        return createFabStack;
    }

    public final ImageButton getFloatingActionButton()
    {
        Object obj = createFabStack;
        if (((CreateFabFragment.CreateFabStack) (obj)).stack.empty())
        {
            obj = ((CreateFabFragment.CreateFabStack) (obj)).findCreateFab();
        } else
        {
            obj = ((CreateFabFragment.CreateFabStack.Scope)((CreateFabFragment.CreateFabStack) (obj)).stack.peek()).createFab;
        }
        return (ImageButton)obj;
    }

    public final View getFragmentView()
    {
        OverlayFragment overlayfragment;
        if (TextUtils.isEmpty(super.overlayFragmentTag))
        {
            overlayfragment = null;
        } else
        {
            overlayfragment = (OverlayFragment)super.mFragments.mHost.mFragmentManager.findFragmentByTag(super.overlayFragmentTag);
        }
        if (overlayfragment != null && overlayfragment.isVisible())
        {
            return ((Fragment) (overlayfragment)).mView;
        } else
        {
            return null;
        }
    }

    final Fragment getMainFragment()
    {
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = super.mFragments.mHost.mFragmentManager;
        Fragment fragment = fragmentmanagerimpl.findFragmentById(0x7f100115);
        if (fragment != null)
        {
            return fragment;
        } else
        {
            return fragmentmanagerimpl.findFragmentById(0x7f10011a);
        }
    }

    public final long getSupportedCommands()
    {
        return 13312L;
    }

    public final void handleCommand(CalendarController.Command command)
    {
_L2:
        do
        {
            return;
        } while (command.type != 1024L || getSupportActionBar() == null || datePickerTextView == null);
        Object obj;
        Long long1;
        int i;
        long l;
        long l1;
        if (command.selectedTime == null)
        {
            i = command.startTime.month;
        } else
        {
            i = command.selectedTime.month;
        }
        setBackgroundMonth(i);
        obj = command.startTime;
        ((Time) (obj)).writeFieldsToImpl();
        l1 = ((Time) (obj)).impl.toMillis(false);
        if (command.endTime != null)
        {
            obj = command.endTime;
            ((Time) (obj)).writeFieldsToImpl();
            l = ((Time) (obj)).impl.toMillis(false);
        } else
        {
            l = l1;
        }
        long1 = Long.valueOf(l);
        l = command.extraLong;
        command = DateTimeFormatHelper.instance;
        if (command == null)
        {
            throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
        }
        obj = (DateTimeFormatHelper)command;
        if (long1 == null)
        {
            command = Absent.INSTANCE;
        } else
        {
            command = new Present(long1);
        }
        command = ((DateTimeFormatHelper) (obj)).getDateRangeText(l1, ((Long)command.or(Long.valueOf(l1))).longValue(), (int)l);
        datePickerTextView.setText(StringUtils.capitalizeStandalone(command, Locale.getDefault()));
        Calendar.getInstance(TimeZone.getTimeZone(timeZone)).setTimeInMillis(l1);
        setDatePickerContentDescription();
        return;
        if (command.type == 1024L)
        {
            break MISSING_BLOCK_LABEL_11;
        }
        if (command.type != 4096L)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (miniMonthListener == null || !datePickerButton.isEnabled()) goto _L2; else goto _L1
_L1:
        miniMonthListener.toggleDatePicker();
        return;
        if (command.type != 8192L || command.type != 8192L || getSupportActionBar() == null) goto _L2; else goto _L3
_L3:
        setAlternateMonthLabel(Utils.getJulianDay(command.startTime.year, command.startTime.month, command.startTime.monthDay), Utils.getJulianDay(command.endTime.year, command.endTime.month, command.endTime.monthDay));
        return;
    }

    public final void launchInfoBubble(TimelineItem timelineitem, EventInfoAnimationData eventinfoanimationdata, Bundle bundle)
    {
        if (viewScreenFuture != null)
        {
            viewScreenFuture.cancel(true);
            viewScreenFuture = null;
        }
        if (timelineitem instanceof TimelineExternalEvent)
        {
            Object obj = (EventKey)((TimelineEvent) ((TimelineExternalEvent)timelineitem)).eventKey;
            timelineitem = new TimeBoxToTimelineAdapter(this);
            obj = (new EventsApiImpl(this, new NewViewScreenFactory..Lambda._cls0(this))).getAsync(((EventKey) (obj)));
            timelineitem.getClass();
            timelineitem = (FluentFuture)AbstractTransformFuture.create((FluentFuture)AbstractTransformFuture.create(((ListenableFuture) (obj)), new NewViewScreenFactory..Lambda._cls1(timelineitem), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE), new NewViewScreenFactory..Lambda._cls2(eventinfoanimationdata, bundle), CalendarExecutor.MAIN);
        } else
        {
            timelineitem = NewViewScreenFactory.onTimelineItem(timelineitem, eventinfoanimationdata, bundle);
        }
        viewScreenFuture = timelineitem;
        timelineitem = viewScreenFuture;
        eventinfoanimationdata = new _cls7();
        bundle = CalendarExecutor.MAIN;
        if (eventinfoanimationdata == null)
        {
            throw new NullPointerException();
        } else
        {
            timelineitem.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(timelineitem, eventinfoanimationdata), bundle);
            return;
        }
    }

    final void logMenuItemSelected(int i)
    {
        String s;
        AnalyticsLogger analyticslogger;
        if (i == 0x7f100004)
        {
            s = "agenda";
        } else
        if (i == 0x7f100022)
        {
            s = "day";
        } else
        if (i == 0x7f100026)
        {
            s = "nDay";
        } else
        if (i == 0x7f100037)
        {
            s = "search";
        } else
        if (i == 0x7f100426)
        {
            s = "refresh";
        } else
        if (i == 0x7f100425)
        {
            s = "today";
        } else
        if (i == 0x7f100050)
        {
            s = "week";
        } else
        if (i == 0x7f100027)
        {
            s = "month";
        } else
        if (i == 0x102002c)
        {
            s = "home";
        } else
        if (i == 0x7f100394)
        {
            s = "overflow";
        } else
        {
            s = String.valueOf(Integer.toHexString(i));
            if (s.length() != 0)
            {
                s = "0x".concat(s);
            } else
            {
                s = new String("0x");
            }
        }
        analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(this, "menu_item", s);
            return;
        }
    }

    public void newEventButtonClick(View view)
    {
        if (speedDialSupported)
        {
            view = createFabStack.getSpeedDial();
            if (view != null)
            {
                view.setExpanded(true, 0L);
                return;
            }
        }
        onCreateEventClicked();
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if (i == 1010)
        {
            CalendarHatsService.logSurveyTaken(this);
        }
    }

    public final void onAttachFragment(Fragment fragment)
    {
        super.onAttachFragment(fragment);
        if ((fragment instanceof MiniMonthInteractionController) && orientation != 0)
        {
            datePickerOpen = false;
            setDatePickerState((MiniMonthInteractionController)fragment);
        }
    }

    public final void onCalendarPropertyChanged(int i, Object obj)
    {
        i;
        JVM INSTR lookupswitch 2: default 28
    //                   0: 29
    //                   13: 42;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        timeZone = (String)obj;
        invalidateOptionsMenu();
        return;
_L3:
        configureDatePickerTextViews();
        setAlternateMonthLabel(startJulianDay, endJulianDay);
        if (datePickerOpen)
        {
            miniMonthListener.setDatePickerVisible(false, 0);
        }
        if (miniMonthController.isPresent())
        {
            ((MiniMonthController)miniMonthController.get()).onAlternateCalendarChanged();
            return;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        configuration = drawerToggle;
        ((ActionBarDrawerToggle) (configuration)).mActivityImpl.getThemeUpIndicator();
        configuration.syncState();
        preferencesManager.onConfigurationChanged();
    }

    protected void onCreate(Bundle bundle)
    {
        Trace.beginSection("OnCreateActivity");
        if (this != null)
        {
            break MISSING_BLOCK_LABEL_27;
        }
        throw new NullPointerException("activity");
        bundle;
        Trace.endSection();
        throw bundle;
        android.app.Application application = getApplication();
        if (!(application instanceof HasActivityInjector))
        {
            throw new RuntimeException(String.format("%s does not implement %s", new Object[] {
                application.getClass().getCanonicalName(), dagger/android/HasActivityInjector.getCanonicalName()
            }));
        }
        AndroidInjector androidinjector = ((HasActivityInjector)application).activityInjector();
        Preconditions.checkNotNull(androidinjector, "%s.activityInjector() returned null", application.getClass());
        androidinjector.inject(this);
        isTabletConfig = getResources().getBoolean(0x7f0c0016);
        isRtl = RtlUtils.isLayoutDirectionRtl(this);
        Bundle bundle1 = bundle;
        if (!AndroidPermissionUtils.hasMandatoryPermissions(this))
        {
            bundle1 = null;
        }
        setTheme(0x7f1400e4);
        super.onCreate(bundle1);
        bundle = (DataFactory)super.mFragments.mHost.mFragmentManager.findFragmentByTag("data_factory");
        if (bundle == null) goto _L2; else goto _L1
_L1:
        float f;
        Account aaccount[];
        Object obj;
        Object obj1;
        Object obj3;
        int i;
        int j;
        long l;
        boolean flag;
        if (((DataFactory) (bundle)).eventQueryHandler != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
          goto _L3
_L57:
        if (i == 0) goto _L5; else goto _L4
_L4:
        if (Utils.isClearTaskFlagSet(getIntent())) goto _L5; else goto _L6
_L6:
        j = 1;
_L62:
        launchScreenManager = new LaunchScreenManager(this);
        bundle = launchScreenManager;
        if (j == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        bundle.minTimeoutElapsed = flag;
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_273;
        }
        if (!((LaunchScreenManager) (bundle)).launchScreenIsDismissed)
        {
            ((LaunchScreenManager) (bundle)).activity.findViewById(0x1020002).getRootView().getViewTreeObserver().addOnPreDrawListener(LaunchScreenManager.drawPreventer);
        }
        bundle = Features.instance;
        if (bundle != null) goto _L8; else goto _L7
_L7:
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
_L56:
        if (((DataFactory) (bundle)).dataToday.numDays != 0) goto _L10; else goto _L9
_L9:
        bundle = null;
_L16:
        if (bundle == null) goto _L12; else goto _L11
_L11:
        if (!((MonthData) (bundle)).eventsReady) goto _L14; else goto _L13
_L13:
        if (((MonthData) (bundle)).currentMonth && !((MonthData) (bundle)).tasksReady)
        {
            i = 1;
        } else
        {
            i = 0;
        }
          goto _L15
_L10:
        bundle = ((DataFactory) (bundle)).dataToday;
          goto _L16
_L8:
        ((FeatureConfig)bundle).bugfood_build();
        CalendarUncaughtExceptionHandler.installHandler(getApplicationContext());
        if (AndroidPermissionUtils.hasMandatoryPermissions(this)) goto _L18; else goto _L17
_L17:
        Log.w("CalUtils", "Mandatory Permissions not granted. Redirecting to LaunchInfoActivity");
        bundle = new Intent();
        bundle.setClass(this, com/android/calendar/event/LaunchInfoActivity);
        startActivity(bundle);
        finish();
        i = 1;
_L20:
        if (i != 0)
        {
            Trace.endSection();
            return;
        }
        break; /* Loop/switch isn't completed */
_L18:
        i = 0;
        if (true) goto _L20; else goto _L19
_L19:
        LatencyLoggerHolder.get().markAt(12);
        bundle = PreferenceManager.getDefaultSharedPreferences(this);
        aaccount = AccountsUtil.getGoogleAccounts(this);
        j = aaccount.length;
        i = 0;
_L63:
        if (i >= j) goto _L22; else goto _L21
_L21:
        obj3 = aaccount[i];
        if (ContentResolver.isSyncActive(((Account) (obj3)), "com.android.calendar")) goto _L24; else goto _L23
_L23:
        flag = ContentResolver.isSyncPending(((Account) (obj3)), "com.android.calendar");
        if (Clock.mockedTimestamp <= 0L) goto _L26; else goto _L25
_L25:
        l = Clock.mockedTimestamp;
_L29:
        obj1 = PendingSyncTracker.getPerAccountPrefKey("pending_sync_num_", ((Account) (obj3)));
        obj3 = PendingSyncTracker.getPerAccountPrefKey("pending_sync_since_", ((Account) (obj3)));
        if (!flag) goto _L28; else goto _L27
_L27:
        int k = bundle.getInt(((String) (obj1)), 0);
        l = bundle.getLong(((String) (obj3)), l);
        bundle.edit().putInt(((String) (obj1)), k + 1).putLong(((String) (obj3)), l).apply();
          goto _L24
_L26:
        l = System.currentTimeMillis();
          goto _L29
_L28:
        bundle.edit().remove(((String) (obj1))).remove(((String) (obj3))).apply();
          goto _L24
_L22:
        if (bundle1 == null)
        {
            break MISSING_BLOCK_LABEL_635;
        }
        backgroundMonth = bundle1.getInt("key_saved_background_month", -1);
        if (bundle1 == null)
        {
            break MISSING_BLOCK_LABEL_2464;
        }
        if (!bundle1.getBoolean("key_restore_speed_dial", false))
        {
            break MISSING_BLOCK_LABEL_2464;
        }
        flag = true;
_L64:
        bundle = CalendarListEntryCache.instance;
        if (bundle != null)
        {
            break MISSING_BLOCK_LABEL_675;
        }
        throw new NullPointerException(String.valueOf("Not initialized"));
        class .Lambda._cls0
            implements Consumer
        {

            private final AllInOneCalendarActivity arg$1;
            private final boolean arg$2;

            public final void accept(Object obj4)
            {
                AllInOneCalendarActivity allinonecalendaractivity;
                int i1;
                int j1;
                boolean flag2;
                boolean flag3;
                flag2 = true;
                allinonecalendaractivity = arg$1;
                flag3 = arg$2;
                obj4 = (CalendarListEntry[])obj4;
                j1 = obj4.length;
                i1 = 0;
_L3:
                if (i1 >= j1)
                {
                    break MISSING_BLOCK_LABEL_119;
                }
                if (!AccountUtil.isGoogleAccount(obj4[i1].getDescriptor().account)) goto _L2; else goto _L1
_L1:
                boolean flag1 = true;
_L4:
                allinonecalendaractivity.speedDialSupported = flag1;
                if (!allinonecalendaractivity.receivedFirstCalendar)
                {
                    allinonecalendaractivity.receivedFirstCalendar = true;
                    if (allinonecalendaractivity.speedDialSupported && flag3)
                    {
                        flag1 = flag2;
                    } else
                    {
                        flag1 = false;
                    }
                    allinonecalendaractivity.restoreExpandedSpeedDial = flag1;
                }
                obj4 = Features.instance;
                if (obj4 == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                } else
                {
                    ((FeatureConfig)obj4).uss();
                    return;
                }
_L2:
                i1++;
                  goto _L3
                flag1 = false;
                  goto _L4
            }

            .Lambda._cls0(boolean flag)
            {
                arg$1 = AllInOneCalendarActivity.this;
                arg$2 = flag;
            }
        }

        calendarsSubscription = ((ListenableFutureCache)bundle).subscribe(new .Lambda._cls0(flag), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN), true);
        obj = UpgradeReceiver.UPGRADE_RECEIVERS.iterator();
_L31:
        if (!((Iterator) (obj)).hasNext())
        {
            break MISSING_BLOCK_LABEL_847;
        }
        obj1 = (Class)((Iterator) (obj)).next();
        bundle = (UpgradeReceiver)((Class) (obj1)).newInstance();
_L32:
        if (bundle == null) goto _L31; else goto _L30
_L30:
        bundle.tryUpgradeAndDisableReceiver(this);
          goto _L31
        bundle;
        LogUtils.e(UpgradeReceiver.TAG, bundle, "%s: unable to access class.", new Object[] {
            ((Class) (obj1)).getName()
        });
        bundle = null;
          goto _L32
        bundle;
        LogUtils.e(UpgradeReceiver.TAG, bundle, "%s: unable to create instance.", new Object[] {
            ((Class) (obj1)).getName()
        });
        bundle = null;
          goto _L32
        bundle;
        LogUtils.e(UpgradeReceiver.TAG, bundle, "%s: unable to cast class.", new Object[] {
            ((Class) (obj1)).getName()
        });
        bundle = null;
          goto _L32
        bundle = CalendarProperties.instance;
        if (bundle != null)
        {
            break MISSING_BLOCK_LABEL_869;
        }
        throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        ((CalendarProperties)bundle).registerListener(0, this);
        bundle = CalendarProperties.instance;
        if (bundle != null)
        {
            break MISSING_BLOCK_LABEL_900;
        }
        throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        ((CalendarProperties)bundle).registerListener(13, this);
        bundle = Features.instance;
        if (bundle != null)
        {
            break MISSING_BLOCK_LABEL_932;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        ((FeatureConfig)bundle).alternate_timeline();
        flag = RemoteFeatureConfig.ALTERNATE_TIMELINE.enabled();
        bundle = Features.instance;
        if (bundle != null)
        {
            break MISSING_BLOCK_LABEL_972;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        ((FeatureConfig)bundle).experimental_options();
        if (!flag)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L34; else goto _L33
_L33:
        obj = (DataFactory)super.mFragments.mHost.mFragmentManager.findFragmentByTag("data_factory");
        if (obj != null) goto _L36; else goto _L35
_L35:
        bundle = new DataFactory();
        bundle.initialize(getApplicationContext());
        super.mFragments.mHost.mFragmentManager.beginTransaction().add(bundle, "data_factory").commit();
_L40:
        class .Lambda._cls1
            implements com.google.android.calendar.timely.DataFactory.OnAllEventsDataReadyListener
        {

            private final AllInOneCalendarActivity arg$1;

            public final void onAllEventsDataReady()
            {
                DataFactory datafactory = (DataFactory)((FragmentActivity) (arg$1)).mFragments.mHost.mFragmentManager.findFragmentByTag("data_factory");
                if (datafactory == null)
                {
                    LogUtils.e("AllInOneCalendarAct", "DataFactory unavailable, cannot log analytics for AllDataReady.", new Object[0]);
                } else
                {
                    FragmentActivity fragmentactivity;
                    if (((Fragment) (datafactory)).mHost == null)
                    {
                        fragmentactivity = null;
                    } else
                    {
                        fragmentactivity = (FragmentActivity)((Fragment) (datafactory)).mHost.mActivity;
                    }
                    if (fragmentactivity == null)
                    {
                        LogUtils.e("AllInOneCalendarAct", "Unable to retrieve context from DataFactory for AllDataReady", new Object[0]);
                        return;
                    }
                    if (!AnalyticsUtils.haveLoggedAllDataReady)
                    {
                        AnalyticsUtils.haveLoggedAllDataReady = true;
                        FragmentActivity fragmentactivity1;
                        AnalyticsLogger analyticslogger;
                        if (((Fragment) (datafactory)).mHost == null)
                        {
                            fragmentactivity1 = null;
                        } else
                        {
                            fragmentactivity1 = (FragmentActivity)((Fragment) (datafactory)).mHost.mActivity;
                        }
                        analyticslogger = AnalyticsLoggerHolder.instance;
                        if (analyticslogger == null)
                        {
                            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                        }
                        analyticslogger = (AnalyticsLogger)analyticslogger;
                        HashSet hashset = new HashSet();
                        int j1 = Utils.getTodayJulianDay(fragmentactivity1);
label0:
                        for (int i1 = 0; i1 < 7; i1++)
                        {
                            int k1 = i1 + j1;
                            Object obj4 = datafactory.getDataAllowNull(k1);
                            if (obj4 == null)
                            {
                                AnalyticsUtils.haveLoggedAllDataReady = false;
                                return;
                            }
                            obj4 = ((MonthData) (obj4)).getItems(k1);
                            if (obj4 == null)
                            {
                                continue;
                            }
                            obj4 = ((List) (obj4)).iterator();
                            do
                            {
                                TimelineItem timelineitem;
                                do
                                {
                                    if (!((Iterator) (obj4)).hasNext())
                                    {
                                        continue label0;
                                    }
                                    timelineitem = (TimelineItem)((Iterator) (obj4)).next();
                                } while (timelineitem instanceof TimelineTaskType);
                                hashset.add(timelineitem);
                            } while (true);
                        }

                        analyticslogger.trackTiming(fragmentactivity1, "events_count", hashset.size() * 1000, "num_events", null);
                        return;
                    }
                }
            }

            .Lambda._cls1()
            {
                arg$1 = AllInOneCalendarActivity.this;
            }
        }

        obj = new .Lambda._cls1();
        EventRangedQueryHandler eventrangedqueryhandler = ((DataFactory) (bundle)).eventQueryHandler;
        if (eventrangedqueryhandler.allDataReadyListeners == null)
        {
            eventrangedqueryhandler.allDataReadyListeners = new ArrayList();
        }
        eventrangedqueryhandler.allDataReadyListeners.add(obj);
        obj = launchScreenManager;
        ((DataFactory) (bundle)).dataToday.viewUpdatePerformedListener = ((com.google.android.calendar.timely.RangedData.ViewUpdatePerformedListener) (obj));
_L34:
        bundle = feedbackReceiver;
        obj = new IntentFilter("com.google.android.calendar.intent.action.ACTION_SHOW_FEEDBACK");
        LocalBroadcastManager.getInstance(((FeedbackUtils.FeedbackBroadcastReceiver) (bundle)).activity).registerReceiver(bundle, ((IntentFilter) (obj)));
        controller = (CalendarController)CalendarController.instances.getInstance(this);
        timeZone = Utils.getTimeZoneId(this);
        timeZoneUpdateDialogLauncher = new TimeZoneUpdateDialogLauncher();
        bundle = getIntent();
        if (selectedJulianDayInMonthViewWidget != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L38; else goto _L37
_L37:
        l = Utils.getMillisFromJulianDay(selectedJulianDayInMonthViewWidget.intValue());
_L41:
        if (bundle1 == null || bundle == null)
        {
            break MISSING_BLOCK_LABEL_1453;
        }
        LogUtils.d("AllInOneCalendarAct", "both, icicle:%s  intent:%s", new Object[] {
            bundle1, bundle
        });
_L42:
        bundle = getResources();
        orientation = bundle.getConfiguration().orientation;
        cardLeftMargin = getResources().getDisplayMetrics().widthPixels / 2 - bundle.getDimensionPixelOffset(0x7f0e03a1);
        setContentView(0x7f050021);
        backgroundLayout = (FrameLayout)findViewById(0x7f100117);
        datePickerButton = getLayoutInflater().inflate(0x7f05001d, null);
        datePickerTextView = (TextView)datePickerButton.findViewById(0x7f100102);
        datePickerArrow = (ImageView)datePickerButton.findViewById(0x7f100104);
        miniMonthListener = new OnMiniMonthDatePickerListener();
        bundle = Features.instance;
        if (bundle != null)
        {
            break MISSING_BLOCK_LABEL_1478;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
_L36:
        class .Lambda._cls2
            implements Runnable
        {

            private final AllInOneCalendarActivity arg$1;

            public final void run()
            {
                Object obj4 = arg$1;
                RoomServiceStatusTask roomservicestatustask = new RoomServiceStatusTask();
                obj4 = ((Context) (obj4)).getApplicationContext();
                CalendarExecutor.NET.execute(new com.google.android.calendar.timely.rooms.RoomServiceStatusTask..Lambda._cls0(roomservicestatustask, ((Context) (obj4))));
            }

            .Lambda._cls2()
            {
                arg$1 = AllInOneCalendarActivity.this;
            }
        }

        class .Lambda._cls3
            implements Runnable
        {

            private final AllInOneCalendarActivity arg$1;

            public final void run()
            {
                BelongUtils.startActivityCheck(arg$1, false);
            }

            .Lambda._cls3()
            {
                arg$1 = AllInOneCalendarActivity.this;
            }
        }

        Object obj2;
        if (((DataFactory) (obj)).eventQueryHandler != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        bundle = ((Bundle) (obj));
        if (i != 0) goto _L40; else goto _L39
_L39:
        ((DataFactory) (obj)).initialize(getApplicationContext());
        bundle = ((Bundle) (obj));
          goto _L40
_L38:
        if (bundle1 == null)
        {
            break MISSING_BLOCK_LABEL_1444;
        }
        l = bundle1.getLong("key_restore_time");
          goto _L41
        l = Utils.timeFromIntentInMillis(bundle);
          goto _L41
        LogUtils.d("AllInOneCalendarAct", "not both, icicle:%s intent:%s", new Object[] {
            bundle1, bundle
        });
          goto _L42
        ((FeatureConfig)bundle).google_material();
        obj = datePickerTextView;
        if (Material.robotoMedium == null) goto _L44; else goto _L43
_L43:
        bundle = Material.robotoMedium;
_L51:
        ((TextView) (obj)).setTypeface(bundle);
        datePickerButton.setOnTouchListener(miniMonthListener);
        datePickerButton.setOnKeyListener(miniMonthListener);
        datePickerButton.setOnClickListener(miniMonthListener);
        datePickerAlternateMonthName = (TextView)datePickerButton.findViewById(0x7f100103);
        setCustomActionBar(datePickerButton);
        initializeActionBar(isTabletConfig);
        datePickerOpen = false;
        if (datePickerOpen)
        {
            f = 1.0F;
        } else
        {
            f = 0.0F;
        }
        setDatePickerArrow(false, f);
        configureDatePickerTextViews();
        controller.registerFirstHandler(0, this);
        initFragments(l);
        if (bundle1 != null)
        {
            break MISSING_BLOCK_LABEL_1623;
        }
        launchFragmentsFromIntent();
        getSharedPreferences("com.google.android.calendar_preferences", 0).registerOnSharedPreferenceChangeListener(this);
        configureDrawerLayout();
        bundle = getWindow().getDecorView();
        bundle.setSystemUiVisibility(bundle.getSystemUiVisibility() | 0x400 | 0x100);
        bundle = new SystemWindowInsetApplier();
        if (!isTabletConfig) goto _L46; else goto _L45
_L45:
        bundle.addView(findViewById(0x7f100118), 2, 2);
_L52:
        ViewCompat.setOnApplyWindowInsetsListener(drawerLayout, bundle);
        ((ViewGroup)findViewById(0x7f100122)).addView(new SyncOffNotification(this));
        Utils.schedulePeriodicSyncs(this);
        bundle = (SwipeRefreshLayout)findViewById(0x7f100114);
        bundle.setImportantForAccessibility(1);
        bundle.setEnabled(false);
        if (RefreshUiManager.instance == null)
        {
            RefreshUiManager.instance = new RefreshUiManager();
        }
        RefreshUiManager.instance.initialize(this, bundle);
        if (WhatsNewFactory.instance == null)
        {
            WhatsNewFactory.instance = new WhatsNewFactory();
        }
        obj = WhatsNewFactory.instance;
        if ((new DemoUserUtil(this)).isDemoUser()) goto _L48; else goto _L47
_L47:
        bundle = AccountsUtil.getGoogleAccounts(this);
        if (bundle.length <= 0) goto _L50; else goto _L49
_L49:
        ((WhatsNewFactory) (obj)).processAccountChanges(this, bundle);
_L48:
        new ConfidentialityDialog();
        bundle = CalendarExecutor.MAIN;
        obj = new .Lambda._cls2();
        obj2 = TimeUnit.MILLISECONDS;
        LogUtils.logOnFailure(bundle.getDelegate().schedule(((Runnable) (obj)), 3000L, ((TimeUnit) (obj2))), "AllInOneCalendarAct", "Room Service status refresh failed.", new Object[0]);
        bundle = CalendarExecutor.MAIN;
        obj = new .Lambda._cls3();
        obj2 = TimeUnit.MILLISECONDS;
        LogUtils.logOnFailure(bundle.getDelegate().schedule(((Runnable) (obj)), 5000L, ((TimeUnit) (obj2))), "AllInOneCalendarAct", "Belong activity check failed.", new Object[0]);
        bundle = Features.instance;
        if (bundle != null)
        {
            break MISSING_BLOCK_LABEL_2088;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
_L44:
        bundle = Typeface.create("sans-serif-medium", 0);
        Material.robotoMedium = bundle;
          goto _L51
_L46:
        obj = findViewById(0x7f100113);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_1989;
        }
        bundle.addView(((View) (obj)), 2, 2);
          goto _L52
        bundle.addView(findViewById(0x7f100115), 2, 2);
          goto _L52
_L50:
        bundle = AbstractTransformFuture.create(CalendarApi.CalendarList.list(null), com.google.android.apps.calendar.api.util.account.CalendarAccountsUtil..Lambda._cls0.$instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        obj = LogUtils.newFailureLoggingCallback(new com.google.android.calendar.launch.oobe.WhatsNewFactory..Lambda._cls0(((WhatsNewFactory) (obj)), this), WhatsNewFactory.TAG, "Unable to list calendars", new Object[0]);
        obj2 = CalendarExecutor.MAIN;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_2067;
        }
        throw new NullPointerException();
        bundle.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(bundle, ((FutureCallback) (obj))), ((java.util.concurrent.Executor) (obj2)));
          goto _L48
        NotificationChannels.initialize(this, ((FeatureConfig)bundle).dogfood_features());
        bundle = VisualElementHolder.instance;
        if (bundle != null)
        {
            break MISSING_BLOCK_LABEL_2123;
        }
        throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
        ((VisualElementAttacher)bundle).attachMainCalendarPage(this);
        bundle = growthKitCallback;
        if (RemoteFeatureConfig.GROWTH_KIT.enabled())
        {
            GrowthKit.get(this).getGrowthKitCallbacksManager().registerGrowthKitCallbacks(bundle);
        }
        if (bundle1 != null)
        {
            break MISSING_BLOCK_LABEL_2200;
        }
        bundle = AnalyticsLoggerHolder.instance;
        if (bundle != null)
        {
            break MISSING_BLOCK_LABEL_2188;
        }
        throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        ((AnalyticsLogger)bundle).logClearcutEvent(CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType.ACTIVITY_CREATE);
        bundle = findViewById(0x7f100391);
        obj = Features.instance;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_2232;
        }
        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        ((FeatureConfig)obj).dogfood_features();
        bundle.setVisibility(8);
        if (bundle1 == null) goto _L54; else goto _L53
_L53:
        bundle = PerformanceMetricCollectorHolder.instance;
        if (bundle != null)
        {
            break MISSING_BLOCK_LABEL_2275;
        }
        throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        ((PerformanceMetricCollector)bundle).recordMemory("AllInOneCalendarActivity.Recreated");
_L55:
        Trace.endSection();
        return;
_L54:
        if (!hasBeenRestarted)
        {
            break MISSING_BLOCK_LABEL_2334;
        }
        bundle = PerformanceMetricCollectorHolder.instance;
        if (bundle != null)
        {
            break MISSING_BLOCK_LABEL_2319;
        }
        throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        ((PerformanceMetricCollector)bundle).recordMemory("AllInOneCalendarActivity.WarmStart");
        continue; /* Loop/switch isn't completed */
        hasBeenRestarted = true;
        bundle = PerformanceMetricCollectorHolder.instance;
        if (bundle != null)
        {
            break MISSING_BLOCK_LABEL_2360;
        }
        throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        ((PerformanceMetricCollector)bundle).recordMemory("AllInOneCalendarActivity.ColdStart");
        if (true) goto _L55; else goto _L3
_L3:
        if (i != 0) goto _L56; else goto _L2
_L2:
        i = 0;
_L60:
        if (i == 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
          goto _L57
_L15:
        if (i != 0) goto _L14; else goto _L58
_L58:
        i = 1;
_L61:
        if (i == 0) goto _L12; else goto _L59
_L59:
        i = 1;
          goto _L60
_L14:
        i = 0;
          goto _L61
_L12:
        i = 0;
          goto _L60
_L5:
        j = 0;
          goto _L62
_L24:
        i++;
          goto _L63
        flag = false;
          goto _L64
    }

    public final void onCreateEventClicked()
    {
        Object obj = NewEventTimeChangedListenerHolder.INSTANCE;
        long l = ((NewEventTimeChangedListenerHolder) (obj)).createNewEventTime;
        if (l != -1L)
        {
            ((NewEventTimeChangedListenerHolder) (obj)).setCreateNewEventTime(-1L);
        } else
        {
            l = EditHelper.constructDefaultStartTime(EditHelper.constructDefaultStartTimeWithoutCorrection(getCreateFabDay(), this));
        }
        obj = Utils.getExtraEventBundle(l, null, false);
        ((Bundle) (obj)).putString("createEditSource", "fab");
        onLaunchInsertOrEdit(((Bundle) (obj)));
    }

    public final void onCreateGrooveClicked()
    {
        if (this != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).trackEvent(this, "groove", "fab_create_groove_pressed", "", null);
        }
        Intent intent = new Intent(this, com/google/android/calendar/groove/CreateGrooveActivity);
        intent.setFlags(0x20030000);
        startActivity(intent);
    }

    public final void onCreateOooClicked()
    {
        Object obj = new Bundle();
        ((Bundle) (obj)).putLong("beginTime", EditHelper.constructDefaultStartTime(EditHelper.constructDefaultStartTimeWithoutCorrection(getCreateFabDay(), this)));
        ((Bundle) (obj)).putString("createEditSource", "fab");
        ((Bundle) (obj)).putString("title", getString(0x7f130370));
        ((Bundle) (obj)).putBoolean("out_of_office_event", true);
        onLaunchInsertOrEdit(((Bundle) (obj)));
        obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)obj).trackEvent(this, "ooo", "fab_create_ooo_pressed");
            return;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        return createAllInOneMenu(menu, timeZone);
    }

    public final void onCreateTaskClicked()
    {
        Object obj = NewEventTimeChangedListenerHolder.INSTANCE;
        long l = ((NewEventTimeChangedListenerHolder) (obj)).createNewEventTime;
        if (l != -1L)
        {
            ((NewEventTimeChangedListenerHolder) (obj)).setCreateNewEventTime(-1L);
        } else
        {
            l = EditHelper.constructDefaultStartTimeWithoutCorrection(getCreateFabDay(), this);
        }
        obj = Utils.getExtraEventBundle(l, null, false);
        ((Bundle) (obj)).putString("createEditSource", "fab");
        onLaunchInsertTask(((Bundle) (obj)));
    }

    protected void onDestroy()
    {
        Object obj = PerformanceMetricCollectorHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        }
        ((PerformanceMetricCollector)obj).recordMemory("AllInOneCalendarActivity.Destroyed");
        super.onDestroy();
        boolean flag;
        if (VolleyQueueHolder.requestQueue != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            obj = VolleyQueueHolder.requestQueue;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("VolleyQueueHolder#initialize(...) must be called first"));
            }
            ((RequestQueue)obj).cancelAll("calendar_volley_request");
        }
        if (!AndroidPermissionUtils.hasMandatoryPermissions(this))
        {
            return;
        }
        getSharedPreferences("com.google.android.calendar_preferences", 0).unregisterOnSharedPreferenceChangeListener(this);
        obj = CalendarProperties.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        }
        ((CalendarProperties)obj).unregisterListener(0, this);
        obj = CalendarProperties.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        }
        ((CalendarProperties)obj).unregisterListener(13, this);
        Object obj1 = controller;
        obj1;
        JVM INSTR monitorenter ;
        if (((CalendarController) (obj1)).dispatchInProgressCounter <= 0)
        {
            break MISSING_BLOCK_LABEL_332;
        }
        ((CalendarController) (obj1)).toBeRemovedHandlers.addAll(((CalendarController) (obj1)).handlers.keySet());
_L1:
        Object obj2;
        SpeedDialLayout speeddiallayout;
        calendarsSubscription.cancel(false);
        receivedFirstCalendar = false;
        obj1 = feedbackReceiver;
        LocalBroadcastManager.getInstance(((FeedbackUtils.FeedbackBroadcastReceiver) (obj1)).activity).unregisterReceiver(((android.content.BroadcastReceiver) (obj1)));
        if (RemoteFeatureConfig.GROWTH_KIT.enabled())
        {
            GrowthKit.get(this).getGrowthKitCallbacksManager().unregisterGrowthKitCallbacks();
        }
        obj2 = createFabStack;
        obj1 = ((MainFabStack) (obj2))._fld0;
        speeddiallayout = ((MainFabStack) (obj2)).speedDialLayout;
        if (speeddiallayout == null || !ViewCompat.isAttachedToWindow(speeddiallayout))
        {
            break MISSING_BLOCK_LABEL_436;
        }
        if (FullScreenManager.fullScreenManager != null)
        {
            obj1 = FullScreenManager.fullScreenManager;
        } else
        {
            obj1 = new FullScreenManager(((Activity) (obj1)));
            FullScreenManager.fullScreenManager = ((FullScreenManager) (obj1));
        }
        if (((FullScreenManager) (obj1)).windowManager != null)
        {
            ((FullScreenManager) (obj1)).windowManager.removeViewImmediate(speeddiallayout);
        }
        obj1 = ((FullScreenManager) (obj1)).systemWindowInsetApplier;
        if (speeddiallayout == null)
        {
            throw new NullPointerException();
        }
        break MISSING_BLOCK_LABEL_368;
        ((CalendarController) (obj1)).handlers.clear();
        obj1.firstHandler = null;
          goto _L1
        obj2;
        obj1;
        JVM INSTR monitorexit ;
        throw obj2;
        if (((SystemWindowInsetApplier) (obj1)).customHandledViews.remove(speeddiallayout) == null)
        {
            com.google.android.calendar.utils.SystemWindowInsetApplier.InsetSensitiveViewRegistration insetsensitiveviewregistration = (com.google.android.calendar.utils.SystemWindowInsetApplier.InsetSensitiveViewRegistration)((SystemWindowInsetApplier) (obj1)).views.remove(speeddiallayout);
            if (insetsensitiveviewregistration == null)
            {
                LogUtils.wtf(SystemWindowInsetApplier.TAG, "Could not remove view %s in removeView. Items in mViews: %s", new Object[] {
                    speeddiallayout, ((SystemWindowInsetApplier) (obj1)).views.keySet()
                });
            } else
            if (insetsensitiveviewregistration.applicationStyle == 1)
            {
                SystemWindowInsetApplier.applyMarginToView(insetsensitiveviewregistration, insetsensitiveviewregistration.oldLeftSpacing, insetsensitiveviewregistration.oldTopSpacing, insetsensitiveviewregistration.oldRightSpacing, insetsensitiveviewregistration.oldBottomSpacing);
            } else
            {
                SystemWindowInsetApplier.applyPaddingToView(insetsensitiveviewregistration, insetsensitiveviewregistration.oldLeftSpacing, insetsensitiveviewregistration.oldTopSpacing, insetsensitiveviewregistration.oldRightSpacing, insetsensitiveviewregistration.oldBottomSpacing);
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(speeddiallayout, null);
        obj2.speedDialLayout = null;
        return;
    }

    public final void onDrawerItemClicked(int i)
    {
        pendingDrawerActionId = i;
        if (drawerLayout != null)
        {
            drawerLayout.closeDrawers(false);
        }
    }

    public final void onInfoBack(DialogFragment dialogfragment, boolean flag)
    {
        onBackPressed();
        dismissOverlay((OverlayFragment)dialogfragment, false);
    }

    public final void onInfoCancel(DialogFragment dialogfragment, boolean flag)
    {
        dismissOverlay((OverlayFragment)dialogfragment, flag);
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        i;
        JVM INSTR lookupswitch 5: default 52
    //                   4: 188
    //                   41: 127
    //                   42: 59
    //                   55: 114
    //                   76: 162;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return super.onKeyUp(i, keyevent);
_L4:
        if (keyevent.isCtrlPressed())
        {
            keyevent = createFabStack;
            if (((CreateFabFragment.CreateFabStack) (keyevent)).stack.empty())
            {
                keyevent = keyevent.findCreateFab();
            } else
            {
                keyevent = ((CreateFabFragment.CreateFabStack.Scope)((CreateFabFragment.CreateFabStack) (keyevent)).stack.peek()).createFab;
            }
            if (keyevent != null)
            {
                keyevent.performClick();
            }
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (keyevent.isCtrlPressed())
        {
            CalendarController.launchSettings(this);
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (keyevent.isCtrlPressed())
        {
            class .Lambda._cls7
                implements Runnable
            {

                private final AllInOneCalendarActivity arg$1;

                public final void run()
                {
                    AllInOneCalendarActivity allinonecalendaractivity = arg$1;
                    DrawerLayout drawerlayout1 = allinonecalendaractivity.drawerLayout;
                    View view1 = drawerlayout1.findDrawerWithGravity(3);
                    boolean flag1;
                    if (view1 != null)
                    {
                        flag1 = drawerlayout1.isDrawerOpen(view1);
                    } else
                    {
                        flag1 = false;
                    }
                    if (flag1)
                    {
                        allinonecalendaractivity.drawerLayout.closeDrawer(3);
                        return;
                    } else
                    {
                        allinonecalendaractivity.drawerLayout.openDrawer(3);
                        return;
                    }
                }

            .Lambda._cls7()
            {
                arg$1 = AllInOneCalendarActivity.this;
            }
            }

            if (drawerLayout != null)
            {
                (new Handler()).post(new .Lambda._cls7());
            }
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if (keyevent.isCtrlPressed())
        {
            GoogleFeedbackUtils.launchHelpAndFeedback(this, getString(0x7f13015a), Integer.valueOf(0x7f100114));
            return true;
        }
        if (true) goto _L1; else goto _L2
_L2:
        if (drawerLayout != null)
        {
            DrawerLayout drawerlayout = drawerLayout;
            View view = drawerlayout.findDrawerWithGravity(3);
            boolean flag;
            if (view != null)
            {
                flag = drawerlayout.isDrawerOpen(view);
            } else
            {
                flag = false;
            }
            if (flag)
            {
                drawerLayout.closeDrawer(3);
                return true;
            }
        }
        if (datePickerOpen)
        {
            miniMonthListener.toggleDatePicker();
            return true;
        }
        if (getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preference_key_back_to_month", false))
        {
            switchView(0x7f100027, false);
            return true;
        }
        if (true) goto _L1; else goto _L7
_L7:
    }

    public final void onLaunchDayDetails(int i, int j, int k)
    {
        if (isTabletConfig)
        {
            FeatureConfig featureconfig = Features.instance;
            if (featureconfig == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            ((FeatureConfig)featureconfig).alternate_timeline();
            boolean flag1 = RemoteFeatureConfig.ALTERNATE_TIMELINE.enabled();
            featureconfig = Features.instance;
            if (featureconfig == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            ((FeatureConfig)featureconfig).experimental_options();
            boolean flag;
            if (!flag1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                DayPopUpFragment daypopupfragment = new DayPopUpFragment();
                daypopupfragment.setArguments(DayPopUpFragment.createArguments(i, j, k));
                String s = DayPopUpFragment.TAG;
                if (paused)
                {
                    newIntentFragment = new Pair(s, daypopupfragment);
                    return;
                } else
                {
                    newIntentFragment = null;
                    showOverlayFragment(s, daypopupfragment);
                    return;
                }
            }
        }
        Time time = new Time(timeZone);
        time.writeFieldsToImpl();
        time.impl.set(k, j, i);
        time.copyFieldsFromImpl();
        time.normalizeSafe();
        showDayOnScheduleOrDayView(time);
    }

    public final void onLaunchDetails(TimelineItem timelineitem, EventInfoAnimationData eventinfoanimationdata)
    {
        if (paused)
        {
            return;
        }
        if (TimelineItemUtil.isReminderBundle(timelineitem))
        {
            TaskBundleFragment taskbundlefragment = new TaskBundleFragment();
            taskbundlefragment.setArguments(TaskBundleFragment.createArguments((TimelineTaskBundle)timelineitem, eventinfoanimationdata));
            showOverlayFragment(TaskBundleFragment.TAG, taskbundlefragment);
            timelineitem = AnalyticsLoggerHolder.instance;
            if (timelineitem == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            } else
            {
                ((AnalyticsLogger)timelineitem).trackEvent(this, "view_event", "reminder_bundle");
                return;
            }
        } else
        {
            LatencyLoggerHolder.get().markAt(3);
            launchInfoBubble(timelineitem, eventinfoanimationdata, null);
            return;
        }
    }

    public final void onLaunchInsertOrEdit(Bundle bundle)
    {
        ListenableFutureCache listenablefuturecache = CalendarListEntryCache.instance;
        if (listenablefuturecache == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        CalendarListEntry acalendarlistentry[] = (CalendarListEntry[])((ListenableFutureCache)listenablefuturecache).result;
        boolean flag;
        if (acalendarlistentry != null && acalendarlistentry.length > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            Object obj;
            if (android.os.Build.VERSION.SDK_INT >= 25)
            {
                obj = (ShortcutManager)getSystemService(android/content/pm/ShortcutManager);
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                obj = new Present(obj);
            } else
            {
                obj = Absent.INSTANCE;
            }
            if (((Optional) (obj)).isPresent() && android.os.Build.VERSION.SDK_INT >= 25)
            {
                ((ShortcutManager)((Optional) (obj)).get()).reportShortcutUsed("launcher_shortcuts_shortcut_new_event");
            }
            HostDialog.showWithChildFragment(this, super.mFragments.mHost.mFragmentManager, EventEditScreenController.createEvent(bundle));
            bundle = createFabStack.getHideAnimatorCreateFab();
            if (bundle != null)
            {
                bundle.start();
            }
            return;
        } else
        {
            Toast.makeText(this, 0x7f1301ac, 1).show();
            return;
        }
    }

    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        shouldCloseOverlays = Utils.hasSourceMonthWidget(intent);
        onSaveInstanceStateCalled = false;
        timeZoneUpdateDialogLauncher.onSaveInstanceStateCalled = false;
        String s = intent.getAction();
        LogUtils.d("AllInOneCalendarAct", "New intent received %s with action %s", new Object[] {
            intent, s
        });
        setIntent(intent);
        controller.registerFirstHandler(0, this);
        launchFragmentsFromIntent();
        if ("android.intent.action.VIEW".equals(s) && intent.getType() == null && !intent.getBooleanExtra("KEY_HOME", false))
        {
            long l = Utils.timeFromIntentInMillis(intent);
            if (l != -1L && controller != null)
            {
                intent = new Time(timeZone);
                ((Time) (intent)).impl.timezone = ((Time) (intent)).timezone;
                ((Time) (intent)).impl.set(l);
                ((Time) (intent)).impl.toMillis(true);
                intent.copyFieldsFromImpl();
                intent.writeFieldsToImpl();
                ((Time) (intent)).impl.normalize(true);
                intent.copyFieldsFromImpl();
                controller.goTo(this, intent, 2L);
            }
        }
    }

    final void onNewRange(Range range)
    {
        if (currentCalendarFragment != null)
        {
            if (miniMonthInteractionController == null || !miniMonthInteractionController.isMiniMonthVisible())
            {
                Time time = new Time(timeZone);
                time.setJulianDaySafe(((Integer)range.lowerBound.endpoint()).intValue());
                Object obj = new Time(timeZone);
                ((Time) (obj)).setJulianDaySafe(((Integer)range.upperBound.endpoint()).intValue());
                int i;
                boolean flag;
                if (isTabletConfig && currentCalendarFragment.getViewType() == com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType.WEEK)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                i = DateTimeFormatHelper.getToolbarFormatFlags(isTabletConfig, Utils.isCurrentYear(this, ((Integer)range.lowerBound.endpoint()).intValue()));
                controller.updateVisibleRange(this, time, ((Time) (obj)), null, flag, i);
                if (AlternateCalendarUtils.isAlternateCalendarEnabled(this))
                {
                    if (getSelectedViewId() == 0x7f100004 || getSelectedViewId() == 0x7f100022)
                    {
                        obj = controller;
                        CalendarController.Command command = new CalendarController.Command(8192L);
                        command.startTime = time;
                        command.endTime = time;
                        ((CalendarController) (obj)).executeCommand$5166KOBMC4NMOOBECSNKUOJACLHN8EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UGR1DHIMSP31E91MURJKE9NMOR35E8I46RRDDLGMSP1R55B0____0(command);
                    } else
                    {
                        CalendarController calendarcontroller = controller;
                        CalendarController.Command command1 = new CalendarController.Command(8192L);
                        command1.startTime = time;
                        command1.endTime = ((Time) (obj));
                        calendarcontroller.executeCommand$5166KOBMC4NMOOBECSNKUOJACLHN8EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UGR1DHIMSP31E91MURJKE9NMOR35E8I46RRDDLGMSP1R55B0____0(command1);
                    }
                }
            }
            if (miniMonthInteractionController != null && !miniMonthInteractionController.isMiniMonthVisible())
            {
                miniMonthInteractionController.pointTo(((Integer)range.lowerBound.endpoint()).intValue());
                return;
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        int i = menuitem.getItemId();
        logMenuItemSelected(i);
        ActionBarDrawerToggle actionbardrawertoggle = drawerToggle;
        boolean flag;
        if (menuitem != null && menuitem.getItemId() == 0x102002c && actionbardrawertoggle.mDrawerIndicatorEnabled)
        {
            actionbardrawertoggle.toggle();
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            if (i == 0x7f100426)
            {
                CreateNewEventView.removeSelectedTime();
                (new _cls5()).execute(new Void[0]);
                return true;
            }
            if (i == 0x7f100425)
            {
                menuitem = Features.instance;
                if (menuitem == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                }
                ((FeatureConfig)menuitem).experimental_options();
                menuitem = new Time(timeZone);
                long l;
                if (Clock.mockedTimestamp > 0L)
                {
                    l = Clock.mockedTimestamp;
                } else
                {
                    l = System.currentTimeMillis();
                }
                ((Time) (menuitem)).impl.timezone = ((Time) (menuitem)).timezone;
                ((Time) (menuitem)).impl.set(l);
                ((Time) (menuitem)).impl.toMillis(true);
                menuitem.copyFieldsFromImpl();
                controller.goTo(this, menuitem, 8L);
                return true;
            }
        }
        return true;
    }

    protected void onPause()
    {
        super.onPause();
        if (Utils.isCalendarStorageEnabled(this))
        {
            LaunchScreenManager launchscreenmanager = launchScreenManager;
            launchscreenmanager.forceDismiss = true;
            launchscreenmanager.hideLaunchScreen(false);
            controller.deregisterHandler(Integer.valueOf(0));
            paused = true;
            getContentResolver().unregisterContentObserver(observer);
            if (isFinishing())
            {
                getSharedPreferences("com.google.android.calendar_preferences", 0).unregisterOnSharedPreferenceChangeListener(this);
            }
            Utils.resetMidnightUpdater(handler, timeChangesUpdater);
            if (timeChangeSubscription != null)
            {
                timeChangeSubscription.cancel(false);
                timeChangeSubscription = null;
                return;
            }
        }
    }

    protected void onPostCreate(Bundle bundle)
    {
        Trace.beginSection("OnPostCreateActivity");
        super.onPostCreate(bundle);
        drawerToggle.syncState();
        Trace.endSection();
    }

    public void onRestoreInstanceState(Bundle bundle)
    {
        super.onRestoreInstanceState(bundle);
        super.overlayFragmentTag = bundle.getString("overlay_fragment");
    }

    protected void onResume()
    {
        Trace.beginSection("onResume");
        super.onResume();
        if (Utils.isCalendarStorageEnabled(this)) goto _L2; else goto _L1
_L1:
        Log.w("CalUtils", "Calendar Storage is disabled. Redirecting to LaunchInfoActivity");
        Intent intent = new Intent();
        intent.setClass(this, com/android/calendar/event/LaunchInfoActivity);
        startActivity(intent);
        finish();
        boolean flag = true;
_L4:
        if (flag)
        {
            Trace.endSection();
            return;
        }
        break; /* Loop/switch isn't completed */
_L2:
        flag = false;
        if (true) goto _L4; else goto _L3
_L3:
        Object obj;
        if (getSelectedViewId() != 0x7f100004);
        obj = launchScreenManager;
        if (!((LaunchScreenManager) (obj)).launchScreenIsDismissed && !((LaunchScreenManager) (obj)).launchScreenIsConfigured) goto _L6; else goto _L5
_L5:
        if (!isTabletConfig) goto _L8; else goto _L7
_L7:
        if (currentCalendarFragment != null)
        {
            AlternateTimelineHolderManager.adjustHolder((ViewGroup)findViewById(0x7f100119), currentCalendarFragment.getViewType());
        }
        class .Lambda._cls6
            implements Runnable
        {

            private final AllInOneCalendarActivity arg$1;

            public final void run()
            {
                SpeedDialLayout speeddiallayout = arg$1.createFabStack.getSpeedDial();
                if (speeddiallayout != null)
                {
                    speeddiallayout.setExpanded(true, 0L);
                }
            }

            .Lambda._cls6()
            {
                arg$1 = AllInOneCalendarActivity.this;
            }
        }

        Object obj1;
        Object obj2;
        ViewTreeObserver viewtreeobserver;
        int i;
        long l;
        long l1;
        long l2;
        boolean flag1;
        if (!getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preference_showMoreEvents", false))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L10; else goto _L9
_L9:
        if (backgroundImagesFrame != null)
        {
            backgroundLayout.removeView(backgroundImagesFrame);
            backgroundImagesFrame = null;
        }
_L8:
        controller.registerFirstHandler(0, this);
        onSaveInstanceStateCalled = false;
        timeZoneUpdateDialogLauncher.onSaveInstanceStateCalled = false;
        getContentResolver().registerContentObserver(android.provider.CalendarContract.Events.CONTENT_URI, true, observer);
        eventsChangedSubscription = null;
        if (updateOnResume)
        {
            obj = controller.time;
            ((Time) (obj)).writeFieldsToImpl();
            initFragments(((Time) (obj)).impl.toMillis(false));
            updateOnResume = false;
        }
        paused = false;
        if (newIntentFragment != null)
        {
            showOverlayFragment((String)newIntentFragment.first, (OverlayFragment)newIntentFragment.second);
            newIntentFragment = null;
        }
        Utils.setMidnightUpdater(handler, timeChangesUpdater, timeZone);
        invalidateOptionsMenu();
        configureDrawerLayout();
        timeChangeSubscription = TimeUtils.subscribeToTimeChanges(this, timeChangesUpdater);
        if (!TextUtils.isEmpty(super.overlayFragmentTag)) goto _L12; else goto _L11
_L11:
        if (!TextUtils.isEmpty(super.overlayFragmentTag)) goto _L14; else goto _L13
_L13:
        obj = null;
_L49:
        if ((obj instanceof CreateFabFragment) || findOverlayFragment(new String[] {
            "ViewScreenController"
        }) != null || findOverlayFragment(new String[] {
            "HostDialog"
        }) != null) goto _L12; else goto _L15
_L15:
        i = 1;
_L70:
        if (i != 0) goto _L17; else goto _L16
_L16:
        obj = createFabStack;
        if (!((CreateFabFragment.CreateFabStack) (obj)).stack.empty()) goto _L19; else goto _L18
_L18:
        obj = ((CreateFabFragment.CreateFabStack) (obj)).findCreateFab();
_L50:
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_468;
        }
        if ((double)((View) (obj)).getTranslationY() == 0.0D)
        {
            obj = createFabStack;
            obj.getClass();
            class .Lambda._cls5
                implements Runnable
            {

                private final MainFabStack arg$1;

                public final void run()
                {
                    Object obj3 = arg$1;
                    if (((CreateFabFragment.CreateFabStack) (obj3)).stack.empty())
                    {
                        obj3 = ((CreateFabFragment.CreateFabStack) (obj3)).findCreateFab();
                    } else
                    {
                        obj3 = ((CreateFabFragment.CreateFabStack.Scope)((CreateFabFragment.CreateFabStack) (obj3)).stack.peek()).createFab;
                    }
                    if (obj3 != null)
                    {
                        ((View) (obj3)).setTranslationY(((View)((View) (obj3)).getParent()).getHeight() - ((View) (obj3)).getTop());
                    }
                }

            .Lambda._cls5(MainFabStack mainfabstack)
            {
                arg$1 = mainfabstack;
            }
            }

            runOnGlobalLayout(new .Lambda._cls5(((MainFabStack) (obj))));
        }
_L52:
        if (!speedDialSupported) goto _L21; else goto _L20
_L20:
        obj1 = createFabStack;
        obj2 = getString(0x7f130054);
        if (!((CreateFabFragment.CreateFabStack) (obj1)).stack.empty()) goto _L23; else goto _L22
_L22:
        obj = ((CreateFabFragment.CreateFabStack) (obj1)).findCreateFab();
_L55:
        if (obj == null) goto _L21; else goto _L24
_L24:
        viewtreeobserver = ((View) (obj)).getViewTreeObserver();
        if (viewtreeobserver == null) goto _L21; else goto _L25
_L25:
        viewtreeobserver.addOnGlobalLayoutListener(new CreateFabFragment.CreateFabStack._cls1(((CreateFabFragment.CreateFabStack) (obj1)), ((View) (obj)), ((CharSequence) (obj2))));
_L21:
        obj = timeZoneUpdateDialogLauncher;
        if (!((TimeZoneUpdateDialogLauncher) (obj)).inProgress && super.mFragments.mHost.mFragmentManager.findFragmentByTag(com.google.android.calendar.groove.TimeZoneUpdateDialogLauncher.TimeZoneUpdateDialog.TAG) == null) goto _L27; else goto _L26
_L26:
        LogUtils.d(TimeZoneUpdateDialogLauncher.TAG, "Launcher in progress or dialog shown. Exiting.", new Object[0]);
_L56:
        if (!shouldCloseOverlays) goto _L29; else goto _L28
_L28:
        shouldCloseOverlays = false;
        if (findOverlayFragment(new String[] {
            "HostDialog"
        }) != null) goto _L29; else goto _L30
_L30:
        obj1 = findOverlayFragment(new String[] {
            "ViewScreenController"
        });
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_1886;
        }
        obj2 = ((Fragment) (obj1)).mFragmentManager;
        if (obj1 == null) goto _L32; else goto _L31
_L31:
        if (((Fragment) (obj1)).mHost == null) goto _L34; else goto _L33
_L33:
        flag1 = ((Fragment) (obj1)).mAdded;
        if (!flag1) goto _L34; else goto _L35
_L35:
        i = 1;
_L71:
        if (i != 0) goto _L36; else goto _L32
_L32:
        i = 0;
_L64:
        if (i == 0) goto _L29; else goto _L37
_L37:
        try
        {
            ((DialogFragment) (obj1)).dismissAllowingStateLoss();
        }
        catch (IllegalStateException illegalstateexception) { }
_L29:
        if (areAnyBottomSheetsShown()) goto _L39; else goto _L38
_L38:
        obj1 = hatsService;
        if (!ExperimentConfiguration.HATS_WEEKS.isActive(this)) goto _L41; else goto _L40
_L40:
        l1 = getSharedPreferences("com.google.android.calendar_preferences", 0).getLong("dasher_status_expires", 0L);
        if (Clock.mockedTimestamp <= 0L) goto _L43; else goto _L42
_L42:
        l = Clock.mockedTimestamp;
          goto _L44
_L69:
        if (i == 0) goto _L46; else goto _L45
_L45:
        obj = (FluentFuture)CalendarExecutor.BACKGROUND.submit(new com.google.android.calendar.hats.DasherStatusHelper..Lambda._cls0(this));
        obj1 = LogUtils.newFailureLoggingCallback(new com.google.android.calendar.hats.CalendarHatsService..Lambda._cls0(((CalendarHatsService) (obj1)), this), CalendarHatsService.TAG, "Failed to determine whether the user is a dasher.", new Object[0]);
        obj2 = CalendarExecutor.MAIN;
        if (obj1 != null) goto _L48; else goto _L47
_L47:
        throw new NullPointerException();
        obj;
        Trace.endSection();
        throw obj;
_L6:
        obj.launchScreenIsConfigured = true;
        (new Handler()).postDelayed(new LaunchScreenManager..Lambda._cls0(((LaunchScreenManager) (obj))), 500L);
        (new Handler()).postDelayed(new LaunchScreenManager..Lambda._cls1(((LaunchScreenManager) (obj))), 2000L);
          goto _L5
_L10:
label0:
        {
            if (backgroundImagesFrame == null)
            {
                getLayoutInflater().inflate(0x7f050027, backgroundLayout);
                backgroundImagesFrame = (BackgroundImagesFrame)backgroundLayout.findViewById(0x7f10011f);
                obj = backgroundImagesFrame;
                obj1 = ((BackgroundImagesFrame) (obj)).getResources();
                obj.orientation = ((Resources) (obj1)).getConfiguration().orientation;
                obj.cache = BitmapCacheHolder.getBackgroundImageCache();
                obj.primaryBackgroundImageView = (BackgroundImageView)((BackgroundImagesFrame) (obj)).findViewById(0x7f100121);
                obj.secondaryBackgroundImageView = (BackgroundImageView)((BackgroundImagesFrame) (obj)).findViewById(0x7f100120);
                obj.backgroundMonth = -1;
                obj2 = getWindowManager().getDefaultDisplay();
                Point point = new Point();
                ((Display) (obj2)).getRealSize(point);
                obj.backgroundDrawableSize = Math.max(point.x, point.y);
                point = new Point();
                ((Display) (obj2)).getRealSize(point);
                i = Math.min(point.x, point.y);
                obj.backgroundOffsetFromLeftPortrait = ((BackgroundImagesFrame) (obj)).backgroundDrawableSize / 2 - i / 2;
                i = ((Resources) (obj1)).getDimensionPixelOffset(0x7f0e0441);
                obj.backgroundOffsetFromTopPortrait = ((BackgroundImagesFrame) (obj)).backgroundDrawableSize / 2 - i;
                i = ((Resources) (obj1)).getDimensionPixelOffset(0x7f0e0440);
                obj.backgroundOffsetFromTopLandscape = ((BackgroundImagesFrame) (obj)).backgroundDrawableSize / 2 - i;
            }
            if (backgroundMonth >= 0)
            {
                backgroundMonth = backgroundMonth;
                if (backgroundImagesFrame != null)
                {
                    obj = backgroundImagesFrame;
                    i = backgroundMonth;
                    int j = getSelectedViewId();
                    if (((BackgroundImagesFrame) (obj)).backgroundMonth != i || ((BackgroundImagesFrame) (obj)).selectedViewId != j)
                    {
                        if (((BackgroundImagesFrame) (obj)).selectedViewId != j)
                        {
                            ((BackgroundImagesFrame) (obj)).secondaryBackgroundImageView.setClippingTranslationY(0.0F);
                        }
                        obj.backgroundMonth = i;
                        obj.selectedViewId = j;
                        if (!((BackgroundImagesFrame) (obj)).doingFade)
                        {
                            break label0;
                        }
                        obj.pendingMonth = true;
                    }
                }
            }
        }
          goto _L8
        ((BackgroundImagesFrame) (obj)).loadSelectedMonth();
          goto _L8
_L14:
        obj = (OverlayFragment)super.mFragments.mHost.mFragmentManager.findFragmentByTag(super.overlayFragmentTag);
          goto _L49
_L19:
        obj = ((CreateFabFragment.CreateFabStack.Scope)((CreateFabFragment.CreateFabStack) (obj)).stack.peek()).createFab;
          goto _L50
_L17:
        if (!restoreExpandedSpeedDial) goto _L52; else goto _L51
_L51:
        restoreExpandedSpeedDial = false;
        obj = createFabStack;
        if (!((CreateFabFragment.CreateFabStack) (obj)).stack.empty())
        {
            break MISSING_BLOCK_LABEL_1281;
        }
        obj = ((CreateFabFragment.CreateFabStack) (obj)).findCreateFab();
_L54:
        if (obj == null) goto _L52; else goto _L53
_L53:
        runOnGlobalLayout(new .Lambda._cls6());
          goto _L52
        obj = ((CreateFabFragment.CreateFabStack.Scope)((CreateFabFragment.CreateFabStack) (obj)).stack.peek()).createFab;
          goto _L54
_L23:
        obj = ((CreateFabFragment.CreateFabStack.Scope)((CreateFabFragment.CreateFabStack) (obj1)).stack.peek()).createFab;
          goto _L55
_L27:
        obj.inProgress = true;
        obj1 = getSharedPreferences("com.google.android.calendar_preferences", 0).getString("preferences_last_display_tz", null);
        if (obj1 != null)
        {
            break MISSING_BLOCK_LABEL_1407;
        }
        LogUtils.d(TimeZoneUpdateDialogLauncher.TAG, "Last displayed timezone is null, setting to current.", new Object[0]);
        obj1 = Utils.getTimeZoneId(this);
        getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putString("preferences_last_display_tz", ((String) (obj1))).apply();
        (new BackupManager(this)).dataChanged();
        obj.inProgress = false;
          goto _L56
        obj2 = Utils.getTimeZoneId(this);
        if (Clock.mockedTimestamp <= 0L)
        {
            break MISSING_BLOCK_LABEL_1503;
        }
        l = Clock.mockedTimestamp;
_L57:
        l1 = TimeZone.getTimeZone(((String) (obj1))).getOffset(l);
        l2 = TimeZone.getTimeZone(((String) (obj2))).getOffset(l);
        LogUtils.d(TimeZoneUpdateDialogLauncher.TAG, "Last timezone: %s (%d), Current timezone: %s (%d)", new Object[] {
            obj1, Long.valueOf(l1), obj2, Long.valueOf(l2)
        });
        if (l1 != l2)
        {
            break MISSING_BLOCK_LABEL_1511;
        }
        obj.inProgress = false;
          goto _L56
        l = System.currentTimeMillis();
          goto _L57
        (new LoaderManagerImpl(this, ((ViewModelStoreOwner)this).getViewModelStore())).restartLoader(0, null, new com.google.android.calendar.groove.TimeZoneUpdateDialogLauncher._cls1(((TimeZoneUpdateDialogLauncher) (obj)), this, l, ((String) (obj2)), l2)).onForceLoad();
          goto _L56
_L36:
        if (((Fragment) (obj1)).mHost != null) goto _L59; else goto _L58
_L58:
        obj = null;
_L63:
        if (obj == null) goto _L61; else goto _L60
_L60:
        if (!((Activity) (obj)).isDestroyed() && !((Activity) (obj)).isFinishing()) goto _L62; else goto _L61
_L59:
        obj = (FragmentActivity)((Fragment) (obj1)).mHost.mActivity;
          goto _L63
_L62:
        if (obj2 == null)
        {
            break MISSING_BLOCK_LABEL_1886;
        }
        if (((FragmentManager) (obj2)).isDestroyed())
        {
            break MISSING_BLOCK_LABEL_1886;
        }
        i = 1;
          goto _L64
_L43:
        l = System.currentTimeMillis();
          goto _L44
_L48:
        ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj)), ((FutureCallback) (obj1))), ((java.util.concurrent.Executor) (obj2)));
_L41:
        obj = hatsService;
        obj1 = CalendarHatsService.getSiteId(getResources().getConfiguration().locale.getLanguage(), getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("dasher_status", false));
        if (obj1 != null) goto _L66; else goto _L65
_L65:
        flag1 = false;
_L68:
        if (!flag1) goto _L39; else goto _L67
_L67:
        if (SyncOffNotificationsManager.instance == null)
        {
            SyncOffNotificationsManager.instance = new SyncOffNotificationsManager(this);
        }
        SyncOffNotificationsManager.instance.shouldNotShow = flag1;
_L39:
        Trace.endSection();
        AnalyticsUtils.postAppOpenAnalytics(this, getIntent(), isTabletConfig);
        return;
_L46:
        ((CalendarHatsService) (obj1)).downloadSurveyIfSupported(this, getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("dasher_status", false));
          goto _L41
_L66:
        obj1 = (new com.google.android.libraries.hats20.HatsShowRequest.Builder(this)).forSiteId(((String) (obj1)));
        obj1.requestCode = Integer.valueOf(1010);
        if (((com.google.android.libraries.hats20.HatsShowRequest.Builder) (obj1)).siteId == null)
        {
            obj1.siteId = "-1";
        }
        obj1 = new HatsShowRequest(((com.google.android.libraries.hats20.HatsShowRequest.Builder) (obj1)));
        if (HatsModule.get().getHatsController().showSurveyIfAvailable(((HatsShowRequest) (obj1))))
        {
            if (!((CalendarHatsService) (obj)).wasSurveyShown)
            {
                CalendarHatsService.logEvent(this, "show");
            }
            obj.wasSurveyShown = true;
        }
        flag1 = ((CalendarHatsService) (obj)).wasSurveyShown;
          goto _L68
_L44:
        if (l1 < l)
        {
            i = 1;
        } else
        {
            i = 0;
        }
          goto _L69
_L12:
        i = 0;
          goto _L70
_L34:
        i = 0;
          goto _L71
_L61:
        i = 0;
          goto _L64
        i = 0;
          goto _L64
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        if (infoFragmentDismiss != null && infoFragmentDismiss.isStarted())
        {
            infoFragmentDismiss.cancel();
        }
        onSaveInstanceStateCalled = true;
        timeZoneUpdateDialogLauncher.onSaveInstanceStateCalled = true;
        super.onSaveInstanceState(bundle);
        Object obj = controller.time;
        ((Time) (obj)).writeFieldsToImpl();
        bundle.putLong("key_restore_time", ((Time) (obj)).impl.toMillis(false));
        obj = super.overlayFragmentTag;
        if (!TextUtils.isEmpty(((CharSequence) (obj))))
        {
            bundle.putString("overlay_fragment", ((String) (obj)));
        }
        if (speedDialSupported && createFabStack.isSpeedDialExpanded())
        {
            bundle.putBoolean("key_restore_speed_dial", true);
        }
        bundle.putInt("key_saved_background_month", backgroundMonth);
    }

    public boolean onSearchRequested()
    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setClass(this, com/google/android/calendar/SearchActivity);
        intent.setFlags(0x20030000);
        startActivity(intent);
        return true;
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedpreferences, String s)
    {
        if (s.equals("preferences_week_start_day"))
        {
            if (paused)
            {
                updateOnResume = true;
            } else
            {
                sharedpreferences = controller.time;
                sharedpreferences.writeFieldsToImpl();
                initFragments(((Time) (sharedpreferences)).impl.toMillis(false));
            }
        }
        if ("preference_key_last_view".equals(s) && drawerFragment != null)
        {
            sharedpreferences = drawerFragment;
            if (((DrawerFragment) (sharedpreferences)).adapter != null)
            {
                sharedpreferences = ((DrawerFragment) (sharedpreferences)).adapter;
                if (((DrawerListAdapter) (sharedpreferences)).viewSwitcherIds.size() != 0)
                {
                    sharedpreferences.selectedViewId = PreferencesUtils.getLastUsedView(((DrawerListAdapter) (sharedpreferences)).mContext, ((DrawerListAdapter) (sharedpreferences)).isTabletConfig);
                    sharedpreferences.notifyDataSetChanged();
                }
            }
        }
    }

    protected void onStop()
    {
        super.onStop();
        if (viewScreenFuture != null)
        {
            viewScreenFuture.cancel(true);
            viewScreenFuture = null;
        }
    }

    protected void onUserLeaveHint()
    {
        if (!AndroidPermissionUtils.hasMandatoryPermissions(this))
        {
            return;
        } else
        {
            controller.executeCommand$5166KOBMC4NMOOBECSNKUOJACLHN8EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UGR1DHIMSP31E91MURJKE9NMOR35E8I46RRDDLGMSP1R55B0____0(new CalendarController.Command(512L));
            super.onUserLeaveHint();
            return;
        }
    }

    public final void onWeekDividerTap()
    {
        Object obj;
        if (liftFabAnimator != null)
        {
            obj = liftFabAnimator;
        } else
        {
            obj = ObjectAnimator.ofPropertyValuesHolder(null, new PropertyValuesHolder[] {
                PropertyValuesHolder.ofFloat("scaleX", new float[] {
                    1.4F
                }), PropertyValuesHolder.ofFloat("scaleY", new float[] {
                    1.4F
                })
            });
            ((Animator) (obj)).setDuration(333L);
            ObjectAnimator objectanimator = ObjectAnimator.ofPropertyValuesHolder(null, new PropertyValuesHolder[] {
                PropertyValuesHolder.ofFloat("scaleX", new float[] {
                    1.0F
                }), PropertyValuesHolder.ofFloat("scaleY", new float[] {
                    1.0F
                })
            });
            objectanimator.setDuration(333L);
            obj1 = ObjectAnimator.ofInt(null, "level", new int[] {
                0, 10000
            });
            ((Animator) (obj1)).setStartDelay(83L);
            ((Animator) (obj1)).setDuration(500L);
            AnimatorSet animatorset = new AnimatorSet();
            animatorset.play(((Animator) (obj))).before(objectanimator);
            liftFabAnimatorButton = animatorset;
            liftFabAnimatorDrawable = ((Animator) (obj1));
            obj = new AnimatorSet();
            ((AnimatorSet) (obj)).play(animatorset).with(((Animator) (obj1)));
            ((AnimatorSet) (obj)).setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
            liftFabAnimator = ((Animator) (obj));
        }
        if (!((Animator) (obj)).isRunning())
        {
            Object obj1 = createFabStack;
            if (((CreateFabFragment.CreateFabStack) (obj1)).stack.empty())
            {
                obj1 = ((CreateFabFragment.CreateFabStack) (obj1)).findCreateFab();
            } else
            {
                obj1 = ((CreateFabFragment.CreateFabStack.Scope)((CreateFabFragment.CreateFabStack) (obj1)).stack.peek()).createFab;
            }
            obj1 = (ImageButton)obj1;
            liftFabAnimatorButton.setTarget(obj1);
            liftFabAnimatorDrawable.setTarget(((ImageButton) (obj1)).getDrawable());
            ((Animator) (obj)).start();
        }
    }

    public final void performDelayedAction(DelayedActionDescription delayedactiondescription)
    {
        DelayedActionPerformer delayedactionperformer = getDelayedActionPerformer();
        if (delayedactionperformer != null)
        {
            delayedactionperformer.performDelayedAction(delayedactiondescription);
            return;
        } else
        {
            LogUtils.e("AllInOneCalendarAct", "Failing to perform delayed action due to performer not found", new Object[0]);
            return;
        }
    }

    final void setDatePickerArrow(boolean flag, float f)
    {
        ImageView imageview = datePickerArrow;
        int i;
        if (flag)
        {
            i = 0;
        } else
        {
            i = 8;
        }
        imageview.setVisibility(i);
        f = Math.min(1.0F, Math.max(0.0F, f));
        if (flag)
        {
            datePickerArrow.setRotation(360F - f * 180F);
        }
    }

    final void setDatePickerContentDescription()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(datePickerTextView.getText()).append(", ");
        if (datePickerAlternateMonthName.getVisibility() == 0)
        {
            stringbuilder.append(datePickerAlternateMonthName.getText()).append(", ");
        }
        Resources resources = getResources();
        int i;
        if (datePickerOpen)
        {
            i = 0x7f130067;
        } else
        {
            i = 0x7f130072;
        }
        stringbuilder.append(resources.getString(i));
        datePickerButton.setContentDescription(stringbuilder);
    }

    public final boolean shouldDelayAction(DelayedActionDescription delayedactiondescription)
    {
        DelayedActionPerformer delayedactionperformer;
        if (delayedactiondescription.actionId == 0 && ((delayedactiondescription.timelineItem instanceof TimelineTask) || (delayedactiondescription.timelineItem instanceof TimelineGroove)))
        {
            if ((delayedactionperformer = getDelayedActionPerformer()) != null && delayedactionperformer.shouldDelayAction(delayedactiondescription))
            {
                return true;
            }
        }
        return false;
    }

    public final void showOverlayFragment(String s, OverlayFragment overlayfragment)
    {
        super.showOverlayFragment(s, overlayfragment);
        if (!(overlayfragment instanceof CreateFabFragment))
        {
            s = createFabStack.getHideAnimatorCreateFab();
            if (s != null)
            {
                s.start();
            }
        }
    }

    public final AndroidInjector supportFragmentInjector()
    {
        return dispatchingFragmentInjector;
    }

    public final void switchView(int i, boolean flag)
    {
        int j;
        byte byte0;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        byte0 = -1;
        j = 0;
        flag2 = false;
        flag3 = true;
        flag1 = true;
        if (!onSaveInstanceStateCalled) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Object obj;
        Object obj1;
        Object obj3;
        obj3 = super.mFragments.mHost.mFragmentManager;
        obj1 = super.mFragments.mHost.mFragmentManager;
        obj = ((FragmentManager) (obj1)).findFragmentById(0x7f100115);
        TimeZone timezone;
        if (obj == null)
        {
            obj = ((FragmentManager) (obj1)).findFragmentById(0x7f10011a);
        }
        if (currentCalendarFragment == null) goto _L4; else goto _L3
_L3:
        AnalyticsLogger analyticslogger;
        obj1 = AlternateTimelineUtils.viewIdToViewType(i);
        obj = currentCalendarFragment;
        timezone = TimeZone.getTimeZone(timeZone);
        obj3 = controller.time;
        ((Time) (obj3)).writeFieldsToImpl();
        j = TimeBoxUtil.msToJulianDay(timezone, ((Time) (obj3)).impl.toMillis(false));
        if (AlternateTimelineHolderManager.shouldShowInCard(this, currentCalendarFragment.getViewType()) == AlternateTimelineHolderManager.shouldShowInCard(this, ((com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType) (obj1))))
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        ((CalendarFragment) (obj)).onSwitchView(((com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType) (obj1)), j, flag3);
        obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        analyticslogger = (AnalyticsLogger)obj;
        ((com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType) (obj1)).ordinal();
        JVM INSTR tableswitch 0 4: default 240
    //                   0 284
    //                   1 369
    //                   2 376
    //                   3 383
    //                   4 390;
           goto _L5 _L6 _L7 _L8 _L9 _L10
_L5:
        obj = String.valueOf(obj1);
        throw new IllegalArgumentException((new StringBuilder(String.valueOf(obj).length() + 19)).append("Unknown view type: ").append(((String) (obj))).toString());
_L6:
        obj = "day_grid";
_L11:
        analyticslogger.trackView(this, ((String) (obj)));
        PreferencesUtils.setLastUsedView(this, isTabletConfig, i);
        PreferencesUtils.setLastUsedDayView(this, i);
        getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putBoolean("preference_key_back_to_month", flag).apply();
        (new BackupManager(this)).dataChanged();
        refreshMiniMonthState();
        AlternateTimelineHolderManager.adjustHolder((ViewGroup)findViewById(0x7f100119), ((com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType) (obj1)));
        return;
_L7:
        obj = "nDay";
        continue; /* Loop/switch isn't completed */
_L8:
        obj = "week";
        continue; /* Loop/switch isn't completed */
_L9:
        obj = "agenda";
        continue; /* Loop/switch isn't completed */
_L10:
        obj = "month";
        if (true) goto _L11; else goto _L4
_L4:
        if (isTabletConfig) goto _L13; else goto _L12
_L12:
        if (i != 0x7f100026) goto _L15; else goto _L14
_L14:
        if (obj instanceof ThreeDayViewFragment) goto _L1; else goto _L16
_L16:
        obj1 = new ThreeDayViewFragment();
        obj = controller.time;
        ((Time) (obj)).writeFieldsToImpl();
        ((Fragment) (obj1)).setArguments(ThreeDayViewFragment.createArgs(((Time) (obj)).impl.toMillis(false)));
        obj = "nDay";
        j = ((flag1) ? 1 : 0);
_L18:
        if (j == 0)
        {
            break MISSING_BLOCK_LABEL_543;
        }
        ((FragmentManager) (obj3)).beginTransaction().replace(0x7f100115, ((Fragment) (obj1))).commitAllowingStateLoss();
        controller.registerHandler(0x7f100115, (CalendarController.Command.Handler)obj1);
        selectedJulianDayInMonthViewWidget = null;
        getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putBoolean("preference_key_back_to_month", flag).apply();
        (new BackupManager(this)).dataChanged();
        PreferencesUtils.setLastUsedView(this, isTabletConfig, i);
        obj1 = AnalyticsLoggerHolder.instance;
        OnTimelineModeChangedListener ontimelinemodechangedlistener;
        TimelineRecyclerView timelinerecyclerview;
        if (obj1 == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)obj1).trackView(this, ((String) (obj)));
            return;
        }
_L15:
        if (i != 0x7f100027)
        {
            break MISSING_BLOCK_LABEL_638;
        }
        if (obj instanceof MonthFragment) goto _L1; else goto _L17
_L17:
        obj1 = new MonthFragment();
        obj = controller.time;
        ((Time) (obj)).writeFieldsToImpl();
        ((Fragment) (obj1)).setArguments(MonthFragment.createArgs(((Time) (obj)).impl.toMillis(false)));
        obj = "month";
        j = ((flag1) ? 1 : 0);
          goto _L18
        if (i != 0x7f100050)
        {
            break MISSING_BLOCK_LABEL_700;
        }
        if (obj instanceof WeekFragment) goto _L1; else goto _L19
_L19:
        obj1 = new WeekFragment();
        obj = controller.time;
        ((Time) (obj)).writeFieldsToImpl();
        ((Fragment) (obj1)).setArguments(WeekFragment.createArgs(((Time) (obj)).impl.toMillis(false)));
        obj = "week";
        j = ((flag1) ? 1 : 0);
          goto _L18
        if (obj instanceof DualTimelineGridFragment) goto _L21; else goto _L20
_L20:
        j = 0;
_L49:
        if (j != 0) goto _L23; else goto _L22
_L22:
        obj1 = new DualTimelineGridFragment();
        if (i == 0x7f100022)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        obj = controller.time;
        ((Time) (obj)).writeFieldsToImpl();
        ((Fragment) (obj1)).setArguments(DualTimelineGridFragment.createArgs(((Time) (obj)).impl.toMillis(false), flag3));
        if (flag) goto _L25; else goto _L24
_L24:
        j = ((flag2) ? 1 : 0);
        if (selectedJulianDayInMonthViewWidget != null)
        {
            j = 1;
        }
          goto _L26
_L25:
        ((DualTimelineGridFragment)obj1).forceShowInitialDay = true;
        break; /* Loop/switch isn't completed */
_L21:
        obj1 = (DualTimelineGridFragment)obj;
        if (((DualTimelineGridFragment) (obj1)).list != null && ((DualTimelineGridFragment) (obj1)).list.viewMode == 1)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0) goto _L28; else goto _L27
_L27:
        j = ((DualTimelineGridFragment) (obj1)).dayPager.getCurrentItem();
          goto _L29
_L28:
        obj1 = ((DualTimelineGridFragment) (obj1)).list;
        if (((TimelineRecyclerView) (obj1)).getChildCount() <= 0) goto _L31; else goto _L30
_L30:
        j = ((TimelineRecyclerView) (obj1)).layoutManager.findFirstVisibleItemPosition();
          goto _L29
_L23:
        obj1 = (DualTimelineGridFragment)obj;
        if (((DualTimelineGridFragment) (obj1)).list != null && ((DualTimelineGridFragment) (obj1)).list.viewMode == 1)
        {
            j = 1;
        } else
        {
            j = 0;
        }
          goto _L32
_L50:
        if (((DualTimelineGridFragment) (obj1)).list != null && ((DualTimelineGridFragment) (obj1)).list.viewMode == 1)
        {
            j = 1;
        } else
        {
            j = 0;
        }
          goto _L33
_L52:
        ontimelinemodechangedlistener = ((DualTimelineGridFragment) (obj1)).timelineModeChangedListener;
        if (((DualTimelineGridFragment) (obj1)).list != null && ((DualTimelineGridFragment) (obj1)).list.viewMode == 1)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0) goto _L35; else goto _L34
_L34:
        j = ((DualTimelineGridFragment) (obj1)).dayPager.getCurrentItem();
_L39:
        ontimelinemodechangedlistener.onModeChanged(j);
        if (((DualTimelineGridFragment) (obj1)).list != null && ((DualTimelineGridFragment) (obj1)).list.viewMode == 1)
        {
            j = 1;
        } else
        {
            j = 0;
        }
          goto _L36
_L54:
        ((DualTimelineGridFragment) (obj1)).annotateVisualElement(flag3);
          goto _L37
_L35:
        timelinerecyclerview = ((DualTimelineGridFragment) (obj1)).list;
        j = byte0;
        if (timelinerecyclerview.getChildCount() <= 0) goto _L39; else goto _L38
_L38:
        j = timelinerecyclerview.layoutManager.findFirstVisibleItemPosition();
          goto _L39
_L53:
        selectDayFromMonthWidget(((DualTimelineGridFragment) (obj1)));
        return;
        obj;
        obj1 = null;
        break MISSING_BLOCK_LABEL_1075;
_L13:
        if (i != 0x7f100050)
        {
            break MISSING_BLOCK_LABEL_1166;
        }
        if (obj instanceof WeekFragment) goto _L1; else goto _L40
_L40:
        obj1 = new WeekFragment();
        obj = controller.time;
        ((Time) (obj)).writeFieldsToImpl();
        ((Fragment) (obj1)).setArguments(WeekFragment.createArgs(((Time) (obj)).impl.toMillis(false)));
        obj = "week";
        j = ((flag1) ? 1 : 0);
          goto _L18
        if (i != 0x7f100027) goto _L42; else goto _L41
_L41:
        if (obj instanceof MonthFragment) goto _L1; else goto _L43
_L43:
        obj1 = new MonthFragment();
        obj = controller.time;
        ((Time) (obj)).writeFieldsToImpl();
        ((Fragment) (obj1)).setArguments(MonthFragment.createArgs(((Time) (obj)).impl.toMillis(false)));
        obj = "month";
        j = ((flag1) ? 1 : 0);
          goto _L18
_L56:
        if (!(obj instanceof DualTimelineGridFragment))
        {
            break MISSING_BLOCK_LABEL_1274;
        }
        obj = (DualTimelineGridFragment)obj;
        Exception exception;
        Object obj2;
        boolean flag4;
        if (((DualTimelineGridFragment) (obj)).list != null && ((DualTimelineGridFragment) (obj)).list.viewMode == 1)
        {
            flag4 = true;
        } else
        {
            flag4 = false;
        }
        if (flag4 != flag3)
        {
            break MISSING_BLOCK_LABEL_1274;
        }
        selectDayFromMonthWidget(((DualTimelineGridFragment) (obj)));
        return;
        obj1 = new DualTimelineGridFragment();
        obj = controller.time;
        ((Time) (obj)).writeFieldsToImpl();
        ((Fragment) (obj1)).setArguments(DualTimelineGridFragment.createArgs(((Time) (obj)).impl.toMillis(false), flag3));
        if (flag) goto _L45; else goto _L44
_L44:
        if (selectedJulianDayInMonthViewWidget != null)
        {
            j = 1;
        }
          goto _L46
_L45:
        ((DualTimelineGridFragment)obj1).forceShowInitialDay = true;
        break; /* Loop/switch isn't completed */
_L59:
        obj = "agenda";
        break MISSING_BLOCK_LABEL_1589;
_L47:
        if (obj1 != null)
        {
            AnalyticsLogger analyticslogger1 = AnalyticsLoggerHolder.instance;
            if (analyticslogger1 == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger1).trackView(this, ((String) (obj1)));
        }
        throw obj;
        exception;
        obj1 = obj;
        obj = exception;
          goto _L47
_L26:
        if (j == 0) goto _L48; else goto _L25
_L48:
        if (flag3)
        {
            obj = "day_grid";
        } else
        {
            obj = "agenda";
        }
        j = ((flag1) ? 1 : 0);
          goto _L18
_L29:
        if (j == -1)
        {
            j = 0;
        } else
        {
            j = 1;
        }
          goto _L49
_L31:
        j = -1;
          goto _L29
_L32:
        if (j != 0 && i == 0x7f100004) goto _L51; else goto _L50
_L51:
        break; /* Loop/switch isn't completed */
_L33:
        if (j != 0 || i != 0x7f100022) goto _L53; else goto _L52
_L36:
        if (j != 0) goto _L55; else goto _L54
_L37:
        if (i == 0x7f100022)
        {
            obj1 = "day_grid";
        } else
        {
            obj1 = "agenda";
        }
        j = 0;
        obj2 = obj1;
        obj1 = obj;
        obj = obj2;
          goto _L18
_L55:
        flag3 = false;
          goto _L54
_L42:
        if (i == 0x7f100022)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
          goto _L56
_L46:
        if (j == 0) goto _L57; else goto _L45
_L57:
        if (!flag3) goto _L59; else goto _L58
_L58:
        obj = "day_grid";
        j = ((flag1) ? 1 : 0);
          goto _L18
    }


    private class _cls1
        implements Runnable
    {

        private final AllInOneCalendarActivity this$0;

        public final void run()
        {
            timeZone = Utils.getTimeZoneId(AllInOneCalendarActivity.this);
            invalidateOptionsMenu();
            Utils.setMidnightUpdater(handler, timeChangesUpdater, timeZone);
        }

        _cls1()
        {
            this$0 = AllInOneCalendarActivity.this;
            super();
        }
    }


    private class _cls2 extends ContentObserver
    {

        private final AllInOneCalendarActivity this$0;

        public final boolean deliverSelfNotifications()
        {
            return true;
        }

        public final void onChange(boolean flag)
        {
            CalendarController calendarcontroller = controller;
            AllInOneCalendarActivity allinonecalendaractivity = AllInOneCalendarActivity.this;
            calendarcontroller.executeCommand$5166KOBMC4NMOOBECSNKUOJACLHN8EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UGR1DHIMSP31E91MURJKE9NMOR35E8I46RRDDLGMSP1R55B0____0(new CalendarController.Command(128L));
        }

        public final void onChange(boolean flag, Uri uri)
        {
            onChange(flag);
            AbstractEventViewScreenController.notifyContentProviderUpdateIfAvailable(mFragments.mHost.mFragmentManager, uri);
        }

        _cls2(Handler handler1)
        {
            this$0 = AllInOneCalendarActivity.this;
            super(handler1);
        }
    }


    private class _cls3 extends ActionBarDrawerToggle
    {

        public final AllInOneCalendarActivity this$0;

        public final void onDrawerClosed(View view)
        {
            super.onDrawerClosed(view);
            invalidateOptionsMenu();
            view = drawerFragment;
            ((DrawerFragment) (view)).adapter.reorderItems();
            ((DrawerFragment) (view)).list.setSelectionAfterHeaderView();
            for (view = ((DrawerFragment) (view)).drawerClosedListeners.iterator(); view.hasNext(); ((com.google.android.calendar.calendarlist.DrawerFragment.OnDrawerClosedListener)view.next()).onDrawerClosed()) { }
            if (pendingDrawerActionId == -1) goto _L2; else goto _L1
_L1:
            Object obj;
            int i;
            obj = AllInOneCalendarActivity.this;
            i = pendingDrawerActionId;
            if (i != 0x7f100004 && i != 0x7f100022 && i != 0x7f100026 && i != 0x7f100050 && i != 0x7f100027) goto _L4; else goto _L3
_L3:
            logMenuItemSelected(i);
            switchView(i, false);
_L6:
            class .Lambda._cls0
                implements Runnable
            {

                private final _cls3 arg$1;

                public final void run()
                {
                    arg$1._fld0.pendingDrawerActionId = -1;
                }

                .Lambda._cls0()
                {
                    arg$1 = _cls3.this;
                }
            }

            drawerLayout.getRootView().post(new .Lambda._cls0());
_L2:
            return;
_L4:
            if (i == 0x7f100037)
            {
                logMenuItemSelected(i);
                onSearchRequested();
            } else
            if (i == 0x7f100039 || i == 0x7f10017f)
            {
                CalendarController.launchSettings(((Activity) (obj)));
            } else
            if (i == 0x7f100020)
            {
                GoogleFeedbackUtils.launchHelpAndFeedback(((Activity) (obj)), ((Activity) (obj)).getString(0x7f13015a), Integer.valueOf(0x7f100114));
            } else
            if (i == 0x7f100012)
            {
                view = new Bundle(8);
                view.putBoolean("force", true);
                view.putBoolean("expedited", true);
                view.putBoolean("do_not_retry", true);
                view.putBoolean("sync_extra_get_settings", true);
                view.putBoolean("sync_extra_get_recent_notifications", true);
                view.putBoolean("sync_extra_get_default_notifications", true);
                view.putBoolean("sync_extra_check_consistency", true);
                obj = AccountsUtil.getGoogleAccounts(((Context) (obj)));
                int j = obj.length;
                i = 0;
                while (i < j) 
                {
                    Account account = obj[i];
                    view.putString("feed_internal", account.name);
                    if (RefreshUiManager.instance == null)
                    {
                        RefreshUiManager.instance = new RefreshUiManager();
                    }
                    SyncProgressTracker.getInstance().addPendingSync(account, view);
                    ContentResolver.requestSync(account, "com.android.calendar", view);
                    i++;
                }
            } else
            if (i == 0x7f100014)
            {
                view = new Bundle(1);
                view.putBoolean("db_dump_from_drawer", true);
                GoogleFeedbackUtils.launchGoogleFeedback(((Activity) (obj)), view);
            } else
            if (i == 0x7f100013)
            {
                ((Activity) (obj)).startActivity(new Intent(((Context) (obj)), com/google/android/calendar/experimental/ExperimentalDashboardActivity));
            }
            if (true) goto _L6; else goto _L5
_L5:
        }

        public final void onDrawerOpened(View view)
        {
            super.onDrawerOpened(view);
            invalidateOptionsMenu();
        }

        public final void onDrawerSlide$51662RJ4E9NMIP1FEPKMATPFAPKMATPR8OKLC___0(float f)
        {
        }

        _cls3(Activity activity, DrawerLayout drawerlayout, int i, int j)
        {
            this$0 = AllInOneCalendarActivity.this;
            super(activity, drawerlayout, i, j);
        }
    }


    private class _cls4 extends DrawerArrowDrawable
    {

        public final void setProgress(float f)
        {
        }

        _cls4(Context context)
        {
            super(context);
        }
    }


    private class _cls6
        implements CalendarController.Command.Handler
    {

        private final AllInOneCalendarActivity this$0;
        private final CalendarFragment val$fragment;

        public final long getSupportedCommands()
        {
            return 160L;
        }

        public final void handleCommand(CalendarController.Command command)
        {
            if (command.type != 32L) goto _L2; else goto _L1
_L1:
            boolean flag;
            if ((command.extraLong & 8L) != 0L)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag) goto _L4; else goto _L3
_L3:
            fragment.goToNow();
            miniMonthInteractionController.pointTo(TimeBoxUtil.msToJulianDay(TimeZone.getTimeZone(timeZone), command.selectedTime.toMillisWithFallback()));
_L6:
            return;
_L4:
            fragment.goToTime(command.selectedTime.toMillisWithFallback());
            return;
_L2:
            if (command.type == 128L && calendarStoreInvalidator.isPresent())
            {
                ((CalendarStoreInvalidator)calendarStoreInvalidator.get()).onEventsChanged();
                return;
            }
            if (true) goto _L6; else goto _L5
_L5:
        }

        _cls6()
        {
            this$0 = AllInOneCalendarActivity.this;
            fragment = calendarfragment;
            super();
        }
    }


    private class _cls9
        implements android.view.ViewTreeObserver.OnGlobalLayoutListener
    {

        private final Runnable val$runnable;
        private final View val$view;

        public final void onGlobalLayout()
        {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            runnable.run();
        }

        _cls9()
        {
            view = view1;
            runnable = runnable1;
            super();
        }
    }


    private class _cls8 extends AnimatorListenerAdapter
    {

        private final AllInOneCalendarActivity this$0;
        private final OverlayFragment val$fragment;
        private final boolean val$shouldShowFab;

        public final void onAnimationEnd(Animator animator)
        {
            infoFragmentDismiss = null;
            animator = AllInOneCalendarActivity.this;
            OverlayFragment overlayfragment = fragment;
            if (!((AllInOneCalendarActivity) (animator)).onSaveInstanceStateCalled)
            {
                animator.finishDismissOverlay(overlayfragment);
            }
        }

        _cls8()
        {
            this$0 = AllInOneCalendarActivity.this;
            fragment = overlayfragment;
            shouldShowFab = flag;
            super();
        }
    }


    private class _cls7
        implements FutureCallback
    {

        private final AllInOneCalendarActivity this$0;

        public final void onFailure(Throwable throwable)
        {
            if (!(throwable instanceof CancellationException))
            {
                Toast.makeText(AllInOneCalendarActivity.this, 0x7f1301d8, 0).show();
            }
        }

        public final void onSuccess(Object obj)
        {
            obj = (OverlayFragment)obj;
            showOverlayFragment("ViewScreenController", ((OverlayFragment) (obj)));
        }

        _cls7()
        {
            this$0 = AllInOneCalendarActivity.this;
            super();
        }
    }


    private class _cls5 extends AsyncTask
    {

        private final AllInOneCalendarActivity this$0;
        private final String val$authority;

        protected final Object doInBackground(Object aobj[])
        {
            aobj = CalendarAccountsUtil.getSyncableAccounts(AllInOneCalendarActivity.this);
            LogUtils.d("AllInOneCalendarAct", "Refreshing %d accounts", new Object[] {
                Integer.valueOf(aobj.length)
            });
            int j = aobj.length;
            for (int i = 0; i < j; i++)
            {
                Object obj = aobj[i];
                LogUtils.d("AllInOneCalendarAct", "Refreshing calendars for: %s", new Object[] {
                    obj
                });
                Bundle bundle = new Bundle();
                bundle.putBoolean("force", true);
                bundle.putBoolean("sync_only_visible", true);
                bundle.putBoolean("expedited", true);
                bundle.putBoolean("do_not_retry", true);
                if (RefreshUiManager.instance == null)
                {
                    RefreshUiManager.instance = new RefreshUiManager();
                }
                SyncProgressTracker.getInstance().addPendingSync(((Account) (obj)), bundle);
                if (AccountUtil.isGoogleAccount(((Account) (obj))))
                {
                    Utils.appendSyncFlags(bundle);
                }
                String s = authority;
                FeatureConfig featureconfig = Features.instance;
                if (featureconfig == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                }
                ((FeatureConfig)featureconfig).fishfood_debug();
                ContentResolver.requestSync(((Account) (obj)), s, bundle);
            }

            return null;
        }

        _cls5()
        {
            this$0 = AllInOneCalendarActivity.this;
            authority = s;
            super();
        }
    }

}
