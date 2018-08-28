// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.content.res.Resources;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import com.google.android.calendar.event.conference.AccessCode;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.event:
//            AccessCodePickerDialog

final class selectedItemIndex extends BaseAdapter
{

    public int selectedItemIndex;
    private final AccessCodePickerDialog this$0;

    public final int getCount()
    {
        return accessCodes.size() + 1;
    }

    public final Object getItem(int i)
    {
        if (i < accessCodes.size())
        {
            return (AccessCode)accessCodes.get(i);
        } else
        {
            return null;
        }
    }

    public final long getItemId(int i)
    {
        return (long)i;
    }

    public final View getView(int i, View view, ViewGroup viewgroup)
    {
        CheckedTextView checkedtextview;
        viewgroup = view;
        if (view == null)
        {
            viewgroup = inflater.inflate(0x7f050060, null);
        }
        checkedtextview = (CheckedTextView)viewgroup.findViewById(0x7f100042);
        if (i >= accessCodes.size())
        {
            break MISSING_BLOCK_LABEL_219;
        }
        view = (AccessCode)accessCodes.get(i);
        ((AccessCode) (view)).type;
        JVM INSTR tableswitch 2 3: default 88
    //                   2 165
    //                   3 192;
           goto _L1 _L2 _L3
_L1:
        view = ((AccessCode) (view)).number;
_L4:
        SpannableString spannablestring = new SpannableString(view);
        spannablestring.setSpan((new android.text.style.er(view)).er(), 0, spannablestring.length(), 33);
        checkedtextview.setText(spannablestring);
        checkedtextview.setContentDescription(spannablestring);
_L5:
        boolean flag;
        if (selectedItemIndex == i)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        checkedtextview.setChecked(flag);
        return viewgroup;
_L2:
        view = resources.getString(0x7f130049, new Object[] {
            ((AccessCode) (view)).number
        });
          goto _L4
_L3:
        view = resources.getString(0x7f13004b, new Object[] {
            ((AccessCode) (view)).number
        });
          goto _L4
        view = resources.getString(0x7f13004a);
        checkedtextview.setText(view);
        checkedtextview.setContentDescription(view);
          goto _L5
    }

    ()
    {
        this$0 = AccessCodePickerDialog.this;
        super();
        selectedItemIndex = 0;
    }
}
