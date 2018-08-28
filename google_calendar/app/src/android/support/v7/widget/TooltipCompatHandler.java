// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.widget.TextView;

// Referenced classes of package android.support.v7.widget:
//            TooltipPopup

public final class TooltipCompatHandler
    implements android.view.View.OnAttachStateChangeListener, android.view.View.OnHoverListener, android.view.View.OnLongClickListener
{

    public static TooltipCompatHandler sActiveHandler;
    public static TooltipCompatHandler sPendingHandler;
    public final View mAnchor;
    private int mAnchorX;
    private int mAnchorY;
    private boolean mFromTouch;
    private final Runnable mHideRunnable = new _cls2();
    private final int mHoverSlop;
    private TooltipPopup mPopup;
    public final Runnable mShowRunnable = new _cls1();
    private final CharSequence mTooltipText;

    public TooltipCompatHandler(View view, CharSequence charsequence)
    {
        mAnchor = view;
        mTooltipText = charsequence;
        mHoverSlop = ViewConfigurationCompat.getScaledHoverSlop(ViewConfiguration.get(mAnchor.getContext()));
        mAnchorX = 0x7fffffff;
        mAnchorY = 0x7fffffff;
        mAnchor.setOnLongClickListener(this);
        mAnchor.setOnHoverListener(this);
    }

    public final void hide()
    {
        if (sActiveHandler == this)
        {
            sActiveHandler = null;
            if (mPopup != null)
            {
                Object obj = mPopup;
                boolean flag;
                if (((TooltipPopup) (obj)).mContentView.getParent() != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    ((WindowManager)((TooltipPopup) (obj)).mContext.getSystemService("window")).removeView(((TooltipPopup) (obj)).mContentView);
                }
                mPopup = null;
                mAnchorX = 0x7fffffff;
                mAnchorY = 0x7fffffff;
                mAnchor.removeOnAttachStateChangeListener(this);
            } else
            {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (sPendingHandler == this)
        {
            if (sPendingHandler != null)
            {
                obj = sPendingHandler;
                ((TooltipCompatHandler) (obj)).mAnchor.removeCallbacks(((TooltipCompatHandler) (obj)).mShowRunnable);
            }
            sPendingHandler = null;
            if (false)
            {
                obj = sPendingHandler;
                ((TooltipCompatHandler) (obj)).mAnchor.postDelayed(((TooltipCompatHandler) (obj)).mShowRunnable, ViewConfiguration.getLongPressTimeout());
            }
        }
        mAnchor.removeCallbacks(mHideRunnable);
    }

    public final boolean onHover(View view, MotionEvent motionevent)
    {
        if (mPopup == null || !mFromTouch) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        if ((view = (AccessibilityManager)mAnchor.getContext().getSystemService("accessibility")).isEnabled() && view.isTouchExplorationEnabled()) goto _L1; else goto _L3
_L3:
        switch (motionevent.getAction())
        {
        case 8: // '\b'
        case 9: // '\t'
        default:
            return false;

        case 7: // '\007'
            if (mAnchor.isEnabled() && mPopup == null)
            {
                int i = (int)motionevent.getX();
                int j = (int)motionevent.getY();
                if (Math.abs(i - mAnchorX) <= mHoverSlop && Math.abs(j - mAnchorY) <= mHoverSlop)
                {
                    i = 0;
                } else
                {
                    mAnchorX = i;
                    mAnchorY = j;
                    i = 1;
                }
                if (i != 0)
                {
                    if (sPendingHandler != null)
                    {
                        view = sPendingHandler;
                        ((TooltipCompatHandler) (view)).mAnchor.removeCallbacks(((TooltipCompatHandler) (view)).mShowRunnable);
                    }
                    sPendingHandler = this;
                    if (this != null)
                    {
                        view = sPendingHandler;
                        ((TooltipCompatHandler) (view)).mAnchor.postDelayed(((TooltipCompatHandler) (view)).mShowRunnable, ViewConfiguration.getLongPressTimeout());
                        return false;
                    }
                }
            }
            break;

        case 10: // '\n'
            mAnchorX = 0x7fffffff;
            mAnchorY = 0x7fffffff;
            hide();
            return false;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    public final boolean onLongClick(View view)
    {
        mAnchorX = view.getWidth() / 2;
        mAnchorY = view.getHeight() / 2;
        show(true);
        return true;
    }

    public final void onViewAttachedToWindow(View view)
    {
    }

    public final void onViewDetachedFromWindow(View view)
    {
        hide();
    }

    final void show(boolean flag)
    {
        Object obj;
        Object obj1;
        TooltipPopup tooltippopup;
        android.view.WindowManager.LayoutParams layoutparams;
        View view1;
        int i;
        int j;
        int k;
        int j1;
        long l1;
        if (!ViewCompat.isAttachedToWindow(mAnchor))
        {
            return;
        }
        if (sPendingHandler != null)
        {
            TooltipCompatHandler tooltipcompathandler = sPendingHandler;
            tooltipcompathandler.mAnchor.removeCallbacks(tooltipcompathandler.mShowRunnable);
        }
        sPendingHandler = null;
        if (false)
        {
            TooltipCompatHandler tooltipcompathandler1 = sPendingHandler;
            tooltipcompathandler1.mAnchor.postDelayed(tooltipcompathandler1.mShowRunnable, ViewConfiguration.getLongPressTimeout());
        }
        if (sActiveHandler != null)
        {
            sActiveHandler.hide();
        }
        sActiveHandler = this;
        mFromTouch = flag;
        mPopup = new TooltipPopup(mAnchor.getContext());
        tooltippopup = mPopup;
        view1 = mAnchor;
        j = mAnchorX;
        k = mAnchorY;
        flag = mFromTouch;
        obj = mTooltipText;
        int l;
        if (tooltippopup.mContentView.getParent() != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            View view;
            if (tooltippopup.mContentView.getParent() != null)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                ((WindowManager)tooltippopup.mContext.getSystemService("window")).removeView(tooltippopup.mContentView);
            }
        }
        tooltippopup.mMessageView.setText(((CharSequence) (obj)));
        layoutparams = tooltippopup.mLayoutParams;
        layoutparams.token = view1.getApplicationWindowToken();
        l = tooltippopup.mContext.getResources().getDimensionPixelOffset(0x7f0e03c7);
        if (view1.getWidth() >= l)
        {
            i = j;
        } else
        {
            i = view1.getWidth() / 2;
        }
        if (view1.getHeight() >= l)
        {
            l = tooltippopup.mContext.getResources().getDimensionPixelOffset(0x7f0e03c6);
            j = k + l;
            k -= l;
        } else
        {
            j = view1.getHeight();
            k = 0;
        }
        layoutparams.gravity = 49;
        obj = tooltippopup.mContext.getResources();
        if (flag)
        {
            l = 0x7f0e03ca;
        } else
        {
            l = 0x7f0e03c9;
        }
        j1 = ((Resources) (obj)).getDimensionPixelOffset(l);
        view = view1.getRootView();
        obj1 = view.getLayoutParams();
        if (!(obj1 instanceof android.view.WindowManager.LayoutParams)) goto _L2; else goto _L1
_L1:
        obj = view;
        if (((android.view.WindowManager.LayoutParams)obj1).type == 2) goto _L3; else goto _L2
_L2:
        obj1 = view1.getContext();
_L7:
        obj = view;
        if (!(obj1 instanceof ContextWrapper)) goto _L3; else goto _L4
_L4:
        if (!(obj1 instanceof Activity)) goto _L6; else goto _L5
_L5:
        obj = ((Activity)obj1).getWindow().getDecorView();
_L3:
        if (obj == null)
        {
            Log.e("TooltipPopup", "Cannot find app view");
        } else
        {
label0:
            {
                ((View) (obj)).getWindowVisibleDisplayFrame(tooltippopup.mTmpDisplayFrame);
                if (tooltippopup.mTmpDisplayFrame.left < 0 && tooltippopup.mTmpDisplayFrame.top < 0)
                {
                    Object obj2 = tooltippopup.mContext.getResources();
                    int i1 = ((Resources) (obj2)).getIdentifier("status_bar_height", "dimen", "android");
                    int ai[];
                    if (i1 != 0)
                    {
                        i1 = ((Resources) (obj2)).getDimensionPixelSize(i1);
                    } else
                    {
                        i1 = 0;
                    }
                    obj2 = ((Resources) (obj2)).getDisplayMetrics();
                    tooltippopup.mTmpDisplayFrame.set(0, i1, ((DisplayMetrics) (obj2)).widthPixels, ((DisplayMetrics) (obj2)).heightPixels);
                }
                ((View) (obj)).getLocationOnScreen(tooltippopup.mTmpAppPos);
                view1.getLocationOnScreen(tooltippopup.mTmpAnchorPos);
                ai = tooltippopup.mTmpAnchorPos;
                ai[0] = ai[0] - tooltippopup.mTmpAppPos[0];
                ai = tooltippopup.mTmpAnchorPos;
                ai[1] = ai[1] - tooltippopup.mTmpAppPos[1];
                layoutparams.x = (tooltippopup.mTmpAnchorPos[0] + i) - ((View) (obj)).getWidth() / 2;
                i = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
                tooltippopup.mContentView.measure(i, i);
                i = tooltippopup.mContentView.getMeasuredHeight();
                k = (tooltippopup.mTmpAnchorPos[1] + k) - j1 - i;
                j = tooltippopup.mTmpAnchorPos[1] + j + j1;
                if (flag)
                {
                    if (k >= 0)
                    {
                        break label0;
                    }
                    layoutparams.y = j;
                } else
                {
                    if (i + j > tooltippopup.mTmpDisplayFrame.height())
                    {
                        break label0;
                    }
                    layoutparams.y = j;
                }
            }
        }
_L8:
        ((WindowManager)tooltippopup.mContext.getSystemService("window")).addView(tooltippopup.mContentView, tooltippopup.mLayoutParams);
        mAnchor.addOnAttachStateChangeListener(this);
        if (mFromTouch)
        {
            l1 = 2500L;
        } else
        if ((ViewCompat.getWindowSystemUiVisibility(mAnchor) & 1) == 1)
        {
            l1 = 3000L - (long)ViewConfiguration.getLongPressTimeout();
        } else
        {
            l1 = 15000L - (long)ViewConfiguration.getLongPressTimeout();
        }
        mAnchor.removeCallbacks(mHideRunnable);
        mAnchor.postDelayed(mHideRunnable, l1);
        return;
_L6:
        obj1 = ((ContextWrapper)obj1).getBaseContext();
          goto _L7
        layoutparams.y = k;
          goto _L8
    }

    private class _cls1
        implements Runnable
    {

        private final TooltipCompatHandler this$0;

        public final void run()
        {
            show(false);
        }

        _cls1()
        {
            this$0 = TooltipCompatHandler.this;
            super();
        }
    }


    private class _cls2
        implements Runnable
    {

        private final TooltipCompatHandler this$0;

        public final void run()
        {
            hide();
        }

        _cls2()
        {
            this$0 = TooltipCompatHandler.this;
            super();
        }
    }

}
