// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.commandbar;

import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import com.google.android.calendar.newapi.model.BasicViewScreenModel;
import com.google.android.calendar.newapi.model.GrooveViewScreenModel;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.newapi.screen.GrooveViewScreenListener;
import com.google.android.calendar.timely.TimelineGroove;

// Referenced classes of package com.google.android.calendar.newapi.commandbar:
//            CommandBarController, BottomBarController, CommandBar, BottomBar

public final class GrooveCommandBarController extends CommandBarController
{

    private static final int ACTION_IDS[] = {
        0x7f10027d, 0x7f10027e
    };

    public GrooveCommandBarController(GrooveViewScreenListener grooveviewscreenlistener)
    {
        super(grooveviewscreenlistener);
    }

    protected final int getActionsLayout()
    {
        return 0x7f0500d5;
    }

    protected final int[] getPrimaryActionIds()
    {
        return ACTION_IDS;
    }

    public final void onCommandBarActionClick(Object obj, int i)
    {
        obj = (GrooveViewScreenListener)obj;
        if (i == 0x7f10027e)
        {
            ((GrooveViewScreenListener) (obj)).onMarkAsDoneClicked();
        } else
        if (i == 0x7f10027d)
        {
            ((GrooveViewScreenListener) (obj)).onDeferClicked();
            return;
        }
    }

    protected final void onModelChanged(Object obj)
    {
        Object obj1;
        Button button;
label0:
        {
            boolean flag = false;
            obj = (GrooveViewScreenModel)obj;
            obj1 = super.commandBar;
            int i;
            if (!((BasicViewScreenModel) (obj)).showSimplifiedScreen())
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (obj1 != null)
            {
                boolean flag1;
                if (i != 0)
                {
                    i = 0;
                } else
                {
                    i = 8;
                }
                ((View) (obj1)).setVisibility(i);
            }
            if (!((BasicViewScreenModel) (obj)).showSimplifiedScreen())
            {
                obj1 = (Button)((CommandBar)super.commandBar).findViewById(0x7f10027e);
                button = (Button)((CommandBar)super.commandBar).findViewById(0x7f10027d);
                flag1 = ((TimelineGroove)((ViewScreenModel) (obj)).timelineItem).completed;
                obj = ((CommandBar)super.commandBar).getResources();
                if (flag1)
                {
                    i = 0x7f130087;
                } else
                {
                    i = 0x7f130081;
                }
                ((Button) (obj1)).setText(((Resources) (obj)).getString(i));
                if (!flag1)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (button != null)
                {
                    if (i != 0)
                    {
                        i = ((flag) ? 1 : 0);
                    } else
                    {
                        i = 8;
                    }
                    button.setVisibility(i);
                }
                if (android.os.Build.VERSION.SDK_INT >= 22)
                {
                    if (!flag1)
                    {
                        break label0;
                    }
                    ((Button) (obj1)).setAccessibilityTraversalAfter(0x7f1002ae);
                }
            }
            return;
        }
        button.setAccessibilityTraversalAfter(0x7f1002ae);
        ((Button) (obj1)).setAccessibilityTraversalAfter(0x7f10027d);
    }

    public final volatile void setupCommandBar(BottomBar bottombar)
    {
    }

}
