// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.chrono;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.joda.time.DateTimeZone;

// Referenced classes of package org.joda.time.chrono:
//            ISOChronology

final class iZone
    implements Serializable
{

    public static final long serialVersionUID = 0xa9c8116671375027L;
    private transient DateTimeZone iZone;

    private final void readObject(ObjectInputStream objectinputstream)
        throws IOException, ClassNotFoundException
    {
        iZone = (DateTimeZone)objectinputstream.readObject();
    }

    private final Object readResolve()
    {
        return ISOChronology.getInstance(iZone);
    }

    private final void writeObject(ObjectOutputStream objectoutputstream)
        throws IOException
    {
        objectoutputstream.writeObject(iZone);
    }

    (DateTimeZone datetimezone)
    {
        iZone = datetimezone;
    }
}
