// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.apps.calendar.util.android.Blob;
import com.google.android.calendar.api.common.FieldModification;
import com.google.common.base.Optional;
import com.google.common.base.Platform;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event.conference:
//            ConferenceDataModifications, ConferenceData, Conference, ConferenceSolution, 
//            CreateConferenceRequest, CreatedConference, ConferenceDataUtils

public class ConferenceDataModificationsImpl extends ConferenceDataModifications
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private FieldModification conferenceIdModification;
    private FieldModification conferenceNotesModification;
    private FieldModification conferenceSignatureModification;
    private FieldModification conferenceSolutionModification;
    private FieldModification conferencesModification;
    private FieldModification createConferenceRequestModification;
    private FieldModification createdConferenceDataBlobModification;
    private final ConferenceData original;

    ConferenceDataModificationsImpl(Parcel parcel)
    {
        ConferenceData conferencedata = (ConferenceData)parcel.readParcelable(com/google/android/calendar/api/event/conference/ConferenceData.getClassLoader());
        Object obj;
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            obj = new ArrayList();
            parcel.readList(((List) (obj)), com/google/android/calendar/api/event/conference/Conference.getClassLoader());
            obj = new com.google.android.calendar.api.common.FieldModification._cls1(ImmutableList.copyOf(((java.util.Collection) (obj))));
        } else
        {
            obj = new FieldModification();
        }
        this(conferencedata, ((FieldModification) (obj)));
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            conferenceSolutionModification = new com.google.android.calendar.api.common.FieldModification._cls1((ConferenceSolution)parcel.readParcelable(com/google/android/calendar/api/event/conference/ConferenceSolution.getClassLoader()));
        }
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            conferenceIdModification = new com.google.android.calendar.api.common.FieldModification._cls1(Platform.nullToEmpty(parcel.readString()));
        }
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            conferenceNotesModification = new com.google.android.calendar.api.common.FieldModification._cls1(Platform.nullToEmpty(parcel.readString()));
        }
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            conferenceSignatureModification = new com.google.android.calendar.api.common.FieldModification._cls1(Platform.nullToEmpty(parcel.readString()));
        }
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            createConferenceRequestModification = new com.google.android.calendar.api.common.FieldModification._cls1((CreateConferenceRequest)parcel.readParcelable(com/google/android/calendar/api/event/conference/CreateConferenceRequest.getClassLoader()));
        }
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            createdConferenceDataBlobModification = new com.google.android.calendar.api.common.FieldModification._cls1((Blob)parcel.readParcelable(com/google/android/apps/calendar/util/android/Blob.getClassLoader()));
        }
    }

    public ConferenceDataModificationsImpl(ConferenceData conferencedata)
    {
        this(conferencedata, new FieldModification());
    }

    private ConferenceDataModificationsImpl(ConferenceData conferencedata, FieldModification fieldmodification)
    {
        conferencesModification = new FieldModification();
        conferenceSolutionModification = new FieldModification();
        conferenceIdModification = new FieldModification();
        conferenceNotesModification = new FieldModification();
        conferenceSignatureModification = new FieldModification();
        createConferenceRequestModification = new FieldModification();
        createdConferenceDataBlobModification = new FieldModification();
        if (conferencedata == null)
        {
            throw new NullPointerException();
        }
        original = (ConferenceData)conferencedata;
        if (fieldmodification == null)
        {
            throw new NullPointerException();
        } else
        {
            conferencesModification = (FieldModification)fieldmodification;
            return;
        }
    }

    public final void clear()
    {
        cloneFrom(CreatedConference.EMPTY);
    }

    public final void cloneFrom(CreatedConference createdconference)
    {
        ConferenceData conferencedata = createdconference.conferenceData();
        conferencesModification = new com.google.android.calendar.api.common.FieldModification._cls1(conferencedata.getConferences());
        conferenceSolutionModification = new com.google.android.calendar.api.common.FieldModification._cls1(conferencedata.getConferenceSolution());
        conferenceIdModification = new com.google.android.calendar.api.common.FieldModification._cls1(conferencedata.getConferenceId());
        conferenceNotesModification = new com.google.android.calendar.api.common.FieldModification._cls1(conferencedata.getNotes());
        conferenceSignatureModification = new com.google.android.calendar.api.common.FieldModification._cls1(conferencedata.getSignature());
        createConferenceRequestModification = new com.google.android.calendar.api.common.FieldModification._cls1(conferencedata.getCreateConferenceRequest());
        createdConferenceDataBlobModification = new com.google.android.calendar.api.common.FieldModification._cls1((Blob)createdconference.optionalConferenceDataBlob().orNull());
    }

    public final void createNewConference(int i)
    {
        clear();
        createConferenceRequestModification = new com.google.android.calendar.api.common.FieldModification._cls1(CreateConferenceRequest.newCreateRequest(ConferenceDataUtils.convertConferenceTypeToConferenceSolutionType(i)));
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof ConferenceDataModificationsImpl)
        {
            obj = (ConferenceDataModificationsImpl)obj;
            ConferenceData conferencedata = original;
            ConferenceData conferencedata1 = ((ConferenceDataModificationsImpl) (obj)).original;
            boolean flag;
            if (conferencedata == conferencedata1 || conferencedata != null && conferencedata.equals(conferencedata1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                FieldModification fieldmodification = conferencesModification;
                FieldModification fieldmodification7 = ((ConferenceDataModificationsImpl) (obj)).conferencesModification;
                if (fieldmodification == fieldmodification7 || fieldmodification != null && fieldmodification.equals(fieldmodification7))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    FieldModification fieldmodification1 = conferenceSolutionModification;
                    FieldModification fieldmodification8 = ((ConferenceDataModificationsImpl) (obj)).conferenceSolutionModification;
                    if (fieldmodification1 == fieldmodification8 || fieldmodification1 != null && fieldmodification1.equals(fieldmodification8))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        FieldModification fieldmodification2 = conferenceIdModification;
                        FieldModification fieldmodification9 = ((ConferenceDataModificationsImpl) (obj)).conferenceIdModification;
                        if (fieldmodification2 == fieldmodification9 || fieldmodification2 != null && fieldmodification2.equals(fieldmodification9))
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (flag)
                        {
                            FieldModification fieldmodification3 = conferenceNotesModification;
                            FieldModification fieldmodification10 = ((ConferenceDataModificationsImpl) (obj)).conferenceNotesModification;
                            if (fieldmodification3 == fieldmodification10 || fieldmodification3 != null && fieldmodification3.equals(fieldmodification10))
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                            if (flag)
                            {
                                FieldModification fieldmodification4 = conferenceSignatureModification;
                                FieldModification fieldmodification11 = ((ConferenceDataModificationsImpl) (obj)).conferenceSignatureModification;
                                if (fieldmodification4 == fieldmodification11 || fieldmodification4 != null && fieldmodification4.equals(fieldmodification11))
                                {
                                    flag = true;
                                } else
                                {
                                    flag = false;
                                }
                                if (flag)
                                {
                                    FieldModification fieldmodification5 = createConferenceRequestModification;
                                    FieldModification fieldmodification12 = ((ConferenceDataModificationsImpl) (obj)).createConferenceRequestModification;
                                    if (fieldmodification5 == fieldmodification12 || fieldmodification5 != null && fieldmodification5.equals(fieldmodification12))
                                    {
                                        flag = true;
                                    } else
                                    {
                                        flag = false;
                                    }
                                    if (flag)
                                    {
                                        FieldModification fieldmodification6 = createdConferenceDataBlobModification;
                                        obj = ((ConferenceDataModificationsImpl) (obj)).createdConferenceDataBlobModification;
                                        if (fieldmodification6 == obj || fieldmodification6 != null && fieldmodification6.equals(obj))
                                        {
                                            flag = true;
                                        } else
                                        {
                                            flag = false;
                                        }
                                        if (flag)
                                        {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public final String getConferenceId()
    {
        if (conferenceIdModification.shouldModify())
        {
            return (String)conferenceIdModification.getModificationValue();
        } else
        {
            return original.getConferenceId();
        }
    }

    public final ConferenceSolution getConferenceSolution()
    {
        if (conferenceSolutionModification.shouldModify())
        {
            return (ConferenceSolution)conferenceSolutionModification.getModificationValue();
        } else
        {
            return original.getConferenceSolution();
        }
    }

    public final ImmutableList getConferences()
    {
        ImmutableList immutablelist;
        if (!conferencesModification.shouldModify())
        {
            immutablelist = getOriginal().getConferences();
        } else
        {
            ImmutableList immutablelist1 = (ImmutableList)conferencesModification.getModificationValue();
            immutablelist = immutablelist1;
            if (immutablelist1 == null)
            {
                return ImmutableList.of();
            }
        }
        return immutablelist;
    }

    public final CreateConferenceRequest getCreateConferenceRequest()
    {
        if (createConferenceRequestModification.shouldModify())
        {
            return (CreateConferenceRequest)createConferenceRequestModification.getModificationValue();
        } else
        {
            return original.getCreateConferenceRequest();
        }
    }

    public final String getNotes()
    {
        if (conferenceNotesModification.shouldModify())
        {
            return (String)conferenceNotesModification.getModificationValue();
        } else
        {
            return original.getNotes();
        }
    }

    public final ConferenceData getOriginal()
    {
        return original;
    }

    public final String getSignature()
    {
        if (conferenceSignatureModification.shouldModify())
        {
            return (String)conferenceSignatureModification.getModificationValue();
        } else
        {
            return original.getSignature();
        }
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            original, conferencesModification, conferenceSolutionModification, conferenceIdModification, conferenceNotesModification, conferenceSignatureModification, createConferenceRequestModification, createdConferenceDataBlobModification
        });
    }

    public final boolean isModified()
    {
        return conferencesModification.shouldModify() || conferenceSolutionModification.shouldModify() || conferenceIdModification.shouldModify() || conferenceNotesModification.shouldModify() || conferenceSignatureModification.shouldModify() || createConferenceRequestModification.shouldModify() || createdConferenceDataBlobModification.shouldModify();
    }

    public String toString()
    {
        if (conferencesModification.shouldModify()) goto _L2; else goto _L1
_L1:
        Object obj = "no change";
_L14:
        return String.format("ConferenceDataModificationsImpl{original=%s, %s}", new Object[] {
            original, obj
        });
_L2:
        String s;
        Iterator iterator;
        obj = (List)conferencesModification.getModificationValue();
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_255;
        }
        s = "";
        iterator = ((List) (obj)).iterator();
_L11:
        int i;
        obj = s;
        if (!iterator.hasNext())
        {
            continue; /* Loop/switch isn't completed */
        }
        Conference conference = (Conference)iterator.next();
        obj = String.valueOf(s);
        i = conference.getType();
        i;
        JVM INSTR tableswitch 0 5: default 132
    //                   0 213
    //                   1 220
    //                   2 227
    //                   3 234
    //                   4 241
    //                   5 248;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L9:
        break MISSING_BLOCK_LABEL_248;
_L4:
        break; /* Loop/switch isn't completed */
_L3:
        s = (new StringBuilder(20)).append("unknown(").append(i).append(")").toString();
_L12:
        s = (new StringBuilder(String.valueOf(obj).length() + 15 + String.valueOf(s).length())).append(((String) (obj))).append("add ").append(s).append(" conference").toString();
        if (true) goto _L11; else goto _L10
_L10:
        s = "unknown";
          goto _L12
_L5:
        s = "eventHangout";
          goto _L12
_L6:
        s = "eventNamedHangout";
          goto _L12
_L7:
        s = "meeting";
          goto _L12
_L8:
        s = "meetingPhoneNumber";
          goto _L12
        s = "meetingPhoneNumbersLink";
          goto _L12
        obj = "remove conference(s)";
        if (true) goto _L14; else goto _L13
_L13:
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(original, i);
        FieldModification fieldmodification = conferencesModification;
        boolean flag = fieldmodification.shouldModify();
        parcel.writeValue(Boolean.valueOf(flag));
        if (flag)
        {
            parcel.writeList((List)fieldmodification.getModificationValue());
        }
        parcel.writeValue(Boolean.valueOf(conferenceSolutionModification.shouldModify()));
        if (conferenceSolutionModification.shouldModify())
        {
            parcel.writeParcelable((Parcelable)conferenceSolutionModification.getModificationValue(), i);
        }
        parcel.writeValue(Boolean.valueOf(conferenceIdModification.shouldModify()));
        if (conferenceIdModification.shouldModify())
        {
            parcel.writeString(Platform.nullToEmpty((String)conferenceIdModification.getModificationValue()));
        }
        parcel.writeValue(Boolean.valueOf(conferenceNotesModification.shouldModify()));
        if (conferenceNotesModification.shouldModify())
        {
            parcel.writeString(Platform.nullToEmpty((String)conferenceNotesModification.getModificationValue()));
        }
        parcel.writeValue(Boolean.valueOf(conferenceSignatureModification.shouldModify()));
        if (conferenceSignatureModification.shouldModify())
        {
            parcel.writeString(Platform.nullToEmpty((String)conferenceSignatureModification.getModificationValue()));
        }
        parcel.writeValue(Boolean.valueOf(createConferenceRequestModification.shouldModify()));
        if (createConferenceRequestModification.shouldModify())
        {
            parcel.writeParcelable((Parcelable)createConferenceRequestModification.getModificationValue(), i);
        }
        parcel.writeValue(Boolean.valueOf(createdConferenceDataBlobModification.shouldModify()));
        if (createdConferenceDataBlobModification.shouldModify())
        {
            parcel.writeParcelable((Parcelable)createdConferenceDataBlobModification.getModificationValue(), i);
        }
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new ConferenceDataModificationsImpl(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new ConferenceDataModifications[i];
        }

        _cls1()
        {
        }
    }

}
