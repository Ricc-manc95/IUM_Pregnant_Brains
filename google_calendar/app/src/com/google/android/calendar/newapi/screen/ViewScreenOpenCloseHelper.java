// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.calendar.common.view.overlay.OverlayFragment;
import com.google.android.calendar.event.image.helper.ImageHelper;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.animations.EventInfoAnimationData;
import com.google.android.calendar.timely.animations.EventInfoAnimationView;
import com.google.android.calendar.utils.statusbar.StatusbarAnimatorCompat;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            ViewScreenController, ViewScreen

public final class ViewScreenOpenCloseHelper
{

    public EventInfoAnimationData animationData;
    public EventInfoAnimationView animationView;
    public final ViewScreen contentView;
    public final ViewScreenController controller;
    public final boolean fullscreen;
    public boolean openingRequested;
    public final View overlayView;
    public final View rootView;
    public final TimelineItem timelineItem;
    public int topSystemWindowInset;

    public ViewScreenOpenCloseHelper(ViewScreenController viewscreencontroller, TimelineItem timelineitem, EventInfoAnimationData eventinfoanimationdata)
    {
        topSystemWindowInset = -1;
        controller = viewscreencontroller;
        contentView = controller.viewScreen;
        animationData = eventinfoanimationdata;
        fullscreen = controller.isFullScreen(controller.requireContext().getResources());
        rootView = ((Fragment) (controller)).mView;
        if (fullscreen)
        {
            overlayView = rootView;
        } else
        {
            overlayView = rootView.findViewById(0x7f1002a6);
        }
        timelineItem = timelineitem;
    }

    final View createAnimationHeader()
    {
        if (controller.getModel().hasImage(rootView.getContext()) && timelineItem.hasImage())
        {
            LayoutInflater layoutinflater = LayoutInflater.from(rootView.getContext());
            return (new ImageHelper(rootView.getContext(), timelineItem, null, layoutinflater, true)).view;
        } else
        {
            View view = new View(rootView.getContext());
            view.setBackgroundColor(controller.getModel().getColor(rootView.getContext()));
            return view;
        }
    }

    final StatusbarAnimatorCompat getStatusbarAnimator()
    {
        if (fullscreen && controller.getDialog() != null)
        {
            return StatusbarAnimatorCompat.createInstance(controller.getDialog().getWindow());
        } else
        {
            return null;
        }
    }

