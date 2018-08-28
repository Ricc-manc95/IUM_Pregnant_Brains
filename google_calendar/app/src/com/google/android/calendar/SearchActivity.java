// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.SearchRecentSuggestions;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollector;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollectorHolder;
import com.google.android.calendar.common.activity.CalendarSupportActivity;
import com.google.android.calendar.search.SearchFragment;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemUtil;
import com.google.android.calendar.timely.animations.EventInfoAnimationData;
import com.google.android.calendar.timely.callbacks.OnLaunchDetailsHandler;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar:
//            LaunchInfoActivityUtils, KeyboardManipulator, CalendarApplication, Utils, 
//            CalendarController, AllInOneCalendarActivity

public class SearchActivity extends CalendarSupportActivity
    implements TextWatcher, android.widget.TextView.OnEditorActionListener, OnLaunchDetailsHandler
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/SearchActivity);
    private ImageView clearSearchButton;
    public KeyboardManipulator keyboardManipulator;
    private final ContentObserver observer = new _cls1(new Handler());
    public String query;
    public EditText searchEditText;
    public SearchFragment searchFragment;

    public SearchActivity()
    {
    }

    private static String getQueryFromIntent(Intent intent)
    {
        if (intent.hasExtra("android.speech.extra.RESULTS"))
        {
            return (String)intent.getStringArrayListExtra("android.speech.extra.RESULTS").get(0);
        }
        if (intent.hasExtra("query"))
        {
            return intent.getStringExtra("query");
        } else
        {
            return null;
        }
    }

    static final boolean lambda$onCreateOptionsMenu$2$SearchActivity(View view)
    {
        view.setLeft(0);
        return true;
    }

    private final void updateClearSearchVisibility()
    {
        if (searchEditText.getText().length() == 0)
        {
            clearSearchButton.setVisibility(4);
            return;
        } else
        {
            clearSearchButton.setVisibility(0);
            return;
        }
    }

    public void afterTextChanged(Editable editable)
    {
        updateClearSearchVisibility();
    }

    public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (LaunchInfoActivityUtils.redirectIfMandatoryPermissionsNotGranted(this))
        {
            return;
        }
        setContentView(0x7f050152);
        setDefaultKeyMode(3);
        Object obj = (Toolbar)findViewById(0x7f100113);
        class .Lambda._cls0
            implements android.view.View.OnClickListener
        {

            private final SearchActivity arg$1;

            public final void onClick(View view)
            {
                arg$1.finish();
            }

            .Lambda._cls0()
            {
                arg$1 = SearchActivity.this;
            }
        }

        .Lambda._cls0 _lcls0 = new .Lambda._cls0();
        ((Toolbar) (obj)).ensureNavButtonView();
        ((Toolbar) (obj)).mNavButtonView.setOnClickListener(_lcls0);
        searchEditText = (EditText)findViewById(0x7f10035a);
        searchEditText.setOnEditorActionListener(this);
        searchEditText.addTextChangedListener(this);
        searchEditText.requestFocus();
        clearSearchButton = (ImageView)findViewById(0x7f100359);
        class .Lambda._cls1
            implements android.view.View.OnClickListener
        {

            private final SearchActivity arg$1;

            public final void onClick(View view)
            {
                SearchActivity searchactivity = arg$1;
                if (view.getId() == 0x7f100359)
                {
                    searchactivity.searchEditText.setText("");
                    view = searchactivity.keyboardManipulator;
                    view.showPendingSince = SystemClock.uptimeMillis();
                    view.showIfNecessary();
                }
            }

            .Lambda._cls1()
            {
                arg$1 = SearchActivity.this;
            }
        }

        clearSearchButton.setOnClickListener(new .Lambda._cls1());
        keyboardManipulator = new KeyboardManipulator(this, searchEditText);
        if (bundle != null && bundle.containsKey("intent"))
        {
            setIntent((Intent)bundle.getParcelable("intent"));
        }
        obj = getIntent();
        boolean flag;
        if ("android.intent.action.SEARCH".equals(((Intent) (obj)).getAction()))
        {
            if (bundle != null && bundle.containsKey("key_restore_search_query"))
            {
                query = bundle.getString("key_restore_search_query");
            } else
            {
                query = getQueryFromIntent(((Intent) (obj)));
            }
        }
        if (bundle == null || !bundle.containsKey("key_rotation"))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj = super.mFragments.mHost.mFragmentManager;
        if (bundle == null)
        {
            obj = ((FragmentManager) (obj)).beginTransaction();
            searchFragment = new SearchFragment();
            ((FragmentTransaction) (obj)).replace(0x7f100358, searchFragment, "SearchFragment");
            ((FragmentTransaction) (obj)).commit();
        } else
        {
            searchFragment = (SearchFragment)((FragmentManager) (obj)).findFragmentByTag("SearchFragment");
        }
        updateClearSearchVisibility();
        if (flag)
        {
            obj = keyboardManipulator;
            obj.showPendingSince = SystemClock.uptimeMillis();
            ((KeyboardManipulator) (obj)).showIfNecessary();
        }
        if (bundle != null)
        {
            bundle = PerformanceMetricCollectorHolder.instance;
            if (bundle == null)
            {
                throw new NullPointerException(String.valueOf("PrimesLogger not set"));
            } else
            {
                ((PerformanceMetricCollector)bundle).recordMemory("SearchActivity.Recreated");
                return;
            }
        }
        bundle = PerformanceMetricCollectorHolder.instance;
        if (bundle == null)
        {
            throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        } else
        {
            ((PerformanceMetricCollector)bundle).recordMemory("SearchActivity.Created");
            return;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        menu = findViewById(0x102002c);
        if (menu != null)
        {
            int i = getResources().getDimensionPixelSize(0x7f0e0258);
            menu.setPadding(i, 0, i, 0);
            class .Lambda._cls2
                implements android.view.ViewTreeObserver.OnPreDrawListener
            {

                private final View arg$1;

                public final boolean onPreDraw()
                {
                    return SearchActivity.lambda$onCreateOptionsMenu$2$SearchActivity(arg$1);
                }

            .Lambda._cls2(View view)
            {
                arg$1 = view;
            }
            }

            menu.getViewTreeObserver().addOnPreDrawListener(new .Lambda._cls2(menu));
        }
        if (query != null)
        {
            menu = query;
            if (menu != null)
            {
                searchEditText.setText(menu);
                searchEditText.setSelection(searchEditText.getText().length());
            }
        }
        return true;
    }

    protected void onDestroy()
    {
        PerformanceMetricCollector performancemetriccollector = PerformanceMetricCollectorHolder.instance;
        if (performancemetriccollector == null)
        {
            throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        } else
        {
            ((PerformanceMetricCollector)performancemetriccollector).recordMemory("SearchActivity.Destroyed");
            super.onDestroy();
            return;
        }
    }

    public boolean onEditorAction(TextView textview, int i, KeyEvent keyevent)
    {
        if (i == 3 || keyevent != null && keyevent.getKeyCode() == 66)
        {
            keyevent = (CalendarApplication)getApplication();
            textview = textview.getText().toString();
            if (textview != null)
            {
                (new SearchRecentSuggestions(keyevent, Utils.getSearchAuthority(keyevent), 1)).saveRecentQuery(textview, null);
            }
            keyevent = ((SearchManager)keyevent.getSystemService("search")).getSearchableInfo(new ComponentName(keyevent, com/google/android/calendar/SearchActivity));
            Intent intent = new Intent("android.intent.action.SEARCH");
            intent.putExtra("query", textview);
            intent.setComponent(keyevent.getSearchActivity());
            intent.addFlags(0x20000000);
            startActivity(intent);
            keyboardManipulator.requestHide();
            return true;
        } else
        {
            return false;
        }
    }

    public final void onLaunchDetails(TimelineItem timelineitem, EventInfoAnimationData eventinfoanimationdata)
    {
        if (TimelineItemUtil.isReminderBundle(timelineitem))
        {
            LogUtils.e(TAG, "Unable to launch bundle", new Object[0]);
            Toast.makeText(this, 0x7f130475, 0).show();
            return;
        } else
        {
            CalendarController.launchViewDetails(this, timelineitem);
            return;
        }
    }

    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        setIntent(intent);
        if ("android.intent.action.SEARCH".equals(intent.getAction()))
        {
            query = getQueryFromIntent(intent);
            if (query != null)
            {
                intent = query;
                if (intent != null)
                {
                    searchEditText.setText(intent);
                    searchEditText.setSelection(searchEditText.getText().length());
                }
                searchFragment.doSearch();
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        if (menuitem.getItemId() == 0x102002c)
        {
            menuitem = new Intent(this, com/google/android/calendar/AllInOneCalendarActivity);
            menuitem.setAction("android.intent.action.VIEW");
            menuitem.setFlags(0x4000000);
            menuitem.putExtra("KEY_HOME", true);
            startActivity(menuitem);
            return true;
        } else
        {
            return false;
        }
    }

    protected void onPause()
    {
        super.onPause();
        getContentResolver().unregisterContentObserver(observer);
        keyboardManipulator.requestHide();
    }

    protected void onResume()
    {
        super.onResume();
        getContentResolver().registerContentObserver(android.provider.CalendarContract.Events.CONTENT_URI, true, observer);
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putString("key_restore_search_query", query);
        bundle.putBoolean("key_rotation", true);
        bundle.putParcelable("intent", getIntent());
    }

    public void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }


    private class _cls1 extends ContentObserver
    {

        private final SearchActivity this$0;

        public final boolean deliverSelfNotifications()
        {
            return true;
        }

        public final void onChange(boolean flag)
        {
            if (searchFragment != null && query != null)
            {
                searchFragment.doSearch();
            }
        }

        public final void onChange(boolean flag, Uri uri)
        {
            onChange(flag);
            AbstractEventViewScreenController.notifyContentProviderUpdateIfAvailable(mFragments.mHost.mFragmentManager, uri);
        }

        _cls1(Handler handler)
        {
            this$0 = SearchActivity.this;
            super(handler);
        }
    }

}
