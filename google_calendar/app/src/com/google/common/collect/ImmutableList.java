// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

// Referenced classes of package com.google.common.collect:
//            ImmutableCollection, RegularImmutableList, CollectPreconditions, ObjectArrays, 
//            Iterators, UnmodifiableListIterator, UnmodifiableIterator

public abstract class ImmutableList extends ImmutableCollection
    implements List, RandomAccess
{

    private static final UnmodifiableListIterator EMPTY_ITR;

    ImmutableList()
    {
    }

    static ImmutableList asImmutableList(Object aobj[])
    {
        int i = aobj.length;
        if (i == 0)
        {
            return RegularImmutableList.EMPTY;
        } else
        {
            return new RegularImmutableList(aobj, i);
        }
    }

    public static ImmutableList asImmutableList(Object aobj[], int i)
    {
        if (i == 0)
        {
            return RegularImmutableList.EMPTY;
        } else
        {
            return new RegularImmutableList(aobj, i);
        }
    }

    public static Builder builder()
    {
        return new Builder();
    }

    public static Builder builderWithExpectedSize(int i)
    {
        CollectPreconditions.checkNonnegative(i, "expectedSize");
        return new Builder(i);
    }

    public static ImmutableList copyOf(Iterable iterable)
    {
        if (iterable == null)
        {
            throw new NullPointerException();
        }
        if (iterable instanceof Collection)
        {
            return copyOf((Collection)iterable);
        }
        iterable = iterable.iterator();
        if (!iterable.hasNext())
        {
            return RegularImmutableList.EMPTY;
        }
        Object obj = iterable.next();
        if (!iterable.hasNext())
        {
            return of(obj);
        }
        iterable = (Builder)((Builder)(new Builder()).add(obj)).addAll(iterable);
        iterable.forceCopy = true;
        Object aobj[] = ((Builder) (iterable)).contents;
        int i = ((Builder) (iterable)).size;
        if (i == 0)
        {
            return RegularImmutableList.EMPTY;
        } else
        {
            return new RegularImmutableList(aobj, i);
        }
    }

    public static ImmutableList copyOf(Collection collection)
    {
label0:
        {
            int i;
label1:
            {
                if (!(collection instanceof ImmutableCollection))
                {
                    break label0;
                }
                ImmutableList immutablelist = ((ImmutableCollection)collection).asList();
                collection = immutablelist;
                if (immutablelist.isPartialView())
                {
                    collection = ((Collection) (immutablelist.toArray()));
                    i = collection.length;
                    if (i != 0)
                    {
                        break label1;
                    }
                    collection = RegularImmutableList.EMPTY;
                }
                return collection;
            }
            return new RegularImmutableList(collection, i);
        }
        collection = ((Collection) (collection.toArray()));
        int l = collection.length;
        for (int j = 0; j < l; j++)
        {
            ObjectArrays.checkElementNotNull(collection[j], j);
        }

        int k = collection.length;
        if (k == 0)
        {
            return RegularImmutableList.EMPTY;
        } else
        {
            return new RegularImmutableList(collection, k);
        }
    }

    public static ImmutableList copyOf(Object aobj[])
    {
        if (aobj.length == 0)
        {
            return RegularImmutableList.EMPTY;
        }
        aobj = (Object[])((Object []) (aobj)).clone();
        int k = aobj.length;
        for (int i = 0; i < k; i++)
        {
            ObjectArrays.checkElementNotNull(aobj[i], i);
        }

        int j = aobj.length;
        if (j == 0)
        {
            return RegularImmutableList.EMPTY;
        } else
        {
            return new RegularImmutableList(aobj, j);
        }
    }

    public static ImmutableList of()
    {
        return RegularImmutableList.EMPTY;
    }

    public static ImmutableList of(Object obj)
    {
        int i = 0;
        Object aobj[] = new Object[1];
        aobj[0] = obj;
        for (int j = aobj.length; i < j; i++)
        {
            ObjectArrays.checkElementNotNull(aobj[i], i);
        }

        i = aobj.length;
        if (i == 0)
        {
            return RegularImmutableList.EMPTY;
        } else
        {
            return new RegularImmutableList(aobj, i);
        }
    }

    public static ImmutableList of(Object obj, Object obj1)
    {
        int i = 0;
        Object aobj[] = new Object[2];
        aobj[0] = obj;
        aobj[1] = obj1;
        for (int j = aobj.length; i < j; i++)
        {
            ObjectArrays.checkElementNotNull(aobj[i], i);
        }

        i = aobj.length;
        if (i == 0)
        {
            return RegularImmutableList.EMPTY;
        } else
        {
            return new RegularImmutableList(aobj, i);
        }
    }

    public static ImmutableList of(Object obj, Object obj1, Object obj2)
    {
        int i = 0;
        Object aobj[] = new Object[3];
        aobj[0] = obj;
        aobj[1] = obj1;
        aobj[2] = obj2;
        for (int j = aobj.length; i < j; i++)
        {
            ObjectArrays.checkElementNotNull(aobj[i], i);
        }

        i = aobj.length;
        if (i == 0)
        {
            return RegularImmutableList.EMPTY;
        } else
        {
            return new RegularImmutableList(aobj, i);
        }
    }

    public static ImmutableList of(Object obj, Object obj1, Object obj2, Object obj3)
    {
        int i = 0;
        Object aobj[] = new Object[4];
        aobj[0] = obj;
        aobj[1] = obj1;
        aobj[2] = obj2;
        aobj[3] = obj3;
        for (int j = aobj.length; i < j; i++)
        {
            ObjectArrays.checkElementNotNull(aobj[i], i);
        }

        i = aobj.length;
        if (i == 0)
        {
            return RegularImmutableList.EMPTY;
        } else
        {
            return new RegularImmutableList(aobj, i);
        }
    }

    public static ImmutableList of(Object obj, Object obj1, Object obj2, Object obj3, Object obj4)
    {
        int i = 0;
        Object aobj[] = new Object[5];
        aobj[0] = obj;
        aobj[1] = obj1;
        aobj[2] = obj2;
        aobj[3] = obj3;
        aobj[4] = obj4;
        for (int j = aobj.length; i < j; i++)
        {
            ObjectArrays.checkElementNotNull(aobj[i], i);
        }

        i = aobj.length;
        if (i == 0)
        {
            return RegularImmutableList.EMPTY;
        } else
        {
            return new RegularImmutableList(aobj, i);
        }
    }

    public static ImmutableList of(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5)
    {
        int i = 0;
        Object aobj[] = new Object[6];
        aobj[0] = obj;
        aobj[1] = obj1;
        aobj[2] = obj2;
        aobj[3] = obj3;
        aobj[4] = obj4;
        aobj[5] = obj5;
        for (int j = aobj.length; i < j; i++)
        {
            ObjectArrays.checkElementNotNull(aobj[i], i);
        }

        i = aobj.length;
        if (i == 0)
        {
            return RegularImmutableList.EMPTY;
        } else
        {
            return new RegularImmutableList(aobj, i);
        }
    }

    public static ImmutableList of(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6)
    {
        int i = 0;
        Object aobj[] = new Object[7];
        aobj[0] = obj;
        aobj[1] = obj1;
        aobj[2] = obj2;
        aobj[3] = obj3;
        aobj[4] = obj4;
        aobj[5] = obj5;
        aobj[6] = obj6;
        for (int j = aobj.length; i < j; i++)
        {
            ObjectArrays.checkElementNotNull(aobj[i], i);
        }

        i = aobj.length;
        if (i == 0)
        {
            return RegularImmutableList.EMPTY;
        } else
        {
            return new RegularImmutableList(aobj, i);
        }
    }

    public static ImmutableList of(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, 
            Object obj8, Object obj9)
    {
        int i = 0;
        Object aobj[] = new Object[10];
        aobj[0] = obj;
        aobj[1] = obj1;
        aobj[2] = obj2;
        aobj[3] = obj3;
        aobj[4] = obj4;
        aobj[5] = obj5;
        aobj[6] = obj6;
        aobj[7] = obj7;
        aobj[8] = obj8;
        aobj[9] = obj9;
        for (int j = aobj.length; i < j; i++)
        {
            ObjectArrays.checkElementNotNull(aobj[i], i);
        }

        i = aobj.length;
        if (i == 0)
        {
            return RegularImmutableList.EMPTY;
        } else
        {
            return new RegularImmutableList(aobj, i);
        }
    }

    private void readObject(ObjectInputStream objectinputstream)
        throws InvalidObjectException
    {
        throw new InvalidObjectException("Use SerializedForm");
    }

    public static ImmutableList sortedCopyOf(Comparator comparator, Iterable iterable)
    {
        if (comparator == null)
        {
            throw new NullPointerException();
        }
        int k;
        if (iterable instanceof Collection)
        {
            iterable = (Collection)iterable;
        } else
        {
            Iterator iterator1 = iterable.iterator();
            iterable = new ArrayList();
            Iterators.addAll(iterable, iterator1);
        }
        iterable = ((Iterable) (iterable.toArray()));
        k = iterable.length;
        for (int i = 0; i < k; i++)
        {
            ObjectArrays.checkElementNotNull(iterable[i], i);
        }

        Arrays.sort(iterable, comparator);
        int j = iterable.length;
        if (j == 0)
        {
            return RegularImmutableList.EMPTY;
        } else
        {
            return new RegularImmutableList(iterable, j);
        }
    }

    public final void add(int i, Object obj)
    {
        throw new UnsupportedOperationException();
    }

    public final boolean addAll(int i, Collection collection)
    {
        throw new UnsupportedOperationException();
    }

    public final ImmutableList asList()
    {
        return this;
    }

    public boolean contains(Object obj)
    {
        return indexOf(obj) >= 0;
    }

    int copyIntoArray(Object aobj[], int i)
    {
        int k = size();
        for (int j = 0; j < k; j++)
        {
            aobj[i + j] = get(j);
        }

        return i + k;
    }

    public boolean equals(Object obj)
    {
        if (this == null)
        {
            throw new NullPointerException();
        }
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof List)) goto _L4; else goto _L3
_L3:
        Object obj1;
        int j;
        obj1 = (List)obj;
        j = size();
        if (j != ((List) (obj1)).size()) goto _L4; else goto _L5
