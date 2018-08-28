// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementAttacher;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementHolder;
import com.google.android.calendar.AllInOneCalendarActivity;
import com.google.android.calendar.Utils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.common.view.overlay.OverlayFragment;
import com.google.android.calendar.event.DelayedActionDescription;
import com.google.android.calendar.event.DelayedActionPerformer;
import com.google.android.calendar.event.DetailsDialogFragment;
import com.google.android.calendar.event.OnInfoChangedListener;
import com.google.android.calendar.latency.LatencyLogger;
import com.google.android.calendar.latency.LatencyLoggerHolder;
import com.google.android.calendar.newapi.commandbar.BottomBar;
import com.google.android.calendar.newapi.commandbar.BottomBarController;
import com.google.android.calendar.newapi.commandbar.MoreOptionsSheet;
import com.google.android.calendar.newapi.commandbar.MoreOptionsSheetController;
import com.google.android.calendar.newapi.common.Loader;
import com.google.android.calendar.newapi.logging.LoggingUtils;
import com.google.android.calendar.newapi.model.AccountHolder;
import com.google.android.calendar.newapi.model.BasicViewScreenModel;
import com.google.android.calendar.newapi.model.EventViewScreenModel;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.newapi.overflow.OverflowMenuController;
import com.google.android.calendar.swipeclosing.DraggableScrollView;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.animations.EventInfoAnimationData;
import com.google.android.calendar.timely.animations.EventInfoAnimationView;
import com.google.android.calendar.timely.geometry.SimplePartitionItem;
import com.google.android.calendar.utils.SystemWindowInsetApplier;
import com.google.android.calendar.utils.a11y.A11yHelper;
import com.google.android.calendar.utils.animation.QuantumInterpolators;
import com.google.android.calendar.utils.animation.WiggleInterpolator;
import com.google.android.calendar.utils.statusbar.StatusbarAnimatorCompat;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            ViewScreen, SegmentViews, ViewScreenOpenCloseHelper, ContentDisplayHandler

