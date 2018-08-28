// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.NestedScrollingParent2;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.widget.DirectedAcyclicGraph;
import android.support.v4.widget.ViewGroupUtils;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class CoordinatorLayout extends ViewGroup
    implements NestedScrollingParent2
{
    public static interface AttachedBehavior
    {

        public abstract Behavior getBehavior();
    }

    public static class Behavior
    {

        public boolean getInsetDodgeRect(CoordinatorLayout coordinatorlayout, View view, Rect rect)
        {
            return false;
        }

        public boolean layoutDependsOn$51662RJ4E9NMIP1FEDQN0S3FE9Q2UP35EDKMERHFETKM8PR5EGNK6RRFE9I6IRJ1EHNN4J31F5NNAT1R9HGMSP3IDTKM8BRMD5INEBQMD5INEEQCC5N68SJFD5I2UTJ9CLRIULJ9CLRJMAAQ0(View view)
        {
            return false;
        }

        public void onAttachedToLayoutParams(LayoutParams layoutparams)
        {
        }

        public boolean onDependentViewChanged(CoordinatorLayout coordinatorlayout, View view, View view1)
        {
            return false;
        }

        public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorlayout, View view, MotionEvent motionevent)
        {
            return false;
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorlayout, View view, int i)
        {
            return false;
        }

        public boolean onMeasureChild(CoordinatorLayout coordinatorlayout, View view, int i, int j, int k, int l)
        {
            return false;
        }

        public boolean onNestedPreFling(CoordinatorLayout coordinatorlayout, View view, View view1, float f, float f1)
        {
            return false;
        }

        public void onNestedPreScroll(CoordinatorLayout coordinatorlayout, View view, View view1, int i, int j, int ai[], int k)
        {
        }

        public void onNestedScroll(CoordinatorLayout coordinatorlayout, View view, View view1, int i, int j, int k, int l, 
                int i1)
        {
            if (i1 == 0)
            {
                onNestedScroll$51662RJ4E9NMIP1FEDQN0S3FE9Q2UP35EDKMERHFETKM8PR5EGNK6RRFE9I6IRJ1EHNN4J31F5NNAT1R9HGMSP3IDTKM8BRMD5INEBQMD5INEEQCC5N68SJFD5I2UTJ9CLRIULJ9CLRJMIA9954IILG_0(view, j);
            }
        }

        public void onNestedScroll$51662RJ4E9NMIP1FEDQN0S3FE9Q2UP35EDKMERHFETKM8PR5EGNK6RRFE9I6IRJ1EHNN4J31F5NNAT1R9HGMSP3IDTKM8BRMD5INEBQMD5INEEQCC5N68SJFD5I2UTJ9CLRIULJ9CLRJMIA9954IILG_0(View view, int i)
        {
        }

        public boolean onRequestChildRectangleOnScreen(CoordinatorLayout coordinatorlayout, View view, Rect rect, boolean flag)
        {
            return false;
        }

        public void onRestoreInstanceState(CoordinatorLayout coordinatorlayout, View view, Parcelable parcelable)
        {
        }

        public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorlayout, View view)
        {
            return android.view.View.BaseSavedState.EMPTY_STATE;
        }

        public boolean onStartNestedScroll(CoordinatorLayout coordinatorlayout, View view, View view1, View view2, int i, int j)
        {
            if (j == 0)
            {
                return onStartNestedScroll$51662RJ4E9NMIP1FEDQN0S3FE9Q2UP35EDKMERHFETKM8PR5EGNK6RRFE9I6IRJ1EHNN4J31F5NNAT1R9HGMSP3IDTKM8BRMD5INEBQMD5INEEQCC5N68SJFD5I2UTJ9CLRIULJ9CLRJMJ31DPI74RR9CGNNCQB5ESNLCQB5ESTKIAAQ0(i);
            } else
            {
                return false;
            }
        }

        public boolean onStartNestedScroll$51662RJ4E9NMIP1FEDQN0S3FE9Q2UP35EDKMERHFETKM8PR5EGNK6RRFE9I6IRJ1EHNN4J31F5NNAT1R9HGMSP3IDTKM8BRMD5INEBQMD5INEEQCC5N68SJFD5I2UTJ9CLRIULJ9CLRJMJ31DPI74RR9CGNNCQB5ESNLCQB5ESTKIAAQ0(int i)
        {
            return false;
        }

        public void onStopNestedScroll(CoordinatorLayout coordinatorlayout, View view, View view1, int i)
        {
        }

        public boolean onTouchEvent(CoordinatorLayout coordinatorlayout, View view, MotionEvent motionevent)
        {
            return false;
        }

        public Behavior()
        {
        }

        public Behavior(Context context, AttributeSet attributeset)
        {
        }
    }

    public static interface DefaultBehavior
        extends Annotation
    {

        public abstract Class value();
    }

    final class HierarchyChangeListener
        implements android.view.ViewGroup.OnHierarchyChangeListener
    {

        private final CoordinatorLayout this$0;

        public final void onChildViewAdded(View view, View view1)
        {
            if (mOnHierarchyChangeListener != null)
            {
                mOnHierarchyChangeListener.onChildViewAdded(view, view1);
            }
        }

        public final void onChildViewRemoved(View view, View view1)
        {
            onChildViewsChanged(2);
            if (mOnHierarchyChangeListener != null)
            {
                mOnHierarchyChangeListener.onChildViewRemoved(view, view1);
            }
        }

        HierarchyChangeListener()
        {
            this$0 = CoordinatorLayout.this;
            super();
        }
    }

    public static final class LayoutParams extends android.view.ViewGroup.MarginLayoutParams
    {

        public int anchorGravity;
        public int dodgeInsetEdges;
        public int gravity;
        public int insetEdge;
        public int keyline;
        public View mAnchorDirectChild;
        public int mAnchorId;
        public View mAnchorView;
        public Behavior mBehavior;
        public boolean mBehaviorResolved;
        private boolean mDidAcceptNestedScrollNonTouch;
        private boolean mDidAcceptNestedScrollTouch;
        public boolean mDidBlockInteraction;
        public boolean mDidChangeAfterNestedScroll;
        public int mInsetOffsetX;
        public int mInsetOffsetY;
        public final Rect mLastChildRect;

        final boolean isNestedScrollAccepted(int i)
        {
            switch (i)
            {
            default:
                return false;

            case 0: // '\0'
                return mDidAcceptNestedScrollTouch;

            case 1: // '\001'
                return mDidAcceptNestedScrollNonTouch;
            }
        }

        final void setNestedScrollAccepted(int i, boolean flag)
        {
            switch (i)
            {
            default:
                return;

            case 0: // '\0'
                mDidAcceptNestedScrollTouch = flag;
                return;

            case 1: // '\001'
                mDidAcceptNestedScrollNonTouch = flag;
                break;
            }
        }

        public LayoutParams(int i, int j)
        {
            super(-2, -2);
            mBehaviorResolved = false;
            gravity = 0;
            anchorGravity = 0;
            keyline = -1;
            mAnchorId = -1;
            insetEdge = 0;
            dodgeInsetEdges = 0;
            mLastChildRect = new Rect();
        }

        LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            mBehaviorResolved = false;
            gravity = 0;
            anchorGravity = 0;
            keyline = -1;
            mAnchorId = -1;
            insetEdge = 0;
            dodgeInsetEdges = 0;
            mLastChildRect = new Rect();
            TypedArray typedarray = context.obtainStyledAttributes(attributeset, android.support.coordinatorlayout.R.styleable.CoordinatorLayout_Layout);
            gravity = typedarray.getInteger(android.support.coordinatorlayout.R.styleable.CoordinatorLayout_Layout_android_layout_gravity, 0);
            mAnchorId = typedarray.getResourceId(android.support.coordinatorlayout.R.styleable.CoordinatorLayout_Layout_layout_anchor, -1);
            anchorGravity = typedarray.getInteger(android.support.coordinatorlayout.R.styleable.CoordinatorLayout_Layout_layout_anchorGravity, 0);
            keyline = typedarray.getInteger(android.support.coordinatorlayout.R.styleable.CoordinatorLayout_Layout_layout_keyline, -1);
            insetEdge = typedarray.getInt(android.support.coordinatorlayout.R.styleable.CoordinatorLayout_Layout_layout_insetEdge, 0);
            dodgeInsetEdges = typedarray.getInt(android.support.coordinatorlayout.R.styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
            mBehaviorResolved = typedarray.hasValue(android.support.coordinatorlayout.R.styleable.CoordinatorLayout_Layout_layout_behavior);
            if (mBehaviorResolved)
            {
                mBehavior = CoordinatorLayout.parseBehavior(context, attributeset, typedarray.getString(android.support.coordinatorlayout.R.styleable.CoordinatorLayout_Layout_layout_behavior));
            }
            typedarray.recycle();
            if (mBehavior != null)
            {
                mBehavior.onAttachedToLayoutParams(this);
            }
        }

        public LayoutParams(LayoutParams layoutparams)
        {
            super(layoutparams);
            mBehaviorResolved = false;
            gravity = 0;
            anchorGravity = 0;
            keyline = -1;
            mAnchorId = -1;
            insetEdge = 0;
            dodgeInsetEdges = 0;
            mLastChildRect = new Rect();
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            mBehaviorResolved = false;
            gravity = 0;
            anchorGravity = 0;
            keyline = -1;
            mAnchorId = -1;
            insetEdge = 0;
            dodgeInsetEdges = 0;
            mLastChildRect = new Rect();
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
            mBehaviorResolved = false;
            gravity = 0;
            anchorGravity = 0;
            keyline = -1;
            mAnchorId = -1;
            insetEdge = 0;
            dodgeInsetEdges = 0;
            mLastChildRect = new Rect();
        }
    }

    final class OnPreDrawListener
        implements android.view.ViewTreeObserver.OnPreDrawListener
    {

        private final CoordinatorLayout this$0;

        public final boolean onPreDraw()
        {
            onChildViewsChanged(0);
            return true;
        }

        OnPreDrawListener()
        {
            this$0 = CoordinatorLayout.this;
            super();
        }
    }

    public static final class SavedState extends AbsSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();
        public SparseArray behaviorStates;

        public final void writeToParcel(Parcel parcel, int i)
        {
            int k = 0;
            super.writeToParcel(parcel, i);
            int ai[];
            Parcelable aparcelable[];
            int j;
            if (behaviorStates != null)
            {
                j = behaviorStates.size();
            } else
            {
                j = 0;
            }
            parcel.writeInt(j);
            ai = new int[j];
            aparcelable = new Parcelable[j];
            for (; k < j; k++)
            {
                ai[k] = behaviorStates.keyAt(k);
                aparcelable[k] = (Parcelable)behaviorStates.valueAt(k);
            }

            parcel.writeIntArray(ai);
            parcel.writeParcelableArray(aparcelable, i);
        }


        public SavedState(Parcel parcel, ClassLoader classloader)
        {
            super(parcel, classloader);
            int j = parcel.readInt();
            int ai[] = new int[j];
            parcel.readIntArray(ai);
            parcel = parcel.readParcelableArray(classloader);
            behaviorStates = new SparseArray(j);
            for (int i = 0; i < j; i++)
            {
                behaviorStates.append(ai[i], parcel[i]);
            }

        }

        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }

        class _cls1
            implements android.os.Parcelable.ClassLoaderCreator
        {

            public final Object createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel, null);
            }

            public final Object createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return new SavedState(parcel, classloader);
            }

            public final Object[] newArray(int i)
            {
                return new SavedState[i];
            }

                _cls1()
                {
                }
        }

    }

    static final class ViewElevationComparator
        implements Comparator
    {

        public final int compare(Object obj, Object obj1)
        {
            obj = (View)obj;
            obj1 = (View)obj1;
            float f = ViewCompat.getZ(((View) (obj)));
            float f1 = ViewCompat.getZ(((View) (obj1)));
            if (f > f1)
            {
                return -1;
            }
            return f >= f1 ? 0 : 1;
        }

        ViewElevationComparator()
        {
        }
    }


    private static final Class CONSTRUCTOR_PARAMS[] = {
        android/content/Context, android/util/AttributeSet
    };
    private static final Comparator TOP_SORTED_CHILDREN_COMPARATOR = new ViewElevationComparator();
    private static final String WIDGET_PACKAGE_NAME;
    private static final ThreadLocal sConstructors = new ThreadLocal();
    private static final android.support.v4.util.Pools.Pool sRectPool = new android.support.v4.util.Pools.SynchronizedPool(12);
    private OnApplyWindowInsetsListener mApplyWindowInsetsListener;
    private View mBehaviorTouchView;
    public final DirectedAcyclicGraph mChildDag;
    private final List mDependencySortedChildren;
    private boolean mDisallowInterceptReset;
    public boolean mDrawStatusBarBackground;
    private boolean mIsAttachedToWindow;
    private int mKeylines[];
    public WindowInsetsCompat mLastInsets;
    private boolean mNeedsPreDrawListener;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    private View mNestedScrollingTarget;
    public android.view.ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;
    private OnPreDrawListener mOnPreDrawListener;
    private Drawable mStatusBarBackground;
    public final List mTempDependenciesList;
    private final int mTempIntPair[];
    private final List mTempList1;

    public CoordinatorLayout(Context context)
    {
        this(context, null);
    }

    public CoordinatorLayout(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f010009);
    }

    public CoordinatorLayout(Context context, AttributeSet attributeset, int i)
    {
        boolean flag = false;
        super(context, attributeset, i);
        mDependencySortedChildren = new ArrayList();
        mChildDag = new DirectedAcyclicGraph();
        mTempList1 = new ArrayList();
        mTempDependenciesList = new ArrayList();
        mTempIntPair = new int[2];
        mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        if (i == 0)
        {
            attributeset = context.obtainStyledAttributes(attributeset, android.support.coordinatorlayout.R.styleable.CoordinatorLayout, 0, 0x7f1403b6);
        } else
        {
            attributeset = context.obtainStyledAttributes(attributeset, android.support.coordinatorlayout.R.styleable.CoordinatorLayout, i, 0);
        }
        i = attributeset.getResourceId(android.support.coordinatorlayout.R.styleable.CoordinatorLayout_keylines, 0);
        if (i != 0)
        {
            context = context.getResources();
            mKeylines = context.getIntArray(i);
            float f = context.getDisplayMetrics().density;
            int j = mKeylines.length;
            for (i = ((flag) ? 1 : 0); i < j; i++)
            {
                mKeylines[i] = (int)((float)mKeylines[i] * f);
            }

        }
        mStatusBarBackground = attributeset.getDrawable(android.support.coordinatorlayout.R.styleable.CoordinatorLayout_statusBarBackground);
        attributeset.recycle();
        setupForInsets();
        super.setOnHierarchyChangeListener(new HierarchyChangeListener());
    }

    private final void constrainChildRect(LayoutParams layoutparams, Rect rect, int i, int j)
    {
        int l = getWidth();
        int k = getHeight();
        l = Math.max(getPaddingLeft() + layoutparams.leftMargin, Math.min(rect.left, l - getPaddingRight() - i - layoutparams.rightMargin));
        k = Math.max(getPaddingTop() + layoutparams.topMargin, Math.min(rect.top, k - getPaddingBottom() - j - layoutparams.bottomMargin));
        rect.set(l, k, l + i, k + j);
    }

    private final void getChildRect(View view, boolean flag, Rect rect)
    {
        if (view.isLayoutRequested() || view.getVisibility() == 8)
        {
            rect.setEmpty();
            return;
        }
        if (flag)
        {
            ViewGroupUtils.getDescendantRect(this, view, rect);
            return;
        } else
        {
            rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            return;
        }
    }

    private static void getDesiredAnchoredChildRectWithoutConstraints$51662RJ4E9NMIP1FEPKMATPFAPKMATPR95662RJ4E9NMIP1FCTP62S38D5HN6BQICLHN8EQCC5N68SJFD5I2UPRIC5O6GQB3ECNL4PB3EGTKOOBECHP6UQB45TPNAS3GDTP78BR4CLPMIPRE5TRMIP37CLQ2UGRFDTP68QBEC5Q6USICC5SMUTBK4H662UBFELQ50OBIC5MN6EQ994KLC___0(int i, Rect rect, Rect rect1, LayoutParams layoutparams, int j, int k)
    {
        int l;
        int k1;
        int i1 = layoutparams.gravity;
        l = i1;
        if (i1 == 0)
        {
            l = 17;
        }
        k1 = Gravity.getAbsoluteGravity(l, i);
        i1 = layoutparams.anchorGravity;
        l = i1;
        if ((i1 & 7) == 0)
        {
            l = i1 | 0x800003;
        }
        i1 = l;
        if ((l & 0x70) == 0)
        {
            i1 = l | 0x30;
        }
        l = Gravity.getAbsoluteGravity(i1, i);
        l & 7;
        JVM INSTR lookupswitch 2: default 112
    //                   1: 259
    //                   5: 251;
           goto _L1 _L2 _L3
_L1:
        i = rect.left;
_L13:
        l & 0x70;
        JVM INSTR lookupswitch 2: default 148
    //                   16: 283
    //                   80: 274;
           goto _L4 _L5 _L6
_L4:
        l = rect.top;
_L14:
        int j1 = i;
        k1 & 7;
        JVM INSTR lookupswitch 2: default 188
    //                   1: 299
    //                   5: 194;
           goto _L7 _L8 _L9
_L9:
        break; /* Loop/switch isn't completed */
_L7:
        j1 = i - j;
_L15:
        i = l;
        k1 & 0x70;
        JVM INSTR lookupswitch 2: default 228
    //                   16: 310
    //                   80: 234;
           goto _L10 _L11 _L12
_L12:
        break; /* Loop/switch isn't completed */
_L10:
        i = l - k;
_L16:
        rect1.set(j1, i, j1 + j, i + k);
        return;
_L3:
        i = rect.right;
          goto _L13
_L2:
        i = rect.left + rect.width() / 2;
          goto _L13
_L6:
        l = rect.bottom;
          goto _L14
_L5:
        l = rect.top + rect.height() / 2;
          goto _L14
_L8:
        j1 = i - j / 2;
          goto _L15
_L11:
        i = l - k / 2;
          goto _L16
    }

    private final int getKeyline(int i)
    {
        if (mKeylines == null)
        {
            Log.e("CoordinatorLayout", (new StringBuilder("No keylines defined for ")).append(this).append(" - attempted index lookup ").append(i).toString());
            return 0;
        }
        if (i < 0 || i >= mKeylines.length)
        {
            Log.e("CoordinatorLayout", (new StringBuilder("Keyline index ")).append(i).append(" out of range for ").append(this).toString());
            return 0;
        } else
        {
            return mKeylines[i];
        }
    }

    private static LayoutParams getResolvedLayoutParams(View view)
    {
        LayoutParams layoutparams;
label0:
        {
            layoutparams = (LayoutParams)view.getLayoutParams();
            if (!layoutparams.mBehaviorResolved)
            {
                if (!(view instanceof AttachedBehavior))
                {
                    break label0;
                }
                view = ((AttachedBehavior)view).getBehavior();
                if (view == null)
                {
                    Log.e("CoordinatorLayout", "Attached behavior class is null");
                }
                if (layoutparams.mBehavior != view)
                {
                    layoutparams.mBehavior = view;
                    layoutparams.mBehaviorResolved = true;
                    if (view != null)
                    {
                        view.onAttachedToLayoutParams(layoutparams);
                    }
                }
                layoutparams.mBehaviorResolved = true;
            }
            return layoutparams;
        }
        Class class1 = view.getClass();
        view = null;
        do
        {
            if (class1 == null)
            {
                break;
            }
            DefaultBehavior defaultbehavior = (DefaultBehavior)class1.getAnnotation(android/support/design/widget/CoordinatorLayout$DefaultBehavior);
            view = defaultbehavior;
            if (defaultbehavior != null)
            {
                break;
            }
            class1 = class1.getSuperclass();
            view = defaultbehavior;
        } while (true);
        if (view == null)
        {
            break MISSING_BLOCK_LABEL_172;
        }
        Behavior behavior;
        behavior = (Behavior)view.value().getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        if (layoutparams.mBehavior == behavior)
        {
            break MISSING_BLOCK_LABEL_172;
        }
        layoutparams.mBehavior = behavior;
        layoutparams.mBehaviorResolved = true;
        if (behavior != null)
        {
            try
            {
                behavior.onAttachedToLayoutParams(layoutparams);
            }
            catch (Exception exception)
            {
                Log.e("CoordinatorLayout", (new StringBuilder("Default behavior class ")).append(view.value().getName()).append(" could not be instantiated. Did you forget a default constructor?").toString(), exception);
            }
        }
        layoutparams.mBehaviorResolved = true;
        return layoutparams;
    }

    static Behavior parseBehavior(Context context, AttributeSet attributeset, String s)
    {
        if (TextUtils.isEmpty(s))
        {
            return null;
        }
        String s1;
        Constructor constructor;
        Constructor constructor1;
        if (s.startsWith("."))
        {
            s1 = (new StringBuilder()).append(context.getPackageName()).append(s).toString();
        } else
        {
            s1 = s;
            if (s.indexOf('.') < 0)
            {
                s1 = s;
                if (!TextUtils.isEmpty(WIDGET_PACKAGE_NAME))
                {
                    s1 = (new StringBuilder()).append(WIDGET_PACKAGE_NAME).append('.').append(s).toString();
                }
            }
        }
        try
        {
            s = (Map)sConstructors.get();
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new RuntimeException((new StringBuilder("Could not inflate Behavior subclass ")).append(s1).toString(), context);
        }
        if (s != null)
        {
            break MISSING_BLOCK_LABEL_70;
        }
        s = new HashMap();
        sConstructors.set(s);
        constructor1 = (Constructor)s.get(s1);
        constructor = constructor1;
        if (constructor1 != null)
        {
            break MISSING_BLOCK_LABEL_123;
        }
        constructor = context.getClassLoader().loadClass(s1).getConstructor(CONSTRUCTOR_PARAMS);
        constructor.setAccessible(true);
        s.put(s1, constructor);
        context = (Behavior)constructor.newInstance(new Object[] {
            context, attributeset
        });
        return context;
    }

    private final boolean performIntercept(MotionEvent motionevent, int i)
    {
        MotionEvent motionevent1;
        List list;
        int j;
        int l;
        int i1;
        int j1;
        boolean flag1;
        flag1 = false;
        boolean flag = false;
        i1 = motionevent.getActionMasked();
        list = mTempList1;
        list.clear();
        boolean flag2 = isChildrenDrawingOrderEnabled();
        j1 = getChildCount();
        j = j1 - 1;
        while (j >= 0) 
        {
            int k;
            if (flag2)
            {
                k = getChildDrawingOrder(j1, j);
            } else
            {
                k = j;
            }
            list.add(getChildAt(k));
            j--;
        }
        if (TOP_SORTED_CHILDREN_COMPARATOR != null)
        {
            Collections.sort(list, TOP_SORTED_CHILDREN_COMPARATOR);
        }
        j1 = list.size();
        l = 0;
        motionevent1 = null;
        j = ((flag) ? 1 : 0);
_L9:
        if (l >= j1) goto _L2; else goto _L1
_L1:
        View view;
        Behavior behavior1;
        LayoutParams layoutparams;
        view = (View)list.get(l);
        layoutparams = (LayoutParams)view.getLayoutParams();
        behavior1 = layoutparams.mBehavior;
        if (!flag1 && !j || i1 == 0) goto _L4; else goto _L3
_L3:
        if (behavior1 == null) goto _L6; else goto _L5
_L5:
        if (motionevent1 == null)
        {
            long l1 = SystemClock.uptimeMillis();
            motionevent1 = MotionEvent.obtain(l1, l1, 3, 0.0F, 0.0F, 0);
        }
        i;
        JVM INSTR tableswitch 0 1: default 228
    //                   0 237
    //                   1 250;
           goto _L6 _L7 _L8
_L6:
        l++;
          goto _L9
_L7:
        behavior1.onInterceptTouchEvent(this, view, motionevent1);
          goto _L6
_L8:
        behavior1.onTouchEvent(this, view, motionevent1);
          goto _L6
_L4:
        boolean flag3 = flag1;
        if (flag1) goto _L11; else goto _L10
_L10:
        flag3 = flag1;
        if (behavior1 == null) goto _L11; else goto _L12
_L12:
        i;
        JVM INSTR tableswitch 0 1: default 304
    //                   0 389
    //                   1 403;
           goto _L13 _L14 _L15
_L13:
        flag3 = flag1;
        if (flag1)
        {
            mBehaviorTouchView = view;
            flag3 = flag1;
        }
_L11:
        flag1 = flag3;
        if (layoutparams.mBehavior == null)
        {
            layoutparams.mDidBlockInteraction = false;
        }
        boolean flag4 = layoutparams.mDidBlockInteraction;
        if (layoutparams.mDidBlockInteraction)
        {
            flag3 = true;
        } else
        {
            flag3 = layoutparams.mDidBlockInteraction;
            Behavior behavior;
            if (layoutparams.mBehavior != null)
            {
                behavior = layoutparams.mBehavior;
            }
            layoutparams.mDidBlockInteraction = flag3;
        }
        if (flag3 && !flag4)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (flag3)
        {
            flag3 = flag1;
            if (!j)
            {
                break MISSING_BLOCK_LABEL_459;
            }
        }
          goto _L6
_L14:
        flag1 = behavior1.onInterceptTouchEvent(this, view, motionevent);
          goto _L13
_L15:
        flag1 = behavior1.onTouchEvent(this, view, motionevent);
          goto _L13
_L2:
        flag3 = flag1;
        list.clear();
        return flag3;
          goto _L13
    }

    private final void resetTouchBehaviors(boolean flag)
    {
        int k = getChildCount();
        int i = 0;
        while (i < k) 
        {
            View view = getChildAt(i);
            Behavior behavior = ((LayoutParams)view.getLayoutParams()).mBehavior;
            if (behavior != null)
            {
                long l = SystemClock.uptimeMillis();
                MotionEvent motionevent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
                if (flag)
                {
                    behavior.onInterceptTouchEvent(this, view, motionevent);
                } else
                {
                    behavior.onTouchEvent(this, view, motionevent);
                }
                motionevent.recycle();
            }
            i++;
        }
        for (int j = 0; j < k; j++)
        {
            ((LayoutParams)getChildAt(j).getLayoutParams()).mDidBlockInteraction = false;
        }

        mBehaviorTouchView = null;
        mDisallowInterceptReset = false;
    }

    private static void setInsetOffsetX(View view, int i)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if (layoutparams.mInsetOffsetX != i)
        {
            ViewCompat.offsetLeftAndRight(view, i - layoutparams.mInsetOffsetX);
            layoutparams.mInsetOffsetX = i;
        }
    }

    private static void setInsetOffsetY(View view, int i)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if (layoutparams.mInsetOffsetY != i)
        {
            ViewCompat.offsetTopAndBottom(view, i - layoutparams.mInsetOffsetY);
            layoutparams.mInsetOffsetY = i;
        }
    }

    private final void setStatusBarBackground(Drawable drawable)
    {
        Drawable drawable1 = null;
        if (mStatusBarBackground != drawable)
        {
            if (mStatusBarBackground != null)
            {
                mStatusBarBackground.setCallback(null);
            }
            if (drawable != null)
            {
                drawable1 = drawable.mutate();
            }
            mStatusBarBackground = drawable1;
            if (mStatusBarBackground != null)
            {
                if (mStatusBarBackground.isStateful())
                {
                    mStatusBarBackground.setState(getDrawableState());
                }
                DrawableCompat.setLayoutDirection(mStatusBarBackground, ViewCompat.getLayoutDirection(this));
                drawable = mStatusBarBackground;
                boolean flag;
                if (getVisibility() == 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                drawable.setVisible(flag, false);
                mStatusBarBackground.setCallback(this);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private final void setupForInsets()
    {
        if (ViewCompat.getFitsSystemWindows(this))
        {
            if (mApplyWindowInsetsListener == null)
            {
                mApplyWindowInsetsListener = new _cls1();
            }
            ViewCompat.setOnApplyWindowInsetsListener(this, mApplyWindowInsetsListener);
            setSystemUiVisibility(1280);
            return;
        } else
        {
            ViewCompat.setOnApplyWindowInsetsListener(this, null);
            return;
        }
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return (layoutparams instanceof LayoutParams) && super.checkLayoutParams(layoutparams);
    }

    public final void dispatchDependentViewsChanged(View view)
    {
        List list = (List)mChildDag.mGraph.get(view);
        if (list != null && !list.isEmpty())
        {
            for (int i = 0; i < list.size(); i++)
            {
                View view1 = (View)list.get(i);
                Behavior behavior = ((LayoutParams)view1.getLayoutParams()).mBehavior;
                if (behavior != null)
                {
                    behavior.onDependentViewChanged(this, view1, view);
                }
            }

        }
    }

    public final boolean doViewsOverlap(View view, View view1)
    {
        Rect rect;
        boolean flag;
        boolean flag1 = true;
        if (view.getVisibility() != 0 || view1.getVisibility() != 0)
        {
            break MISSING_BLOCK_LABEL_244;
        }
        rect = (Rect)sRectPool.acquire();
        if (rect == null)
        {
            rect = new Rect();
        }
        Rect rect1;
        int i;
        int j;
        if (view.getParent() != this)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        getChildRect(view, flag, rect);
        rect1 = (Rect)sRectPool.acquire();
        view = rect1;
        if (rect1 == null)
        {
            view = new Rect();
        }
        if (view1.getParent() != this)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        getChildRect(view1, flag, view);
        if (rect.left > ((Rect) (view)).right || rect.top > ((Rect) (view)).bottom || rect.right < ((Rect) (view)).left) goto _L2; else goto _L1
_L1:
        i = rect.bottom;
        j = ((Rect) (view)).top;
        if (i < j) goto _L2; else goto _L3
_L3:
        flag = flag1;
_L5:
        rect.setEmpty();
        sRectPool.release(rect);
        view.setEmpty();
        sRectPool.release(view);
        return flag;
_L2:
        flag = false;
        if (true) goto _L5; else goto _L4
_L4:
        view1;
        rect.setEmpty();
        sRectPool.release(rect);
        view.setEmpty();
        sRectPool.release(view);
        throw view1;
        return false;
    }

    protected boolean drawChild(Canvas canvas, View view, long l)
    {
        view.getLayoutParams();
        return super.drawChild(canvas, view, l);
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        int ai[] = getDrawableState();
        boolean flag1 = false;
        Drawable drawable = mStatusBarBackground;
        boolean flag = flag1;
        if (drawable != null)
        {
            flag = flag1;
            if (drawable.isStateful())
            {
                flag = drawable.setState(ai) | false;
            }
        }
        if (flag)
        {
            invalidate();
        }
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(-2, -2);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        if (layoutparams instanceof LayoutParams)
        {
            return new LayoutParams((LayoutParams)layoutparams);
        }
        if (layoutparams instanceof android.view.ViewGroup.MarginLayoutParams)
        {
            return new LayoutParams((android.view.ViewGroup.MarginLayoutParams)layoutparams);
        } else
        {
            return new LayoutParams(layoutparams);
        }
    }

    public final List getDependencies(View view)
    {
        DirectedAcyclicGraph directedacyclicgraph = mChildDag;
        ArrayList arraylist = null;
        int j = directedacyclicgraph.mGraph.size();
        for (int i = 0; i < j;)
        {
            ArrayList arraylist2 = (ArrayList)directedacyclicgraph.mGraph.mArray[(i << 1) + 1];
            ArrayList arraylist1 = arraylist;
            if (arraylist2 != null)
            {
                arraylist1 = arraylist;
                if (arraylist2.contains(view))
                {
                    if (arraylist == null)
                    {
                        arraylist = new ArrayList();
                    }
                    arraylist.add(directedacyclicgraph.mGraph.mArray[i << 1]);
                    arraylist1 = arraylist;
                }
            }
            i++;
            arraylist = arraylist1;
        }

        mTempDependenciesList.clear();
        if (arraylist != null)
        {
            mTempDependenciesList.addAll(arraylist);
        }
        return mTempDependenciesList;
    }

    public int getNestedScrollAxes()
    {
        return mNestedScrollingParentHelper.mNestedScrollAxes;
    }

    protected int getSuggestedMinimumHeight()
    {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingTop() + getPaddingBottom());
    }

    protected int getSuggestedMinimumWidth()
    {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingLeft() + getPaddingRight());
    }

    public final boolean isPointInChildBounds(View view, int i, int j)
    {
        Rect rect;
        Rect rect1 = (Rect)sRectPool.acquire();
        rect = rect1;
        if (rect1 == null)
        {
            rect = new Rect();
        }
        ViewGroupUtils.getDescendantRect(this, view, rect);
        boolean flag = rect.contains(i, j);
        rect.setEmpty();
        sRectPool.release(rect);
        return flag;
        view;
        rect.setEmpty();
        sRectPool.release(rect);
        throw view;
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        resetTouchBehaviors(false);
        if (mNeedsPreDrawListener)
        {
            if (mOnPreDrawListener == null)
            {
                mOnPreDrawListener = new OnPreDrawListener();
            }
            getViewTreeObserver().addOnPreDrawListener(mOnPreDrawListener);
        }
        if (mLastInsets == null && ViewCompat.getFitsSystemWindows(this))
        {
            ViewCompat.requestApplyInsets(this);
        }
        mIsAttachedToWindow = true;
    }

    final void onChildViewsChanged(int i)
    {
        Rect rect;
        Rect rect1;
        Rect rect2;
        int i1;
        int k1;
        int l1;
        k1 = ViewCompat.getLayoutDirection(this);
        l1 = mDependencySortedChildren.size();
        rect = (Rect)sRectPool.acquire();
        if (rect == null)
        {
            rect = new Rect();
        }
        rect1 = (Rect)sRectPool.acquire();
        if (rect1 == null)
        {
            rect1 = new Rect();
        }
        rect2 = (Rect)sRectPool.acquire();
        if (rect2 == null)
        {
            rect2 = new Rect();
        }
        i1 = 0;
_L16:
        if (i1 >= l1) goto _L2; else goto _L1
_L1:
        View view;
        LayoutParams layoutparams1;
        view = (View)mDependencySortedChildren.get(i1);
        layoutparams1 = (LayoutParams)view.getLayoutParams();
        if (i == 0 && view.getVisibility() == 8)
        {
            continue; /* Loop/switch isn't completed */
        }
        int j = 0;
        while (j < i1) 
        {
            Object obj = (View)mDependencySortedChildren.get(j);
            if (layoutparams1.mAnchorDirectChild != obj)
            {
                continue;
            }
            LayoutParams layoutparams2 = (LayoutParams)view.getLayoutParams();
            if (layoutparams2.mAnchorView == null)
            {
                continue;
            }
            Rect rect3 = (Rect)sRectPool.acquire();
            obj = rect3;
            if (rect3 == null)
            {
                obj = new Rect();
            }
            rect3 = (Rect)sRectPool.acquire();
            if (rect3 == null)
            {
                rect3 = new Rect();
            }
            Rect rect5 = (Rect)sRectPool.acquire();
            Rect rect4 = rect5;
            if (rect5 == null)
            {
                rect4 = new Rect();
            }
            ViewGroupUtils.getDescendantRect(this, layoutparams2.mAnchorView, ((Rect) (obj)));
            getChildRect(view, false, rect3);
            int i2 = view.getMeasuredWidth();
            int l2 = view.getMeasuredHeight();
            getDesiredAnchoredChildRectWithoutConstraints$51662RJ4E9NMIP1FEPKMATPFAPKMATPR95662RJ4E9NMIP1FCTP62S38D5HN6BQICLHN8EQCC5N68SJFD5I2UPRIC5O6GQB3ECNL4PB3EGTKOOBECHP6UQB45TPNAS3GDTP78BR4CLPMIPRE5TRMIP37CLQ2UGRFDTP68QBEC5Q6USICC5SMUTBK4H662UBFELQ50OBIC5MN6EQ994KLC___0(k1, ((Rect) (obj)), rect4, layoutparams2, i2, l2);
            boolean flag1;
            if (rect4.left != rect3.left || rect4.top != rect3.top)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            constrainChildRect(layoutparams2, rect4, i2, l2);
            i2 = rect4.left - rect3.left;
            l2 = rect4.top - rect3.top;
            if (i2 != 0)
            {
                ViewCompat.offsetLeftAndRight(view, i2);
            }
            if (l2 != 0)
            {
                ViewCompat.offsetTopAndBottom(view, l2);
            }
            if (flag1)
            {
                Behavior behavior = layoutparams2.mBehavior;
                if (behavior != null)
                {
                    behavior.onDependentViewChanged(this, view, layoutparams2.mAnchorView);
                }
            }
            ((Rect) (obj)).setEmpty();
            sRectPool.release(obj);
            rect3.setEmpty();
            sRectPool.release(rect3);
            rect4.setEmpty();
            sRectPool.release(rect4);
            j++;
        }
        getChildRect(view, true, rect1);
        if (layoutparams1.insetEdge == 0 || rect1.isEmpty()) goto _L4; else goto _L3
_L3:
        int k = Gravity.getAbsoluteGravity(layoutparams1.insetEdge, k1);
        k & 0x70;
        JVM INSTR lookupswitch 2: default 584
    //                   48: 789
    //                   80: 807;
           goto _L5 _L6 _L7
_L5:
        k & 7;
        JVM INSTR tableswitch 3 5: default 616
    //                   3 830
    //                   4 616
    //                   5 848;
           goto _L4 _L8 _L4 _L9
_L4:
        Object obj1;
        Object obj2;
        Object obj3;
        int l;
        if (layoutparams1.dodgeInsetEdges != 0 && view.getVisibility() == 0 && ViewCompat.isLaidOut(view) && view.getWidth() > 0 && view.getHeight() > 0)
        {
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            Behavior behavior1 = layoutparams.mBehavior;
            obj1 = (Rect)sRectPool.acquire();
            if (obj1 == null)
            {
                obj1 = new Rect();
            }
            obj3 = (Rect)sRectPool.acquire();
            obj2 = obj3;
            if (obj3 == null)
            {
                obj2 = new Rect();
            }
            ((Rect) (obj2)).set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            if (behavior1 != null && behavior1.getInsetDodgeRect(this, view, ((Rect) (obj1))))
            {
                if (!((Rect) (obj2)).contains(((Rect) (obj1))))
                {
                    throw new IllegalArgumentException((new StringBuilder("Rect should be within the child's bounds. Rect:")).append(((Rect) (obj1)).toShortString()).append(" | Bounds:").append(((Rect) (obj2)).toShortString()).toString());
                }
            } else
            {
                ((Rect) (obj1)).set(((Rect) (obj2)));
            }
            ((Rect) (obj2)).setEmpty();
            sRectPool.release(obj2);
            if (((Rect) (obj1)).isEmpty())
            {
                ((Rect) (obj1)).setEmpty();
                sRectPool.release(obj1);
            } else
            {
                int j2 = Gravity.getAbsoluteGravity(layoutparams.dodgeInsetEdges, k1);
                boolean flag2 = false;
                boolean flag = flag2;
                if ((j2 & 0x30) == 48)
                {
                    int i3 = ((Rect) (obj1)).top - layoutparams.topMargin - layoutparams.mInsetOffsetY;
                    flag = flag2;
                    if (i3 < rect.top)
                    {
                        setInsetOffsetY(view, rect.top - i3);
                        flag = true;
                    }
                }
                flag2 = flag;
                if ((j2 & 0x50) == 80)
                {
                    int j3 = (getHeight() - ((Rect) (obj1)).bottom - layoutparams.bottomMargin) + layoutparams.mInsetOffsetY;
                    flag2 = flag;
                    if (j3 < rect.bottom)
                    {
                        setInsetOffsetY(view, j3 - rect.bottom);
                        flag2 = true;
                    }
                }
                if (!flag2)
                {
                    setInsetOffsetY(view, 0);
                }
                flag2 = false;
                flag = flag2;
                if ((j2 & 3) == 3)
                {
                    int k3 = ((Rect) (obj1)).left - layoutparams.leftMargin - layoutparams.mInsetOffsetX;
                    flag = flag2;
                    if (k3 < rect.left)
                    {
                        setInsetOffsetX(view, rect.left - k3);
                        flag = true;
                    }
                }
                if ((j2 & 5) == 5)
                {
                    int j1 = getWidth();
                    int k2 = ((Rect) (obj1)).right;
                    int l3 = layoutparams.rightMargin;
                    j1 = layoutparams.mInsetOffsetX + (j1 - k2 - l3);
                    if (j1 < rect.right)
                    {
                        setInsetOffsetX(view, j1 - rect.right);
                        flag = true;
                    }
                }
                if (!flag)
                {
                    setInsetOffsetX(view, 0);
                }
                ((Rect) (obj1)).setEmpty();
                sRectPool.release(obj1);
            }
        }
        if (i != 2)
        {
            rect2.set(((LayoutParams)view.getLayoutParams()).mLastChildRect);
            if (rect2.equals(rect1))
            {
                continue; /* Loop/switch isn't completed */
            }
            ((LayoutParams)view.getLayoutParams()).mLastChildRect.set(rect1);
        }
        l = i1 + 1;
_L11:
        if (l >= l1)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj1 = (View)mDependencySortedChildren.get(l);
        obj2 = (LayoutParams)((View) (obj1)).getLayoutParams();
        obj3 = ((LayoutParams) (obj2)).mBehavior;
        if (obj3 != null && ((Behavior) (obj3))._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UP35EDKMERHFETKM8PR5EGNK6RRFE9I6IRJ1EHNN4J31F5NNAT1R9HGMSP3IDTKM8BRMD5INEBQMD5INEEQCC5N68SJFD5I2UTJ9CLRIULJ9CLRJMAAQ0(view))
        {
            if (i != 0 || !((LayoutParams) (obj2)).mDidChangeAfterNestedScroll)
            {
                break; /* Loop/switch isn't completed */
            }
            obj2.mDidChangeAfterNestedScroll = false;
        }
_L14:
        l++;
        if (true) goto _L11; else goto _L10
_L6:
        rect.top = Math.max(rect.top, rect1.bottom);
          goto _L5
_L7:
        rect.bottom = Math.max(rect.bottom, getHeight() - rect1.top);
          goto _L5
_L8:
        rect.left = Math.max(rect.left, rect1.right);
          goto _L4
_L9:
        rect.right = Math.max(rect.right, getWidth() - rect1.left);
          goto _L4
_L10:
        i;
        JVM INSTR tableswitch 2 2: default 1424
    //                   2 1451;
           goto _L12 _L13
_L12:
        boolean flag3 = ((Behavior) (obj3)).onDependentViewChanged(this, ((View) (obj1)), view);
_L15:
        if (i == 1)
        {
            obj2.mDidChangeAfterNestedScroll = flag3;
        }
          goto _L14
_L13:
        flag3 = true;
          goto _L15
        i1++;
          goto _L16
_L2:
        rect.setEmpty();
        sRectPool.release(rect);
        rect1.setEmpty();
        sRectPool.release(rect1);
        rect2.setEmpty();
        sRectPool.release(rect2);
        return;
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        resetTouchBehaviors(false);
        if (mNeedsPreDrawListener && mOnPreDrawListener != null)
        {
            getViewTreeObserver().removeOnPreDrawListener(mOnPreDrawListener);
        }
        if (mNestedScrollingTarget != null)
        {
            onStopNestedScroll(mNestedScrollingTarget);
        }
        mIsAttachedToWindow = false;
    }

    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (mDrawStatusBarBackground && mStatusBarBackground != null)
        {
            int i;
            if (mLastInsets != null)
            {
                i = ((WindowInsets)mLastInsets.mInsets).getSystemWindowInsetTop();
            } else
            {
                i = 0;
            }
            if (i > 0)
            {
                mStatusBarBackground.setBounds(0, 0, getWidth(), i);
                mStatusBarBackground.draw(canvas);
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        int i = motionevent.getActionMasked();
        if (i == 0)
        {
            resetTouchBehaviors(true);
        }
        boolean flag = performIntercept(motionevent, 0);
        if (i == 1 || i == 3)
        {
            resetTouchBehaviors(true);
        }
        return flag;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        j = ViewCompat.getLayoutDirection(this);
        k = mDependencySortedChildren.size();
        for (i = 0; i < k; i++)
        {
            View view = (View)mDependencySortedChildren.get(i);
            if (view.getVisibility() == 8)
            {
                continue;
            }
            Behavior behavior = ((LayoutParams)view.getLayoutParams()).mBehavior;
            if (behavior == null || !behavior.onLayoutChild(this, view, j))
            {
                onLayoutChild(view, j);
            }
        }

    }

    public final void onLayoutChild(View view, int i)
    {
        Object obj;
        Rect rect;
        Object obj1;
        obj = (LayoutParams)view.getLayoutParams();
        boolean flag;
        if (((LayoutParams) (obj)).mAnchorView == null && ((LayoutParams) (obj)).mAnchorId != -1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        }
        if (((LayoutParams) (obj)).mAnchorView == null)
        {
            break MISSING_BLOCK_LABEL_253;
        }
        obj1 = ((LayoutParams) (obj)).mAnchorView;
        obj = (Rect)sRectPool.acquire();
        if (obj == null)
        {
            obj = new Rect();
        }
        rect = (Rect)sRectPool.acquire();
        if (rect == null)
        {
            rect = new Rect();
        }
        ViewGroupUtils.getDescendantRect(this, ((View) (obj1)), ((Rect) (obj)));
        obj1 = (LayoutParams)view.getLayoutParams();
        int j = view.getMeasuredWidth();
        int l = view.getMeasuredHeight();
        getDesiredAnchoredChildRectWithoutConstraints$51662RJ4E9NMIP1FEPKMATPFAPKMATPR95662RJ4E9NMIP1FCTP62S38D5HN6BQICLHN8EQCC5N68SJFD5I2UPRIC5O6GQB3ECNL4PB3EGTKOOBECHP6UQB45TPNAS3GDTP78BR4CLPMIPRE5TRMIP37CLQ2UGRFDTP68QBEC5Q6USICC5SMUTBK4H662UBFELQ50OBIC5MN6EQ994KLC___0(i, ((Rect) (obj)), rect, ((LayoutParams) (obj1)), j, l);
        constrainChildRect(((LayoutParams) (obj1)), rect, j, l);
        view.layout(rect.left, rect.top, rect.right, rect.bottom);
        ((Rect) (obj)).setEmpty();
        sRectPool.release(obj);
        rect.setEmpty();
        sRectPool.release(rect);
        return;
        view;
        ((Rect) (obj)).setEmpty();
        sRectPool.release(obj);
        rect.setEmpty();
        sRectPool.release(rect);
        throw view;
        if (((LayoutParams) (obj)).keyline < 0) goto _L2; else goto _L1
_L1:
        int i1 = ((LayoutParams) (obj)).keyline;
        obj = (LayoutParams)view.getLayoutParams();
        int j1 = ((LayoutParams) (obj)).gravity;
        int k = j1;
        if (j1 == 0)
        {
            k = 0x800035;
        }
        k = Gravity.getAbsoluteGravity(k, i);
        int i2 = getWidth();
        int l1 = getHeight();
        j1 = view.getMeasuredWidth();
        int k1 = view.getMeasuredHeight();
        Rect rect1;
        LayoutParams layoutparams;
        if (i == 1)
        {
            i = i2 - i1;
        } else
        {
            i = i1;
        }
        i = getKeyline(i) - j1;
        k & 7;
        JVM INSTR lookupswitch 2: default 376
    //                   1: 502
    //                   5: 494;
           goto _L3 _L4 _L5
_L3:
        k & 0x70;
        JVM INSTR lookupswitch 2: default 408
    //                   16: 521
    //                   80: 512;
           goto _L6 _L7 _L8
_L6:
        k = 0;
_L9:
        i = Math.max(getPaddingLeft() + ((LayoutParams) (obj)).leftMargin, Math.min(i, i2 - getPaddingRight() - j1 - ((LayoutParams) (obj)).rightMargin));
        k = Math.max(getPaddingTop() + ((LayoutParams) (obj)).topMargin, Math.min(k, l1 - getPaddingBottom() - k1 - ((LayoutParams) (obj)).bottomMargin));
        view.layout(i, k, i + j1, k + k1);
        return;
_L5:
        i += j1;
        continue; /* Loop/switch isn't completed */
_L4:
        i += j1 / 2;
        continue; /* Loop/switch isn't completed */
_L8:
        k = k1 + 0;
        continue; /* Loop/switch isn't completed */
_L7:
        k = k1 / 2 + 0;
        if (true) goto _L9; else goto _L2
_L2:
        layoutparams = (LayoutParams)view.getLayoutParams();
        obj = (Rect)sRectPool.acquire();
        if (obj == null)
        {
            obj = new Rect();
        }
        ((Rect) (obj)).set(getPaddingLeft() + layoutparams.leftMargin, getPaddingTop() + layoutparams.topMargin, getWidth() - getPaddingRight() - layoutparams.rightMargin, getHeight() - getPaddingBottom() - layoutparams.bottomMargin);
        if (mLastInsets != null && ViewCompat.getFitsSystemWindows(this) && !ViewCompat.getFitsSystemWindows(view))
        {
            k = ((Rect) (obj)).left;
            obj.left = ((WindowInsets)mLastInsets.mInsets).getSystemWindowInsetLeft() + k;
            k = ((Rect) (obj)).top;
            obj.top = ((WindowInsets)mLastInsets.mInsets).getSystemWindowInsetTop() + k;
            obj.right = ((Rect) (obj)).right - ((WindowInsets)mLastInsets.mInsets).getSystemWindowInsetRight();
            obj.bottom = ((Rect) (obj)).bottom - ((WindowInsets)mLastInsets.mInsets).getSystemWindowInsetBottom();
        }
        rect1 = (Rect)sRectPool.acquire();
        if (rect1 == null)
        {
            rect1 = new Rect();
        }
        i1 = layoutparams.gravity;
        k = i1;
        if ((i1 & 7) == 0)
        {
            k = i1 | 0x800003;
        }
        i1 = k;
        if ((k & 0x70) == 0)
        {
            i1 = k | 0x30;
        }
        Gravity.apply(i1, view.getMeasuredWidth(), view.getMeasuredHeight(), ((Rect) (obj)), rect1, i);
        view.layout(rect1.left, rect1.top, rect1.right, rect1.bottom);
        ((Rect) (obj)).setEmpty();
        sRectPool.release(obj);
        rect1.setEmpty();
        sRectPool.release(rect1);
        return;
        if (true) goto _L3; else goto _L10
_L10:
    }

    protected void onMeasure(int i, int j)
    {
        int i1;
        int l1;
        mDependencySortedChildren.clear();
        DirectedAcyclicGraph directedacyclicgraph = mChildDag;
        i1 = directedacyclicgraph.mGraph.size();
        for (int k = 0; k < i1; k++)
        {
            ArrayList arraylist1 = (ArrayList)directedacyclicgraph.mGraph.mArray[(k << 1) + 1];
            if (arraylist1 != null)
            {
                arraylist1.clear();
                directedacyclicgraph.mListPool.release(arraylist1);
            }
        }

        directedacyclicgraph.mGraph.clear();
        l1 = getChildCount();
        i1 = 0;
_L23:
        Object obj1;
        LayoutParams layoutparams1;
        if (i1 >= l1)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = getChildAt(i1);
        layoutparams1 = getResolvedLayoutParams(((View) (obj1)));
        if (layoutparams1.mAnchorId != -1) goto _L2; else goto _L1
_L1:
        layoutparams1.mAnchorDirectChild = null;
        layoutparams1.mAnchorView = null;
_L17:
        int l;
        DirectedAcyclicGraph directedacyclicgraph1 = mChildDag;
        if (!directedacyclicgraph1.mGraph.containsKey(obj1))
        {
            directedacyclicgraph1.mGraph.put(obj1, null);
        }
        l = 0;
_L21:
        if (l >= l1) goto _L4; else goto _L3
_L3:
        View view3;
        if (l == i1)
        {
            continue; /* Loop/switch isn't completed */
        }
        view3 = getChildAt(l);
        if (view3 == layoutparams1.mAnchorDirectChild) goto _L6; else goto _L5
_L5:
        boolean flag;
        int j1 = ViewCompat.getLayoutDirection(this);
        int j2 = Gravity.getAbsoluteGravity(((LayoutParams)view3.getLayoutParams()).insetEdge, j1);
        Object obj;
        View view2;
        if (j2 != 0 && (Gravity.getAbsoluteGravity(layoutparams1.dodgeInsetEdges, j1) & j2) == j2)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag && (layoutparams1.mBehavior == null || !layoutparams1.mBehavior._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UP35EDKMERHFETKM8PR5EGNK6RRFE9I6IRJ1EHNN4J31F5NNAT1R9HGMSP3IDTKM8BRMD5INEBQMD5INEEQCC5N68SJFD5I2UTJ9CLRIULJ9CLRJMAAQ0(view3))) goto _L7; else goto _L6
_L6:
        flag = true;
        break MISSING_BLOCK_LABEL_284;
_L2:
        if (layoutparams1.mAnchorView == null) goto _L9; else goto _L8
_L8:
        if (layoutparams1.mAnchorView.getId() == layoutparams1.mAnchorId) goto _L11; else goto _L10
_L10:
        l = 0;
_L18:
        if (l != 0) goto _L12; else goto _L9
_L9:
        layoutparams1.mAnchorView = findViewById(layoutparams1.mAnchorId);
        if (layoutparams1.mAnchorView == null)
        {
            break MISSING_BLOCK_LABEL_659;
        }
        if (layoutparams1.mAnchorView != this) goto _L14; else goto _L13
_L13:
        if (!isInEditMode()) goto _L16; else goto _L15
_L15:
        layoutparams1.mAnchorDirectChild = null;
        layoutparams1.mAnchorView = null;
_L12:
        obj = layoutparams1.mAnchorView;
          goto _L17
_L11:
        view2 = layoutparams1.mAnchorView;
        obj = layoutparams1.mAnchorView.getParent();
_L19:
        if (obj != this)
        {
label0:
            {
                if (obj != null && obj != obj1)
                {
                    break label0;
                }
                layoutparams1.mAnchorDirectChild = null;
                layoutparams1.mAnchorView = null;
                l = 0;
            }
        } else
        {
            layoutparams1.mAnchorDirectChild = view2;
            l = 1;
        }
          goto _L18
        if (obj instanceof View)
        {
            view2 = (View)obj;
        }
        obj = ((ViewParent) (obj)).getParent();
          goto _L19
_L16:
        throw new IllegalStateException("View can not be anchored to the the parent CoordinatorLayout");
_L14:
        view2 = layoutparams1.mAnchorView;
        obj = layoutparams1.mAnchorView.getParent();
_L20:
        if (obj != this && obj != null)
        {
label1:
            {
                if (obj != obj1)
                {
                    break label1;
                }
                if (isInEditMode())
                {
                    layoutparams1.mAnchorDirectChild = null;
                    layoutparams1.mAnchorView = null;
                } else
                {
                    throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
                }
            }
        } else
        {
            layoutparams1.mAnchorDirectChild = view2;
        }
          goto _L12
        if (obj instanceof View)
        {
            view2 = (View)obj;
        }
        obj = ((ViewParent) (obj)).getParent();
          goto _L20
        if (isInEditMode())
        {
            layoutparams1.mAnchorDirectChild = null;
            layoutparams1.mAnchorView = null;
        } else
        {
            throw new IllegalStateException((new StringBuilder("Could not find CoordinatorLayout descendant view with id ")).append(getResources().getResourceName(layoutparams1.mAnchorId)).append(" to anchor view ").append(obj1).toString());
        }
          goto _L12
_L7:
        flag = false;
        if (!flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (!mChildDag.mGraph.containsKey(view3))
        {
            obj = mChildDag;
            if (!((DirectedAcyclicGraph) (obj)).mGraph.containsKey(view3))
            {
                ((DirectedAcyclicGraph) (obj)).mGraph.put(view3, null);
            }
        }
        DirectedAcyclicGraph directedacyclicgraph4 = mChildDag;
        if (!directedacyclicgraph4.mGraph.containsKey(view3) || !directedacyclicgraph4.mGraph.containsKey(obj1))
        {
            throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
        }
        ArrayList arraylist2 = (ArrayList)directedacyclicgraph4.mGraph.get(view3);
        ArrayList arraylist = arraylist2;
        if (arraylist2 == null)
        {
            ArrayList arraylist3 = (ArrayList)directedacyclicgraph4.mListPool.acquire();
            arraylist = arraylist3;
            if (arraylist3 == null)
            {
                arraylist = new ArrayList();
            }
            directedacyclicgraph4.mGraph.put(view3, arraylist);
        }
        arraylist.add(obj1);
        l++;
          goto _L21
_L4:
        i1++;
        if (true) goto _L23; else goto _L22
_L22:
        int k1;
        List list = mDependencySortedChildren;
        DirectedAcyclicGraph directedacyclicgraph2 = mChildDag;
        directedacyclicgraph2.mSortResult.clear();
        directedacyclicgraph2.mSortTmpMarked.clear();
        l = 0;
        for (i1 = directedacyclicgraph2.mGraph.size(); l < i1; l++)
        {
            directedacyclicgraph2.dfs(directedacyclicgraph2.mGraph.mArray[l << 1], directedacyclicgraph2.mSortResult, directedacyclicgraph2.mSortTmpMarked);
        }

        list.addAll(directedacyclicgraph2.mSortResult);
        Collections.reverse(mDependencySortedChildren);
        k1 = getChildCount();
        l = 0;
_L42:
        View view;
        DirectedAcyclicGraph directedacyclicgraph3;
        if (l >= k1)
        {
            break MISSING_BLOCK_LABEL_1739;
        }
        view = getChildAt(l);
        directedacyclicgraph3 = mChildDag;
        l1 = directedacyclicgraph3.mGraph.size();
        i1 = 0;
_L40:
        if (i1 >= l1) goto _L25; else goto _L24
_L24:
        obj1 = (ArrayList)directedacyclicgraph3.mGraph.mArray[(i1 << 1) + 1];
        if (obj1 == null || !((ArrayList) (obj1)).contains(view)) goto _L27; else goto _L26
_L26:
        i1 = 1;
_L41:
        if (i1 == 0) goto _L29; else goto _L28
_L28:
        boolean flag3 = true;
_L47:
        boolean flag1;
        int l2;
        int i3;
        int j3;
        int l5;
        View view1;
        LayoutParams layoutparams;
        Behavior behavior;
        boolean flag2;
        int k2;
        int k3;
        int l3;
        int i4;
        int j4;
        int k4;
        int l4;
        int i5;
        int j5;
        int k5;
        int i6;
        if (flag3 != mNeedsPreDrawListener)
        {
            if (flag3)
            {
                if (mIsAttachedToWindow)
                {
                    if (mOnPreDrawListener == null)
                    {
                        mOnPreDrawListener = new OnPreDrawListener();
                    }
                    getViewTreeObserver().addOnPreDrawListener(mOnPreDrawListener);
                }
                mNeedsPreDrawListener = true;
            } else
            {
                if (mIsAttachedToWindow && mOnPreDrawListener != null)
                {
                    getViewTreeObserver().removeOnPreDrawListener(mOnPreDrawListener);
                }
                mNeedsPreDrawListener = false;
            }
        }
        j3 = getPaddingLeft();
        k3 = getPaddingTop();
        l3 = getPaddingRight();
        i4 = getPaddingBottom();
        j4 = ViewCompat.getLayoutDirection(this);
        if (j4 == 1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        k4 = android.view.View.MeasureSpec.getMode(i);
        l4 = android.view.View.MeasureSpec.getSize(i);
        i5 = android.view.View.MeasureSpec.getMode(j);
        j5 = android.view.View.MeasureSpec.getSize(j);
        k1 = getSuggestedMinimumWidth();
        i1 = getSuggestedMinimumHeight();
        l = 0;
        if (mLastInsets != null && ViewCompat.getFitsSystemWindows(this))
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        k5 = mDependencySortedChildren.size();
        k2 = 0;
_L39:
        if (k2 >= k5) goto _L31; else goto _L30
_L30:
        view1 = (View)mDependencySortedChildren.get(k2);
        if (view1.getVisibility() == 8) goto _L33; else goto _L32
_L32:
        layoutparams = (LayoutParams)view1.getLayoutParams();
        l2 = 0;
        l1 = l2;
        if (layoutparams.keyline < 0) goto _L35; else goto _L34
_L34:
        l1 = l2;
        if (k4 == 0) goto _L35; else goto _L36
_L36:
        l5 = getKeyline(layoutparams.keyline);
        i3 = layoutparams.gravity;
        l1 = i3;
        if (i3 == 0)
        {
            l1 = 0x800035;
        }
        i3 = Gravity.getAbsoluteGravity(l1, j4) & 7;
        if ((i3 != 3 || flag1) && (i3 != 5 || !flag1)) goto _L38; else goto _L37
_L37:
        l1 = Math.max(0, l4 - l3 - l5);
_L35:
        if (flag2 && !ViewCompat.getFitsSystemWindows(view1))
        {
            l2 = ((WindowInsets)mLastInsets.mInsets).getSystemWindowInsetLeft();
            i6 = ((WindowInsets)mLastInsets.mInsets).getSystemWindowInsetRight();
            i3 = ((WindowInsets)mLastInsets.mInsets).getSystemWindowInsetTop();
            l5 = ((WindowInsets)mLastInsets.mInsets).getSystemWindowInsetBottom();
            l2 = android.view.View.MeasureSpec.makeMeasureSpec(l4 - (l2 + i6), k4);
            i3 = android.view.View.MeasureSpec.makeMeasureSpec(j5 - (l5 + i3), i5);
        } else
        {
            i3 = j;
            l2 = i;
        }
        behavior = layoutparams.mBehavior;
        if (behavior == null || !behavior.onMeasureChild(this, view1, l2, l1, i3, 0))
        {
            measureChildWithMargins(view1, l2, l1, i3, 0);
        }
        l1 = Math.max(k1, view1.getMeasuredWidth() + (j3 + l3) + layoutparams.leftMargin + layoutparams.rightMargin);
        i1 = Math.max(i1, view1.getMeasuredHeight() + (k3 + i4) + layoutparams.topMargin + layoutparams.bottomMargin);
        k1 = View.combineMeasuredStates(l, view1.getMeasuredState());
        l = l1;
_L46:
        k2++;
        l1 = l;
        l = k1;
        k1 = l1;
          goto _L39
_L27:
        i1++;
          goto _L40
_L25:
        i1 = 0;
          goto _L41
_L29:
        l++;
          goto _L42
_L38:
        if (i3 == 5 && !flag1) goto _L44; else goto _L43
_L43:
        l1 = l2;
        if (i3 != 3) goto _L35; else goto _L45
_L45:
        l1 = l2;
        if (!flag1) goto _L35; else goto _L44
_L44:
        l1 = Math.max(0, l5 - j3);
          goto _L35
_L31:
        setMeasuredDimension(View.resolveSizeAndState(k1, i, 0xff000000 & l), View.resolveSizeAndState(i1, j, l << 16));
        return;
_L33:
        int i2 = k1;
        k1 = l;
        l = i2;
          goto _L46
        flag3 = false;
          goto _L47
    }

    public final void onMeasureChild(View view, int i, int j, int k, int l)
    {
        measureChildWithMargins(view, i, j, k, l);
    }

    public boolean onNestedFling(View view, float f, float f1, boolean flag)
    {
        int j = getChildCount();
        for (int i = 0; i < j; i++)
        {
            view = getChildAt(i);
            if (view.getVisibility() != 8)
            {
                view.getLayoutParams();
            }
        }

        return false;
    }

    public boolean onNestedPreFling(View view, float f, float f1)
    {
        int j = getChildCount();
        int i = 0;
        boolean flag = false;
        for (; i < j; i++)
        {
            View view1 = getChildAt(i);
            if (view1.getVisibility() == 8)
            {
                continue;
            }
            Object obj = (LayoutParams)view1.getLayoutParams();
            if (!((LayoutParams) (obj)).isNestedScrollAccepted(0))
            {
                continue;
            }
            obj = ((LayoutParams) (obj)).mBehavior;
            if (obj != null)
            {
                flag = ((Behavior) (obj)).onNestedPreFling(this, view1, view, f, f1) | flag;
            }
        }

        return flag;
    }

    public void onNestedPreScroll(View view, int i, int j, int ai[])
    {
        onNestedPreScroll(view, i, j, ai, 0);
    }

    public final void onNestedPreScroll(View view, int i, int j, int ai[], int k)
    {
        int l;
        int i1;
        int j1;
        int k1;
        int j2;
        i1 = 0;
        l = 0;
        j1 = 0;
        j2 = getChildCount();
        k1 = 0;
_L2:
        if (k1 >= j2)
        {
            break; /* Loop/switch isn't completed */
        }
        View view1 = getChildAt(k1);
        if (view1.getVisibility() == 8)
        {
            break MISSING_BLOCK_LABEL_235;
        }
        Object obj = (LayoutParams)view1.getLayoutParams();
        if (!((LayoutParams) (obj)).isNestedScrollAccepted(k))
        {
            break MISSING_BLOCK_LABEL_235;
        }
        obj = ((LayoutParams) (obj)).mBehavior;
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_235;
        }
        int ai1[] = mTempIntPair;
        mTempIntPair[1] = 0;
        ai1[0] = 0;
        ((Behavior) (obj)).onNestedPreScroll(this, view1, view, i, j, mTempIntPair, k);
        int l1;
        if (i > 0)
        {
            i1 = Math.max(i1, mTempIntPair[0]);
        } else
        {
            i1 = Math.min(i1, mTempIntPair[0]);
        }
        if (j > 0)
        {
            l = Math.max(l, mTempIntPair[1]);
        } else
        {
            l = Math.min(l, mTempIntPair[1]);
        }
        l1 = 1;
        j1 = l;
        l = l1;
_L3:
        l1 = k1 + 1;
        k1 = j1;
        j1 = l;
        l = k1;
        k1 = l1;
        if (true) goto _L2; else goto _L1
_L1:
        ai[0] = i1;
        ai[1] = l;
        if (j1 != 0)
        {
            onChildViewsChanged(1);
        }
        return;
        int i2 = l;
        l = j1;
        j1 = i2;
          goto _L3
    }

    public void onNestedScroll(View view, int i, int j, int k, int l)
    {
        onNestedScroll(view, i, j, k, l, 0);
    }

    public final void onNestedScroll(View view, int i, int j, int k, int l, int i1)
    {
        int k1 = getChildCount();
        boolean flag = false;
        for (int j1 = 0; j1 < k1; j1++)
        {
            View view1 = getChildAt(j1);
            if (view1.getVisibility() == 8)
            {
                continue;
            }
            Object obj = (LayoutParams)view1.getLayoutParams();
            if (!((LayoutParams) (obj)).isNestedScrollAccepted(i1))
            {
                continue;
            }
            obj = ((LayoutParams) (obj)).mBehavior;
            if (obj != null)
            {
                ((Behavior) (obj)).onNestedScroll(this, view1, view, i, j, k, l, i1);
                flag = true;
            }
        }

        if (flag)
        {
            onChildViewsChanged(1);
        }
    }

    public void onNestedScrollAccepted(View view, View view1, int i)
    {
        onNestedScrollAccepted(view, view1, i, 0);
    }

    public final void onNestedScrollAccepted(View view, View view1, int i, int j)
    {
        mNestedScrollingParentHelper.mNestedScrollAxes = i;
        mNestedScrollingTarget = view1;
        j = getChildCount();
        for (i = 0; i < j; i++)
        {
            getChildAt(i).getLayoutParams();
        }

    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        if (!(parcelable instanceof SavedState))
        {
            super.onRestoreInstanceState(parcelable);
        } else
        {
            parcelable = (SavedState)parcelable;
            super.onRestoreInstanceState(((AbsSavedState) (parcelable)).mSuperState);
            parcelable = ((SavedState) (parcelable)).behaviorStates;
            int j = getChildCount();
            int i = 0;
            while (i < j) 
            {
                View view = getChildAt(i);
                int k = view.getId();
                Behavior behavior = getResolvedLayoutParams(view).mBehavior;
                if (k != -1 && behavior != null)
                {
                    Parcelable parcelable1 = (Parcelable)parcelable.get(k);
                    if (parcelable1 != null)
                    {
                        behavior.onRestoreInstanceState(this, view, parcelable1);
                    }
                }
                i++;
            }
        }
    }

    protected Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        SparseArray sparsearray = new SparseArray();
        int j = getChildCount();
        for (int i = 0; i < j; i++)
        {
            Object obj = getChildAt(i);
            int k = ((View) (obj)).getId();
            Behavior behavior = ((LayoutParams)((View) (obj)).getLayoutParams()).mBehavior;
            if (k == -1 || behavior == null)
            {
                continue;
            }
            obj = behavior.onSaveInstanceState(this, ((View) (obj)));
            if (obj != null)
            {
                sparsearray.append(k, obj);
            }
        }

        savedstate.behaviorStates = sparsearray;
        return savedstate;
    }

    public boolean onStartNestedScroll(View view, View view1, int i)
    {
        return onStartNestedScroll(view, view1, i, 0);
    }

    public final boolean onStartNestedScroll(View view, View view1, int i, int j)
    {
        boolean flag = false;
        int l = getChildCount();
        int k = 0;
        while (k < l) 
        {
            View view2 = getChildAt(k);
            if (view2.getVisibility() != 8)
            {
                LayoutParams layoutparams = (LayoutParams)view2.getLayoutParams();
                Behavior behavior = layoutparams.mBehavior;
                if (behavior != null)
                {
                    boolean flag1 = behavior.onStartNestedScroll(this, view2, view, view1, i, j);
                    flag |= flag1;
                    layoutparams.setNestedScrollAccepted(j, flag1);
                } else
                {
                    layoutparams.setNestedScrollAccepted(j, false);
                }
            }
            k++;
        }
        return flag;
    }

    public void onStopNestedScroll(View view)
    {
        onStopNestedScroll(view, 0);
    }

    public final void onStopNestedScroll(View view, int i)
    {
        mNestedScrollingParentHelper.mNestedScrollAxes = 0;
        int k = getChildCount();
        for (int j = 0; j < k; j++)
        {
            View view1 = getChildAt(j);
            LayoutParams layoutparams = (LayoutParams)view1.getLayoutParams();
            if (!layoutparams.isNestedScrollAccepted(i))
            {
                continue;
            }
            Behavior behavior = layoutparams.mBehavior;
            if (behavior != null)
            {
                behavior.onStopNestedScroll(this, view1, view, i);
            }
            layoutparams.setNestedScrollAccepted(i, false);
            layoutparams.mDidChangeAfterNestedScroll = false;
        }

        mNestedScrollingTarget = null;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i = motionevent.getActionMasked();
        if (mBehaviorTouchView != null) goto _L2; else goto _L1
_L1:
        boolean flag1 = performIntercept(motionevent, 1);
        if (!flag1) goto _L4; else goto _L3
_L3:
        boolean flag;
        Behavior behavior = ((LayoutParams)mBehaviorTouchView.getLayoutParams()).mBehavior;
        long l;
        if (behavior != null)
        {
            flag = behavior.onTouchEvent(this, mBehaviorTouchView, motionevent);
        } else
        {
            flag = false;
        }
_L5:
        if (mBehaviorTouchView == null)
        {
            flag |= super.onTouchEvent(motionevent);
            motionevent = null;
        } else
        if (flag1)
        {
            l = SystemClock.uptimeMillis();
            motionevent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
            super.onTouchEvent(motionevent);
        } else
        {
            motionevent = null;
        }
        if (motionevent != null)
        {
            motionevent.recycle();
        }
        if (i == 1 || i == 3)
        {
            resetTouchBehaviors(false);
        }
        return flag;
_L4:
        flag = false;
        if (true) goto _L5; else goto _L2
_L2:
        flag1 = false;
        if (true) goto _L3; else goto _L6
_L6:
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean flag)
    {
        Behavior behavior = ((LayoutParams)view.getLayoutParams()).mBehavior;
        if (behavior != null && behavior.onRequestChildRectangleOnScreen(this, view, rect, flag))
        {
            return true;
        } else
        {
            return super.requestChildRectangleOnScreen(view, rect, flag);
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean flag)
    {
        super.requestDisallowInterceptTouchEvent(flag);
        if (flag && !mDisallowInterceptReset)
        {
            resetTouchBehaviors(false);
            mDisallowInterceptReset = true;
        }
    }

    public void setFitsSystemWindows(boolean flag)
    {
        super.setFitsSystemWindows(flag);
        setupForInsets();
    }

    public void setOnHierarchyChangeListener(android.view.ViewGroup.OnHierarchyChangeListener onhierarchychangelistener)
    {
        mOnHierarchyChangeListener = onhierarchychangelistener;
    }

    public void setStatusBarBackgroundColor(int i)
    {
        setStatusBarBackground(new ColorDrawable(i));
    }

    public void setStatusBarBackgroundResource(int i)
    {
        Drawable drawable;
        if (i != 0)
        {
            drawable = ContextCompat.getDrawable(getContext(), i);
        } else
        {
            drawable = null;
        }
        setStatusBarBackground(drawable);
    }

    public void setVisibility(int i)
    {
        super.setVisibility(i);
        boolean flag;
        if (i == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (mStatusBarBackground != null && mStatusBarBackground.isVisible() != flag)
        {
            mStatusBarBackground.setVisible(flag, false);
        }
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        return super.verifyDrawable(drawable) || drawable == mStatusBarBackground;
    }

    static 
    {
        Object obj = android/support/design/widget/CoordinatorLayout.getPackage();
        if (obj != null)
        {
            obj = ((Package) (obj)).getName();
        } else
        {
            obj = null;
        }
        WIDGET_PACKAGE_NAME = ((String) (obj));
    }

    private class _cls1
        implements OnApplyWindowInsetsListener
    {

        private final CoordinatorLayout this$0;

        public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowinsetscompat)
        {
            boolean flag1 = true;
            int i = 0;
            CoordinatorLayout coordinatorlayout = CoordinatorLayout.this;
            if (!Objects.equals(coordinatorlayout.mLastInsets, windowinsetscompat))
            {
                coordinatorlayout.mLastInsets = windowinsetscompat;
                boolean flag;
                if (windowinsetscompat != null && ((WindowInsets)windowinsetscompat.mInsets).getSystemWindowInsetTop() > 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                coordinatorlayout.mDrawStatusBarBackground = flag;
                if (!coordinatorlayout.mDrawStatusBarBackground && coordinatorlayout.getBackground() == null)
                {
                    flag = flag1;
                } else
                {
                    flag = false;
                }
                coordinatorlayout.setWillNotDraw(flag);
                if (!((WindowInsets)windowinsetscompat.mInsets).isConsumed())
                {
                    int j = coordinatorlayout.getChildCount();
                    view = windowinsetscompat;
                    do
                    {
                        windowinsetscompat = view;
                        if (i >= j)
                        {
                            break;
                        }
                        windowinsetscompat = coordinatorlayout.getChildAt(i);
                        if (ViewCompat.getFitsSystemWindows(windowinsetscompat) && ((LayoutParams)windowinsetscompat.getLayoutParams()).mBehavior != null)
                        {
                            windowinsetscompat = view;
                            if (((WindowInsets)((WindowInsetsCompat) (view)).mInsets).isConsumed())
                            {
                                break;
                            }
                        }
                        i++;
                    } while (true);
                }
                coordinatorlayout.requestLayout();
                return windowinsetscompat;
            } else
            {
                return windowinsetscompat;
            }
        }

        _cls1()
        {
            this$0 = CoordinatorLayout.this;
            super();
        }
    }

}
