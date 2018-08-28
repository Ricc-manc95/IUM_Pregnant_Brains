// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.database.DataSetObserver;
import android.widget.PopupWindow;

// Referenced classes of package android.support.v7.widget:
//            ListPopupWindow

final class this._cls0 extends DataSetObserver
{

    private final ListPopupWindow this$0;

    public final void onChanged()
    {
        if (mPopup.isShowing())
        {
            show();
        }
    }

    public final void onInvalidated()
    {
        dismiss();
    }

    ()
    {
        this$0 = ListPopupWindow.this;
        super();
    }
}
