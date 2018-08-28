// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.google.devrel.hats.proto.Question;
import com.google.devrel.hats.proto.QuestionType;
import java.util.List;

// Referenced classes of package com.google.android.libraries.hats20.view:
//            MultipleSelectFragment, MultipleChoiceFragment, OpenTextFragment, RatingFragment

public final class SurveyViewPagerAdapter extends FragmentPagerAdapter
{

    private int displayLogo;
    private final List questions;

    public SurveyViewPagerAdapter(FragmentManager fragmentmanager, List list, int i)
    {
        super(fragmentmanager);
        displayLogo = 0;
        if (list == null)
        {
            throw new NullPointerException("Questions were missing!");
        } else
        {
            questions = list;
            displayLogo = i;
            return;
        }
    }

    public final int getCount()
    {
        return questions.size();
    }

    public final Fragment getItem(int i)
    {
        Object obj;
        Question question;
        question = (Question)questions.get(i);
        QuestionType questiontype = QuestionType.forNumber(question.questionType_);
        obj = questiontype;
        if (questiontype == null)
        {
            obj = QuestionType.UNRECOGNIZED;
        }
        ((QuestionType) (obj)).ordinal();
        JVM INSTR tableswitch 1 4: default 68
    //                   1 146
    //                   2 108
    //                   3 175
    //                   4 204;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        QuestionType questiontype1 = QuestionType.forNumber(question.questionType_);
        obj = questiontype1;
        if (questiontype1 == null)
        {
            obj = QuestionType.UNRECOGNIZED;
        }
        throw new AssertionError(String.format("Attempted to build fragment for unsupported Question type %s.  Note this should never happen as it's invalid to create a Question type that does not have a matching fragment.", new Object[] {
            obj
        }));
_L3:
        int j = displayLogo;
        obj = new MultipleSelectFragment();
        ((Fragment) (obj)).setArguments(MultipleSelectFragment.createArguments(question, j, i));
_L7:
        ((Fragment) (obj)).getArguments().putInt("QuestionIndex", i);
        return ((Fragment) (obj));
_L2:
        int k = displayLogo;
        obj = new MultipleChoiceFragment();
        ((Fragment) (obj)).setArguments(MultipleChoiceFragment.createArguments(question, k, i));
        continue; /* Loop/switch isn't completed */
_L4:
        int l = displayLogo;
        obj = new OpenTextFragment();
        ((Fragment) (obj)).setArguments(OpenTextFragment.createArguments(question, l, i));
        continue; /* Loop/switch isn't completed */
_L5:
        int i1 = displayLogo;
        obj = new RatingFragment();
        ((Fragment) (obj)).setArguments(RatingFragment.createArguments(question, i1, i));
        if (true) goto _L7; else goto _L6
_L6:
    }
}
