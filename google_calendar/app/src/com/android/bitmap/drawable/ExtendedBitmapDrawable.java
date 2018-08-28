// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap.drawable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Trace;
import android.view.animation.LinearInterpolator;
import com.android.bitmap.BitmapCache;
import com.android.bitmap.DecodeAggregator;
import com.android.bitmap.RequestKey;
import com.android.bitmap.ReusableBitmap;

// Referenced classes of package com.android.bitmap.drawable:
//            BasicBitmapDrawable, TileDrawable

public class ExtendedBitmapDrawable extends BasicBitmapDrawable
    implements Runnable
{
    public static final class ExtendedOptions
    {

        public int backgroundColor;
        public DecodeAggregator decodeAggregator;
        public float decodeHorizontalCenter;
        public float decodeVerticalCenter;
        public final int features;
        public int parallaxDirection;
        public float parallaxSpeedMultiplier;
        public Drawable placeholder;
        public int placeholderAnimationDuration;
        public Drawable progressBar;

        public ExtendedOptions(int i)
        {
            this(i, null, null);
        }

        private ExtendedOptions(int i, Drawable drawable, Drawable drawable1)
        {
            decodeHorizontalCenter = 0.5F;
            decodeVerticalCenter = 0.5F;
            decodeAggregator = null;
            parallaxSpeedMultiplier = 1.0F;
            parallaxDirection = 0;
            backgroundColor = 0;
            placeholderAnimationDuration = 0;
            features = i;
            placeholder = null;
            progressBar = null;
        }
    }

    static final class Placeholder extends TileDrawable
    {

        public int pulseAlpha;
        public final ValueAnimator pulseAnimator;
        public boolean pulseEnabled;

        protected final int getInnerAlpha()
        {
            return pulseAlpha;
        }

        public final boolean setVisible(boolean flag)
        {
            flag = super.setVisible(flag);
            if (flag)
            {
                if (isVisible())
                {
                    if (pulseAnimator != null && pulseEnabled && !pulseAnimator.isStarted())
                    {
                        pulseAnimator.start();
                    }
                } else
                {
                    boolean flag1;
                    if (super.fadeAlpha == 0)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    if (flag1 && pulseAnimator != null)
                    {
                        pulseAnimator.cancel();
                        pulseAlpha = 255;
                        invalidateSelf();
                        return flag;
                    }
                }
            }
            return flag;
        }

        public Placeholder(Drawable drawable, Resources resources1, int i, int j, int k, ExtendedOptions extendedoptions)
        {
            super(drawable, i, j, k, extendedoptions);
            pulseEnabled = true;
            pulseAlpha = 255;
            class _cls2 extends AnimatorListenerAdapter
            {

                private final Placeholder this$0;

                public final void onAnimationEnd(Animator animator)
                {
                    animator = Placeholder.this;
                    if (((Placeholder) (animator)).pulseAnimator != null)
                    {
                        ((Placeholder) (animator)).pulseAnimator.cancel();
                        animator.pulseAlpha = 255;
                        animator.invalidateSelf();
                    }
                }

                _cls2()
                {
                    this$0 = Placeholder.this;
                    super();
                }
            }

            if (extendedoptions.placeholderAnimationDuration == -1)
            {
                pulseAnimator = null;
            } else
            {
                class _cls1
                    implements android.animation.ValueAnimator.AnimatorUpdateListener
                {

                    private final Placeholder this$0;

                    public final void onAnimationUpdate(ValueAnimator valueanimator)
                    {
                        pulseAlpha = ((Integer)valueanimator.getAnimatedValue()).intValue();
                        invalidateSelf();
                    }

                _cls1()
                {
                    this$0 = Placeholder.this;
                    super();
                }
                }

                long l;
                if (extendedoptions.placeholderAnimationDuration == 0)
                {
                    l = resources1.getInteger(0x7f110004);
                } else
                {
                    l = extendedoptions.placeholderAnimationDuration;
                }
                pulseAnimator = ValueAnimator.ofInt(new int[] {
                    55, 255
                }).setDuration(l);
                pulseAnimator.setRepeatCount(-1);
                pulseAnimator.setRepeatMode(2);
                pulseAnimator.addUpdateListener(new _cls1());
            }
            mFadeOutAnimator.addListener(new _cls2());
        }
    }

    static final class Progress extends TileDrawable
    {

        public final ValueAnimator rotateAnimator;

        public final boolean setVisible(boolean flag)
        {
            flag = super.setVisible(flag);
            if (flag)
            {
                if (isVisible())
                {
                    if (rotateAnimator != null)
                    {
                        rotateAnimator.start();
                    }
                } else
                {
                    boolean flag1;
                    if (super.fadeAlpha == 0)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    if (flag1 && rotateAnimator != null)
                    {
                        rotateAnimator.cancel();
                        return flag;
                    }
                }
            }
            return flag;
        }

        public Progress(Drawable drawable, Resources resources1, int i, int j, int k, ExtendedOptions extendedoptions)
        {
            super(drawable, i, j, k, extendedoptions);
            rotateAnimator = ValueAnimator.ofInt(new int[] {
                0, 10000
            }).setDuration(resources1.getInteger(0x7f110006));
            rotateAnimator.setInterpolator(new LinearInterpolator());
            rotateAnimator.setRepeatCount(-1);
            class _cls1
                implements android.animation.ValueAnimator.AnimatorUpdateListener
            {

                private final Progress this$0;

                public final void onAnimationUpdate(ValueAnimator valueanimator)
                {
                    setLevel(((Integer)valueanimator.getAnimatedValue()).intValue());
                }

                _cls1()
                {
                    this$0 = Progress.this;
                    super();
                }
            }

            rotateAnimator.addUpdateListener(new _cls1());
            class _cls2 extends AnimatorListenerAdapter
            {

                private final Progress this$0;

                public final void onAnimationEnd(Animator animator)
                {
                    if (rotateAnimator != null)
                    {
                        rotateAnimator.cancel();
                    }
                }

                _cls2()
                {
                    this$0 = Progress.this;
                    super();
                }
            }

            mFadeOutAnimator.addListener(new _cls2());
        }
    }


    private int alpha;
    private final Handler handler = new Handler();
    private int loadState;
    public final ExtendedOptions opts;
    public float parallaxFraction;
    private Placeholder placeholder;
    private Progress progress;
    private int progressDelayMs;
    private final Resources resources;

    public ExtendedBitmapDrawable(Resources resources1, BitmapCache bitmapcache, boolean flag, ExtendedOptions extendedoptions)
    {
        super(resources1, bitmapcache, flag);
        parallaxFraction = 0.5F;
        loadState = 0;
        alpha = 255;
        resources = resources1;
        resources1 = extendedoptions;
        if (extendedoptions == null)
        {
            resources1 = new ExtendedOptions(0);
        }
        opts = resources1;
        onOptsChanged();
    }

    private final void setLoadState(int i)
    {
        Trace.beginSection("set load state");
        i;
        JVM INSTR tableswitch 0 4: default 40
    //                   0 79
    //                   1 146
    //                   2 250
    //                   3 292
    //                   4 327;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        Trace.endSection();
        loadState = i;
        if (placeholder != null)
        {
            placeholder.isVisible();
        }
        if (progress != null)
        {
            progress.isVisible();
        }
        return;
_L2:
        if (placeholder != null)
        {
            Placeholder placeholder1 = placeholder;
            placeholder1.alpha = 0;
            placeholder1.fadeAlpha = 0;
            placeholder1.setVisible(false);
            placeholder1.invalidateSelf();
        }
        if (progress != null)
        {
            Progress progress1 = progress;
            progress1.alpha = 0;
            progress1.fadeAlpha = 0;
            progress1.setVisible(false);
            progress1.invalidateSelf();
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (placeholder != null)
        {
            Placeholder placeholder2 = placeholder;
            placeholder2.pulseEnabled = true;
            if (!placeholder2.pulseEnabled)
            {
                if (placeholder2.pulseAnimator != null)
                {
                    placeholder2.pulseAnimator.cancel();
                    placeholder2.pulseAlpha = 255;
                    placeholder2.invalidateSelf();
                }
            } else
            if (placeholder2.pulseAnimator != null && !placeholder2.pulseAnimator.isStarted())
            {
                placeholder2.pulseAnimator.start();
            }
            placeholder.setVisible(true);
        }
        if (progress != null)
        {
            progress.setVisible(false);
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (progress != null)
        {
            if (placeholder != null)
            {
                placeholder.setVisible(false);
            }
            if (progress != null)
            {
                progress.setVisible(true);
            }
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (placeholder != null)
        {
            placeholder.setVisible(false);
        }
        if (progress != null)
        {
            progress.setVisible(false);
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if (placeholder != null)
        {
            Placeholder placeholder3 = placeholder;
            placeholder3.pulseEnabled = false;
            if (!placeholder3.pulseEnabled)
            {
                if (placeholder3.pulseAnimator != null)
                {
                    placeholder3.pulseAnimator.cancel();
                    placeholder3.pulseAlpha = 255;
                    placeholder3.invalidateSelf();
                }
            } else
            if (placeholder3.pulseAnimator != null && !placeholder3.pulseAnimator.isStarted())
            {
                placeholder3.pulseAnimator.start();
            }
            placeholder.setVisible(true);
        }
        if (progress != null)
        {
            progress.setVisible(false);
        }
        if (true) goto _L1; else goto _L7
_L7:
    }

    public void draw(Canvas canvas)
    {
        if (getBounds().isEmpty())
        {
            return;
        }
        Trace.beginSection("ext");
        super.draw(canvas);
        if (progress != null)
        {
            progress.setAlpha(alpha);
            onDrawPlaceholderOrProgress(canvas, progress);
        }
        if (placeholder != null)
        {
            placeholder.setAlpha(alpha);
            onDrawPlaceholderOrProgress(canvas, placeholder);
        }
        Trace.endSection();
    }

    public int getAlpha()
    {
        return alpha;
    }

    protected final float getDecodeHorizontalCenter()
    {
        return opts.decodeHorizontalCenter;
    }

    protected final float getDecodeVerticalCenter()
    {
        return opts.decodeVerticalCenter;
    }

    public final float getDrawHorizontalCenter()
    {
        if (opts.parallaxDirection == 1)
        {
            return parallaxFraction;
        } else
        {
            return super.getDrawHorizontalCenter();
        }
    }

    public final float getDrawVerticalCenter()
    {
        if (opts.parallaxDirection == 0)
        {
            return parallaxFraction;
        } else
        {
            return super.getDrawVerticalCenter();
        }
    }

    protected final float getDrawVerticalOffsetMultiplier()
    {
        return opts.parallaxSpeedMultiplier;
    }

    protected final void loadFileDescriptorFactoryOrByteArray()
    {
        if (mCurrKey == null || mDecodeWidth == 0 || mDecodeHeight == 0)
        {
            return;
        } else
        {
            setLoadState(1);
            super.loadFileDescriptorFactoryOrByteArray();
            return;
        }
    }

    protected void onBoundsChange(Rect rect)
    {
        super.onBoundsChange(rect);
        if (placeholder != null)
        {
            placeholder.setBounds(rect);
        }
        if (progress != null)
        {
            progress.setBounds(rect);
        }
    }

    public final void onDecodeBegin(RequestKey requestkey)
    {
        if (requestkey.equals(mCurrKey))
        {
            handler.postDelayed(this, progressDelayMs);
        }
        super.onDecodeBegin(requestkey);
    }

    public final void onDecodeCancel(RequestKey requestkey)
    {
        super.onDecodeCancel(requestkey);
    }

    public final void onDecodeComplete(RequestKey requestkey, ReusableBitmap reusablebitmap)
    {
        super.onDecodeComplete(requestkey, reusablebitmap);
    }

    protected final void onDecodeFailed()
    {
        super.onDecodeFailed();
        setLoadState(4);
    }

    protected void onDrawPlaceholderOrProgress(Canvas canvas, TileDrawable tiledrawable)
    {
        tiledrawable.draw(canvas);
    }

    public final void onOptsChanged()
    {
        ExtendedOptions extendedoptions = opts;
        if (extendedoptions.decodeHorizontalCenter < 0.0F || extendedoptions.decodeHorizontalCenter > 1.0F)
        {
            throw new IllegalStateException("ExtendedOptions: decodeHorizontalCenter must be within 0 and 1, inclusive");
        }
        if (extendedoptions.decodeVerticalCenter < 0.0F || extendedoptions.decodeVerticalCenter > 1.0F)
        {
            throw new IllegalStateException("ExtendedOptions: decodeVerticalCenter must be within 0 and 1, inclusive");
        }
        if ((extendedoptions.features & 1) != 0)
        {
            throw new IllegalStateException("ExtendedOptions: To support FEATURE_ORDERED_DISPLAY, decodeAggregator must be set.");
        }
        if ((extendedoptions.features & 2) != 0 && extendedoptions.parallaxSpeedMultiplier <= 1.0F)
        {
            throw new IllegalStateException("ExtendedOptions: To support FEATURE_PARALLAX, parallaxSpeedMultiplier must be greater than 1.");
        }
        if ((extendedoptions.features & 4) != 0)
        {
            if (extendedoptions.backgroundColor == 0 && extendedoptions.placeholder == null)
            {
                throw new IllegalStateException("ExtendedOptions: To support FEATURE_STATE_CHANGES, either backgroundColor or placeholder must be set.");
            }
            if (extendedoptions.placeholderAnimationDuration < -1)
            {
                throw new IllegalStateException("ExtendedOptions: To support FEATURE_STATE_CHANGES, placeholderAnimationDuration must be set correctly.");
            }
            if (extendedoptions.backgroundColor != 0 && Color.alpha(extendedoptions.backgroundColor) != 255)
            {
                throw new IllegalStateException("ExtendedOptions: To support FEATURE_STATE_CHANGES, backgroundColor must be set to an opaque color.");
            }
        }
        if ((opts.features & 4) != 0)
        {
            int k = resources.getInteger(0x7f110003);
            progressDelayMs = resources.getInteger(0x7f110005);
            int i = resources.getDimensionPixelSize(0x7f0e030b);
            int j = resources.getDimensionPixelSize(0x7f0e030b);
            Object obj;
            if (opts.placeholder != null)
            {
                obj = opts.placeholder.getConstantState();
                Rect rect;
                if (obj != null)
                {
                    obj = ((android.graphics.drawable.Drawable.ConstantState) (obj)).newDrawable(resources);
                } else
                {
                    obj = opts.placeholder;
                }
                rect = opts.placeholder.getBounds();
                if (rect.width() != 0)
                {
                    i = rect.width();
                } else
                if (((Drawable) (obj)).getIntrinsicWidth() != -1)
                {
                    i = ((Drawable) (obj)).getIntrinsicWidth();
                }
                if (rect.height() != 0)
                {
                    j = rect.height();
                } else
                if (((Drawable) (obj)).getIntrinsicHeight() != -1)
                {
                    j = ((Drawable) (obj)).getIntrinsicHeight();
                }
            } else
            {
                obj = null;
            }
            placeholder = new Placeholder(((Drawable) (obj)), resources, i, j, k, opts);
            placeholder.setCallback(this);
            placeholder.setBounds(getBounds());
            if (opts.progressBar != null)
            {
                i = resources.getDimensionPixelSize(0x7f0e0319);
                progress = new Progress(opts.progressBar.getConstantState().newDrawable(resources), resources, i, i, k, opts);
                progress.setCallback(this);
                progress.setBounds(getBounds());
            } else
            {
                progress = null;
            }
        }
        setLoadState(loadState);
    }

    public void run()
    {
        if (loadState == 1)
        {
            setLoadState(2);
        }
    }

    public void setAlpha(int i)
    {
        int j = getAlpha();
        super.setAlpha(i);
        alpha = i;
        if (i != j)
        {
            invalidateSelf();
        }
    }

    public void setBitmap(ReusableBitmap reusablebitmap)
    {
        if (reusablebitmap != null)
        {
            setLoadState(3);
        } else
        {
            onDecodeFailed();
        }
        super.setBitmap(reusablebitmap);
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        super.setColorFilter(colorfilter);
        if (placeholder != null)
        {
            placeholder.setColorFilter(colorfilter);
        }
        if (progress != null)
        {
            progress.setColorFilter(colorfilter);
        }
        invalidateSelf();
    }

    public final void setDecodeDimensions(int i, int j)
    {
        if (opts.parallaxDirection == 0)
        {
            super.setDecodeDimensions(i, (int)((float)j * opts.parallaxSpeedMultiplier));
            return;
        } else
        {
            super.setDecodeDimensions((int)((float)i * opts.parallaxSpeedMultiplier), j);
            return;
        }
    }

    protected final void setImage(RequestKey requestkey)
    {
        if (mCurrKey == null);
        handler.removeCallbacks(this);
        setLoadState(0);
        super.setImage(requestkey);
        if (requestkey == null)
        {
            setLoadState(4);
        }
    }

    static 
    {
        com/android/bitmap/drawable/ExtendedBitmapDrawable.getSimpleName();
        new RectF();
    }
}
