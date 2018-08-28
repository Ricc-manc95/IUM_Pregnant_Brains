// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.social.licenses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;

// Referenced classes of package com.google.android.libraries.social.licenses:
//            License, Licenses

public final class LicenseActivity extends AppCompatActivity
{

    public LicenseActivity()
    {
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f05009f);
        bundle = (License)getIntent().getParcelableExtra("license");
        getSupportActionBar().setTitle(((License) (bundle)).libraryName);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setLogo(null);
        TextView textview = (TextView)findViewById(0x7f100229);
        long l = ((License) (bundle)).licenseOffset;
        int i = ((License) (bundle)).licenseLength;
        String s1 = ((License) (bundle)).path;
        if (s1.isEmpty())
        {
            bundle = Licenses.getTextFromResource(this, "third_party_licenses", l, i);
        } else
        {
            String s = Licenses.getTextFromJar("res/raw/third_party_licenses", s1, l, i);
            bundle = s;
            if (s == null)
            {
                throw new RuntimeException((new StringBuilder(String.valueOf(s1).length() + 46)).append(s1).append(" does not contain res/raw/").append("third_party_licenses").toString());
            }
        }
        if (bundle == null)
        {
            finish();
            return;
        } else
        {
            textview.setText(bundle);
            return;
        }
    }

    public final boolean onOptionsItemSelected(MenuItem menuitem)
    {
        if (menuitem.getItemId() == 0x102002c)
        {
            finish();
            return true;
        } else
        {
            return super.onOptionsItemSelected(menuitem);
        }
    }

    public final void onRestoreInstanceState(Bundle bundle)
    {
        super.onRestoreInstanceState(bundle);
        ScrollView scrollview = (ScrollView)findViewById(0x7f100228);
        scrollview.post(new _cls1());
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        ScrollView scrollview = (ScrollView)findViewById(0x7f100228);
        TextView textview = (TextView)findViewById(0x7f100229);
        int i = textview.getLayout().getLineForVertical(scrollview.getScrollY());
        bundle.putInt("scroll_pos", textview.getLayout().getLineStart(i));
    }

    private class _cls1
        implements Runnable
    {

        private final LicenseActivity this$0;
        private final int val$firstVisibleChar;
        private final ScrollView val$scrollView;

        public final void run()
        {
            TextView textview = (TextView)findViewById(0x7f100229);
            int i = textview.getLayout().getLineForOffset(firstVisibleChar);
            i = textview.getLayout().getLineTop(i);
            scrollView.scrollTo(0, i);
        }

        _cls1()
        {
            this$0 = LicenseActivity.this;
            firstVisibleChar = i;
            scrollView = scrollview;
            super();
        }
    }

}
