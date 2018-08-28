// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove.category;

import android.content.res.Resources;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.google.android.calendar.api.habit.Habit;

public final class GrooveCategories
{

    public static final SparseIntArray GROOVE_NAME_IDS = new _cls1();
    public static GrooveCategories instance;
    public static Resources resources;
    public final SparseArray categories = new SparseArray();

    public GrooveCategories(Resources resources1)
    {
        resources = resources1;
        categories.append(256, new Category(resources, 0x7f130279, 0x7f13026e, 0x7f130275, 0x7f0d0054, 0x7f0d0055, 0x7f020113));
        categories.append(512, new Category(resources, 0x7f13026c, 0x7f13025c, 0x7f13026a, 0x7f0d0052, 0x7f0d0053, 0x7f020117));
        categories.append(768, new Category(resources, 0x7f13028a, 0x7f130281, 0x7f130288, 0x7f0d0056, 0x7f0d0057, 0x7f020114));
        categories.append(1024, new Category(resources, 0x7f13029c, 0x7f13028e, 0x7f130296, 0x7f0d0058, 0x7f0d0059, 0x7f020115));
        categories.append(1280, new Category(resources, 0x7f1302a8, 0x7f1302a5, 0x7f1302a7, 0x7f0d005a, 0x7f0d005b, 0x7f020116));
    }

    public static String getName(Habit habit)
    {
        if (!TextUtils.isEmpty(habit.getSummary()))
        {
            return habit.getSummary();
        } else
        {
            return resources.getString(GROOVE_NAME_IDS.get(habit.getType()));
        }
    }

