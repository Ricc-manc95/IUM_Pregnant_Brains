// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.view.overlay;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import com.android.calendarcommon2.LogUtils;
import java.lang.ref.WeakReference;

public abstract class OverlayFragment extends DialogFragment
{
    public static final class LayoutChangeListener
        implements android.view.ViewTreeObserver.OnGlobalLayoutListener
    {

        public View contentView;
        private final WeakReference reference;

        public final void onGlobalLayout()
        {
            OverlayFragment overlayfragment = (OverlayFragment)reference.get();
            if (overlayfragment != null)
            {
                overlayfragment.onGlobalLayout(contentView, this);
            }
        }

        public LayoutChangeListener(OverlayFragment overlayfragment, View view)
        {
            contentView = view;
            reference = new WeakReference(overlayfragment);
        }
    }

    public static final class OverlayBackground extends Enum
    {

        private static final OverlayBackground $VALUES[];
        public static final OverlayBackground BottomDocked;
        public static final OverlayBackground BottomDockedMatched;
        public static final OverlayBackground Floating;
        public static final OverlayBackground FullDocked;
        public static final OverlayBackground Unknown;
        public final int background;
        public final int gravity;
        public final int height;
        private final int topPadding;

        public static OverlayBackground[] values()
        {
            return (OverlayBackground[])$VALUES.clone();
        }

        public final OverlayBackground setToCard(OverlayFragment overlayfragment)
        {
            View view;
            LogUtils.v(OverlayFragment.TAG, "fragment switched from %s to %s", new Object[] {
                overlayfragment.overlayBackground.toString(), toString()
            });
            view = overlayfragment.getOverlayView();
            if (view == null) goto _L2; else goto _L1
_L1:
            Object obj;
            Object obj1;
            int i;
            int l;
            i = topPadding;
            l = background;
            obj1 = view.getResources();
            obj = view.getLayoutParams();
            if (!(obj instanceof android.view.ViewGroup.MarginLayoutParams) || obj1 == null) goto _L4; else goto _L3
_L3:
            int j;
            obj = (android.view.ViewGroup.MarginLayoutParams)obj;
            j = ((android.view.ViewGroup.MarginLayoutParams) (obj)).topMargin;
            if (i != -1) goto _L6; else goto _L5
_L5:
            if (l == 0) goto _L8; else goto _L7
_L7:
            Rect rect;
            obj1 = ((Resources) (obj1)).getDrawable(l);
            rect = new Rect();
            if (obj1 == null || !((Drawable) (obj1)).getPadding(rect)) goto _L8; else goto _L9
_L9:
            i = rect.bottom - rect.top;
_L11:
            if (j != i)
            {
                obj.topMargin = i;
                LogUtils.v(OverlayFragment.TAG, "requestLayout for margin", new Object[0]);
                view.requestLayout();
            }
_L4:
            i = gravity;
            int k = height;
            View view1 = overlayfragment.getOverlayView();
            if (view1 != null)
            {
                obj1 = (android.widget.FrameLayout.LayoutParams)view1.getLayoutParams();
                if (obj1 != null)
                {
                    int i1 = ((android.widget.FrameLayout.LayoutParams) (obj1)).gravity & 7 | i;
                    if (i1 != ((android.widget.FrameLayout.LayoutParams) (obj1)).gravity || k != ((android.widget.FrameLayout.LayoutParams) (obj1)).height)
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    if (i != 0)
                    {
                        obj1.gravity = i1;
                        obj1.height = k;
                        view1.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj1)));
                        LogUtils.v(OverlayFragment.TAG, "requestLayout for view layout", new Object[0]);
                    }
                }
            }
            view.setBackgroundResource(background);
            overlayfragment.onBackgroundChanged(this);
            overlayfragment.overlayBackground = this;
_L2:
            return this;
_L6:
            if (i != 0)
            {
                i = ((Resources) (obj1)).getDimensionPixelSize(i) - overlayfragment.shadowPaddingTop;
                continue; /* Loop/switch isn't completed */
            }
