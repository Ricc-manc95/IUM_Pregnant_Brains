// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Trace;
import android.support.v4.view.ViewCompat;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.Choreographer;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import com.android.bitmap.RequestKey;
import com.android.bitmap.ReusableBitmap;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.graphics.ChoreographerValidator;
import com.google.android.apps.calendar.graphics.Shaders;
import com.google.android.apps.calendar.graphics.drawable.CustomAlphaDrawable;
import com.google.android.apps.calendar.graphics.drawable.DrawableContainer;
import com.google.android.apps.calendar.graphics.drawable.ShaderFactory;
import com.google.android.apps.calendar.graphics.drawable.VisibilityDrawable;
import com.google.android.apps.calendar.util.Optionals;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.Cancelable;
import com.google.android.apps.calendar.util.concurrent.CancelableFutureCallback;
import com.google.android.apps.calendar.util.concurrent.CancelableHolder;
import com.google.android.apps.calendar.util.validator.DirectValidator;
import com.google.android.calendar.timely.geometry.PartitionItem;
import com.google.android.calendar.utils.NumberUtils;
import com.google.android.calendar.utils.animation.QuantumInterpolators;
import com.google.android.calendar.utils.rtl.RtlUtils;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            ChipBackgroundImage, SolidChipBackgroundDrawable, ChipForegroundDrawable, ChipSwipeHelper, 
//            ChipConfig, ChipViewModel, ChipSwipeData, ChipConstants, 
//            ChipAnimations

public class Chip extends View
    implements PartitionItem
{
    public static interface ChipActionListener
    {

        public abstract void onChipPrimaryAction(Chip chip);

        public abstract void onChipSecondaryAction(Chip chip);
    }

    public static interface ChipLongPressListener
    {

        public abstract boolean onChipLongPress(Chip chip, Point point);
    }

    public static interface ChipPressListener
    {

        public abstract boolean onPress$51662RJ4E9NMIP1FCTP62S38D5HN6BQGDTKMST1R55D0____0();
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timeline/chip/Chip);
    public ChipActionListener actionListener;
    public final ChipBackgroundImage backgroundImage = new ChipBackgroundImage();
    public float backgroundImageAlpha;
    public final CancelableHolder backgroundImageCancelableHolder = new CancelableHolder();
    public final VisibilityDrawable backgroundImageVisibilityDrawable;
    private CancelableFutureCallback badgeSetter;
    private final RectF borderRect = new RectF();
    private final Rect clipRecycle = new Rect();
    private final ChipConfig config;
    private final CustomAlphaDrawable foregroundCustomAlphaDrawable;
    public final ChipForegroundDrawable foregroundDrawable;
    private GestureDetector gestureDetector;
    public final boolean hasRipples;
    public boolean isFocused;
    public ChipLongPressListener longPressListener;
    private final Paint paint = new Paint();
    public PartitionItem partitionInfo;
    public ChipPressListener pressListener;
    private Drawable primarySwipeIcon;
    public long requestBackgroundImageStartTimeMillis;
    public boolean requestBackgroundImageWasImmediate;
    private final Drawable rippleDrawable;
    public ListenableScheduledFuture scheduledPrimaryAction;
    private Drawable secondarySwipeIcon;
    private final SolidChipBackgroundDrawable solidBackground;
    public final ChoreographerValidator solidBackgroundAlphaValidator = new ChoreographerValidator(new .Lambda._cls1());
    public final CustomAlphaDrawable solidBackgroundCustomAlphaDrawable;
    public ChipSwipeData swipeData;
    public final ChipSwipeHelper swipeHelper;
    public float textIconScale;
    private final ViewConfiguration viewConfiguration;
    public ChipViewModel viewModel;

    Chip(Context context, ChipConfig chipconfig, ViewConfiguration viewconfiguration, Drawable drawable)
    {
        super(context);
        backgroundImageVisibilityDrawable = new VisibilityDrawable(backgroundImage.drawable);
        backgroundImageAlpha = 1.0F;
        textIconScale = 1.0F;
        config = chipconfig;
        viewConfiguration = viewconfiguration;
        rippleDrawable = drawable;
        setFocusable(true);
        class .Lambda._cls0
            implements android.view.View.OnKeyListener
        {

            private final Chip arg$1;

            public final boolean onKey(View view, int i, KeyEvent keyevent)
            {
                view = arg$1;
                return keyevent.getAction() == 1 && i == 66 && view.performClick();
            }

            .Lambda._cls0()
            {
                arg$1 = Chip.this;
            }
        }

        setOnKeyListener(new .Lambda._cls0());
        setFocusableInTouchMode(false);
        solidBackground = new SolidChipBackgroundDrawable(context.getResources());
        solidBackgroundCustomAlphaDrawable = new CustomAlphaDrawable(solidBackground);
        foregroundDrawable = new ChipForegroundDrawable(config, getContext().getResources());
        foregroundCustomAlphaDrawable = new CustomAlphaDrawable(foregroundDrawable);
        setBackground(new LayerDrawable(new Drawable[] {
            backgroundImageVisibilityDrawable, solidBackgroundCustomAlphaDrawable, drawable, foregroundCustomAlphaDrawable
        }));
        paint.setTextAlign(android.graphics.Paint.Align.LEFT);
        paint.setAntiAlias(true);
        paint.setAlpha(255);
        hasRipples = rippleDrawable instanceof RippleDrawable;
        swipeHelper = new ChipSwipeHelper(this, config.swipeThreshold, viewConfiguration);
        class .Lambda._cls1
            implements Runnable
        {

            private final Chip arg$1;

            public final void run()
            {
                Object obj1 = arg$1;
                Object obj;
                DirectValidator directvalidator;
                int i;
                boolean flag;
                if (((Chip) (obj1)).backgroundImage.reusableBitmap != null)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i != 0 && !((Chip) (obj1)).isFocused)
                {
                    i = 255 - Math.round(((Chip) (obj1)).backgroundImageAlpha * 255F);
                } else
                {
                    i = 255;
                }
                obj = ((Chip) (obj1)).solidBackgroundCustomAlphaDrawable;
                obj.customAlpha = i;
                ((CustomAlphaDrawable) (obj)).actualAlphaValidator.isValid = false;
                ((CustomAlphaDrawable) (obj)).invalidateSelf();
                obj = ((Chip) (obj1)).backgroundImageVisibilityDrawable;
                obj1 = ((Chip) (obj1)).solidBackgroundCustomAlphaDrawable;
                directvalidator = ((CustomAlphaDrawable) (obj1)).actualAlphaValidator;
                if (!directvalidator.isValid)
                {
                    directvalidator.validateRunnable.run();
                    directvalidator.isValid = true;
                }
                if (((CustomAlphaDrawable) (obj1)).actualAlpha == 255)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                obj.isHidden = flag;
            }

            .Lambda._cls1()
            {
                arg$1 = Chip.this;
            }
        }

    }

    private final Drawable getSwipeIconDrawable(Integer integer)
    {
        if (integer == null)
        {
            LogUtils.wtf(TAG, "No icon found for supported swipe direction.", new Object[0]);
            return null;
        } else
        {
            integer = getResources().getDrawable(integer.intValue());
            integer.setBounds(0, 0, integer.getIntrinsicWidth(), integer.getIntrinsicHeight());
            return integer;
        }
    }

    private final void updateForegroundTextIconSize()
    {
        if (viewModel != null)
        {
            ChipForegroundDrawable chipforegrounddrawable = foregroundDrawable;
            float f = Math.round((float)viewModel.getTextSize() * textIconScale);
            if (f != chipforegrounddrawable.textPaint.getTextSize())
            {
                chipforegrounddrawable.textPaint.setTextSize(f);
                chipforegrounddrawable.valid = false;
                chipforegrounddrawable.invalidateSelf();
            }
            chipforegrounddrawable = foregroundDrawable;
            int i = Math.round((float)viewModel.getIconSize() * textIconScale);
            if (i != chipforegrounddrawable.iconSize)
            {
                chipforegrounddrawable.iconSize = i;
                if (chipforegrounddrawable.icon != null)
                {
                    chipforegrounddrawable.invalidateSelf();
                }
            }
        }
    }

    public final void clean()
    {
        setActionListener(null);
        pressListener = null;
        updateInteractionListeners();
        longPressListener = null;
        updateInteractionListeners();
        swipeHelper._flddelegate = null;
        configureSwipeState(null);
        setOnTouchListener(null);
        setViewModel(null);
        partitionInfo = null;
        setTextIconScale(1.0F);
        ViewCompat.setElevation(this, 0.0F);
        ViewCompat.setClipBounds(this, null);
        setTranslationX(0.0F);
        setTranslationY(0.0F);
        ViewCompat.setTranslationZ(this, 0.0F);
        setAlpha(1.0F);
        setEnabled(true);
    }

    public final void configureSwipeState(ChipViewModel chipviewmodel)
    {
        ChipSwipeHelper chipswipehelper1 = null;
        if (viewModel == null) goto _L2; else goto _L1
_L1:
        ChipSwipeHelper chipswipehelper = swipeHelper;
        boolean flag;
        if (chipswipehelper._flddelegate != null && chipswipehelper._flddelegate.isSwipePossible())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L3; else goto _L2
_L2:
        swipeHelper.directions = 0;
        if (swipeData != null)
        {
            swipeData.dispose();
            swipeData = null;
        }
        primarySwipeIcon = null;
        secondarySwipeIcon = null;
_L5:
        return;
_L3:
        swipeHelper.directions = viewModel.getSupportedSwipeDirections();
        Integer integer;
        Integer integer1;
        ChipSwipeHelper chipswipehelper2;
        boolean flag1;
        if (chipviewmodel == null)
        {
            integer = null;
        } else
        {
            integer = chipviewmodel.getPrimarySwipeIcon();
        }
        integer1 = viewModel.getPrimarySwipeIcon();
        chipswipehelper2 = swipeHelper;
        if (chipswipehelper2._flddelegate != null && (chipswipehelper2.directions & 1) != 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            if (integer == integer1 || integer != null && integer.equals(integer1))
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                primarySwipeIcon = getSwipeIconDrawable(integer1);
            }
        }
        if (chipviewmodel == null)
        {
            chipviewmodel = chipswipehelper1;
        } else
        {
            chipviewmodel = chipviewmodel.getSecondarySwipeIcon();
        }
        integer = viewModel.getSecondarySwipeIcon();
        chipswipehelper1 = swipeHelper;
        if (chipswipehelper1._flddelegate != null && (chipswipehelper1.directions & 2) != 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            if (chipviewmodel == integer || chipviewmodel != null && chipviewmodel.equals(integer))
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                secondarySwipeIcon = getSwipeIconDrawable(integer);
                return;
            }
        }
        if (true) goto _L5; else goto _L4
