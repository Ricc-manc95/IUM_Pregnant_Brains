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
import android.graphics.Typeface;
import android.util.Log;
import android.util.Property;
import android.view.View;

public final class RadialTextsView extends View
{

    private static final Property animationRadiusMultiplierProperty = new _cls1(java/lang/Float, "animationRadiusMultiplier");
    private float amPmCircleRadiusMultiplier;
    public float animationRadiusMultiplier;
    private float circleRadius;
    private float circleRadiusMultiplier;
    private ObjectAnimator disappearAnimator;
    private boolean drawValuesReady;
    private boolean hasInnerCircle;
    private float innerNumbersRadiusMultiplier;
    private float innerTextGridHeights[];
    private float innerTextGridWidths[];
    private float innerTextSize;
    private float innerTextSizeMultiplier;
    private String innerTexts[];
    private InvalidateUpdateListener invalidateUpdateListener;
    private boolean is24HourMode;
    private boolean isInitialized;
    private float numbersRadiusMultiplier;
    private final Paint paint = new Paint();
    private ObjectAnimator reappearAnimator;
    private float textGridHeights[];
    public boolean textGridValuesDirty;
    private float textGridWidths[];
    private float textSize;
    private float textSizeMultiplier;
    private String texts[];
    private float transitionEndRadiusMultiplier;
    private float transitionMidRadiusMultiplier;
    private Typeface typefaceLight;
    private Typeface typefaceRegular;
    private int xCenter;
    private int yCenter;

    public RadialTextsView(Context context)
    {
        super(context);
        isInitialized = false;
    }

    private final void calculateGridSizes(float f, float f1, float f2, float f3, float af[], float af1[])
    {
        float f4 = ((float)Math.sqrt(3D) * f) / 2.0F;
        float f5 = f / 2.0F;
        paint.setTextSize(f3);
        f2 -= (paint.descent() + paint.ascent()) / 2.0F;
        af[0] = f2 - f;
        af1[0] = f1 - f;
        af[1] = f2 - f4;
        af1[1] = f1 - f4;
        af[2] = f2 - f5;
        af1[2] = f1 - f5;
        af[3] = f2;
        af1[3] = f1;
        af[4] = f2 + f5;
        af1[4] = f5 + f1;
        af[5] = f2 + f4;
        af1[5] = f4 + f1;
        af[6] = f2 + f;
        af1[6] = f1 + f;
    }

    private final void drawTexts(Canvas canvas, float f, Typeface typeface, String as[], float af[], float af1[])
    {
        paint.setTextSize(f);
        paint.setTypeface(typeface);
        canvas.drawText(as[0], af[3], af1[0], paint);
        canvas.drawText(as[1], af[4], af1[1], paint);
        canvas.drawText(as[2], af[5], af1[2], paint);
        canvas.drawText(as[3], af[6], af1[3], paint);
        canvas.drawText(as[4], af[5], af1[4], paint);
        canvas.drawText(as[5], af[4], af1[5], paint);
        canvas.drawText(as[6], af[3], af1[6], paint);
        canvas.drawText(as[7], af[2], af1[5], paint);
        canvas.drawText(as[8], af[1], af1[4], paint);
        canvas.drawText(as[9], af[0], af1[3], paint);
        canvas.drawText(as[10], af[1], af1[2], paint);
        canvas.drawText(as[11], af[2], af1[1], paint);
    }

    public final ObjectAnimator getDisappearAnimator()
    {
        if (!isInitialized || !drawValuesReady || disappearAnimator == null)
        {
            Log.e("RadialTextsView", "RadialTextView was not ready for animation.");
            return null;
        } else
        {
            return disappearAnimator;
        }
    }

    public final ObjectAnimator getReappearAnimator()
    {
        if (!isInitialized || !drawValuesReady || reappearAnimator == null)
        {
            Log.e("RadialTextsView", "RadialTextView was not ready for animation.");
            return null;
        } else
        {
            return reappearAnimator;
        }
    }

    public final boolean hasOverlappingRendering()
    {
        return false;
    }

    public final void initialize(Resources resources, String as[], String as1[], boolean flag, boolean flag1)
    {
        byte byte0 = -1;
        boolean flag2 = false;
        if (isInitialized)
        {
            Log.e("RadialTextsView", "This RadialTextsView may only be initialized once.");
            return;
        }
        int i = resources.getColor(0x7f0d0166);
        paint.setColor(i);
        typefaceLight = Typeface.create(resources.getString(0x7f1303d0), 0);
        typefaceRegular = Typeface.create(resources.getString(0x7f130424), 0);
        paint.setAntiAlias(true);
        paint.setTextAlign(android.graphics.Paint.Align.CENTER);
        texts = as;
        innerTexts = as1;
        is24HourMode = flag;
        if (as1 != null)
        {
            flag2 = true;
        }
        hasInnerCircle = flag2;
        if (flag)
        {
            circleRadiusMultiplier = Float.parseFloat(resources.getString(0x7f13010d));
        } else
        {
            circleRadiusMultiplier = Float.parseFloat(resources.getString(0x7f13010c));
            amPmCircleRadiusMultiplier = Float.parseFloat(resources.getString(0x7f1300b4));
        }
        textGridHeights = new float[7];
        textGridWidths = new float[7];
        if (hasInnerCircle)
        {
            numbersRadiusMultiplier = Float.parseFloat(resources.getString(0x7f130362));
            textSizeMultiplier = Float.parseFloat(resources.getString(0x7f13047b));
            innerNumbersRadiusMultiplier = Float.parseFloat(resources.getString(0x7f130360));
            innerTextSizeMultiplier = Float.parseFloat(resources.getString(0x7f130479));
            innerTextGridHeights = new float[7];
            innerTextGridWidths = new float[7];
        } else
        {
            numbersRadiusMultiplier = Float.parseFloat(resources.getString(0x7f130361));
            textSizeMultiplier = Float.parseFloat(resources.getString(0x7f13047a));
        }
        animationRadiusMultiplier = 1.0F;
        if (flag1)
        {
            i = -1;
        } else
        {
            i = 1;
        }
        transitionMidRadiusMultiplier = (float)i * 0.05F + 1.0F;
        i = byte0;
        if (flag1)
        {
            i = 1;
        }
        transitionEndRadiusMultiplier = 0.3F * (float)i + 1.0F;
        invalidateUpdateListener = new InvalidateUpdateListener();
        textGridValuesDirty = true;
        isInitialized = true;
    }

