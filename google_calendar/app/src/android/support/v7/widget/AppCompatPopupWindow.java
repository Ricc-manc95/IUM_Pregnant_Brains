// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.PopupWindowCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;

// Referenced classes of package android.support.v7.widget:
//            TintTypedArray

final class AppCompatPopupWindow extends PopupWindow
{

    private static final boolean COMPAT_OVERLAP_ANCHOR = false;

    public AppCompatPopupWindow(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        context = new TintTypedArray(context, context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.PopupWindow, i, j));
        i = android.support.v7.appcompat.R.styleable.PopupWindow_overlapAnchor;
        if (((TintTypedArray) (context)).mWrapped.hasValue(i))
        {
            i = android.support.v7.appcompat.R.styleable.PopupWindow_overlapAnchor;
            PopupWindowCompat.setOverlapAnchor(this, ((TintTypedArray) (context)).mWrapped.getBoolean(i, false));
        }
        setBackgroundDrawable(context.getDrawable(android.support.v7.appcompat.R.styleable.PopupWindow_android_popupBackground));
        ((TintTypedArray) (context)).mWrapped.recycle();
    }

    public final void showAsDropDown(View view, int i, int j)
    {
        super.showAsDropDown(view, i, j);
    }

    public final void showAsDropDown(View view, int i, int j, int k)
    {
        super.showAsDropDown(view, i, j, k);
    }

    public final void update(View view, int i, int j, int k, int l)
    {
        super.update(view, i, j, k, l);
    }

}
