// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import net.fortuna.ical4j.model.property.ExtendedAddress;

// Referenced classes of package net.fortuna.ical4j.model:
//            PropertyFactory, Property

final class I
    implements PropertyFactory
{

    public static final long serialVersionUID = 1L;

    public final Property createProperty(String s)
    {
        return new ExtendedAddress();
    }

    I()
    {
    }
}
