// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.location.fullscreen;

import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.newapi.segment.location.fullscreen:
//            LocalLocationSuggestionFetcher

final class arg._cls2
    implements Callable
{

    private final LocalLocationSuggestionFetcher arg$1;
    private final CharSequence arg$2;

    public final Object call()
    {
        LocalLocationSuggestionFetcher locallocationsuggestionfetcher = arg$1;
        CharSequence charsequence = arg$2;
        return new stionList(LocalLocationSuggestionFetcher.fetchContacts(locallocationsuggestionfetcher.context, charsequence));
    }

    stionList(LocalLocationSuggestionFetcher locallocationsuggestionfetcher, CharSequence charsequence)
    {
        arg$1 = locallocationsuggestionfetcher;
        arg$2 = charsequence;
    }
}
