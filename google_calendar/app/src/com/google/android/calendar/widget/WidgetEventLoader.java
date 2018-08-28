// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import android.content.Context;
import com.google.android.calendar.timely.EventRangedQueryHandler;
import com.google.android.calendar.timely.RangedData;

final class WidgetEventLoader extends EventRangedQueryHandler
{

    public EventsProcessor eventsProcessor;

    WidgetEventLoader(Context context)
    {
        super(context, false);
    }

    protected final com.google.android.calendar.timely.RangedData.EventResults createStorage(int i)
    {
        return eventsProcessor.getStorage();
    }

    protected final void processResults(RangedData rangeddata, com.google.android.calendar.timely.RangedData.EventResults eventresults)
    {
        eventsProcessor._mth51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL4OBECTIM8H31EHGJMJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOR1DHIMSP31E8NN8QBDCLM7IBQIC5N6EPB48HGN8O948LR6ARJKA9IN6TBCEHPJMAAM0(rangeddata);
    }

    private class EventsProcessor
    {

        public abstract com.google.android.calendar.timely.RangedData.EventResults getStorage();

        public abstract void processResults$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL4OBECTIM8H31EHGJMJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOR1DHIMSP31E8NN8QBDCLM7IBQIC5N6EPB48HGN8O948LR6ARJKA9IN6TBCEHPJMAAM0(RangedData rangeddata);
    }

}
