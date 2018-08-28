// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.graphics;


final class lter
    implements lter
{

    public final boolean isAllowed(int i, float af[])
    {
        if (af[2] >= 0.95F)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            if (af[2] <= 0.05F)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                if (af[0] >= 10F && af[0] <= 37F && af[1] <= 0.82F)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    return true;
                }
            }
        }
        return false;
    }

    lter()
    {
    }
}
