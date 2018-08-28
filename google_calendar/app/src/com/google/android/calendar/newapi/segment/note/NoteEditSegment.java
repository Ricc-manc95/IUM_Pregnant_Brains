// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.note;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.google.android.calendar.common.view.NinjaEditText;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;

public class NoteEditSegment extends EditSegment
{
    public static interface Listener
    {

        public abstract void onNoteChanged(String s);

        public abstract void onNoteEditStart();
    }


    private TextView noteEditPreview;
    public NinjaEditText noteEditText;

    public NoteEditSegment(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        noteEditPreview = (TextView)findViewById(0x7f100288);
        noteEditText = (NinjaEditText)findViewById(0x7f100289);
        noteEditText.addTextChangedListener(new _cls1());
        class .Lambda._cls0
            implements android.view.View.OnClickListener
        {

            private final NoteEditSegment arg$1;

            public final void onClick(View view)
            {
                view = arg$1;
                if (((NoteEditSegment) (view)).mListener != null)
                {
                    ((Listener)((NoteEditSegment) (view)).mListener).onNoteEditStart();
                }
            }

            .Lambda._cls0()
            {
                arg$1 = NoteEditSegment.this;
            }
        }

        noteEditPreview.setOnClickListener(new .Lambda._cls0());
    }

    final void setNote(CharSequence charsequence, boolean flag)
    {
        int i = 1;
        boolean flag1 = false;
        noteEditPreview.setText(charsequence);
        NinjaEditText ninjaedittext = noteEditText;
        ninjaedittext.stealth = true;
        ninjaedittext.setText(charsequence);
        ninjaedittext.stealth = false;
        charsequence = noteEditPreview;
        if (flag)
        {
            i = 0;
        }
        if (charsequence != null)
        {
            if (i != 0)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            charsequence.setVisibility(i);
        }
        charsequence = noteEditText;
        if (charsequence != null)
        {
            if (flag)
            {
                i = ((flag1) ? 1 : 0);
            } else
            {
                i = 8;
            }
            charsequence.setVisibility(i);
        }
    }

    private class _cls1
        implements TextWatcher
    {

        private final NoteEditSegment this$0;

        public final void afterTextChanged(Editable editable)
        {
            if (mListener == null || editable == null)
            {
                return;
            } else
            {
                ((Listener)mListener).onNoteChanged(editable.toString());
                return;
            }
        }

        public final void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        public final void onTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        _cls1()
        {
            this$0 = NoteEditSegment.this;
            super();
        }
    }

}
