// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.graphics.Target;
import android.text.TextUtils;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.bitmap.BitmapCache;
import com.android.calendarcommon2.LogUtils;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;
import com.google.android.apps.calendar.flair.FlairAllocatorFactory;
import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.api.habit.HabitContract;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.api.habit.HabitModifications;
import com.google.android.calendar.common.drawable.ListenableBitmapDrawable;
import com.google.android.calendar.event.image.BitmapCacheHolder;
import com.google.android.calendar.groove.category.GrooveCategories;
import com.google.android.calendar.utils.AccessibilityUtils;
import com.google.android.calendar.utils.ColorUtils;
import com.google.android.calendar.utils.SystemWindowInsetApplier;
import com.google.android.calendar.utils.rtl.RtlUtils;
import com.google.android.calendar.utils.viewpager.DisablableViewPager;
import com.google.android.calendar.volley.VolleyQueueHolder;
import java.util.ArrayList;
import java.util.Collections;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveWizardFragment, AnimatorHelper, GrooveFrequencySelectionView, GrooveSummaryView, 
//            BackButtonView, GrooveScheduleView, GrooveDurationSelectionView, GroovePreferredTimesSelectionView, 
//            GrooveBelongIntegrationSelectionView, GrooveUtils

public class GrooveScheduleFragment extends GrooveWizardFragment
{

