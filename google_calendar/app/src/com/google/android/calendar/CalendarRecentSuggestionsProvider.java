// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.SearchRecentSuggestionsProvider;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.feature.VariantFeatureConfig_release;
import com.google.android.apps.calendar.primes.PerformanceMetricCollectorImpl;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollectorHolder;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.analytics.CalendarAnalyticsLoggerExtension;

// Referenced classes of package com.google.android.calendar:
//            Utils

public class CalendarRecentSuggestionsProvider extends SearchRecentSuggestionsProvider
{

    public CalendarRecentSuggestionsProvider()
    {
    }

    public boolean onCreate()
    {
        AnalyticsLoggerHolder.set(new CalendarAnalyticsLoggerExtension(getContext()));
        setupSuggestions(Utils.getSearchAuthority(getContext()), 1);
        return super.onCreate();
    }

    static 
    {
        Features.instance = new VariantFeatureConfig_release();
        PerformanceMetricCollectorImpl performancemetriccollectorimpl = new PerformanceMetricCollectorImpl();
        if (PerformanceMetricCollectorHolder.instance == null)
        {
            PerformanceMetricCollectorHolder.instance = performancemetriccollectorimpl;
        }
    }
}
