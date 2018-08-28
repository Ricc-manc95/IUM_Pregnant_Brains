// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.slab.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

public class ExpandableTextView extends TextView
{

    private int collapsedLineCount;
    private int expandedLineCount;
    public boolean isExpanded;

    public ExpandableTextView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        context = context.obtainStyledAttributes(attributeset, R.styleable.ExpandableTextView);
        collapsedLineCount = context.getInt(R.styleable.ExpandableTextView_collapsedLineCount, 0);
        expandedLineCount = context.getInt(R.styleable.ExpandableTextView_expandedLineCount, 0x7fffffff);
        context.recycle();
        setOnClickListener(new _cls1());
    }

    public void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        super.onTextChanged(charsequence, i, j, k);
        if (isExpanded)
        {
            i = expandedLineCount;
        } else
        {
            i = collapsedLineCount;
        }
        setMaxLines(i);
    }

    public void toggle(ExpandableTextViewState expandabletextviewstate)
    {
        boolean flag;
        if (!isExpanded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag != isExpanded)
        {
            isExpanded = flag;
            int i;
            if (isExpanded)
            {
                i = expandedLineCount;
            } else
            {
                i = collapsedLineCount;
            }
            setMaxLines(i);
        }
    }

    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final ExpandableTextView this$0;

        public final void onClick(View view)
        {
            toggle(null);
            requestLayout();
        }

        _cls1()
        {
            this$0 = ExpandableTextView.this;
            super();
        }
    }

}
