// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.content.ClipData;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowInsets;
import java.lang.reflect.Field;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package android.support.v4.view:
//            ViewPropertyAnimatorCompat, WindowInsetsCompat, AccessibilityDelegateCompat, OnApplyWindowInsetsListener

public final class ViewCompat
{

    private static boolean sAccessibilityDelegateCheckFailed = false;
    private static Field sAccessibilityDelegateField;
    private static ThreadLocal sThreadLocalRect;
    private static WeakHashMap sViewPropertyAnimatorMap = null;

    public static ViewPropertyAnimatorCompat animate(View view)
    {
        if (sViewPropertyAnimatorMap == null)
        {
            sViewPropertyAnimatorMap = new WeakHashMap();
        }
        ViewPropertyAnimatorCompat viewpropertyanimatorcompat1 = (ViewPropertyAnimatorCompat)sViewPropertyAnimatorMap.get(view);
        ViewPropertyAnimatorCompat viewpropertyanimatorcompat = viewpropertyanimatorcompat1;
        if (viewpropertyanimatorcompat1 == null)
        {
            viewpropertyanimatorcompat = new ViewPropertyAnimatorCompat(view);
            sViewPropertyAnimatorMap.put(view, viewpropertyanimatorcompat);
        }
        return viewpropertyanimatorcompat;
    }

    public static void cancelDragAndDrop(View view)
    {
        if (android.os.Build.VERSION.SDK_INT >= 24)
        {
            view.cancelDragAndDrop();
        }
    }

    public static WindowInsetsCompat dispatchApplyWindowInsets(View view, WindowInsetsCompat windowinsetscompat)
    {
        WindowInsets windowinsets;
        if (windowinsetscompat == null)
        {
            windowinsetscompat = null;
        } else
        {
            windowinsetscompat = ((WindowInsetsCompat) (windowinsetscompat.mInsets));
        }
        windowinsetscompat = (WindowInsets)windowinsetscompat;
        windowinsets = view.dispatchApplyWindowInsets(windowinsetscompat);
        view = windowinsetscompat;
        if (windowinsets != windowinsetscompat)
        {
            view = new WindowInsets(windowinsets);
        }
        if (view == null)
        {
            return null;
        } else
        {
            return new WindowInsetsCompat(view);
        }
    }

    public static boolean dispatchUnhandledKeyEventPost(View view, KeyEvent keyevent)
    {
        if (android.os.Build.VERSION.SDK_INT >= 28)
        {
            return false;
        } else
        {
            return UnhandledKeyEventManager.at(view).dispatch(view, keyevent);
        }
    }

    public static boolean dispatchUnhandledKeyEventPre(View view, KeyEvent keyevent)
    {
        if (android.os.Build.VERSION.SDK_INT < 28)
        {
            boolean flag;
            if (UnhandledKeyEventManager.at(view).mCurrentReceiver != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag && UnhandledKeyEventManager.at(view).dispatch(view, keyevent))
            {
                return true;
            }
        }
        return false;
    }

    public static int getAccessibilityLiveRegion(View view)
    {
        return view.getAccessibilityLiveRegion();
    }

    public static ColorStateList getBackgroundTintList(View view)
    {
        return view.getBackgroundTintList();
    }

    public static android.graphics.PorterDuff.Mode getBackgroundTintMode(View view)
    {
        return view.getBackgroundTintMode();
    }

    public static Rect getClipBounds(View view)
    {
        return view.getClipBounds();
    }

    public static Display getDisplay(View view)
    {
        return view.getDisplay();
    }

    public static float getElevation(View view)
    {
        return view.getElevation();
    }

    private static Rect getEmptyTempRect()
    {
        if (sThreadLocalRect == null)
        {
            sThreadLocalRect = new ThreadLocal();
        }
        Rect rect1 = (Rect)sThreadLocalRect.get();
        Rect rect = rect1;
        if (rect1 == null)
        {
            rect = new Rect();
            sThreadLocalRect.set(rect);
        }
        rect.setEmpty();
        return rect;
    }

    public static boolean getFitsSystemWindows(View view)
    {
        return view.getFitsSystemWindows();
    }

