// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;


final class .ItemHolderInfo
{

    public static android.support.v4.util.d.sPool sPool = new android.support.v4.util.d.sPool(20);
    public int flags;
    public .ItemHolderInfo postInfo;
    public .ItemHolderInfo preInfo;

    static void recycle(.ItemHolderInfo itemholderinfo)
    {
        itemholderinfo.flags = 0;
        itemholderinfo.preInfo = null;
        itemholderinfo.postInfo = null;
        sPool.sPool(itemholderinfo);
    }


    .ItemHolderInfo()
    {
    }
}
