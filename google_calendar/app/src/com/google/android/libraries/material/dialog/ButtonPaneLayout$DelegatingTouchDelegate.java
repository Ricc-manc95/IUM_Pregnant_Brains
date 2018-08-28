// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.dialog;

import android.graphics.Rect;
import android.support.v4.util.ArraySet;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.google.android.libraries.material.dialog:
//            ButtonPaneLayout

final class this._cls0 extends TouchDelegate
{

    public TouchDelegate parentDelegate;
    private final ButtonPaneLayout this$0;
    public final Set touchDelegates = new ArraySet();

    public final boolean onTouchEvent(MotionEvent motionevent)
    {
label0:
        {
            if (parentDelegate != null && parentDelegate.onTouchEvent(motionevent))
            {
                return true;
            }
            motionevent.offsetLocation(-getLeft(), -getTop());
            if (touchDelegates.isEmpty())
            {
                break label0;
            }
            Iterator iterator = touchDelegates.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break label0;
                }
            } while (!((TouchDelegate)iterator.next()).onTouchEvent(motionevent));
            return true;
        }
        return false;
    }

    ()
    {
        this$0 = ButtonPaneLayout.this;
        super(new Rect(), ButtonPaneLayout.this);
    }
}
