// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.Display;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.calendarcommon2.LogUtils;
import com.android.datetimepicker.date.MonthView;
import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.preference.PreferenceUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.alternatecalendar.AlternateCalendarUtils;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.material.Material;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timely.geometry.PartitionItem;
import com.google.android.calendar.timely.settings.common.PreferencesConstants;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import com.google.android.calendar.utils.AccessibilityUtils;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

// Referenced classes of package com.google.android.calendar.timely:
//            CalendarMonthView

public class MonthViewFrame extends MonthView
{
    final class ChipMonthViewTouchHelper extends com.android.datetimepicker.date.MonthView.MonthViewTouchHelper
    {

        private Rect secondSharedRect;
        private Rect sharedRect;
        private final MonthViewFrame this$0;

        private final Chip asVisibleChip(View view)
        {
            if (view.getVisibility() == 0 && (view instanceof Chip))
            {
                view.getHitRect(sharedRect);
                if (!sharedRect.isEmpty())
                {
                    return (Chip)view;
                }
            }
            return null;
        }

        protected final CharSequence getDateDescription(int i)
        {
            StringBuilder stringbuilder = new StringBuilder(3);
            stringbuilder.append(super.getDateDescription(i));
            if (AlternateCalendarUtils.isAlternateCalendarEnabled(getContext()))
            {
                stringbuilder.append(", ");
                stringbuilder.append(AlternateCalendarUtils.getAlternateFullDate(Utils.getJulianDay(mYear, mMonth, i), getResources(), PreferencesConstants.getAlternateCalendarPref(getContext())));
            }
            return stringbuilder;
        }

        protected final int getVirtualViewAt(float f, float f1)
        {
            Object obj = getJulianDayFromLocation(f, f1);
            MonthViewFrame monthviewframe;
            int i;
            int j;
            int k;
            boolean flag;
            if (obj != null)
            {
                i = ((Integer) (obj)).intValue();
            } else
            {
                i = mFirstJulianDay;
            }
            obj = recycleTimeUtc;
            i = Integer.valueOf(i).intValue();
            j = mFirstJulianDay;
            monthviewframe = MonthViewFrame.this;
            k = monthviewframe.mFirstJulianDay;
            k = (monthviewframe.mNumCells + k) - 1;
            if (j <= k)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            Preconditions.checkArgument(flag, "min (%s) must be less than or equal to max (%s)", j, k);
            ((Time) (obj)).setJulianDaySafe(Math.min(Math.max(i, j), k));
            return recycleTimeUtc.monthDay;
        }