public abstract class ViewScreenController extends DetailsDialogFragment
    implements com.google.android.calendar.newapi.common.Loader.Callback, ContentDisplayHandler.Callback, ViewScreen.Callback
{

    private boolean analyticsViewLogged;
    public ViewScreenOpenCloseHelper animationHelper;
    public BottomBarController commandBarController;
    private ContentDisplayHandler contentDisplayHandler;
    private DelayedActionDescription delayedAction;
    public boolean editOpeningInitiated;
    private boolean firstOpening;
    private boolean instanceRestored;
    public LatencyLogger latencyLogger;
    public Loader loader;
    public ViewScreenModel model;
    public MoreOptionsSheetController moreOptionsSheetController;
    private android.support.v4.app.FragmentManager.OnBackStackChangedListener onBackStackChangedListener;
    public OverflowMenuController overflowMenuController;
    public boolean segmentsCreated;
    public boolean shouldPulseEditButton;
    public boolean started;
    public ViewScreen viewScreen;

    public ViewScreenController()
    {
        firstOpening = true;
        class .Lambda._cls0
            implements android.support.v4.app.FragmentManager.OnBackStackChangedListener
        {

            private final ViewScreenController arg$1;

            public final void onBackStackChanged()
            {
                ViewScreenController viewscreencontroller = arg$1;
                Object obj = ((Fragment) (viewscreencontroller)).mFragmentManager;
                int i = ((FragmentManager) (obj)).getBackStackEntryCount();
                if (i > 0)
                {
                    String s = ((Fragment) (viewscreencontroller)).mTag;
                    obj = ((FragmentManager) (obj)).getBackStackEntryAt(i - 1).getName();
                    boolean flag;
                    if (s == obj || s != null && s.equals(obj))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        viewscreencontroller.editOpeningInitiated = false;
                    }
                }
            }

            .Lambda._cls0()
            {
                arg$1 = ViewScreenController.this;
            }
        }

        onBackStackChangedListener = new .Lambda._cls0();
    }

    private final void createHeaderSegment()
    {
        ViewScreen viewscreen;
        View view3;
label0:
        {
            viewscreen = viewScreen;
            View view = createHeaderSegment(model);
            ViewGroup viewgroup = (ViewGroup)viewscreen.findViewById(0x7f100265);
            if (viewscreen.segmentViews.headerView != null)
            {
                viewgroup.removeView(viewscreen.segmentViews.headerView);
            }
            viewgroup.addView(view);
            viewscreen.headerLayoutChangeListener = new ViewScreen._cls2(viewscreen);
            view.addOnLayoutChangeListener(viewscreen.headerLayoutChangeListener);
            viewscreen.segmentViews.headerView = view;
            if (android.os.Build.VERSION.SDK_INT >= 22)
            {
                View view1 = viewscreen.findViewById(0x7f100047);
                View view2 = viewscreen.findViewById(0x7f100266);
                view3 = viewscreen.findViewById(0x7f1002ae);
                view2.setAccessibilityTraversalAfter(0x7f1002a2);
                boolean flag;
                if (viewscreen.overflowMenuView != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    viewscreen.overflowMenuView.setAccessibilityTraversalAfter(view2.getId());
                    view1.setAccessibilityTraversalAfter(viewscreen.overflowMenuView.getId());
                } else
                {
                    view1.setAccessibilityTraversalAfter(view2.getId());
                }
                view3.setAccessibilityTraversalAfter(view1.getId());
                if (viewscreen.commandBar == null)
                {
                    break label0;
                }
                viewscreen.commandBar.setAccessibilityTraversalAfter(view3.getId());
                viewscreen.scrollView.setAccessibilityTraversalAfter(viewscreen.commandBar.getId());
            }
            return;
        }
        viewscreen.scrollView.setAccessibilityTraversalAfter(view3.getId());
    }

    private final void createSegments()
    {
        if (segmentsCreated)
        {
            updateSegments();
            return;
        }
        latencyLogger.markAt(33);
        segmentsCreated = true;
        createHeaderSegment();
        ArrayList arraylist = new ArrayList();
        createBodySegments(getModel(), arraylist);
        ViewScreen viewscreen = viewScreen;
        ViewGroup viewgroup = (ViewGroup)viewscreen.findViewById(0x7f100231);
        for (int i = 0; i < arraylist.size(); i++)
        {
            viewgroup.addView((View)arraylist.get(i));
        }

        ViewCompat.requestApplyInsets(viewscreen);
        viewscreen.segmentViews.bodyViews.clear();
        viewscreen.segmentViews.bodyViews.addAll(arraylist);
        viewScreen.updateSegmentViews();
        if (isFullScreen(requireContext().getResources()))
        {
            Object obj;
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            if (obj instanceof AllInOneCalendarActivity)
            {
                A11yHelper.getInstance();
                if (!A11yHelper.isAccessibilityEnabled(getContext()))
                {
                    obj = viewScreen;
                    ((ViewScreen) (obj)).scrollView.attachDraggableView(((View) (obj)), ((com.google.android.calendar.swipeclosing.DraggableScrollView.ActionListener) (obj)));
                    DraggableScrollView draggablescrollview = (DraggableScrollView)((ViewScreen) (obj)).findViewById(0x7f1002a4);
                    draggablescrollview.attachDraggableView(((View) (obj)), ((com.google.android.calendar.swipeclosing.DraggableScrollView.ActionListener) (obj)));
                    draggablescrollview.singleClickListener = ((com.google.android.calendar.swipeclosing.DraggableScrollView.SingleClickListener) (obj));
                }
            }
        }
        onViewUpdated();
        latencyLogger.markAt(34);
    }

    private final void openView()
    {
        viewScreen.updateEditButton();
        Object obj = viewScreen;
        Object obj2 = createHeaderSegment(model);
        ViewGroup viewgroup = (ViewGroup)((ViewScreen) (obj)).findViewById(0x7f100265);
        if (((ViewScreen) (obj)).segmentViews.headerView != null)
        {
            viewgroup.removeView(((ViewScreen) (obj)).segmentViews.headerView);
        }
        viewgroup.addView(((View) (obj2)));
        obj.headerLayoutChangeListener = new ViewScreen._cls2(((ViewScreen) (obj)));
        ((View) (obj2)).addOnLayoutChangeListener(((ViewScreen) (obj)).headerLayoutChangeListener);
        ((ViewScreen) (obj)).segmentViews.headerView = ((View) (obj2));
        boolean flag;
        if (android.os.Build.VERSION.SDK_INT >= 22)
        {
            obj2 = ((ViewScreen) (obj)).findViewById(0x7f100047);
            View view = ((ViewScreen) (obj)).findViewById(0x7f100266);
            View view1 = ((ViewScreen) (obj)).findViewById(0x7f1002ae);
            view.setAccessibilityTraversalAfter(0x7f1002a2);
            if (((ViewScreen) (obj)).overflowMenuView != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                ((ViewScreen) (obj)).overflowMenuView.setAccessibilityTraversalAfter(view.getId());
                ((View) (obj2)).setAccessibilityTraversalAfter(((ViewScreen) (obj)).overflowMenuView.getId());
            } else
            {
                ((View) (obj2)).setAccessibilityTraversalAfter(view.getId());
            }
            view1.setAccessibilityTraversalAfter(((View) (obj2)).getId());
            if (((ViewScreen) (obj)).commandBar != null)
            {
                ((ViewScreen) (obj)).commandBar.setAccessibilityTraversalAfter(view1.getId());
                ((ViewScreen) (obj)).scrollView.setAccessibilityTraversalAfter(((ViewScreen) (obj)).commandBar.getId());
            } else
            {
                ((ViewScreen) (obj)).scrollView.setAccessibilityTraversalAfter(view1.getId());
            }
        }
        viewScreen.updateSegmentViews();
        obj2 = animationHelper;
        if (((ViewScreenOpenCloseHelper) (obj2)).animationData != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            obj = ((ViewScreenOpenCloseHelper) (obj2)).controller;
            ((ViewScreenController) (obj)).latencyLogger.markAt(31);
            obj = ((ViewScreenController) (obj)).contentDisplayHandler;
            obj2 = ((ContentDisplayHandler) (obj)).handler.obtainMessage(1338, null);
            ((ContentDisplayHandler) (obj)).handler.sendMessage(((android.os.Message) (obj2)));
        } else
        {
            obj2.openingRequested = true;
            if (((ViewScreenOpenCloseHelper) (obj2)).openingRequested && ((ViewScreenOpenCloseHelper) (obj2)).topSystemWindowInset != -1)
            {
                obj2.openingRequested = false;
                ViewScreenController viewscreencontroller = ((ViewScreenOpenCloseHelper) (obj2)).controller;
                int i;
                if (((Fragment) (viewscreencontroller)).mHost != null && ((Fragment) (viewscreencontroller)).mAdded)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i != 0)
                {
                    ((ViewScreenOpenCloseHelper) (obj2)).controller.latencyLogger.markAt(32);
                    Object obj1;
                    EventInfoAnimationView eventinfoanimationview;
                    EventInfoAnimationData eventinfoanimationdata;
                    TimelineItem timelineitem;
                    View view2;
                    if (((ViewScreenOpenCloseHelper) (obj2)).fullscreen)
                    {
                        obj1 = null;
                    } else
                    {
                        obj1 = ((ViewScreenOpenCloseHelper) (obj2)).overlayView;
                    }
                    eventinfoanimationview = ((ViewScreenOpenCloseHelper) (obj2)).animationView;
                    eventinfoanimationdata = ((ViewScreenOpenCloseHelper) (obj2)).animationData;
                    timelineitem = ((ViewScreenOpenCloseHelper) (obj2)).timelineItem;
                    view2 = ((ViewScreenOpenCloseHelper) (obj2)).createAnimationHeader();
                    eventinfoanimationview.shouldDrawScrim = ((ViewScreenOpenCloseHelper) (obj2)).fullscreen;
                    eventinfoanimationview.item = timelineitem;
                    eventinfoanimationview.contentView = ((View) (obj1));
                    eventinfoanimationview.animationData = eventinfoanimationdata;
                    eventinfoanimationview.startRect = new Rect(eventinfoanimationview.animationData.position);
                    eventinfoanimationview.chipReplacement = view2;
                    eventinfoanimationview.chip.partitionInfo = new SimplePartitionItem(eventinfoanimationview.item);
                    eventinfoanimationview.chip.setViewModel(eventinfoanimationview.animationData.getChipViewModel(eventinfoanimationview.getContext(), eventinfoanimationview.item));
                    eventinfoanimationview.headlineView.addView(eventinfoanimationview.chipReplacement);
                    eventinfoanimationview.headlineView.addView(eventinfoanimationview.chip);
                    if (((ViewScreenOpenCloseHelper) (obj2)).fullscreen)
                    {
                        obj1 = ((ViewScreenOpenCloseHelper) (obj2)).animationView;
                        Context context = ((ViewScreenOpenCloseHelper) (obj2)).rootView.getContext();
                        if (((ViewScreenOpenCloseHelper) (obj2)).controller.getModel().hasImage(context))
                        {
                            i = context.getResources().getDimensionPixelOffset(0x7f0e034b);
                        } else
                        {
                            i = context.getResources().getDimensionPixelOffset(0x7f0e024d);
                        }
                        ((EventInfoAnimationView) (obj1)).startOpenAnimation(null, i + ((ViewScreenOpenCloseHelper) (obj2)).topSystemWindowInset, new ViewScreenOpenCloseHelper._cls1(((ViewScreenOpenCloseHelper) (obj2))), ((ViewScreenOpenCloseHelper) (obj2)).getStatusbarAnimator());
                    } else
                    {
                        ViewScreenOpenCloseHelper._cls2 _lcls2 = new ViewScreenOpenCloseHelper._cls2(((ViewScreenOpenCloseHelper) (obj2)), (android.widget.FrameLayout.LayoutParams)((ViewScreenOpenCloseHelper) (obj2)).overlayView.getLayoutParams());
                        Rect rect = new Rect();
                        ((ViewScreenOpenCloseHelper) (obj2)).controller.overlayBackground.startRectAnimation(((ViewScreenOpenCloseHelper) (obj2)).controller, rect);
                        EventInfoAnimationView eventinfoanimationview1 = ((ViewScreenOpenCloseHelper) (obj2)).animationView;
                        Context context1 = ((ViewScreenOpenCloseHelper) (obj2)).rootView.getContext();
                        int j;
                        if (((ViewScreenOpenCloseHelper) (obj2)).controller.getModel().hasImage(context1))
                        {
                            j = context1.getResources().getDimensionPixelOffset(0x7f0e034b);
                        } else
                        {
                            j = context1.getResources().getDimensionPixelOffset(0x7f0e024d);
                        }
                        eventinfoanimationview1.startOpenAnimation(rect, j + ((ViewScreenOpenCloseHelper) (obj2)).topSystemWindowInset, new ViewScreenOpenCloseHelper._cls3(((ViewScreenOpenCloseHelper) (obj2)), _lcls2), ((ViewScreenOpenCloseHelper) (obj2)).getStatusbarAnimator());
                    }
                    (new Handler()).postDelayed(new ViewScreenOpenCloseHelper._cls4(((ViewScreenOpenCloseHelper) (obj2))), 300L);
                    return;
                }
            }
        }
    }

    public static ViewScreenController prepare(ViewScreenController viewscreencontroller, TimelineItem timelineitem, EventInfoAnimationData eventinfoanimationdata, Bundle bundle)
    {
        Object obj = viewscreencontroller.getArguments();
        if (obj == null)
        {
            obj = Absent.INSTANCE;
        } else
        {
            obj = new Present(obj);
        }
        obj = (Bundle)((Optional) (obj)).or(new Bundle());
        ((Bundle) (obj)).putParcelable("animation_data", eventinfoanimationdata);
        ((Bundle) (obj)).putParcelable("view_screen_extras", bundle);
        viewscreencontroller.setArguments(((Bundle) (obj)));
        viewscreencontroller.model = viewscreencontroller.createModel(timelineitem);
        return viewscreencontroller;
    }

    public final void closeViewScreen()
    {
        boolean flag;
        if (super.mHost != null && super.mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            Object obj;
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            if (obj instanceof OnInfoChangedListener)
            {
                OnInfoChangedListener oninfochangedlistener = (OnInfoChangedListener)obj;
                ViewScreenOpenCloseHelper viewscreenopenclosehelper = animationHelper;
                float f = viewScreen.getTranslationY();
                int i;
                boolean flag1;
                if (viewscreenopenclosehelper.animationData != null)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i != 0 && viewscreenopenclosehelper.animationView.canAnimate())
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1)
                {
                    obj = null;
                    Object obj1;
                    Object obj2;
                    android.animation.AnimatorSet.Builder builder;
                    ObjectAnimator objectanimator1;
                    int j;
                    if (viewscreenopenclosehelper.fullscreen)
                    {
                        viewscreenopenclosehelper.overlayView.setBackgroundResource(0x106000d);
                    } else
                    {
                        obj = new Rect();
                        viewscreenopenclosehelper.controller.overlayBackground.startRectAnimation(viewscreenopenclosehelper.controller, ((Rect) (obj)));
                        viewscreenopenclosehelper.overlayView.setTranslationY(((Rect) (obj)).top);
                        viewscreenopenclosehelper.overlayView.setTranslationX(((Rect) (obj)).left);
                    }
                    if (!viewscreenopenclosehelper.timelineItem.equals(viewscreenopenclosehelper.animationView.item))
                    {
                        viewscreenopenclosehelper.animationView.reinitialize(viewscreenopenclosehelper.timelineItem, viewscreenopenclosehelper.createAnimationHeader());
                    }
                    obj1 = viewscreenopenclosehelper.animationView;
                    obj2 = viewscreenopenclosehelper.rootView.getContext();
                    if (viewscreenopenclosehelper.controller.getModel().hasImage(((Context) (obj2))))
                    {
                        i = ((Context) (obj2)).getResources().getDimensionPixelOffset(0x7f0e034b);
                    } else
                    {
                        i = ((Context) (obj2)).getResources().getDimensionPixelOffset(0x7f0e024d);
                    }
                    j = viewscreenopenclosehelper.topSystemWindowInset;
                    obj2 = viewscreenopenclosehelper.getStatusbarAnimator();
                    ((EventInfoAnimationView) (obj1)).setVisibility(0);
                    if (((EventInfoAnimationView) (obj1)).animSet != null && ((EventInfoAnimationView) (obj1)).animSet.isRunning())
                    {
                        ((EventInfoAnimationView) (obj1)).animSet.end();
                    }
                    obj1.viewTranslationTop = f;
                    obj1.finalRect = ((Rect) (obj));
                    obj1.animSet = new AnimatorSet();
                    ((EventInfoAnimationView) (obj1)).setAnimationHeight(1.0F);
                    obj = ObjectAnimator.ofFloat(obj1, "animationHeight", new float[] {
                        1.0F, 0.0F
                    }).setDuration(300L);
                    builder = ((EventInfoAnimationView) (obj1)).animSet.play(((Animator) (obj)));
                    ((EventInfoAnimationView) (obj1)).chip.setAlpha(0.0F);
                    objectanimator1 = ObjectAnimator.ofFloat(((EventInfoAnimationView) (obj1)).chip, "alpha", new float[] {
                        0.0F, 1.0F
                    }).setDuration(150L);
                    objectanimator1.setStartDelay(75L);
                    builder.with(objectanimator1);
                    builder.with(ObjectAnimator.ofInt(obj1, "headlineHeight", new int[] {
                        i + j, ((EventInfoAnimationView) (obj1)).startRect.height()
                    }).setDuration(225L));
                    objectanimator1 = ObjectAnimator.ofFloat(obj1, "animationWidth", new float[] {
                        1.0F, 0.0F
                    }).setDuration(225L);
                    objectanimator1.setStartDelay(75L);
                    builder.with(objectanimator1);
                    if (((EventInfoAnimationView) (obj1)).shouldDrawScrim)
                    {
                        ObjectAnimator objectanimator2 = ObjectAnimator.ofFloat(obj1, "overlayAlpha", new float[] {
                            0.2F, 0.0F
                        }).setDuration(225L);
                        objectanimator2.setStartDelay(75L);
                        builder.with(objectanimator2);
                    }
                    if (obj2 != null)
                    {
                        Animator animator = ((StatusbarAnimatorCompat) (obj2)).animateStatusbarColor(0, 75, ((StatusbarAnimatorCompat) (obj2)).getStatusbarColor());
                        ((StatusbarAnimatorCompat) (obj2)).addLightStatusbarChangeToAnimationStart(((Animator) (obj)), Utils.getConfigBool(((EventInfoAnimationView) (obj1)).getContext(), 0x7f0c0010));
                        builder.with(animator);
                    }
                    ((EventInfoAnimationView) (obj1)).animSet.setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
                    ((EventInfoAnimationView) (obj1)).animSet.start();
                    obj2 = viewscreenopenclosehelper.contentView;
                    obj1 = ObjectAnimator.ofFloat(obj2, View.ALPHA, new float[] {
                        1.0F, 0.0F
                    }).setDuration(75L);
                    ((ObjectAnimator) (obj1)).addListener(new com.google.android.calendar.utils.animation.AnimationUtils._cls4(((View) (obj2)), ((View) (obj2)).getLayerType()));
                    ((ObjectAnimator) (obj1)).setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
                    ((ObjectAnimator) (obj1)).addListener(new ViewScreen._cls4(((ViewScreen) (obj2))));
                    obj = new AnimatorSet();
                    obj1 = ((AnimatorSet) (obj)).play(((Animator) (obj1)));
                    obj2 = ((ViewScreen) (obj2)).findViewById(0x7f1002ad);
                    if (obj2 != null && ((View) (obj2)).getVisibility() == 0)
                    {
                        ObjectAnimator objectanimator = ObjectAnimator.ofFloat(obj2, View.SCALE_X, new float[] {
                            1.0F, 0.0F
                        }).setDuration(75L);
                        objectanimator.setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
                        ((android.animation.AnimatorSet.Builder) (obj1)).with(objectanimator);
                        obj2 = ObjectAnimator.ofFloat(obj2, View.SCALE_Y, new float[] {
                            1.0F, 0.0F
                        }).setDuration(75L);
                        ((ObjectAnimator) (obj2)).setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
                        ((android.animation.AnimatorSet.Builder) (obj1)).with(((Animator) (obj2)));
                    }
                    ((AnimatorSet) (obj)).start();
                }
                oninfochangedlistener.onInfoCancel(viewscreenopenclosehelper.controller, flag1);
                return;
            }
            if (super.mFragmentManager != null)
            {
                dismiss();
                return;
            }
        }
    }

    public abstract void createBodySegments(ViewScreenModel viewscreenmodel, List list);

    public abstract BottomBarController createCommandBarController(MoreOptionsSheetController moreoptionssheetcontroller);

    public abstract View createHeaderSegment(ViewScreenModel viewscreenmodel);

    public abstract Loader createLoader(boolean flag);

    public abstract ViewScreenModel createModel(TimelineItem timelineitem);

    public abstract MoreOptionsSheetController createMoreOptionsSheetController();

    public abstract OverflowMenuController createOverflowMenuController();

    public abstract ViewScreen createViewScreen();

    public final int getContentViewId()
    {
        return 0x7f100231;
    }

    public final Context getContext()
    {
        if (super.mView != null)
        {
            return super.mView.getContext();
        }
        if (super.mHost == null)
        {
            return null;
        } else
        {
            return (FragmentActivity)super.mHost.mActivity;
        }
    }

    public final com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayBackground getLoadingBackground()
    {
        return com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayBackground.Floating;
    }

    public ViewScreenModel getModel()
    {
        return model;
    }

    protected final View getOverlayView()
    {
        return animationHelper.overlayView;
    }

    protected String getPrimesLogTag()
    {
        return "";
    }

    public final com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayBackground getShortBackground()
    {
        return com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayBackground.Floating;
    }

    public final com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayBackground getTallBackground()
    {
        return com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayBackground.BottomDocked;
    }

    protected final boolean getWindowHeight(int ai[])
    {
        ai[0] = getLoadingBackground().height;
        return true;
    }

    public final void notifyAnimationFinished(Runnable runnable)
    {
        latencyLogger.markAt(31);
        ContentDisplayHandler contentdisplayhandler = contentDisplayHandler;
        runnable = contentdisplayhandler.handler.obtainMessage(1338, runnable);
        contentdisplayhandler.handler.sendMessage(runnable);
    }

    public void notifyLoadingFinished()
    {
        ContentDisplayHandler contentdisplayhandler = contentDisplayHandler;
        contentdisplayhandler.handler.removeMessages(1339);
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        l -= contentdisplayhandler.progressViewVisibleStartedAt;
        if (l >= 500L)
        {
            contentdisplayhandler.handler.sendEmptyMessage(1337);
            return;
        } else
        {
            contentdisplayhandler.handler.sendEmptyMessageDelayed(1337, 500L - l);
            return;
        }
    }

    public final void onCancelClicked()
    {
        closeViewScreen();
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        boolean flag;
        if (bundle != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        instanceRestored = flag;
        if (instanceRestored)
        {
            analyticsViewLogged = bundle.getBoolean("INSTANCE_ANALYTICS_VIEW_LOGGED");
            model = (ViewScreenModel)bundle.getParcelable("INSTANCE_MODEL");
            firstOpening = bundle.getBoolean("INSTANCE_FIRST_OPENING");
        }
        super.mFragmentManager.addOnBackStackChangedListener(onBackStackChangedListener);
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        return new _cls1(bundle);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return layoutinflater.inflate(0x7f0500f3, viewgroup, false);
    }

    public final void onDestroy()
    {
        super.mFragmentManager.removeOnBackStackChangedListener(onBackStackChangedListener);
        super.onDestroy();
    }

    public final void onDestroyView()
    {
        segmentsCreated = false;
        commandBarController = null;
        overflowMenuController = null;
        viewScreen = null;
        super.onDestroyView();
    }

    protected final void onDialogBackPressed()
    {
        closeViewScreen();
    }

    public void onDismiss(DialogInterface dialoginterface)
    {
        super.onDismiss(dialoginterface);
        if (super.mHost == null)
        {
            dialoginterface = null;
        } else
        {
            dialoginterface = (FragmentActivity)super.mHost.mActivity;
        }
        DelayedActionDescription delayedactiondescription;
        if (dialoginterface != null)
        {
            if ((delayedactiondescription = delayedAction) != null)
            {
                if (dialoginterface instanceof DelayedActionPerformer)
                {
                    ((DelayedActionPerformer)dialoginterface).performDelayedAction(delayedactiondescription);
                    return;
                } else
                {
                    LogUtils.wtf("ViewScreenController", "Wanted to perform a delayed action without a DelayedActionPerformer.", new Object[0]);
                    return;
                }
            }
        }
    }

    public final void onEditClicked()
    {
        if (editOpeningInitiated)
        {
            return;
        }
        editOpeningInitiated = true;
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        if (obj != null)
        {
            obj = getContext();
            ViewScreenModel viewscreenmodel = getModel();
            ViewScreen viewscreen = viewScreen;
            String s1 = viewscreenmodel.getCategory();
            if (obj != null)
            {
                AnalyticsLogger analyticslogger1 = AnalyticsLoggerHolder.instance;
                if (analyticslogger1 == null)
                {
                    throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                }
                ((AnalyticsLogger)analyticslogger1).trackEvent(((Context) (obj)), s1, "edit_button_pressed");
            }
            if (viewscreenmodel instanceof EventViewScreenModel)
            {
                VisualElementAttacher visualelementattacher = VisualElementHolder.instance;
                if (visualelementattacher == null)
                {
                    throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
                }
                ((VisualElementAttacher)visualelementattacher).recordTap(((Context) (obj)), viewscreen.editButton, ((EventViewScreenModel)viewscreenmodel).getAccount());
            }
            if (shouldPulseEditButton)
            {
                Context context = getContext();
                String s = getModel().getCategory();
                if (context != null)
                {
                    AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
                    if (analyticslogger == null)
                    {
                        throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                    }
                    ((AnalyticsLogger)analyticslogger).trackEvent(context, s, "pulsing_edit_button_pressed");
                }
            }
        }
        showEditScreen();
    }

    protected final void onFinalLayoutFinished()
    {
        super.onFinalLayoutFinished();
        boolean flag;
        if (super.mHost != null && super.mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && !isFullScreen(requireContext().getResources()))
        {
            openView();
        }
    }

    public final void onLoadingFailure(Loader loader1, String s)
    {
        latencyLogger.markAt(7);
        loader1 = getContext();
        String s1 = getModel().getViewType();
        if (loader1 != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).trackEvent(loader1, "view_event_failed", s1);
        }
        LogUtils.e("ViewScreenController", "%s", new Object[] {
            s
        });
        if (super.mHost == null)
        {
            loader1 = null;
        } else
        {
            loader1 = (FragmentActivity)super.mHost.mActivity;
        }
        Toast.makeText(loader1, 0x7f1301aa, 0).show();
        closeViewScreen();
    }

    public void onLoadingSuccess(Loader loader1)
    {
        latencyLogger.markAt(7);
        model.mergeModel((ViewScreenModel)loader.getResult());
        updateCommandBar();
        if (overflowMenuController != null)
        {
            loader1 = overflowMenuController;
            ViewScreenModel viewscreenmodel = getModel();
            loader1.model = viewscreenmodel;
            loader1.onModelChanged(((OverflowMenuController) (loader1)).overflowMenu, viewscreenmodel);
        }
        if (!isFullScreen(requireContext().getResources()))
        {
            createSegments();
        }
        notifyLoadingFinished();
        ViewScreen viewscreen;
        Object obj;
        String s;
        if (super.mHost != null)
        {
            loader1 = (FragmentActivity)super.mHost.mActivity;
        }
        loader1 = getContext();
        obj = getModel();
        viewscreen = viewScreen;
        if (obj instanceof AccountHolder)
        {
            LoggingUtils.addAccountType(loader1, (AccountHolder)obj);
        }
        s = ((ViewScreenModel) (obj)).getViewType();
        if (loader1 != null)
        {
            AnalyticsLogger analyticslogger1 = AnalyticsLoggerHolder.instance;
            if (analyticslogger1 == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger1).trackEvent(loader1, "view_event", s);
        }
        s = ((ViewScreenModel) (obj)).getCategory();
        if (loader1 != null)
        {
            AnalyticsLogger analyticslogger2 = AnalyticsLoggerHolder.instance;
            if (analyticslogger2 == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger2).trackEvent(loader1, s, "info_bubble_shown");
        }
        if (loader1 != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).trackView(loader1, "view_screen");
        }
        if (obj instanceof EventViewScreenModel)
        {
            Object obj1 = ((BasicViewScreenModel) ((EventViewScreenModel)obj)).event;
            String s1 = ((Event) (obj1)).getCalendarListEntry().getDescriptor().calendarId;
            obj1 = ((Event) (obj1)).getSyncId();
            obj = ((EventViewScreenModel)obj).getAccount();
            VisualElementAttacher visualelementattacher = VisualElementHolder.instance;
            if (visualelementattacher == null)
            {
                throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
            }
            visualelementattacher = (VisualElementAttacher)visualelementattacher;
            visualelementattacher.attachEventDetailsPage$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5TGN0S1F8PP62PRDCLN78GB3EHKNCQBKF4TKOOBECHP6UQB45TR6IPBN5TB6IPBN7D66KOBMC4NMOOBECSNL6T3ID5N6EEQCD9GNCO9FDHGMSPPFADQ74QBECSTIILG_0(viewscreen, s1, ((String) (obj1)));
            visualelementattacher.attachEditEventButton(viewscreen.editButton, s1, ((String) (obj1)));
            visualelementattacher.recordImpression(loader1, viewscreen, ((android.accounts.Account) (obj)));
        }
    }

    public final boolean onPreDraw(View view, com.google.android.calendar.common.view.overlay.OverlayFragment.PreDrawListener predrawlistener)
    {
        com.google.android.calendar.common.view.overlay.OverlayFragment.LayoutChangeListener layoutchangelistener = null;
        if (view.getHeight() == 0)
        {
            return false;
        }
        if (predrawlistener.contentView != null)
        {
            ViewTreeObserver viewtreeobserver = predrawlistener.contentView.getViewTreeObserver();
            if (viewtreeobserver != null)
            {
                viewtreeobserver.removeOnPreDrawListener(predrawlistener);
            }
            predrawlistener.contentView = null;
        }
        View view1 = view.findViewById(getContentViewId());
        if (view1 != null)
        {
            ViewTreeObserver viewtreeobserver1 = view1.getViewTreeObserver();
            if (viewtreeobserver1 != null)
            {
                layoutchangelistener = new com.google.android.calendar.common.view.overlay.OverlayFragment.LayoutChangeListener(this, view1);
                viewtreeobserver1.addOnGlobalLayoutListener(layoutchangelistener);
            }
            onGlobalLayout(view1, layoutchangelistener);
        }
        return super.onPreDraw(view, predrawlistener);
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        bundle.putBoolean("INSTANCE_ANALYTICS_VIEW_LOGGED", analyticsViewLogged);
        bundle.putParcelable("INSTANCE_MODEL", model);
        bundle.putBoolean("INSTANCE_FIRST_OPENING", firstOpening);
        super.onSaveInstanceState(bundle);
    }

    public final void onShowContent(Runnable runnable)
    {
        if (isFullScreen(requireContext().getResources()))
        {
            createSegments();
        }
        if (!firstOpening) goto _L2; else goto _L1
_L1:
        Object obj;
        boolean flag;
        firstOpening = false;
        ViewScreenController viewscreencontroller;
        boolean flag1;
        if ((Bundle)getArguments().getParcelable("view_screen_extras") != null && ((Bundle)getArguments().getParcelable("view_screen_extras")).getBoolean("animate_header", false))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj = animationHelper;
        viewscreencontroller = ((ViewScreenOpenCloseHelper) (obj)).controller;
        if (((Fragment) (viewscreencontroller)).mHost != null && ((Fragment) (viewscreencontroller)).mAdded)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1 && ((ViewScreenOpenCloseHelper) (obj)).contentView != null) goto _L4; else goto _L3
