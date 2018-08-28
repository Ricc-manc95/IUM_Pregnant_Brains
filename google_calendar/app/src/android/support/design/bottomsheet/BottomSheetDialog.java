// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.bottomsheet;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.app.AppCompatDialog;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

// Referenced classes of package android.support.design.bottomsheet:
//            BottomSheetBehavior

public final class BottomSheetDialog extends AppCompatDialog
{

    private BottomSheetBehavior behavior;
    private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback;
    public boolean cancelable;
    public boolean canceledOnTouchOutside;
    public boolean canceledOnTouchOutsideSet;

    public BottomSheetDialog(Context context)
    {
        this(context, 0);
    }

    private BottomSheetDialog(Context context, int i)
    {
        TypedValue typedvalue = new TypedValue();
        if (context.getTheme().resolveAttribute(0x7f010002, typedvalue, true))
        {
            i = typedvalue.resourceId;
        } else
        {
            i = 0x7f1402bc;
        }
        super(context, i);
        cancelable = true;
        canceledOnTouchOutside = true;
        bottomSheetCallback = new _cls4();
        if (super.mDelegate == null)
        {
            super.mDelegate = new AppCompatDelegateImpl(getContext(), getWindow(), this);
        }
        super.mDelegate.requestWindowFeature(1);
    }

    private final View wrapInBottomSheet(int i, View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        FrameLayout framelayout = (FrameLayout)View.inflate(getContext(), 0x7f050041, null);
        CoordinatorLayout coordinatorlayout = (CoordinatorLayout)framelayout.findViewById(0x7f100162);
        View view1 = view;
        if (i != 0)
        {
            view1 = view;
            if (view == null)
            {
                view1 = getLayoutInflater().inflate(i, coordinatorlayout, false);
            }
        }
        view = (FrameLayout)coordinatorlayout.findViewById(0x7f100164);
        behavior = BottomSheetBehavior.from(view);
        behavior.callback = bottomSheetCallback;
        behavior.hideable = cancelable;
        if (layoutparams == null)
        {
            view.addView(view1);
        } else
        {
            view.addView(view1, layoutparams);
        }
        coordinatorlayout.findViewById(0x7f100163).setOnClickListener(new _cls1());
        ViewCompat.setAccessibilityDelegate(view, new _cls2());
        view.setOnTouchListener(new _cls3());
        return framelayout;
    }

    protected final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        bundle = getWindow();
        if (bundle != null)
        {
            bundle.clearFlags(0x4000000);
            bundle.addFlags(0x80000000);
            bundle.setLayout(-1, -1);
        }
    }

    protected final void onStart()
    {
        super.onStart();
        if (behavior != null && behavior.state == 5)
        {
            behavior.setState(4);
        }
    }

    public final void setCancelable(boolean flag)
    {
        super.setCancelable(flag);
        if (cancelable != flag)
        {
            cancelable = flag;
            if (behavior != null)
            {
                behavior.hideable = flag;
            }
        }
    }

    public final void setCanceledOnTouchOutside(boolean flag)
    {
        super.setCanceledOnTouchOutside(flag);
        if (flag && !cancelable)
        {
            cancelable = true;
        }
        canceledOnTouchOutside = flag;
        canceledOnTouchOutsideSet = true;
    }

    public final void setContentView(int i)
    {
        super.setContentView(wrapInBottomSheet(i, null, null));
    }

    public final void setContentView(View view)
    {
        super.setContentView(wrapInBottomSheet(0, view, null));
    }

    public final void setContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        super.setContentView(wrapInBottomSheet(0, view, layoutparams));
    }

    private class _cls4 extends BottomSheetBehavior.BottomSheetCallback
    {

        private final BottomSheetDialog this$0;

        public final void onSlide$51662RJ4E9NMIP1FEPKMATPFAPKMATPR8OKLC___0()
        {
        }

        public final void onStateChanged$51662RJ4E9NMIP1FEPKMATPFAPKMATPR94KLC___0(int i)
        {
            if (i == 5)
            {
                cancel();
            }
        }

        _cls4()
        {
            this$0 = BottomSheetDialog.this;
            super();
        }
    }


    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final BottomSheetDialog this$0;

        public final void onClick(View view)
        {
            if (cancelable && isShowing())
            {
                view = BottomSheetDialog.this;
                if (!((BottomSheetDialog) (view)).canceledOnTouchOutsideSet)
                {
                    TypedArray typedarray = view.getContext().obtainStyledAttributes(new int[] {
                        0x101035b
                    });
                    view.canceledOnTouchOutside = typedarray.getBoolean(0, true);
                    typedarray.recycle();
                    view.canceledOnTouchOutsideSet = true;
                }
                if (((BottomSheetDialog) (view)).canceledOnTouchOutside)
                {
                    cancel();
                }
            }
        }

        _cls1()
        {
            this$0 = BottomSheetDialog.this;
            super();
        }
    }


    private class _cls2 extends AccessibilityDelegateCompat
    {

        private final BottomSheetDialog this$0;

        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
            if (cancelable)
            {
                accessibilitynodeinfocompat.mInfo.addAction(0x100000);
                accessibilitynodeinfocompat.mInfo.setDismissable(true);
                return;
            } else
            {
                accessibilitynodeinfocompat.mInfo.setDismissable(false);
                return;
            }
        }

        public final boolean performAccessibilityAction(View view, int i, Bundle bundle)
        {
            if (i == 0x100000 && cancelable)
            {
                cancel();
                return true;
            } else
            {
                return super.performAccessibilityAction(view, i, bundle);
            }
        }

        _cls2()
        {
            this$0 = BottomSheetDialog.this;
            super();
        }
    }


    private class _cls3
        implements android.view.View.OnTouchListener
    {

        public final boolean onTouch(View view, MotionEvent motionevent)
        {
            return true;
        }

        _cls3()
        {
        }
    }

}
