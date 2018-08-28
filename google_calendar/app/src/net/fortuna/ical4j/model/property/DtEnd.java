// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.PropertyFactoryImpl;

// Referenced classes of package net.fortuna.ical4j.model.property:
//            DateProperty

public final class DtEnd extends DateProperty
{

    public static final long serialVersionUID = 0x7083548512a54109L;

    public DtEnd()
    {
        super("DTEND", PropertyFactoryImpl.instance);
    }

    public DtEnd(Date date)
    {
        super("DTEND", PropertyFactoryImpl.instance);
        setDate(date);
    }
}
