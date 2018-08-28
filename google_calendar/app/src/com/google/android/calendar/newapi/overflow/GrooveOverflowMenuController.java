// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.overflow;

import android.view.Menu;
import android.view.MenuItem;
import com.google.android.calendar.newapi.model.GrooveViewScreenModel;
import com.google.android.calendar.newapi.model.ViewScreenModel;

// Referenced classes of package com.google.android.calendar.newapi.overflow:
//            OverflowMenuController

public final class GrooveOverflowMenuController extends OverflowMenuController
{

    public GrooveOverflowMenuController(Callback callback)
    {
        super(callback);
    }

    public final int getMenuResourceId()
    {
        return 0x7f150005;
    }

    protected final void onMenuItemClicked(MenuItem menuitem, Object obj)
    {
        obj = (Callback)obj;
        if (menuitem.getItemId() == 0x7f100429)
        {
            ((Callback) (obj)).onDeleteClicked();
        }
    }

    protected final void onModelChanged(OverflowMenuCompat.OverflowMenu overflowmenu, Object obj)
    {
        obj = (GrooveViewScreenModel)obj;
        overflowmenu = overflowmenu.getMenu();
        boolean flag = ((ViewScreenModel) (obj)).isEditable();
        overflowmenu.findItem(0x7f100429).setVisible(flag).setEnabled(flag);
    }

    private class Callback
    {

        public abstract void onDeleteClicked();
    }

}
