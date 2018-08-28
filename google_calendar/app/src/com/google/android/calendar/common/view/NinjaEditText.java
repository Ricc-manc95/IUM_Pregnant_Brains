// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.view;

import android.content.Context;
import android.os.Parcelable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import java.util.IdentityHashMap;

public class NinjaEditText extends EditText
{

    public boolean stealth;
    private IdentityHashMap watcherToWrapper;

    public NinjaEditText(Context context)
    {
        super(context);
        stealth = false;
    }

    public NinjaEditText(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        stealth = false;
    }

    public NinjaEditText(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        stealth = false;
    }

    public NinjaEditText(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        stealth = false;
    }

    public void addTextChangedListener(final TextWatcher watcher)
    {
        _cls1 _lcls1 = new _cls1();
        if (watcherToWrapper == null)
        {
            watcherToWrapper = new IdentityHashMap();
        }
        watcherToWrapper.put(watcher, _lcls1);
        super.addTextChangedListener(_lcls1);
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        stealth = true;
        super.onRestoreInstanceState(parcelable);
        stealth = false;
    }

    public void removeTextChangedListener(TextWatcher textwatcher)
    {
        if (watcherToWrapper != null)
        {
            if ((textwatcher = (TextWatcher)watcherToWrapper.get(textwatcher)) != null)
            {
                super.removeTextChangedListener(textwatcher);
                return;
            }
        }
    }

    private class _cls1
        implements TextWatcher
    {

        private final NinjaEditText this$0;
        private final TextWatcher val$watcher;

        public final void afterTextChanged(Editable editable)
        {
            if (!stealth)
            {
                watcher.afterTextChanged(editable);
            }
        }

        public final void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
        {
            if (!stealth)
            {
                watcher.beforeTextChanged(charsequence, i, j, k);
            }
        }

        public final void onTextChanged(CharSequence charsequence, int i, int j, int k)
        {
            if (!stealth)
            {
                watcher.onTextChanged(charsequence, i, j, k);
            }
        }

        _cls1()
        {
            this$0 = NinjaEditText.this;
            watcher = textwatcher;
            super();
        }
    }

}
