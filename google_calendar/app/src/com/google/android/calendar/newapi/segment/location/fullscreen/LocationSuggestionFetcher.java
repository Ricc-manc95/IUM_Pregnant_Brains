// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.location.fullscreen;

import android.content.Context;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.segment.location.fullscreen:
//            LocalLocationSuggestionFetcher, RemoteLocationSuggestionFetcher

final class LocationSuggestionFetcher
{

    private final Context context;
    public Listener listener;
    public final LocalLocationSuggestionFetcher localSuggestionFetcher;
    public final RemoteLocationSuggestionFetcher remoteSuggestionFetcher;
    public ListenableFuture resultSetFuture;

    LocationSuggestionFetcher(Context context1, LocalLocationSuggestionFetcher locallocationsuggestionfetcher, RemoteLocationSuggestionFetcher remotelocationsuggestionfetcher)
    {
        context = context1;
        localSuggestionFetcher = locallocationsuggestionfetcher;
        remoteSuggestionFetcher = remotelocationsuggestionfetcher;
    }

    final List buildSection(int i, List list)
    {
        if (list.isEmpty())
        {
            return Collections.emptyList();
        } else
        {
            ArrayList arraylist = new ArrayList();
            arraylist.add(context.getString(i));
            arraylist.addAll(list);
            return arraylist;
        }
    }
}
