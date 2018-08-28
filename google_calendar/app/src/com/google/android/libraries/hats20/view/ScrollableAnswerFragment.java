// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.libraries.hats20.util.TextFormatUtil;

// Referenced classes of package com.google.android.libraries.hats20.view:
//            BaseFragment, ScrollViewWithSizeCallback

public abstract class ScrollableAnswerFragment extends BaseFragment
{

    private boolean isOnScrollChangedListenerAttached;
    private ImageView logoImageView;
    private TextView questionTextView;
    private ScrollShadowHandler scrollShadowHandler;
    public ScrollViewWithSizeCallback scrollView;
    public View scrollViewContents;
    public View surveyControlsContainer;
    public View surveyQuestionHeader;

    public ScrollableAnswerFragment()
    {
        scrollShadowHandler = new ScrollShadowHandler();
        isOnScrollChangedListenerAttached = false;
    }

    abstract View createScrollViewContents();

    public final String getCurrentQuestionText()
    {
        return questionTextView.getText().toString();
    }

    abstract String getQuestionText();

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        layoutinflater = layoutinflater.inflate(0x7f050098, viewgroup, false);
        surveyQuestionHeader = layoutinflater.findViewById(0x7f10020f);
        questionTextView = (TextView)layoutinflater.findViewById(0x7f100210);
        questionTextView.setText(TextFormatUtil.format(getQuestionText()));
        questionTextView.setContentDescription(getQuestionText());
        scrollViewContents = createScrollViewContents();
        scrollView = (ScrollViewWithSizeCallback)layoutinflater.findViewById(0x7f10021b);
        scrollView.addView(scrollViewContents);
        scrollView.onHeightChangedListener = scrollShadowHandler;
        if (!isOnScrollChangedListenerAttached && scrollView != null)
        {
            scrollView.getViewTreeObserver().addOnScrollChangedListener(scrollShadowHandler);
            isOnScrollChangedListenerAttached = true;
        }
        logoImageView = (ImageView)layoutinflater.findViewById(0x7f100202);
        bundle = logoImageView;
        int i = logoResId;
        if (i > 0)
        {
            bundle.setImageResource(i);
            bundle.setVisibility(0);
        } else
        {
            bundle.setVisibility(8);
        }
        surveyControlsContainer = ((FragmentActivity)viewgroup.getContext()).findViewById(0x7f10020d);
        return layoutinflater;
    }

    public final void onDestroyView()
    {
        if (isOnScrollChangedListenerAttached && scrollView != null)
        {
            scrollView.getViewTreeObserver().removeOnScrollChangedListener(scrollShadowHandler);
            isOnScrollChangedListenerAttached = false;
        }
        super.onDestroyView();
    }

    public final void updateQuestionText(String s)
    {
        questionTextView.setText(TextFormatUtil.format(s));
        questionTextView.setContentDescription(getQuestionText());
    }

    private class ScrollShadowHandler
        implements android.view.ViewTreeObserver.OnScrollChangedListener, ScrollViewWithSizeCallback.OnHeightChangedListener
    {

        private final ScrollableAnswerFragment this$0;

        private final void updateShadowVisibility(int i)
        {
            boolean flag2 = true;
            if (!mUserVisibleHint)
            {
                return;
            }
            boolean flag;
            boolean flag1;
            if (scrollView.getScrollY() == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (scrollViewContents.getBottom() == scrollView.getScrollY() + i)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (scrollViewContents.getBottom() > i)
            {
                i = ((flag2) ? 1 : 0);
            } else
            {
                i = 0;
            }
            if (i == 0 || flag)
            {
                surveyQuestionHeader.setElevation(0.0F);
            } else
            {
                surveyQuestionHeader.setElevation(requireContext().getResources().getDimensionPixelSize(0x7f0e0226));
            }
            if (i == 0 || flag1)
            {
                surveyControlsContainer.setElevation(0.0F);
                return;
            } else
            {
                surveyControlsContainer.setElevation(requireContext().getResources().getDimensionPixelSize(0x7f0e0235));
                return;
            }
        }

        public final void onHeightChanged(int i)
        {
            if (i != 0)
            {
                updateShadowVisibility(i);
            }
        }

        public final void onScrollChanged()
        {
            updateShadowVisibility(scrollView.getHeight());
        }

        ScrollShadowHandler()
        {
            this$0 = ScrollableAnswerFragment.this;
            super();
        }
    }

}
