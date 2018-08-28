// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.util.SparseArray;
import java.util.ArrayList;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView

public static final class mAttachCount
{

    public int mAttachCount;
    public SparseArray mScrap;

    final ScrapData getScrapDataForType(int i)
    {
        class ScrapData
        {

            public long mBindRunningAverageNs;
            public long mCreateRunningAverageNs;
            public int mMaxScrap;
            public final ArrayList mScrapHeap = new ArrayList();

            ScrapData()
            {
                mMaxScrap = 5;
                mCreateRunningAverageNs = 0L;
                mBindRunningAverageNs = 0L;
            }
        }

        ScrapData scrapdata1 = (ScrapData)mScrap.get(i);
        ScrapData scrapdata = scrapdata1;
        if (scrapdata1 == null)
        {
            scrapdata = new ScrapData();
            mScrap.put(i, scrapdata);
        }
        return scrapdata;
    }

    public final void setMaxRecycledViews(int i, int j)
    {
        Object obj = getScrapDataForType(i);
        obj.mMaxScrap = j;
        for (obj = ((ScrapData) (obj)).mScrapHeap; ((ArrayList) (obj)).size() > j; ((ArrayList) (obj)).remove(((ArrayList) (obj)).size() - 1)) { }
    }

    public ScrapData()
    {
        mScrap = new SparseArray();
        mAttachCount = 0;
    }
}
