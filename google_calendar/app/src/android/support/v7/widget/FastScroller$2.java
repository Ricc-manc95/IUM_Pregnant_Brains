// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;


// Referenced classes of package android.support.v7.widget:
//            RecyclerView, FastScroller

final class ScrollListener extends ScrollListener
{

    private final FastScroller this$0;

    public final void onScrolled$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D4KIAAM0(RecyclerView recyclerview, int i)
    {
        FastScroller fastscroller = FastScroller.this;
        i = recyclerview.computeHorizontalScrollOffset();
        int j = recyclerview.computeVerticalScrollOffset();
        int k = fastscroller.mRecyclerView.computeVerticalScrollRange();
        int l = fastscroller.mRecyclerViewHeight;
        int i1;
        int j1;
        boolean flag;
        if (k - l > 0 && fastscroller.mRecyclerViewHeight >= fastscroller.mScrollbarMinimumRange)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        fastscroller.mNeedVerticalScrollbar = flag;
        i1 = fastscroller.mRecyclerView.computeHorizontalScrollRange();
        j1 = fastscroller.mRecyclerViewWidth;
        if (i1 - j1 > 0 && fastscroller.mRecyclerViewWidth >= fastscroller.mScrollbarMinimumRange)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        fastscroller.mNeedHorizontalScrollbar = flag;
        if (!fastscroller.mNeedVerticalScrollbar && !fastscroller.mNeedHorizontalScrollbar)
        {
            if (fastscroller.mState != 0)
            {
                fastscroller.setState(0);
            }
        } else
        {
            if (fastscroller.mNeedVerticalScrollbar)
            {
                fastscroller.mVerticalThumbCenterY = (int)((((float)j + (float)l / 2.0F) * (float)l) / (float)k);
                fastscroller.mVerticalThumbHeight = Math.min(l, (l * l) / k);
            }
            if (fastscroller.mNeedHorizontalScrollbar)
            {
                fastscroller.mHorizontalThumbCenterX = (int)((((float)i + (float)j1 / 2.0F) * (float)j1) / (float)i1);
                fastscroller.mHorizontalThumbWidth = Math.min(j1, (j1 * j1) / i1);
            }
            if (fastscroller.mState == 0 || fastscroller.mState == 1)
            {
                fastscroller.setState(1);
                return;
            }
        }
    }

    ScrollListener()
    {
        this$0 = FastScroller.this;
        super();
    }
}
