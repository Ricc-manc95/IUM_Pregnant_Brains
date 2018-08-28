// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.time;

import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import com.android.datetimepicker.HapticFeedbackController;
import java.util.List;

// Referenced classes of package com.android.datetimepicker.time:
//            CircleView, AmPmCirclesView, RadialTextsView, RadialSelectorView

public class RadialPickerLayout extends FrameLayout
    implements android.view.View.OnTouchListener
{
    public static interface OnValueSelectedListener
    {

        public abstract void onValueSelected(int i, int j, boolean flag);
    }


    private final int TAP_TIMEOUT = ViewConfiguration.getTapTimeout();
    private final int TOUCH_SLOP;
    public AccessibilityManager accessibilityManager;
    public AmPmCirclesView amPmCirclesView;
    public CircleView circleView;
    public int currentHoursOfDay;
    public int currentItemShowing;
    public int currentMinutes;
    public boolean doingMove;
    private boolean doingTouch;
    public int downDegrees;
    private float downX;
    private float downY;
    private View grayBox;
    private Handler handler;
    public HapticFeedbackController hapticFeedbackController;
    public boolean hideAmPm;
    public RadialSelectorView hourRadialSelectorView;
    public RadialTextsView hourRadialTextsView;
    private boolean inputEnabled;
    public boolean is24HourMode;
    public int isTouchingAmOrPm;
    public int lastValueSelected;
    public OnValueSelectedListener listener;
    public RadialSelectorView minuteRadialSelectorView;
    public RadialTextsView minuteRadialTextsView;
    private int snapPrefer30sMap[];
    public boolean timeInitialized;
    public AnimatorSet transition;

    public RadialPickerLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        isTouchingAmOrPm = -1;
        handler = new Handler();
        setOnTouchListener(this);
        TOUCH_SLOP = ViewConfiguration.get(context).getScaledTouchSlop();
        doingMove = false;
        circleView = new CircleView(context);
        addView(circleView);
        amPmCirclesView = new AmPmCirclesView(context);
        addView(amPmCirclesView);
        hourRadialTextsView = new RadialTextsView(context);
        addView(hourRadialTextsView);
        minuteRadialTextsView = new RadialTextsView(context);
        addView(minuteRadialTextsView);
        hourRadialSelectorView = new RadialSelectorView(context);
        addView(hourRadialSelectorView);
        minuteRadialSelectorView = new RadialSelectorView(context);
        addView(minuteRadialSelectorView);
        snapPrefer30sMap = new int[361];
        int i1 = 8;
        int k = 0;
        int j = 1;
        int i = 0;
        do
        {
            while (k < 361) 
            {
                snapPrefer30sMap[k] = i;
                int l;
                if (j == i1)
                {
                    l = i + 6;
                    if (l == 360)
                    {
                        i = 7;
                    } else
                    if (l % 30 == 0)
                    {
                        i = 14;
                    } else
                    {
                        i = 4;
                    }
                    j = 1;
                    i1 = i;
                } else
                {
                    j++;
                    l = i;
                }
                k++;
                i = l;
            }
            lastValueSelected = -1;
            inputEnabled = true;
            grayBox = new View(context);
            grayBox.setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, -1));
            grayBox.setBackgroundColor(getResources().getColor(0x7f0d031e));
            grayBox.setVisibility(4);
            addView(grayBox);
            accessibilityManager = (AccessibilityManager)context.getSystemService("accessibility");
            timeInitialized = false;
            return;
        } while (true);
    }

    private final int getDegreesFromCoords(float f, float f1, boolean flag, Boolean aboolean[])
    {
        int i = getCurrentItemShowing();
        if (i == 0)
        {
            return hourRadialSelectorView.getDegreesFromCoords(f, f1, flag, aboolean);
        }
        if (i == 1)
        {
            return minuteRadialSelectorView.getDegreesFromCoords(f, f1, flag, aboolean);
        } else
        {
            return -1;
        }
    }

    private static int snapOnly30s(int i, int j)
    {
        int k;
        int l;
        k = (i / 30) * 30;
        l = k + 30;
        if (j == 1) goto _L2; else goto _L1
_L1:
        if (j != -1) goto _L4; else goto _L3
_L3:
        j = k;
        if (i == k)
        {
            j = k - 30;
        }
_L5:
        return j;
_L4:
        j = k;
        if (i - k < l - i) goto _L5; else goto _L2
_L2:
        return l;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        if (accessibilityevent.getEventType() == 32)
        {
            accessibilityevent.getText().clear();
            Object obj = new Time();
            obj.hour = currentHoursOfDay;
            obj.minute = currentMinutes;
            long l = ((Time) (obj)).normalize(true);
            char c;
            if (is24HourMode)
            {
                c = '\201';
            } else
            {
                c = '\001';
            }
            obj = DateUtils.formatDateTime(getContext(), l, c);
            accessibilityevent.getText().add(obj);
            return true;
        } else
        {
            return super.dispatchPopulateAccessibilityEvent(accessibilityevent);
        }
    }

    public final int getCurrentItemShowing()
    {
        if (currentItemShowing != 0 && currentItemShowing != 1)
        {
            int i = currentItemShowing;
            Log.e("RadialPickerLayout", (new StringBuilder(57)).append("Current item showing was unfortunately set to ").append(i).toString());
            return -1;
        } else
        {
            return currentItemShowing;
        }
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfo(accessibilitynodeinfo);
        accessibilitynodeinfo.addAction(4096);
        accessibilitynodeinfo.addAction(8192);
    }

    public void onMeasure(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getSize(i);
        i = android.view.View.MeasureSpec.getMode(i);
        int l = android.view.View.MeasureSpec.getSize(j);
        j = android.view.View.MeasureSpec.getMode(j);
        k = Math.min(k, l);
        super.onMeasure(android.view.View.MeasureSpec.makeMeasureSpec(k, i), android.view.View.MeasureSpec.makeMeasureSpec(k, j));
    }

    public boolean onTouch(final View isInnerCircle, MotionEvent motionevent)
    {
        float f;
        float f1;
        int i;
        int k;
        boolean flag2;
        k = -1;
        i = 1;
        flag2 = true;
        f = motionevent.getX();
        f1 = motionevent.getY();
        isInnerCircle = new Boolean[1];
        isInnerCircle[0] = Boolean.valueOf(false);
        motionevent.getAction();
        JVM INSTR tableswitch 0 2: default 64
    //                   0 70
    //                   1 483
    //                   2 248;
           goto _L1 _L2 _L3 _L4
_L1:
        boolean flag = false;
_L6:
        return flag;
_L2:
        flag = flag2;
        if (!inputEnabled) goto _L6; else goto _L5
_L5:
        downX = f;
        downY = f1;
        lastValueSelected = -1;
        doingMove = false;
        doingTouch = true;
        if (!hideAmPm)
        {
            isTouchingAmOrPm = amPmCirclesView.getIsTouchingAmOrPm(f, f1);
        } else
        {
            isTouchingAmOrPm = -1;
        }
        if (isTouchingAmOrPm == 0 || isTouchingAmOrPm == 1)
        {
            hapticFeedbackController.tryVibrate();
            downDegrees = -1;
            handler.postDelayed(new _cls1(), TAP_TIMEOUT);
            return true;
        }
        downDegrees = getDegreesFromCoords(f, f1, accessibilityManager.isTouchExplorationEnabled(), isInnerCircle);
        flag = flag2;
        if (downDegrees == -1) goto _L6; else goto _L7
_L7:
        hapticFeedbackController.tryVibrate();
        handler.postDelayed(new _cls2(), TAP_TIMEOUT);
        return true;
_L4:
        if (!inputEnabled)
        {
            Log.e("RadialPickerLayout", "Input was disabled, but received ACTION_MOVE.");
            return true;
        }
        float f2 = Math.abs(f1 - downY);
        float f3 = Math.abs(f - downX);
        if (!doingMove && f3 <= (float)TOUCH_SLOP && f2 <= (float)TOUCH_SLOP)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (isTouchingAmOrPm == 0 || isTouchingAmOrPm == 1)
        {
            handler.removeCallbacksAndMessages(null);
            if (amPmCirclesView.getIsTouchingAmOrPm(f, f1) != isTouchingAmOrPm)
            {
                amPmCirclesView.setAmOrPmPressed(-1);
                amPmCirclesView.invalidate();
                isTouchingAmOrPm = -1;
            }
            continue; /* Loop/switch isn't completed */
        }
        if (downDegrees == -1)
        {
            continue; /* Loop/switch isn't completed */
        }
        doingMove = true;
        handler.removeCallbacksAndMessages(null);
        i = getDegreesFromCoords(f, f1, true, isInnerCircle);
        flag = flag2;
        if (i == -1) goto _L6; else goto _L8
_L8:
        i = reselectSelector(i, isInnerCircle[0].booleanValue(), false, true);
        flag = flag2;
        if (i == lastValueSelected) goto _L6; else goto _L9
_L9:
        hapticFeedbackController.tryVibrate();
        lastValueSelected = i;
        listener.onValueSelected(getCurrentItemShowing(), i, false);
        return true;
_L3:
        if (!inputEnabled)
        {
            listener.onValueSelected(3, 1, false);
            return true;
        }
        handler.removeCallbacksAndMessages(null);
        doingTouch = false;
        if (isTouchingAmOrPm != 0 && isTouchingAmOrPm != 1)
        {
            break; /* Loop/switch isn't completed */
        }
        k = amPmCirclesView.getIsTouchingAmOrPm(f, f1);
        amPmCirclesView.setAmOrPmPressed(-1);
        amPmCirclesView.invalidate();
        if (k == isTouchingAmOrPm)
        {
            amPmCirclesView.setAmOrPm(k);
            if (currentHoursOfDay < 12)
            {
                i = 0;
            } else
            if (currentHoursOfDay >= 24)
            {
                i = -1;
            }
            if (i != k)
            {
                listener.onValueSelected(2, isTouchingAmOrPm, false);
                setValueForItem(2, k);
            }
        }
        isTouchingAmOrPm = -1;
        if (true) goto _L1; else goto _L10
_L10:
        if (downDegrees != -1)
        {
            int j = getDegreesFromCoords(f, f1, doingMove, isInnerCircle);
            if (j != -1)
            {
                boolean flag3 = isInnerCircle[0].booleanValue();
                int l;
                boolean flag1;
                if (!doingMove)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                l = reselectSelector(j, flag3, flag1, false);
                j = l;
                if (getCurrentItemShowing() == 0)
                {
                    j = l;
                    if (!is24HourMode)
                    {
                        if (currentHoursOfDay < 12)
                        {
                            k = 0;
                        } else
                        if (currentHoursOfDay < 24)
                        {
                            k = 1;
                        }
                        if (k == 0 && l == 12)
                        {
                            j = 0;
                        } else
                        {
                            j = l;
                            if (k == 1)
                            {
                                j = l;
                                if (l != 12)
                                {
                                    j = l + 12;
                                }
                            }
                        }
                    }
                }
                setValueForItem(getCurrentItemShowing(), j);
                listener.onValueSelected(getCurrentItemShowing(), j, true);
            }
        }
        doingMove = false;
        return true;
    }

    public boolean performAccessibilityAction(int i, Bundle bundle)
    {
        int k = -1;
        boolean flag = false;
        if (super.performAccessibilityAction(i, bundle))
        {
            flag = true;
        } else
        {
            int j;
            if (i == 4096)
            {
                j = 1;
            } else
            if (i == 8192)
            {
                j = -1;
            } else
            {
                j = 0;
            }
            if (j != 0)
            {
                int l = getCurrentItemShowing();
                if (l == 0)
                {
                    i = currentHoursOfDay;
                } else
                {
                    i = k;
                    if (l == 1)
                    {
                        i = currentMinutes;
                    }
                }
                l = getCurrentItemShowing();
                if (l == 0)
                {
                    k = i % 12;
                    i = 30;
                } else
                if (l == 1)
                {
                    k = i;
                    i = 6;
                } else
                {
                    k = i;
                    i = 0;
                }
                k = snapOnly30s(k * i, j) / i;
                if (l == 0)
                {
                    if (is24HourMode)
                    {
                        j = 23;
                        i = 0;
                    } else
                    {
                        j = 12;
                        i = 1;
                    }
                } else
                {
                    j = 55;
                    i = 0;
                }
                if (k <= j)
                {
                    if (k < i)
                    {
                        i = j;
                    } else
                    {
                        i = k;
                    }
                }
                setItem(l, i);
                listener.onValueSelected(l, i, false);
                return true;
            }
        }
        return flag;
    }

    final int reselectSelector(int i, boolean flag, boolean flag1, boolean flag2)
    {
        byte byte0 = -1;
        if (i == -1)
        {
            return -1;
        }
        int k = getCurrentItemShowing();
        RadialSelectorView radialselectorview;
        int j;
        if (!flag1 && k == 1)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            if (snapPrefer30sMap == null)
            {
                i = byte0;
            } else
            {
                i = snapPrefer30sMap[i];
            }
        } else
        {
            i = snapOnly30s(i, 0);
        }
        if (k == 0)
        {
            radialselectorview = hourRadialSelectorView;
            j = 30;
        } else
        {
            radialselectorview = minuteRadialSelectorView;
            j = 6;
        }
        radialselectorview.setSelection(i, flag, flag2);
        radialselectorview.invalidate();
        if (k == 0)
        {
            if (is24HourMode)
            {
                if (i == 0 && flag)
                {
                    i = 360;
                } else
                if (i == 360 && !flag)
                {
                    i = 0;
                }
            } else
            if (i == 0)
            {
                i = 360;
            }
        } else
        if (i == 360 && k == 1)
        {
            i = 0;
        }
        j = i / j;
        if (k == 0 && is24HourMode && !flag && i != 0)
        {
            return j + 12;
        } else
        {
            return j;
        }
    }

    public void setAmOrPm(int i)
    {
        amPmCirclesView.setAmOrPm(i);
        amPmCirclesView.invalidate();
        setValueForItem(2, i);
    }

    final void setItem(int i, int j)
    {
        boolean flag = true;
        if (i == 0)
        {
            setValueForItem(0, j);
            RadialSelectorView radialselectorview = hourRadialSelectorView;
            if (!is24HourMode || j > 12 || j == 0)
            {
                flag = false;
            }
            radialselectorview.setSelection((j % 12) * 30, flag, false);
            hourRadialSelectorView.invalidate();
        } else
        if (i == 1)
        {
            setValueForItem(1, j);
            minuteRadialSelectorView.setSelection(j * 6, false, false);
            minuteRadialSelectorView.invalidate();
            return;
        }
    }

    final void setValueForItem(int i, int j)
    {
        if (i == 0)
        {
            currentHoursOfDay = j;
        } else
        {
            if (i == 1)
            {
                currentMinutes = j;
                return;
            }
            if (i == 2)
            {
                if (j == 0)
                {
                    currentHoursOfDay = currentHoursOfDay % 12;
                    return;
                }
                if (j == 1)
                {
                    currentHoursOfDay = currentHoursOfDay % 12 + 12;
                    return;
                }
            }
        }
    }

    public final boolean trySettingInputEnabled(boolean flag)
    {
        byte byte0 = 0;
        if (doingTouch && !flag)
        {
            return false;
        }
        inputEnabled = flag;
        View view = grayBox;
        if (flag)
        {
            byte0 = 4;
        }
        view.setVisibility(byte0);
        return true;
    }

    private class _cls1
        implements Runnable
    {

        private final RadialPickerLayout this$0;

        public final void run()
        {
            amPmCirclesView.setAmOrPmPressed(isTouchingAmOrPm);
            amPmCirclesView.invalidate();
        }

        _cls1()
        {
            this$0 = RadialPickerLayout.this;
            super();
        }
    }


    private class _cls2
        implements Runnable
    {

        private final RadialPickerLayout this$0;
        private final Boolean val$isInnerCircle[];

        public final void run()
        {
            doingMove = true;
            int i = reselectSelector(downDegrees, isInnerCircle[0].booleanValue(), false, true);
            lastValueSelected = i;
            listener.onValueSelected(getCurrentItemShowing(), i, false);
        }

        _cls2()
        {
            this$0 = RadialPickerLayout.this;
            isInnerCircle = aboolean;
            super();
        }
    }

}
