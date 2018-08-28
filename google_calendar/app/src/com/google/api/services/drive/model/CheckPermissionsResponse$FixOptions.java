// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.drive.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.util.List;

// Referenced classes of package com.google.api.services.drive.model:
//            CheckPermissionsResponse

public static final class IncreaseDomainVisibilityInfo extends GenericJson
{
    public static final class AddCollaboratorsInfo extends GenericJson
    {

        public List outOfDomainWarningEmailAddresses;

        public final volatile GenericJson clone()
        {
            return (AddCollaboratorsInfo)clone();
        }

        public final volatile GenericData clone()
        {
            return (AddCollaboratorsInfo)clone();
        }

        public final Object clone()
            throws CloneNotSupportedException
        {
            return (AddCollaboratorsInfo)super.clone();
        }

        public final volatile GenericJson set(String s, Object obj)
        {
            return (AddCollaboratorsInfo)set(s, obj);
        }

        public final GenericData set(String s, Object obj)
        {
            return (AddCollaboratorsInfo)super.set(s, obj);
        }

        public AddCollaboratorsInfo()
        {
        }
    }

    public static final class IncreaseDomainVisibilityInfo extends GenericJson
    {

        public String domainDisplayName;
        public String domainName;

        public final volatile GenericJson clone()
        {
            return (IncreaseDomainVisibilityInfo)clone();
        }

        public final volatile GenericData clone()
        {
            return (IncreaseDomainVisibilityInfo)clone();
        }

        public final Object clone()
            throws CloneNotSupportedException
        {
            return (IncreaseDomainVisibilityInfo)super.clone();
        }

        public final volatile GenericJson set(String s, Object obj)
        {
            return (IncreaseDomainVisibilityInfo)set(s, obj);
        }

        public final GenericData set(String s, Object obj)
        {
            return (IncreaseDomainVisibilityInfo)super.set(s, obj);
        }

        public IncreaseDomainVisibilityInfo()
        {
        }
    }


    public AddCollaboratorsInfo addCollaboratorsInfo;
    public List allowedRoles;
    public List fixableFileIds;
    public List fixableRecipientEmailAddresses;
    public Boolean fixesEverything;
    public IncreaseDomainVisibilityInfo increaseDomainVisibilityInfo;
    public String optionType;

    public final volatile GenericJson clone()
    {
        return (IncreaseDomainVisibilityInfo)clone();
    }

    public final volatile GenericData clone()
    {
        return (IncreaseDomainVisibilityInfo)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (IncreaseDomainVisibilityInfo)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (IncreaseDomainVisibilityInfo)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (IncreaseDomainVisibilityInfo)super.set(s, obj);
    }

    public IncreaseDomainVisibilityInfo()
    {
    }
}