_L3:
        latencyLogger.markAt(9);
        return;
_L4:
        ((ViewScreenOpenCloseHelper) (obj)).showContentInstantly();
        if (!flag)
        {
            boolean flag2;
            if (((ViewScreenOpenCloseHelper) (obj)).animationData != null)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            if (!flag2)
            {
                ((ViewScreenOpenCloseHelper) (obj)).contentView.contentView.requestLayout();
                continue; /* Loop/switch isn't completed */
            }
        }
        ((ViewScreenOpenCloseHelper) (obj)).contentView.requestLayout();
        ViewScreen viewscreen = ((ViewScreenOpenCloseHelper) (obj)).contentView;
        ViewScreenOpenCloseHelper..Lambda._cls1 _lcls1 = new ViewScreenOpenCloseHelper..Lambda._cls1(((ViewScreenOpenCloseHelper) (obj)), runnable);
        AnimatorSet animatorset = new AnimatorSet();
        viewscreen.contentView.setVisibility(0);
        View view = viewscreen.findViewById(0x7f100265);
        DraggableScrollView draggablescrollview = viewscreen.scrollView;
        View view1;
        View view2;
        int i;
        if (flag)
        {
            obj = view;
        } else
        {
            obj = view.findViewById(0x7f100047);
        }
        view1 = viewscreen.findViewById(0x7f100266);
        view2 = viewscreen.overflowMenuView;
        i = 0;
        runnable = null;
        while (i < 4) 
        {
            View view3 = (new View[] {
                draggablescrollview, obj, view1, view2
            })[i];
            Object obj2 = runnable;
            if (view3 != null)
            {
                obj2 = ObjectAnimator.ofFloat(view3, View.ALPHA, new float[] {
                    0.0F, 1.0F
                });
                long l;
                if (view3 == view)
                {
                    l = 500L;
                } else
                {
                    l = 75L;
                }
                obj2 = ((ObjectAnimator) (obj2)).setDuration(l);
                ((ObjectAnimator) (obj2)).addListener(new com.google.android.calendar.utils.animation.AnimationUtils._cls4(view3, view3.getLayerType()));
                if (runnable == null)
                {
                    obj2 = animatorset.play(((Animator) (obj2)));
                } else
                {
                    runnable.with(((Animator) (obj2)));
                    obj2 = runnable;
                }
            }
            i++;
            runnable = ((Runnable) (obj2));
        }
        obj = viewscreen.findViewById(0x7f1002ad);
        viewscreen.updateEditButton();
        if (obj != null && ((View) (obj)).getVisibility() == 0)
        {
            runnable.with(ObjectAnimator.ofFloat(obj, View.SCALE_X, new float[] {
                0.0F, 1.0F
            }).setDuration(150L));
            runnable.with(ObjectAnimator.ofFloat(obj, View.SCALE_Y, new float[] {
                0.0F, 1.0F
            }).setDuration(150L));
        }
        if (viewscreen.commandBar != null && viewscreen.commandBar.getVisibility() == 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i)
        {
            Object obj1 = viewscreen.findViewById(0x7f1002af);
            if (obj1 != null)
            {
                ((View) (obj1)).setVisibility(0);
            }
            obj1 = viewscreen.commandBar.createEnterAnimation().setDuration(150L);
            ((ValueAnimator) (obj1)).setStartDelay(150L);
            runnable.with(((Animator) (obj1)));
        }
        animatorset.setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
        animatorset.addListener(new ViewScreen._cls3(viewscreen, _lcls1));
        animatorset.start();
        continue; /* Loop/switch isn't completed */
