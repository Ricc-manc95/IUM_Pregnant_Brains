// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            CardViewImpl, CardViewDelegate, RoundRectDrawable, RoundRectDrawableWithShadow

final class CardViewApi21Impl
    implements CardViewImpl
{

    CardViewApi21Impl()
    {
    }

    public final float getMaxElevation(CardViewDelegate cardviewdelegate)
    {
        return ((RoundRectDrawable)cardviewdelegate.getCardBackground()).mPadding;
    }

    public final float getMinHeight(CardViewDelegate cardviewdelegate)
    {
        return ((RoundRectDrawable)cardviewdelegate.getCardBackground()).mRadius * 2.0F;
    }

    public final float getMinWidth(CardViewDelegate cardviewdelegate)
    {
        return ((RoundRectDrawable)cardviewdelegate.getCardBackground()).mRadius * 2.0F;
    }

    public final void initialize(CardViewDelegate cardviewdelegate, Context context, ColorStateList colorstatelist, float f, float f1, float f2)
    {
        cardviewdelegate.setCardBackground(new RoundRectDrawable(colorstatelist, f));
        context = cardviewdelegate.getCardView();
        context.setClipToOutline(true);
        context.setElevation(f1);
        context = (RoundRectDrawable)cardviewdelegate.getCardBackground();
        boolean flag = cardviewdelegate.getUseCompatPadding();
        boolean flag1 = cardviewdelegate.getPreventCornerOverlap();
        if (f2 != ((RoundRectDrawable) (context)).mPadding || ((RoundRectDrawable) (context)).mInsetForPadding != flag || ((RoundRectDrawable) (context)).mInsetForRadius != flag1)
        {
            context.mPadding = f2;
            context.mInsetForPadding = flag;
            context.mInsetForRadius = flag1;
            context.updateBounds(null);
            context.invalidateSelf();
        }
        if (!cardviewdelegate.getUseCompatPadding())
        {
            cardviewdelegate.setShadowPadding(0, 0, 0, 0);
            return;
        } else
        {
            f = ((RoundRectDrawable)cardviewdelegate.getCardBackground()).mPadding;
            f1 = ((RoundRectDrawable)cardviewdelegate.getCardBackground()).mRadius;
            int i = (int)Math.ceil(RoundRectDrawableWithShadow.calculateHorizontalPadding(f, f1, cardviewdelegate.getPreventCornerOverlap()));
            int j = (int)Math.ceil(RoundRectDrawableWithShadow.calculateVerticalPadding(f, f1, cardviewdelegate.getPreventCornerOverlap()));
            cardviewdelegate.setShadowPadding(i, j, i, j);
            return;
        }
    }

    public final void setBackgroundColor(CardViewDelegate cardviewdelegate, ColorStateList colorstatelist)
    {
        cardviewdelegate = (RoundRectDrawable)cardviewdelegate.getCardBackground();
        cardviewdelegate.setBackground(colorstatelist);
        cardviewdelegate.invalidateSelf();
    }

    public final void setElevation(CardViewDelegate cardviewdelegate, float f)
    {
        cardviewdelegate.getCardView().setElevation(f);
    }

    public final void setMaxElevation(CardViewDelegate cardviewdelegate, float f)
    {
        RoundRectDrawable roundrectdrawable = (RoundRectDrawable)cardviewdelegate.getCardBackground();
        boolean flag = cardviewdelegate.getUseCompatPadding();
        boolean flag1 = cardviewdelegate.getPreventCornerOverlap();
        if (f != roundrectdrawable.mPadding || roundrectdrawable.mInsetForPadding != flag || roundrectdrawable.mInsetForRadius != flag1)
        {
            roundrectdrawable.mPadding = f;
            roundrectdrawable.mInsetForPadding = flag;
            roundrectdrawable.mInsetForRadius = flag1;
            roundrectdrawable.updateBounds(null);
            roundrectdrawable.invalidateSelf();
        }
        if (!cardviewdelegate.getUseCompatPadding())
        {
            cardviewdelegate.setShadowPadding(0, 0, 0, 0);
            return;
        } else
        {
            f = ((RoundRectDrawable)cardviewdelegate.getCardBackground()).mPadding;
            float f1 = ((RoundRectDrawable)cardviewdelegate.getCardBackground()).mRadius;
            int i = (int)Math.ceil(RoundRectDrawableWithShadow.calculateHorizontalPadding(f, f1, cardviewdelegate.getPreventCornerOverlap()));
            int j = (int)Math.ceil(RoundRectDrawableWithShadow.calculateVerticalPadding(f, f1, cardviewdelegate.getPreventCornerOverlap()));
            cardviewdelegate.setShadowPadding(i, j, i, j);
            return;
        }
    }

    public final void setRadius(CardViewDelegate cardviewdelegate, float f)
    {
        cardviewdelegate = (RoundRectDrawable)cardviewdelegate.getCardBackground();
        if (f != ((RoundRectDrawable) (cardviewdelegate)).mRadius)
        {
            cardviewdelegate.mRadius = f;
            cardviewdelegate.updateBounds(null);
            cardviewdelegate.invalidateSelf();
        }
    }
}
