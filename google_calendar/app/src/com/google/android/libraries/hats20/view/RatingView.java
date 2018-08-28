// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.view;

import android.content.Context;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.libraries.hats20.ui.StarRatingBar;
import com.google.devrel.hats.proto.QuestionRating;
import java.util.Collections;
import java.util.Map;

public class RatingView extends LinearLayout
{
    public static interface OnRatingClickListener
    {

        public abstract void onClickRating(int i);
    }


    private static final Map RATING_SMILEY_ICON_RESOURCE_MAP;
    public OnRatingClickListener onRatingClickListener;
    private QuestionRating question;

    public RatingView(Context context)
    {
        super(context);
        setOrientation(1);
        LayoutInflater.from(context).inflate(0x7f050099, this, true);
        context.getSystemService("accessibility");
    }

    public RatingView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        setOrientation(1);
        LayoutInflater.from(context).inflate(0x7f050099, this, true);
        context.getSystemService("accessibility");
    }

    public RatingView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        setOrientation(1);
        LayoutInflater.from(context).inflate(0x7f050099, this, true);
        context.getSystemService("accessibility");
    }

    public RatingView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        setOrientation(1);
        LayoutInflater.from(context).inflate(0x7f050099, this, true);
        context.getSystemService("accessibility");
    }

    static void removeOnClickListenersAndDisableClickEvents(ViewGroup viewgroup)
    {
        for (int i = 0; i < viewgroup.getChildCount(); i++)
        {
            viewgroup.getChildAt(i).setOnClickListener(null);
            viewgroup.getChildAt(i).setClickable(false);
        }

    }

    final void setDescriptionForTalkBack(View view, int i, int j)
    {
        String s1 = String.format("%d of %d", new Object[] {
            Integer.valueOf(i), Integer.valueOf(j)
        });
        if (i != 1) goto _L2; else goto _L1
_L1:
        String s;
        s = String.valueOf(s1);
        s1 = (String)question.label_.get(0);
        s = (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(s1).length())).append(s).append(" ").append(s1).toString();
_L4:
        view.setContentDescription(s);
        return;
_L2:
        s = s1;
        if (i == j)
        {
            s = String.valueOf(s1);
            s1 = (String)question.label_.get(1);
            s = (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(s1).length())).append(s).append(" ").append(s1).toString();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void setupRatingView(final QuestionRating question, boolean flag)
    {
        final ViewGroup ratingImageIconsContainer;
        final Object starRatingBar;
        this.question = question;
        ratingImageIconsContainer = (TextView)findViewById(0x7f10021e);
        starRatingBar = (String)question.label_.get(0);
        ratingImageIconsContainer.setText(((CharSequence) (starRatingBar)));
        ratingImageIconsContainer.setContentDescription(((CharSequence) (starRatingBar)));
        ratingImageIconsContainer = (TextView)findViewById(0x7f10021f);
        starRatingBar = (String)question.label_.get(1);
        ratingImageIconsContainer.setText(((CharSequence) (starRatingBar)));
        ratingImageIconsContainer.setContentDescription(((CharSequence) (starRatingBar)));
        ratingImageIconsContainer = (ViewGroup)findViewById(0x7f10021c);
        starRatingBar = (StarRatingBar)findViewById(0x7f10021d);
        if (!flag) goto _L2; else goto _L1
_L1:
        int i;
        ratingImageIconsContainer.setVisibility(0);
        question = LayoutInflater.from(getContext());
        i = 0;
_L11:
        if (i >= 5) goto _L4; else goto _L3
_L3:
        starRatingBar = question.inflate(0x7f050097, ratingImageIconsContainer, false);
        ((ImageView)((View) (starRatingBar)).findViewById(0x7f10021a)).setImageDrawable(VectorDrawableCompat.create(getResources(), ((Integer)RATING_SMILEY_ICON_RESOURCE_MAP.get(Integer.valueOf(i))).intValue(), null));
        final int rating = i + 1;
        ((View) (starRatingBar)).setTag(Integer.valueOf(rating));
        setDescriptionForTalkBack(((View) (starRatingBar)), rating, 5);
        ((View) (starRatingBar)).setOnClickListener(new _cls1());
        if (i != 0 && i != 4) goto _L6; else goto _L5
_L5:
        android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)((View) (starRatingBar)).getLayoutParams();
        if (i != 0) goto _L8; else goto _L7
_L7:
        layoutparams.setMargins(0, layoutparams.topMargin, layoutparams.rightMargin, layoutparams.bottomMargin);
_L9:
        ((View) (starRatingBar)).setLayoutParams(layoutparams);
_L6:
        ratingImageIconsContainer.addView(((View) (starRatingBar)));
        i++;
        continue; /* Loop/switch isn't completed */
_L8:
        if (i == 4)
        {
            layoutparams.setMargins(layoutparams.leftMargin, layoutparams.topMargin, 0, layoutparams.bottomMargin);
        }
        if (true) goto _L9; else goto _L2
_L2:
        ((StarRatingBar) (starRatingBar)).setVisibility(0);
        ((StarRatingBar) (starRatingBar)).setNumStars(question.numStars_);
        starRatingBar.onRatingChangeListener = new _cls2();
_L4:
        return;
        if (true) goto _L11; else goto _L10
_L10:
    }

    static 
    {
        ArrayMap arraymap = new ArrayMap();
        arraymap.put(Integer.valueOf(0), Integer.valueOf(0x7f02013f));
        arraymap.put(Integer.valueOf(1), Integer.valueOf(0x7f02013c));
        arraymap.put(Integer.valueOf(2), Integer.valueOf(0x7f02013d));
        arraymap.put(Integer.valueOf(3), Integer.valueOf(0x7f02013e));
        arraymap.put(Integer.valueOf(4), Integer.valueOf(0x7f020140));
        RATING_SMILEY_ICON_RESOURCE_MAP = Collections.unmodifiableMap(arraymap);
    }

    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final RatingView this$0;
        private final int val$rating;
        private final ViewGroup val$ratingImageIconsContainer;

        public final void onClick(View view)
        {
            view = RatingView.this;
            RatingView.removeOnClickListenersAndDisableClickEvents(ratingImageIconsContainer);
            if (onRatingClickListener != null)
            {
                onRatingClickListener.onClickRating(rating);
            }
        }

        _cls1()
        {
            this$0 = RatingView.this;
            ratingImageIconsContainer = viewgroup;
            rating = i;
            super();
        }
    }


    private class _cls2
        implements com.google.android.libraries.hats20.ui.StarRatingBar.OnRatingChangeListener
    {

        private final RatingView this$0;
        private final QuestionRating val$question;
        private final StarRatingBar val$starRatingBar;

        public final void onRatingChanged(int i)
        {
            setDescriptionForTalkBack(starRatingBar, i, question.numStars_);
            if (onRatingClickListener != null)
            {
                onRatingClickListener.onClickRating(i);
            }
        }

        _cls2()
        {
            this$0 = RatingView.this;
            starRatingBar = starratingbar;
            question = questionrating;
            super();
        }
    }

}
