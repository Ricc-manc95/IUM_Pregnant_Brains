// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.featurehighlight;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

// Referenced classes of package com.google.android.libraries.material.featurehighlight:
//            FeatureHighlightContent

public class TextContentView extends LinearLayout
    implements FeatureHighlightContent
{

    private static final int SPACING_ATTRS[] = {
        0x1010217
    };
    private TextView bodyTextView;
    public FeatureHighlightView.InteractionCallback callback;
    private TextView dismissActionTextView;
    private TextView headerTextView;
    private String text;

    public TextContentView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    private final void setLineSpacingFromStyle(TextView textview, int i)
    {
        TypedArray typedarray = null;
        TypedArray typedarray1 = getContext().obtainStyledAttributes(i, SPACING_ATTRS);
        typedarray = typedarray1;
        textview.setLineSpacing(typedarray1.getDimension(0, textview.getLineSpacingExtra()), textview.getLineSpacingMultiplier());
        if (typedarray1 != null)
        {
            typedarray1.recycle();
        }
        return;
        textview;
        if (typedarray != null)
        {
            typedarray.recycle();
        }
        throw textview;
    }

    private static void setTextAlignment(TextView textview, int i)
    {
        switch (i)
        {
        default:
            return;

        case 1: // '\001'
            textview.setTextAlignment(4);
            return;

        case 0: // '\0'
            textview.setTextAlignment(5);
            return;

        case 2: // '\002'
            textview.setTextAlignment(6);
            break;
        }
    }

    public final View asView()
    {
        return this;
    }

    public final String getText()
    {
        return text;
    }

    public final boolean isEmpty()
    {
        return TextUtils.isEmpty(text);
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        TextView textview = (TextView)findViewById(0x7f100018);
        if (textview == null)
        {
            throw new NullPointerException();
        }
        headerTextView = (TextView)textview;
        textview = (TextView)findViewById(0x7f100017);
        if (textview == null)
        {
            throw new NullPointerException();
        }
        bodyTextView = (TextView)textview;
        textview = (TextView)findViewById(0x7f100016);
        if (textview == null)
        {
            throw new NullPointerException();
        } else
        {
            dismissActionTextView = (TextView)textview;
            return;
        }
    }

    public void setBodyTextAlignment(int i)
    {
        setTextAlignment(bodyTextView, i);
    }

    public void setBodyTextAppearance(int i)
    {
        TextView textview = bodyTextView;
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            textview.setTextAppearance(i);
        } else
        {
            textview.setTextAppearance(textview.getContext(), i);
        }
        setLineSpacingFromStyle(bodyTextView, i);
    }

    public void setBodyTextSize(float f)
    {
        bodyTextView.setTextSize(f);
    }

    public final void setCallback(FeatureHighlightView.InteractionCallback interactioncallback)
    {
        callback = interactioncallback;
    }

    public void setDismissActionTextAlignment(int i)
    {
        setTextAlignment(dismissActionTextView, i);
    }

    public void setDismissActionTextAppearance(int i)
    {
        TextView textview = dismissActionTextView;
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            textview.setTextAppearance(i);
        } else
        {
            textview.setTextAppearance(textview.getContext(), i);
        }
        setLineSpacingFromStyle(dismissActionTextView, i);
    }

    public void setHeaderTextAlignment(int i)
    {
        setTextAlignment(headerTextView, i);
    }

    public void setHeaderTextAppearance(int i)
    {
        TextView textview = headerTextView;
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            textview.setTextAppearance(i);
        } else
        {
            textview.setTextAppearance(textview.getContext(), i);
        }
        setLineSpacingFromStyle(headerTextView, i);
    }

    public void setHeaderTextSize(float f)
    {
        headerTextView.setTextSize(f);
    }

    public final void setText(CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2)
    {
        byte byte1 = 8;
        Object obj = headerTextView;
        ((TextView) (obj)).setText(charsequence);
        class .Lambda._cls0
            implements android.view.View.OnClickListener
        {

            private final TextContentView arg$1;

            public final void onClick(View view)
            {
                arg$1.callback.onDismiss();
            }

            .Lambda._cls0()
            {
                arg$1 = TextContentView.this;
            }
        }

        byte byte0;
        if (TextUtils.isEmpty(charsequence))
        {
            byte0 = 8;
        } else
        {
            byte0 = 0;
        }
        ((TextView) (obj)).setVisibility(byte0);
        obj = bodyTextView;
        ((TextView) (obj)).setText(charsequence1);
        if (TextUtils.isEmpty(charsequence1))
        {
            byte0 = 8;
        } else
        {
            byte0 = 0;
        }
        ((TextView) (obj)).setVisibility(byte0);
        obj = dismissActionTextView;
        ((TextView) (obj)).setText(charsequence2);
        if (TextUtils.isEmpty(charsequence2))
        {
            byte0 = byte1;
        } else
        {
            byte0 = 0;
        }
        ((TextView) (obj)).setVisibility(byte0);
        dismissActionTextView.setOnClickListener(new .Lambda._cls0());
        obj = new StringBuilder();
        if (!TextUtils.isEmpty(charsequence))
        {
            ((StringBuilder) (obj)).append(charsequence);
        }
        if (!TextUtils.isEmpty(charsequence1))
        {
            if (!TextUtils.isEmpty(charsequence))
            {
                ((StringBuilder) (obj)).append('\n');
            }
            ((StringBuilder) (obj)).append(charsequence1);
        }
        if (!TextUtils.isEmpty(charsequence2))
        {
            if (!TextUtils.isEmpty(charsequence) || !TextUtils.isEmpty(charsequence1))
            {
                ((StringBuilder) (obj)).append('\n');
            }
            ((StringBuilder) (obj)).append(charsequence2);
        }
        text = ((StringBuilder) (obj)).toString();
    }

}
