// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.TimelineAdapterViewHolder;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.AnimationUtil;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.AnimatorFuture;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.AnimatorSetFuture;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.Rect;
import com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype.CalendarViewType;
import com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype.EventViewPositionHelper;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.base.Platform;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout:
//            LayoutUpdater, ViewLayoutParamsImpl, LayoutItemParams

public class LayoutUpdaterImpl
    implements LayoutUpdater
{
    static final class LayoutParams extends android.support.v7.widget.RecyclerView.LayoutParams
    {

        public final AccessibilityVirtualView.Builder accessibilityBuilder = new AutoValue_AccessibilityVirtualView.Builder();
        public int index;
        public int zOrder;

        LayoutParams(int i, int j)
        {
            super(-2, -2);
        }
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/timeline/alternate/view/impl/layout/LayoutUpdaterImpl);
    private final Set animatedToAlternateView = new HashSet();
    public ListenableFuture animator;
    private final Provider animatorSetFutureProvider;
    private final List animators = new ArrayList();
    public int expectedViewCount;
    private final ObservableReference fullyLoaded;
    private final ObservableReference isA11yEnabled;
    private final com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.IsVisibleSupplier isVisibleSupplier;
    public final List lastVirtualViews = new ArrayList();
    private final Set placedItems = new HashSet();
    private android.support.v7.widget.RecyclerView.Recycler recycler;
    public final RecyclerView recyclerView;
    public LayoutParams sortedViews[];
    public boolean sortedViewsValid;
    private final SparseArray viewCache = new SparseArray(50);
    private final ViewLayoutParamsImpl viewLayoutParamsImpl = new ViewLayoutParamsImpl();
    private final List virtualViewsTmp = new ArrayList();

    LayoutUpdaterImpl(final RecyclerView recyclerView, Provider provider, ObservableReference observablereference, ObservableReference observablereference1, com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.IsVisibleSupplier isvisiblesupplier)
    {
        Object obj = new Object();
        class .Lambda._cls0
            implements android.support.v7.widget.RecyclerView.ChildDrawingOrderCallback
        {

            private final LayoutUpdaterImpl arg$1;

            public final int onGetChildDrawingOrder(int i, int j)
            {
                LayoutUpdaterImpl layoutupdaterimpl;
                layoutupdaterimpl = arg$1;
                if (i != layoutupdaterimpl.expectedViewCount)
                {
                    LogUtils.wtf(LayoutUpdaterImpl.TAG, "Unexpected child count %d, %d", new Object[] {
                        Integer.valueOf(layoutupdaterimpl.expectedViewCount), Integer.valueOf(i)
                    });
                    layoutupdaterimpl.sortedViewsValid = false;
                }
                if (!layoutupdaterimpl.sortedViewsValid)
                {
                    if (layoutupdaterimpl.sortedViews.length < i)
                    {
                        layoutupdaterimpl.sortedViews = new LayoutParams[i];
                    }
                    android.support.v7.widget.RecyclerView.LayoutManager layoutmanager = layoutupdaterimpl.recyclerView.mLayout;
                    for (int k = 0; k < i; k++)
                    {
                        layoutupdaterimpl.sortedViews[k] = (LayoutParams)layoutmanager.getChildAt(k).getLayoutParams();
                        layoutupdaterimpl.sortedViews[k].index = k;
                    }

                    class .Lambda._cls4
                        implements Comparator
                    {

                        public static final Comparator $instance = new .Lambda._cls4();

                        public final int compare(Object obj1, Object obj2)
                        {
                            return LayoutUpdaterImpl.lambda$onGetChildDrawingOrder$2$LayoutUpdaterImpl((LayoutParams)obj1, (LayoutParams)obj2);
                        }


                        private .Lambda._cls4()
                        {
                        }
                    }

                    Arrays.sort(layoutupdaterimpl.sortedViews, 0, i, .Lambda._cls4..instance);
                    layoutupdaterimpl.sortedViewsValid = true;
                }
                if (j < layoutupdaterimpl.sortedViews.length) goto _L2; else goto _L1
_L1:
                LogUtils.wtf(LayoutUpdaterImpl.TAG, "Child index out of bounds %d, %d", new Object[] {
                    Integer.valueOf(j), Integer.valueOf(layoutupdaterimpl.sortedViews.length)
                });
                j = 0;
_L4:
                return j;
_L2:
                int l;
                l = layoutupdaterimpl.sortedViews[j].index;
                if (l < 0)
                {
                    break; /* Loop/switch isn't completed */
                }
                j = l;
                if (l < i) goto _L4; else goto _L3
_L3:
                LogUtils.wtf(LayoutUpdaterImpl.TAG, "Index out of bounds %d, %d", new Object[] {
                    Integer.valueOf(l), Integer.valueOf(i)
                });
                return Math.min(Math.max(0, l), i - 1);
            }

            .Lambda._cls0()
            {
                arg$1 = LayoutUpdaterImpl.this;
            }
        }

        class .Lambda._cls1
            implements android.support.v7.widget.RecyclerView.RecyclerListener
        {

            public static final android.support.v7.widget.RecyclerView.RecyclerListener $instance = new .Lambda._cls1();

            public final void onViewRecycled(android.support.v7.widget.RecyclerView.ViewHolder viewholder)
            {
                LayoutUpdaterImpl.lambda$new$0$LayoutUpdaterImpl(viewholder);
            }


            private .Lambda._cls1()
            {
            }
        }

        if (obj == null)
        {
            obj = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            obj = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(obj);
        }
        animator = ((ListenableFuture) (obj));
        sortedViewsValid = false;
        sortedViews = new LayoutParams[64];
        this.recyclerView = recyclerView;
        animatorSetFutureProvider = provider;
        isA11yEnabled = observablereference;
        fullyLoaded = observablereference1;
        isVisibleSupplier = isvisiblesupplier;
        recyclerView.setChildDrawingOrderCallback(new .Lambda._cls0());
        recyclerView.mRecyclerListener = .Lambda._cls1..instance;
        recyclerView.setOnHierarchyChangeListener(new _cls1());
    }

    private final void addAnimator(Animator animator1, LayoutItemParams layoutitemparams)
    {
        long l;
        if (layoutitemparams != null && layoutitemparams.hasAnimationDuration)
        {
            l = layoutitemparams.animationDurationMs;
        } else
        {
            l = 300L;
        }
        animator1.setDuration(l);
        animators.add(animator1);
    }

    static final void lambda$new$0$LayoutUpdaterImpl(android.support.v7.widget.RecyclerView.ViewHolder viewholder)
    {
        ((TimelineAdapterViewHolder)viewholder).onRecycled();
    }

    static final int lambda$onGetChildDrawingOrder$2$LayoutUpdaterImpl(LayoutParams layoutparams, LayoutParams layoutparams1)
    {
        if (layoutparams.zOrder > layoutparams1.zOrder)
        {
            return 1;
        }
        return layoutparams.zOrder >= layoutparams1.zOrder ? 0 : -1;
    }

    public final void addItem(LayoutItemParams layoutitemparams)
    {
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        k1 = layoutitemparams.position;
        Rect rect = layoutitemparams.rect;
        k = rect.left;
        l = rect.top;
        i1 = rect.right;
        j1 = rect.bottom;
        if (placedItems.add(Integer.valueOf(k1))) goto _L2; else goto _L1
_L1:
        LogUtils.e(TAG, "already added %d", new Object[] {
            Integer.valueOf(k1)
        });
_L5:
        return;
_L2:
        Object obj1;
        Object obj2;
        int i;
        boolean flag;
        try
        {
            obj2 = CalendarViewType.forPosition(k1);
        }
        // Misplaced declaration of an exception variable
        catch (LayoutItemParams layoutitemparams)
        {
            LogUtils.wtf(TAG, layoutitemparams, "bad position: %d", new Object[] {
                Integer.valueOf(k1)
            });
            return;
        }
        obj1 = recyclerView.mLayout;
        if (layoutitemparams.hasZOrder)
        {
            i = layoutitemparams.zOrder;
        } else
        {
            i = ((CalendarViewType) (obj2)).defaultZOrder;
        }
        if (((Boolean)fullyLoaded.get()).booleanValue() && layoutitemparams.animate)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (layoutitemparams.type != LayoutItemParams.Type.VIRTUAL_ONLY) goto _L4; else goto _L3
_L3:
        obj2 = (new AutoValue_AccessibilityVirtualView.Builder()).contentDescription(Platform.nullToEmpty(layoutitemparams.contentDescription));
_L6:
        if (((Boolean)isA11yEnabled.get()).booleanValue() && layoutitemparams.type != LayoutItemParams.Type.DISPLAY_ONLY)
        {
            obj1 = ((AccessibilityVirtualView.Builder) (obj2)).id(k1).actionHandler(layoutitemparams.virtualActionHandler).zOrder(i).type(layoutitemparams.type);
            class .Lambda._cls2
                implements Runnable
            {

                private final View arg$1;

                public final void run()
                {
                    arg$1.callOnClick();
                }

            .Lambda._cls2(View view)
            {
                arg$1 = view;
            }
            }

            float f;
            float f1;
            Object obj;
            Object obj3;
            AccessibilityVirtualView.Builder builder;
            int j;
            boolean flag1;
            if (layoutitemparams.hasParentId)
            {
                obj = Integer.valueOf(layoutitemparams.parentId);
            } else
            {
                obj = null;
            }
            obj1 = ((AccessibilityVirtualView.Builder) (obj1)).parentId(((Integer) (obj))).canScrollForward(Boolean.valueOf(layoutitemparams.canScrollForward)).canScrollBackward(Boolean.valueOf(layoutitemparams.canScrollBackward));
            if (layoutitemparams.traversalBefore >= 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                obj = Integer.valueOf(layoutitemparams.traversalBefore);
            } else
            {
                obj = null;
            }
            obj1 = ((AccessibilityVirtualView.Builder) (obj1)).traversalBefore(((Integer) (obj)));
            if (layoutitemparams.traversalAfter >= 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                obj = Integer.valueOf(layoutitemparams.traversalAfter);
            } else
            {
                obj = null;
            }
            ((AccessibilityVirtualView.Builder) (obj1)).traversalAfter(((Integer) (obj)));
            if (layoutitemparams.hasVirtualRect)
            {
                layoutitemparams = layoutitemparams.virtualRect;
                ((AccessibilityVirtualView.Builder) (obj2)).left(((Rect) (layoutitemparams)).left).top(((Rect) (layoutitemparams)).top).right(((Rect) (layoutitemparams)).right).bottom(((Rect) (layoutitemparams)).bottom);
            } else
            {
                ((AccessibilityVirtualView.Builder) (obj2)).left(k).top(l).right(i1).bottom(j1);
            }
            virtualViewsTmp.add(obj2);
            return;
        }
        if (true) goto _L5; else goto _L4
_L4:
        obj = (View)viewCache.get(k1);
        if (obj != null || !flag)
        {
            break MISSING_BLOCK_LABEL_1333;
        }
        if (obj2 != CalendarViewType.EVENT)
        {
            j = -1;
        } else
        {
            j = EventViewPositionHelper.toAlternatePosition(k1);
        }
        if (j == -1)
        {
            break MISSING_BLOCK_LABEL_1333;
        }
        obj2 = (View)viewCache.get(j);
        if (obj2 != null)
        {
            animatedToAlternateView.add(obj2);
        }
_L7:
        if (obj == null)
        {
            obj3 = recycler.tryGetViewHolderForPositionByDeadline(k1, false, 0x7fffffffffffffffL).itemView;
            obj = (LayoutParams)((View) (obj3)).getLayoutParams();
            builder = ((LayoutParams) (obj)).accessibilityBuilder;
            obj.zOrder = i;
            obj.width = i1 - k;
            obj.height = j1 - l;
            ((android.support.v7.widget.RecyclerView.LayoutManager) (obj1)).addViewInt(((View) (obj3)), -1, false);
            ((android.support.v7.widget.RecyclerView.LayoutManager) (obj1)).measureChildWithMargins(((View) (obj3)), 0, 0);
            android.support.v7.widget.RecyclerView.LayoutManager.layoutDecorated(((View) (obj3)), k, l, i1, j1);
            obj = obj3;
            obj1 = builder;
            if (flag)
            {
                if (obj2 != null)
                {
                    ((View) (obj3)).setLeft(((View) (obj2)).getLeft());
                    ((View) (obj3)).setTop(((View) (obj2)).getTop());
                    ((View) (obj3)).setRight(((View) (obj2)).getRight());
                    ((View) (obj3)).setBottom(((View) (obj2)).getBottom());
                    ((View) (obj3)).setZ(((View) (obj2)).getZ());
                    addAnimator(AnimationUtil.createMoveAnimation(((View) (obj3)), k, l, i1, j1), layoutitemparams);
                    obj1 = builder;
                    obj = obj3;
                } else
                {
                    ((View) (obj3)).setAlpha(0.0F);
                    obj = ValueAnimator.ofFloat(new float[] {
                        0.0F, 1.0F
                    });
                    ((ValueAnimator) (obj)).addUpdateListener(new com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.AnimationUtil..Lambda._cls2(((View) (obj3))));
                    ((ValueAnimator) (obj)).addListener(new com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.AnimationUtil._cls3(((View) (obj3))));
                    addAnimator(((Animator) (obj)), layoutitemparams);
                    obj = obj3;
                    obj1 = builder;
                }
            }
        } else
        {
            viewCache.remove(k1);
            obj2 = (LayoutParams)((View) (obj)).getLayoutParams();
            obj1 = ((LayoutParams) (obj2)).accessibilityBuilder;
            if (((LayoutParams) (obj2)).zOrder != i)
            {
                obj2.zOrder = i;
                sortedViewsValid = false;
            }
            if (((View) (obj)).getAlpha() != 1.0F)
            {
                ((View) (obj)).setAlpha(1.0F);
            }
            if (((View) (obj)).getLeft() != k || ((View) (obj)).getTop() != l || ((View) (obj)).getRight() != i1 || ((View) (obj)).getBottom() != j1)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                if (flag)
                {
                    addAnimator(AnimationUtil.createMoveAnimation(((View) (obj)), k, l, i1, j1), layoutitemparams);
                } else
                if (((View) (obj)).getWidth() == i1 - k && ((View) (obj)).getHeight() == j1 - l)
                {
                    ((View) (obj)).offsetLeftAndRight(k - ((View) (obj)).getLeft());
                    ((View) (obj)).offsetTopAndBottom(j1 - ((View) (obj)).getBottom());
                } else
                {
                    android.support.v7.widget.RecyclerView.LayoutManager.layoutDecorated(((View) (obj)), k, l, i1, j1);
                }
            }
        }
        if (layoutitemparams.hasElevation)
        {
            f = layoutitemparams.elevation;
        } else
        {
            f = 0.0F;
        }
        if (((View) (obj)).getZ() != f)
        {
            if (flag)
            {
                f = layoutitemparams.elevation;
                f1 = ((View) (obj)).getZ();
                obj2 = ValueAnimator.ofFloat(new float[] {
                    0.0F, 1.0F
                });
                ((ValueAnimator) (obj2)).addUpdateListener(new com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.AnimationUtil..Lambda._cls3(f1, f, ((View) (obj))));
                ((ValueAnimator) (obj2)).addListener(new com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.AnimationUtil._cls4(((View) (obj)), f));
                addAnimator(((Animator) (obj2)), layoutitemparams);
            } else
            {
                if (layoutitemparams.hasElevation)
                {
                    f = layoutitemparams.elevation;
                } else
                {
                    f = 0.0F;
                }
                ((View) (obj)).setZ(f);
            }
        }
        obj3 = (TimelineAdapterViewHolder)recyclerView.getChildViewHolder(((View) (obj)));
        viewLayoutParamsImpl.layoutItemParams = layoutitemparams;
        ((TimelineAdapterViewHolder) (obj3)).onLayoutUpdate(viewLayoutParamsImpl);
        if (layoutitemparams.hasClip)
        {
            obj2 = layoutitemparams.clipRect;
        } else
        {
            obj2 = null;
        }
        ((TimelineAdapterViewHolder) (obj3)).setClipRect(((Rect) (obj2)));
        obj2 = obj1;
        if (((Boolean)isA11yEnabled.get()).booleanValue())
        {
            obj3 = ((View) (obj)).getContentDescription();
            obj2 = obj3;
            if (obj3 == null)
            {
                obj2 = obj3;
                if (obj instanceof TextView)
                {
                    obj2 = ((TextView)obj).getText();
                }
            }
            ((AccessibilityVirtualView.Builder) (obj1)).contentDescription(((CharSequence) (obj2)));
            obj2 = obj1;
            if (((View) (obj)).isClickable())
            {
                obj.getClass();
                ((AccessibilityVirtualView.Builder) (obj1)).clickHandler(new .Lambda._cls2(((View) (obj))));
                obj2 = obj1;
            }
        }
          goto _L6
        obj2 = null;
          goto _L7
    }

    public final FluentFuture finish(boolean flag, boolean flag1)
    {
        boolean flag5 = true;
        Object obj;
        boolean flag2;
        int i;
        if (flag && isVisibleSupplier.get())
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (!((Boolean)fullyLoaded.get()).booleanValue() && flag1)
        {
            fullyLoaded.set(Boolean.valueOf(true));
        }
        obj = recyclerView.mLayout;
        i = 0;
        while (i < viewCache.size()) 
        {
            View view = (View)viewCache.valueAt(i);
            boolean flag4;
            if (view.getLeft() >= ((android.support.v7.widget.RecyclerView.LayoutManager) (obj)).mWidth || view.getTop() >= ((android.support.v7.widget.RecyclerView.LayoutManager) (obj)).mHeight || view.getRight() < 0 || view.getBottom() < 0)
            {
                flag4 = true;
            } else
            {
                flag4 = false;
            }
            if (flag2 && !flag4 && (animatedToAlternateView.isEmpty() || !animatedToAlternateView.contains(view)))
            {
                android.support.v7.widget.RecyclerView.Recycler recycler1 = recycler;
                ValueAnimator valueanimator = ValueAnimator.ofFloat(new float[] {
                    view.getAlpha(), 0.0F
                });
                valueanimator.addUpdateListener(new com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.AnimationUtil..Lambda._cls1(view));
                valueanimator.addListener(new com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.AnimationUtil._cls2(view, ((android.support.v7.widget.RecyclerView.LayoutManager) (obj)), recycler1));
                addAnimator(valueanimator, null);
            } else
            {
                ((android.support.v7.widget.RecyclerView.LayoutManager) (obj)).detachView(view);
                recycler.recycleView((View)viewCache.valueAt(i));
                sortedViewsValid = false;
            }
            i++;
        }
        animator.cancel(true);
        if (flag2 && !animators.isEmpty())
        {
            AnimatorSetFuture animatorsetfuture = (AnimatorSetFuture)animatorSetFutureProvider.get();
            List list = animators;
            boolean flag3;
            if (!((AnimatorFuture) (animatorsetfuture)).started)
            {
                flag3 = flag5;
            } else
            {
                flag3 = false;
            }
            if (!flag3)
            {
                throw new IllegalStateException();
            }
            ((AnimatorSet)((AnimatorFuture) (animatorsetfuture)).animator).playTogether(list);
            class .Lambda._cls3
                implements Runnable
            {

                private final LayoutUpdaterImpl arg$1;
                private final android.support.v7.widget.RecyclerView.LayoutManager arg$2;

                public final void run()
                {
                    LayoutUpdaterImpl layoutupdaterimpl = arg$1;
                    android.support.v7.widget.RecyclerView.LayoutManager layoutmanager = arg$2;
                    layoutupdaterimpl.sortedViewsValid = false;
                    if (layoutmanager.mRecyclerView != null)
                    {
                        layoutmanager.mRecyclerView.requestLayout();
                    }
                }

            .Lambda._cls3(android.support.v7.widget.RecyclerView.LayoutManager layoutmanager)
            {
                arg$1 = LayoutUpdaterImpl.this;
                arg$2 = layoutmanager;
            }
            }

            obj = new .Lambda._cls3(((android.support.v7.widget.RecyclerView.LayoutManager) (obj)));
            ((AnimatorFuture) (animatorsetfuture)).animator.addListener(new com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.AnimatorFuture._cls2(((Runnable) (obj))));
            obj = ((AnimatorSetFuture)animatorsetfuture.cast()).start();
        } else
        {
            obj = new Object();
            if (obj == null)
            {
                obj = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                obj = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(obj);
            }
        }
        animator = ((ListenableFuture) (obj));
        viewCache.clear();
        animators.clear();
        lastVirtualViews.clear();
        lastVirtualViews.addAll(virtualViewsTmp);
        virtualViewsTmp.clear();
        obj = animator;
        if (obj instanceof FluentFuture)
        {
            return (FluentFuture)obj;
        } else
        {
            return new ForwardingFluentFuture(((ListenableFuture) (obj)));
        }
    }

    final void init(android.support.v7.widget.RecyclerView.Recycler recycler1)
    {
        viewCache.clear();
        animators.clear();
        placedItems.clear();
        animatedToAlternateView.clear();
        virtualViewsTmp.clear();
        recycler = recycler1;
        recycler1 = recyclerView.mLayout;
        int k = recycler1.getChildCount();
        for (int i = 0; i < k; i++)
        {
            View view = recycler1.getChildAt(i);
            android.support.v7.widget.RecyclerView.ViewHolder viewholder = recyclerView.getChildViewHolder(view);
            int j;
            boolean flag;
            if (viewholder.mPreLayoutPosition == -1)
            {
                j = viewholder.mPosition;
            } else
            {
                j = viewholder.mPreLayoutPosition;
            }
            if (j == -1)
            {
                continue;
            }
            if (viewCache.get(j) == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException();
            }
            viewCache.put(j, view);
        }

    }


    private class _cls1
        implements android.view.ViewGroup.OnHierarchyChangeListener
    {

        private final LayoutUpdaterImpl this$0;
        private final RecyclerView val$recyclerView;

        public final void onChildViewAdded(View view, View view1)
        {
            sortedViewsValid = false;
            expectedViewCount = recyclerView.getChildCount();
        }

        public final void onChildViewRemoved(View view, View view1)
        {
            sortedViewsValid = false;
            expectedViewCount = recyclerView.getChildCount();
        }

        _cls1()
        {
            this$0 = LayoutUpdaterImpl.this;
            recyclerView = recyclerview;
            super();
        }
    }

}
