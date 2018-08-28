// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.featurehighlight;


// Referenced classes of package com.google.android.libraries.material.featurehighlight:
//            ViewFinder

public final class targetViewFinder
{

    public CharSequence bodyText;
    private int bodyTextAlignment;
    public int bodyTextAppearance;
    private int bodyTextSizeRes;
    private String callbackId;
    private int centerThresholdRes;
    public int confiningViewId;
    private CharSequence contentDescription;
    public CharSequence dismissActionText;
    private int dismissActionTextAlignment;
    public int dismissActionTextAppearance;
    public long durationInMillis;
    public CharSequence headerText;
    private int headerTextAlignment;
    public int headerTextAppearance;
    private int headerTextSizeRes;
    private int horizontalOffsetRes;
    public int innerColor;
    public int outerColor;
    public boolean swipeToDismissEnabled;
    private int targetDrawableColor;
    private int targetDrawableId;
    private int targetTextColor;
    public final ViewFinder targetViewFinder;
    public int targetViewTintColor;
    public boolean taskCompleteOnTap;
    private String taskTag;
    private int textVerticalGravityHint;
    private int verticalOffsetRes;

    public (ViewFinder viewfinder)
    {
        confiningViewId = 0x1020002;
        headerText = null;
        headerTextSizeRes = 0;
        headerTextAppearance = 0;
        headerTextAlignment = 0;
        bodyText = null;
        bodyTextSizeRes = 0;
        bodyTextAppearance = 0;
        bodyTextAlignment = 0;
        dismissActionText = null;
        dismissActionTextAppearance = 0;
        dismissActionTextAlignment = 0;
        outerColor = 0;
        innerColor = 0;
        targetTextColor = 0;
        targetDrawableId = 0;
        targetDrawableColor = 0;
        callbackId = null;
        taskTag = null;
        verticalOffsetRes = 0;
        horizontalOffsetRes = 0;
        centerThresholdRes = 0;
        taskCompleteOnTap = true;
        durationInMillis = -1L;
        swipeToDismissEnabled = true;
        textVerticalGravityHint = 0;
        contentDescription = null;
        if (viewfinder == null)
        {
            throw new NullPointerException();
        } else
        {
            targetViewFinder = (ViewFinder)viewfinder;
            return;
        }
    }
}
