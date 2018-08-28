// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.common.view.ObservableScrollView;
import com.google.android.calendar.groove.category.GrooveCategories;
import com.google.android.calendar.material.Material;
import java.util.List;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveWizardFragment, GrooveCategoryView, BackButtonView

public class GrooveCategorySelectionFragment extends GrooveWizardFragment
{
    public static interface Listener
    {

        public abstract void onCategorySelectionComplete(int i, View view);
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/groove/GrooveCategorySelectionFragment);
    public static boolean isTablet;
    public ViewGroup actionBar;
    public boolean actionBarTitleShowing;
    public TextView actionBarTitleView;
    public BackButtonView backButton;
    public View categoryTiles[];
    public ObservableScrollView scrollView;
    public LinearLayout storeHeader;

    public GrooveCategorySelectionFragment()
    {
        actionBarTitleShowing = false;
    }

    private final GrooveCategoryView createCategoryView(final int categoryType)
    {
        final Object categoryView = requireContext().getResources();
        if (GrooveCategories.instance == null)
        {
            GrooveCategories.instance = new GrooveCategories(((Resources) (categoryView)));
        }
        final com.google.android.calendar.groove.category.GrooveCategories.Category category = (com.google.android.calendar.groove.category.GrooveCategories.Category)GrooveCategories.instance.categories.get(categoryType);
        String s;
        String s1;
        if (super.mHost == null)
        {
            categoryView = null;
        } else
        {
            categoryView = (FragmentActivity)super.mHost.mActivity;
        }
        categoryView = new GrooveCategoryView(((Context) (categoryView)));
        s = category.title;
        ((GrooveCategoryView) (categoryView)).title.setText(s);
        s = category.description;
        ((GrooveCategoryView) (categoryView)).description.setText(s);
        ((GrooveCategoryView) (categoryView)).setBackgroundColor(category.backgroundColor);
        s = category.title;
        s1 = category.description;
        ((GrooveCategoryView) (categoryView)).setContentDescription(requireContext().getResources().getString(0x7f13025a, new Object[] {
            s, s1
        }));
        ((GrooveCategoryView) (categoryView)).setTransitionName((new StringBuilder(20)).append("category_").append(categoryType).toString());
        ((GrooveCategoryView) (categoryView)).setTransitionGroup(true);
        ((GrooveCategoryView) (categoryView)).getViewTreeObserver().addOnPreDrawListener(new _cls5());
        ((GrooveCategoryView) (categoryView)).setOnClickListener(new _cls6());
        if (isTablet)
        {
            ((GrooveCategoryView) (categoryView)).setElevation(requireContext().getResources().getDimensionPixelSize(0x7f0e007f));
            ((View) (categoryView)).setOutlineProvider(new GrooveWizardFragment._cls1(requireContext().getResources().getDimensionPixelSize(0x7f0e007d)));
            ((View) (categoryView)).setClipToOutline(true);
        }
        ((GrooveCategoryView) (categoryView)).setFocusable(true);
        ((GrooveCategoryView) (categoryView)).setClickable(true);
        return ((GrooveCategoryView) (categoryView));
    }

    public static String getBackgroundSharedElementName(int i)
    {
        return (new StringBuilder(20)).append("category_").append(i).toString();
    }

    protected final void adjustCardUi()
    {
        if (!isTablet)
        {
            TypedValue typedvalue = new TypedValue();
            FragmentActivity fragmentactivity;
            if (super.mHost == null)
            {
                fragmentactivity = null;
            } else
            {
                fragmentactivity = (FragmentActivity)super.mHost.mActivity;
            }
            if (fragmentactivity.getTheme().resolveAttribute(0x10102eb, typedvalue, true))
            {
                int i = TypedValue.complexToDimensionPixelSize(typedvalue.data, requireContext().getResources().getDisplayMetrics());
                ((android.view.ViewGroup.MarginLayoutParams)scrollView.getLayoutParams()).setMargins(0, i, 0, 0);
                scrollView.requestLayout();
            }
        }
    }

