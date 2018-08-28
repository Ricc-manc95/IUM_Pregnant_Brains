// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.animation;

import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

public final class QuantumInterpolators
{

    public static final Interpolator FAST_OUT_GENTLE_IN = new PathInterpolator(0.4F, 0.0F, 0.6F, 1.0F);
    public static final Interpolator FAST_OUT_LINEAR_IN = new PathInterpolator(0.4F, 0.0F, 1.0F, 1.0F);
    public static final Interpolator FAST_OUT_SLOW_IN = new PathInterpolator(0.4F, 0.0F, 0.2F, 1.0F);
    public static final Interpolator LINEAR_OUT_SLOW_IN = new PathInterpolator(0.0F, 0.0F, 0.2F, 1.0F);

}
