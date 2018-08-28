// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.view.View;
import android.view.WindowInsets;
import java.util.Objects;

// Referenced classes of package android.support.design.widget:
//            CoordinatorLayout

final class this._cls0
    implements OnApplyWindowInsetsListener
{

    private final CoordinatorLayout this$0;

    public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowinsetscompat)
    {
        boolean flag1 = true;
        int i = 0;
        CoordinatorLayout coordinatorlayout = CoordinatorLayout.this;
        if (!Objects.equals(coordinatorlayout.mLastInsets, windowinsetscompat))
        {
            coordinatorlayout.mLastInsets = windowinsetscompat;
            boolean flag;
            if (windowinsetscompat != null && ((WindowInsets)windowinsetscompat.mInsets).getSystemWindowInsetTop() > 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            coordinatorlayout.mDrawStatusBarBackground = flag;
            if (!coordinatorlayout.mDrawStatusBarBackground && coordinatorlayout.getBackground() == null)
            {
                flag = flag1;
            } else
            {
                flag = false;
            }
            coordinatorlayout.setWillNotDraw(flag);
            if (!((WindowInsets)windowinsetscompat.mInsets).isConsumed())
            {
                int j = coordinatorlayout.getChildCount();
                view = windowinsetscompat;
                do
                {
                    windowinsetscompat = view;
                    if (i >= j)
                    {
                        break;
                    }
                    windowinsetscompat = coordinatorlayout.getChildAt(i);
                    if (ViewCompat.getFitsSystemWindows(windowinsetscompat) && ((youtParams)windowinsetscompat.getLayoutParams()).mBehavior != null)
                    {
                        windowinsetscompat = view;
                        if (((WindowInsets)((WindowInsetsCompat) (view)).mInsets).isConsumed())
                        {
                            break;
                        }
                    }
                    i++;
                } while (true);
            }
            coordinatorlayout.requestLayout();
            return windowinsetscompat;
        } else
        {
            return windowinsetscompat;
        }
    }

    youtParams()
    {
        this$0 = CoordinatorLayout.this;
        super();
    }
}
