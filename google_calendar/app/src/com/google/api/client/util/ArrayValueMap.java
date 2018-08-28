// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.api.client.util:
//            ArrayMap, Types, FieldInfo

public final class ArrayValueMap
{

    private final Object destination;
    private final Map fieldMap = new ArrayMap();
    private final Map keyMap = new ArrayMap();

    public ArrayValueMap(Object obj)
    {
        destination = obj;
    }

    public final void put(Field field, Class class1, Object obj)
    {
        ArrayValue arrayvalue1 = (ArrayValue)fieldMap.get(field);
        ArrayValue arrayvalue = arrayvalue1;
        if (arrayvalue1 == null)
        {
            arrayvalue = new ArrayValue(class1);
            fieldMap.put(field, arrayvalue);
        }
        boolean flag;
        if (class1 == arrayvalue.componentType)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            arrayvalue.values.add(obj);
            return;
        }
    }

    public final void setValues()
    {
        Map map;
        String s;
        Object obj1;
        for (Iterator iterator = keyMap.entrySet().iterator(); iterator.hasNext(); map.put(s, Types.toArray(((ArrayValue) (obj1)).values, ((ArrayValue) (obj1)).componentType)))
        {
            obj1 = (java.util.Map.Entry)iterator.next();
            map = (Map)destination;
            s = (String)((java.util.Map.Entry) (obj1)).getKey();
            obj1 = (ArrayValue)((java.util.Map.Entry) (obj1)).getValue();
        }

        Field field;
        Object obj;
        Object obj2;
        for (Iterator iterator1 = fieldMap.entrySet().iterator(); iterator1.hasNext(); FieldInfo.setFieldValue(field, obj, Types.toArray(((ArrayValue) (obj2)).values, ((ArrayValue) (obj2)).componentType)))
        {
            obj2 = (java.util.Map.Entry)iterator1.next();
            field = (Field)((java.util.Map.Entry) (obj2)).getKey();
            obj = destination;
            obj2 = (ArrayValue)((java.util.Map.Entry) (obj2)).getValue();
        }

    }

    private class ArrayValue
    {

        public final Class componentType;
        public final ArrayList values = new ArrayList();

        ArrayValue(Class class1)
        {
            componentType = class1;
        }
    }

}