_L2:
        animationHelper.showContentInstantly();
        viewScreen.contentView.requestLayout();
        return;
        if (true) goto _L3; else goto _L5
_L5:
    }

    public final void onStart()
    {
        super.onStart();
        contentDisplayHandler.callback = this;
        started = true;
        setLoader(createLoader(true));
        loader.load();
        contentDisplayHandler.handler.sendEmptyMessageDelayed(1339, 500L);
        latencyLogger.markAt(6);
        viewScreen.callback = this;
        viewScreen.model = getModel();
    }

    public void onStop()
    {
        contentDisplayHandler.callback = null;
        loader.setCallback(null);
        viewScreen.callback = null;
        started = false;
        super.onStop();
    }

    protected final void onTouchOutside()
    {
        closeViewScreen();
    }

    public final void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        moreOptionsSheetController = createMoreOptionsSheetController();
        viewScreen = createViewScreen();
        commandBarController = createCommandBarController(moreOptionsSheetController);
        bundle = viewScreen;
        Object obj1 = commandBarController;
        if (obj1 != null)
        {
            ViewGroup viewgroup = (ViewGroup)bundle.findViewById(0x7f1002af);
            viewgroup.removeAllViews();
            obj1.commandBar = ((BottomBarController) (obj1)).inflateCommandBar(bundle.getContext(), viewgroup);
            ((BottomBarController) (obj1)).commandBar.initialize(((BottomBarController) (obj1)).getActionsLayout(), ((BottomBarController) (obj1)).getPrimaryActionIds());
            ((BottomBarController) (obj1)).commandBar.setVisibility(4);
            ((BottomBarController) (obj1)).commandBar.listener = ((com.google.android.calendar.newapi.commandbar.BottomBar.OnCommandBarActionClickListener) (obj1));
            ((BottomBarController) (obj1)).setupCommandBar(((BottomBarController) (obj1)).commandBar);
            bundle.commandBar = ((BottomBarController) (obj1)).commandBar;
            obj1 = ((ViewScreen) (bundle)).commandBar;
            ViewScreen..Lambda._cls0 _lcls0 = new ViewScreen..Lambda._cls0(bundle);
            obj1.onHeightChangedListener = _lcls0;
            ((BottomBar) (obj1)).addOnLayoutChangeListener(new com.google.android.calendar.newapi.commandbar.BottomBar..Lambda._cls0(_lcls0));
            viewgroup.addView(((ViewScreen) (bundle)).commandBar);
        }
        setupOverflowMenuController();
        obj1 = viewScreen;
        bundle = moreOptionsSheetController;
        if (bundle != null)
        {
            ViewGroup viewgroup1 = (ViewGroup)((ViewScreen) (obj1)).findViewById(0x7f1002af);
            bundle.moreOptionsSheet = (MoreOptionsSheet)LayoutInflater.from(((ViewScreen) (obj1)).getContext()).inflate(0x7f0500da, viewgroup1, false);
            obj1 = ((MoreOptionsSheetController) (bundle)).moreOptionsSheet;
            obj1.scrimView = ((MoreOptionsSheet) (obj1)).findViewById(0x7f100283);
            obj1.panelView = ((MoreOptionsSheet) (obj1)).findViewById(0x7f100284);
            ((MoreOptionsSheet) (obj1)).scrimView.setOnClickListener(((android.view.View.OnClickListener) (obj1)));
            ((MoreOptionsSheet) (obj1)).findViewById(0x7f100285).setOnClickListener(((android.view.View.OnClickListener) (obj1)));
            ((MoreOptionsSheet) (obj1)).findViewById(0x7f100286).setOnClickListener(((android.view.View.OnClickListener) (obj1)));
            ((MoreOptionsSheetController) (bundle)).moreOptionsSheet.listener = bundle;
            viewgroup1.addView(((MoreOptionsSheetController) (bundle)).moreOptionsSheet);
        }
        viewScreen.model = model;
        contentDisplayHandler = new ContentDisplayHandler(viewScreen.findViewById(0x7f10010d), this);
        latencyLogger = LatencyLoggerHolder.get();
        ((ViewGroup)view.findViewById(0x7f1002a6)).addView(viewScreen);
        animationHelper = new ViewScreenOpenCloseHelper(this, model.timelineItem, (EventInfoAnimationData)getArguments().getParcelable("animation_data"));
        if (!instanceRestored) goto _L2; else goto _L1
