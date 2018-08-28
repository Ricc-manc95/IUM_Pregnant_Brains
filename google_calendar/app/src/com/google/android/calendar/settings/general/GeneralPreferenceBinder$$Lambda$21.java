// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.Preference;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.base.Supplier;

// Referenced classes of package com.google.android.calendar.settings.general:
//            GeneralPreferenceBinder

final class arg._cls3
    implements android.support.v7.preference.er
{

    private final Consumer arg$1;
    private final SwitchPreference arg$2;
    private final Supplier arg$3;

    public final boolean onPreferenceChange(Preference preference, Object obj)
    {
        return GeneralPreferenceBinder.lambda$bindSwitchPreference$11$GeneralPreferenceBinder$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFELQ6IR1FCPQMSORKD5NMSBQ3DTN76TBDCLP3MJ31DPI74RR9CGNN6TBGE1NN4T1FEOOJ8BRGE9IMCPBICLN66P9FADRMIT33D1874PB6CLP6ARJ3CKTKOORFDKNMERRFCTM6ABR3DTMMQRRE5TH62SR55T9NAS3GDHKMASHR9HGMSP3IDTKM8BRJELO70RRIEGNNCDPFE1P6APJ5E9IMSOR55T874PB6CLP6ARJ3CKTKOQJ1EPGIUR31DPJIUJR2D9IM6T1R55D0____0(arg$1, arg$2, arg$3, obj);
    }

    (Consumer consumer, SwitchPreference switchpreference, Supplier supplier)
    {
        arg$1 = consumer;
        arg$2 = switchpreference;
        arg$3 = supplier;
    }
}
