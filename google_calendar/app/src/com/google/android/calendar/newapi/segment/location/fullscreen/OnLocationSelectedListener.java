// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.location.fullscreen;


interface OnLocationSelectedListener
{

    public abstract void onContactSelected(ContactLocationViewHolder.Contact contact);

    public abstract void onSuggestionSelected(SuggestionLocationViewHolder.Suggestion suggestion);
}
