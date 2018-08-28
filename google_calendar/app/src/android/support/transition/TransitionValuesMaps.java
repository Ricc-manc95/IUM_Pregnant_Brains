// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.util.SparseArray;

final class TransitionValuesMaps
{

    public final SparseArray mIdValues = new SparseArray();
    public final LongSparseArray mItemIdValues = new LongSparseArray();
    public final ArrayMap mNameValues = new ArrayMap();
    public final ArrayMap mViewValues = new ArrayMap();

    TransitionValuesMaps()
    {
    }
}
