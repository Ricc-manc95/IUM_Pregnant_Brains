// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.ParameterList;

// Referenced classes of package net.fortuna.ical4j.model.property:
//            Status

final class  extends Status
{

    public static final long serialVersionUID = 0x6bdb399114aa8d6cL;

    public final void setValue(String s)
    {
        throw new UnsupportedOperationException("Cannot modify constant instances");
    }

    (String s)
    {
        super(new ParameterList(true), s);
    }
}
