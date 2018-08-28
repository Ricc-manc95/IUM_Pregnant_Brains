// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.location.fullscreen;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.common.view.NinjaEditText;
import com.google.android.calendar.newapi.common.net.LocationResolver;
import com.google.android.calendar.newapi.segment.common.fullscreen.EditFullScreenController;
import com.google.android.calendar.newapi.segment.common.fullscreen.SearchBoxPresenter;
import com.google.android.calendar.newapi.segment.common.fullscreen.SearchFullScreenViewHolder;
import com.google.android.calendar.timely.SuggestionFetcher;
import com.google.common.base.Joiner;
import com.google.common.base.Platform;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.Collections;
import java.util.Locale;

// Referenced classes of package com.google.android.calendar.newapi.segment.location.fullscreen:
//            OnLocationSelectedListener, LocationSuggestionAdapter, ContactPhotoCache, LocationFullScreenPresenter, 
//            LocationSuggestionFetcher, RemoteLocationSuggestionFetcher, LocalLocationSuggestionFetcher

public final class LocationEditFullScreenController extends EditFullScreenController
    implements OnLocationSelectedListener
{

    public int currentAcceptedSuggestionId;
    private LocationSuggestionFetcher fetcher;
    private boolean instanceRestored;
    private LocationFullScreenPresenter presenter;

    public LocationEditFullScreenController()
    {
        currentAcceptedSuggestionId = 0;
        instanceRestored = false;
    }

    static EventLocation createLegacyLocation(SuggestionLocationViewHolder.Suggestion suggestion)
    {
        Object obj1 = suggestion.title;
        String s = suggestion.address;
        suggestion = new com.google.android.calendar.api.event.location.EventLocation.Builder();
        Object obj = (new Joiner(", ")).skipNulls();
        obj1 = Platform.emptyToNull(((String) (obj1)));
        s = Platform.emptyToNull(s);
        Object aobj[] = new Object[0];
        if (aobj == null)
        {
            throw new NullPointerException();
        }
        obj1 = (new com.google.common.base.Joiner._cls3(aobj, obj1, s)).iterator();
        obj = ((Joiner) (obj)).appendTo(new StringBuilder(), ((java.util.Iterator) (obj1))).toString();
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            suggestion.name = (String)obj;
            return new EventLocation(suggestion);
        }
    }

    public static LocationEditFullScreenController newInstance(String s, int i)
    {
        Bundle bundle = new Bundle();
        bundle.putString("ARG_SEARCH_TEXT", s);
        bundle.putInt("ARG_HEADER_COLOR", i);
        s = new LocationEditFullScreenController();
        s.setArguments(bundle);
        return s;
    }

    public final View createView(Context context)
    {
        int i = getArguments().getInt("ARG_HEADER_COLOR");
        SearchFullScreenViewHolder searchfullscreenviewholder = new SearchFullScreenViewHolder(context);
        Object obj = searchfullscreenviewholder.toolbar;
        class .Lambda._cls0
            implements android.view.View.OnClickListener
        {

            private final LocationEditFullScreenController arg$1;

            public final void onClick(View view)
            {
                arg$1.hideKeyboardAndClose();
            }

            .Lambda._cls0()
            {
                arg$1 = LocationEditFullScreenController.this;
            }
        }

        .Lambda._cls0 _lcls0 = new .Lambda._cls0();
        ((Toolbar) (obj)).ensureNavButtonView();
        ((Toolbar) (obj)).mNavButtonView.setOnClickListener(_lcls0);
        obj = fetcher;
        searchfullscreenviewholder.searchBox.setHint(0x7f1301b5);
        if (i != -1)
        {
            searchfullscreenviewholder.toolbar.setBackgroundColor(i);
        }
        context = new LocationSuggestionAdapter(context, new ContactPhotoCache(context));
        searchfullscreenviewholder.suggestionList.setAdapter(context);
        context = new LocationFullScreenPresenter(searchfullscreenviewholder.searchBox, ((LocationSuggestionFetcher) (obj)), context);
        obj.listener = context;
        SearchBoxPresenter.create(searchfullscreenviewholder.searchBox, searchfullscreenviewholder.clearButton, null, context);
        presenter = context;
        context = searchfullscreenviewholder.toolbar;
        class .Lambda._cls1
            implements android.view.View.OnClickListener
        {

            private final LocationEditFullScreenController arg$1;

            public final void onClick(View view)
            {
                arg$1.onNavigateAway();
            }

            .Lambda._cls1()
            {
                arg$1 = LocationEditFullScreenController.this;
            }
        }

        obj = new .Lambda._cls1();
        context.ensureNavButtonView();
        ((Toolbar) (context)).mNavButtonView.setOnClickListener(((android.view.View.OnClickListener) (obj)));
        if (!instanceRestored)
        {
            context = presenter;
            String s = getArguments().getString("ARG_SEARCH_TEXT");
            ((LocationFullScreenPresenter) (context)).searchBox.setText(s);
            if (s != null)
            {
                ((LocationFullScreenPresenter) (context)).searchBox.setSelection(s.length());
            }
        }
        return searchfullscreenviewholder.view;
    }

    final void hideKeyboardAndClose()
    {
        EditText edittext = presenter.searchBox;
        ((InputMethodManager)edittext.getContext().getSystemService("input_method")).hideSoftInputFromWindow(edittext.getWindowToken(), 0);
        close();
    }

    public final void onContactSelected(ContactLocationViewHolder.Contact contact)
    {
        contact = contact.address;
        com.google.android.calendar.api.event.location.EventLocation.Builder builder = new com.google.android.calendar.api.event.location.EventLocation.Builder();
        if (contact == null)
        {
            throw new NullPointerException();
        } else
        {
            builder.name = (String)contact;
            onStructuredLocationRetrieved(new EventLocation(builder));
            return;
        }
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        Object obj;
        RemoteLocationSuggestionFetcher remotelocationsuggestionfetcher;
        boolean flag;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        remotelocationsuggestionfetcher = new RemoteLocationSuggestionFetcher(((Context) (obj)));
        fetcher = new LocationSuggestionFetcher(((Context) (obj)), new LocalLocationSuggestionFetcher(((Context) (obj))), remotelocationsuggestionfetcher);
        if (bundle != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        instanceRestored = flag;
    }

    public final void onDestroy()
    {
        fetcher.remoteSuggestionFetcher.remoteFetcher.close();
        fetcher = null;
        super.onDestroy();
    }

    public final boolean onNavigateAway()
    {
        EditText edittext = presenter.searchBox;
        ((InputMethodManager)edittext.getContext().getSystemService("input_method")).hideSoftInputFromWindow(edittext.getWindowToken(), 0);
        close();
        return true;
    }

    public final void onResume()
    {
        super.onResume();
        EditText edittext = presenter.searchBox;
        edittext.getClass();
        edittext.post(new com.google.android.calendar.newapi.common.Keyboard..Lambda._cls0(edittext));
        edittext.setOnFocusChangeListener(com.google.android.calendar.newapi.common.Keyboard..Lambda._cls1.$instance);
    }

    public final void onStart()
    {
        super.onStart();
        LocationFullScreenPresenter locationfullscreenpresenter = presenter;
        locationfullscreenpresenter.listener = this;
        locationfullscreenpresenter.adapter.listener = this;
    }

    public final void onStop()
    {
        LocationFullScreenPresenter locationfullscreenpresenter = presenter;
        locationfullscreenpresenter.listener = null;
        locationfullscreenpresenter.adapter.listener = null;
        super.onStop();
    }

    final void onStructuredLocationRetrieved(EventLocation eventlocation)
    {
        if ((com.google.android.calendar.newapi.segment.common.fullscreen.EditFullScreenController.OnFullScreenResultListener)super.mTarget != null)
        {
            ((com.google.android.calendar.newapi.segment.common.fullscreen.EditFullScreenController.OnFullScreenResultListener)super.mTarget).onFullScreenResult(eventlocation);
        }
        eventlocation = presenter.searchBox;
        ((InputMethodManager)eventlocation.getContext().getSystemService("input_method")).hideSoftInputFromWindow(eventlocation.getWindowToken(), 0);
        close();
    }

    public final void onSuggestionSelected(final SuggestionLocationViewHolder.Suggestion suggestion)
    {
        Object obj = null;
        if (suggestion == null)
        {
            onStructuredLocationRetrieved(null);
            return;
        }
        final int acceptedSuggestionId = currentAcceptedSuggestionId + 1;
        currentAcceptedSuggestionId = acceptedSuggestionId;
        if (TextUtils.isEmpty(suggestion.mapsClusterId))
        {
            Object obj1 = suggestion.title;
            String s = suggestion.address;
            suggestion = new com.google.android.calendar.api.event.location.EventLocation.Builder();
            obj = (new Joiner(", ")).skipNulls();
            obj1 = Platform.emptyToNull(((String) (obj1)));
            s = Platform.emptyToNull(s);
            Object aobj[] = new Object[0];
            if (aobj == null)
            {
                throw new NullPointerException();
            }
            obj1 = (new com.google.common.base.Joiner._cls3(aobj, obj1, s)).iterator();
            obj = ((Joiner) (obj)).appendTo(new StringBuilder(), ((java.util.Iterator) (obj1))).toString();
            if (obj == null)
            {
                throw new NullPointerException();
            } else
            {
                suggestion.name = (String)obj;
                onStructuredLocationRetrieved(new EventLocation(suggestion));
                return;
            }
        }
        Object obj2;
        if (super.mHost != null)
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = new LocationResolver(((Context) (obj)), Locale.getDefault().getLanguage());
        obj2 = suggestion.mapsClusterId;
        obj = (FluentFuture)CalendarExecutor.NET.submit(new com.google.android.calendar.newapi.common.net.LocationResolver..Lambda._cls0(((LocationResolver) (obj)), ((String) (obj2))));
        suggestion = new _cls1();
        obj2 = CalendarExecutor.MAIN;
        if (suggestion == null)
        {
            throw new NullPointerException();
        } else
        {
            ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj)), suggestion), ((java.util.concurrent.Executor) (obj2)));
            return;
        }
    }

    public final void onViewStateRestored(Bundle bundle)
    {
        super.onViewStateRestored(bundle);
        Object obj = presenter;
        bundle = ((LocationFullScreenPresenter) (obj)).searchBox.getText();
        obj = ((LocationFullScreenPresenter) (obj)).fetcher;
        if (bundle == null)
        {
            bundle = "";
        } else
        {
            bundle = bundle.toString().trim();
        }
        if (TextUtils.isEmpty(bundle))
        {
            bundle = Collections.emptyList();
            if (bundle == null)
            {
                bundle = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                bundle = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(bundle);
            }
            if (((LocationSuggestionFetcher) (obj)).listener != null)
            {
                bundle.addListener(new LocationSuggestionFetcher..Lambda._cls3(((LocationSuggestionFetcher) (obj)), bundle), CalendarExecutor.MAIN);
            }
        } else
        {
            if (((LocationSuggestionFetcher) (obj)).resultSetFuture != null)
            {
                ((LocationSuggestionFetcher) (obj)).resultSetFuture.cancel(true);
            }
            Object obj1 = ((LocationSuggestionFetcher) (obj)).localSuggestionFetcher;
            obj1 = AbstractTransformFuture.create((FluentFuture)CalendarExecutor.DISK.submit(new LocalLocationSuggestionFetcher..Lambda._cls0(((LocalLocationSuggestionFetcher) (obj1)), bundle)), new LocationSuggestionFetcher..Lambda._cls2(((LocationSuggestionFetcher) (obj))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            RemoteLocationSuggestionFetcher remotelocationsuggestionfetcher = ((LocationSuggestionFetcher) (obj)).remoteSuggestionFetcher;
            bundle = bundle.toString();
            if (remotelocationsuggestionfetcher.future != null)
            {
                remotelocationsuggestionfetcher.future.cancel(true);
            }
            remotelocationsuggestionfetcher.future = new SettableFuture();
            remotelocationsuggestionfetcher.remoteFetcher.startFetchingSuggestions(bundle);
            obj.resultSetFuture = AbstractTransformFuture.create(new com.google.common.util.concurrent.CollectionFuture.ListFuture(ImmutableList.copyOf(new ListenableFuture[] {
                obj1, AbstractTransformFuture.create(remotelocationsuggestionfetcher.future, new LocationSuggestionFetcher..Lambda._cls1(((LocationSuggestionFetcher) (obj))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE)
            }), false), LocationSuggestionFetcher..Lambda._cls0.$instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            bundle = ((LocationSuggestionFetcher) (obj)).resultSetFuture;
            if (((LocationSuggestionFetcher) (obj)).listener != null)
            {
                bundle.addListener(new LocationSuggestionFetcher..Lambda._cls3(((LocationSuggestionFetcher) (obj)), bundle), CalendarExecutor.MAIN);
                return;
            }
        }
    }

    private class _cls1
        implements FutureCallback
    {

        private final LocationEditFullScreenController this$0;
        private final int val$acceptedSuggestionId;
        private final SuggestionLocationViewHolder.Suggestion val$suggestion;

        public final void onFailure(Throwable throwable)
        {
            if (acceptedSuggestionId == currentAcceptedSuggestionId)
            {
                onStructuredLocationRetrieved(LocationEditFullScreenController.createLegacyLocation(suggestion));
            }
        }

        public final void onSuccess(Object obj)
        {
            obj = (EventLocation)obj;
            if (acceptedSuggestionId == currentAcceptedSuggestionId)
            {
                onStructuredLocationRetrieved(((EventLocation) (obj)));
            }
        }

        _cls1()
        {
            this$0 = LocationEditFullScreenController.this;
            acceptedSuggestionId = i;
            suggestion = suggestion1;
            super();
        }
    }

}
