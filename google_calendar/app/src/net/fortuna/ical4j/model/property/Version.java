// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;

public class Version extends Property
{

    public static final Version VERSION_2_0 = new ImmutableVersion("2.0");
    public static final long serialVersionUID = 0x7b217b1422beebd8L;
    private String maxVersion;
    private String minVersion;

    public Version()
    {
        super("VERSION", PropertyFactoryImpl.instance);
    }

    public Version(ParameterList parameterlist, String s)
    {
        super("VERSION", parameterlist, PropertyFactoryImpl.instance);
        if (s.indexOf(';') >= 0)
        {
            minVersion = s.substring(0, s.indexOf(';') - 1);
            maxVersion = s.substring(s.indexOf(';'));
            return;
        } else
        {
            maxVersion = s;
            return;
        }
    }

    public final String getValue()
    {
        StringBuffer stringbuffer = new StringBuffer();
        if (minVersion != null)
        {
            stringbuffer.append(minVersion);
            if (maxVersion != null)
            {
                stringbuffer.append(';');
            }
        }
        if (maxVersion != null)
        {
            stringbuffer.append(maxVersion);
        }
        return stringbuffer.toString();
    }

    public void setValue(String s)
    {
        if (s.indexOf(';') >= 0)
        {
            minVersion = s.substring(0, s.indexOf(';') - 1);
            maxVersion = s.substring(s.indexOf(';'));
            return;
        } else
        {
            maxVersion = s;
            return;
        }
    }

    public final void validate()
        throws ValidationException
    {
    }


    private class ImmutableVersion extends Version
    {

        public static final long serialVersionUID = 0xba0be8d4a8227dadL;

        public final void setValue(String s)
        {
            throw new UnsupportedOperationException("Cannot modify constant instances");
        }

        ImmutableVersion(String s)
        {
            super(new ParameterList(true), s);
        }
    }

}
