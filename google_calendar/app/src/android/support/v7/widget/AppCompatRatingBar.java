// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;

// Referenced classes of package android.support.v7.widget:
//            AppCompatProgressBarHelper

public final class AppCompatRatingBar extends RatingBar
{

    private final AppCompatProgressBarHelper mAppCompatProgressBarHelper;

    public AppCompatRatingBar(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f0100da);
    }

    private AppCompatRatingBar(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mAppCompatProgressBarHelper = new AppCompatProgressBarHelper(this);
        mAppCompatProgressBarHelper.loadFromAttributes(attributeset, i);
    }

    protected final void onMeasure(int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        Bitmap bitmap;
        super.onMeasure(i, j);
        bitmap = mAppCompatProgressBarHelper.mSampleTile;
        if (bitmap == null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        setMeasuredDimension(View.resolveSizeAndState(bitmap.getWidth() * getNumStars(), i, 0), getMeasuredHeight());
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }
}
