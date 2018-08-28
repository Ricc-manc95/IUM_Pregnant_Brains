// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.view.View;
import android.view.ViewTreeObserver;

// Referenced classes of package android.support.v7.view.menu:
//            StandardMenuPopup

final class this._cls0
    implements android.view.angeListener
{

    private final StandardMenuPopup this$0;

    public final void onViewAttachedToWindow(View view)
    {
    }

    public final void onViewDetachedFromWindow(View view)
    {
        if (mTreeObserver != null)
        {
            if (!mTreeObserver.isAlive())
            {
                mTreeObserver = view.getViewTreeObserver();
            }
            mTreeObserver.removeGlobalOnLayoutListener(mGlobalLayoutListener);
        }
        view.removeOnAttachStateChangeListener(this);
    }

    ()
    {
        this$0 = StandardMenuPopup.this;
        super();
    }
}