    final AnimationSet createEnterAnimation()
    {
        AnimationSet animationset = new AnimationSet(true);
        animationset.addAnimation(new AlphaAnimation(0.0F, 1.0F));
        TranslateAnimation translateanimation = new TranslateAnimation(0.0F, 0.0F, requireContext().getResources().getDimensionPixelSize(0x7f0e01eb), 0.0F);
        translateanimation.setInterpolator(new LinearOutSlowInInterpolator());
        animationset.addAnimation(translateanimation);
        animationset.setDuration(300L);
        return animationset;
    }

    final TransitionSet createTransitionSet(int i, boolean flag)
    {
        int j = 1;
        boolean flag1 = true;
        int k = 0;
        if (android.os.Build.VERSION.SDK_INT < 22)
        {
            TransitionSet transitionset = new TransitionSet();
            j = ((flag1) ? 1 : 0);
            if (flag)
            {
                j = 2;
            }
            Fade fade = new Fade(j);
            fade.setDuration(150L);
            fade.addTarget(0x7f1000f0);
            fade.addTarget(0x7f1001c9);
            String s = (new StringBuilder(20)).append("category_").append(i).toString();
            View aview[] = categoryTiles;
            j = aview.length;
            for (i = k; i < j; i++)
            {
                View view = aview[i];
                if (!s.equals(view.getTransitionName()))
                {
                    fade.addTarget(view.getTransitionName());
                }
            }

            transitionset.addTransition(fade);
            return transitionset;
        }
        TransitionSet transitionset1 = new TransitionSet();
        Slide slide = new Slide(48);
        slide.setDuration(300L);
        slide.setInterpolator(new FastOutSlowInInterpolator());
        slide.setPropagation(null);
        Slide slide1 = new Slide(80);
        slide1.setDuration(300L);
        slide1.setInterpolator(new FastOutSlowInInterpolator());
        slide1.setPropagation(null);
        String s1 = (new StringBuilder(20)).append("category_").append(i).toString();
        Slide slide2;
        Slide slide3;
        View aview1[];
        if (flag)
        {
            slide2 = slide;
        } else
        {
            slide2 = slide1;
        }
        if (flag)
        {
            slide3 = slide1;
        } else
        {
            slide3 = slide;
        }
        slide2.addTarget(0x7f1000f0);
        slide2.addTarget(0x7f1001c9);
        aview1 = categoryTiles;
        k = aview1.length;
        i = 0;
        while (i < k) 
        {
            View view1 = aview1[i];
            if (s1.equals(view1.getTransitionName()))
            {
                j = 0;
            } else
            if (j != 0)
            {
                slide2.addTarget(view1.getTransitionName());
            } else
            {
                slide3.addTarget(view1.getTransitionName());
            }
            i++;
        }
        transitionset1.addTransition(new _cls8());
        transitionset1.addTransition(slide);
        if (slide1.getTargetNames() != null && !slide1.getTargetNames().isEmpty())
        {
            transitionset1.addTransition(slide1);
        }
        return transitionset1;
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        Object obj;
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        ((AnalyticsLogger)obj).trackView(bundle, "goal1_categories");
        LinearLayout linearlayout;
        int i;
        if (Material.robotoMedium != null)
        {
            obj = Material.robotoMedium;
        } else
        {
            obj = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = ((Typeface) (obj));
        }
        isTablet = bundle.getResources().getBoolean(0x7f0c0016);
        viewgroup = layoutinflater.inflate(0x7f05007f, viewgroup, false);
        linearlayout = (LinearLayout)viewgroup.findViewById(0x7f1001c8);
        scrollView = (ObservableScrollView)viewgroup.findViewById(0x7f1001c7);
        storeHeader = (LinearLayout)viewgroup.findViewById(0x7f1001c9);
        storeHeader.setTransitionGroup(false);
        scrollView.setTransitionGroup(false);
        linearlayout.setTransitionGroup(false);
        layoutinflater = (TextView)viewgroup.findViewById(0x7f1001ca);
        if (!isTablet)
        {
            backButton = (BackButtonView)viewgroup.findViewById(0x7f10011e);
            backButton.setIcon(1);
            backButton.setTheme(2, false);
            actionBar = (ViewGroup)viewgroup.findViewById(0x7f1000f0);
            actionBarTitleView = (TextView)viewgroup.findViewById(0x7f1001c6);
            TextView textview = actionBarTitleView;
            float f;
            int j;
            int k;
            if (actionBarTitleShowing)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            textview.setVisibility(i);
            actionBarTitleView.setTypeface(((Typeface) (obj)));
            scrollView.listener = new _cls1();
            actionBar.getViewTreeObserver().addOnGlobalLayoutListener(new _cls2());
            viewgroup.findViewById(0x7f10013e).setFitsSystemWindows(true);
            actionBar.setOutlineProvider(new _cls3());
            actionBar.setTransitionGroup(false);
            bundle = bundle.obtainStyledAttributes(new int[] {
                0x101030e
            });
            backButton.setBackground(bundle.getDrawable(0));
            backButton.setClipToOutline(true);
            backButton.setOutlineProvider(new _cls4());
        }
        setStatusBarTheme(1);
        adjustCardUi();
        layoutinflater.setTypeface(((Typeface) (obj)));
        k = requireContext().getResources().getDimensionPixelSize(0x7f0e01ea);
        categoryTiles = new View[5];
        categoryTiles[0] = createCategoryView(256);
        categoryTiles[1] = createCategoryView(512);
        categoryTiles[2] = createCategoryView(768);
        categoryTiles[3] = createCategoryView(1024);
        categoryTiles[4] = createCategoryView(1280);
        j = 0;
        do
        {
            if (j < categoryTiles.length)
            {
                if (isTablet)
                {
                    layoutinflater = requireContext().getResources();
                    f = Math.max(layoutinflater.getConfiguration().screenWidthDp, layoutinflater.getConfiguration().screenHeightDp);
                    i = Math.round(layoutinflater.getDisplayMetrics().density * f) / 2;
                } else
                {
                    if (super.mHost == null)
                    {
                        layoutinflater = null;
                    } else
                    {
                        layoutinflater = (FragmentActivity)super.mHost.mActivity;
                    }
                    if (android.os.Build.VERSION.SDK_INT >= 24 && layoutinflater.isInMultiWindowMode())
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    if (i != 0)
                    {
                        i = (int)Math.ceil((float)requireContext().getResources().getConfiguration().screenWidthDp * requireContext().getResources().getDisplayMetrics().density);
                    } else
                    {
                        if (super.mHost == null)
                        {
                            layoutinflater = null;
                        } else
                        {
                            layoutinflater = (FragmentActivity)super.mHost.mActivity;
                        }
                        layoutinflater = ((WindowManager)layoutinflater.getSystemService("window")).getDefaultDisplay();
                        bundle = new Point();
                        layoutinflater.getRealSize(bundle);
                        i = ((Point) (bundle)).x;
                    }
                }
            } else
            {
                return viewgroup;
            }
            layoutinflater = new android.widget.LinearLayout.LayoutParams(i, (int)(((float)i / 16F) * 9F));
            layoutinflater.setMargins(k, 0, k, requireContext().getResources().getDimensionPixelOffset(0x7f0e01e9));
            if (!isTablet && j == categoryTiles.length - 1)
            {
                layoutinflater.setMargins(k, 0, k, 0);
            }
            linearlayout.addView(categoryTiles[j], layoutinflater);
            j++;
        } while (true);
    }


