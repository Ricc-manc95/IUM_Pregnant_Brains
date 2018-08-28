// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;


public final class Constraint
{

    private static final int ALL_CONSTRAINTS[] = {
        2, 1, 4, 8
    };

    static int compact(int ai[])
    {
        int i;
        int k;
        i = 0;
        k = 0;
        if (ai != null) goto _L2; else goto _L1
_L1:
        return k;
_L2:
        int l = ai.length;
        int j = 0;
        do
        {
            k = i;
            if (j >= l)
            {
                continue;
            }
            k = ai[j];
            j++;
            i = k | i;
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
    }

    static int[] uncompact(int i)
    {
        boolean flag = false;
        int ai[] = ALL_CONSTRAINTS;
        int k1 = ai.length;
        int j = 0;
        int k = 0;
        while (j < k1) 
        {
            int l = ai[j];
            if ((i & l) == l)
            {
                l = 1;
            } else
            {
                l = 0;
            }
            k += l;
            j++;
        }
        ai = new int[k];
        int ai1[] = ALL_CONSTRAINTS;
        k1 = ai1.length;
        k = 0;
        j = ((flag) ? 1 : 0);
        for (; k < k1; k++)
        {
            int j1 = ai1[k];
            if ((i & j1) == j1)
            {
                int i1 = j + 1;
                ai[j] = j1;
                j = i1;
            }
        }

        return ai;
    }

}
