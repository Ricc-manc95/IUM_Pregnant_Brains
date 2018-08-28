// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.data;

import java.io.IOException;
import java.io.Reader;

// Referenced classes of package net.fortuna.ical4j.data:
//            ParserException, ContentHandler

public interface CalendarParser
{

    public abstract void parse(Reader reader, ContentHandler contenthandler)
        throws IOException, ParserException;
}
