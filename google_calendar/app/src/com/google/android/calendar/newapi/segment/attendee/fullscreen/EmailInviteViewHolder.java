// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee.fullscreen;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;
import com.google.android.calendar.avatar.ContactInfo;
import com.google.android.calendar.newapi.segment.attendee.ContactTileView;
import com.google.android.calendar.tiles.view.TileView;

// Referenced classes of package com.google.android.calendar.newapi.segment.attendee.fullscreen:
//            AttendeeContact

final class EmailInviteViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder
{

    public AttendeeContact attendee;
    public final AttendeeViewHolder.Listener listener;
    public final ContactTileView view;

    private EmailInviteViewHolder(ContactTileView contacttileview, AttendeeViewHolder.Listener listener1)
    {
        super(contacttileview);
        view = contacttileview;
        listener = listener1;
    }

    public static EmailInviteViewHolder create(Context context, AttendeeViewHolder.Listener listener1)
    {
        context = new ContactTileView(context);
        listener1 = new EmailInviteViewHolder(context, listener1);
        context = context.treatAsButton(true);
        listener1.getClass();
        class .Lambda._cls0
            implements android.view.View.OnClickListener
        {

            private final EmailInviteViewHolder arg$1;

            public final void onClick(View view1)
            {
                EmailInviteViewHolder emailinviteviewholder = arg$1;
                AttendeeViewHolder.Listener listener2 = emailinviteviewholder.listener;
                AttendeeContact attendeecontact = emailinviteviewholder.attendee;
                com.google.android.calendar.avatar.ContactInfo.Builder builder = ContactInfo.newBuilder(attendeecontact.getContact());
                builder.lookupKey = null;
                listener2.onContactSelected(new AutoValue_AttendeeContact(new ContactInfo(builder), attendeecontact.getType()));
                view1.announceForAccessibility(view1.getResources().getString(0x7f130000, new Object[] {
                    emailinviteviewholder.attendee.getContact().primaryEmail
                }));
            }

            .Lambda._cls0()
            {
                arg$1 = EmailInviteViewHolder.this;
            }
        }

        context.setOnClickListener(listener1. new .Lambda._cls0());
        return listener1;
    }

    public static ContactInfo createEmailInvite(Account account, CharSequence charsequence)
    {
        com.google.android.calendar.avatar.ContactInfo.Builder builder = ContactInfo.newBuilder();
        builder.sourceAccountName = account.name;
        builder.lookupKey = "EMAIL_INVITE_LOOKUP_KEY";
        if (charsequence == null)
        {
            account = "";
        } else
        {
            account = charsequence.toString();
        }
        builder.primaryEmail = account;
        return new ContactInfo(builder);
    }

    public static boolean isEmailInvite(AttendeeContact attendeecontact)
    {
        return "EMAIL_INVITE_LOOKUP_KEY".equals(attendeecontact.getContact().lookupKey) && !TextUtils.isEmpty(attendeecontact.getContact().primaryEmail);
    }
}
