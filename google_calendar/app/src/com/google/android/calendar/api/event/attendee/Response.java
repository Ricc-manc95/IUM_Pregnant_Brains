// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.attendee;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.base.Platform;

public class Response
    implements Parcelable
{
    public static final class Builder
    {

        public String comment;
        public Long proposedEndTimeMillis;
        public Long proposedStartTimeMillis;
        public ResponseStatus status;

        public final Builder setProposedTime(Long long1, Long long2)
        {
            boolean flag2 = true;
            boolean flag;
            boolean flag1;
            if (long1 == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (long2 == null)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag == flag1)
            {
                flag = flag2;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException();
            } else
            {
                proposedStartTimeMillis = long1;
                proposedEndTimeMillis = long2;
                return this;
            }
        }

        public Builder()
        {
            status = ResponseStatus.NEEDS_ACTION;
            comment = "";
            proposedStartTimeMillis = null;
            proposedEndTimeMillis = null;
        }
    }

    public static final class ResponseStatus extends Enum
    {

        private static final ResponseStatus $VALUES[];
        public static final ResponseStatus ACCEPTED;
        public static final ResponseStatus DECLINED;
        public static final ResponseStatus NEEDS_ACTION;
        public static final ResponseStatus TENTATIVE;

        public static ResponseStatus createFromInteger(int i)
        {
            ResponseStatus aresponsestatus[] = values();
            int k = aresponsestatus.length;
            for (int j = 0; j < k; j++)
            {
                ResponseStatus responsestatus = aresponsestatus[j];
                if (responsestatus.ordinal() == i)
                {
                    return responsestatus;
                }
            }

            throw new IllegalStateException("Invalid attendeeDescriptor type value");
        }

        public static ResponseStatus createFromParcel(Parcel parcel)
        {
            return createFromInteger(parcel.readInt());
        }

        public static ResponseStatus valueOf(String s)
        {
            return (ResponseStatus)Enum.valueOf(com/google/android/calendar/api/event/attendee/Response$ResponseStatus, s);
        }

        public static ResponseStatus[] values()
        {
            return (ResponseStatus[])$VALUES.clone();
        }

        public static void writeToParcel(Parcel parcel, ResponseStatus responsestatus)
        {
            parcel.writeInt(responsestatus.ordinal());
        }

        static 
        {
            NEEDS_ACTION = new ResponseStatus("NEEDS_ACTION", 0);
            ACCEPTED = new ResponseStatus("ACCEPTED", 1);
            TENTATIVE = new ResponseStatus("TENTATIVE", 2);
            DECLINED = new ResponseStatus("DECLINED", 3);
            $VALUES = (new ResponseStatus[] {
                NEEDS_ACTION, ACCEPTED, TENTATIVE, DECLINED
            });
        }

        private ResponseStatus(String s, int i)
        {
            super(s, i);
        }
    }


    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final String comment;
    public final Long proposedEndTimeMillis;
    public final Long proposedStartTimeMillis;
    public final ResponseStatus status;

    Response(Parcel parcel)
    {
        Builder builder = new Builder();
        builder.status = ResponseStatus.createFromParcel(parcel);
        builder.comment = Platform.nullToEmpty(parcel.readString());
        this(builder.setProposedTime((Long)parcel.readValue(java/lang/Long.getClassLoader()), (Long)parcel.readValue(java/lang/Long.getClassLoader())));
    }

    public Response(Builder builder)
    {
        status = builder.status;
        comment = builder.comment;
        proposedStartTimeMillis = builder.proposedStartTimeMillis;
        proposedEndTimeMillis = builder.proposedEndTimeMillis;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (!(obj instanceof Response))
            {
                return false;
            }
            obj = (Response)obj;
            if (status != ((Response) (obj)).status)
            {
                return false;
            }
            if (!comment.equals(((Response) (obj)).comment))
            {
                return false;
            }
            if (proposedStartTimeMillis == null ? ((Response) (obj)).proposedStartTimeMillis != null : !proposedStartTimeMillis.equals(((Response) (obj)).proposedStartTimeMillis))
            {
                return false;
            }
            if (proposedEndTimeMillis != null)
            {
                return proposedEndTimeMillis.equals(((Response) (obj)).proposedEndTimeMillis);
            }
            if (((Response) (obj)).proposedEndTimeMillis != null)
            {
                return false;
            }
        }
        return true;
    }

    public int hashCode()
    {
        int j = 0;
        int k = status.hashCode();
        int l = comment.hashCode();
        int i;
        if (proposedStartTimeMillis != null)
        {
            i = proposedStartTimeMillis.hashCode();
        } else
        {
            i = 0;
        }
        if (proposedEndTimeMillis != null)
        {
            j = proposedEndTimeMillis.hashCode();
        }
        return (i + (k * 31 + l) * 31) * 31 + j;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        ResponseStatus.writeToParcel(parcel, status);
        parcel.writeString(comment);
        parcel.writeValue(proposedStartTimeMillis);
        parcel.writeValue(proposedEndTimeMillis);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new Response(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new Response[i];
        }

        _cls1()
        {
        }
    }

}
