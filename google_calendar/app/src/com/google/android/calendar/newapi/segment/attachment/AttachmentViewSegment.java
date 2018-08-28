// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attachment;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.attachment.Attachment;
import com.google.android.calendar.newapi.model.EventHolder;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.libraries.view.horizontalcarousel.HorizontalCarouselAdapter;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.newapi.segment.attachment:
//            AttachmentTileView, AttachmentUtils

public final class AttachmentViewSegment extends AttachmentTileView
    implements ViewSegment, com.google.android.libraries.view.horizontalcarousel.HorizontalCarouselAdapter.OnCarouselTileClickListener
{

    private final EventHolder model;

    public AttachmentViewSegment(Context context, EventHolder eventholder)
    {
        super(context);
        model = eventholder;
        super.denseMode = false;
        super.adapter.onCarouselTileClickListener = this;
        setContentDescription(getResources().getString(0x7f130165));
        setFocusable(true);
    }

    public final void onCarouselTileClick(View view, Object obj)
    {
        obj = (Attachment)obj;
        view = model.getEvent().getCalendar().account.name;
        obj = ((Attachment) (obj)).fileUrl;
        AttachmentUtils.openAttachment(getContext(), ((String) (obj)), view);
    }

    public final void updateUi()
    {
        if (model.getEvent().getAttachments().isEmpty())
        {
            setVisibility(8);
            return;
        } else
        {
            setVisibility(0);
            setAttachments(model.getEvent().getAttachments());
            int i = model.getEvent().getAttachments().size();
            setContentDescription(getResources().getQuantityString(0x7f120011, i, new Object[] {
                Integer.valueOf(i)
            }));
            return;
        }
    }
}
