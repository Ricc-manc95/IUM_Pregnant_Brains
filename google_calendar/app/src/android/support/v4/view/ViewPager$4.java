// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.graphics.Rect;
import android.view.View;
import android.view.WindowInsets;

// Referenced classes of package android.support.v4.view:
//            OnApplyWindowInsetsListener, ViewCompat, WindowInsetsCompat, ViewPager

final class this._cls0
    implements OnApplyWindowInsetsListener
{

    private final Rect mTempRect = new Rect();
    private final ViewPager this$0;

    public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowinsetscompat)
    {
        view = ViewCompat.onApplyWindowInsets(view, windowinsetscompat);
        if (((WindowInsets)((WindowInsetsCompat) (view)).mInsets).isConsumed())
        {
            return view;
        }
        windowinsetscompat = mTempRect;
        windowinsetscompat.left = ((WindowInsets)((WindowInsetsCompat) (view)).mInsets).getSystemWindowInsetLeft();
        windowinsetscompat.top = ((WindowInsets)((WindowInsetsCompat) (view)).mInsets).getSystemWindowInsetTop();
        windowinsetscompat.right = ((WindowInsets)((WindowInsetsCompat) (view)).mInsets).getSystemWindowInsetRight();
        windowinsetscompat.bottom = ((WindowInsets)((WindowInsetsCompat) (view)).mInsets).getSystemWindowInsetBottom();
        int k = getChildCount();
        for (int i = 0; i < k; i++)
        {
            WindowInsetsCompat windowinsetscompat1 = ViewCompat.dispatchApplyWindowInsets(getChildAt(i), view);
            windowinsetscompat.left = Math.min(((WindowInsets)windowinsetscompat1.mInsets).getSystemWindowInsetLeft(), ((Rect) (windowinsetscompat)).left);
            windowinsetscompat.top = Math.min(((WindowInsets)windowinsetscompat1.mInsets).getSystemWindowInsetTop(), ((Rect) (windowinsetscompat)).top);
            windowinsetscompat.right = Math.min(((WindowInsets)windowinsetscompat1.mInsets).getSystemWindowInsetRight(), ((Rect) (windowinsetscompat)).right);
            windowinsetscompat.bottom = Math.min(((WindowInsets)windowinsetscompat1.mInsets).getSystemWindowInsetBottom(), ((Rect) (windowinsetscompat)).bottom);
        }

        int j = ((Rect) (windowinsetscompat)).left;
        k = ((Rect) (windowinsetscompat)).top;
        int l = ((Rect) (windowinsetscompat)).right;
        int i1 = ((Rect) (windowinsetscompat)).bottom;
        return new WindowInsetsCompat(((WindowInsets)((WindowInsetsCompat) (view)).mInsets).replaceSystemWindowInsets(j, k, l, i1));
    }

    Compat()
    {
        this$0 = ViewPager.this;
        super();
    }
}
