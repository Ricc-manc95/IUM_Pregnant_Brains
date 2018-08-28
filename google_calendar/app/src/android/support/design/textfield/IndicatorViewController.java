// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.textfield;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.support.design.animation.AnimationUtils;
import android.support.design.animation.AnimatorSetCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.Space;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.design.textfield:
//            TextInputLayout

final class IndicatorViewController
{

    public Animator captionAnimator;
    private FrameLayout captionArea;
    public int captionDisplayed;
    public int captionToShow;
    private final float captionTranslationYPx;
    private int captionViewsAdded;
    public final Context context;
    public boolean errorEnabled;
    public CharSequence errorText;
    public int errorTextAppearance;
    public TextView errorView;
    public CharSequence helperText;
    public boolean helperTextEnabled;
    public int helperTextTextAppearance;
    public TextView helperTextView;
    private LinearLayout indicatorArea;
    private int indicatorsAdded;
    public final TextInputLayout textInputView;

    public IndicatorViewController(TextInputLayout textinputlayout)
    {
        context = textinputlayout.getContext();
        textInputView = textinputlayout;
        captionTranslationYPx = context.getResources().getDimensionPixelSize(0x7f0e0110);
    }

    private final void createCaptionAnimators(List list, boolean flag, TextView textview, int i, int j, int k)
    {
        if (textview != null && flag && (i == k || i == j))
        {
            float f;
            ObjectAnimator objectanimator;
            if (k == i)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (j != 0)
            {
                f = 1.0F;
            } else
            {
                f = 0.0F;
            }
            objectanimator = ObjectAnimator.ofFloat(textview, View.ALPHA, new float[] {
                f
            });
            objectanimator.setDuration(167L);
            objectanimator.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
            list.add(objectanimator);
            if (k == i)
            {
                textview = ObjectAnimator.ofFloat(textview, View.TRANSLATION_Y, new float[] {
                    -captionTranslationYPx, 0.0F
                });
                textview.setDuration(217L);
                textview.setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
                list.add(textview);
                return;
            }
        }
    }

