// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

// Referenced classes of package android.support.v7.widget:
//            TintTypedArray

public final class ActivityChooserView extends ViewGroup
{
    public static class InnerLayout extends LinearLayout
    {

        private static final int TINT_ATTRS[] = {
            0x10100d4
        };


        public InnerLayout(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            context = new TintTypedArray(context, context.obtainStyledAttributes(attributeset, TINT_ATTRS));
            setBackgroundDrawable(context.getDrawable(0));
            ((TintTypedArray) (context)).mWrapped.recycle();
        }
    }


    protected final void onAttachedToWindow()
    {
        throw new NoSuchMethodError();
    }

    protected final void onDetachedFromWindow()
    {
        throw new NoSuchMethodError();
    }

    protected final void onLayout(boolean flag, int i, int j, int k, int l)
    {
        throw new NoSuchMethodError();
    }

    protected final void onMeasure(int i, int j)
    {
        throw new NoSuchMethodError();
    }

    public final void setDefaultActionButtonContentDescription(int i)
    {
    }

    public final void setExpandActivityOverflowButtonContentDescription(int i)
    {
        getContext().getString(i);
        throw new NullPointerException();
    }

    public final void setInitialActivityCount(int i)
    {
    }
}
