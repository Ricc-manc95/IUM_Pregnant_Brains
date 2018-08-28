// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.view.horizontalcarousel;

import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

// Referenced classes of package com.google.android.libraries.view.horizontalcarousel:
//            HorizontalCarousel

final class this._cls0 extends android.support.v7.widget.ration
{

    private final HorizontalCarousel this$0;

    public final void getItemOffsets(Rect rect, View view, RecyclerView recyclerview, android.support.v7.widget.sel._cls1 _pcls1)
    {
        int j = HorizontalCarousel.getChildAdapterPosition(view);
        boolean flag;
        int i;
        if (ViewCompat.getLayoutDirection(HorizontalCarousel.this) == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (_pcls1.nPreLayout)
        {
            i = _pcls1.reviousLayoutItemCount - _pcls1.eletedInvisibleItemCountSincePreviousLayout;
        } else
        {
            i = _pcls1.temCount;
        }
        if (flag)
        {
            if (j == 0 && i == 1)
            {
                rect.set(spaceStart, 0, spaceEnd, 0);
                return;
            }
            if (j == 0)
            {
                rect.set(spaceStart, 0, spaceBetween, 0);
                return;
            }
            if (j == i - 1)
            {
                rect.set(0, 0, spaceEnd, 0);
                return;
            } else
            {
                rect.set(0, 0, spaceBetween, 0);
                return;
            }
        }
        if (j == 0 && i == 1)
        {
            rect.set(spaceEnd, 0, spaceStart, 0);
            return;
        }
        if (j == 0)
        {
            rect.set(spaceBetween, 0, spaceStart, 0);
            return;
        }
        if (j == i - 1)
        {
            rect.set(spaceEnd, 0, 0, 0);
            return;
        } else
        {
            rect.set(spaceBetween, 0, 0, 0);
            return;
        }
    }

    ()
    {
        this$0 = HorizontalCarousel.this;
        super();
    }
}
