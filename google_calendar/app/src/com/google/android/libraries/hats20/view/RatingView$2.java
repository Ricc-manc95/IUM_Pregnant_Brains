// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.view;

import com.google.android.libraries.hats20.ui.StarRatingBar;
import com.google.devrel.hats.proto.QuestionRating;

// Referenced classes of package com.google.android.libraries.hats20.view:
//            RatingView

final class val.question
    implements com.google.android.libraries.hats20.ui..OnRatingChangeListener
{

    private final RatingView this$0;
    private final QuestionRating val$question;
    private final StarRatingBar val$starRatingBar;

    public final void onRatingChanged(int i)
    {
        setDescriptionForTalkBack(val$starRatingBar, i, val$question.numStars_);
        if (onRatingClickListener != null)
        {
            onRatingClickListener.onClickRating(i);
        }
    }

    RatingClickListener()
    {
        this$0 = final_ratingview;
        val$starRatingBar = starratingbar;
        val$question = QuestionRating.this;
        super();
    }
}
