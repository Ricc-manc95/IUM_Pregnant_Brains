// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.PropertyFactoryImpl;

// Referenced classes of package net.fortuna.ical4j.model.property:
//            UtcProperty, DateProperty

public final class DtStamp extends UtcProperty
{

    public static final long serialVersionUID = 0x6935d34b9b8beac6L;

    public DtStamp()
    {
        super("DTSTAMP", PropertyFactoryImpl.instance);
    }

    public DtStamp(DateTime datetime)
    {
        super("DTSTAMP", PropertyFactoryImpl.instance);
        datetime.setUtc(true);
        setDate(datetime);
    }
}
