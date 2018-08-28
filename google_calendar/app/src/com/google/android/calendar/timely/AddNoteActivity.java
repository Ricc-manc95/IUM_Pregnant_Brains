// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.google.android.calendar.common.activity.CalendarSupportActivity;

// Referenced classes of package com.google.android.calendar.timely:
//            ProposeTimeAddNoteFragment

public class AddNoteActivity extends CalendarSupportActivity
    implements ProposeTimeAddNoteFragment.Listener
{

    public AddNoteActivity()
    {
    }

    public final void onCancelled$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL0SJFE1NN6PAKD5MMAGB4CH76UT358PP62PRDCLN78EP9AO______0()
    {
        setResult(0, getIntent());
        finish();
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f05001f);
        ProposeTimeAddNoteFragment proposetimeaddnotefragment = (ProposeTimeAddNoteFragment)super.mFragments.mHost.mFragmentManager.findFragmentByTag("add_note_fragment");
        bundle = proposetimeaddnotefragment;
        if (proposetimeaddnotefragment == null)
        {
            int i = getIntent().getIntExtra("color", 0);
            bundle = new ProposeTimeAddNoteFragment();
            Bundle bundle1 = new Bundle(2);
            bundle1.putInt("event_color", i);
            bundle1.putString("note", null);
            bundle.setArguments(bundle1);
            super.mFragments.mHost.mFragmentManager.beginTransaction().add(0x7f10010a, bundle, "add_note_fragment").commit();
        }
        bundle.listener = this;
    }

    public final void onNoteAdded$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL0SJFE1NN6PAKD5MMAGB4CH76UT358PP62PRDCLN78EQA9966KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0(String s)
    {
        Intent intent = getIntent();
        intent.putExtra("note", s);
        setResult(-1, intent);
        finish();
    }
}
