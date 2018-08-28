// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.graphics.drawable.Drawable;
import android.view.View;

interface CardViewDelegate
{

    public abstract Drawable getCardBackground();

    public abstract View getCardView();

    public abstract boolean getPreventCornerOverlap();

    public abstract boolean getUseCompatPadding();

    public abstract void setCardBackground(Drawable drawable);

    public abstract void setShadowPadding(int i, int j, int k, int l);
}
