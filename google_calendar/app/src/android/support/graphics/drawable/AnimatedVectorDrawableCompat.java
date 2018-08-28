// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import android.util.AttributeSet;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.support.graphics.drawable:
//            VectorDrawableCommon, VectorDrawableCompat, AndroidResources, AnimatorInflaterCompat

public final class AnimatedVectorDrawableCompat extends VectorDrawableCommon
    implements Animatable
{

    private AnimatedVectorDrawableCompatState mAnimatedVectorState;
    private ArgbEvaluator mArgbEvaluator;
    public final android.graphics.drawable.Drawable.Callback mCallback;
    private Context mContext;

    AnimatedVectorDrawableCompat()
    {
        this(null, null, null);
    }

    public AnimatedVectorDrawableCompat(Context context)
    {
        this(context, null, null);
    }

    private AnimatedVectorDrawableCompat(Context context, AnimatedVectorDrawableCompatState animatedvectordrawablecompatstate, Resources resources)
    {
        mArgbEvaluator = null;
        mCallback = new _cls1();
        mContext = context;
        mAnimatedVectorState = new AnimatedVectorDrawableCompatState(null, mCallback, null);
    }

    public final void applyTheme(android.content.res.Resources.Theme theme)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.applyTheme(theme);
        }
    }

    public final boolean canApplyTheme()
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.canApplyTheme();
        } else
        {
            return false;
        }
    }

    public final volatile void clearColorFilter()
    {
        super.clearColorFilter();
    }

    public final void draw(Canvas canvas)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.draw(canvas);
        } else
        {
            mAnimatedVectorState.mVectorDrawable.draw(canvas);
            if (mAnimatedVectorState.mAnimatorSet.isStarted())
            {
                invalidateSelf();
                return;
            }
        }
    }

    public final int getAlpha()
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.getAlpha();
        } else
        {
            return mAnimatedVectorState.mVectorDrawable.getAlpha();
        }
    }

    public final int getChangingConfigurations()
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.getChangingConfigurations();
        } else
        {
            return super.getChangingConfigurations() | mAnimatedVectorState.mChangingConfigurations;
        }
    }

    public final volatile ColorFilter getColorFilter()
    {
        return super.getColorFilter();
    }

    public final android.graphics.drawable.Drawable.ConstantState getConstantState()
    {
        if (mDelegateDrawable != null && android.os.Build.VERSION.SDK_INT >= 24)
        {
            return new AnimatedVectorDrawableDelegateState(mDelegateDrawable.getConstantState());
        } else
        {
            return null;
        }
    }

    public final volatile Drawable getCurrent()
    {
        return super.getCurrent();
    }

    public final int getIntrinsicHeight()
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.getIntrinsicHeight();
        } else
        {
            return mAnimatedVectorState.mVectorDrawable.getIntrinsicHeight();
        }
    }

    public final int getIntrinsicWidth()
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.getIntrinsicWidth();
        } else
        {
            return mAnimatedVectorState.mVectorDrawable.getIntrinsicWidth();
        }
    }

    public final volatile int getMinimumHeight()
    {
        return super.getMinimumHeight();
    }

    public final volatile int getMinimumWidth()
    {
        return super.getMinimumWidth();
    }

    public final int getOpacity()
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.getOpacity();
        } else
        {
            return mAnimatedVectorState.mVectorDrawable.getOpacity();
        }
    }

    public final volatile boolean getPadding(Rect rect)
    {
        return super.getPadding(rect);
    }

    public final volatile int[] getState()
    {
        return super.getState();
    }

    public final volatile Region getTransparentRegion()
    {
        return super.getTransparentRegion();
    }

    public final void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset)
        throws XmlPullParserException, IOException
    {
        inflate(resources, xmlpullparser, attributeset, null);
    }

    public final void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        int i;
        int k;
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.inflate(resources, xmlpullparser, attributeset, theme);
            return;
        }
        i = xmlpullparser.getEventType();
        k = xmlpullparser.getDepth();
