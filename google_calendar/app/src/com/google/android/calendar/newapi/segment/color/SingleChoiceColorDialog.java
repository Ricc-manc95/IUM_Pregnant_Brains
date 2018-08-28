// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.color;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.widget.ListAdapter;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.common.dialog.SingleChoiceDialog;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import java.util.ArrayList;
import java.util.List;

public final class SingleChoiceColorDialog extends SingleChoiceDialog
{

    private ArrayList colors;

    public SingleChoiceColorDialog()
    {
    }

    public static SingleChoiceDialog newInstance(List list, int i, boolean flag, Fragment fragment)
    {
        SingleChoiceColorDialog singlechoicecolordialog = new SingleChoiceColorDialog();
        Object obj = singlechoicecolordialog.getArguments();
        if (obj == null)
        {
            obj = Absent.INSTANCE;
        } else
        {
            obj = new Present(obj);
        }
        obj = (Bundle)((Optional) (obj)).or(new Bundle());
        ((Bundle) (obj)).putParcelableArrayList("argument_colors", new ArrayList(list));
        ((Bundle) (obj)).putBoolean("argument_calendar_colors_only", flag);
        singlechoicecolordialog.setArguments(((Bundle) (obj)));
        singlechoicecolordialog.selectedItem = i;
        singlechoicecolordialog.setTargetFragment(fragment, -1);
        return singlechoicecolordialog;
    }

    protected final ListAdapter createListAdapter(int i)
    {
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        return new SingleChoiceColorDialogAdapter(((Context) (obj)), colors, i, getArguments().getBoolean("argument_calendar_colors_only"));
    }

    protected final Object getValue(int i)
    {
        return (EntityColor)colors.get(i);
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        colors = getArguments().getParcelableArrayList("argument_colors");
    }

    private class SingleChoiceColorDialogAdapter extends BaseAdapter
    {

        private final boolean calendarColorsOnly;
        private final List colors;
        private final LayoutInflater inflater;
        private final int selectedItem;

        public final int getCount()
        {
            return colors.size();
        }

        public final Object getItem(int i)
        {
            return (EntityColor)colors.get(i);
        }

        public final long getItemId(int i)
        {
            return (long)i;
        }

        public final View getView(int i, View view, ViewGroup viewgroup)
        {
            boolean flag = false;
            View view1 = view;
            if (view == null)
            {
                view1 = inflater.inflate(0x7f0500e5, viewgroup, false);
                view1.setTag(new ColorViewHolder((ViewGroup)view1, calendarColorsOnly));
            }
            viewgroup = (ColorViewHolder)view1.getTag();
            Object obj = (EntityColor)getItem(i);
            Object obj1;
            int j;
            if (i == selectedItem)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            obj1 = ((ColorViewHolder) (viewgroup)).label;
            if (((ColorViewHolder) (viewgroup)).calendarColorsOnly)
            {
                view = ((ColorViewHolder) (viewgroup)).check.getResources();
                Object obj2 = (CalendarColor)obj;
                if (obj2 instanceof NamedCalendarColor)
                {
                    obj2 = (NamedCalendarColor)obj2;
                    view = view.getStringArray(((NamedCalendarColor) (obj2)).getNamesArray())[((NamedCalendarColor) (obj2)).getNameIndex()];
                } else
                {
                    view = view.getString(0x7f1303a8);
                }
            } else
            {
                view = ((ColorViewHolder) (viewgroup)).check.getResources();
                if (obj instanceof EventColor)
                {
                    view = ((EventColor)obj).getName();
                } else
                {
                    view = view.getString(0x7f1301a3);
                }
            }
            ((TextView) (obj1)).setText(view);
            view = ((ColorViewHolder) (viewgroup)).label;
            obj1 = ((ColorViewHolder) (viewgroup)).label.getResources();
            if (i != 0)
            {
                j = 0x7f0d01d7;
            } else
            {
                j = 0x7f0d00aa;
            }
            view.setTextColor(((Resources) (obj1)).getColor(j));
            view = ((ColorViewHolder) (viewgroup)).check;
            if (i != 0)
            {
                j = ((flag) ? 1 : 0);
            } else
            {
                j = 8;
            }
            view.setVisibility(j);
            view = ((ColorViewHolder) (viewgroup)).circle;
            j = ((EntityColor) (obj)).getValue();
            view.getPaint().setColor(j);
            obj = view.getPaint();
            if (i != 0)
            {
                view = android.graphics.Paint.Style.FILL;
            } else
            {
                view = android.graphics.Paint.Style.STROKE;
            }
            ((Paint) (obj)).setStyle(view);
            ((ColorViewHolder) (viewgroup)).circleImage.invalidate();
            return view1;
        }

        public final boolean isEmpty()
        {
            return colors.isEmpty();
        }

        SingleChoiceColorDialogAdapter(Context context, ArrayList arraylist, int i, boolean flag)
        {
            inflater = LayoutInflater.from(context);
            colors = arraylist;
            selectedItem = i;
            calendarColorsOnly = flag;
        }

        private class ColorViewHolder
        {

            public final boolean calendarColorsOnly;
            public final ImageView check;
            public final ColorCircle circle;
            public final ImageView circleImage;
            public final TextView label;

            ColorViewHolder(ViewGroup viewgroup, boolean flag)
            {
                label = (TextView)viewgroup.findViewById(0x7f100188);
                circleImage = (ImageView)viewgroup.findViewById(0x7f100187);
                check = (ImageView)viewgroup.findViewById(0x7f100148);
                circle = new ColorCircle(viewgroup.getResources(), 0x7f0e0137);
                circleImage.setImageDrawable(circle);
                calendarColorsOnly = flag;
            }
        }

    }

}
