// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20;

import android.view.View;

// Referenced classes of package com.google.android.libraries.hats20:
//            PromptDialogDelegate, SurveyPromptActivity

final class val.hatsDisplayLogo
    implements android.view.PromptDialogDelegate._cls3
{

    private final PromptDialogDelegate this$0;
    private final int val$hatsDisplayLogo;
    private final int val$requestCode;
    private final String val$siteId;

    public final void onClick(View view)
    {
        SurveyPromptActivity.startSurveyActivity(dialogFragment.getActivity(), val$siteId, survey, surveyPayload, answerBeacon, Integer.valueOf(val$requestCode), isBottomSheet, isRatingBanner, val$hatsDisplayLogo);
        isStartingSurvey = true;
        dialogFragment.dismissAllowingStateLoss();
    }

    alogFragmentInterface()
    {
        this$0 = final_promptdialogdelegate;
        val$siteId = s;
        val$requestCode = i;
        val$hatsDisplayLogo = I.this;
        super();
    }
}
