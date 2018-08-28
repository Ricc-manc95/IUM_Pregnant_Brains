// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.format;

import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;

// Referenced classes of package org.joda.time.format:
//            DateTimeParser, DateTimePrinter, DateTimeParserBucket

final class  extends Enum
    implements DateTimeParser, DateTimePrinter
{

    private static final MAX_LENGTH $VALUES[];
    private static final Set ALL_IDS;
    public static final MAX_LENGTH INSTANCE;
    private static final int MAX_LENGTH;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    public final int estimateParsedLength()
    {
        return MAX_LENGTH;
    }

    public final int estimatePrintedLength()
    {
        return MAX_LENGTH;
    }

    public final int parseInto(DateTimeParserBucket datetimeparserbucket, String s, int i)
    {
        String s2;
        Iterator iterator;
        s2 = s.substring(i);
        iterator = ALL_IDS.iterator();
        s = null;
_L2:
        Object obj;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        String s1 = (String)iterator.next();
        if (!s2.startsWith(s1))
        {
            break MISSING_BLOCK_LABEL_113;
        }
        obj = s1;
        if (s != null)
        {
            if (s1.length() <= s.length())
            {
                break MISSING_BLOCK_LABEL_113;
            }
            obj = s1;
        }
_L3:
        s = ((String) (obj));
        if (true) goto _L2; else goto _L1
_L1:
        if (s != null)
        {
            obj = DateTimeZone.forID(s);
            datetimeparserbucket.iSavedState = null;
            datetimeparserbucket.iZone = ((DateTimeZone) (obj));
            return s.length() + i;
        } else
        {
            return ~i;
        }
        obj = s;
          goto _L3
    }

    public final void printTo(StringBuffer stringbuffer, long l, Chronology chronology, int i, DateTimeZone datetimezone, Locale locale)
    {
        if (datetimezone != null)
        {
            chronology = datetimezone.iID;
        } else
        {
            chronology = "";
        }
        stringbuffer.append(chronology);
    }

    static 
    {
        INSTANCE = new <init>("INSTANCE", 0);
        $VALUES = (new .VALUES[] {
            INSTANCE
        });
        ALL_IDS = DateTimeZone.cAvailableIDs;
        Iterator iterator = ALL_IDS.iterator();
        int i;
        for (i = 0; iterator.hasNext(); i = Math.max(i, ((String)iterator.next()).length())) { }
        MAX_LENGTH = i;
    }

    private (String s, int i)
    {
        super(s, 0);
    }
}
