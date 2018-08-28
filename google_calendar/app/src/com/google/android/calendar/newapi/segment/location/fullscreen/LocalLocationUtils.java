// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.location.fullscreen;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;

final class LocalLocationUtils
{

    private static final String CONTACTS_PROJECTION[] = {
        "display_name", "data1", "contact_id", "photo_id"
    };

    static Cursor queryContactsByNameOrAddress(Context context, CharSequence charsequence)
    {
        String s = String.valueOf(charsequence);
        s = (new StringBuilder(String.valueOf(s).length() + 1)).append(s).append("%").toString();
        charsequence = String.valueOf(charsequence);
        charsequence = (new StringBuilder(String.valueOf(charsequence).length() + 3)).append("% ").append(charsequence).append("%").toString();
        return context.getContentResolver().query(android.provider.ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI, CONTACTS_PROJECTION, "(data1 LIKE ? OR data1 LIKE ? OR display_name LIKE ? OR display_name LIKE ?)", new String[] {
            s, charsequence, s, charsequence
        }, "display_name ASC");
    }

}
