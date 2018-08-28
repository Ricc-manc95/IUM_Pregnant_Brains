// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.textfield;

import android.text.Editable;
import android.text.TextWatcher;

// Referenced classes of package android.support.design.textfield:
//            TextInputLayout

final class this._cls0
    implements TextWatcher
{

    private final TextInputLayout this$0;

    public final void afterTextChanged(Editable editable)
    {
        TextInputLayout textinputlayout = TextInputLayout.this;
        boolean flag;
        if (!restoringSavedState)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        textinputlayout.updateLabelState(flag, false);
        if (counterEnabled)
        {
            updateCounter(editable.length());
        }
    }

    public final void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    public final void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    ()
    {
        this$0 = TextInputLayout.this;
        super();
    }
}