    public final void onDraw(Canvas canvas)
    {
        if (getWidth() != 0 && isInitialized)
        {
            if (!drawValuesReady)
            {
                xCenter = getWidth() / 2;
                yCenter = getHeight() / 2;
                circleRadius = (float)Math.min(xCenter, yCenter) * circleRadiusMultiplier;
                if (!is24HourMode)
                {
                    float f = circleRadius;
                    float f3 = amPmCircleRadiusMultiplier;
                    yCenter = (int)((float)yCenter - (f * f3) / 2.0F);
                }
                textSize = circleRadius * textSizeMultiplier;
                if (hasInnerCircle)
                {
                    innerTextSize = circleRadius * innerTextSizeMultiplier;
                }
                Keyframe keyframe = Keyframe.ofFloat(0.0F, 1.0F);
                Keyframe keyframe1 = Keyframe.ofFloat(0.2F, transitionMidRadiusMultiplier);
                Keyframe keyframe2 = Keyframe.ofFloat(1.0F, transitionEndRadiusMultiplier);
                disappearAnimator = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[] {
                    PropertyValuesHolder.ofKeyframe(animationRadiusMultiplierProperty, new Keyframe[] {
                        keyframe, keyframe1, keyframe2
                    }), PropertyValuesHolder.ofKeyframe("alpha", new Keyframe[] {
                        Keyframe.ofFloat(0.0F, 1.0F), Keyframe.ofFloat(1.0F, 0.0F)
                    })
                }).setDuration(500L);
                disappearAnimator.addUpdateListener(invalidateUpdateListener);
                keyframe = Keyframe.ofFloat(0.0F, transitionEndRadiusMultiplier);
                keyframe1 = Keyframe.ofFloat(0.2F, transitionEndRadiusMultiplier);
                keyframe2 = Keyframe.ofFloat(0.84F, transitionMidRadiusMultiplier);
                Keyframe keyframe3 = Keyframe.ofFloat(1.0F, 1.0F);
                reappearAnimator = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[] {
                    PropertyValuesHolder.ofKeyframe(animationRadiusMultiplierProperty, new Keyframe[] {
                        keyframe, keyframe1, keyframe2, keyframe3
                    }), PropertyValuesHolder.ofKeyframe("alpha", new Keyframe[] {
                        Keyframe.ofFloat(0.0F, 0.0F), Keyframe.ofFloat(0.2F, 0.0F), Keyframe.ofFloat(1.0F, 1.0F)
                    })
                }).setDuration(625L);
                reappearAnimator.addUpdateListener(invalidateUpdateListener);
                textGridValuesDirty = true;
                drawValuesReady = true;
            }
            if (textGridValuesDirty)
            {
                float f1 = circleRadius;
                float f4 = numbersRadiusMultiplier;
                calculateGridSizes(animationRadiusMultiplier * (f1 * f4), xCenter, yCenter, textSize, textGridHeights, textGridWidths);
                if (hasInnerCircle)
                {
                    float f2 = circleRadius;
                    float f5 = innerNumbersRadiusMultiplier;
                    calculateGridSizes(animationRadiusMultiplier * (f2 * f5), xCenter, yCenter, innerTextSize, innerTextGridHeights, innerTextGridWidths);
                }
                textGridValuesDirty = false;
            }
            drawTexts(canvas, textSize, typefaceLight, texts, textGridWidths, textGridHeights);
            if (hasInnerCircle)
            {
                drawTexts(canvas, innerTextSize, typefaceRegular, innerTexts, innerTextGridWidths, innerTextGridHeights);
                return;
            }
        }
    }

    final void setTheme(Context context, boolean flag)
    {
        context = context.getResources();
        int i;
        if (flag)
        {
            i = context.getColor(0x106000b);
        } else
        {
            i = context.getColor(0x7f0d0166);
        }
        paint.setColor(i);
    }


    private class InvalidateUpdateListener
        implements android.animation.ValueAnimator.AnimatorUpdateListener
    {

        private final RadialTextsView this$0;

        public final void onAnimationUpdate(ValueAnimator valueanimator)
        {
            invalidate();
        }

        InvalidateUpdateListener()
        {
            this$0 = RadialTextsView.this;
            super();
        }
    }


    private class _cls1 extends Property
    {

        public final Object get(Object obj)
        {
            return Float.valueOf(((RadialTextsView)obj).animationRadiusMultiplier);
        }

        public final void set(Object obj, Object obj1)
        {
            obj = (RadialTextsView)obj;
            obj.animationRadiusMultiplier = ((Float)obj1).floatValue();
            obj.textGridValuesDirty = true;
        }

        _cls1(Class class1, String s)
        {
            super(class1, s);
        }
    }

}
