// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.chips;

import android.accounts.Account;
import android.content.Context;
import android.widget.Filter;
import com.android.ex.chips.BaseRecipientAdapter;
import com.android.ex.chips.PhotoManager;
import com.android.ex.chips.RecipientEntry;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.google.android.gms.chips:
//            GmsChipsLoggingFlags, GmsPhotoManager

public final class GmsRecipientAdapter extends BaseRecipientAdapter
{

    public final AutocompletionEventsListenerDispatcher eventsListenerDispatcher;
    public GmsChipsLoggingFlags gmsChipsLoggingFlags;
    public final GoogleApiClient mClient;
    public List mEntryList;
    public Set mExistingDestinations;
    private boolean mLoggingEnabled;
    public double samplingRate;

    public GmsRecipientAdapter(Context context, Account account, GoogleApiClient googleapiclient, GmsPhotoManager gmsphotomanager)
    {
        this(context, null, googleapiclient, gmsphotomanager, 10);
    }

    private GmsRecipientAdapter(Context context, Account account, GoogleApiClient googleapiclient, GmsPhotoManager gmsphotomanager, int i)
    {
        super(context, 10);
        mLoggingEnabled = false;
        context = new AutoValue_GmsChipsLoggingFlags.Builder();
        context.loggingErrorsEnabled = Boolean.valueOf(false);
        gmsChipsLoggingFlags = context.setLoggingDataSourceEnabled(false).build();
        samplingRate = 0.01D;
        eventsListenerDispatcher = new AutocompletionEventsListenerDispatcher();
        super.account = account;
        mClient = googleapiclient;
        super.photoManager = gmsphotomanager;
    }

    protected final List constructEntryList()
    {
        return mEntryList;
    }

    public final Filter getFilter()
    {
        return new GmsFilter();
    }

    protected final void putOneEntry(com.android.ex.chips.BaseRecipientAdapter.TemporaryEntry temporaryentry, boolean flag)
    {
        if (mEntryList.size() >= mPreferredMaxResultCount || mExistingDestinations.contains(temporaryentry.destination))
        {
            return;
        } else
        {
            mExistingDestinations.add(temporaryentry.destination);
            temporaryentry = RecipientEntry.constructTopLevelEntry(temporaryentry.displayName, temporaryentry.displayNameSource, temporaryentry.destination, temporaryentry.destinationType, temporaryentry.destinationLabel, temporaryentry.contactId, temporaryentry.directoryId, temporaryentry.dataId, temporaryentry.thumbnailUriString, true, temporaryentry.lookupKey);
            mEntryList.add(temporaryentry);
            super.photoManager.populatePhotoBytesAsync(temporaryentry, this);
            return;
        }
    }

    private class AutocompletionEventsListenerDispatcher
        implements AutocompletionEventsListener
    {

        private final List autocompletionEventsListeners = new ArrayList();
        private boolean isSelectionSessionOpen;

        public final void queryUpdated(String s)
        {
            if (!isSelectionSessionOpen)
            {
                selectionSessionStarted();
            }
            for (Iterator iterator = autocompletionEventsListeners.iterator(); iterator.hasNext(); ((AutocompletionEventsListener)iterator.next()).queryUpdated(s)) { }
        }

        public final void resultsReceived(int i, boolean flag)
        {
            for (Iterator iterator = autocompletionEventsListeners.iterator(); iterator.hasNext(); ((AutocompletionEventsListener)iterator.next()).resultsReceived(i, flag)) { }
        }

        public final void selectionSessionStarted()
        {
            isSelectionSessionOpen = true;
            for (Iterator iterator = autocompletionEventsListeners.iterator(); iterator.hasNext(); ((AutocompletionEventsListener)iterator.next()).selectionSessionStarted()) { }
        }

        AutocompletionEventsListenerDispatcher()
        {
            isSelectionSessionOpen = false;
        }
    }


    private class GmsFilter extends Filter
    {

        private CharSequence previousConstraint;
        private final GmsRecipientAdapter this$0;

