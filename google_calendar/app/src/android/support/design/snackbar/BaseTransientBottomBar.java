// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.snackbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.support.design.animation.AnimationUtils;
import android.support.design.behavior.SwipeDismissBehavior;
import android.support.design.internal.ThemeEnforcement;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import java.lang.ref.WeakReference;
import java.util.List;

// Referenced classes of package android.support.design.snackbar:
//            SnackbarManager, ContentViewCallback

public class BaseTransientBottomBar
{
    public static class Behavior extends SwipeDismissBehavior
    {
        private class BehaviorDelegate
        {

            public SnackbarManager.Callback managerCallback;

            public final void onInterceptTouchEvent(CoordinatorLayout coordinatorlayout, View view1, MotionEvent motionevent)
            {
                motionevent.getActionMasked();
                JVM INSTR tableswitch 0 3: default 36
            //                           0 37
            //                           1 82
            //                           2 36
            //                           3 82;
                   goto _L1 _L2 _L3 _L1 _L3
_L1:
                return;
_L2:
                if (coordinatorlayout.isPointInChildBounds(view1, (int)motionevent.getX(), (int)motionevent.getY()))
                {
                    if (SnackbarManager.snackbarManager == null)
                    {
                        SnackbarManager.snackbarManager = new SnackbarManager();
                    }
                    SnackbarManager.snackbarManager.pauseTimeout(managerCallback);
                    return;
                }
                  goto _L1
_L3:
                if (SnackbarManager.snackbarManager == null)
                {
                    SnackbarManager.snackbarManager = new SnackbarManager();
                }
                SnackbarManager.snackbarManager.restoreTimeoutIfPaused(managerCallback);
                return;
            }

            public BehaviorDelegate(SwipeDismissBehavior swipedismissbehavior)
            {
                swipedismissbehavior.alphaStartSwipeDistance = Math.min(Math.max(0.0F, 0.1F), 1.0F);
                swipedismissbehavior.alphaEndSwipeDistance = Math.min(Math.max(0.0F, 0.6F), 1.0F);
                swipedismissbehavior.swipeDirection = 0;
            }
        }


        public final BehaviorDelegate _flddelegate = new BehaviorDelegate(this);

        public final boolean canSwipeDismissView(View view1)
        {
            return view1 instanceof SnackbarBaseLayout;
        }

        public final boolean onInterceptTouchEvent(CoordinatorLayout coordinatorlayout, View view1, MotionEvent motionevent)
        {
            _flddelegate.onInterceptTouchEvent(coordinatorlayout, view1, motionevent);
            return super.onInterceptTouchEvent(coordinatorlayout, view1, motionevent);
        }

        public Behavior()
        {
        }

        private class SnackbarBaseLayout extends FrameLayout
        {

            public OnAttachStateChangeListener onAttachStateChangeListener;
            public OnLayoutChangeListener onLayoutChangeListener;

            protected void onAttachedToWindow()
            {
                super.onAttachedToWindow();
                if (onAttachStateChangeListener != null)
                {
                    onAttachStateChangeListener._mth51662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0();
                }
                ViewCompat.requestApplyInsets(this);
            }

            protected void onDetachedFromWindow()
            {
                super.onDetachedFromWindow();
                if (onAttachStateChangeListener != null)
                {
                    onAttachStateChangeListener._mth51662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0();
                }
            }

            protected void onLayout(boolean flag, int i, int j, int k, int l)
            {
                super.onLayout(flag, i, j, k, l);
                if (onLayoutChangeListener != null)
                {
                    onLayoutChangeListener._mth51662RJ4E9NMIP1FEPKMATPFAPKMATPR954KII99AO______0();
                }
            }

            public SnackbarBaseLayout(Context context1)
            {
                this(context1, null);
            }

            public SnackbarBaseLayout(Context context1, AttributeSet attributeset)
            {
                super(context1, attributeset);
                context1 = context1.obtainStyledAttributes(attributeset, R.styleable.SnackbarLayout);
                if (context1.hasValue(R.styleable.SnackbarLayout_elevation))
                {
                    ViewCompat.setElevation(this, context1.getDimensionPixelSize(R.styleable.SnackbarLayout_elevation, 0));
                }
                context1.recycle();
                setClickable(true);
            }

            private class OnAttachStateChangeListener
            {

                public abstract void onViewAttachedToWindow$51662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0();

