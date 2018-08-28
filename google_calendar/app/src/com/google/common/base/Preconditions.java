// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;


// Referenced classes of package com.google.common.base:
//            Strings

public final class Preconditions
{

    static String badPositionIndex(int i, int j, String s)
    {
        if (i < 0)
        {
            return Strings.lenientFormat("%s (%s) must not be negative", new Object[] {
                s, Integer.valueOf(i)
            });
        }
        if (j < 0)
        {
            throw new IllegalArgumentException((new StringBuilder(26)).append("negative size: ").append(j).toString());
        } else
        {
            return Strings.lenientFormat("%s (%s) must not be greater than size (%s)", new Object[] {
                s, Integer.valueOf(i), Integer.valueOf(j)
            });
        }
    }

    public static void checkArgument(boolean flag, String s, int i, int j)
    {
        if (!flag)
        {
            throw new IllegalArgumentException(Strings.lenientFormat(s, new Object[] {
                Integer.valueOf(i), Integer.valueOf(j)
            }));
        } else
        {
            return;
        }
    }

    public static int checkElementIndex(int i, int j)
    {
        if (i < 0 || i >= j)
        {
            String s;
            if (i < 0)
            {
                s = Strings.lenientFormat("%s (%s) must not be negative", new Object[] {
                    "index", Integer.valueOf(i)
                });
            } else
            {
                if (j < 0)
                {
                    throw new IllegalArgumentException((new StringBuilder(26)).append("negative size: ").append(j).toString());
                }
                s = Strings.lenientFormat("%s (%s) must be less than size (%s)", new Object[] {
                    "index", Integer.valueOf(i), Integer.valueOf(j)
                });
            }
            throw new IndexOutOfBoundsException(s);
        } else
        {
            return i;
        }
    }

    public static int checkPositionIndex(int i, int j)
    {
        if (i < 0 || i > j)
        {
            throw new IndexOutOfBoundsException(badPositionIndex(i, j, "index"));
        } else
        {
            return i;
        }
    }

    public static void checkPositionIndexes(int i, int j, int k)
    {
        if (i < 0 || j < i || j > k)
        {
            String s;
            if (i < 0 || i > k)
            {
                s = badPositionIndex(i, k, "start index");
            } else
            if (j < 0 || j > k)
            {
                s = badPositionIndex(j, k, "end index");
            } else
            {
                s = Strings.lenientFormat("end index (%s) must not be less than start index (%s)", new Object[] {
                    Integer.valueOf(j), Integer.valueOf(i)
                });
            }
            throw new IndexOutOfBoundsException(s);
        } else
        {
            return;
        }
    }

    public static void checkState(boolean flag, String s, int i, int j)
    {
        if (!flag)
        {
            throw new IllegalStateException(Strings.lenientFormat(s, new Object[] {
                Integer.valueOf(i), Integer.valueOf(j)
            }));
        } else
        {
            return;
        }
    }
}