        protected final android.widget.Filter.FilterResults performFiltering(CharSequence charsequence)
        {
            Object obj;
            Object obj2;
            android.widget.Filter.FilterResults filterresults;
            int k;
            int j1;
            if (previousConstraint == null)
            {
                GmsRecipientAdapter gmsrecipientadapter = GmsRecipientAdapter.this;
                if (gmsrecipientadapter.eventsListenerDispatcher != null)
                {
                    gmsrecipientadapter.eventsListenerDispatcher.selectionSessionStarted();
                }
            }
            previousConstraint = charsequence;
            (new Stopwatch()).start();
            obj = GmsRecipientAdapter.this;
            obj2 = charsequence.toString();
            if (((GmsRecipientAdapter) (obj)).eventsListenerDispatcher != null)
            {
                ((GmsRecipientAdapter) (obj)).eventsListenerDispatcher.queryUpdated(((String) (obj2)));
            }
            if (!TextUtils.isEmpty(charsequence))
            {
                obj = charsequence.toString();
            } else
            {
                obj = "";
            }
            filterresults = new android.widget.Filter.FilterResults();
            if (!mClient.isConnected() || TextUtils.isEmpty(charsequence))
            {
                if (gmsChipsLoggingFlags.loggingErrorsEnabled())
                {
                    obj2 = GmsRecipientAdapter.this;
                    double d;
                    ChipsRequestParams chipsrequestparams;
                    int i;
                    int l;
                    int k1;
                    if (!mClient.isConnected())
                    {
                        i = 5;
                    } else
                    {
                        i = 6;
                    }
                    charsequence = ((BaseRecipientAdapter) (obj2)).account;
                    if (charsequence != null)
                    {
                        charsequence = ((Account) (charsequence)).name;
                    } else
                    {
                        charsequence = null;
                    }
                    if (!TextUtils.isEmpty(((CharSequence) (obj))))
                    {
                        l = ((String) (obj)).length();
                    } else
                    {
                        l = 0;
                    }
                    obj = ((BaseRecipientAdapter) (obj2)).context;
                    charsequence = GmsChipsClearcutLogger.getInstance(((Context) (obj)), charsequence);
                    obj = ((Context) (obj)).getPackageName();
                    k1 = ((Integer)GmsChipsUtil.PACKAGE_NAME_TO_LABEL.getOrDefault(obj, Integer.valueOf(0))).intValue();
                    d = ((GmsRecipientAdapter) (obj2)).samplingRate;
                    obj = new ChipsExtension();
                    obj2 = new ChipsResponse();
                    chipsrequestparams = new ChipsRequestParams();
                    chipsrequestparams.queryLength = Integer.valueOf(l);
                    chipsrequestparams.autocompleteType = 1;
                    obj2.chipsRequestParams = chipsrequestparams;
                    obj2.numResults = Integer.valueOf(0);
                    obj2.latencyUsec = Long.valueOf(0L);
                    obj2.status = i;
                    obj.eventType = 2;
                    obj.clientLabel = k1;
                    obj.oneof_event_ = -1;
                    obj.oneof_event_ = 1;
                    obj.chipsResponse = ((ChipsResponse) (obj2));
                    charsequence.logEventWithSampling(((ChipsExtension) (obj)), d);
                }
                tempEntries = null;
                return filterresults;
            }
            obj2 = context;
            Object obj3 = GmsRecipientAdapter.this;
            if (!ChipsUtil.hasPermissions(((Context) (obj2)), null))
            {
                tempEntries = null;
                charsequence = GmsRecipientAdapter.this;
                if (gmsChipsLoggingFlags.loggingErrorsEnabled())
                {
                    obj2 = GmsRecipientAdapter.this;
                    charsequence = ((BaseRecipientAdapter) (obj2)).account;
                    double d1;
                    int j;
                    int i1;
                    if (charsequence != null)
                    {
                        charsequence = ((Account) (charsequence)).name;
                    } else
                    {
                        charsequence = null;
                    }
                    if (!TextUtils.isEmpty(((CharSequence) (obj))))
                    {
                        j = ((String) (obj)).length();
                    } else
                    {
                        j = 0;
                    }
                    obj = ((BaseRecipientAdapter) (obj2)).context;
                    charsequence = GmsChipsClearcutLogger.getInstance(((Context) (obj)), charsequence);
                    obj = ((Context) (obj)).getPackageName();
                    i1 = ((Integer)GmsChipsUtil.PACKAGE_NAME_TO_LABEL.getOrDefault(obj, Integer.valueOf(0))).intValue();
                    d1 = ((GmsRecipientAdapter) (obj2)).samplingRate;
                    obj = new ChipsExtension();
                    obj2 = new ChipsResponse();
                    obj3 = new ChipsRequestParams();
                    obj3.queryLength = Integer.valueOf(j);
                    obj3.autocompleteType = 1;
                    obj2.chipsRequestParams = ((ChipsRequestParams) (obj3));
                    obj2.numResults = Integer.valueOf(0);
                    obj2.latencyUsec = Long.valueOf(0L);
                    obj2.status = 7;
                    obj.eventType = 2;
                    obj.clientLabel = i1;
                    obj.oneof_event_ = -1;
                    obj.oneof_event_ = 1;
                    obj.chipsResponse = ((ChipsResponse) (obj2));
                    charsequence.logEventWithSampling(((ChipsExtension) (obj)), d1);
                }
                return filterresults;
            }
            obj2 = new com.google.android.gms.people.Autocomplete.AutocompleteOptions.Builder();
            obj2.mAccount = account.name;
            obj2.zzbSL = true;
            obj2.zzbSK = mPreferredMaxResultCount;
            obj3 = new com.google.android.gms.people.Autocomplete.AutocompleteOptions(((com.google.android.gms.people.Autocomplete.AutocompleteOptions.Builder) (obj2)));
            if (gmsChipsLoggingFlags.loggingDataSourceEnabled())
            {
                Object obj4 = GmsRecipientAdapter.this;
                obj2 = ((BaseRecipientAdapter) (obj4)).account;
                double d2;
                Object obj5;
                ChipsRequestParams chipsrequestparams1;
                int l1;
                if (obj2 != null)
                {
                    obj2 = ((Account) (obj2)).name;
                } else
                {
                    obj2 = null;
                }
                if (!TextUtils.isEmpty(((CharSequence) (obj))))
                {
                    k = ((String) (obj)).length();
                } else
                {
                    k = 0;
                }
                obj5 = ((BaseRecipientAdapter) (obj4)).context;
                obj2 = GmsChipsClearcutLogger.getInstance(((Context) (obj5)), ((String) (obj2)));
                obj5 = ((Context) (obj5)).getPackageName();
                j1 = ((Integer)GmsChipsUtil.PACKAGE_NAME_TO_LABEL.getOrDefault(obj5, Integer.valueOf(0))).intValue();
                d2 = ((GmsRecipientAdapter) (obj4)).samplingRate;
                obj4 = new ChipsExtension();
                obj5 = new ChipsDataSourceRequest();
                chipsrequestparams1 = new ChipsRequestParams();
                chipsrequestparams1.queryLength = Integer.valueOf(k);
                chipsrequestparams1.autocompleteType = 1;
                obj5.chipsRequestParams = chipsrequestparams1;
                obj5.label = 1;
                obj4.eventType = 3;
                obj4.clientLabel = j1;
                obj4.oneof_event_ = -1;
                obj4.oneof_event_ = 2;
                obj4.chipsDataSourceRequest = ((ChipsDataSourceRequest) (obj5));
                ((GmsChipsClearcutLogger) (obj2)).logEventWithSampling(((ChipsExtension) (obj4)), d2);
            }
            charsequence = (com.google.android.gms.people.Autocomplete.AutocompleteResult)People.AutocompleteApi.loadAutocompleteList(mClient, charsequence.toString(), ((com.google.android.gms.people.Autocomplete.AutocompleteOptions) (obj3))).await(5L, TimeUnit.SECONDS);
            obj3 = charsequence.getStatus();
            k = ((Status) (obj3)).zzaEP;
            obj2 = charsequence.getAutocompleteEntries();
            if (((Status) (obj3)).zzaEP <= 0)
            {
                k = 1;
            } else
            {
                k = 0;
            }
              goto _L1
_L16:
            if (!gmsChipsLoggingFlags.loggingErrorsEnabled()) goto _L3; else goto _L2
_L2:
            obj4 = GmsRecipientAdapter.this;
            if (((Status) (obj3)).zzaEP == 15)
            {
                k = 4;
            } else
            {
                k = 2;
            }
            charsequence = ((BaseRecipientAdapter) (obj4)).account;
            if (charsequence == null) goto _L5; else goto _L4
_L4:
            charsequence = ((Account) (charsequence)).name;
_L6:
            if (TextUtils.isEmpty(((CharSequence) (obj))))
            {
                break MISSING_BLOCK_LABEL_1313;
            }
            j1 = ((String) (obj)).length();
_L7:
            obj = ((BaseRecipientAdapter) (obj4)).context;
            charsequence = GmsChipsClearcutLogger.getInstance(((Context) (obj)), charsequence);
            obj = ((Context) (obj)).getPackageName();
            l1 = ((Integer)GmsChipsUtil.PACKAGE_NAME_TO_LABEL.getOrDefault(obj, Integer.valueOf(0))).intValue();
            d2 = ((GmsRecipientAdapter) (obj4)).samplingRate;
            obj = new ChipsExtension();
            obj4 = new ChipsResponse();
            obj5 = new ChipsRequestParams();
            obj5.queryLength = Integer.valueOf(j1);
            obj5.autocompleteType = 1;
            obj4.chipsRequestParams = ((ChipsRequestParams) (obj5));
            obj4.numResults = Integer.valueOf(0);
            obj4.latencyUsec = Long.valueOf(0L);
            obj4.status = k;
            obj.eventType = 2;
            obj.clientLabel = l1;
            obj.oneof_event_ = -1;
            obj.oneof_event_ = 1;
            obj.chipsResponse = ((ChipsResponse) (obj4));
            charsequence.logEventWithSampling(((ChipsExtension) (obj)), d2);
_L3:
            charsequence = String.valueOf(obj3);
            obj = ((Status) (obj3)).zzaIk;
            Log.e("chips", (new StringBuilder(String.valueOf(charsequence).length() + 44 + String.valueOf(obj).length())).append("Autocomplete list query failed. status=").append(charsequence).append(" msg=").append(((String) (obj))).toString());
            tempEntries = null;
            if (obj2 != null)
            {
                ((DataBuffer) (obj2)).close();
            }
            return filterresults;
_L5:
            charsequence = null;
              goto _L6
            j1 = 0;
              goto _L7
_L17:
            ArrayList arraylist;
            HashSet hashset;
            Object obj6;
            arraylist = new ArrayList();
            hashset = new HashSet();
            charsequence = photoManager;
            obj6 = ((DataBuffer) (obj2)).iterator();
            k = 0;
_L9:
            if (!((Iterator) (obj6)).hasNext())
            {
                break; /* Loop/switch isn't completed */
            }
            Object obj7 = (AutocompleteEntry)((Iterator) (obj6)).next();
            String s = ((AutocompleteEntry) (obj7)).getItemValue();
            if (hashset.contains(s))
            {
                continue; /* Loop/switch isn't completed */
            }
            hashset.add(s);
            obj7 = new GmsRecipientEntry(android.support.v4.content.ModernAsyncTask.Status.AUTOCOMPLETE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCTMN6BR3D1KN0SPF8TMN6KJ5CDKN0QB5DPQ4ARJKE9SI8KJ5CDKN0QB5DPQ46SJ5C5Q6IRREALPMAGR1EDIJM___0, ((AutocompleteEntry) (obj7)), k);
            arraylist.add(obj7);
            charsequence.populatePhotoBytesAsync(((RecipientEntry) (obj7)), GmsRecipientAdapter.this);
            k++;
            if (true) goto _L9; else goto _L8
_L8:
            if (!gmsChipsLoggingFlags.loggingDataSourceEnabled()) goto _L11; else goto _L10
_L10:
            obj6 = GmsRecipientAdapter.this;
            j1 = arraylist.size();
            charsequence = ((BaseRecipientAdapter) (obj6)).account;
            if (charsequence == null) goto _L13; else goto _L12
_L12:
            charsequence = ((Account) (charsequence)).name;
_L14:
            if (TextUtils.isEmpty(((CharSequence) (obj))))
            {
                break MISSING_BLOCK_LABEL_1764;
            }
            k = ((String) (obj)).length();
_L15:
            Object obj1 = ((BaseRecipientAdapter) (obj6)).context;
            charsequence = GmsChipsClearcutLogger.getInstance(((Context) (obj1)), charsequence);
            obj1 = ((Context) (obj1)).getPackageName();
            int i2 = ((Integer)GmsChipsUtil.PACKAGE_NAME_TO_LABEL.getOrDefault(obj1, Integer.valueOf(0))).intValue();
            double d3 = ((GmsRecipientAdapter) (obj6)).samplingRate;
            obj1 = new ChipsExtension();
            obj6 = new ChipsDataSourceResponse();
            ChipsRequestParams chipsrequestparams2 = new ChipsRequestParams();
            chipsrequestparams2.queryLength = Integer.valueOf(k);
            chipsrequestparams2.autocompleteType = 1;
            obj6.chipsRequestParams = chipsrequestparams2;
            obj6.label = 1;
            obj6.status = 1;
            obj6.numResults = Integer.valueOf(j1);
            obj1.eventType = 4;
            obj1.clientLabel = i2;
            obj1.oneof_event_ = -1;
            obj1.oneof_event_ = 3;
            obj1.chipsDataSourceResponse = ((ChipsDataSourceResponse) (obj6));
            charsequence.logEventWithSampling(((ChipsExtension) (obj1)), d3);
_L11:
            filterresults.values = new GmsFilterResult(arraylist, hashset, searchOtherDirectories(hashset));
            filterresults.count = arraylist.size();
            charsequence = GmsRecipientAdapter.this;
            charsequence = GmsRecipientAdapter.this;
            k = filterresults.count;
            if (((GmsRecipientAdapter) (charsequence)).eventsListenerDispatcher != null)
            {
                ((GmsRecipientAdapter) (charsequence)).eventsListenerDispatcher.resultsReceived(k, true);
            }
            if (obj2 != null)
            {
                ((DataBuffer) (obj2)).close();
            }
            return filterresults;
_L13:
            charsequence = null;
              goto _L14
            k = 0;
              goto _L15
            charsequence;
            if (obj2 != null)
            {
                ((DataBuffer) (obj2)).close();
            }
            throw charsequence;
_L1:
            if (k != 0 && obj2 != null) goto _L17; else goto _L16
        }

