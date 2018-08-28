// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attachment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.utils.rtl.RtlUtils;
import com.google.android.libraries.view.horizontalcarousel.HorizontalCarousel;

// Referenced classes of package com.google.android.calendar.newapi.segment.attachment:
//            AttachmentTileView

final class this._cls0 extends android.support.v7.widget.Listener
{

    private final AttachmentTileView this$0;

    public final void onScrolled$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D4KIAAM0(RecyclerView recyclerview, int i)
    {
        i = 1;
        boolean flag = false;
        recyclerview = AttachmentTileView.this;
        if (recyclerview.getIcon() != null)
        {
            int j = ((AttachmentTileView) (recyclerview)).carousel.computeHorizontalScrollRange();
            int k = ((AttachmentTileView) (recyclerview)).carousel.getWidth();
            if (RtlUtils.isLayoutDirectionRtl(recyclerview.getContext()))
            {
                if (((AttachmentTileView) (recyclerview)).carousel.computeHorizontalScrollOffset() <= j - k - ((AttachmentTileView) (recyclerview)).iconVisibilityThreshold)
                {
                    i = 0;
                }
            } else
            if (((AttachmentTileView) (recyclerview)).carousel.computeHorizontalScrollOffset() > ((AttachmentTileView) (recyclerview)).iconVisibilityThreshold)
            {
                i = 0;
            }
            recyclerview = recyclerview.getIcon();
            if (recyclerview != null)
            {
                if (i != 0)
                {
                    i = ((flag) ? 1 : 0);
                } else
                {
                    i = 8;
                }
                recyclerview.setVisibility(i);
            }
        }
    }

    ()
    {
        this$0 = AttachmentTileView.this;
        super();
    }
}