                public abstract void onViewDetachedFromWindow$51662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0();
            }


            private class OnLayoutChangeListener
            {

                public abstract void onLayoutChange$51662RJ4E9NMIP1FEPKMATPFAPKMATPR954KII99AO______0();
            }

        }

    }


    private static final int SNACKBAR_STYLE_ATTR[] = {
        0x7f010270
    };
    private static final boolean USE_OFFSET_API = false;
    public static final Handler handler = new Handler(Looper.getMainLooper(), new _cls1());
    public final AccessibilityManager accessibilityManager;
    public List callbacks;
    public final ContentViewCallback contentViewCallback;
    private final Context context;
    public int duration;
    public final SnackbarManager.Callback managerCallback = new _cls3();
    public final ViewGroup targetParent;
    public final SnackbarBaseLayout view;

    public BaseTransientBottomBar(ViewGroup viewgroup, View view1, ContentViewCallback contentviewcallback)
    {
        if (viewgroup == null)
        {
            throw new IllegalArgumentException("Transient bottom bar must have non-null parent");
        }
        if (view1 == null)
        {
            throw new IllegalArgumentException("Transient bottom bar must have non-null content");
        }
        if (contentviewcallback == null)
        {
            throw new IllegalArgumentException("Transient bottom bar must have non-null callback");
        } else
        {
            targetParent = viewgroup;
            contentViewCallback = contentviewcallback;
            context = viewgroup.getContext();
            ThemeEnforcement.checkAppCompatTheme(context);
            view = (SnackbarBaseLayout)LayoutInflater.from(context).inflate(getSnackbarBaseLayoutResId(), targetParent, false);
            view.addView(view1);
            ViewCompat.setAccessibilityLiveRegion(view, 1);
            ViewCompat.setImportantForAccessibility(view, 1);
            ViewCompat.setFitsSystemWindows(view, true);
            ViewCompat.setOnApplyWindowInsetsListener(view, new _cls2());
            accessibilityManager = (AccessibilityManager)context.getSystemService("accessibility");
            return;
        }
    }

    final void animateViewIn()
    {
        final int translationYBottom = getTranslationYBottom();
        view.setTranslationY(translationYBottom);
        ValueAnimator valueanimator = new ValueAnimator();
        valueanimator.setIntValues(new int[] {
            translationYBottom, 0
        });
        valueanimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        valueanimator.setDuration(250L);
        valueanimator.addListener(new _cls7());
        valueanimator.addUpdateListener(new _cls8());
        valueanimator.start();
    }

    public void dismiss()
    {
        if (SnackbarManager.snackbarManager == null)
        {
            SnackbarManager.snackbarManager = new SnackbarManager();
        }
        SnackbarManager.snackbarManager.dismiss(managerCallback, 3);
    }

    public final void dispatchDismiss(int i)
    {
        SnackbarManager snackbarmanager;
        SnackbarManager.Callback callback;
        boolean flag1;
        flag1 = true;
        if (SnackbarManager.snackbarManager == null)
        {
            SnackbarManager.snackbarManager = new SnackbarManager();
        }
        snackbarmanager = SnackbarManager.snackbarManager;
        callback = managerCallback;
        Object obj = snackbarmanager.lock;
        obj;
        JVM INSTR monitorenter ;
        if (snackbarmanager.currentSnackbar == null) goto _L2; else goto _L1
_L1:
        SnackbarManager.SnackbarRecord snackbarrecord = snackbarmanager.currentSnackbar;
        if (callback == null) goto _L4; else goto _L3
_L3:
        if (snackbarrecord.callback.get() != callback) goto _L4; else goto _L5
_L5:
        boolean flag = true;
          goto _L6
_L19:
        if (!flag) goto _L8; else goto _L7
_L7:
        snackbarmanager.cancelSnackbarLocked(snackbarmanager.currentSnackbar, i);
_L16:
        obj;
        JVM INSTR monitorexit ;
        return;
_L8:
        if (snackbarmanager.nextSnackbar == null) goto _L10; else goto _L9
_L9:
        snackbarrecord = snackbarmanager.nextSnackbar;
        if (callback == null) goto _L12; else goto _L11
_L11:
        if (snackbarrecord.callback.get() != callback) goto _L12; else goto _L13
_L13:
        flag = true;
          goto _L14
_L17:
        if (!flag) goto _L16; else goto _L15
_L15:
        snackbarmanager.cancelSnackbarLocked(snackbarmanager.nextSnackbar, i);
          goto _L16
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
_L12:
        flag = false;
          goto _L14
_L10:
        flag = false;
          goto _L17
_L6:
        if (!flag) goto _L2; else goto _L18
_L18:
        flag = true;
          goto _L19
_L4:
        flag = false;
          goto _L6
_L2:
        flag = false;
          goto _L19
_L14:
        if (!flag) goto _L10; else goto _L20
_L20:
        flag = flag1;
          goto _L17
    }