        protected final void getVisibleVirtualViews(List list)
        {
            if (owner != null) goto _L2; else goto _L1
_L1:
            return;
_L2:
            ArrayList arraylist;
            ArrayList arraylist1;
            Rect rect;
            Rect rect1;
            Rect rect2;
            int i;
            int l;
            int k1;
            int j = mFirstJulianDay;
            k1 = owner.getChildCount();
            arraylist1 = new ArrayList();
            arraylist = new ArrayList();
            for (i = 0; i < k1; i++)
            {
                Chip chip = asVisibleChip(owner.getChildAt(i));
                if (chip == null)
                {
                    continue;
                }
                if (chip.partitionInfo.getStartDay() >= mFirstJulianDay)
                {
                    break;
                }
                if (chip.partitionInfo.getEndDay() > j - 1)
                {
                    arraylist1.add(chip);
                }
            }

            rect = new Rect();
            rect1 = new Rect();
            rect2 = new Rect();
            l = 1;
_L9:
            int k;
            int l1;
            if (l > mNumCells)
            {
                continue; /* Loop/switch isn't completed */
            }
            list.add(Integer.valueOf(MonthViewFrame.idFromIndex(0, l, 0)));
            getItemBounds(l, rect);
            l1 = arraylist1.size();
            boolean flag = false;
            k = i;
            i = ((flag) ? 1 : 0);
            do
            {
                if (k >= k1)
                {
                    break;
                }
                Chip chip1 = asVisibleChip(owner.getChildAt(k));
                int i1 = i;
                if (chip1 != null)
                {
                    chip1.getHitRect(rect1);
                    if (!Rect.intersects(rect1, rect))
                    {
                        break;
                    }
                    do
                    {
                        if (i >= l1)
                        {
                            break;
                        }
                        Chip chip3 = (Chip)arraylist1.get(i);
                        chip3.getHitRect(rect2);
                        if (rect2.top > rect1.top)
                        {
                            break;
                        }
                        if (rect2.right > rect.right)
                        {
                            arraylist.add(chip3);
                        }
                        i1 = owner.indexOfChild(chip3);
                        if (i1 != -1)
                        {
                            i1 = MonthViewFrame.idFromIndex(1, i1, l);
                        } else
                        {
                            i1 = -1;
                        }
                        list.add(Integer.valueOf(i1));
                        i++;
                    } while (true);
                    if (rect1.right > rect.right)
                    {
                        arraylist.add(chip1);
                    }
                    i1 = owner.indexOfChild(chip1);
                    if (i1 != -1)
                    {
                        i1 = MonthViewFrame.idFromIndex(1, i1, l);
                    } else
                    {
                        i1 = -1;
                    }
                    list.add(Integer.valueOf(i1));
                    i1 = i;
                }
                k++;
                i = i1;
            } while (true);
              goto _L3
_L7:
            int j1 = -1;
_L5:
            Chip chip2;
            list.add(Integer.valueOf(j1));
            if (rect2.right > rect.right)
            {
                arraylist.add(chip2);
            }
            i++;
_L3:
label0:
            {
                if (i >= l1)
                {
                    break label0;
                }
                chip2 = (Chip)arraylist1.get(i);
                chip2.getHitRect(rect2);
                j1 = owner.indexOfChild(chip2);
                if (j1 == -1)
                {
                    break; /* Loop/switch isn't completed */
                }
                j1 = MonthViewFrame.idFromIndex(1, j1, l);
            }
            if (true) goto _L5; else goto _L4
_L4:
            if (true) goto _L7; else goto _L6
_L6:
            arraylist1.clear();
            if (numHiddenChips != null && numHiddenChips[(l - 1) + getFirstDayOffset()] > 0)
            {
                list.add(Integer.valueOf(MonthViewFrame.idFromIndex(2, l, 0)));
            }
            l++;
            i = k;
            ArrayList arraylist2 = arraylist;
            arraylist = arraylist1;
            arraylist1 = arraylist2;
            if (true) goto _L9; else goto _L8
_L8:
            if (true) goto _L1; else goto _L10
_L10:
        }

        protected final boolean onPerformActionForVirtualView(int i, int j, Bundle bundle)
        {
            return !super.onPerformActionForVirtualView(i, j, bundle) && j == 16;
        }

        protected final void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityevent)
        {
            Chip chip = maybeGetChipById(i);
            if (chip != null)
            {
                accessibilityevent.setContentDescription(chip.getContentDescription());
                return;
            } else
            {
                super.onPopulateEventForVirtualView(i, accessibilityevent);
                return;
            }
        }

