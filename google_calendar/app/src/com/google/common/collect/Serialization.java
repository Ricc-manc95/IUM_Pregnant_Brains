// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.common.collect:
//            Multimap

final class Serialization
{

    static FieldSetter getFieldSetter(Class class1, String s)
    {
        try
        {
            class1 = new FieldSetter(class1.getDeclaredField(s));
        }
        // Misplaced declaration of an exception variable
        catch (Class class1)
        {
            throw new AssertionError(class1);
        }
        return class1;
    }

    static void populateMultimap(Multimap multimap, ObjectInputStream objectinputstream, int i)
        throws IOException, ClassNotFoundException
    {
        for (int j = 0; j < i; j++)
        {
            Collection collection = multimap.get(objectinputstream.readObject());
            int l = objectinputstream.readInt();
            for (int k = 0; k < l; k++)
            {
                collection.add(objectinputstream.readObject());
            }

        }

    }

    static void writeMultimap(Multimap multimap, ObjectOutputStream objectoutputstream)
        throws IOException
    {
        objectoutputstream.writeInt(multimap.asMap().size());
        for (multimap = multimap.asMap().entrySet().iterator(); multimap.hasNext();)
        {
            Object obj = (java.util.Map.Entry)multimap.next();
            objectoutputstream.writeObject(((java.util.Map.Entry) (obj)).getKey());
            objectoutputstream.writeInt(((Collection)((java.util.Map.Entry) (obj)).getValue()).size());
            obj = ((Collection)((java.util.Map.Entry) (obj)).getValue()).iterator();
            while (((Iterator) (obj)).hasNext()) 
            {
                objectoutputstream.writeObject(((Iterator) (obj)).next());
            }
        }

    }

    private class FieldSetter
    {

        public final Field field;

        FieldSetter(Field field1)
        {
            field = field1;
            field1.setAccessible(true);
        }
    }

}
