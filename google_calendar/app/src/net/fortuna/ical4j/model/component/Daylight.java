// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.component;

import net.fortuna.ical4j.model.PropertyList;

// Referenced classes of package net.fortuna.ical4j.model.component:
//            Observance

public final class Daylight extends Observance
{

    public static final long serialVersionUID = 0xdd6101ea7a2a4435L;

    public Daylight()
    {
        super("DAYLIGHT");
    }

    public Daylight(PropertyList propertylist)
    {
        super("DAYLIGHT", propertylist);
    }
}
