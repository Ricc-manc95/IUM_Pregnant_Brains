// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.view.View;
import java.util.List;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView, ChildHelper

final class this._cls0
    implements tener
{

    private final RecyclerView this$0;

    public final void onAnimationFinished(tener tener)
    {
        tener tener1 = null;
        boolean flag = true;
        tener.tener(true);
        if (tener.tener != null && tener.tener == null)
        {
            tener.tener = null;
        }
        tener.tener = null;
        int i;
        if ((tener.tener & 0x10) != 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            RecyclerView recyclerview = RecyclerView.this;
            View view = tener._fld0;
            recyclerview.mInterceptRequestLayoutDepth = recyclerview.mInterceptRequestLayoutDepth + 1;
            if (recyclerview.mInterceptRequestLayoutDepth == 1 && !recyclerview.mLayoutFrozen)
            {
                recyclerview.mLayoutWasDefered = false;
            }
            ChildHelper childhelper = recyclerview.mChildHelper;
            i = childhelper.mCallback.(view);
            boolean flag1;
            if (i == -1)
            {
                if (childhelper.mHiddenViews.remove(view))
                {
                    childhelper.mCallback.(view);
                }
                i = 1;
            } else
            if (childhelper.mBucket.(i))
            {
                childhelper.mBucket.(i);
                if (childhelper.mHiddenViews.remove(view))
                {
                    childhelper.mCallback.(view);
                }
                childhelper.mCallback.(i);
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                if (view != null)
                {
                    tener1 = (()view.getLayoutParams()).;
                }
                recyclerview.mRecycler.(tener1);
                recyclerview.mRecycler.ternal(tener1);
            }
            if (i == 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            recyclerview.stopInterceptRequestLayout(flag1);
            if (i == 0)
            {
                if ((tener.ternal & 0x100) != 0)
                {
                    i = ((flag) ? 1 : 0);
                } else
                {
                    i = 0;
                }
                if (i != 0)
                {
                    removeDetachedView(tener.ternal, false);
                }
            }
        }
    }

    tener()
    {
        this$0 = RecyclerView.this;
        super();
    }
}
