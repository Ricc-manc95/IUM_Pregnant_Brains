// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.data;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

public interface ContentHandler
{

    public abstract void endCalendar();

    public abstract void endComponent$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0();

    public abstract void endProperty$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0();

    public abstract void parameter(String s, String s1)
        throws URISyntaxException;

    public abstract void propertyValue(String s)
        throws URISyntaxException, ParseException, IOException;

    public abstract void startCalendar();

    public abstract void startComponent(String s);

    public abstract void startProperty(String s);
}
