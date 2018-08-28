// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.support.v7.view.WindowCallbackWrapper;
import android.support.v7.widget.DecorToolbar;
import android.view.Menu;
import android.view.View;

// Referenced classes of package android.support.v7.app:
//            ToolbarActionBar

final class this._cls0 extends WindowCallbackWrapper
{

    private final ToolbarActionBar this$0;

    public final View onCreatePanelView(int i)
    {
        if (i == 0)
        {
            return new View(mDecorToolbar.getContext());
        } else
        {
            return super.onCreatePanelView(i);
        }
    }

    public final boolean onPreparePanel(int i, View view, Menu menu)
    {
        boolean flag = super.onPreparePanel(i, view, menu);
        if (flag && !mToolbarMenuPrepared)
        {
            mDecorToolbar.setMenuPrepared();
            mToolbarMenuPrepared = true;
        }
        return flag;
    }

    public (android.view.ckWrapper ckwrapper)
    {
        this$0 = ToolbarActionBar.this;
        super(ckwrapper);
    }
}
