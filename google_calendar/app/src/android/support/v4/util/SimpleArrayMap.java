// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.util;

import java.util.ConcurrentModificationException;
import java.util.Map;

// Referenced classes of package android.support.v4.util:
//            ContainerHelpers, ArrayMap

public class SimpleArrayMap
{

    private static Object mBaseCache[];
    private static int mBaseCacheSize;
    private static Object mTwiceBaseCache[];
    private static int mTwiceBaseCacheSize;
    public Object mArray[];
    private int mHashes[];
    public int mSize;

    public SimpleArrayMap()
    {
        mHashes = ContainerHelpers.EMPTY_INTS;
        mArray = ContainerHelpers.EMPTY_OBJECTS;
        mSize = 0;
    }

    public SimpleArrayMap(int i)
    {
        if (i == 0)
        {
            mHashes = ContainerHelpers.EMPTY_INTS;
            mArray = ContainerHelpers.EMPTY_OBJECTS;
        } else
        {
            allocArrays(i);
        }
        mSize = 0;
    }

    public SimpleArrayMap(SimpleArrayMap simplearraymap)
    {
        this();
        if (simplearraymap != null)
        {
            putAll(simplearraymap);
        }
    }

    private final void allocArrays(int i)
    {
        if (i != 8) goto _L2; else goto _L1
_L1:
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorenter ;
        Object aobj[];
        if (mTwiceBaseCache == null)
        {
            break MISSING_BLOCK_LABEL_63;
        }
        aobj = mTwiceBaseCache;
        mArray = aobj;
        mTwiceBaseCache = (Object[])aobj[0];
        mHashes = (int[])aobj[1];
        aobj[1] = null;
        aobj[0] = null;
        mTwiceBaseCacheSize--;
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorexit ;
        return;
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorexit ;
_L4:
        mHashes = new int[i];
        mArray = new Object[i << 1];
        return;
        Exception exception;
        exception;
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorexit ;
        throw exception;
_L2:
        if (i != 4) goto _L4; else goto _L3
_L3:
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorenter ;
        if (mBaseCache == null)
        {
            break MISSING_BLOCK_LABEL_158;
        }
        exception = ((Exception) (mBaseCache));
        mArray = exception;
        mBaseCache = (Object[])exception[0];
        mHashes = (int[])exception[1];
        exception[1] = null;
        exception[0] = null;
        mBaseCacheSize--;
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorexit ;
        return;
        exception;
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorexit ;
        throw exception;
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorexit ;
          goto _L4
    }

    private static int binarySearchHashes(int ai[], int i, int j)
    {
        try
        {
            i = ContainerHelpers.binarySearch(ai, i, j);
        }
        // Misplaced declaration of an exception variable
        catch (int ai[])
        {
            throw new ConcurrentModificationException();
        }
        return i;
    }

