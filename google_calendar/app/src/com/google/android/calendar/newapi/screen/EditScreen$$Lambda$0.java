// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.view.View;
import android.view.ViewGroup;
import com.google.android.calendar.viewedit.segment.edit.EditSegmentDivider;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            EditScreen

final class arg._cls1
    implements android.view.obalLayoutListener
{

    private final EditScreen arg$1;

    public final void onGlobalLayout()
    {
        EditScreen editscreen = arg$1;
        int k = editscreen.segmentContainer.getChildCount();
        int j = 0;
        int i = 0;
        while (j < k) 
        {
            View view = editscreen.segmentContainer.getChildAt(j);
            if (view instanceof EditSegmentDivider)
            {
                if (view != null)
                {
                    if (i != 0)
                    {
                        i = 0;
                    } else
                    {
                        i = 8;
                    }
                    view.setVisibility(i);
                }
                i = 0;
            } else
            {
                boolean flag;
                if (view.getVisibility() == 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                i = flag | i;
            }
            j++;
        }
    }

    vider(EditScreen editscreen)
    {
        arg$1 = editscreen;
    }
}
