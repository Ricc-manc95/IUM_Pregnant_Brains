// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.apps.calendar.util.concurrent.UncancelableFuture;
import com.google.android.calendar.timebox.Birthday;
import com.google.common.collect.Collections2;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

// Referenced classes of package com.google.android.calendar.timely:
//            BirthdayLoader

final class BirthdayCache
{

    private static final Map cache = new HashMap();

    static List getBirthdaysIfLoaded(List list)
    {
        Object obj = null;
        ListenableFuture listenablefuture = getFuture(null, list);
        list = obj;
        if (listenablefuture != null)
        {
            list = obj;
            if (listenablefuture.isDone())
            {
                list = (List)Futures.getUnchecked(listenablefuture);
            }
        }
        return list;
    }

    private static ListenableFuture getFuture(Context context, List list)
    {
        com/google/android/calendar/timely/BirthdayCache;
        JVM INSTR monitorenter ;
        ListenableFuture listenablefuture1;
        HashSet hashset;
        hashset = new HashSet(list.size());
        class .Lambda._cls1
            implements Function
        {

            public static final Function $instance = new .Lambda._cls1();

            public final Object apply(Object obj)
            {
                obj = (Birthday)obj;
                return Pair.create(((Birthday) (obj)).calendarId(), ((Birthday) (obj)).eventId());
            }


            private .Lambda._cls1()
            {
            }
        }

        hashset.addAll(new com.google.common.collect.Collections2.TransformedCollection(list, .Lambda._cls1..instance));
        listenablefuture1 = (ListenableFuture)cache.get(hashset);
        ListenableFuture listenablefuture;
        listenablefuture = listenablefuture1;
        if (listenablefuture1 != null)
        {
            break MISSING_BLOCK_LABEL_100;
        }
        listenablefuture = listenablefuture1;
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_100;
        }
        class .Lambda._cls0
            implements Function
        {

            public static final Function $instance = new .Lambda._cls0();

            public final Object apply(Object obj)
            {
                return BirthdayCache.lambda$getFuture$0$BirthdayCache((List)obj);
            }


            private .Lambda._cls0()
            {
            }
        }

        listenablefuture = AbstractTransformFuture.create((new BirthdayLoader(context)).apply(list), .Lambda._cls0..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        cache.put(hashset, UncancelableFuture.uncancelable(listenablefuture));
        com/google/android/calendar/timely/BirthdayCache;
        JVM INSTR monitorexit ;
        return listenablefuture;
        context;
        throw context;
    }

    static final List lambda$getFuture$0$BirthdayCache(List list)
    {
        Object obj;
        ArrayList arraylist;
        HashSet hashset;
        Iterator iterator1;
        arraylist = new ArrayList(list.size());
        hashset = new HashSet();
        obj = null;
        iterator1 = list.iterator();
_L6:
        Birthday birthday;
        if (!iterator1.hasNext())
        {
            break MISSING_BLOCK_LABEL_121;
        }
        birthday = (Birthday)iterator1.next();
        if (!birthday.isSelfBirthday()) goto _L2; else goto _L1
_L1:
        Object obj1 = birthday.email();
        if (!TextUtils.isEmpty(((CharSequence) (obj1)))) goto _L4; else goto _L3
_L3:
        if (obj != null) goto _L2; else goto _L5
_L5:
        obj1 = birthday;
_L8:
        obj = obj1;
          goto _L6
_L4:
        hashset.add(obj1);
        obj1 = birthday;
        if (obj == null) goto _L8; else goto _L7
_L7:
        if (((Birthday) (obj)).isContactAvailable()) goto _L2; else goto _L9
_L9:
        obj1 = birthday;
        if (birthday.isContactAvailable()) goto _L8; else goto _L2
_L2:
        obj1 = obj;
          goto _L8
        Iterator iterator = list.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Birthday birthday1 = (Birthday)iterator.next();
            if (birthday1.isBirthday() && !birthday1.isSelfBirthday())
            {
                String s = birthday1.email();
                if (TextUtils.isEmpty(s) || hashset.add(s))
                {
                    arraylist.add(birthday1);
                }
            }
        } while (true);
        if (obj != null)
        {
            arraylist.add(obj);
        }
        arraylist.addAll(Collections2.filter(list, com.google.android.calendar.timebox.BirthdaysProcessor..Lambda._cls0.$instance));
        return Collections.unmodifiableList(arraylist);
    }

    static ListenableFuture loadBirthdaysAsync(Context context, List list)
    {
        return getFuture(context, list);
    }

}