_L5:
        if (!(this instanceof RandomAccess) || !(obj1 instanceof RandomAccess)) goto _L7; else goto _L6
_L6:
        int i = 0;
_L10:
        if (i >= j) goto _L1; else goto _L8
_L8:
        obj = get(i);
        Object obj2 = ((List) (obj1)).get(i);
        boolean flag1;
        if (obj == obj2 || obj != null && obj.equals(obj2))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1) goto _L4; else goto _L9
_L9:
        i++;
          goto _L10
_L7:
        obj = iterator();
        obj1 = ((List) (obj1)).iterator();
_L13:
        if (!((Iterator) (obj)).hasNext()) goto _L12; else goto _L11
_L11:
        if (!((Iterator) (obj1)).hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        Object obj3 = ((Iterator) (obj)).next();
        Object obj4 = ((Iterator) (obj1)).next();
        boolean flag;
        if (obj3 == obj4 || obj3 != null && obj3.equals(obj4))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L13; else goto _L4
_L4:
        return false;
_L12:
        if (!((Iterator) (obj1)).hasNext())
        {
            return true;
        }
        if (true) goto _L4; else goto _L14
_L14:
    }

    public int hashCode()
    {
        int j = 1;
        int k = size();
        for (int i = 0; i < k; i++)
        {
            j = ~~(j * 31 + get(i).hashCode());
        }

        return j;
    }

    public int indexOf(Object obj)
    {
        byte byte0;
        byte0 = -1;
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_170;
        }
        if (!(this instanceof RandomAccess)) goto _L2; else goto _L1