_L1:
        int i;
        animationHelper.animationData = null;
        animationHelper.initialize();
        bundle = animationHelper;
        ViewGroup viewgroup2;
        if (((ViewScreenOpenCloseHelper) (bundle)).animationData != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L4; else goto _L3
_L3:
        view = ((ViewScreenOpenCloseHelper) (bundle)).controller;
        ((ViewScreenController) (view)).latencyLogger.markAt(31);
        view = ((ViewScreenController) (view)).contentDisplayHandler;
        bundle = ((ContentDisplayHandler) (view)).handler.obtainMessage(1338, null);
        ((ContentDisplayHandler) (view)).handler.sendMessage(bundle);
_L12:
        if (!isFullScreen(requireContext().getResources())) goto _L6; else goto _L5
_L5:
        viewScreen.updateEditButton();
        view = viewScreen;
        bundle = createHeaderSegment(model);
        viewgroup2 = (ViewGroup)view.findViewById(0x7f100265);
        if (((ViewScreen) (view)).segmentViews.headerView != null)
        {
            viewgroup2.removeView(((ViewScreen) (view)).segmentViews.headerView);
        }
        viewgroup2.addView(bundle);
        view.headerLayoutChangeListener = new ViewScreen._cls2(view);
        bundle.addOnLayoutChangeListener(((ViewScreen) (view)).headerLayoutChangeListener);
        ((ViewScreen) (view)).segmentViews.headerView = bundle;
        if (android.os.Build.VERSION.SDK_INT >= 22)
        {
            bundle = view.findViewById(0x7f100047);
            Object obj = view.findViewById(0x7f100266);
            Object obj2 = view.findViewById(0x7f1002ae);
            ((View) (obj)).setAccessibilityTraversalAfter(0x7f1002a2);
            Object obj3;
            View view1;
            if (((ViewScreen) (view)).overflowMenuView != null)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                ((ViewScreen) (view)).overflowMenuView.setAccessibilityTraversalAfter(((View) (obj)).getId());
                bundle.setAccessibilityTraversalAfter(((ViewScreen) (view)).overflowMenuView.getId());
            } else
            {
                bundle.setAccessibilityTraversalAfter(((View) (obj)).getId());
            }
            ((View) (obj2)).setAccessibilityTraversalAfter(bundle.getId());
            if (((ViewScreen) (view)).commandBar != null)
            {
                ((ViewScreen) (view)).commandBar.setAccessibilityTraversalAfter(((View) (obj2)).getId());
                ((ViewScreen) (view)).scrollView.setAccessibilityTraversalAfter(((ViewScreen) (view)).commandBar.getId());
            } else
            {
                ((ViewScreen) (view)).scrollView.setAccessibilityTraversalAfter(((View) (obj2)).getId());
            }
        }
        viewScreen.updateSegmentViews();
        bundle = animationHelper;
        if (((ViewScreenOpenCloseHelper) (bundle)).animationData != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L8; else goto _L7
_L7:
        view = ((ViewScreenOpenCloseHelper) (bundle)).controller;
        ((ViewScreenController) (view)).latencyLogger.markAt(31);
        view = ((ViewScreenController) (view)).contentDisplayHandler;
        bundle = ((ContentDisplayHandler) (view)).handler.obtainMessage(1338, null);
        ((ContentDisplayHandler) (view)).handler.sendMessage(bundle);
_L6:
        return;
_L4:
        bundle.openingRequested = true;
        if (((ViewScreenOpenCloseHelper) (bundle)).openingRequested && ((ViewScreenOpenCloseHelper) (bundle)).topSystemWindowInset != -1)
        {
            bundle.openingRequested = false;
            view = ((ViewScreenOpenCloseHelper) (bundle)).controller;
            if (((Fragment) (view)).mHost != null && ((Fragment) (view)).mAdded)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                ((ViewScreenOpenCloseHelper) (bundle)).controller.latencyLogger.markAt(32);
                if (((ViewScreenOpenCloseHelper) (bundle)).fullscreen)
                {
                    view = null;
                } else
                {
                    view = ((ViewScreenOpenCloseHelper) (bundle)).overlayView;
                }
                obj = ((ViewScreenOpenCloseHelper) (bundle)).animationView;
                obj2 = ((ViewScreenOpenCloseHelper) (bundle)).animationData;
                obj3 = ((ViewScreenOpenCloseHelper) (bundle)).timelineItem;
                view1 = bundle.createAnimationHeader();
                obj.shouldDrawScrim = ((ViewScreenOpenCloseHelper) (bundle)).fullscreen;
                obj.item = ((TimelineItem) (obj3));
                obj.contentView = view;
                obj.animationData = ((EventInfoAnimationData) (obj2));
                obj.startRect = new Rect(((EventInfoAnimationView) (obj)).animationData.position);
                obj.chipReplacement = view1;
                ((EventInfoAnimationView) (obj)).chip.partitionInfo = new SimplePartitionItem(((EventInfoAnimationView) (obj)).item);
                ((EventInfoAnimationView) (obj)).chip.setViewModel(((EventInfoAnimationView) (obj)).animationData.getChipViewModel(((EventInfoAnimationView) (obj)).getContext(), ((EventInfoAnimationView) (obj)).item));
                ((EventInfoAnimationView) (obj)).headlineView.addView(((EventInfoAnimationView) (obj)).chipReplacement);
                ((EventInfoAnimationView) (obj)).headlineView.addView(((EventInfoAnimationView) (obj)).chip);
                if (((ViewScreenOpenCloseHelper) (bundle)).fullscreen)
                {
                    view = ((ViewScreenOpenCloseHelper) (bundle)).animationView;
                    obj = ((ViewScreenOpenCloseHelper) (bundle)).rootView.getContext();
                    if (((ViewScreenOpenCloseHelper) (bundle)).controller.getModel().hasImage(((Context) (obj))))
                    {
                        i = ((Context) (obj)).getResources().getDimensionPixelOffset(0x7f0e034b);
                    } else
                    {
                        i = ((Context) (obj)).getResources().getDimensionPixelOffset(0x7f0e024d);
                    }
                    view.startOpenAnimation(null, i + ((ViewScreenOpenCloseHelper) (bundle)).topSystemWindowInset, new ViewScreenOpenCloseHelper._cls1(bundle), bundle.getStatusbarAnimator());
                } else
                {
                    view = new ViewScreenOpenCloseHelper._cls2(bundle, (android.widget.FrameLayout.LayoutParams)((ViewScreenOpenCloseHelper) (bundle)).overlayView.getLayoutParams());
                    obj = new Rect();
                    ((ViewScreenOpenCloseHelper) (bundle)).controller.overlayBackground.startRectAnimation(((ViewScreenOpenCloseHelper) (bundle)).controller, ((Rect) (obj)));
                    obj2 = ((ViewScreenOpenCloseHelper) (bundle)).animationView;
                    obj3 = ((ViewScreenOpenCloseHelper) (bundle)).rootView.getContext();
                    if (((ViewScreenOpenCloseHelper) (bundle)).controller.getModel().hasImage(((Context) (obj3))))
                    {
                        i = ((Context) (obj3)).getResources().getDimensionPixelOffset(0x7f0e034b);
                    } else
                    {
                        i = ((Context) (obj3)).getResources().getDimensionPixelOffset(0x7f0e024d);
                    }
                    ((EventInfoAnimationView) (obj2)).startOpenAnimation(((Rect) (obj)), i + ((ViewScreenOpenCloseHelper) (bundle)).topSystemWindowInset, new ViewScreenOpenCloseHelper._cls3(bundle, view), bundle.getStatusbarAnimator());
                }
                (new Handler()).postDelayed(new ViewScreenOpenCloseHelper._cls4(bundle), 300L);
            }
        }
        continue; /* Loop/switch isn't completed */
