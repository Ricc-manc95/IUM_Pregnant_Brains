// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.time;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.util.Property;
import android.view.View;

public final class RadialSelectorView extends View
{

    private static final Property animationRadiusMultiplierProperty = new _cls1(java/lang/Float, "animationRadiusMultiplier");
    private float amPmCircleRadiusMultiplier;
    public float animationRadiusMultiplier;
    private int circleRadius;
    private float circleRadiusMultiplier;
    private boolean drawValuesReady;
    private boolean forceDrawDot;
    private boolean hasInnerCircle;
    private float innerNumbersRadiusMultiplier;
    private InvalidateUpdateListener invalidateUpdateListener;
    private boolean is24HourMode;
    private boolean isInitialized;
    private int lineLength;
    private float numbersRadiusMultiplier;
    private float outerNumbersRadiusMultiplier;
    private final Paint paint = new Paint();
    private int selectionAlpha;
    private int selectionDegrees;
    private double selectionRadians;
    private int selectionRadius;
    private float selectionRadiusMultiplier;
    private float transitionEndRadiusMultiplier;
    private float transitionMidRadiusMultiplier;
    private int xCenter;
    private int yCenter;

    public RadialSelectorView(Context context)
    {
        super(context);
        isInitialized = false;
    }

    public final int getDegreesFromCoords(float f, float f1, boolean flag, Boolean aboolean[])
    {
        boolean flag1 = true;
        if (drawValuesReady) goto _L2; else goto _L1
_L1:
        return -1;
_L2:
        int i;
        int j;
        double d = Math.sqrt((f1 - (float)yCenter) * (f1 - (float)yCenter) + (f - (float)xCenter) * (f - (float)xCenter));
        int k;
        int l;
        int i1;
        if (hasInnerCircle)
        {
            if (flag)
            {
                if ((int)Math.abs(d - (double)(int)((float)circleRadius * innerNumbersRadiusMultiplier)) <= (int)Math.abs(d - (double)(int)((float)circleRadius * outerNumbersRadiusMultiplier)))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                aboolean[0] = Boolean.valueOf(flag);
            } else
            {
                i = (int)((float)circleRadius * innerNumbersRadiusMultiplier);
                j = selectionRadius;
                k = (int)((float)circleRadius * outerNumbersRadiusMultiplier);
                l = selectionRadius;
                i1 = (int)((float)circleRadius * ((outerNumbersRadiusMultiplier + innerNumbersRadiusMultiplier) / 2.0F));
                if (d < (double)(i - j) || d > (double)i1)
                {
                    continue; /* Loop/switch isn't completed */
                }
                aboolean[0] = Boolean.valueOf(true);
            }
        } else
        if (!flag && (int)Math.abs(d - (double)lineLength) > (int)((float)circleRadius * (1.0F - numbersRadiusMultiplier)))
        {
            return -1;
        }
        j = (int)((Math.asin((double)Math.abs(f1 - (float)yCenter) / d) * 180D) / 3.1415926535897931D);
        if (f > (float)xCenter)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (f1 >= (float)yCenter)
        {
            flag1 = false;
        }
        if (i != 0 && flag1)
        {
            return 90 - j;
        }
        break MISSING_BLOCK_LABEL_351;
        if (d > (double)(k + l) || d < (double)i1) goto _L1; else goto _L3
_L3:
        aboolean[0] = Boolean.valueOf(false);
        break MISSING_BLOCK_LABEL_111;
        if (i != 0)
        {
            return j + 90;
        }
        if (!flag1)
        {
            return 270 - j;
        } else
        {
            return j + 270;
        }
    }

