// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.content.Context;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.ContentFrameLayout;
import android.view.KeyEvent;
import android.view.MotionEvent;

// Referenced classes of package android.support.v7.app:
//            AppCompatDelegateImpl

final class this._cls0 extends ContentFrameLayout
{

    private final AppCompatDelegateImpl this$0;

    public final boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        return AppCompatDelegateImpl.this.dispatchKeyEvent(keyevent) || super.dispatchKeyEvent(keyevent);
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        if (motionevent.getAction() == 0)
        {
            int i = (int)motionevent.getX();
            int j = (int)motionevent.getY();
            boolean flag;
            if (i < -5 || j < -5 || i > getWidth() + 5 || j > getHeight() + 5)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                motionevent = AppCompatDelegateImpl.this;
                motionevent.closePanel(motionevent.getPanelState$514LKAACC5N68SJFD5I2USRLE1O6USJK5TR3EBR1E1O2UGBGE11MURBGC5Q48PBCCLJM2T3595MN0R14A1GMSPBC8PIM2T3LE9IL6T31EHIJM___0(0), true);
                return true;
            }
        }
        return super.onInterceptTouchEvent(motionevent);
    }

    public final void setBackgroundResource(int i)
    {
        setBackgroundDrawable(AppCompatResources.getDrawable(getContext(), i));
    }

    public (Context context)
    {
        this$0 = AppCompatDelegateImpl.this;
        super(context);
    }
}