    public SwipeDismissBehavior getNewBehavior()
    {
        return new Behavior();
    }

    public int getSnackbarBaseLayoutResId()
    {
        return !hasSnackbarStyleAttr() ? 0x7f050042 : 0x7f0500b9;
    }

    final int getTranslationYBottom()
    {
        int i = view.getHeight();
        android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
        if (layoutparams instanceof android.view.ViewGroup.MarginLayoutParams)
        {
            return ((android.view.ViewGroup.MarginLayoutParams)layoutparams).bottomMargin + i;
        } else
        {
            return i;
        }
    }

    public final boolean hasSnackbarStyleAttr()
    {
        boolean flag = false;
        TypedArray typedarray = context.obtainStyledAttributes(SNACKBAR_STYLE_ATTR);
        int i = typedarray.getResourceId(0, -1);
        typedarray.recycle();
        if (i != -1)
        {
            flag = true;
        }
        return flag;
    }

    final void onViewHidden(int i)
    {
        SnackbarManager snackbarmanager;
        SnackbarManager.Callback callback;
        boolean flag;
        flag = true;
        if (SnackbarManager.snackbarManager == null)
        {
            SnackbarManager.snackbarManager = new SnackbarManager();
        }
        snackbarmanager = SnackbarManager.snackbarManager;
        callback = managerCallback;
        Object obj = snackbarmanager.lock;
        obj;
        JVM INSTR monitorenter ;
        if (snackbarmanager.currentSnackbar == null) goto _L2; else goto _L1
_L1:
        SnackbarManager.SnackbarRecord snackbarrecord = snackbarmanager.currentSnackbar;
        if (callback == null) goto _L4; else goto _L3
_L3:
        if (snackbarrecord.callback.get() != callback) goto _L4; else goto _L5
_L5:
        int j = 1;
          goto _L6
_L10:
        if (j == 0)
        {
            break MISSING_BLOCK_LABEL_94;
        }
        snackbarmanager.currentSnackbar = null;
        if (snackbarmanager.nextSnackbar != null)
        {
            snackbarmanager.showNextSnackbarLocked();
        }
        obj;
        JVM INSTR monitorexit ;
          goto _L7
_L4:
        j = 0;
          goto _L6
_L2:
        j = 0;
        continue; /* Loop/switch isn't completed */
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
_L7:
        Exception exception;
        if (callbacks != null)
        {
            for (j = callbacks.size() - 1; j >= 0; j--)
            {
                ((BaseCallback)callbacks.get(j)).onDismissed(this, i);
            }

        }
        obj = view.getParent();
        if (obj instanceof ViewGroup)
        {
            ((ViewGroup)obj).removeView(view);
        }
        return;
_L6:
        if (j == 0) goto _L2; else goto _L8
_L8:
        j = ((flag) ? 1 : 0);
        if (true) goto _L10; else goto _L9
_L9:
    }

