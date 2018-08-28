// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attachment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.bitmap.drawable.BasicBitmapDrawable;
import com.android.bitmap.drawable.StyledCornersBitmapDrawable;
import com.google.android.calendar.api.event.attachment.Attachment;
import com.google.android.calendar.common.drawable.CalendarStyledCornersBitmapDrawable;
import com.google.android.calendar.event.image.BitmapCacheHolder;
import com.google.android.calendar.timely.LargeThreadpoolBitmapDrawable;
import com.google.android.calendar.timely.UrlRequestKey;
import com.google.android.calendar.viewedit.segment.attachment.AttachmentHelper;
import com.google.android.libraries.view.horizontalcarousel.HorizontalCarouselAdapter;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.segment.attachment:
//            AttachmentView

public final class AttachmentCarouselAdapter extends HorizontalCarouselAdapter
{

    private final Context context;
    public final List items = new ArrayList();

    public AttachmentCarouselAdapter(Context context1)
    {
        context = context1;
    }

    public final int getItemCount()
    {
        return items.size();
    }

    public final Object getItemForPosition(int i)
    {
        return (Attachment)items.get(i);
    }

    public final int getItemViewType(int i)
    {
        return 1;
    }

    public final android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewgroup, int i)
    {
        return new com.google.android.libraries.view.horizontalcarousel.HorizontalCarousel.ViewHolder(LayoutInflater.from(context).inflate(0x7f0500be, viewgroup, false));
    }

    protected final void onInternalBindViewHolder(com.google.android.libraries.view.horizontalcarousel.HorizontalCarousel.ViewHolder viewholder, int i)
    {
        boolean flag = false;
        Object obj1 = (Attachment)getItemForPosition(i);
        if (viewholder.itemView != null && (viewholder.itemView instanceof AttachmentView))
        {
            viewholder = (AttachmentView)viewholder.itemView;
            if (obj1 != null)
            {
                Object obj = AttachmentHelper.inferMimeType(((Attachment) (obj1)).iconLink);
                obj1 = ((Attachment) (obj1)).title;
                ((AttachmentView) (viewholder)).fileNameView.setText(((CharSequence) (obj1)));
                obj1 = AttachmentHelper.getTypeDescription(viewholder.getContext(), ((String) (obj)));
                Object obj2 = AttachmentHelper.getTypeIconUrl(((String) (obj)));
                ((AttachmentView) (viewholder)).fileTypeView.setText(((CharSequence) (obj1)));
                if (obj2 != null)
                {
                    obj1 = ((AttachmentView) (viewholder)).fileTypeView;
                    viewholder.getContext();
                    obj2 = UrlRequestKey.fromUrlString$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D66KOBMC4NMOOBECSNL6T3ID5N6EEP99HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKD5MMAR3P5TAN4R2ICLONAPBJEH5MAU9R0(((String) (obj2)));
                    LargeThreadpoolBitmapDrawable largethreadpoolbitmapdrawable = new LargeThreadpoolBitmapDrawable(viewholder.getResources(), ((AttachmentView) (viewholder)).iconCache, false, null);
                    largethreadpoolbitmapdrawable.setDecodeDimensions(((AttachmentView) (viewholder)).iconWidth, ((AttachmentView) (viewholder)).iconHeight);
                    largethreadpoolbitmapdrawable.setBounds(new Rect(0, 0, ((AttachmentView) (viewholder)).iconWidth, ((AttachmentView) (viewholder)).iconHeight));
                    largethreadpoolbitmapdrawable.bind(((com.android.bitmap.RequestKey) (obj2)));
                    ((TextView) (obj1)).setCompoundDrawablesRelative(largethreadpoolbitmapdrawable, null, null, null);
                }
                int j = AttachmentHelper.getTypeColor(viewholder.getContext(), ((String) (obj)));
                obj = ((AttachmentView) (viewholder)).background;
                obj1 = new com.android.bitmap.drawable.ExtendedBitmapDrawable.ExtendedOptions(4);
                obj1.backgroundColor = viewholder.getResources().getColor(0x7f0d000a);
                obj1.placeholderAnimationDuration = -1;
                obj1 = new CalendarStyledCornersBitmapDrawable(viewholder.getResources(), BitmapCacheHolder.getAttachmentChipCache(), false, ((com.android.bitmap.drawable.ExtendedBitmapDrawable.ExtendedOptions) (obj1)), viewholder.getResources().getDimension(0x7f0e0059), viewholder.getResources().getDimension(0x7f0e0058));
                ((BasicBitmapDrawable) (obj1)).setDecodeDimensions(viewholder.getResources().getDimensionPixelSize(0x7f0e0060), viewholder.getResources().getDimensionPixelSize(0x7f0e005b));
                obj1.topStartCornerStyle = 1;
                obj1.topEndCornerStyle = 1;
                obj1.bottomEndCornerStyle = 2;
                obj1.bottomStartCornerStyle = 1;
                ((StyledCornersBitmapDrawable) (obj1)).resolveCornerStyles();
                i = ((flag) ? 1 : 0);
                if (((StyledCornersBitmapDrawable) (obj1)).flapPaint.getColor() != j)
                {
                    i = 1;
                }
                ((StyledCornersBitmapDrawable) (obj1)).flapPaint.setColor(j);
                if (i != 0)
                {
                    ((StyledCornersBitmapDrawable) (obj1)).invalidateSelf();
                }
                ((ImageView) (obj)).setImageDrawable(((android.graphics.drawable.Drawable) (obj1)));
                viewholder.setContentDescription(null);
            }
        }
    }
}
