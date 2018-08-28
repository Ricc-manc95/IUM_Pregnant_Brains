// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.view.View;

// Referenced classes of package android.support.transition:
//            ViewUtils, ViewUtilsBase

final class mView
{

    public int mBottom;
    public int mBottomRightCalls;
    public int mLeft;
    public int mRight;
    public int mTop;
    public int mTopLeftCalls;
    private View mView;

    final void setLeftTopRightBottom()
    {
        View view = mView;
        int i = mLeft;
        int j = mTop;
        int k = mRight;
        int l = mBottom;
        ViewUtils.IMPL.setLeftTopRightBottom(view, i, j, k, l);
        mTopLeftCalls = 0;
        mBottomRightCalls = 0;
    }

    (View view)
    {
        mView = view;
    }
}