    private static void freeArrays(int ai[], Object aobj[], int i)
    {
        if (ai.length != 8) goto _L2; else goto _L1
_L1:
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorenter ;
        if (mTwiceBaseCacheSize < 10)
        {
            aobj[0] = ((Object) (mTwiceBaseCache));
            break MISSING_BLOCK_LABEL_24;
        }
          goto _L3
_L8:
        mTwiceBaseCache = aobj;
        mTwiceBaseCacheSize++;
_L3:
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorexit ;
        return;
        ai;
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorexit ;
        throw ai;
_L2:
        if (ai.length != 4) goto _L5; else goto _L4
_L4:
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorenter ;
        if (mBaseCacheSize >= 10) goto _L7; else goto _L6
_L6:
        aobj[0] = ((Object) (mBaseCache));
        aobj[1] = ai;
        i = (i << 1) - 1;
        break MISSING_BLOCK_LABEL_134;
_L9:
        mBaseCache = aobj;
        mBaseCacheSize++;
_L7:
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorexit ;
        return;
        ai;
        android/support/v4/util/ArrayMap;
        JVM INSTR monitorexit ;
        throw ai;
        aobj[1] = ai;
        i = (i << 1) - 1;
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

    public void clear()
    {
        if (mSize > 0)
        {
            int ai[] = mHashes;
            Object aobj[] = mArray;
            int i = mSize;
            mHashes = ContainerHelpers.EMPTY_INTS;
            mArray = ContainerHelpers.EMPTY_OBJECTS;
            mSize = 0;
            freeArrays(ai, aobj, i);
        }
        if (mSize > 0)
        {
            throw new ConcurrentModificationException();
        } else
        {
            return;
        }
    }

    public boolean containsKey(Object obj)
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

    public boolean containsValue(Object obj)
    {
        return indexOfValue(obj) >= 0;
    }

    public final void ensureCapacity(int i)
    {
        int j = mSize;
        if (mHashes.length < i)
        {
            int ai[] = mHashes;
            Object aobj[] = mArray;
            allocArrays(i);
            if (mSize > 0)
            {
                System.arraycopy(ai, 0, mHashes, 0, j);
                System.arraycopy(((Object) (aobj)), 0, ((Object) (mArray)), 0, j << 1);
            }
            freeArrays(ai, aobj, j);
        }
        if (mSize != j)
        {
            throw new ConcurrentModificationException();
        } else
        {
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof SimpleArrayMap)) goto _L4; else goto _L3
_L3:
        int i;
        obj = (SimpleArrayMap)obj;
        if (size() != ((SimpleArrayMap) (obj)).size())
        {
            return false;
        }
        i = 0;
_L5:
        Object obj1;
        Object obj2;
        Object obj3;
        if (i >= mSize)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj1 = mArray[i << 1];
        obj2 = mArray[(i << 1) + 1];
        obj3 = ((SimpleArrayMap) (obj)).get(obj1);
        if (obj2 != null)
        {
            break MISSING_BLOCK_LABEL_93;
        }
        if (obj3 != null)
        {
            break MISSING_BLOCK_LABEL_247;
        }
        if (!((SimpleArrayMap) (obj)).containsKey(obj1))
        {
            break MISSING_BLOCK_LABEL_247;
        }
        break MISSING_BLOCK_LABEL_108;
        boolean flag;
        try
        {
            flag = obj2.equals(obj3);
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
        if (true) goto _L5; else goto _L4
_L4:
        if (!(obj instanceof Map))
        {
            break; /* Loop/switch isn't completed */
        }
        obj = (Map)obj;
        if (size() != ((Map) (obj)).size())
        {
            return false;
        }
        i = 0;
_L7:
        if (i >= mSize)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = mArray[i << 1];
        obj2 = mArray[(i << 1) + 1];
        obj3 = ((Map) (obj)).get(obj1);
        if (obj2 != null)
        {
            break MISSING_BLOCK_LABEL_215;
        }
        if (obj3 != null)
        {
            break MISSING_BLOCK_LABEL_249;
        }
        if (!((Map) (obj)).containsKey(obj1))
        {
            break MISSING_BLOCK_LABEL_249;
        }
        break MISSING_BLOCK_LABEL_230;
        boolean flag1;
        try
        {
            flag1 = obj2.equals(obj3);
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
        if (!flag1)
        {
            return false;
        }
        i++;
        if (true) goto _L7; else goto _L6
_L6:
        if (true) goto _L1; else goto _L8
_L8:
        return false;
        return false;
        return false;
    }

    public Object get(Object obj)
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
            return mArray[(i << 1) + 1];
        } else
        {
            return null;
        }
    }

    public int hashCode()
    {
        int ai[] = mHashes;
        Object aobj[] = mArray;
        int i1 = mSize;
        int i = 1;
        int j = 0;
        int k = 0;
        while (j < i1) 
        {
            Object obj = aobj[i];
            int j1 = ai[j];
            int l;
            if (obj == null)
            {
                l = 0;
            } else
            {
                l = obj.hashCode();
            }
            k += l ^ j1;
            j++;
            i += 2;
        }
        return k;
    }

