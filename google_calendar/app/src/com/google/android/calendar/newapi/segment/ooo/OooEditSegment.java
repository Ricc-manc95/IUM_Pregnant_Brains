// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.ooo;

import android.content.Context;
import android.support.design.textfield.TextInputLayout;
import android.util.AttributeSet;
import android.widget.EditText;
import com.google.android.calendar.utils.text.TextWatcherAdapter;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;

public class OooEditSegment extends EditSegment
{
    public static interface Listener
    {

        public abstract void onAutoDeclineMessageTextChanged(String s);
    }


    public EditText autoDeclineMessageText;
    public TextInputLayout autoDeclineTextInputLayout;

    public OooEditSegment(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        autoDeclineMessageText = (EditText)findViewById(0x7f10028b);
        autoDeclineTextInputLayout = (TextInputLayout)findViewById(0x7f10028a);
        class .Lambda._cls0
            implements com.google.android.calendar.utils.text.TextWatcherAdapter.OnTextChangedListener
        {

            private final OooEditSegment arg$1;

            public final void onTextChanged(String s)
            {
                ((Listener)((EditSegment) (arg$1)).mListener).onAutoDeclineMessageTextChanged(s);
            }

            .Lambda._cls0()
            {
                arg$1 = OooEditSegment.this;
            }
        }

        autoDeclineMessageText.addTextChangedListener(new TextWatcherAdapter(new .Lambda._cls0()));
    }
}