    private class _cls5
        implements android.view.ViewTreeObserver.OnPreDrawListener
    {

        private final GrooveCategorySelectionFragment this$0;
        private final com.google.android.calendar.groove.category.GrooveCategories.Category val$category;
        private final GrooveCategoryView val$categoryView;

        public final boolean onPreDraw()
        {
            int i = mView.getWidth();
            if (GrooveCategorySelectionFragment.isTablet)
            {
                i /= 2;
            }
            int j = (int)(((float)i / 16F) * 9F);
            Object obj = GrooveCategorySelectionFragment.this;
            Object obj1;
            if (((Fragment) (obj)).mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
            }
            obj1 = GrooveCategorySelectionFragment.this;
            if (((Fragment) (obj1)).mHost == null)
            {
                obj1 = null;
            } else
            {
                obj1 = (FragmentActivity)((Fragment) (obj1)).mHost.mActivity;
            }
            obj = Utils.getRtlAdjustedImage(((Context) (obj)), GrooveUtils.decodeScaledBitmapFromResource(((Context) (obj1)), category.backgroundDrawableResId, i, j));
            categoryView.setBackground(new RippleDrawable(ColorStateList.valueOf(requireContext().getResources().getColor(0x7f0d0087)), new BitmapDrawable(requireContext().getResources(), ((android.graphics.Bitmap) (obj))), null));
            categoryView.getViewTreeObserver().removeOnPreDrawListener(this);
            return false;
        }

        _cls5()
        {
            this$0 = GrooveCategorySelectionFragment.this;
            category = category1;
            categoryView = groovecategoryview;
            super();
        }
    }