    public static Subcategory[] getSubcategoryListForCategory(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 256: 
            return (new Subcategory[] {
                new Subcategory(resources, 257, false), new Subcategory(resources, 258, false), new Subcategory(resources, 259, false), new Subcategory(resources, 260, false), new Subcategory(resources, 261, false), new Subcategory(resources, 262, false), new Subcategory(resources, 263, false), new Subcategory(resources, 264, false), new Subcategory(resources, 265, false), new Subcategory(resources, 266, false), 
                new Subcategory(resources, 267, false), new Subcategory(resources, 268, false), new Subcategory(resources, 269, false), new Subcategory(resources, 270, false)
            });

        case 512: 
            return (new Subcategory[] {
                new Subcategory(resources, 513, true, 0x7f130268), new Subcategory(resources, 514, false), new Subcategory(resources, 515, true, 0x7f13025f), new Subcategory(resources, 516, true, 0x7f130266), new Subcategory(resources, 517, false), new Subcategory(resources, 518, false), new Subcategory(resources, 519, false), new Subcategory(resources, 520, false), new Subcategory(resources, 521, false), new Subcategory(resources, 522, false), 
                new Subcategory(resources, 523, false), new Subcategory(resources, 524, false)
            });

        case 768: 
            return (new Subcategory[] {
                new Subcategory(resources, 769, false), new Subcategory(resources, 770, false), new Subcategory(resources, 771, false), new Subcategory(resources, 772, false), new Subcategory(resources, 773, false), new Subcategory(resources, 774, false), new Subcategory(resources, 775, false), new Subcategory(resources, 776, false), new Subcategory(resources, 777, false), new Subcategory(resources, 778, false), 
                new Subcategory(resources, 779, false), new Subcategory(resources, 780, false)
            });

        case 1024: 
            return (new Subcategory[] {
                new Subcategory(resources, 1025, false), new Subcategory(resources, 1026, false), new Subcategory(resources, 1027, true, 0x7f130291), new Subcategory(resources, 1028, false), new Subcategory(resources, 1029, false), new Subcategory(resources, 1030, false), new Subcategory(resources, 1031, false), new Subcategory(resources, 1032, false), new Subcategory(resources, 1033, false), new Subcategory(resources, 1034, false), 
                new Subcategory(resources, 1035, false), new Subcategory(resources, 1036, false), new Subcategory(resources, 1037, false)
            });

        case 1280: 
            return (new Subcategory[] {
                new Subcategory(resources, 1281, false), new Subcategory(resources, 1282, false), new Subcategory(resources, 1283, false), new Subcategory(resources, 1284, false), new Subcategory(resources, 1285, false), new Subcategory(resources, 1286, false), new Subcategory(resources, 1287, false), new Subcategory(resources, 1288, false), new Subcategory(resources, 1289, false), new Subcategory(resources, 1291, false), 
                new Subcategory(resources, 1292, false)
            });
        }
    }

    public static String[] getTextSuggestionsForType(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 513: 
            return resources.getStringArray(0x7f0b002e);

        case 515: 
            return resources.getStringArray(0x7f0b002c);

        case 516: 
            return resources.getStringArray(0x7f0b002d);

        case 1027: 
            return resources.getStringArray(0x7f0b002f);
        }
    }


    private class Category
    {

        public final int backgroundColor;
        public final int backgroundDrawableResId;
        public final String description;
        public final int fabColor;
        public final String question;
        public final String title;

        public Category(Resources resources1, int i, int j, int k, int l, int i1, int j1)
        {
            title = resources1.getString(i);
            description = resources1.getString(j);
            question = resources1.getString(k);
            backgroundColor = resources1.getColor(l);
            fabColor = resources1.getColor(i1);
            backgroundDrawableResId = j1;
        }
    }


    private class Subcategory
    {

        public final String name;
        public final String question;
        public final boolean requiresInput;
        public final int type;

        Subcategory(Resources resources1, int i, boolean flag)
        {
            this(resources1, i, false, 0);
        }

        Subcategory(Resources resources1, int i, boolean flag, int j)
        {
            type = i;
            name = resources1.getString(GrooveCategories.GROOVE_NAME_IDS.get(i));
            requiresInput = flag;
            if (j != 0)
            {
                resources1 = resources1.getString(j);
            } else
            {
                resources1 = null;
            }
            question = resources1;
        }
    }


    private class _cls1 extends SparseIntArray
    {

        _cls1()
        {
            append(256, 0x7f130279);
            append(258, 0x7f130277);
            append(259, 0x7f13027a);
            append(257, 0x7f13027c);
            append(260, 0x7f13027d);
            append(261, 0x7f13026f);
            append(262, 0x7f13026d);
            append(263, 0x7f130278);
            append(264, 0x7f130276);
            append(265, 0x7f130274);
            append(266, 0x7f130270);
            append(267, 0x7f130271);
            append(268, 0x7f130272);
            append(269, 0x7f130273);
            append(270, 0x7f13027b);
            append(512, 0x7f13026c);
            append(513, 0x7f130267);
            append(515, 0x7f130260);
            append(514, 0x7f130262);
            append(516, 0x7f130265);
            append(517, 0x7f130269);
            append(518, 0x7f13025d);
            append(519, 0x7f13026b);
            append(520, 0x7f13025e);
            append(521, 0x7f130261);
            append(522, 0x7f13025b);
            append(523, 0x7f130263);
            append(524, 0x7f130264);
            append(768, 0x7f13028a);
            append(769, 0x7f130289);
            append(770, 0x7f130283);
            append(771, 0x7f130280);
            append(772, 0x7f13027f);
            append(773, 0x7f130284);
            append(774, 0x7f130282);
            append(775, 0x7f13028b);
            append(776, 0x7f13027e);
            append(777, 0x7f130287);
            append(778, 0x7f130285);
            append(779, 0x7f130286);
            append(780, 0x7f13028c);
            append(1024, 0x7f13029c);
            append(1025, 0x7f130297);
            append(1026, 0x7f130294);
            append(1027, 0x7f130290);
            append(1028, 0x7f13028d);
            append(1029, 0x7f130292);
            append(1030, 0x7f130295);
            append(1031, 0x7f13029d);
            append(1032, 0x7f130299);
            append(1033, 0x7f13028f);
            append(1034, 0x7f130298);
            append(1035, 0x7f13029b);
            append(1036, 0x7f130293);
            append(1037, 0x7f13029a);
            append(1280, 0x7f1302a8);
            append(1281, 0x7f1302a6);
            append(1282, 0x7f1302a4);
            append(1283, 0x7f1302a3);
            append(1284, 0x7f1302a9);
            append(1285, 0x7f13029e);
            append(1286, 0x7f1302ab);
            append(1287, 0x7f1302a2);
            append(1288, 0x7f1302a1);
            append(1289, 0x7f1302aa);
            append(1291, 0x7f1302a0);
            append(1292, 0x7f13029f);
        }
    }

}
