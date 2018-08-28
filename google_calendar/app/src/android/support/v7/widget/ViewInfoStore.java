// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.SimpleArrayMap;

final class ViewInfoStore
{

    public final ArrayMap mLayoutHolderMap = new ArrayMap();
    public final LongSparseArray mOldChangedHolders = new LongSparseArray();

    ViewInfoStore()
    {
    }

    final void addToDisappearedInLayout(RecyclerView.ViewHolder viewholder)
    {
        InfoRecord inforecord1 = (InfoRecord)mLayoutHolderMap.get(viewholder);
        InfoRecord inforecord = inforecord1;
        if (inforecord1 == null)
        {
            InfoRecord inforecord2 = (InfoRecord)InfoRecord.sPool.acquire();
            inforecord = inforecord2;
            if (inforecord2 == null)
            {
                inforecord = new InfoRecord();
            }
            mLayoutHolderMap.put(viewholder, inforecord);
        }
        inforecord.flags = inforecord.flags | 1;
    }

    final void addToPostLayout(RecyclerView.ViewHolder viewholder, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo)
    {
        InfoRecord inforecord1 = (InfoRecord)mLayoutHolderMap.get(viewholder);
        InfoRecord inforecord = inforecord1;
        if (inforecord1 == null)
        {
            InfoRecord inforecord2 = (InfoRecord)InfoRecord.sPool.acquire();
            inforecord = inforecord2;
            if (inforecord2 == null)
            {
                inforecord = new InfoRecord();
            }
            mLayoutHolderMap.put(viewholder, inforecord);
        }
        inforecord.postInfo = itemholderinfo;
        inforecord.flags = inforecord.flags | 8;
    }

    final void addToPreLayout(RecyclerView.ViewHolder viewholder, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo)
    {
        InfoRecord inforecord1 = (InfoRecord)mLayoutHolderMap.get(viewholder);
        InfoRecord inforecord = inforecord1;
        if (inforecord1 == null)
        {
            InfoRecord inforecord2 = (InfoRecord)InfoRecord.sPool.acquire();
            inforecord = inforecord2;
            if (inforecord2 == null)
            {
                inforecord = new InfoRecord();
            }
            mLayoutHolderMap.put(viewholder, inforecord);
        }
        inforecord.preInfo = itemholderinfo;
        inforecord.flags = inforecord.flags | 4;
    }

    final RecyclerView.ItemAnimator.ItemHolderInfo popFromLayoutStep(RecyclerView.ViewHolder viewholder, int i)
    {
        Object obj1 = null;
        Object obj = mLayoutHolderMap;
        int j;
        if (viewholder == null)
        {
            j = ((SimpleArrayMap) (obj)).indexOfNull();
        } else
        {
            j = ((SimpleArrayMap) (obj)).indexOf(viewholder, viewholder.hashCode());
        }
        if (j < 0)
        {
            obj = obj1;
        } else
        {
            InfoRecord inforecord = (InfoRecord)((SimpleArrayMap) (mLayoutHolderMap)).mArray[(j << 1) + 1];
            obj = obj1;
            if (inforecord != null)
            {
                obj = obj1;
                if ((inforecord.flags & i) != 0)
                {
                    inforecord.flags = inforecord.flags & ~i;
                    if (i == 4)
                    {
                        viewholder = inforecord.preInfo;
                    } else
                    if (i == 8)
                    {
                        viewholder = inforecord.postInfo;
                    } else
                    {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    obj = viewholder;
                    if ((inforecord.flags & 0xc) == 0)
                    {
                        mLayoutHolderMap.removeAt(j);
                        InfoRecord.recycle(inforecord);
                        return viewholder;
                    }
                }
            }
        }
        return ((RecyclerView.ItemAnimator.ItemHolderInfo) (obj));
    }

    final void removeViewHolder(RecyclerView.ViewHolder viewholder)
    {
        LongSparseArray longsparsearray = mOldChangedHolders;
        if (longsparsearray.mGarbage)
        {
            longsparsearray.gc();
        }
        int i = longsparsearray.mSize - 1;
        do
        {
label0:
            {
                if (i >= 0)
                {
                    LongSparseArray longsparsearray1 = mOldChangedHolders;
                    if (longsparsearray1.mGarbage)
                    {
                        longsparsearray1.gc();
                    }
                    if (viewholder != longsparsearray1.mValues[i])
                    {
                        break label0;
                    }
                    longsparsearray1 = mOldChangedHolders;
                    if (longsparsearray1.mValues[i] != LongSparseArray.DELETED)
                    {
                        longsparsearray1.mValues[i] = LongSparseArray.DELETED;
                        longsparsearray1.mGarbage = true;
                    }
                }
                viewholder = (InfoRecord)mLayoutHolderMap.remove(viewholder);
                if (viewholder != null)
                {
                    InfoRecord.recycle(viewholder);
                }
                return;
            }
            i--;
        } while (true);
    }

    private class InfoRecord
    {

        public static android.support.v4.util.Pools.Pool sPool = new android.support.v4.util.Pools.SimplePool(20);
        public int flags;
        public RecyclerView.ItemAnimator.ItemHolderInfo postInfo;
        public RecyclerView.ItemAnimator.ItemHolderInfo preInfo;

        static void recycle(InfoRecord inforecord)
        {
            inforecord.flags = 0;
            inforecord.preInfo = null;
            inforecord.postInfo = null;
            sPool.release(inforecord);
        }


        InfoRecord()
        {
        }
    }

}
