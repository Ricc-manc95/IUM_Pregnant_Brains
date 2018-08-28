// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap.drawable;

import android.graphics.drawable.Drawable;
import com.android.bitmap.DecodeAggregator;

// Referenced classes of package com.android.bitmap.drawable:
//            ExtendedBitmapDrawable

public static final class progressBar
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

    public (int i)
    {
        this(i, null, null);
    }

    private <init>(int i, Drawable drawable, Drawable drawable1)
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