    public static int getImportantForAccessibility(View view)
    {
        return view.getImportantForAccessibility();
    }

    public static int getImportantForAutofill(View view)
    {
        if (android.os.Build.VERSION.SDK_INT >= 26)
        {
            return view.getImportantForAutofill();
        } else
        {
            return 0;
        }
    }

    public static int getLayoutDirection(View view)
    {
        return view.getLayoutDirection();
    }

    public static int getMinimumHeight(View view)
    {
        return view.getMinimumHeight();
    }

    public static int getMinimumWidth(View view)
    {
        return view.getMinimumWidth();
    }

    public static int getPaddingEnd(View view)
    {
        return view.getPaddingEnd();
    }

    public static int getPaddingStart(View view)
    {
        return view.getPaddingStart();
    }

    public static ViewParent getParentForAccessibility(View view)
    {
        return view.getParentForAccessibility();
    }

    public static String getTransitionName(View view)
    {
        return view.getTransitionName();
    }

    public static float getTranslationY(View view)
    {
        return view.getTranslationY();
    }

    public static int getWindowSystemUiVisibility(View view)
    {
        return view.getWindowSystemUiVisibility();
    }

    public static float getZ(View view)
    {
        return view.getZ();
    }

    public static boolean hasAccessibilityDelegate(View view)
    {
        if (!sAccessibilityDelegateCheckFailed)
        {
            if (sAccessibilityDelegateField == null)
            {
                try
                {
                    Field field = android/view/View.getDeclaredField("mAccessibilityDelegate");
                    sAccessibilityDelegateField = field;
                    field.setAccessible(true);
                }
                // Misplaced declaration of an exception variable
                catch (View view)
                {
                    sAccessibilityDelegateCheckFailed = true;
                    return false;
                }
            }
            try
            {
                view = ((View) (sAccessibilityDelegateField.get(view)));
            }
            // Misplaced declaration of an exception variable
            catch (View view)
            {
                sAccessibilityDelegateCheckFailed = true;
                return false;
            }
            if (view != null)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean hasOnClickListeners(View view)
    {
        return view.hasOnClickListeners();
    }

    public static boolean hasOverlappingRendering(View view)
    {
        return view.hasOverlappingRendering();
    }

    public static boolean hasTransientState(View view)
    {
        return view.hasTransientState();
    }

    public static boolean isAttachedToWindow(View view)
    {
        return view.isAttachedToWindow();
    }

    public static boolean isLaidOut(View view)
    {
        return view.isLaidOut();
    }

    public static boolean isNestedScrollingEnabled(View view)
    {
        return view.isNestedScrollingEnabled();
    }

    public static boolean isPaddingRelative(View view)
    {
        return view.isPaddingRelative();
    }

    public static void offsetLeftAndRight(View view, int i)
    {
        boolean flag = false;
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            view.offsetLeftAndRight(i);
        } else
        {
            Rect rect = getEmptyTempRect();
            ViewParent viewparent = view.getParent();
            if (viewparent instanceof View)
            {
                Object obj = (View)viewparent;
                rect.set(((View) (obj)).getLeft(), ((View) (obj)).getTop(), ((View) (obj)).getRight(), ((View) (obj)).getBottom());
                float f;
                if (!rect.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
            }
            view.offsetLeftAndRight(i);
            if (view.getVisibility() == 0)
            {
                f = view.getTranslationY();
                view.setTranslationY(f + 1.0F);
                view.setTranslationY(f);
                obj = view.getParent();
                if (obj instanceof View)
                {
                    obj = (View)obj;
                    f = ((View) (obj)).getTranslationY();
                    ((View) (obj)).setTranslationY(f + 1.0F);
                    ((View) (obj)).setTranslationY(f);
                }
            }
            if (flag && rect.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()))
            {
                ((View)viewparent).invalidate(rect);
                return;
            }
        }
    }

    public static void offsetTopAndBottom(View view, int i)
    {
        boolean flag = false;
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            view.offsetTopAndBottom(i);
        } else
        {
            Rect rect = getEmptyTempRect();
            ViewParent viewparent = view.getParent();
            if (viewparent instanceof View)
            {
                Object obj = (View)viewparent;
                rect.set(((View) (obj)).getLeft(), ((View) (obj)).getTop(), ((View) (obj)).getRight(), ((View) (obj)).getBottom());
                float f;
                if (!rect.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
            }
            view.offsetTopAndBottom(i);
            if (view.getVisibility() == 0)
            {
                f = view.getTranslationY();
                view.setTranslationY(f + 1.0F);
                view.setTranslationY(f);
                obj = view.getParent();
                if (obj instanceof View)
                {
                    obj = (View)obj;
                    f = ((View) (obj)).getTranslationY();
                    ((View) (obj)).setTranslationY(f + 1.0F);
                    ((View) (obj)).setTranslationY(f);
                }
            }
            if (flag && rect.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()))
            {
                ((View)viewparent).invalidate(rect);
                return;
            }
        }
    }