        protected final void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            Object obj = maybeGetChipById(i);
            if (obj != null)
            {
                CharSequence charsequence = ((Chip) (obj)).getContentDescription();
                accessibilitynodeinfocompat.mInfo.setContentDescription(charsequence);
                ((Chip) (obj)).getHitRect(sharedRect);
                sharedRect.inset(-owner.chipHorizontalMargin, -owner.chipVerticalMargin);
                i = MonthViewFrame.auxIndexFromId(i);
                if (i > 0)
                {
                    getItemBounds(i, secondSharedRect);
                    sharedRect.intersect(secondSharedRect);
                }
                obj = sharedRect;
                accessibilitynodeinfocompat.mInfo.setBoundsInParent(((Rect) (obj)));
                accessibilitynodeinfocompat.mInfo.addAction(16);
                return;
            }
            if (MonthViewFrame.maybeIndexFromId(2, i) != -1)
            {
                i = MonthViewFrame.maybeIndexFromId(2, i);
                super.onPopulateNodeForVirtualView(MonthViewFrame.idFromIndex(0, i, 0), accessibilitynodeinfocompat);
                Object obj1 = sharedRect;
                accessibilitynodeinfocompat.mInfo.getBoundsInParent(((Rect) (obj1)));
                sharedRect.top = sharedRect.bottom - owner.eventChipHeight;
                obj1 = sharedRect;
                accessibilitynodeinfocompat.mInfo.setBoundsInParent(((Rect) (obj1)));
                i = numHiddenChips[(i - 1) + getFirstDayOffset()];
                obj1 = resources.getQuantityString(0x7f120025, i, new Object[] {
                    Integer.valueOf(i)
                });
                accessibilitynodeinfocompat.mInfo.setContentDescription(((CharSequence) (obj1)));
                return;
            } else
            {
                super.onPopulateNodeForVirtualView(i, accessibilitynodeinfocompat);
                Rect rect = sharedRect;
                accessibilitynodeinfocompat.mInfo.getBoundsInParent(rect);
                sharedRect.inset(-owner.chipHorizontalMargin, -owner.chipVerticalMargin);
                rect = sharedRect;
                accessibilitynodeinfocompat.mInfo.setBoundsInParent(rect);
                return;
            }
        }

        public ChipMonthViewTouchHelper(View view)
        {
            this$0 = MonthViewFrame.this;
            super(MonthViewFrame.this, view);
            sharedRect = new Rect();
            secondSharedRect = new Rect();
        }
    }


    private static final Typeface ROBOTO_REGULAR = Typeface.create("sans-serif", 0);
    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/MonthViewFrame);
    private int alternateCalendarPreference;
    public final SparseArray alternateDateStrings;
    private final int chipVerticalMargin;
    private final Date date;
    private int dayDarkTextColor;
    private final int dayHeaderHeight;
    private int dayLightTextColor;
    private int dayNumberBottomPosition;
    private final int dayNumberStartMargin;
    private boolean displayOverlfowDots;
    private final int dotDistance;
    private final float dotRadius;
    private final int dotTopOffset;
    private final int eventChipHeight;
    private final Paint highlightPaint;
    private final boolean isTabletConfig;
    private final int lineColor;
    public int maxNumChipsPerDay;
    private final int monthHeaderBottomPosition;
    private final int monthHeaderHeight;
    private final Paint neighborsMonthsPaint;
    public int numHiddenChips[];
    private int overflowOffsetY;
    public CalendarMonthView owner;
    private int prevMonthNumsDays;
    public final Time recycleTimeUtc;
    public final Resources resources;
    private final Typeface robotoMedium;
    private final int todayCircleRadius;
    public int todayJulianDay;
    private final int todayWeekdayLabelColor;
    private int xMoreLabelOffsetY;
    private final Paint xMoreLabelPaint;

    public MonthViewFrame(Context context, AttributeSet attributeset)
    {
        CalendarListEntry acalendarlistentry[];
        com.google.android.calendar.api.calendarlist.CalendarDescriptor calendardescriptor;
        int i;
        int j;
        int k;
        super(context, attributeset);
        date = new Date();
        recycleTimeUtc = new Time("UTC");
        displayOverlfowDots = true;
        alternateCalendarPreference = -1;
        highlightPaint = new Paint();
        isTabletConfig = context.getResources().getBoolean(0x7f0c0016);
        resources = context.getResources();
        j = ContextCompat.getColor(context, 0x7f0d004d);
        attributeset = CalendarListEntryCache.instance;
        if (attributeset == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        acalendarlistentry = (CalendarListEntry[])((ListenableFutureCache)attributeset).result;
        if (acalendarlistentry == null)
        {
            break MISSING_BLOCK_LABEL_851;
        }
        calendardescriptor = CalendarProperties.getDefaultCalendarId();
        k = acalendarlistentry.length;
        i = 0;
_L3:
        if (i >= k)
        {
            break MISSING_BLOCK_LABEL_851;
        }
        attributeset = acalendarlistentry[i];
        com.google.android.calendar.api.calendarlist.CalendarDescriptor calendardescriptor1 = attributeset.getDescriptor();
        boolean flag;
        if (calendardescriptor1 == calendardescriptor || calendardescriptor1 != null && calendardescriptor1.equals(calendardescriptor))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        if (attributeset != null)
        {
            i = attributeset.getColor().getValue();
        } else
        {
            i = j;
        }
        highlightPaint.setColor(i);
        highlightPaint.setStrokeWidth(10F);
        highlightPaint.setStyle(android.graphics.Paint.Style.FILL);
        context = context.obtainStyledAttributes(new int[] {
            0x101030e
        });
        attributeset = context.getDrawable(0);
        if (super.mSelector != null)
        {
            super.mSelector.setCallback(null);
            unscheduleDrawable(super.mSelector);
        }
        if (attributeset != null)
        {
            setClickable(true);
            setFocusable(true);
            super.mSelector = attributeset;
            attributeset.setCallback(this);
            super.updateSelectorState();
            boolean flag1;
            if (attributeset instanceof RippleDrawable)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            super.mSelectorHasRipples = flag1;
        } else
        {
            super.mSelector = null;
            setClickable(false);
            setFocusable(false);
            super.mSelectorHasRipples = false;
        }
        context.recycle();
        dotRadius = (float)resources.getDimensionPixelSize(0x7f0e02ab) / 2.0F;
        dotDistance = resources.getDimensionPixelSize(0x7f0e02ac);
        dotTopOffset = resources.getDimensionPixelSize(0x7f0e02ad);
        mDayTextColor = resources.getColor(0x7f0d0152);
        dayLightTextColor = resources.getColor(0x7f0d0156);
        dayDarkTextColor = resources.getColor(0x7f0d0153);
        monthHeaderHeight = resources.getDimensionPixelSize(0x7f0e02af);
        monthHeaderBottomPosition = resources.getDimensionPixelSize(0x7f0e02ae);
        dayNumberStartMargin = resources.getDimensionPixelOffset(0x7f0e02a9);
        mWeekNumsColWidth = resources.getDimensionPixelSize(0x7f0e02b2);
        mTodayNumberColor = -1;
        todayWeekdayLabelColor = resources.getColor(0x7f0d01d7);
        mWeekNumPaint.setTypeface(ROBOTO_REGULAR);
        mWeekNumPaint.setColor(resources.getColor(0x7f0d032d));
        mWeekNumPaint.setTextSize(resources.getDimensionPixelSize(0x7f0e03e7));
        mMonthNumPaint.setTextAlign(android.graphics.Paint.Align.LEFT);
        mMonthNumPaint.setTextSize(resources.getDimensionPixelSize(0x7f0e02aa));
        mMonthDayLabelPaint.setTextAlign(android.graphics.Paint.Align.LEFT);
        mMonthDayLabelPaint.setTypeface(ROBOTO_REGULAR);
        mMonthDayLabelPaint.setColor(mDayTextColor);
        mMonthDayLabelPaint.setTextSize(resources.getDimensionPixelSize(0x7f0e02a8));
        mSelectedCirclePaint.setStyle(android.graphics.Paint.Style.FILL);
        mSelectedCirclePaint.setColor(todayWeekdayLabelColor);
        todayCircleRadius = resources.getDimensionPixelSize(0x7f0e02b1);
        neighborsMonthsPaint = new Paint();
        neighborsMonthsPaint.setColor(resources.getColor(0x7f0d0155));
        neighborsMonthsPaint.setStyle(android.graphics.Paint.Style.FILL);
        xMoreLabelPaint = new Paint();
        xMoreLabelPaint.setAntiAlias(true);
        xMoreLabelPaint.setStyle(android.graphics.Paint.Style.FILL);
        xMoreLabelPaint.setColor(resources.getColor(0x7f0d0154));
        xMoreLabelPaint.setTextSize(resources.getDimensionPixelSize(0x7f0e0438));
        xMoreLabelPaint.setTypeface(ROBOTO_REGULAR);
        alternateDateStrings = new SparseArray(42);
        lineColor = resources.getColor(0x7f0d011a);
        eventChipHeight = resources.getDimensionPixelSize(0x7f0e02a5);
        dayHeaderHeight = eventChipHeight;
        chipVerticalMargin = resources.getDimensionPixelSize(0x7f0e02a7);
        mEdgePadding = resources.getDimensionPixelOffset(0x7f0e02b0);
        if (Material.robotoMedium != null)
        {
            context = Material.robotoMedium;
        } else
        {
            context = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = context;
        }
        robotoMedium = context;
        return;
_L2:
        i++;
          goto _L3
        attributeset = null;
          goto _L1
    }

    static int auxIndexFromId(int i)
    {
        return i >> 16 & 0x1f;
    }

    private final void computeRowHeight(int i)
    {
        mRowHeight = (i - getMonthHeaderSize()) / mNumRows;
        maxNumChipsPerDay = (mRowHeight - dayHeaderHeight) / (eventChipHeight + chipVerticalMargin);
        i = mRowHeight;
        int j = maxNumChipsPerDay;
        int k = eventChipHeight;
        int l = maxNumChipsPerDay;
        int i1 = chipVerticalMargin;
        float f = mMonthNumPaint.getTextSize();
        float f1 = mMonthNumPaint.descent();
        dayNumberBottomPosition = (int)((float)((i - j * k - (l - 1) * i1) / 2) + (f - f1) / 2.0F);
        f = xMoreLabelPaint.getTextSize();
        f1 = mMonthNumPaint.descent();
        float f2 = mRowHeight - eventChipHeight / 2;
        xMoreLabelOffsetY = (int)((f - f1) / 2.0F + f2);
        overflowOffsetY = (mRowHeight - eventChipHeight) + dotTopOffset;
    }

    private final void drawMonthDay$51662RJ4E9NMIP1FCTP62S38D5HN6BQ3C5N7COBJ7D4KIIA9954KIIA994KLC___0(Canvas canvas, int i, int j, int k, int l, int i1)
    {
        Object obj = String.format("%d", new Object[] {
            Integer.valueOf(i)
        });
        float f = 0.0F;
        if (super.rtlEnabled && super.isRtl)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            i = k - dayNumberStartMargin;
        } else
        {
            i = dayNumberStartMargin + j;
        }
        setPaintForDay(mMonthNumPaint, i1, false);
        if (i1 == todayJulianDay)
        {
            f = mMonthNumPaint.measureText(((String) (obj)));
            float f2 = mMonthNumPaint.getTextSize();
            float f5 = mMonthNumPaint.descent();
            Object obj1;
            Paint paint;
            boolean flag;
            if (super.rtlEnabled && super.isRtl)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                f = (float)i - f / 2.0F;
            } else
            {
                f = (float)i + f / 2.0F;
            }
            canvas.drawCircle(f, (float)(dayNumberBottomPosition + l) - (f2 - f5) / 2.0F, todayCircleRadius, mSelectedCirclePaint);
        }
        canvas.drawText(((String) (obj)), i, dayNumberBottomPosition + l, mMonthNumPaint);
        if (alternateCalendarPreference != 0)
        {
            float f3 = mMonthNumPaint.measureText(((String) (obj)));
            if (i1 == todayJulianDay)
            {
                if (super.rtlEnabled && super.isRtl)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i != 0)
                {
                    f -= todayCircleRadius;
                } else
                {
                    f = (float)todayCircleRadius + f;
                }
            } else
            {
                f = mMonthNumPaint.measureText(" ");
                boolean flag1;
                if (super.rtlEnabled && super.isRtl)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1)
                {
                    f = (float)i - f3 - f;
                } else
                {
                    f = (float)i + f3 + f;
                }
            }
            if (super.rtlEnabled && super.isRtl)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                f3 = f - (float)j;
            } else
            {
                f3 = (float)k - f;
            }
            setPaintForDay(mMonthNumPaint, i1, true);
            paint = mMonthNumPaint;
            obj1 = (String)alternateDateStrings.get(i1);
            obj = obj1;
            if (obj1 == null)
            {
                obj = AlternateCalendarUtils.getAlternateDayOfMonthString(i1, resources, alternateCalendarPreference);
                f5 = paint.measureText("()");
                obj = TextUtils.ellipsize(((CharSequence) (obj)), new TextPaint(paint), f3 - f5, android.text.TextUtils.TruncateAt.END);
                obj = (new StringBuilder("(")).append(((CharSequence) (obj))).append(")").toString();
                alternateDateStrings.put(i1, obj);
            }
            canvas.drawText(((String) (obj)), f, dayNumberBottomPosition + l, mMonthNumPaint);
        }
        if (displayOverlfowDots)
        {
            i = super.findDayOffset();
            if (numHiddenChips[(i1 - mFirstJulianDay) + i] > 0)
            {
                if (isTabletConfig)
                {
                    obj1 = xMoreLabelPaint;
                    Typeface typeface;
                    if (todayJulianDay <= i1)
                    {
                        typeface = robotoMedium;
                    } else
                    {
                        typeface = ROBOTO_REGULAR;
                    }
                    ((Paint) (obj1)).setTypeface(typeface);
                    canvas.drawText(resources.getQuantityString(0x7f120025, numHiddenChips[(i1 - mFirstJulianDay) + i], new Object[] {
                        Integer.valueOf(numHiddenChips[i + (i1 - mFirstJulianDay)])
                    }), dayNumberStartMargin + j, xMoreLabelOffsetY + l, xMoreLabelPaint);
                } else
                {
                    float f1 = dayNumberStartMargin + j;
                    float f6 = dotRadius;
                    float f4 = overflowOffsetY + l;
                    f1 += f6;
                    i = 0;
                    while (i < 3) 
                    {
                        canvas.drawCircle(f1, f4, dotRadius, xMoreLabelPaint);
                        f1 += dotRadius * 2.0F + (float)dotDistance;
                        i++;
                    }
                }
            }
        }
    }

    static int idFromIndex(int i, int j, int k)
    {
        boolean flag1 = true;
        boolean flag;
        if (j > 0 && j < 0x10000)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        }
        if (k >= 0 && k < 32)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        } else
        {
            return (i << 5 | k) << 16 | j;
        }
    }

    static int maybeIndexFromId(int i, int j)
    {
        if (j >> 21 != i)
        {
            return -1;
        } else
        {
            return 0xffff & j;
        }
    }

    private final void setPaintForDay(Paint paint, int i, boolean flag)
    {
        if (i == todayJulianDay)
        {
            if (flag)
            {
                paint.setTypeface(ROBOTO_REGULAR);
                paint.setColor(todayWeekdayLabelColor);
                return;
            } else
            {
                paint.setTypeface(robotoMedium);
                paint.setColor(mTodayNumberColor);
                return;
            }
        }
        if (isTabletConfig)
        {
            if (todayJulianDay > i)
            {
                i = dayLightTextColor;
            } else
            {
                i = dayDarkTextColor;
            }
            paint.setColor(i);
        } else
        {
            if (i < mFirstJulianDay || i > (mFirstJulianDay + mNumCells) - 1)
            {
                i = dayLightTextColor;
            } else
            {
                i = dayDarkTextColor;
            }
            paint.setColor(i);
        }
        paint.setTypeface(ROBOTO_REGULAR);
    }

    protected final boolean dispatchClickOnDay(float f, float f1, com.android.datetimepicker.date.MonthView.OnDayClickDispatcher ondayclickdispatcher)
    {
        if (AccessibilityUtils.isAccessibilityEnabled(getContext()))
        {
            int i = ((ExploreByTouchHelper) (mTouchHelper)).mAccessibilityFocusedVirtualViewId;
            if (i != 0x80000000)
            {
                Chip chip = maybeGetChipById(i);
                if (chip != null && owner != null)
                {
                    owner.onChipPrimaryAction(chip);
                    return true;
                }
            }
        }
        return super.dispatchClickOnDay(f, f1, ondayclickdispatcher);
    }

    public final void drawMonthDay(Canvas canvas, int i, int j, int k, int l, int i1, int j1, 
            int k1, int l1, int i2)
    {
        drawMonthDay$51662RJ4E9NMIP1FCTP62S38D5HN6BQ3C5N7COBJ7D4KIIA9954KIIA994KLC___0(canvas, k, j1, k1, l1, (mFirstJulianDay + k) - 1);
    }

    protected final void drawMonthDayLabels(Canvas canvas)
    {
        boolean flag = false;
        float f1 = (mWidth - getEdgePadding()) / 7;
        int i = -1;
        if (mHasToday)
        {
            i = ((todayJulianDay - mFirstJulianDay) + findDayOffset()) % mNumDays;
        }
        float f;
        int j;
        if (super.rtlEnabled && super.isRtl)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            f = ((float)getInternalStartPadding() + f1) - (float)dayNumberStartMargin;
        } else
        {
            f = dayNumberStartMargin + getInternalStartPadding();
        }
        j = ((flag) ? 1 : 0);
        while (j < 7) 
        {
            int i1 = getRtlCompatibleColumnIndex(j);
            if (i == i1)
            {
                mMonthDayLabelPaint.setTypeface(robotoMedium);
                mMonthDayLabelPaint.setColor(todayWeekdayLabelColor);
            }
            int l = (mWeekStart + i1) % mNumDays;
            int k = l;
            if (l == 0)
            {
                k = 7;
            }
            mDayLabelCalendar.set(7, k);
            String s;
            if (isTabletConfig)
            {
                s = mDayLabelCalendar.getDisplayName(7, 1, Locale.getDefault());
            } else
            {
                date.setTime(mDayLabelCalendar.getTimeInMillis());
                s = Utils.getShortDayOfWeek(date);
            }
            canvas.drawText(s, f, monthHeaderBottomPosition, mMonthDayLabelPaint);
            if (i == i1)
            {
                mMonthDayLabelPaint.setTypeface(ROBOTO_REGULAR);
                mMonthDayLabelPaint.setColor(mDayTextColor);
            }
            f += f1;
            j++;
        }
    }

    protected final void drawMonthTitle(Canvas canvas)
    {
    }

    protected final void drawWeekNum$51662RJ4E9NMIP1FCTP62S38D5HN6BQ3C5N7COBJ7D4KIIA9954IILG_0(Canvas canvas, int i, int j, int k, int l, int i1)
    {
        j = (j + l) / 2;
        l = dayNumberBottomPosition;
        canvas.drawText(String.format("%d", new Object[] {
            Integer.valueOf(i)
        }), j, l + k, mWeekNumPaint);
    }

    public final Rect getCellPosition(int i, int j, int k)
    {
        i = (i - mFirstJulianDay) + findDayOffset();
        float f = (float)(mWidth - getEdgePadding()) / (float)mNumDays;
        int l = getRtlCompatibleColumnIndex(i % mNumDays);
        float f1 = getInternalStartPadding() + k;
        f1 = (float)l * f + f1;
        k = getMonthHeaderSize();
        i = (i / mNumDays) * mRowHeight + (k + j);
        j = mRowHeight;
        return new Rect((int)f1, i, (int)(f + f1), j + i);
    }

    public final com.android.datetimepicker.date.MonthAdapter.CalendarDay getDayFromLocation(float f, float f1)
    {
        Integer integer = getJulianDayFromLocation(f, f1);
        if (integer != null)
        {
            recycleTimeUtc.setJulianDaySafe(integer.intValue());
            return new com.android.datetimepicker.date.MonthAdapter.CalendarDay(recycleTimeUtc.year, recycleTimeUtc.month, recycleTimeUtc.monthDay);
        } else
        {
            return null;
        }
    }

    public final int getFirstDayOffset()
    {
        return super.findDayOffset();
    }

    final Integer getJulianDayFromLocation(float f, float f1)
    {
        Integer integer = getInternalDayFromLocation(f, f1);
        if (integer != null)
        {
            int i = mFirstJulianDay;
            i = (integer.intValue() + i) - 1;
            if (i <= ((mFirstJulianDay - findDayOffset()) + mNumRows * 7) - 1)
            {
                return Integer.valueOf(i);
            }
        }
        return null;
    }

    protected final int getMonthHeaderSize()
    {
        return monthHeaderHeight;
    }

    protected final com.android.datetimepicker.date.MonthView.MonthViewTouchHelper getMonthViewTouchHelper()
    {
        return new ChipMonthViewTouchHelper(this);
    }

    final Chip maybeGetChipById(int i)
    {
        if (i >> 21 != 1)
        {
            i = -1;
        } else
        {
            i = 0xffff & i;
        }
        if (i >= 0)
        {
            View view = owner.getChildAt(i);
            if (view instanceof Chip)
            {
                return (Chip)view;
            }
            LogUtils.e(TAG, "Expected a TimelyChip, found %s", new Object[] {
                view
            });
        }
        return null;
    }

    protected void onDraw(Canvas canvas)
    {
        int j1 = ((mRowHeight + MINI_DAY_NUMBER_TEXT_SIZE) / 2 - 1) + getMonthHeaderSize();
        float f = (float)(mWidth - getEdgePadding()) / (float)(mNumDays << 1);
        int l1 = (mRowHeight + MINI_DAY_NUMBER_TEXT_SIZE) / 2 - 1;
        int i1 = findDayOffset();
        if (i1 > 0)
        {
            int i = i1 - 1;
            if (isTabletConfig)
            {
                float f1 = getMonthHeaderSize();
                float f5 = getInternalStartPadding();
                canvas.drawRect(0.0F, f1, (float)((i + 1) * 2) * f + f5, getMonthHeaderSize() + mRowHeight, neighborsMonthsPaint);
            }
            int k = prevMonthNumsDays;
            for (; i >= 0; i--)
            {
                float f2 = (float)(getRtlCompatibleColumnIndex(i) * 2 + 1) * f + (float)getInternalStartPadding();
                drawMonthDay$51662RJ4E9NMIP1FCTP62S38D5HN6BQ3C5N7COBJ7D4KIIA9954KIIA994KLC___0(canvas, k, (int)(f2 - f), (int)(f2 + f), j1 - l1, mFirstJulianDay - (i1 - i));
                k--;
            }

        }
        i1 = (findDayOffset() + mNumCells) % mNumDays;
        int l = calculateNumRows();
        int j;
        if (i1 == 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        l = (l - 1) + j;
        if (i1 != 0 || l < mNumRows)
        {
            j = j1 + mRowHeight * l;
            if (isTabletConfig)
            {
                float f3 = i1 * 2;
                canvas.drawRect((float)getInternalStartPadding() + f3 * f, j - l1, mWidth, (j - l1) + mRowHeight, neighborsMonthsPaint);
            }
            int i2 = mFirstJulianDay;
            int j2 = mNumCells;
            j1 = 1;
            while (l < mNumRows) 
            {
                float f4 = (float)(getRtlCompatibleColumnIndex(i1) * 2 + 1) * f + (float)getInternalStartPadding();
                drawMonthDay$51662RJ4E9NMIP1FCTP62S38D5HN6BQ3C5N7COBJ7D4KIIA9954KIIA994KLC___0(canvas, j1, (int)(f4 - f), (int)(f4 + f), j - l1, ((i2 + j2) - 1) + j1);
                int k1 = i1 + 1;
                if (k1 == mNumDays)
                {
                    i1 = 0;
                    k1 = l + 1;
                    j = mRowHeight + j;
                    l = i1;
                    i1 = k1;
                } else
                {
                    i1 = l;
                    l = k1;
                }
                j1++;
                k1 = l;
                l = i1;
                i1 = k1;
            }
        }
        mMonthNumPaint.setColor(lineColor);
        l = getMonthHeaderSize() + mRowHeight;
        for (j = 1; j < mNumRows; j++)
        {
            canvas.drawLine(0.0F, l, canvas.getWidth(), l, mMonthNumPaint);
            l = mRowHeight + l;
        }

        super.onDraw(canvas);
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        computeRowHeight(j);
        Display display = getDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        display.getMetrics(displaymetrics);
        boolean flag;
        if ((float)j > displaymetrics.density * 250F)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        displayOverlfowDots = flag;
    }

    public final void setMonthParams(HashMap hashmap)
    {
        int i;
        int j;
        if (CalendarProperties.getBooleanProperty(7).booleanValue())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        hashmap.put("show_wk_num", Integer.valueOf(i));
        hashmap.put("week_start", Integer.valueOf(PreferenceUtils.getFirstDayOfWeekAsCalendar(getContext())));
        super.setMonthParams(hashmap);
        if (!isTabletConfig)
        {
            mNumRows = 6;
        }
        if (mMonth == 0)
        {
            i = mYear - 1;
        } else
        {
            i = mYear;
        }
        if (mMonth == 0)
        {
            j = 11;
        } else
        {
            j = mMonth - 1;
        }
        prevMonthNumsDays = com.android.datetimepicker.Utils.getDaysInMonth(j, i);
        mFirstJulianDay = Utils.getJulianFirstDayFromMonth(mMonth, mYear);
        numHiddenChips = new int[mNumRows * mNumDays];
        todayJulianDay = Utils.getTodayJulianDay(getContext());
        computeRowHeight(getHeight());
        alternateDateStrings.clear();
        alternateCalendarPreference = PreferencesConstants.getAlternateCalendarPref(getContext());
    }

    public final void setRtlEnabled(boolean flag)
    {
        super.setRtlEnabled(flag);
        Paint paint = mMonthNumPaint;
        android.graphics.Paint.Align align;
        boolean flag1;
        if (super.rtlEnabled && super.isRtl)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            align = android.graphics.Paint.Align.RIGHT;
        } else
        {
            align = android.graphics.Paint.Align.LEFT;
        }
        paint.setTextAlign(align);
        paint = mMonthDayLabelPaint;
        if (super.rtlEnabled && super.isRtl)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            align = android.graphics.Paint.Align.RIGHT;
        } else
        {
            align = android.graphics.Paint.Align.LEFT;
        }
        paint.setTextAlign(align);
    }

}
