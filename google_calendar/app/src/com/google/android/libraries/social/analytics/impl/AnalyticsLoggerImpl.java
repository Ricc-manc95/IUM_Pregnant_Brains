// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.social.analytics.impl;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import com.google.android.libraries.social.analytics.AnalyticsEvent;
import com.google.android.libraries.social.analytics.AnalyticsLogger;
import java.util.Iterator;
import java.util.Set;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.social.analytics.impl:
//            EventHandler

final class AnalyticsLoggerImpl
    implements AnalyticsLogger
{

    private Set eventHandlers;
    private final Provider eventHandlersProvider;

    AnalyticsLoggerImpl(Provider provider)
    {
        eventHandlersProvider = provider;
    }

    private final Set getEventHandlers()
    {
        this;
        JVM INSTR monitorenter ;
        Set set;
        if (eventHandlers == null)
        {
            eventHandlers = (Set)eventHandlersProvider.get();
        }
        set = eventHandlers;
        this;
        JVM INSTR monitorexit ;
        return set;
        Exception exception;
        exception;
        throw exception;
    }

    public final void recordEvent(Context context, AnalyticsEvent analyticsevent)
    {
        SystemClock.elapsedRealtimeNanos();
        Bundle bundle = new Bundle();
        EventHandler eventhandler;
        for (Iterator iterator = getEventHandlers().iterator(); iterator.hasNext(); eventhandler.handleEvent(analyticsevent, bundle))
        {
            eventhandler = (EventHandler)iterator.next();
            eventhandler.populateBundle(context, analyticsevent, bundle);
        }

        SystemClock.elapsedRealtimeNanos();
    }
}
