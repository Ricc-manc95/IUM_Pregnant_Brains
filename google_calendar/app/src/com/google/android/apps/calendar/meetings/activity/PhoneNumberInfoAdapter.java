// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.meetings.activity;

import android.accounts.Account;
import android.content.res.Resources;
import android.support.v4.text.BidiFormatter;
import android.support.v4.text.TextDirectionHeuristicsCompat;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementAttacher;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementHolder;
import com.google.android.calendar.event.conference.Availability;
import com.google.android.calendar.event.conference.PhoneNumberDetails;
import com.google.common.base.Platform;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// Referenced classes of package com.google.android.apps.calendar.meetings.activity:
//            PhoneClickListener

final class PhoneNumberInfoAdapter extends android.support.v7.widget.RecyclerView.Adapter
{

    private final Account account;
    private final PhoneClickListener phoneClickListener;
    public final List phoneList = new ArrayList(0);

    PhoneNumberInfoAdapter(PhoneClickListener phoneclicklistener, Account account1)
    {
        phoneClickListener = phoneclicklistener;
        account = account1;
    }

    public final int getItemCount()
    {
        return phoneList.size();
    }

    public final void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder viewholder, int i)
    {
        PhoneNumberHolder phonenumberholder = (PhoneNumberHolder)viewholder;
        phonenumberholder.phone = (PhoneNumberDetails)phoneList.get(i);
        viewholder = phonenumberholder.phone;
        Locale locale = phonenumberholder.phone.region();
        if (Platform.stringIsNullOrEmpty(viewholder.phoneNumber()))
        {
            viewholder = null;
        } else
        {
            viewholder = BidiFormatter.getInstance(locale).unicodeWrap(PhoneNumberUtils.formatNumber(viewholder.phoneNumber(), locale.getCountry()), TextDirectionHeuristicsCompat.LTR);
        }
        phonenumberholder.phoneTextView.setText(viewholder);
        if (Platform.stringIsNullOrEmpty(phonenumberholder.phone.region().getCountry()))
        {
            phonenumberholder.countryTextView.setText(null);
        } else
        {
            viewholder = phonenumberholder.phone.region().getDisplayCountry();
            phonenumberholder.countryTextView.setText(phonenumberholder.resources.getString(0x7f13012c, new Object[] {
                viewholder, phonenumberholder.phone.region().getCountry()
            }));
            phonenumberholder.countryTextView.setContentDescription(viewholder);
        }
        viewholder = phonenumberholder.confidentialNumberView;
        if (phonenumberholder.phone.availability() == Availability.PUBLIC)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            i = 8;
        } else
        {
            i = 0;
        }
        viewholder.setVisibility(i);
    }

    public final android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewgroup, int i)
    {
        viewgroup = LayoutInflater.from(viewgroup.getContext()).inflate(0x7f05010c, viewgroup, false);
        Object obj = VisualElementHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
        } else
        {
            ((VisualElementAttacher)obj).attachPhoneNumberItem(viewgroup);
            obj = new PhoneNumberHolder(viewgroup, phoneClickListener, account);
            viewgroup.setOnClickListener(((android.view.View.OnClickListener) (obj)));
            return ((android.support.v7.widget.RecyclerView.ViewHolder) (obj));
        }
    }

    private class PhoneNumberHolder extends android.support.v7.widget.RecyclerView.ViewHolder
        implements android.view.View.OnClickListener
    {

        private final Account account;
        public final View confidentialNumberView;
        public final TextView countryTextView;
        public PhoneNumberDetails phone;
        private final PhoneClickListener phoneClickListener;
        public final TextView phoneTextView;
        public final Resources resources;

        public final void onClick(View view)
        {
            if (phoneClickListener != null)
            {
                phoneClickListener.onDial(phone);
                VisualElementAttacher visualelementattacher = VisualElementHolder.instance;
                if (visualelementattacher == null)
                {
                    throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
                }
                ((VisualElementAttacher)visualelementattacher).recordTap(view.getContext(), view, account);
            }
        }

        PhoneNumberHolder(View view, PhoneClickListener phoneclicklistener, Account account1)
        {
            super(view);
            resources = view.getResources();
            countryTextView = (TextView)view.findViewById(0x7f1002d5);
            phoneTextView = (TextView)view.findViewById(0x7f1002d7);
            confidentialNumberView = view.findViewById(0x7f1002d6);
            phoneClickListener = phoneclicklistener;
            account = account1;
        }
    }

}
