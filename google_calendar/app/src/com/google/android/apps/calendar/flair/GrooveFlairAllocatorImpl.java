// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.flair;

import android.util.SparseArray;

// Referenced classes of package com.google.android.apps.calendar.flair:
//            GrooveFlairAllocator

public final class GrooveFlairAllocatorImpl
    implements GrooveFlairAllocator
{

    private static final SparseArray GROOVE_CATEGORY_KEYS = new _cls2();
    private static final SparseArray GROOVE_FLAIR_KEYS = new _cls1();

    public GrooveFlairAllocatorImpl()
    {
    }

    public final String allocateCategoryIllustration(int i)
    {
        return (String)GROOVE_CATEGORY_KEYS.get(0xff00 & i);
    }

    public final String allocateFlair(int i)
    {
        return (String)GROOVE_FLAIR_KEYS.get(i);
    }


    private class _cls1 extends SparseArray
    {

        _cls1()
        {
            append(257, "gym");
            append(258, "running");
            append(259, "walk");
            append(260, "yoga");
            append(261, "hiking");
            append(262, "cycling");
            append(263, "swimming");
            append(264, "climbing");
            append(265, "tennis");
            append(266, "badminton");
            append(267, "baseball");
            append(268, "basketball");
            append(269, "soccer");
            append(513, "learnlanguage");
            append(514, "code");
            append(515, "learninstrument");
            append(516, "art");
            append(521, "code");
            append(522, "cooking");
            append(769, "reachout");
            append(770, "mealfamily");
            append(771, "reachout");
            append(772, "reachout");
            append(774, "dinner");
            append(776, "bbq");
            append(777, "gamenight");
            append(780, "walkingdog");
            append(1025, "read");
            append(1026, "massage");
            append(1027, "hobby");
            append(1028, "cooking");
            append(1031, "cinema");
            append(1032, "sleep");
            append(1281, "planmyday");
            append(1282, "clean");
            append(1283, "chores");
            append(1292, "clean");
        }
    }


    private class _cls2 extends SparseArray
    {

        _cls2()
        {
            append(256, "groove_exercise");
            append(512, "groove_skill");
            append(768, "groove_friends");
            append(1024, "groove_metime");
            append(1280, "groove_organize");
        }
    }

}
