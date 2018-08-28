// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;

// Referenced classes of package com.google.common.collect:
//            ImmutableCollection, ObjectArrays, RegularImmutableSet, SingletonImmutableSet, 
//            ImmutableList, Sets

public abstract class ImmutableSet extends ImmutableCollection
    implements Set
{

    private transient ImmutableList asList;

    ImmutableSet()
    {
    }

    static int chooseTableSize(int i)
    {
        int k = Math.max(i, 2);
        int j;
        if (k < 0x2ccccccc)
        {
            i = Integer.highestOneBit(k - 1) << 1;
            do
            {
                j = i;
                if ((double)i * 0.69999999999999996D >= (double)k)
                {
                    break;
                }
                i <<= 1;
            } while (true);
        } else
        {
            if (k < 0x40000000)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                throw new IllegalArgumentException(String.valueOf("collection too large"));
            }
            j = 0x40000000;
        }
        return j;
    }

    public static transient ImmutableSet construct(int i, Object aobj[])
    {
        int j = i;
_L13:
        j;
        JVM INSTR tableswitch 0 1: default 28
    //                   0 154
    //                   1 158;
           goto _L1 _L2 _L3
_L1:
        Object aobj1[];
        int l;
        int i1;
        int i2;
        int j2;
        j2 = chooseTableSize(j);
        aobj1 = new Object[j2];
        i2 = j2 - 1;
        i1 = 0;
        i = 0;
        l = 0;
_L8:
        if (l >= j) goto _L5; else goto _L4
_L4:
        Object obj;
        int k1;
        int k2;
        obj = ObjectArrays.checkElementNotNull(aobj[l], l);
        k2 = obj.hashCode();
        k1 = (int)(0x1b873593L * (long)Integer.rotateLeft((int)((long)k2 * 0xffffffffcc9e2d51L), 15));
_L11:
        Object obj1;
        int j1;
        j1 = k1 & i2;
        obj1 = aobj1[j1];
        if (obj1 != null) goto _L7; else goto _L6
_L6:
        int l1;
        aobj[i] = obj;
        aobj1[j1] = obj;
        l1 = i1 + k2;
        j1 = i + 1;
_L10:
        l++;
        i1 = l1;
        i = j1;
          goto _L8
_L2:
        return RegularImmutableSet.EMPTY;
_L3:
        return new SingletonImmutableSet(aobj[0]);
_L7:
        l1 = i1;
        j1 = i;
        if (obj1.equals(obj)) goto _L10; else goto _L9
_L9:
        k1++;
          goto _L11
_L5:
        Arrays.fill(aobj, i, j, null);
        if (i == 1)
        {
            return new SingletonImmutableSet(aobj[0], i1);
        }
        if (chooseTableSize(i) >= j2 / 2)
        {
            break; /* Loop/switch isn't completed */
        }
        j = i;
        if (true) goto _L13; else goto _L12
_L12:
        int k = aobj.length;
        if (i < (k >> 2) + (k >> 1))
        {
            aobj = Arrays.copyOf(aobj, i);
        }
        return new RegularImmutableSet(aobj, i1, aobj1, i2, i);
    }

    public static ImmutableSet copyOf(Collection collection)
    {
        if ((collection instanceof ImmutableSet) && !(collection instanceof SortedSet))
        {
            ImmutableSet immutableset = (ImmutableSet)collection;
            if (!immutableset.isPartialView())
            {
                return immutableset;
            }
        }
        collection = ((Collection) (collection.toArray()));
        return construct(collection.length, collection);
    }

    public static ImmutableSet copyOf(Object aobj[])
    {
        switch (aobj.length)
        {
        default:
            return construct(aobj.length, (Object[])((Object []) (aobj)).clone());

        case 0: // '\0'
            return RegularImmutableSet.EMPTY;

        case 1: // '\001'
            return new SingletonImmutableSet(aobj[0]);
        }
    }

    public static transient ImmutableSet of(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object aobj[])
    {
        boolean flag;
        if (aobj.length <= 0x7ffffff9)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("the total number of elements must fit in an int"));
        } else
        {
            Object aobj1[] = new Object[aobj.length + 6];
            aobj1[0] = obj;
            aobj1[1] = obj1;
            aobj1[2] = obj2;
            aobj1[3] = obj3;
            aobj1[4] = obj4;
            aobj1[5] = obj5;
            System.arraycopy(((Object) (aobj)), 0, ((Object) (aobj1)), 6, aobj.length);
            return construct(aobj1.length, aobj1);
        }
    }

    public ImmutableList asList()
    {
        ImmutableList immutablelist1 = asList;
        ImmutableList immutablelist = immutablelist1;
        if (immutablelist1 == null)
        {
            immutablelist = createAsList();
            asList = immutablelist;
        }
        return immutablelist;
    }

    ImmutableList createAsList()
    {
        return ImmutableList.asImmutableList(toArray());
    }

    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if ((obj instanceof ImmutableSet) && isHashCodeFast() && ((ImmutableSet)obj).isHashCodeFast() && hashCode() != obj.hashCode())
        {
            return false;
        } else
        {
            return Sets.equalsImpl(this, obj);
        }
    }

    public int hashCode()
    {
        return Sets.hashCodeImpl(this);
    }

    boolean isHashCodeFast()
    {
        return false;
    }

    public volatile Iterator iterator()
    {
        return iterator();
    }

    Object writeReplace()
    {
        return new SerializedForm(toArray());
    }

    private class SerializedForm
        implements Serializable
    {

        public static final long serialVersionUID = 0L;
        private final Object elements[];

        final Object readResolve()
        {
            return ImmutableSet.copyOf(elements);
        }

        SerializedForm(Object aobj[])
        {
            elements = aobj;
        }
    }

}
