// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20;

import android.os.Bundle;
import android.view.View;
import com.google.android.libraries.hats20.answer.AnswerBeacon;
import com.google.android.libraries.hats20.answer.AnswerBeaconTransmitter;
import com.google.android.libraries.hats20.cookie.HatsCookieManager;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.libraries.hats20:
//            PromptDialogDelegate

final class this._cls0
    implements android.view.PromptDialogDelegate._cls5
{

    private final PromptDialogDelegate this$0;

    public final void onClick(View view)
    {
        view = PromptDialogDelegate.this;
        Object obj = ((PromptDialogDelegate) (view)).answerBeacon;
        if ("o" == null)
        {
            throw new NullPointerException("Beacon type cannot be null.");
        }
        if ("o" == null)
        {
            ((AnswerBeacon) (obj)).parameterBundle.remove("t");
        } else
        {
            ((AnswerBeacon) (obj)).parameterBundle.putString("t", "o");
        }
        obj = new AnswerBeaconTransmitter(((PromptDialogDelegate) (view)).survey.nswerUrl_, HatsCookieManager.getInstance(((PromptDialogDelegate) (view)).context));
        view = ((PromptDialogDelegate) (view)).answerBeacon;
        ((AnswerBeaconTransmitter) (obj)).executor.execute(new com.google.android.libraries.hats20.answer..TransmitTask(((AnswerBeaconTransmitter) (obj)), view.exportAllParametersInQueryString(true)));
        dialogFragment.dismissAllowingStateLoss();
    }

    alogFragmentInterface()
    {
        this$0 = PromptDialogDelegate.this;
        super();
    }
}
