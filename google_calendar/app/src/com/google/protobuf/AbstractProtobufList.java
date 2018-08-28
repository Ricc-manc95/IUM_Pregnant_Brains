// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

abstract class AbstractProtobufList extends AbstractList
    implements Internal.ProtobufList
{

    public boolean isMutable;

    AbstractProtobufList()
    {
        isMutable = true;
    }

    public void add(int i, Object obj)
    {
        if (!isMutable)
        {
            throw new UnsupportedOperationException();
        } else
        {
            super.add(i, obj);
            return;
        }
    }

    public boolean add(Object obj)
    {
        if (!isMutable)
        {
            throw new UnsupportedOperationException();
        } else
        {
            return super.add(obj);
        }
    }

    public boolean addAll(int i, Collection collection)
    {
        if (!isMutable)
        {
            throw new UnsupportedOperationException();
        } else
        {
            return super.addAll(i, collection);
        }
    }

    public boolean addAll(Collection collection)
    {
        if (!isMutable)
        {
            throw new UnsupportedOperationException();
        } else
        {
            return super.addAll(collection);
        }
    }

    public void clear()
    {
        if (!isMutable)
        {
            throw new UnsupportedOperationException();
        } else
        {
            super.clear();
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (!(obj instanceof List))
            {
                return false;
            }
            if (!(obj instanceof RandomAccess))
            {
                return super.equals(obj);
            }
            obj = (List)obj;
            int j = size();
            if (j != ((List) (obj)).size())
            {
                return false;
            }
            int i = 0;
            while (i < j) 
            {
                if (!get(i).equals(((List) (obj)).get(i)))
                {
                    return false;
                }
                i++;
            }
        }
        return true;
    }

    public int hashCode()
    {
        int k = size();
        int j = 1;
        for (int i = 0; i < k; i++)
        {
            j = j * 31 + get(i).hashCode();
        }

        return j;
    }

    public boolean isModifiable()
    {
        return isMutable;
    }

    public final void makeImmutable()
    {
        isMutable = false;
    }

    public Object remove(int i)
    {
        if (!isMutable)
        {
            throw new UnsupportedOperationException();
        } else
        {
            return super.remove(i);
        }
    }

    public boolean remove(Object obj)
    {
        if (!isMutable)
        {
            throw new UnsupportedOperationException();
        } else
        {
            return super.remove(obj);
        }
    }

    public boolean removeAll(Collection collection)
    {
        if (!isMutable)
        {
            throw new UnsupportedOperationException();
        } else
        {
            return super.removeAll(collection);
        }
    }

    public boolean retainAll(Collection collection)
    {
        if (!isMutable)
        {
            throw new UnsupportedOperationException();
        } else
        {
            return super.retainAll(collection);
        }
    }

    public Object set(int i, Object obj)
    {
        if (!isMutable)
        {
            throw new UnsupportedOperationException();
        } else
        {
            return super.set(i, obj);
        }
    }
}
