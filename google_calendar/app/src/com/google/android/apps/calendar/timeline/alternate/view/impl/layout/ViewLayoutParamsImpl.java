// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout;

import com.google.android.apps.calendar.timeline.alternate.view.api.ViewMode;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.ViewLayoutParams;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout:
//            LayoutItemParams

final class ViewLayoutParamsImpl
    implements ViewLayoutParams
{

    public LayoutItemParams layoutItemParams;

    ViewLayoutParamsImpl()
    {
        layoutItemParams = new LayoutItemParams();
    }

    public final float getTextScale()
    {
        return layoutItemParams.textScale;
    }

    public final ViewMode getViewMode()
    {
        return layoutItemParams.viewMode;
    }

    public final int getZOrder()
    {
        return layoutItemParams.zOrder;
    }

    public final boolean hasTextScale()
    {
        return layoutItemParams.hasTextScale;
    }

    public final boolean hasZOrder()
    {
        return layoutItemParams.hasZOrder;
    }
}
