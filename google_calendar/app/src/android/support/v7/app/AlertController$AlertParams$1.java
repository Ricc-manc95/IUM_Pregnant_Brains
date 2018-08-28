// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

final class val.listView extends ArrayAdapter
{

    private final w.setItemChecked this$0;
    private final w val$listView;

    public final View getView(int i, View view, ViewGroup viewgroup)
    {
        view = super.getView(i, view, viewgroup);
        if (heckedItems != null && heckedItems[i])
        {
            val$listView.setItemChecked(i, true);
        }
        return view;
    }

    w(int i, int j, CharSequence acharsequence[], w w1)
    {
        this$0 = final_w;
        val$listView = w1;
        super(Context.this, i, 0x1020014, acharsequence);
    }
}
