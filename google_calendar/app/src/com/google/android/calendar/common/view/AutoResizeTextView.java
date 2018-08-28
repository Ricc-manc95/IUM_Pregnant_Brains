// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.view;

import android.content.Context;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

public class AutoResizeTextView extends TextView
{

    private float desiredTextSize;
    private int desiredWidth;
    private final TextPaint recyclePaint = new TextPaint();

    public AutoResizeTextView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    private final void updateDesiredWidth()
    {
        recyclePaint.setTextSize(desiredTextSize);
        desiredWidth = (int)Math.ceil(recyclePaint.measureText(getText().toString())) + getPaddingLeft() + getPaddingRight();
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        desiredTextSize = getTextSize();
        recyclePaint.set(getPaint());
        updateDesiredWidth();
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        l = getWidth() - getPaddingLeft() - getPaddingRight();
        if (desiredWidth > l)
        {
            TextPaint textpaint = recyclePaint;
            String s = getText().toString();
            k = (int)desiredTextSize;
            i = 0;
            for (j = 1; j <= k;)
            {
                int i1 = (j + k) / 2;
                textpaint.setTextSize(i1);
                if ((int)Math.ceil(textpaint.measureText(s)) <= l)
                {
                    i = j;
                    j = i1 + 1;
                } else
                {
                    i = i1 - 1;
                    k = i;
                }
            }

        } else
        {
            i = (int)desiredTextSize;
        }
        setTextSize(0, i);
    }

    protected void onMeasure(int i, int j)
    {
        android.view.View.MeasureSpec.getMode(i);
        JVM INSTR lookupswitch 2: default 32
    //                   0: 62
    //                   1073741824: 54;
           goto _L1 _L2 _L3
_L1:
        i = Math.min(android.view.View.MeasureSpec.getSize(i), desiredWidth);
_L5:
        setMeasuredDimension(i, android.view.View.MeasureSpec.getSize(j));
        return;
_L3:
        i = android.view.View.MeasureSpec.getSize(i);
        continue; /* Loop/switch isn't completed */
_L2:
        i = desiredWidth;
        if (true) goto _L5; else goto _L4
_L4:
    }

    protected void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        super.onTextChanged(charsequence, i, j, k);
        if (recyclePaint != null)
        {
            updateDesiredWidth();
        }
        invalidate();
        requestLayout();
    }
}