_L4:
    }

    public void draw(Canvas canvas)
    {
        int i;
        boolean flag;
        flag = true;
        ChipSwipeHelper chipswipehelper = swipeHelper;
        boolean flag1;
        if (chipswipehelper._flddelegate != null && chipswipehelper._flddelegate.isSwipeEnabled())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_615;
        }
        chipswipehelper = swipeHelper;
        if (chipswipehelper._flddelegate != null && (chipswipehelper.directions & 1) != 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            ChipSwipeHelper chipswipehelper1 = swipeHelper;
            float f4;
            float f5;
            ChipSwipeHelper chipswipehelper2;
            long l1;
            if (chipswipehelper1._flddelegate != null && (chipswipehelper1.directions & 2) != 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                break MISSING_BLOCK_LABEL_615;
            }
        }
        if (getTranslationX() == 0.0F)
        {
            break MISSING_BLOCK_LABEL_615;
        }
        if (swipeData == null)
        {
            swipeData = new ChipSwipeData(this);
        }
        chipswipehelper1 = swipeHelper;
        chipswipehelper2 = swipeHelper;
        i = chipswipehelper2.getDirectionsFromTranslation(chipswipehelper2.chip.getTranslationX());
        if (chipswipehelper1._flddelegate != null && (chipswipehelper1.directions & i) != 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0 && Math.abs(getTranslationX()) >= (float)config.swipeThreshold)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (swipeData.aboveThreshold != flag1)
        {
            swipeData.aboveThreshold = flag1;
            float f2 = swipeData.rippleRadius;
            float f3 = swipeData.iconScaleAddend;
            float f;
            if (swipeData.rippleAnimator.isStarted())
            {
                f = swipeData.rippleAnimator.getAnimatedFraction();
            } else
            {
                f = 1.0F;
            }
            swipeData.rippleAnimator.cancel();
            swipeData.iconAnimator.cancel();
            if (swipeData.aboveThreshold)
            {
                f4 = (float)Math.sqrt(getWidth() * getWidth() + getHeight() * getHeight());
                f5 = viewModel.getCornerRadius();
                swipeData.rippleAnimator.setFloatValues(new float[] {
                    f2, f4 * 0.5F - f5
                });
                swipeData.iconAnimator.setFloatValues(new float[] {
                    f3, 0.3F
                });
                swipeData.iconAnimator.setInterpolator(ChipConstants.SPRING_INTERPOLATOR);
            } else
            {
                swipeData.rippleAnimator.setFloatValues(new float[] {
                    f2, 0.0F
                });
                swipeData.iconAnimator.setFloatValues(new float[] {
                    f3, 0.0F
                });
                swipeData.iconAnimator.setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
            }
            l1 = (long)(f * 200F);
            swipeData.rippleAnimator.setDuration(l1);
            swipeData.rippleAnimator.start();
            swipeData.iconAnimator.setDuration(l1);
            swipeData.iconAnimator.start();
            swipeData.rippleRadius = f2;
            swipeData.iconScaleAddend = f3;
        }
