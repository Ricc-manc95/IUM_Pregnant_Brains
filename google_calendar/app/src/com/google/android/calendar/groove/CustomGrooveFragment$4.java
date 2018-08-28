// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import com.google.android.calendar.common.view.fab.FloatingActionButton;
import com.google.android.calendar.utils.animation.QuantumInterpolators;

// Referenced classes of package com.google.android.calendar.groove:
//            CustomGrooveFragment

final class this._cls0
    implements TextWatcher
{

    private final CustomGrooveFragment this$0;

    public final void afterTextChanged(Editable editable)
    {
    }

    public final void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    public final void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        if (!TextUtils.isEmpty(editText.getText().toString()))
        {
            charsequence = fab;
            if (((FloatingActionButton) (charsequence)).isHidden)
            {
                AnimatorSet animatorset = new AnimatorSet();
                animatorset.playTogether(new Animator[] {
                    ObjectAnimator.ofFloat(charsequence, "rotation", new float[] {
                        -180F, 0.0F
                    }), ObjectAnimator.ofFloat(charsequence, "scaleX", new float[] {
                        0.0F, 1.0F
                    }), ObjectAnimator.ofFloat(charsequence, "scaleY", new float[] {
                        0.0F, 1.0F
                    })
                });
                animatorset.setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
                animatorset.setDuration(100L);
                animatorset.start();
                charsequence.isHidden = false;
            }
            return;
        } else
        {
            fab.hide(true);
            return;
        }
    }

    lators()
    {
        this$0 = CustomGrooveFragment.this;
        super();
    }
}
