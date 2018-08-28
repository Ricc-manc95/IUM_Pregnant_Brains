// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.animation.Animator;
import android.view.animation.Animation;

final class animator
{

    public final Animation animation;
    public final Animator animator;

    (Animator animator1)
    {
        animation = null;
        animator = animator1;
        if (animator1 == null)
        {
            throw new IllegalStateException("Animator cannot be null");
        } else
        {
            return;
        }
    }

    animator(Animation animation1)
    {
        animation = animation1;
        animator = null;
        if (animation1 == null)
        {
            throw new IllegalStateException("Animation cannot be null");
        } else
        {
            return;
        }
    }
}
