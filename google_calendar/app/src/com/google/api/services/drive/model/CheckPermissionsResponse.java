// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.drive.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Data;
import com.google.api.client.util.GenericData;
import java.util.List;

public final class CheckPermissionsResponse extends GenericJson
{
    public static final class FixOptions extends GenericJson
    {

        public AddCollaboratorsInfo addCollaboratorsInfo;
        public List allowedRoles;
        public List fixableFileIds;
        public List fixableRecipientEmailAddresses;
        public Boolean fixesEverything;
        public IncreaseDomainVisibilityInfo increaseDomainVisibilityInfo;
        public String optionType;

        public final volatile GenericJson clone()
        {
            return (FixOptions)clone();
        }

        public final volatile GenericData clone()
        {
            return (FixOptions)clone();
        }

        public final Object clone()
            throws CloneNotSupportedException
        {
            return (FixOptions)super.clone();
        }

        public final volatile GenericJson set(String s, Object obj)
        {
            return (FixOptions)set(s, obj);
        }

        public final GenericData set(String s, Object obj)
        {
            return (FixOptions)super.set(s, obj);
        }

        public FixOptions()
        {
        }
    }

    public static final class FixOptions.AddCollaboratorsInfo extends GenericJson
    {

        public List outOfDomainWarningEmailAddresses;

        public final volatile GenericJson clone()
        {
            return (FixOptions.AddCollaboratorsInfo)clone();
        }

        public final volatile GenericData clone()
        {
            return (FixOptions.AddCollaboratorsInfo)clone();
        }

        public final Object clone()
            throws CloneNotSupportedException
        {
            return (FixOptions.AddCollaboratorsInfo)super.clone();
        }

        public final volatile GenericJson set(String s, Object obj)
        {
            return (FixOptions.AddCollaboratorsInfo)set(s, obj);
        }

        public final GenericData set(String s, Object obj)
        {
            return (FixOptions.AddCollaboratorsInfo)super.set(s, obj);
        }

        public FixOptions.AddCollaboratorsInfo()
        {
        }
    }

    public static final class FixOptions.IncreaseDomainVisibilityInfo extends GenericJson
    {

        public String domainDisplayName;
        public String domainName;

        public final volatile GenericJson clone()
        {
            return (FixOptions.IncreaseDomainVisibilityInfo)clone();
        }

        public final volatile GenericData clone()
        {
            return (FixOptions.IncreaseDomainVisibilityInfo)clone();
        }

        public final Object clone()
            throws CloneNotSupportedException
        {
            return (FixOptions.IncreaseDomainVisibilityInfo)super.clone();
        }

        public final volatile GenericJson set(String s, Object obj)
        {
            return (FixOptions.IncreaseDomainVisibilityInfo)set(s, obj);
        }

        public final GenericData set(String s, Object obj)
        {
            return (FixOptions.IncreaseDomainVisibilityInfo)super.set(s, obj);
        }

        public FixOptions.IncreaseDomainVisibilityInfo()
        {
        }
    }


    public List fixOptions;
    public String fixabilitySummaryState;
    public String kind;

    public CheckPermissionsResponse()
    {
    }

    public final volatile GenericJson clone()
    {
        return (CheckPermissionsResponse)clone();
    }

    public final volatile GenericData clone()
    {
        return (CheckPermissionsResponse)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (CheckPermissionsResponse)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (CheckPermissionsResponse)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (CheckPermissionsResponse)super.set(s, obj);
    }

    static 
    {
        Data.nullOf(com/google/api/services/drive/model/CheckPermissionsResponse$FixOptions);
    }
}
