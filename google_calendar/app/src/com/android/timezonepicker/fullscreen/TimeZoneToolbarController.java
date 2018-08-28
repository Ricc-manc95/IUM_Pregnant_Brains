// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.timezonepicker.fullscreen;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import com.android.timezonepicker.TimeZoneManager;
import com.google.android.calendar.utils.text.TextWatcherAdapter;

final class TimeZoneToolbarController
{

    private final ImageButton clearButton;
    private final Context context;
    public final EditText editTextSearch;
    private final Toolbar toolbar;

    TimeZoneToolbarController(Context context1, Toolbar toolbar1, EditText edittext, ImageButton imagebutton, int i, int j, TimeZoneManager timezonemanager)
    {
        context = context1;
        toolbar = toolbar1;
        editTextSearch = edittext;
        clearButton = imagebutton;
        class .Lambda._cls1
            implements com.google.android.calendar.utils.text.TextWatcherAdapter.OnTextChangedListener
        {

            private final TimeZoneToolbarController arg$1;
            private final TimeZoneManager arg$2;

            public final void onTextChanged(String s)
            {
                TimeZoneToolbarController timezonetoolbarcontroller = arg$1;
                TimeZoneManager timezonemanager1 = arg$2;
                timezonemanager1.filteringRequest = s;
                if (timezonemanager1.timeZoneGroupsList != null)
                {
                    timezonemanager1.filter.filter(s);
                }
                timezonetoolbarcontroller.updateClearButtonVisibility();
            }

            .Lambda._cls1(TimeZoneManager timezonemanager)
            {
                arg$1 = TimeZoneToolbarController.this;
                arg$2 = timezonemanager;
            }
        }

        editTextSearch.addTextChangedListener(new TextWatcherAdapter(new .Lambda._cls1(timezonemanager)));
        class .Lambda._cls2
            implements android.view.View.OnClickListener
        {

            private final TimeZoneToolbarController arg$1;

            public final void onClick(View view)
            {
                arg$1.editTextSearch.setText("");
            }

            .Lambda._cls2()
            {
                arg$1 = TimeZoneToolbarController.this;
            }
        }

        clearButton.setOnClickListener(new .Lambda._cls2());
        i - 1;
        JVM INSTR tableswitch 0 1: default 92
    //                   0 102
    //                   1 137;
           goto _L1 _L2 _L3
_L1:
        throw new IllegalArgumentException("Unsupported theme.");
_L2:
        setToolbarStyle(0x7f0201c3, 0x7f0d0322, 0x7f0d0324, 0x7f0d0323, 0x7f0201d7);
_L5:
        toolbar.setBackgroundDrawable(new ColorDrawable(j));
        updateClearButtonVisibility();
        return;
_L3:
        setToolbarStyle(0x7f0201c5, 0x7f0d0326, 0x7f0d0328, 0x7f0d0327, 0x7f0201d9);
        if (true) goto _L5; else goto _L4
_L4:
    }

    private final void setToolbarStyle(int i, int j, int k, int l, int i1)
    {
        j = context.getResources().getColor(j);
        k = context.getResources().getColor(k);
        l = context.getResources().getColor(l);
        toolbar.setNavigationIcon(i);
        editTextSearch.setTextColor(j);
        editTextSearch.setLinkTextColor(j);
        editTextSearch.setHintTextColor(k);
        editTextSearch.setHighlightColor(l);
        clearButton.setImageDrawable(context.getResources().getDrawable(i1));
    }

    final void updateClearButtonVisibility()
    {
        boolean flag = false;
        ImageButton imagebutton;
        int i;
        if (!TextUtils.isEmpty(editTextSearch.getText()))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        imagebutton = clearButton;
        if (i != 0)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 8;
        }
        imagebutton.setVisibility(i);
    }
}
