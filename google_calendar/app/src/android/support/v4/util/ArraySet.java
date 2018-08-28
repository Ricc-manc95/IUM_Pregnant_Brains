// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package android.support.v4.util:
//            ContainerHelpers, MapCollections

public final class ArraySet
    implements Collection, Set
{

    private static final int INT[] = new int[0];
    private static final Object OBJECT[] = new Object[0];
    private static Object sBaseCache[];
    private static int sBaseCacheSize;
    private static Object sTwiceBaseCache[];
    private static int sTwiceBaseCacheSize;
    public Object mArray[];
    private MapCollections mCollections;
    private int mHashes[];
    public int mSize;

    public ArraySet()
    {
        this(0);
    }

    private ArraySet(int i)
    {
        mHashes = INT;
        mArray = OBJECT;
        mSize = 0;
    }

    private final void allocArrays(int i)
    {
        if (i != 8) goto _L2; else goto _L1
_L1:
        android/support/v4/util/ArraySet;
        JVM INSTR monitorenter ;
        Object aobj[];
        if (sTwiceBaseCache == null)
        {
            break MISSING_BLOCK_LABEL_63;
        }
        aobj = sTwiceBaseCache;
        mArray = aobj;
        sTwiceBaseCache = (Object[])aobj[0];
        mHashes = (int[])aobj[1];
        aobj[1] = null;
        aobj[0] = null;
        sTwiceBaseCacheSize--;
        android/support/v4/util/ArraySet;
        JVM INSTR monitorexit ;
        return;
        android/support/v4/util/ArraySet;
        JVM INSTR monitorexit ;
_L4:
        mHashes = new int[i];
        mArray = new Object[i];
        return;
        Exception exception;
        exception;
        android/support/v4/util/ArraySet;
        JVM INSTR monitorexit ;
        throw exception;
_L2:
        if (i != 4) goto _L4; else goto _L3
_L3:
        android/support/v4/util/ArraySet;
        JVM INSTR monitorenter ;
        if (sBaseCache == null)
        {
            break MISSING_BLOCK_LABEL_156;
        }
        exception = ((Exception) (sBaseCache));
        mArray = exception;
        sBaseCache = (Object[])exception[0];
        mHashes = (int[])exception[1];
        exception[1] = null;
        exception[0] = null;
        sBaseCacheSize--;
        android/support/v4/util/ArraySet;
        JVM INSTR monitorexit ;
        return;
        exception;
        android/support/v4/util/ArraySet;
        JVM INSTR monitorexit ;
        throw exception;
        android/support/v4/util/ArraySet;
        JVM INSTR monitorexit ;
          goto _L4
    }

    private static void freeArrays(int ai[], Object aobj[], int i)
    {
        if (ai.length != 8) goto _L2; else goto _L1
_L1:
        android/support/v4/util/ArraySet;
        JVM INSTR monitorenter ;
        if (sTwiceBaseCacheSize < 10)
        {
            aobj[0] = ((Object) (sTwiceBaseCache));
            break MISSING_BLOCK_LABEL_24;
        }
          goto _L3
_L8:
        sTwiceBaseCache = aobj;
        sTwiceBaseCacheSize++;
_L3:
        android/support/v4/util/ArraySet;
        JVM INSTR monitorexit ;
        return;
        ai;
        android/support/v4/util/ArraySet;
        JVM INSTR monitorexit ;
        throw ai;
_L2:
        if (ai.length != 4) goto _L5; else goto _L4
_L4:
        android/support/v4/util/ArraySet;
        JVM INSTR monitorenter ;
        if (sBaseCacheSize >= 10) goto _L7; else goto _L6
_L6:
        aobj[0] = ((Object) (sBaseCache));
        aobj[1] = ai;
        i--;
        break MISSING_BLOCK_LABEL_130;
_L9:
        sBaseCache = aobj;
        sBaseCacheSize++;
_L7:
        android/support/v4/util/ArraySet;
        JVM INSTR monitorexit ;
        return;
        ai;
        android/support/v4/util/ArraySet;
        JVM INSTR monitorexit ;
        throw ai;
        aobj[1] = ai;
        i--;
        while (i >= 2) 
        {
            aobj[i] = null;
            i--;
        }
          goto _L8
_L5:
        return;
        while (i >= 2) 
        {
            aobj[i] = null;
            i--;
        }
          goto _L9
    }

    public final boolean add(Object obj)
    {
        int k;
        byte byte0;
        int l;
        byte0 = 8;
        int i;
        if (obj == null)
        {
            i = indexOfNull();
            k = 0;
        } else
        {
            k = obj.hashCode();
            i = indexOf(obj, k);
        }
        if (i >= 0)
        {
            return false;
        }
        l = ~i;
        if (mSize < mHashes.length) goto _L2; else goto _L1
_L1:
        if (mSize < 8) goto _L4; else goto _L3
_L3:
        int j = mSize + (mSize >> 1);
_L6:
        int ai[] = mHashes;
        Object aobj[] = mArray;
        allocArrays(j);
        if (mHashes.length > 0)
        {
            System.arraycopy(ai, 0, mHashes, 0, ai.length);
            System.arraycopy(((Object) (aobj)), 0, ((Object) (mArray)), 0, aobj.length);
        }
        freeArrays(ai, aobj, mSize);
_L2:
        if (l < mSize)
        {
            System.arraycopy(mHashes, l, mHashes, l + 1, mSize - l);
            System.arraycopy(((Object) (mArray)), l, ((Object) (mArray)), l + 1, mSize - l);
        }
        mHashes[l] = k;
        mArray[l] = obj;
        mSize = mSize + 1;
        return true;
_L4:
        j = byte0;
        if (mSize < 4)
        {
            j = 4;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final boolean addAll(Collection collection)
    {
        boolean flag = false;
        int i = mSize + collection.size();
        if (mHashes.length < i)
        {
            int ai[] = mHashes;
            Object aobj[] = mArray;
            allocArrays(i);
            if (mSize > 0)
            {
                System.arraycopy(ai, 0, mHashes, 0, mSize);
                System.arraycopy(((Object) (aobj)), 0, ((Object) (mArray)), 0, mSize);
            }
            freeArrays(ai, aobj, mSize);
        }
        for (collection = collection.iterator(); collection.hasNext();)
        {
            flag |= add(collection.next());
        }

        return flag;
    }

    public final void clear()
    {
        if (mSize != 0)
        {
            freeArrays(mHashes, mArray, mSize);
            mHashes = INT;
            mArray = OBJECT;
            mSize = 0;
        }
    }

    public final boolean contains(Object obj)
    {
        int i;
        if (obj == null)
        {
            i = indexOfNull();
        } else
        {
            i = indexOf(obj, obj.hashCode());
        }
        return i >= 0;
    }

    public final boolean containsAll(Collection collection)
    {
        for (collection = collection.iterator(); collection.hasNext();)
        {
            if (!contains(collection.next()))
            {
                return false;
            }
        }

        return true;
    }

    public final boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
label0:
        {
            if (!(obj instanceof Set))
            {
                break label0;
            }
            obj = (Set)obj;
            if (size() != ((Set) (obj)).size())
            {
                return false;
            }
            int i = 0;
            do
            {
                boolean flag;
                try
                {
                    if (i >= mSize)
                    {
                        break;
                    }
                    flag = ((Set) (obj)).contains(mArray[i]);
                }
                // Misplaced declaration of an exception variable
                catch (Object obj)
                {
                    return false;
                }
                // Misplaced declaration of an exception variable
                catch (Object obj)
                {
                    return false;
                }
                if (!flag)
                {
                    return false;
                }
                i++;
            } while (true);
        }
        if (true) goto _L1; else goto _L3
_L3:
        return false;
    }

    public final int hashCode()
    {
        int i = 0;
        int ai[] = mHashes;
        int k = mSize;
        int j = 0;
        for (; i < k; i++)
        {
            j += ai[i];
        }

        return j;
    }

    final int indexOf(Object obj, int i)
    {
        int i1 = mSize;
        if (i1 != 0) goto _L2; else goto _L1
_L1:
        int j = -1;
_L4:
        return j;
_L2:
        int l;
        int k = ContainerHelpers.binarySearch(mHashes, i1, i);
        j = k;
        if (k >= 0)
        {
            j = k;
            if (!obj.equals(mArray[k]))
            {
                for (l = k + 1; l < i1 && mHashes[l] == i; l++)
                {
                    if (obj.equals(mArray[l]))
                    {
                        return l;
                    }
                }

                k--;
label0:
                do
                {
label1:
                    {
                        if (k < 0 || mHashes[k] != i)
                        {
                            break label1;
                        }
                        j = k;
                        if (obj.equals(mArray[k]))
                        {
                            break label0;
                        }
                        k--;
                    }
                } while (true);
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
        return ~l;
    }

    final int indexOfNull()
    {
        int l = mSize;
        if (l != 0) goto _L2; else goto _L1
_L1:
        int i = -1;
_L4:
        return i;
_L2:
        int k;
        int j = ContainerHelpers.binarySearch(mHashes, l, 0);
        i = j;
        if (j >= 0)
        {
            i = j;
            if (mArray[j] != null)
            {
                for (k = j + 1; k < l && mHashes[k] == 0; k++)
                {
                    if (mArray[k] == null)
                    {
                        return k;
                    }
                }

                j--;
label0:
                do
                {
label1:
                    {
                        if (j < 0 || mHashes[j] != 0)
                        {
                            break label1;
                        }
                        i = j;
                        if (mArray[j] == null)
                        {
                            break label0;
                        }
                        j--;
                    }
                } while (true);
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
        return ~k;
    }

    public final boolean isEmpty()
    {
        return mSize <= 0;
    }

    public final Iterator iterator()
    {
        if (mCollections == null)
        {
            mCollections = new _cls1();
        }
        MapCollections mapcollections = mCollections;
        if (mapcollections.mKeySet == null)
        {
            mapcollections.mKeySet = new MapCollections.KeySet(mapcollections);
        }
        return mapcollections.mKeySet.iterator();
    }

    public final boolean remove(Object obj)
    {
        int i;
        if (obj == null)
        {
            i = indexOfNull();
        } else
        {
            i = indexOf(obj, obj.hashCode());
        }
        if (i >= 0)
        {
            removeAt(i);
            return true;
        } else
        {
            return false;
        }
    }

    public final boolean removeAll(Collection collection)
    {
        boolean flag = false;
        for (collection = collection.iterator(); collection.hasNext();)
        {
            flag |= remove(collection.next());
        }

        return flag;
    }

    public final Object removeAt(int i)
    {
        int j = 8;
        Object obj = mArray[i];
        if (mSize <= 1)
        {
            freeArrays(mHashes, mArray, mSize);
            mHashes = INT;
            mArray = OBJECT;
            mSize = 0;
        } else
        if (mHashes.length > 8 && mSize < mHashes.length / 3)
        {
            if (mSize > 8)
            {
                j = mSize + (mSize >> 1);
            }
            int ai[] = mHashes;
            Object aobj[] = mArray;
            allocArrays(j);
            mSize = mSize - 1;
            if (i > 0)
            {
                System.arraycopy(ai, 0, mHashes, 0, i);
                System.arraycopy(((Object) (aobj)), 0, ((Object) (mArray)), 0, i);
            }
            if (i < mSize)
            {
                System.arraycopy(ai, i + 1, mHashes, i, mSize - i);
                System.arraycopy(((Object) (aobj)), i + 1, ((Object) (mArray)), i, mSize - i);
                return obj;
            }
        } else
        {
            mSize = mSize - 1;
            if (i < mSize)
            {
                System.arraycopy(mHashes, i + 1, mHashes, i, mSize - i);
                System.arraycopy(((Object) (mArray)), i + 1, ((Object) (mArray)), i, mSize - i);
            }
            mArray[mSize] = null;
            return obj;
        }
        return obj;
    }

    public final boolean retainAll(Collection collection)
    {
        int i = mSize;
        boolean flag = false;
        for (i--; i >= 0; i--)
        {
            if (!collection.contains(mArray[i]))
            {
                removeAt(i);
                flag = true;
            }
        }

        return flag;
    }

    public final int size()
    {
        return mSize;
    }

    public final Object[] toArray()
    {
        Object aobj[] = new Object[mSize];
        System.arraycopy(((Object) (mArray)), 0, ((Object) (aobj)), 0, mSize);
        return aobj;
    }

    public final Object[] toArray(Object aobj[])
    {
        if (aobj.length < mSize)
        {
            aobj = (Object[])Array.newInstance(((Object) (aobj)).getClass().getComponentType(), mSize);
        }
        System.arraycopy(((Object) (mArray)), 0, ((Object) (aobj)), 0, mSize);
        if (aobj.length > mSize)
        {
            aobj[mSize] = null;
        }
        return aobj;
    }

    public final String toString()
    {
        if (isEmpty())
        {
            return "{}";
        }
        StringBuilder stringbuilder = new StringBuilder(mSize * 14);
        stringbuilder.append('{');
        int i = 0;
        while (i < mSize) 
        {
            if (i > 0)
            {
                stringbuilder.append(", ");
            }
            Object obj = mArray[i];
            if (obj != this)
            {
                stringbuilder.append(obj);
            } else
            {
                stringbuilder.append("(this Set)");
            }
            i++;
        }
        stringbuilder.append('}');
        return stringbuilder.toString();
    }


    private class _cls1 extends MapCollections
    {

        private final ArraySet this$0;

        protected final void colClear()
        {
            clear();
        }

        protected final Object colGetEntry(int i, int j)
        {
            return mArray[i];
        }

        protected final Map colGetMap()
        {
            throw new UnsupportedOperationException("not a map");
        }

        protected final int colGetSize()
        {
            return mSize;
        }

        protected final int colIndexOfKey(Object obj)
        {
            ArraySet arrayset = ArraySet.this;
            if (obj == null)
            {
                return arrayset.indexOfNull();
            } else
            {
                return arrayset.indexOf(obj, obj.hashCode());
            }
        }

        protected final int colIndexOfValue(Object obj)
        {
            ArraySet arrayset = ArraySet.this;
            if (obj == null)
            {
                return arrayset.indexOfNull();
            } else
            {
                return arrayset.indexOf(obj, obj.hashCode());
            }
        }

        protected final void colPut(Object obj, Object obj1)
        {
            add(obj);
        }

        protected final void colRemoveAt(int i)
        {
            removeAt(i);
        }

        protected final Object colSetValue(int i, Object obj)
        {
            throw new UnsupportedOperationException("not a map");
        }

        _cls1()
        {
            this$0 = ArraySet.this;
            super();
        }
    }

}
