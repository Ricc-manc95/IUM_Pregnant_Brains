// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip;

import android.view.View;
import com.google.common.base.Optional;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip:
//            TooltipModel

final class AutoValue_TooltipModel extends TooltipModel
{

    private final android.view.View.OnClickListener actionListener;
    private final CharSequence actionText;
    private final Optional actionTextColor;
    private final TooltipModel.Alignment alignment;
    private final Optional backgroundColor;
    private final CharSequence detailText;
    private final TooltipModel.OnDismissListener dismissListener;
    private final float maxWidthPercentage;
    private final TooltipModel.Placement placement;
    private final TooltipModel.TapDismissalType tapDismissalType;
    private final View targetView;
    private final android.view.View.OnClickListener targetViewClickListener;
    private final Optional textColor;
    private final CharSequence titleText;
    private final android.view.View.OnClickListener userClickedListener;

    AutoValue_TooltipModel(View view, Optional optional, CharSequence charsequence, android.view.View.OnClickListener onclicklistener, CharSequence charsequence1, Optional optional1, CharSequence charsequence2, 
            Optional optional2, android.view.View.OnClickListener onclicklistener1, TooltipModel.OnDismissListener ondismisslistener, android.view.View.OnClickListener onclicklistener2, TooltipModel.TapDismissalType tapdismissaltype, TooltipModel.Placement placement1, TooltipModel.Alignment alignment1, 
            float f)
    {
        targetView = view;
        backgroundColor = optional;
        titleText = charsequence;
        targetViewClickListener = onclicklistener;
        detailText = charsequence1;
        textColor = optional1;
        actionText = charsequence2;
        actionTextColor = optional2;
        actionListener = onclicklistener1;
        dismissListener = ondismisslistener;
        userClickedListener = onclicklistener2;
        tapDismissalType = tapdismissaltype;
        placement = placement1;
        alignment = alignment1;
        maxWidthPercentage = f;
    }

    public final android.view.View.OnClickListener actionListener()
    {
        return actionListener;
    }

    public final CharSequence actionText()
    {
        return actionText;
    }

    public final Optional actionTextColor()
    {
        return actionTextColor;
    }

    public final TooltipModel.Alignment alignment()
    {
        return alignment;
    }

    public final Optional backgroundColor()
    {
        return backgroundColor;
    }

    public final CharSequence detailText()
    {
        return detailText;
    }

