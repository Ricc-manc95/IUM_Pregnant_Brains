// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import java.util.ArrayList;

public final class mBindRunningAverageNs
{

    public long mBindRunningAverageNs;
    public long mCreateRunningAverageNs;
    public int mMaxScrap;
    public final ArrayList mScrapHeap = new ArrayList();

    ()
    {
        mMaxScrap = 5;
        mCreateRunningAverageNs = 0L;
        mBindRunningAverageNs = 0L;
    }
}
