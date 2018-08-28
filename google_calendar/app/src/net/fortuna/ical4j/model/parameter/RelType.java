// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.parameter;

import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterFactoryImpl;
import net.fortuna.ical4j.util.Strings;

public final class RelType extends Parameter
{

    public static final RelType CHILD = new RelType("CHILD");
    public static final RelType PARENT = new RelType("PARENT");
    public static final RelType SIBLING = new RelType("SIBLING");
    public static final long serialVersionUID = 0x4a30eac4d72697c8L;
    private String value;

    public RelType(String s)
    {
        super("RELTYPE", ParameterFactoryImpl.instance);
        value = Strings.unquote(s);
    }

    public final String getValue()
    {
        return value;
    }

}
