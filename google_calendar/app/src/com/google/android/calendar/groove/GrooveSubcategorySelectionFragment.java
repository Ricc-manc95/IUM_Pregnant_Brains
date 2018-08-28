// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.transition.Fade;
import android.transition.TransitionSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.groove.category.GrooveCategories;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveWizardFragment, GrooveCategorySelectionFragment

public class GrooveSubcategorySelectionFragment extends GrooveWizardFragment
{
    public static interface Listener
    {

        public abstract void onSubcategorySelectionComplete(int i);
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/groove/GrooveSubcategorySelectionFragment);
    private ImageView backgroundImage;
    public int categoryId;
    private boolean hasAnimated;
    private LinearLayout subcategoryContainer;
    private LinearLayout textContainer;
    public TextView titleView;

    public GrooveSubcategorySelectionFragment()
    {
        hasAnimated = false;
    }

    static TransitionSet createEnterTransition()
    {
        Fade fade = new Fade();
        fade.setDuration(195L);
        TransitionSet transitionset = new TransitionSet();
        transitionset.addTransition(new _cls4());
        transitionset.addTransition(fade);
        return transitionset;
    }

    static TransitionSet createReturnTransition()
    {
        Fade fade = new Fade();
        fade.setDuration(195L);
        TransitionSet transitionset = new TransitionSet();
        _cls5 _lcls5 = new _cls5();
        transitionset.addTransition(fade);
        transitionset.addTransition(_lcls5);
        return transitionset;
    }

    static Button createSubcategoryButton(ContextThemeWrapper contextthemewrapper, String s)
    {
        Button button = new Button(contextthemewrapper, null, 0x7f140151);
        button.setTextColor(contextthemewrapper.getResources().getColor(0x7f0d011d));
        button.setText(s);
        return button;
    }

    private final AnimationSet createTextEnterAnimation()
    {
        AnimationSet animationset = new AnimationSet(true);
        animationset.addAnimation(new AlphaAnimation(0.0F, 1.0F));
        TranslateAnimation translateanimation = new TranslateAnimation(0.0F, 0.0F, requireContext().getResources().getDimensionPixelSize(0x7f0e01fe), 0.0F);
        translateanimation.setInterpolator(new FastOutSlowInInterpolator());
        animationset.addAnimation(translateanimation);
        animationset.setDuration(195L);
        return animationset;
    }

    static int getNumSubcategoriesToDisplay(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException("Invalid category");

        case 256: 
        case 512: 
        case 768: 
            return 4;

        case 1024: 
        case 1280: 
            return 3;
        }
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        categoryId = getArguments().getInt("CATEGORY_ID_KEY");
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        bundle = null;
        viewgroup = layoutinflater.inflate(0x7f050087, viewgroup, false);
        layoutinflater = requireContext().getResources();
        if (GrooveCategories.instance == null)
        {
            GrooveCategories.instance = new GrooveCategories(layoutinflater);
        }
        layoutinflater = GrooveCategories.instance;
        subcategoryContainer = (LinearLayout)viewgroup.findViewById(0x7f100147);
        backgroundImage = (ImageView)viewgroup.findViewById(0x7f1001ee);
        mFrame = (ViewGroup)viewgroup.findViewById(0x7f100143);
        titleView = (TextView)viewgroup.findViewById(0x7f1001ef);
        textContainer = (LinearLayout)viewgroup.findViewById(0x7f100144);
        textContainer.setTransitionGroup(true);
        viewgroup.findViewById(0x7f10013e).setFitsSystemWindows(true);
        backgroundImage.setTransitionName(GrooveCategorySelectionFragment.getBackgroundSharedElementName(categoryId));
        adjustCardUi();
        int i = categoryId;
        Object obj = (com.google.android.calendar.groove.category.GrooveCategories.Category)((GrooveCategories) (layoutinflater)).categories.get(i);
        titleView.setText(((com.google.android.calendar.groove.category.GrooveCategories.Category) (obj)).question);
        subcategoryContainer.removeAllViews();
        com.google.android.calendar.groove.category.GrooveCategories.Subcategory asubcategory[] = GrooveCategories.getSubcategoryListForCategory(categoryId);
        int k;
        if (super.mHost == null)
        {
            layoutinflater = null;
        } else
        {
            layoutinflater = (FragmentActivity)super.mHost.mActivity;
        }
        layoutinflater = new ContextThemeWrapper(layoutinflater, 0x7f140151);
        i = getNumSubcategoriesToDisplay(categoryId);
        if (asubcategory.length <= i)
        {
            i = asubcategory.length;
        }
        for (k = 0; k < i; k++)
        {
            final com.google.android.calendar.groove.category.GrooveCategories.Subcategory subcategory = asubcategory[k];
            Button button = createSubcategoryButton(layoutinflater, subcategory.name);
            button.setOnClickListener(new _cls1());
            subcategoryContainer.addView(button);
        }

