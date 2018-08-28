// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;


// Referenced classes of package com.google.common.base:
//            Preconditions

final class FastMatcher extends FastMatcher
{

    public static final PositionIndex INSTANCE = new <init>();

    public final int countIn(CharSequence charsequence)
    {
        if (charsequence == null)
        {
            throw new NullPointerException();
        } else
        {
            return 0;
        }
    }

    public final int indexIn(CharSequence charsequence, int i)
    {
        int j = charsequence.length();
        if (i < 0 || i > j)
        {
            throw new IndexOutOfBoundsException(Preconditions.badPositionIndex(i, j, "index"));
        } else
        {
            return -1;
        }
    }

    public final boolean matches(char c)
    {
        return false;
    }


    private FastMatcher()
    {
        super("CharMatcher.none()");
    }
}
