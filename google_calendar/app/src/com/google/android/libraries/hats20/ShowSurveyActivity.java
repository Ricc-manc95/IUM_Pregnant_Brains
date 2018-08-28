// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.google.android.libraries.hats20.inject.HatsModule;

// Referenced classes of package com.google.android.libraries.hats20:
//            HatsController, HatsShowRequest

public class ShowSurveyActivity extends AppCompatActivity
{

    public ShowSurveyActivity()
    {
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        HatsModule.get().getHatsController().markSurveyFinished();
        finish();
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setFinishOnTouchOutside(false);
        bundle = getIntent();
        if (bundle != null)
        {
            String s = bundle.getStringExtra("SiteId");
            int i = bundle.getIntExtra("ResponseCode", 1380);
            bundle = (new HatsShowRequest.Builder(this)).forSiteId(s);
            bundle.requestCode = Integer.valueOf(i);
            bundle.showSurveyWithoutPrompt = true;
            if (((HatsShowRequest.Builder) (bundle)).siteId == null)
            {
                bundle.siteId = "-1";
            }
            bundle = new HatsShowRequest(bundle);
            if (!HatsModule.get().getHatsController().showSurveyIfAvailable(bundle))
            {
                finish();
            }
        }
    }
}
