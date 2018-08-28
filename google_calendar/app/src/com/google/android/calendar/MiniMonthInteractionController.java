// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.support.v4.view.ViewPager;
import android.view.View;

public interface MiniMonthInteractionController
{

    public abstract int getCurrentMonthHeight();

    public abstract ViewPager getDatePicker();

    public abstract View getDatePickerContainer();

    public abstract View getDragUpView();

    public abstract boolean hasMiniMonth();

    public abstract boolean isFragmentAttached();

    public abstract boolean isMiniMonthDraggable();

    public abstract boolean isMiniMonthToggleable();

    public abstract boolean isMiniMonthVisible();

    public abstract void onMiniMonthVisibilityChanged(boolean flag);

    public abstract void onMiniMonthVisibilityChanging(boolean flag);

    public abstract void pointTo(int i);

    public abstract void refreshState();

    public abstract void setViewTranslationX(float f);

    public abstract void setViewTranslationY(float f);
}
