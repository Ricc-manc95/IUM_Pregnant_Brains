// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Comparator;

// Referenced classes of package com.google.common.collect:
//            ComparisonChain

final class nit> extends ComparisonChain
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

    ()
    {
        super((byte)0);
    }
}
