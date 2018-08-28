// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip;

import android.view.View;
import com.google.common.base.Optional;

public abstract class TooltipModel
{

    public TooltipModel()
    {
    }

    public abstract android.view.View.OnClickListener actionListener();

    public abstract CharSequence actionText();

    public abstract Optional actionTextColor();

    public abstract Alignment alignment();

    public abstract Optional backgroundColor();

    public abstract CharSequence detailText();

    public abstract OnDismissListener dismissListener();

    public abstract float maxWidthPercentage();

    public abstract Placement placement();

    public abstract TapDismissalType tapDismissalType();

    public abstract View targetView();

    public abstract android.view.View.OnClickListener targetViewClickListener();

    public abstract Optional textColor();

    public abstract CharSequence titleText();

    public abstract android.view.View.OnClickListener userClickedListener();
}
