// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.featurehighlight;


// Referenced classes of package com.google.android.libraries.material.featurehighlight:
//            ViewFinder

public final class FeatureHighlight
{

    public final CharSequence bodyText;
    public final int bodyTextAlignment;
    public final int bodyTextAppearance;
    public final int bodyTextSizeRes;
    public final String callbackId;
    public final int centerThresholdRes;
    public final int confiningViewId;
    public CharSequence contentDescription;
    public CharSequence dismissActionText;
    public final int dismissActionTextAlignment;
    public final int dismissActionTextAppearance;
    public final long durationInMillis;
    public final CharSequence headerText;
    public final int headerTextAlignment;
    public final int headerTextAppearance;
    public final int headerTextSizeRes;
    public final int horizontalOffsetRes;
    public final int innerColor;
    public final int outerColor;
    public final boolean pinToClosestVerticalEdge;
    public final boolean swipeToDismissEnabled;
    public final int targetDrawableColor;
    public final int targetDrawableId;
    public final int targetTextColor;
    public final int targetViewTintColor;
    public final boolean taskCompleteOnTap;
    public final String taskTag;
    public final int textVerticalGravityHint;
    public final int verticalOffsetRes;
    public final ViewFinder viewFinder;

    public FeatureHighlight(ViewFinder viewfinder, int i, int j, CharSequence charsequence, int k, int l, int i1, 
            CharSequence charsequence1, int j1, int k1, int l1, CharSequence charsequence2, int i2, int j2, 
            int k2, int l2, int i3, int j3, int k3, String s, String s1, 
            int l3, int i4, int j4, boolean flag, long l4, boolean flag1, 
            boolean flag2, int k4, CharSequence charsequence3)
    {
        dismissActionText = null;
        contentDescription = null;
        viewFinder = viewfinder;
        targetViewTintColor = i;
        confiningViewId = j;
        headerText = charsequence;
        headerTextSizeRes = k;
        headerTextAppearance = l;
        headerTextAlignment = i1;
        bodyText = charsequence1;
        bodyTextSizeRes = j1;
        bodyTextAppearance = k1;
        bodyTextAlignment = l1;
        dismissActionText = charsequence2;
        dismissActionTextAppearance = i2;
        dismissActionTextAlignment = j2;
        outerColor = k2;
        innerColor = l2;
        targetTextColor = i3;
        targetDrawableId = j3;
        targetDrawableColor = k3;
        callbackId = s;
        taskTag = s1;
        verticalOffsetRes = l3;
        horizontalOffsetRes = i4;
        centerThresholdRes = j4;
        taskCompleteOnTap = flag;
        durationInMillis = l4;
        pinToClosestVerticalEdge = flag1;
        swipeToDismissEnabled = flag2;
        textVerticalGravityHint = k4;
        contentDescription = charsequence3;
    }
}
