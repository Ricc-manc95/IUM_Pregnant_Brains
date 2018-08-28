// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.autoresizetext;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.widget.TextView;

public class AutoResizeTextView extends TextView
{

    private boolean allCaps;
    private final RectF availableSpaceRect;
    private final DisplayMetrics displayMetrics;
    private float lineSpacingExtra;
    private float lineSpacingMultiplier;
    private int maxHeight;
    private int maxLines;
    private float maxTextSize;
    private int maxWidth;
    private float minTextSize;
    private int resizeStepUnit;
    private final TextPaint textPaint;
    private final SparseIntArray textSizesCache;

    public AutoResizeTextView(Context context)
    {
        super(context, null, 0);
        displayMetrics = getResources().getDisplayMetrics();
        availableSpaceRect = new RectF();
        textSizesCache = new SparseIntArray();
        textPaint = new TextPaint();
        resizeStepUnit = 0;
        minTextSize = 16F;
        lineSpacingMultiplier = 1.0F;
        lineSpacingExtra = 0.0F;
        initialize(context, null, 0, 0);
    }

    public AutoResizeTextView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset, 0);
        displayMetrics = getResources().getDisplayMetrics();
        availableSpaceRect = new RectF();
        textSizesCache = new SparseIntArray();
        textPaint = new TextPaint();
        resizeStepUnit = 0;
        minTextSize = 16F;
        lineSpacingMultiplier = 1.0F;
        lineSpacingExtra = 0.0F;
        initialize(context, attributeset, 0, 0);
    }

    public AutoResizeTextView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        displayMetrics = getResources().getDisplayMetrics();
        availableSpaceRect = new RectF();
        textSizesCache = new SparseIntArray();
        textPaint = new TextPaint();
        resizeStepUnit = 0;
        minTextSize = 16F;
        lineSpacingMultiplier = 1.0F;
        lineSpacingExtra = 0.0F;
        initialize(context, attributeset, i, 0);
    }

    public AutoResizeTextView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        displayMetrics = getResources().getDisplayMetrics();
        availableSpaceRect = new RectF();
        textSizesCache = new SparseIntArray();
        textPaint = new TextPaint();
        resizeStepUnit = 0;
        minTextSize = 16F;
        lineSpacingMultiplier = 1.0F;
        lineSpacingExtra = 0.0F;
        initialize(context, attributeset, i, j);
    }

    private final void adjustTextSize()
    {
        if (maxWidth <= 0 || maxHeight <= 0)
        {
            return;
        }
        availableSpaceRect.right = maxWidth;
        availableSpaceRect.bottom = maxHeight;
        int j = (int)Math.ceil(minTextSize * (1.0F / TypedValue.applyDimension(resizeStepUnit, 1.0F, displayMetrics)));
        int k = (int)Math.floor(maxTextSize * (1.0F / TypedValue.applyDimension(resizeStepUnit, 1.0F, displayMetrics)));
        Object obj = availableSpaceRect;
        String s = getFormattedText();
        float f;
        if (s != null && textSizesCache.get(s.hashCode()) != 0)
        {
            f = textSizesCache.get(s.hashCode());
        } else
        {
            int i = j;
            for (j++; j <= k;)
            {
                int l = (j + k) / 2;
                f = TypedValue.applyDimension(resizeStepUnit, l, displayMetrics);
                textPaint.setTextSize(f);
                Object obj1 = getFormattedText();
                i = getMaxLines();
                if (i == 1)
                {
                    if (textPaint.getFontSpacing() <= ((RectF) (obj)).bottom && textPaint.measureText(((String) (obj1))) <= ((RectF) (obj)).right)
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                } else
                {
                    obj1 = new StaticLayout(((CharSequence) (obj1)), textPaint, maxWidth, android.text.Layout.Alignment.ALIGN_NORMAL, getLineSpacingMultiplier(), getLineSpacingExtra(), true);
                    if (i != -1 && ((StaticLayout) (obj1)).getLineCount() > i)
                    {
                        i = 0;
                    } else
                    if ((float)((StaticLayout) (obj1)).getHeight() > ((RectF) (obj)).bottom)
                    {
                        i = 0;
                    } else
                    {
                        i = 1;
                    }
                }
                if (i != 0)
                {
                    i = j;
                    j = l + 1;
                } else
                {
                    i = l - 1;
                    k = i;
                }
            }

            obj = textSizesCache;
            if (s == null)
            {
                j = 0;
            } else
            {
                j = s.hashCode();
            }
            ((SparseIntArray) (obj)).put(j, i);
            f = i;
        }
        super.setTextSize(resizeStepUnit, f);
    }

    private final String getFormattedText()
    {
        java.util.Locale locale = getTextLocale();
        if (allCaps)
        {
            return getText().toString().toUpperCase(locale);
        } else
        {
            return getText().toString();
        }
    }

    private final void initialize(Context context, AttributeSet attributeset, int i, int j)
    {
        TypedArray typedarray = context.getTheme().obtainStyledAttributes(attributeset, R.styleable.AutoResizeTextView, i, j);
        resizeStepUnit = typedarray.getInt(R.styleable.AutoResizeTextView_autoResizeText_resizeStepUnit, 0);
        minTextSize = (int)typedarray.getDimension(R.styleable.AutoResizeTextView_autoResizeText_minTextSize, 16F);
        maxTextSize = (int)getTextSize();
        allCaps = context.obtainStyledAttributes(attributeset, new int[] {
            0x101038c
        }).getBoolean(0, false);
        textPaint.set(getPaint());
    }

    public final float getLineSpacingExtra()
    {
        return super.getLineSpacingExtra();
    }

    public final float getLineSpacingMultiplier()
    {
        return super.getLineSpacingMultiplier();
    }

    public final int getMaxLines()
    {
        return super.getMaxLines();
    }

    protected final void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        maxWidth = android.view.View.MeasureSpec.getSize(i) - getPaddingLeft() - getPaddingRight();
        maxHeight = android.view.View.MeasureSpec.getSize(j) - getPaddingTop() - getPaddingBottom();
        adjustTextSize();
    }

    protected final void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        if (i != k || j != l)
        {
            textSizesCache.clear();
            adjustTextSize();
        }
    }

    protected final void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        super.onTextChanged(charsequence, i, j, k);
        adjustTextSize();
    }

    public void setAllCaps(boolean flag)
    {
        super.setAllCaps(flag);
        allCaps = flag;
    }

    public final void setLineSpacing(float f, float f1)
    {
        super.setLineSpacing(f, f1);
        lineSpacingMultiplier = f1;
        lineSpacingExtra = f;
    }

    public final void setMaxLines(int i)
    {
        super.setMaxLines(i);
        maxLines = i;
    }

    public final void setResizeStepUnit(int i)
    {
        if (resizeStepUnit != i)
        {
            resizeStepUnit = i;
            requestLayout();
        }
    }

    public final void setTextSize(int i, float f)
    {
        f = TypedValue.applyDimension(i, f, displayMetrics);
        if (maxTextSize != f)
        {
            maxTextSize = f;
            textSizesCache.clear();
            requestLayout();
        }
    }
}
