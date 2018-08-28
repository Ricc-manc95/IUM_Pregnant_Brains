// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RotateDrawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementAttacher;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementHolder;
import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.calendar.groove.GrooveUtils;
import com.google.android.calendar.newapi.segment.ooo.OooUtils;
import com.google.android.calendar.utils.AccessibilityUtils;
import com.google.android.calendar.utils.animation.QuantumInterpolators;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SpeedDialLayout extends FrameLayout
    implements android.view.View.OnFocusChangeListener
{
    public static interface EndSpeedDialFadeOut
    {

        public abstract void onEndSpeedDialFadeOut();
    }

    public static interface SpeedDialActivity
    {

        public abstract ImageButton getFloatingActionButton();

        public abstract void onCreateEventClicked();

        public abstract void onCreateGrooveClicked();

        public abstract void onCreateOooClicked();

        public abstract void onCreateTaskClicked();
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/SpeedDialLayout);
    public Animator currentAnimator;
    public EndSpeedDialFadeOut endSpeedDialFadeOutListener;
    public View eventView;
    public View fadeLayer;
    public boolean isExpanded;
    private boolean isInAccessibilityMode;
    public WeakReference speedDialActivity;
    private ViewGroup speedDialContents;

    public SpeedDialLayout(Context context)
    {
        super(context);
        init(context);
    }

    public SpeedDialLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        init(context);
    }

    private static void addRotationAnimation(Collection collection, ImageView imageview, boolean flag, int i)
    {
        char c = '\u2710';
        if (imageview != null)
        {
            if ((imageview = imageview.getDrawable()) instanceof RotateDrawable)
            {
                int j;
                if (flag)
                {
                    j = 0;
                } else
                {
                    j = 10000;
                }
                imageview.setLevel(j);
                if (flag)
                {
                    j = 0;
                } else
                {
                    j = 10000;
                }
                if (!flag)
                {
                    c = '\0';
                }
                imageview = ObjectAnimator.ofInt(imageview, "level", new int[] {
                    j, c
                });
                imageview.setDuration(i);
                imageview.setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
                collection.add(imageview);
                return;
            }
        }
    }

    private final Animator createAnimator(final boolean fadeIn)
    {
        ArrayList arraylist = new ArrayList();
        Object obj1 = getResources();
        int i1 = ((Resources) (obj1)).getInteger(0x7f11002f);
        int j1 = ((Resources) (obj1)).getInteger(0x7f110030);
        float f;
        Object obj;
        final ArrayList viewsWithLayer;
        int i;
        if (fadeIn)
        {
            i = i1;
        } else
        {
            i = j1;
        }
        if (fadeIn)
        {
            obj = QuantumInterpolators.LINEAR_OUT_SLOW_IN;
        } else
        {
            obj = QuantumInterpolators.FAST_OUT_LINEAR_IN;
        }
        if (speedDialActivity.get() != null)
        {
            addRotationAnimation(arraylist, ((SpeedDialActivity)speedDialActivity.get()).getFloatingActionButton(), fadeIn, i);
        }
        addRotationAnimation(arraylist, (ImageView)eventView.findViewById(0x7f100367), fadeIn, i);
        f = 0.0F;
        viewsWithLayer = new ArrayList();
        if (!isInAccessibilityMode)
        {
            int j = speedDialContents.getChildCount() - 1;
            long l2 = 0L;
            while (j >= 0) 
            {
                Object obj4 = speedDialContents.getChildAt(j);
                float f1;
                float f3;
                Object obj2;
                Object obj3;
                int k;
                int l;
                int k1;
                long l4;
                if (obj4 == eventView)
                {
                    k = 1;
                } else
                {
                    k = 0;
                }
                obj3 = ((View) (obj4)).findViewById(0x7f100366);
                obj2 = (ImageView)((View) (obj4)).findViewById(0x7f100367);
                viewsWithLayer.add(obj3);
                viewsWithLayer.add(obj2);
                ((View) (obj4)).setClickable(fadeIn);
                if (k != 0)
                {
                    f1 = 0.0F;
                    f = 0.0F;
                } else
                {
                    l = ((Resources) (obj1)).getDimensionPixelSize(0x7f0e0373);
                    int l1 = ((Resources) (obj1)).getDimensionPixelOffset(0x7f0e0374);
                    f = (float)(-l) + f;
                    f1 = (float)l1 + f;
                }
                if (fadeIn)
                {
                    ((View) (obj4)).setTranslationY(f1);
                    obj4 = ObjectAnimator.ofFloat(obj4, "translationY", new float[] {
                        f1, f
                    });
                    ((ObjectAnimator) (obj4)).setStartDelay(l2);
                    ((ObjectAnimator) (obj4)).setDuration(i);
                    ((ObjectAnimator) (obj4)).setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
                    arraylist.add(obj4);
                }
                if (fadeIn)
                {
                    f1 = 0.0F;
                } else
                {
                    f1 = 1.0F;
                }
                ((View) (obj3)).setAlpha(f1);
                if (fadeIn)
                {
                    f1 = 0.0F;
                } else
                {
                    f1 = 1.0F;
                }
                if (fadeIn)
                {
                    f3 = 1.0F;
                } else
                {
                    f3 = 0.0F;
                }
                obj3 = ObjectAnimator.ofFloat(obj3, "alpha", new float[] {
                    f1, f3
                });
                k1 = ((Resources) (obj1)).getInteger(0x7f110031);
                if (fadeIn)
                {
                    l = k1;
                } else
                {
                    l = 0;
                }
                ((ObjectAnimator) (obj3)).setStartDelay((long)l + l2);
                if (fadeIn)
                {
                    l4 = i1 - k1;
                } else
                {
                    l4 = j1;
                }
                ((ObjectAnimator) (obj3)).setDuration(l4);
                ((ObjectAnimator) (obj3)).setInterpolator(((android.animation.TimeInterpolator) (obj)));
                arraylist.add(obj3);
                if (fadeIn)
                {
                    f1 = 0.0F;
                } else
                {
                    f1 = 1.0F;
                }
                ((ImageView) (obj2)).setAlpha(f1);
                if (fadeIn)
                {
                    f1 = 0.0F;
                } else
                {
                    f1 = 1.0F;
                }
                if (fadeIn)
                {
                    f3 = 1.0F;
                } else
                {
                    f3 = 0.0F;
                }
                obj3 = ObjectAnimator.ofFloat(obj2, "alpha", new float[] {
                    f1, f3
                });
                ((ObjectAnimator) (obj3)).setStartDelay(l2);
                ((ObjectAnimator) (obj3)).setDuration(i);
                ((ObjectAnimator) (obj3)).setInterpolator(((android.animation.TimeInterpolator) (obj)));
                arraylist.add(obj3);
                if (fadeIn && k == 0)
                {
                    ((ImageView) (obj2)).setScaleX(0.1F);
                    ((ImageView) (obj2)).setScaleY(0.1F);
                    ObjectAnimator objectanimator = ObjectAnimator.ofFloat(obj2, "scaleX", new float[] {
                        0.1F, 1.0F
                    });
                    obj2 = ObjectAnimator.ofFloat(obj2, "scaleY", new float[] {
                        0.1F, 1.0F
                    });
                    objectanimator.setStartDelay(l2);
                    ((ObjectAnimator) (obj2)).setStartDelay(l2);
                    objectanimator.setDuration(i);
                    ((ObjectAnimator) (obj2)).setDuration(i);
                    objectanimator.setInterpolator(QuantumInterpolators.LINEAR_OUT_SLOW_IN);
                    ((ObjectAnimator) (obj2)).setInterpolator(QuantumInterpolators.LINEAR_OUT_SLOW_IN);
                    arraylist.add(objectanimator);
                    arraylist.add(obj2);
                }
                k = ((Resources) (obj1)).getInteger(0x7f11002e);
                if (fadeIn)
                {
                    l4 = k;
                } else
                {
                    l4 = 0L;
                }
                j--;
                l2 = l4 + l2;
            }
        }
        viewsWithLayer.add(fadeLayer);
        obj1 = fadeLayer;
        float f2;
        long l3;
        if (fadeIn)
        {
            f = 0.0F;
        } else
        {
            f = 1.0F;
        }
        ((View) (obj1)).setAlpha(f);
        obj1 = fadeLayer;
        if (fadeIn)
        {
            f = 0.0F;
        } else
        {
            f = 1.0F;
        }
        if (fadeIn)
        {
            f2 = 1.0F;
        } else
        {
            f2 = 0.0F;
        }
        obj1 = ObjectAnimator.ofFloat(obj1, "alpha", new float[] {
            f, f2
        });
        if (fadeIn)
        {
            l3 = i1;
        } else
        {
            l3 = j1;
        }
        ((ObjectAnimator) (obj1)).setDuration(l3);
        ((ObjectAnimator) (obj1)).setInterpolator(((android.animation.TimeInterpolator) (obj)));
        arraylist.add(obj1);
        obj = new AnimatorSet();
        ((AnimatorSet) (obj)).playTogether(arraylist);
        ((AnimatorSet) (obj)).addListener(new _cls1());
        return ((Animator) (obj));
    }

    private final void init(Context context)
    {
        Object obj;
        View view1;
        Object obj1;
        FeatureConfig featureconfig;
        boolean flag;
        flag = false;
        isInAccessibilityMode = AccessibilityUtils.isAccessibilityEnabled(context);
        obj = (LayoutInflater)context.getSystemService("layout_inflater");
        int i;
        if (isInAccessibilityMode)
        {
            i = 0x7f050160;
        } else
        {
            i = 0x7f050162;
        }
        ((LayoutInflater) (obj)).inflate(i, this);
        fadeLayer = findViewById(0x7f100368);
        speedDialContents = (ViewGroup)findViewById(0x7f100360);
        eventView = findViewById(0x7f100361);
        obj = VisualElementHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
        }
        ((VisualElementAttacher)obj).attachCreateEventButton(eventView);
        eventView.setOnFocusChangeListener(this);
        obj = findViewById(0x7f100362);
        ((View) (obj)).setOnFocusChangeListener(this);
        view1 = findViewById(0x7f100363);
        view1.setOnFocusChangeListener(this);
        obj1 = findViewById(0x7f100365);
        featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        if (!((FeatureConfig)featureconfig).ooo()) goto _L2; else goto _L1
