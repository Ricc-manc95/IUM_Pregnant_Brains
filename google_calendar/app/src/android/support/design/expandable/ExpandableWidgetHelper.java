// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.expandable;

import android.view.View;

// Referenced classes of package android.support.design.expandable:
//            ExpandableWidget

public final class ExpandableWidgetHelper
{

    public boolean expanded;
    public int expandedComponentIdHint;
    public final View widget;

    public ExpandableWidgetHelper(ExpandableWidget expandablewidget)
    {
        expanded = false;
        expandedComponentIdHint = 0;
        widget = (View)expandablewidget;
    }
}
