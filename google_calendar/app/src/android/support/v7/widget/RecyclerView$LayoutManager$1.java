// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.graphics.Rect;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView

final class this._cls0
    implements this._cls0
{

    private final ecyclerView this$0;

    public final View getChildAt(int i)
    {
        return tChildAt(i);
    }

    public final int getChildEnd(View view)
    {
        tChildAt tchildat = (tChildAt)view.getLayoutParams();
        tChildAt tchildat1 = this._cls0.this;
        int i = view.getRight();
        int j = ((this._cls0)view.getLayoutParams()).corInsets.right;
        return tchildat.htMargin + (j + i);
    }

    public final int getChildStart(View view)
    {
        htMargin htmargin = (htMargin)view.getLayoutParams();
        htMargin htmargin1 = this._cls0.this;
        return view.getLeft() - ((this._cls0)view.getLayoutParams()).corInsets.left - htmargin.tMargin;
    }

    public final int getParentEnd()
    {
        int j = idth;
        tMargin tmargin = this._cls0.this;
        int i;
        if (tmargin.ecyclerView != null)
        {
            i = tmargin.ecyclerView.getPaddingRight();
        } else
        {
            i = 0;
        }
        return j - i;
    }

    public final int getParentStart()
    {
        ecyclerView ecyclerview = this._cls0.this;
        if (ecyclerview.ecyclerView != null)
        {
            return ecyclerview.ecyclerView.getPaddingLeft();
        } else
        {
            return 0;
        }
    }

    ()
    {
        this$0 = this._cls0.this;
        super();
    }
}
