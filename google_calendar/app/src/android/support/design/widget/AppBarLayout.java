// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.content.Context;
import android.util.AttributeSet;

public final class AppBarLayout extends android.support.design.appbar.AppBarLayout
{
    public static class Behavior extends android.support.design.appbar.BaseBehavior
    {

        public Behavior()
        {
        }

        public Behavior(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }
    }

    public static class ScrollingViewBehavior extends android.support.design.appbar.ScrollingViewBehavior
    {

        public ScrollingViewBehavior()
        {
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }
    }


    protected final volatile android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        throw new NoSuchMethodError();
    }

    protected final volatile android.widget.LinearLayout.LayoutParams generateDefaultLayoutParams()
    {
        throw new NoSuchMethodError();
    }

    public final volatile android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        throw new NoSuchMethodError();
    }

    protected final volatile android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        throw new NoSuchMethodError();
    }

    public final volatile android.widget.LinearLayout.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        throw new NoSuchMethodError();
    }

    protected final volatile android.widget.LinearLayout.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        throw new NoSuchMethodError();
    }
}
