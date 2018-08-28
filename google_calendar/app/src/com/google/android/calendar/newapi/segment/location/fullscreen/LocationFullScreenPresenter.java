// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.location.fullscreen;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.timely.SuggestionFetcher;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.segment.location.fullscreen:
//            OnLocationSelectedListener, LocationSuggestionAdapter, LocationSuggestionFetcher, RemoteLocationSuggestionFetcher

final class LocationFullScreenPresenter
    implements com.google.android.calendar.newapi.segment.common.fullscreen.SearchBoxPresenter.Listener, LocationSuggestionFetcher.Listener
{

    public final LocationSuggestionAdapter adapter;
    public final LocationSuggestionFetcher fetcher;
    public OnLocationSelectedListener listener;
    public final EditText searchBox;

    LocationFullScreenPresenter(EditText edittext, LocationSuggestionFetcher locationsuggestionfetcher, LocationSuggestionAdapter locationsuggestionadapter)
    {
        searchBox = edittext;
        fetcher = locationsuggestionfetcher;
        adapter = locationsuggestionadapter;
    }

    public final void onDonePressed()
    {
        throw new IllegalStateException("Location picker does not have done button.");
    }

    public final void onEnterPressed()
    {
        EditText edittext = searchBox;
        ((InputMethodManager)edittext.getContext().getSystemService("input_method")).hideSoftInputFromWindow(edittext.getWindowToken(), 0);
        if (listener == null)
        {
            return;
        }
        if (!TextUtils.isEmpty(searchBox.getText()))
        {
            SuggestionLocationViewHolder.Suggestion suggestion = new SuggestionLocationViewHolder.Suggestion(searchBox.getText().toString());
            listener.onSuggestionSelected(suggestion);
            return;
        } else
        {
            listener.onSuggestionSelected(null);
            return;
        }
    }

    public final void onItemsChanged(List list)
    {
        LocationSuggestionAdapter locationsuggestionadapter = adapter;
        locationsuggestionadapter.items = list;
        ((android.support.v7.widget.RecyclerView.Adapter) (locationsuggestionadapter)).mObservable.notifyChanged();
    }

    public final void onTextChanged(CharSequence charsequence)
    {
        LocationSuggestionFetcher locationsuggestionfetcher = fetcher;
        if (charsequence == null)
        {
            charsequence = "";
        } else
        {
            charsequence = charsequence.toString().trim();
        }
        if (TextUtils.isEmpty(charsequence))
        {
            charsequence = Collections.emptyList();
            if (charsequence == null)
            {
                charsequence = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                charsequence = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(charsequence);
            }
            if (locationsuggestionfetcher.listener != null)
            {
                charsequence.addListener(new LocationSuggestionFetcher..Lambda._cls3(locationsuggestionfetcher, charsequence), CalendarExecutor.MAIN);
            }
        } else
        {
            if (locationsuggestionfetcher.resultSetFuture != null)
            {
                locationsuggestionfetcher.resultSetFuture.cancel(true);
            }
            Object obj = locationsuggestionfetcher.localSuggestionFetcher;
            obj = AbstractTransformFuture.create((FluentFuture)CalendarExecutor.DISK.submit(new LocalLocationSuggestionFetcher..Lambda._cls0(((LocalLocationSuggestionFetcher) (obj)), charsequence)), new LocationSuggestionFetcher..Lambda._cls2(locationsuggestionfetcher), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            RemoteLocationSuggestionFetcher remotelocationsuggestionfetcher = locationsuggestionfetcher.remoteSuggestionFetcher;
            charsequence = charsequence.toString();
            if (remotelocationsuggestionfetcher.future != null)
            {
                remotelocationsuggestionfetcher.future.cancel(true);
            }
            remotelocationsuggestionfetcher.future = new SettableFuture();
            remotelocationsuggestionfetcher.remoteFetcher.startFetchingSuggestions(charsequence);
            locationsuggestionfetcher.resultSetFuture = AbstractTransformFuture.create(new com.google.common.util.concurrent.CollectionFuture.ListFuture(ImmutableList.copyOf(new ListenableFuture[] {
                obj, AbstractTransformFuture.create(remotelocationsuggestionfetcher.future, new LocationSuggestionFetcher..Lambda._cls1(locationsuggestionfetcher), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE)
            }), false), LocationSuggestionFetcher..Lambda._cls0.$instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            charsequence = locationsuggestionfetcher.resultSetFuture;
            if (locationsuggestionfetcher.listener != null)
            {
                charsequence.addListener(new LocationSuggestionFetcher..Lambda._cls3(locationsuggestionfetcher, charsequence), CalendarExecutor.MAIN);
                return;
            }
        }
    }
}