    public final void initialize()
    {
        boolean flag;
        if (animationData != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        Object obj = getStatusbarAnimator();
        if (obj != null)
        {
            ((StatusbarAnimatorCompat) (obj)).setLightStatusbar(true);
            ((StatusbarAnimatorCompat) (obj)).setStatusbarColor(rootView.getContext().getResources().getColor(0x7f0d0143));
        }
        if (fullscreen)
        {
            obj = rootView.findViewById(0x7f1002a6);
            if (obj != null)
            {
                ((View) (obj)).setVisibility(0);
            }
        }
        animationView = new EventInfoAnimationView(rootView.getContext());
        animationView.setVisibility(8);
        ((FrameLayout)rootView).addView(animationView, 0);
        animationView.setBackgroundColor(0);
        obj = new android.widget.FrameLayout.LayoutParams(-1, -1);
        animationView.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
        rootView.setSystemUiVisibility(1280);
        if (!fullscreen) goto _L4; else goto _L3
_L3:
        class .Lambda._cls0
            implements OnApplyWindowInsetsListener
        {

            private final ViewScreenOpenCloseHelper arg$1;

            public final WindowInsetsCompat onApplyWindowInsets(final View onAnimationEnd, WindowInsetsCompat windowinsetscompat)
            {
                ViewScreenOpenCloseHelper viewscreenopenclosehelper = arg$1;
                viewscreenopenclosehelper.topSystemWindowInset = ((WindowInsets)windowinsetscompat.mInsets).getSystemWindowInsetTop();
                if (viewscreenopenclosehelper.openingRequested && viewscreenopenclosehelper.topSystemWindowInset != -1)
                {
                    viewscreenopenclosehelper.openingRequested = false;
                    onAnimationEnd = viewscreenopenclosehelper.controller;
                    int i;
                    if (((Fragment) (onAnimationEnd)).mHost != null && ((Fragment) (onAnimationEnd)).mAdded)
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    if (i != 0)
                    {
                        viewscreenopenclosehelper.controller.latencyLogger.markAt(32);
                        EventInfoAnimationView eventinfoanimationview;
                        EventInfoAnimationData eventinfoanimationdata;
                        TimelineItem timelineitem;
                        View view;
                        if (viewscreenopenclosehelper.fullscreen)
                        {
                            onAnimationEnd = null;
                        } else
                        {
                            onAnimationEnd = viewscreenopenclosehelper.overlayView;
                        }
                        eventinfoanimationview = viewscreenopenclosehelper.animationView;
                        eventinfoanimationdata = viewscreenopenclosehelper.animationData;
                        timelineitem = viewscreenopenclosehelper.timelineItem;
                        view = viewscreenopenclosehelper.createAnimationHeader();
                        eventinfoanimationview.shouldDrawScrim = viewscreenopenclosehelper.fullscreen;
                        eventinfoanimationview.item = timelineitem;
                        eventinfoanimationview.contentView = onAnimationEnd;
                        eventinfoanimationview.animationData = eventinfoanimationdata;
                        eventinfoanimationview.startRect = new Rect(eventinfoanimationview.animationData.position);
                        eventinfoanimationview.chipReplacement = view;
                        eventinfoanimationview.chip.partitionInfo = new SimplePartitionItem(eventinfoanimationview.item);
                        eventinfoanimationview.chip.setViewModel(eventinfoanimationview.animationData.getChipViewModel(eventinfoanimationview.getContext(), eventinfoanimationview.item));
                        eventinfoanimationview.headlineView.addView(eventinfoanimationview.chipReplacement);
                        eventinfoanimationview.headlineView.addView(eventinfoanimationview.chip);
                        if (viewscreenopenclosehelper.fullscreen)
                        {
                            onAnimationEnd = viewscreenopenclosehelper.animationView;
                            Context context = viewscreenopenclosehelper.rootView.getContext();
                            if (viewscreenopenclosehelper.controller.getModel().hasImage(context))
                            {
                                i = context.getResources().getDimensionPixelOffset(0x7f0e034b);
                            } else
                            {
                                i = context.getResources().getDimensionPixelOffset(0x7f0e024d);
                            }
                            onAnimationEnd.startOpenAnimation(null, i + viewscreenopenclosehelper.topSystemWindowInset, viewscreenopenclosehelper. new _cls1(), viewscreenopenclosehelper.getStatusbarAnimator());
                        } else
                        {
                            onAnimationEnd = viewscreenopenclosehelper. new _cls2();
                            Rect rect = new Rect();
                            viewscreenopenclosehelper.controller.overlayBackground.startRectAnimation(viewscreenopenclosehelper.controller, rect);
                            EventInfoAnimationView eventinfoanimationview1 = viewscreenopenclosehelper.animationView;
                            Context context1 = viewscreenopenclosehelper.rootView.getContext();
                            int j;
                            if (viewscreenopenclosehelper.controller.getModel().hasImage(context1))
                            {
                                j = context1.getResources().getDimensionPixelOffset(0x7f0e034b);
                            } else
                            {
                                j = context1.getResources().getDimensionPixelOffset(0x7f0e024d);
                            }
                            eventinfoanimationview1.startOpenAnimation(rect, j + viewscreenopenclosehelper.topSystemWindowInset, viewscreenopenclosehelper. new _cls3(), viewscreenopenclosehelper.getStatusbarAnimator());
                        }
                        (new Handler()).postDelayed(viewscreenopenclosehelper. new _cls4(), 300L);
                        return windowinsetscompat;
                    }
                }
                return windowinsetscompat;
            }

            .Lambda._cls0()
            {
                arg$1 = ViewScreenOpenCloseHelper.this;
            }

            private class _cls1 extends AnimatorListenerAdapter
            {

                private final ViewScreenOpenCloseHelper this$0;

                public final void onAnimationEnd(Animator animator)
                {
                    controller.notifyAnimationFinished(null);
                }

                public final void onAnimationStart(Animator animator)
                {
                    rootView.setBackgroundResource(0x106000d);
                    animationView.setVisibility(0);
                }

                _cls1()
                {
                    this$0 = ViewScreenOpenCloseHelper.this;
                    super();
                }
            }


            private class _cls4
                implements Runnable
            {

                private final ViewScreenOpenCloseHelper this$0;

                public final void run()
                {
                    ViewScreenController viewscreencontroller = controller;
                    boolean flag;
                    if (((Fragment) (viewscreencontroller)).mHost != null && ((Fragment) (viewscreencontroller)).mAdded)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    while (!flag || contentView == null || !fullscreen) 
                    {
                        return;
                    }
                    rootView.setBackgroundResource(0x7f0d021b);
                }

                _cls4()
                {
                    this$0 = ViewScreenOpenCloseHelper.this;
                    super();
                }
            }


            private class _cls2
                implements Runnable
            {

                private final ViewScreenOpenCloseHelper this$0;
                private final android.widget.FrameLayout.LayoutParams val$layoutParams;

                public final void run()
                {
                    ViewScreenController viewscreencontroller = controller;
                    boolean flag;
                    if (((Fragment) (viewscreencontroller)).mHost != null && ((Fragment) (viewscreencontroller)).mAdded)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        animationView.setVisibility(8);
                        overlayView.setLayoutParams(layoutParams);
                        overlayView.setTranslationX(0.0F);
                        overlayView.setTranslationY(0.0F);
                        controller.overlayBackground.setToCard(controller);
                    }
                }

                _cls2()
                {
                    this$0 = ViewScreenOpenCloseHelper.this;
                    layoutParams = layoutparams;
                    super();
                }
            }


            private class _cls3 extends AnimatorListenerAdapter
            {

                private final ViewScreenOpenCloseHelper this$0;
                private final Runnable val$onAnimationEnd;

                public final void onAnimationEnd(Animator animator)
                {
                    controller.notifyAnimationFinished(onAnimationEnd);
                }

                public final void onAnimationStart(Animator animator)
                {
                    super.onAnimationStart(animator);
                    animationView.setVisibility(0);
                    overlayView.setVisibility(0);
                }

                _cls3()
                {
                    this$0 = ViewScreenOpenCloseHelper.this;
                    onAnimationEnd = runnable;
                    super();
                }
            }

        }

        ViewCompat.setOnApplyWindowInsetsListener(animationView, new .Lambda._cls0());
        ViewCompat.requestApplyInsets(animationView);
_L6:
        return;
_L4:
        topSystemWindowInset = 0;
        return;
_L2:
        if (fullscreen)
        {
            rootView.setBackgroundColor(-1);
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final void showContentInstantly()
    {
        View view = rootView.findViewById(0x7f1002a6);
        if (view != null)
        {
            view.setVisibility(0);
        }
        view = rootView.findViewById(0x7f1002af);
        if (view != null)
        {
            view.setVisibility(0);
        }
        view = contentView.findViewById(0x7f1002a9);
        if (view != null)
        {
            view.setVisibility(0);
        }
        view.setBackgroundColor(-1);
    }
}
