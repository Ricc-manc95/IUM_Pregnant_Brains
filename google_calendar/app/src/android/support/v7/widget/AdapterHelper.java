// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.v7.widget:
//            OpReorderer

public final class AdapterHelper
    implements OpReorderer.Callback
{

    private final Callback mCallback;
    private final boolean mDisableRecycler;
    public int mExistingUpdateTypes;
    private final OpReorderer mOpReorderer;
    public final ArrayList mPendingUpdates;
    public final ArrayList mPostponedList;
    private android.support.v4.util.Pools.Pool mUpdateOpPool;

    AdapterHelper(Callback callback)
    {
        this(callback, false);
    }

    private AdapterHelper(Callback callback, boolean flag)
    {
        mUpdateOpPool = new android.support.v4.util.Pools.SimplePool(30);
        mPendingUpdates = new ArrayList();
        mPostponedList = new ArrayList();
        mExistingUpdateTypes = 0;
        mCallback = callback;
        mDisableRecycler = false;
        mOpReorderer = new OpReorderer(this);
    }

    private final boolean canFindInPreLayout(int i)
    {
        int l = mPostponedList.size();
label0:
        for (int j = 0; j < l; j++)
        {
            UpdateOp updateop = (UpdateOp)mPostponedList.get(j);
            if (updateop.cmd == 8)
            {
                if (findPositionOffset(updateop.itemCount, j + 1) == i)
                {
                    return true;
                }
                continue;
            }
            if (updateop.cmd != 1)
            {
                continue;
            }
            int i1 = updateop.positionStart;
            int j1 = updateop.itemCount;
            int k = updateop.positionStart;
            do
            {
                if (k >= i1 + j1)
                {
                    continue label0;
                }
                if (findPositionOffset(k, j + 1) == i)
                {
                    return true;
                }
                k++;
            } while (true);
        }

        return false;
    }

    private final void dispatchAndUpdateViewHolders(UpdateOp updateop)
    {
        int j;
        int j1;
        if (updateop.cmd == 1 || updateop.cmd == 8)
        {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        j1 = updatePositionWithPostponed(updateop.positionStart, updateop.cmd);
        j = updateop.positionStart;
        updateop.cmd;
        JVM INSTR tableswitch 2 4: default 76
    //                   2 191
    //                   3 76
    //                   4 100;
           goto _L1 _L2 _L1 _L3
_L1:
        throw new IllegalArgumentException((new StringBuilder("op should be remove or update.")).append(updateop).toString());
_L3:
        int k = 1;
_L8:
        int l;
        int i1;
        i1 = 1;
        l = 1;
_L7:
        int k1;
        if (l >= updateop.itemCount)
        {
            break MISSING_BLOCK_LABEL_313;
        }
        k1 = updatePositionWithPostponed(updateop.positionStart + k * l, updateop.cmd);
        updateop.cmd;
        JVM INSTR tableswitch 2 4: default 168
    //                   2 216
    //                   3 168
    //                   4 197;
           goto _L4 _L5 _L4 _L6
_L4:
        int i = 0;
_L9:
        if (i != 0)
        {
            i = i1 + 1;
        } else
        {
            UpdateOp updateop1 = obtainUpdateOp(updateop.cmd, j1, i1, updateop.payload);
            dispatchFirstPassAndUpdateViewHolders(updateop1, j);
            if (!mDisableRecycler)
            {
                updateop1.payload = null;
                mUpdateOpPool.release(updateop1);
            }
            i = j;
            if (updateop.cmd == 4)
            {
                i = j + i1;
            }
            i1 = 1;
            j1 = k1;
            j = i;
            i = i1;
        }
        l++;
        i1 = i;
          goto _L7
_L2:
        k = 0;
          goto _L8
_L6:
        if (k1 == j1 + 1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
          goto _L9
_L5:
        if (k1 == j1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
          goto _L9
        Object obj = updateop.payload;
        if (!mDisableRecycler)
        {
            updateop.payload = null;
            mUpdateOpPool.release(updateop);
        }
        if (i1 > 0)
        {
            updateop = obtainUpdateOp(updateop.cmd, j1, i1, obj);
            dispatchFirstPassAndUpdateViewHolders(updateop, j);
            if (!mDisableRecycler)
            {
                updateop.payload = null;
                mUpdateOpPool.release(updateop);
            }
        }
        return;
          goto _L7
    }

    private final void dispatchFirstPassAndUpdateViewHolders(UpdateOp updateop, int i)
    {
        mCallback.onDispatchFirstPass(updateop);
        switch (updateop.cmd)
        {
        case 3: // '\003'
        default:
            throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");

        case 2: // '\002'
            mCallback.offsetPositionsForRemovingInvisible(i, updateop.itemCount);
            return;

        case 4: // '\004'
            mCallback.markViewHoldersUpdated(i, updateop.itemCount, updateop.payload);
            break;
        }
    }

    private final void postponeAndUpdateViewHolders(UpdateOp updateop)
    {
        mPostponedList.add(updateop);
        switch (updateop.cmd)
        {
        case 3: // '\003'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        default:
            throw new IllegalArgumentException((new StringBuilder("Unknown update op type for ")).append(updateop).toString());

        case 1: // '\001'
            mCallback.offsetPositionsForAdd(updateop.positionStart, updateop.itemCount);
            return;

        case 8: // '\b'
            mCallback.offsetPositionsForMove(updateop.positionStart, updateop.itemCount);
            return;

        case 2: // '\002'
            mCallback.offsetPositionsForRemovingLaidOutOrNewView(updateop.positionStart, updateop.itemCount);
            return;

        case 4: // '\004'
            mCallback.markViewHoldersUpdated(updateop.positionStart, updateop.itemCount, updateop.payload);
            return;
        }
    }

    private final int updatePositionWithPostponed(int i, int j)
    {
        int k = mPostponedList.size() - 1;
        do
        {
            if (k >= 0)
            {
                UpdateOp updateop = (UpdateOp)mPostponedList.get(k);
                if (updateop.cmd == 8)
                {
                    int l;
                    int i1;
                    if (updateop.positionStart < updateop.itemCount)
                    {
                        i1 = updateop.positionStart;
                        l = updateop.itemCount;
                    } else
                    {
                        i1 = updateop.itemCount;
                        l = updateop.positionStart;
                    }
                    if (i >= i1 && i <= l)
                    {
                        if (i1 == updateop.positionStart)
                        {
                            if (j == 1)
                            {
                                updateop.itemCount = updateop.itemCount + 1;
                            } else
                            if (j == 2)
                            {
                                updateop.itemCount = updateop.itemCount - 1;
                            }
                            i++;
                        } else
                        {
                            if (j == 1)
                            {
                                updateop.positionStart = updateop.positionStart + 1;
                            } else
                            if (j == 2)
                            {
                                updateop.positionStart = updateop.positionStart - 1;
                            }
                            i--;
                        }
                    } else
                    if (i < updateop.positionStart)
                    {
                        if (j == 1)
                        {
                            updateop.positionStart = updateop.positionStart + 1;
                            updateop.itemCount = updateop.itemCount + 1;
                        } else
                        if (j == 2)
                        {
                            updateop.positionStart = updateop.positionStart - 1;
                            updateop.itemCount = updateop.itemCount - 1;
                        }
                    }
                } else
                if (updateop.positionStart <= i)
                {
                    if (updateop.cmd == 1)
                    {
                        i -= updateop.itemCount;
                    } else
                    if (updateop.cmd == 2)
                    {
                        i = updateop.itemCount + i;
                    }
                } else
                if (j == 1)
                {
                    updateop.positionStart = updateop.positionStart + 1;
                } else
                if (j == 2)
                {
                    updateop.positionStart = updateop.positionStart - 1;
                }
                k--;
                continue;
            }
            j = mPostponedList.size() - 1;
            while (j >= 0) 
            {
                UpdateOp updateop1 = (UpdateOp)mPostponedList.get(j);
                if (updateop1.cmd == 8)
                {
                    if (updateop1.itemCount == updateop1.positionStart || updateop1.itemCount < 0)
                    {
                        mPostponedList.remove(j);
                        if (!mDisableRecycler)
                        {
                            updateop1.payload = null;
                            mUpdateOpPool.release(updateop1);
                        }
                    }
                } else
                if (updateop1.itemCount <= 0)
                {
                    mPostponedList.remove(j);
                    if (!mDisableRecycler)
                    {
                        updateop1.payload = null;
                        mUpdateOpPool.release(updateop1);
                    }
                }
                j--;
            }
            return i;
        } while (true);
    }

    final void consumePostponedUpdates()
    {
        int j = mPostponedList.size();
        for (int i = 0; i < j; i++)
        {
            mCallback.onDispatchSecondPass((UpdateOp)mPostponedList.get(i));
        }

        recycleUpdateOpsAndClearList(mPostponedList);
        mExistingUpdateTypes = 0;
    }

    final void consumeUpdatesInOnePass()
    {
        int i;
        int j;
        consumePostponedUpdates();
        j = mPendingUpdates.size();
        i = 0;
_L7:
        UpdateOp updateop;
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_211;
        }
        updateop = (UpdateOp)mPendingUpdates.get(i);
        updateop.cmd;
        JVM INSTR tableswitch 1 8: default 80
    //                   1 87
    //                   2 117
    //                   3 80
    //                   4 147
    //                   5 80
    //                   6 80
    //                   7 80
    //                   8 181;
           goto _L1 _L2 _L3 _L1 _L4 _L1 _L1 _L1 _L5
_L5:
        break MISSING_BLOCK_LABEL_181;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L8:
        i++;
        if (true) goto _L7; else goto _L6
_L6:
        mCallback.onDispatchSecondPass(updateop);
        mCallback.offsetPositionsForAdd(updateop.positionStart, updateop.itemCount);
          goto _L8
_L3:
        mCallback.onDispatchSecondPass(updateop);
        mCallback.offsetPositionsForRemovingInvisible(updateop.positionStart, updateop.itemCount);
          goto _L8
_L4:
        mCallback.onDispatchSecondPass(updateop);
        mCallback.markViewHoldersUpdated(updateop.positionStart, updateop.itemCount, updateop.payload);
          goto _L8
        mCallback.onDispatchSecondPass(updateop);
        mCallback.offsetPositionsForMove(updateop.positionStart, updateop.itemCount);
          goto _L8
        recycleUpdateOpsAndClearList(mPendingUpdates);
        mExistingUpdateTypes = 0;
        return;
    }

    final int findPositionOffset(int i, int j)
    {
        int l;
        int i1;
        i1 = mPostponedList.size();
        l = j;
        j = i;
_L5:
        i = j;
        if (l >= i1) goto _L2; else goto _L1
_L1:
        UpdateOp updateop = (UpdateOp)mPostponedList.get(l);
        if (updateop.cmd != 8) goto _L4; else goto _L3
_L3:
        if (updateop.positionStart == j)
        {
            i = updateop.itemCount;
        } else
        {
            int k = j;
            if (updateop.positionStart < j)
            {
                k = j - 1;
            }
            i = k;
            if (updateop.itemCount <= k)
            {
                i = k + 1;
            }
        }
_L7:
        l++;
        j = i;
          goto _L5
_L4:
        i = j;
        if (updateop.positionStart > j) goto _L7; else goto _L6
_L6:
        if (updateop.cmd != 2)
        {
            break MISSING_BLOCK_LABEL_150;
        }
        if (j >= updateop.positionStart + updateop.itemCount) goto _L9; else goto _L8
_L8:
        i = -1;
_L2:
        return i;
_L9:
        i = j - updateop.itemCount;
          goto _L7
        i = j;
        if (updateop.cmd == 1)
        {
            i = j + updateop.itemCount;
        }
          goto _L7
    }

    public final UpdateOp obtainUpdateOp(int i, int j, int k, Object obj)
    {
        UpdateOp updateop = (UpdateOp)mUpdateOpPool.acquire();
        if (updateop == null)
        {
            return new UpdateOp(i, j, k, obj);
        } else
        {
            updateop.cmd = i;
            updateop.positionStart = j;
            updateop.itemCount = k;
            updateop.payload = obj;
            return updateop;
        }
    }

    final void preProcess()
    {
        OpReorderer opreorderer;
        ArrayList arraylist;
        opreorderer = mOpReorderer;
        arraylist = mPendingUpdates;
_L16:
        int i;
        int i1;
        i1 = 0;
        i = arraylist.size() - 1;
_L9:
        if (i < 0) goto _L2; else goto _L1
_L1:
        if (((UpdateOp)arraylist.get(i)).cmd != 8) goto _L4; else goto _L3
_L3:
        if (i1 == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        i1 = i;
_L10:
        UpdateOp updateop3;
        UpdateOp updateop4;
        int i4;
        if (i1 == -1)
        {
            break; /* Loop/switch isn't completed */
        }
        i4 = i1 + 1;
        updateop3 = (UpdateOp)arraylist.get(i1);
        updateop4 = (UpdateOp)arraylist.get(i4);
        updateop4.cmd;
        JVM INSTR tableswitch 1 4: default 136
    //                   1 139
    //                   2 297
    //                   3 136
    //                   4 995;
           goto _L5 _L6 _L7 _L5 _L8
_L5:
        continue; /* Loop/switch isn't completed */
_L6:
        i = 0;
        if (updateop3.itemCount < updateop4.positionStart)
        {
            i = -1;
        }
        int k1 = i;
        if (updateop3.positionStart < updateop4.positionStart)
        {
            k1 = i + 1;
        }
        if (updateop4.positionStart <= updateop3.positionStart)
        {
            updateop3.positionStart = updateop3.positionStart + updateop4.itemCount;
        }
        if (updateop4.positionStart <= updateop3.itemCount)
        {
            updateop3.itemCount = updateop3.itemCount + updateop4.itemCount;
        }
        updateop4.positionStart = k1 + updateop4.positionStart;
        arraylist.set(i1, updateop4);
        arraylist.set(i4, updateop3);
        continue; /* Loop/switch isn't completed */
_L4:
        i1 = 1;
        i--;
          goto _L9
_L2:
        i1 = -1;
          goto _L10
_L7:
        UpdateOp updateop;
        boolean flag1;
        updateop = null;
        boolean flag2 = false;
        boolean flag;
        if (updateop3.positionStart < updateop3.itemCount)
        {
            boolean flag3 = false;
            flag1 = flag3;
            flag = flag2;
            if (updateop4.positionStart == updateop3.positionStart)
            {
                flag1 = flag3;
                flag = flag2;
                if (updateop4.itemCount == updateop3.itemCount - updateop3.positionStart)
                {
                    flag = true;
                    flag1 = flag3;
                }
            }
        } else
        {
            boolean flag4 = true;
            flag1 = flag4;
            flag = flag2;
            if (updateop4.positionStart == updateop3.itemCount + 1)
            {
                flag1 = flag4;
                flag = flag2;
                if (updateop4.itemCount == updateop3.positionStart - updateop3.itemCount)
                {
                    flag = true;
                    flag1 = flag4;
                }
            }
        }
        if (updateop3.itemCount >= updateop4.positionStart) goto _L12; else goto _L11
_L11:
        updateop4.positionStart = updateop4.positionStart - 1;
_L14:
        if (updateop3.positionStart <= updateop4.positionStart)
        {
            updateop4.positionStart = updateop4.positionStart + 1;
        } else
        if (updateop3.positionStart < updateop4.positionStart + updateop4.itemCount)
        {
            int j2 = updateop4.positionStart;
            int i3 = updateop4.itemCount;
            int j4 = updateop3.positionStart;
            updateop = opreorderer.mCallback.obtainUpdateOp(2, updateop3.positionStart + 1, (j2 + i3) - j4, null);
            updateop4.itemCount = updateop3.positionStart - updateop4.positionStart;
        }
        if (flag)
        {
            arraylist.set(i1, updateop4);
            arraylist.remove(i4);
            opreorderer.mCallback.recycleUpdateOp(updateop3);
        } else
        {
            if (flag1)
            {
                if (updateop != null)
                {
                    if (updateop3.positionStart > updateop.positionStart)
                    {
                        updateop3.positionStart = updateop3.positionStart - updateop.itemCount;
                    }
                    if (updateop3.itemCount > updateop.positionStart)
                    {
                        updateop3.itemCount = updateop3.itemCount - updateop.itemCount;
                    }
                }
                if (updateop3.positionStart > updateop4.positionStart)
                {
                    updateop3.positionStart = updateop3.positionStart - updateop4.itemCount;
                }
                if (updateop3.itemCount > updateop4.positionStart)
                {
                    updateop3.itemCount = updateop3.itemCount - updateop4.itemCount;
                }
            } else
            {
                if (updateop != null)
                {
                    if (updateop3.positionStart >= updateop.positionStart)
                    {
                        updateop3.positionStart = updateop3.positionStart - updateop.itemCount;
                    }
                    if (updateop3.itemCount >= updateop.positionStart)
                    {
                        updateop3.itemCount = updateop3.itemCount - updateop.itemCount;
                    }
                }
                if (updateop3.positionStart >= updateop4.positionStart)
                {
                    updateop3.positionStart = updateop3.positionStart - updateop4.itemCount;
                }
                if (updateop3.itemCount >= updateop4.positionStart)
                {
                    updateop3.itemCount = updateop3.itemCount - updateop4.itemCount;
                }
            }
            arraylist.set(i1, updateop4);
            if (updateop3.positionStart != updateop3.itemCount)
            {
                arraylist.set(i4, updateop3);
            } else
            {
                arraylist.remove(i4);
            }
            if (updateop != null)
            {
                arraylist.add(i1, updateop);
            }
        }
        continue; /* Loop/switch isn't completed */
_L12:
        if (updateop3.itemCount >= updateop4.positionStart + updateop4.itemCount) goto _L14; else goto _L13
_L13:
        updateop4.itemCount = updateop4.itemCount - 1;
        updateop3.cmd = 2;
        updateop3.itemCount = 1;
        if (updateop4.itemCount == 0)
        {
            arraylist.remove(i4);
            opreorderer.mCallback.recycleUpdateOp(updateop4);
        }
        continue; /* Loop/switch isn't completed */
_L8:
        UpdateOp updateop1;
        updateop = null;
        updateop1 = null;
        if (updateop3.itemCount < updateop4.positionStart)
        {
            updateop4.positionStart = updateop4.positionStart - 1;
        } else
        if (updateop3.itemCount < updateop4.positionStart + updateop4.itemCount)
        {
            updateop4.itemCount = updateop4.itemCount - 1;
            updateop = opreorderer.mCallback.obtainUpdateOp(4, updateop3.positionStart, 1, updateop4.payload);
        }
_L25:
        if (updateop3.positionStart <= updateop4.positionStart)
        {
            updateop4.positionStart = updateop4.positionStart + 1;
        } else
        if (updateop3.positionStart < updateop4.positionStart + updateop4.itemCount)
        {
            int j = (updateop4.positionStart + updateop4.itemCount) - updateop3.positionStart;
            updateop1 = opreorderer.mCallback.obtainUpdateOp(4, updateop3.positionStart + 1, j, updateop4.payload);
            updateop4.itemCount = updateop4.itemCount - j;
        }
        arraylist.set(i4, updateop3);
        if (updateop4.itemCount > 0)
        {
            arraylist.set(i1, updateop4);
        } else
        {
            arraylist.remove(i1);
            opreorderer.mCallback.recycleUpdateOp(updateop4);
        }
        if (updateop != null)
        {
            arraylist.add(i1, updateop);
        }
        if (updateop1 != null)
        {
            arraylist.add(i1, updateop1);
        }
        if (true) goto _L16; else goto _L15
_L15:
        int l4;
        l4 = mPendingUpdates.size();
        i4 = 0;
_L24:
        if (i4 >= l4) goto _L18; else goto _L17
_L17:
        updateop = (UpdateOp)mPendingUpdates.get(i4);
        updateop.cmd;
        JVM INSTR tableswitch 1 8: default 1352
    //                   1 1361
    //                   2 1369
    //                   3 1352
    //                   4 1630
    //                   5 1352
    //                   6 1352
    //                   7 1352
    //                   8 1879;
           goto _L19 _L20 _L21 _L19 _L22 _L19 _L19 _L19 _L23
_L19:
        i4++;
          goto _L24
_L20:
        postponeAndUpdateViewHolders(updateop);
          goto _L19
_L21:
        int i5 = updateop.positionStart;
        int k = updateop.positionStart;
        int l1 = updateop.itemCount + k;
        byte byte1 = -1;
        k = updateop.positionStart;
        int j3 = 0;
        while (k < l1) 
        {
            byte byte0 = 0;
            int k2 = 0;
            if (mCallback.findViewHolder(k) != null || canFindInPreLayout(k))
            {
                if (byte1 == 0)
                {
                    dispatchAndUpdateViewHolders(obtainUpdateOp(2, i5, j3, null));
                    k2 = 1;
                }
                byte0 = 1;
            } else
            {
                if (byte1 == 1)
                {
                    postponeAndUpdateViewHolders(obtainUpdateOp(2, i5, j3, null));
                    byte0 = 1;
                }
                byte1 = 0;
                k2 = byte0;
                byte0 = byte1;
            }
            if (k2 != 0)
            {
                k2 = k - j3;
                k = l1 - j3;
                l1 = 1;
            } else
            {
                j3++;
                k2 = k;
                k = l1;
                l1 = j3;
            }
            j3 = l1;
            l1 = k;
            k = k2 + 1;
            byte1 = byte0;
        }
        UpdateOp updateop2 = updateop;
        if (j3 != updateop.itemCount)
        {
            if (!mDisableRecycler)
            {
                updateop.payload = null;
                mUpdateOpPool.release(updateop);
            }
            updateop2 = obtainUpdateOp(2, i5, j3, null);
        }
        if (byte1 == 0)
        {
            dispatchAndUpdateViewHolders(updateop2);
        } else
        {
            postponeAndUpdateViewHolders(updateop2);
        }
          goto _L19
_L22:
        int i2 = updateop.positionStart;
        int j5 = updateop.positionStart;
        int k5 = updateop.itemCount;
        int l = updateop.positionStart;
        int j1 = 0;
        int k4 = -1;
        while (l < j5 + k5) 
        {
            int l2;
            if (mCallback.findViewHolder(l) != null || canFindInPreLayout(l))
            {
                l2 = j1;
                int k3 = i2;
                if (k4 == 0)
                {
                    dispatchAndUpdateViewHolders(obtainUpdateOp(4, i2, j1, updateop.payload));
                    l2 = 0;
                    k3 = l;
                }
                i2 = k3;
                j1 = 1;
            } else
            {
                l2 = j1;
                int l3 = i2;
                if (k4 == 1)
                {
                    postponeAndUpdateViewHolders(obtainUpdateOp(4, i2, j1, updateop.payload));
                    l2 = 0;
                    l3 = l;
                }
                i2 = l3;
                j1 = 0;
            }
            l++;
            l2++;
            k4 = j1;
            j1 = l2;
        }
        Object obj = updateop;
        if (j1 != updateop.itemCount)
        {
            obj = updateop.payload;
            if (!mDisableRecycler)
            {
                updateop.payload = null;
                mUpdateOpPool.release(updateop);
            }
            obj = obtainUpdateOp(4, i2, j1, obj);
        }
        if (k4 == 0)
        {
            dispatchAndUpdateViewHolders(((UpdateOp) (obj)));
        } else
        {
            postponeAndUpdateViewHolders(((UpdateOp) (obj)));
        }
          goto _L19
_L23:
        postponeAndUpdateViewHolders(updateop);
          goto _L19
_L18:
        mPendingUpdates.clear();
        return;
          goto _L25
    }

    public final void recycleUpdateOp(UpdateOp updateop)
    {
        if (!mDisableRecycler)
        {
            updateop.payload = null;
            mUpdateOpPool.release(updateop);
        }
    }

    public final void recycleUpdateOpsAndClearList(List list)
    {
        int j = list.size();
        for (int i = 0; i < j; i++)
        {
            UpdateOp updateop = (UpdateOp)list.get(i);
            if (!mDisableRecycler)
            {
                updateop.payload = null;
                mUpdateOpPool.release(updateop);
            }
        }

        list.clear();
    }

    private class UpdateOp
    {

        public int cmd;
        public int itemCount;
        public Object payload;
        public int positionStart;

        public final boolean equals(Object obj)
        {
            if (this != obj) goto _L2; else goto _L1
_L1:
            return true;
_L2:
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            obj = (UpdateOp)obj;
            if (cmd != ((UpdateOp) (obj)).cmd)
            {
                return false;
            }
            if (cmd == 8 && Math.abs(itemCount - positionStart) == 1 && itemCount == ((UpdateOp) (obj)).positionStart && positionStart == ((UpdateOp) (obj)).itemCount) goto _L1; else goto _L3
_L3:
            if (itemCount != ((UpdateOp) (obj)).itemCount)
            {
                return false;
            }
            if (positionStart != ((UpdateOp) (obj)).positionStart)
            {
                return false;
            }
            if (payload == null)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (payload.equals(((UpdateOp) (obj)).payload)) goto _L1; else goto _L4
_L4:
            return false;
            if (((UpdateOp) (obj)).payload == null) goto _L1; else goto _L5
_L5:
            return false;
        }

        public final int hashCode()
        {
            return (cmd * 31 + positionStart) * 31 + itemCount;
        }

        public final String toString()
        {
            StringBuilder stringbuilder = (new StringBuilder()).append(Integer.toHexString(System.identityHashCode(this))).append("[");
            cmd;
            JVM INSTR tableswitch 1 8: default 72
        //                       1 125
        //                       2 131
        //                       3 72
        //                       4 137
        //                       5 72
        //                       6 72
        //                       7 72
        //                       8 143;
               goto _L1 _L2 _L3 _L1 _L4 _L1 _L1 _L1 _L5
_L1:
            String s = "??";
_L7:
            return stringbuilder.append(s).append(",s:").append(positionStart).append("c:").append(itemCount).append(",p:").append(payload).append("]").toString();
_L2:
            s = "add";
            continue; /* Loop/switch isn't completed */
_L3:
            s = "rm";
            continue; /* Loop/switch isn't completed */
_L4:
            s = "up";
            continue; /* Loop/switch isn't completed */
_L5:
            s = "mv";
            if (true) goto _L7; else goto _L6
_L6:
        }

        UpdateOp(int i, int j, int k, Object obj)
        {
            cmd = i;
            positionStart = j;
            itemCount = k;
            payload = obj;
        }
    }


    private class Callback
    {

        public abstract RecyclerView.ViewHolder findViewHolder(int i);

        public abstract void markViewHoldersUpdated(int i, int j, Object obj);

        public abstract void offsetPositionsForAdd(int i, int j);

        public abstract void offsetPositionsForMove(int i, int j);

        public abstract void offsetPositionsForRemovingInvisible(int i, int j);

        public abstract void offsetPositionsForRemovingLaidOutOrNewView(int i, int j);

        public abstract void onDispatchFirstPass(UpdateOp updateop);

        public abstract void onDispatchSecondPass(UpdateOp updateop);
    }

}
