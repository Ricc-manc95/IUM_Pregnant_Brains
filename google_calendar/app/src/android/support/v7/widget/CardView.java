// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.FrameLayout;

// Referenced classes of package android.support.v7.widget:
//            CardViewApi21Impl, CardViewImpl, CardViewDelegate

public class CardView extends FrameLayout
{

    private static final int COLOR_BACKGROUND_ATTR[] = {
        0x1010031
    };
    public static final CardViewImpl IMPL = new CardViewApi21Impl();
    public final CardViewDelegate mCardViewDelegate;
    public boolean mCompatPadding;
    public final Rect mContentPadding;
    public boolean mPreventCornerOverlap;
    public final Rect mShadowBounds;
    public int mUserSetMinHeight;
    public int mUserSetMinWidth;

    public CardView(Context context)
    {
        this(context, null);
    }

    public CardView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f010004);
    }

    public CardView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mContentPadding = new Rect();
        mShadowBounds = new Rect();
        mCardViewDelegate = new _cls1();
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, android.support.v7.cardview.R.styleable.CardView, i, 0x7f1400ee);
        float f;
        float f1;
        float f2;
        float f3;
        if (typedarray.hasValue(android.support.v7.cardview.R.styleable.CardView_cardBackgroundColor))
        {
            attributeset = typedarray.getColorStateList(android.support.v7.cardview.R.styleable.CardView_cardBackgroundColor);
        } else
        {
            attributeset = getContext().obtainStyledAttributes(COLOR_BACKGROUND_ATTR);
            i = attributeset.getColor(0, 0);
            attributeset.recycle();
            attributeset = new float[3];
            Color.colorToHSV(i, attributeset);
            if (attributeset[2] > 0.5F)
            {
                i = getResources().getColor(0x7f0d004f);
            } else
            {
                i = getResources().getColor(0x7f0d004e);
            }
            attributeset = ColorStateList.valueOf(i);
        }
        f3 = typedarray.getDimension(android.support.v7.cardview.R.styleable.CardView_cardCornerRadius, 0.0F);
        f1 = typedarray.getDimension(android.support.v7.cardview.R.styleable.CardView_cardElevation, 0.0F);
        f2 = typedarray.getDimension(android.support.v7.cardview.R.styleable.CardView_cardMaxElevation, 0.0F);
        mCompatPadding = typedarray.getBoolean(android.support.v7.cardview.R.styleable.CardView_cardUseCompatPadding, false);
        mPreventCornerOverlap = typedarray.getBoolean(android.support.v7.cardview.R.styleable.CardView_cardPreventCornerOverlap, true);
        i = typedarray.getDimensionPixelSize(android.support.v7.cardview.R.styleable.CardView_contentPadding, 0);
        mContentPadding.left = typedarray.getDimensionPixelSize(android.support.v7.cardview.R.styleable.CardView_contentPaddingLeft, i);
        mContentPadding.top = typedarray.getDimensionPixelSize(android.support.v7.cardview.R.styleable.CardView_contentPaddingTop, i);
        mContentPadding.right = typedarray.getDimensionPixelSize(android.support.v7.cardview.R.styleable.CardView_contentPaddingRight, i);
        mContentPadding.bottom = typedarray.getDimensionPixelSize(android.support.v7.cardview.R.styleable.CardView_contentPaddingBottom, i);
        f = f2;
        if (f1 > f2)
        {
            f = f1;
        }
        mUserSetMinWidth = typedarray.getDimensionPixelSize(android.support.v7.cardview.R.styleable.CardView_android_minWidth, 0);
        mUserSetMinHeight = typedarray.getDimensionPixelSize(android.support.v7.cardview.R.styleable.CardView_android_minHeight, 0);
        typedarray.recycle();
        IMPL.initialize(mCardViewDelegate, context, attributeset, f3, f1, f);
    }

    protected void onMeasure(int i, int j)
    {
        if (IMPL instanceof CardViewApi21Impl) goto _L2; else goto _L1
_L1:
        int k = android.view.View.MeasureSpec.getMode(i);
        k;
        JVM INSTR lookupswitch 2: default 40
    //                   -2147483648: 79
    //                   1073741824: 79;
           goto _L3 _L4 _L4
_L3:
        k = android.view.View.MeasureSpec.getMode(j);
        k;
        JVM INSTR lookupswitch 2: default 72
    //                   -2147483648: 111
    //                   1073741824: 111;
           goto _L5 _L6 _L6
_L5:
        super.onMeasure(i, j);
        return;
_L4:
        i = android.view.View.MeasureSpec.makeMeasureSpec(Math.max((int)Math.ceil(IMPL.getMinWidth(mCardViewDelegate)), android.view.View.MeasureSpec.getSize(i)), k);
        continue; /* Loop/switch isn't completed */
_L6:
        j = android.view.View.MeasureSpec.makeMeasureSpec(Math.max((int)Math.ceil(IMPL.getMinHeight(mCardViewDelegate)), android.view.View.MeasureSpec.getSize(j)), k);
        if (true) goto _L5; else goto _L2
_L2:
        super.onMeasure(i, j);
        return;
        if (true) goto _L3; else goto _L7
_L7:
    }

    public void setCardBackgroundColor(int i)
    {
        IMPL.setBackgroundColor(mCardViewDelegate, ColorStateList.valueOf(i));
    }

    public void setCardElevation(float f)
    {
        IMPL.setElevation(mCardViewDelegate, f);
    }

    public void setMaxCardElevation(float f)
    {
        IMPL.setMaxElevation(mCardViewDelegate, f);
    }

    public void setMinimumHeight(int i)
    {
        mUserSetMinHeight = i;
        super.setMinimumHeight(i);
    }

    public void setMinimumWidth(int i)
    {
        mUserSetMinWidth = i;
        super.setMinimumWidth(i);
    }

    public void setPadding(int i, int j, int k, int l)
    {
    }

    public void setPaddingRelative(int i, int j, int k, int l)
    {
    }

    public void setRadius(float f)
    {
        IMPL.setRadius(mCardViewDelegate, f);
    }



    private class _cls1
        implements CardViewDelegate
    {

        private Drawable mCardBackground;
        private final CardView this$0;

        public final Drawable getCardBackground()
        {
            return mCardBackground;
        }

        public final View getCardView()
        {
            return CardView.this;
        }

        public final boolean getPreventCornerOverlap()
        {
            return mPreventCornerOverlap;
        }

        public final boolean getUseCompatPadding()
        {
            return mCompatPadding;
        }

        public final void setCardBackground(Drawable drawable)
        {
            mCardBackground = drawable;
            setBackgroundDrawable(drawable);
        }

        public final void setShadowPadding(int i, int j, int k, int l)
        {
            mShadowBounds.set(i, j, k, l);
            setPadding(mContentPadding.left + i, mContentPadding.top + j, mContentPadding.right + k, mContentPadding.bottom + l);
        }

        _cls1()
        {
            this$0 = CardView.this;
            super();
        }
    }

}
