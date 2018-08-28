// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20;

import com.google.android.libraries.hats20.inject.HatsModule;
import com.google.android.libraries.hats20.support.espresso.IdleResourceManager;

// Referenced classes of package com.google.android.libraries.hats20:
//            SurveyPromptActivity

final class this._cls0
    implements Runnable
{

    private final SurveyPromptActivity this$0;

    public final void run()
    {
        boolean flag1 = true;
        isSubmitting = true;
        IdleResourceManager idleresourcemanager = HatsModule.get().getIdleResourceManager();
        boolean flag;
        if (!idleresourcemanager.isMultipleChoiceSelectionAnimating && !idleresourcemanager.isThankYouAnimating)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        idleresourcemanager.isThankYouAnimating = false;
        if (!flag)
        {
            if (!idleresourcemanager.isMultipleChoiceSelectionAnimating && !idleresourcemanager.isThankYouAnimating)
            {
                flag = flag1;
            } else
            {
                flag = false;
            }
            if (!flag);
        }
        finish();
    }

    sourceManager()
    {
        this$0 = SurveyPromptActivity.this;
        super();
    }
}
