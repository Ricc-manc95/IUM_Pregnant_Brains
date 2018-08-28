// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.swipeclosing;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;

public class DraggableScrollView extends NestedScrollView
{
    public static interface ActionListener
    {

        public abstract void onActionTriggered();
    }

    public static interface SingleClickListener
    {

        public abstract void onSingleClick(View view);
    }


    private View draggableView;
    private boolean draggingStarted;
    private GestureDetector gestureDetector;
    private ActionListener listener;
    private int overscrollType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0;
    private float posY;
    public SingleClickListener singleClickListener;
    private float translationY;

    public DraggableScrollView(Context context)
    {
        super(context);
        draggableView = null;
        overscrollType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.NONE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0;
        draggingStarted = false;
    }

    public DraggableScrollView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        draggableView = null;
        overscrollType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.NONE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0;
        draggingStarted = false;
    }

    public DraggableScrollView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        draggableView = null;
        overscrollType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.NONE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0;
        draggingStarted = false;
    }

    private final boolean bottomReached()
    {
        boolean flag = false;
        int j = getHeight() - getPaddingBottom();
        int i;
        if (getChildCount() != 0)
        {
            i = getChildAt(0).getBottom();
        } else
        {
            i = 0;
        }
        if (i <= j || i == j + getScrollY())
        {
            flag = true;
        }
        return flag;
    }

    public final void attachDraggableView(View view, ActionListener actionlistener)
    {
        draggableView = view;
        listener = actionlistener;
        gestureDetector = new GestureDetector(getContext(), new _cls1());
        translationY = draggableView.getTranslationY();
    }

    protected void onOverScrolled(int i, int j, boolean flag, boolean flag1)
    {
        if (flag1 && draggableView != null)
        {
            if (getScrollY() == 0 && bottomReached())
            {
                overscrollType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.BOTH$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0;
                return;
            }
            if (getScrollY() == 0)
            {
                overscrollType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.TOP$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0;
                return;
            }
            if (bottomReached())
            {
                overscrollType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.BOTTOM$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0;
                return;
            } else
            {
                overscrollType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.NONE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0;
                super.onOverScrolled(i, j, flag, flag1);
                return;
            }
        } else
        {
            super.onOverScrolled(i, j, flag, flag1);
            return;
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if (gestureDetector == null || !gestureDetector.onTouchEvent(motionevent)) goto _L2; else goto _L1
_L1:
        if (singleClickListener != null)
        {
            singleClickListener.onSingleClick(this);
        }
_L4:
        return true;
_L2:
        if (overscrollType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0 == android.support.v4.content.ModernAsyncTask.Status.NONE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0)
        {
            return super.onTouchEvent(motionevent);
        }
        if (motionevent.getPointerCount() != 1)
        {
            draggingStarted = false;
            overscrollType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.NONE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0;
            draggableView.setTranslationY(translationY);
            return super.onTouchEvent(motionevent);
        }
        float f = motionevent.getRawY();
        if (!draggingStarted)
        {
            posY = f;
            draggingStarted = true;
        }
        f -= posY;
        if (f < 0.0F && overscrollType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0 == android.support.v4.content.ModernAsyncTask.Status.TOP$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0 || f > 0.0F && overscrollType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0 == android.support.v4.content.ModernAsyncTask.Status.BOTTOM$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0)
        {
            draggingStarted = false;
            overscrollType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.NONE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0;
            draggableView.setTranslationY(translationY);
            return super.onTouchEvent(motionevent);
        }
        switch (motionevent.getAction())
        {
        default:
            return false;

        case 2: // '\002'
            draggableView.setTranslationY(f * 0.3F + translationY);
            return true;

        case 1: // '\001'
            draggingStarted = false;
            overscrollType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.NONE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0;
            if (Math.abs(f) / (float)draggableView.getHeight() >= 0.3F)
            {
                listener.onActionTriggered();
                return true;
            } else
            {
                draggableView.animate().translationY(translationY).setDuration(300L).start();
                return true;
            }

        case 3: // '\003'
            draggingStarted = false;
            overscrollType$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.NONE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJETKN0PB3DHNN6QBECSNK8SJ1CTJM2OJCCL9M6SJFDHM5CQB5ESI4UTJ5E9PM6SJFDHM58UBGCKTG____0;
            return true;

        case 0: // '\0'
            break;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private class _cls1 extends android.view.GestureDetector.SimpleOnGestureListener
    {

        public final boolean onSingleTapUp(MotionEvent motionevent)
        {
            return true;
        }

        _cls1()
        {
        }
    }

}
