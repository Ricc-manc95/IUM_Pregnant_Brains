// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.TintAwareDrawable;
import android.support.v4.graphics.drawable.WrappedDrawableApi21;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

// Referenced classes of package com.google.android.libraries.material.featurehighlight:
//            FeatureHighlightView, LayoutManager, OuterHighlightDrawable, FeatureHighlightContent, 
//            FeatureHighlightCallbackProvider, ViewFinder, FeatureHighlightCallback, InnerZoneDrawable

public final class FeatureHighlightFragment extends Fragment
{

    public final Runnable autoCollapseRunnable = new _cls1();
    private CharSequence bodyText;
    private int bodyTextAlignment;
    private int bodyTextAppearance;
    private int bodyTextSizeRes;
    public String callbackId;
    private int centerThresholdRes;
    public int confiningViewId;
    private CharSequence contentDescription;
    private CharSequence dismissActionText;
    private int dismissActionTextAlignment;
    private int dismissActionTextAppearance;
    public long durationInMillis;
    public FeatureHighlightCallbackProvider featureHighlightCallbackProvider;
    public FeatureHighlightView featureHighlightView;
    public boolean featureHighlightViewInitialized;
    private CharSequence headerText;
    private int headerTextAlignment;
    private int headerTextAppearance;
    private int headerTextSizeRes;
    private int horizontalOffsetRes;
    private int innerColor;
    public boolean isBeingRestored;
    private int outerColor;
    private boolean pinToClosestVerticalEdge;
    public int showState;
    private boolean swipeToDismissEnabled;
    private int targetDrawableColor;
    private int targetDrawableId;
    private int targetTextColor;
    private int targetViewTintColor;
    public boolean taskCompleteOnTap;
    public String taskTag;
    private int textVerticalGravityHint;
    private int verticalOffsetRes;
    public ViewFinder viewFinder;

    public FeatureHighlightFragment()
    {
        showState = 0;
        isBeingRestored = false;
        featureHighlightViewInitialized = false;
    }

    static boolean isBelowKitKat()
    {
        return false;
    }

