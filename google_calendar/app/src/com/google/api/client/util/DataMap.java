// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.util;

import java.util.AbstractMap;
import java.util.Set;

// Referenced classes of package com.google.api.client.util:
//            ClassInfo, FieldInfo

final class DataMap extends AbstractMap
{

    public final ClassInfo classInfo;
    public final Object object;

    DataMap(Object obj, boolean flag)
    {
        object = obj;
        classInfo = ClassInfo.of(obj.getClass(), flag);
        boolean flag1;
        if (!classInfo.clazz.isEnum())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            throw new IllegalArgumentException();
        } else
        {
            return;
        }
    }

    public final boolean containsKey(Object obj)
    {
        return get(obj) != null;
    }

    public final Set entrySet()
    {
        return new EntrySet();
    }

    public final Object get(Object obj)
    {
        if (obj instanceof String)
        {
            if ((obj = classInfo.getFieldInfo((String)obj)) != null)
            {
                Object obj1 = object;
                return FieldInfo.getFieldValue(((FieldInfo) (obj)).field, obj1);
            }
        }
        return null;
    }

    public final Object put(Object obj, Object obj1)
    {
        obj = (String)obj;
        FieldInfo fieldinfo = classInfo.getFieldInfo(((String) (obj)));
        obj = String.valueOf(obj);
        if (((String) (obj)).length() != 0)
        {
            obj = "no field of key ".concat(((String) (obj)));
        } else
        {
            obj = new String("no field of key ");
        }
        if (fieldinfo == null)
        {
            throw new NullPointerException(String.valueOf(obj));
        }
        obj = object;
        obj = FieldInfo.getFieldValue(fieldinfo.field, obj);
        Object obj2 = object;
        if (obj1 == null)
        {
            throw new NullPointerException();
        } else
        {
            FieldInfo.setFieldValue(fieldinfo.field, obj2, obj1);
            return obj;
        }
    }

    private class EntrySet extends AbstractSet
    {

        private final DataMap this$0;

        public final void clear()
        {
            Object obj;
            Object obj1;
            for (Iterator iterator1 = classInfo.names.iterator(); iterator1.hasNext(); FieldInfo.setFieldValue(((FieldInfo) (obj)).field, obj1, null))
            {
                obj = (String)iterator1.next();
                obj = classInfo.getFieldInfo(((String) (obj)));
                obj1 = object;
            }

        }

        public final boolean isEmpty()
        {
            for (Iterator iterator1 = classInfo.names.iterator(); iterator1.hasNext();)
            {
                Object obj = (String)iterator1.next();
                obj = classInfo.getFieldInfo(((String) (obj)));
                Object obj1 = object;
                if (FieldInfo.getFieldValue(((FieldInfo) (obj)).field, obj1) != null)
                {
                    return false;
                }
            }

            return true;
        }

        public final Iterator iterator()
        {
            return new EntryIterator();
        }

        public final int size()
        {
            Iterator iterator1 = classInfo.names.iterator();
            int i = 0;
            do
            {
                if (!iterator1.hasNext())
                {
                    break;
                }
                Object obj = (String)iterator1.next();
                obj = classInfo.getFieldInfo(((String) (obj)));
                Object obj1 = object;
                if (FieldInfo.getFieldValue(((FieldInfo) (obj)).field, obj1) != null)
                {
                    i++;
                }
            } while (true);
            return i;
        }

        EntrySet()
        {
            this$0 = DataMap.this;
            super();
        }

        private class EntryIterator
            implements Iterator
        {

            private FieldInfo currentFieldInfo;
            private boolean isComputed;
            private boolean isRemoved;
            private FieldInfo nextFieldInfo;
            private Object nextFieldValue;
            private int nextKeyIndex;
            private final DataMap this$0;

            public final boolean hasNext()
            {
                if (!isComputed)
                {
                    isComputed = true;
                    nextFieldValue = null;
                    do
                    {
                        if (nextFieldValue != null)
                        {
                            break;
                        }
                        int i = nextKeyIndex + 1;
                        nextKeyIndex = i;
                        if (i >= classInfo.names.size())
                        {
                            break;
                        }
                        nextFieldInfo = classInfo.getFieldInfo((String)classInfo.names.get(nextKeyIndex));
                        FieldInfo fieldinfo = nextFieldInfo;
                        Object obj = object;
                        nextFieldValue = FieldInfo.getFieldValue(fieldinfo.field, obj);
                    } while (true);
                }
                return nextFieldValue != null;
            }

            public final Object next()
            {
                if (!hasNext())
                {
                    throw new NoSuchElementException();
                } else
                {
                    currentFieldInfo = nextFieldInfo;
                    Object obj = nextFieldValue;
                    isComputed = false;
                    isRemoved = false;
                    nextFieldInfo = null;
                    nextFieldValue = null;
                    return new DataMap.Entry(currentFieldInfo, obj);
                }
            }

            public final void remove()
            {
                boolean flag;
                if (currentFieldInfo != null && !isRemoved)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    throw new IllegalStateException();
                } else
                {
                    isRemoved = true;
                    FieldInfo fieldinfo = currentFieldInfo;
                    Object obj = object;
                    FieldInfo.setFieldValue(fieldinfo.field, obj, null);
                    return;
                }
            }

            EntryIterator()
            {
                this$0 = DataMap.this;
                super();
                nextKeyIndex = -1;
            }

            private class Entry
                implements java.util.Map.Entry
            {

                private final FieldInfo fieldInfo;
                private Object fieldValue;
                private final DataMap this$0;

                public final boolean equals(Object obj)
                {
                    if (this == obj)
                    {
                        return true;
                    }
                    if (!(obj instanceof java.util.Map.Entry))
                    {
                        return false;
                    }
                    obj = (java.util.Map.Entry)obj;
                    return ((String)getKey()).equals(((java.util.Map.Entry) (obj)).getKey()) && getValue().equals(((java.util.Map.Entry) (obj)).getValue());
                }

                public final Object getKey()
                {
                    String s1 = fieldInfo.name;
                    String s = s1;
                    if (classInfo.ignoreCase)
                    {
                        s = s1.toLowerCase();
                    }
                    return s;
                }

                public final Object getValue()
                {
                    return fieldValue;
                }

                public final int hashCode()
                {
                    return ((String)getKey()).hashCode() ^ getValue().hashCode();
                }

                public final Object setValue(Object obj)
                {
                    Object obj1 = fieldValue;
                    if (obj == null)
                    {
                        throw new NullPointerException();
                    } else
                    {
                        fieldValue = obj;
                        FieldInfo fieldinfo = fieldInfo;
                        Object obj2 = object;
                        FieldInfo.setFieldValue(fieldinfo.field, obj2, obj);
                        return obj1;
                    }
                }

                Entry(FieldInfo fieldinfo, Object obj)
                {
                    this$0 = DataMap.this;
                    super();
                    fieldInfo = fieldinfo;
                    if (obj == null)
                    {
                        throw new NullPointerException();
                    } else
                    {
                        fieldValue = obj;
                        return;
                    }
                }
            }

        }

    }

}
