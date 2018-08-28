// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chipviewmodelfactory;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Trace;
import android.text.TextUtils;
import android.util.SparseIntArray;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.calendar.api.color.ColorUtils;
import com.google.android.calendar.timeline.chip.ChipViewModel;
import com.google.android.calendar.timeline.chip.image.AutoValue_BackgroundImageViewModel;
import com.google.android.calendar.timeline.chip.image.AutoValue_ImageViewModel;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineGroove;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemOperation;
import com.google.android.calendar.timely.TimelineItemUtil;
import com.google.android.calendar.utils.AccessibilityUtils;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;
import com.google.android.calendar.utils.rtl.RtlUtils;
import com.google.common.base.Absent;
import com.google.common.base.Present;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

// Referenced classes of package com.google.android.calendar.timeline.chipviewmodelfactory:
//            TimelineItemLoadingOperation, ChipFragmentInfo

public class ChipViewModelFactory
{
    public static interface SwipeConfigProvider
    {

        public abstract int getSupportedSwipeDirections(TimelineItem timelineitem);

        public abstract Integer getSwipeIcon(TimelineItem timelineitem, int i);
    }


    private static final TimelineItemOperation SINGLE_LINE_TITLE_GETTER = new TimelineItemDisplayProperties.SingleLineTitle();
    private static final TimelineItemOperation STRIKE_THROUGH_TYPE_GETTER = new TimelineItemDisplayProperties.StrikeThroughType();
    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timeline/chipviewmodelfactory/ChipViewModelFactory);
    private final TimelineItemOperation agendaDoubleChipSubtitleGetter;
    private final TimelineItemOperation agendaTripleChipSubtitleGetter;
    private final TimelineItemOperation backgroundImageOperation;
    private final int badgedIconSize;
    private final int badgedIconSizeMonth;
    private final TimelineItemOperation colorPaletteInitializer;
    public final Context context;
    private final int cornerRadius;
    private final int cornerRadiusMonth;
    private final TimelineItemOperation iconGetter;
    private final int iconSize;
    private final int iconSizeMonth;
    private final TimelineItemLoadingOperation itemLoader;
    private final int monthChipIconHorizontalCorrection;
    private final int monthTextLeftPaddingWithIcon;
    public final String multidayCountAccessibilityPlaceholder;
    private final String multidayCountPlaceholder;
    private final int paddingGridStart;
    private final int paddingGridVertical;
    private final int paddingMonthStart;
    private final int paddingScheduleAssistBottom;
    private final int paddingScheduleAssistTop;
    private final int paddingScheduleDoubleVertical;
    private final int paddingScheduleSingleVertical;
    private final int paddingScheduleStart;
    private final int paddingScheduleTripleBottom;
    private final int paddingScheduleTripleTop;
    private final int textLeftPaddingWithIcon;
    private final int textSize;
    private final int textSizeMonth;
    private final int textTimelineLeftPaddingWithIcon;

    public ChipViewModelFactory(Context context1)
    {
        context = context1;
        Resources resources = context1.getResources();
        backgroundImageOperation = new TimelineItemDisplayProperties.EventImageGetter(context1);
        itemLoader = new TimelineItemLoadingOperation(context1);
        colorPaletteInitializer = new TimelineItemDisplayProperties.ViewModelColorInitializer(context1);
        agendaDoubleChipSubtitleGetter = new TimelineItemDisplayProperties.AgendaDoubleSubtitle(resources);
        agendaTripleChipSubtitleGetter = new TimelineItemDisplayProperties.AgendaTripleSubtitle(resources);
        multidayCountPlaceholder = resources.getString(0x7f130481);
        multidayCountAccessibilityPlaceholder = resources.getString(0x7f130079);
        monthChipIconHorizontalCorrection = resources.getDimensionPixelOffset(0x7f0e029f);
        textLeftPaddingWithIcon = resources.getDimensionPixelOffset(0x7f0e00a5);
        textTimelineLeftPaddingWithIcon = resources.getDimensionPixelOffset(0x7f0e00a8);
        monthTextLeftPaddingWithIcon = resources.getDimensionPixelOffset(0x7f0e02a0);
        textSize = resources.getDimensionPixelSize(0x7f0e039f);
        textSizeMonth = resources.getDimensionPixelSize(0x7f0e03a0);
        iconSize = resources.getDimensionPixelSize(0x7f0e0098);
        iconSizeMonth = resources.getDimensionPixelSize(0x7f0e009a);
        badgedIconSize = resources.getDimensionPixelSize(0x7f0e0084);
        badgedIconSizeMonth = resources.getDimensionPixelSize(0x7f0e0085);
        paddingScheduleSingleVertical = resources.getDimensionPixelSize(0x7f0e03a8);
        paddingGridVertical = resources.getDimensionPixelSize(0x7f0e0095);
        paddingScheduleDoubleVertical = resources.getDimensionPixelSize(0x7f0e0119);
        paddingScheduleTripleTop = resources.getDimensionPixelSize(0x7f0e03d0);
        paddingScheduleTripleBottom = resources.getDimensionPixelSize(0x7f0e03cf);
        paddingScheduleAssistTop = resources.getDimensionPixelSize(0x7f0e0057);
        paddingScheduleAssistBottom = resources.getDimensionPixelSize(0x7f0e0056);
        paddingGridStart = resources.getDimensionPixelSize(0x7f0e0093);
        paddingScheduleStart = resources.getDimensionPixelSize(0x7f0e00a7);
        paddingMonthStart = resources.getDimensionPixelSize(0x7f0e029e);
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)featureconfig).google_material();
        cornerRadius = resources.getDimensionPixelSize(0x7f0e008b);
        featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)featureconfig).google_material();
            cornerRadiusMonth = resources.getDimensionPixelSize(0x7f0e008b);
            iconGetter = new TimelineItemDisplayProperties.IconGetter(context1);
            return;
        }
    }

    private static transient List makeTitle(String as[])
    {
        ArrayList arraylist = new ArrayList(as.length);
        for (int i = 0; i < as.length; i++)
        {
            if (!TextUtils.isEmpty(as[i]))
            {
                arraylist.add(as[i]);
            }
        }

        return arraylist;
    }

    public final ChipViewModel buildViewModel(TimelineItem timelineitem, ChipFragmentInfo chipfragmentinfo, int i)
    {
        Object obj;
        com.google.android.calendar.timeline.chip.ChipViewModel.Builder builder;
        ListenableFuture listenablefuture;
        int j;
        boolean flag2;
        Object obj1 = (new com.google.android.calendar.timeline.chip.AutoValue_ChipViewModel.Builder()).setStrikeThroughText(0).setIcon(0).setColorStyle(com.google.android.calendar.timeline.chip.ChipViewModel.ColorStyle.REGULAR).setPrimaryTextInsetForIcon(0).setIconHorizontalCorrection(0).setIconSize(0).setIconBadgeSize(0).setIconMode(com.google.android.calendar.timeline.chip.ChipViewModel.IconMode.REGULAR);
        if (true)
        {
            obj = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            obj = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
        }
        builder = ((com.google.android.calendar.timeline.chip.ChipViewModel.Builder) (obj1)).setBadgeRequestKey(((ListenableFuture) (obj))).setSupportedSwipeDirections(0).setOuterBorderColor(0).setDragHandles(Boolean.valueOf(false));
        flag2 = TimelineItemUtil.isOutOfOfficeEvent(timelineitem);
        listenablefuture = (ListenableFuture)timelineitem.perform(itemLoader, new Void[0]);
        timelineitem.perform(colorPaletteInitializer, new com.google.android.calendar.timeline.chip.ChipViewModel.Builder[] {
            builder
        });
        builder.setStrikeThroughText(((Integer)timelineitem.perform(STRIKE_THROUGH_TYPE_GETTER, new Void[0])).intValue());
        builder.setOptionalBackgroundImageViewModel(Absent.INSTANCE);
        if (!chipfragmentinfo.shouldShowImages) goto _L2; else goto _L1
_L1:
        if (ChipFragmentInfo.shouldForceFirstDay(i) || TimelineItemUtil.isFirstDay(timelineitem, i))
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (!j) goto _L2; else goto _L3
_L3:
        int l;
        boolean flag;
        boolean flag1;
        Trace.beginSection("setAndInitItem/setEventImage");
        obj = (ListenableFuture)timelineitem.perform(backgroundImageOperation, new Void[0]);
        if (obj != null)
        {
            obj1 = new AutoValue_ImageViewModel(obj, new com.google.android.calendar.event.image.EventImageResolvers..Lambda._cls0(((ListenableFuture) (obj))));
            if (TimelineItemUtil.hasAssist(timelineitem))
            {
                obj = com.google.android.calendar.timeline.chip.image.BackgroundImageViewModel.BottomLineStyle.WITHOUT_BOTTOM_LINE;
            } else
            {
                obj = com.google.android.calendar.timeline.chip.image.BackgroundImageViewModel.BottomLineStyle.WITH_BOTTOM_LINE;
            }
            obj = new AutoValue_BackgroundImageViewModel(((com.google.android.calendar.timeline.chip.image.ImageViewModel) (obj1)), ((com.google.android.calendar.timeline.chip.image.BackgroundImageViewModel.BottomLineStyle) (obj)));
            if (obj == null)
            {
                throw new NullPointerException();
            }
            builder.setOptionalBackgroundImageViewModel(new Present(obj));
            j = 1;
        } else
        {
            j = 0;
        }
        Trace.endSection();
        flag1 = j;
_L20:
        builder.setIcon(((Integer)timelineitem.perform(iconGetter, new Void[0])).intValue());
        if (chipfragmentinfo.viewType == 2)
        {
            j = iconSizeMonth;
        } else
        {
            j = iconSize;
        }
        if (chipfragmentinfo.viewType == 2)
        {
            l = badgedIconSizeMonth;
        } else
        {
            l = badgedIconSize;
        }
        builder.setIconSize(j);
        chipfragmentinfo.viewType;
        JVM INSTR tableswitch 0 2: default 420
    //                   0 933
    //                   1 420
    //                   2 942;
           goto _L4 _L5 _L4 _L6
_L4:
        j = textLeftPaddingWithIcon;
_L21:
        builder.setPrimaryTextInsetForIcon(j);
        if (chipfragmentinfo.viewType == 2)
        {
            j = monthChipIconHorizontalCorrection;
        } else
        {
            j = 0;
        }
        builder.setIconHorizontalCorrection(j);
        if (flag2)
        {
            obj = com.google.android.calendar.timeline.chip.ChipViewModel.IconMode.BADGED;
        } else
        {
            obj = com.google.android.calendar.timeline.chip.ChipViewModel.IconMode.REGULAR;
        }
        builder.setIconMode(((com.google.android.calendar.timeline.chip.ChipViewModel.IconMode) (obj)));
        builder.setIconBadgeSize(l);
        if (ChipFragmentInfo.shouldForceFirstDay(i) || TimelineItemUtil.isFirstDay(timelineitem, i))
        {
            j = 1;
        } else
        {
            j = 0;
        }
        flag2 = TimelineItemUtil.isLastDay(timelineitem, i);
        if (TimelineItemUtil.hasAssist(timelineitem) && !timelineitem.hasDeclinedStatus())
        {
            j = 4;
        } else
        if (TimelineItemUtil.isGroove(timelineitem))
        {
            obj = (TimelineGroove)timelineitem;
            if (((TimelineEvent) (obj)).hasImage() && !((TimelineEvent) (obj)).hasDeclinedStatus() && !((TimelineGroove) (obj)).completed)
            {
                j = 3;
            } else
            {
                j = 2;
            }
        } else
        if (TimelineItemUtil.isBirthday(timelineitem))
        {
            j = 2;
        } else
        if (TimelineItemUtil.isReminderBundle(timelineitem))
        {
            if (timelineitem.hasDeclinedStatus())
            {
                j = 1;
            } else
            {
                j = 2;
            }
        } else
        if (flag1)
        {
            j = 3;
        } else
        if (timelineitem.isAllDay() || TimelineItemUtil.isTimedMidnightToNextMidnight(timelineitem))
        {
            j = 1;
        } else
        if (timelineitem.getStartDay() == timelineitem.getEndDay())
        {
            j = 2;
        } else
        if (timelineitem.spansAtLeastOneDay())
        {
            if (j != 0 || flag2)
            {
                j = 2;
            } else
            {
                j = 1;
            }
        } else
        if (j != 0)
        {
            j = 2;
        } else
        {
            j = 0;
        }
        builder.setChipType(j);
        if (timelineitem.spansAtLeastOneDay())
        {
            flag = true;
        } else
        if (i > 0 && TimelineItemUtil.getMinutesOn(timelineitem, i) <= 30)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        chipfragmentinfo.viewType;
        JVM INSTR tableswitch 0 3: default 580
    //                   0 1189
    //                   1 1234
    //                   2 1251
    //                   3 1234;
           goto _L7 _L8 _L9 _L10 _L9
_L7:
        LogUtils.e(TAG, "Unknown view type '%d'", new Object[] {
            Integer.valueOf(chipfragmentinfo.viewType)
        });
        l = 1;
_L23:
        builder.setVerticalAlignType(l);
        flag2 = RtlUtils.isLayoutDirectionRtl(context);
        if (chipfragmentinfo.viewType == 2)
        {
            l = textSizeMonth;
        } else
        {
            l = textSize;
        }
        builder.setTextSize(l);
        if (chipfragmentinfo.viewType == 0) goto _L12; else goto _L11
_L11:
        if (chipfragmentinfo.viewType != 1 && chipfragmentinfo.viewType != 3 || flag) goto _L14; else goto _L13
_L13:
        l = 1;
_L25:
        builder.setEllipsizeType(l);
        builder.setIsRtl(flag2);
        chipfragmentinfo.viewType;
        JVM INSTR tableswitch -1 3: default 732
    //                   -1 1283
    //                   0 1291
    //                   1 1512
    //                   2 1552
    //                   3 1512;
           goto _L15 _L16 _L17 _L18 _L19 _L18
_L19:
        break MISSING_BLOCK_LABEL_1552;
_L15:
        LogUtils.e(TAG, "Unknown view type '%d'.", new Object[] {
            Integer.valueOf(chipfragmentinfo.viewType)
        });
        obj = Collections.emptyList();
_L26:
        if (chipfragmentinfo.showMultiDayAnnotations && TimelineItemUtil.shouldItemBeRenderedAsMultiday(timelineitem))
        {
            l = 1;
        } else
        {
            l = 0;
        }
        if (l != 0 && !((List) (obj)).isEmpty())
        {
            ((List) (obj)).set(0, String.format(multidayCountPlaceholder, new Object[] {
                ((List) (obj)).get(0), Integer.valueOf(TimelineItemUtil.getDayIndex(timelineitem, i)), Integer.valueOf(TimelineItemUtil.getNumIntersectingDays(timelineitem))
            }));
        }
        l = 0;
        while (l < ((List) (obj)).size()) 
        {
            String s1 = (String)((List) (obj)).get(l);
            if (!TextUtils.isEmpty(s1))
            {
                String s3 = s1;
                if (s1.length() > 299)
                {
                    s3 = s1.substring(0, 299);
                }
                s1 = s3.replace('\n', ' ');
            }
            ((List) (obj)).set(l, s1);
            l++;
        }
        break MISSING_BLOCK_LABEL_1629;
_L2:
        flag1 = false;
          goto _L20
_L5:
        j = textTimelineLeftPaddingWithIcon;
          goto _L21
_L6:
        j = monthTextLeftPaddingWithIcon;
          goto _L21
_L8:
        switch (j)
        {
        case 2: // '\002'
        default:
            l = 0;
            break;

        case 1: // '\001'
            l = 1;
            break;

        case 3: // '\003'
            l = 2;
            break;
        }
        if (true) goto _L23; else goto _L22
_L22:
_L9:
        if (flag)
        {
            l = 1;
        } else
        {
            l = 0;
        }
          goto _L23
_L10:
        l = 1;
          goto _L23
_L14:
        if (flag2) goto _L12; else goto _L24
_L24:
        l = 2;
          goto _L25
_L12:
        l = 0;
          goto _L25
_L16:
        obj = Collections.emptyList();
          goto _L26
_L17:
        switch (j)
        {
        default:
            LogUtils.e(TAG, "Unknown chip type: '%d'", new Object[] {
                Integer.valueOf(j)
            });
            obj = Collections.emptyList();
            break;

        case 1: // '\001'
            obj = makeTitle(new String[] {
                timelineitem.getTitle()
            });
            break;

        case 2: // '\002'
        case 4: // '\004'
            obj = makeTitle(new String[] {
                timelineitem.getTitle(), (String)timelineitem.perform(agendaDoubleChipSubtitleGetter, new Integer[] {
                    Integer.valueOf(i), Integer.valueOf(chipfragmentinfo.extraTimeFlags)
                })
            });
            break;

        case 3: // '\003'
            obj = makeTitle(new String[] {
                timelineitem.getTitle(), (String)timelineitem.perform(agendaTripleChipSubtitleGetter, new Integer[] {
                    Integer.valueOf(i), Integer.valueOf(chipfragmentinfo.extraTimeFlags)
                }), timelineitem.getLocation()
            });
            break;

        case 0: // '\0'
            obj = Collections.emptyList();
            break;
        }
        if (true) goto _L26; else goto _L27
_L27:
_L18:
        obj = makeTitle(new String[] {
            (String)timelineitem.perform(SINGLE_LINE_TITLE_GETTER, new Void[0]), timelineitem.getLocation()
        });
          goto _L26
        obj = makeTitle(new String[] {
            (String)timelineitem.perform(SINGLE_LINE_TITLE_GETTER, new Void[0])
        });
          goto _L26
        builder.setPrimaryText(((List) (obj)));
        class .Lambda._cls1
            implements Supplier
        {

            private final ChipViewModelFactory arg$1;
            private final TimelineItem arg$2;
            private final ChipFragmentInfo arg$3;
            private final int arg$4;

            public final Object get()
            {
                boolean flag4 = true;
                ChipViewModelFactory chipviewmodelfactory = arg$1;
                TimelineItem timelineitem1 = arg$2;
                ChipFragmentInfo chipfragmentinfo1 = arg$3;
                int i1 = arg$4;
                Object obj2 = timelineitem1.getTitle();
                String s4 = ((String) (obj2));
                if (obj2 != null)
                {
                    boolean flag3;
                    if (chipfragmentinfo1.showMultiDayAnnotations && TimelineItemUtil.shouldItemBeRenderedAsMultiday(timelineitem1))
                    {
                        flag3 = true;
                    } else
                    {
                        flag3 = false;
                    }
                    s4 = ((String) (obj2));
                    if (flag3)
                    {
                        s4 = String.format(chipviewmodelfactory.multidayCountAccessibilityPlaceholder, new Object[] {
                            obj2, Integer.valueOf(TimelineItemUtil.getDayIndex(timelineitem1, i1)), Integer.valueOf(TimelineItemUtil.getNumIntersectingDays(timelineitem1))
                        });
                    }
                }
                obj2 = chipviewmodelfactory.context;
                if (chipfragmentinfo1.viewType != 0)
                {
                    flag4 = false;
                }
                return TimelineItemUtil.createContentDescription(((Context) (obj2)), timelineitem1, flag4, chipfragmentinfo1.contentDescriptionPrefix, chipfragmentinfo1.contentDescriptionSuffix, s4);
            }

            .Lambda._cls1(TimelineItem timelineitem, ChipFragmentInfo chipfragmentinfo, int i)
            {
                arg$1 = ChipViewModelFactory.this;
                arg$2 = timelineitem;
                arg$3 = chipfragmentinfo;
                arg$4 = i;
            }
        }

        builder.setContentDescription(new .Lambda._cls1(timelineitem, chipfragmentinfo, i));
        if (chipfragmentinfo.viewType == 0 && j == 4 && !AccessibilityUtils.isAccessibilityEnabled(context))
        {
            String s2 = TimelineItemUtil.getAssistActionText(timelineitem, context);
            String s = s2;
            if (s2 != null)
            {
                s = s2.toUpperCase(Locale.getDefault());
            }
            builder.setSecondaryActionAction(s).setSecondaryActionInfo(TimelineItemUtil.getAssistInfoText(timelineitem, context));
        }
        l = chipfragmentinfo.viewType;
        l;
        JVM INSTR tableswitch 1 2: default 1756
    //                   1 2029
    //                   2 2021;
           goto _L28 _L29 _L30
_L29:
        break MISSING_BLOCK_LABEL_2029;
_L28:
        i = paddingScheduleStart;
_L31:
        builder.setForegroundPaddingStart(i).setForegroundPaddingEnd(0);
        float f = context.getResources().getConfiguration().fontScale;
        if (f <= 1.0F)
        {
            f = 1.0F;
        } else
        if ((double)f <= 1.2D)
        {
            f = 0.5F;
        } else
        {
            f = 0.3F;
        }
        if (l == 0)
        {
            if (j == 3 || j == 4)
            {
                if (j == 3)
                {
                    class .Lambda._cls0
                        implements Function
                    {

                        private final ChipViewModelFactory arg$1;

                        public final Object apply(Object obj2)
                        {
                            Object obj3 = arg$1;
                            obj2 = (TimelineItem)obj2;
                            obj3 = ((ChipViewModelFactory) (obj3)).context.getApplicationContext();
                            com.google.android.calendar.avatar.ContactInfo.Builder builder1 = ContactInfo.newBuilder();
                            builder1.sourceAccountName = ((TimelineItem) (obj2)).getSourceAccountName();
                            builder1.primaryEmail = ((TimelineItem) (obj2)).getOrganizer();
                            return new ContactPhotoRequestKey(((Context) (obj3)), new ContactInfo(builder1));
                        }

            .Lambda._cls0()
            {
                arg$1 = ChipViewModelFactory.this;
            }
                    }

                    float f1;
                    if (f < 1.0F)
                    {
                        f1 = 1.5F * f;
                    } else
                    {
                        f1 = 1.0F;
                    }
                    j = (int)(f1 * (float)paddingScheduleTripleTop);
                    i = (int)((float)paddingScheduleTripleBottom * f);
                } else
                {
                    j = paddingScheduleAssistTop;
                    i = (int)((float)paddingScheduleAssistBottom * f);
                }
            } else
            if (j == 2)
            {
                i = (int)((float)paddingScheduleDoubleVertical * f);
                j = i;
            } else
            {
                i = (int)((float)paddingScheduleSingleVertical * f);
                j = i;
            }
        } else
        if (l == 2 || flag)
        {
            i = 0;
            j = 0;
        } else
        {
            i = (int)((float)paddingGridVertical * f);
            j = i;
        }
        builder.setForegroundPaddingTop(j).setForegroundPaddingBottom(i);
        if (chipfragmentinfo.swipeConfigProvider == null)
        {
            builder.setSupportedSwipeDirections(0);
        } else
        {
            i = chipfragmentinfo.swipeConfigProvider.getSupportedSwipeDirections(timelineitem);
            Integer integer;
            Integer integer1;
            Integer integer2;
            Integer integer3;
            if ((i & 1) == 0)
            {
                integer = null;
            } else
            {
                integer = chipfragmentinfo.swipeConfigProvider.getSwipeIcon(timelineitem, 1);
            }
            if ((i & 2) == 0)
            {
                integer1 = null;
            } else
            {
                integer1 = chipfragmentinfo.swipeConfigProvider.getSwipeIcon(timelineitem, 2);
            }
            integer3 = (Integer)timelineitem.perform(new _cls1(), new Void[0]);
            if (integer3 == null)
            {
                integer2 = null;
            } else
            {
                int k = integer3.intValue();
                integer2 = Integer.valueOf(ColorUtils.lightColorVariants.get(k, 0xffeeeeee));
            }
            builder.setSupportedSwipeDirections(i).setPrimarySwipeIcon(integer).setSecondarySwipeIcon(integer1).setSwipeAccentColor(integer3).setRippleColor(integer2);
        }
        if (chipfragmentinfo.viewType == 0 && chipfragmentinfo.shouldShowImages && !flag1 && timelineitem.shouldShowOrganizerImage() && !timelineitem.hasDeclinedStatus() && (TimelineItemUtil.isBirthday(timelineitem) || !timelineitem.spansAtLeastOneDay()) && AndroidPermissionUtils.hasContactsPermissions(context))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            timelineitem = AbstractTransformFuture.create(listenablefuture, new .Lambda._cls0(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        } else
        if (true)
        {
            timelineitem = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            timelineitem = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
        }
        builder.setBadgeRequestKey(timelineitem);
        if (chipfragmentinfo.forceCompatibilityModeForStyledCorners)
        {
            builder.setStyledCornersCompatibilityMode(Integer.valueOf(chipfragmentinfo.forceCompatibilityBackgroundColorForStyledCorners));
        }
        if (chipfragmentinfo.viewType == 2)
        {
            i = cornerRadiusMonth;
        } else
        {
            i = cornerRadius;
        }
        builder.setCornerRadius(i);
        return builder.build();
_L30:
        i = paddingMonthStart;
          goto _L31
        i = paddingGridStart;
          goto _L31
    }


    private class _cls1 extends TimelineItemOperation
    {

        public final Object onAnyReminder(TimelineTaskType timelinetasktype, Object aobj[])
        {
            return Integer.valueOf(timelinetasktype.getColor());
        }

        _cls1()
        {
        }
    }

}
