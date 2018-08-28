// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

public abstract class ChipViewModel
{

    public ChipViewModel()
    {
    }

    public abstract int getBackgroundColor();

    public abstract ListenableFuture getBadgeRequestKey();

    public abstract int getBorderColor();

    public abstract Float getBorderWidth();

    public abstract int getChipType();

    public abstract ColorStyle getColorStyle();

    public abstract Supplier getContentDescription();

    public abstract int getCornerRadius();

    public abstract Boolean getDragHandles();

    public abstract int getEllipsizeType();

    public abstract int getForegroundColor();

    public abstract int getForegroundPaddingBottom();

    public abstract int getForegroundPaddingEnd();

    public abstract int getForegroundPaddingStart();

    public abstract int getForegroundPaddingTop();

    public abstract int getIcon();

    public abstract int getIconBadgeSize();

    public abstract int getIconHorizontalCorrection();

    public abstract IconMode getIconMode();

    public abstract int getIconSize();

    public abstract boolean getIsRtl();

    public abstract Optional getOptionalBackgroundImageViewModel();

    public abstract int getOuterBorderColor();

    public abstract Integer getPrimarySwipeIcon();

    public abstract List getPrimaryText();

    public abstract int getPrimaryTextInsetForIcon();

    public abstract Integer getRippleColor();

    public abstract String getSecondaryActionAction();

    public abstract String getSecondaryActionInfo();

    public abstract Integer getSecondarySwipeIcon();

    public abstract int getStrikeThroughText();

    public abstract Integer getStyledCornersCompatibilityMode();

    public abstract int getSupportedSwipeDirections();

    public abstract Integer getSwipeAccentColor();

    public abstract int getTextSize();

    public abstract int getVerticalAlignType();

    public abstract Builder toBuilder();
}
