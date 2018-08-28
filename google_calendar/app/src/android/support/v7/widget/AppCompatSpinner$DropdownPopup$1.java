// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

// Referenced classes of package android.support.v7.widget:
//            AppCompatSpinner, ListPopupWindow

final class this._cls1
    implements android.widget.ownPopup._cls1
{

    private final dapter this$1;

    public final void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        setSelection(i);
        if (getOnItemClickListener() != null)
        {
            performItemClick(view, i, dapter.getItemId(i));
        }
        dismiss();
    }

    (AppCompatSpinner appcompatspinner)
    {
        this$1 = this._cls1.this;
        super();
    }
}
