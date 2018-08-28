// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package android.support.v4.widget:
//            ViewDragHelper

public class DrawerLayout extends ViewGroup
{
    final class AccessibilityDelegate extends AccessibilityDelegateCompat
    {

        private final Rect mTmpRect = new Rect();
        private final DrawerLayout this$0;

        public final boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
        {
            if (accessibilityevent.getEventType() == 32)
            {
                view = accessibilityevent.getText();
                accessibilityevent = findVisibleDrawer();
                if (accessibilityevent != null)
                {
                    DrawerLayout drawerlayout = DrawerLayout.this;
                    if (Gravity.getAbsoluteGravity(Gravity.getAbsoluteGravity(((LayoutParams)accessibilityevent.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(drawerlayout)), ViewCompat.getLayoutDirection(DrawerLayout.this)) == 3);
                    if (false)
                    {
                        view.add(null);
                    }
                }
                return true;
            } else
            {
                return super.dispatchPopulateAccessibilityEvent(view, accessibilityevent);
            }
        }

        public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
        {
            super.onInitializeAccessibilityEvent(view, accessibilityevent);
            accessibilityevent.setClassName(android/support/v4/widget/DrawerLayout.getName());
        }

        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            if (DrawerLayout.CAN_HIDE_DESCENDANTS)
            {
                super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
            } else
            {
                AccessibilityNodeInfoCompat accessibilitynodeinfocompat1 = new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain(accessibilitynodeinfocompat.mInfo));
                super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat1);
                accessibilitynodeinfocompat.mInfo.setSource(view);
                Object obj = ViewCompat.getParentForAccessibility(view);
                if (obj instanceof View)
                {
                    obj = (View)obj;
                    accessibilitynodeinfocompat.mInfo.setParent(((View) (obj)));
                }
                obj = mTmpRect;
                accessibilitynodeinfocompat1.mInfo.getBoundsInParent(((Rect) (obj)));
                accessibilitynodeinfocompat.mInfo.setBoundsInParent(((Rect) (obj)));
                accessibilitynodeinfocompat1.mInfo.getBoundsInScreen(((Rect) (obj)));
                accessibilitynodeinfocompat.mInfo.setBoundsInScreen(((Rect) (obj)));
                boolean flag = accessibilitynodeinfocompat1.mInfo.isVisibleToUser();
                accessibilitynodeinfocompat.mInfo.setVisibleToUser(flag);
                obj = accessibilitynodeinfocompat1.mInfo.getPackageName();
                accessibilitynodeinfocompat.mInfo.setPackageName(((CharSequence) (obj)));
                obj = accessibilitynodeinfocompat1.mInfo.getClassName();
                accessibilitynodeinfocompat.mInfo.setClassName(((CharSequence) (obj)));
                obj = accessibilitynodeinfocompat1.mInfo.getContentDescription();
                accessibilitynodeinfocompat.mInfo.setContentDescription(((CharSequence) (obj)));
                flag = accessibilitynodeinfocompat1.mInfo.isEnabled();
                accessibilitynodeinfocompat.mInfo.setEnabled(flag);
                flag = accessibilitynodeinfocompat1.mInfo.isClickable();
                accessibilitynodeinfocompat.mInfo.setClickable(flag);
                flag = accessibilitynodeinfocompat1.mInfo.isFocusable();
                accessibilitynodeinfocompat.mInfo.setFocusable(flag);
                flag = accessibilitynodeinfocompat1.mInfo.isFocused();
                accessibilitynodeinfocompat.mInfo.setFocused(flag);
                flag = accessibilitynodeinfocompat1.mInfo.isAccessibilityFocused();
                accessibilitynodeinfocompat.mInfo.setAccessibilityFocused(flag);
                flag = accessibilitynodeinfocompat1.mInfo.isSelected();
                accessibilitynodeinfocompat.mInfo.setSelected(flag);
                flag = accessibilitynodeinfocompat1.mInfo.isLongClickable();
                accessibilitynodeinfocompat.mInfo.setLongClickable(flag);
                int i = accessibilitynodeinfocompat1.mInfo.getActions();
                accessibilitynodeinfocompat.mInfo.addAction(i);
                accessibilitynodeinfocompat1.mInfo.recycle();
                view = (ViewGroup)view;
                int j = view.getChildCount();
                i = 0;
                while (i < j) 
                {
                    View view1 = view.getChildAt(i);
                    if (DrawerLayout.includeChildForAccessibility(view1))
                    {
                        accessibilitynodeinfocompat.mInfo.addChild(view1);
                    }
                    i++;
                }
            }
            view = android/support/v4/widget/DrawerLayout.getName();
            accessibilitynodeinfocompat.mInfo.setClassName(view);
            accessibilitynodeinfocompat.mInfo.setFocusable(false);
            accessibilitynodeinfocompat.mInfo.setFocused(false);
            view = android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_FOCUS;
            accessibilitynodeinfocompat.mInfo.removeAction((android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction)((android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat) (view)).mAction);
            view = android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLEAR_FOCUS;
            accessibilitynodeinfocompat.mInfo.removeAction((android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction)((android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat) (view)).mAction);
        }

        public final boolean onRequestSendAccessibilityEvent(ViewGroup viewgroup, View view, AccessibilityEvent accessibilityevent)
        {
            if (DrawerLayout.CAN_HIDE_DESCENDANTS || DrawerLayout.includeChildForAccessibility(view))
            {
                return super.onRequestSendAccessibilityEvent(viewgroup, view, accessibilityevent);
            } else
            {
                return false;
            }
        }

        AccessibilityDelegate()
        {
            this$0 = DrawerLayout.this;
            super();
        }
    }

    static final class ChildAccessibilityDelegate extends AccessibilityDelegateCompat
    {

        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
            if (!DrawerLayout.includeChildForAccessibility(view))
            {
                accessibilitynodeinfocompat.mInfo.setParent(null);
            }
        }

        ChildAccessibilityDelegate()
        {
        }
    }

    public static interface DrawerListener
    {

        public abstract void onDrawerClosed(View view);

        public abstract void onDrawerOpened(View view);

        public abstract void onDrawerSlide$51662RJ4E9NMIP1FEPKMATPFAPKMATPR8OKLC___0(float f);

        public abstract void onDrawerStateChanged$514IILG_0();
    }

    public static final class LayoutParams extends android.view.ViewGroup.MarginLayoutParams
    {

        public int gravity;
        public boolean isPeeking;
        public float onScreen;
        public int openState;

        public LayoutParams(int i, int j)
        {
            super(-1, -1);
            gravity = 0;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            gravity = 0;
            context = context.obtainStyledAttributes(attributeset, DrawerLayout.LAYOUT_ATTRS);
            gravity = context.getInt(0, 0);
            context.recycle();
        }

        public LayoutParams(LayoutParams layoutparams)
        {
            super(layoutparams);
            gravity = 0;
            gravity = layoutparams.gravity;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            gravity = 0;
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
            gravity = 0;
        }
    }

    public static final class SavedState extends AbsSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();
        public int lockModeEnd;
        public int lockModeLeft;
        public int lockModeRight;
        public int lockModeStart;
        public int openDrawerGravity;

        public final void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(openDrawerGravity);
            parcel.writeInt(lockModeLeft);
            parcel.writeInt(lockModeRight);
            parcel.writeInt(lockModeStart);
            parcel.writeInt(lockModeEnd);
        }


        public SavedState(Parcel parcel, ClassLoader classloader)
        {
            super(parcel, classloader);
            openDrawerGravity = 0;
            openDrawerGravity = parcel.readInt();
            lockModeLeft = parcel.readInt();
            lockModeRight = parcel.readInt();
            lockModeStart = parcel.readInt();
            lockModeEnd = parcel.readInt();
        }

        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
            openDrawerGravity = 0;
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

    final class ViewDragCallback extends ViewDragHelper.Callback
    {

        public final int mAbsGravity;
        public ViewDragHelper mDragger;
        public final Runnable mPeekRunnable = new _cls1();
        public final DrawerLayout this$0;

        public final int clampViewPositionHorizontal$51662RJ4E9NMIP1FEPKMATPFAPKMATPR954III8_0(View view, int i)
        {
            if (checkDrawerViewAbsoluteGravity(view, 3))
            {
                return Math.max(-view.getWidth(), Math.min(i, 0));
            } else
            {
                int j = getWidth();
                return Math.max(j - view.getWidth(), Math.min(i, j));
            }
        }

        public final int clampViewPositionVertical$51662RJ4E9NMIP1FEPKMATPFAPKMATPR954III8_0(View view, int i)
        {
            return view.getTop();
        }

        final void closeOtherDrawer()
        {
            byte byte0 = 3;
            if (mAbsGravity == 3)
            {
                byte0 = 5;
            }
            View view = findDrawerWithGravity(byte0);
            if (view != null)
            {
                closeDrawer(view, true);
            }
        }

        public final int getViewHorizontalDragRange(View view)
        {
            if (DrawerLayout.isDrawerView(view))
            {
                return view.getWidth();
            } else
            {
                return 0;
            }
        }

        public final void onEdgeDragStarted(int i, int j)
        {
            View view;
            if ((i & 1) == 1)
            {
                view = findDrawerWithGravity(3);
            } else
            {
                view = findDrawerWithGravity(5);
            }
            if (view != null && getDrawerLockMode(view) == 0)
            {
                mDragger.captureChildView(view, j);
            }
        }

        public final void onEdgeTouched$514KIAAM0()
        {
            postDelayed(mPeekRunnable, 160L);
        }

        public final void onViewCaptured(View view, int i)
        {
            ((LayoutParams)view.getLayoutParams()).isPeeking = false;
            closeOtherDrawer();
        }

        public final void onViewDragStateChanged(int i)
        {
            updateDrawerState$514KIJ31DPI74RR9CGNNCQB5ESNLCQB5ESTIILG_0(i, mDragger.mCapturedView);
        }

        public final void onViewPositionChanged$51662RJ4E9NMIP1FEPKMATPFAPKMATPR954KII99AO______0(View view, int i, int j)
        {
            j = view.getWidth();
            float f;
            if (checkDrawerViewAbsoluteGravity(view, 3))
            {
                f = (float)(j + i) / (float)j;
            } else
            {
                f = (float)(getWidth() - i) / (float)j;
            }
            setDrawerViewOffset(view, f);
            if (f == 0.0F)
            {
                i = 4;
            } else
            {
                i = 0;
            }
            view.setVisibility(i);
            invalidate();
        }

        public final void onViewReleased(View view, float f, float f1)
        {
            int k;
            f1 = DrawerLayout.getDrawerViewOffset(view);
            k = view.getWidth();
            if (!checkDrawerViewAbsoluteGravity(view, 3)) goto _L2; else goto _L1
_L1:
            int i;
            if (f > 0.0F || f == 0.0F && f1 > 0.5F)
            {
                i = 0;
            } else
            {
                i = -k;
            }
_L4:
            mDragger.settleCapturedViewAt(i, view.getTop());
            invalidate();
            return;
_L2:
            int j = getWidth();
            if (f >= 0.0F)
            {
                i = j;
                if (f != 0.0F)
                {
                    continue; /* Loop/switch isn't completed */
                }
                i = j;
                if (f1 <= 0.5F)
                {
                    continue; /* Loop/switch isn't completed */
                }
            }
            i = j - k;
            if (true) goto _L4; else goto _L3
_L3:
        }

        public final boolean tryCaptureView(View view, int i)
        {
            return DrawerLayout.isDrawerView(view) && checkDrawerViewAbsoluteGravity(view, mAbsGravity) && getDrawerLockMode(view) == 0;
        }

        ViewDragCallback(int i)
        {
            this$0 = DrawerLayout.this;
            super();
            class _cls1
                implements Runnable
            {

                private final ViewDragCallback this$1;

                public final void run()
                {
                    boolean flag = false;
                    Object obj1 = ViewDragCallback.this;
                    int l = ((ViewDragCallback) (obj1)).mDragger.mEdgeSize;
                    Object obj;
                    int j;
                    int k;
                    if (((ViewDragCallback) (obj1)).mAbsGravity == 3)
                    {
                        j = 1;
                    } else
                    {
                        j = 0;
                    }
                    if (j != 0)
                    {
                        obj = ((ViewDragCallback) (obj1))._fld0.findDrawerWithGravity(3);
                        LayoutParams layoutparams;
                        long l1;
                        if (obj != null)
                        {
                            k = -((View) (obj)).getWidth();
                        } else
                        {
                            k = 0;
                        }
                        k += l;
                    } else
                    {
                        obj = ((ViewDragCallback) (obj1))._fld0.findDrawerWithGravity(5);
                        k = ((ViewDragCallback) (obj1))._fld0.getWidth();
                        k -= l;
                    }
                    if (obj != null && (j != 0 && ((View) (obj)).getLeft() < k || j == 0 && ((View) (obj)).getLeft() > k) && ((ViewDragCallback) (obj1))._fld0.getDrawerLockMode(((View) (obj))) == 0)
                    {
                        layoutparams = (LayoutParams)((View) (obj)).getLayoutParams();
                        ((ViewDragCallback) (obj1)).mDragger.smoothSlideViewTo(((View) (obj)), k, ((View) (obj)).getTop());
                        layoutparams.isPeeking = true;
                        ((ViewDragCallback) (obj1))._fld0.invalidate();
                        ((ViewDragCallback) (obj1)).closeOtherDrawer();
                        obj = ((ViewDragCallback) (obj1))._fld0;
                        if (!((DrawerLayout) (obj)).mChildrenCanceledTouch)
                        {
                            l1 = SystemClock.uptimeMillis();
                            obj1 = MotionEvent.obtain(l1, l1, 3, 0.0F, 0.0F, 0);
                            k = ((DrawerLayout) (obj)).getChildCount();
                            for (j = ((flag) ? 1 : 0); j < k; j++)
                            {
                                ((DrawerLayout) (obj)).getChildAt(j).dispatchTouchEvent(((MotionEvent) (obj1)));
                            }

                            ((MotionEvent) (obj1)).recycle();
                            obj.mChildrenCanceledTouch = true;
                        }
                    }
                }

                _cls1()
                {
                    this$1 = ViewDragCallback.this;
                    super();
                }
            }

            mAbsGravity = i;
        }
    }


    public static final boolean CAN_HIDE_DESCENDANTS = true;
    public static final int LAYOUT_ATTRS[] = {
        0x10100b3
    };
    private static final boolean SET_DRAWER_SHADOW_FROM_ELEVATION = true;
    private static final int THEME_ATTRS[] = {
        0x1010434
    };
    private final ChildAccessibilityDelegate mChildAccessibilityDelegate;
    private Rect mChildHitRect;
    private Matrix mChildInvertedMatrix;
    public boolean mChildrenCanceledTouch;
    public boolean mDrawStatusBarBackground;
    private float mDrawerElevation;
    private int mDrawerState;
    private boolean mFirstLayout;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    public Object mLastInsets;
    private final ViewDragCallback mLeftCallback;
    private final ViewDragHelper mLeftDragger;
    public DrawerListener mListener;
    public List mListeners;
    private int mLockModeEnd;
    private int mLockModeLeft;
    private int mLockModeRight;
    private int mLockModeStart;
    private int mMinDrawerMargin;
    private final ArrayList mNonDrawerViews;
    private final ViewDragCallback mRightCallback;
    private final ViewDragHelper mRightDragger;
    private int mScrimColor;
    private float mScrimOpacity;
    private Paint mScrimPaint;
    private Drawable mShadowEnd;
    private Drawable mShadowLeft;
    private Drawable mShadowLeftResolved;
    private Drawable mShadowRight;
    private Drawable mShadowRightResolved;
    private Drawable mShadowStart;
    private Drawable mStatusBarBackground;

    public DrawerLayout(Context context)
    {
        this(context, null);
    }

    public DrawerLayout(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public DrawerLayout(Context context, AttributeSet attributeset, int i)
    {
        float f;
        super(context, attributeset, i);
        mChildAccessibilityDelegate = new ChildAccessibilityDelegate();
        mScrimColor = 0x99000000;
        mScrimPaint = new Paint();
        mFirstLayout = true;
        mLockModeLeft = 3;
        mLockModeRight = 3;
        mLockModeStart = 3;
        mLockModeEnd = 3;
        mShadowStart = null;
        mShadowEnd = null;
        mShadowLeft = null;
        mShadowRight = null;
        setDescendantFocusability(0x40000);
        f = getResources().getDisplayMetrics().density;
        mMinDrawerMargin = (int)(64F * f + 0.5F);
        float f1 = 400F * f;
        mLeftCallback = new ViewDragCallback(3);
        mRightCallback = new ViewDragCallback(5);
        mLeftDragger = ViewDragHelper.create(this, 1.0F, mLeftCallback);
        mLeftDragger.mTrackingEdges = 1;
        mLeftDragger.mMinVelocity = f1;
        mLeftCallback.mDragger = mLeftDragger;
        mRightDragger = ViewDragHelper.create(this, 1.0F, mRightCallback);
        mRightDragger.mTrackingEdges = 2;
        mRightDragger.mMinVelocity = f1;
        mRightCallback.mDragger = mRightDragger;
        setFocusableInTouchMode(true);
        ViewCompat.setImportantForAccessibility(this, 1);
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegate());
        setMotionEventSplittingEnabled(false);
        if (!ViewCompat.getFitsSystemWindows(this))
        {
            break MISSING_BLOCK_LABEL_300;
        }
        setOnApplyWindowInsetsListener(new _cls1());
        setSystemUiVisibility(1280);
        context = context.obtainStyledAttributes(THEME_ATTRS);
        mStatusBarBackground = context.getDrawable(0);
        context.recycle();
        mDrawerElevation = f * 10F;
        mNonDrawerViews = new ArrayList();
        return;
        attributeset;
        context.recycle();
        throw attributeset;
    }

    private final View findOpenDrawer()
    {
        int j = getChildCount();
        for (int i = 0; i < j; i++)
        {
            View view = getChildAt(i);
            if ((((LayoutParams)view.getLayoutParams()).openState & 1) == 1)
            {
                return view;
            }
        }

        return null;
    }

    static float getDrawerViewOffset(View view)
    {
        return ((LayoutParams)view.getLayoutParams()).onScreen;
    }

    static boolean includeChildForAccessibility(View view)
    {
        return ViewCompat.getImportantForAccessibility(view) != 4 && ViewCompat.getImportantForAccessibility(view) != 2;
    }

    static boolean isDrawerView(View view)
    {
        int i = Gravity.getAbsoluteGravity(((LayoutParams)view.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(view));
        if ((i & 3) != 0)
        {
            return true;
        }
        return (i & 5) != 0;
    }

    private final void moveDrawerToOffset(View view, float f)
    {
        float f1 = ((LayoutParams)view.getLayoutParams()).onScreen;
        int i = view.getWidth();
        int j = (int)(f1 * (float)i);
        i = (int)((float)i * f) - j;
        if (!checkDrawerViewAbsoluteGravity(view, 3))
        {
            i = -i;
        }
        view.offsetLeftAndRight(i);
        setDrawerViewOffset(view, f);
    }

    private final void openDrawer(View view, boolean flag)
    {
        if (!isDrawerView(view))
        {
            throw new IllegalArgumentException((new StringBuilder("View ")).append(view).append(" is not a sliding drawer").toString());
        }
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if (mFirstLayout)
        {
            layoutparams.onScreen = 1.0F;
            layoutparams.openState = 1;
            updateChildrenImportantForAccessibility(view, true);
        } else
        if (flag)
        {
            layoutparams.openState = layoutparams.openState | 2;
            if (checkDrawerViewAbsoluteGravity(view, 3))
            {
                mLeftDragger.smoothSlideViewTo(view, 0, view.getTop());
            } else
            {
                mRightDragger.smoothSlideViewTo(view, getWidth() - view.getWidth(), view.getTop());
            }
        } else
        {
            moveDrawerToOffset(view, 1.0F);
            updateDrawerState$514KIJ31DPI74RR9CGNNCQB5ESNLCQB5ESTIILG_0(0, view);
            view.setVisibility(0);
        }
        invalidate();
    }

    private final void setDrawerLockMode(int i, int j)
    {
        int k = Gravity.getAbsoluteGravity(j, ViewCompat.getLayoutDirection(this));
        j;
        JVM INSTR lookupswitch 4: default 52
    //                   3: 185
    //                   5: 193
    //                   8388611: 201
    //                   8388613: 209;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        if (i != 0)
        {
            ViewDragHelper viewdraghelper;
            if (k == 3)
            {
                viewdraghelper = mLeftDragger;
            } else
            {
                viewdraghelper = mRightDragger;
            }
            viewdraghelper.mActivePointerId = -1;
            if (viewdraghelper.mInitialMotionX != null)
            {
                Arrays.fill(viewdraghelper.mInitialMotionX, 0.0F);
                Arrays.fill(viewdraghelper.mInitialMotionY, 0.0F);
                Arrays.fill(viewdraghelper.mLastMotionX, 0.0F);
                Arrays.fill(viewdraghelper.mLastMotionY, 0.0F);
                Arrays.fill(viewdraghelper.mInitialEdgesTouched, 0);
                Arrays.fill(viewdraghelper.mEdgeDragsInProgress, 0);
                Arrays.fill(viewdraghelper.mEdgeDragsLocked, 0);
                viewdraghelper.mPointersDown = 0;
            }
            if (viewdraghelper.mVelocityTracker != null)
            {
                viewdraghelper.mVelocityTracker.recycle();
                viewdraghelper.mVelocityTracker = null;
            }
        }
        i;
        JVM INSTR tableswitch 1 2: default 184
    //                   1 243
    //                   2 225;
           goto _L6 _L7 _L8
_L6:
        break; /* Loop/switch isn't completed */
_L7:
        break MISSING_BLOCK_LABEL_250;
_L9:
        return;
_L2:
        mLockModeLeft = i;
          goto _L1
_L3:
        mLockModeRight = i;
          goto _L1
_L4:
        mLockModeStart = i;
          goto _L1
_L5:
        View view;
        mLockModeEnd = i;
          goto _L1
_L8:
        if ((view = findDrawerWithGravity(k)) != null)
        {
            openDrawer(view, true);
            return;
        }
          goto _L9
        if ((view = findDrawerWithGravity(k)) != null)
        {
            closeDrawer(view, true);
            return;
        }
          goto _L9
    }

    private final void updateChildrenImportantForAccessibility(View view, boolean flag)
    {
        int j = getChildCount();
        int i = 0;
        while (i < j) 
        {
            View view1 = getChildAt(i);
            if (!flag && !isDrawerView(view1) || flag && view1 == view)
            {
                ViewCompat.setImportantForAccessibility(view1, 1);
            } else
            {
                ViewCompat.setImportantForAccessibility(view1, 4);
            }
            i++;
        }
    }

    public void addFocusables(ArrayList arraylist, int i, int j)
    {
        boolean flag1 = false;
        if (getDescendantFocusability() == 0x60000)
        {
            return;
        }
        int j1 = getChildCount();
        int k = 0;
        boolean flag = false;
        while (k < j1) 
        {
            View view = getChildAt(k);
            if (isDrawerView(view))
            {
                if (isDrawerOpen(view))
                {
                    flag = true;
                    view.addFocusables(arraylist, i, j);
                }
            } else
            {
                mNonDrawerViews.add(view);
            }
            k++;
        }
        if (!flag)
        {
            int i1 = mNonDrawerViews.size();
            for (int l = ((flag1) ? 1 : 0); l < i1; l++)
            {
                View view1 = (View)mNonDrawerViews.get(l);
                if (view1.getVisibility() == 0)
                {
                    view1.addFocusables(arraylist, i, j);
                }
            }

        }
        mNonDrawerViews.clear();
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        super.addView(view, i, layoutparams);
        if (findOpenDrawer() != null || isDrawerView(view))
        {
            ViewCompat.setImportantForAccessibility(view, 4);
        } else
        {
            ViewCompat.setImportantForAccessibility(view, 1);
        }
        if (!CAN_HIDE_DESCENDANTS)
        {
            ViewCompat.setAccessibilityDelegate(view, mChildAccessibilityDelegate);
        }
    }

    final boolean checkDrawerViewAbsoluteGravity(View view, int i)
    {
        return (Gravity.getAbsoluteGravity(((LayoutParams)view.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(this)) & i) == i;
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return (layoutparams instanceof LayoutParams) && super.checkLayoutParams(layoutparams);
    }

    public final void closeDrawer(int i)
    {
        Object obj = findDrawerWithGravity(i);
        if (obj == null)
        {
            StringBuilder stringbuilder = new StringBuilder("No drawer view found with gravity ");
            if ((i & 3) == 3)
            {
                obj = "LEFT";
            } else
            if ((i & 5) == 5)
            {
                obj = "RIGHT";
            } else
            {
                obj = Integer.toHexString(i);
            }
            throw new IllegalArgumentException(stringbuilder.append(((String) (obj))).toString());
        } else
        {
            closeDrawer(((View) (obj)), true);
            return;
        }
    }

    public final void closeDrawer(View view, boolean flag)
    {
        if (!isDrawerView(view))
        {
            throw new IllegalArgumentException((new StringBuilder("View ")).append(view).append(" is not a sliding drawer").toString());
        }
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if (mFirstLayout)
        {
            layoutparams.onScreen = 0.0F;
            layoutparams.openState = 0;
        } else
        if (flag)
        {
            layoutparams.openState = layoutparams.openState | 4;
            if (checkDrawerViewAbsoluteGravity(view, 3))
            {
                mLeftDragger.smoothSlideViewTo(view, -view.getWidth(), view.getTop());
            } else
            {
                mRightDragger.smoothSlideViewTo(view, getWidth(), view.getTop());
            }
        } else
        {
            moveDrawerToOffset(view, 0.0F);
            updateDrawerState$514KIJ31DPI74RR9CGNNCQB5ESNLCQB5ESTIILG_0(0, view);
            view.setVisibility(4);
        }
        invalidate();
    }

    public final void closeDrawers(boolean flag)
    {
        int l = getChildCount();
        int j = 0;
        int i = 0;
        while (j < l) 
        {
label0:
            {
                View view = getChildAt(j);
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                int k = i;
                if (!isDrawerView(view))
                {
                    break label0;
                }
                if (flag)
                {
                    k = i;
                    if (!layoutparams.isPeeking)
                    {
                        break label0;
                    }
                }
                k = view.getWidth();
                if (checkDrawerViewAbsoluteGravity(view, 3))
                {
                    i |= mLeftDragger.smoothSlideViewTo(view, -k, view.getTop());
                } else
                {
                    i |= mRightDragger.smoothSlideViewTo(view, getWidth(), view.getTop());
                }
                layoutparams.isPeeking = false;
                k = i;
            }
            j++;
            i = k;
        }
        ViewDragCallback viewdragcallback = mLeftCallback;
        viewdragcallback._fld0.removeCallbacks(viewdragcallback.mPeekRunnable);
        viewdragcallback = mRightCallback;
        viewdragcallback._fld0.removeCallbacks(viewdragcallback.mPeekRunnable);
        if (i != 0)
        {
            invalidate();
        }
    }

    public void computeScroll()
    {
        int j = getChildCount();
        float f = 0.0F;
        for (int i = 0; i < j; i++)
        {
            f = Math.max(f, ((LayoutParams)getChildAt(i).getLayoutParams()).onScreen);
        }

        mScrimOpacity = f;
        boolean flag = mLeftDragger.continueSettling(true);
        boolean flag1 = mRightDragger.continueSettling(true);
        if (flag || flag1)
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionevent)
    {
        if ((motionevent.getSource() & 2) == 0 || motionevent.getAction() == 10 || mScrimOpacity <= 0.0F)
        {
            return super.dispatchGenericMotionEvent(motionevent);
        }
        int i = getChildCount();
        if (i != 0)
        {
            float f = motionevent.getX();
            float f1 = motionevent.getY();
            float f3;
            float f5;
            View view;
            boolean flag;
            boolean flag1;
            for (i--; i >= 0; i--)
            {
                view = getChildAt(i);
                if (mChildHitRect == null)
                {
                    mChildHitRect = new Rect();
                }
                view.getHitRect(mChildHitRect);
                if (!mChildHitRect.contains((int)f, (int)f1))
                {
                    continue;
                }
                if (((LayoutParams)view.getLayoutParams()).gravity == 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    continue;
                }
                if (!view.getMatrix().isIdentity())
                {
                    float f2 = getScrollX() - view.getLeft();
                    float f4 = getScrollY() - view.getTop();
                    MotionEvent motionevent1 = MotionEvent.obtain(motionevent);
                    motionevent1.offsetLocation(f2, f4);
                    Matrix matrix = view.getMatrix();
                    if (!matrix.isIdentity())
                    {
                        if (mChildInvertedMatrix == null)
                        {
                            mChildInvertedMatrix = new Matrix();
                        }
                        matrix.invert(mChildInvertedMatrix);
                        motionevent1.transform(mChildInvertedMatrix);
                    }
                    flag1 = view.dispatchGenericMotionEvent(motionevent1);
                    motionevent1.recycle();
                } else
                {
                    f3 = getScrollX() - view.getLeft();
                    f5 = getScrollY() - view.getTop();
                    motionevent.offsetLocation(f3, f5);
                    flag1 = view.dispatchGenericMotionEvent(motionevent);
                    motionevent.offsetLocation(-f3, -f5);
                }
                if (flag1)
                {
                    return true;
                }
            }

        }
        return false;
    }

    protected boolean drawChild(Canvas canvas, View view, long l)
    {
        View view1;
        int i;
        int j1;
        int i2;
        boolean flag;
        int k3;
        int j4;
        int i4 = getHeight();
        Drawable drawable;
        int j3;
        int k4;
        if (((LayoutParams)view.getLayoutParams()).gravity == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        j1 = 0;
        k3 = 0;
        i = getWidth();
        j4 = canvas.save();
        i2 = i;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_260;
        }
        k4 = getChildCount();
        j3 = 0;
        j1 = k3;
        if (j3 >= k4)
        {
            break MISSING_BLOCK_LABEL_242;
        }
        view1 = getChildAt(j3);
        if (view1 == view || view1.getVisibility() != 0) goto _L2; else goto _L1
_L1:
        drawable = view1.getBackground();
        if (drawable != null)
        {
            if (drawable.getOpacity() == -1)
            {
                i2 = 1;
            } else
            {
                i2 = 0;
            }
        } else
        {
            i2 = 0;
        }
        if (i2 == 0 || !isDrawerView(view1) || view1.getHeight() < i4) goto _L2; else goto _L3
_L3:
        if (!checkDrawerViewAbsoluteGravity(view1, 3)) goto _L5; else goto _L4
_L4:
        i2 = view1.getRight();
        if (i2 > j1)
        {
            j1 = i2;
        }
        k3 = j1;
        i2 = i;
_L7:
        j3++;
        i = i2;
        j1 = k3;
        break MISSING_BLOCK_LABEL_62;
_L5:
        int l3 = view1.getLeft();
        i2 = l3;
        k3 = j1;
        if (l3 < i)
        {
            continue; /* Loop/switch isn't completed */
        }
_L2:
        i2 = i;
        k3 = j1;
        if (true) goto _L7; else goto _L6
_L6:
        canvas.clipRect(j1, 0, i, getHeight());
        i2 = i;
        boolean flag1 = super.drawChild(canvas, view, l);
        canvas.restoreToCount(j4);
        if (mScrimOpacity > 0.0F && flag)
        {
            int j = (int)((float)((mScrimColor & 0xff000000) >>> 24) * mScrimOpacity);
            int l2 = mScrimColor;
            mScrimPaint.setColor(j << 24 | l2 & 0xffffff);
            canvas.drawRect(j1, 0.0F, i2, getHeight(), mScrimPaint);
        } else
        {
            if (mShadowLeftResolved != null && checkDrawerViewAbsoluteGravity(view, 3))
            {
                int k = mShadowLeftResolved.getIntrinsicWidth();
                int k1 = view.getRight();
                int j2 = mLeftDragger.mEdgeSize;
                float f = Math.max(0.0F, Math.min((float)k1 / (float)j2, 1.0F));
                mShadowLeftResolved.setBounds(k1, view.getTop(), k + k1, view.getBottom());
                mShadowLeftResolved.setAlpha((int)(255F * f));
                mShadowLeftResolved.draw(canvas);
                return flag1;
            }
            if (mShadowRightResolved != null && checkDrawerViewAbsoluteGravity(view, 5))
            {
                int i1 = mShadowRightResolved.getIntrinsicWidth();
                int l1 = view.getLeft();
                int k2 = getWidth();
                int i3 = mRightDragger.mEdgeSize;
                float f1 = Math.max(0.0F, Math.min((float)(k2 - l1) / (float)i3, 1.0F));
                mShadowRightResolved.setBounds(l1 - i1, view.getTop(), l1, view.getBottom());
                mShadowRightResolved.setAlpha((int)(255F * f1));
                mShadowRightResolved.draw(canvas);
                return flag1;
            }
        }
        return flag1;
    }

    public final View findDrawerWithGravity(int i)
    {
        int j = Gravity.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this));
        int k = getChildCount();
        for (i = 0; i < k; i++)
        {
            View view = getChildAt(i);
            if ((Gravity.getAbsoluteGravity(((LayoutParams)view.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(this)) & 7) == (j & 7))
            {
                return view;
            }
        }

        return null;
    }

    final View findVisibleDrawer()
    {
        int j = getChildCount();
        for (int i = 0; i < j; i++)
        {
            View view = getChildAt(i);
            if (isDrawerView(view) && isDrawerVisible(view))
            {
                return view;
            }
        }

        return null;
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(-1, -1);
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

    public final int getDrawerLockMode(int i)
    {
        int j = ViewCompat.getLayoutDirection(this);
        i;
        JVM INSTR lookupswitch 4: default 48
    //                   3: 50
    //                   5: 87
    //                   8388611: 124
    //                   8388613: 161;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return 0;
_L2:
        if (mLockModeLeft != 3)
        {
            return mLockModeLeft;
        }
        if (j == 0)
        {
            i = mLockModeStart;
        } else
        {
            i = mLockModeEnd;
        }
        if (i != 3)
        {
            return i;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (mLockModeRight != 3)
        {
            return mLockModeRight;
        }
        if (j == 0)
        {
            i = mLockModeEnd;
        } else
        {
            i = mLockModeStart;
        }
        if (i != 3)
        {
            return i;
        }
        if (true) goto _L1; else goto _L4
_L4:
        if (mLockModeStart != 3)
        {
            return mLockModeStart;
        }
        if (j == 0)
        {
            i = mLockModeLeft;
        } else
        {
            i = mLockModeRight;
        }
        if (i != 3)
        {
            return i;
        }
        if (true)
        {
            continue; /* Loop/switch isn't completed */
        }
_L5:
        if (mLockModeEnd != 3)
        {
            return mLockModeEnd;
        }
        if (j == 0)
        {
            i = mLockModeRight;
        } else
        {
            i = mLockModeLeft;
        }
        if (i != 3)
        {
            return i;
        }
        if (true) goto _L1; else goto _L6
_L6:
    }

    public final int getDrawerLockMode(View view)
    {
        if (!isDrawerView(view))
        {
            throw new IllegalArgumentException((new StringBuilder("View ")).append(view).append(" is not a drawer").toString());
        } else
        {
            return getDrawerLockMode(((LayoutParams)view.getLayoutParams()).gravity);
        }
    }

    public final boolean isDrawerOpen(View view)
    {
        if (!isDrawerView(view))
        {
            throw new IllegalArgumentException((new StringBuilder("View ")).append(view).append(" is not a drawer").toString());
        }
        return (((LayoutParams)view.getLayoutParams()).openState & 1) == 1;
    }

    public final boolean isDrawerVisible(View view)
    {
        if (!isDrawerView(view))
        {
            throw new IllegalArgumentException((new StringBuilder("View ")).append(view).append(" is not a drawer").toString());
        }
        return ((LayoutParams)view.getLayoutParams()).onScreen > 0.0F;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        mFirstLayout = true;
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        mFirstLayout = true;
    }

    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (mDrawStatusBarBackground && mStatusBarBackground != null)
        {
            int i;
            if (mLastInsets != null)
            {
                i = ((WindowInsets)mLastInsets).getSystemWindowInsetTop();
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
        int i;
        boolean flag;
        boolean flag1;
        boolean flag2;
        flag = false;
        i = motionevent.getActionMasked();
        flag1 = mLeftDragger.shouldInterceptTouchEvent(motionevent);
        flag2 = mRightDragger.shouldInterceptTouchEvent(motionevent);
        i;
        JVM INSTR tableswitch 0 3: default 60
    //                   0 131
    //                   1 398
    //                   2 214
    //                   3 398;
           goto _L1 _L2 _L3 _L4 _L3
_L1:
        i = 0;
_L12:
        if (flag2 | flag1 || i != 0) goto _L6; else goto _L5
_L5:
        int j;
        j = getChildCount();
        i = 0;
_L20:
        if (i >= j) goto _L8; else goto _L7
_L7:
        if (!((LayoutParams)getChildAt(i).getLayoutParams()).isPeeking) goto _L10; else goto _L9
_L9:
        i = 1;
_L21:
        if (i == 0 && !mChildrenCanceledTouch) goto _L11; else goto _L6
_L6:
        flag = true;
_L11:
        return flag;
_L2:
        float f = motionevent.getX();
        float f2 = motionevent.getY();
        mInitialMotionX = f;
        mInitialMotionY = f2;
        if (mScrimOpacity <= 0.0F)
        {
            break MISSING_BLOCK_LABEL_426;
        }
        motionevent = mLeftDragger.findTopChildUnder((int)f, (int)f2);
        if (motionevent == null)
        {
            break MISSING_BLOCK_LABEL_426;
        }
        if (((LayoutParams)motionevent.getLayoutParams()).gravity == 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_426;
        }
        i = 1;
_L22:
        mChildrenCanceledTouch = false;
          goto _L12
_L4:
        int k;
        motionevent = mLeftDragger;
        k = ((ViewDragHelper) (motionevent)).mInitialMotionX.length;
        j = 0;
_L18:
        if (j >= k) goto _L14; else goto _L13
_L13:
        if ((((ViewDragHelper) (motionevent)).mPointersDown & 1 << j) != 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            float f1 = ((ViewDragHelper) (motionevent)).mLastMotionX[j] - ((ViewDragHelper) (motionevent)).mInitialMotionX[j];
            float f3 = ((ViewDragHelper) (motionevent)).mLastMotionY[j] - ((ViewDragHelper) (motionevent)).mInitialMotionY[j];
            if (f1 * f1 + f3 * f3 > (float)(((ViewDragHelper) (motionevent)).mTouchSlop * ((ViewDragHelper) (motionevent)).mTouchSlop))
            {
                i = 1;
            } else
            {
                i = 0;
            }
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L16; else goto _L15
_L15:
        i = 1;
_L19:
        if (i == 0) goto _L1; else goto _L17
_L17:
        motionevent = mLeftCallback;
        ((ViewDragCallback) (motionevent))._fld0.removeCallbacks(((ViewDragCallback) (motionevent)).mPeekRunnable);
        motionevent = mRightCallback;
        ((ViewDragCallback) (motionevent))._fld0.removeCallbacks(((ViewDragCallback) (motionevent)).mPeekRunnable);
        i = 0;
          goto _L12
_L16:
        j++;
          goto _L18
_L14:
        i = 0;
          goto _L19
_L3:
        closeDrawers(true);
        mChildrenCanceledTouch = false;
          goto _L1
_L10:
        i++;
          goto _L20
_L8:
        i = 0;
          goto _L21
        i = 0;
          goto _L22
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if (i == 4)
        {
            boolean flag;
            if (findVisibleDrawer() != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                keyevent.startTracking();
                return true;
            }
        }
        return super.onKeyDown(i, keyevent);
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        boolean flag = false;
        if (i == 4)
        {
            keyevent = findVisibleDrawer();
            if (keyevent != null && getDrawerLockMode(keyevent) == 0)
            {
                closeDrawers(false);
            }
            if (keyevent != null)
            {
                flag = true;
            }
            return flag;
        } else
        {
            return super.onKeyUp(i, keyevent);
        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int k1;
        int l1;
        mInLayout = true;
        k1 = k - i;
        l1 = getChildCount();
        k = 0;
_L2:
        View view;
        LayoutParams layoutparams;
        if (k >= l1)
        {
            break MISSING_BLOCK_LABEL_462;
        }
        view = getChildAt(k);
        if (view.getVisibility() != 8)
        {
            layoutparams = (LayoutParams)view.getLayoutParams();
            if (((LayoutParams)view.getLayoutParams()).gravity == 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                break; /* Loop/switch isn't completed */
            }
            view.layout(layoutparams.leftMargin, layoutparams.topMargin, layoutparams.leftMargin + view.getMeasuredWidth(), layoutparams.topMargin + view.getMeasuredHeight());
        }
_L6:
        k++;
        if (true) goto _L2; else goto _L1
_L1:
        int i1;
        int i2;
        int j2;
        i2 = view.getMeasuredWidth();
        j2 = view.getMeasuredHeight();
        float f;
        boolean flag1;
        if (checkDrawerViewAbsoluteGravity(view, 3))
        {
            i = -i2;
            i1 = (int)((float)i2 * layoutparams.onScreen) + i;
            f = (float)(i2 + i1) / (float)i2;
        } else
        {
            i1 = k1 - (int)((float)i2 * layoutparams.onScreen);
            f = (float)(k1 - i1) / (float)i2;
        }
        if (f != layoutparams.onScreen)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        layoutparams.gravity & 0x70;
        JVM INSTR lookupswitch 2: default 228
    //                   16: 372
    //                   80: 332;
           goto _L3 _L4 _L5
_L4:
        break MISSING_BLOCK_LABEL_372;
_L3:
        view.layout(i1, layoutparams.topMargin, i2 + i1, j2 + layoutparams.topMargin);
_L7:
        if (flag1)
        {
            setDrawerViewOffset(view, f);
        }
        int j1;
        int k2;
        if (layoutparams.onScreen > 0.0F)
        {
            i = 0;
        } else
        {
            i = 4;
        }
        if (view.getVisibility() != i)
        {
            view.setVisibility(i);
        }
          goto _L6
_L5:
        i = l - j;
        view.layout(i1, i - layoutparams.bottomMargin - view.getMeasuredHeight(), i2 + i1, i - layoutparams.bottomMargin);
          goto _L7
        k2 = l - j;
        j1 = (k2 - j2) / 2;
        if (j1 < layoutparams.topMargin)
        {
            i = layoutparams.topMargin;
        } else
        {
            i = j1;
            if (j1 + j2 > k2 - layoutparams.bottomMargin)
            {
                i = k2 - layoutparams.bottomMargin - j2;
            }
        }
        view.layout(i1, i, i2 + i1, j2 + i);
          goto _L7
        mInLayout = false;
        mFirstLayout = false;
        return;
          goto _L6
    }

    protected void onMeasure(int i, int j)
    {
        int j1;
        int k1;
        int l1;
        int i2;
        i2 = android.view.View.MeasureSpec.getMode(i);
        l1 = android.view.View.MeasureSpec.getMode(j);
        j1 = android.view.View.MeasureSpec.getSize(i);
        k1 = android.view.View.MeasureSpec.getSize(j);
        if (i2 != 0x40000000) goto _L2; else goto _L1
_L1:
        int i1 = j1;
        if (l1 == 0x40000000) goto _L3; else goto _L2
_L2:
        int l;
        boolean flag;
        int l2;
        int i3;
        if (isInEditMode())
        {
            int k = j1;
            if (i2 != 0x80000000)
            {
                k = j1;
                if (i2 == 0)
                {
                    k = 300;
                }
            }
            i1 = k;
            if (l1 != 0x80000000)
            {
label0:
                {
                    i1 = k;
                    if (l1 == 0)
                    {
                        j1 = 300;
                        k1 = k;
                        break label0;
                    }
                }
            }
        } else
        {
            throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
        }
          goto _L3
_L5:
        setMeasuredDimension(k1, j1);
        if (mLastInsets != null && ViewCompat.getFitsSystemWindows(this))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        l2 = ViewCompat.getLayoutDirection(this);
        l = 0;
        i1 = 0;
        i3 = getChildCount();
        i2 = 0;
        while (i2 < i3) 
        {
            View view = getChildAt(i2);
            int j2 = i1;
            int k2 = l;
            if (view.getVisibility() != 8)
            {
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                if (flag)
                {
                    j2 = Gravity.getAbsoluteGravity(layoutparams.gravity, l2);
                    if (ViewCompat.getFitsSystemWindows(view))
                    {
                        WindowInsets windowinsets2 = (WindowInsets)mLastInsets;
                        WindowInsets windowinsets;
                        if (j2 == 3)
                        {
                            windowinsets = windowinsets2.replaceSystemWindowInsets(windowinsets2.getSystemWindowInsetLeft(), windowinsets2.getSystemWindowInsetTop(), 0, windowinsets2.getSystemWindowInsetBottom());
                        } else
                        {
                            windowinsets = windowinsets2;
                            if (j2 == 5)
                            {
                                windowinsets = windowinsets2.replaceSystemWindowInsets(0, windowinsets2.getSystemWindowInsetTop(), windowinsets2.getSystemWindowInsetRight(), windowinsets2.getSystemWindowInsetBottom());
                            }
                        }
                        view.dispatchApplyWindowInsets(windowinsets);
                    } else
                    {
                        WindowInsets windowinsets3 = (WindowInsets)mLastInsets;
                        WindowInsets windowinsets1;
                        if (j2 == 3)
                        {
                            windowinsets1 = windowinsets3.replaceSystemWindowInsets(windowinsets3.getSystemWindowInsetLeft(), windowinsets3.getSystemWindowInsetTop(), 0, windowinsets3.getSystemWindowInsetBottom());
                        } else
                        {
                            windowinsets1 = windowinsets3;
                            if (j2 == 5)
                            {
                                windowinsets1 = windowinsets3.replaceSystemWindowInsets(0, windowinsets3.getSystemWindowInsetTop(), windowinsets3.getSystemWindowInsetRight(), windowinsets3.getSystemWindowInsetBottom());
                            }
                        }
                        layoutparams.leftMargin = windowinsets1.getSystemWindowInsetLeft();
                        layoutparams.topMargin = windowinsets1.getSystemWindowInsetTop();
                        layoutparams.rightMargin = windowinsets1.getSystemWindowInsetRight();
                        layoutparams.bottomMargin = windowinsets1.getSystemWindowInsetBottom();
                    }
                }
                if (((LayoutParams)view.getLayoutParams()).gravity == 0)
                {
                    j2 = 1;
                } else
                {
                    j2 = 0;
                }
                if (j2 != 0)
                {
                    view.measure(android.view.View.MeasureSpec.makeMeasureSpec(k1 - layoutparams.leftMargin - layoutparams.rightMargin, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(j1 - layoutparams.topMargin - layoutparams.bottomMargin, 0x40000000));
                    k2 = l;
                    j2 = i1;
                } else
                if (isDrawerView(view))
                {
                    if (SET_DRAWER_SHADOW_FROM_ELEVATION && ViewCompat.getElevation(view) != mDrawerElevation)
                    {
                        ViewCompat.setElevation(view, mDrawerElevation);
                    }
                    k2 = Gravity.getAbsoluteGravity(((LayoutParams)view.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(this)) & 7;
                    if (k2 == 3)
                    {
                        j2 = 1;
                    } else
                    {
                        j2 = 0;
                    }
                    if (j2 != 0 && l != 0 || j2 == 0 && i1 != 0)
                    {
                        StringBuilder stringbuilder = new StringBuilder("Child drawer has absolute gravity ");
                        String s;
                        if ((k2 & 3) == 3)
                        {
                            s = "LEFT";
                        } else
                        if ((k2 & 5) == 5)
                        {
                            s = "RIGHT";
                        } else
                        {
                            s = Integer.toHexString(k2);
                        }
                        throw new IllegalStateException(stringbuilder.append(s).append(" but this DrawerLayout").append(" already has a drawer view along that edge").toString());
                    }
                    if (j2 != 0)
                    {
                        j2 = 1;
                        l = i1;
                        i1 = j2;
                    } else
                    {
                        boolean flag1 = true;
                        i1 = l;
                        l = ((flag1) ? 1 : 0);
                    }
                    view.measure(getChildMeasureSpec(i, mMinDrawerMargin + layoutparams.leftMargin + layoutparams.rightMargin, layoutparams.width), getChildMeasureSpec(j, layoutparams.topMargin + layoutparams.bottomMargin, layoutparams.height));
                    j2 = l;
                    k2 = i1;
                } else
                {
                    throw new IllegalStateException((new StringBuilder("Child ")).append(view).append(" at index ").append(i2).append(" does not have a valid layout_gravity - must be Gravity.LEFT, Gravity.RIGHT or Gravity.NO_GRAVITY").toString());
                }
            }
            i2++;
            i1 = j2;
            l = k2;
        }
        return;
_L3:
        j1 = k1;
        k1 = i1;
        if (true) goto _L5; else goto _L4
_L4:
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
            if (((SavedState) (parcelable)).openDrawerGravity != 0)
            {
                View view = findDrawerWithGravity(((SavedState) (parcelable)).openDrawerGravity);
                if (view != null)
                {
                    openDrawer(view, true);
                }
            }
            if (((SavedState) (parcelable)).lockModeLeft != 3)
            {
                setDrawerLockMode(((SavedState) (parcelable)).lockModeLeft, 3);
            }
            if (((SavedState) (parcelable)).lockModeRight != 3)
            {
                setDrawerLockMode(((SavedState) (parcelable)).lockModeRight, 5);
            }
            if (((SavedState) (parcelable)).lockModeStart != 3)
            {
                setDrawerLockMode(((SavedState) (parcelable)).lockModeStart, 0x800003);
            }
            if (((SavedState) (parcelable)).lockModeEnd != 3)
            {
                setDrawerLockMode(((SavedState) (parcelable)).lockModeEnd, 0x800005);
                return;
            }
        }
    }

    public void onRtlPropertiesChanged(int i)
    {
        if (!SET_DRAWER_SHADOW_FROM_ELEVATION)
        {
            ViewCompat.getLayoutDirection(this);
            mShadowLeftResolved = null;
            ViewCompat.getLayoutDirection(this);
            mShadowRightResolved = null;
        }
    }

    protected Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        int j = getChildCount();
        int i = 0;
        do
        {
label0:
            {
                if (i < j)
                {
                    LayoutParams layoutparams = (LayoutParams)getChildAt(i).getLayoutParams();
                    boolean flag;
                    boolean flag1;
                    if (layoutparams.openState == 1)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (layoutparams.openState == 2)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    if (!flag && !flag1)
                    {
                        break label0;
                    }
                    savedstate.openDrawerGravity = layoutparams.gravity;
                }
                savedstate.lockModeLeft = mLockModeLeft;
                savedstate.lockModeRight = mLockModeRight;
                savedstate.lockModeStart = mLockModeStart;
                savedstate.lockModeEnd = mLockModeEnd;
                return savedstate;
            }
            i++;
        } while (true);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        mLeftDragger.processTouchEvent(motionevent);
        mRightDragger.processTouchEvent(motionevent);
        motionevent.getAction() & 0xff;
        JVM INSTR tableswitch 0 3: default 56
    //                   0 58
    //                   1 85
    //                   2 56
    //                   3 213;
           goto _L1 _L2 _L3 _L1 _L4
_L1:
        return true;
_L2:
        float f = motionevent.getX();
        float f2 = motionevent.getY();
        mInitialMotionX = f;
        mInitialMotionY = f2;
        mChildrenCanceledTouch = false;
        return true;
_L3:
        float f1;
        float f3;
        f3 = motionevent.getX();
        f1 = motionevent.getY();
        motionevent = mLeftDragger.findTopChildUnder((int)f3, (int)f1);
        if (motionevent == null) goto _L6; else goto _L5
_L5:
        int i;
        boolean flag;
        if (((LayoutParams)motionevent.getLayoutParams()).gravity == 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L6; else goto _L7
_L7:
        f3 -= mInitialMotionX;
        f1 -= mInitialMotionY;
        i = mLeftDragger.mTouchSlop;
        if (f3 * f3 + f1 * f1 >= (float)(i * i)) goto _L6; else goto _L8
_L8:
        motionevent = findOpenDrawer();
        if (motionevent == null) goto _L6; else goto _L9
_L9:
        if (getDrawerLockMode(motionevent) == 2)
        {
            flag = true;
        } else
        {
            flag = false;
        }
_L11:
        closeDrawers(flag);
        return true;
_L4:
        closeDrawers(true);
        mChildrenCanceledTouch = false;
        return true;
_L6:
        flag = true;
        if (true) goto _L11; else goto _L10
_L10:
    }

    public final void openDrawer(int i)
    {
        Object obj = findDrawerWithGravity(i);
        if (obj == null)
        {
            StringBuilder stringbuilder = new StringBuilder("No drawer view found with gravity ");
            if ((i & 3) == 3)
            {
                obj = "LEFT";
            } else
            if ((i & 5) == 5)
            {
                obj = "RIGHT";
            } else
            {
                obj = Integer.toHexString(i);
            }
            throw new IllegalArgumentException(stringbuilder.append(((String) (obj))).toString());
        } else
        {
            openDrawer(((View) (obj)), true);
            return;
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean flag)
    {
        super.requestDisallowInterceptTouchEvent(flag);
        if (flag)
        {
            closeDrawers(true);
        }
    }

    public void requestLayout()
    {
        if (!mInLayout)
        {
            super.requestLayout();
        }
    }

    public void setDrawerElevation(float f)
    {
        mDrawerElevation = f;
        for (int i = 0; i < getChildCount(); i++)
        {
            View view = getChildAt(i);
            if (isDrawerView(view))
            {
                ViewCompat.setElevation(view, mDrawerElevation);
            }
        }

    }

    public void setDrawerLockMode(int i)
    {
        setDrawerLockMode(i, 3);
        setDrawerLockMode(i, 5);
    }

    final void setDrawerViewOffset(View view, float f)
    {
        view = (LayoutParams)view.getLayoutParams();
        if (f != ((LayoutParams) (view)).onScreen) goto _L2; else goto _L1
_L1:
        return;
_L2:
        view.onScreen = f;
        if (mListeners != null)
        {
            int i = mListeners.size() - 1;
            while (i >= 0) 
            {
                ((DrawerListener)mListeners.get(i))._mth51662RJ4E9NMIP1FEPKMATPFAPKMATPR8OKLC___0(f);
                i--;
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    public void setScrimColor(int i)
    {
        mScrimColor = i;
        invalidate();
    }

    public void setStatusBarBackground(int i)
    {
        Drawable drawable;
        if (i != 0)
        {
            drawable = ContextCompat.getDrawable(getContext(), i);
        } else
        {
            drawable = null;
        }
        mStatusBarBackground = drawable;
        invalidate();
    }

    public void setStatusBarBackgroundColor(int i)
    {
        mStatusBarBackground = new ColorDrawable(i);
        invalidate();
    }

    final void updateDrawerState$514KIJ31DPI74RR9CGNNCQB5ESNLCQB5ESTIILG_0(int i, View view)
    {
        LayoutParams layoutparams;
        int j;
        j = mLeftDragger.mDragState;
        int k = mRightDragger.mDragState;
        if (j == 1 || k == 1)
        {
            j = 1;
        } else
        if (j == 2 || k == 2)
        {
            j = 2;
        } else
        {
            j = 0;
        }
        if (view == null || i != 0) goto _L2; else goto _L1
_L1:
        layoutparams = (LayoutParams)view.getLayoutParams();
        if (layoutparams.onScreen != 0.0F) goto _L4; else goto _L3
_L3:
        layoutparams = (LayoutParams)view.getLayoutParams();
        if ((layoutparams.openState & 1) == 1)
        {
            layoutparams.openState = 0;
            if (mListeners != null)
            {
                for (i = mListeners.size() - 1; i >= 0; i--)
                {
                    ((DrawerListener)mListeners.get(i)).onDrawerClosed(view);
                }

            }
            updateChildrenImportantForAccessibility(view, false);
            if (hasWindowFocus())
            {
                view = getRootView();
                if (view != null)
                {
                    view.sendAccessibilityEvent(32);
                }
            }
        }
_L2:
        if (j != mDrawerState)
        {
            mDrawerState = j;
            if (mListeners != null)
            {
                for (i = mListeners.size() - 1; i >= 0; i--)
                {
                    ((DrawerListener)mListeners.get(i))._mth514IILG_0();
                }

            }
        }
        break; /* Loop/switch isn't completed */
_L4:
        if (layoutparams.onScreen == 1.0F)
        {
            LayoutParams layoutparams1 = (LayoutParams)view.getLayoutParams();
            if ((layoutparams1.openState & 1) == 0)
            {
                layoutparams1.openState = 1;
                if (mListeners != null)
                {
                    for (i = mListeners.size() - 1; i >= 0; i--)
                    {
                        ((DrawerListener)mListeners.get(i)).onDrawerOpened(view);
                    }

                }
                updateChildrenImportantForAccessibility(view, true);
                if (hasWindowFocus())
                {
                    sendAccessibilityEvent(32);
                }
            }
        }
        if (true) goto _L2; else goto _L5
_L5:
    }


    private class _cls1
        implements android.view.View.OnApplyWindowInsetsListener
    {

        public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowinsets)
        {
            boolean flag1 = true;
            view = (DrawerLayout)view;
            boolean flag;
            if (windowinsets.getSystemWindowInsetTop() > 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            view.mLastInsets = windowinsets;
            view.mDrawStatusBarBackground = flag;
            if (!flag && view.getBackground() == null)
            {
                flag = flag1;
            } else
            {
                flag = false;
            }
            view.setWillNotDraw(flag);
            view.requestLayout();
            return windowinsets.consumeSystemWindowInsets();
        }

        _cls1()
        {
        }
    }

}
