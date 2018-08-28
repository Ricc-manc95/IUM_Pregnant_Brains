// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.LinearLayout;

// Referenced classes of package com.google.android.libraries.hats20:
//            SurveyPromptActivity

final class this._cls0 extends AnimatorListenerAdapter
{

    private final SurveyPromptActivity this$0;

    public final void onAnimationEnd(Animator animator)
    {
        surveyContainer.setVisibility(8);
    }

    ()
    {
        this$0 = SurveyPromptActivity.this;
        super();
    }
}
