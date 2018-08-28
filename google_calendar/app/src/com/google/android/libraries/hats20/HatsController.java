// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20;


// Referenced classes of package com.google.android.libraries.hats20:
//            HatsDownloadRequest, HatsShowRequest

public interface HatsController
{

    public abstract void downloadSurvey(HatsDownloadRequest hatsdownloadrequest);

    public abstract void markSurveyFinished();

    public abstract void markSurveyRunning();

    public abstract boolean showSurveyIfAvailable(HatsShowRequest hatsshowrequest);
}
