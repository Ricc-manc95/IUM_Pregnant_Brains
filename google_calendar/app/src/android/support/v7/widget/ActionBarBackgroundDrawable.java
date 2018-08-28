// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;

// Referenced classes of package android.support.v7.widget:
//            ActionBarContainer

final class ActionBarBackgroundDrawable extends Drawable
{

    private final ActionBarContainer mContainer;

    public ActionBarBackgroundDrawable(ActionBarContainer actionbarcontainer)
    {
        mContainer = actionbarcontainer;
    }

    public final void draw(Canvas canvas)
    {
        if (mContainer.mIsSplit)
        {
            if (mContainer.mSplitBackground != null)
            {
                mContainer.mSplitBackground.draw(canvas);
            }
        } else
        {
            if (mContainer.mBackground != null)
            {
                mContainer.mBackground.draw(canvas);
            }
            if (mContainer.mStackedBackground != null && mContainer.mIsStacked)
            {
                mContainer.mStackedBackground.draw(canvas);
                return;
            }
        }
    }

    public final int getOpacity()
    {
        return 0;
    }

    public final void getOutline(Outline outline)
    {
        if (mContainer.mIsSplit)
        {
            if (mContainer.mSplitBackground != null)
            {
                mContainer.mSplitBackground.getOutline(outline);
            }
        } else
        if (mContainer.mBackground != null)
        {
            mContainer.mBackground.getOutline(outline);
            return;
        }
    }

    public final void setAlpha(int i)
    {
    }

    public final void setColorFilter(ColorFilter colorfilter)
    {
    }
}