_L1:
        chipswipehelper1 = swipeHelper;
        if (chipswipehelper1._flddelegate != null && chipswipehelper1._flddelegate.isSwipeEnabled())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0 && getTranslationX() != 0.0F)
        {
            canvas.save();
            float f1 = getTranslationX();
            int l = viewModel.getCornerRadius();
            canvas.translate(-f1, 0.0F);
            getLocalVisibleRect(clipRecycle);
            Object obj;
            Integer integer;
            int j;
            if (f1 > 0.0F)
            {
                clipRecycle.left = 0;
                clipRecycle.right = (int)f1 + l;
            } else
            {
                obj = clipRecycle;
                j = getWidth();
                obj.left = ((int)f1 + j) - l;
                clipRecycle.right = getWidth();
            }
            canvas.clipRect(clipRecycle);
            obj = paint;
            if (swipeData != null && swipeData.aboveThreshold && !swipeData.rippleAnimator.isRunning())
            {
                j = ((flag) ? 1 : 0);
            } else
            {
                j = 0;
            }
            if (j != 0)
            {
                integer = viewModel.getRippleColor();
                int k;
                if (integer == null)
                {
                    j = config.chipFootprintRippleColor;
                } else
                {
                    j = integer.intValue();
                }
            } else
            {
                j = config.chipFootprintColor;
            }
            ((Paint) (obj)).setColor(j);
            paint.setStyle(android.graphics.Paint.Style.FILL);
            borderRect.set(0.0F, 0.0F, getWidth(), getHeight());
            canvas.drawRoundRect(borderRect, l, l, paint);
            if (swipeData != null)
            {
                if (swipeData.rippleAnimator.isRunning())
                {
                    obj = paint;
                    integer = viewModel.getRippleColor();
                    if (integer == null)
                    {
                        j = config.chipFootprintRippleColor;
                    } else
                    {
                        j = integer.intValue();
                    }
                    ((Paint) (obj)).setColor(j);
                    canvas.drawCircle((float)getWidth() * 0.5F, (float)getHeight() * 0.5F, swipeData.rippleRadius, paint);
                }
                f1 = getTranslationX();
                if (f1 != 0.0F)
                {
                    obj = swipeHelper;
                    if ((((ChipSwipeHelper) (obj)).getDirectionsFromTranslation(((ChipSwipeHelper) (obj)).chip.getTranslationX()) & 1) != 0)
                    {
                        obj = primarySwipeIcon;
                    } else
                    {
                        obj = secondarySwipeIcon;
                    }
                    if (obj != null)
                    {
                        integer = viewModel.getSwipeAccentColor();
                        k = getHeight() / 2;
                        l = ((Drawable) (obj)).getIntrinsicHeight() / 2;
                        if (f1 > 0.0F)
                        {
                            j = config.swipeIconIndent;
                        } else
                        {
                            j = getWidth() - config.swipeIconIndent - primarySwipeIcon.getIntrinsicWidth();
                        }
                        canvas.save();
                        canvas.translate(j, k - l);
                        f1 = NumberUtils.clamp(Math.abs(getTranslationX()) / (float)config.swipeThreshold, 0.0F, 1.0F) * 0.5F + 0.2F + swipeData.iconScaleAddend;
                        canvas.scale(f1, f1, ((Drawable) (obj)).getIntrinsicWidth() / 2, ((Drawable) (obj)).getIntrinsicHeight() / 2);
                        if (integer != null)
                        {
                            ((Drawable) (obj)).setColorFilter(integer.intValue(), android.graphics.PorterDuff.Mode.SRC_IN);
                        } else
                        {
                            ((Drawable) (obj)).clearColorFilter();
                        }
                        ((Drawable) (obj)).draw(canvas);
                        canvas.restore();
                    }
                }
                if (swipeData.footprintMaskAlpha > 0)
                {
                    paint.setColor(-1);
                    paint.setAlpha(swipeData.footprintMaskAlpha);
                    canvas.drawRoundRect(borderRect, viewModel.getCornerRadius(), viewModel.getCornerRadius(), paint);
                }
            }
            canvas.restore();
        }
        super.draw(canvas);
        return;
        if (getTranslationX() == 0.0F && swipeData != null)
        {
            swipeData.dispose();
            swipeData = null;
        }
          goto _L1
    }

    public CharSequence getContentDescription()
    {
        CharSequence charsequence1 = super.getContentDescription();
        CharSequence charsequence = charsequence1;
        if (viewModel != null)
        {
            charsequence = charsequence1;
            if (TextUtils.isEmpty(charsequence1))
            {
                Trace.beginSection("formatText/computeContentDescription");
                charsequence = (CharSequence)viewModel.getContentDescription().get();
                setContentDescription(charsequence);
                Trace.endSection();
            }
        }
        return charsequence;
    }

    public final int getEndDay()
    {
        return partitionInfo.getEndDay();
    }

    public final long getEndMillis()
    {
        return partitionInfo.getEndMillis();
    }

    public final int getEndTime()
    {
        return partitionInfo.getEndTime();
    }

    public final int getMaxPartitions()
    {
        return partitionInfo.getMaxPartitions();
    }

    public final int getPartition()
    {
        return partitionInfo.getPartition();
    }

    public final int getStartDay()
    {
        return partitionInfo.getStartDay();
    }

    public final long getStartMillis()
    {
        return partitionInfo.getStartMillis();
    }

    public final int getStartTime()
    {
        return partitionInfo.getStartTime();
    }

    public final void invalidateIncludingFootprint()
    {
        if (getParent() instanceof View)
        {
            getHitRect(clipRecycle);
            clipRecycle.left = (int)((float)clipRecycle.left - Math.max(getTranslationX(), 0.0F));
            clipRecycle.right = (int)((float)clipRecycle.right - Math.min(getTranslationX(), 0.0F));
            ((View)getParent()).invalidate(clipRecycle);
        }
        invalidate();
    }

    public final boolean isAllDay()
    {
        return partitionInfo.isAllDay();
    }

    protected void onFocusChanged(boolean flag, int i, Rect rect)
    {
        super.onFocusChanged(flag, i, rect);
        isFocused = flag;
        if (viewModel != null)
        {
            if (isFocused)
            {
                rect = solidBackground;
                i = config.focusedColor;
                rect.setColor(i, i, 0);
            } else
            {
                solidBackground.configure(viewModel);
            }
            rect = solidBackgroundAlphaValidator;
            if (((ChoreographerValidator) (rect)).isValid)
            {
                Choreographer.getInstance().postFrameCallback(((ChoreographerValidator) (rect)).validateFrameCallback);
                rect.isValid = false;
            }
        }
    }

    public void onRtlPropertiesChanged(int i)
    {
        super.onRtlPropertiesChanged(i);
        boolean flag = RtlUtils.isLayoutDirectionRtl(getContext());
        if (viewModel != null && viewModel.getIsRtl() != flag)
        {
            setViewModel(viewModel.toBuilder().setIsRtl(flag).build());
        }
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        foregroundDrawable.setBounds(0, 0, i, j);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if (isEnabled()) goto _L2; else goto _L1
_L1:
        if (gestureDetector == null) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        ChipSwipeHelper chipswipehelper = swipeHelper;
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 3: default 60
    //                   0 98
    //                   1 623
    //                   2 193
    //                   3 1331;
           goto _L5 _L6 _L7 _L8 _L9
_L5:
        float f;
        float f1;
        float f2;
        float f3;
        Object obj;
        boolean flag;
        int i;
        int j;
        boolean flag1;
        if (chipswipehelper.recognitionStatus == 2)
        {
            flag1 = false;
        } else
        {
            motionevent.offsetLocation(chipswipehelper.chip.getX(), chipswipehelper.chip.getY());
            chipswipehelper.velocityTracker.addMovement(motionevent);
            motionevent.offsetLocation(-chipswipehelper.chip.getX(), -chipswipehelper.chip.getY());
            flag1 = true;
        }
        if (gestureDetector == null || !gestureDetector.onTouchEvent(motionevent))
        {
            setPressed(false);
            return flag1;
        }
          goto _L10
_L6:
        chipswipehelper.initialTouchX = motionevent.getX();
        chipswipehelper.initialTouchY = motionevent.getY();
        if (chipswipehelper._flddelegate != null && chipswipehelper._flddelegate.isSwipeEnabled())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            chipswipehelper.recognitionStatus = 0;
            if (chipswipehelper.velocityTracker != null)
            {
                chipswipehelper.velocityTracker.recycle();
            }
            chipswipehelper.velocityTracker = VelocityTracker.obtain();
        } else
        {
            chipswipehelper.recognitionStatus = 2;
        }
          goto _L5
