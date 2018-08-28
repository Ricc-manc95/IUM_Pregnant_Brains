// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.data;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.net.URISyntaxException;
import java.text.ParseException;
import org.apache.commons.logging.Log;

// Referenced classes of package net.fortuna.ical4j.data:
//            CalendarParserImpl, ParserException, ContentHandler

final class this._cls0
{

    private final CalendarParserImpl this$0;

    public final void parse(StreamTokenizer streamtokenizer, Reader reader, ContentHandler contenthandler)
        throws IOException, ParseException, URISyntaxException, ParserException
    {
        assertToken(streamtokenizer, reader, -3);
        do
        {
            if ("END".equalsIgnoreCase(streamtokenizer.sval))
            {
                break;
            }
            CalendarParserImpl calendarparserimpl;
            if ("BEGIN".equalsIgnoreCase(streamtokenizer.sval))
            {
                this._cls0 _lcls0 = componentParser;
                _lcls0._fld0.assertToken(streamtokenizer, reader, 58);
                _lcls0._fld0.assertToken(streamtokenizer, reader, -3);
                String s = streamtokenizer.sval;
                contenthandler.startComponent(s);
                _lcls0._fld0.assertToken(streamtokenizer, reader, 10);
                _lcls0._fld0.propertyListParser.parse(streamtokenizer, reader, contenthandler);
                _lcls0._fld0.assertToken(streamtokenizer, reader, 58);
                _lcls0._fld0.assertToken(streamtokenizer, reader, s, false);
                _lcls0._fld0.assertToken(streamtokenizer, reader, 10);
                contenthandler.endComponent$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0();
            } else
            {
                propertyParser.e(streamtokenizer, reader, contenthandler);
            }
            calendarparserimpl = CalendarParserImpl.this;
            do
            {
                int i = streamtokenizer.nextToken();
                if (i == -1)
                {
                    throw new ParserException("Unexpected end of file", CalendarParserImpl.getLineNumber(streamtokenizer, reader));
                }
                if (i != 10)
                {
                    break;
                }
                if (calendarparserimpl.log.isTraceEnabled())
                {
                    calendarparserimpl.log.trace("Absorbing extra whitespace..");
                }
            } while (true);
            if (calendarparserimpl.log.isTraceEnabled())
            {
                calendarparserimpl.log.trace("Aborting: absorbing extra whitespace complete");
            }
        } while (true);
    }

    ()
    {
        this$0 = CalendarParserImpl.this;
        super();
    }
}
