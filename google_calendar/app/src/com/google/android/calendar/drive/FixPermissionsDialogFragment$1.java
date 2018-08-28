// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.drive;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

// Referenced classes of package com.google.android.calendar.drive:
//            FixPermissionsDialogFragment

final class  extends ArrayAdapter
{

    public final View getDropDownView(int i, View view, ViewGroup viewgroup)
    {
        view = (TextView)super.getDropDownView(i, view, viewgroup);
        view.setText(FixPermissionsDialogFragment.getRoleStringId((String)getItem(i)));
        return view;
    }

    public final View getView(int i, View view, ViewGroup viewgroup)
    {
        view = (TextView)super.getView(i, view, viewgroup);
        view.setText(FixPermissionsDialogFragment.getRoleStringId((String)getItem(i)));
        return view;
    }

    (Context context, int i, List list)
    {
        super(context, i, list);
    }
}