_L2:
        animationHelper.initialize();
        if (shouldPulseEditButton)
        {
            bundle = viewScreen;
            view = bundle.findViewById(0x7f1002ad);
            if (view == null || view.getVisibility() != 0)
            {
                view = null;
            } else
            {
                view.setScaleX(0.9F);
                view.setScaleY(0.9F);
                obj = ObjectAnimator.ofPropertyValuesHolder(view, new PropertyValuesHolder[] {
                    PropertyValuesHolder.ofFloat("scaleX", new float[] {
                        1.1F
                    }), PropertyValuesHolder.ofFloat("scaleY", new float[] {
                        1.1F
                    })
                });
                ((ObjectAnimator) (obj)).setInterpolator(new FastOutSlowInInterpolator());
                ((ObjectAnimator) (obj)).setDuration(750L);
                ((ObjectAnimator) (obj)).setRepeatCount(-1);
                ((ObjectAnimator) (obj)).setRepeatMode(2);
                view.setRotation(0.0F);
                obj2 = ObjectAnimator.ofPropertyValuesHolder(view, new PropertyValuesHolder[] {
                    PropertyValuesHolder.ofFloat("rotation", new float[] {
                        7F
                    })
                });
                ((ObjectAnimator) (obj2)).setInterpolator(new WiggleInterpolator());
                ((ObjectAnimator) (obj2)).setDuration(1500L);
                ((ObjectAnimator) (obj2)).setStartDelay(-675L);
                ((ObjectAnimator) (obj2)).setRepeatCount(-1);
                ((ObjectAnimator) (obj2)).setRepeatMode(1);
                view = new AnimatorSet();
                view.playTogether(new Animator[] {
                    obj, obj2
                });
            }
            bundle.editButtonPulseAnimator = view;
            ((ViewScreen) (bundle)).editButtonPulseAnimator.start();
        }
        continue; /* Loop/switch isn't completed */
