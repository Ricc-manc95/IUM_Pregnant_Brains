// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.os.Handler;
import android.widget.AbsListView;
import android.widget.PopupWindow;

// Referenced classes of package android.support.v7.widget:
//            ListPopupWindow

final class this._cls0
    implements android.widget.llListener
{

    private final ListPopupWindow this$0;

    public final void onScroll(AbsListView abslistview, int i, int j, int k)
    {
    }

    public final void onScrollStateChanged(AbsListView abslistview, int i)
    {
        boolean flag = true;
        if (i == 1)
        {
            if (mPopup.getInputMethodMode() == 2)
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = 0;
            }
            if (i == 0 && mPopup.getContentView() != null)
            {
                mHandler.removeCallbacks(mResizePopupRunnable);
                mResizePopupRunnable.run();
            }
        }
    }

    ()
    {
        this$0 = ListPopupWindow.this;
        super();
    }
}
