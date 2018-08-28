// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ListView;

// Referenced classes of package android.support.v7.app:
//            AlertController

public static class w_paddingTopNoTitle extends ListView
{

    public final int mPaddingBottomNoButtons;
    public final int mPaddingTopNoTitle;

    public (Context context)
    {
        this(context, null);
    }

    public <init>(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        context = context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.);
        mPaddingBottomNoButtons = context.getDimensionPixelOffset(android.support.v7.appcompat.ingBottomNoButtons, -1);
        mPaddingTopNoTitle = context.getDimensionPixelOffset(android.support.v7.appcompat.ingTopNoTitle, -1);
    }
}
