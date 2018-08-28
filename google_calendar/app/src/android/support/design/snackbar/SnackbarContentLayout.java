// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.snackbar;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

// Referenced classes of package android.support.design.snackbar:
//            ContentViewCallback

public class SnackbarContentLayout extends LinearLayout
    implements ContentViewCallback
{

    public Button actionView;
    private int maxInlineActionWidth;
    private int maxWidth;
    public TextView messageView;

    public SnackbarContentLayout(Context context)
    {
        this(context, null);
    }

    public SnackbarContentLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        context = context.obtainStyledAttributes(attributeset, R.styleable.SnackbarLayout);
        maxWidth = context.getDimensionPixelSize(R.styleable.SnackbarLayout_android_maxWidth, -1);
        maxInlineActionWidth = context.getDimensionPixelSize(R.styleable.SnackbarLayout_maxActionInlineWidth, -1);
        context.recycle();
    }

    private final boolean updateViewsWithinLayout(int i, int j, int k)
    {
        boolean flag = false;
        if (i != getOrientation())
        {
            setOrientation(i);
            flag = true;
        }
        if (messageView.getPaddingTop() != j || messageView.getPaddingBottom() != k)
        {
            TextView textview = messageView;
            if (ViewCompat.isPaddingRelative(textview))
            {
                ViewCompat.setPaddingRelative(textview, ViewCompat.getPaddingStart(textview), j, ViewCompat.getPaddingEnd(textview), k);
            } else
            {
                textview.setPadding(textview.getPaddingLeft(), j, textview.getPaddingRight(), k);
            }
            flag = true;
        }
        return flag;
    }

    public final void animateContentIn(int i, int j)
    {
        messageView.setAlpha(0.0F);
        messageView.animate().alpha(1.0F).setDuration(180L).setStartDelay(70L).start();
        if (actionView.getVisibility() == 0)
        {
            actionView.setAlpha(0.0F);
            actionView.animate().alpha(1.0F).setDuration(180L).setStartDelay(70L).start();
        }
    }

    public final void animateContentOut(int i, int j)
    {
        messageView.setAlpha(1.0F);
        messageView.animate().alpha(0.0F).setDuration(180L).setStartDelay(0L).start();
        if (actionView.getVisibility() == 0)
        {
            actionView.setAlpha(1.0F);
            actionView.animate().alpha(0.0F).setDuration(180L).setStartDelay(0L).start();
        }
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        messageView = (TextView)findViewById(0x7f10003c);
        actionView = (Button)findViewById(0x7f10003b);
    }

    protected void onMeasure(int i, int j)
    {
        int l;
        int i1;
        super.onMeasure(i, j);
        int k = i;
        if (maxWidth > 0)
        {
            k = i;
            if (getMeasuredWidth() > maxWidth)
            {
                k = android.view.View.MeasureSpec.makeMeasureSpec(maxWidth, 0x40000000);
                super.onMeasure(k, j);
            }
        }
        l = getResources().getDimensionPixelSize(0x7f0e010a);
        i1 = getResources().getDimensionPixelSize(0x7f0e0109);
        if (messageView.getLayout().getLineCount() > 1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0 || maxInlineActionWidth <= 0 || actionView.getMeasuredWidth() <= maxInlineActionWidth) goto _L2; else goto _L1
_L1:
        if (!updateViewsWithinLayout(1, l, l - i1)) goto _L4; else goto _L3
_L3:
        i = 1;
_L6:
        if (i != 0)
        {
            super.onMeasure(k, j);
        }
        return;
_L2:
        if (i != 0)
        {
            i = l;
        } else
        {
            i = i1;
        }
        if (updateViewsWithinLayout(0, i, i))
        {
            i = 1;
            continue; /* Loop/switch isn't completed */
        }
_L4:
        i = 0;
        if (true) goto _L6; else goto _L5
_L5:
    }
}
