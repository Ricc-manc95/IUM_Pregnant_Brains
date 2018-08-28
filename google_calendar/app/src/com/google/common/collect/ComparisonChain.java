// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Comparator;

public abstract class ComparisonChain
{

    public static final ComparisonChain ACTIVE = new _cls1();
    public static final ComparisonChain GREATER = new InactiveComparisonChain(1);
    public static final ComparisonChain LESS = new InactiveComparisonChain(-1);

    private ComparisonChain()
    {
    }

    ComparisonChain(byte byte0)
    {
        this();
    }

    public abstract ComparisonChain compare(double d, double d1);

    public abstract ComparisonChain compare(float f, float f1);

    public abstract ComparisonChain compare(int i, int j);

    public abstract ComparisonChain compare(long l, long l1);

    public abstract ComparisonChain compare(Comparable comparable, Comparable comparable1);

    public abstract ComparisonChain compare(Object obj, Object obj1, Comparator comparator);

    public abstract ComparisonChain compareTrueFirst(boolean flag, boolean flag1);

    public abstract int result();


    private class _cls1 extends ComparisonChain
    {

        public final ComparisonChain compare(double d, double d1)
        {
            int i = Double.compare(d, d1);
            if (i < 0)
            {
                return ComparisonChain.LESS;
            }
            if (i > 0)
            {
                return ComparisonChain.GREATER;
            } else
            {
                return ComparisonChain.ACTIVE;
            }
        }

        public final ComparisonChain compare(float f, float f1)
        {
            int i = Float.compare(f, f1);
            if (i < 0)
            {
                return ComparisonChain.LESS;
            }
            if (i > 0)
            {
                return ComparisonChain.GREATER;
            } else
            {
                return ComparisonChain.ACTIVE;
            }
        }

        public final ComparisonChain compare(int i, int j)
        {
            if (i < j)
            {
                i = -1;
            } else
            if (i > j)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i < 0)
            {
                return ComparisonChain.LESS;
            }
            if (i > 0)
            {
                return ComparisonChain.GREATER;
            } else
            {
                return ComparisonChain.ACTIVE;
            }
        }

        public final ComparisonChain compare(long l, long l1)
        {
            byte byte0;
            if (l < l1)
            {
                byte0 = -1;
            } else
            if (l > l1)
            {
                byte0 = 1;
            } else
            {
                byte0 = 0;
            }
            if (byte0 < 0)
            {
                return ComparisonChain.LESS;
            }
            if (byte0 > 0)
            {
                return ComparisonChain.GREATER;
            } else
            {
                return ComparisonChain.ACTIVE;
            }
        }

        public final ComparisonChain compare(Comparable comparable, Comparable comparable1)
        {
            int i = comparable.compareTo(comparable1);
            if (i < 0)
            {
                return ComparisonChain.LESS;
            }
            if (i > 0)
            {
                return ComparisonChain.GREATER;
            } else
            {
                return ComparisonChain.ACTIVE;
            }
        }

        public final ComparisonChain compare(Object obj, Object obj1, Comparator comparator)
        {
            int i = comparator.compare(obj, obj1);
            if (i < 0)
            {
                return ComparisonChain.LESS;
            }
            if (i > 0)
            {
                return ComparisonChain.GREATER;
            } else
            {
                return ComparisonChain.ACTIVE;
            }
        }

        public final ComparisonChain compareTrueFirst(boolean flag, boolean flag1)
        {
            int i;
            if (flag1 == flag)
            {
                i = 0;
            } else
            if (flag1)
            {
                i = 1;
            } else
            {
                i = -1;
            }
            if (i < 0)
            {
                return ComparisonChain.LESS;
            }
            if (i > 0)
            {
                return ComparisonChain.GREATER;
            } else
            {
                return ComparisonChain.ACTIVE;
            }
        }

        public final int result()
        {
            return 0;
        }

        _cls1()
        {
            super((byte)0);
        }
    }


    private class InactiveComparisonChain extends ComparisonChain
    {

        private final int result;

        public final ComparisonChain compare(double d, double d1)
        {
            return this;
        }

        public final ComparisonChain compare(float f, float f1)
        {
            return this;
        }

        public final ComparisonChain compare(int i, int j)
        {
            return this;
        }

        public final ComparisonChain compare(long l, long l1)
        {
            return this;
        }

        public final ComparisonChain compare(Comparable comparable, Comparable comparable1)
        {
            return this;
        }

        public final ComparisonChain compare(Object obj, Object obj1, Comparator comparator)
        {
            return this;
        }

        public final ComparisonChain compareTrueFirst(boolean flag, boolean flag1)
        {
            return this;
        }

        public final int result()
        {
            return result;
        }

        InactiveComparisonChain(int i)
        {
            super((byte)0);
            result = i;
        }
    }

}
