// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.ics;

import android.view.View;
import android.widget.Button;
import com.google.android.calendar.newapi.commandbar.BottomBar;
import com.google.android.calendar.newapi.commandbar.BottomBarController;
import com.google.android.calendar.newapi.commandbar.CommandBar;
import com.google.android.calendar.newapi.commandbar.CommandBarController;

// Referenced classes of package com.google.android.calendar.newapi.screen.ics:
//            IcsViewScreenModel

public final class IcsCommandBarController extends CommandBarController
{

    public IcsCommandBarController(Callback callback)
    {
        super(callback);
    }

    protected final int getActionsLayout()
    {
        return 0x7f0500d8;
    }

    protected final int[] getPrimaryActionIds()
    {
        return (new int[] {
            0x7f100281
        });
    }

    protected final void onCommandBarActionClick(Object obj, int i)
    {
        ((Callback)obj).onImportClicked();
    }

    protected final void onModelChanged(Object obj)
    {
        obj = super.commandBar;
        ((IcsViewScreenModel)super.model).importType;
        JVM INSTR tableswitch 5 6: default 36
    //                   5 133
    //                   6 133;
           goto _L1 _L2 _L2
_L1:
        int i = 1;
_L8:
        if (obj != null)
        {
            int j;
            if (i != 0)
            {
                j = 0;
            } else
            {
                j = 8;
            }
            ((View) (obj)).setVisibility(j);
        }
        if (i == 0) goto _L4; else goto _L3
_L3:
        obj = (Button)((CommandBar)super.commandBar).findViewById(0x7f100281);
        ((IcsViewScreenModel)super.model).importType;
        JVM INSTR tableswitch 1 7: default 124
    //                   1 144
    //                   2 144
    //                   3 144
    //                   4 144
    //                   5 124
    //                   6 124
    //                   7 150;
           goto _L5 _L6 _L6 _L6 _L6 _L5 _L5 _L7
_L7:
        break MISSING_BLOCK_LABEL_150;
_L5:
        i = 0x7f13030b;
_L9:
        ((Button) (obj)).setText(i);
_L4:
        return;
_L2:
        i = 0;
          goto _L8
_L6:
        i = 0x7f1304a2;
          goto _L9
        i = 0x7f130379;
          goto _L9
    }

    protected final volatile void setupCommandBar(BottomBar bottombar)
    {
    }

    private class Callback
    {

        public abstract void onImportClicked();
    }

}
