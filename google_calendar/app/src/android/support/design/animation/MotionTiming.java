// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.animation;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;

// Referenced classes of package android.support.design.animation:
//            AnimationUtils

public final class MotionTiming
{

    private long delay;
    private long duration;
    private TimeInterpolator interpolator;
    public int repeatCount;
    public int repeatMode;

    public MotionTiming(long l, long l1, TimeInterpolator timeinterpolator)
    {
        delay = 0L;
        duration = 300L;
        interpolator = null;
        repeatCount = 0;
        repeatMode = 1;
        delay = l;
        duration = l1;
        interpolator = timeinterpolator;
    }

    public final void apply(Animator animator)
    {
        animator.setStartDelay(delay);
        animator.setDuration(duration);
        TimeInterpolator timeinterpolator;
        if (interpolator != null)
        {
            timeinterpolator = interpolator;
        } else
        {
            timeinterpolator = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
        }
        animator.setInterpolator(timeinterpolator);
        if (animator instanceof ValueAnimator)
        {
            ((ValueAnimator)animator).setRepeatCount(repeatCount);
            ((ValueAnimator)animator).setRepeatMode(repeatMode);
        }
    }

    public final boolean equals(Object obj)
    {
        boolean flag1 = false;
        boolean flag;
        if (this == obj)
        {
            flag = true;
        } else
        {
            flag = flag1;
            if (obj != null)
            {
                flag = flag1;
                if (getClass() == obj.getClass())
                {
                    MotionTiming motiontiming = (MotionTiming)obj;
                    flag = flag1;
                    if (delay == motiontiming.delay)
                    {
                        flag = flag1;
                        if (duration == motiontiming.duration)
                        {
                            flag = flag1;
                            if (repeatCount == motiontiming.repeatCount)
                            {
                                flag = flag1;
                                if (repeatMode == motiontiming.repeatMode)
                                {
                                    Class class1;
                                    if (interpolator != null)
                                    {
                                        obj = interpolator;
                                    } else
                                    {
                                        obj = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
                                    }
                                    class1 = obj.getClass();
                                    if (motiontiming.interpolator != null)
                                    {
                                        obj = motiontiming.interpolator;
                                    } else
                                    {
                                        obj = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
                                    }
                                    return class1.equals(obj.getClass());
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    public final int hashCode()
    {
        int i = (int)(delay ^ delay >>> 32);
        int j = (int)(duration ^ duration >>> 32);
        TimeInterpolator timeinterpolator;
        if (interpolator != null)
        {
            timeinterpolator = interpolator;
        } else
        {
            timeinterpolator = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
        }
        return ((timeinterpolator.getClass().hashCode() + (i * 31 + j) * 31) * 31 + repeatCount) * 31 + repeatMode;
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append('\n');
        stringbuilder.append(getClass().getName());
        stringbuilder.append('{');
        stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringbuilder.append(" delay: ");
        stringbuilder.append(delay);
        stringbuilder.append(" duration: ");
        stringbuilder.append(duration);
        stringbuilder.append(" interpolator: ");
        TimeInterpolator timeinterpolator;
        if (interpolator != null)
        {
            timeinterpolator = interpolator;
        } else
        {
            timeinterpolator = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
        }
        stringbuilder.append(timeinterpolator.getClass());
        stringbuilder.append(" repeatCount: ");
        stringbuilder.append(repeatCount);
        stringbuilder.append(" repeatMode: ");
        stringbuilder.append(repeatMode);
        stringbuilder.append("}\n");
        return stringbuilder.toString();
    }
}
