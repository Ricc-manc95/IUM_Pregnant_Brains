// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.view.overlay;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

// Referenced classes of package com.google.android.calendar.common.view.overlay:
//            OverlayFragment

public final class this._cls0 extends android.view.ner
{

    private final OverlayFragment this$0;

    public final boolean onDown(MotionEvent motionevent)
    {
        Object obj = getOverlayView();
        if (obj != null)
        {
            int ai[] = new int[2];
            ((View) (obj)).getLocationInWindow(ai);
            Rect rect = new Rect(0, 0, ((View) (obj)).getWidth(), ((View) (obj)).getHeight());
            rect.offset(ai[0], ai[1]);
            obj = ((View) (obj)).getBackground();
            Rect rect1 = new Rect();
            if (obj != null && ((Drawable) (obj)).getPadding(rect1))
            {
                rect.left = rect.left + rect1.left;
                rect.right = rect.right - rect1.right;
                rect.top = rect.top + rect1.top;
                rect.bottom = rect.bottom - rect1.bottom;
            }
            if (!rect.contains((int)motionevent.getX(), (int)motionevent.getY()))
            {
                onTouchOutside();
                return true;
            }
        }
        return super.onDown(motionevent);
    }

    public Y()
    {
        this$0 = OverlayFragment.this;
        super();
    }
}
