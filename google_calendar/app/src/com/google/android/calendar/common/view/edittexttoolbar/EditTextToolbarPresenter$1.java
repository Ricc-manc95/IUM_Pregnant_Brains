// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.view.edittexttoolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

// Referenced classes of package com.google.android.calendar.common.view.edittexttoolbar:
//            EditTextToolbarPresenter

final class this._cls0
    implements TextWatcher
{

    private final EditTextToolbarPresenter this$0;

    public final void afterTextChanged(Editable editable)
    {
        updateButtonVisibilities();
        if (callback != null)
        {
            boolean flag;
            if (editContainer.getVisibility() == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                callback.searchStringChanged(editable.toString());
            }
        }
    }

    public final void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    public final void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    llback()
    {
        this$0 = EditTextToolbarPresenter.this;
        super();
    }
}
