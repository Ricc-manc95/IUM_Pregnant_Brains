// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.calendar;

import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

// Referenced classes of package com.google.android.calendar.settings.calendar:
//            ColorPreferenceController

public final class ColorPreferenceDialogFragment extends PreferenceDialogFragmentCompat
{

    public ColorPreferenceDialogFragment()
    {
    }

    protected final void onBindDialogView(View view)
    {
        super.onBindDialogView(view);
        class .Lambda._cls0
            implements Supplier
        {

            private final ColorPreferenceDialogFragment arg$1;

            public final Object get()
            {
                ColorPreferenceDialogFragment colorpreferencedialogfragment = arg$1;
                if (((Fragment) (colorpreferencedialogfragment)).mLayoutInflater == null)
                {
                    colorpreferencedialogfragment.mLayoutInflater = colorpreferencedialogfragment.onGetLayoutInflater(null);
                    return ((Fragment) (colorpreferencedialogfragment)).mLayoutInflater;
                } else
                {
                    return ((Fragment) (colorpreferencedialogfragment)).mLayoutInflater;
                }
            }

            .Lambda._cls0()
            {
                arg$1 = ColorPreferenceDialogFragment.this;
            }
        }

        class .Lambda._cls1
            implements Consumer
        {

            private final ColorPreferenceDialogFragment arg$1;

            public final void accept(Object obj)
            {
                ColorPreferenceDialogFragment colorpreferencedialogfragment = arg$1;
                obj = (String)obj;
                colorpreferencedialogfragment.getDialog().hide();
            }

            .Lambda._cls1()
            {
                arg$1 = ColorPreferenceDialogFragment.this;
            }
        }

        view = new ColorPreferenceController((RecyclerView)view.findViewById(0x7f100136), new .Lambda._cls0(), new .Lambda._cls1());
        LinearLayoutManager linearlayoutmanager = new LinearLayoutManager(((ColorPreferenceController) (view)).recyclerView.getContext());
        ((ColorPreferenceController) (view)).recyclerView.setLayoutManager(linearlayoutmanager);
        ((ColorPreferenceController) (view)).recyclerView.setAdapter(new ColorPreferenceController._cls1(view, new String[] {
            "Red", "Blue", "Green"
        }));
    }

    public final void onDialogClosed(boolean flag)
    {
    }
}
