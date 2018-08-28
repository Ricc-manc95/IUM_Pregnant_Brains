// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.graphics.Rect;
import java.util.Comparator;

final class mAdapter
    implements Comparator
{

    private final mIsLayoutRtl mAdapter;
    private final boolean mIsLayoutRtl;
    private final Rect mTemp1 = new Rect();
    private final Rect mTemp2 = new Rect();

    public final int compare(Object obj, Object obj1)
    {
        Rect rect;
        Rect rect1;
        rect = mTemp1;
        rect1 = mTemp2;
        mAdapter.ounds(obj, rect);
        mAdapter.ounds(obj1, rect1);
        if (rect.top >= rect1.top) goto _L2; else goto _L1
_L1:
        return -1;
_L2:
        if (rect.top > rect1.top)
        {
            return 1;
        }
        if (rect.left >= rect1.left)
        {
            break; /* Loop/switch isn't completed */
        }
        if (mIsLayoutRtl)
        {
            return 1;
        }
        if (true) goto _L1; else goto _L3
_L3:
        if (rect.left <= rect1.left)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (!mIsLayoutRtl)
        {
            return 1;
        }
        continue; /* Loop/switch isn't completed */
        if (rect.bottom < rect1.bottom) goto _L1; else goto _L4
_L4:
        if (rect.bottom > rect1.bottom)
        {
            return 1;
        }
        if (rect.right >= rect1.right)
        {
            break; /* Loop/switch isn't completed */
        }
        if (mIsLayoutRtl)
        {
            return 1;
        }
        if (true) goto _L1; else goto _L5
_L5:
        if (rect.right > rect1.right)
        {
            if (!mIsLayoutRtl)
            {
                return 1;
            }
        } else
        {
            return 0;
        }
        if (true) goto _L1; else goto _L6
_L6:
    }

    (boolean flag,  )
    {
        mIsLayoutRtl = flag;
        mAdapter = ;
    }
}
