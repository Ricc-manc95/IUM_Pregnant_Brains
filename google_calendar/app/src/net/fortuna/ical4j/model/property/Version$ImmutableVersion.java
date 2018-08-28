// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.ParameterList;

// Referenced classes of package net.fortuna.ical4j.model.property:
//            Version

final class  extends Version
{

    public static final long serialVersionUID = 0xba0be8d4a8227dadL;

    public final void setValue(String s)
    {
        throw new UnsupportedOperationException("Cannot modify constant instances");
    }

    (String s)
    {
        super(new ParameterList(true), s);
    }
}
