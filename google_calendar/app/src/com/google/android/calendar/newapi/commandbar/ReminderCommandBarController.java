// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.commandbar;

import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import com.google.android.calendar.newapi.model.TaskHolder;
import com.google.android.gms.reminders.model.Task;

// Referenced classes of package com.google.android.calendar.newapi.commandbar:
//            CommandBarController, BottomBarController, CommandBar, BottomBar

public final class ReminderCommandBarController extends CommandBarController
{

    private static final int ACTION_IDS[] = {
        0x7f10027e
    };

    public ReminderCommandBarController(Listener listener)
    {
        super(listener);
    }

    protected final int getActionsLayout()
    {
        return 0x7f0500e1;
    }

    protected final int[] getPrimaryActionIds()
    {
        return ACTION_IDS;
    }

    public final void onCommandBarActionClick(Object obj, int i)
    {
        obj = (Listener)obj;
        if (i == 0x7f10027e)
        {
            ((Listener) (obj)).onMarkAsDoneClicked();
        }
    }

    protected final void onModelChanged(Object obj)
    {
        obj = (TaskHolder)obj;
        Object obj1 = super.commandBar;
        if (obj1 != null)
        {
            ((View) (obj1)).setVisibility(0);
        }
        obj1 = (Button)((CommandBar)super.commandBar).findViewById(0x7f10027e);
        Resources resources = ((CommandBar)super.commandBar).getResources();
        obj = ((TaskHolder) (obj)).getTask();
        int i;
        if (Boolean.TRUE.equals(((Task) (obj)).getArchived()))
        {
            i = 0x7f130087;
        } else
        {
            i = 0x7f130086;
        }
        ((Button) (obj1)).setText(resources.getString(i));
    }

    public final volatile void setupCommandBar(BottomBar bottombar)
    {
    }


    private class Listener
    {

        public abstract void onMarkAsDoneClicked();
    }

}
