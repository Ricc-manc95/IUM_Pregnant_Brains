// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.PropertyFactoryImpl;

// Referenced classes of package net.fortuna.ical4j.model.property:
//            UtcProperty

public final class Completed extends UtcProperty
{

    public static final long serialVersionUID = 0x5eb479d2d455f10dL;

    public Completed()
    {
        super("COMPLETED", PropertyFactoryImpl.instance);
    }
}
