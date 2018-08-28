// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.res.Configuration;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;

public final class ScrollingTabContainerView extends HorizontalScrollView
    implements android.widget.AdapterView.OnItemSelectedListener
{

    public static void setAllowCollapse$51D2ILG_0()
    {
        throw new NoSuchMethodError();
    }

    public final void onAttachedToWindow()
    {
        throw new NoSuchMethodError();
    }

    protected final void onConfigurationChanged(Configuration configuration)
    {
        throw new NoSuchMethodError();
    }

    public final void onDetachedFromWindow()
    {
        throw new NoSuchMethodError();
    }

    public final void onItemSelected(AdapterView adapterview, View view, int i, long l)
    {
        throw new NoSuchMethodError();
    }

    public final void onMeasure(int i, int j)
    {
        throw new NoSuchMethodError();
    }

    public final void onNothingSelected(AdapterView adapterview)
    {
        throw new NoSuchMethodError();
    }

    public final void setContentHeight(int i)
    {
        requestLayout();
    }

    public final void setTabSelected(int i)
    {
        throw new NullPointerException();
    }

    static 
    {
        new DecelerateInterpolator();
    }
}
