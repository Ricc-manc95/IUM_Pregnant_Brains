// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.view;

import android.graphics.Point;
import android.view.View;
import android.view.ViewTreeObserver;

public final class FragmentViewDelegate
    implements android.view.ViewTreeObserver.OnGlobalLayoutListener
{

    public View fragmentView;
    public MeasurementSurrogate measurementSurrogate;

    public FragmentViewDelegate()
    {
    }

    public final void onGlobalLayout()
    {
        Point point = measurementSurrogate.getMeasureSpecs();
        fragmentView.measure(point.x, point.y);
        measurementSurrogate.onFragmentContentMeasurement(fragmentView.getMeasuredWidth(), fragmentView.getMeasuredHeight());
        if (fragmentView != null)
        {
            fragmentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
        fragmentView = null;
        measurementSurrogate = null;
    }

    private class MeasurementSurrogate
    {

        public abstract Point getMeasureSpecs();

        public abstract void onFragmentContentMeasurement(int i, int j);
    }

}
