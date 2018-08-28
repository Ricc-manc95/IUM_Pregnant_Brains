// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.io.Serializable;

// Referenced classes of package com.google.common.collect:
//            Range

public abstract class Cut
    implements Serializable, Comparable
{

    public static final long serialVersionUID = 0L;
    public final Comparable endpoint;

    Cut(Comparable comparable)
    {
        endpoint = comparable;
    }

    public int compareTo(Cut cut)
    {
        if (cut != BelowAll.INSTANCE)
        {
            if (cut == AboveAll.INSTANCE)
            {
                return -1;
            }
            int i = Range.compareOrThrow(endpoint, cut.endpoint);
            if (i != 0)
            {
                return i;
            }
            boolean flag = this instanceof AboveValue;
            if (flag == (cut instanceof AboveValue))
            {
                return 0;
            }
            if (!flag)
            {
                return -1;
            }
        }
        return 1;
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((Cut)obj);
    }

    abstract void describeAsLowerBound(StringBuilder stringbuilder);

    abstract void describeAsUpperBound(StringBuilder stringbuilder);

    public Comparable endpoint()
    {
        return endpoint;
    }

    public boolean equals(Object obj)
    {
        boolean flag1 = false;
        boolean flag = flag1;
        if (obj instanceof Cut)
        {
            obj = (Cut)obj;
            int i;
            try
            {
                i = compareTo(((Cut) (obj)));
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                return false;
            }
            flag = flag1;
            if (i == 0)
            {
                flag = true;
            }
        }
        return flag;
    }

    public abstract int hashCode();

    abstract boolean isLessThan(Comparable comparable);

    private class BelowAll extends Cut
    {

        public static final BelowAll INSTANCE = new BelowAll();
        public static final long serialVersionUID = 0L;

        private final Object readResolve()
        {
            return INSTANCE;
        }

        public final int compareTo(Cut cut)
        {
            return cut != this ? -1 : 0;
        }

        public final volatile int compareTo(Object obj)
        {
            return compareTo((Cut)obj);
        }

        final void describeAsLowerBound(StringBuilder stringbuilder)
        {
            stringbuilder.append("(-\u221E");
        }

        final void describeAsUpperBound(StringBuilder stringbuilder)
        {
            throw new AssertionError();
        }

        final Comparable endpoint()
        {
            throw new IllegalStateException("range unbounded on this side");
        }

        public final int hashCode()
        {
            return System.identityHashCode(this);
        }

        final boolean isLessThan(Comparable comparable)
        {
            return true;
        }

        public final String toString()
        {
            return "-\u221E";
        }


        private BelowAll()
        {
            super(null);
        }
    }


    private class AboveAll extends Cut
    {

        public static final AboveAll INSTANCE = new AboveAll();
        public static final long serialVersionUID = 0L;

        private final Object readResolve()
        {
            return INSTANCE;
        }

        public final int compareTo(Cut cut)
        {
            return cut != this ? 1 : 0;
        }

        public final volatile int compareTo(Object obj)
        {
            return compareTo((Cut)obj);
        }

        final void describeAsLowerBound(StringBuilder stringbuilder)
        {
            throw new AssertionError();
        }

        final void describeAsUpperBound(StringBuilder stringbuilder)
        {
            stringbuilder.append("+\u221E)");
        }

        final Comparable endpoint()
        {
            throw new IllegalStateException("range unbounded on this side");
        }

        public final int hashCode()
        {
            return System.identityHashCode(this);
        }

        final boolean isLessThan(Comparable comparable)
        {
            return false;
        }

        public final String toString()
        {
            return "+\u221E";
        }


        private AboveAll()
        {
            super(null);
        }
    }


    private class AboveValue extends Cut
    {

        public static final long serialVersionUID = 0L;

        final void describeAsLowerBound(StringBuilder stringbuilder)
        {
            stringbuilder.append('(').append(endpoint);
        }

        final void describeAsUpperBound(StringBuilder stringbuilder)
        {
            stringbuilder.append(endpoint).append(']');
        }

        public final int hashCode()
        {
            return ~endpoint.hashCode();
        }

        final boolean isLessThan(Comparable comparable)
        {
            return Range.compareOrThrow(endpoint, comparable) < 0;
        }

        public final String toString()
        {
            String s = String.valueOf(endpoint);
            return (new StringBuilder(String.valueOf(s).length() + 2)).append("/").append(s).append("\\").toString();
        }

        AboveValue(Comparable comparable)
        {
            if (comparable == null)
            {
                throw new NullPointerException();
            } else
            {
                super((Comparable)comparable);
                return;
            }
        }
    }

}
