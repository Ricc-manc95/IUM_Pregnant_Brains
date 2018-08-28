// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee.fullscreen;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Filter;
import com.android.ex.chips.BaseRecipientAdapter;
import com.google.android.calendar.avatar.ContactInfo;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.android.calendar.newapi.segment.attendee.fullscreen:
//            AttendeeContact, AutoValue_AttendeeContact, ContactList, EmailInviteViewHolder, 
//            AttendeeSuggestionAdapter, AttendeeSuggestionFetcher

final class AttendeeFullScreenPresenter
    implements AttendeeSuggestionFetcher.Listener, AttendeeViewHolder.Listener, com.google.android.calendar.newapi.segment.common.fullscreen.SearchBoxPresenter.Listener
{

    private final Account account;
    private final AttendeeSuggestionAdapter adapter;
    private final ContactList contactList;
    private final Context context;
    private final AttendeeSuggestionFetcher fetcher;
    public Listener listener;
    private List pendingQueries;
    public final EditText searchBox;

    AttendeeFullScreenPresenter(Context context1, Account account1, EditText edittext, ContactList contactlist, AttendeeSuggestionFetcher attendeesuggestionfetcher, AttendeeSuggestionAdapter attendeesuggestionadapter)
    {
        pendingQueries = new ArrayList();
        context = context1;
        account = account1;
        searchBox = edittext;
        contactList = contactlist;
        fetcher = attendeesuggestionfetcher;
        adapter = attendeesuggestionadapter;
    }

    private final void finish()
    {
        EditText edittext = searchBox;
        ((InputMethodManager)edittext.getContext().getSystemService("input_method")).hideSoftInputFromWindow(edittext.getWindowToken(), 0);
        if (listener != null)
        {
            listener.onEditingFinished();
        }
    }

    public final void onContactSelected(AttendeeContact attendeecontact)
    {
        attendeecontact.getType().ordinal();
        JVM INSTR tableswitch 0 1: default 28
    //                   0 73
    //                   1 123;
           goto _L1 _L2 _L3
_L1:
        attendeecontact = String.valueOf(attendeecontact.getType());
        throw new IllegalArgumentException((new StringBuilder(String.valueOf(attendeecontact).length() + 33)).append("Unexpected AttendeeContact type: ").append(attendeecontact).toString());
_L2:
        ContactList contactlist = contactList;
        AttendeeContact.Type type = AttendeeContact.Type.ADDED_REMOVABLE;
        attendeecontact = new AutoValue_AttendeeContact(attendeecontact.getContact(), type);
        contactlist.contacts.put(attendeecontact.getContact().primaryEmail, attendeecontact);
_L5:
        searchBox.setText("");
        return;
_L3:
        if (contactList.contacts.remove(attendeecontact.getContact().primaryEmail) == null);
        if (true) goto _L5; else goto _L4
_L4:
    }

    public final void onDonePressed()
    {
        finish();
    }

    public final void onEnterPressed()
    {
        Object obj = searchBox.getText();
        if (TextUtils.isEmpty(((CharSequence) (obj))))
        {
            obj = searchBox;
            ((InputMethodManager)((View) (obj)).getContext().getSystemService("input_method")).hideSoftInputFromWindow(((View) (obj)).getWindowToken(), 0);
            if (listener != null)
            {
                listener.onEditingFinished();
            }
        } else
        if (Patterns.EMAIL_ADDRESS.matcher(((CharSequence) (obj))).matches())
        {
            EditText edittext = searchBox;
            ((InputMethodManager)edittext.getContext().getSystemService("input_method")).hideSoftInputFromWindow(edittext.getWindowToken(), 0);
            contactList.addEmail(((CharSequence) (obj)));
            searchBox.setText("");
            return;
        }
    }

    public final void onSuggestionsFetched(List list)
    {
        boolean flag = false;
        if (pendingQueries.isEmpty())
        {
            if (!TextUtils.isEmpty(searchBox.getText().toString()))
            {
                flag = true;
            }
        } else
        {
            CharSequence charsequence = (CharSequence)pendingQueries.remove(0);
            flag = TextUtils.equals(searchBox.getText().toString(), charsequence);
        }
        if (!flag)
        {
            return;
        }
        Object obj = searchBox.getText().toString();
        if (Patterns.EMAIL_ADDRESS.matcher(((CharSequence) (obj))).matches() && list.isEmpty())
        {
            list = new AutoValue_AttendeeContact(EmailInviteViewHolder.createEmailInvite(account, searchBox.getText().toString()), AttendeeContact.Type.SUGGESTION);
            obj = adapter;
            obj.items = Collections.singletonList(list);
            ((android.support.v7.widget.RecyclerView.Adapter) (obj)).mObservable.notifyChanged();
            return;
        }
        obj = ImmutableSet.copyOf(contactList.contacts.keySet());
        class .Lambda._cls0
            implements Predicate
        {

            private final Set arg$1;

            public final boolean apply(Object obj1)
            {
                return !arg$1.contains(((AttendeeContact)obj1).getContact().primaryEmail);
            }

            .Lambda._cls0(Set set)
            {
                arg$1 = set;
            }
        }

        if (list instanceof FluentIterable)
        {
            list = (FluentIterable)list;
        } else
        {
            list = new com.google.common.collect.FluentIterable._cls1(list, list);
        }
        obj = new .Lambda._cls0(((Set) (obj)));
        list = (Iterable)((FluentIterable) (list)).iterableDelegate.or(list);
        if (list == null)
        {
            throw new NullPointerException();
        }
        if (obj == null)
        {
            throw new NullPointerException();
        }
        list = new com.google.common.collect.Iterables._cls4(list, ((Predicate) (obj)));
        if (list instanceof FluentIterable)
        {
            list = (FluentIterable)list;
        } else
        {
            list = new com.google.common.collect.FluentIterable._cls1(list, list);
        }
        list = ImmutableList.copyOf((Iterable)((FluentIterable) (list)).iterableDelegate.or(list));
        obj = adapter;
        obj.items = list;
        ((android.support.v7.widget.RecyclerView.Adapter) (obj)).mObservable.notifyChanged();
    }

    public final void onTextChanged(CharSequence charsequence)
    {
        query(charsequence);
    }

    final void query(CharSequence charsequence)
    {
        if (TextUtils.isEmpty(charsequence))
        {
            Object obj = new ArrayList(contactList.contacts.values());
            charsequence = new ArrayList();
            if (!((List) (obj)).isEmpty())
            {
                charsequence.addAll(((java.util.Collection) (obj)));
                charsequence.add(context.getString(0x7f1300a1));
                Collections.reverse(charsequence);
            }
            obj = adapter;
            obj.items = charsequence;
            ((android.support.v7.widget.RecyclerView.Adapter) (obj)).mObservable.notifyChanged();
        } else
        {
            if (charsequence.toString().endsWith(","))
            {
                CharSequence charsequence1 = charsequence.subSequence(0, charsequence.length() - 1);
                if (Patterns.EMAIL_ADDRESS.matcher(charsequence1).matches())
                {
                    contactList.addEmail(charsequence1);
                    searchBox.setText("");
                    return;
                }
            }
            pendingQueries.add(charsequence.toString());
            AttendeeSuggestionFetcher attendeesuggestionfetcher = fetcher;
            if (attendeesuggestionfetcher.hasContactsPermissions)
            {
                attendeesuggestionfetcher.contactFetcher.getFilter().filter(charsequence);
                return;
            }
        }
    }

    private class Listener
    {

        public abstract void onEditingFinished();
    }

}
