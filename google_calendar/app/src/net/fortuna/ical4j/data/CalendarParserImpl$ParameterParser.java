// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.data;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.net.URISyntaxException;
import org.apache.commons.logging.Log;

// Referenced classes of package net.fortuna.ical4j.data:
//            CalendarParserImpl, ParserException, ContentHandler

final class this._cls0
{

    private final CalendarParserImpl this$0;

    final void parse(StreamTokenizer streamtokenizer, Reader reader, ContentHandler contenthandler)
        throws IOException, ParserException, URISyntaxException
    {
        assertToken(streamtokenizer, reader, -3);
        Object obj = streamtokenizer.sval;
        if (log.isDebugEnabled())
        {
            log.debug((new StringBuilder(String.valueOf(obj).length() + 12)).append("Parameter [").append(((String) (obj))).append("]").toString());
        }
        assertToken(streamtokenizer, reader, 61);
        StringBuffer stringbuffer = new StringBuffer();
        CalendarParserImpl calendarparserimpl = CalendarParserImpl.this;
        int i = streamtokenizer.nextToken();
        if (i == -1)
        {
            throw new ParserException("Unexpected end of file", CalendarParserImpl.getLineNumber(streamtokenizer, reader));
        }
        if (i == 34)
        {
            stringbuffer.append('"');
            stringbuffer.append(streamtokenizer.sval);
            stringbuffer.append('"');
        } else
        if (streamtokenizer.sval != null)
        {
            stringbuffer.append(streamtokenizer.sval);
            CalendarParserImpl calendarparserimpl1 = CalendarParserImpl.this;
            int k = streamtokenizer.nextToken();
            int j = k;
            if (k == -1)
            {
                throw new ParserException("Unexpected end of file", CalendarParserImpl.getLineNumber(streamtokenizer, reader));
            }
            while (j != 59 && j != 58 && j != 44) 
            {
                CalendarParserImpl calendarparserimpl2;
                int l;
                if (streamtokenizer.ttype == -3)
                {
                    stringbuffer.append(streamtokenizer.sval);
                } else
                {
                    stringbuffer.append((char)streamtokenizer.ttype);
                }
                calendarparserimpl2 = CalendarParserImpl.this;
                l = streamtokenizer.nextToken();
                j = l;
                if (l == -1)
                {
                    throw new ParserException("Unexpected end of file", CalendarParserImpl.getLineNumber(streamtokenizer, reader));
                }
            }
            streamtokenizer.pushBack();
        } else
        if (streamtokenizer.sval == null)
        {
            streamtokenizer.pushBack();
        }
        try
        {
            contenthandler.parameter(((String) (obj)), stringbuffer.toString());
            return;
        }
        // Misplaced declaration of an exception variable
        catch (ContentHandler contenthandler)
        {
            obj = CalendarParserImpl.this;
        }
        throw new ParserException("Error parsing parameter", CalendarParserImpl.getLineNumber(streamtokenizer, reader), contenthandler);
    }

    ()
    {
        this$0 = CalendarParserImpl.this;
        super();
    }
}
