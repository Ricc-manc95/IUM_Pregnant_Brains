// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.android.bitmap.BitmapCache;
import com.android.bitmap.ResourceRequestKey;
import com.android.bitmap.drawable.BasicBitmapDrawable;
import com.google.android.calendar.common.drawable.ListenableBitmapDrawable;
import com.google.android.calendar.experimental.ExperimentalOptions;
import com.google.android.calendar.timely.settings.common.OnPropertyChangedListener;
import com.google.android.calendar.timely.settings.data.CalendarProperties;

// Referenced classes of package com.google.android.calendar.timely:
//            MonthBackgrounds, BackgroundImageView

public class BackgroundImagesFrame extends FrameLayout
    implements com.google.android.calendar.common.drawable.ListenableBitmapDrawable.OnLoadCompleteListener, OnPropertyChangedListener
{

    public int backgroundDrawableSize;
    public int backgroundMonth;
    public int backgroundOffsetFromLeftPortrait;
    public int backgroundOffsetFromTopLandscape;
    public int backgroundOffsetFromTopPortrait;
    public BitmapCache cache;
    public boolean doingFade;
    private int hemisphereOffset;
    private final MonthBackgrounds monthBackgrounds;
    public int orientation;
    public boolean pendingMonth;
    public BackgroundImageView primaryBackgroundImageView;
    public BackgroundImageView secondaryBackgroundImageView;
    public int selectedViewId;

    public BackgroundImagesFrame(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        context = getResources();
        if (MonthBackgrounds.instance == null)
        {
            MonthBackgrounds.instance = new MonthBackgrounds(context);
        }
        monthBackgrounds = MonthBackgrounds.instance;
        context = CalendarProperties.instance;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        } else
        {
            hemisphereOffset = ((Integer)((CalendarProperties)context).getPropertyValue(1)).intValue();
            return;
        }
    }

    private final ListenableBitmapDrawable instantiateBackgroundDrawable(int i)
    {
        Object obj = null;
        if (i != 0)
        {
            obj = new com.android.bitmap.drawable.ExtendedBitmapDrawable.ExtendedOptions(4);
            obj.backgroundColor = i;
        }
        obj = new ListenableBitmapDrawable(getResources(), cache, false, ((com.android.bitmap.drawable.ExtendedBitmapDrawable.ExtendedOptions) (obj)));
        ((BasicBitmapDrawable) (obj)).setDecodeDimensions(backgroundDrawableSize, backgroundDrawableSize);
        ((ListenableBitmapDrawable) (obj)).setBounds(0, 0, backgroundDrawableSize, backgroundDrawableSize);
        return ((ListenableBitmapDrawable) (obj));
    }

    public final void loadSelectedMonth()
    {
        boolean flag1 = true;
        int j = (backgroundMonth + hemisphereOffset) % 12;
        Object obj = new Point();
        Object obj1;
        int i;
        int k;
        int l;
        int i1;
        int j1;
        if (orientation == 1)
        {
            obj.x = -backgroundOffsetFromLeftPortrait;
            obj.y = -backgroundOffsetFromTopPortrait;
        } else
        {
            if (selectedViewId == 0x7f100004 || ExperimentalOptions.isAlternateTimelineEnabled(getContext()) && selectedViewId == 0x7f100022)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                obj.y = -backgroundOffsetFromTopLandscape;
            }
        }
        k = ((Point) (obj)).x;
        l = ((Point) (obj)).y;
        i1 = monthBackgrounds.monthTopColorBackgrounds[j];
        j1 = monthBackgrounds.monthBottomColorBackgrounds[j];
        if (primaryBackgroundImageView.drawable != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            obj = instantiateBackgroundDrawable(monthBackgrounds.monthTopColorBackgrounds[j]);
            obj1 = primaryBackgroundImageView;
            obj1.drawable = ((ListenableBitmapDrawable) (obj));
            ((BackgroundImageView) (obj1)).drawable.setCallback(((android.graphics.drawable.Drawable.Callback) (obj1)));
            obj1 = primaryBackgroundImageView;
        } else
        {
            boolean flag;
            if (secondaryBackgroundImageView.drawable != null)
            {
                flag = flag1;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                obj = instantiateBackgroundDrawable(0);
                secondaryBackgroundImageView.setVisibility(8);
                obj1 = secondaryBackgroundImageView;
                obj1.drawable = ((ListenableBitmapDrawable) (obj));
                ((BackgroundImageView) (obj1)).drawable.setCallback(((android.graphics.drawable.Drawable.Callback) (obj1)));
            } else
            {
                obj = secondaryBackgroundImageView.drawable;
            }
            obj.onLoadCompleteListener = this;
            obj1 = secondaryBackgroundImageView;
        }
        ((BackgroundImageView) (obj1)).setInitialTranslationX(k);
        ((BackgroundImageView) (obj1)).setInitialTranslationY(l);
        ((BackgroundImageView) (obj1)).setTopBackgroundColor(i1);
        ((BackgroundImageView) (obj1)).setBottomBackgroundColor(j1);
        obj1 = getResources();
        i = MonthBackgrounds.MONTH_BACKGROUND_DRAWABLE_IDS[j];
        if (i != 0)
        {
            obj1 = new ResourceRequestKey(((android.content.res.Resources) (obj1)), i);
        } else
        {
            obj1 = null;
        }
        ((BasicBitmapDrawable) (obj)).bind(((com.android.bitmap.RequestKey) (obj1)));
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        CalendarProperties calendarproperties = CalendarProperties.instance;
        if (calendarproperties == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        } else
        {
            ((CalendarProperties)calendarproperties).registerListener(1, this);
            return;
        }
    }

    public final void onCalendarPropertyChanged(int i, Object obj)
    {
        hemisphereOffset = ((Integer)obj).intValue();
        loadSelectedMonth();
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        CalendarProperties calendarproperties = CalendarProperties.instance;
        if (calendarproperties == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        } else
        {
            ((CalendarProperties)calendarproperties).unregisterListener(1, this);
            return;
        }
    }

    public final void onLoadComplete(ListenableBitmapDrawable listenablebitmapdrawable)
    {
        if (secondaryBackgroundImageView.equals(primaryBackgroundImageView))
        {
            doingFade = false;
            return;
        } else
        {
            doingFade = true;
            listenablebitmapdrawable = ValueAnimator.ofFloat(new float[] {
                0.0F, 1.0F
            });
            listenablebitmapdrawable.addListener(new _cls1());
            listenablebitmapdrawable.addUpdateListener(new _cls2());
            listenablebitmapdrawable.start();
            return;
        }
    }

    public void setClippingTranslationY(float f)
    {
        primaryBackgroundImageView.setClippingTranslationY(f);
        secondaryBackgroundImageView.setClippingTranslationY(f);
    }

    private class _cls1 extends AnimatorListenerAdapter
    {

        private final BackgroundImagesFrame this$0;

        public final void onAnimationEnd(Animator animator)
        {
            animator = secondaryBackgroundImageView;
            secondaryBackgroundImageView = primaryBackgroundImageView;
            primaryBackgroundImageView = animator;
            secondaryBackgroundImageView.setVisibility(8);
            if (pendingMonth)
            {
                pendingMonth = false;
                loadSelectedMonth();
                return;
            } else
            {
                doingFade = false;
                return;
            }
        }

        public final void onAnimationStart(Animator animator)
        {
            secondaryBackgroundImageView.setVisibility(0);
        }

        _cls1()
        {
            this$0 = BackgroundImagesFrame.this;
            super();
        }
    }


    private class _cls2
        implements android.animation.ValueAnimator.AnimatorUpdateListener
    {

        private final BackgroundImagesFrame this$0;

        public final void onAnimationUpdate(ValueAnimator valueanimator)
        {
            float f = ((Float)valueanimator.getAnimatedValue()).floatValue();
            secondaryBackgroundImageView.setAlpha(f);
            primaryBackgroundImageView.setAlpha(1.0F - f);
        }

        _cls2()
        {
            this$0 = BackgroundImagesFrame.this;
            super();
        }
    }

}
