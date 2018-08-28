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

final class  extends 
{

    public final int estimatePrintedLength()
    {
        return iMaxParsedDigits;
    }

    public final void printTo(StringBuffer stringbuffer, long l, Chronology chronology, int i, DateTimeZone datetimezone, Locale locale)
    {
        try
        {
            FormatUtils.appendUnpaddedInteger(stringbuffer, iFieldType.getField(chronology).get(l));
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Chronology chronology)
        {
            stringbuffer.append('\uFFFD');
        }
    }

    protected (DateTimeFieldType datetimefieldtype, int i, boolean flag)
    {
        super(datetimefieldtype, i, flag);
    }
}
