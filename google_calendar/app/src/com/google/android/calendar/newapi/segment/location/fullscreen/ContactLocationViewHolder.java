// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.location.fullscreen;

import android.content.Context;
import com.google.android.calendar.tiles.view.AvatarTileView;
import com.google.android.calendar.tiles.view.TileView;

// Referenced classes of package com.google.android.calendar.newapi.segment.location.fullscreen:
//            OnLocationSelectedListener, ContactPhotoCache

final class ContactLocationViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder
{

    public Contact contact;
    private final OnLocationSelectedListener listener;
    public final ContactPhotoCache photoCache;
    public final AvatarTileView view;

    private ContactLocationViewHolder(AvatarTileView avatartileview, ContactPhotoCache contactphotocache, OnLocationSelectedListener onlocationselectedlistener)
    {
        super(avatartileview);
        view = avatartileview;
        photoCache = contactphotocache;
        listener = onlocationselectedlistener;
    }

    public static ContactLocationViewHolder create(Context context, ContactPhotoCache contactphotocache, OnLocationSelectedListener onlocationselectedlistener)
    {
        context = new AvatarTileView(context);
        context.indentContent();
        contactphotocache = new ContactLocationViewHolder(context, contactphotocache, onlocationselectedlistener);
        class .Lambda._cls0
            implements android.view.View.OnClickListener
        {

            private final ContactLocationViewHolder arg$1;

            public final void onClick(View view1)
            {
                ContactLocationViewHolder.lambda$create$0$ContactLocationViewHolder$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFDPINEOBGD4NN6PB7DLIMST1FDHNM6OBKD5NMSBR6ELM6OSR3E9IMARHF8DNMST31CDQ4ORR3C5Q6IRREAPKMATQ8DTM68PBI7D662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0(arg$1);
            }

            .Lambda._cls0()
            {
                arg$1 = ContactLocationViewHolder.this;
            }
        }

        context.treatAsButton(true).setOnClickListener(contactphotocache. new .Lambda._cls0());
        return contactphotocache;
    }

    static final void lambda$create$0$ContactLocationViewHolder$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFDPINEOBGD4NN6PB7DLIMST1FDHNM6OBKD5NMSBR6ELM6OSR3E9IMARHF8DNMST31CDQ4ORR3C5Q6IRREAPKMATQ8DTM68PBI7D662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0(ContactLocationViewHolder contactlocationviewholder)
    {
        contactlocationviewholder.listener.onContactSelected(contactlocationviewholder.contact);
    }
}
