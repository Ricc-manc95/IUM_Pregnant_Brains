// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ScrollView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.newevent.CreateNewEventView;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.timely:
//            PinchZoomController

public class PagedScrollView extends ScrollView
{
    public static final class ScrollManager
        implements android.view.View.OnLayoutChangeListener
    {

        private boolean handleLayoutChanges;
        public boolean isScaleInProgress;
        public ArrayList scrollViews;
        public int verticalScrollPositionFromBottom;

        public final void add(PagedScrollView pagedscrollview)
        {
            pagedscrollview.setVerticalScrollPositionFromBottom(verticalScrollPositionFromBottom, false);
            scrollViews.add(pagedscrollview);
            pagedscrollview.addOnLayoutChangeListener(this);
            pagedscrollview.scrollManager = this;
            pagedscrollview.pinchZoomController = new PinchZoomController(pagedscrollview, this);
            pagedscrollview.pinchZoomController.bottomPadding = 0;
        }

        public final void notifyListeners(View view)
        {
            ArrayList arraylist = (ArrayList)scrollViews;
            int k = arraylist.size();
            int i = 0;
            do
            {
                if (i >= k)
                {
                    break;
                }
                Object obj = arraylist.get(i);
                int j = i + 1;
                obj = (PagedScrollView)obj;
                i = j;
                if (obj != view)
                {
                    ((PagedScrollView) (obj)).setVerticalScrollPositionFromBottom(verticalScrollPositionFromBottom, false);
                    i = j;
                }
            } while (true);
        }

        public final void onLayoutChange(View view, int i, int j, int k, int l, int i1, int j1, 
                int k1, int l1)
        {
            if (handleLayoutChanges || isScaleInProgress)
            {
                ((PagedScrollView)view).setVerticalScrollPositionFromBottom(verticalScrollPositionFromBottom, false);
            }
        }

        public ScrollManager()
        {
            this(true);
        }

        public ScrollManager(boolean flag)
        {
            scrollViews = new ArrayList();
            verticalScrollPositionFromBottom = 0;
            handleLayoutChanges = flag;
        }
    }


    private static final String TAG = com/google/android/calendar/timely/PagedScrollView.getSimpleName();
    private boolean isInternalScrollChange;
    public PinchZoomController pinchZoomController;
    public ScrollManager scrollManager;

    public PagedScrollView(Context context)
    {
        super(context);
        isInternalScrollChange = false;
    }

    public PagedScrollView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        isInternalScrollChange = false;
    }

    public final int computeScrollPositionFromBottom(int i)
    {
        return computeVerticalScrollRange() - (getHeight() + i);
    }

    public final int getVerticalScrollPositionFromBottom()
    {
        int i = computeVerticalScrollOffset();
        return computeVerticalScrollRange() - (i + getHeight());
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas = TAG;
        boolean flag;
        if (LogUtils.maxEnabledLogLevel > 3)
        {
            flag = false;
        } else
        if (Log.isLoggable(canvas, 3))
        {
            flag = true;
        } else
        {
            flag = Log.isLoggable(canvas, 3);
        }
        if (flag)
        {
            canvas = new StringBuilder();
            View view = getChildAt(0);
            if (view != null)
            {
                canvas.append(view.getClass().getSimpleName()).append(": ");
            }
            int i = computeVerticalScrollOffset();
            int j = computeVerticalScrollRange();
            int k = getHeight();
            canvas.append((new StringBuilder(35)).append("PagedScrollView.onDraw: ").append(j - (i + k)).toString());
            LogUtils.d(TAG, "%s", new Object[] {
                canvas
            });
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        if (isEnabled())
        {
            return super.onInterceptTouchEvent(motionevent);
        } else
        {
            return false;
        }
    }

    protected void onScrollChanged(int i, int j, int k, int l)
    {
        super.onScrollChanged(i, j, k, l);
        i = computeVerticalScrollOffset();
        i = computeVerticalScrollRange() - (i + getHeight());
        Object obj = TAG;
        boolean flag;
        if (LogUtils.maxEnabledLogLevel > 3)
        {
            flag = false;
        } else
        if (Log.isLoggable(((String) (obj)), 3))
        {
            flag = true;
        } else
        {
            flag = Log.isLoggable(((String) (obj)), 3);
        }
        if (flag)
        {
            StringBuilder stringbuilder = new StringBuilder();
            if (isInternalScrollChange)
            {
                obj = "";
            } else
            {
                obj = "EXTERNAL: ";
            }
            stringbuilder.append(((String) (obj)));
            obj = getChildAt(0);
            if (obj != null)
            {
                stringbuilder.append(obj.getClass().getSimpleName()).append(": ");
            }
            stringbuilder.append(computeVerticalScrollRange() - (getHeight() + l)).append(" => ");
            stringbuilder.append(i);
            LogUtils.d(TAG, "%s", new Object[] {
                stringbuilder
            });
        }
        if (!isInternalScrollChange)
        {
            CreateNewEventView.removeSelectedTime();
            if (scrollManager != null && !scrollManager.isScaleInProgress)
            {
                obj = scrollManager;
                if (i != ((ScrollManager) (obj)).verticalScrollPositionFromBottom)
                {
                    obj.verticalScrollPositionFromBottom = i;
                    ((ScrollManager) (obj)).notifyListeners(this);
                }
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        if (!isEnabled())
        {
            flag = false;
        } else
        {
            boolean flag1 = super.onTouchEvent(motionevent);
            flag = flag1;
            if (pinchZoomController != null)
            {
                pinchZoomController.scaleDetector.onTouchEvent(motionevent);
                return flag1;
            }
        }
        return flag;
    }

    public final void setVerticalScrollPositionFromBottom(int i, boolean flag)
    {
        i = computeVerticalScrollRange() - getHeight() - i;
        isInternalScrollChange = true;
        if (flag)
        {
            smoothScrollTo(getScrollX(), i);
        } else
        {
            setScrollY(i);
        }
        isInternalScrollChange = false;
    }

    public final void verticalScrollBy(int i)
    {
        ScrollManager scrollmanager = scrollManager;
        int j = computeVerticalScrollOffset();
        i = (computeVerticalScrollRange() - (j + getHeight())) + i;
        if (i != scrollmanager.verticalScrollPositionFromBottom)
        {
            scrollmanager.verticalScrollPositionFromBottom = i;
            scrollmanager.notifyListeners(null);
        }
    }

}
