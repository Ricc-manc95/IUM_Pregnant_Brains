// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.text;

import android.text.Editable;
import android.text.TextWatcher;

public final class TextWatcherAdapter
    implements TextWatcher
{

    private final OnTextChangedListener listener;

    public TextWatcherAdapter(OnTextChangedListener ontextchangedlistener)
    {
        listener = ontextchangedlistener;
    }

    public final void afterTextChanged(Editable editable)
    {
    }

    public final void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    public final void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        listener.onTextChanged(charsequence.toString());
    }

    private class OnTextChangedListener
    {

        public abstract void onTextChanged(String s);
    }

}
