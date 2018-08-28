// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.animation.ValueAnimator;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.timely.BackgroundImagesFrame;
import com.google.android.calendar.utils.AccessibilityUtils;
import com.google.common.collect.Range;

// Referenced classes of package com.google.android.calendar:
//            AllInOneCalendarActivity, MiniMonthInteractionController

final class _cls1
    implements android.view.PickerListener, android.view.PickerListener, android.view.PickerListener
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
        android.support.v4.app.Fragment fragment = ((AllInOneCalendarActivity) (controller)).getMainFragment();
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
            private final AllInOneCalendarActivity.OnMiniMonthDatePickerListener this$1;
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
                        AllInOneCalendarActivity.OnMiniMonthDatePickerListener onminimonthdatepickerlistener = AllInOneCalendarActivity.OnMiniMonthDatePickerListener.this;
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
                this$1 = AllInOneCalendarActivity.OnMiniMonthDatePickerListener.this;
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
    //                   66 22;
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
    //                   0 134
    //                   1 405
    //                   2 320
    //                   3 405
    //                   4 116
    //                   5 116
    //                   6 415;
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

            private final AllInOneCalendarActivity.OnMiniMonthDatePickerListener this$1;
            private final boolean val$visible;

            public final void onAnimationEnd(Animator animator)
            {
                animator = this$0;
                onDatePickerVisibilityChangeDone(visible);
            }

            _cls3()
            {
                this$1 = AllInOneCalendarActivity.OnMiniMonthDatePickerListener.this;
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

                private final AllInOneCalendarActivity.OnMiniMonthDatePickerListener arg$1;

                public final void onAnimationUpdate(ValueAnimator valueanimator1)
                {
                    arg$1.setDatePickerBottom(((Float)valueanimator1.getAnimatedValue()).floatValue(), true);
                }

            .Lambda._cls0()
            {
                arg$1 = AllInOneCalendarActivity.OnMiniMonthDatePickerListener.this;
            }
            }

            valueanimator.addUpdateListener(new .Lambda._cls0());
        } else
        {
            class .Lambda._cls1
                implements android.animation.ValueAnimator.AnimatorUpdateListener
            {

                private final AllInOneCalendarActivity.OnMiniMonthDatePickerListener arg$1;

                public final void onAnimationUpdate(ValueAnimator valueanimator1)
                {
                    arg$1.setDatePickerRight(((Float)valueanimator1.getAnimatedValue()).floatValue());
                }

            .Lambda._cls1()
            {
                arg$1 = AllInOneCalendarActivity.OnMiniMonthDatePickerListener.this;
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

    public _cls1()
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

            _cls1(AllInOneCalendarActivity allinonecalendaractivity)
            {
            }
        }

        tapDetector = new GestureDetector(AllInOneCalendarActivity.this, new _cls1(AllInOneCalendarActivity.this));
    }
}
