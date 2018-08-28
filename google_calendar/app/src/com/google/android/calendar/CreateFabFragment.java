// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.view.View;
import android.view.ViewTreeObserver;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.common.view.overlay.OverlayFragment;
import java.util.Stack;

// Referenced classes of package com.google.android.calendar:
//            SpeedDialLayout

public abstract class CreateFabFragment extends OverlayFragment
{

    public View createFab;
    private final int createFabId = 0x7f100146;
    public Activity interfaceActivity;
    public View overlayView;

    public CreateFabFragment()
    {
    }

    public final View getOverlayView()
    {
        return overlayView;
    }

    public abstract CreateFabStack.StartDay getStartDayInterface();

    public final void onAttach(Activity activity)
    {
        super.onAttach(activity);
        if (activity instanceof CreateFabInterface)
        {
            interfaceActivity = activity;
            return;
        } else
        {
            LogUtils.w(TAG, "Further use of CreateFabFragment will fail", new Object[0]);
            return;
        }
    }

    public final void onDetach()
    {
        super.onDetach();
        if (createFab != null)
        {
            Object obj = ((CreateFabInterface)interfaceActivity).getCreateFabStack();
            class CreateFabStack.Scope
            {

                public final View createFab;
                public final View createFabParent;
                public final CreateFabStack.StartDay startDay;

            CreateFabStack.Scope(View view, View view1, CreateFabStack.StartDay startday)
            {
                createFab = view;
                createFabParent = view1;
                startDay = startday;
            }
            }

            ((CreateFabStack.Scope)((CreateFabStack) (obj)).stack.pop()).createFab.setVisibility(4);
            if (((CreateFabStack) (obj)).stack.empty())
            {
                obj = ((CreateFabStack) (obj)).findCreateFab();
            } else
            {
                obj = ((CreateFabStack.Scope)((CreateFabStack) (obj)).stack.peek()).createFab;
            }
            if (obj != null)
            {
                ((View) (obj)).setVisibility(0);
            }
            createFab = null;
        }
        interfaceActivity = null;
    }

    public final void onDialogBackPressed()
    {
        if (createFab != null && (CreateFabInterface)interfaceActivity != null)
        {
            SpeedDialLayout speeddiallayout = ((CreateFabInterface)interfaceActivity).getCreateFabStack().getSpeedDial();
            if (speeddiallayout != null && speeddiallayout.isExpanded)
            {
                speeddiallayout.setExpanded(false, 0L);
                return;
            }
        }
        FragmentActivity fragmentactivity;
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        if (fragmentactivity instanceof com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayListener)
        {
            ((com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayListener)fragmentactivity).dismissOverlay(this, false);
            return;
        } else
        {
            dismiss();
            return;
        }
    }

    public final void onViewCreated(final View view, final Bundle createFab)
    {
        super.onViewCreated(view, createFab);
        createFab = view.findViewById(createFabId);
        if (createFab != null)
        {
            createFab.setVisibility(4);
            ViewTreeObserver viewtreeobserver = createFab.getViewTreeObserver();
            if (viewtreeobserver != null)
            {
                viewtreeobserver.addOnGlobalLayoutListener(new _cls1());
            }
        }
    }

    private class CreateFabInterface
    {

        public abstract CreateFabStack getCreateFabStack();
    }


    private class CreateFabStack
    {

        public final Stack stack = new Stack();

        abstract View findCreateFab();

        abstract Time getCreateFabDay();

        public final ObjectAnimator getHideAnimatorCreateFab()
        {
            Object obj;
            if (stack.empty())
            {
                obj = findCreateFab();
            } else
            {
                obj = ((Scope)stack.peek()).createFab;
            }
            if (obj == null)
            {
                return null;
            } else
            {
                int i = (int)((View) (obj)).getTranslationY();
                int j = ((View)((View) (obj)).getParent()).getHeight();
                int k = ((View) (obj)).getTop();
                obj = ObjectAnimator.ofFloat(obj, View.TRANSLATION_Y, new float[] {
                    (float)i, (float)(j - k)
                });
                ((ObjectAnimator) (obj)).setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
                return ((ObjectAnimator) (obj));
            }
        }

        public final ObjectAnimator getShowAnimatorCreateFab()
        {
            Object obj;
            if (stack.empty())
            {
                obj = findCreateFab();
            } else
            {
                obj = ((Scope)stack.peek()).createFab;
            }
            if (obj == null)
            {
                return null;
            }
            int i = (int)((View) (obj)).getTranslationY();
            if (i < 0)
            {
                return null;
            } else
            {
                obj = ObjectAnimator.ofFloat(obj, View.TRANSLATION_Y, new float[] {
                    (float)i, 0.0F
                });
                ((ObjectAnimator) (obj)).setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
                return ((ObjectAnimator) (obj));
            }
        }

        public SpeedDialLayout getSpeedDial()
        {
            View view;
            if (stack.empty())
            {
                view = null;
            } else
            {
                view = ((Scope)stack.peek()).createFabParent;
            }
            if (view == null)
            {
                return null;
            } else
            {
                return (SpeedDialLayout)view.findViewById(0x7f10013b);
            }
        }

        CreateFabStack()
        {
        }
    }


    private class _cls1
        implements android.view.ViewTreeObserver.OnGlobalLayoutListener
    {

        private final CreateFabFragment this$0;
        private final View val$createFab;
        private final View val$view;

        public final void onGlobalLayout()
        {
            createFab.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            Object obj = (CreateFabInterface)interfaceActivity;
            if (obj != null)
            {
                CreateFabFragment createfabfragment = CreateFabFragment.this;
                CreateFabStack createfabstack = ((CreateFabInterface) (obj)).getCreateFabStack();
                View view1 = createFab;
                View view2 = view;
                CreateFabStack.StartDay startday = getStartDayInterface();
                if (createfabstack.stack.empty())
                {
                    obj = createfabstack.findCreateFab();
                } else
                {
                    obj = ((CreateFabStack.Scope)createfabstack.stack.peek()).createFab;
                }
                if (obj != null)
                {
                    ((View) (obj)).setVisibility(4);
                }
                view1.setVisibility(0);
                createfabfragment.createFab = ((CreateFabStack.Scope)createfabstack.stack.push(new CreateFabStack.Scope(view1, view2, startday))).createFab;
            }
        }

        _cls1()
        {
            this$0 = CreateFabFragment.this;
            createFab = view1;
            view = view2;
            super();
        }
    }

}
