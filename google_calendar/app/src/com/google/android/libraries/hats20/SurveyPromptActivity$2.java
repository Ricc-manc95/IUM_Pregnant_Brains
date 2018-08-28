// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20;

import android.view.View;

// Referenced classes of package com.google.android.libraries.hats20:
//            SurveyPromptActivity

final class this._cls0
    implements android.view.SurveyPromptActivity._cls2
{

    private final SurveyPromptActivity this$0;

    public final void onClick(View view)
    {
        setBeaconTypeAndTransmit("o");
        closeKeyboardIfOpenTextQuestion();
        finish();
    }

    ()
    {
        this$0 = SurveyPromptActivity.this;
        super();
    }
}
