// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.location.fullscreen;

import android.content.Context;
import com.google.android.calendar.timely.SuggestionFetcher;
import com.google.android.calendar.timely.location.LocationFetcher;
import com.google.common.util.concurrent.SettableFuture;

final class RemoteLocationSuggestionFetcher
{

    public SettableFuture future;
    public SuggestionFetcher remoteFetcher;

    RemoteLocationSuggestionFetcher(Context context)
    {
        remoteFetcher = new LocationFetcher(context);
        class .Lambda._cls0
            implements com.google.android.calendar.timely.SuggestionFetcher.OnSuggestionsListener
        {

            private final RemoteLocationSuggestionFetcher arg$1;

            public final void onUpdateSuggestions(List list)
            {
                Object obj = arg$1;
                if (((RemoteLocationSuggestionFetcher) (obj)).future != null)
                {
                    obj = ((RemoteLocationSuggestionFetcher) (obj)).future;
                    class .Lambda._cls1
                        implements Function
                    {

                        public static final Function $instance = new .Lambda._cls1();

                        public final Object apply(Object obj2)
                        {
                            obj2 = (EventLocationResult)obj2;
                            if (((EventLocationResult) (obj2)).isHeader)
                            {
                                return null;
                            } else
                            {
                                return new SuggestionLocationViewHolder.Suggestion(((EventLocationResult) (obj2)).name, ((EventLocationResult) (obj2)).address, ((EventLocationResult) (obj2)).reference);
                            }
                        }


                        private .Lambda._cls1()
                        {
                        }
                    }

                    Object obj1;
                    if (list instanceof FluentIterable)
                    {
                        list = (FluentIterable)list;
                    } else
                    {
                        list = new com.google.common.collect.FluentIterable._cls1(list, list);
                    }
                    obj1 = .Lambda._cls1..instance;
                    list = (Iterable)((FluentIterable) (list)).iterableDelegate.or(list);
                    if (list == null)
                    {
                        throw new NullPointerException();
                    }
                    if (obj1 == null)
                    {
                        throw new NullPointerException();
                    }
                    list = new com.google.common.collect.Iterables._cls5(list, ((Function) (obj1)));
                    if (list instanceof FluentIterable)
                    {
                        list = (FluentIterable)list;
                    } else
                    {
                        list = new com.google.common.collect.FluentIterable._cls1(list, list);
                    }
                    obj1 = com.google.common.base.Predicates.ObjectPredicate.NOT_NULL;
                    list = (Iterable)((FluentIterable) (list)).iterableDelegate.or(list);
                    if (list == null)
                    {
                        throw new NullPointerException();
                    }
                    if (obj1 == null)
                    {
                        throw new NullPointerException();
                    }
                    list = new com.google.common.collect.Iterables._cls4(list, ((com.google.common.base.Predicate) (obj1)));
                    if (list instanceof FluentIterable)
                    {
                        list = (FluentIterable)list;
                    } else
                    {
                        list = new com.google.common.collect.FluentIterable._cls1(list, list);
                    }
                    ((AbstractFuture) (obj)).set(ImmutableList.copyOf((Iterable)((FluentIterable) (list)).iterableDelegate.or(list)));
                }
            }

            .Lambda._cls0()
            {
                arg$1 = RemoteLocationSuggestionFetcher.this;
            }
        }

        remoteFetcher.suggestionsListener = new .Lambda._cls0();
    }
}
