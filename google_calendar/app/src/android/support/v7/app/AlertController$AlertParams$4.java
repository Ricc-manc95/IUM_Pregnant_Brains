// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.view.View;
import android.widget.AdapterView;

// Referenced classes of package android.support.v7.app:
//            AlertController

final class val.dialog
    implements android.widget.r
{

    private final w.isItemChecked this$0;
    private final AlertController val$dialog;
    private final w val$listView;

    public final void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        if (heckedItems != null)
        {
            heckedItems[i] = val$listView.isItemChecked(i);
        }
        nCheckboxClickListener.onClick(val$dialog.mDialog, i, val$listView.isItemChecked(i));
    }

    w()
    {
        this$0 = final_w;
        val$listView = w1;
        val$dialog = AlertController.this;
        super();
    }
}
