// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

// Referenced classes of package com.google.protobuf:
//            AbstractProtobufList, LazyStringList, ByteString, Internal, 
//            UnmodifiableLazyStringList

public final class LazyStringArrayList extends AbstractProtobufList
    implements LazyStringList, RandomAccess
{

    private static final LazyStringArrayList EMPTY_LIST;
    private final List list;

    public LazyStringArrayList()
    {
        this(10);
    }

    public LazyStringArrayList(int i)
    {
        this(new ArrayList(i));
    }

    private LazyStringArrayList(ArrayList arraylist)
    {
        list = arraylist;
    }

    private static String asString(Object obj)
    {
        if (obj instanceof String)
        {
            return (String)obj;
        }
        if (obj instanceof ByteString)
        {
            obj = (ByteString)obj;
            java.nio.charset.Charset charset = Internal.UTF_8;
            if (((ByteString) (obj)).size() == 0)
            {
                return "";
            } else
            {
                return ((ByteString) (obj)).toStringInternal(charset);
            }
        } else
        {
            return Internal.toStringUtf8((byte[])obj);
        }
    }

    public final void add(int i, Object obj)
    {
        obj = (String)obj;
        if (!super.isMutable)
        {
            throw new UnsupportedOperationException();
        } else
        {
            list.add(i, obj);
            modCount = modCount + 1;
            return;
        }
    }

    public final void add(ByteString bytestring)
    {
        if (!super.isMutable)
        {
            throw new UnsupportedOperationException();
        } else
        {
            list.add(bytestring);
            modCount = modCount + 1;
            return;
        }
    }

    public final boolean addAll(int i, Collection collection)
    {
        if (!super.isMutable)
        {
            throw new UnsupportedOperationException();
        }
        Object obj = collection;
        if (collection instanceof LazyStringList)
        {
            obj = ((LazyStringList)collection).getUnderlyingElements();
        }
        boolean flag = list.addAll(i, ((Collection) (obj)));
        modCount = modCount + 1;
        return flag;
    }

    public final boolean addAll(Collection collection)
    {
        return addAll(size(), collection);
    }

    public final void clear()
    {
        if (!super.isMutable)
        {
            throw new UnsupportedOperationException();
        } else
        {
            list.clear();
            modCount = modCount + 1;
            return;
        }
    }

    public final volatile boolean equals(Object obj)
    {
        return super.equals(obj);
    }

    public final Object get(int i)
    {
        Object obj = list.get(i);
        if (obj instanceof String)
        {
            return (String)obj;
        }
        if (obj instanceof ByteString)
        {
            ByteString bytestring = (ByteString)obj;
            obj = Internal.UTF_8;
            if (bytestring.size() == 0)
            {
                obj = "";
            } else
            {
                obj = bytestring.toStringInternal(((java.nio.charset.Charset) (obj)));
            }
            if (bytestring.isValidUtf8())
            {
                list.set(i, obj);
            }
            return obj;
        }
        byte abyte0[] = (byte[])obj;
        String s = Internal.toStringUtf8(abyte0);
        if (Internal.isValidUtf8(abyte0))
        {
            list.set(i, s);
        }
        return s;
    }

    public final Object getRaw(int i)
    {
        return list.get(i);
    }

    public final List getUnderlyingElements()
    {
        return Collections.unmodifiableList(list);
    }

    public final LazyStringList getUnmodifiableView()
    {
        Object obj = this;
        if (isModifiable())
        {
            obj = new UnmodifiableLazyStringList(this);
        }
        return ((LazyStringList) (obj));
    }

    public final volatile int hashCode()
    {
        return super.hashCode();
    }

    public final volatile boolean isModifiable()
    {
        return super.isModifiable();
    }

    public final Internal.ProtobufList mutableCopyWithCapacity(int i)
    {
        if (i < size())
        {
            throw new IllegalArgumentException();
        } else
        {
            ArrayList arraylist = new ArrayList(i);
            arraylist.addAll(list);
            return new LazyStringArrayList(arraylist);
        }
    }

    public final Object remove(int i)
    {
        if (!super.isMutable)
        {
            throw new UnsupportedOperationException();
        } else
        {
            Object obj = list.remove(i);
            modCount = modCount + 1;
            return asString(obj);
        }
    }

    public final volatile boolean remove(Object obj)
    {
        return super.remove(obj);
    }

    public final volatile boolean removeAll(Collection collection)
    {
        return super.removeAll(collection);
    }

    public final volatile boolean retainAll(Collection collection)
    {
        return super.retainAll(collection);
    }

    public final Object set(int i, Object obj)
    {
        obj = (String)obj;
        if (!super.isMutable)
        {
            throw new UnsupportedOperationException();
        } else
        {
            return asString(list.set(i, obj));
        }
    }

    public final int size()
    {
        return list.size();
    }

    static 
    {
        LazyStringArrayList lazystringarraylist = new LazyStringArrayList();
        EMPTY_LIST = lazystringarraylist;
        lazystringarraylist.isMutable = false;
    }
}
