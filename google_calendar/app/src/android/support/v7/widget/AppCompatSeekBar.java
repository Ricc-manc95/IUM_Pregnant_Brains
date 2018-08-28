// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;

// Referenced classes of package android.support.v7.widget:
//            AppCompatSeekBarHelper, AppCompatProgressBarHelper

public final class AppCompatSeekBar extends SeekBar
{

    private final AppCompatSeekBarHelper mAppCompatSeekBarHelper;

    public AppCompatSeekBar(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f0100dd);
    }

    private AppCompatSeekBar(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mAppCompatSeekBarHelper = new AppCompatSeekBarHelper(this);
        mAppCompatSeekBarHelper.loadFromAttributes(attributeset, i);
    }

    protected final void drawableStateChanged()
    {
        super.drawableStateChanged();
        AppCompatSeekBarHelper appcompatseekbarhelper = mAppCompatSeekBarHelper;
        Drawable drawable = appcompatseekbarhelper.mTickMark;
        if (drawable != null && drawable.isStateful() && drawable.setState(appcompatseekbarhelper.mView.getDrawableState()))
        {
            appcompatseekbarhelper.mView.invalidateDrawable(drawable);
        }
    }

    public final void jumpDrawablesToCurrentState()
    {
        super.jumpDrawablesToCurrentState();
        AppCompatSeekBarHelper appcompatseekbarhelper = mAppCompatSeekBarHelper;
        if (appcompatseekbarhelper.mTickMark != null)
        {
            appcompatseekbarhelper.mTickMark.jumpToCurrentState();
        }
    }

    protected final void onDraw(Canvas canvas)
    {
        int j = 1;
        this;
        JVM INSTR monitorenter ;
        AppCompatSeekBarHelper appcompatseekbarhelper;
        super.onDraw(canvas);
        appcompatseekbarhelper = mAppCompatSeekBarHelper;
        if (appcompatseekbarhelper.mTickMark == null) goto _L2; else goto _L1
_L1:
        int k = appcompatseekbarhelper.mView.getMax();
        if (k <= 1) goto _L2; else goto _L3
_L3:
        int i;
        int l;
        i = appcompatseekbarhelper.mTickMark.getIntrinsicWidth();
        l = appcompatseekbarhelper.mTickMark.getIntrinsicHeight();
        if (i < 0) goto _L5; else goto _L4
_L4:
        i /= 2;
_L9:
        if (l < 0)
        {
            break MISSING_BLOCK_LABEL_77;
        }
        j = l / 2;
        float f;
        appcompatseekbarhelper.mTickMark.setBounds(-i, -j, i, j);
        f = (float)(appcompatseekbarhelper.mView.getWidth() - appcompatseekbarhelper.mView.getPaddingLeft() - appcompatseekbarhelper.mView.getPaddingRight()) / (float)k;
        j = canvas.save();
        canvas.translate(appcompatseekbarhelper.mView.getPaddingLeft(), appcompatseekbarhelper.mView.getHeight() / 2);
        i = 0;
_L7:
        if (i > k)
        {
            break; /* Loop/switch isn't completed */
        }
        appcompatseekbarhelper.mTickMark.draw(canvas);
        canvas.translate(f, 0.0F);
        i++;
        if (true) goto _L7; else goto _L6
_L6:
        canvas.restoreToCount(j);
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        canvas;
        throw canvas;
_L5:
        i = 1;
        if (true) goto _L9; else goto _L8
_L8:
    }
}
