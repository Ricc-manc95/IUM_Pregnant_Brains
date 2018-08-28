// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.PropertyFactoryImpl;

// Referenced classes of package net.fortuna.ical4j.model.property:
//            DateProperty

public final class DtStart extends DateProperty
{

    public static final long serialVersionUID = 0xb0cc51153fa82cf9L;

    public DtStart()
    {
        super("DTSTART", PropertyFactoryImpl.instance);
    }

    public DtStart(Date date)
    {
        super("DTSTART", PropertyFactoryImpl.instance);
        setDate(date);
    }
}
