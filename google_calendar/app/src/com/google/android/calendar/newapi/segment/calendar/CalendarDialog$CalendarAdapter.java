// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.calendar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.google.android.calendar.common.drawable.ColorCircle;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.segment.calendar:
//            CalendarDialog

final class this._cls0 extends BaseAdapter
{

    private final CalendarDialog this$0;

    public final int getCount()
    {
        return calendarList.size();
    }

    public final Object getItem(int i)
    {
        return (this._cls0)calendarList.get(i);
    }

    public final long getItemId(int i)
    {
        return (long)i;
    }

    public final View getView(int i, View view, ViewGroup viewgroup)
    {
        Context context = viewgroup.getContext();
        viewgroup = (TextTileView)view;
        view = viewgroup;
        if (viewgroup == null)
        {
            view = new TextTileView(context);
            view.setHorizontalIncrement(0x7f110022).setIconDrawable(new ColorCircle(context.getResources(), 0x7f0e007c));
        }
        view.setLayoutDirection(3);
        viewgroup = (this._cls0)getItem(i);
        view.setPrimaryText(new CharSequence[] {
            viewgroup.layName()
        });
        view.setSecondaryText(new CharSequence[] {
            viewgroup.untName()
        });
        ((ColorCircle)view.getIcon().getDrawable()).setColor(viewgroup.r());
        view.getIcon().invalidate();
        return view;
    }

    ()
    {
        this$0 = CalendarDialog.this;
        super();
    }
}
