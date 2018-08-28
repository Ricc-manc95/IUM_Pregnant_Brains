// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.color;

import android.content.res.Resources;
import com.google.common.base.Platform;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

// Referenced classes of package com.google.android.calendar.api.color:
//            ColorFactory, AutoValue_EventColor, EventColor, AutoValue_NamedCalendarColor, 
//            CalendarColor, AutoValue_CustomCalendarColor, NamedCalendarColor, CustomCalendarColor

public final class ColorFactoryImpl
    implements ColorFactory
{

    private static final ImmutableMap LEGACY_COLOR_MAPPING = (new com.google.common.collect.ImmutableMap.Builder()).put(Integer.valueOf(0xfff83a22), Integer.valueOf(0xffd50000)).put(Integer.valueOf(0xfffa573c), Integer.valueOf(0xfff4511e)).put(Integer.valueOf(-35529), Integer.valueOf(0xffef6c00)).put(Integer.valueOf(-21178), Integer.valueOf(0xfff09300)).put(Integer.valueOf(0xfffad165), Integer.valueOf(0xfff6bf26)).put(Integer.valueOf(0xfffbe983), Integer.valueOf(0xffe4c441)).put(Integer.valueOf(0xffb3dc6c), Integer.valueOf(0xffc0ca33)).put(Integer.valueOf(0xff7bd148), Integer.valueOf(0xff7cb342)).put(Integer.valueOf(0xff16a765), Integer.valueOf(0xff0b8043)).put(Integer.valueOf(0xff42d692), Integer.valueOf(0xff009688)).put(Integer.valueOf(0xff92e1c0), Integer.valueOf(0xff33b679)).put(Integer.valueOf(0xff9fe1e7), Integer.valueOf(0xff039be5)).put(Integer.valueOf(0xff9fc6e7), Integer.valueOf(0xff4285f4)).put(Integer.valueOf(0xff4986e7), Integer.valueOf(0xff3f51b5)).put(Integer.valueOf(0xff9a9cff), Integer.valueOf(0xff7986cb)).put(Integer.valueOf(0xffb99aff), Integer.valueOf(0xffb39ddb)).put(Integer.valueOf(0xffa47ae2), Integer.valueOf(0xff9e69af)).put(Integer.valueOf(0xffcd74e6), Integer.valueOf(0xff8e24aa)).put(Integer.valueOf(0xffcca6ac), Integer.valueOf(0xffad1457)).put(Integer.valueOf(0xfff691b2), Integer.valueOf(0xffd81b60)).put(Integer.valueOf(0xffd06b64), Integer.valueOf(0xffe67c73)).put(Integer.valueOf(0xffac725e), Integer.valueOf(0xff795548)).put(Integer.valueOf(0xffc2c2c2), Integer.valueOf(0xff616161)).put(Integer.valueOf(0xffcabdbf), Integer.valueOf(0xffa79b8e)).put(Integer.valueOf(0xffd40000), Integer.valueOf(0xffd50000)).put(Integer.valueOf(0xfff3501d), Integer.valueOf(0xfff4511e)).put(Integer.valueOf(0xffee6b00), Integer.valueOf(0xffef6c00)).put(Integer.valueOf(0xffef9200), Integer.valueOf(0xfff09300)).put(Integer.valueOf(0xfff5be25), Integer.valueOf(0xfff6bf26)).put(Integer.valueOf(0xffe2cb4c), Integer.valueOf(0xffe4c441)).put(Integer.valueOf(0xffbfc932), Integer.valueOf(0xffc0ca33)).put(Integer.valueOf(0xff7bb241), Integer.valueOf(0xff7cb342)).put(Integer.valueOf(0xff0a7f42), Integer.valueOf(0xff0b8043)).put(Integer.valueOf(0xff009587), Integer.valueOf(0xff009688)).put(Integer.valueOf(0xff029ae4), Integer.valueOf(0xff039be5)).put(Integer.valueOf(0xff4184f3), Integer.valueOf(0xff4285f4)).put(Integer.valueOf(0xff3f5ca9), Integer.valueOf(0xff3f51b5)).put(Integer.valueOf(0xff7885ca), Integer.valueOf(0xff7986cb)).put(Integer.valueOf(0xffb29cda), Integer.valueOf(0xffb39ddb)).put(Integer.valueOf(0xff8d23a9), Integer.valueOf(0xff8e24aa)).put(Integer.valueOf(0xffac1356), Integer.valueOf(0xffad1457)).put(Integer.valueOf(0xffd71a5f), Integer.valueOf(0xffd81b60)).put(Integer.valueOf(0xffe57b72), Integer.valueOf(0xffe67c73)).put(Integer.valueOf(0xff785447), Integer.valueOf(0xff795548)).put(Integer.valueOf(0xff636363), Integer.valueOf(0xff616161)).build();
    private final ImmutableList calendarColorList;
    private final ImmutableBiMap calendarKeyToValue;
    private final ImmutableList eventColorList;
    private final ImmutableMap eventColorMap;
    private final ImmutableMap valueToColor;

    private ColorFactoryImpl(ImmutableList immutablelist, ImmutableMap immutablemap, ImmutableList immutablelist1, ImmutableBiMap immutablebimap, ImmutableMap immutablemap1)
    {
        eventColorList = immutablelist;
        eventColorMap = immutablemap;
        calendarColorList = immutablelist1;
        calendarKeyToValue = immutablebimap;
        valueToColor = immutablemap1;
    }

    public static ColorFactoryImpl create(Resources resources)
    {
        String as[] = resources.getStringArray(0x7f0b000d);
        com.google.common.collect.ImmutableList.Builder builder2 = new com.google.common.collect.ImmutableList.Builder();
        Object obj = new com.google.common.collect.ImmutableMap.Builder();
        resources = new com.google.common.collect.ImmutableList.Builder();
        com.google.common.collect.ImmutableBiMap.Builder builder = new com.google.common.collect.ImmutableBiMap.Builder();
        com.google.common.collect.ImmutableMap.Builder builder1 = new com.google.common.collect.ImmutableMap.Builder();
        int j = ColorDefinition.values().length;
        boolean flag;
        if (j == as.length)
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
        int i = 0;
        while (i < j) 
        {
            ColorDefinition colordefinition = ColorDefinition.values()[i];
            Object obj1 = as[i];
            Object obj3;
            boolean flag1;
            if (!colordefinition.eventKey.equals("N/A"))
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                Object obj2 = colordefinition.eventKey;
                obj1 = new AutoValue_EventColor(colordefinition.value | 0xff000000, Platform.nullToEmpty(((String) (obj2))), Platform.nullToEmpty(((String) (obj1))));
                obj2 = (com.google.common.collect.ImmutableList.Builder)builder2.add(obj1);
                ((com.google.common.collect.ImmutableMap.Builder) (obj)).put(((EventColor) (obj1)).getKey(), obj1);
            }
            obj1 = new AutoValue_NamedCalendarColor(colordefinition.value | 0xff000000, 0x7f0b000d, i);
            obj3 = (com.google.common.collect.ImmutableList.Builder)resources.add(obj1);
            obj3 = (com.google.common.collect.ImmutableBiMap.Builder)builder.put(colordefinition.calendarKey, Integer.valueOf(colordefinition.value));
            builder1.put(Integer.valueOf(colordefinition.value), obj1);
            i++;
        }
        builder2.forceCopy = true;
        ImmutableList immutablelist = ImmutableList.asImmutableList(builder2.contents, builder2.size);
        obj = ((com.google.common.collect.ImmutableMap.Builder) (obj)).build();
        resources.forceCopy = true;
        return new ColorFactoryImpl(immutablelist, ((ImmutableMap) (obj)), ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (resources)).contents, ((com.google.common.collect.ImmutableList.Builder) (resources)).size), (ImmutableBiMap)builder.build(), builder1.build());
    }

    private final CalendarColor createCalendarColorImpl(int i)
    {
        int j = i | 0xff000000;
        if (LEGACY_COLOR_MAPPING.containsKey(Integer.valueOf(j)))
        {
            i = ((Integer)LEGACY_COLOR_MAPPING.get(Integer.valueOf(j))).intValue();
        } else
        {
            i = j;
        }
        if (valueToColor.containsKey(Integer.valueOf(i)))
        {
            return (CalendarColor)valueToColor.get(Integer.valueOf(i));
        } else
        {
            return new AutoValue_CustomCalendarColor(j | 0xff000000, "");
        }
    }

    public final CalendarColor createBirthdayColor(int i)
    {
        return createCalendarColorImpl(i);
    }

    public final CalendarColor createCalendarColor(String s, int i)
    {
        if (s != null && calendarKeyToValue.containsKey(s))
        {
            return (CalendarColor)valueToColor.get(calendarKeyToValue.get(s));
        } else
        {
            return new AutoValue_CustomCalendarColor(0xff000000 | i, "");
        }
    }

    public final EventColor createGoogleEventColor(String s)
    {
        return (EventColor)eventColorMap.get(s);
    }

    public final CalendarColor createHolidayColor(int i)
    {
        return createCalendarColorImpl(i);
    }

    public final CalendarColor createTaskColor(int i)
    {
        return createCalendarColorImpl(i);
    }

    public final NamedCalendarColor defaultHolidayColor()
    {
        return (NamedCalendarColor)valueToColor.get(Integer.valueOf(ColorDefinition.EUCALYPTUS.value));
    }

    public final NamedCalendarColor defaultTaskColor()
    {
        return (NamedCalendarColor)valueToColor.get(Integer.valueOf(ColorDefinition.COBALT.value));
    }

    public final int getBirthdayColorKey(CalendarColor calendarcolor)
    {
        if (calendarcolor instanceof CustomCalendarColor)
        {
            return ((CustomCalendarColor)calendarcolor).getOriginalValue();
        } else
        {
            return calendarcolor.getValue();
        }
    }

    public final String getCalendarColorKey(NamedCalendarColor namedcalendarcolor)
    {
        return (String)((ImmutableBiMap)calendarKeyToValue.inverse()).get(Integer.valueOf(namedcalendarcolor.getValue()));
    }

    public final ImmutableList getCalendarColorList()
    {
        return calendarColorList;
    }

    public final ImmutableList getEventColorList()
    {
        return eventColorList;
    }


    private class ColorDefinition extends Enum
    {

        private static final ColorDefinition $VALUES[];
        private static final ColorDefinition AMETHYST;
        private static final ColorDefinition AVOCADO;
        private static final ColorDefinition BANANA;
        private static final ColorDefinition BASIL;
        private static final ColorDefinition BIRCH;
        private static final ColorDefinition BLUEBERRY;
        private static final ColorDefinition CHERRY_BLOSSOM;
        private static final ColorDefinition CITRON;
        public static final ColorDefinition COBALT;
        private static final ColorDefinition COCOA;
        public static final ColorDefinition EUCALYPTUS;
        private static final ColorDefinition FLAMINGO;
        private static final ColorDefinition GRAPE;
        private static final ColorDefinition GRAPHITE;
        private static final ColorDefinition LAVENDER;
        private static final ColorDefinition MANGO;
        private static final ColorDefinition PEACOCK;
        private static final ColorDefinition PISTACHIO;
        private static final ColorDefinition PUMPKIN;
        private static final ColorDefinition RADICCIO;
        private static final ColorDefinition SAGE;
        private static final ColorDefinition TANGERINE;
        private static final ColorDefinition TOMATO;
        private static final ColorDefinition WISTERIA;
        public final String calendarKey;
        public final String eventKey;
        public final int value;

        public static ColorDefinition[] values()
        {
            return (ColorDefinition[])$VALUES.clone();
        }

        static 
        {
            TOMATO = new ColorDefinition("TOMATO", 0, 0xffd50000, "3", "11");
            TANGERINE = new ColorDefinition("TANGERINE", 1, 0xfff4511e, "4", "6");
            PUMPKIN = new ColorDefinition("PUMPKIN", 2, 0xffef6c00, "5", "N/A");
            MANGO = new ColorDefinition("MANGO", 3, 0xfff09300, "6", "N/A");
            BANANA = new ColorDefinition("BANANA", 4, 0xfff6bf26, "12", "5");
            CITRON = new ColorDefinition("CITRON", 5, 0xffe4c441, "11", "N/A");
            AVOCADO = new ColorDefinition("AVOCADO", 6, 0xffc0ca33, "10", "N/A");
            PISTACHIO = new ColorDefinition("PISTACHIO", 7, 0xff7cb342, "9", "N/A");
            BASIL = new ColorDefinition("BASIL", 8, 0xff0b8043, "8", "10");
            EUCALYPTUS = new ColorDefinition("EUCALYPTUS", 9, 0xff009688, "7", "N/A");
            SAGE = new ColorDefinition("SAGE", 10, 0xff33b679, "13", "2");
            PEACOCK = new ColorDefinition("PEACOCK", 11, 0xff039be5, "14", "7");
            COBALT = new ColorDefinition("COBALT", 12, 0xff4285f4, "15", "N/A");
            BLUEBERRY = new ColorDefinition("BLUEBERRY", 13, 0xff3f51b5, "16", "9");
            LAVENDER = new ColorDefinition("LAVENDER", 14, 0xff7986cb, "17", "1");
            WISTERIA = new ColorDefinition("WISTERIA", 15, 0xffb39ddb, "18", "N/A");
            AMETHYST = new ColorDefinition("AMETHYST", 16, 0xff9e69af, "24", "N/A");
            GRAPE = new ColorDefinition("GRAPE", 17, 0xff8e24aa, "23", "3");
            RADICCIO = new ColorDefinition("RADICCIO", 18, 0xffad1457, "21", "N/A");
            CHERRY_BLOSSOM = new ColorDefinition("CHERRY_BLOSSOM", 19, 0xffd81b60, "22", "N/A");
            FLAMINGO = new ColorDefinition("FLAMINGO", 20, 0xffe67c73, "2", "4");
            COCOA = new ColorDefinition("COCOA", 21, 0xff795548, "1", "N/A");
            GRAPHITE = new ColorDefinition("GRAPHITE", 22, 0xff616161, "19", "8");
            BIRCH = new ColorDefinition("BIRCH", 23, 0xffa79b8e, "20", "N/A");
            $VALUES = (new ColorDefinition[] {
                TOMATO, TANGERINE, PUMPKIN, MANGO, BANANA, CITRON, AVOCADO, PISTACHIO, BASIL, EUCALYPTUS, 
                SAGE, PEACOCK, COBALT, BLUEBERRY, LAVENDER, WISTERIA, AMETHYST, GRAPE, RADICCIO, CHERRY_BLOSSOM, 
                FLAMINGO, COCOA, GRAPHITE, BIRCH
            });
        }

        private ColorDefinition(String s, int i, int j, String s1, String s2)
        {
            super(s, i);
            value = j;
            calendarKey = s1;
            eventKey = s2;
        }
    }

}
