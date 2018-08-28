// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.tz;


final class iSaveMillis
{

    public final String iNameKey;
    public final iOfYear iOfYear;
    public final int iSaveMillis;

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj instanceof iSaveMillis)
            {
                if (iSaveMillis != ((iSaveMillis) (obj = (iSaveMillis)obj)).iSaveMillis || !iNameKey.equals(((iNameKey) (obj)).iNameKey) || !iOfYear.ls(((ls) (obj)).iOfYear))
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    ( , String s, int i)
    {
        iOfYear = ;
        iNameKey = s;
        iSaveMillis = i;
    }
}
