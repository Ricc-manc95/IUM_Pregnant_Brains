// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.people;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

public interface Autocomplete
{

    public abstract PendingResult loadAutocompleteList(GoogleApiClient googleapiclient, String s, AutocompleteOptions autocompleteoptions);
}