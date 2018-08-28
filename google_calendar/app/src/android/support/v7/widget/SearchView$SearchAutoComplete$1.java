// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

final class this._cls0
    implements Runnable
{

    private final asPendingShowSoftInputRequest this$0;

    public final void run()
    {
        this._cls0 _lcls0 = this._cls0.this;
        if (_lcls0.asPendingShowSoftInputRequest)
        {
            ((InputMethodManager)_lcls0.tContext().getSystemService("input_method")).showSoftInput(_lcls0, 0);
            _lcls0.asPendingShowSoftInputRequest = false;
        }
    }

    ()
    {
        this$0 = this._cls0.this;
        super();
    }
}
