// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time;

import java.io.Serializable;
import org.joda.time.base.AbstractInstant;
import org.joda.time.chrono.ISOChronology;

// Referenced classes of package org.joda.time:
//            ReadableInstant, DateTimeUtils, Chronology

public final class Instant extends AbstractInstant
    implements Serializable, ReadableInstant
{

    public static final long serialVersionUID = 0x2dc8bed0c60e9ccdL;
    public final long iMillis;

    public Instant()
    {
        iMillis = DateTimeUtils.cMillisProvider.getMillis();
    }

    public Instant(long l)
    {
        iMillis = l;
    }

    public final Chronology getChronology()
    {
        return ISOChronology.INSTANCE_UTC;
    }

    public final long getMillis()
    {
        return iMillis;
    }
}
