// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.util;

import com.google.common.base.Strings;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.WeakHashMap;

// Referenced classes of package com.google.api.client.util:
//            Data, Value, NullValue, Key

public final class FieldInfo
{

    private static final Map CACHE = new WeakHashMap();
    public final Field field;
    public final boolean isPrimitive;
    public final String name;

    private FieldInfo(Field field1, String s)
    {
        field = field1;
        if (s == null)
        {
            field1 = null;
        } else
        {
            field1 = s.intern();
        }
        name = field1;
        isPrimitive = Data.isPrimitive(field.getType());
    }

    public static Object getFieldValue(Field field1, Object obj)
    {
        try
        {
            field1 = ((Field) (field1.get(obj)));
        }
        // Misplaced declaration of an exception variable
        catch (Field field1)
        {
            throw new IllegalArgumentException(field1);
        }
        return field1;
    }

    public static FieldInfo of(Enum enum)
    {
        FieldInfo fieldinfo;
        boolean flag = true;
        try
        {
            fieldinfo = of(enum.getClass().getField(enum.name()));
        }
        // Misplaced declaration of an exception variable
        catch (Enum enum)
        {
            throw new RuntimeException(enum);
        }
        if (fieldinfo == null)
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_61;
        }
        throw new IllegalArgumentException(Strings.lenientFormat("enum constant missing @Value or @NullValue annotation: %s", new Object[] {
            enum
        }));
        return fieldinfo;
    }

    public static FieldInfo of(Field field1)
    {
        if (field1 == null)
        {
            return null;
        }
        Map map = CACHE;
        map;
        JVM INSTR monitorenter ;
        FieldInfo fieldinfo;
        boolean flag;
        fieldinfo = (FieldInfo)CACHE.get(field1);
        flag = field1.isEnumConstant();
        Object obj = fieldinfo;
        if (fieldinfo != null) goto _L2; else goto _L1
_L1:
        if (flag) goto _L4; else goto _L3
_L3:
        obj = fieldinfo;
        if (Modifier.isStatic(field1.getModifiers())) goto _L2; else goto _L4
_L4:
        if (!flag) goto _L6; else goto _L5
_L5:
        obj = (Value)field1.getAnnotation(com/google/api/client/util/Value);
        if (obj == null) goto _L8; else goto _L7
_L7:
        obj = ((Value) (obj)).value();
_L9:
        if ("##default".equals(obj))
        {
            obj = field1.getName();
        }
        obj = new FieldInfo(field1, ((String) (obj)));
        CACHE.put(field1, obj);
_L2:
        map;
        JVM INSTR monitorexit ;
        return ((FieldInfo) (obj));
        field1;
        map;
        JVM INSTR monitorexit ;
        throw field1;
_L8:
        if ((NullValue)field1.getAnnotation(com/google/api/client/util/NullValue) == null)
        {
            break MISSING_BLOCK_LABEL_141;
        }
        obj = null;
          goto _L9
        map;
        JVM INSTR monitorexit ;
        return null;
_L6:
        obj = (Key)field1.getAnnotation(com/google/api/client/util/Key);
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_163;
        }
        map;
        JVM INSTR monitorexit ;
        return null;
        obj = ((Key) (obj)).value();
        field1.setAccessible(true);
          goto _L9
    }

    public static void setFieldValue(Field field1, Object obj, Object obj1)
    {
        if (Modifier.isFinal(field1.getModifiers()))
        {
            Object obj2 = getFieldValue(field1, obj);
            if (obj1 != null ? !obj1.equals(obj2) : obj2 != null)
            {
                obj2 = String.valueOf(obj2);
                obj1 = String.valueOf(obj1);
                field1 = field1.getName();
                obj = obj.getClass().getName();
                throw new IllegalArgumentException((new StringBuilder(String.valueOf(obj2).length() + 48 + String.valueOf(obj1).length() + String.valueOf(field1).length() + String.valueOf(obj).length())).append("expected final value <").append(((String) (obj2))).append("> but was <").append(((String) (obj1))).append("> on ").append(field1).append(" field in ").append(((String) (obj))).toString());
            } else
            {
                return;
            }
        }
        try
        {
            field1.set(obj, obj1);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Field field1)
        {
            throw new IllegalArgumentException(field1);
        }
        // Misplaced declaration of an exception variable
        catch (Field field1)
        {
            throw new IllegalArgumentException(field1);
        }
    }

}
