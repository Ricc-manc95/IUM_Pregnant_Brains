// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.view;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.view.View;

// Referenced classes of package com.google.android.libraries.hats20.view:
//            ScrollableAnswerFragment, ScrollViewWithSizeCallback

final class this._cls0
    implements android.view.ragment.ScrollShadowHandler, tener
{

    private final ScrollableAnswerFragment this$0;

    private final void updateShadowVisibility(int i)
    {
        boolean flag2 = true;
        if (!mUserVisibleHint)
        {
            return;
        }
        boolean flag;
        boolean flag1;
        if (scrollView.getScrollY() == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (scrollViewContents.getBottom() == scrollView.getScrollY() + i)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (scrollViewContents.getBottom() > i)
        {
            i = ((flag2) ? 1 : 0);
        } else
        {
            i = 0;
        }
        if (i == 0 || flag)
        {
            surveyQuestionHeader.setElevation(0.0F);
        } else
        {
            surveyQuestionHeader.setElevation(requireContext().getResources().getDimensionPixelSize(0x7f0e0226));
        }
        if (i == 0 || flag1)
        {
            surveyControlsContainer.setElevation(0.0F);
            return;
        } else
        {
            surveyControlsContainer.setElevation(requireContext().getResources().getDimensionPixelSize(0x7f0e0235));
            return;
        }
    }

    public final void onHeightChanged(int i)
    {
        if (i != 0)
        {
            updateShadowVisibility(i);
        }
    }

    public final void onScrollChanged()
    {
        updateShadowVisibility(scrollView.getHeight());
    }

    tener()
    {
        this$0 = ScrollableAnswerFragment.this;
        super();
    }
}