    private final TextView getCaptionViewFromDisplayState(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 1: // '\001'
            return errorView;

        case 2: // '\002'
            return helperTextView;
        }
    }

    final void addIndicator(TextView textview, int i)
    {
        boolean flag1 = true;
        if (indicatorArea == null && captionArea == null)
        {
            indicatorArea = new LinearLayout(context);
            indicatorArea.setOrientation(0);
            textInputView.addView(indicatorArea, -1, -2);
            captionArea = new FrameLayout(context);
            indicatorArea.addView(captionArea, -1, new android.widget.FrameLayout.LayoutParams(-2, -2));
            Space space = new Space(context);
            android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(0, 0, 1.0F);
            indicatorArea.addView(space, layoutparams);
            if (textInputView.editText != null)
            {
                adjustIndicatorPadding();
            }
        }
        boolean flag = flag1;
        if (i != 0)
        {
            if (i == 1)
            {
                flag = flag1;
            } else
            {
                flag = false;
            }
        }
        if (flag)
        {
            captionArea.setVisibility(0);
            captionArea.addView(textview);
            captionViewsAdded = captionViewsAdded + 1;
        } else
        {
            indicatorArea.addView(textview, i);
        }
        indicatorArea.setVisibility(0);
        indicatorsAdded = indicatorsAdded + 1;
    }

    final void adjustIndicatorPadding()
    {
        boolean flag;
        if (indicatorArea != null && textInputView.editText != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            ViewCompat.setPaddingRelative(indicatorArea, ViewCompat.getPaddingStart(textInputView.editText), 0, ViewCompat.getPaddingEnd(textInputView.editText), 0);
        }
    }

    final boolean errorShouldBeShown()
    {
        return captionToShow == 1 && errorView != null && !TextUtils.isEmpty(errorText);
    }

    final void hideError()
    {
        errorText = null;
        if (captionAnimator != null)
        {
            captionAnimator.cancel();
        }
        if (captionDisplayed == 1)
        {
            if (helperTextEnabled && !TextUtils.isEmpty(helperText))
            {
                captionToShow = 2;
            } else
            {
                captionToShow = 0;
            }
        }
        updateCaptionViewsVisibility(captionDisplayed, captionToShow, shouldAnimateCaptionView(errorView, null));
    }

    final void removeIndicator(TextView textview, int i)
    {
        boolean flag1 = true;
        if (indicatorArea != null)
        {
            boolean flag = flag1;
            if (i != 0)
            {
                if (i == 1)
                {
                    flag = flag1;
                } else
                {
                    flag = false;
                }
            }
            if (flag && captionArea != null)
            {
                captionViewsAdded = captionViewsAdded - 1;
                FrameLayout framelayout = captionArea;
                if (captionViewsAdded == 0)
                {
                    framelayout.setVisibility(8);
                }
                captionArea.removeView(textview);
            } else
            {
                indicatorArea.removeView(textview);
            }
            indicatorsAdded = indicatorsAdded - 1;
            textview = indicatorArea;
            if (indicatorsAdded == 0)
            {
                textview.setVisibility(8);
                return;
            }
        }
    }

    final void setHelperTextAppearance(int i)
    {
        TextView textview;
label0:
        {
            helperTextTextAppearance = i;
            if (helperTextView != null)
            {
                textview = helperTextView;
                if (android.os.Build.VERSION.SDK_INT < 23)
                {
                    break label0;
                }
                textview.setTextAppearance(i);
            }
            return;
        }
        textview.setTextAppearance(textview.getContext(), i);
    }

    final boolean shouldAnimateCaptionView(TextView textview, CharSequence charsequence)
    {
        return ViewCompat.isLaidOut(textInputView) && textInputView.isEnabled() && (captionToShow != captionDisplayed || textview == null || !TextUtils.equals(textview.getText(), charsequence));
    }

    final void updateCaptionViewsVisibility(int i, final int captionToShow, boolean flag)
    {
        if (!flag) goto _L2; else goto _L1
_L1:
        AnimatorSet animatorset = new AnimatorSet();
        captionAnimator = animatorset;
        ArrayList arraylist = new ArrayList();
        createCaptionAnimators(arraylist, helperTextEnabled, helperTextView, 2, i, captionToShow);
        createCaptionAnimators(arraylist, errorEnabled, errorView, 1, i, captionToShow);
        AnimatorSetCompat.playTogether(animatorset, arraylist);
        animatorset.addListener(new _cls1());
        animatorset.start();
_L4:
        textInputView.updateEditTextBackground();
        textInputView.updateLabelState(flag, false);
        textInputView.updateTextInputBoxState();
        return;
_L2:
        if (i != captionToShow)
        {
            if (captionToShow != 0)
            {
                TextView textview = getCaptionViewFromDisplayState(captionToShow);
                if (textview != null)
                {
                    textview.setVisibility(0);
                    textview.setAlpha(1.0F);
                }
            }
            if (i != 0)
            {
                TextView textview1 = getCaptionViewFromDisplayState(i);
                if (textview1 != null)
                {
                    textview1.setVisibility(4);
                    if (i == 1)
                    {
                        textview1.setText(null);
                    }
                }
            }
            captionDisplayed = captionToShow;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private class _cls1 extends AnimatorListenerAdapter
    {

        private final IndicatorViewController this$0;
        private final int val$captionToHide;
        private final int val$captionToShow;
        private final TextView val$captionViewToHide;
        private final TextView val$captionViewToShow;

        public final void onAnimationEnd(Animator animator)
        {
            captionDisplayed = captionToShow;
            captionAnimator = null;
            if (captionViewToHide != null)
            {
                captionViewToHide.setVisibility(4);
                if (captionToHide == 1 && errorView != null)
                {
                    errorView.setText(null);
                }
            }
        }

        public final void onAnimationStart(Animator animator)
        {
            if (captionViewToShow != null)
            {
                captionViewToShow.setVisibility(0);
            }
        }

        _cls1()
        {
            this$0 = IndicatorViewController.this;
            captionToShow = i;
            captionViewToHide = textview;
            captionToHide = j;
            captionViewToShow = textview1;
            super();
        }
    }

}
