// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attachment;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.libraries.view.horizontalcarousel.HorizontalCarousel;
import com.google.android.libraries.view.horizontalcarousel.HorizontalCarouselAdapter;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.segment.attachment:
//            AttachmentCarouselAdapter

public class AttachmentTileView extends TileView
{

    public AttachmentCarouselAdapter adapter;
    public HorizontalCarousel carousel;
    private int contentHeight;
    public int iconVisibilityThreshold;

    public AttachmentTileView(Context context)
    {
        super(context);
    }

    public AttachmentTileView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected final int adjustTileHeight(int i)
    {
        return contentHeight;
    }

    protected final View createContentView(LayoutInflater layoutinflater)
    {
        return layoutinflater.inflate(0x7f0500ec, this, false);
    }

    protected final int getContentEndPadding()
    {
        return 0;
    }

    protected final int getContentStartPadding()
    {
        return 0;
    }

    public final TileView indentContent()
    {
        return this;
    }

    protected final void onContentViewSet(View view)
    {
        super.onContentViewSet(view);
        setIconDrawable(0x7f0201e6);
        int i = getResources().getDimensionPixelOffset(0x7f0e0062);
        setPaddingRelative(getPaddingStart(), i, getPaddingEnd(), i);
        i = getResources().getDimensionPixelOffset(0x7f0e0354);
        int j = getResources().getDimensionPixelOffset(0x7f0e0060);
        iconVisibilityThreshold = (int)((float)i + (float)j / 2.0F);
        contentHeight = getResources().getDimensionPixelOffset(0x7f0e005b);
        carousel = (HorizontalCarousel)findViewById(0x7f10029d);
        carousel.setOnScrollListener(new _cls1());
        adapter = new AttachmentCarouselAdapter(getContext());
        carousel.setAdapter(adapter);
    }

    public final void setAttachments(List list)
    {
        AttachmentCarouselAdapter attachmentcarouseladapter = adapter;
        if (list != null)
        {
            attachmentcarouseladapter.items.clear();
            attachmentcarouseladapter.items.addAll(list);
            ((android.support.v7.widget.RecyclerView.Adapter) (attachmentcarouseladapter)).mObservable.notifyChanged();
            if (((HorizontalCarouselAdapter) (attachmentcarouseladapter)).horizontalCarousel != null)
            {
                ((HorizontalCarouselAdapter) (attachmentcarouseladapter)).horizontalCarousel.scrollToPosition(0);
            }
        }
        ((android.support.v7.widget.RecyclerView.Adapter) (adapter)).mObservable.notifyChanged();
    }

    private class _cls1 extends android.support.v7.widget.RecyclerView.OnScrollListener
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

        _cls1()
        {
            this$0 = AttachmentTileView.this;
            super();
        }
    }

}
