// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.support.v7.view.CollapsibleActionView;
import android.view.View;
import android.widget.FrameLayout;

final class addView extends FrameLayout
    implements CollapsibleActionView
{

    public final android.view.CollapsibleActionView mWrappedView;

    public final void onActionViewCollapsed()
    {
        mWrappedView.onActionViewCollapsed();
    }

    public final void onActionViewExpanded()
    {
        mWrappedView.onActionViewExpanded();
    }

    (View view)
    {
        super(view.getContext());
        mWrappedView = (android.view.CollapsibleActionView)view;
        addView(view);
    }
}
