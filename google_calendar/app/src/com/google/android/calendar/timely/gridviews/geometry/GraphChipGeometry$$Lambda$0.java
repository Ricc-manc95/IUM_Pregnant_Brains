// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.geometry;

import android.view.View;
import com.google.android.calendar.utils.Holder;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.timely.gridviews.geometry:
//            PositionOnGrid

final class 
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        return (PositionOnGrid)((Holder)((View)(View)obj).getLayoutParams()).get();
    }


    private ()
    {
    }
}
