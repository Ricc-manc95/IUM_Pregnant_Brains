// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.Window;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.flair.FlairAllocatorFactory;
import com.google.android.apps.calendar.flair.GrooveFlairAllocator;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollector;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollectorHolder;
import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.CancelableFutureCallback;
import com.google.android.apps.calendar.util.gms.GmsFutures;
import com.google.android.calendar.LaunchInfoActivityUtils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.habit.HabitClient;
import com.google.android.calendar.api.habit.HabitContract;
import com.google.android.calendar.api.habit.HabitContractModifications;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.api.habit.HabitFactory;
import com.google.android.calendar.api.habit.HabitFilterOptions;
import com.google.android.calendar.api.habit.HabitModifications;
import com.google.android.calendar.common.activity.CalendarSupportActivity;
import com.google.android.calendar.groove.category.GrooveCategories;
import com.google.android.calendar.newapi.screen.GrooveEditScreenListener;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.utils.belong.FitIntegrationConstants;
import com.google.android.calendar.utils.snackbar.SnackbarFeedbackUtils;
import com.google.android.calendar.utils.statusbar.StatusbarAnimatorCompat;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveScheduleFragment, GrooveCategorySelectionFragment, BackButtonView, GrooveSubcategorySelectionFragment, 
//            GrooveFrequencyMoreOptionsFragment, CustomGrooveFragment, GrooveUtils

public class CreateGrooveActivity extends CalendarSupportActivity
    implements CustomGrooveFragment.Listener, GrooveBelongIntegrationSelectionView.Listener, GrooveCategorySelectionFragment.Listener, GrooveDurationSelectionView.Listener, GrooveFrequencyMoreOptionsFragment.Listener, GrooveFrequencySelectionView.Listener, GroovePreferredTimesSelectionView.Listener, GrooveSubcategorySelectionFragment.Listener, GrooveSummaryView.Listener, GrooveEditScreenListener
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/groove/CreateGrooveActivity);
    public String analyticsCategory;
    private BackButtonView backButton;
    private ListenableFuture backgroundLoadingFuture;
    public ImageView banner;
    private CancelableFutureCallback calendarListCallback;
    public GrooveCategorySelectionFragment categoryFragment;
    public boolean confirmedScheduling;
    public final Runnable forceFinishTask = new .Lambda._cls0();
    public HabitContractModifications habitContractModifications;
    public HabitModifications habitModifications;
    private HabitModifications habitModificationsMoreOptions;
    private boolean isTablet;
    public AnalyticsLogger logger;
    public GrooveScheduleFragment scheduleFragment;
    public Handler timeoutHandler;

    public CreateGrooveActivity()
    {
        timeoutHandler = new Handler();
        class .Lambda._cls0
            implements Runnable
        {

            private final CreateGrooveActivity arg$1;

            public final void run()
            {
                CreateGrooveActivity creategrooveactivity = arg$1;
                SnackbarFeedbackUtils.showSnackbarFeedback(creategrooveactivity, creategrooveactivity.getResources().getString(0x7f1300bf), true, null, 0);
                creategrooveactivity.finish();
                creategrooveactivity.logger.trackEvent(creategrooveactivity, creategrooveactivity.analyticsCategory, "goal_creation_timeout");
            }

            .Lambda._cls0()
            {
                arg$1 = CreateGrooveActivity.this;
            }
        }

    }

    private final void customizeBackground()
    {
        if (!isTablet) goto _L2; else goto _L1
_L1:
        int i;
        int k;
        super.mFragments.mHost.mFragmentManager.executePendingTransactions();
        k = super.mFragments.mHost.mFragmentManager.getBackStackEntryCount();
        i = 0;
_L10:
        if (i >= k) goto _L4; else goto _L3
_L3:
        if (!GrooveScheduleFragment.TAG.equals(super.mFragments.mHost.mFragmentManager.getBackStackEntryAt(i).getName())) goto _L6; else goto _L5
_L5:
        i = 1;
_L8:
        if (i != 0)
        {
            break; /* Loop/switch isn't completed */
        }
        banner.setImageDrawable(null);
_L2:
        return;
_L6:
        i++;
        continue; /* Loop/switch isn't completed */
_L4:
        i = 0;
        if (true) goto _L8; else goto _L7
_L7:
        if (backgroundLoadingFuture != null)
        {
            backgroundLoadingFuture.cancel(true);
        }
        int j = habitModifications.getType();
        class .Lambda._cls10
            implements Function
        {

            private final CreateGrooveActivity arg$1;

            public final Object apply(Object obj3)
            {
                Object obj4 = arg$1;
                obj3 = (String)obj3;
                Resources resources = ((AppCompatActivity) (obj4)).getResources();
                float f = resources.getConfiguration().screenWidthDp;
                int l = Math.round(resources.getDisplayMetrics().density * f);
                int i1 = ((AppCompatActivity) (obj4)).getResources().getDimensionPixelSize(0x7f0e038b);
                obj4 = new com.google.android.calendar.volley.VolleyRequests.VolleyRequestFuture();
                obj3 = new ImageRequest(((String) (obj3)), ((com.android.volley.Response.Listener) (obj4)), l, i1, android.widget.ImageView.ScaleType.CENTER_CROP, null, ((com.android.volley.Response.ErrorListener) (obj4)));
                boolean flag;
                if (((com.google.android.calendar.volley.VolleyRequests.VolleyRequestFuture) (obj4)).request == null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    throw new IllegalStateException(String.valueOf("Already started"));
                }
                obj4.request = ((Request) (obj3));
                ((com.google.android.calendar.volley.VolleyRequests.VolleyRequestFuture) (obj4)).request.mTag = "calendar_volley_request";
                obj3 = VolleyQueueHolder.requestQueue;
                if (obj3 == null)
                {
                    throw new NullPointerException(String.valueOf("VolleyQueueHolder#initialize(...) must be called first"));
                } else
                {
                    ((RequestQueue)obj3).add(((com.google.android.calendar.volley.VolleyRequests.VolleyRequestFuture) (obj4)).request);
                    return obj4;
                }
            }

            .Lambda._cls10()
            {
                arg$1 = CreateGrooveActivity.this;
            }
        }

        class .Lambda._cls11
            implements Supplier
        {

            public static final Supplier $instance = new .Lambda._cls11();

            public final Object get()
            {
                return CreateGrooveActivity.lambda$customizeBackground$11$CreateGrooveActivity();
            }


            private .Lambda._cls11()
            {
            }
        }

        class .Lambda._cls12
            implements Consumer
        {

            private final CreateGrooveActivity arg$1;

            public final void accept(Object obj3)
            {
                CreateGrooveActivity creategrooveactivity = arg$1;
                obj3 = (Bitmap)obj3;
                if (!creategrooveactivity.isDestroyed())
                {
                    obj3 = new BitmapDrawable(creategrooveactivity.getResources(), Utils.getRtlAdjustedImage(creategrooveactivity, ((Bitmap) (obj3))));
                    creategrooveactivity.banner.setImageDrawable(((android.graphics.drawable.Drawable) (obj3)));
                    obj3 = ObjectAnimator.ofFloat(creategrooveactivity.banner, "alpha", new float[] {
                        0.0F, 1.0F
                    });
                    ((ValueAnimator) (obj3)).setDuration(210L);
                    ((ValueAnimator) (obj3)).setInterpolator(new LinearInterpolator());
                    ((Animator) (obj3)).start();
                    creategrooveactivity.banner.requestLayout();
                }
            }

            .Lambda._cls12()
            {
                arg$1 = CreateGrooveActivity.this;
            }
        }

        Object obj;
        Object obj1;
        Object obj2;
        if (FlairAllocatorFactory.getAllocator() == null)
        {
            obj = null;
        } else
        {
            obj = FlairAllocatorFactory.GROOVE_ALLOCATOR.allocateCategoryIllustration(j & 0xff00);
            if (obj == null)
            {
                obj = null;
            } else
            {
                obj1 = FlairAllocatorFactory.flairUrl;
                obj2 = FlairAllocatorFactory.densityLabelDirectory;
                obj = (new StringBuilder(String.valueOf(obj1).length() + 5 + String.valueOf(obj2).length() + String.valueOf(obj).length())).append(((String) (obj1))).append(((String) (obj2))).append("/").append(((String) (obj))).append(".jpg").toString();
            }
        }
        if (obj == null)
        {
            obj = Absent.INSTANCE;
        } else
        {
            obj = new Present(obj);
        }
        backgroundLoadingFuture = (ListenableFuture)((Optional) (obj)).transform(new .Lambda._cls10()).or(.Lambda._cls11..instance);
        obj = backgroundLoadingFuture;
        obj1 = LogUtils.newFailureLoggingCallback(new .Lambda._cls12(), TAG, "Background request failed", new Object[0]);
        obj2 = new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN);
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj)), ((com.google.common.util.concurrent.FutureCallback) (obj1))), ((java.util.concurrent.Executor) (obj2)));
        return;
        if (true) goto _L10; else goto _L9
