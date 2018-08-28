// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.location.fullscreen;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.segment.location.fullscreen:
//            LocalLocationUtils

final class LocalLocationSuggestionFetcher
{

    public final Context context;

    LocalLocationSuggestionFetcher(Context context1)
    {
        context = context1;
    }

    static List fetchContacts(Context context1, CharSequence charsequence)
    {
        if (!TextUtils.isEmpty(charsequence) && AndroidPermissionUtils.hasContactsPermissions(context1)) goto _L2; else goto _L1
_L1:
        context1 = Collections.emptyList();
_L7:
        return context1;
_L2:
        Cursor cursor = LocalLocationUtils.queryContactsByNameOrAddress(context1, charsequence);
        HashSet hashset;
        charsequence = new ArrayList();
        hashset = new HashSet();
_L4:
        if (hashset.size() >= 3 || cursor == null)
        {
            break MISSING_BLOCK_LABEL_168;
        }
        String s;
        String s1;
        if (!cursor.moveToNext())
        {
            break MISSING_BLOCK_LABEL_168;
        }
        s = cursor.getString(0);
        s1 = cursor.getString(1);
        if (TextUtils.isEmpty(s)) goto _L4; else goto _L3
_L3:
        if (cursor.getLong(3) <= 0L)
        {
            break MISSING_BLOCK_LABEL_163;
        }
        context1 = ContentUris.withAppendedId(android.provider.ContactsContract.Contacts.CONTENT_URI, cursor.getLong(2));
_L5:
        charsequence.add(new ContactLocationViewHolder.Contact(s, s1, context1, hashset.contains(s)));
        hashset.add(s);
          goto _L4
        context1;
        if (cursor != null)
        {
            cursor.close();
        }
        throw context1;
        context1 = null;
          goto _L5
        context1 = charsequence;
        if (cursor != null)
        {
            cursor.close();
            return charsequence;
        }
        if (true) goto _L7; else goto _L6
_L6:
    }
}
