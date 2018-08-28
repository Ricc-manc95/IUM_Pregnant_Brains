// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.util;

import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.WeakHashMap;

// Referenced classes of package com.google.api.client.util:
//            FieldInfo

public final class ClassInfo
{

    private static final Map CACHE = new WeakHashMap();
    private static final Map CACHE_IGNORE_CASE = new WeakHashMap();
    public final Class clazz;
    public final boolean ignoreCase;
    public final IdentityHashMap nameToFieldInfoMap = new IdentityHashMap();
    public final List names;

    private ClassInfo(Class class1, boolean flag)
    {
        clazz = class1;
        ignoreCase = flag;
        String s;
        boolean flag1;
        if (!flag || !class1.isEnum())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        s = String.valueOf(class1);
        s = (new StringBuilder(String.valueOf(s).length() + 31)).append("cannot ignore case on an enum: ").append(s).toString();
        if (!flag1)
        {
            throw new IllegalArgumentException(String.valueOf(s));
        }
        TreeSet treeset = new TreeSet(new _cls1());
        java.lang.reflect.Field afield[] = class1.getDeclaredFields();
        int j = afield.length;
        for (int i = 0; i < j; i++)
        {
            java.lang.reflect.Field field = afield[i];
            FieldInfo fieldinfo = FieldInfo.of(field);
            if (fieldinfo == null)
            {
                continue;
            }
            String s1 = fieldinfo.name;
            if (flag)
            {
                s1 = s1.toLowerCase().intern();
            }
            Object obj = (FieldInfo)nameToFieldInfoMap.get(s1);
            String s2;
            boolean flag2;
            if (obj == null)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            if (flag)
            {
                s2 = "case-insensitive ";
            } else
            {
                s2 = "";
            }
            if (obj == null)
            {
                obj = null;
            } else
            {
                obj = ((FieldInfo) (obj)).field;
            }
            if (!flag2)
            {
                throw new IllegalArgumentException(Strings.lenientFormat("two fields have the same %sname <%s>: %s and %s", new Object[] {
                    s2, s1, field, obj
                }));
            }
            nameToFieldInfoMap.put(s1, fieldinfo);
            treeset.add(s1);
        }

        class1 = class1.getSuperclass();
        if (class1 != null)
        {
            class1 = of(class1, flag);
            treeset.addAll(((ClassInfo) (class1)).names);
            class1 = ((ClassInfo) (class1)).nameToFieldInfoMap.entrySet().iterator();
            do
            {
                if (!class1.hasNext())
                {
                    break;
                }
                java.util.Map.Entry entry = (java.util.Map.Entry)class1.next();
                String s3 = (String)entry.getKey();
                if (!nameToFieldInfoMap.containsKey(s3))
                {
                    nameToFieldInfoMap.put(s3, (FieldInfo)entry.getValue());
                }
            } while (true);
        }
        if (treeset.isEmpty())
        {
            class1 = Collections.emptyList();
        } else
        {
            class1 = Collections.unmodifiableList(new ArrayList(treeset));
        }
        names = class1;
    }

    public static ClassInfo of(Class class1)
    {
        return of(class1, false);
    }

    public static ClassInfo of(Class class1, boolean flag)
    {
        if (class1 == null)
        {
            return null;
        }
        Map map;
        ClassInfo classinfo;
        ClassInfo classinfo1;
        if (flag)
        {
            map = CACHE_IGNORE_CASE;
        } else
        {
            map = CACHE;
        }
        map;
        JVM INSTR monitorenter ;
        classinfo1 = (ClassInfo)map.get(class1);
        classinfo = classinfo1;
        if (classinfo1 != null)
        {
            break MISSING_BLOCK_LABEL_55;
        }
        classinfo = new ClassInfo(class1, flag);
        map.put(class1, classinfo);
        map;
        JVM INSTR monitorexit ;
        return classinfo;
        class1;
        map;
        JVM INSTR monitorexit ;
        throw class1;
    }

    public final FieldInfo getFieldInfo(String s)
    {
        String s1 = s;
        if (s != null)
        {
            s1 = s;
            if (ignoreCase)
            {
                s1 = s.toLowerCase();
            }
            s1 = s1.intern();
        }
        return (FieldInfo)nameToFieldInfoMap.get(s1);
    }


    private class _cls1
        implements Comparator
    {

        public final int compare(Object obj, Object obj1)
        {
            obj = (String)obj;
            obj1 = (String)obj1;
            if (obj == obj1)
            {
                return 0;
            }
            if (obj == null)
            {
                return -1;
            }
            if (obj1 == null)
            {
                return 1;
            } else
            {
                return ((String) (obj)).compareTo(((String) (obj1)));
            }
        }

        _cls1()
        {
        }
    }

}
