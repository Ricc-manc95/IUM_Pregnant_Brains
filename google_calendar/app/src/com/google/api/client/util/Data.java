// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.util;

import com.google.common.base.Strings;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package com.google.api.client.util:
//            DateTime, GenericData, ArrayMap, Types, 
//            ClassInfo, FieldInfo, DataMap

public final class Data
{

    private static final BigDecimal NULL_BIG_DECIMAL;
    private static final BigInteger NULL_BIG_INTEGER;
    public static final Boolean NULL_BOOLEAN;
    private static final Byte NULL_BYTE;
    private static final ConcurrentHashMap NULL_CACHE;
    private static final Character NULL_CHARACTER;
    public static final DateTime NULL_DATE_TIME;
    private static final Double NULL_DOUBLE;
    private static final Float NULL_FLOAT;
    private static final Integer NULL_INTEGER;
    public static final Long NULL_LONG;
    private static final Short NULL_SHORT;
    public static final String NULL_STRING = new String();

    public static Object clone(Object obj)
    {
        if (obj == null || isPrimitive(obj.getClass()))
        {
            return obj;
        }
        if (obj instanceof GenericData)
        {
            return (GenericData)((GenericData)obj).clone();
        }
        Object obj1 = obj.getClass();
        if (((Class) (obj1)).isArray())
        {
            obj1 = Array.newInstance(((Class) (obj1)).getComponentType(), Array.getLength(obj));
        } else
        if (obj instanceof ArrayMap)
        {
            obj1 = (ArrayMap)((ArrayMap)obj).clone();
        } else
        {
            if ("java.util.Arrays$ArrayList".equals(((Class) (obj1)).getName()))
            {
                obj = ((Object) (((List)obj).toArray()));
                deepCopy(obj, obj);
                return Arrays.asList(((Object []) (obj)));
            }
            obj1 = Types.newInstance(((Class) (obj1)));
        }
        deepCopy(obj, obj1);
        return obj1;
    }

