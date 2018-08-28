// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime.net;

import com.google.common.collect.ImmutableList;

public final class responseId
{

    public final int acceptableSuggestions;
    public final ImmutableList consideredAttendees;
    public final ImmutableList omittedAttendees;
    public final String responseId;
    public final ImmutableList suggestions;

    public (ImmutableList immutablelist, ImmutableList immutablelist1, ImmutableList immutablelist2, int i, String s)
    {
        suggestions = immutablelist;
        acceptableSuggestions = i;
        consideredAttendees = immutablelist1;
        omittedAttendees = immutablelist2;
        responseId = s;
    }
}