    public static WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowinsetscompat)
    {
        WindowInsets windowinsets;
        if (windowinsetscompat == null)
        {
            windowinsetscompat = null;
        } else
        {
            windowinsetscompat = ((WindowInsetsCompat) (windowinsetscompat.mInsets));
        }
        windowinsetscompat = (WindowInsets)windowinsetscompat;
        windowinsets = view.onApplyWindowInsets(windowinsetscompat);
        view = windowinsetscompat;
        if (windowinsets != windowinsetscompat)
        {
            view = new WindowInsets(windowinsets);
        }
        if (view == null)
        {
            return null;
        } else
        {
            return new WindowInsetsCompat(view);
        }
    }

    public static void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        view.onInitializeAccessibilityNodeInfo(accessibilitynodeinfocompat.mInfo);
    }

    public static boolean performAccessibilityAction(View view, int i, Bundle bundle)
    {
        return view.performAccessibilityAction(i, bundle);
    }

    public static void postInvalidateOnAnimation(View view)
    {
        view.postInvalidateOnAnimation();
    }

    public static void postOnAnimation(View view, Runnable runnable)
    {
        view.postOnAnimation(runnable);
    }

    public static void postOnAnimationDelayed(View view, Runnable runnable, long l)
    {
        view.postOnAnimationDelayed(runnable, l);
    }

    public static void requestApplyInsets(View view)
    {
        view.requestApplyInsets();
    }

    public static void setAccessibilityDelegate(View view, AccessibilityDelegateCompat accessibilitydelegatecompat)
    {
        if (accessibilitydelegatecompat == null)
        {
            accessibilitydelegatecompat = null;
        } else
        {
            accessibilitydelegatecompat = accessibilitydelegatecompat.mBridge;
        }
        view.setAccessibilityDelegate(accessibilitydelegatecompat);
    }

    public static void setAccessibilityLiveRegion(View view, int i)
    {
        view.setAccessibilityLiveRegion(i);
    }

    public static void setBackground(View view, Drawable drawable)
    {
        view.setBackground(drawable);
    }

    public static void setBackgroundTintList(View view, ColorStateList colorstatelist)
    {
        view.setBackgroundTintList(colorstatelist);
        if (android.os.Build.VERSION.SDK_INT == 21)
        {
            colorstatelist = view.getBackground();
            boolean flag;
            if (view.getBackgroundTintList() != null || view.getBackgroundTintMode() != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (colorstatelist != null && flag)
            {
                if (colorstatelist.isStateful())
                {
                    colorstatelist.setState(view.getDrawableState());
                }
                view.setBackground(colorstatelist);
            }
        }
    }

    public static void setBackgroundTintMode(View view, android.graphics.PorterDuff.Mode mode)
    {
        view.setBackgroundTintMode(mode);
        if (android.os.Build.VERSION.SDK_INT == 21)
        {
            mode = view.getBackground();
            boolean flag;
            if (view.getBackgroundTintList() != null || view.getBackgroundTintMode() != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (mode != null && flag)
            {
                if (mode.isStateful())
                {
                    mode.setState(view.getDrawableState());
                }
                view.setBackground(mode);
            }
        }
    }

    public static void setClipBounds(View view, Rect rect)
    {
        view.setClipBounds(rect);
    }

    public static void setElevation(View view, float f)
    {
        view.setElevation(f);
    }

    public static void setFitsSystemWindows(View view, boolean flag)
    {
        view.setFitsSystemWindows(true);
    }

    public static void setHasTransientState(View view, boolean flag)
    {
        view.setHasTransientState(flag);
    }

    public static void setImportantForAccessibility(View view, int i)
    {
        view.setImportantForAccessibility(i);
    }

    public static void setImportantForAutofill(View view, int i)
    {
        if (android.os.Build.VERSION.SDK_INT >= 26)
        {
            view.setImportantForAutofill(8);
        }
    }

    public static void setOnApplyWindowInsetsListener(View view, final OnApplyWindowInsetsListener listener)
    {
        if (listener == null)
        {
            view.setOnApplyWindowInsetsListener(null);
            return;
        } else
        {
            view.setOnApplyWindowInsetsListener(new _cls1());
            return;
        }
    }

    public static void setPaddingRelative(View view, int i, int j, int k, int l)
    {
        view.setPaddingRelative(i, j, k, l);
    }

    public static void setScrollIndicators(View view, int i, int j)
    {
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            view.setScrollIndicators(i, j);
        }
    }

    public static void setTransitionName(View view, String s)
    {
        view.setTransitionName(s);
    }

    public static void setTranslationY(View view, float f)
    {
        view.setTranslationY(f);
    }

    public static void setTranslationZ(View view, float f)
    {
        view.setTranslationZ(f);
    }

    public static void setZ(View view, float f)
    {
        view.setZ(f);
    }

    public static boolean startDragAndDrop(View view, ClipData clipdata, android.view.View.DragShadowBuilder dragshadowbuilder, Object obj, int i)
    {
        if (android.os.Build.VERSION.SDK_INT >= 24)
        {
            return view.startDragAndDrop(null, dragshadowbuilder, null, 0);
        } else
        {
            return view.startDrag(null, dragshadowbuilder, null, 0);
        }
    }

    public static void stopNestedScroll(View view)
    {
        view.stopNestedScroll();
    }

    static 
    {
        new AtomicInteger(1);
    }

    private class UnhandledKeyEventManager
    {

        private static final ArrayList sViewsWithListeners = new ArrayList();
        private SparseBooleanArray mCapturedKeys;
        public WeakReference mCurrentReceiver;
        private WeakHashMap mViewsContainingListeners;

        static UnhandledKeyEventManager at(View view)
        {
            UnhandledKeyEventManager unhandledkeyeventmanager1 = (UnhandledKeyEventManager)view.getTag(0x7f10003f);
            UnhandledKeyEventManager unhandledkeyeventmanager = unhandledkeyeventmanager1;
            if (unhandledkeyeventmanager1 == null)
            {
                unhandledkeyeventmanager = new UnhandledKeyEventManager();
                view.setTag(0x7f10003f, unhandledkeyeventmanager);
            }
            return unhandledkeyeventmanager;
        }

        private final View dispatchInOrder(View view, KeyEvent keyevent)
        {
            Object obj;
            if (mViewsContainingListeners == null || !mViewsContainingListeners.containsKey(view))
            {
                obj = null;
            } else
            {
                if (view instanceof ViewGroup)
                {
                    obj = (ViewGroup)view;
                    for (int i = ((ViewGroup) (obj)).getChildCount() - 1; i >= 0; i--)
                    {
                        View view1 = dispatchInOrder(((ViewGroup) (obj)).getChildAt(i), keyevent);
                        if (view1 != null)
                        {
                            return view1;
                        }
                    }

                }
                obj = view;
                if (!onUnhandledKeyEvent(view, keyevent))
                {
                    return null;
                }
            }
            return ((View) (obj));
        }

        private static boolean onUnhandledKeyEvent(View view, KeyEvent keyevent)
        {
            view = (ArrayList)view.getTag(0x7f100040);
            if (view != null)
            {
                for (int i = view.size() - 1; i >= 0; i--)
                {
                    if (((OnUnhandledKeyEventListenerCompat)view.get(i))._mth51662RJ4E9NMIP1FEPKMATPFAPKMATPR9HGMSP3IDTKM8BRMD5INEBQBCLSKATJ5DPQ3MAAQ0())
                    {
                        return true;
                    }
                }

            }
            return false;
        }

        final boolean dispatch(View view, KeyEvent keyevent)
        {
            if (keyevent.getAction() == 0)
            {
                if (mCapturedKeys == null)
                {
                    mCapturedKeys = new SparseBooleanArray();
                }
                mCapturedKeys.append(keyevent.getKeyCode(), true);
            } else
            if (keyevent.getAction() == 1)
            {
                if (mCapturedKeys == null)
                {
                    mCapturedKeys = new SparseBooleanArray();
                }
                mCapturedKeys.delete(keyevent.getKeyCode());
            }
            if (mCurrentReceiver != null)
            {
                view = (View)mCurrentReceiver.get();
                if (mCapturedKeys == null)
                {
                    mCapturedKeys = new SparseBooleanArray();
                }
                if (mCapturedKeys.size() == 0)
                {
                    mCurrentReceiver = null;
                }
                if (view != null && ViewCompat.isAttachedToWindow(view))
                {
                    return onUnhandledKeyEvent(view, keyevent);
                } else
                {
                    return true;
                }
            }
            if (keyevent.getAction() != 0) goto _L2; else goto _L1
_L1:
            if (mCapturedKeys == null)
            {
                mCapturedKeys = new SparseBooleanArray();
            }
            if (mCapturedKeys.size() != 1) goto _L2; else goto _L3
_L3:
            if (mViewsContainingListeners != null)
            {
                mViewsContainingListeners.clear();
            }
            if (sViewsWithListeners.isEmpty()) goto _L2; else goto _L4
_L4:
            ArrayList arraylist = sViewsWithListeners;
            arraylist;
            JVM INSTR monitorenter ;
            int i;
            if (mViewsContainingListeners == null)
            {
                mViewsContainingListeners = new WeakHashMap();
            }
            i = sViewsWithListeners.size() - 1;
_L11:
            if (i < 0) goto _L6; else goto _L5
_L5:
            Object obj = (View)((WeakReference)sViewsWithListeners.get(i)).get();
            if (obj != null) goto _L8; else goto _L7
_L7:
            sViewsWithListeners.remove(i);
              goto _L9
_L8:
            mViewsContainingListeners.put(obj, Boolean.TRUE);
            for (obj = ((View) (obj)).getParent(); obj instanceof View; obj = ((ViewParent) (obj)).getParent())
            {
                mViewsContainingListeners.put((View)obj, Boolean.TRUE);
            }

              goto _L9
_L6:
            arraylist;
            JVM INSTR monitorexit ;
_L2:
            view = dispatchInOrder(view, keyevent);
            if (view != null)
            {
                mCurrentReceiver = new WeakReference(view);
            }
            return view != null;
            view;
            arraylist;
            JVM INSTR monitorexit ;
            throw view;
_L9:
            i--;
            if (true) goto _L11; else goto _L10
_L10:
        }


        UnhandledKeyEventManager()
        {
            mViewsContainingListeners = null;
            mCapturedKeys = null;
            mCurrentReceiver = null;
        }

        private class OnUnhandledKeyEventListenerCompat
        {

            public abstract boolean onUnhandledKeyEvent$51662RJ4E9NMIP1FEPKMATPFAPKMATPR9HGMSP3IDTKM8BRMD5INEBQBCLSKATJ5DPQ3MAAQ0();
        }

    }


    private class _cls1
        implements android.view.View.OnApplyWindowInsetsListener
    {

        private final OnApplyWindowInsetsListener val$listener;

        public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowinsets)
        {
            if (windowinsets == null)
            {
                windowinsets = null;
            } else
            {
                windowinsets = new WindowInsetsCompat(windowinsets);
            }
            view = listener.onApplyWindowInsets(view, windowinsets);
            if (view == null)
            {
                view = null;
            } else
            {
                view = ((View) (((WindowInsetsCompat) (view)).mInsets));
            }
            return (WindowInsets)view;
        }

        _cls1()
        {
            listener = onapplywindowinsetslistener;
            super();
        }
    }

}
