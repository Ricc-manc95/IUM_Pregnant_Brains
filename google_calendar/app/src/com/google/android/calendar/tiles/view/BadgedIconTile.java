// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.tiles.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.ImageView;

// Referenced classes of package com.google.android.calendar.tiles.view:
//            TileView

public class BadgedIconTile extends TileView
{

    private int badgeAlignLeft;
    private int badgeAlignTop;
    private int badgeDiameter;
    private ImageView badgeView;

    public BadgedIconTile(Context context)
    {
        super(context);
    }

    public BadgedIconTile(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    private final boolean shouldLayoutBadge()
    {
        if (badgeView != null)
        {
            boolean flag;
            if (super.icon != null && super.icon.getDrawable() != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag && getIcon().getVisibility() != 8 && badgeView.getVisibility() != 8)
            {
                return true;
            }
        }
        return false;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        if (shouldLayoutBadge())
        {
            if (getLayoutDirection() == 1)
            {
                i = getIcon().getRight() - badgeAlignLeft - badgeDiameter;
            } else
            {
                i = getIcon().getLeft() + badgeAlignLeft;
            }
            j = getIcon().getTop() + badgeAlignTop;
            badgeView.layout(i, j, badgeDiameter + i, badgeDiameter + j);
        }
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        if (shouldLayoutBadge())
        {
            i = android.view.View.MeasureSpec.makeMeasureSpec(badgeDiameter, 0x40000000);
            badgeView.measure(i, i);
        }
    }

    public final BadgedIconTile setBadge(Integer integer)
    {
        if (integer == null)
        {
            if (badgeView != null)
            {
                badgeView.setVisibility(8);
            }
            return this;
        }
        if (badgeView == null)
        {
            badgeView = new ImageView(getContext());
            addView(badgeView);
        }
        badgeView.setImageResource(integer.intValue());
        badgeView.setVisibility(0);
        return this;
    }

    protected final void setupDefaultValues()
    {
        super.setupDefaultValues();
        setIconSize(getResources().getDimensionPixelSize(0x7f0e0064));
        badgeDiameter = getResources().getDimensionPixelSize(0x7f0e0067);
        badgeAlignLeft = getResources().getDimensionPixelOffset(0x7f0e0065);
        badgeAlignTop = getResources().getDimensionPixelOffset(0x7f0e0066);
    }
}
