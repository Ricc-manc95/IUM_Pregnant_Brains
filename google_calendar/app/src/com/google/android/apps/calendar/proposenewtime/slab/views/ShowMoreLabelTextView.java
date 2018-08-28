// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.slab.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.util.AttributeSet;
import java.util.Locale;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime.slab.views:
//            ExpandableTextView

public class ShowMoreLabelTextView extends ExpandableTextView
{

    private SpannableString blueMoreLabel;
    private Rect bounds;
    public CharSequence fullText;
    private String moreLabel;
    private Paint paint;

    public ShowMoreLabelTextView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        bounds = new Rect();
        paint = new Paint();
        setExpandLabel();
    }

    private final int getTextWidth(String s)
    {
        paint.setTextSize(getTextSize());
        paint.getTextBounds(s, 0, s.length(), bounds);
        return (int)Math.ceil(bounds.width());
    }

    private final void setExpandLabel()
    {
        String s;
        if (super.isExpanded)
        {
            s = getContext().getString(0x7f1303bc);
        } else
        {
            s = getContext().getString(0x7f1303bd);
        }
        s = String.valueOf(s.toUpperCase(Locale.getDefault()));
        if (s.length() != 0)
        {
            s = " ".concat(s);
        } else
        {
            s = new String(" ");
        }
        moreLabel = s;
        blueMoreLabel = new SpannableString(moreLabel);
        blueMoreLabel.setSpan(new TypefaceSpan("sans-serif-medium"), 0, moreLabel.length(), 17);
        blueMoreLabel.setSpan(new AbsoluteSizeSpan(getResources().getDimensionPixelSize(0x7f0e032b)), 0, moreLabel.length(), 17);
        blueMoreLabel.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(0x7f0d01d7)), 0, moreLabel.length(), 17);
    }

    protected void onMeasure(int i, int j)
    {
        if (fullText != null)
        {
            setText(fullText);
        }
        super.onMeasure(i, j);
        Object obj;
        if (length() != 0)
        {
            if ((obj = getLayout()) != null)
            {
                int k = ((Layout) (obj)).getLineCount();
                int i1 = k - 1;
                if (k > 0 && ((Layout) (obj)).getEllipsisCount(i1) > 0)
                {
                    Object obj1 = fullText.toString().substring(((Layout) (obj)).getLineStart(0), ((Layout) (obj)).getLineStart(i1));
                    String s = fullText.toString().substring(((Layout) (obj)).getLineStart(i1), ((Layout) (obj)).getLineEnd(i1) - ((Layout) (obj)).getEllipsisCount(i1));
                    obj = String.valueOf(obj1);
                    String s1 = String.valueOf(s);
                    int l;
                    if (s1.length() != 0)
                    {
                        obj = ((String) (obj)).concat(s1);
                    } else
                    {
                        obj = new String(((String) (obj)));
                    }
                    i1 = getTextWidth(((String) (obj)));
                    l = Math.min(moreLabel.length() + 1, s.length());
                    obj = s.substring(0, s.length() - l);
                    s = moreLabel;
                    for (l = getTextWidth((new StringBuilder(String.valueOf(obj1).length() + 1 + String.valueOf(obj).length() + String.valueOf(s).length())).append(((String) (obj1))).append(((String) (obj))).append("\u2026").append(s).toString()); l > i1 && ((String) (obj)).length() > 0; l = getTextWidth((new StringBuilder(String.valueOf(obj1).length() + 1 + String.valueOf(obj).length() + String.valueOf(s).length())).append(((String) (obj1))).append(((String) (obj))).append("\u2026").append(s).toString()))
                    {
                        obj = ((String) (obj)).substring(0, ((String) (obj)).length() - 1).trim();
                        s = moreLabel;
                    }

                    obj1 = (new SpannableStringBuilder()).append(((CharSequence) (obj1)));
                    if (((String) (obj)).length() > 0)
                    {
                        ((SpannableStringBuilder) (obj1)).append(((CharSequence) (obj))).append("\u2026");
                    }
                    ((SpannableStringBuilder) (obj1)).append(blueMoreLabel);
                    super.setText(((CharSequence) (obj1)));
                    if (l > i1)
                    {
                        super.onMeasure(i, j);
                        return;
                    }
                }
            }
        }
    }

    public final void setFullText(CharSequence charsequence)
    {
        super.setText(charsequence);
        fullText = charsequence;
    }

    public final void toggle(ExpandableTextView.ExpandableTextViewState expandabletextviewstate)
    {
        super.toggle(expandabletextviewstate);
        setExpandLabel();
    }
}