    public static void deepCopy(Object obj, Object obj1)
    {
        boolean flag4 = true;
        boolean flag3 = false;
        boolean flag2 = false;
        Object obj2 = obj.getClass();
        boolean flag;
        if (obj2 == obj1.getClass())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        if (((Class) (obj2)).isArray())
        {
            boolean flag1;
            if (Array.getLength(obj) == Array.getLength(obj1))
            {
                flag1 = flag4;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                throw new IllegalArgumentException();
            }
            obj = Types.iterableOf(obj).iterator();
            for (int i = ((flag2) ? 1 : 0); ((Iterator) (obj)).hasNext(); i++)
            {
                Array.set(obj1, i, clone(((Iterator) (obj)).next()));
            }

        } else
        if (java/util/Collection.isAssignableFrom(((Class) (obj2))))
        {
            obj = (Collection)obj;
            if (java/util/ArrayList.isAssignableFrom(((Class) (obj2))))
            {
                ((ArrayList)obj1).ensureCapacity(((Collection) (obj)).size());
            }
            obj1 = (Collection)obj1;
            for (obj = ((Collection) (obj)).iterator(); ((Iterator) (obj)).hasNext(); ((Collection) (obj1)).add(clone(((Iterator) (obj)).next()))) { }
        } else
        {
            boolean flag5 = com/google/api/client/util/GenericData.isAssignableFrom(((Class) (obj2)));
            if (flag5 || !java/util/Map.isAssignableFrom(((Class) (obj2))))
            {
                Iterator iterator;
                if (flag5)
                {
                    obj2 = ((GenericData)obj).classInfo;
                } else
                {
                    obj2 = ClassInfo.of(((Class) (obj2)));
                }
                iterator = ((ClassInfo) (obj2)).names.iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break;
                    }
                    FieldInfo fieldinfo = ((ClassInfo) (obj2)).getFieldInfo((String)iterator.next());
                    if (!Modifier.isFinal(fieldinfo.field.getModifiers()) && (!flag5 || !fieldinfo.isPrimitive))
                    {
                        Object obj3 = FieldInfo.getFieldValue(fieldinfo.field, obj);
                        if (obj3 != null)
                        {
                            obj3 = clone(obj3);
                            FieldInfo.setFieldValue(fieldinfo.field, obj1, obj3);
                        }
                    }
                } while (true);
            } else
            if (com/google/api/client/util/ArrayMap.isAssignableFrom(((Class) (obj2))))
            {
                obj1 = (ArrayMap)obj1;
                obj = (ArrayMap)obj;
                int k = ((ArrayMap) (obj)).size();
                for (int j = ((flag3) ? 1 : 0); j < k; j++)
                {
                    ((ArrayMap) (obj1)).set(j, clone(((ArrayMap) (obj)).getValue(j)));
                }

            } else
            {
                obj1 = (Map)obj1;
                java.util.Map.Entry entry;
                for (obj = ((Map)obj).entrySet().iterator(); ((Iterator) (obj)).hasNext(); ((Map) (obj1)).put((String)entry.getKey(), clone(entry.getValue())))
                {
                    entry = (java.util.Map.Entry)((Iterator) (obj)).next();
                }

            }
        }
    }

    public static boolean isNull(Object obj)
    {
        return obj != null && obj == NULL_CACHE.get(obj.getClass());
    }

    public static boolean isPrimitive(Type type)
    {
        if (type instanceof WildcardType)
        {
            type = (WildcardType)type;
            Type atype[] = type.getLowerBounds();
            if (atype.length != 0)
            {
                type = atype[0];
            } else
            {
                type = type.getUpperBounds()[0];
            }
        }
        if (!(type instanceof Class))
        {
            return false;
        }
        type = (Class)type;
        return type.isPrimitive() || type == java/lang/Character || type == java/lang/String || type == java/lang/Integer || type == java/lang/Long || type == java/lang/Short || type == java/lang/Byte || type == java/lang/Float || type == java/lang/Double || type == java/math/BigInteger || type == java/math/BigDecimal || type == com/google/api/client/util/DateTime || type == java/lang/Boolean;
    }

    public static boolean isValueOfPrimitiveType(Object obj)
    {
        return obj == null || isPrimitive(obj.getClass());
    }

    public static Map mapOf(Object obj)
    {
label0:
        {
            if (obj != null)
            {
                boolean flag;
                if (obj != null && obj == NULL_CACHE.get(obj.getClass()))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    break label0;
                }
            }
            return Collections.emptyMap();
        }
        if (obj instanceof Map)
        {
            return (Map)obj;
        } else
        {
            return new DataMap(obj, false);
        }
    }

    public static Collection newCollectionInstance(Type type)
    {
        Class class1;
        if (type instanceof WildcardType)
        {
            type = (WildcardType)type;
            Type atype[] = type.getLowerBounds();
            if (atype.length != 0)
            {
                type = atype[0];
            } else
            {
                type = type.getUpperBounds()[0];
            }
        }
        if (type instanceof ParameterizedType)
        {
            type = ((ParameterizedType)type).getRawType();
        }
        if (type instanceof Class)
        {
            class1 = (Class)type;
        } else
        {
            class1 = null;
        }
        if (type == null || (type instanceof GenericArrayType) || class1 != null && (class1.isArray() || class1.isAssignableFrom(java/util/ArrayList)))
        {
            return new ArrayList();
        }
        if (class1 == null)
        {
            type = String.valueOf(type);
            throw new IllegalArgumentException((new StringBuilder(String.valueOf(type).length() + 39)).append("unable to create new instance of type: ").append(type).toString());
        }
        if (class1.isAssignableFrom(java/util/HashSet))
        {
            return new HashSet();
        }
        if (class1.isAssignableFrom(java/util/TreeSet))
        {
            return new TreeSet();
        } else
        {
            return (Collection)Types.newInstance(class1);
        }
    }

    public static Map newMapInstance(Class class1)
    {
        if (class1 == null || class1.isAssignableFrom(com/google/api/client/util/ArrayMap))
        {
            return new ArrayMap();
        }
        if (class1.isAssignableFrom(java/util/TreeMap))
        {
            return new TreeMap();
        } else
        {
            return (Map)Types.newInstance(class1);
        }
    }

    public static Object nullOf(Class class1)
    {
        Object obj;
        int i;
        i = 0;
        obj = NULL_CACHE.get(class1);
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_166;
        }
        ConcurrentHashMap concurrenthashmap = NULL_CACHE;
        concurrenthashmap;
        JVM INSTR monitorenter ;
        Object obj1 = NULL_CACHE.get(class1);
        obj = obj1;
        if (obj1 != null) goto _L2; else goto _L1
_L1:
        if (!class1.isArray()) goto _L4; else goto _L3
_L3:
        obj = class1;
_L6:
        obj1 = ((Class) (obj)).getComponentType();
        int j;
        j = i + 1;
        obj = obj1;
        i = j;
        if (((Class) (obj1)).isArray()) goto _L6; else goto _L5
_L5:
        obj = Array.newInstance(((Class) (obj1)), new int[j]);
_L7:
        NULL_CACHE.put(class1, obj);
_L2:
        concurrenthashmap;
        JVM INSTR monitorexit ;
        return obj;
