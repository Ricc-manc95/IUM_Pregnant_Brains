// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.shadow;

import android.graphics.drawable.Drawable;

public interface ShadowViewDelegate
{

    public abstract float getRadius();

    public abstract boolean isCompatPaddingEnabled();

    public abstract void setBackgroundDrawable(Drawable drawable);

    public abstract void setShadowPadding(int i, int j, int k, int l);
}