_L8:
        bundle.openingRequested = true;
        if (!((ViewScreenOpenCloseHelper) (bundle)).openingRequested || ((ViewScreenOpenCloseHelper) (bundle)).topSystemWindowInset == -1) goto _L6; else goto _L9
_L9:
        bundle.openingRequested = false;
        view = ((ViewScreenOpenCloseHelper) (bundle)).controller;
        EventInfoAnimationView eventinfoanimationview;
        EventInfoAnimationData eventinfoanimationdata;
        TimelineItem timelineitem;
        View view2;
        if (((Fragment) (view)).mHost != null && ((Fragment) (view)).mAdded)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L6; else goto _L10
_L10:
        ((ViewScreenOpenCloseHelper) (bundle)).controller.latencyLogger.markAt(32);
        if (((ViewScreenOpenCloseHelper) (bundle)).fullscreen)
        {
            view = null;
        } else
        {
            view = ((ViewScreenOpenCloseHelper) (bundle)).overlayView;
        }
        eventinfoanimationview = ((ViewScreenOpenCloseHelper) (bundle)).animationView;
        eventinfoanimationdata = ((ViewScreenOpenCloseHelper) (bundle)).animationData;
        timelineitem = ((ViewScreenOpenCloseHelper) (bundle)).timelineItem;
        view2 = bundle.createAnimationHeader();
        eventinfoanimationview.shouldDrawScrim = ((ViewScreenOpenCloseHelper) (bundle)).fullscreen;
        eventinfoanimationview.item = timelineitem;
        eventinfoanimationview.contentView = view;
        eventinfoanimationview.animationData = eventinfoanimationdata;
        eventinfoanimationview.startRect = new Rect(eventinfoanimationview.animationData.position);
        eventinfoanimationview.chipReplacement = view2;
        eventinfoanimationview.chip.partitionInfo = new SimplePartitionItem(eventinfoanimationview.item);
        eventinfoanimationview.chip.setViewModel(eventinfoanimationview.animationData.getChipViewModel(eventinfoanimationview.getContext(), eventinfoanimationview.item));
        eventinfoanimationview.headlineView.addView(eventinfoanimationview.chipReplacement);
        eventinfoanimationview.headlineView.addView(eventinfoanimationview.chip);
        if (((ViewScreenOpenCloseHelper) (bundle)).fullscreen)
        {
            view = ((ViewScreenOpenCloseHelper) (bundle)).animationView;
            Context context = ((ViewScreenOpenCloseHelper) (bundle)).rootView.getContext();
            if (((ViewScreenOpenCloseHelper) (bundle)).controller.getModel().hasImage(context))
            {
                i = context.getResources().getDimensionPixelOffset(0x7f0e034b);
            } else
            {
                i = context.getResources().getDimensionPixelOffset(0x7f0e024d);
            }
            view.startOpenAnimation(null, i + ((ViewScreenOpenCloseHelper) (bundle)).topSystemWindowInset, new ViewScreenOpenCloseHelper._cls1(bundle), bundle.getStatusbarAnimator());
        } else
        {
            view = new ViewScreenOpenCloseHelper._cls2(bundle, (android.widget.FrameLayout.LayoutParams)((ViewScreenOpenCloseHelper) (bundle)).overlayView.getLayoutParams());
            Rect rect = new Rect();
            ((ViewScreenOpenCloseHelper) (bundle)).controller.overlayBackground.startRectAnimation(((ViewScreenOpenCloseHelper) (bundle)).controller, rect);
            EventInfoAnimationView eventinfoanimationview1 = ((ViewScreenOpenCloseHelper) (bundle)).animationView;
            Context context1 = ((ViewScreenOpenCloseHelper) (bundle)).rootView.getContext();
            int j;
            if (((ViewScreenOpenCloseHelper) (bundle)).controller.getModel().hasImage(context1))
            {
                j = context1.getResources().getDimensionPixelOffset(0x7f0e034b);
            } else
            {
                j = context1.getResources().getDimensionPixelOffset(0x7f0e024d);
            }
            eventinfoanimationview1.startOpenAnimation(rect, j + ((ViewScreenOpenCloseHelper) (bundle)).topSystemWindowInset, new ViewScreenOpenCloseHelper._cls3(bundle, view), bundle.getStatusbarAnimator());
        }
        (new Handler()).postDelayed(new ViewScreenOpenCloseHelper._cls4(bundle), 300L);
        return;
        if (true) goto _L12; else goto _L11
