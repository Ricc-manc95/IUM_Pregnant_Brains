// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee.fullscreen;

import com.android.ex.chips.BaseRecipientAdapter;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;

final class AttendeeSuggestionFetcher
{

    public final BaseRecipientAdapter contactFetcher;
    public boolean hasContactsPermissions;
    public Listener listener;

    AttendeeSuggestionFetcher(BaseRecipientAdapter baserecipientadapter)
    {
        hasContactsPermissions = AndroidPermissionUtils.hasContactsPermissions(baserecipientadapter.context);
        contactFetcher = baserecipientadapter;
    }
}