    private class _cls6
        implements android.view.View.OnClickListener
    {

        private final GrooveCategorySelectionFragment this$0;
        private final int val$categoryType;
        private final GrooveCategoryView val$categoryView;

        public final void onClick(View view)
        {
            view = GrooveCategorySelectionFragment.this;
            if (((Fragment) (view)).mHost == null)
            {
                view = null;
            } else
            {
                view = (FragmentActivity)((Fragment) (view)).mHost.mActivity;
            }
            if (view instanceof Listener)
            {
                view = GrooveCategorySelectionFragment.this;
                if (((Fragment) (view)).mHost == null)
                {
                    view = null;
                } else
                {
                    view = (FragmentActivity)((Fragment) (view)).mHost.mActivity;
                }
                ((Listener)view).onCategorySelectionComplete(categoryType, categoryView);
            }
        }

        _cls6()
        {
            this$0 = GrooveCategorySelectionFragment.this;
            categoryType = i;
            categoryView = groovecategoryview;
            super();
        }
    }


    private class _cls8 extends Visibility
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

        _cls8()
        {
        }
    }


    private class _cls1
        implements com.google.android.calendar.common.view.ObservableScrollView.OnScrollChangeListener
    {

        private final GrooveCategorySelectionFragment this$0;

        public final void onScrollChanged$514KIIA955B0____0()
        {
            GrooveCategorySelectionFragment groovecategoryselectionfragment = GrooveCategorySelectionFragment.this;
            Resources resources = groovecategoryselectionfragment.requireContext().getResources();
            int i = groovecategoryselectionfragment.scrollView.getScrollY();
            if (!groovecategoryselectionfragment.actionBarTitleShowing && i > resources.getDimensionPixelSize(0x7f0e01e1))
            {
                groovecategoryselectionfragment.actionBarTitleShowing = true;
                groovecategoryselectionfragment.actionBarTitleView.setVisibility(0);
                AnimationSet animationset = new AnimationSet(true);
                animationset.addAnimation(new AlphaAnimation(0.0F, 1.0F));
                TranslateAnimation translateanimation = new TranslateAnimation(0.0F, 0.0F, groovecategoryselectionfragment.requireContext().getResources().getDimensionPixelSize(0x7f0e01e4), 0.0F);
                translateanimation.setInterpolator(new FastOutSlowInInterpolator());
                animationset.addAnimation(translateanimation);
                animationset.setDuration(300L);
                groovecategoryselectionfragment.actionBarTitleView.startAnimation(animationset);
            } else
            if (groovecategoryselectionfragment.actionBarTitleShowing && i <= resources.getDimensionPixelSize(0x7f0e01e2))
            {
                groovecategoryselectionfragment.actionBarTitleShowing = false;
                AnimationSet animationset1 = new AnimationSet(true);
                animationset1.addAnimation(new AlphaAnimation(1.0F, 0.0F));
                TranslateAnimation translateanimation1 = new TranslateAnimation(0.0F, 0.0F, 0.0F, groovecategoryselectionfragment.requireContext().getResources().getDimensionPixelSize(0x7f0e01e4));
                translateanimation1.setInterpolator(new FastOutSlowInInterpolator());
                animationset1.addAnimation(translateanimation1);
                animationset1.setDuration(300L);
                animationset1.setAnimationListener(groovecategoryselectionfragment. new _cls7());
                groovecategoryselectionfragment.actionBarTitleView.startAnimation(animationset1);
            }
            if (i > 0)
            {
                int j = resources.getDimensionPixelSize(0x7f0e01e3);
                groovecategoryselectionfragment.actionBar.setElevation(Math.min((float)i / 5F, j));
                return;
            } else
            {
                groovecategoryselectionfragment.actionBar.setElevation(0.0F);
                return;
            }
        }

        _cls1()
        {
            this$0 = GrooveCategorySelectionFragment.this;
            super();
        }

        private class _cls7
            implements android.view.animation.Animation.AnimationListener
        {

            private final GrooveCategorySelectionFragment this$0;

            public final void onAnimationEnd(Animation animation)
            {
                actionBarTitleView.setVisibility(8);
            }

            public final void onAnimationRepeat(Animation animation)
            {
            }

            public final void onAnimationStart(Animation animation)
            {
            }

            _cls7()
            {
                this$0 = GrooveCategorySelectionFragment.this;
                super();
            }
        }

    }


    private class _cls2
        implements android.view.ViewTreeObserver.OnGlobalLayoutListener
    {

        private final GrooveCategorySelectionFragment this$0;

        public final void onGlobalLayout()
        {
            GrooveCategorySelectionFragment groovecategoryselectionfragment = GrooveCategorySelectionFragment.this;
            boolean flag;
            if (((Fragment) (groovecategoryselectionfragment)).mHost != null && ((Fragment) (groovecategoryselectionfragment)).mAdded)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                return;
            }
            groovecategoryselectionfragment = GrooveCategorySelectionFragment.this;
            Resources resources = groovecategoryselectionfragment.requireContext().getResources();
            int i = groovecategoryselectionfragment.scrollView.getScrollY();
            if (!groovecategoryselectionfragment.actionBarTitleShowing && i > resources.getDimensionPixelSize(0x7f0e01e1))
            {
                groovecategoryselectionfragment.actionBarTitleShowing = true;
                groovecategoryselectionfragment.actionBarTitleView.setVisibility(0);
                AnimationSet animationset = new AnimationSet(true);
                animationset.addAnimation(new AlphaAnimation(0.0F, 1.0F));
                TranslateAnimation translateanimation = new TranslateAnimation(0.0F, 0.0F, groovecategoryselectionfragment.requireContext().getResources().getDimensionPixelSize(0x7f0e01e4), 0.0F);
                translateanimation.setInterpolator(new FastOutSlowInInterpolator());
                animationset.addAnimation(translateanimation);
                animationset.setDuration(300L);
                groovecategoryselectionfragment.actionBarTitleView.startAnimation(animationset);
            } else
            if (groovecategoryselectionfragment.actionBarTitleShowing && i <= resources.getDimensionPixelSize(0x7f0e01e2))
            {
                groovecategoryselectionfragment.actionBarTitleShowing = false;
                AnimationSet animationset1 = new AnimationSet(true);
                animationset1.addAnimation(new AlphaAnimation(1.0F, 0.0F));
                TranslateAnimation translateanimation1 = new TranslateAnimation(0.0F, 0.0F, 0.0F, groovecategoryselectionfragment.requireContext().getResources().getDimensionPixelSize(0x7f0e01e4));
                translateanimation1.setInterpolator(new FastOutSlowInInterpolator());
                animationset1.addAnimation(translateanimation1);
                animationset1.setDuration(300L);
                animationset1.setAnimationListener(groovecategoryselectionfragment. new _cls7());
                groovecategoryselectionfragment.actionBarTitleView.startAnimation(animationset1);
            }
            if (i > 0)
            {
                int j = resources.getDimensionPixelSize(0x7f0e01e3);
                groovecategoryselectionfragment.actionBar.setElevation(Math.min((float)i / 5F, j));
                return;
            } else
            {
                groovecategoryselectionfragment.actionBar.setElevation(0.0F);
                return;
            }
        }

        _cls2()
        {
            this$0 = GrooveCategorySelectionFragment.this;
            super();
        }
    }


    private class _cls3 extends ViewOutlineProvider
    {

        public final void getOutline(View view, Outline outline)
        {
            outline.setRect(0, 0, view.getWidth(), view.getHeight());
        }

        _cls3()
        {
        }
    }


    private class _cls4 extends ViewOutlineProvider
    {

        public final void getOutline(View view, Outline outline)
        {
            outline.setOval(0, 0, view.getWidth(), view.getHeight());
        }

        _cls4()
        {
        }
    }

}
