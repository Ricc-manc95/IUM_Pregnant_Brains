// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.view.View;
import android.view.WindowInsets;

// Referenced classes of package android.support.v7.app:
//            AppCompatDelegateImpl

final class this._cls0
    implements OnApplyWindowInsetsListener
{

    private final AppCompatDelegateImpl this$0;

    public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowinsetscompat)
    {
        int j = ((WindowInsets)windowinsetscompat.mInsets).getSystemWindowInsetTop();
        int i = updateStatusGuard(j);
        WindowInsetsCompat windowinsetscompat1 = windowinsetscompat;
        if (j != i)
        {
            int k = ((WindowInsets)windowinsetscompat.mInsets).getSystemWindowInsetLeft();
            int l = ((WindowInsets)windowinsetscompat.mInsets).getSystemWindowInsetRight();
            int i1 = ((WindowInsets)windowinsetscompat.mInsets).getSystemWindowInsetBottom();
            windowinsetscompat1 = new WindowInsetsCompat(((WindowInsets)windowinsetscompat.mInsets).replaceSystemWindowInsets(k, i, l, i1));
        }
        return ViewCompat.onApplyWindowInsets(view, windowinsetscompat1);
    }

    ener()
    {
        this$0 = AppCompatDelegateImpl.this;
        super();
    }
}
