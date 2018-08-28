// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.Stack;

// Referenced classes of package com.google.android.calendar:
//            CreateFabFragment

final class val.view
    implements android.view.lobalLayoutListener
{

    private final CreateFabFragment this$0;
    private final View val$createFab;
    private final View val$view;

    public final void onGlobalLayout()
    {
        val$createFab.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        Object obj = (eateFabInterface)interfaceActivity;
        if (obj != null)
        {
            CreateFabFragment createfabfragment = CreateFabFragment.this;
            eateFabStack eatefabstack = ((eateFabInterface) (obj)).getCreateFabStack();
            View view1 = val$createFab;
            View view2 = val$view;
            eateFabStack.StartDay startday = getStartDayInterface();
            if (eatefabstack.stack.empty())
            {
                obj = eatefabstack.findCreateFab();
            } else
            {
                obj = ((eateFabStack.Scope)eatefabstack.stack.peek()).createFab;
            }
            if (obj != null)
            {
                ((View) (obj)).setVisibility(4);
            }
            view1.setVisibility(0);
            createfabfragment.createFab = ((eateFabStack.Scope)eatefabstack.stack.push(new eateFabStack.Scope(view1, view2, startday))).createFab;
        }
    }

    eateFabStack.Scope()
    {
        this$0 = final_createfabfragment;
        val$createFab = view1;
        val$view = View.this;
        super();
    }
}
