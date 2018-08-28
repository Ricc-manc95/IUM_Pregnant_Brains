// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee;

import android.content.Context;
import android.text.TextUtils;
import com.android.bitmap.drawable.BasicBitmapDrawable;
import com.google.android.calendar.avatar.ContactInfo;
import com.google.android.calendar.avatar.ContactPhotoRequestKey;
import com.google.android.calendar.common.drawable.DefaultableBitmapDrawable;
import com.google.android.calendar.event.image.cache.contactphoto.ContactPhotoCacheHolder;
import com.google.android.calendar.tiles.view.AvatarTileView;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;

public class ContactTileView extends AvatarTileView
{

    public ContactTileView(Context context)
    {
        super(context);
    }

    public final ContactTileView setData(ContactInfo contactinfo, CharSequence charsequence)
    {
        Object obj = null;
        ContactPhotoRequestKey contactphotorequestkey = new ContactPhotoRequestKey(getContext().getApplicationContext(), contactinfo);
        DefaultableBitmapDrawable defaultablebitmapdrawable = new DefaultableBitmapDrawable(getResources(), ContactPhotoCacheHolder.getContactPhotoCache(), false, null, 0x7f020132);
        int i = super.iconSize;
        defaultablebitmapdrawable.setDecodeDimensions(i, i);
        defaultablebitmapdrawable.setBounds(0, 0, i, i);
        defaultablebitmapdrawable.bind(contactphotorequestkey);
        if (TextUtils.isEmpty(contactinfo.name))
        {
            contactinfo = contactinfo.primaryEmail;
            if (TextUtils.isEmpty(contactinfo))
            {
                contactinfo = null;
            } else
            {
                contactinfo = contactinfo.toLowerCase();
            }
        } else
        {
            contactinfo = contactinfo.name;
        }
        if (TextUtils.equals(contactinfo, charsequence))
        {
            charsequence = obj;
        }
        setTextAdaptively(contactinfo, charsequence);
        setIconDrawable(defaultablebitmapdrawable);
        return this;
    }

    public final ContactTileView setData(String s, CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2)
    {
        com.google.android.calendar.avatar.ContactInfo.Builder builder = ContactInfo.newBuilder();
        builder.sourceAccountName = s;
        builder.name = charsequence1.toString();
        builder.primaryEmail = (String)charsequence;
        return setData(new ContactInfo(builder), charsequence2);
    }
}
