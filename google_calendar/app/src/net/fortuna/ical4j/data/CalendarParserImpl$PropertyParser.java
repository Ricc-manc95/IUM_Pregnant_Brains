// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.data;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.text.ParseException;
import org.apache.commons.logging.Log;

// Referenced classes of package net.fortuna.ical4j.data:
//            CalendarParserImpl, ParserException, ContentHandler

final class this._cls0
{

    private final CalendarParserImpl this$0;

    final void parse(StreamTokenizer streamtokenizer, Reader reader, ContentHandler contenthandler)
        throws IOException, ParserException, URISyntaxException, ParseException
    {
        String s = streamtokenizer.sval;
        if (log.isDebugEnabled())
        {
            log.debug(MessageFormat.format("Property [{0}]", new Object[] {
                s
            }));
        }
        contenthandler.startProperty(s);
        Object obj = paramListParser;
        do
        {
            CalendarParserImpl calendarparserimpl = ((rser) (obj))._fld0;
            int i = streamtokenizer.nextToken();
            if (i == -1)
            {
                throw new ParserException("Unexpected end of file", CalendarParserImpl.getLineNumber(streamtokenizer, reader));
            }
            if (i != 59)
            {
                break;
            }
            ((rser) (obj))._fld0.paramParser.parse(streamtokenizer, reader, contenthandler);
        } while (true);
        obj = new StringBuffer();
        streamtokenizer.ordinaryChar(34);
        CalendarParserImpl calendarparserimpl1 = CalendarParserImpl.this;
        int k = streamtokenizer.nextToken();
        int j = k;
        if (k == -1)
        {
            throw new ParserException("Unexpected end of file", CalendarParserImpl.getLineNumber(streamtokenizer, reader));
        }
        while (j != 10) 
        {
            CalendarParserImpl calendarparserimpl2;
            int l;
            if (streamtokenizer.ttype == -3)
            {
                ((StringBuffer) (obj)).append(streamtokenizer.sval);
            } else
            {
                ((StringBuffer) (obj)).append((char)streamtokenizer.ttype);
            }
            calendarparserimpl2 = CalendarParserImpl.this;
            l = streamtokenizer.nextToken();
            j = l;
            if (l == -1)
            {
                throw new ParserException("Unexpected end of file", CalendarParserImpl.getLineNumber(streamtokenizer, reader));
            }
        }
        streamtokenizer.quoteChar(34);
        try
        {
            contenthandler.propertyValue(((StringBuffer) (obj)).toString());
        }
        // Misplaced declaration of an exception variable
        catch (StreamTokenizer streamtokenizer)
        {
            reader = streamtokenizer.getMessage();
            reader = new ParseException((new StringBuilder(String.valueOf(s).length() + 3 + String.valueOf(reader).length())).append("[").append(s).append("] ").append(reader).toString(), streamtokenizer.getErrorOffset());
            reader.initCause(streamtokenizer);
            throw reader;
        }
        contenthandler.endProperty$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0();
    }

    ()
    {
        this$0 = CalendarParserImpl.this;
        super();
    }
}