_L2:
        Object obj;
        if (i == 1 || xmlpullparser.getDepth() < k + 1 && i == 3)
        {
            break MISSING_BLOCK_LABEL_415;
        }
        if (i == 2)
        {
            obj = xmlpullparser.getName();
            if (!"animated-vector".equals(obj))
            {
                break; /* Loop/switch isn't completed */
            }
            obj = AndroidResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE;
            if (theme == null)
            {
                obj = resources.obtainAttributes(attributeset, ((int []) (obj)));
            } else
            {
                obj = theme.obtainStyledAttributes(attributeset, ((int []) (obj)), 0, 0);
            }
            i = ((TypedArray) (obj)).getResourceId(0, 0);
            if (i != 0)
            {
                VectorDrawableCompat vectordrawablecompat = VectorDrawableCompat.create(resources, i, theme);
                vectordrawablecompat.mAllowCaching = false;
                vectordrawablecompat.setCallback(mCallback);
                if (mAnimatedVectorState.mVectorDrawable != null)
                {
                    mAnimatedVectorState.mVectorDrawable.setCallback(null);
                }
                mAnimatedVectorState.mVectorDrawable = vectordrawablecompat;
            }
            ((TypedArray) (obj)).recycle();
        }
_L4:
        i = xmlpullparser.next();
        if (true) goto _L2; else goto _L1
_L1:
        if (!"target".equals(obj)) goto _L4; else goto _L3
_L3:
        TypedArray typedarray;