_L1:
        if (OooUtils.getDefaultCalendar() != null)
        {
            ((View) (obj1)).setOnFocusChangeListener(this);
            class .Lambda._cls0
                implements android.view.View.OnClickListener
            {

                private final SpeedDialLayout arg$1;

                public final void onClick(View view2)
                {
                    view2 = arg$1;
                    view2.setExpanded(false, 300L);
                    if (((SpeedDialLayout) (view2)).speedDialActivity.get() != null)
                    {
                        ((SpeedDialActivity)((SpeedDialLayout) (view2)).speedDialActivity.get()).onCreateOooClicked();
                    }
                }

            .Lambda._cls0()
            {
                arg$1 = SpeedDialLayout.this;
            }
            }

            ((View) (obj1)).setOnClickListener(new .Lambda._cls0());
        } else
        {
            speedDialContents.removeView(((View) (obj1)));
        }
_L4:
        obj1 = CalendarListEntryCache.instance;
        if (obj1 == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        break; /* Loop/switch isn't completed */
_L2:
        if (obj1 != null)
        {
            speedDialContents.removeView(((View) (obj1)));
        }
        if (true) goto _L4; else goto _L3
_L3:
        class .Lambda._cls1
            implements android.view.View.OnClickListener
        {

            private final SpeedDialLayout arg$1;

            public final void onClick(View view2)
            {
                view2 = arg$1;
                view2.setExpanded(false, 300L);
                if (((SpeedDialLayout) (view2)).speedDialActivity.get() != null)
                {
                    ((SpeedDialActivity)((SpeedDialLayout) (view2)).speedDialActivity.get()).onCreateGrooveClicked();
                }
            }

            .Lambda._cls1()
            {
                arg$1 = SpeedDialLayout.this;
            }
        }

        class .Lambda._cls2
            implements android.view.View.OnClickListener
        {

            private final SpeedDialLayout arg$1;

            public final void onClick(View view2)
            {
                arg$1.setExpanded(false, 0L);
            }

            .Lambda._cls2()
            {
                arg$1 = SpeedDialLayout.this;
            }
        }

        class .Lambda._cls3
            implements android.view.View.OnClickListener
        {

            private final SpeedDialLayout arg$1;

            public final void onClick(View view2)
            {
                arg$1.setExpanded(false, 0L);
            }

            .Lambda._cls3()
            {
                arg$1 = SpeedDialLayout.this;
            }
        }

        class .Lambda._cls4
            implements android.view.View.OnClickListener
        {

            private final SpeedDialLayout arg$1;

            public final void onClick(View view2)
            {
                view2 = arg$1;
                view2.setExpanded(false, 300L);
                if (((SpeedDialLayout) (view2)).speedDialActivity.get() != null)
                {
                    VisualElementAttacher visualelementattacher = VisualElementHolder.instance;
                    if (visualelementattacher == null)
                    {
                        throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
                    }
                    ((VisualElementAttacher)visualelementattacher).recordTap(view2.getContext(), ((SpeedDialLayout) (view2)).eventView);
                    ((SpeedDialActivity)((SpeedDialLayout) (view2)).speedDialActivity.get()).onCreateEventClicked();
                }
            }

            .Lambda._cls4()
            {
                arg$1 = SpeedDialLayout.this;
            }
        }

        class .Lambda._cls5
            implements android.view.View.OnClickListener
        {

            private final SpeedDialLayout arg$1;

            public final void onClick(View view2)
            {
                view2 = arg$1;
                view2.setExpanded(false, 300L);
                if (((SpeedDialLayout) (view2)).speedDialActivity.get() != null)
                {
                    ((SpeedDialActivity)((SpeedDialLayout) (view2)).speedDialActivity.get()).onCreateTaskClicked();
                }
            }

            .Lambda._cls5()
            {
                arg$1 = SpeedDialLayout.this;
            }
        }

        if (GrooveUtils.getGrooveSupportedCalendar(context, (com.google.android.calendar.api.calendarlist.CalendarListEntry[])((ListenableFutureCache)obj1).result) != null)
        {
            view1.setOnClickListener(new .Lambda._cls1());
        } else
        {
            speedDialContents.removeView(view1);
        }
        if (context instanceof SpeedDialActivity)
        {
            speedDialActivity = new WeakReference((SpeedDialActivity)context);
        } else
        {
            LogUtils.wtf(TAG, "Activity not set.", new Object[0]);
            speedDialActivity = new WeakReference(null);
        }
        fadeLayer.setOnClickListener(new .Lambda._cls2());
        if (isInAccessibilityMode)
        {
            findViewById(0x7f100364).setOnClickListener(new .Lambda._cls3());
        }
        eventView.setOnClickListener(new .Lambda._cls4());
        ((View) (obj)).setOnClickListener(new .Lambda._cls5());
        if (!isInAccessibilityMode)
        {
            context = new ArrayList();
            int k = 0;
            int j;
            do
            {
                j = ((flag) ? 1 : 0);
                if (k >= speedDialContents.getChildCount())
                {
                    break;
                }
                View view = speedDialContents.getChildAt(k);
                if (view.isFocusable())
                {
                    context.add(view);
                }
                k++;
            } while (true);
            for (; j < context.size(); j++)
            {
                int l = (j + 1) % context.size();
                ((View)context.get(j)).setNextFocusDownId(((View)context.get(l)).getId());
                ((View)context.get(l)).setNextFocusUpId(((View)context.get(j)).getId());
                ((View)context.get(j)).setNextFocusRightId(((View)context.get(l)).getId());
                ((View)context.get(l)).setNextFocusLeftId(((View)context.get(j)).getId());
            }

        }
        setVisibility(8);
        return;
    }

    static void setLayerTypeOnAllViewTargets(List list, int i)
    {
        int k = list.size();
        for (int j = 0; j < k; j++)
        {
            ((View)list.get(j)).setLayerType(i, null);
        }

    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        if (keyevent.getKeyCode() == 4 && keyevent.getAction() == 1)
        {
            setExpanded(false, 0L);
            return true;
        } else
        {
            return super.dispatchKeyEvent(keyevent);
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        return true;
    }

    public void onFocusChange(View view, boolean flag)
    {
        if (!isInAccessibilityMode)
        {
            view.findViewById(0x7f100366).setPressed(flag);
        }
    }

    public void onWindowFocusChanged(boolean flag)
    {
        super.onWindowFocusChanged(flag);
        if (flag && eventView != null)
        {
            eventView.requestFocus();
            AccessibilityUtils.requestAccessibilityFocus(eventView);
        }
    }

    final void setExpanded(boolean flag, long l)
    {
        if ((SpeedDialActivity)speedDialActivity.get() == null)
        {
            throw new NullPointerException();
        }
        if (isExpanded == flag)
        {
            return;
        }
        if (currentAnimator != null)
        {
            currentAnimator.cancel();
            currentAnimator = null;
        }
        isExpanded = flag;
        Object obj;
        if (isExpanded)
        {
            obj = fadeLayer;
            boolean flag1;
            if (!isInAccessibilityMode)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            ((View) (obj)).setClickable(flag1);
        }
        if (flag)
        {
            obj = createAnimator(true);
            ((Animator) (obj)).addListener(new _cls2());
        } else
        {
            obj = createAnimator(false);
            ((Animator) (obj)).addListener(new _cls3());
        }
        currentAnimator = ((Animator) (obj));
        if (l > 0L)
        {
            currentAnimator.setStartDelay(l);
        }
        currentAnimator.start();
    }


    private class _cls1 extends AnimatorListenerAdapter
    {

        private final SpeedDialLayout this$0;
        private final boolean val$fadeIn;
        private final List val$viewsWithLayer;

        public final void onAnimationEnd(Animator animator)
        {
            if (!fadeIn)
            {
                fadeLayer.setVisibility(8);
            }
            animator = SpeedDialLayout.this;
            SpeedDialLayout.setLayerTypeOnAllViewTargets(viewsWithLayer, 0);
        }

        public final void onAnimationStart(Animator animator)
        {
            if (fadeIn)
            {
                fadeLayer.setVisibility(0);
            }
            animator = SpeedDialLayout.this;
            SpeedDialLayout.setLayerTypeOnAllViewTargets(viewsWithLayer, 2);
        }

        _cls1()
        {
            this$0 = SpeedDialLayout.this;
            fadeIn = flag;
            viewsWithLayer = list;
            super();
        }
    }


    private class _cls2 extends AnimatorListenerAdapter
    {

        private final SpeedDialLayout this$0;

        public final void onAnimationEnd(Animator animator)
        {
            currentAnimator = null;
        }

        public final void onAnimationStart(Animator animator)
        {
            setVisibility(0);
        }

        _cls2()
        {
            this$0 = SpeedDialLayout.this;
            super();
        }
    }


    private class _cls3 extends AnimatorListenerAdapter
    {

        private final SpeedDialLayout this$0;

        public final void onAnimationEnd(Animator animator)
        {
            if (endSpeedDialFadeOutListener != null)
            {
                endSpeedDialFadeOutListener.onEndSpeedDialFadeOut();
            }
            setVisibility(8);
            currentAnimator = null;
        }

        _cls3()
        {
            this$0 = SpeedDialLayout.this;
            super();
        }
    }

}
