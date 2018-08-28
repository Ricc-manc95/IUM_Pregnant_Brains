// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.design.animation:
//            AnimationUtils, MotionTiming

public final class MotionSpec
{

    private final SimpleArrayMap timings = new SimpleArrayMap();

    public MotionSpec()
    {
    }

    public static MotionSpec createFromResource(Context context, int i)
    {
        ArrayList arraylist;
        try
        {
            context = AnimatorInflater.loadAnimator(context, i);
            if (context instanceof AnimatorSet)
            {
                return createSpecFromAnimators(((AnimatorSet)context).getChildAnimations());
            }
        }
        catch (Exception exception)
        {
            context = String.valueOf(Integer.toHexString(i));
            if (context.length() != 0)
            {
                context = "Can't load animation resource ID #0x".concat(context);
            } else
            {
                context = new String("Can't load animation resource ID #0x");
            }
            Log.w("MotionSpec", context, exception);
            return null;
        }
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_51;
        }
        arraylist = new ArrayList();
        arraylist.add(context);
        context = createSpecFromAnimators(arraylist);
        return context;
        return null;
    }

    private static MotionSpec createSpecFromAnimators(List list)
    {
        MotionSpec motionspec = new MotionSpec();
        int j = list.size();
        for (int i = 0; i < j;)
        {
            Object obj = (Animator)list.get(i);
            if (obj instanceof ObjectAnimator)
            {
                ObjectAnimator objectanimator = (ObjectAnimator)obj;
                String s = objectanimator.getPropertyName();
                long l = objectanimator.getStartDelay();
                long l1 = objectanimator.getDuration();
                android.animation.TimeInterpolator timeinterpolator = objectanimator.getInterpolator();
                if ((timeinterpolator instanceof AccelerateDecelerateInterpolator) || timeinterpolator == null)
                {
                    obj = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
                } else
                if (timeinterpolator instanceof AccelerateInterpolator)
                {
                    obj = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
                } else
                {
                    obj = timeinterpolator;
                    if (timeinterpolator instanceof DecelerateInterpolator)
                    {
                        obj = AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR;
                    }
                }
                obj = new MotionTiming(l, l1, ((android.animation.TimeInterpolator) (obj)));
                obj.repeatCount = objectanimator.getRepeatCount();
                obj.repeatMode = objectanimator.getRepeatMode();
                motionspec.timings.put(s, obj);
                i++;
            } else
            {
                list = String.valueOf(obj);
                throw new IllegalArgumentException((new StringBuilder(String.valueOf(list).length() + 36)).append("Animator must be an ObjectAnimator: ").append(list).toString());
            }
        }

        return motionspec;
    }

    public final boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        } else
        {
            obj = (MotionSpec)obj;
            return timings.equals(((MotionSpec) (obj)).timings);
        }
    }

    public final MotionTiming getTiming(String s)
    {
        boolean flag;
        if (timings.get(s) != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            return (MotionTiming)timings.get(s);
        }
    }

    public final int hashCode()
    {
        return timings.hashCode();
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append('\n');
        stringbuilder.append(getClass().getName());
        stringbuilder.append('{');
        stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringbuilder.append(" timings: ");
        stringbuilder.append(timings);
        stringbuilder.append("}\n");
        return stringbuilder.toString();
    }
}
