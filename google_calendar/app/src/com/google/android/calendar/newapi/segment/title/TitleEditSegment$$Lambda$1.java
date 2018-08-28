// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.title;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

// Referenced classes of package com.google.android.calendar.newapi.segment.title:
//            TitleEditSegment

final class arg._cls1
    implements android.widget.ener
{

    private final TitleEditSegment arg$1;

    public final boolean onEditorAction(TextView textview, int i, KeyEvent keyevent)
    {
        textview = arg$1;
        if (i == 6 || i == 1)
        {
            ((arg._cls1)((TitleEditSegment) (textview)).mListener).nKeyboardDone();
            textview = ((TitleEditSegment) (textview)).titleEditText;
            ((InputMethodManager)textview.getContext().getSystemService("input_method")).hideSoftInputFromWindow(textview.getWindowToken(), 0);
            return true;
        } else
        {
            return false;
        }
    }

    Q(TitleEditSegment titleeditsegment)
    {
        arg$1 = titleeditsegment;
    }
}
