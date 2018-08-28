// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.dialog;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

final class selectedItem extends BaseAdapter
{

    private final LayoutInflater inflater;
    private final int selectedItem;
    private final List strings;

    public final int getCount()
    {
        return strings.size();
    }

    public final Object getItem(int i)
    {
        return strings.get(i);
    }

    public final long getItemId(int i)
    {
        return (long)i;
    }

    public final View getView(int i, View view, ViewGroup viewgroup)
    {
        boolean flag = false;
        View view1 = view;
        class ViewHolder
        {

            public final ImageView check;
            public final TextView text;

            public ViewHolder(ViewGroup viewgroup)
            {
                text = (TextView)viewgroup.findViewById(0x7f100042);
                check = (ImageView)viewgroup.findViewById(0x7f100148);
            }
        }

        if (view == null)
        {
            view1 = inflater.inflate(0x7f05015e, viewgroup, false);
            view1.setTag(new ViewHolder((ViewGroup)view1));
        }
        view = (ViewHolder)view1.getTag();
        viewgroup = (String)strings.get(i);
        Resources resources;
        int j;
        if (i == selectedItem)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        ((ViewHolder) (view)).text.setText(viewgroup);
        viewgroup = ((ViewHolder) (view)).text;
        resources = ((ViewHolder) (view)).text.getResources();
        if (i != 0)
        {
            j = 0x7f0d01d7;
        } else
        {
            j = 0x7f0d021e;
        }
        viewgroup.setTextColor(resources.getColor(j));
        view = ((ViewHolder) (view)).check;
        if (i != 0)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 8;
        }
        view.setVisibility(i);
        return view1;
    }

    public ViewHolder(Context context, List list, int i)
    {
        inflater = LayoutInflater.from(context);
        strings = list;
        selectedItem = i;
    }
}
