// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.view.View;
import android.widget.AdapterView;

// Referenced classes of package android.support.v7.widget:
//            ListPopupWindow, DropDownListView

final class this._cls0
    implements android.widget.SelectedListener
{

    private final ListPopupWindow this$0;

    public final void onItemSelected(AdapterView adapterview, View view, int i, long l)
    {
        if (i != -1)
        {
            adapterview = mDropDownList;
            if (adapterview != null)
            {
                adapterview.mListSelectionHidden = false;
            }
        }
    }

    public final void onNothingSelected(AdapterView adapterview)
    {
    }

    tener()
    {
        this$0 = ListPopupWindow.this;
        super();
    }
}