_L8:
        if (chipswipehelper._flddelegate != null && chipswipehelper._flddelegate.isSwipeEnabled())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            chipswipehelper.recognitionStatus = 2;
        }
        if (chipswipehelper.recognitionStatus == 1)
        {
            f = motionevent.getX();
            f1 = chipswipehelper.initialTouchX;
            obj = chipswipehelper.chip;
            j = ((View)chipswipehelper.chip.getParent()).getWidth() - chipswipehelper.chip.getLeft();
            f1 = NumberUtils.clamp(chipswipehelper.chip.getTranslationX() + (f - f1), -j, j);
            i = chipswipehelper.getDirectionsFromTranslation(f1);
            if (chipswipehelper._flddelegate != null && (i & chipswipehelper.directions) != 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            f = f1;
            if (i == 0)
            {
                f = j;
                f = (float)Math.sin((double)(f1 / (float)j) * 1.5707963267948966D) * (0.15F * f);
            }
            ((Chip) (obj)).setTranslationX(f);
            chipswipehelper.chip.invalidateIncludingFootprint();
        }
        j = motionevent.getHistorySize();
        i = 0;
        while (i < j + 1 && chipswipehelper.recognitionStatus == 0) 
        {
            if (i < j)
            {
                f = motionevent.getHistoricalX(i);
            } else
            {
                f = motionevent.getX();
            }
            if (i < j)
            {
                f1 = motionevent.getHistoricalY(i);
            } else
            {
                f1 = motionevent.getY();
            }
            f2 = Math.abs(f - chipswipehelper.initialTouchX);
            f3 = Math.abs(f1 - chipswipehelper.initialTouchY);
            if (f3 > (float)chipswipehelper.touchSlop && f3 > 1.2F * f2)
            {
                chipswipehelper.recognitionStatus = 2;
            } else
            if (f2 > (float)chipswipehelper.touchSlop)
            {
                chipswipehelper.chip.getParent().requestDisallowInterceptTouchEvent(true);
                if (!chipswipehelper.chip.isEnabled())
                {
                    chipswipehelper.recognitionStatus = 2;
                } else
                {
                    chipswipehelper.initialTouchX = f;
                    chipswipehelper.initialTouchY = f1;
                    chipswipehelper.chip.setPressed(false);
                    if (chipswipehelper._flddelegate != null)
                    {
                        chipswipehelper._flddelegate.onSwipeGestureStart(chipswipehelper.chip);
                    }
                    chipswipehelper.recognitionStatus = 1;
                }
            }
            i++;
        }
          goto _L5
_L7:
        if (chipswipehelper.recognitionStatus != 1) goto _L5; else goto _L11
_L11:
        if (chipswipehelper._flddelegate != null && chipswipehelper._flddelegate.isSwipeEnabled())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L13; else goto _L12
_L12:
        chipswipehelper.velocityTracker.computeCurrentVelocity(1000, chipswipehelper.maxFlingVelocity);
        f1 = chipswipehelper.velocityTracker.getXVelocity();
        f = chipswipehelper.velocityTracker.getYVelocity();
        chipswipehelper.recognitionStatus = 0;
        f2 = chipswipehelper.chip.getTranslationX();
        f3 = chipswipehelper.chip.getWidth();
        i = chipswipehelper.getDirectionsFromTranslation(f1);
        if (chipswipehelper._flddelegate != null && (i & chipswipehelper.directions) != 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L15; else goto _L14
_L14:
        if (f1 * f2 >= 0.0F)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0 || Math.abs(f1) <= (float)chipswipehelper.minFlingVelocity || Math.abs(f1) <= Math.abs(f) || Math.abs(f2) <= 0.05F * f3) goto _L15; else goto _L16
_L16:
        i = 1;
_L26:
        if (i != 0) goto _L18; else goto _L17
_L17:
        f = chipswipehelper.chip.getTranslationX();
        i = chipswipehelper.getDirectionsFromTranslation(f1);
        if (chipswipehelper._flddelegate != null && (i & chipswipehelper.directions) != 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L20; else goto _L19
_L19:
        if (f1 * f >= 0.0F)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0 || Math.abs(f) < (float)chipswipehelper.swipeThreshold) goto _L20; else goto _L21
_L21:
        i = 1;
_L27:
        if (i == 0) goto _L22; else goto _L18
_L18:
        i = chipswipehelper.getDirectionsFromTranslation(chipswipehelper.chip.getTranslationX());
        obj = chipswipehelper.chip;
        i;
        JVM INSTR tableswitch 1 2: default 952
    //                   1 1005
    //                   2 1134;
           goto _L23 _L24 _L25
_L23:
        throw new IllegalArgumentException("Invalid direction");
_L15:
        i = 0;
          goto _L26
_L20:
        i = 0;
          goto _L27
_L24:
        if (!((Chip) (obj)).viewModel.getIsRtl())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
_L28:
        if (flag1)
        {
            f = ((View)((Chip) (obj)).getParent()).getWidth() - ((Chip) (obj)).getLeft() - 1;
        } else
        {
            f = -((Chip) (obj)).getRight() + 1;
        }
        obj = ChipAnimations.createTranslationXSwipeAnimator(((Chip) (obj)), f, ChipAnimations.calculateTranslationDuration(f - ((Chip) (obj)).getTranslationX(), Float.valueOf(f1)));
        ((Animator) (obj)).addListener(new ChipSwipeHelper._cls1(chipswipehelper, i));
        if (chipswipehelper.animator != null && chipswipehelper.animator.isRunning())
        {
            chipswipehelper.animator.end();
        }
        chipswipehelper.animator = ((Animator) (obj));
        chipswipehelper.animator.start();
          goto _L5
_L25:
        flag1 = ((Chip) (obj)).viewModel.getIsRtl();
          goto _L28
_L22:
        obj = chipswipehelper.chip;
        obj = ChipAnimations.createTranslationXSwipeAnimator(((Chip) (obj)), 0.0F, ChipAnimations.calculateTranslationDuration(-((Chip) (obj)).getTranslationX(), Float.valueOf(f1)));
        ((Animator) (obj)).addListener(new ChipSwipeHelper._cls2(chipswipehelper));
        if (chipswipehelper.animator != null && chipswipehelper.animator.isRunning())
        {
            chipswipehelper.animator.end();
        }
        chipswipehelper.animator = ((Animator) (obj));
        chipswipehelper.animator.start();
          goto _L5
_L13:
        obj = chipswipehelper.chip;
        obj = ChipAnimations.createTranslationXSwipeAnimator(((Chip) (obj)), 0.0F, ChipAnimations.calculateTranslationDuration(-((Chip) (obj)).getTranslationX(), null));
        ((Animator) (obj)).addListener(new ChipSwipeHelper._cls2(chipswipehelper));
        if (chipswipehelper.animator != null && chipswipehelper.animator.isRunning())
        {
            chipswipehelper.animator.end();
        }
        chipswipehelper.animator = ((Animator) (obj));
        chipswipehelper.animator.start();
          goto _L5
_L9:
        if (chipswipehelper.recognitionStatus == 1)
        {
            obj = chipswipehelper.chip;
            obj = ChipAnimations.createTranslationXSwipeAnimator(((Chip) (obj)), 0.0F, ChipAnimations.calculateTranslationDuration(-((Chip) (obj)).getTranslationX(), null));
            ((Animator) (obj)).addListener(new ChipSwipeHelper._cls2(chipswipehelper));
            if (chipswipehelper.animator != null && chipswipehelper.animator.isRunning())
            {
                chipswipehelper.animator.end();
            }
            chipswipehelper.animator = ((Animator) (obj));
            chipswipehelper.animator.start();
        }
          goto _L5
_L10:
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 4: default 1520
    //                   0 1548
    //                   1 1556
    //                   2 1520
    //                   3 1556
    //                   4 1556;
           goto _L29 _L30 _L31 _L29 _L31 _L31
_L29:
        break; /* Loop/switch isn't completed */
_L30:
        break; /* Loop/switch isn't completed */
_L33:
        if (hasRipples)
        {
            rippleDrawable.setHotspot((int)motionevent.getX(), (int)motionevent.getY());
            return true;
        }
        if (true) goto _L3; else goto _L32
_L32:
        setPressed(true);
          goto _L33
_L31:
        setPressed(false);
          goto _L33
    }

    public final void setActionListener(ChipActionListener chipactionlistener)
    {
        CalendarExecutor.MAIN.checkOnThread();
        if (scheduledPrimaryAction != null)
        {
            scheduledPrimaryAction.cancel(false);
            scheduledPrimaryAction = null;
            actionListener.onChipPrimaryAction(this);
        }
        actionListener = chipactionlistener;
        updateInteractionListeners();
    }

    public void setBackgroundImageAlpha(float f)
    {
        backgroundImageAlpha = f;
        ChoreographerValidator choreographervalidator = solidBackgroundAlphaValidator;
        if (choreographervalidator.isValid)
        {
            Choreographer.getInstance().postFrameCallback(choreographervalidator.validateFrameCallback);
            choreographervalidator.isValid = false;
        }
    }

    public void setForegroundAlpha(float f)
    {
        CustomAlphaDrawable customalphadrawable = foregroundCustomAlphaDrawable;
        customalphadrawable.customAlpha = Math.round(255F * f);
        customalphadrawable.actualAlphaValidator.isValid = false;
        customalphadrawable.invalidateSelf();
    }

    public void setMaxPartitions(int i)
    {
        partitionInfo.setMaxPartitions(i);
    }

    public void setPartition(int i)
    {
        partitionInfo.setPartition(i);
    }

    public void setTextIconScale(float f)
    {
        if (f != textIconScale)
        {
            textIconScale = f;
            updateForegroundTextIconSize();
        }
    }

    public final void setViewModel(ChipViewModel chipviewmodel)
    {
        CalendarExecutor.MAIN.checkOnThread();
        ChipViewModel chipviewmodel1 = viewModel;
        boolean flag;
        if (chipviewmodel == chipviewmodel1 || chipviewmodel != null && chipviewmodel.equals(chipviewmodel1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Object obj;
        Object obj2;
        int i;
label0:
        {
            obj = backgroundImageCancelableHolder;
            obj2 = Cancelable.EMPTY;
            ((Cancelable)((CancelableHolder) (obj)).previousCancelableRef.getAndSet(obj2)).cancel(true);
            obj2 = viewModel;
            viewModel = chipviewmodel;
            obj = foregroundDrawable;
            Object obj3 = getResources();
            obj.viewModel = chipviewmodel;
            if (((ChipForegroundDrawable) (obj)).viewModel != null)
            {
                ((ChipForegroundDrawable) (obj)).textPaint.setColor(((ChipForegroundDrawable) (obj)).viewModel.getForegroundColor());
                class .Lambda._cls5
                    implements Consumer
                {

                    private final Chip arg$1;

                    public final void accept(Object obj5)
                    {
                        Chip chip = arg$1;
                        BackgroundImageViewModel backgroundimageviewmodel = (BackgroundImageViewModel)obj5;
                        obj5 = chip.getContext().getResources();
                        AutoValue_ImageResolverContext autovalue_imageresolvercontext = new AutoValue_ImageResolverContext(chip.getContext(), ((Resources) (obj5)).getDimensionPixelSize(0x7f0e009d), ((Resources) (obj5)).getDimensionPixelSize(0x7f0e009c));
                        ImageViewModel imageviewmodel = backgroundimageviewmodel.imageViewModel();
                        class .Lambda._cls3
                            implements Consumer
                        {

                            private final Chip arg$1;
                            private final BackgroundImageViewModel arg$2;

                            public final void accept(Object obj6)
                            {
                                Chip chip1 = arg$1;
                                BackgroundImageViewModel backgroundimageviewmodel1 = arg$2;
                                class .Lambda._cls7
                                    implements Consumer
                                {

                                    private final Chip arg$1;
                                    private final BackgroundImageViewModel arg$2;

                                    public final void accept(Object obj7)
                                    {
                                        Chip chip2 = arg$1;
                                        Object obj8 = arg$2;
                                        Object obj9 = (Image)obj7;
                                        int i1 = chip2.viewModel.getCornerRadius();
                                        obj7 = ((BackgroundImageViewModel) (obj8)).bottomLineStyle();
                                        int j1 = (int)(System.currentTimeMillis() - chip2.requestBackgroundImageStartTimeMillis);
                                        float f1;
                                        Resources resources;
                                        com.google.android.calendar.utils.rtl.RtlContext..Lambda._cls0 _lcls0;
                                        ChipDrawables..Lambda._cls0 _lcls0_1;
                                        Object obj10;
                                        boolean flag1;
                                        int k1;
                                        int l1;
                                        if (!chip2.requestBackgroundImageWasImmediate)
                                        {
                                            flag1 = true;
                                        } else
                                        {
                                            flag1 = false;
                                        }
                                        l1 = ChipConstants.backgroundImageFadeInDurationMillis(j1);
                                        obj8 = chip2.backgroundImage;
                                        resources = chip2.getContext().getResources();
                                        _lcls0 = new com.google.android.calendar.utils.rtl.RtlContext..Lambda._cls0(chip2.getContext().getResources().getConfiguration());
                                        if (((ChipBackgroundImage) (obj8)).reusableBitmap != null)
                                        {
                                            ((ChipBackgroundImage) (obj8)).reusableBitmap.releaseReference();
                                        }
                                        obj8.reusableBitmap = ((Image) (obj9)).reusableBitmap();
                                        f1 = i1;
                                        obj10 = new RoundRectShape(new float[] {
                                            f1, f1, f1, f1, f1, f1, f1, f1
                                        }, null, null);
                                        _lcls0_1 = new ChipDrawables..Lambda._cls0(resources, ((Image) (obj9)));
                                        obj10 = new ShapeDrawable(((android.graphics.drawable.shapes.Shape) (obj10)));
                                        ((ShapeDrawable) (obj10)).setShaderFactory(_lcls0_1.toAndroid());
                                        ((ShapeDrawable) (obj10)).getPaint().setFilterBitmap(true);
                                        obj9 = ((Image) (obj9)).rtlMirroring();
                                        obj10.getClass();
                                        obj9 = new com.google.android.apps.calendar.graphics.drawable.Drawables._cls4(((Drawable) (obj10)), new com.google.android.apps.calendar.graphics.DrawFunction..Lambda._cls0(((Drawable) (obj10))), _lcls0, ((com.google.android.apps.calendar.graphics.RtlMirroring) (obj9)));
                                        if (obj7 == com.google.android.calendar.timeline.chip.image.BackgroundImageViewModel.BottomLineStyle.WITH_BOTTOM_LINE)
                                        {
                                            i1 = 1;
                                        } else
                                        {
                                            i1 = 0;
                                        }
                                        if (i1 != 0)
                                        {
                                            i1 = resources.getDimensionPixelSize(0x7f0e0068);
                                        } else
                                        {
                                            i1 = 0;
                                        }
                                        obj7 = new com.google.android.apps.calendar.graphics.drawable.Drawables..Lambda._cls2(0, 0);
                                        j1 = ((Drawable) (obj9)).getIntrinsicWidth();
                                        if (j1 == -1)
                                        {
                                            j1 = -1;
                                        } else
                                        {
                                            j1 = ((IntUnaryOperator) (obj7)).apply(j1);
                                        }
                                        obj7 = new com.google.android.apps.calendar.graphics.drawable.Drawables..Lambda._cls3(0, i1);
                                        k1 = ((Drawable) (obj9)).getIntrinsicHeight();
                                        if (k1 == -1)
                                        {
                                            k1 = -1;
                                        } else
                                        {
                                            k1 = ((IntUnaryOperator) (obj7)).apply(k1);
                                        }
                                        obj7 = new com.google.android.apps.calendar.graphics.drawable.Drawables._cls1(((Drawable) (obj9)), new com.google.android.apps.calendar.graphics.drawable.Drawables..Lambda._cls4(((Drawable) (obj9)), 0, 0, 0, i1), j1, k1);
                                        if (flag1)
                                        {
                                            obj7 = new CustomAlphaDrawable(((Drawable) (obj7)));
                                            ValueAnimator valueanimator = ValueAnimator.ofInt(new int[] {
                                                0, 255
                                            });
                                            valueanimator.setDuration(l1);
                                            valueanimator.addUpdateListener(new com.google.android.apps.calendar.graphics.drawable.Drawables..Lambda._cls1(((CustomAlphaDrawable) (obj7))));
                                            obj7 = new com.google.android.apps.calendar.graphics.drawable.Drawables._cls2(((Drawable) (obj7)), valueanimator);
                                        }
                                        obj8 = ((ChipBackgroundImage) (obj8)).imageContainer;
                                        obj8.drawable = ((Drawable) (obj7));
                                        ((DrawableContainer) (obj8)).updateDrawable();
                                        ((DrawableContainer) (obj8)).invalidateSelf();
                                        obj7 = chip2.solidBackgroundAlphaValidator;
                                        if (((ChoreographerValidator) (obj7)).isValid)
                                        {
                                            Choreographer.getInstance().postFrameCallback(((ChoreographerValidator) (obj7)).validateFrameCallback);
                                            obj7.isValid = false;
                                        }
                                    }

                                        .Lambda._cls7(BackgroundImageViewModel backgroundimageviewmodel)
                                        {
                                            arg$1 = Chip.this;
                                            arg$2 = backgroundimageviewmodel;
                                        }
                                }

                                Optionals.ifPresent((Optional)obj6, chip1. new .Lambda._cls7(backgroundimageviewmodel1));
                            }

                                .Lambda._cls3(BackgroundImageViewModel backgroundimageviewmodel)
                                {
                                    arg$1 = Chip.this;
                                    arg$2 = backgroundimageviewmodel;
                                }
                        }

                        Cancelable cancelable;
                        if (chip.viewModel == null)
                        {
                            obj5 = "[Unconfigured chip]";
                        } else
                        {
                            String s;
                            if (chip.viewModel.getPrimaryText().isEmpty())
                            {
                                s = "";
                            } else
                            {
                                s = (String)chip.viewModel.getPrimaryText().get(0);
                            }
                            obj5 = s;
                            if (s.length() >= 30)
                            {
                                obj5 = s.substring(0, 30);
                            }
                        }
                        obj5 = String.valueOf(obj5);
                        if (((String) (obj5)).length() != 0)
                        {
                            obj5 = "setEventImage - ".concat(((String) (obj5)));
                        } else
                        {
                            obj5 = new String("setEventImage - ");
                        }
                        Trace.beginSection(((String) (obj5)));
                        chip.requestBackgroundImageStartTimeMillis = System.currentTimeMillis();
                        chip.requestBackgroundImageWasImmediate = true;
                        obj5 = chip.backgroundImageCancelableHolder;
                        cancelable = CalendarFutures.asyncGet(imageviewmodel.imageResolver().resolveImage(autovalue_imageresolvercontext), chip. new .Lambda._cls3(backgroundimageviewmodel), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN));
                        ((Cancelable)((CancelableHolder) (obj5)).previousCancelableRef.getAndSet(cancelable)).cancel(true);
                        chip.requestBackgroundImageWasImmediate = false;
                        Trace.endSection();
                        return;
                        obj5;
                        Trace.endSection();
                        throw obj5;
                    }

            .Lambda._cls5()
            {
                arg$1 = Chip.this;
            }
                }

                float f;
                Object obj4;
                int j;
                int k;
                int l;
                if (chipviewmodel.getIcon() != 0)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i != 0)
                {
                    obj.icon = ((Resources) (obj3)).getDrawable(chipviewmodel.getIcon());
                    if (chipviewmodel.getIconMode() != ChipViewModel.IconMode.BADGED && chipviewmodel.getForegroundColor() != -1)
                    {
                        obj.icon = ((ChipForegroundDrawable) (obj)).icon.mutate();
                        ((ChipForegroundDrawable) (obj)).icon.setColorFilter(chipviewmodel.getForegroundColor(), android.graphics.PorterDuff.Mode.MULTIPLY);
                    }
                    ((ChipForegroundDrawable) (obj)).icon.setCallback(((ChipForegroundDrawable) (obj)).callback);
                } else
                {
                    obj.icon = null;
                }
                ((ChipForegroundDrawable) (obj)).badgeBackgroundPaint.setColor(((ChipForegroundDrawable) (obj)).viewModel.getBackgroundColor());
                if (((ChipForegroundDrawable) (obj)).viewModel.getIsRtl())
                {
                    i = 0;
                } else
                {
                    i = ((ChipForegroundDrawable) (obj)).viewModel.getForegroundPaddingStart();
                }
                if (((ChipForegroundDrawable) (obj)).viewModel.getIsRtl())
                {
                    j = ((ChipForegroundDrawable) (obj)).viewModel.getForegroundPaddingStart();
                } else
                {
                    j = 0;
                }
                k = ((ChipForegroundDrawable) (obj)).viewModel.getForegroundPaddingTop();
                l = ((ChipForegroundDrawable) (obj)).viewModel.getForegroundPaddingBottom();
                ((ChipForegroundDrawable) (obj)).padding.set(i, k, j, l);
                obj.valid = false;
                ((ChipForegroundDrawable) (obj)).invalidateSelf();
                obj.valid = false;
                ((ChipForegroundDrawable) (obj)).invalidateSelf();
            }
            obj.primaryTextLayout = null;
            updateForegroundTextIconSize();
            solidBackground.configure(chipviewmodel);
            obj = backgroundImage;
            if (((ChipBackgroundImage) (obj)).reusableBitmap != null)
            {
                ((ChipBackgroundImage) (obj)).reusableBitmap.releaseReference();
            }
            obj.reusableBitmap = null;
            obj3 = ((ChipBackgroundImage) (obj)).imageContainer;
            obj3.drawable = new ColorDrawable(0);
            ((DrawableContainer) (obj3)).updateDrawable();
            ((DrawableContainer) (obj3)).invalidateSelf();
            obj = ((ChipBackgroundImage) (obj)).backgroundContainer;
            obj.drawable = new ColorDrawable(0);
            ((DrawableContainer) (obj)).updateDrawable();
            ((DrawableContainer) (obj)).invalidateSelf();
            if (chipviewmodel != null)
            {
                obj = backgroundImage.backgroundContainer;
                i = chipviewmodel.getBackgroundColor();
                f = chipviewmodel.getCornerRadius();
                obj4 = new RoundRectShape(new float[] {
                    f, f, f, f, f, f, f, f
                }, null, null);
                obj3 = new com.google.android.apps.calendar.graphics.drawable.ShaderFactory..Lambda._cls0(Shaders.color(i));
                obj4 = new ShapeDrawable(((android.graphics.drawable.shapes.Shape) (obj4)));
                ((ShapeDrawable) (obj4)).setShaderFactory(((ShaderFactory) (obj3)).toAndroid());
                ((ShapeDrawable) (obj4)).getPaint().setFilterBitmap(true);
                obj.drawable = ((Drawable) (obj4));
                ((DrawableContainer) (obj)).updateDrawable();
                ((DrawableContainer) (obj)).invalidateSelf();
                Optionals.ifPresent(chipviewmodel.getOptionalBackgroundImageViewModel(), new .Lambda._cls5());
            }
            if (obj2 != null && chipviewmodel != null)
            {
                obj = ((ChipViewModel) (obj2)).getContentDescription();
                obj3 = chipviewmodel.getContentDescription();
                if (obj == obj3 || obj != null && obj.equals(obj3))
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i != 0)
                {
                    break label0;
                }
            }
            setContentDescription(null);
        }
        if (obj2 == null)
        {
            obj = null;
        } else
        {
            obj = ((ChipViewModel) (obj2)).getBadgeRequestKey();
        }
        if (chipviewmodel == null)
        {
            chipviewmodel = null;
        } else
        {
            chipviewmodel = chipviewmodel.getBadgeRequestKey();
        }
        if (obj == chipviewmodel || obj != null && obj.equals(chipviewmodel))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            if (badgeSetter != null)
            {
                badgeSetter.nestedFutureCallbackReference.set(null);
                badgeSetter = null;
            }
            if (chipviewmodel != null)
            {
                if (chipviewmodel.isDone())
                {
                    foregroundDrawable.setBadge((RequestKey)Futures.getUnchecked(chipviewmodel));
                } else
                {
                    Object obj1 = foregroundDrawable;
                    obj1.getClass();
                    class .Lambda._cls6
                        implements Consumer
                    {

                        private final ChipForegroundDrawable arg$1;

                        public final void accept(Object obj5)
                        {
                            arg$1.setBadge((RequestKey)obj5);
                        }

            .Lambda._cls6(ChipForegroundDrawable chipforegrounddrawable)
            {
                arg$1 = chipforegrounddrawable;
            }
                    }

                    badgeSetter = new CancelableFutureCallback(LogUtils.newFailureLoggingCallback(new .Lambda._cls6(((ChipForegroundDrawable) (obj1))), TAG, "Error while loading badge.", new Object[0]));
                    obj1 = badgeSetter;
                    CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
                    if (obj1 == null)
                    {
                        throw new NullPointerException();
                    }
                    chipviewmodel.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(chipviewmodel, ((com.google.common.util.concurrent.FutureCallback) (obj1))), calendarexecutor);
                }
            }
        }
        configureSwipeState(((ChipViewModel) (obj2)));
        chipviewmodel = solidBackgroundAlphaValidator;
        if (((ChoreographerValidator) (chipviewmodel)).isValid)
        {
            Choreographer.getInstance().postFrameCallback(((ChoreographerValidator) (chipviewmodel)).validateFrameCallback);
            chipviewmodel.isValid = false;
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    public final boolean spansAtLeastOneDay()
    {
        return partitionInfo.spansAtLeastOneDay();
    }

    public final void updateInteractionListeners()
    {
        boolean flag1 = false;
        class .Lambda._cls2
            implements android.view.View.OnClickListener
        {

            private final Chip arg$1;

            public final void onClick(View view)
            {
                view = arg$1;
                ((Chip) (view)).actionListener.onChipPrimaryAction(view);
            }

            .Lambda._cls2()
            {
                arg$1 = Chip.this;
            }
        }

        Object obj;
        boolean flag;
        if (actionListener == null)
        {
            obj = null;
        } else
        {
            obj = new .Lambda._cls2();
        }
        setOnClickListener(((android.view.View.OnClickListener) (obj)));
        if (actionListener != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        setClickable(flag);
        if (actionListener == null && longPressListener == null && pressListener == null)
        {
            gestureDetector = null;
            setImportantForAccessibility(4);
            return;
        }
        if (gestureDetector == null)
        {
            gestureDetector = new GestureDetector(getContext(), new _cls1());
        }
        obj = gestureDetector;
        flag = flag1;
        if (longPressListener != null)
        {
            flag = true;
        }
        ((GestureDetector) (obj)).setIsLongpressEnabled(flag);
        setImportantForAccessibility(1);
    }


    private class _cls1 extends android.view.GestureDetector.SimpleOnGestureListener
    {

        private final Chip this$0;

        public final boolean onDown(MotionEvent motionevent)
        {
            if (pressListener == null) goto _L2; else goto _L1
_L1:
            new Point((int)motionevent.getX(), (int)motionevent.getY());
            if (!pressListener._mth51662RJ4E9NMIP1FCTP62S38D5HN6BQGDTKMST1R55D0____0()) goto _L2; else goto _L3
_L3:
            return false;
_L2:
            if (actionListener != null)
            {
                return true;
            }
            if (true) goto _L3; else goto _L4
_L4:
        }

        public final void onLongPress(MotionEvent motionevent)
        {
            if (longPressListener != null)
            {
                motionevent = new Point((int)motionevent.getX(), (int)motionevent.getY());
                longPressListener.onChipLongPress(Chip.this, motionevent);
            }
        }

        public final boolean onSingleTapUp(MotionEvent motionevent)
        {
            boolean flag1 = false;
            playSoundEffect(0);
            Object obj = Chip.this;
            boolean flag = flag1;
            if (((Chip) (obj)).viewModel.getSecondaryActionAction() != null)
            {
                obj = ((Chip) (obj)).foregroundDrawable;
                int i = (int)motionevent.getX();
                int j = (int)motionevent.getY();
                flag = flag1;
                if (((ChipForegroundDrawable) (obj)).secondaryTextArea.contains(i, j))
                {
                    flag = true;
                }
            }
            if (flag)
            {
                actionListener.onChipSecondaryAction(Chip.this);
            } else
            {
                motionevent = Chip.this;
                if (((Chip) (motionevent)).scheduledPrimaryAction == null)
                {
                    if (((Chip) (motionevent)).hasRipples)
                    {
                        CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
                        class .Lambda._cls4
                            implements Runnable
                        {

                            private final Chip arg$1;

                            public final void run()
                            {
                                Chip chip = arg$1;
                                chip.actionListener.onChipPrimaryAction(chip);
                                chip.scheduledPrimaryAction = null;
                            }

                .Lambda._cls4()
                {
                    arg$1 = Chip.this;
                }
                        }

                        .Lambda._cls4 _lcls4 = motionevent. new .Lambda._cls4();
                        TimeUnit timeunit = TimeUnit.MILLISECONDS;
                        motionevent.scheduledPrimaryAction = calendarexecutor.getDelegate().schedule(_lcls4, 50L, timeunit);
                        return true;
                    } else
                    {
                        ((Chip) (motionevent)).actionListener.onChipPrimaryAction(motionevent);
                        return true;
                    }
                }
            }
            return true;
        }

        _cls1()
        {
            this$0 = Chip.this;
            super();
        }
    }

}
