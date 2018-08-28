// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.snackbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

// Referenced classes of package android.support.design.snackbar:
//            BaseTransientBottomBar

public final class Snackbar extends BaseTransientBottomBar
{
    public static final class SnackbarLayout extends BaseTransientBottomBar.SnackbarBaseLayout
    {

        protected final void onMeasure(int i, int j)
        {
            super.onMeasure(i, j);
            j = getChildCount();
            int k = getMeasuredWidth();
            int l = getPaddingLeft();
            int i1 = getPaddingRight();
            for (i = 0; i < j; i++)
            {
                View view = getChildAt(i);
                if (view.getLayoutParams().width == -1)
                {
                    view.measure(android.view.View.MeasureSpec.makeMeasureSpec(k - l - i1, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 0x40000000));
                }
            }

        }

        public SnackbarLayout(Context context)
        {
            super(context);
        }

        public SnackbarLayout(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }
    }


    public final void dismiss()
    {
        throw new NoSuchMethodError();
    }

    public final void show()
    {
        throw new NoSuchMethodError();
    }
}
