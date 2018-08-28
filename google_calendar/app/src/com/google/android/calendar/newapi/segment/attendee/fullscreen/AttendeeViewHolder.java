// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee.fullscreen;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.google.android.calendar.avatar.ContactInfo;
import com.google.android.calendar.newapi.segment.attendee.ContactTileView;
import com.google.android.calendar.tiles.view.TileView;

// Referenced classes of package com.google.android.calendar.newapi.segment.attendee.fullscreen:
//            AttendeeContact

final class AttendeeViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder
{

    public AttendeeContact attendee;
    private final Listener listener;
    public final ContactTileView view;

    private AttendeeViewHolder(ContactTileView contacttileview, Listener listener1)
    {
        super(contacttileview);
        view = contacttileview;
        listener = listener1;
    }

    public static AttendeeViewHolder create(Context context, Listener listener1)
    {
        ContactTileView contacttileview = new ContactTileView(context);
        listener1 = new AttendeeViewHolder(contacttileview, listener1);
        View view1 = LayoutInflater.from(context).inflate(0x7f05013f, null);
        view1.setContentDescription(context.getString(0x7f13000d));
        ((ImageView)view1.findViewById(0x7f1000d4)).setImageResource(0x7f0201d8);
        class .Lambda._cls0
            implements android.view.View.OnClickListener
        {

            private final AttendeeViewHolder arg$1;
            private final ContactTileView arg$2;

            public final void onClick(View view2)
            {
                AttendeeViewHolder.lambda$create$0$AttendeeViewHolder$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFDPINEOBGD4NN6PB7DLIMST1FC5Q78PBECHIMABR6ELM6OSR3E9IMARHF85Q78PBECHIMALJ9CLRKGRRCCHIN4EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2URJ5ETGN0Q9FEDIMERB5DPQ2UOBKEHIMSP35CKNK6RREEHGM6T2KD5M6ALJ9CLRJMJ31DPI74RR9CGNNCQB5ESNLCQB5ESTIILG_0(arg$1, arg$2);
            }

            .Lambda._cls0(ContactTileView contacttileview)
            {
                arg$1 = AttendeeViewHolder.this;
                arg$2 = contacttileview;
            }
        }

        view1.setOnClickListener(listener1. new .Lambda._cls0(contacttileview));
        contacttileview.rightActionView = view1;
        contacttileview.addView(view1);
        if (((TileView) (contacttileview)).rightActionView == null || contacttileview.hasOnClickListeners())
        {
            return listener1;
        } else
        {
            contacttileview.treatAsButton(true);
            contacttileview.setOnClickListener(new com.google.android.calendar.tiles.view.TileView..Lambda._cls1(contacttileview));
            return listener1;
        }
    }

    static final void lambda$create$0$AttendeeViewHolder$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFDPINEOBGD4NN6PB7DLIMST1FC5Q78PBECHIMABR6ELM6OSR3E9IMARHF85Q78PBECHIMALJ9CLRKGRRCCHIN4EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2URJ5ETGN0Q9FEDIMERB5DPQ2UOBKEHIMSP35CKNK6RREEHGM6T2KD5M6ALJ9CLRJMJ31DPI74RR9CGNNCQB5ESNLCQB5ESTIILG_0(AttendeeViewHolder attendeeviewholder, ContactTileView contacttileview)
    {
        attendeeviewholder.onAttendeeClicked(contacttileview);
    }

    final void onAttendeeClicked(ContactTileView contacttileview)
    {
        listener.onContactSelected(attendee);
        String s;
        Resources resources;
        ContactInfo contactinfo;
        int i;
        if (attendee.getType() == AttendeeContact.Type.ADDED_REMOVABLE)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            i = 0x7f13000e;
        } else
        {
            i = 0x7f130000;
        }
        resources = contacttileview.getResources();
        contactinfo = attendee.getContact();
        s = contactinfo.name;
        if (TextUtils.isEmpty(s))
        {
            s = contactinfo.primaryEmail;
        }
        contacttileview.announceForAccessibility(resources.getString(i, new Object[] {
            s
        }));
    }

    private class Listener
    {

        public abstract void onContactSelected(AttendeeContact attendeecontact);
    }

}
