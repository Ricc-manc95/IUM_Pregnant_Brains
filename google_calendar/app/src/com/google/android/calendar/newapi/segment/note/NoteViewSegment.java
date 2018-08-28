// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.note;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.userstatus.UserStatus;
import com.google.android.calendar.event.ConferenceCallUtils;
import com.google.android.calendar.event.ConferenceCallView;
import com.google.android.calendar.newapi.model.ConferenceEvent;
import com.google.android.calendar.newapi.model.EventHolder;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.newapi.segment.note:
//            ConferenceTileView, NoteHtmlConverter

public final class NoteViewSegment extends ConferenceTileView
    implements com.google.android.calendar.event.ConferenceCallSpan.OnConferenceNumberClickListener, ViewSegment
{

    private Set accessCodes;
    private final FragmentManager fragmentManager;
    private final EventHolder model;

    public NoteViewSegment(Context context, FragmentManager fragmentmanager, EventHolder eventholder)
    {
        super(context);
        model = eventholder;
        fragmentManager = fragmentmanager;
    }

    protected final String getAnalyticsSegmentDescription()
    {
        return "in_segment_description";
    }

    public final void onClick(String s)
    {
        ConferenceCallUtils.logAction(getContext(), "conference_link_tapped", "in_segment_description");
        ConferenceCallUtils.dialConferenceCall(getContext(), fragmentManager, s, accessCodes);
    }

    protected final void onContentViewSet(View view)
    {
        super.onContentViewSet(view);
        indentContent();
        super.denseMode = false;
        setIconDrawable(0x7f020217);
        setContentDescription(getResources().getString(0x7f130179));
        ((ConferenceCallView)super.primaryLine).listener = this;
        setFocusable(true);
        super.primaryLine.setTextIsSelectable(true);
    }

    public final void updateUi()
    {
        accessCodes = ((ConferenceEvent)model).getConferenceAccessTokens();
        String s = model.getEvent().getDescription();
        Object obj = s;
        if (NoteHtmlConverter.isHtml(s))
        {
            obj = NoteHtmlConverter.toFormattedText(s);
        }
        int i;
        if (!TextUtils.isEmpty(((CharSequence) (obj))) && (model.getEvent().getParticipantStatus() == null || model.getEvent().getParticipantStatus().getOutOfOffice() == null))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (this != null)
        {
            if (i != 0)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            setVisibility(i);
        }
        obj = (ConferenceTileView)setPrimaryText(new CharSequence[] {
            obj
        });
    }
}
