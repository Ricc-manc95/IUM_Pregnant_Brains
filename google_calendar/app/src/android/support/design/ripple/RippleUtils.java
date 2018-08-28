// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.ripple;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;
import android.util.StateSet;

public final class RippleUtils
{

    private static final int FOCUSED_STATE_SET[] = {
        0x101009c
    };
    private static final int HOVERED_FOCUSED_STATE_SET[] = {
        0x1010367, 0x101009c
    };
    private static final int HOVERED_STATE_SET[] = {
        0x1010367
    };
    private static final int PRESSED_STATE_SET[] = {
        0x10100a7
    };
    private static final int SELECTED_FOCUSED_STATE_SET[] = {
        0x10100a1, 0x101009c
    };
    private static final int SELECTED_HOVERED_FOCUSED_STATE_SET[] = {
        0x10100a1, 0x1010367, 0x101009c
    };
    private static final int SELECTED_HOVERED_STATE_SET[] = {
        0x10100a1, 0x1010367
    };
    private static final int SELECTED_PRESSED_STATE_SET[] = {
        0x10100a1, 0x10100a7
    };
    private static final int SELECTED_STATE_SET[] = {
        0x10100a1
    };
    public static final boolean USE_FRAMEWORK_RIPPLE = true;

    public static ColorStateList convertToRippleDrawableColor(ColorStateList colorstatelist)
    {
        if (USE_FRAMEWORK_RIPPLE)
        {
            int ai[] = SELECTED_STATE_SET;
            int i = getColorForState(colorstatelist, SELECTED_PRESSED_STATE_SET);
            int ai2[] = StateSet.NOTHING;
            int k = getColorForState(colorstatelist, PRESSED_STATE_SET);
            return new ColorStateList(new int[][] {
                ai, ai2
            }, new int[] {
                i, k
            });
        } else
        {
            int ai1[] = SELECTED_PRESSED_STATE_SET;
            int j = getColorForState(colorstatelist, SELECTED_PRESSED_STATE_SET);
            int ai3[] = SELECTED_HOVERED_FOCUSED_STATE_SET;
            int l = getColorForState(colorstatelist, SELECTED_HOVERED_FOCUSED_STATE_SET);
            int ai4[] = SELECTED_FOCUSED_STATE_SET;
            int i1 = getColorForState(colorstatelist, SELECTED_FOCUSED_STATE_SET);
            int ai5[] = SELECTED_HOVERED_STATE_SET;
            int j1 = getColorForState(colorstatelist, SELECTED_HOVERED_STATE_SET);
            int ai6[] = SELECTED_STATE_SET;
            int ai7[] = PRESSED_STATE_SET;
            int k1 = getColorForState(colorstatelist, PRESSED_STATE_SET);
            int ai8[] = HOVERED_FOCUSED_STATE_SET;
            int l1 = getColorForState(colorstatelist, HOVERED_FOCUSED_STATE_SET);
            int ai9[] = FOCUSED_STATE_SET;
            int i2 = getColorForState(colorstatelist, FOCUSED_STATE_SET);
            int ai10[] = HOVERED_STATE_SET;
            int j2 = getColorForState(colorstatelist, HOVERED_STATE_SET);
            return new ColorStateList(new int[][] {
                ai1, ai3, ai4, ai5, ai6, ai7, ai8, ai9, ai10, StateSet.NOTHING
            }, new int[] {
                j, l, i1, j1, 0, k1, l1, i2, j2, 0
            });
        }
    }

    private static int getColorForState(ColorStateList colorstatelist, int ai[])
    {
        int i;
        int j;
        if (colorstatelist != null)
        {
            i = colorstatelist.getColorForState(ai, colorstatelist.getDefaultColor());
        } else
        {
            i = 0;
        }
        j = i;
        if (USE_FRAMEWORK_RIPPLE)
        {
            j = ColorUtils.setAlphaComponent(i, Math.min(Color.alpha(i) * 2, 255));
        }
        return j;
    }

}
