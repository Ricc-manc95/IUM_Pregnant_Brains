// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.data;


// Referenced classes of package net.fortuna.ical4j.data:
//            CalendarParserFactory, CalendarParserImpl, CalendarParser

public final class DefaultCalendarParserFactory extends CalendarParserFactory
{

    public DefaultCalendarParserFactory()
    {
    }

    public final CalendarParser createParser()
    {
        return new CalendarParserImpl();
    }
}
