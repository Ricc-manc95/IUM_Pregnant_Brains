// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;

// Referenced classes of package com.google.android.calendar:
//            SearchActivity, KeyboardManipulator

final class arg._cls1
    implements android.view.vity..Lambda._cls1
{

    private final SearchActivity arg$1;

    public final void onClick(View view)
    {
        SearchActivity searchactivity = arg$1;
        if (view.getId() == 0x7f100359)
        {
            searchactivity.searchEditText.setText("");
            view = searchactivity.keyboardManipulator;
            view.showPendingSince = SystemClock.uptimeMillis();
            view.showIfNecessary();
        }
    }

    (SearchActivity searchactivity)
    {
        arg$1 = searchactivity;
    }
}
