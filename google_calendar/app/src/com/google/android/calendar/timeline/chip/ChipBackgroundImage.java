// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import com.android.bitmap.ReusableBitmap;
import com.google.android.apps.calendar.graphics.drawable.DrawableContainer;

public final class ChipBackgroundImage
{

    public final DrawableContainer backgroundContainer = new DrawableContainer();
    public final Drawable drawable;
    public final DrawableContainer imageContainer = new DrawableContainer();
    public ReusableBitmap reusableBitmap;

    public ChipBackgroundImage()
    {
        drawable = new LayerDrawable(new Drawable[] {
            backgroundContainer, imageContainer
        });
    }
}
