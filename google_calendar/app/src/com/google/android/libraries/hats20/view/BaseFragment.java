// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.google.android.libraries.hats20.model.ProtoParcels;
import com.google.devrel.hats.proto.Question;
import com.google.devrel.hats.proto.QuestionResponse;
import com.google.protobuf.AbstractMessageLite;

public abstract class BaseFragment extends Fragment
{

    public int logoResId;
    public Question question;
    public int questionIndex;

    public BaseFragment()
    {
    }

    static Bundle createArguments(Question question1, int i, int j)
    {
        Bundle bundle = new Bundle();
        bundle.putByteArray("Question", question1.toByteArray());
        bundle.putInt("DispalyLogoResId", i);
        bundle.putInt("QuestionIndex", j);
        return bundle;
    }

    public void animateFadeIn()
    {
    }

    public abstract QuestionResponse computeQuestionResponse();

    public abstract String getCurrentQuestionText();

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        bundle = getArguments();
        byte abyte0[] = bundle.getByteArray("Question");
        question = (Question)ProtoParcels.getMessage(Question.DEFAULT_INSTANCE, abyte0);
        logoResId = bundle.getInt("DispalyLogoResId", 0);
        questionIndex = bundle.getInt("QuestionIndex");
    }

    public abstract void onPageScrolledIntoView();

    public abstract void updateQuestionText(String s);
}
