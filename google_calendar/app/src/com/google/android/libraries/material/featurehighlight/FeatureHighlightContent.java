// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.featurehighlight;

import android.view.View;

public interface FeatureHighlightContent
{

    public abstract View asView();

    public abstract String getText();

    public abstract boolean isEmpty();

    public abstract void setBodyTextAlignment(int i);

    public abstract void setBodyTextAppearance(int i);

    public abstract void setBodyTextSize(float f);

    public abstract void setCallback(FeatureHighlightView.InteractionCallback interactioncallback);

    public abstract void setDismissActionTextAlignment(int i);

    public abstract void setDismissActionTextAppearance(int i);

    public abstract void setHeaderTextAlignment(int i);

    public abstract void setHeaderTextAppearance(int i);

    public abstract void setHeaderTextSize(float f);

    public abstract void setText(CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2);
}
