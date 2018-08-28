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
        int i = view.getBottom();
        int j = ((this._cls0)view.getLayoutParams()).corInsets.bottom;
        return tchildat.tomMargin + (j + i);
    }

    public final int getChildStart(View view)
    {
        tomMargin tommargin = (tomMargin)view.getLayoutParams();
        tomMargin tommargin1 = this._cls0.this;
        return view.getTop() - ((this._cls0)view.getLayoutParams()).corInsets.top - tommargin.Margin;
    }

    public final int getParentEnd()
    {
        int j = eight;
        Margin margin = this._cls0.this;
        int i;
        if (margin.ecyclerView != null)
        {
            i = margin.ecyclerView.getPaddingBottom();
        } else
        {
            i = 0;
        }
        return j - i;
    }

    public final int getParentStart()
    {
          = this._cls0.this;
        if (.ecyclerView != null)
        {
            return .ecyclerView.getPaddingTop();
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