_L1:
        int l = size();
        if (obj != null) goto _L4; else goto _L3
_L3:
        int i = 0;
_L9:
        int k = byte0;
        if (i >= l) goto _L6; else goto _L5
_L5:
        if (get(i) != null) goto _L8; else goto _L7
_L7:
        k = i;
_L6:
        return k;
_L8:
        i++;
          goto _L9
_L4:
        int j = 0;
_L12:
        k = byte0;
        if (j >= l) goto _L6; else goto _L10
_L10:
        i = j;
        if (obj.equals(get(j))) goto _L7; else goto _L11
_L11:
        j++;
          goto _L12
_L2:
        ListIterator listiterator = listIterator();
        do
        {
            if (!listiterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_170;
            }
            Object obj1 = listiterator.next();
            if (obj == obj1 || obj != null && obj.equals(obj1))
            {
                i = 1;
            } else
            {
                i = 0;
            }
        } while (i == 0);
        return listiterator.previousIndex();
        i = -1;
          goto _L7
    }

    public final UnmodifiableIterator iterator()
    {
        return (UnmodifiableListIterator)listIterator();
    }

    public volatile Iterator iterator()
    {
        return iterator();
    }

    public int lastIndexOf(Object obj)
    {
        byte byte0;
        byte0 = -1;
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_178;
        }
        if (!(this instanceof RandomAccess)) goto _L2; else goto _L1
_L1:
        if (obj != null) goto _L4; else goto _L3
_L3:
        int i = size() - 1;
_L9:
        int k = byte0;
        if (i < 0) goto _L6; else goto _L5
_L5:
        if (get(i) != null) goto _L8; else goto _L7
_L7:
        k = i;
_L6:
        return k;
_L8:
        i--;
          goto _L9
_L4:
        int j = size() - 1;
_L12:
        k = byte0;
        if (j < 0) goto _L6; else goto _L10
_L10:
        i = j;
        if (obj.equals(get(j))) goto _L7; else goto _L11
_L11:
        j--;
          goto _L12