    public final int indexOf(Object obj, int i)
    {
        int i1 = mSize;
        if (i1 != 0) goto _L2; else goto _L1
_L1:
        int j = -1;
_L4:
        return j;
_L2:
        int l;
        int k = binarySearchHashes(mHashes, i1, i);
        j = k;
        if (k >= 0)
        {
            j = k;
            if (!obj.equals(mArray[k << 1]))
            {
                for (l = k + 1; l < i1 && mHashes[l] == i; l++)
                {
                    if (obj.equals(mArray[l << 1]))
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
                        if (obj.equals(mArray[k << 1]))
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

    public final int indexOfNull()
    {
        int l = mSize;
        if (l != 0) goto _L2; else goto _L1
_L1:
        int i = -1;
_L4:
        return i;
_L2:
        int k;
        int j = binarySearchHashes(mHashes, l, 0);
        i = j;
        if (j >= 0)
        {
            i = j;
            if (mArray[j << 1] != null)
            {
                for (k = j + 1; k < l && mHashes[k] == 0; k++)
                {
                    if (mArray[k << 1] == null)
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
                        if (mArray[j << 1] == null)
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

    final int indexOfValue(Object obj)
    {
        Object aobj[];
        int i;
        boolean flag;
        int j;
        i = 1;
        flag = true;
        j = mSize << 1;
        aobj = mArray;
        if (obj != null) goto _L2; else goto _L1
_L1:
        for (i = ((flag) ? 1 : 0); i < j; i += 2)
        {
            if (aobj[i] == null)
            {
                return i >> 1;
            }
        }

          goto _L3
_L4:
        i += 2;
_L2:
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        if (obj.equals(aobj[i]))
        {
            return i >> 1;
        }
        if (true) goto _L4; else goto _L3
_L3:
        return -1;
    }

    public boolean isEmpty()
    {
        return mSize <= 0;
    }

    public Object put(Object obj, Object obj1)
    {
        int k;
        byte byte0;
        int l;
        int i1;
        byte0 = 8;
        l = mSize;
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
            i = (i << 1) + 1;
            obj = mArray[i];
            mArray[i] = obj1;
            return obj;
        }
        i1 = ~i;
        if (l < mHashes.length)
        {
            break MISSING_BLOCK_LABEL_196;
        }
        if (l < 8) goto _L2; else goto _L1
_L1:
        int j = (l >> 1) + l;
_L4:
        int ai[];
        Object aobj[];
        ai = mHashes;
        aobj = mArray;
        allocArrays(j);
        if (l != mSize)
        {
            throw new ConcurrentModificationException();
        }
        break; /* Loop/switch isn't completed */
_L2:
        j = byte0;
        if (l < 4)
        {
            j = 4;
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (mHashes.length > 0)
        {
            System.arraycopy(ai, 0, mHashes, 0, ai.length);
            System.arraycopy(((Object) (aobj)), 0, ((Object) (mArray)), 0, aobj.length);
        }
        freeArrays(ai, aobj, l);
        if (i1 < l)
        {
            System.arraycopy(mHashes, i1, mHashes, i1 + 1, l - i1);
            System.arraycopy(((Object) (mArray)), i1 << 1, ((Object) (mArray)), i1 + 1 << 1, mSize - i1 << 1);
        }
        if (l != mSize || i1 >= mHashes.length)
        {
            throw new ConcurrentModificationException();
        } else
        {
            mHashes[i1] = k;
            mArray[i1 << 1] = obj;
            mArray[(i1 << 1) + 1] = obj1;
            mSize = mSize + 1;
            return null;
        }
    }

    public final void putAll(SimpleArrayMap simplearraymap)
    {
        int i = 0;
        int j = simplearraymap.mSize;
        ensureCapacity(mSize + j);
        if (mSize == 0)
        {
            if (j > 0)
            {
                System.arraycopy(simplearraymap.mHashes, 0, mHashes, 0, j);
                System.arraycopy(((Object) (simplearraymap.mArray)), 0, ((Object) (mArray)), 0, j << 1);
                mSize = j;
            }
        } else
        {
            while (i < j) 
            {
                put(simplearraymap.mArray[i << 1], simplearraymap.mArray[(i << 1) + 1]);
                i++;
            }
        }
    }

    public Object remove(Object obj)
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
            return removeAt(i);
        } else
        {
            return null;
        }
    }

    public final Object removeAt(int i)
    {
        int k = 8;
        Object obj = mArray[(i << 1) + 1];
        int l = mSize;
        if (l <= 1)
        {
            freeArrays(mHashes, mArray, l);
            mHashes = ContainerHelpers.EMPTY_INTS;
            mArray = ContainerHelpers.EMPTY_OBJECTS;
            i = 0;
        } else
        {
            int j = l - 1;
            if (mHashes.length > 8 && mSize < mHashes.length / 3)
            {
                if (l > 8)
                {
                    k = (l >> 1) + l;
                }
                int ai[] = mHashes;
                Object aobj[] = mArray;
                allocArrays(k);
                if (l != mSize)
                {
                    throw new ConcurrentModificationException();
                }
                if (i > 0)
                {
                    System.arraycopy(ai, 0, mHashes, 0, i);
                    System.arraycopy(((Object) (aobj)), 0, ((Object) (mArray)), 0, i << 1);
                }
                if (i < j)
                {
                    System.arraycopy(ai, i + 1, mHashes, i, j - i);
                    System.arraycopy(((Object) (aobj)), i + 1 << 1, ((Object) (mArray)), i << 1, j - i << 1);
                }
                i = j;
            } else
            {
                if (i < j)
                {
                    System.arraycopy(mHashes, i + 1, mHashes, i, j - i);
                    System.arraycopy(((Object) (mArray)), i + 1 << 1, ((Object) (mArray)), i << 1, j - i << 1);
                }
                mArray[j << 1] = null;
                mArray[(j << 1) + 1] = null;
                i = j;
            }
        }
        if (l != mSize)
        {
            throw new ConcurrentModificationException();
        } else
        {
            mSize = i;
            return obj;
        }
    }

    public int size()
    {
        return mSize;
    }

    public String toString()
    {
        if (isEmpty())
        {
            return "{}";
        }
        StringBuilder stringbuilder = new StringBuilder(mSize * 28);
        stringbuilder.append('{');
        int i = 0;
        while (i < mSize) 
        {
            if (i > 0)
            {
                stringbuilder.append(", ");
            }
            Object obj = mArray[i << 1];
            if (obj != this)
            {
                stringbuilder.append(obj);
            } else
            {
                stringbuilder.append("(this Map)");
            }
            stringbuilder.append('=');
            obj = mArray[(i << 1) + 1];
            if (obj != this)
            {
                stringbuilder.append(obj);
            } else
            {
                stringbuilder.append("(this Map)");
            }
            i++;
        }
        stringbuilder.append('}');
        return stringbuilder.toString();
    }
}
