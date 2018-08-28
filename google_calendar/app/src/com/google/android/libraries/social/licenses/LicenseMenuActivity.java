// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.social.licenses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

// Referenced classes of package com.google.android.libraries.social.licenses:
//            LicenseMenuFragment, LicenseActivity, License

public final class LicenseMenuActivity extends AppCompatActivity
    implements LicenseMenuFragment.LicenseSelectionListener
{

    public LicenseMenuActivity()
    {
    }

    protected final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f0500a0);
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        bundle = super.mFragments.mHost.mFragmentManager;
        if (!(bundle.findFragmentById(0x7f10022a) instanceof LicenseMenuFragment))
        {
            LicenseMenuFragment licensemenufragment = new LicenseMenuFragment();
            if (getIntent().hasExtra("pluginLicensePaths"))
            {
                licensemenufragment.setArguments(getIntent().getBundleExtra("pluginLicensePaths"));
            }
            bundle.beginTransaction().add(0x7f10022a, licensemenufragment).commitNow();
        }
    }

    public final void onLicenseSelected(License license)
    {
        Intent intent = new Intent(this, com/google/android/libraries/social/licenses/LicenseActivity);
        intent.putExtra("license", license);
        startActivity(intent);
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
}