label0:
        {
            typedarray = resources.obtainAttributes(attributeset, AndroidResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE_TARGET);
            String s = typedarray.getString(0);
            int j = typedarray.getResourceId(1, 0);
            if (j != 0)
            {
                if (mContext == null)
                {
                    break label0;
                }
                Object obj1 = mContext;
                if (android.os.Build.VERSION.SDK_INT >= 24)
                {
                    obj1 = AnimatorInflater.loadAnimator(((Context) (obj1)), j);
                } else
                {
                    obj1 = AnimatorInflaterCompat.loadAnimator(((Context) (obj1)), ((Context) (obj1)).getResources(), ((Context) (obj1)).getTheme(), j, 1.0F);
                }
                ((Animator) (obj1)).setTarget(mAnimatedVectorState.mVectorDrawable.mVectorState.mVPathRenderer.mVGTargetsMap.get(s));
                if (mAnimatedVectorState.mAnimators == null)
                {
                    mAnimatedVectorState.mAnimators = new ArrayList();
                    mAnimatedVectorState.mTargetNameMap = new ArrayMap();
                }
                mAnimatedVectorState.mAnimators.add(obj1);
                mAnimatedVectorState.mTargetNameMap.put(obj1, s);
            }
            typedarray.recycle();
        }
          goto _L4
        typedarray.recycle();
        throw new IllegalStateException("Context can't be null when inflating animators");
        resources = mAnimatedVectorState;
        if (((AnimatedVectorDrawableCompatState) (resources)).mAnimatorSet == null)
        {
            resources.mAnimatorSet = new AnimatorSet();
        }
        ((AnimatedVectorDrawableCompatState) (resources)).mAnimatorSet.playTogether(((AnimatedVectorDrawableCompatState) (resources)).mAnimators);
        return;
    }

    public final boolean isAutoMirrored()
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.isAutoMirrored();
        } else
        {
            return mAnimatedVectorState.mVectorDrawable.isAutoMirrored();
        }
    }

    public final boolean isRunning()
    {
        if (mDelegateDrawable != null)
        {
            return ((AnimatedVectorDrawable)mDelegateDrawable).isRunning();
        } else
        {
            return mAnimatedVectorState.mAnimatorSet.isRunning();
        }
    }

    public final boolean isStateful()
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.isStateful();
        } else
        {
            return mAnimatedVectorState.mVectorDrawable.isStateful();
        }
    }

    public final volatile void jumpToCurrentState()
    {
        super.jumpToCurrentState();
    }

    public final Drawable mutate()
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.mutate();
        }
        return this;
    }

    protected final void onBoundsChange(Rect rect)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.setBounds(rect);
            return;
        } else
        {
            mAnimatedVectorState.mVectorDrawable.setBounds(rect);
            return;
        }
    }

    protected final boolean onLevelChange(int i)
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.setLevel(i);
        } else
        {
            return mAnimatedVectorState.mVectorDrawable.setLevel(i);
        }
    }

    protected final boolean onStateChange(int ai[])
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.setState(ai);
        } else
        {
            return mAnimatedVectorState.mVectorDrawable.setState(ai);
        }
    }

    public final void setAlpha(int i)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.setAlpha(i);
            return;
        } else
        {
            mAnimatedVectorState.mVectorDrawable.setAlpha(i);
            return;
        }
    }

    public final void setAutoMirrored(boolean flag)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.setAutoMirrored(flag);
            return;
        } else
        {
            mAnimatedVectorState.mVectorDrawable.setAutoMirrored(flag);
            return;
        }
    }

    public final volatile void setChangingConfigurations(int i)
    {
        super.setChangingConfigurations(i);
    }

    public final volatile void setColorFilter(int i, android.graphics.PorterDuff.Mode mode)
    {
        super.setColorFilter(i, mode);
    }

    public final void setColorFilter(ColorFilter colorfilter)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.setColorFilter(colorfilter);
            return;
        } else
        {
            mAnimatedVectorState.mVectorDrawable.setColorFilter(colorfilter);
            return;
        }
    }

    public final volatile void setFilterBitmap(boolean flag)
    {
        super.setFilterBitmap(flag);
    }

    public final volatile void setHotspot(float f, float f1)
    {
        super.setHotspot(f, f1);
    }

    public final volatile void setHotspotBounds(int i, int j, int k, int l)
    {
        super.setHotspotBounds(i, j, k, l);
    }

    public final volatile boolean setState(int ai[])
    {
        return super.setState(ai);
    }

    public final void setTint(int i)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.setTint(i);
            return;
        } else
        {
            mAnimatedVectorState.mVectorDrawable.setTint(i);
            return;
        }
    }

    public final void setTintList(ColorStateList colorstatelist)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.setTintList(colorstatelist);
            return;
        } else
        {
            mAnimatedVectorState.mVectorDrawable.setTintList(colorstatelist);
            return;
        }
    }

    public final void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.setTintMode(mode);
            return;
        } else
        {
            mAnimatedVectorState.mVectorDrawable.setTintMode(mode);
            return;
        }
    }

    public final boolean setVisible(boolean flag, boolean flag1)
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.setVisible(flag, flag1);
        } else
        {
            mAnimatedVectorState.mVectorDrawable.setVisible(flag, flag1);
            return super.setVisible(flag, flag1);
        }
    }

    public final void start()
    {
        if (mDelegateDrawable != null)
        {
            ((AnimatedVectorDrawable)mDelegateDrawable).start();
        } else
        if (!mAnimatedVectorState.mAnimatorSet.isStarted())
        {
            mAnimatedVectorState.mAnimatorSet.start();
            invalidateSelf();
            return;
        }
    }

    public final void stop()
    {
        if (mDelegateDrawable != null)
        {
            ((AnimatedVectorDrawable)mDelegateDrawable).stop();
            return;
        } else
        {
            mAnimatedVectorState.mAnimatorSet.end();
            return;
        }
    }

    private class _cls1
        implements android.graphics.drawable.Drawable.Callback
    {

        private final AnimatedVectorDrawableCompat this$0;

        public final void invalidateDrawable(Drawable drawable)
        {
            invalidateSelf();
        }

        public final void scheduleDrawable(Drawable drawable, Runnable runnable, long l)
        {
            scheduleSelf(runnable, l);
        }

        public final void unscheduleDrawable(Drawable drawable, Runnable runnable)
        {
            unscheduleSelf(runnable);
        }

        _cls1()
        {
            this$0 = AnimatedVectorDrawableCompat.this;
            super();
        }
    }


    private class AnimatedVectorDrawableCompatState extends android.graphics.drawable.Drawable.ConstantState
    {

        public AnimatorSet mAnimatorSet;
        public ArrayList mAnimators;
        public int mChangingConfigurations;
        public ArrayMap mTargetNameMap;
        public VectorDrawableCompat mVectorDrawable;

        public final int getChangingConfigurations()
        {
            return mChangingConfigurations;
        }

        public final Drawable newDrawable()
        {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        public final Drawable newDrawable(Resources resources)
        {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        public AnimatedVectorDrawableCompatState(AnimatedVectorDrawableCompatState animatedvectordrawablecompatstate, android.graphics.drawable.Drawable.Callback callback, Resources resources)
        {
            int i = 0;
            super();
            if (animatedvectordrawablecompatstate != null)
            {
                mChangingConfigurations = animatedvectordrawablecompatstate.mChangingConfigurations;
                if (animatedvectordrawablecompatstate.mVectorDrawable != null)
                {
                    android.graphics.drawable.Drawable.ConstantState constantstate = animatedvectordrawablecompatstate.mVectorDrawable.getConstantState();
                    int j;
                    if (resources != null)
                    {
                        mVectorDrawable = (VectorDrawableCompat)constantstate.newDrawable(resources);
                    } else
                    {
                        mVectorDrawable = (VectorDrawableCompat)constantstate.newDrawable();
                    }
                    mVectorDrawable = (VectorDrawableCompat)mVectorDrawable.mutate();
                    mVectorDrawable.setCallback(callback);
                    mVectorDrawable.setBounds(animatedvectordrawablecompatstate.mVectorDrawable.getBounds());
                    mVectorDrawable.mAllowCaching = false;
                }
                if (animatedvectordrawablecompatstate.mAnimators != null)
                {
                    j = animatedvectordrawablecompatstate.mAnimators.size();
                    mAnimators = new ArrayList(j);
                    mTargetNameMap = new ArrayMap(j);
                    for (; i < j; i++)
                    {
                        resources = (Animator)animatedvectordrawablecompatstate.mAnimators.get(i);
                        callback = resources.clone();
                        resources = (String)animatedvectordrawablecompatstate.mTargetNameMap.get(resources);
                        callback.setTarget(mVectorDrawable.mVectorState.mVPathRenderer.mVGTargetsMap.get(resources));
                        mAnimators.add(callback);
                        mTargetNameMap.put(callback, resources);
                    }

                    if (mAnimatorSet == null)
                    {
                        mAnimatorSet = new AnimatorSet();
                    }
                    mAnimatorSet.playTogether(mAnimators);
                }
            }
        }
    }


    private class AnimatedVectorDrawableDelegateState extends android.graphics.drawable.Drawable.ConstantState
    {

        private final android.graphics.drawable.Drawable.ConstantState mDelegateState;

        public final boolean canApplyTheme()
        {
            return mDelegateState.canApplyTheme();
        }

        public final int getChangingConfigurations()
        {
            return mDelegateState.getChangingConfigurations();
        }

        public final Drawable newDrawable()
        {
            AnimatedVectorDrawableCompat animatedvectordrawablecompat = new AnimatedVectorDrawableCompat();
            animatedvectordrawablecompat.mDelegateDrawable = mDelegateState.newDrawable();
            animatedvectordrawablecompat.mDelegateDrawable.setCallback(animatedvectordrawablecompat.mCallback);
            return animatedvectordrawablecompat;
        }

        public final Drawable newDrawable(Resources resources)
        {
            AnimatedVectorDrawableCompat animatedvectordrawablecompat = new AnimatedVectorDrawableCompat();
            animatedvectordrawablecompat.mDelegateDrawable = mDelegateState.newDrawable(resources);
            animatedvectordrawablecompat.mDelegateDrawable.setCallback(animatedvectordrawablecompat.mCallback);
            return animatedvectordrawablecompat;
        }

        public final Drawable newDrawable(Resources resources, android.content.res.Resources.Theme theme)
        {
            AnimatedVectorDrawableCompat animatedvectordrawablecompat = new AnimatedVectorDrawableCompat();
            animatedvectordrawablecompat.mDelegateDrawable = mDelegateState.newDrawable(resources, theme);
            animatedvectordrawablecompat.mDelegateDrawable.setCallback(animatedvectordrawablecompat.mCallback);
            return animatedvectordrawablecompat;
        }

        public AnimatedVectorDrawableDelegateState(android.graphics.drawable.Drawable.ConstantState constantstate)
        {
            mDelegateState = constantstate;
        }
    }

}
