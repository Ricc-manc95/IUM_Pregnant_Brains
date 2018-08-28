// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.core.internal;

import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

public class SafeIterableMap
    implements Iterable
{

    public Entry mEnd;
    public WeakHashMap mIterators;
    public int mSize;
    public Entry mStart;

    public SafeIterableMap()
    {
        mIterators = new WeakHashMap();
        mSize = 0;
    }

    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (!(obj instanceof SafeIterableMap))
        {
            return false;
        }
        Object obj1 = (SafeIterableMap)obj;
        if (mSize != ((SafeIterableMap) (obj1)).mSize)
        {
            return false;
        }
        obj = iterator();
        for (obj1 = ((SafeIterableMap) (obj1)).iterator(); ((Iterator) (obj)).hasNext() && ((Iterator) (obj1)).hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)((Iterator) (obj)).next();
            Object obj2 = ((Iterator) (obj1)).next();
            if (entry == null && obj2 != null || entry != null && !entry.equals(obj2))
            {
                return false;
            }
        }

        return !((Iterator) (obj)).hasNext() && !((Iterator) (obj1)).hasNext();
    }

    protected Entry get(Object obj)
    {
        Entry entry;
        for (entry = mStart; entry != null && !entry.mKey.equals(obj); entry = entry.mNext) { }
        return entry;
    }

    public Iterator iterator()
    {
        AscendingIterator ascendingiterator = new AscendingIterator(mStart, mEnd);
        mIterators.put(ascendingiterator, Boolean.valueOf(false));
        return ascendingiterator;
    }

    protected final Entry put(Object obj, Object obj1)
    {
        obj = new Entry(obj, obj1);
        mSize = mSize + 1;
        if (mEnd == null)
        {
            mStart = ((Entry) (obj));
            mEnd = mStart;
            return ((Entry) (obj));
        } else
        {
            mEnd.mNext = ((Entry) (obj));
            obj.mPrevious = mEnd;
            mEnd = ((Entry) (obj));
            return ((Entry) (obj));
        }
    }

    public Object putIfAbsent(Object obj, Object obj1)
    {
        Entry entry = get(obj);
        if (entry != null)
        {
            return entry.mValue;
        } else
        {
            put(obj, obj1);
            return null;
        }
    }

    public Object remove(Object obj)
    {
        obj = get(obj);
        if (obj == null)
        {
            return null;
        }
        mSize = mSize - 1;
        if (!mIterators.isEmpty())
        {
            for (Iterator iterator1 = mIterators.keySet().iterator(); iterator1.hasNext(); ((SupportRemove)iterator1.next()).supportRemove(((Entry) (obj)))) { }
        }
        if (((Entry) (obj)).mPrevious != null)
        {
            ((Entry) (obj)).mPrevious.mNext = ((Entry) (obj)).mNext;
        } else
        {
            mStart = ((Entry) (obj)).mNext;
        }
        if (((Entry) (obj)).mNext != null)
        {
            ((Entry) (obj)).mNext.mPrevious = ((Entry) (obj)).mPrevious;
        } else
        {
            mEnd = ((Entry) (obj)).mPrevious;
        }
        obj.mNext = null;
        obj.mPrevious = null;
        return ((Entry) (obj)).mValue;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("[");
        Iterator iterator1 = iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            stringbuilder.append(((java.util.Map.Entry)iterator1.next()).toString());
            if (iterator1.hasNext())
            {
                stringbuilder.append(", ");
            }
        } while (true);
        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    private class Entry
        implements java.util.Map.Entry
    {

        public final Object mKey;
        public Entry mNext;
        public Entry mPrevious;
        public final Object mValue;

        public final boolean equals(Object obj)
        {
            if (obj != this)
            {
                if (!(obj instanceof Entry))
                {
                    return false;
                }
                obj = (Entry)obj;
                if (!mKey.equals(((Entry) (obj)).mKey) || !mValue.equals(((Entry) (obj)).mValue))
                {
                    return false;
                }
            }
            return true;
        }

        public final Object getKey()
        {
            return mKey;
        }

        public final Object getValue()
        {
            return mValue;
        }

        public final Object setValue(Object obj)
        {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public final String toString()
        {
            return (new StringBuilder()).append(mKey).append("=").append(mValue).toString();
        }

        Entry(Object obj, Object obj1)
        {
            mKey = obj;
            mValue = obj1;
        }
    }


    private class AscendingIterator extends ListIterator
    {
        private class ListIterator
            implements SupportRemove, Iterator
        {

            private SafeIterableMap.Entry mExpectedEnd;
            private SafeIterableMap.Entry mNext;

            abstract SafeIterableMap.Entry backward(SafeIterableMap.Entry entry);

            abstract SafeIterableMap.Entry forward(SafeIterableMap.Entry entry);

            public boolean hasNext()
            {
                return mNext != null;
            }

            public Object next()
            {
                SafeIterableMap.Entry entry1 = mNext;
                SafeIterableMap.Entry entry;
                if (mNext == mExpectedEnd || mExpectedEnd == null)
                {
                    entry = null;
                } else
                {
                    entry = forward(mNext);
                }
                mNext = entry;
                return entry1;
            }

            public final void supportRemove(SafeIterableMap.Entry entry)
            {
                Object obj = null;
                if (mExpectedEnd == entry && entry == mNext)
                {
                    mNext = null;
                    mExpectedEnd = null;
                }
                if (mExpectedEnd == entry)
                {
                    mExpectedEnd = backward(mExpectedEnd);
                }
                if (mNext == entry)
                {
                    entry = obj;
                    if (mNext != mExpectedEnd)
                    {
                        if (mExpectedEnd == null)
                        {
                            entry = obj;
                        } else
                        {
                            entry = forward(mNext);
                        }
                    }
                    mNext = entry;
                }
            }

            ListIterator(SafeIterableMap.Entry entry, SafeIterableMap.Entry entry1)
            {
                mExpectedEnd = entry1;
                mNext = entry;
            }
        }


        final SafeIterableMap.Entry backward(SafeIterableMap.Entry entry)
        {
            return entry.mPrevious;
        }

        final SafeIterableMap.Entry forward(SafeIterableMap.Entry entry)
        {
            return entry.mNext;
        }

        AscendingIterator(SafeIterableMap.Entry entry, SafeIterableMap.Entry entry1)
        {
            super(entry, entry1);
        }
    }


    private class SupportRemove
    {

        public abstract void supportRemove(SafeIterableMap.Entry entry);
    }

}
