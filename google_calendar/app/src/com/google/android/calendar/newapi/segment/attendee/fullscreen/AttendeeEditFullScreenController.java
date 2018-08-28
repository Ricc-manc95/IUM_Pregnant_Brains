// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee.fullscreen;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import com.android.ex.chips.BaseRecipientAdapter;
import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.avatar.ContactInfo;
import com.google.android.calendar.avatar.RecipientAdapterFactory;
import com.google.android.calendar.common.view.NinjaEditText;
import com.google.android.calendar.newapi.segment.attendee.AttendeesResult;
import com.google.android.calendar.newapi.segment.common.fullscreen.EditFullScreenController;
import com.google.android.calendar.newapi.segment.common.fullscreen.SearchBoxPresenter;
import com.google.android.calendar.newapi.segment.common.fullscreen.SearchFullScreenViewHolder;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.newapi.segment.attendee.fullscreen:
//            AttendeeSuggestionAdapter, AttendeeSuggestionFetcher, AttendeeFullScreenPresenter, ContactList, 
//            AutoValue_AttendeeContact, AttendeeContact

public final class AttendeeEditFullScreenController extends EditFullScreenController
    implements AttendeeFullScreenPresenter.Listener
{

    public boolean canAttendeesAddAttendees;
    private boolean canModifyCanAttendeesAddAttendees;
    private ContactList contactList;
    private AttendeeFullScreenPresenter presenter;

    public AttendeeEditFullScreenController()
    {
    }

    static final AttendeeDescriptor lambda$contactListFromArguments$0$AttendeeEditFullScreenController(Attendee attendee)
    {
        return attendee.attendeeDescriptor;
    }

    public static AttendeeEditFullScreenController newInstance(List list, List list1, boolean flag, boolean flag1, Account account, int i)
    {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ARG_ATTENDEE_LIST", new ArrayList(list));
        if (list1 != null)
        {
            list = new ArrayList(list1);
        } else
        {
            list = new ArrayList();
        }
        bundle.putParcelableArrayList("ARG_NON_REMOVABLE_ATTENDEE_LIST", list);
        bundle.putBoolean("ARG_CAN_MODIFY_CAN_ATTENDEES_ADD_ATTENDEES", flag);
        bundle.putBoolean("ARG_CAN_ATTENDEES_ADD_ATTENDEES", flag1);
        bundle.putParcelable("ARG_ACCOUNT", account);
        bundle.putInt("ARG_HEADER_COLOR", i);
        list = new AttendeeEditFullScreenController();
        list.setArguments(bundle);
        return list;
    }

    public final View createView(Context context)
    {
        int i = getArguments().getInt("ARG_HEADER_COLOR");
        Object obj = (Account)getArguments().getParcelable("ARG_ACCOUNT");
        SearchFullScreenViewHolder searchfullscreenviewholder = new SearchFullScreenViewHolder(context);
        Object obj1 = searchfullscreenviewholder.toolbar;
        class .Lambda._cls1
            implements android.view.View.OnClickListener
        {

            private final AttendeeEditFullScreenController arg$1;

            public final void onClick(View view)
            {
                arg$1.onNavigateAway();
            }

            .Lambda._cls1()
            {
                arg$1 = AttendeeEditFullScreenController.this;
            }
        }

        Object obj2 = new .Lambda._cls1();
        ((Toolbar) (obj1)).ensureNavButtonView();
        ((Toolbar) (obj1)).mNavButtonView.setOnClickListener(((android.view.View.OnClickListener) (obj2)));
        ContactList contactlist = contactList;
        searchfullscreenviewholder.searchBox.setHint(0x7f130091);
        searchfullscreenviewholder.searchBox.setImeActionLabel(context.getString(0x7f130083), 6);
        searchfullscreenviewholder.searchBox.setImeOptions(0x2000006);
        if (i != -1)
        {
            searchfullscreenviewholder.toolbar.setBackgroundColor(i);
        }
        obj1 = new AttendeeSuggestionAdapter(context);
        searchfullscreenviewholder.suggestionList.setAdapter(((android.support.v7.widget.RecyclerView.Adapter) (obj1)));
        BaseRecipientAdapter baserecipientadapter = RecipientAdapterFactory.create(context);
        obj2 = new AttendeeSuggestionFetcher(baserecipientadapter);
        baserecipientadapter.account = ((Account) (obj));
        obj2.getClass();
        baserecipientadapter.entriesUpdatedObserver = new AttendeeSuggestionFetcher..Lambda._cls0(((AttendeeSuggestionFetcher) (obj2)));
        obj = new AttendeeFullScreenPresenter(context, ((Account) (obj)), searchfullscreenviewholder.searchBox, contactlist, ((AttendeeSuggestionFetcher) (obj2)), ((AttendeeSuggestionAdapter) (obj1)));
        obj2.listener = ((AttendeeSuggestionFetcher.Listener) (obj));
        obj1.listener = ((AttendeeViewHolder.Listener) (obj));
        SearchBoxPresenter.create(searchfullscreenviewholder.searchBox, searchfullscreenviewholder.clearButton, searchfullscreenviewholder.doneButton, ((com.google.android.calendar.newapi.segment.common.fullscreen.SearchBoxPresenter.Listener) (obj)));
        presenter = ((AttendeeFullScreenPresenter) (obj));
        if (canModifyCanAttendeesAddAttendees)
        {
            context = (Switch)((TileView) ((TextTileView)LayoutInflater.from(context).inflate(0x7f0500e6, searchfullscreenviewholder.container).findViewById(0x7f100296))).rightActionView;
            context.setChecked(canAttendeesAddAttendees);
            class .Lambda._cls2
                implements android.widget.CompoundButton.OnCheckedChangeListener
            {

                private final AttendeeEditFullScreenController arg$1;

                public final void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
                {
                    arg$1.canAttendeesAddAttendees = flag;
                }

            .Lambda._cls2()
            {
                arg$1 = AttendeeEditFullScreenController.this;
            }
            }

            context.setOnCheckedChangeListener(new .Lambda._cls2());
        }
        return searchfullscreenviewholder.view;
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (bundle == null)
        {
            Bundle bundle1 = getArguments();
            Account account = (Account)bundle1.getParcelable("ARG_ACCOUNT");
            ArrayList arraylist = bundle1.getParcelableArrayList("ARG_ATTENDEE_LIST");
            bundle = bundle1.getParcelableArrayList("ARG_NON_REMOVABLE_ATTENDEE_LIST");
            class .Lambda._cls0
                implements Function
            {

                public static final Function $instance = new .Lambda._cls0();

                public final Object apply(Object obj2)
                {
                    return AttendeeEditFullScreenController.lambda$contactListFromArguments$0$AttendeeEditFullScreenController((Attendee)obj2);
                }


            private .Lambda._cls0()
            {
            }
            }

            Object obj1;
            if (bundle instanceof FluentIterable)
            {
                bundle = (FluentIterable)bundle;
            } else
            {
                bundle = new com.google.common.collect.FluentIterable._cls1(bundle, bundle);
            }
            obj1 = .Lambda._cls0..instance;
            bundle = (Iterable)((FluentIterable) (bundle)).iterableDelegate.or(bundle);
            if (bundle == null)
            {
                throw new NullPointerException();
            }
            if (obj1 == null)
            {
                throw new NullPointerException();
            }
            bundle = new com.google.common.collect.Iterables._cls5(bundle, ((Function) (obj1)));
            ArrayList arraylist1;
            int i;
            int j;
            if (bundle instanceof FluentIterable)
            {
                bundle = (FluentIterable)bundle;
            } else
            {
                bundle = new com.google.common.collect.FluentIterable._cls1(bundle, bundle);
            }
            bundle = (Iterable)((FluentIterable) (bundle)).iterableDelegate.or(bundle);
            if (bundle instanceof Collection)
            {
                bundle = ImmutableSet.copyOf((Collection)bundle);
            } else
            {
                bundle = bundle.iterator();
                if (!bundle.hasNext())
                {
                    bundle = RegularImmutableSet.EMPTY;
                } else
                {
                    obj1 = bundle.next();
                    if (!bundle.hasNext())
                    {
                        bundle = new SingletonImmutableSet(obj1);
                    } else
                    {
                        bundle = ((com.google.common.collect.ImmutableSet.Builder)((com.google.common.collect.ImmutableSet.Builder)(new com.google.common.collect.ImmutableSet.Builder()).add(obj1)).addAll(bundle)).build();
                    }
                }
            }
            obj1 = new ContactList((Account)bundle1.getParcelable("ARG_ACCOUNT"));
            arraylist1 = (ArrayList)arraylist;
            j = arraylist1.size();
            i = 0;
            while (i < j) 
            {
                Object obj = (Attendee)arraylist1.get(i);
                if (AttendeeUtils.isPerson(((Attendee) (obj))))
                {
                    ContactInfo contactinfo;
                    boolean flag;
                    if (!bundle.contains(((Attendee) (obj)).attendeeDescriptor))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    contactinfo = ContactList.attendeeToContact(((Attendee) (obj)), account);
                    if (flag)
                    {
                        obj = AttendeeContact.Type.ADDED_REMOVABLE;
                    } else
                    {
                        obj = AttendeeContact.Type.ADDED_FINAL;
                    }
                    obj = new AutoValue_AttendeeContact(contactinfo, ((AttendeeContact.Type) (obj)));
                    ((ContactList) (obj1)).contacts.put(((AttendeeContact) (obj)).getContact().primaryEmail, obj);
                }
                i++;
            }
            contactList = ((ContactList) (obj1));
            canModifyCanAttendeesAddAttendees = bundle1.getBoolean("ARG_CAN_MODIFY_CAN_ATTENDEES_ADD_ATTENDEES");
            canAttendeesAddAttendees = bundle1.getBoolean("ARG_CAN_ATTENDEES_ADD_ATTENDEES");
            return;
        } else
        {
            contactList = (ContactList)bundle.getParcelable("INSTANCE_CONTACT_LIST");
            canModifyCanAttendeesAddAttendees = bundle.getBoolean("ARG_CAN_MODIFY_CAN_ATTENDEES_ADD_ATTENDEES");
            canAttendeesAddAttendees = bundle.getBoolean("ARG_CAN_ATTENDEES_ADD_ATTENDEES");
            return;
        }
    }

    public final void onDestroy()
    {
        Object obj = contactList.contacts.values();
        Function function;
        if (obj instanceof FluentIterable)
        {
            obj = (FluentIterable)obj;
        } else
        {
            obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
        }
        function = ContactList..Lambda._cls0.$instance;
        obj = (Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj);
        if (obj == null)
        {
            throw new NullPointerException();
        }
        if (function == null)
        {
            throw new NullPointerException();
        }
        obj = new com.google.common.collect.Iterables._cls5(((Iterable) (obj)), function);
        if (obj instanceof FluentIterable)
        {
            obj = (FluentIterable)obj;
        } else
        {
            obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
        }
        obj = new AttendeesResult(ImmutableList.copyOf((Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj)), canAttendeesAddAttendees);
        ((com.google.android.calendar.newapi.segment.common.fullscreen.EditFullScreenController.OnFullScreenResultListener)super.mTarget).onFullScreenResult(obj);
        super.onDestroy();
    }

    public final void onEditingFinished()
    {
        if (this == null) goto _L2; else goto _L1
_L1:
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = super.mFragmentManager;
        if (this == null) goto _L4; else goto _L3
_L3:
        boolean flag;
        if (super.mHost != null && super.mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L5; else goto _L4
_L4:
        flag = false;
_L7:
        if (flag)
        {
            super.mFragmentManager.beginTransaction().remove(this).commitAllowingStateLoss();
        }
        return;
_L5:
        FragmentActivity fragmentactivity;
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        if (fragmentactivity == null || fragmentactivity.isDestroyed() || fragmentactivity.isFinishing())
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if (fragmentmanagerimpl != null && !fragmentmanagerimpl.isDestroyed())
        {
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
_L2:
        flag = false;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final boolean onNavigateAway()
    {
        EditText edittext = presenter.searchBox;
        ((InputMethodManager)edittext.getContext().getSystemService("input_method")).hideSoftInputFromWindow(edittext.getWindowToken(), 0);
        if (this == null) goto _L2; else goto _L1
_L1:
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = super.mFragmentManager;
        if (this == null) goto _L4; else goto _L3
_L3:
        boolean flag;
        if (super.mHost != null && super.mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L5; else goto _L4
_L4:
        flag = false;
_L7:
        if (flag)
        {
            super.mFragmentManager.beginTransaction().remove(this).commitAllowingStateLoss();
        }
        return true;
_L5:
        FragmentActivity fragmentactivity;
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        if (fragmentactivity == null || fragmentactivity.isDestroyed() || fragmentactivity.isFinishing())
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if (fragmentmanagerimpl != null && !fragmentmanagerimpl.isDestroyed())
        {
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
_L2:
        flag = false;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final void onResume()
    {
        super.onResume();
        EditText edittext = presenter.searchBox;
        edittext.getClass();
        edittext.post(new com.google.android.calendar.newapi.common.Keyboard..Lambda._cls0(edittext));
        edittext.setOnFocusChangeListener(com.google.android.calendar.newapi.common.Keyboard..Lambda._cls1.$instance);
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        bundle.putParcelable("INSTANCE_CONTACT_LIST", contactList);
        bundle.putBoolean("ARG_CAN_MODIFY_CAN_ATTENDEES_ADD_ATTENDEES", canModifyCanAttendeesAddAttendees);
        bundle.putBoolean("ARG_CAN_ATTENDEES_ADD_ATTENDEES", canAttendeesAddAttendees);
        super.onSaveInstanceState(bundle);
    }

    public final void onStart()
    {
        super.onStart();
        presenter.listener = this;
    }

    public final void onStop()
    {
        presenter.listener = null;
        super.onStop();
    }

    public final void onViewStateRestored(Bundle bundle)
    {
        super.onViewStateRestored(bundle);
        bundle = presenter;
        bundle.query(((AttendeeFullScreenPresenter) (bundle)).searchBox.getText());
    }
}