_L4:
        if (!class1.isEnum())
        {
            break MISSING_BLOCK_LABEL_158;
        }
        obj = ClassInfo.of(class1).getFieldInfo(null);
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_137;
        }
        throw new NullPointerException(Strings.lenientFormat("enum missing constant with @NullValue annotation: %s", new Object[] {
            class1
        }));
        class1;
        concurrenthashmap;
        JVM INSTR monitorexit ;
        throw class1;
        obj = Enum.valueOf(((FieldInfo) (obj)).field.getDeclaringClass(), ((FieldInfo) (obj)).field.getName());
          goto _L7
        obj = Types.newInstance(class1);
          goto _L7
        return obj;
    }

    public static Object parsePrimitiveValue(Type type, String s)
    {
        Class class1;
        String s1;
        if (type instanceof Class)
        {
            class1 = (Class)type;
        } else
        {
            class1 = null;
        }
        if (type != null && class1 == null)
        {
            break MISSING_BLOCK_LABEL_333;
        }
        if (class1 != java/lang/Void) goto _L2; else goto _L1
_L1:
        s1 = null;
_L4:
        return s1;
_L2:
        s1 = s;
        if (s == null) goto _L4; else goto _L3
_L3:
        s1 = s;
        if (class1 == null) goto _L4; else goto _L5
_L5:
        s1 = s;
        if (class1.isAssignableFrom(java/lang/String)) goto _L4; else goto _L6
_L6:
        if (class1 == java/lang/Character || class1 == Character.TYPE)
        {
            if (s.length() != 1)
            {
                type = String.valueOf(class1);
                throw new IllegalArgumentException((new StringBuilder(String.valueOf(type).length() + 37)).append("expected type Character/char but got ").append(type).toString());
            } else
            {
                return Character.valueOf(s.charAt(0));
            }
        }
        if (class1 == java/lang/Boolean || class1 == Boolean.TYPE)
        {
            return Boolean.valueOf(s);
        }
        if (class1 == java/lang/Byte || class1 == Byte.TYPE)
        {
            return Byte.valueOf(s);
        }
        if (class1 == java/lang/Short || class1 == Short.TYPE)
        {
            return Short.valueOf(s);
        }
        if (class1 == java/lang/Integer || class1 == Integer.TYPE)
        {
            return Integer.valueOf(s);
        }
        if (class1 == java/lang/Long || class1 == Long.TYPE)
        {
            return Long.valueOf(s);
        }
        if (class1 == java/lang/Float || class1 == Float.TYPE)
        {
            return Float.valueOf(s);
        }
        if (class1 == java/lang/Double || class1 == Double.TYPE)
        {
            return Double.valueOf(s);
        }
        if (class1 == com/google/api/client/util/DateTime)
        {
            return DateTime.parseRfc3339(s);
        }
        if (class1 == java/math/BigInteger)
        {
            return new BigInteger(s);
        }
        if (class1 == java/math/BigDecimal)
        {
            return new BigDecimal(s);
        }
        if (class1.isEnum())
        {
            type = ClassInfo.of(class1).getFieldInfo(s);
            return Enum.valueOf(((FieldInfo) (type)).field.getDeclaringClass(), ((FieldInfo) (type)).field.getName());
        }
        type = String.valueOf(type);
        throw new IllegalArgumentException((new StringBuilder(String.valueOf(type).length() + 35)).append("expected primitive class, but got: ").append(type).toString());
    }

    public static Type resolveWildcardTypeOrTypeVariable(List list, Type type)
    {
        if (type instanceof WildcardType)
        {
            type = (WildcardType)type;
            Type atype[] = type.getLowerBounds();
            Type type1;
            if (atype.length != 0)
            {
                type = atype[0];
            } else
            {
                type = type.getUpperBounds()[0];
            }
        }
        do
        {
            if (!(type instanceof TypeVariable))
            {
                break;
            }
            type1 = Types.resolveTypeVariable(list, (TypeVariable)type);
            if (type1 != null)
            {
                type = type1;
            }
            if (type instanceof TypeVariable)
            {
                type = ((TypeVariable)type).getBounds()[0];
            }
        } while (true);
        return type;
    }

    static 
    {
        NULL_BOOLEAN = new Boolean(true);
        NULL_CHARACTER = new Character('\0');
        NULL_BYTE = new Byte((byte)0);
        NULL_SHORT = new Short((short)0);
        NULL_INTEGER = new Integer(0);
        NULL_FLOAT = new Float(0.0F);
        NULL_LONG = new Long(0L);
        NULL_DOUBLE = new Double(0.0D);
        NULL_BIG_INTEGER = new BigInteger("0");
        NULL_BIG_DECIMAL = new BigDecimal("0");
        NULL_DATE_TIME = new DateTime(0L);
        ConcurrentHashMap concurrenthashmap = new ConcurrentHashMap();
        NULL_CACHE = concurrenthashmap;
        concurrenthashmap.put(java/lang/Boolean, NULL_BOOLEAN);
        NULL_CACHE.put(java/lang/String, NULL_STRING);
        NULL_CACHE.put(java/lang/Character, NULL_CHARACTER);
        NULL_CACHE.put(java/lang/Byte, NULL_BYTE);
        NULL_CACHE.put(java/lang/Short, NULL_SHORT);
        NULL_CACHE.put(java/lang/Integer, NULL_INTEGER);
        NULL_CACHE.put(java/lang/Float, NULL_FLOAT);
        NULL_CACHE.put(java/lang/Long, NULL_LONG);
        NULL_CACHE.put(java/lang/Double, NULL_DOUBLE);
        NULL_CACHE.put(java/math/BigInteger, NULL_BIG_INTEGER);
        NULL_CACHE.put(java/math/BigDecimal, NULL_BIG_DECIMAL);
        NULL_CACHE.put(com/google/api/client/util/DateTime, NULL_DATE_TIME);
    }
}
