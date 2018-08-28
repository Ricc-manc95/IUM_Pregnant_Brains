// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.bottomsheet;

import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.view.View;
import java.lang.ref.WeakReference;

// Referenced classes of package android.support.design.bottomsheet:
//            BottomSheetBehavior

final class this._cls0 extends android.support.v4.widget.k
{

    private final BottomSheetBehavior this$0;

    public final int clampViewPositionHorizontal$51662RJ4E9NMIP1FEPKMATPFAPKMATPR954III8_0(View view, int i)
    {
        return view.getLeft();
    }

    public final int clampViewPositionVertical$51662RJ4E9NMIP1FEPKMATPFAPKMATPR954III8_0(View view, int i)
    {
        view = BottomSheetBehavior.this;
        int j;
        int k;
        if (((BottomSheetBehavior) (view)).fitToContents)
        {
            j = ((BottomSheetBehavior) (view)).fitToContentsOffset;
        } else
        {
            j = 0;
        }
        if (hideable)
        {
            k = parentHeight;
        } else
        {
            k = collapsedOffset;
        }
        if (i < j)
        {
            return j;
        }
        if (i > k)
        {
            return k;
        } else
        {
            return i;
        }
    }

    public final int getViewVerticalDragRange$51662RJ4E9NMIP1FEPKMATPFAPKMATPR554G____0()
    {
        if (hideable)
        {
            return parentHeight;
        } else
        {
            return collapsedOffset;
        }
    }

    public final void onViewDragStateChanged(int i)
    {
        if (i == 1)
        {
            setStateInternal(1);
        }
    }

    public final void onViewPositionChanged$51662RJ4E9NMIP1FEPKMATPFAPKMATPR954KII99AO______0(View view, int i, int j)
    {
        dispatchOnSlide(j);
    }

    public final void onViewReleased(View view, float f, float f1)
    {
        byte byte0 = 3;
        int i;
        if (f1 < 0.0F)
        {
            if (fitToContents)
            {
                i = fitToContentsOffset;
            } else
            if (view.getTop() > halfExpandedOffset)
            {
                i = halfExpandedOffset;
                byte0 = 6;
            } else
            {
                i = 0;
            }
        } else
        if (hideable && shouldHide(view, f1) && (view.getTop() > collapsedOffset || Math.abs(f) < Math.abs(f1)))
        {
            i = parentHeight;
            byte0 = 5;
        } else
        if (f1 == 0.0F || Math.abs(f) > Math.abs(f1))
        {
            i = view.getTop();
            if (fitToContents)
            {
                if (Math.abs(i - fitToContentsOffset) < Math.abs(i - collapsedOffset))
                {
                    i = fitToContentsOffset;
                } else
                {
                    i = collapsedOffset;
                    byte0 = 4;
                }
            } else
            if (i < halfExpandedOffset)
            {
                if (i < Math.abs(i - collapsedOffset))
                {
                    i = 0;
                } else
                {
                    i = halfExpandedOffset;
                    byte0 = 6;
                }
            } else
            if (Math.abs(i - halfExpandedOffset) < Math.abs(i - collapsedOffset))
            {
                i = halfExpandedOffset;
                byte0 = 6;
            } else
            {
                i = collapsedOffset;
                byte0 = 4;
            }
        } else
        {
            i = collapsedOffset;
            byte0 = 4;
        }
        if (viewDragHelper.settleCapturedViewAt(view.getLeft(), i))
        {
            setStateInternal(2);
            ViewCompat.postOnAnimation(view, new ttleRunnable(BottomSheetBehavior.this, view, byte0));
            return;
        } else
        {
            setStateInternal(byte0);
            return;
        }
    }

    public final boolean tryCaptureView(View view, int i)
    {
        if (state == 1)
        {
            return false;
        }
        if (touchingScrollingChild)
        {
            return false;
        }
        if (state == 3 && activePointerId == i)
        {
            View view1 = (View)nestedScrollingChildRef.get();
            if (view1 != null && view1.canScrollVertically(-1))
            {
                return false;
            }
        }
        return viewRef != null && viewRef.get() == view;
    }

    ttleRunnable()
    {
        this$0 = BottomSheetBehavior.this;
        super();
    }
}