    public final TooltipModel.OnDismissListener dismissListener()
    {
        return dismissListener;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof TooltipModel)
            {
                if (!targetView.equals(((TooltipModel) (obj = (TooltipModel)obj)).targetView()) || !backgroundColor.equals(((TooltipModel) (obj)).backgroundColor()) || (titleText != null ? !titleText.equals(((TooltipModel) (obj)).titleText()) : ((TooltipModel) (obj)).titleText() != null) || (targetViewClickListener != null ? !targetViewClickListener.equals(((TooltipModel) (obj)).targetViewClickListener()) : ((TooltipModel) (obj)).targetViewClickListener() != null) || (detailText != null ? !detailText.equals(((TooltipModel) (obj)).detailText()) : ((TooltipModel) (obj)).detailText() != null) || !textColor.equals(((TooltipModel) (obj)).textColor()) || (actionText != null ? !actionText.equals(((TooltipModel) (obj)).actionText()) : ((TooltipModel) (obj)).actionText() != null) || !actionTextColor.equals(((TooltipModel) (obj)).actionTextColor()) || (actionListener != null ? !actionListener.equals(((TooltipModel) (obj)).actionListener()) : ((TooltipModel) (obj)).actionListener() != null) || (dismissListener != null ? !dismissListener.equals(((TooltipModel) (obj)).dismissListener()) : ((TooltipModel) (obj)).dismissListener() != null) || (userClickedListener != null ? !userClickedListener.equals(((TooltipModel) (obj)).userClickedListener()) : ((TooltipModel) (obj)).userClickedListener() != null) || (!tapDismissalType.equals(((TooltipModel) (obj)).tapDismissalType()) || !placement.equals(((TooltipModel) (obj)).placement()) || !alignment.equals(((TooltipModel) (obj)).alignment()) || Float.floatToIntBits(maxWidthPercentage) != Float.floatToIntBits(((TooltipModel) (obj)).maxWidthPercentage())))
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        int k1 = 0;
        int l1 = targetView.hashCode();
        int i2 = backgroundColor.hashCode();
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int j2;
        int k2;
        if (titleText == null)
        {
            i = 0;
        } else
        {
            i = titleText.hashCode();
        }
        if (targetViewClickListener == null)
        {
            j = 0;
        } else
        {
            j = targetViewClickListener.hashCode();
        }
        if (detailText == null)
        {
            k = 0;
        } else
        {
            k = detailText.hashCode();
        }
        j2 = textColor.hashCode();
        if (actionText == null)
        {
            l = 0;
        } else
        {
            l = actionText.hashCode();
        }
        k2 = actionTextColor.hashCode();
        if (actionListener == null)
        {
            i1 = 0;
        } else
        {
            i1 = actionListener.hashCode();
        }
        if (dismissListener == null)
        {
            j1 = 0;
        } else
        {
            j1 = dismissListener.hashCode();
        }
        if (userClickedListener != null)
        {
            k1 = userClickedListener.hashCode();
        }
        return (((((j1 ^ (i1 ^ ((l ^ ((k ^ (j ^ (i ^ ((l1 ^ 0xf4243) * 0xf4243 ^ i2) * 0xf4243) * 0xf4243) * 0xf4243) * 0xf4243 ^ j2) * 0xf4243) * 0xf4243 ^ k2) * 0xf4243) * 0xf4243) * 0xf4243 ^ k1) * 0xf4243 ^ tapDismissalType.hashCode()) * 0xf4243 ^ placement.hashCode()) * 0xf4243 ^ alignment.hashCode()) * 0xf4243 ^ Float.floatToIntBits(maxWidthPercentage);
    }

    public final float maxWidthPercentage()
    {
        return maxWidthPercentage;
    }

    public final TooltipModel.Placement placement()
    {
        return placement;
    }

    public final TooltipModel.TapDismissalType tapDismissalType()
    {
        return tapDismissalType;
    }

    public final View targetView()
    {
        return targetView;
    }

    public final android.view.View.OnClickListener targetViewClickListener()
    {
        return targetViewClickListener;
    }

    public final Optional textColor()
    {
        return textColor;
    }

    public final CharSequence titleText()
    {
        return titleText;
    }

    public final String toString()
    {
        String s = String.valueOf(targetView);
        String s1 = String.valueOf(backgroundColor);
        String s2 = String.valueOf(titleText);
        String s3 = String.valueOf(targetViewClickListener);
        String s4 = String.valueOf(detailText);
        String s5 = String.valueOf(textColor);
        String s6 = String.valueOf(actionText);
        String s7 = String.valueOf(actionTextColor);
        String s8 = String.valueOf(actionListener);
        String s9 = String.valueOf(dismissListener);
        String s10 = String.valueOf(userClickedListener);
        String s11 = String.valueOf(tapDismissalType);
        String s12 = String.valueOf(placement);
        String s13 = String.valueOf(alignment);
        float f = maxWidthPercentage;
        return (new StringBuilder(String.valueOf(s).length() + 273 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length() + String.valueOf(s5).length() + String.valueOf(s6).length() + String.valueOf(s7).length() + String.valueOf(s8).length() + String.valueOf(s9).length() + String.valueOf(s10).length() + String.valueOf(s11).length() + String.valueOf(s12).length() + String.valueOf(s13).length())).append("TooltipModel{targetView=").append(s).append(", backgroundColor=").append(s1).append(", titleText=").append(s2).append(", targetViewClickListener=").append(s3).append(", detailText=").append(s4).append(", textColor=").append(s5).append(", actionText=").append(s6).append(", actionTextColor=").append(s7).append(", actionListener=").append(s8).append(", dismissListener=").append(s9).append(", userClickedListener=").append(s10).append(", tapDismissalType=").append(s11).append(", placement=").append(s12).append(", alignment=").append(s13).append(", maxWidthPercentage=").append(f).append("}").toString();
    }

    public final android.view.View.OnClickListener userClickedListener()
    {
        return userClickedListener;
    }
}