    public final void onActivityCreated(Bundle bundle)
    {
        Object obj = null;
        super.onActivityCreated(bundle);
        boolean flag;
        if (bundle != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        isBeingRestored = flag;
        if (isBeingRestored && showState == 0)
        {
            removeFragment();
            return;
        }
        featureHighlightView = new FeatureHighlightView(getContext());
        bundle = featureHighlightView;
        flag = pinToClosestVerticalEdge;
        bundle.pinToClosestVerticalEdge = flag;
        ((FeatureHighlightView) (bundle)).layoutManager.pinToClosestVerticalEdge = flag;
        featureHighlightView.swipeToDismissEnabled = swipeToDismissEnabled;
        featureHighlightView.setTextVerticalGravityHint(textVerticalGravityHint);
        if (outerColor != 0)
        {
            featureHighlightView.setOuterColor(outerColor);
        }
        if (innerColor != 0)
        {
            featureHighlightView.setInnerColor(innerColor);
        }
        if (targetTextColor != 0)
        {
            featureHighlightView.setTargetTextColor(targetTextColor);
        }
        if (targetDrawableId != 0)
        {
            Object obj1 = requireContext().getResources();
            int i = targetDrawableId;
            float f;
            CharSequence charsequence;
            CharSequence charsequence1;
            int j;
            if (super.mHost == null)
            {
                bundle = null;
            } else
            {
                bundle = (FragmentActivity)super.mHost.mActivity;
            }
            bundle = ((Resources) (obj1)).getDrawable(i, bundle.getTheme());
            if (bundle != null)
            {
                if (targetDrawableColor != 0)
                {
                    bundle.mutate();
                    if (android.os.Build.VERSION.SDK_INT < 23 && !(bundle instanceof TintAwareDrawable))
                    {
                        bundle = new WrappedDrawableApi21(bundle);
                    }
                    bundle.setTint(targetDrawableColor);
                }
                obj1 = featureHighlightView;
                obj1.targetDrawable = bundle;
                if (bundle != null)
                {
                    bundle.setBounds(0, 0, bundle.getIntrinsicWidth(), bundle.getIntrinsicHeight());
                    bundle.setCallback(((android.graphics.drawable.Drawable.Callback) (obj1)));
                }
            }
        }
        if (headerTextSizeRes != 0)
        {
            f = requireContext().getResources().getDimension(headerTextSizeRes) / requireContext().getResources().getDisplayMetrics().density;
            featureHighlightView.setHeaderTextSize(f);
        }
        if (headerTextAppearance != 0)
        {
            featureHighlightView.setHeaderTextAppearance(headerTextAppearance);
        }
        featureHighlightView.setHeaderTextAlignment(headerTextAlignment);
        if (bodyTextSizeRes != 0)
        {
            f = requireContext().getResources().getDimension(bodyTextSizeRes) / requireContext().getResources().getDisplayMetrics().density;
            featureHighlightView.setBodyTextSize(f);
        }
        if (bodyTextAppearance != 0)
        {
            featureHighlightView.setBodyTextAppearance(bodyTextAppearance);
        }
        featureHighlightView.setBodyTextAlignment(bodyTextAlignment);
        if (dismissActionTextAppearance != 0)
        {
            featureHighlightView.setDismissActionTextAppearance(dismissActionTextAppearance);
        }
        featureHighlightView.setDismissActionTextAlignment(dismissActionTextAlignment);
        if (verticalOffsetRes != 0 && horizontalOffsetRes != 0)
        {
            i = requireContext().getResources().getDimensionPixelOffset(verticalOffsetRes);
            j = requireContext().getResources().getDimensionPixelOffset(horizontalOffsetRes);
            bundle = featureHighlightView.outerHighlight;
            bundle.offsetVerticalOffsetPx = i;
            bundle.offsetHorizontalOffsetPx = j;
        }
        if (centerThresholdRes != 0)
        {
            i = requireContext().getResources().getDimensionPixelOffset(centerThresholdRes);
            featureHighlightView.setCenterThreshold(i);
        }
        if (targetViewTintColor != 0)
        {
            featureHighlightView.setTargetViewTintColor(targetViewTintColor);
        }
        bundle = featureHighlightView;
        obj1 = headerText;
        charsequence = bodyText;
        charsequence1 = dismissActionText;
        ((FeatureHighlightView) (bundle)).content.setText(((CharSequence) (obj1)), charsequence, charsequence1);
        featureHighlightView.setContentDescription(contentDescription);
        featureHighlightView.setTag(0x7f10001a, this);
        if (super.mHost == null)
        {
            bundle = obj;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        ((ViewGroup)bundle.findViewById(0x1020002)).addView(featureHighlightView);
    }

    public final void onAttach(Activity activity)
    {
        super.onAttach(activity);
        Fragment fragment = super.mParentFragment;
        if (fragment instanceof FeatureHighlightCallbackProvider)
        {
            featureHighlightCallbackProvider = (FeatureHighlightCallbackProvider)fragment;
        } else
        if (activity instanceof FeatureHighlightCallbackProvider)
        {
            featureHighlightCallbackProvider = (FeatureHighlightCallbackProvider)activity;
            return;
        }
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        Bundle bundle1 = getArguments();
        if (bundle1 != null)
        {
            viewFinder = (ViewFinder)bundle1.getParcelable("fh_view_finder");
            targetViewTintColor = bundle1.getInt("fh_target_view_tint_color");
            confiningViewId = bundle1.getInt("fh_confining_view_id");
            headerText = bundle1.getCharSequence("fh_header_text");
            headerTextSizeRes = bundle1.getInt("fh_header_text_size_res");
            headerTextAppearance = bundle1.getInt("fh_header_text_appearance");
            headerTextAlignment = bundle1.getInt("fh_header_text_alignment");
            bodyText = bundle1.getCharSequence("fh_body_text");
            bodyTextSizeRes = bundle1.getInt("fh_body_text_size_res");
            bodyTextAppearance = bundle1.getInt("fh_body_text_appearance");
            bodyTextAlignment = bundle1.getInt("fh_body_text_alignment");
            dismissActionText = bundle1.getCharSequence("fh_dismiss_action_text");
            dismissActionTextAppearance = bundle1.getInt("fh_dismiss_action_text_appearance");
            dismissActionTextAlignment = bundle1.getInt("fh_dismiss_action_text_alignment");
            outerColor = bundle1.getInt("fh_outer_color");
            innerColor = bundle1.getInt("fh_inner_color");
            targetTextColor = bundle1.getInt("fh_target_text_color");
            targetDrawableId = bundle1.getInt("fh_target_drawable");
            targetDrawableColor = bundle1.getInt("fh_target_drawable_color");
            callbackId = bundle1.getString("fh_callback_id");
            taskTag = bundle1.getString("fh_task_tag");
            verticalOffsetRes = bundle1.getInt("fh_vertical_offset_res");
            horizontalOffsetRes = bundle1.getInt("fh_horizontal_offset_res");
            centerThresholdRes = bundle1.getInt("fh_center_threshold_res");
            taskCompleteOnTap = bundle1.getBoolean("fh_task_complete_on_tap");
            durationInMillis = bundle1.getLong("fh_duration");
            pinToClosestVerticalEdge = bundle1.getBoolean("fh_pin_to_closest_vertical_edge");
            swipeToDismissEnabled = bundle1.getBoolean("fh_swipe_to_dismiss_enabled");
            textVerticalGravityHint = bundle1.getInt("fh_text_vertical_gravity_hint");
            contentDescription = bundle1.getCharSequence("fh_content_description");
            if (bundle != null)
            {
                int i = bundle.getInt("showState");
                switch (i)
                {
                default:
                    throw new IllegalArgumentException("Unrecognised show state.");

                case 0: // '\0'
                case 1: // '\001'
                    showState = i;
                    break;
                }
                return;
            }
        }
    }

    public final void onDestroy()
    {
        if (featureHighlightView != null)
        {
            featureHighlightView.setTag(0x7f10001a, null);
            FragmentActivity fragmentactivity;
            if (super.mHost == null)
            {
                fragmentactivity = null;
            } else
            {
                fragmentactivity = (FragmentActivity)super.mHost.mActivity;
            }
            ((ViewGroup)fragmentactivity.findViewById(0x1020002)).removeView(featureHighlightView);
            featureHighlightView = null;
        }
        super.onDestroy();
    }

    public final void onDetach()
    {
        super.onDetach();
        FeatureHighlightCallback featurehighlightcallback;
        if (featureHighlightCallbackProvider != null)
        {
            featurehighlightcallback = featureHighlightCallbackProvider.getFeatureHighlightCallback(callbackId);
        } else
        {
            featurehighlightcallback = null;
        }
        if (featurehighlightcallback != null)
        {
            featurehighlightcallback.onDetached$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0();
        }
        featureHighlightCallbackProvider = null;
    }

    final void onDismiss()
    {
        if (showState == 1 && featureHighlightView != null)
        {
            Object obj;
            _cls5 _lcls5;
            if (featureHighlightCallbackProvider != null)
            {
                obj = featureHighlightCallbackProvider.getFeatureHighlightCallback(callbackId);
            } else
            {
                obj = null;
            }
            if (obj != null)
            {
                ((FeatureHighlightCallback) (obj)).onDismiss$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0();
            }
            showState = 0;
            if (featureHighlightCallbackProvider != null)
            {
                featureHighlightCallbackProvider.getFeatureHighlightCallback(callbackId);
            }
            obj = featureHighlightView;
            _lcls5 = new _cls5();
            if (!((FeatureHighlightView) (obj)).hiding)
            {
                ObjectAnimator objectanimator = ObjectAnimator.ofFloat(((FeatureHighlightView) (obj)).content.asView(), "alpha", new float[] {
                    0.0F
                }).setDuration(200L);
                objectanimator.setInterpolator(com.google.android.libraries.material.animation.MaterialInterpolators.Interpolators.fastOutLinearIn);
                float f = ((FeatureHighlightView) (obj)).targetBounds.exactCenterX();
                float f1 = ((FeatureHighlightView) (obj)).outerHighlight.centerX;
                float f2 = ((FeatureHighlightView) (obj)).targetBounds.exactCenterY();
                float f3 = ((FeatureHighlightView) (obj)).outerHighlight.centerY;
                Object obj1 = ((FeatureHighlightView) (obj)).outerHighlight;
                Object obj2 = PropertyValuesHolder.ofFloat("scale", new float[] {
                    ((OuterHighlightDrawable) (obj1)).getScale(), 0.0F
                });
                Object obj3 = PropertyValuesHolder.ofInt("alpha", new int[] {
                    ((OuterHighlightDrawable) (obj1)).getAlpha(), 0
                });
                obj1 = ObjectAnimator.ofPropertyValuesHolder(obj1, new PropertyValuesHolder[] {
                    obj2, PropertyValuesHolder.ofFloat("translationX", new float[] {
                        ((OuterHighlightDrawable) (obj1)).getTranslationX(), f - f1
                    }), PropertyValuesHolder.ofFloat("translationY", new float[] {
                        ((OuterHighlightDrawable) (obj1)).getTranslationY(), f2 - f3
                    }), obj3
                });
                ((Animator) (obj1)).setInterpolator(com.google.android.libraries.material.animation.MaterialInterpolators.Interpolators.fastOutLinearIn);
                obj1 = ((Animator) (obj1)).setDuration(200L);
                obj2 = ((FeatureHighlightView) (obj)).innerZone.createDismissAnimation();
                obj3 = new AnimatorSet();
                ((AnimatorSet) (obj3)).playTogether(new Animator[] {
                    objectanimator, obj1, obj2
                });
                ((AnimatorSet) (obj3)).addListener(new FeatureHighlightView._cls8(((FeatureHighlightView) (obj)), _lcls5));
                if (((FeatureHighlightView) (obj)).currentAnimation != null)
                {
                    ((FeatureHighlightView) (obj)).currentAnimation.cancel();
                }
                if (obj3 != null)
                {
                    obj.currentAnimation = ((Animator) (obj3));
                    ((FeatureHighlightView) (obj)).currentAnimation.start();
                }
            }
        }
    }

    public final void onPause()
    {
        super.onPause();
        featureHighlightView.removeCallbacks(autoCollapseRunnable);
    }

    public final void onResume()
    {
        super.onResume();
        if (featureHighlightView != null)
        {
            if (durationInMillis > 0L)
            {
                featureHighlightView.postDelayed(autoCollapseRunnable, durationInMillis);
            }
            if (!featureHighlightViewInitialized)
            {
                ViewCompat.postOnAnimation(featureHighlightView, new _cls2());
            }
        }
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putInt("showState", showState);
    }

    final void removeFragment()
    {
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        if (obj != null)
        {
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            if (!((FragmentActivity) (obj)).isFinishing())
            {
                boolean flag;
                if (super.mHost != null && super.mAdded)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag && !super.mRemoving)
                {
                    if ((obj = super.mFragmentManager) != null)
                    {
                        ((FragmentManager) (obj)).beginTransaction().remove(this).commitAllowingStateLoss();
                        return;
                    }
                }
            }
        }
    }

    private class _cls1
        implements Runnable
    {

        private final FeatureHighlightFragment this$0;

        public final void run()
        {
            onDismiss();
        }

        _cls1()
        {
            this$0 = FeatureHighlightFragment.this;
            super();
        }
    }


    private class _cls5
        implements Runnable
    {

        private final FeatureHighlightFragment this$0;

        public final void run()
        {
            removeFragment();
        }

        _cls5()
        {
            this$0 = FeatureHighlightFragment.this;
            super();
        }
    }


    private class _cls2
        implements Runnable
    {

        private final FeatureHighlightFragment this$0;

        public final void run()
        {
            if (featureHighlightView == null) goto _L2; else goto _L1
_L1:
            FeatureHighlightFragment featurehighlightfragment1;
            featureHighlightView.bringToFront();
            FeatureHighlightFragment featurehighlightfragment = FeatureHighlightFragment.this;
            if (FeatureHighlightFragment.isBelowKitKat())
            {
                View view = (View)featureHighlightView.getParent();
                view.requestLayout();
                view.invalidate();
            }
            featurehighlightfragment1 = FeatureHighlightFragment.this;
            if (featurehighlightfragment1.featureHighlightViewInitialized) goto _L2; else goto _L3
_L3:
            Object obj3;
            featurehighlightfragment1.featureHighlightViewInitialized = true;
            FeatureHighlightCallback featurehighlightcallback;
            if (featurehighlightfragment1.featureHighlightCallbackProvider != null)
            {
                featurehighlightcallback = featurehighlightfragment1.featureHighlightCallbackProvider.getFeatureHighlightCallback(featurehighlightfragment1.callbackId);
            } else
            {
                featurehighlightcallback = null;
            }
            if (featurehighlightcallback != null)
            {
                featurehighlightcallback.onAttached$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0();
            }
            if (((Fragment) (featurehighlightfragment1)).mHost == null)
            {
                obj3 = null;
            } else
            {
                obj3 = (FragmentActivity)((Fragment) (featurehighlightfragment1)).mHost.mActivity;
            }
            if (obj3 == null)
            {
                obj3 = null;
            } else
            {
                ViewFinder viewfinder = featurehighlightfragment1.viewFinder;
                Object obj;
                if (featurehighlightfragment1.confiningViewId == -1)
                {
                    obj = null;
                } else
                {
                    if (((Fragment) (featurehighlightfragment1)).mHost == null)
                    {
                        obj = null;
                    } else
                    {
                        obj = (FragmentActivity)((Fragment) (featurehighlightfragment1)).mHost.mActivity;
                    }
                    if (obj == null)
                    {
                        obj = null;
                    } else
                    {
                        obj = ((Activity) (obj)).findViewById(featurehighlightfragment1.confiningViewId);
                    }
                }
                obj3 = viewfinder.find(((Activity) (obj3)), ((View) (obj)));
            }
_L8:
            if (obj3 != null) goto _L5; else goto _L4
_L4:
            if (featurehighlightcallback != null)
            {
                featurehighlightcallback.onViewNotFound$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0();
            }
            featurehighlightfragment1.removeFragment();
_L2:
            return;
_L5:
            Object obj2 = featurehighlightfragment1.featureHighlightView;
            Object obj1;
            if (featurehighlightfragment1.confiningViewId == -1)
            {
                obj1 = null;
            } else
            {
                if (((Fragment) (featurehighlightfragment1)).mHost == null)
                {
                    obj1 = null;
                } else
                {
                    obj1 = (FragmentActivity)((Fragment) (featurehighlightfragment1)).mHost.mActivity;
                }
                if (obj1 == null)
                {
                    obj1 = null;
                } else
                {
                    obj1 = ((Activity) (obj1)).findViewById(featurehighlightfragment1.confiningViewId);
                }
            }
            obj2.confiningView = ((View) (obj1));
            obj1 = featurehighlightfragment1.featureHighlightView;
            obj2 = featurehighlightfragment1. new _cls3();
            ((FeatureHighlightView) (obj1)).content.setCallback(((FeatureHighlightView.InteractionCallback) (obj2)));
            obj1.callback = ((FeatureHighlightView.InteractionCallback) (obj2));
            if (featurehighlightfragment1.showState != 1) goto _L2; else goto _L6
_L6:
            if (featurehighlightfragment1.isBeingRestored)
            {
                obj1 = featurehighlightfragment1.featureHighlightView;
                ((FeatureHighlightView) (obj1)).setupForTarget(((View) (obj3)));
                ((FeatureHighlightView) (obj1)).addOnLayoutChangeListener(new FeatureHighlightView._cls5(((FeatureHighlightView) (obj1)), null));
                ((FeatureHighlightView) (obj1)).requestLayout();
                return;
            }
            FeatureHighlightView featurehighlightview = featurehighlightfragment1.featureHighlightView;
            _cls4 _lcls4 = featurehighlightfragment1. new _cls4();
            featurehighlightview.setupForTarget(((View) (obj3)));
            featurehighlightview.addOnLayoutChangeListener(new FeatureHighlightView._cls4(featurehighlightview, _lcls4));
            featurehighlightview.requestLayout();
            return;
            if (true) goto _L8; else goto _L7
_L7:
        }

        _cls2()
        {
            this$0 = FeatureHighlightFragment.this;
            super();
        }

        private class _cls3 extends FeatureHighlightView.InteractionCallback
        {

            private final FeatureHighlightFragment this$0;

            public final void onDismiss()
            {
                FeatureHighlightFragment.this.onDismiss();
            }

            public final void onSwipeCompleted$51662RJ4E9NMIP1FEPKMATPF9LNN8QBFDP2NCPBEEGTIILG_0()
            {
                if (durationInMillis > 0L)
                {
                    featureHighlightView.postDelayed(autoCollapseRunnable, durationInMillis);
                }
            }

            public final void onSwipeStarted$51662RJ4E9NMIP1FEPKMATPF9LNN8QBFDP2NCPBEEGTIILG_0()
            {
                if (durationInMillis > 0L)
                {
                    featureHighlightView.removeCallbacks(autoCollapseRunnable);
                }
            }

            public final void onTargetViewClick()
            {
                if (taskCompleteOnTap)
                {
                    Object obj1 = FeatureHighlightFragment.this;
                    Object obj = taskTag;
                    Object obj2 = ((FeatureHighlightFragment) (obj1)).taskTag;
                    boolean flag;
                    if (obj2 == null)
                    {
                        if (obj == null)
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                    } else
                    {
                        flag = obj2.equals(obj);
                    }
                    if (flag && ((FeatureHighlightFragment) (obj1)).showState == 1 && ((FeatureHighlightFragment) (obj1)).featureHighlightView != null)
                    {
                        if (((FeatureHighlightFragment) (obj1)).featureHighlightCallbackProvider != null)
                        {
                            obj = ((FeatureHighlightFragment) (obj1)).featureHighlightCallbackProvider.getFeatureHighlightCallback(((FeatureHighlightFragment) (obj1)).callbackId);
                        } else
                        {
                            obj = null;
                        }
                        if (obj != null)
                        {
                            ((FeatureHighlightCallback) (obj)).onTaskComplete$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0();
                        }
                        obj1.showState = 0;
                        if (((FeatureHighlightFragment) (obj1)).featureHighlightCallbackProvider != null)
                        {
                            ((FeatureHighlightFragment) (obj1)).featureHighlightCallbackProvider.getFeatureHighlightCallback(((FeatureHighlightFragment) (obj1)).callbackId);
                        }
                        obj = ((FeatureHighlightFragment) (obj1)).featureHighlightView;
                        obj1 = ((_cls6) (obj1)). new _cls6();
                        if (!((FeatureHighlightView) (obj)).hiding)
                        {
                            obj2 = ObjectAnimator.ofFloat(((FeatureHighlightView) (obj)).content.asView(), "alpha", new float[] {
                                0.0F
                            }).setDuration(200L);
                            ((Animator) (obj2)).setInterpolator(com.google.android.libraries.material.animation.MaterialInterpolators.Interpolators.fastOutLinearIn);
                            Object obj3 = ((FeatureHighlightView) (obj)).outerHighlight;
                            obj3 = ObjectAnimator.ofPropertyValuesHolder(obj3, new PropertyValuesHolder[] {
                                PropertyValuesHolder.ofFloat("scale", new float[] {
                                    ((OuterHighlightDrawable) (obj3)).getScale(), 1.125F
                                }), PropertyValuesHolder.ofInt("alpha", new int[] {
                                    ((OuterHighlightDrawable) (obj3)).getAlpha(), 0
                                })
                            });
                            ((Animator) (obj3)).setInterpolator(com.google.android.libraries.material.animation.MaterialInterpolators.Interpolators.fastOutLinearIn);
                            obj3 = ((Animator) (obj3)).setDuration(200L);
                            Animator animator = ((FeatureHighlightView) (obj)).innerZone.createDismissAnimation();
                            AnimatorSet animatorset = new AnimatorSet();
                            animatorset.playTogether(new Animator[] {
                                obj2, obj3, animator
                            });
                            animatorset.addListener(new FeatureHighlightView._cls8(((FeatureHighlightView) (obj)), ((Runnable) (obj1))));
                            if (((FeatureHighlightView) (obj)).currentAnimation != null)
                            {
                                ((FeatureHighlightView) (obj)).currentAnimation.cancel();
                            }
                            if (animatorset != null)
                            {
                                obj.currentAnimation = animatorset;
                                ((FeatureHighlightView) (obj)).currentAnimation.start();
                            }
                        }
                    }
                }
            }

            _cls3()
            {
                this$0 = FeatureHighlightFragment.this;
                super();
            }

            private class _cls6
                implements Runnable
            {

                private final FeatureHighlightFragment this$0;

                public final void run()
                {
                    removeFragment();
                }

                _cls6()
                {
                    this$0 = FeatureHighlightFragment.this;
                    super();
                }
            }

        }


        private class _cls4
            implements Runnable
        {

            private final FeatureHighlightFragment this$0;

            public final void run()
            {
                Object obj = FeatureHighlightFragment.this;
                if (((FeatureHighlightFragment) (obj)).featureHighlightCallbackProvider != null)
                {
                    obj = ((FeatureHighlightFragment) (obj)).featureHighlightCallbackProvider.getFeatureHighlightCallback(((FeatureHighlightFragment) (obj)).callbackId);
                } else
                {
                    obj = null;
                }
                if (obj != null)
                {
                    ((FeatureHighlightCallback) (obj)).onShow$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0();
                }
            }

            _cls4()
            {
                this$0 = FeatureHighlightFragment.this;
                super();
            }
        }

    }

}
