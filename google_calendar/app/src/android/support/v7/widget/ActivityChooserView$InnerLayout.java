// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

// Referenced classes of package android.support.v7.widget:
//            ActivityChooserView, TintTypedArray

public static class setBackgroundDrawable extends LinearLayout
{

    private static final int TINT_ATTRS[] = {
        0x10100d4
    };


    public (Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        context = new TintTypedArray(context, context.obtainStyledAttributes(attributeset, TINT_ATTRS));
        setBackgroundDrawable(context.getDrawable(0));
        ((TintTypedArray) (context)).mWrapped.recycle();
    }
}