_L2:
        ListIterator listiterator = listIterator(size());
        do
        {
            if (!listiterator.hasPrevious())
            {
                break MISSING_BLOCK_LABEL_178;
            }
            Object obj1 = listiterator.previous();
            if (obj == obj1 || obj != null && obj.equals(obj1))
            {
                i = 1;
            } else
            {
                i = 0;
            }
        } while (i == 0);
        return listiterator.nextIndex();
        i = -1;
          goto _L7
    }

    public ListIterator listIterator()
    {
        return (UnmodifiableListIterator)listIterator(0);
    }

    public ListIterator listIterator(int i)
    {
        Preconditions.checkPositionIndex(i, size());
        if (isEmpty())
        {
            return EMPTY_ITR;
        } else
        {
            return new Itr(i);
        }
    }

    public final Object remove(int i)
    {
        throw new UnsupportedOperationException();
    }

    public ImmutableList reverse()
    {
        if (size() <= 1)
        {
            return this;
        } else
        {
            return new ReverseImmutableList();
        }
    }

    public final Object set(int i, Object obj)
    {
        throw new UnsupportedOperationException();
    }

    public ImmutableList subList(int i, int j)
    {
        Preconditions.checkPositionIndexes(i, j, size());
        int k = j - i;
        if (k == size())
        {
            return this;
        }
        if (k == 0)
        {
            return RegularImmutableList.EMPTY;
        } else
        {
            return new SubList(i, j - i);
        }
    }

    public volatile List subList(int i, int j)
    {
        return subList(i, j);
    }

    Object writeReplace()
    {
        return new SerializedForm(toArray());
    }

    static 
    {
        EMPTY_ITR = RegularImmutableList.EMPTY. new Itr(0);
    }

    private class Builder extends ImmutableCollection.ArrayBasedBuilder
    {

        public final volatile ImmutableCollection.ArrayBasedBuilder add(Object obj)
        {
            return (Builder)add(obj);
        }

        public final ImmutableCollection.Builder add(Object obj)
        {
            super.add(obj);
            return this;
        }

        public final ImmutableCollection.Builder addAll(Iterable iterable)
        {
            super.addAll(iterable);
            return this;
        }

        public final ImmutableCollection.Builder addAll(Iterator iterator1)
        {
            super.addAll(iterator1);
            return this;
        }

        public Builder()
        {
            this(4);
        }

        Builder(int i)
        {
            super(i);
        }
    }


    private class Itr extends AbstractIndexedListIterator
    {

        private final ImmutableList list;

        protected final Object get(int i)
        {
            return list.get(i);
        }

        Itr(int i)
        {
            super(size(), i);
            list = ImmutableList.this;
        }
    }


    private class ReverseImmutableList extends ImmutableList
    {

        private final transient ImmutableList forwardList;

        public final boolean contains(Object obj)
        {
            return forwardList.contains(obj);
        }

        public final Object get(int i)
        {
            Preconditions.checkElementIndex(i, size());
            return forwardList.get(size() - 1 - i);
        }

        public final int indexOf(Object obj)
        {
            int i = forwardList.lastIndexOf(obj);
            if (i >= 0)
            {
                return size() - 1 - i;
            } else
            {
                return -1;
            }
        }

        final boolean isPartialView()
        {
            return forwardList.isPartialView();
        }

        public final int lastIndexOf(Object obj)
        {
            int i = forwardList.indexOf(obj);
            if (i >= 0)
            {
                return size() - 1 - i;
            } else
            {
                return -1;
            }
        }

        public final ImmutableList reverse()
        {
            return forwardList;
        }

        public final int size()
        {
            return forwardList.size();
        }

        public final ImmutableList subList(int i, int j)
        {
            Preconditions.checkPositionIndexes(i, j, size());
            return ((ImmutableList)forwardList.subList(size() - j, size() - i)).reverse();
        }

        public final volatile List subList(int i, int j)
        {
            return subList(i, j);
        }

        ReverseImmutableList()
        {
            forwardList = ImmutableList.this;
        }
    }


    private class SubList extends ImmutableList
    {

        private final transient int length;
        private final transient int offset;
        private final ImmutableList this$0;

        public final Object get(int i)
        {
            Preconditions.checkElementIndex(i, length);
            return ImmutableList.this.get(offset + i);
        }

        final boolean isPartialView()
        {
            return true;
        }

        public final int size()
        {
            return length;
        }

        public final ImmutableList subList(int i, int j)
        {
            Preconditions.checkPositionIndexes(i, j, length);
            return (ImmutableList)ImmutableList.this.subList(offset + i, offset + j);
        }

        public final volatile List subList(int i, int j)
        {
            return subList(i, j);
        }

        SubList(int i, int j)
        {
            this$0 = ImmutableList.this;
            super();
            offset = i;
            length = j;
        }
    }


    private class SerializedForm
        implements Serializable
    {

        public static final long serialVersionUID = 0L;
        private final Object elements[];

        final Object readResolve()
        {
            return ImmutableList.copyOf(elements);
        }

        SerializedForm(Object aobj[])
        {
            elements = aobj;
        }
    }

}