    final void onViewShown()
    {
        SnackbarManager snackbarmanager;
        SnackbarManager.Callback callback;
        boolean flag;
        flag = true;
        if (SnackbarManager.snackbarManager == null)
        {
            SnackbarManager.snackbarManager = new SnackbarManager();
        }
        snackbarmanager = SnackbarManager.snackbarManager;
        callback = managerCallback;
        Object obj = snackbarmanager.lock;
        obj;
        JVM INSTR monitorenter ;
        if (snackbarmanager.currentSnackbar == null) goto _L2; else goto _L1
_L1:
        SnackbarManager.SnackbarRecord snackbarrecord = snackbarmanager.currentSnackbar;
        if (callback == null) goto _L4; else goto _L3
_L3:
        if (snackbarrecord.callback.get() != callback) goto _L4; else goto _L5
_L5:
        int i = 1;
          goto _L6
_L10:
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_83;
        }
        snackbarmanager.scheduleTimeoutLocked(snackbarmanager.currentSnackbar);
        obj;
        JVM INSTR monitorexit ;
          goto _L7
_L4:
        i = 0;
          goto _L6
_L2:
        i = 0;
        continue; /* Loop/switch isn't completed */
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
_L7:
        Exception exception;
        if (callbacks != null)
        {
            for (i = callbacks.size() - 1; i >= 0; i--)
            {
                ((BaseCallback)callbacks.get(i)).onShown(this);
            }

        }
        return;
_L6:
        if (i == 0) goto _L2; else goto _L8
_L8:
        i = ((flag) ? 1 : 0);
        if (true) goto _L10; else goto _L9
_L9:
    }

    public void show()
    {
        Object obj1;
        SnackbarManager.Callback callback;
        boolean flag1;
        int i;
        flag1 = true;
        if (SnackbarManager.snackbarManager == null)
        {
            SnackbarManager.snackbarManager = new SnackbarManager();
        }
        obj1 = SnackbarManager.snackbarManager;
        i = duration;
        callback = managerCallback;
        Object obj = ((SnackbarManager) (obj1)).lock;
        obj;
        JVM INSTR monitorenter ;
        if (((SnackbarManager) (obj1)).currentSnackbar == null) goto _L2; else goto _L1
_L1:
        SnackbarManager.SnackbarRecord snackbarrecord = ((SnackbarManager) (obj1)).currentSnackbar;
        if (callback == null) goto _L4; else goto _L3
_L3:
        if (snackbarrecord.callback.get() != callback) goto _L4; else goto _L5
_L5:
        boolean flag = true;
          goto _L6
_L20:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_112;
        }
        ((SnackbarManager) (obj1)).currentSnackbar.duration = i;
        ((SnackbarManager) (obj1)).handler.removeCallbacksAndMessages(((SnackbarManager) (obj1)).currentSnackbar);
        ((SnackbarManager) (obj1)).scheduleTimeoutLocked(((SnackbarManager) (obj1)).currentSnackbar);
        obj;
        JVM INSTR monitorexit ;
        return;
        if (((SnackbarManager) (obj1)).nextSnackbar == null) goto _L8; else goto _L7
_L7:
        snackbarrecord = ((SnackbarManager) (obj1)).nextSnackbar;
        if (callback == null) goto _L10; else goto _L9
_L9:
        if (snackbarrecord.callback.get() != callback) goto _L10; else goto _L11
_L11:
        flag = true;
          goto _L12
_L17:
        if (!flag) goto _L14; else goto _L13
_L13:
        ((SnackbarManager) (obj1)).nextSnackbar.duration = i;
_L18:
        if (((SnackbarManager) (obj1)).currentSnackbar == null || !((SnackbarManager) (obj1)).cancelSnackbarLocked(((SnackbarManager) (obj1)).currentSnackbar, 4)) goto _L16; else goto _L15
_L15:
        obj;
        JVM INSTR monitorexit ;
        return;
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
_L10:
        flag = false;
          goto _L12
_L8:
        flag = false;
          goto _L17
_L14:
        obj1.nextSnackbar = new SnackbarManager.SnackbarRecord(i, callback);
          goto _L18
_L16:
        obj1.currentSnackbar = null;
        ((SnackbarManager) (obj1)).showNextSnackbarLocked();
        obj;
        JVM INSTR monitorexit ;
        return;
_L6:
        if (!flag) goto _L2; else goto _L19
_L19:
        flag = true;
          goto _L20
_L4:
        flag = false;
          goto _L6
_L2:
        flag = false;
          goto _L20
_L12:
        if (!flag) goto _L8; else goto _L21
