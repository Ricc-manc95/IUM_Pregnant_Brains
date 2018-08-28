// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.dnd;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.SystemClock;
import android.view.ViewConfiguration;
import com.google.android.calendar.utils.datatypes.Circle;

// Referenced classes of package com.google.android.calendar.timeline.dnd:
//            DragAreaEdges, ScrollRateInterpolator

public final class DragScrollPageController
{

    private final DragAreaEdges area;
    public final AreaState areas[] = new AreaState[4];
    public final Delegate _flddelegate;
    public boolean disableAreasAroundNextPosition;
    private Circle noPageScrollWithin;
    private final AreaState.Callback pageCallback = new _cls1();
    private final AreaState.Callback scrollCallback = new _cls2();
    public final ScrollRateInterpolator scrollInterpolator;
    private final int touchSlop;

    public DragScrollPageController(Context context, Rect rect, int i, Delegate delegate1)
    {
        _flddelegate = delegate1;
        area = new DragAreaEdges(rect, i);
        scrollInterpolator = new ScrollRateInterpolator(context.getResources());
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        areas[0] = new AreaState(0, pageCallback, 800L);
        areas[1] = new AreaState(1, pageCallback, 800L);
        areas[2] = new AreaState(2, scrollCallback, 16L);
        areas[3] = new AreaState(3, scrollCallback, 16L);
    }

    public final void onDrag(int i, int j)
    {
        int k;
        if (disableAreasAroundNextPosition)
        {
            noPageScrollWithin = new Circle(i, j, touchSlop);
            disableAreasAroundNextPosition = false;
            k = 1;
        } else
        {
label0:
            {
                if (noPageScrollWithin == null)
                {
                    break MISSING_BLOCK_LABEL_115;
                }
                Circle circle = noPageScrollWithin;
                if (Math.pow(i - circle.x, 2D) + Math.pow(j - circle.y, 2D) <= (double)circle.rr)
                {
                    k = 1;
                } else
                {
                    k = 0;
                }
                if (!k)
                {
                    break label0;
                }
                k = 1;
            }
        }
_L3:
        if (!k) goto _L2; else goto _L1
_L1:
        return;
        noPageScrollWithin = null;
        k = 0;
          goto _L3
_L2:
        k = 0;
_L10:
        if (k >= areas.length) goto _L1; else goto _L4
_L4:
        AreaState areastate;
        DragAreaEdges dragareaedges;
        areastate = areas[k];
        dragareaedges = area;
        k;
        JVM INSTR tableswitch 0 3: default 180
    //                   0 210
    //                   1 328
    //                   2 367
    //                   3 406;
           goto _L5 _L6 _L7 _L8 _L9
_L9:
        break MISSING_BLOCK_LABEL_406;
_L5:
        throw new AssertionError((new StringBuilder(37)).append("Invalid value for 'edge': ").append(k).toString());
_L6:
        float f;
        int l = dragareaedges.boundaries.left;
        f = Math.min(Math.max((dragareaedges.edgeSize - (float)(i - l)) / dragareaedges.edgeSize, 0.0F), 1.0F);
_L11:
        int i1;
        boolean flag;
        if (f > 0.0F)
        {
            i1 = 1;
        } else
        {
            i1 = 0;
        }
        if (areastate.currentDepth > 0.0F)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (i1 != 0 && !flag)
        {
            areastate.enterTime = SystemClock.uptimeMillis();
            AreaState.HANDLER.postDelayed(areastate.callbackRunner, areastate.callbackInterval);
            areastate.callback._mth514IILG_0();
        } else
        if (i1 == 0 && flag)
        {
            AreaState.HANDLER.removeCallbacks(areastate.callbackRunner);
            areastate.callback._mth514IILG_0();
        }
        areastate.currentDepth = f;
        k++;
          goto _L10
_L7:
        i1 = dragareaedges.boundaries.right;
        f = Math.min(Math.max((dragareaedges.edgeSize - (float)(i1 - i)) / dragareaedges.edgeSize, 0.0F), 1.0F);
          goto _L11
_L8:
        i1 = dragareaedges.boundaries.top;
        f = Math.min(Math.max((dragareaedges.edgeSize - (float)(j - i1)) / dragareaedges.edgeSize, 0.0F), 1.0F);
          goto _L11
        i1 = dragareaedges.boundaries.bottom;
        f = Math.min(Math.max((dragareaedges.edgeSize - (float)(i1 - j)) / dragareaedges.edgeSize, 0.0F), 1.0F);
          goto _L11
    }

