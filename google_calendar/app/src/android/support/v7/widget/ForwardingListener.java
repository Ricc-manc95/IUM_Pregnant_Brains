// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.os.SystemClock;
import android.support.v7.view.menu.ShowableListMenu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;

// Referenced classes of package android.support.v7.widget:
//            DropDownListView

public abstract class ForwardingListener
    implements android.view.View.OnAttachStateChangeListener, android.view.View.OnTouchListener
{

    private int mActivePointerId;
    private Runnable mDisallowIntercept;
    public boolean mForwarding;
    private final int mLongPressTimeout;
    private final float mScaledTouchSlop;
    public final View mSrc;
    private final int mTapTimeout = ViewConfiguration.getTapTimeout();
    private final int mTmpLocation[] = new int[2];
    private Runnable mTriggerLongPress;

    public ForwardingListener(View view)
    {
        mSrc = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        mScaledTouchSlop = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        mLongPressTimeout = (mTapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
    }

    final void clearCallbacks()
    {
        if (mTriggerLongPress != null)
        {
            mSrc.removeCallbacks(mTriggerLongPress);
        }
        if (mDisallowIntercept != null)
        {
            mSrc.removeCallbacks(mDisallowIntercept);
        }
    }

    public abstract ShowableListMenu getPopup();

    public boolean onForwardingStarted()
    {
        ShowableListMenu showablelistmenu = getPopup();
        if (showablelistmenu != null && !showablelistmenu.isShowing())
        {
            showablelistmenu.show();
        }
        return true;
    }

    protected boolean onForwardingStopped()
    {
        ShowableListMenu showablelistmenu = getPopup();
        if (showablelistmenu != null && showablelistmenu.isShowing())
        {
            showablelistmenu.dismiss();
        }
        return true;
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        boolean flag1;
        boolean flag2;
        flag1 = false;
        flag2 = mForwarding;
        if (!flag2) goto _L2; else goto _L1
_L1:
        boolean flag;
        view = mSrc;
        Object obj = getPopup();
        int i;
        if (obj == null || !((ShowableListMenu) (obj)).isShowing())
        {
            i = 0;
        } else
        {
            obj = (DropDownListView)((ShowableListMenu) (obj)).getListView();
            if (obj == null || !((DropDownListView) (obj)).isShown())
            {
                i = 0;
            } else
            {
                MotionEvent motionevent1 = MotionEvent.obtainNoHistory(motionevent);
                int ai[] = mTmpLocation;
                view.getLocationOnScreen(ai);
                motionevent1.offsetLocation(ai[0], ai[1]);
                view = mTmpLocation;
                ((View) (obj)).getLocationOnScreen(view);
                motionevent1.offsetLocation(-view[0], -view[1]);
                flag = ((DropDownListView) (obj)).onForwardedEvent(motionevent1, mActivePointerId);
                motionevent1.recycle();
                i = motionevent.getActionMasked();
                if (i != 1 && i != 3)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (flag && i != 0)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
            }
        }
        if (i != 0 || !onForwardingStopped())
        {
            flag = true;
        } else
        {
            flag = false;
        }
_L9:
label0:
        {
            mForwarding = flag;
            if (!flag)
            {
                flag = flag1;
                if (!flag2)
                {
                    break label0;
                }
            }
            flag = true;
        }
        return flag;
_L2:
        view = mSrc;
        if (!view.isEnabled()) goto _L4; else goto _L3
_L3:
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 3: default 292
    //                   0 348
    //                   1 550
    //                   2 426
    //                   3 550;
           goto _L5 _L6 _L7 _L8 _L7
_L5:
        break; /* Loop/switch isn't completed */
_L7:
        break MISSING_BLOCK_LABEL_550;
_L4:
        int j = 0;
_L12:
        float f;
        float f1;
        float f2;
        if (j != 0 && onForwardingStarted())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            long l = SystemClock.uptimeMillis();
            view = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
            mSrc.onTouchEvent(view);
            view.recycle();
        }
          goto _L9
_L6:
        mActivePointerId = motionevent.getPointerId(0);
        if (mDisallowIntercept == null)
        {
            mDisallowIntercept = new DisallowIntercept();
        }
        view.postDelayed(mDisallowIntercept, mTapTimeout);
        if (mTriggerLongPress == null)
        {
            mTriggerLongPress = new TriggerLongPress();
        }
        view.postDelayed(mTriggerLongPress, mLongPressTimeout);
          goto _L4
_L8:
        j = motionevent.findPointerIndex(mActivePointerId);
        if (j < 0) goto _L4; else goto _L10
_L10:
        f = motionevent.getX(j);
        f1 = motionevent.getY(j);
        f2 = mScaledTouchSlop;
        if (f >= -f2 && f1 >= -f2 && f < (float)(view.getRight() - view.getLeft()) + f2 && f1 < (float)(view.getBottom() - view.getTop()) + f2)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0) goto _L4; else goto _L11
_L11:
        clearCallbacks();
        view.getParent().requestDisallowInterceptTouchEvent(true);
        j = 1;
          goto _L12
        clearCallbacks();
          goto _L4
    }

    public void onViewAttachedToWindow(View view)
    {
    }

    public void onViewDetachedFromWindow(View view)
    {
        mForwarding = false;
        mActivePointerId = -1;
        if (mDisallowIntercept != null)
        {
            mSrc.removeCallbacks(mDisallowIntercept);
        }
    }

    private class DisallowIntercept
        implements Runnable
    {

        private final ForwardingListener this$0;

        public final void run()
        {
            ViewParent viewparent = mSrc.getParent();
            if (viewparent != null)
            {
                viewparent.requestDisallowInterceptTouchEvent(true);
            }
        }

        DisallowIntercept()
        {
            this$0 = ForwardingListener.this;
            super();
        }
    }


    private class TriggerLongPress
        implements Runnable
    {

        private final ForwardingListener this$0;

        public final void run()
        {
            ForwardingListener forwardinglistener = ForwardingListener.this;
            forwardinglistener.clearCallbacks();
            View view;
            for (view = forwardinglistener.mSrc; !view.isEnabled() || view.isLongClickable() || !forwardinglistener.onForwardingStarted();)
            {
                return;
            }

            view.getParent().requestDisallowInterceptTouchEvent(true);
            long l = SystemClock.uptimeMillis();
            MotionEvent motionevent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
            view.onTouchEvent(motionevent);
            motionevent.recycle();
            forwardinglistener.mForwarding = true;
        }

        TriggerLongPress()
        {
            this$0 = ForwardingListener.this;
            super();
        }
    }

}