        layoutinflater = createSubcategoryButton(layoutinflater, requireContext().getResources().getString(0x7f130149));
        layoutinflater.setOnClickListener(new _cls2());
        subcategoryContainer.addView(layoutinflater);
        mFrame.setBackgroundColor(((com.google.android.calendar.groove.category.GrooveCategories.Category) (obj)).backgroundColor);
        if (super.mHost == null)
        {
            layoutinflater = null;
        } else
        {
            layoutinflater = (FragmentActivity)super.mHost.mActivity;
        }
        obj = Utils.getRtlAdjustedImage(layoutinflater, BitmapFactory.decodeResource(requireContext().getResources(), ((com.google.android.calendar.groove.category.GrooveCategories.Category) (obj)).backgroundDrawableResId));
        backgroundImage.setImageBitmap(((Bitmap) (obj)));
        if (super.mHost == null)
        {
            layoutinflater = null;
        } else
        {
            layoutinflater = (FragmentActivity)super.mHost.mActivity;
        }
        if (!Utils.isPortrait(layoutinflater))
        {
            int j = subcategoryContainer.getChildCount();
            DisplayMetrics displaymetrics = new DisplayMetrics();
            float f;
            int l;
            if (super.mHost == null)
            {
                layoutinflater = bundle;
            } else
            {
                layoutinflater = (FragmentActivity)super.mHost.mActivity;
            }
            layoutinflater.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            l = displaymetrics.heightPixels;
            f = displaymetrics.widthPixels / 2;
            j = Math.max(0, ((int)(((float)((Bitmap) (obj)).getHeight() / (float)((Bitmap) (obj)).getWidth()) * f) + (requireContext().getResources().getDimensionPixelSize(0x7f0e01fc) + j * requireContext().getResources().getDimensionPixelSize(0x7f0e01fd))) - l);
            ((android.view.ViewGroup.MarginLayoutParams)backgroundImage.getLayoutParams()).setMargins(0, 0, 0, j * -1);
        }
        setStatusBarTheme(1);
        return viewgroup;
    }

    public final void onStart()
    {
        super.onStart();
        if (!hasAnimated)
        {
            hasAnimated = true;
            AnimationSet animationset = createTextEnterAnimation();
            animationset.setStartOffset(200L);
            animationset.setAnimationListener(new _cls3());
            titleView.startAnimation(animationset);
            for (int i = 0; i < subcategoryContainer.getChildCount(); i++)
            {
                AnimationSet animationset1 = createTextEnterAnimation();
                animationset1.setStartOffset((i + 1) * 50 + 200);
                subcategoryContainer.getChildAt(i).startAnimation(animationset1);
            }

        }
    }


    private class _cls4 extends Visibility
    {

        public final Animator onAppear(ViewGroup viewgroup, TransitionValues transitionvalues, int i, TransitionValues transitionvalues1, int j)
        {
            if (transitionvalues1 != null)
            {
                return BackButtonView.createAnimator(transitionvalues1.view, true);
            } else
            {
                return null;
            }
        }

        _cls4()
        {
        }
    }


    private class _cls5 extends Visibility
    {

        public final Animator onDisappear(ViewGroup viewgroup, TransitionValues transitionvalues, int i, TransitionValues transitionvalues1, int j)
        {
            if (transitionvalues != null)
            {
                return BackButtonView.createAnimator(transitionvalues.view, false);
            } else
            {
                return null;
            }
        }

        _cls5()
        {
        }
    }


    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final GrooveSubcategorySelectionFragment this$0;
        private final com.google.android.calendar.groove.category.GrooveCategories.Subcategory val$subcategory;

        public final void onClick(View view)
        {
            if (subcategory.requiresInput)
            {
                Object obj1 = GrooveSubcategorySelectionFragment.this;
                int i = subcategory.type;
                Object obj = subcategory.question;
                view = AnalyticsLoggerHolder.instance;
                if (view == null)
                {
                    throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                }
                Object obj2 = (AnalyticsLogger)view;
                if (((Fragment) (obj1)).mHost == null)
                {
                    view = null;
                } else
                {
                    view = (FragmentActivity)((Fragment) (obj1)).mHost.mActivity;
                }
                ((AnalyticsLogger) (obj2)).trackView(view, "goal2a_custom");
                view = new Fade();
                view.setDuration(105);
                ((Fragment) (obj1)).setExitTransition(view);
                ((Fragment) (obj1)).setReenterTransition(null);
                view = ((Fragment) (obj1)).mFragmentManager.beginTransaction();
                obj1 = new CustomGrooveFragment();
                obj2 = new Bundle();
                ((Bundle) (obj2)).putInt("GROOVE_TYPE_KEY", i);
                ((Bundle) (obj2)).putString("CUSTOM_QUESTION", ((String) (obj)));
                ((Fragment) (obj1)).setArguments(((Bundle) (obj2)));
                obj = new Fade();
                ((Fade) (obj)).setDuration(105);
                ((Fragment) (obj1)).setReturnTransition(obj);
                ((Fragment) (obj1)).setExitTransition(null);
                view.replace(0x7f10013c, ((Fragment) (obj1)), CustomGrooveFragment.TAG);
                view.addToBackStack(CustomGrooveFragment.TAG).commit();
            } else
            {
                view = GrooveSubcategorySelectionFragment.this;
                if (((Fragment) (view)).mHost == null)
                {
                    view = null;
                } else
                {
                    view = (FragmentActivity)((Fragment) (view)).mHost.mActivity;
                }
                if (view instanceof Listener)
                {
                    if (android.os.Build.VERSION.SDK_INT >= 22)
                    {
                        setReenterTransition(createReenterTransition());
                        GrooveSubcategorySelectionFragment groovesubcategoryselectionfragment = GrooveSubcategorySelectionFragment.this;
                        view = GrooveSubcategorySelectionFragment.this;
                        TransitionSet transitionset = new TransitionSet();
                        if (((Fragment) (view)).mHost == null)
                        {
                            view = null;
                        } else
                        {
                            view = (FragmentActivity)((Fragment) (view)).mHost.mActivity;
                        }
                        transitionset.addTransition(AnimatorHelper.createSlideTransition(view, true).addTarget(0x7f100144));
                        view = new Fade();
                        view.setDuration(75);
                        transitionset.addTransition(view.addTarget(0x7f1001ee));
                        transitionset.setOrdering(0);
                        groovesubcategoryselectionfragment.setExitTransition(transitionset);
                    }
                    view = GrooveSubcategorySelectionFragment.this;
                    if (((Fragment) (view)).mHost == null)
                    {
                        view = null;
                    } else
                    {
                        view = (FragmentActivity)((Fragment) (view)).mHost.mActivity;
                    }
                    ((Listener)view).onSubcategorySelectionComplete(subcategory.type);
                    return;
                }
            }
        }

        _cls1()
        {
            this$0 = GrooveSubcategorySelectionFragment.this;
            subcategory = subcategory1;
            super();
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        private final GrooveSubcategorySelectionFragment this$0;

        public final void onClick(View view)
        {
            Object obj = GrooveSubcategorySelectionFragment.this;
            int i = categoryId;
            view = AnalyticsLoggerHolder.instance;
            if (view == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            Object obj1 = (AnalyticsLogger)view;
            if (((Fragment) (obj)).mHost == null)
            {
                view = null;
            } else
            {
                view = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
            }
            ((AnalyticsLogger) (obj1)).trackView(view, "goal2a_custom");
            view = new Fade();
            view.setDuration(105);
            ((Fragment) (obj)).setExitTransition(view);
            ((Fragment) (obj)).setReenterTransition(null);
            view = ((Fragment) (obj)).mFragmentManager.beginTransaction();
            obj = new CustomGrooveFragment();
            obj1 = new Bundle();
            ((Bundle) (obj1)).putInt("GROOVE_TYPE_KEY", i);
            ((Bundle) (obj1)).putString("CUSTOM_QUESTION", null);
            ((Fragment) (obj)).setArguments(((Bundle) (obj1)));
            obj1 = new Fade();
            ((Fade) (obj1)).setDuration(105);
            ((Fragment) (obj)).setReturnTransition(obj1);
            ((Fragment) (obj)).setExitTransition(null);
            view.replace(0x7f10013c, ((Fragment) (obj)), CustomGrooveFragment.TAG);
            view.addToBackStack(CustomGrooveFragment.TAG).commit();
        }

        _cls2()
        {
            this$0 = GrooveSubcategorySelectionFragment.this;
            super();
        }
    }


    private class _cls3
        implements android.view.animation.Animation.AnimationListener
    {

        private final GrooveSubcategorySelectionFragment this$0;

        public final void onAnimationEnd(Animation animation)
        {
            titleView.requestFocus();
        }

        public final void onAnimationRepeat(Animation animation)
        {
        }

        public final void onAnimationStart(Animation animation)
        {
        }

        _cls3()
        {
            this$0 = GrooveSubcategorySelectionFragment.this;
            super();
        }
    }

}
