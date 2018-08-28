// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.util;

import android.util.Log;
import java.util.List;
import java.util.regex.Pattern;

public final class AnswerPiping
{

    public static final Pattern PATTERN = Pattern.compile("Q(\\d+)_ANSWER");

    public AnswerPiping()
    {
    }

    public static String getPipedResponse(int i, List list)
    {
        if (i < 0 || i >= list.size())
        {
            Log.e("AnswerPiping", (new StringBuilder(53)).append("Failed to find a piped answer for question").append(i + 1).toString());
            return null;
        }
        list = (com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse)list.get(i);
        if ((((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (list)).bitField0_ & 0x10) == 16)
        {
            return ((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (list)).candidateForPipedResponse_;
        } else
        {
            return null;
        }
    }

}
