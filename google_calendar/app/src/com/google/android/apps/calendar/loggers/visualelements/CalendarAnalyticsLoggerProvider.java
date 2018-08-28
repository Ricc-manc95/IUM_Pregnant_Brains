// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.loggers.visualelements;

import android.content.Context;
import com.google.android.libraries.gcoreclient.clearcut.impl.GcoreClearcutLoggerImpl;
import com.google.android.libraries.social.analytics.AnalyticsLogger;
import com.google.android.libraries.social.analytics.events.handler.lite.UserEventLiteProtoPopulator;
import com.google.android.libraries.social.analytics.impl.AnalyticsLoggerProvider;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import java.util.Set;

// Referenced classes of package com.google.android.apps.calendar.loggers.visualelements:
//            EventMedatadaHandler, ConferenceMetadataHandler, UserEventHandler

final class CalendarAnalyticsLoggerProvider
{

    private static final Object LOCK = new Object();
    private static volatile AnalyticsLogger analyticsLogger;
    public static Set handlers;

    public static AnalyticsLogger get(Context context)
    {
        if (analyticsLogger == null)
        {
            synchronized (LOCK)
            {
                if (analyticsLogger == null)
                {
                    context = new GcoreClearcutLoggerImpl(context.getApplicationContext(), "CALENDAR_CLIENT", null);
                    UserEventLiteProtoPopulator usereventliteprotopopulator = new UserEventLiteProtoPopulator(ImmutableSet.construct(2, new Object[] {
                        new EventMedatadaHandler(), new ConferenceMetadataHandler()
                    }));
                    class .Lambda._cls0
                        implements AuthenticationProvider
                    {

                        public static final AuthenticationProvider $instance = new .Lambda._cls0();

                        public final String getAccountName(Context context1)
                        {
                            return CalendarAnalyticsLoggerProvider.lambda$get$0$CalendarAnalyticsLoggerProvider$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7CKKOQJ1EPGIUR31DPJIUKRKE9KMSPPR0();
                        }


            private .Lambda._cls0()
            {
            }
                    }

                    handlers = new SingletonImmutableSet(new UserEventHandler(.Lambda._cls0..instance, context, usereventliteprotopopulator));
                    class .Lambda._cls1
                        implements Provider
                    {

                        public static final Provider $instance = new .Lambda._cls1();

                        public final Object get()
                        {
                            return CalendarAnalyticsLoggerProvider.handlers;
                        }


            private .Lambda._cls1()
            {
            }
                    }

                    analyticsLogger = (AnalyticsLogger)(new AnalyticsLoggerProvider(.Lambda._cls1..instance)).get();
                }
            }
        }
        return analyticsLogger;
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
    }

    static final String lambda$get$0$CalendarAnalyticsLoggerProvider$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7CKKOQJ1EPGIUR31DPJIUKRKE9KMSPPR0()
    {
        return null;
    }

}
