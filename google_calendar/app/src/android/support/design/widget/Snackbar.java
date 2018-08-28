// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.content.Context;
import android.support.design.internal.ThemeEnforcement;
import android.support.design.snackbar.BaseTransientBottomBar;
import android.support.design.snackbar.ContentViewCallback;
import android.support.design.snackbar.SnackbarContentLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

// Referenced classes of package android.support.design.widget:
//            BaseTransientBottomBar, CoordinatorLayout

public final class Snackbar extends android.support.design.widget.BaseTransientBottomBar
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


    private Snackbar(ViewGroup viewgroup, View view, ContentViewCallback contentviewcallback)
    {
        super(viewgroup, view, contentviewcallback);
    }

    public static Snackbar make(View view, CharSequence charsequence, int i)
    {
        Object obj2;
        View view1;
        view1 = null;
        obj2 = view;
_L3:
        if (obj2 instanceof CoordinatorLayout)
        {
            view = (ViewGroup)obj2;
            break MISSING_BLOCK_LABEL_20;
        }
        view = view1;
        if (!(obj2 instanceof FrameLayout))
        {
            break MISSING_BLOCK_LABEL_70;
        }
        if (((View) (obj2)).getId() != 0x1020002)
        {
            break; /* Loop/switch isn't completed */
        }
        view = (ViewGroup)obj2;
        if (true) goto _L2; else goto _L1
_L1:
        view = (ViewGroup)obj2;
        Object obj = obj2;
        if (obj2 != null)
        {
            obj = ((View) (obj2)).getParent();
            if (obj instanceof View)
            {
                obj = (View)obj;
            } else
            {
                obj = null;
            }
        }
        obj2 = obj;
        view1 = view;
        if (obj != null) goto _L3; else goto _L2
_L2:
        if (view == null)
        {
            throw new IllegalArgumentException("No suitable parent found from the given view. Please provide a valid view.");
        }
        Object obj1 = LayoutInflater.from(view.getContext());
        int j;
        if (ThemeEnforcement.isMaterialTheme(view.getContext()))
        {
            j = 0x7f0500ba;
        } else
        {
            j = 0x7f050043;
        }
        obj1 = (SnackbarContentLayout)((LayoutInflater) (obj1)).inflate(j, view, false);
        view = new Snackbar(view, ((View) (obj1)), ((ContentViewCallback) (obj1)));
        ((SnackbarContentLayout)((Snackbar) (view)).view.getChildAt(0)).messageView.setText(charsequence);
        view.duration = i;
        return view;
    }
}