    public final ObjectAnimator getDisappearAnimator()
    {
        if (!isInitialized || !drawValuesReady)
        {
            Log.e("RadialSelectorView", "RadialSelectorView was not ready for animation.");
            return null;
        } else
        {
            Object obj = Keyframe.ofFloat(0.0F, 1.0F);
            Keyframe keyframe = Keyframe.ofFloat(0.2F, transitionMidRadiusMultiplier);
            Keyframe keyframe1 = Keyframe.ofFloat(1.0F, transitionEndRadiusMultiplier);
            obj = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[] {
                PropertyValuesHolder.ofKeyframe(animationRadiusMultiplierProperty, new Keyframe[] {
                    obj, keyframe, keyframe1
                }), PropertyValuesHolder.ofKeyframe("alpha", new Keyframe[] {
                    Keyframe.ofFloat(0.0F, 1.0F), Keyframe.ofFloat(1.0F, 0.0F)
                })
            }).setDuration(500L);
            ((ObjectAnimator) (obj)).addUpdateListener(invalidateUpdateListener);
            return ((ObjectAnimator) (obj));
        }
    }

    public final ObjectAnimator getReappearAnimator()
    {
        if (!isInitialized || !drawValuesReady)
        {
            Log.e("RadialSelectorView", "RadialSelectorView was not ready for animation.");
            return null;
        } else
        {
            Object obj = Keyframe.ofFloat(0.0F, transitionEndRadiusMultiplier);
            Keyframe keyframe = Keyframe.ofFloat(0.2F, transitionEndRadiusMultiplier);
            Keyframe keyframe1 = Keyframe.ofFloat(0.84F, transitionMidRadiusMultiplier);
            Keyframe keyframe2 = Keyframe.ofFloat(1.0F, 1.0F);
            obj = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[] {
                PropertyValuesHolder.ofKeyframe(animationRadiusMultiplierProperty, new Keyframe[] {
                    obj, keyframe, keyframe1, keyframe2
                }), PropertyValuesHolder.ofKeyframe("alpha", new Keyframe[] {
                    Keyframe.ofFloat(0.0F, 0.0F), Keyframe.ofFloat(0.2F, 0.0F), Keyframe.ofFloat(1.0F, 1.0F)
                })
            }).setDuration(625L);
            ((ObjectAnimator) (obj)).addUpdateListener(invalidateUpdateListener);
            return ((ObjectAnimator) (obj));
        }
    }

    public final boolean hasOverlappingRendering()
    {
        return false;
    }

    public final void initialize(Context context, boolean flag, boolean flag1, boolean flag2, int i, boolean flag3)
    {
        byte byte0 = -1;
        if (isInitialized)
        {
            Log.e("RadialSelectorView", "This RadialSelectorView may only be initialized once.");
            return;
        }
        context = context.getResources();
        int j = context.getColor(0x7f0d003c);
        paint.setColor(j);
        paint.setAntiAlias(true);
        selectionAlpha = 51;
        is24HourMode = flag;
        if (flag)
        {
            circleRadiusMultiplier = Float.parseFloat(context.getString(0x7f13010d));
        } else
        {
            circleRadiusMultiplier = Float.parseFloat(context.getString(0x7f13010c));
            amPmCircleRadiusMultiplier = Float.parseFloat(context.getString(0x7f1300b4));
        }
        hasInnerCircle = flag1;
        if (flag1)
        {
            innerNumbersRadiusMultiplier = Float.parseFloat(context.getString(0x7f130360));
            outerNumbersRadiusMultiplier = Float.parseFloat(context.getString(0x7f130362));
        } else
        {
            numbersRadiusMultiplier = Float.parseFloat(context.getString(0x7f130361));
        }
        selectionRadiusMultiplier = Float.parseFloat(context.getString(0x7f130437));
        animationRadiusMultiplier = 1.0F;
        if (flag2)
        {
            j = -1;
        } else
        {
            j = 1;
        }
        transitionMidRadiusMultiplier = (float)j * 0.05F + 1.0F;
        j = byte0;
        if (flag2)
        {
            j = 1;
        }
        transitionEndRadiusMultiplier = 0.3F * (float)j + 1.0F;
        invalidateUpdateListener = new InvalidateUpdateListener();
        setSelection(i, flag3, false);
        isInitialized = true;
    }

    public final void onDraw(Canvas canvas)
    {
        int i = 1;
        if (getWidth() == 0 || !isInitialized)
        {
            return;
        }
        if (!drawValuesReady)
        {
            xCenter = getWidth() / 2;
            yCenter = getHeight() / 2;
            circleRadius = (int)((float)Math.min(xCenter, yCenter) * circleRadiusMultiplier);
            if (!is24HourMode)
            {
                int j = (int)((float)circleRadius * amPmCircleRadiusMultiplier);
                yCenter = yCenter - j / 2;
            }
            selectionRadius = (int)((float)circleRadius * selectionRadiusMultiplier);
            drawValuesReady = true;
        }
        lineLength = (int)((float)circleRadius * numbersRadiusMultiplier * animationRadiusMultiplier);
        int k = xCenter;
        k = (int)((double)lineLength * Math.sin(selectionRadians)) + k;
        int l = yCenter - (int)((double)lineLength * Math.cos(selectionRadians));
        paint.setAlpha(selectionAlpha);
        canvas.drawCircle(k, l, selectionRadius, paint);
        boolean flag = forceDrawDot;
        if (selectionDegrees % 30 == 0)
        {
            i = 0;
        }
        if (i | flag)
        {
            paint.setAlpha(255);
            canvas.drawCircle(k, l, (selectionRadius << 1) / 7, paint);
            i = l;
        } else
        {
            i = lineLength - selectionRadius;
            k = xCenter;
            int i1 = (int)((double)i * Math.sin(selectionRadians));
            i = yCenter - (int)((double)i * Math.cos(selectionRadians));
            k += i1;
        }
        paint.setAlpha(255);
        paint.setStrokeWidth(1.0F);
        canvas.drawLine(xCenter, yCenter, k, i, paint);
    }

    public final void setSelection(int i, boolean flag, boolean flag1)
    {
label0:
        {
            selectionDegrees = i;
            selectionRadians = ((double)i * 3.1415926535897931D) / 180D;
            forceDrawDot = flag1;
            if (hasInnerCircle)
            {
                if (!flag)
                {
                    break label0;
                }
                numbersRadiusMultiplier = innerNumbersRadiusMultiplier;
            }
            return;
        }
        numbersRadiusMultiplier = outerNumbersRadiusMultiplier;
    }

    final void setTheme(Context context, boolean flag)
    {
        context = context.getResources();
        int i;
        if (flag)
        {
            i = context.getColor(0x7f0d02e7);
            selectionAlpha = 102;
        } else
        {
            i = context.getColor(0x7f0d003c);
            selectionAlpha = 51;
        }
        paint.setColor(i);
    }


    private class InvalidateUpdateListener
        implements android.animation.ValueAnimator.AnimatorUpdateListener
    {

        private final RadialSelectorView this$0;

        public final void onAnimationUpdate(ValueAnimator valueanimator)
        {
            invalidate();
        }

        InvalidateUpdateListener()
        {
            this$0 = RadialSelectorView.this;
            super();
        }
    }


    private class _cls1 extends Property
    {

        public final Object get(Object obj)
        {
            return Float.valueOf(((RadialSelectorView)obj).animationRadiusMultiplier);
        }

        public final void set(Object obj, Object obj1)
        {
            ((RadialSelectorView)obj).animationRadiusMultiplier = ((Float)obj1).floatValue();
        }

        _cls1(Class class1, String s)
        {
            super(class1, s);
        }
    }

}