_L11:
    }

    protected final void onViewUpdated()
    {
        if (!isFullScreen(requireContext().getResources()) && super.mView != null)
        {
            if (viewScreen.getVisibility() == 8)
            {
                viewScreen.setVisibility(4);
            }
            ViewTreeObserver viewtreeobserver = viewScreen.getViewTreeObserver();
            if (overlayBackground == com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayBackground.Unknown)
            {
                getLoadingBackground().setToCard(this);
                viewScreen.requestLayout();
                if (viewtreeobserver != null)
                {
                    viewtreeobserver.addOnGlobalLayoutListener(new _cls2());
                }
            } else
            if (viewtreeobserver != null)
            {
                viewtreeobserver.addOnPreDrawListener(new com.google.android.calendar.common.view.overlay.OverlayFragment.PreDrawListener(this, viewScreen));
                return;
            }
        }
    }

    public final void repositionDialog()
    {
        if (viewScreen != null && !isFullScreen(requireContext().getResources()))
        {
            viewScreen.requestLayout();
            ViewTreeObserver viewtreeobserver = viewScreen.getViewTreeObserver();
            if (viewtreeobserver != null)
            {
                viewtreeobserver.addOnGlobalLayoutListener(new _cls3());
                return;
            }
        }
    }

    public boolean setDelayedAction(TimelineItem timelineitem, int i)
    {
        DelayedActionDescription delayedactiondescription = new DelayedActionDescription(timelineitem, i);
        if (super.mHost == null)
        {
            timelineitem = null;
        } else
        {
            timelineitem = (FragmentActivity)super.mHost.mActivity;
        }
        if (!(timelineitem instanceof DelayedActionPerformer) || !((DelayedActionPerformer)timelineitem).shouldDelayAction(delayedactiondescription))
        {
            return false;
        } else
        {
            delayedAction = delayedactiondescription;
            return true;
        }
    }

    public final void setLoader(Loader loader1)
    {
        if (loader != null)
        {
            loader.setCallback(null);
        }
        loader = loader1;
        loader.setCallback(this);
    }

    public final void setupCommandBarController()
    {
        commandBarController = createCommandBarController(moreOptionsSheetController);
        ViewScreen viewscreen = viewScreen;
        Object obj = commandBarController;
        if (obj != null)
        {
            ViewGroup viewgroup = (ViewGroup)viewscreen.findViewById(0x7f1002af);
            viewgroup.removeAllViews();
            obj.commandBar = ((BottomBarController) (obj)).inflateCommandBar(viewscreen.getContext(), viewgroup);
            ((BottomBarController) (obj)).commandBar.initialize(((BottomBarController) (obj)).getActionsLayout(), ((BottomBarController) (obj)).getPrimaryActionIds());
            ((BottomBarController) (obj)).commandBar.setVisibility(4);
            ((BottomBarController) (obj)).commandBar.listener = ((com.google.android.calendar.newapi.commandbar.BottomBar.OnCommandBarActionClickListener) (obj));
            ((BottomBarController) (obj)).setupCommandBar(((BottomBarController) (obj)).commandBar);
            viewscreen.commandBar = ((BottomBarController) (obj)).commandBar;
            obj = viewscreen.commandBar;
            ViewScreen..Lambda._cls0 _lcls0 = new ViewScreen..Lambda._cls0(viewscreen);
            obj.onHeightChangedListener = _lcls0;
            ((BottomBar) (obj)).addOnLayoutChangeListener(new com.google.android.calendar.newapi.commandbar.BottomBar..Lambda._cls0(_lcls0));
            viewgroup.addView(viewscreen.commandBar);
        }
    }

    public final void setupOverflowMenuController()
    {
        ViewScreen viewscreen;
        OverflowMenuController overflowmenucontroller;
label0:
        {
            overflowMenuController = createOverflowMenuController();
            if (RemoteFeatureConfig.EVERYONE_DECLINED.enabled())
            {
                FeatureConfig featureconfig = Features.instance;
                if (featureconfig == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                }
                ((FeatureConfig)featureconfig).dogfood_features();
            }
            viewscreen = viewScreen;
            overflowmenucontroller = overflowMenuController;
            if (viewscreen.overflowMenuView != null)
            {
                ViewGroup viewgroup = (ViewGroup)viewscreen.overflowMenuView.getParent();
                if (viewgroup != null)
                {
                    viewgroup.removeView(viewscreen.overflowMenuView);
                }
            }
            if (overflowmenucontroller != null)
            {
                boolean flag;
                if (overflowmenucontroller.getMenuResourceId() != 0)
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
            return;
        }
        ViewGroup viewgroup1 = (ViewGroup)viewscreen.findViewById(0x7f1002ac);
        if (viewgroup1 == null)
        {
            LogUtils.wtf(ViewScreen.TAG, "Failed to find overflow_container for %s", new Object[] {
                overflowmenucontroller.getClass().getName()
            });
            return;
        } else
        {
            Context context = viewscreen.getContext();
            int i = overflowmenucontroller.getMenuResourceId();
            View view = LayoutInflater.from(context).inflate(0x7f0500df, viewgroup1, false);
            ((com.google.android.calendar.newapi.overflow.OverflowMenuCompat.OverflowMenu)view).init(i);
            ((com.google.android.calendar.newapi.overflow.OverflowMenuCompat.OverflowMenu)view).setCallback(overflowmenucontroller);
            overflowmenucontroller.context = context;
            overflowmenucontroller.overflowMenu = (com.google.android.calendar.newapi.overflow.OverflowMenuCompat.OverflowMenu)view;
            viewscreen.overflowMenuView = view;
            viewgroup1.addView(viewscreen.overflowMenuView);
            viewscreen.insetApplier.addView(viewgroup1, 2, 1);
            ViewCompat.requestApplyInsets(viewscreen);
            return;
        }
    }

    public abstract void showEditScreen();

    public final void updateCommandBar()
    {
        if (commandBarController != null)
        {
            BottomBarController bottombarcontroller = commandBarController;
            ViewScreenModel viewscreenmodel = getModel();
            bottombarcontroller.model = viewscreenmodel;
            bottombarcontroller.onModelChanged(viewscreenmodel);
            viewScreen.adjustExtraCommandBarPadding();
        }
    }

    public void updateSegments()
    {
        viewScreen.updateEditButton();
        viewScreen.updateSegmentViews();
    }


    private class _cls1 extends com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayDialog
    {

        private final ViewScreenController this$0;

        public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
        {
            if (accessibilityevent.getEventType() == 32)
            {
                List list = accessibilityevent.getText();
                Object obj = ViewScreenController.this;
                accessibilityevent = ((OverlayFragment) (obj)).getTitle();
                obj = ((ViewScreenController) (obj)).getModel().getTitle();
                if (!TextUtils.isEmpty(((CharSequence) (obj))))
                {
                    accessibilityevent = (new StringBuilder(String.valueOf(accessibilityevent).length() + 1 + String.valueOf(obj).length())).append(accessibilityevent).append("\n").append(((String) (obj))).toString();
                }
                list.add(accessibilityevent);
                return true;
            } else
            {
                return super.dispatchPopulateAccessibilityEvent(accessibilityevent);
            }
        }

        _cls1(Context context)
        {
            this$0 = ViewScreenController.this;
            super(ViewScreenController.this, context);
        }
    }


    private class _cls2
        implements android.view.ViewTreeObserver.OnGlobalLayoutListener
    {

        private final ViewScreenController this$0;

        public final void onGlobalLayout()
        {
            viewScreen.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            onViewUpdated();
        }

        _cls2()
        {
            this$0 = ViewScreenController.this;
            super();
        }
    }


    private class _cls3
        implements android.view.ViewTreeObserver.OnGlobalLayoutListener
    {

        private final ViewScreenController this$0;

        public final void onGlobalLayout()
        {
            viewScreen.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            repositionDialog();
        }

        _cls3()
        {
            this$0 = ViewScreenController.this;
            super();
        }
    }

}
