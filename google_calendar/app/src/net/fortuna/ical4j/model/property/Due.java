// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.PropertyFactoryImpl;

// Referenced classes of package net.fortuna.ical4j.model.property:
//            DateProperty

public final class Due extends DateProperty
{

    public static final long serialVersionUID = 0xd6d918216b59bcdaL;

    public Due()
    {
        super("DUE", PropertyFactoryImpl.instance);
        setDate(new DateTime(true));
    }
}
