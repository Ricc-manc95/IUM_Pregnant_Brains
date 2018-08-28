// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.drive;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public final class PotentialFix
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final List allowedRoles;
    public final String domainDisplayName;
    public final List fixableFileIds;
    public final List fixableRecipientEmailAddresses;
    public final boolean fixesEverything;
    public final String optionType;
    public final List outOfDomainWarningEmailAddresses;

    public PotentialFix(Parcel parcel)
    {
        optionType = parcel.readString();
        fixableRecipientEmailAddresses = new ArrayList();
        parcel.readStringList(fixableRecipientEmailAddresses);
        fixableFileIds = new ArrayList();
        parcel.readStringList(fixableFileIds);
        allowedRoles = new ArrayList();
        parcel.readStringList(allowedRoles);
        boolean flag;
        if (parcel.readInt() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        fixesEverything = flag;
        domainDisplayName = parcel.readString();
        outOfDomainWarningEmailAddresses = new ArrayList();
        parcel.readStringList(outOfDomainWarningEmailAddresses);
    }

    public PotentialFix(com.google.api.services.drive.model.CheckPermissionsResponse.FixOptions fixoptions)
    {
        Object obj1 = null;
        super();
        optionType = fixoptions.optionType;
        fixableRecipientEmailAddresses = fixoptions.fixableRecipientEmailAddresses;
        fixableFileIds = fixoptions.fixableFileIds;
        fixesEverything = fixoptions.fixesEverything.booleanValue();
        Object obj = fixoptions.allowedRoles;
        allowedRoles = new ArrayList();
        if (((List) (obj)).contains("READER"))
        {
            allowedRoles.add("READER");
        }
        if (((List) (obj)).contains("COMMENTER"))
        {
            allowedRoles.add("COMMENTER");
        }
        if (((List) (obj)).contains("WRITER"))
        {
            allowedRoles.add("WRITER");
        }
        obj = fixoptions.increaseDomainVisibilityInfo;
        if (obj != null)
        {
            obj = ((com.google.api.services.drive.model.CheckPermissionsResponse.FixOptions.IncreaseDomainVisibilityInfo) (obj)).domainDisplayName;
        } else
        {
            obj = null;
        }
        domainDisplayName = ((String) (obj));
        obj = fixoptions.addCollaboratorsInfo;
        fixoptions = obj1;
        if (obj != null)
        {
            fixoptions = ((com.google.api.services.drive.model.CheckPermissionsResponse.FixOptions.AddCollaboratorsInfo) (obj)).outOfDomainWarningEmailAddresses;
        }
        outOfDomainWarningEmailAddresses = fixoptions;
    }

    public static boolean isSupportedFixOption(String s)
    {
        return "ADD_COLLABORATORS".equals(s) || "INCREASE_PUBLIC_VISIBILITY".equals(s) || "INCREASE_DOMAIN_VISIBILITY".equals(s);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(optionType);
        parcel.writeStringList(fixableRecipientEmailAddresses);
        parcel.writeStringList(fixableFileIds);
        parcel.writeStringList(allowedRoles);
        if (fixesEverything)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        parcel.writeString(domainDisplayName);
        parcel.writeStringList(outOfDomainWarningEmailAddresses);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new PotentialFix(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new PotentialFix[i];
        }

        _cls1()
        {
        }
    }

}
