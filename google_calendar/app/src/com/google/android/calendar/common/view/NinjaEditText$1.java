// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.view;

import android.text.Editable;
import android.text.TextWatcher;

// Referenced classes of package com.google.android.calendar.common.view:
//            NinjaEditText

final class val.watcher
    implements TextWatcher
{

    private final NinjaEditText this$0;
    private final TextWatcher val$watcher;

    public final void afterTextChanged(Editable editable)
    {
        if (!stealth)
        {
            val$watcher.afterTextChanged(editable);
        }
    }

    public final void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        if (!stealth)
        {
            val$watcher.beforeTextChanged(charsequence, i, j, k);
        }
    }

    public final void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        if (!stealth)
        {
            val$watcher.onTextChanged(charsequence, i, j, k);
        }
    }

    ()
    {
        this$0 = final_ninjaedittext;
        val$watcher = TextWatcher.this;
        super();
    }
}
