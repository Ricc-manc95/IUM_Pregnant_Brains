// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;


public interface AnimationThresholdEvaluator
{

    public abstract boolean canAnimate(Object obj);

    public abstract boolean isAnimationThresholdMet(Object obj);
}