    public static final Target FAB_TARGET;
    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/groove/GrooveScheduleFragment);
    private static BitmapCache cache;
    public BackButtonView backArrow;
    public int backgroundColor;
    public ListenableBitmapDrawable backgroundDrawable;
    public ViewGroup backgroundFrame;
    public ImageView backgroundImage;
    public GrooveScheduleView belongIntegrationView;
    public int colorTheme;
    private HabitDescriptor descriptor;
    public GrooveDurationSelectionView durationView;
    public GrooveScheduleView frequencyView;
    public boolean isRtl;
    public PagerAdapter pagerAdapter;
    public GrooveScheduleView preferredTimesView;
    public ArrayList screenList;
    public GrooveSummaryView summaryView;
    private String title;
    private int type;
    public DisablableViewPager viewPager;

    public GrooveScheduleFragment()
    {
    }

    static AnimatorSet createImageEnterAnimator(View view)
    {
        AnimatorSet animatorset = new AnimatorSet();
        ObjectAnimator objectanimator = ObjectAnimator.ofFloat(view, "alpha", new float[] {
            0.0F, 1.0F
        });
        objectanimator.setDuration(105L);
        objectanimator.setInterpolator(new LinearInterpolator());
        view = ObjectAnimator.ofFloat(view, "translationX", new float[] {
            (float)view.getWidth(), 0.0F
        });
        view.setDuration(300L);
        view.setInterpolator(new FastOutSlowInInterpolator());
        animatorset.playTogether(new Animator[] {
            objectanimator, view
        });
        return animatorset;
    }

    static int getFocusViewId(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalStateException((new StringBuilder(26)).append("Unknown index: ").append(i).toString());

        case 0: // '\0'
            return 0x7f1001d7;

        case 1: // '\001'
            return 0x7f1001cb;

        case 2: // '\002'
            return 0x7f1001e6;

        case 3: // '\003'
            return 0x7f1001c2;

        case 4: // '\004'
            return 0x7f1001f0;
        }
    }

    static void startBackgroundTransitionAnimation(View view, int i, int j, final View imageView)
    {
        view = ObjectAnimator.ofInt(view, "backgroundColor", new int[] {
            i, j
        });
        view.setStartDelay(50L);
        view.setDuration(105L);
        view.setEvaluator(new ArgbEvaluator());
        view.setInterpolator(new LinearInterpolator());
        if (imageView != null)
        {
            imageView.setAlpha(0.0F);
            view.addListener(new _cls7());
        }
        view.start();
    }

    final TransitionSet createTransition(Activity activity, boolean flag)
    {
        activity = AnimatorHelper.createSlideTransition(activity, false);
        activity.addTarget(com/google/android/calendar/groove/GrooveFrequencySelectionView);
        if (!flag)
        {
            return activity;
        } else
        {
            Transition transition = (new Fade()).addTarget(0x7f1001eb).setDuration(105L);
            Transition transition1 = (new _cls8()).addTarget(0x7f10011e).setDuration(105L);
            TransitionSet transitionset = new TransitionSet();
            transitionset.addTransition(transition);
            transitionset.addTransition(activity);
            transitionset.addTransition(transition1);
            return transitionset;
        }
    }

    final void initializeImageBackground(final boolean animateFromCategoryPage)
    {
        Object obj;
        final com.google.android.calendar.groove.category.GrooveCategories.Category category;
        int i;
        Object obj1 = new com.android.bitmap.drawable.ExtendedBitmapDrawable.ExtendedOptions(2);
        obj1.parallaxSpeedMultiplier = 1.05F;
        obj1.parallaxDirection = 1;
        int j;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = ((WindowManager)((FragmentActivity) (obj)).getSystemService("window")).getDefaultDisplay();
        category = new Point();
        ((Display) (obj)).getRealSize(category);
        i = Math.max(((Point) (category)).x, ((Point) (category)).y);
        backgroundDrawable = new ListenableBitmapDrawable(requireContext().getResources(), cache, true, ((com.android.bitmap.drawable.ExtendedBitmapDrawable.ExtendedOptions) (obj1)));
        obj = requireContext().getResources();
        if (GrooveCategories.instance == null)
        {
            GrooveCategories.instance = new GrooveCategories(((Resources) (obj)));
        }
        obj = GrooveCategories.instance;
        obj1 = FlairAllocatorFactory.getFlairUrlString(title);
        j = type;
        category = (com.google.android.calendar.groove.category.GrooveCategories.Category)((GrooveCategories) (obj)).categories.get(j & 0xff00);
        obj = obj1;
        if (TextUtils.isEmpty(((CharSequence) (obj1))))
        {
            obj = FlairAllocatorFactory.getGrooveFlairUrlString(type);
        }
        if (!TextUtils.isEmpty(((CharSequence) (obj)))) goto _L2; else goto _L1
_L1:
        obj = CalendarListEntryCache.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        obj = (CalendarListEntry[])((ListenableFutureCache)obj).result;
        if (obj == null) goto _L4; else goto _L3
_L3:
        int k;
        k = obj.length;
        i = 0;
_L8:
        if (i >= k) goto _L4; else goto _L5
_L5:
        CalendarListEntry calendarlistentry = obj[i];
        if (!descriptor.calendar.matches(calendarlistentry.getDescriptor())) goto _L7; else goto _L6
_L6:
        backgroundColor = ColorUtils.getDisplayColorFromColor(calendarlistentry.getColor().getValue());
        if (animateFromCategoryPage)
        {
            backgroundFrame.setBackgroundColor(category.backgroundColor);
            startBackgroundTransitionAnimation(backgroundFrame, category.backgroundColor, backgroundColor, null);
        } else
        {
            backgroundFrame.setBackgroundColor(backgroundColor);
        }
        summaryView.setFabColor(-1);
        backgroundImage.setVisibility(4);
        matchColorThemeToBackgroundColor(backgroundColor);
_L4:
        return;
_L7:
        i++;
        if (true) goto _L8; else goto _L2
_L2:
        Object obj2;
        if (animateFromCategoryPage)
        {
            backgroundColor = category.backgroundColor;
            backgroundFrame.setBackgroundColor(backgroundColor);
            if (super.mHost == null)
            {
                obj2 = null;
            } else
            {
                obj2 = (FragmentActivity)super.mHost.mActivity;
            }
            if (Utils.isPortrait(((Context) (obj2))))
            {
                if (super.mHost == null)
                {
                    obj2 = null;
                } else
                {
                    obj2 = (FragmentActivity)super.mHost.mActivity;
                }
                obj2 = Utils.getRtlAdjustedImage(((Context) (obj2)), BitmapFactory.decodeResource(requireContext().getResources(), category.backgroundDrawableResId));
                obj2 = new BitmapDrawable(requireContext().getResources(), ((Bitmap) (obj2)));
                backgroundImage.setAlpha(0.0F);
                backgroundImage.setImageDrawable(((android.graphics.drawable.Drawable) (obj2)));
            }
            summaryView.setFabColor(category.fabColor);
        }
        obj = new ImageRequest(((String) (obj)), new _cls4(), i, i, null, null, new _cls5());
        obj.mTag = "background_target_request";
        obj2 = VolleyQueueHolder.requestQueue;
        if (obj2 == null)
        {
            throw new NullPointerException(String.valueOf("VolleyQueueHolder#initialize(...) must be called first"));
        } else
        {
            ((RequestQueue)obj2).add(((Request) (obj)));
            return;
        }
    }

    final void matchColorThemeToBackgroundColor(int i)
    {
        if (android.support.v4.graphics.ColorUtils.calculateContrast(-1, i) > 4.5D)
        {
            i = 0;
        } else
        {
            i = 1;
        }
        if (colorTheme == i)
        {
            return;
        }
        colorTheme = i;
        backArrow.setTheme(i, true);
        durationView.changeTheme(i);
        frequencyView.changeTheme(i);
        preferredTimesView.changeTheme(i);
        belongIntegrationView.changeTheme(i);
        GrooveSummaryView groovesummaryview = summaryView;
        int j;
        if (i == 0)
        {
            j = 0x7f0d011e;
        } else
        {
            j = 0x7f0d011d;
        }
        AnimatorHelper.createFadeOutFadeInAnimator(groovesummaryview, new GrooveSummaryView._cls4(groovesummaryview, groovesummaryview.getResources().getColor(j))).start();
        setStatusBarTheme(i);
    }

    public final void onCreate(Bundle bundle)
    {
        Object obj = null;
        super.onCreate(bundle);
        type = getArguments().getInt("groove_type");
        title = getArguments().getString("title");
        descriptor = (HabitDescriptor)getArguments().getParcelable("groove_descriptor");
        colorTheme = 1;
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        summaryView = new GrooveSummaryView(bundle);
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        frequencyView = new GrooveFrequencySelectionView(bundle, type);
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        durationView = new GrooveDurationSelectionView(bundle);
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        preferredTimesView = new GroovePreferredTimesSelectionView(bundle);
        if (super.mHost == null)
        {
            bundle = obj;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        belongIntegrationView = new GrooveBelongIntegrationSelectionView(bundle);
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        viewgroup = layoutinflater.inflate(0x7f050086, viewgroup, false);
        cache = BitmapCacheHolder.getBackgroundImageCache();
        viewPager = (DisablableViewPager)viewgroup.findViewById(0x7f1001ed);
        backgroundImage = (ImageView)viewgroup.findViewById(0x7f1001ec);
        backArrow = (BackButtonView)viewgroup.findViewById(0x7f10011e);
        mFrame = (ViewGroup)viewgroup.findViewById(0x7f100143);
        backgroundFrame = (ViewGroup)viewgroup.findViewById(0x7f1001eb);
        layoutinflater = viewgroup.findViewById(0x7f10013e);
        SystemWindowInsetApplier systemwindowinsetapplier = new SystemWindowInsetApplier();
        systemwindowinsetapplier.addView(layoutinflater, 10, 2);
        ViewCompat.setOnApplyWindowInsetsListener(layoutinflater, systemwindowinsetapplier);
        adjustCardUi();
        int i;
        if (bundle != null)
        {
            type = bundle.getInt("groove_type");
            descriptor = (HabitDescriptor)bundle.getParcelable("groove_descriptor");
            screenList = bundle.getIntegerArrayList("screen_list");
        } else
        {
            screenList = new ArrayList(4);
            Collections.addAll(screenList, new Integer[] {
                Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(4)
            });
        }
        pagerAdapter = new _cls1();
        viewPager.setAdapter(pagerAdapter);
        if (bundle == null)
        {
            i = 0;
        } else
        {
            i = bundle.getInt("current_page", 0);
        }
        pagerAdapter.notifyDataSetChanged();
        viewPager.setCurrentItem(screenList.indexOf(Integer.valueOf(i)));
        initializeImageBackground(true);
        if (super.mHost == null)
        {
            layoutinflater = null;
        } else
        {
            layoutinflater = (FragmentActivity)super.mHost.mActivity;
        }
        isRtl = RtlUtils.isLayoutDirectionRtl(layoutinflater);
        if (isRtl)
        {
            viewPager.leftScrollEnabled = false;
        } else
        {
            viewPager.rightScrollEnabled = false;
        }
        viewPager.addOnPageChangeListener(new _cls2());
        viewPager.setEnabled(true);
        return viewgroup;
    }

    public final void onDestroy()
    {
        super.onDestroy();
        RequestQueue requestqueue = VolleyQueueHolder.requestQueue;
        if (requestqueue == null)
        {
            throw new NullPointerException(String.valueOf("VolleyQueueHolder#initialize(...) must be called first"));
        } else
        {
            ((RequestQueue)requestqueue).cancelAll("background_target_request");
            return;
        }
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        bundle.putInt("groove_type", type);
        bundle.putString("title", title);
        bundle.putParcelable("groove_descriptor", descriptor);
        bundle.putInt("current_page", viewPager.getCurrentItem());
        bundle.putInt("color_theme", colorTheme);
        bundle.putIntegerArrayList("screen_list", screenList);
        super.onSaveInstanceState(bundle);
    }

    public final void onStart()
    {
        super.onStart();
        int i = viewPager.getCurrentItem();
        if (i >= screenList.size())
        {
            i = -1;
        } else
        {
            i = ((Integer)screenList.get(i)).intValue();
        }
        requestInitialScreenFocus(i);
    }

    final void requestInitialScreenFocus(final int position)
    {
        final View view;
        if (super.mHost == null)
        {
            view = null;
        } else
        {
            view = (FragmentActivity)super.mHost.mActivity;
        }
        if (AccessibilityUtils.isAccessibilityEnabled(view))
        {
            if ((view = super.mView) != null && position != -1)
            {
                view.postDelayed(new _cls3(), 100L);
                return;
            }
        }
    }

    public final void setGrooveModifications(HabitModifications habitmodifications)
    {
        CalendarListEntry acalendarlistentry[];
        Object obj2;
        int j;
        int l;
        boolean flag;
        int i1;
        flag = false;
        obj2 = summaryView;
        obj2.habitModifications = habitmodifications;
        HabitContract habitcontract = habitmodifications.getContract();
        Object obj;
        if (habitcontract != null)
        {
            if (TextUtils.isEmpty(habitmodifications.getSummary()))
            {
                obj = ((GrooveSummaryView) (obj2)).getResources();
                if (GrooveCategories.instance == null)
                {
                    GrooveCategories.instance = new GrooveCategories(((Resources) (obj)));
                }
                int i = habitmodifications.getType();
                obj = GrooveCategories.resources.getString(GrooveCategories.GROOVE_NAME_IDS.get(i));
            } else
            {
                obj = habitmodifications.getSummary();
            }
            ((GrooveSummaryView) (obj2)).titleView.setText(((CharSequence) (obj)));
            obj = GrooveUtils.getFrequencyString(((GrooveSummaryView) (obj2)).getResources(), habitcontract);
            ((GrooveSummaryView) (obj2)).frequencyTextView.setText(((CharSequence) (obj)));
            ((GrooveSummaryView) (obj2)).durationTextView.setText(GrooveUtils.getDurationAndPreferredTimesString(((GrooveSummaryView) (obj2)).getResources(), habitcontract));
            ((GrooveSummaryView) (obj2)).durationTextView.setContentDescription(GrooveUtils.getDurationAndPreferredTimesAccessibilityString(((GrooveSummaryView) (obj2)).getResources(), habitcontract));
        }
        obj = CalendarListEntryCache.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        acalendarlistentry = (CalendarListEntry[])((ListenableFutureCache)obj).result;
        if (acalendarlistentry == null)
        {
            break MISSING_BLOCK_LABEL_406;
        }
        i1 = acalendarlistentry.length;
        j = 0;
        l = 0;
_L6:
        if (j >= i1) goto _L2; else goto _L1
_L1:
        int k = l;
        if (!GrooveUtils.isPrimaryGoogleCalendar(acalendarlistentry[j])) goto _L4; else goto _L3
_L3:
        l++;
        k = l;
        if (l <= 1) goto _L4; else goto _L5
_L5:
        j = 1;
_L7:
        Object obj1;
        if (j != 0)
        {
            ((GrooveSummaryView) (obj2)).calendarNameTextView.setText(((GrooveSummaryView) (obj2)).habitModifications.getDescriptor().calendar.calendarId);
            ((GrooveSummaryView) (obj2)).calendarNameTextView.setVisibility(0);
        } else
        {
            ((GrooveSummaryView) (obj2)).calendarNameTextView.setVisibility(8);
        }
        obj1 = durationView;
        k = habitmodifications.getContract().getInterval();
        obj2 = ((GrooveDurationSelectionView) (obj1)).findViewById(0x7f1001cc);
        if (k == 2)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (obj2 != null)
        {
            if (j != 0)
            {
                j = 0;
            } else
            {
                j = 8;
            }
            ((View) (obj2)).setVisibility(j);
        }
        obj1 = ((GrooveDurationSelectionView) (obj1)).findViewById(0x7f1001d1);
        if (k == 3)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (obj1 != null)
        {
            if (j != 0)
            {
                j = ((flag) ? 1 : 0);
            } else
            {
                j = 8;
            }
            ((View) (obj1)).setVisibility(j);
        }
        descriptor = habitmodifications.getDescriptor();
        title = habitmodifications.getSummary();
        type = habitmodifications.getType();
        return;
_L4:
        j++;
        l = k;
          goto _L6
_L2:
        j = 0;
          goto _L7
    }

    static 
    {
        android.support.v7.graphics.Target.Builder builder = new android.support.v7.graphics.Target.Builder();
        builder.mTarget.mSaturationTargets[1] = 0.3F;
        builder.mTarget.mLightnessTargets[1] = 0.65F;
        builder.mTarget.mWeights[2] = 0.05F;
        builder.mTarget.mLightnessTargets[2] = 0.8F;
        FAB_TARGET = builder.mTarget;
    }

    private class _cls7
        implements android.animation.Animator.AnimatorListener
    {

        private final View val$imageView;

        public final void onAnimationCancel(Animator animator)
        {
        }

        public final void onAnimationEnd(Animator animator)
        {
            if (imageView != null)
            {
                GrooveScheduleFragment.createImageEnterAnimator(imageView).start();
            }
        }

        public final void onAnimationRepeat(Animator animator)
        {
        }

        public final void onAnimationStart(Animator animator)
        {
        }

        _cls7()
        {
            imageView = view;
            super();
        }
    }


    private class _cls8 extends Visibility
    {

        _cls8()
        {
        }
    }


    private class _cls4
        implements com.android.volley.Response.Listener
    {

        private final GrooveScheduleFragment this$0;
        private final boolean val$animateFromCategoryPage;
        private final com.google.android.calendar.groove.category.GrooveCategories.Category val$category;

        public final void onResponse(Object obj)
        {
label0:
            {
                GrooveSummaryView groovesummaryview;
                Object obj2;
                final int backgroundColor;
                {
                    Object obj1 = (Bitmap)obj;
                    obj = GrooveScheduleFragment.this;
                    double d;
                    double d1;
                    float f1;
                    float f3;
                    int ai[];
                    int i;
                    int l;
                    int k1;
                    if (((Fragment) (obj)).mHost == null)
                    {
                        obj = null;
                    } else
                    {
                        obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
                    }
                    obj = Utils.getRtlAdjustedImage(((Context) (obj)), ((Bitmap) (obj1)));
                    backgroundDrawable.setDecodeDimensions(((Bitmap) (obj)).getWidth(), ((Bitmap) (obj)).getHeight());
                    backgroundDrawable.setBounds(0, 0, ((Bitmap) (obj)).getWidth(), ((Bitmap) (obj)).getHeight());
                    obj1 = new ReusableBitmap(((Bitmap) (obj)));
                    obj1.height = ((Bitmap) (obj)).getHeight();
                    obj1.width = ((Bitmap) (obj)).getWidth();
                    backgroundDrawable.setBitmap(((ReusableBitmap) (obj1)));
                    GrooveScheduleFragment.this.backgroundColor = ((Bitmap) (obj)).getPixel(0, 0);
                    if (animateFromCategoryPage)
                    {
                        GrooveScheduleFragment.startBackgroundTransitionAnimation(backgroundFrame, category.backgroundColor, GrooveScheduleFragment.this.backgroundColor, backgroundImage);
                    } else
                    {
                        backgroundFrame.setBackgroundColor(GrooveScheduleFragment.this.backgroundColor);
                        backgroundImage.setAlpha(1.0F);
                        obj1 = backgroundDrawable;
                        obj1.parallaxFraction = (2.5F + (float)screenList.indexOf(Integer.valueOf(4))) * 0.15F;
                        ((ExtendedBitmapDrawable) (obj1)).invalidateSelf();
                    }
                    matchColorThemeToBackgroundColor(GrooveScheduleFragment.this.backgroundColor);
                    backgroundImage.setImageDrawable(backgroundDrawable);
                    backgroundImage.getLayoutParams().height = ((Bitmap) (obj)).getHeight();
                    backgroundImage.requestLayout();
                    groovesummaryview = summaryView;
                    obj1 = GrooveScheduleFragment.this;
                    backgroundColor = GrooveScheduleFragment.this.backgroundColor;
                    obj2 = Palette.from(((Bitmap) (obj)));
                    obj = GrooveScheduleFragment.FAB_TARGET;
                    if (!((android.support.v7.graphics.Palette.Builder) (obj2)).mTargets.contains(obj))
                    {
                        ((android.support.v7.graphics.Palette.Builder) (obj2)).mTargets.add(obj);
                    }
                    obj = new _cls6();
                    ((android.support.v7.graphics.Palette.Builder) (obj2)).mFilters.add(obj);
                    if (((android.support.v7.graphics.Palette.Builder) (obj2)).mBitmap == null)
                    {
                        break label0;
                    }
                    obj = ((android.support.v7.graphics.Palette.Builder) (obj2)).mBitmap;
                    d1 = -1D;
                    if (((android.support.v7.graphics.Palette.Builder) (obj2)).mResizeArea > 0)
                    {
                        backgroundColor = ((Bitmap) (obj)).getWidth() * ((Bitmap) (obj)).getHeight();
                        d = d1;
                        if (backgroundColor > ((android.support.v7.graphics.Palette.Builder) (obj2)).mResizeArea)
                        {
                            d = Math.sqrt((double)((android.support.v7.graphics.Palette.Builder) (obj2)).mResizeArea / (double)backgroundColor);
                        }
                    } else
                    {
                        d = d1;
                        if (((android.support.v7.graphics.Palette.Builder) (obj2)).mResizeMaxDimension > 0)
                        {
                            backgroundColor = Math.max(((Bitmap) (obj)).getWidth(), ((Bitmap) (obj)).getHeight());
                            d = d1;
                            if (backgroundColor > ((android.support.v7.graphics.Palette.Builder) (obj2)).mResizeMaxDimension)
                            {
                                d = (double)((android.support.v7.graphics.Palette.Builder) (obj2)).mResizeMaxDimension / (double)backgroundColor;
                            }
                        }
                    }
                    if (d > 0.0D)
                    {
                        obj = Bitmap.createScaledBitmap(((Bitmap) (obj)), (int)Math.ceil((double)((Bitmap) (obj)).getWidth() * d), (int)Math.ceil(d * (double)((Bitmap) (obj)).getHeight()), false);
                    }
                    backgroundColor = ((Bitmap) (obj)).getWidth();
                    i = ((Bitmap) (obj)).getHeight();
                    ai = new int[backgroundColor * i];
                    ((Bitmap) (obj)).getPixels(ai, 0, backgroundColor, 0, 0, backgroundColor, i);
                    backgroundColor = ((android.support.v7.graphics.Palette.Builder) (obj2)).mMaxColors;
                    if (((android.support.v7.graphics.Palette.Builder) (obj2)).mFilters.isEmpty())
                    {
                        obj1 = null;
                    } else
                    {
                        obj1 = (android.support.v7.graphics.Palette.Filter[])((android.support.v7.graphics.Palette.Builder) (obj2)).mFilters.toArray(new android.support.v7.graphics.Palette.Filter[((android.support.v7.graphics.Palette.Builder) (obj2)).mFilters.size()]);
                    }
                    obj1 = new ColorCutQuantizer(ai, backgroundColor, ((android.support.v7.graphics.Palette.Filter []) (obj1)));
                    if (obj != ((android.support.v7.graphics.Palette.Builder) (obj2)).mBitmap)
                    {
                        ((Bitmap) (obj)).recycle();
                    }
                    obj2 = new Palette(((ColorCutQuantizer) (obj1)).mQuantizedColors, ((android.support.v7.graphics.Palette.Builder) (obj2)).mTargets);
                    k1 = ((Palette) (obj2)).mTargets.size();
                    backgroundColor = 0;
                }
                if (backgroundColor < k1)
                {
                    Target target = (Target)((Palette) (obj2)).mTargets.get(backgroundColor);
                    l = target.mWeights.length;
                    float f = 0.0F;
                    for (i = 0; i < l;)
                    {
                        f3 = target.mWeights[i];
                        f1 = f;
                        if (f3 > 0.0F)
                        {
                            f1 = f + f3;
                        }
                        i++;
                        f = f1;
                    }

                    if (f != 0.0F)
                    {
                        int j = 0;
                        for (int i1 = target.mWeights.length; j < i1; j++)
                        {
                            if (target.mWeights[j] > 0.0F)
                            {
                                obj = target.mWeights;
                                obj[j] = obj[j] / f;
                            }
                        }

                    }
                    Map map = ((Palette) (obj2)).mSelectedSwatches;
                    f = 0.0F;
                    obj = null;
                    int l1 = ((Palette) (obj2)).mSwatches.size();
                    int k = 0;
                    while (k < l1) 
                    {
                        android.support.v7.graphics.Palette.Swatch swatch = (android.support.v7.graphics.Palette.Swatch)((Palette) (obj2)).mSwatches.get(k);
                        float af[] = swatch.getHsl();
                        float f2;
                        float f4;
                        float f5;
                        int j1;
                        if (af[1] >= target.mSaturationTargets[0] && af[1] <= target.mSaturationTargets[2] && af[2] >= target.mLightnessTargets[0] && af[2] <= target.mLightnessTargets[2] && !((Palette) (obj2)).mUsedColors.get(swatch.mRgb))
                        {
                            j1 = 1;
                        } else
                        {
                            j1 = 0;
                        }
                        if (j1 == 0)
                        {
                            continue;
                        }
                        af = swatch.getHsl();
                        f2 = 0.0F;
                        f4 = 0.0F;
                        if (((Palette) (obj2)).mDominantSwatch != null)
                        {
                            j1 = ((Palette) (obj2)).mDominantSwatch.mPopulation;
                        } else
                        {
                            j1 = 1;
                        }
                        if (target.mWeights[0] > 0.0F)
                        {
                            f2 = target.mWeights[0] * (1.0F - Math.abs(af[1] - target.mSaturationTargets[1]));
                        }
                        if (target.mWeights[1] > 0.0F)
                        {
                            f4 = target.mWeights[1] * (1.0F - Math.abs(af[2] - target.mLightnessTargets[1]));
                        }
                        if (target.mWeights[2] > 0.0F)
                        {
                            f5 = target.mWeights[2];
                            f5 = ((float)swatch.mPopulation / (float)j1) * f5;
                        } else
                        {
                            f5 = 0.0F;
                        }
                        f2 = f5 + (f2 + f4);
                        if (obj == null || f2 > f)
                        {
                            f = f2;
                            obj = swatch;
                        }
                        k++;
                    }
                    if (obj != null && target.mIsExclusive)
                    {
                        ((Palette) (obj2)).mUsedColors.append(((android.support.v7.graphics.Palette.Swatch) (obj)).mRgb, true);
                    }
                    map.put(target, obj);
                    backgroundColor++;
                    break MISSING_BLOCK_LABEL_488;
                } else
                {
                    ((Palette) (obj2)).mUsedColors.clear();
                    obj = Target.VIBRANT;
                    obj = (android.support.v7.graphics.Palette.Swatch)((Palette) (obj2)).mSelectedSwatches.get(obj);
                    if (obj != null)
                    {
                        backgroundColor = ((android.support.v7.graphics.Palette.Swatch) (obj)).mRgb;
                    } else
                    {
                        backgroundColor = -1;
                    }
                    if (backgroundColor == -1)
                    {
                        obj = Target.LIGHT_VIBRANT;
                        obj = (android.support.v7.graphics.Palette.Swatch)((Palette) (obj2)).mSelectedSwatches.get(obj);
                        if (obj != null)
                        {
                            backgroundColor = ((android.support.v7.graphics.Palette.Swatch) (obj)).mRgb;
                        } else
                        {
                            backgroundColor = -1;
                        }
                    }
                    groovesummaryview.setFabColor(backgroundColor);
                    return;
                }
            }
            throw new AssertionError();
        }

        _cls4()
        {
            this$0 = GrooveScheduleFragment.this;
            animateFromCategoryPage = flag;
            category = category1;
            super();
        }

        private class _cls6
            implements android.support.v7.graphics.Palette.Filter
        {

            private final int val$backgroundColor;

            public final boolean isAllowed(int i, float af[])
            {
                return android.support.v4.graphics.ColorUtils.calculateContrast(i, backgroundColor) > 1.5D;
            }

            _cls6()
            {
                backgroundColor = i;
                super();
            }
        }

    }


    private class _cls5
        implements com.android.volley.Response.ErrorListener
    {

        public final void onErrorResponse(VolleyError volleyerror)
        {
            LogUtils.e(GrooveScheduleFragment.TAG, volleyerror, "Background request failed", new Object[0]);
        }

        _cls5()
        {
        }
    }


    private class _cls1 extends PagerAdapter
    {

        private final GrooveScheduleFragment this$0;

        public final void destroyItem(ViewGroup viewgroup, int i, Object obj)
        {
            viewgroup.removeView((View)obj);
        }

        public final int getCount()
        {
            return 5;
        }

        public final int getItemPosition(Object obj)
        {
            int i = 0;
_L11:
            if (i >= screenList.size()) goto _L2; else goto _L1
_L1:
            Object obj1;
            obj1 = GrooveScheduleFragment.this;
            GrooveScheduleFragment grooveschedulefragment = GrooveScheduleFragment.this;
            int j;
            if (i >= grooveschedulefragment.screenList.size())
            {
                j = -1;
            } else
            {
                j = ((Integer)grooveschedulefragment.screenList.get(i)).intValue();
            }
            j;
            JVM INSTR tableswitch 0 4: default 80
        //                       0 110
        //                       1 118
        //                       2 126
        //                       3 134
        //                       4 142;
               goto _L3 _L4 _L5 _L6 _L7 _L8
_L3:
            obj1 = null;
_L10:
            if (obj == obj1)
            {
                return i;
            }
            continue; /* Loop/switch isn't completed */
_L4:
            obj1 = ((GrooveScheduleFragment) (obj1)).frequencyView;
            continue; /* Loop/switch isn't completed */
_L5:
            obj1 = ((GrooveScheduleFragment) (obj1)).durationView;
            continue; /* Loop/switch isn't completed */
_L6:
            obj1 = ((GrooveScheduleFragment) (obj1)).preferredTimesView;
            continue; /* Loop/switch isn't completed */
_L7:
            obj1 = ((GrooveScheduleFragment) (obj1)).belongIntegrationView;
            continue; /* Loop/switch isn't completed */
_L8:
            obj1 = ((GrooveScheduleFragment) (obj1)).summaryView;
            if (true) goto _L10; else goto _L9
_L9:
            i++;
              goto _L11
_L2:
            return -2;
        }

        public final Object instantiateItem(ViewGroup viewgroup, int i)
        {
            Object obj;
            obj = GrooveScheduleFragment.this;
            GrooveScheduleFragment grooveschedulefragment = GrooveScheduleFragment.this;
            if (i >= grooveschedulefragment.screenList.size())
            {
                i = -1;
            } else
            {
                i = ((Integer)grooveschedulefragment.screenList.get(i)).intValue();
            }
            i;
            JVM INSTR tableswitch 0 4: default 60
        //                       0 99
        //                       1 107
        //                       2 115
        //                       3 123
        //                       4 131;
               goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
            obj = null;
_L8:
            if (obj != null && ((View) (obj)).getParent() == null)
            {
                viewgroup.addView(((View) (obj)));
            }
            return obj;
_L2:
            obj = ((GrooveScheduleFragment) (obj)).frequencyView;
            continue; /* Loop/switch isn't completed */
_L3:
            obj = ((GrooveScheduleFragment) (obj)).durationView;
            continue; /* Loop/switch isn't completed */
_L4:
            obj = ((GrooveScheduleFragment) (obj)).preferredTimesView;
            continue; /* Loop/switch isn't completed */
_L5:
            obj = ((GrooveScheduleFragment) (obj)).belongIntegrationView;
            continue; /* Loop/switch isn't completed */
_L6:
            obj = ((GrooveScheduleFragment) (obj)).summaryView;
            if (true) goto _L8; else goto _L7
_L7:
        }

        public final boolean isViewFromObject(View view, Object obj)
        {
            return view == obj;
        }

        _cls1()
        {
            this$0 = GrooveScheduleFragment.this;
            super();
        }
    }


    private class _cls2
        implements android.support.v4.view.ViewPager.OnPageChangeListener
    {

        private final GrooveScheduleFragment this$0;

        public final void onPageScrollStateChanged(int i)
        {
            if (i == 0)
            {
                pagerAdapter.notifyDataSetChanged();
            }
        }

        public final void onPageScrolled(int i, float f, int j)
        {
label0:
            {
                if (backgroundDrawable != null)
                {
                    if (!isRtl)
                    {
                        break label0;
                    }
                    ListenableBitmapDrawable listenablebitmapdrawable = backgroundDrawable;
                    listenablebitmapdrawable.parallaxFraction = (4F - (float)i - f) * 0.15F;
                    listenablebitmapdrawable.invalidateSelf();
                }
                return;
            }
            ListenableBitmapDrawable listenablebitmapdrawable1 = backgroundDrawable;
            listenablebitmapdrawable1.parallaxFraction = (2.5F + (float)i + f) * 0.15F;
            listenablebitmapdrawable1.invalidateSelf();
        }

        public final void onPageSelected(int i)
        {
            GrooveScheduleFragment grooveschedulefragment = GrooveScheduleFragment.this;
            GrooveScheduleFragment grooveschedulefragment1 = GrooveScheduleFragment.this;
            if (i >= grooveschedulefragment1.screenList.size())
            {
                i = -1;
            } else
            {
                i = ((Integer)grooveschedulefragment1.screenList.get(i)).intValue();
            }
            grooveschedulefragment.requestInitialScreenFocus(i);
        }

        _cls2()
        {
            this$0 = GrooveScheduleFragment.this;
            super();
        }
    }


    private class _cls3
        implements Runnable
    {

        private final GrooveScheduleFragment this$0;
        private final int val$position;
        private final View val$view;

        public final void run()
        {
            View view1 = view;
            GrooveScheduleFragment grooveschedulefragment = GrooveScheduleFragment.this;
            view1 = view1.findViewById(GrooveScheduleFragment.getFocusViewId(position));
            if (view1 != null)
            {
                AccessibilityUtils.requestAccessibilityFocus(view1);
            }
        }

        _cls3()
        {
            this$0 = GrooveScheduleFragment.this;
            view = view1;
            position = i;
            super();
        }
    }

}
