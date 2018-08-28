// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package androidx.browser.browseractions;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

public class BrowserActionsFallbackMenuView extends LinearLayout
{

    private final int mBrowserActionsMenuMaxWidthPx = getResources().getDimensionPixelOffset(0x7f0e0076);
    private final int mBrowserActionsMenuMinPaddingPx = getResources().getDimensionPixelOffset(0x7f0e0077);

    public BrowserActionsFallbackMenuView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(android.view.View.MeasureSpec.makeMeasureSpec(Math.min(getResources().getDisplayMetrics().widthPixels - mBrowserActionsMenuMinPaddingPx * 2, mBrowserActionsMenuMaxWidthPx), 0x40000000), j);
    }
}
