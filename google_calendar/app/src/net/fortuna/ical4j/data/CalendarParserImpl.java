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
import org.apache.commons.logging.LogFactory;

// Referenced classes of package net.fortuna.ical4j.data:
//            CalendarParser, UnfoldingReader, ParserException, ContentHandler

public class CalendarParserImpl
    implements CalendarParser
{
    final class ComponentListParser
    {

        public final CalendarParserImpl this$0;

        ComponentListParser()
        {
            this$0 = CalendarParserImpl.this;
            super();
        }
    }

    final class ComponentParser
    {

        public final CalendarParserImpl this$0;

        ComponentParser()
        {
            this$0 = CalendarParserImpl.this;
            super();
        }
    }

    final class ParameterListParser
    {

        public final CalendarParserImpl this$0;

        ParameterListParser()
        {
            this$0 = CalendarParserImpl.this;
            super();
        }
    }

    final class ParameterParser
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

        ParameterParser()
        {
            this$0 = CalendarParserImpl.this;
            super();
        }
    }

    final class PropertyListParser
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
                    ComponentParser componentparser = componentParser;
                    componentparser._fld0.assertToken(streamtokenizer, reader, 58);
                    componentparser._fld0.assertToken(streamtokenizer, reader, -3);
                    String s = streamtokenizer.sval;
                    contenthandler.startComponent(s);
                    componentparser._fld0.assertToken(streamtokenizer, reader, 10);
                    componentparser._fld0.propertyListParser.parse(streamtokenizer, reader, contenthandler);
                    componentparser._fld0.assertToken(streamtokenizer, reader, 58);
                    componentparser._fld0.assertToken(streamtokenizer, reader, s, false);
                    componentparser._fld0.assertToken(streamtokenizer, reader, 10);
                    contenthandler.endComponent$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0();
                } else
                {
                    propertyParser.parse(streamtokenizer, reader, contenthandler);
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

        PropertyListParser()
        {
            this$0 = CalendarParserImpl.this;
            super();
        }
    }

    final class PropertyParser
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
                CalendarParserImpl calendarparserimpl = ((ParameterListParser) (obj))._fld0;
                int i = streamtokenizer.nextToken();
                if (i == -1)
                {
                    throw new ParserException("Unexpected end of file", CalendarParserImpl.getLineNumber(streamtokenizer, reader));
                }
                if (i != 59)
                {
                    break;
                }
                ((ParameterListParser) (obj))._fld0.paramParser.parse(streamtokenizer, reader, contenthandler);
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

        PropertyParser()
        {
            this$0 = CalendarParserImpl.this;
            super();
        }
    }


    private final ComponentListParser componentListParser = new ComponentListParser();
    public final ComponentParser componentParser = new ComponentParser();
    public Log log;
    public final ParameterListParser paramListParser = new ParameterListParser();
    public final ParameterParser paramParser = new ParameterParser();
    public final PropertyListParser propertyListParser = new PropertyListParser();
    public final PropertyParser propertyParser = new PropertyParser();

    public CalendarParserImpl()
    {
        log = LogFactory.getLog(net/fortuna/ical4j/data/CalendarParserImpl);
    }

    static int getLineNumber(StreamTokenizer streamtokenizer, Reader reader)
    {
        int j = streamtokenizer.lineno();
        int i = j;
        if (streamtokenizer.ttype == 10)
        {
            i = j - 1;
        }
        j = i;
        if (reader instanceof UnfoldingReader)
        {
            j = i + ((UnfoldingReader)reader).linesUnfolded;
        }
        return j;
    }

    final void assertToken(StreamTokenizer streamtokenizer, Reader reader, int i)
        throws IOException, ParserException
    {
        int j = streamtokenizer.nextToken();
        if (j == -1)
        {
            throw new ParserException("Unexpected end of file", getLineNumber(streamtokenizer, reader));
        }
        if (j != i)
        {
            throw new ParserException(MessageFormat.format("Expected [{0}], read [{1}]", new Object[] {
                new Integer(i), new Integer(streamtokenizer.ttype)
            }), getLineNumber(streamtokenizer, reader));
        }
        if (log.isDebugEnabled())
        {
            log.debug((new StringBuilder(13)).append("[").append(i).append("]").toString());
        }
    }

    final void assertToken(StreamTokenizer streamtokenizer, Reader reader, String s, boolean flag)
        throws IOException, ParserException
    {
        assertToken(streamtokenizer, reader, -3);
        if (flag)
        {
            if (!s.equalsIgnoreCase(streamtokenizer.sval))
            {
                throw new ParserException(MessageFormat.format("Expected [{0}], read [{1}]", new Object[] {
                    s, streamtokenizer.sval
                }), getLineNumber(streamtokenizer, reader));
            }
        } else
        if (!s.equals(streamtokenizer.sval))
        {
            throw new ParserException(MessageFormat.format("Expected [{0}], read [{1}]", new Object[] {
                s, streamtokenizer.sval
            }), getLineNumber(streamtokenizer, reader));
        }
        if (log.isDebugEnabled())
        {
            log.debug((new StringBuilder(String.valueOf(s).length() + 2)).append("[").append(s).append("]").toString());
        }
    }

    public final void parse(Reader reader, ContentHandler contenthandler)
        throws IOException, ParserException
    {
        StreamTokenizer streamtokenizer = new StreamTokenizer(reader);
        ComponentListParser componentlistparser;
        streamtokenizer.resetSyntax();
        streamtokenizer.wordChars(32, 255);
        streamtokenizer.whitespaceChars(0, 20);
        streamtokenizer.ordinaryChar(58);
        streamtokenizer.ordinaryChar(59);
        streamtokenizer.ordinaryChar(61);
        streamtokenizer.ordinaryChar(9);
        streamtokenizer.eolIsSignificant(true);
        streamtokenizer.whitespaceChars(0, 0);
        streamtokenizer.quoteChar(34);
        assertToken(streamtokenizer, reader, "BEGIN", true);
        assertToken(streamtokenizer, reader, 58);
        assertToken(streamtokenizer, reader, "VCALENDAR", true);
        assertToken(streamtokenizer, reader, 10);
        contenthandler.startCalendar();
        propertyListParser.parse(streamtokenizer, reader, contenthandler);
        componentlistparser = componentListParser;
_L6:
        Object obj;
        if (!"BEGIN".equalsIgnoreCase(streamtokenizer.sval))
        {
            break MISSING_BLOCK_LABEL_365;
        }
        obj = componentlistparser._fld0.componentParser;
        ((ComponentParser) (obj))._fld0.assertToken(streamtokenizer, reader, 58);
        ((ComponentParser) (obj))._fld0.assertToken(streamtokenizer, reader, -3);
        String s = streamtokenizer.sval;
        contenthandler.startComponent(s);
        ((ComponentParser) (obj))._fld0.assertToken(streamtokenizer, reader, 10);
        ((ComponentParser) (obj))._fld0.propertyListParser.parse(streamtokenizer, reader, contenthandler);
        ((ComponentParser) (obj))._fld0.assertToken(streamtokenizer, reader, 58);
        ((ComponentParser) (obj))._fld0.assertToken(streamtokenizer, reader, s, false);
        ((ComponentParser) (obj))._fld0.assertToken(streamtokenizer, reader, 10);
        contenthandler.endComponent$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0();
        obj = componentlistparser._fld0;
_L4:
        int i = streamtokenizer.nextToken();
        if (i == -1)
        {
            try
            {
                throw new ParserException("Unexpected end of file", getLineNumber(streamtokenizer, reader));
            }
            // Misplaced declaration of an exception variable
            catch (ContentHandler contenthandler) { }
            if (contenthandler instanceof IOException)
            {
                throw (IOException)contenthandler;
            }
            break MISSING_BLOCK_LABEL_389;
        }
        if (i != 10) goto _L2; else goto _L1
_L1:
        if (!((CalendarParserImpl) (obj)).log.isTraceEnabled()) goto _L4; else goto _L3
_L3:
        ((CalendarParserImpl) (obj)).log.trace("Absorbing extra whitespace..");
          goto _L4
_L2:
        if (!((CalendarParserImpl) (obj)).log.isTraceEnabled()) goto _L6; else goto _L5
_L5:
        ((CalendarParserImpl) (obj)).log.trace("Aborting: absorbing extra whitespace complete");
          goto _L6
        assertToken(streamtokenizer, reader, 58);
        assertToken(streamtokenizer, reader, "VCALENDAR", true);
        contenthandler.endCalendar();
        return;
        if (contenthandler instanceof ParserException)
        {
            throw (ParserException)contenthandler;
        } else
        {
            throw new ParserException(contenthandler.getMessage(), getLineNumber(streamtokenizer, reader), contenthandler);
        }
    }
}
