// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.format;

import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;

// Referenced classes of package org.joda.time.format:
//            FormatUtils

class iMinPrintedDigits extends er
{

    private final int iMinPrintedDigits;

    public final int estimatePrintedLength()
    {
        return iMaxParsedDigits;
    }

    public final void printTo(StringBuffer stringbuffer, long l, Chronology chronology, int i, DateTimeZone datetimezone, Locale locale)
    {
        FormatUtils.appendPaddedInteger(stringbuffer, iFieldType.getField(chronology).get(l), iMinPrintedDigits);
_L2:
        return;
        chronology;
        i = iMinPrintedDigits;
        do
        {
            i--;
            if (i < 0)
            {
                continue;
            }
            stringbuffer.append('\uFFFD');
        } while (true);
        if (true) goto _L2; else goto _L1
_L1:
    }

    protected er(DateTimeFieldType datetimefieldtype, int i, boolean flag, int j)
    {
        super(datetimefieldtype, i, flag);
        iMinPrintedDigits = j;
    }
}
