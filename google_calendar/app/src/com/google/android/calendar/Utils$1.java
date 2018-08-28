// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.animation.TimeInterpolator;

final class 
    implements TimeInterpolator
{

    public final float getInterpolation(float f)
    {
        return (float)Math.sin(3.141593F * f);
    }

    ()
    {
    }
}
