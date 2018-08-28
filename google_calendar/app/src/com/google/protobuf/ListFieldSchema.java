// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.List;

abstract class ListFieldSchema
{

    public static final ListFieldSchema FULL_INSTANCE = new ListFieldSchemaFull();
    public static final ListFieldSchema LITE_INSTANCE = new ListFieldSchemaLite();

    ListFieldSchema()
    {
    }

    abstract void makeImmutableListAt(Object obj, long l);

    abstract void mergeListsAt(Object obj, Object obj1, long l);

    abstract List mutableListAt(Object obj, long l);


    private class ListFieldSchemaFull extends ListFieldSchema
    {

        private static final Class UNMODIFIABLE_LIST_CLASS = Collections.unmodifiableList(Collections.emptyList()).getClass();

        private static List mutableListAt(Object obj, long l, int i)
        {
            List list = (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l);
            Object obj1;
            if (list.isEmpty())
            {
                if (list instanceof LazyStringList)
                {
                    obj1 = new LazyStringArrayList(i);
                } else
                if ((list instanceof PrimitiveNonBoxingCollection) && (list instanceof Internal.ProtobufList))
                {
                    obj1 = ((Internal.ProtobufList)list).mutableCopyWithCapacity(i);
                } else
                {
                    obj1 = new ArrayList(i);
                }
                UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l, obj1);
            } else
            {
                if (UNMODIFIABLE_LIST_CLASS.isAssignableFrom(list.getClass()))
                {
                    obj1 = new ArrayList(list.size() + i);
                    ((ArrayList) (obj1)).addAll(list);
                    UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l, obj1);
                    return ((List) (obj1));
                }
                if (list instanceof UnmodifiableLazyStringList)
                {
                    obj1 = new LazyStringArrayList(list.size() + i);
                    ((AbstractProtobufList) (obj1)).addAll((UnmodifiableLazyStringList)list);
                    UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l, obj1);
                    return ((List) (obj1));
                }
                obj1 = list;
                if (list instanceof PrimitiveNonBoxingCollection)
                {
                    obj1 = list;
                    if (list instanceof Internal.ProtobufList)
                    {
                        obj1 = list;
                        if (!((Internal.ProtobufList)list).isModifiable())
                        {
                            Internal.ProtobufList protobuflist = ((Internal.ProtobufList)list).mutableCopyWithCapacity(list.size() + i);
                            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l, protobuflist);
                            return protobuflist;
                        }
                    }
                }
            }
            return ((List) (obj1));
        }

        final void makeImmutableListAt(Object obj, long l)
        {
            Object obj1 = (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l);
            if (!(obj1 instanceof LazyStringList)) goto _L2; else goto _L1
_L1:
            obj1 = ((LazyStringList)obj1).getUnmodifiableView();
_L6:
            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l, obj1);
_L4:
            return;
_L2:
            if (UNMODIFIABLE_LIST_CLASS.isAssignableFrom(obj1.getClass()))
            {
                continue; /* Loop/switch isn't completed */
            }
            if (!(obj1 instanceof PrimitiveNonBoxingCollection) || !(obj1 instanceof Internal.ProtobufList))
            {
                break; /* Loop/switch isn't completed */
            }
            if (((Internal.ProtobufList)obj1).isModifiable())
            {
                ((Internal.ProtobufList)obj1).makeImmutable();
                return;
            }
            if (true) goto _L4; else goto _L3
_L3:
            obj1 = Collections.unmodifiableList(((List) (obj1)));
            if (true) goto _L6; else goto _L5
_L5:
        }

        final void mergeListsAt(Object obj, Object obj1, long l)
        {
            obj1 = (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj1, l);
            List list = mutableListAt(obj, l, ((List) (obj1)).size());
            int i = list.size();
            int j = ((List) (obj1)).size();
            if (i > 0 && j > 0)
            {
                list.addAll(((java.util.Collection) (obj1)));
            }
            if (i > 0)
            {
                obj1 = list;
            }
            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l, obj1);
        }

        final List mutableListAt(Object obj, long l)
        {
            return mutableListAt(obj, l, 10);
        }


        ListFieldSchemaFull()
        {
        }
    }


    private class ListFieldSchemaLite extends ListFieldSchema
    {

        final void makeImmutableListAt(Object obj, long l)
        {
            ((Internal.ProtobufList)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l)).makeImmutable();
        }

        final void mergeListsAt(Object obj, Object obj1, long l)
        {
            Object obj2 = (Internal.ProtobufList)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l);
            Internal.ProtobufList protobuflist = (Internal.ProtobufList)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj1, l);
            int i = ((Internal.ProtobufList) (obj2)).size();
            int j = protobuflist.size();
            obj1 = obj2;
            if (i > 0)
            {
                obj1 = obj2;
                if (j > 0)
                {
                    obj1 = obj2;
                    if (!((Internal.ProtobufList) (obj2)).isModifiable())
                    {
                        obj1 = ((Internal.ProtobufList) (obj2)).mutableCopyWithCapacity(j + i);
                    }
                    ((Internal.ProtobufList) (obj1)).addAll(protobuflist);
                }
            }
            obj2 = protobuflist;
            if (i > 0)
            {
                obj2 = obj1;
            }
            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l, obj2);
        }

        final List mutableListAt(Object obj, long l)
        {
            Internal.ProtobufList protobuflist1 = (Internal.ProtobufList)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l);
            Internal.ProtobufList protobuflist = protobuflist1;
            if (!protobuflist1.isModifiable())
            {
                int i = protobuflist1.size();
                if (i == 0)
                {
                    i = 10;
                } else
                {
                    i <<= 1;
                }
                protobuflist = protobuflist1.mutableCopyWithCapacity(i);
                UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l, protobuflist);
            }
            return protobuflist;
        }

        ListFieldSchemaLite()
        {
        }
    }

}