    public final void onEnd()
    {
        int i = 0;
        while (i < areas.length) 
        {
            AreaState areastate = areas[i];
            boolean flag;
            boolean flag1;
            if (0.0F > 0.0F)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (areastate.currentDepth > 0.0F)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag && !flag1)
            {
                areastate.enterTime = SystemClock.uptimeMillis();
                AreaState.HANDLER.postDelayed(areastate.callbackRunner, areastate.callbackInterval);
                areastate.callback._mth514IILG_0();
            } else
            if (!flag && flag1)
            {
                AreaState.HANDLER.removeCallbacks(areastate.callbackRunner);
                areastate.callback._mth514IILG_0();
            }
            areastate.currentDepth = 0.0F;
            i++;
        }
    }

    private class _cls1
        implements AreaState.Callback
    {

        private final DragScrollPageController this$0;

        public final void enterArea$514IILG_0()
        {
            _flddelegate.onPageModeStart();
        }

        public final void inArea(int i, float f, long l)
        {
            Delegate delegate1 = _flddelegate;
            if (i == 0)
            {
                i = -1;
            } else
            {
                i = 1;
            }
            delegate1.onPage(i);
        }

        public final void leaveArea$514IILG_0()
        {
            _flddelegate.onPageModeEnd();
        }

        _cls1()
        {
            this$0 = DragScrollPageController.this;
            super();
        }

        private class Delegate
        {

            public abstract void onPage(int i);

            public abstract void onPageModeEnd();

            public abstract void onPageModeStart();

            public abstract void onScroll(int i);

            public abstract void onScrollModeEnd();

            public abstract void onScrollModeStart();
        }

    }


    private class _cls2
        implements AreaState.Callback
    {

        private final DragScrollPageController this$0;

        public final void enterArea$514IILG_0()
        {
            _flddelegate.onScrollModeStart();
        }

        public final void inArea(int i, float f, long l)
        {
            Delegate delegate1 = _flddelegate;
            ScrollRateInterpolator scrollrateinterpolator = scrollInterpolator;
            double d = f;
            double d1 = scrollrateinterpolator.maxScroll;
            int j = (int)Math.max(1.0D, (Math.pow(d - 1.0D, 5D) + 1.0D) * d1 * Math.min(1.0D, (double)l / 2000D));
            if (i == 2)
            {
                i = 1;
            } else
            {
                i = -1;
            }
            delegate1.onScroll(i * j);
        }

        public final void leaveArea$514IILG_0()
        {
            _flddelegate.onScrollModeEnd();
        }

        _cls2()
        {
            this$0 = DragScrollPageController.this;
            super();
        }
    }


    private class AreaState
    {

        public static final Handler HANDLER = new Handler();
        public final Callback callback;
        public final long callbackInterval;
        public final Runnable callbackRunner = new _cls1();
        public float currentDepth;
        public final int edge;
        public long enterTime;


        AreaState(int i, Callback callback1, long l)
        {
            class _cls1
                implements Runnable
            {

                private final AreaState this$0;

                public final void run()
                {
                    class Callback
                    {

                        public abstract void enterArea$514IILG_0();

                        public abstract void inArea(int j, float f, long l1);

                        public abstract void leaveArea$514IILG_0();
                    }

                    callback.inArea(edge, currentDepth, SystemClock.uptimeMillis() - enterTime);
                    AreaState.HANDLER.postDelayed(this, callbackInterval);
                }

                _cls1()
                {
                    this$0 = AreaState.this;
                    super();
                }
            }

            edge = i;
            callback = callback1;
            callbackInterval = l;
        }
    }

}
