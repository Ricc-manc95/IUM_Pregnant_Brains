// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.view.View;
import android.view.WindowInsets;

// Referenced classes of package android.support.v4.widget:
//            DrawerLayout

final class ener
    implements android.view.dowInsetsListener
{

    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowinsets)
    {
        boolean flag1 = true;
        view = (DrawerLayout)view;
        boolean flag;
        if (windowinsets.getSystemWindowInsetTop() > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        view.mLastInsets = windowinsets;
        view.mDrawStatusBarBackground = flag;
        if (!flag && view.getBackground() == null)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        view.setWillNotDraw(flag);
        view.requestLayout();
        return windowinsets.consumeSystemWindowInsets();
    }

    ener(DrawerLayout drawerlayout)
    {
    }
}