_L9:
    }

    private final boolean isShowingCategorySelection()
    {
        super.mFragments.mHost.mFragmentManager.executePendingTransactions();
        int i = super.mFragments.mHost.mFragmentManager.getBackStackEntryCount();
        return i > 0 && GrooveCategorySelectionFragment.TAG.equals(super.mFragments.mHost.mFragmentManager.getBackStackEntryAt(i - 1).getName());
    }

    static final ListenableFuture lambda$customizeBackground$11$CreateGrooveActivity()
    {
        NullPointerException nullpointerexception = new NullPointerException();
        if (nullpointerexception == null)
        {
            throw new NullPointerException();
        } else
        {
            return new com.google.common.util.concurrent.ImmediateFuture.ImmediateFailedFuture(nullpointerexception);
        }
    }

    private final void maybeShowActionBarControls()
    {
label0:
        {
            boolean flag = isShowingCategorySelection();
            if (backButton != null)
            {
                if (!flag)
                {
                    break label0;
                }
                backButton.setVisibility(0);
            }
            return;
        }
        backButton.setVisibility(8);
    }

    private final void showScheduleFragment(int i, String s)
    {
        HabitDescriptor habitdescriptor = habitModifications.getDescriptor();
        GrooveScheduleFragment grooveschedulefragment = new GrooveScheduleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("groove_type", i);
        bundle.putString("title", s);
        bundle.putParcelable("groove_descriptor", habitdescriptor);
        grooveschedulefragment.setArguments(bundle);
        scheduleFragment = grooveschedulefragment;
        logger.trackView(this, "goal3_frequency");
        s = scheduleFragment;
        if (android.os.Build.VERSION.SDK_INT >= 22)
        {
            s.setEnterTransition(s.createTransition(this, false));
            s.setReturnTransition(s.createTransition(this, true));
        }
        s = super.mFragments.mHost.mFragmentManager.beginTransaction();
        if (android.os.Build.VERSION.SDK_INT < 22)
        {
            s.setCustomAnimations(0x7f06001a, 0x7f060019, 0x7f06001a, 0x7f060019);
        }
        s.replace(0x7f10013c, scheduleFragment, GrooveScheduleFragment.TAG);
        s.addToBackStack(GrooveScheduleFragment.TAG).commit();
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        if (accessibilityevent.getEventType() == 32 && categoryFragment != null && categoryFragment.isVisible())
        {
            accessibilityevent.getText().add(getString(0x7f1302ac));
            return true;
        } else
        {
            return super.dispatchPopulateAccessibilityEvent(accessibilityevent);
        }
    }

    public void onBackPressed()
    {
        if (confirmedScheduling)
        {
            logger.trackEvent(this, analyticsCategory, "back_pressed_interstitial");
            timeoutHandler.removeCallbacks(forceFinishTask);
            SnackbarFeedbackUtils.showSnackbarFeedback(this, getResources().getString(0x7f1300b9), true, null, 0);
            finish();
            logger.trackEvent(this, analyticsCategory, "goal_creation_exit");
        } else
        if (scheduleFragment != null && scheduleFragment.isVisible() && scheduleFragment.viewPager.getCurrentItem() > 0)
        {
            GrooveScheduleFragment grooveschedulefragment = scheduleFragment;
            if (grooveschedulefragment.viewPager.getCurrentItem() >= 0)
            {
                grooveschedulefragment.viewPager.setCurrentItem(grooveschedulefragment.viewPager.getCurrentItem() - 1);
                return;
            }
        } else
        if (super.mFragments.mHost.mFragmentManager.getBackStackEntryCount() > 1)
        {
            super.mFragments.mHost.mFragmentManager.popBackStack();
            maybeShowActionBarControls();
            customizeBackground();
            return;
        } else
        {
            finish();
            return;
        }
    }

    public final void onBelongIntegrationSelectionComplete(int i)
    {
        habitModifications.setFitIntegrationStatus(i);
        habitModificationsMoreOptions = CalendarApi.HabitFactory.modifyHabit(habitModifications);
        scheduleFragment.setGrooveModifications(habitModificationsMoreOptions);
        logger.trackEvent(this, analyticsCategory, "belong_integration_selected", String.valueOf(i), Long.valueOf(0));
        logger.trackView(this, "goal6_contract");
        GrooveScheduleFragment grooveschedulefragment = scheduleFragment;
        grooveschedulefragment.pagerAdapter.notifyDataSetChanged();
        grooveschedulefragment.viewPager.setCurrentItem(grooveschedulefragment.screenList.indexOf(Integer.valueOf(4)));
    }

    public final void onCategorySelectionComplete(int i, View view)
    {
        habitModifications.setType(i);
        if (backButton != null)
        {
            backButton.setVisibility(8);
        }
        logger.trackEvent(this, analyticsCategory, "category_selected", String.valueOf(i), Long.valueOf(i));
        logger.trackView(this, "goal2_subcategories");
        Object obj = categoryFragment;
        ((Fragment) (obj)).setExitTransition(((GrooveCategorySelectionFragment) (obj)).createTransitionSet(i, true));
        ((Fragment) (obj)).setReturnTransition(((GrooveCategorySelectionFragment) (obj)).createTransitionSet(i, false));
        obj = super.mFragments.mHost.mFragmentManager.beginTransaction();
        GrooveSubcategorySelectionFragment groovesubcategoryselectionfragment = new GrooveSubcategorySelectionFragment();
        Object obj1 = new Bundle();
        ((Bundle) (obj1)).putInt("CATEGORY_ID_KEY", i);
        groovesubcategoryselectionfragment.setArguments(((Bundle) (obj1)));
        obj1 = new TransitionSet();
        ((TransitionSet) (obj1)).addTransition(new ChangeTransform());
        ((TransitionSet) (obj1)).addTransition(new ChangeBounds());
        ((TransitionSet) (obj1)).addTransition(new ChangeClipBounds());
        ((TransitionSet) (obj1)).setInterpolator(new FastOutSlowInInterpolator());
        if (((Fragment) (groovesubcategoryselectionfragment)).mAnimationInfo == null)
        {
            groovesubcategoryselectionfragment.mAnimationInfo = new android.support.v4.app.Fragment.AnimationInfo();
        }
        ((Fragment) (groovesubcategoryselectionfragment)).mAnimationInfo.mSharedElementEnterTransition = obj1;
        groovesubcategoryselectionfragment.setEnterTransition(GrooveSubcategorySelectionFragment.createEnterTransition());
        groovesubcategoryselectionfragment.setReturnTransition(GrooveSubcategorySelectionFragment.createReturnTransition());
        ((FragmentTransaction) (obj)).replace(0x7f10013c, groovesubcategoryselectionfragment, GrooveSubcategorySelectionFragment.TAG).addToBackStack(GrooveSubcategorySelectionFragment.TAG);
        ((FragmentTransaction) (obj)).addSharedElement(view, GrooveCategorySelectionFragment.getBackgroundSharedElementName(i));
        ((FragmentTransaction) (obj)).commit();
    }

    public final void onConfirmContract()
    {
        logger.trackView(this, "goal7_interstitial");
        logger.trackEvent(this, analyticsCategory, "goal_creation_confirmed");
        Object obj = habitModifications.getDescriptor().calendar.account.name;
        logger.logClearcutEvent(com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType.CREATE_GOAL, ((String) (obj)));
        Object obj1 = CalendarApi.Habits;
        Object obj2 = new HabitFilterOptions(((String) (obj)));
        class .Lambda._cls4
            implements Function
        {

            public static final Function $instance = new .Lambda._cls4();

            public final Object apply(Object obj3)
            {
                return Integer.valueOf(((com.google.android.calendar.api.habit.HabitClient.GenericResult)obj3).getCount());
            }


            private .Lambda._cls4()
            {
            }
        }

        class .Lambda._cls5
            implements Function
        {

            private final CreateGrooveActivity arg$1;
            private final String arg$2;

            public final Object apply(Object obj3)
            {
                CreateGrooveActivity creategrooveactivity = arg$1;
                String s = arg$2;
                boolean flag;
                if (((Integer)obj3).intValue() == 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    SettingsUtils.updateTimezoneSettings(AccountUtil.newGoogleAccount(s), Utils.getTimeZoneId(creategrooveactivity));
                }
                return null;
            }

            .Lambda._cls5(String s)
            {
                arg$1 = CreateGrooveActivity.this;
                arg$2 = s;
            }
        }

        class .Lambda._cls3
            implements Consumer
        {

            private final CreateGrooveActivity arg$1;

            public final void accept(Object obj3)
            {
                obj3 = arg$1;
                obj3.confirmedScheduling = true;
                Object obj4 = ((CreateGrooveActivity) (obj3)).scheduleFragment;
                ((GrooveScheduleFragment) (obj4)).backArrow.setIcon(1);
                ((GrooveScheduleFragment) (obj4)).viewPager.setEnabled(false);
                obj4 = CalendarApi.Habits.create(((CreateGrooveActivity) (obj3)).habitModifications);
                class .Lambda._cls6
                    implements Function
                {

                    public static final Function $instance = new .Lambda._cls6();

                    public final Object apply(Object obj6)
                    {
                        return ((com.google.android.calendar.api.habit.HabitClient.ReadResult)obj6).getHabit();
                    }


                        private .Lambda._cls6()
                        {
                        }
                }

                Object obj5 = .Lambda._cls6..instance;
                obj4 = AbstractTransformFuture.create(GmsFutures.asFuture(((com.google.android.gms.common.api.PendingResult) (obj4))), new com.google.android.apps.calendar.util.gms.GmsFutures..Lambda._cls0(((Function) (obj5))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
                class .Lambda._cls7
                    implements Consumer
                {

                    private final CreateGrooveActivity arg$1;

                    public final void accept(Object obj6)
                    {
                        CreateGrooveActivity creategrooveactivity = arg$1;
                        obj6 = (Habit)obj6;
                        HabitDescriptor habitdescriptor = ((Habit) (obj6)).getDescriptor();
                        class .Lambda._cls8
                            implements Runnable
                        {

                            private final CreateGrooveActivity arg$1;

                            public final void run()
                            {
                                CreateGrooveActivity creategrooveactivity1 = arg$1;
                                SnackbarFeedbackUtils.showSnackbarFeedback(creategrooveactivity1, creategrooveactivity1.getResources().getString(0x7f1300ba), true, null, 0);
                                creategrooveactivity1.finish();
                                creategrooveactivity1.logger.trackEvent(creategrooveactivity1, creategrooveactivity1.analyticsCategory, "goal_creation_offline");
                            }

                                .Lambda._cls8()
                                {
                                    arg$1 = CreateGrooveActivity.this;
                                }
                        }

                        class .Lambda._cls14
                            implements Runnable
                        {

                            private final CreateGrooveActivity arg$1;
                            private final Habit arg$2;

                            public final void run()
                            {
                                CalendarListEntry acalendarlistentry[];
                                Object obj8;
                                Object obj7 = arg$1;
                                obj8 = arg$2.getDescriptor();
                                obj7 = CalendarListEntryCache.instance;
                                if (obj7 == null)
                                {
                                    throw new NullPointerException(String.valueOf("Not initialized"));
                                }
                                acalendarlistentry = (CalendarListEntry[])((ListenableFutureCache)obj7).result;
                                if (acalendarlistentry == null) goto _L2; else goto _L1
_L1:
                                Account account;
                                int i;
                                int j;
                                account = ((HabitDescriptor) (obj8)).calendar.account;
                                obj8 = ((HabitDescriptor) (obj8)).calendar.calendarId;
                                j = acalendarlistentry.length;
                                i = 0;
_L7:
                                if (i >= j) goto _L2; else goto _L3
_L3:
                                CalendarListEntry calendarlistentry = acalendarlistentry[i];
                                if (!account.equals(calendarlistentry.getDescriptor().account) || !((String) (obj8)).equals(calendarlistentry.getDescriptor().calendarId)) goto _L5; else goto _L4
_L4:
                                CalendarDescriptor calendardescriptor = calendarlistentry.getDescriptor();
                                CalendarProperties calendarproperties = CalendarProperties.instance;
                                if (calendarproperties == null)
                                {
                                    throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
                                }
                                ((CalendarProperties)calendarproperties).setDefaultCalendarIdValue(calendardescriptor, true);
_L2:
                                return;
_L5:
                                i++;
                                if (true) goto _L7; else goto _L6
_L6:
                            }

                                .Lambda._cls14(Habit habit)
                                {
                                    arg$1 = CreateGrooveActivity.this;
                                    arg$2 = habit;
                                }
                        }

                        if (!NetworkUtil.isConnectedToInternet(creategrooveactivity))
                        {
                            CalendarExecutor.MAIN.execute(creategrooveactivity. new .Lambda._cls8());
                        } else
                        {
                            if (GrooveSyncTracker.instance == null)
                            {
                                GrooveSyncTracker.instance = new GrooveSyncTracker();
                            }
                            GrooveSyncTracker groovesynctracker = GrooveSyncTracker.instance;
                            class .Lambda._cls9
                                implements GrooveSyncTracker.HabitSyncListener
                            {

                                private final CreateGrooveActivity arg$1;

                                public final void onHabitInstancesSynced()
                                {
                                    CreateGrooveActivity creategrooveactivity1 = arg$1;
                                    class .Lambda._cls13
                                        implements Runnable
                                    {

                                        private final CreateGrooveActivity arg$1;

                                        public final void run()
                                        {
                                            CreateGrooveActivity creategrooveactivity2 = arg$1;
                                            creategrooveactivity2.timeoutHandler.removeCallbacks(creategrooveactivity2.forceFinishTask);
                                            creategrooveactivity2.finish();
                                            creategrooveactivity2.logger.trackEvent(creategrooveactivity2, creategrooveactivity2.analyticsCategory, "goal_creation_success");
                                        }

                                            .Lambda._cls13()
                                            {
                                                arg$1 = CreateGrooveActivity.this;
                                            }
                                    }

                                    CalendarExecutor.MAIN.execute(creategrooveactivity1. new .Lambda._cls13());
                                }

                                .Lambda._cls9()
                                {
                                    arg$1 = CreateGrooveActivity.this;
                                }
                            }

                            .Lambda._cls9 _lcls9 = creategrooveactivity. new .Lambda._cls9();
                            LatencyLoggerHolder.get().markAt(18, habitdescriptor.habitId);
                            groovesynctracker.habitCreationListeners.put(habitdescriptor.habitId, _lcls9);
                            groovesynctracker.habitDescriptors.put(habitdescriptor.habitId, habitdescriptor);
                            creategrooveactivity.timeoutHandler.postDelayed(creategrooveactivity.forceFinishTask, 11500L);
                        }
                        CalendarExecutor.MAIN.execute(creategrooveactivity. new .Lambda._cls14(((Habit) (obj6))));
                    }

                        .Lambda._cls7()
                        {
                            arg$1 = CreateGrooveActivity.this;
                        }
                }

                obj5 = LogUtils.newFailureLoggingCallback(((.Lambda._cls7) (obj3)). new .Lambda._cls7(), CreateGrooveActivity.TAG, "Habit creation failed.", new Object[0]);
                CalendarExecutor calendarexecutor = CalendarExecutor.BACKGROUND;
                if (obj5 == null)
                {
                    throw new NullPointerException();
                }
                ((ListenableFuture) (obj4)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj4)), ((com.google.common.util.concurrent.FutureCallback) (obj5))), calendarexecutor);
                if (((CreateGrooveActivity) (obj3)).habitModifications.isFitIntegrationEnabled())
                {
                    BelongUtils.onIntegrationStatusChange(((Context) (obj3)), true);
                }
            }

            .Lambda._cls3()
            {
                arg$1 = CreateGrooveActivity.this;
            }
        }

        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        obj2.activeAfterFilter = Long.valueOf(l);
        obj1 = ((HabitClient) (obj1)).count(((HabitFilterOptions) (obj2)));
        obj2 = .Lambda._cls4..instance;
        obj = AbstractTransformFuture.create(AbstractTransformFuture.create(GmsFutures.asFuture(((com.google.android.gms.common.api.PendingResult) (obj1))), new com.google.android.apps.calendar.util.gms.GmsFutures..Lambda._cls0(((Function) (obj2))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE), new .Lambda._cls5(((String) (obj))), CalendarExecutor.BACKGROUND);
        obj1 = LogUtils.newFailureLoggingCallback(new .Lambda._cls3(), TAG, "Timezone update failed.", new Object[0]);
        obj2 = CalendarExecutor.MAIN;
        if (obj1 == null)
        {
            throw new NullPointerException();
        } else
        {
            ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj)), ((com.google.common.util.concurrent.FutureCallback) (obj1))), ((java.util.concurrent.Executor) (obj2)));
            return;
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (LaunchInfoActivityUtils.redirectIfMandatoryPermissionsNotGranted(this))
        {
            return;
        }
        getWindow().requestFeature(12);
        Object obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        logger = (AnalyticsLogger)obj;
        analyticsCategory = "groove";
        isTablet = getResources().getBoolean(0x7f0c0016);
        if (!isTablet)
        {
            setRequestedOrientation(1);
        }
        setContentView(0x7f050033);
        obj = getWindow();
        ((Window) (obj)).addFlags(0x80000000);
        ((Window) (obj)).getDecorView().setSystemUiVisibility(1280);
        int i;
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            i = 0x7f0d031f;
        } else
        {
            i = 0x7f0d0320;
        }
        StatusbarAnimatorCompat.createInstance(((Window) (obj))).setStatusbarColor(getResources().getColor(i));
        if (isTablet)
        {
            banner = (ImageView)findViewById(0x7f10013d);
            obj = (FrameLayout)findViewById(0x7f10013c);
            android.view.ViewGroup.LayoutParams layoutparams = ((FrameLayout) (obj)).getLayoutParams();
            Resources resources = getResources();
            float f = Math.max(resources.getConfiguration().screenWidthDp, resources.getConfiguration().screenHeightDp);
            i = Math.round(resources.getDisplayMetrics().density * f);
            int j = getResources().getDimensionPixelSize(0x7f0e01ea);
            findViewById(0x7f10013e).setFitsSystemWindows(true);
            layoutparams.width = i / 2 + j * 2;
            ((FrameLayout) (obj)).requestLayout();
            backButton = (BackButtonView)findViewById(0x7f10011e);
            backButton.setIcon(1);
            backButton.setTheme(2, false);
        }
        if (bundle != null && bundle.getParcelable("habit") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            habitModifications = (HabitModifications)bundle.getParcelable("habit");
            habitModificationsMoreOptions = (HabitModifications)bundle.getParcelable("habit_more_options");
            confirmedScheduling = bundle.getBoolean("confirmed_scheduling");
            habitContractModifications = habitModifications.getContractModifications();
            categoryFragment = (GrooveCategorySelectionFragment)super.mFragments.mHost.mFragmentManager.findFragmentByTag(GrooveCategorySelectionFragment.TAG);
            scheduleFragment = (GrooveScheduleFragment)super.mFragments.mHost.mFragmentManager.findFragmentByTag(GrooveScheduleFragment.TAG);
            if (scheduleFragment != null)
            {
                scheduleFragment.setGrooveModifications(habitModifications);
            }
            maybeShowActionBarControls();
            customizeBackground();
        } else
        {
            calendarListCallback = new CancelableFutureCallback(new _cls1());
            obj = CalendarListEntryCache.instance;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("Not initialized"));
            }
            obj = ((ListenableFutureCache)obj).getValueAsync();
            CancelableFutureCallback cancelablefuturecallback = calendarListCallback;
            com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0 _lcls0 = new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN);
            if (cancelablefuturecallback == null)
            {
                throw new NullPointerException();
            }
            ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj)), cancelablefuturecallback), _lcls0);
        }
        obj = PerformanceMetricCollectorHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        }
        obj = (PerformanceMetricCollector)obj;
        if (bundle != null)
        {
            bundle = "CreateGrooveActivity.Recreated";
        } else
        {
            bundle = "CreateGrooveActivity.Created";
        }
        ((PerformanceMetricCollector) (obj)).recordMemory(bundle);
    }

    public final void onCustomGrooveSelectionComplete(int i, String s)
    {
        habitModifications.setSummary(s);
        habitModifications.setType(i);
        showScheduleFragment(habitModifications.getType(), s);
        customizeBackground();
    }

    protected void onDestroy()
    {
        PerformanceMetricCollector performancemetriccollector = PerformanceMetricCollectorHolder.instance;
        if (performancemetriccollector == null)
        {
            throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        }
        ((PerformanceMetricCollector)performancemetriccollector).recordMemory("CreateGrooveActivity.Destroyed");
        super.onDestroy();
        if (backgroundLoadingFuture != null)
        {
            backgroundLoadingFuture.cancel(true);
        }
        if (calendarListCallback != null)
        {
            calendarListCallback.nestedFutureCallbackReference.set(null);
        }
    }

    public final void onDurationSelectionComplete(int i)
    {
        habitContractModifications.setDurationMinutes(i);
        logger.trackEvent(this, analyticsCategory, "duration_selected", null, Long.valueOf(i));
        GrooveScheduleFragment grooveschedulefragment;
        if (habitModifications.getContract().getDurationMinutes() > 540)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        grooveschedulefragment = scheduleFragment;
        if (i != 0)
        {
            grooveschedulefragment.screenList.remove(Integer.valueOf(2));
        } else
        if (!grooveschedulefragment.screenList.contains(Integer.valueOf(2)))
        {
            grooveschedulefragment.screenList.add(grooveschedulefragment.screenList.indexOf(Integer.valueOf(1)) + 1, Integer.valueOf(2));
        }
        if (i != 0)
        {
            onPreferredTimesSelectionComplete(0);
            return;
        } else
        {
            logger.trackView(this, "goal5_preferredtime");
            GrooveScheduleFragment grooveschedulefragment1 = scheduleFragment;
            grooveschedulefragment1.pagerAdapter.notifyDataSetChanged();
            grooveschedulefragment1.viewPager.setCurrentItem(grooveschedulefragment1.screenList.indexOf(Integer.valueOf(2)));
            return;
        }
    }

    public final void onEditCancelled()
    {
        habitModificationsMoreOptions = CalendarApi.HabitFactory.modifyHabit(habitModifications);
        scheduleFragment.setGrooveModifications(habitModificationsMoreOptions);
    }

    public final void onEditFinished(HabitModifications habitmodifications)
    {
        habitModifications.applyModifications(habitmodifications);
        habitModificationsMoreOptions = CalendarApi.HabitFactory.modifyHabit(habitModifications);
        scheduleFragment.setGrooveModifications(habitModificationsMoreOptions);
        scheduleFragment.initializeImageBackground(false);
    }

    public final void onFrequencyMoreOptionsClicked()
    {
        Object obj1 = scheduleFragment;
        Object obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        Object obj2 = (AnalyticsLogger)obj;
        int i;
        int j;
        if (((Fragment) (obj1)).mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)((Fragment) (obj1)).mHost.mActivity;
        }
        ((AnalyticsLogger) (obj2)).trackView(((Context) (obj)), "goal3a_frequency_custom");
        obj = ((Fragment) (obj1)).mFragmentManager.beginTransaction();
        if (android.os.Build.VERSION.SDK_INT < 23)
        {
            ((FragmentTransaction) (obj)).setCustomAnimations(0x7f060018, 0x7f06001a, 0x7f060018, 0x7f06001a);
        }
        i = ((GrooveScheduleFragment) (obj1)).backgroundColor;
        j = ((GrooveScheduleFragment) (obj1)).colorTheme;
        obj1 = new GrooveFrequencyMoreOptionsFragment();
        obj2 = new Bundle();
        ((Bundle) (obj2)).putInt("FREQUENCY_MORE_OPTIONS_BACKGROUND_COLOR", i);
        ((Bundle) (obj2)).putInt("FREQUENCY_MORE_OPTIONS_THEME", j);
        ((Fragment) (obj1)).setArguments(((Bundle) (obj2)));
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            ((Fragment) (obj1)).setEnterTransition(GrooveFrequencyMoreOptionsFragment.createFadeTransition());
        }
        ((FragmentTransaction) (obj)).add(0x7f10013c, ((Fragment) (obj1)), GrooveFrequencyMoreOptionsFragment.TAG);
        ((FragmentTransaction) (obj)).addToBackStack(CustomGrooveFragment.TAG);
        ((FragmentTransaction) (obj)).commit();
        logger.trackEvent(this, analyticsCategory, "frequency_more_options_clicked");
    }

    public final void onFrequencyMoreOptionsSelected(int i, int j)
    {
        GrooveScheduleFragment grooveschedulefragment = scheduleFragment;
        Fragment fragment = ((Fragment) (grooveschedulefragment)).mFragmentManager.findFragmentByTag(GrooveFrequencyMoreOptionsFragment.TAG);
        ((Fragment) (grooveschedulefragment)).mFragmentManager.beginTransaction().remove(fragment).commit();
        ((Fragment) (grooveschedulefragment)).mFragmentManager.popBackStack();
        onFrequencySelectionComplete(i, j);
    }

    public final void onFrequencySelectionComplete(int i, int j)
    {
        habitContractModifications.setInterval(i);
        habitContractModifications.setNumInstancesPerInterval(j);
        Object obj;
        if (i == 3)
        {
            obj = "monthly";
        } else
        {
            obj = "weekly";
        }
        logger.trackEvent(this, analyticsCategory, "frequency_selected", ((String) (obj)), Long.valueOf(j));
        logger.trackView(this, "goal4_duration");
        scheduleFragment.setGrooveModifications(habitModifications);
        obj = scheduleFragment;
        ((GrooveScheduleFragment) (obj)).pagerAdapter.notifyDataSetChanged();
        ((GrooveScheduleFragment) (obj)).viewPager.setCurrentItem(((GrooveScheduleFragment) (obj)).screenList.indexOf(Integer.valueOf(1)));
    }

    public final void onPreferredTimesSelectionComplete(int i)
    {
        boolean flag;
        flag = true;
        habitContractModifications.setMorningPreferable(false);
        habitContractModifications.setAfternoonPreferable(false);
        habitContractModifications.setEveningPreferable(false);
        i;
        JVM INSTR tableswitch 0 4: default 72
    //                   0 108
    //                   1 194
    //                   2 208
    //                   3 72
    //                   4 222;
           goto _L1 _L2 _L3 _L4 _L1 _L5
_L5:
        break MISSING_BLOCK_LABEL_222;
_L1:
        throw new IllegalArgumentException((new StringBuilder(37)).append("Preferred time ").append(i).append(" not found.").toString());
_L2:
        habitContractModifications.setAnyDayTimeAcceptable();
_L6:
        Object obj;
        FutureCallback futurecallback;
        CalendarExecutor calendarexecutor;
        class .Lambda._cls1
            implements Callable
        {

            private final CreateGrooveActivity arg$1;

            public final Object call()
            {
                CreateGrooveActivity creategrooveactivity = arg$1;
                return Integer.valueOf(GrooveUtils.getDefaultReminderMinutes(creategrooveactivity, creategrooveactivity.habitModifications.getDescriptor().calendar.account, creategrooveactivity.habitModifications.getDescriptor().calendar.calendarId));
            }

            .Lambda._cls1()
            {
                arg$1 = CreateGrooveActivity.this;
            }
        }

        class .Lambda._cls2
            implements Consumer
        {

            private final CreateGrooveActivity arg$1;
            private final boolean arg$2;

            public final void accept(Object obj1)
            {
                CreateGrooveActivity creategrooveactivity = arg$1;
                boolean flag2 = arg$2;
                obj1 = (Integer)obj1;
                creategrooveactivity.habitModifications.setReminders(new HabitReminders(false, ((Integer) (obj1)), flag2, flag2));
            }

            .Lambda._cls2(boolean flag)
            {
                arg$1 = CreateGrooveActivity.this;
                arg$2 = flag;
            }
        }

        boolean flag1;
        if (habitContractModifications.getDurationMinutes() > 15)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        obj = (FluentFuture)CalendarExecutor.DISK.submit(new .Lambda._cls1());
        futurecallback = LogUtils.newFailureLoggingCallback(new .Lambda._cls2(flag1), TAG, "Default reminder minutes load failed.", new Object[0]);
        calendarexecutor = CalendarExecutor.MAIN;
        if (futurecallback == null)
        {
            throw new NullPointerException();
        }
        break MISSING_BLOCK_LABEL_242;
_L3:
        habitContractModifications.setMorningPreferable(true);
          goto _L6
_L4:
        habitContractModifications.setAfternoonPreferable(true);
          goto _L6
        habitContractModifications.setEveningPreferable(true);
          goto _L6
        ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj)), futurecallback), calendarexecutor);
        habitModificationsMoreOptions = CalendarApi.HabitFactory.modifyHabit(habitModifications);
        scheduleFragment.setGrooveModifications(habitModificationsMoreOptions);
        obj = GrooveUtils.getPreferredTimeString(getResources(), habitContractModifications);
        logger.trackEvent(this, analyticsCategory, "preferred_time_selected", ((String) (obj)), Long.valueOf(i));
        i = habitModifications.getType();
        if (FitIntegrationConstants.HABIT_TYPE_TO_FIT_ACTIVITY_MAP.get(i) != null)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        obj = scheduleFragment;
        if (i != 0)
        {
            if (!((GrooveScheduleFragment) (obj)).screenList.contains(Integer.valueOf(3)))
            {
                ((GrooveScheduleFragment) (obj)).screenList.add(((GrooveScheduleFragment) (obj)).screenList.indexOf(Integer.valueOf(4)), Integer.valueOf(3));
            }
        } else
        {
            ((GrooveScheduleFragment) (obj)).screenList.remove(Integer.valueOf(3));
        }
        if (i != 0)
        {
            logger.trackView(this, "goal5a_belong");
            obj = scheduleFragment;
            ((GrooveScheduleFragment) (obj)).pagerAdapter.notifyDataSetChanged();
            ((GrooveScheduleFragment) (obj)).viewPager.setCurrentItem(((GrooveScheduleFragment) (obj)).screenList.indexOf(Integer.valueOf(3)));
            return;
        } else
        {
            logger.trackView(this, "goal6_contract");
            GrooveScheduleFragment grooveschedulefragment = scheduleFragment;
            grooveschedulefragment.pagerAdapter.notifyDataSetChanged();
            grooveschedulefragment.viewPager.setCurrentItem(grooveschedulefragment.screenList.indexOf(Integer.valueOf(4)));
            return;
        }
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        bundle.putParcelable("habit", habitModifications);
        bundle.putParcelable("habit_more_options", habitModificationsMoreOptions);
        bundle.putBoolean("confirmed_scheduling", confirmedScheduling);
        super.onSaveInstanceState(bundle);
    }

    protected void onStart()
    {
        super.onStart();
        if (categoryFragment != null && isShowingCategorySelection())
        {
            GrooveCategorySelectionFragment groovecategoryselectionfragment = categoryFragment;
            groovecategoryselectionfragment.storeHeader.startAnimation(groovecategoryselectionfragment.createEnterAnimation());
            for (int i = 0; i < groovecategoryselectionfragment.categoryTiles.length; i++)
            {
                AnimationSet animationset = groovecategoryselectionfragment.createEnterAnimation();
                animationset.setStartOffset(i * 20 + 20);
                groovecategoryselectionfragment.categoryTiles[i].startAnimation(animationset);
            }

            if (!GrooveCategorySelectionFragment.isTablet)
            {
                groovecategoryselectionfragment.backButton.startAnimation(groovecategoryselectionfragment.createEnterAnimation());
            }
        }
    }

    public final void onSubcategorySelectionComplete(int i)
    {
        habitModifications.setType(i);
        Object obj = getResources();
        if (GrooveCategories.instance == null)
        {
            GrooveCategories.instance = new GrooveCategories(((Resources) (obj)));
        }
        obj = GrooveCategories.resources.getString(GrooveCategories.GROOVE_NAME_IDS.get(i));
        logger.trackEvent(this, analyticsCategory, "subcategory_selected", ((String) (obj)), Long.valueOf(i));
        habitModifications.setSummary(((String) (obj)));
        showScheduleFragment(i, ((String) (obj)));
        customizeBackground();
    }


    private class _cls1
        implements FutureCallback
    {

        private final CreateGrooveActivity this$0;

        public final void onFailure(Throwable throwable)
        {
            throwable = CreateGrooveActivity.this;
            CalendarListEntry calendarlistentry = GrooveUtils.getGrooveSupportedCalendar(throwable, null);
            if (calendarlistentry == null)
            {
                Toast.makeText(throwable, 0x7f1301ac, 0).show();
                throwable.finish();
                return;
            } else
            {
                throwable.habitModifications = CalendarApi.HabitFactory.newHabit(calendarlistentry.getDescriptor());
                throwable.habitContractModifications = ((CreateGrooveActivity) (throwable)).habitModifications.getContractModifications();
                throwable.categoryFragment = new GrooveCategorySelectionFragment();
                ((FragmentActivity) (throwable)).mFragments.mHost.mFragmentManager.beginTransaction().add(0x7f10013c, ((CreateGrooveActivity) (throwable)).categoryFragment, GrooveCategorySelectionFragment.TAG).addToBackStack(GrooveCategorySelectionFragment.TAG).commit();
                return;
            }
        }

        public final void onSuccess(Object obj)
        {
            CalendarListEntry acalendarlistentry[] = (CalendarListEntry[])obj;
            obj = CreateGrooveActivity.this;
            CalendarListEntry calendarlistentry = GrooveUtils.getGrooveSupportedCalendar(((Context) (obj)), acalendarlistentry);
            if (calendarlistentry == null)
            {
                Toast.makeText(((Context) (obj)), 0x7f1301ac, 0).show();
                ((CreateGrooveActivity) (obj)).finish();
                return;
            } else
            {
                obj.habitModifications = CalendarApi.HabitFactory.newHabit(calendarlistentry.getDescriptor());
                obj.habitContractModifications = ((CreateGrooveActivity) (obj)).habitModifications.getContractModifications();
                obj.categoryFragment = new GrooveCategorySelectionFragment();
                ((FragmentActivity) (obj)).mFragments.mHost.mFragmentManager.beginTransaction().add(0x7f10013c, ((CreateGrooveActivity) (obj)).categoryFragment, GrooveCategorySelectionFragment.TAG).addToBackStack(GrooveCategorySelectionFragment.TAG).commit();
                return;
            }
        }

        _cls1()
        {
            this$0 = CreateGrooveActivity.this;
            super();
        }
    }

}
