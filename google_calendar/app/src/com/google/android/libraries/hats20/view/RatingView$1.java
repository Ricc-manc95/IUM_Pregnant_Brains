// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.view;

import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package com.google.android.libraries.hats20.view:
//            RatingView

final class val.rating
    implements android.view.istener
{

    private final RatingView this$0;
    private final int val$rating;
    private final ViewGroup val$ratingImageIconsContainer;

    public final void onClick(View view)
    {
        view = RatingView.this;
        RatingView.removeOnClickListenersAndDisableClickEvents(val$ratingImageIconsContainer);
        if (onRatingClickListener != null)
        {
            onRatingClickListener.onClickRating(val$rating);
        }
    }

    RatingClickListener()
    {
        this$0 = final_ratingview;
        val$ratingImageIconsContainer = viewgroup;
        val$rating = I.this;
        super();
    }
}
