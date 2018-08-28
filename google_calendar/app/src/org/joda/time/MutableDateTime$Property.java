// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.joda.time.base.BaseDateTime;
import org.joda.time.field.AbstractReadableInstantFieldProperty;

// Referenced classes of package org.joda.time:
//            MutableDateTime, DateTimeFieldType, DateTimeField, Chronology

public final class iField extends AbstractReadableInstantFieldProperty
{

    public static final long serialVersionUID = 0xc1cfd7268213a8c7L;
    private DateTimeField iField;
    public MutableDateTime iInstant;

    private final void readObject(ObjectInputStream objectinputstream)
        throws IOException, ClassNotFoundException
    {
        iInstant = (MutableDateTime)objectinputstream.readObject();
        iField = ((DateTimeFieldType)objectinputstream.readObject()).getField(((BaseDateTime) (iInstant)).iChronology);
    }

    private final void writeObject(ObjectOutputStream objectoutputstream)
        throws IOException
    {
        objectoutputstream.writeObject(iInstant);
        objectoutputstream.writeObject(iField.getType());
    }

    protected final Chronology getChronology()
    {
        return ((BaseDateTime) (iInstant)).iChronology;
    }

    public final DateTimeField getField()
    {
        return iField;
    }

    protected final long getMillis()
    {
        return ((BaseDateTime) (iInstant)).iMillis;
    }

    public tantFieldProperty(MutableDateTime mutabledatetime, DateTimeField datetimefield)
    {
        iInstant = mutabledatetime;
        iField = datetimefield;
    }
}
