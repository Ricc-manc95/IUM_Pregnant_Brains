// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee.fullscreen;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.calendar.avatar.ContactInfo;
import com.google.android.calendar.newapi.segment.attendee.ContactTileView;
import com.google.android.calendar.newapi.segment.common.fullscreen.HeadlineViewHolder;
import com.google.android.calendar.tiles.view.HeadlineTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.common.base.Platform;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.segment.attendee.fullscreen:
//            AttendeeContact, EmailInviteViewHolder, AttendeeViewHolder

final class AttendeeSuggestionAdapter extends android.support.v7.widget.RecyclerView.Adapter
    implements AttendeeViewHolder.Listener
{

    private final Context context;
    public List items;
    public AttendeeViewHolder.Listener listener;

    AttendeeSuggestionAdapter(Context context1)
    {
        items = new ArrayList();
        context = context1;
    }

    public final int getItemCount()
    {
        return items.size();
    }

    public final int getItemViewType(int i)
    {
        Object obj = items.get(i);
        if (obj instanceof CharSequence)
        {
            return 0;
        }
        return !EmailInviteViewHolder.isEmailInvite((AttendeeContact)obj) ? 1 : 2;
    }

    public final void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder viewholder, int i)
    {
        boolean flag1 = true;
        boolean flag = false;
        Object obj = items.get(i);
        if (viewholder instanceof AttendeeViewHolder)
        {
            viewholder = (AttendeeViewHolder)viewholder;
            obj = (AttendeeContact)obj;
            viewholder.attendee = ((AttendeeContact) (obj));
            ((AttendeeViewHolder) (viewholder)).view.setData(((AttendeeContact) (obj)).getContact(), Platform.emptyToNull(((AttendeeContact) (obj)).getContact().primaryEmail));
            boolean flag2;
            if (((AttendeeContact) (obj)).getType() == AttendeeContact.Type.SUGGESTION)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            if (((AttendeeContact) (obj)).getType() == AttendeeContact.Type.ADDED_REMOVABLE)
            {
                i = ((flag1) ? 1 : 0);
            } else
            {
                i = 0;
            }
            obj = ((TileView) (((AttendeeViewHolder) (viewholder)).view)).rightActionView;
            if (obj != null)
            {
                if (i != 0)
                {
                    i = ((flag) ? 1 : 0);
                } else
                {
                    i = 8;
                }
                ((View) (obj)).setVisibility(i);
            }
            obj = ((AttendeeViewHolder) (viewholder)).view.treatAsButton(flag2);
            if (flag2)
            {
                viewholder = new AttendeeViewHolder..Lambda._cls1(viewholder);
            } else
            {
                viewholder = null;
            }
            ((TileView) (obj)).setOnClickListener(viewholder);
        } else
        {
            if (viewholder instanceof HeadlineViewHolder)
            {
                viewholder = (HeadlineViewHolder)viewholder;
                obj = (CharSequence)obj;
                ((HeadlineViewHolder) (viewholder)).view.text.setText(((CharSequence) (obj)));
                return;
            }
            if (viewholder instanceof EmailInviteViewHolder)
            {
                viewholder = (EmailInviteViewHolder)viewholder;
                viewholder.attendee = (AttendeeContact)obj;
                ((EmailInviteViewHolder) (viewholder)).view.setData(((EmailInviteViewHolder) (viewholder)).attendee.getContact(), null);
                return;
            }
        }
    }

    public final void onContactSelected(AttendeeContact attendeecontact)
    {
        if (listener != null)
        {
            listener.onContactSelected(attendeecontact);
        }
    }

    public final android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewgroup, int i)
    {
        viewgroup = context;
        switch (i)
        {
        default:
            throw new IllegalStateException("Unknown ViewType.");

        case 1: // '\001'
            return AttendeeViewHolder.create(viewgroup, this);

        case 0: // '\0'
            return HeadlineViewHolder.create(viewgroup);

        case 2: // '\002'
            return EmailInviteViewHolder.create(viewgroup, this);
        }
    }
}
