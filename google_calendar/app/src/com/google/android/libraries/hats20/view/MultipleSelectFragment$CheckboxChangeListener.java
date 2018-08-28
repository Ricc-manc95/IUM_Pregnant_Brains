// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

// Referenced classes of package com.google.android.libraries.hats20.view:
//            MultipleSelectFragment, OnQuestionProgressableChangeListener

final class index
    implements android.widget.nt.CheckboxChangeListener
{

    private final int index;
    private final MultipleSelectFragment this$0;

    public final void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
    {
        if ("NoneOfTheAbove".equals(compoundbutton.getTag()))
        {
            isNoneOfTheAboveChecked = flag;
            if (flag)
            {
                if (answersContainer.getChildCount() != responses.length + 1)
                {
                    Log.e("HatsLibMultiSelectFrag", "Number of children (checkboxes) contained in the answers container was not equal to the number of possible responses including \"None of the Above\". Note this is not expected to happen in prod.");
                }
                for (int i = 0; i < answersContainer.getChildCount(); i++)
                {
                    compoundbutton = (CheckBox)answersContainer.getChildAt(i).findViewById(0x7f100214);
                    if (!"NoneOfTheAbove".equals(compoundbutton.getTag()))
                    {
                        compoundbutton.setChecked(false);
                    }
                }

            }
        } else
        {
            responses[index] = flag;
            if (flag)
            {
                ((CheckBox)answersContainer.findViewWithTag("NoneOfTheAbove")).setChecked(false);
            }
        }
        compoundbutton = MultipleSelectFragment.this;
        if (((Fragment) (compoundbutton)).mHost == null)
        {
            compoundbutton = null;
        } else
        {
            compoundbutton = (FragmentActivity)((Fragment) (compoundbutton)).mHost.mActivity;
        }
        compoundbutton = (OnQuestionProgressableChangeListener)compoundbutton;
        if (compoundbutton != null)
        {
            compoundbutton.onQuestionProgressableChanged(isResponseSatisfactory(), MultipleSelectFragment.this);
        }
    }

    (int i)
    {
        this$0 = MultipleSelectFragment.this;
        super();
        index = i;
    }
}
