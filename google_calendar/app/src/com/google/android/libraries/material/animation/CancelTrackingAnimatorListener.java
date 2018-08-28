// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v4.util.SimpleArrayMap;

public class CancelTrackingAnimatorListener extends AnimatorListenerAdapter
{

    private SimpleArrayMap animatorCancelledMap;

    public CancelTrackingAnimatorListener()
    {
        animatorCancelledMap = new SimpleArrayMap();
    }

    protected final boolean isCancelled(Animator animator)
    {
        return animatorCancelledMap.containsKey(animator) && ((Boolean)animatorCancelledMap.get(animator)).booleanValue();
    }

    public void onAnimationCancel(Animator animator)
    {
        animatorCancelledMap.put(animator, Boolean.valueOf(true));
    }

    public void onAnimationStart(Animator animator)
    {
        animatorCancelledMap.put(animator, Boolean.valueOf(false));
    }
}
