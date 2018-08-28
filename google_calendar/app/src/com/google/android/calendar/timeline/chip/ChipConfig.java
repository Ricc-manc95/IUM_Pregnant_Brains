// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import com.google.android.calendar.material.Material;
import com.google.android.calendar.utils.text.CustomTypefaceSpan;

final class ChipConfig
{

    public final int badgeHeight;
    public final int badgePadding;
    public final int badgeWidth;
    public final StyleSpan boldSpan = new StyleSpan(1);
    public final int chipFootprintColor;
    public final int chipFootprintRippleColor;
    public final int contactPhotoActualHeight;
    public final int contactPhotoActualWidth;
    public final int focusedColor;
    public final TypefaceSpan mediumSpan;
    public final int minimumWidthRightPadding;
    public final int minimumWidthToShowContents;
    public final StrikethroughSpan strikeThroughSpan = new StrikethroughSpan();
    public final int swipeIconIndent;
    public final int swipeThreshold;

    ChipConfig(Context context)
    {
        Resources resources = context.getResources();
        if (Material.robotoMedium != null)
        {
            context = Material.robotoMedium;
        } else
        {
            context = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = context;
        }
        mediumSpan = new CustomTypefaceSpan("Roboto-Light", context);
        focusedColor = resources.getColor(0x7f0d005e);
        minimumWidthToShowContents = resources.getDimensionPixelSize(0x7f0e009e);
        minimumWidthRightPadding = resources.getDimensionPixelSize(0x7f0e0152);
        contactPhotoActualWidth = resources.getDimensionPixelSize(0x7f0e0088);
        contactPhotoActualHeight = resources.getDimensionPixelSize(0x7f0e0087);
        badgeWidth = resources.getDimensionPixelSize(0x7f0e008a);
        badgeHeight = resources.getDimensionPixelSize(0x7f0e0089);
        badgePadding = resources.getDimensionPixelSize(0x7f0e00a2);
        swipeThreshold = resources.getDimensionPixelSize(0x7f0e00a4);
        swipeIconIndent = resources.getDimensionPixelSize(0x7f0e00a3);
        chipFootprintColor = resources.getColor(0x7f0d0214);
        chipFootprintRippleColor = resources.getColor(0x7f0d0215);
    }
}
