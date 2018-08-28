// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.PropertyFactoryImpl;

// Referenced classes of package net.fortuna.ical4j.model.property:
//            UtcProperty

public final class LastModified extends UtcProperty
{

    public static final long serialVersionUID = 0x4964c8ce96746edeL;

    public LastModified()
    {
        super("LAST-MODIFIED", PropertyFactoryImpl.instance);
    }
}
