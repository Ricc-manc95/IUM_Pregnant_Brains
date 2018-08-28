// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import com.google.android.libraries.hats20.model.QuestionMetrics;
import com.google.devrel.hats.proto.Question;

// Referenced classes of package com.google.android.libraries.hats20.view:
//            RatingFragment, NextPageOrSubmitActionable, OnQuestionProgressableChangeListener

final class this._cls0
    implements ngClickListener
{

    private final RatingFragment this$0;

    public final void onClickRating(int i)
    {
        Object obj1 = null;
        Object obj = null;
        selectedResponse = Integer.toString(i);
        selectedIndex = i;
        questionMetrics.markAsAnswered();
        RatingFragment ratingfragment = RatingFragment.this;
        if (question.isSmiley_)
        {
            if (((Fragment) (ratingfragment)).mHost != null)
            {
                obj = (FragmentActivity)((Fragment) (ratingfragment)).mHost.mActivity;
            }
            ((NextPageOrSubmitActionable)obj).nextPageOrSubmit();
            return;
        }
        boolean flag;
        if (((Fragment) (ratingfragment)).mHost == null)
        {
            obj = obj1;
        } else
        {
            obj = (FragmentActivity)((Fragment) (ratingfragment)).mHost.mActivity;
        }
        obj = (OnQuestionProgressableChangeListener)obj;
        if (ratingfragment.selectedResponse != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        ((OnQuestionProgressableChangeListener) (obj)).onQuestionProgressableChanged(flag, ratingfragment);
    }

    sableChangeListener()
    {
        this$0 = RatingFragment.this;
        super();
    }
}
