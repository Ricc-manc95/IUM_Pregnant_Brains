// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

// Referenced classes of package com.google.common.collect:
//            ImmutableMultimap, ListMultimap, ImmutableList, Serialization, 
//            ImmutableMap, ImmutableCollection

public class ImmutableListMultimap extends ImmutableMultimap
    implements ListMultimap
{

    public static final long serialVersionUID = 0L;

    ImmutableListMultimap(ImmutableMap immutablemap, int i)
    {
        super(immutablemap, i);
    }

    private void readObject(ObjectInputStream objectinputstream)
        throws IOException, ClassNotFoundException
    {
        objectinputstream.defaultReadObject();
        int l = objectinputstream.readInt();
        if (l < 0)
        {
            throw new InvalidObjectException((new StringBuilder(29)).append("Invalid key count ").append(l).toString());
        }
        Object obj = new ImmutableMap.Builder();
        int j = 0;
        int i;
        int i1;
        for (i = 0; j < l; i += i1)
        {
            Object obj1 = objectinputstream.readObject();
            i1 = objectinputstream.readInt();
            if (i1 <= 0)
            {
                throw new InvalidObjectException((new StringBuilder(31)).append("Invalid value count ").append(i1).toString());
            }
            ImmutableList.Builder builder = ImmutableList.builder();
            for (int k = 0; k < i1; k++)
            {
                ImmutableList.Builder builder1 = (ImmutableList.Builder)builder.add(objectinputstream.readObject());
            }

            builder.forceCopy = true;
            ((ImmutableMap.Builder) (obj)).put(obj1, ImmutableList.asImmutableList(builder.contents, builder.size));
            j++;
        }

        try
        {
            objectinputstream = ((ImmutableMap.Builder) (obj)).build();
        }
        // Misplaced declaration of an exception variable
        catch (ObjectInputStream objectinputstream)
        {
            throw (InvalidObjectException)(new InvalidObjectException(objectinputstream.getMessage())).initCause(objectinputstream);
        }
        obj = ImmutableMultimap.FieldSettersHolder.MAP_FIELD_SETTER;
        try
        {
            ((Serialization.FieldSetter) (obj)).field.set(this, objectinputstream);
        }
        // Misplaced declaration of an exception variable
        catch (ObjectInputStream objectinputstream)
        {
            throw new AssertionError(objectinputstream);
        }
        objectinputstream = ImmutableMultimap.FieldSettersHolder.SIZE_FIELD_SETTER;
        try
        {
            ((Serialization.FieldSetter) (objectinputstream)).field.set(this, Integer.valueOf(i));
            return;
        }
        // Misplaced declaration of an exception variable
        catch (ObjectInputStream objectinputstream)
        {
            throw new AssertionError(objectinputstream);
        }
    }

    private void writeObject(ObjectOutputStream objectoutputstream)
        throws IOException
    {
        objectoutputstream.defaultWriteObject();
        Serialization.writeMultimap(this, objectoutputstream);
    }

    public final ImmutableCollection get(Object obj)
    {
        ImmutableList immutablelist = (ImmutableList)map.get(obj);
        obj = immutablelist;
        if (immutablelist == null)
        {
            obj = ImmutableList.of();
        }
        return (ImmutableList)obj;
    }

    public final Collection get(Object obj)
    {
        ImmutableList immutablelist = (ImmutableList)map.get(obj);
        obj = immutablelist;
        if (immutablelist == null)
        {
            obj = ImmutableList.of();
        }
        return (ImmutableList)obj;
    }

    public final List get(Object obj)
    {
        ImmutableList immutablelist = (ImmutableList)map.get(obj);
        obj = immutablelist;
        if (immutablelist == null)
        {
            obj = ImmutableList.of();
        }
        return ((List) (obj));
    }

    public final List removeAll(Object obj)
    {
        throw new UnsupportedOperationException();
    }
}
