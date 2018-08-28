// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.location.fullscreen;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;

// Referenced classes of package com.google.android.calendar.newapi.segment.location.fullscreen:
//            LocationSuggestionFetcher

final class arg._cls1
    implements Function
{

    private final LocationSuggestionFetcher arg$1;

    public final Object apply(Object obj)
    {
        LocationSuggestionFetcher locationsuggestionfetcher = arg$1;
        obj = (SuggestionList)obj;
        Iterable aiterable[] = new Iterable[1];
        aiterable[0] = locationsuggestionfetcher.buildSection(0x7f13010b, ((SuggestionList) (obj)).contacts);
        obj = FluentIterable.concatNoDefensiveCopy((Iterable[])Arrays.copyOf(aiterable, aiterable.length));
        return ImmutableList.copyOf((Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj));
    }

    SuggestionList(LocationSuggestionFetcher locationsuggestionfetcher)
    {
        arg$1 = locationsuggestionfetcher;
    }
}
