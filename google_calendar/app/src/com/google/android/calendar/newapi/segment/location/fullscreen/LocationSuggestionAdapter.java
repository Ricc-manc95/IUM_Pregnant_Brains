// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.location.fullscreen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable21;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.calendar.newapi.segment.common.fullscreen.HeadlineViewHolder;
import com.google.android.calendar.tiles.view.HeadlineTileView;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.newapi.segment.location.fullscreen:
//            OnLocationSelectedListener, SuggestionLocationViewHolder, ContactLocationViewHolder, ContactPhotoCache

final class LocationSuggestionAdapter extends android.support.v7.widget.RecyclerView.Adapter
    implements OnLocationSelectedListener
{

    private final Context context;
    public List items;
    public OnLocationSelectedListener listener;
    private final ContactPhotoCache photoCache;

    LocationSuggestionAdapter(Context context1, ContactPhotoCache contactphotocache)
    {
        items = new ArrayList();
        context = context1;
        photoCache = contactphotocache;
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
        return !(obj instanceof SuggestionLocationViewHolder.Suggestion) ? 2 : 1;
    }

    public final void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder viewholder, int i)
    {
        Object obj;
        obj = items.get(i);
        if (viewholder instanceof HeadlineViewHolder)
        {
            HeadlineViewHolder headlineviewholder = (HeadlineViewHolder)viewholder;
            CharSequence charsequence = (CharSequence)obj;
            headlineviewholder.view.text.setText(charsequence);
        }
        if (viewholder instanceof SuggestionLocationViewHolder)
        {
            SuggestionLocationViewHolder suggestionlocationviewholder = (SuggestionLocationViewHolder)viewholder;
            SuggestionLocationViewHolder.Suggestion suggestion = (SuggestionLocationViewHolder.Suggestion)obj;
            suggestionlocationviewholder.suggestion = suggestion;
            suggestionlocationviewholder.view.setTextAdaptively(suggestion.title, suggestion.address);
        }
        if (!(viewholder instanceof ContactLocationViewHolder)) goto _L2; else goto _L1
_L1:
        Object obj1;
        ContactPhotoCache contactphotocache;
        viewholder = (ContactLocationViewHolder)viewholder;
        obj1 = (ContactLocationViewHolder.Contact)obj;
        viewholder.contact = ((ContactLocationViewHolder.Contact) (obj1));
        obj = ((ContactLocationViewHolder) (viewholder)).view;
        ((TextTileView) (obj)).setTextAdaptively(((ContactLocationViewHolder.Contact) (obj1)).name, ((ContactLocationViewHolder.Contact) (obj1)).address);
        ((TileView) (obj)).setIconDrawable(null);
        contactphotocache = ((ContactLocationViewHolder) (viewholder)).photoCache;
        obj = ((ContactLocationViewHolder) (viewholder)).view.getIcon();
        Bitmap bitmap = (Bitmap)contactphotocache.photoCache.get(((ContactLocationViewHolder.Contact) (obj1)).contactPhoto);
        if (((ContactLocationViewHolder.Contact) (obj1)).isDuplicate)
        {
            viewholder = null;
        } else
        {
            viewholder = ContextCompat.getDrawable(contactphotocache.context, 0x7f020132);
        }
        if (bitmap == null) goto _L4; else goto _L3
_L3:
        viewholder = new RoundedBitmapDrawable21(contactphotocache.context.getResources(), bitmap);
        ((RoundedBitmapDrawable) (viewholder)).mPaint.setAntiAlias(true);
        viewholder.invalidateSelf();
        viewholder.mIsCircular = true;
        viewholder.mApplyGravity = true;
        viewholder.mCornerRadius = Math.min(((RoundedBitmapDrawable) (viewholder)).mBitmapHeight, ((RoundedBitmapDrawable) (viewholder)).mBitmapWidth) / 2;
        ((RoundedBitmapDrawable) (viewholder)).mPaint.setShader(((RoundedBitmapDrawable) (viewholder)).mBitmapShader);
        viewholder.invalidateSelf();
_L6:
        ((ImageView) (obj)).setImageDrawable(viewholder);
_L2:
        return;
_L4:
        if (((ContactLocationViewHolder.Contact) (obj1)).contactPhoto != null)
        {
            android.content.ContentResolver contentresolver = contactphotocache.context.getContentResolver();
            obj1 = ((ContactLocationViewHolder.Contact) (obj1)).contactPhoto;
            ((ImageView) (obj)).setTag(obj1);
            (new ContactPhotoCache._cls1(contactphotocache, contentresolver, ((android.net.Uri) (obj1)), ((ImageView) (obj)))).execute(new Void[0]);
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final void onContactSelected(ContactLocationViewHolder.Contact contact)
    {
        if (listener != null)
        {
            listener.onContactSelected(contact);
        }
    }

    public final android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewgroup, int i)
    {
        viewgroup = context;
        ContactPhotoCache contactphotocache = photoCache;
        switch (i)
        {
        default:
            throw new IllegalStateException("Unknown ViewType.");

        case 2: // '\002'
            return ContactLocationViewHolder.create(viewgroup, contactphotocache, this);

        case 0: // '\0'
            return HeadlineViewHolder.create(viewgroup);

        case 1: // '\001'
            return SuggestionLocationViewHolder.create(viewgroup, this);
        }
    }

    public final void onSuggestionSelected(SuggestionLocationViewHolder.Suggestion suggestion)
    {
        if (listener != null)
        {
            listener.onSuggestionSelected(suggestion);
        }
    }
}
