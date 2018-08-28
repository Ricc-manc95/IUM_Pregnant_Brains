// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.util;

import com.google.common.base.Strings;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class Types
{

    public static Type getActualParameterAtPosition(Type type, Class class1, int i)
    {
        class1 = getSuperParameterizedType(type, class1);
        if (class1 == null)
        {
            class1 = null;
        } else
        {
            Type type1 = class1.getActualTypeArguments()[i];
            class1 = type1;
            if (type1 instanceof TypeVariable)
            {
                type = resolveTypeVariable(Arrays.asList(new Type[] {
                    type
                }), (TypeVariable)type1);
                class1 = type1;
                if (type != null)
                {
                    return type;
                }
            }
        }
        return class1;
    }

    public static Class getRawArrayComponentType(List list, Type type)
    {
        if (type instanceof TypeVariable)
        {
            type = resolveTypeVariable(list, (TypeVariable)type);
        }
        if (type instanceof GenericArrayType)
        {
            if (type instanceof GenericArrayType)
            {
                type = ((GenericArrayType)type).getGenericComponentType();
            } else
            {
                type = ((Class)type).getComponentType();
            }
            return Array.newInstance(getRawArrayComponentType(list, type), 0).getClass();
        }
        if (type instanceof Class)
        {
            return (Class)type;
        }
        if (type instanceof ParameterizedType)
        {
            return (Class)((ParameterizedType)type).getRawType();
        }
        boolean flag;
        if (type == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(Strings.lenientFormat("wildcard type is not supported: %s", new Object[] {
                type
            }));
        } else
        {
            return java/lang/Object;
        }
    }

    private static ParameterizedType getSuperParameterizedType(Type type, Class class1)
    {
        if (!(type instanceof Class) && !(type instanceof ParameterizedType)) goto _L2; else goto _L1
_L1:
        if (type == null || type == java/lang/Object) goto _L2; else goto _L3
_L3:
        if (!(type instanceof Class)) goto _L5; else goto _L4
_L4:
        type = (Class)type;
_L6:
        type = type.getGenericSuperclass();
        continue; /* Loop/switch isn't completed */
_L5:
        type = (ParameterizedType)type;
        Class class2 = (Class)type.getRawType();
        if (class2 == class1)
        {
            return type;
        }
        type = class2;
        if (class1.isInterface())
        {
            Type atype[] = class2.getGenericInterfaces();
            int j = atype.length;
            int i = 0;
            do
            {
                type = class2;
                if (i >= j)
                {
                    break;
                }
                Type type1 = atype[i];
                if (type1 instanceof Class)
                {
                    type = (Class)type1;
                } else
                {
                    type = (Class)((ParameterizedType)type1).getRawType();
                }
                if (class1.isAssignableFrom(type))
                {
                    type = type1;
                    continue; /* Loop/switch isn't completed */
                }
                i++;
            } while (true);
        }
        if (true) goto _L6; else goto _L2
_L2:
        return null;
        if (true) goto _L1; else goto _L7
_L7:
    }

    private static IllegalArgumentException handleExceptionForNewInstance(Exception exception, Class class1)
    {
        StringBuilder stringbuilder = (new StringBuilder("unable to create new instance of class ")).append(class1.getName());
        Object obj = new ArrayList();
        boolean flag;
        int i;
        int j;
        if (class1.isArray())
        {
            ((ArrayList) (obj)).add("because it is an array");
        } else
        if (class1.isPrimitive())
        {
            ((ArrayList) (obj)).add("because it is primitive");
        } else
        if (class1 == java/lang/Void)
        {
            ((ArrayList) (obj)).add("because it is void");
        } else
        {
            if (Modifier.isInterface(class1.getModifiers()))
            {
                ((ArrayList) (obj)).add("because it is an interface");
            } else
            if (Modifier.isAbstract(class1.getModifiers()))
            {
                ((ArrayList) (obj)).add("because it is abstract");
            }
            if (class1.getEnclosingClass() != null && !Modifier.isStatic(class1.getModifiers()))
            {
                ((ArrayList) (obj)).add("because it is not static");
            }
            if (!Modifier.isPublic(class1.getModifiers()))
            {
                ((ArrayList) (obj)).add("possibly because it is not public");
            } else
            {
                try
                {
                    class1.getConstructor(new Class[0]);
                }
                // Misplaced declaration of an exception variable
                catch (Class class1)
                {
                    ((ArrayList) (obj)).add("because it has no accessible default constructor");
                }
            }
        }
        class1 = (ArrayList)obj;
        j = class1.size();
        i = 0;
        flag = false;
        while (i < j) 
        {
            obj = class1.get(i);
            i++;
            obj = (String)obj;
            if (flag)
            {
                stringbuilder.append(" and");
            } else
            {
                flag = true;
            }
            stringbuilder.append(" ").append(((String) (obj)));
        }
        return new IllegalArgumentException(stringbuilder.toString(), exception);
    }

    public static Iterable iterableOf(final Object value)
    {
        if (value instanceof Iterable)
        {
            return (Iterable)value;
        }
        Class class1 = value.getClass();
        if (!class1.isArray())
        {
            throw new IllegalArgumentException(Strings.lenientFormat("not an array or Iterable: %s", new Object[] {
                class1
            }));
        }
        if (!class1.getComponentType().isPrimitive())
        {
            return Arrays.asList((Object[])value);
        } else
        {
            return new _cls1();
        }
    }

    public static Object newInstance(Class class1)
    {
        Object obj;
        try
        {
            obj = class1.newInstance();
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw handleExceptionForNewInstance(illegalaccessexception, class1);
        }
        catch (InstantiationException instantiationexception)
        {
            throw handleExceptionForNewInstance(instantiationexception, class1);
        }
        return obj;
    }

    public static Type resolveTypeVariable(List list, TypeVariable typevariable)
    {
        GenericDeclaration genericdeclaration = typevariable.getGenericDeclaration();
        if (genericdeclaration instanceof Class)
        {
            Class class1 = (Class)genericdeclaration;
            int i = list.size();
            Object obj = null;
            do
            {
                if (obj != null)
                {
                    break;
                }
                i--;
                if (i < 0)
                {
                    break;
                }
                obj = getSuperParameterizedType((Type)list.get(i), class1);
            } while (true);
            if (obj != null)
            {
                TypeVariable atypevariable[] = genericdeclaration.getTypeParameters();
                int j;
                for (j = 0; j < atypevariable.length && !atypevariable[j].equals(typevariable); j++) { }
                obj = ((ParameterizedType) (obj)).getActualTypeArguments()[j];
                typevariable = ((TypeVariable) (obj));
                if (obj instanceof TypeVariable)
                {
                    list = resolveTypeVariable(list, (TypeVariable)obj);
                    typevariable = ((TypeVariable) (obj));
                    if (list != null)
                    {
                        typevariable = list;
                    }
                }
                return typevariable;
            }
        }
        return null;
    }

    public static Object toArray(Collection collection, Class class1)
    {
        if (class1.isPrimitive())
        {
            class1 = ((Class) (Array.newInstance(class1, collection.size())));
            int i = 0;
            for (collection = collection.iterator(); collection.hasNext();)
            {
                Array.set(class1, i, collection.next());
                i++;
            }

            return class1;
        } else
        {
            return ((Object) (collection.toArray((Object[])Array.newInstance(class1, collection.size()))));
        }
    }

    private class _cls1
        implements Iterable
    {

        public final Object val$value;

        public final Iterator iterator()
        {
            class _cls1
                implements Iterator
            {

                private int index;
                private final int length;
                private final _cls1 this$0;

                public final boolean hasNext()
                {
                    return index < length;
                }

                public final Object next()
                {
                    if (!hasNext())
                    {
                        throw new NoSuchElementException();
                    } else
                    {
                        Object obj = value;
                        int i = index;
                        index = i + 1;
                        return Array.get(obj, i);
                    }
                }

                public final void remove()
                {
                    throw new UnsupportedOperationException();
                }

                _cls1()
                {
                    this$0 = _cls1.this;
                    super();
                    length = Array.getLength(value);
                    index = 0;
                }
            }

            return new _cls1();
        }

        _cls1()
        {
            value = obj;
            super();
        }
    }

}