        protected final void publishResults(CharSequence charsequence, android.widget.Filter.FilterResults filterresults)
        {
            mCurrentConstraint = charsequence;
            tempEntries = null;
            if (filterresults.values != null)
            {
                filterresults = (GmsFilterResult)filterresults.values;
                mEntryList = ((GmsFilterResult) (filterresults)).entries;
                mExistingDestinations = ((GmsFilterResult) (filterresults)).existingDestinations;
                GmsRecipientAdapter gmsrecipientadapter = GmsRecipientAdapter.this;
                int j = ((GmsFilterResult) (filterresults)).entries.size();
                List list;
                int i;
                if (((GmsFilterResult) (filterresults)).paramsList == null)
                {
                    i = 0;
                } else
                {
                    i = ((GmsFilterResult) (filterresults)).paramsList.size();
                }
                if (j == 0 && i > 1)
                {
                    gmsrecipientadapter.tempEntries = ((BaseRecipientAdapter) (gmsrecipientadapter)).entries;
                }
                gmsrecipientadapter = GmsRecipientAdapter.this;
                list = ((GmsFilterResult) (filterresults)).entries;
                gmsrecipientadapter.entries = list;
                ((BaseRecipientAdapter) (gmsrecipientadapter)).entriesUpdatedObserver.onChanged(list);
                gmsrecipientadapter.notifyDataSetChanged();
                if (((GmsFilterResult) (filterresults)).paramsList != null)
                {
                    i = mPreferredMaxResultCount;
                    int k = ((GmsFilterResult) (filterresults)).existingDestinations.size();
                    startSearchOtherDirectories(charsequence, ((GmsFilterResult) (filterresults)).paramsList, i - k);
                }
                return;
            } else
            {
                charsequence = GmsRecipientAdapter.this;
                filterresults = Collections.emptyList();
                charsequence.entries = filterresults;
                ((BaseRecipientAdapter) (charsequence)).entriesUpdatedObserver.onChanged(filterresults);
                charsequence.notifyDataSetChanged();
                return;
            }
        }

        public GmsFilter()
        {
            this$0 = GmsRecipientAdapter.this;
            super();
        }

        private class GmsFilterResult
        {

            public final List entries;
            public final Set existingDestinations;
            public final List paramsList;

            public GmsFilterResult(List list, Set set, List list1)
            {
                entries = list;
                existingDestinations = set;
                paramsList = list1;
            }
        }

    }

}