_L21:
        flag = flag1;
          goto _L17
    }


    private class _cls3
        implements SnackbarManager.Callback
    {

        private final BaseTransientBottomBar this$0;

        public final void dismiss(int i)
        {
            BaseTransientBottomBar.handler.sendMessage(BaseTransientBottomBar.handler.obtainMessage(1, i, 0, BaseTransientBottomBar.this));
        }

        public final void show()
        {
            BaseTransientBottomBar.handler.sendMessage(BaseTransientBottomBar.handler.obtainMessage(0, BaseTransientBottomBar.this));
        }

        _cls3()
        {
            this$0 = BaseTransientBottomBar.this;
            super();
        }
    }


    private class _cls2
        implements OnApplyWindowInsetsListener
    {

        public final WindowInsetsCompat onApplyWindowInsets(View view1, WindowInsetsCompat windowinsetscompat)
        {
            view1.setPadding(view1.getPaddingLeft(), view1.getPaddingTop(), view1.getPaddingRight(), ((WindowInsets)windowinsetscompat.mInsets).getSystemWindowInsetBottom());
            return windowinsetscompat;
        }

        _cls2()
        {
        }
    }


    private class _cls7 extends AnimatorListenerAdapter
    {

        private final BaseTransientBottomBar this$0;

        public final void onAnimationEnd(Animator animator)
        {
            onViewShown();
        }

        public final void onAnimationStart(Animator animator)
        {
            contentViewCallback.animateContentIn(70, 180);
        }

        _cls7()
        {
            this$0 = BaseTransientBottomBar.this;
            super();
        }
    }


    private class _cls8
        implements android.animation.ValueAnimator.AnimatorUpdateListener
    {

        private int previousAnimatedIntValue;
        private final BaseTransientBottomBar this$0;
        private final int val$translationYBottom;

        public final void onAnimationUpdate(ValueAnimator valueanimator)
        {
            int i = ((Integer)valueanimator.getAnimatedValue()).intValue();
            view.setTranslationY(i);
            previousAnimatedIntValue = i;
        }

        _cls8()
        {
            this$0 = BaseTransientBottomBar.this;
            translationYBottom = i;
            super();
            previousAnimatedIntValue = translationYBottom;
        }
    }


    private class BaseCallback
    {

        public void onDismissed(Object obj, int i)
        {
        }

        public void onShown(Object obj)
        {
        }

        public BaseCallback()
        {
        }
    }


    private class _cls1
        implements android.os.Handler.Callback
    {

        public final boolean handleMessage(Message message)
        {
            final int event = 0;
            BaseTransientBottomBar basetransientbottombar;
            switch (message.what)
            {
            default:
                return false;

            case 0: // '\0'
                message = (BaseTransientBottomBar)message.obj;
                if (((BaseTransientBottomBar) (message)).view.getParent() == null)
                {
                    Object obj = ((BaseTransientBottomBar) (message)).view.getLayoutParams();
                    if (obj instanceof android.support.design.widget.CoordinatorLayout.LayoutParams)
                    {
                        obj = (android.support.design.widget.CoordinatorLayout.LayoutParams)obj;
                        SwipeDismissBehavior swipedismissbehavior = message.getNewBehavior();
                        if (swipedismissbehavior instanceof Behavior)
                        {
                            ((Behavior)swipedismissbehavior)._flddelegate.managerCallback = ((BaseTransientBottomBar) (message)).managerCallback;
                        }
                        swipedismissbehavior.listener = message. new _cls4();
                        if (((android.support.design.widget.CoordinatorLayout.LayoutParams) (obj)).mBehavior != swipedismissbehavior)
                        {
                            obj.mBehavior = swipedismissbehavior;
                            obj.mBehaviorResolved = true;
                            if (swipedismissbehavior != null)
                            {
                                swipedismissbehavior.onAttachedToLayoutParams(((android.support.design.widget.CoordinatorLayout.LayoutParams) (obj)));
                            }
                        }
                        obj.insetEdge = 80;
                    }
                    ((BaseTransientBottomBar) (message)).targetParent.addView(((BaseTransientBottomBar) (message)).view);
                }
                ((BaseTransientBottomBar) (message)).view.onAttachStateChangeListener = message. new _cls5();
                if (ViewCompat.isLaidOut(((BaseTransientBottomBar) (message)).view))
                {
                    List list = ((BaseTransientBottomBar) (message)).accessibilityManager.getEnabledAccessibilityServiceList(1);
                    boolean flag = event;
                    if (list != null)
                    {
                        flag = event;
                        if (list.isEmpty())
                        {
                            flag = true;
                        }
                    }
                    if (flag)
                    {
                        message.animateViewIn();
                    } else
                    {
                        message.onViewShown();
                    }
                } else
                {
                    ((BaseTransientBottomBar) (message)).view.onLayoutChangeListener = message. new _cls6();
                }
                return true;

            case 1: // '\001'
                basetransientbottombar = (BaseTransientBottomBar)message.obj;
                event = message.arg1;
                message = basetransientbottombar.accessibilityManager.getEnabledAccessibilityServiceList(1);
                break;
            }
            boolean flag1;
            if (message != null && message.isEmpty())
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1 && basetransientbottombar.view.getVisibility() == 0)
            {
                message = new ValueAnimator();
                message.setIntValues(new int[] {
                    0, basetransientbottombar.getTranslationYBottom()
                });
                message.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                message.setDuration(250L);
                message.addListener(basetransientbottombar. new _cls9());
                message.addUpdateListener(basetransientbottombar. new _cls10());
                message.start();
            } else
            {
                basetransientbottombar.onViewHidden(event);
            }
            return true;
        }

        _cls1()
        {
        }

        private class _cls4
            implements android.support.design.behavior.SwipeDismissBehavior.OnDismissListener
        {

            private final BaseTransientBottomBar this$0;

            public final void onDismiss(View view1)
            {
                view1.setVisibility(8);
                dispatchDismiss(0);
            }

            public final void onDragStateChanged(int i)
            {
                switch (i)
                {
                default:
                    return;

                case 1: // '\001'
                case 2: // '\002'
                    if (SnackbarManager.snackbarManager == null)
                    {
                        SnackbarManager.snackbarManager = new SnackbarManager();
                    }
                    SnackbarManager.snackbarManager.pauseTimeout(managerCallback);
                    return;

                case 0: // '\0'
                    break;
                }
                if (SnackbarManager.snackbarManager == null)
                {
                    SnackbarManager.snackbarManager = new SnackbarManager();
                }
                SnackbarManager.snackbarManager.restoreTimeoutIfPaused(managerCallback);
            }

            _cls4()
            {
                this$0 = BaseTransientBottomBar.this;
                super();
            }
        }


        private class _cls5
            implements OnAttachStateChangeListener
        {

            public final BaseTransientBottomBar this$0;

            public final void onViewAttachedToWindow$51662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0()
            {
            }

            public final void onViewDetachedFromWindow$51662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0()
            {
                BaseTransientBottomBar basetransientbottombar = BaseTransientBottomBar.this;
                if (SnackbarManager.snackbarManager == null)
                {
                    SnackbarManager.snackbarManager = new SnackbarManager();
                }
                class _cls1
                    implements Runnable
                {

                    private final _cls5 this$1;

                    public final void run()
                    {
                        onViewHidden(3);
                    }

                    _cls1()
                    {
                        this$1 = _cls5.this;
                        super();
                    }
                }

                if (SnackbarManager.snackbarManager.isCurrentOrNext(basetransientbottombar.managerCallback))
                {
                    BaseTransientBottomBar.handler.post(new _cls1());
                }
            }

            _cls5()
            {
                this$0 = BaseTransientBottomBar.this;
                super();
            }
        }


        private class _cls6
            implements OnLayoutChangeListener
        {

            private final BaseTransientBottomBar this$0;

            public final void onLayoutChange$51662RJ4E9NMIP1FEPKMATPFAPKMATPR954KII99AO______0()
            {
                boolean flag = true;
                view.onLayoutChangeListener = null;
                List list = accessibilityManager.getEnabledAccessibilityServiceList(1);
                if (list == null || !list.isEmpty())
                {
                    flag = false;
                }
                if (flag)
                {
                    animateViewIn();
                    return;
                } else
                {
                    onViewShown();
                    return;
                }
            }

            _cls6()
            {
                this$0 = BaseTransientBottomBar.this;
                super();
            }
        }


        private class _cls9 extends AnimatorListenerAdapter
        {

            private final BaseTransientBottomBar this$0;
            private final int val$event;

            public final void onAnimationEnd(Animator animator)
            {
                onViewHidden(event);
            }

            public final void onAnimationStart(Animator animator)
            {
                contentViewCallback.animateContentOut(0, 180);
            }

            _cls9()
            {
                this$0 = BaseTransientBottomBar.this;
                event = i;
                super();
            }
        }


        private class _cls10
            implements android.animation.ValueAnimator.AnimatorUpdateListener
        {

            private int previousAnimatedIntValue;
            private final BaseTransientBottomBar this$0;

            public final void onAnimationUpdate(ValueAnimator valueanimator)
            {
                int i = ((Integer)valueanimator.getAnimatedValue()).intValue();
                view.setTranslationY(i);
                previousAnimatedIntValue = i;
            }

            _cls10()
            {
                this$0 = BaseTransientBottomBar.this;
                super();
                previousAnimatedIntValue = 0;
            }
        }

    }

}
