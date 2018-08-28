// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            ChipViewModel

public abstract class 
{

    public abstract ChipViewModel build();

    public abstract  setBackgroundColor(int i);

    public abstract  setBadgeRequestKey(ListenableFuture listenablefuture);

    public abstract  setBorderColor(int i);

    public abstract  setChipType(int i);

    public abstract le setColorStyle(le le);

    public abstract le setContentDescription(Supplier supplier);

    public abstract le setCornerRadius(int i);

    public abstract le setDragHandles(Boolean boolean1);

    public abstract le setEllipsizeType(int i);

    public abstract le setForegroundColor(int i);

    public abstract le setForegroundPaddingBottom(int i);

    public abstract le setForegroundPaddingEnd(int i);

    public abstract le setForegroundPaddingStart(int i);

    public abstract le setForegroundPaddingTop(int i);

    public abstract le setIcon(int i);

    public abstract le setIconBadgeSize(int i);

    public abstract le setIconHorizontalCorrection(int i);

    public abstract  setIconMode( );

    public abstract  setIconSize(int i);

    public abstract  setIsRtl(boolean flag);

    public abstract  setOptionalBackgroundImageViewModel(Optional optional);

    public abstract  setOuterBorderColor(int i);

    public abstract  setPrimarySwipeIcon(Integer integer);

    public abstract  setPrimaryText(List list);

    public abstract  setPrimaryTextInsetForIcon(int i);

    public abstract  setRippleColor(Integer integer);

    public abstract  setSecondaryActionAction(String s);

    public abstract  setSecondaryActionInfo(String s);

    public abstract  setSecondarySwipeIcon(Integer integer);

    public abstract  setStrikeThroughText(int i);

    public abstract  setStyledCornersCompatibilityMode(Integer integer);

    public abstract  setSupportedSwipeDirections(int i);

    public abstract  setSwipeAccentColor(Integer integer);

    public abstract  setTextSize(int i);

    public abstract  setVerticalAlignType(int i);

    public ()
    {
    }
}
