// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.support.v7.view.menu.MenuBuilder;
import android.view.Window;

// Referenced classes of package android.support.v7.app:
//            AppCompatDelegateImpl

final class this._cls0
    implements android.support.v7.view.menu.
{

    private final AppCompatDelegateImpl this$0;

    public final void onCloseMenu(MenuBuilder menubuilder, boolean flag)
    {
        checkCloseActionMenu(menubuilder);
    }

    public final boolean onOpenSubMenu(MenuBuilder menubuilder)
    {
        android.view.rCallback rcallback = mWindow.getCallback();
        if (rcallback != null)
        {
            rcallback.rCallback(108, menubuilder);
        }
        return true;
    }

    ()
    {
        this$0 = AppCompatDelegateImpl.this;
        super();
    }
}