_L8:
            i = 0;
            if (true) goto _L11; else goto _L10
_L10:
        }

        public final void startRectAnimation(OverlayFragment overlayfragment, Rect rect)
        {
            LogUtils.v(OverlayFragment.TAG, "fragment switched to animation using %s", new Object[] {
                toString()
            });
            Object obj = overlayfragment.getOverlayView();
            rect.bottom = ((View) (obj)).getBottom();
            rect.left = ((View) (obj)).getLeft();
            rect.right = ((View) (obj)).getRight();
            rect.top = ((View) (obj)).getTop();
            obj = ((View) (obj)).getBackground();
            Rect rect1 = new Rect();
            if (obj != null && ((Drawable) (obj)).getPadding(rect1))
            {
                rect.left = rect.left + rect1.left;
                rect.right = rect.right - rect1.right;
                rect.top = rect.top + rect1.top;
                rect.bottom = rect.bottom - rect1.bottom;
            }
            overlayfragment = overlayfragment.getOverlayView();
            overlayfragment.setBackground(new ColorDrawable(overlayfragment.getResources().getColor(0x106000d)));
            overlayfragment.setPadding(0, 0, 0, 0);
            overlayfragment.setLayoutParams(new android.widget.FrameLayout.LayoutParams(rect.width(), rect.height()));
        }

        static 
        {
            Unknown = new OverlayBackground("Unknown", 0, 0, 0, 16, -2);
            Floating = new OverlayBackground("Floating", 1, 0x7f02025b, -1, 16, -2);
            BottomDocked = new OverlayBackground("BottomDocked", 2, 0x7f02025d, 0x7f0e0080, 80, -2);
            BottomDockedMatched = new OverlayBackground("BottomDockedMatched", 3, 0x7f02025d, 0x7f0e007e, 80, -1);
            FullDocked = new OverlayBackground("FullDocked", 4, 0x7f02025c, 0, 48, -1);
            $VALUES = (new OverlayBackground[] {
                Unknown, Floating, BottomDocked, BottomDockedMatched, FullDocked
            });
        }

        private OverlayBackground(String s, int i, int j, int k, int l, int i1)
        {
            super(s, i);
            background = j;
            topPadding = k;
            gravity = l;
            height = i1;
        }
    }

    public class OverlayDialog extends Dialog
        implements OverlaySetting
    {

        private GestureDetector gestureDetector;
        private final OverlayFragment this$0;

        public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
        {
            boolean flag = super.dispatchPopulateAccessibilityEvent(accessibilityevent);
            accessibilityevent.setClassName(getClass().getName());
            Object obj = OverlayFragment.this;
            if (((Fragment) (obj)).mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
            }
            accessibilityevent.setPackageName(((FragmentActivity) (obj)).getPackageName());
            return flag;
        }

        public boolean dispatchTouchEvent(MotionEvent motionevent)
        {
            boolean flag1 = false;
            if (android.os.Build.VERSION.SDK_INT < 24) goto _L2; else goto _L1
_L1:
            boolean flag;
label0:
            {
                if (!super.dispatchTouchEvent(motionevent))
                {
                    flag = flag1;
                    if (gestureDetector == null)
                    {
                        break label0;
                    }
                    flag = flag1;
                    if (!gestureDetector.onTouchEvent(motionevent))
                    {
                        break label0;
                    }
                }
                flag = true;
            }
_L4:
            return flag;
_L2:
            boolean flag2;
            try
            {
                if (super.dispatchTouchEvent(motionevent))
                {
                    break; /* Loop/switch isn't completed */
                }
            }
            // Misplaced declaration of an exception variable
            catch (MotionEvent motionevent)
            {
                LogUtils.e(OverlayFragment.TAG, motionevent, "Ignoring NPE caused by the system on M and below.", new Object[0]);
                return true;
            }
            flag = flag1;
            if (gestureDetector == null) goto _L4; else goto _L3
_L3:
            flag2 = gestureDetector.onTouchEvent(motionevent);
            flag = flag1;
            if (!flag2) goto _L4; else goto _L5
_L5:
            return true;
        }

        public final Window getOverlaySettingWindow()
        {
            return getWindow();
        }

        public final void initOverlaySetting(android.view.GestureDetector.OnGestureListener ongesturelistener, boolean flag)
        {
            gestureDetector = new GestureDetector(getContext(), ongesturelistener);
            setCanceledOnTouchOutside(flag);
        }

        public void onBackPressed()
        {
            onDialogBackPressed();
        }

        protected void onCreate(Bundle bundle)
        {
            super.onCreate(bundle);
            onOverlaySettingCreated(this);
        }

        public final void resetOverlaySetting()
        {
            gestureDetector = null;
        }

        public OverlayDialog(Context context)
        {
            this$0 = OverlayFragment.this;
            super(context, getDialogTheme());
            overlaySetting = this;
        }
    }

    public static interface OverlayListener
    {

        public abstract void dismissOverlay(OverlayFragment overlayfragment, boolean flag);
    }

    public static interface OverlaySetting
    {

        public abstract Window getOverlaySettingWindow();

        public abstract void initOverlaySetting(android.view.GestureDetector.OnGestureListener ongesturelistener, boolean flag);

        public abstract void resetOverlaySetting();
    }

    public static final class PreDrawListener
        implements android.view.ViewTreeObserver.OnPreDrawListener
    {

        public View contentView;
        private final WeakReference reference;

        public final boolean onPreDraw()
        {
            OverlayFragment overlayfragment = (OverlayFragment)reference.get();
            return overlayfragment == null || overlayfragment.onPreDraw(contentView, this);
        }

        public PreDrawListener(OverlayFragment overlayfragment, View view)
        {
            contentView = view;
            reference = new WeakReference(overlayfragment);
        }
    }

    public final class ShadowTouchListener extends android.view.GestureDetector.SimpleOnGestureListener
    {

        private final OverlayFragment this$0;

        public final boolean onDown(MotionEvent motionevent)
        {
            Object obj = getOverlayView();
            if (obj != null)
            {
                int ai[] = new int[2];
                ((View) (obj)).getLocationInWindow(ai);
                Rect rect = new Rect(0, 0, ((View) (obj)).getWidth(), ((View) (obj)).getHeight());
                rect.offset(ai[0], ai[1]);
                obj = ((View) (obj)).getBackground();
                Rect rect1 = new Rect();
                if (obj != null && ((Drawable) (obj)).getPadding(rect1))
                {
                    rect.left = rect.left + rect1.left;
                    rect.right = rect.right - rect1.right;
                    rect.top = rect.top + rect1.top;
                    rect.bottom = rect.bottom - rect1.bottom;
                }
                if (!rect.contains((int)motionevent.getX(), (int)motionevent.getY()))
                {
                    onTouchOutside();
                    return true;
                }
            }
            return super.onDown(motionevent);
        }

        public ShadowTouchListener()
        {
            this$0 = OverlayFragment.this;
            super();
        }
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/common/view/overlay/OverlayFragment);
    public boolean isTabletConfig;
    public int minimumGutter;
    public OverlayBackground overlayBackground;
    public OverlaySetting overlaySetting;
    public int shadowPaddingBottom;
    private int shadowPaddingHorizontal;
    public int shadowPaddingTop;

    public OverlayFragment()
    {
    }

    public static LayoutChangeListener addLayoutChangeListener(OverlayFragment overlayfragment, View view)
    {
        ViewTreeObserver viewtreeobserver = view.getViewTreeObserver();
        if (viewtreeobserver != null)
        {
            overlayfragment = new LayoutChangeListener(overlayfragment, view);
            viewtreeobserver.addOnGlobalLayoutListener(overlayfragment);
            return overlayfragment;
        } else
        {
            return null;
        }
    }

    public abstract int getDialogTheme();

    public View getOverlayView()
    {
        return super.mView;
    }

    public OverlayBackground getShortBackground()
    {
        return OverlayBackground.Floating;
    }

    public OverlayBackground getTallBackground()
    {
        return OverlayBackground.BottomDocked;
    }

    public String getTitle()
    {
        return null;
    }

    public boolean getWindowHeight(int ai[])
    {
        ai[0] = -2;
        return false;
    }

    public boolean isFullScreen(Resources resources)
    {
        return false;
    }

    public final void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        if (bundle == overlaySetting)
        {
            bundle = overlaySetting.getOverlaySettingWindow();
            Resources resources = requireContext().getResources();
            android.view.WindowManager.LayoutParams layoutparams = bundle.getAttributes();
            bundle.setLayout(-1, -1);
            if (isFullScreen(resources))
            {
                layoutparams.dimAmount = 0.0F;
            } else
            {
                layoutparams.dimAmount = resources.getFraction(0x7f0f0000, 1, 1);
            }
            layoutparams.windowAnimations = 0;
            bundle.setAttributes(layoutparams);
        }
    }

    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        if (activity instanceof OverlaySetting)
        {
            overlaySetting = (OverlaySetting)activity;
        }
        isTabletConfig = activity.getResources().getBoolean(0x7f0c0016);
    }

    public abstract void onBackgroundChanged(OverlayBackground overlaybackground);

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        bundle = requireContext().getResources();
        Drawable drawable = bundle.getDrawable(OverlayBackground.Floating.background);
        Rect rect = new Rect();
        if (drawable != null && drawable.getPadding(rect))
        {
            shadowPaddingHorizontal = rect.left + rect.right;
            shadowPaddingTop = rect.top;
            shadowPaddingBottom = rect.bottom;
        } else
        {
            shadowPaddingBottom = 0;
            shadowPaddingTop = 0;
            shadowPaddingHorizontal = 0;
        }
        minimumGutter = bundle.getDimensionPixelSize(0x7f0e0080);
    }

    public Dialog onCreateDialog(Bundle bundle)
    {
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        return new OverlayDialog(bundle);
    }

    public abstract void onDialogBackPressed();

    public void onFinalLayoutFinished()
    {
    }

    public final void onGlobalLayout(final View contentView, LayoutChangeListener layoutchangelistener)
    {
        Object obj1 = getOverlayView();
        if (obj1 == null)
        {
            if (layoutchangelistener.contentView != null)
            {
                layoutchangelistener.contentView.getViewTreeObserver().removeOnGlobalLayoutListener(layoutchangelistener);
                layoutchangelistener.contentView = null;
            }
            onFinalLayoutFinished();
        } else
        {
            int i = contentView.getHeight();
            int j = ((View)contentView.getParent()).getHeight();
            Object obj = new Point();
            overlaySetting.getOverlaySettingWindow().getWindowManager().getDefaultDisplay().getRealSize(((Point) (obj)));
            Rect rect = new Rect(0, 0, ((View) (obj1)).getWidth(), ((View) (obj1)).getHeight());
            obj1 = ((View) (obj1)).getBackground();
            Rect rect1 = new Rect();
            if (obj1 != null && ((Drawable) (obj1)).getPadding(rect1))
            {
                rect.left = rect.left + rect1.left;
                rect.right = rect.right - rect1.right;
                rect.top = rect.top + rect1.top;
                rect.bottom = rect.bottom - rect1.bottom;
            }
            if (j > i)
            {
                rect.bottom = rect.bottom - (j - i);
            }
            int k = ((Point) (obj)).y;
            int l = rect.height();
            if (i <= j && (k - l) / 2 > minimumGutter + shadowPaddingBottom)
            {
                obj = getShortBackground();
            } else
            {
                obj = getTallBackground();
            }
            if (overlayBackground != obj)
            {
                ((OverlayBackground) (obj)).setToCard(this);
                if (overlayBackground == getTallBackground())
                {
                    if (layoutchangelistener.contentView != null)
                    {
                        layoutchangelistener.contentView.getViewTreeObserver().removeOnGlobalLayoutListener(layoutchangelistener);
                        layoutchangelistener.contentView = null;
                    }
                    layoutchangelistener = contentView.getViewTreeObserver();
                    if (layoutchangelistener != null)
                    {
                        layoutchangelistener.addOnGlobalLayoutListener(new _cls1());
                        return;
                    }
                }
            } else
            {
                if (layoutchangelistener.contentView != null)
                {
                    layoutchangelistener.contentView.getViewTreeObserver().removeOnGlobalLayoutListener(layoutchangelistener);
                    layoutchangelistener.contentView = null;
                }
                onFinalLayoutFinished();
                return;
            }
        }
    }

    final void onOverlaySettingCreated(OverlaySetting overlaysetting)
    {
        overlaysetting = overlaysetting.getOverlaySettingWindow();
        Resources resources = requireContext().getResources();
        android.view.WindowManager.LayoutParams layoutparams = overlaysetting.getAttributes();
        overlaysetting.setLayout(-1, -1);
        if (isFullScreen(resources))
        {
            layoutparams.dimAmount = 0.0F;
        } else
        {
            layoutparams.dimAmount = resources.getFraction(0x7f0f0000, 1, 1);
        }
        layoutparams.windowAnimations = 0;
        overlaysetting.setAttributes(layoutparams);
    }

    public boolean onPreDraw(View view, PreDrawListener predrawlistener)
    {
        return true;
    }

    public void onStart()
    {
        super.onStart();
        if (overlaySetting != null)
        {
            overlaySetting.initOverlaySetting(new ShadowTouchListener(), true);
        }
    }

    public void onStop()
    {
        if (overlaySetting != null)
        {
            overlaySetting.resetOverlaySetting();
        }
        super.onStop();
    }

    public abstract void onTouchOutside();

    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        overlayBackground = OverlayBackground.Unknown;
    }

    public void onViewStateRestored(Bundle bundle)
    {
        super.onViewStateRestored(bundle);
        if (!isFullScreen(requireContext().getResources()))
        {
            bundle = getOverlayView();
            android.widget.FrameLayout.LayoutParams layoutparams;
            if (bundle != null)
            {
                if ((layoutparams = (android.widget.FrameLayout.LayoutParams)bundle.getLayoutParams()) != null)
                {
                    Object obj = overlaySetting.getOverlaySettingWindow();
                    Point point = new Point();
                    obj = ((Window) (obj)).getWindowManager().getDefaultDisplay();
                    ((Display) (obj)).getRealSize(point);
                    int i = Math.max(point.x, point.y) / 2;
                    int j = point.x;
                    ((Display) (obj)).getSize(point);
                    int k = (int)((double)point.x * 0.90000000000000002D);
                    layoutparams.width = Math.min(shadowPaddingHorizontal + i, Math.min(j, k + shadowPaddingHorizontal));
                    int ai[] = new int[1];
                    ai[0] = -2;
                    if (getWindowHeight(ai))
                    {
                        layoutparams.height = ai[0];
                    } else
                    {
                        layoutparams.height = -2;
                    }
                    layoutparams.gravity = 17;
                    bundle.setLayoutParams(layoutparams);
                    LogUtils.v(TAG, "requestLayout for view layout", new Object[0]);
                    return;
                }
            }
        }
    }


    private class _cls1
        implements android.view.ViewTreeObserver.OnGlobalLayoutListener
    {

        private final OverlayFragment this$0;
        private final View val$contentView;

        public final void onGlobalLayout()
        {
            contentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            onFinalLayoutFinished();
        }

        _cls1()
        {
            this$0 = OverlayFragment.this;
            contentView = view;
            super();
        }
    }

}
