// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.location.fullscreen;

import com.google.common.base.Function;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.segment.location.fullscreen:
//            LocationSuggestionFetcher

final class arg._cls1
    implements Function
{

    private final LocationSuggestionFetcher arg$1;

    public final Object apply(Object obj)
    {
        return arg$1.buildSection(0x7f130346, (List)obj);
    }

    (LocationSuggestionFetcher locationsuggestionfetcher)
    {
        arg$1 = locationsuggestionfetcher;
    }
}
