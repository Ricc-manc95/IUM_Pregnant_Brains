// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.net;

import com.google.android.calendar.timely.rooms.data.CalendarEvent;
import com.google.android.calendar.timely.rooms.data.RecurringTimes;
import com.google.android.calendar.timely.rooms.data.RoomHierarchyNode;
import com.google.android.calendar.timely.rooms.data.SingleEventTime;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.timely.rooms.net:
//            RoomRequest, RoomListingParams, RoomRecommendationsParams

abstract class $AutoValue_RoomRequest extends RoomRequest
{

    private final ImmutableList attendees;
    private final String buildingId;
    private final CalendarEvent calendarEvent;
    private final String calendarEventReference;
    private final RoomHierarchyNode hierarchyNode;
    private final RoomListingParams listingParams;
    private final String query;
    private final RoomRecommendationsParams recommendationsParams;
    private final RecurringTimes recurringTimes;
    private final ImmutableList selectedRooms;
    private final SingleEventTime singleEventTime;

    $AutoValue_RoomRequest(String s, RoomRecommendationsParams roomrecommendationsparams, RoomListingParams roomlistingparams, SingleEventTime singleeventtime, RecurringTimes recurringtimes, CalendarEvent calendarevent, ImmutableList immutablelist, 
            ImmutableList immutablelist1, RoomHierarchyNode roomhierarchynode, String s1, String s2)
    {
        if (s == null)
        {
            throw new NullPointerException("Null query");
        }
        query = s;
        if (roomrecommendationsparams == null)
        {
            throw new NullPointerException("Null recommendationsParams");
        }
        recommendationsParams = roomrecommendationsparams;
        if (roomlistingparams == null)
        {
            throw new NullPointerException("Null listingParams");
        }
        listingParams = roomlistingparams;
        if (singleeventtime == null)
        {
            throw new NullPointerException("Null singleEventTime");
        }
        singleEventTime = singleeventtime;
        recurringTimes = recurringtimes;
        if (calendarevent == null)
        {
            throw new NullPointerException("Null calendarEvent");
        }
        calendarEvent = calendarevent;
        if (immutablelist == null)
        {
            throw new NullPointerException("Null attendees");
        }
        attendees = immutablelist;
        if (immutablelist1 == null)
        {
            throw new NullPointerException("Null selectedRooms");
        } else
        {
            selectedRooms = immutablelist1;
            hierarchyNode = roomhierarchynode;
            buildingId = s1;
            calendarEventReference = s2;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
label0:
        {
            if (!(obj instanceof RoomRequest))
            {
                break MISSING_BLOCK_LABEL_242;
            }
            obj = (RoomRequest)obj;
            if (query.equals(((RoomRequest) (obj)).getQuery()) && recommendationsParams.equals(((RoomRequest) (obj)).getRecommendationsParams()) && listingParams.equals(((RoomRequest) (obj)).getListingParams()) && singleEventTime.equals(((RoomRequest) (obj)).getSingleEventTime()) && (recurringTimes != null ? recurringTimes.equals(((RoomRequest) (obj)).getRecurringTimes()) : ((RoomRequest) (obj)).getRecurringTimes() == null) && (calendarEvent.equals(((RoomRequest) (obj)).getCalendarEvent()) && attendees.equals(((RoomRequest) (obj)).getAttendees()) && selectedRooms.equals(((RoomRequest) (obj)).getSelectedRooms())) && (hierarchyNode != null ? hierarchyNode.equals(((RoomRequest) (obj)).getHierarchyNode()) : ((RoomRequest) (obj)).getHierarchyNode() == null) && (buildingId != null ? buildingId.equals(((RoomRequest) (obj)).getBuildingId()) : ((RoomRequest) (obj)).getBuildingId() == null))
            {
                break label0;
            } else
            {
                break; /* Loop/switch isn't completed */
            }
        }
        if (calendarEventReference != null) goto _L4; else goto _L3
_L3:
        if (((RoomRequest) (obj)).getCalendarEventReference() == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!calendarEventReference.equals(((RoomRequest) (obj)).getCalendarEventReference())) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    public final ImmutableList getAttendees()
    {
        return attendees;
    }

    public final String getBuildingId()
    {
        return buildingId;
    }

    public final CalendarEvent getCalendarEvent()
    {
        return calendarEvent;
    }

    public final String getCalendarEventReference()
    {
        return calendarEventReference;
    }

    public final RoomHierarchyNode getHierarchyNode()
    {
        return hierarchyNode;
    }

    public final RoomListingParams getListingParams()
    {
        return listingParams;
    }

    public final String getQuery()
    {
        return query;
    }

    public final RoomRecommendationsParams getRecommendationsParams()
    {
        return recommendationsParams;
    }

    public final RecurringTimes getRecurringTimes()
    {
        return recurringTimes;
    }

    public final ImmutableList getSelectedRooms()
    {
        return selectedRooms;
    }

    public final SingleEventTime getSingleEventTime()
    {
        return singleEventTime;
    }

    public int hashCode()
    {
        int l = 0;
        int i1 = query.hashCode();
        int j1 = recommendationsParams.hashCode();
        int k1 = listingParams.hashCode();
        int l1 = singleEventTime.hashCode();
        int i;
        int j;
        int k;
        int i2;
        int j2;
        int k2;
        if (recurringTimes == null)
        {
            i = 0;
        } else
        {
            i = recurringTimes.hashCode();
        }
        i2 = calendarEvent.hashCode();
        j2 = attendees.hashCode();
        k2 = selectedRooms.hashCode();
        if (hierarchyNode == null)
        {
            j = 0;
        } else
        {
            j = hierarchyNode.hashCode();
        }
        if (buildingId == null)
        {
            k = 0;
        } else
        {
            k = buildingId.hashCode();
        }
        if (calendarEventReference != null)
        {
            l = calendarEventReference.hashCode();
        }
        return (k ^ (j ^ ((((i ^ ((((i1 ^ 0xf4243) * 0xf4243 ^ j1) * 0xf4243 ^ k1) * 0xf4243 ^ l1) * 0xf4243) * 0xf4243 ^ i2) * 0xf4243 ^ j2) * 0xf4243 ^ k2) * 0xf4243) * 0xf4243) * 0xf4243 ^ l;
    }

    public final RoomRequest.Builder toBuilder()
    {
        class Builder extends RoomRequest.Builder
        {

            private ImmutableList attendees;
            private String buildingId;
            private CalendarEvent calendarEvent;
            private String calendarEventReference;
            private RoomHierarchyNode hierarchyNode;
            private RoomListingParams listingParams;
            private String query;
            private RoomRecommendationsParams recommendationsParams;
            private RecurringTimes recurringTimes;
            private ImmutableList selectedRooms;
            private SingleEventTime singleEventTime;

            public final RoomRequest build()
            {
                String s2 = "";
                if (query == null)
                {
                    s2 = String.valueOf("").concat(" query");
                }
                String s = s2;
                if (recommendationsParams == null)
                {
                    s = String.valueOf(s2).concat(" recommendationsParams");
                }
                s2 = s;
                if (listingParams == null)
                {
                    s2 = String.valueOf(s).concat(" listingParams");
                }
                s = s2;
                if (singleEventTime == null)
                {
                    s = String.valueOf(s2).concat(" singleEventTime");
                }
                s2 = s;
                if (calendarEvent == null)
                {
                    s2 = String.valueOf(s).concat(" calendarEvent");
                }
                s = s2;
                if (attendees == null)
                {
                    s = String.valueOf(s2).concat(" attendees");
                }
                s2 = s;
                if (selectedRooms == null)
                {
                    s2 = String.valueOf(s).concat(" selectedRooms");
                }
                if (!s2.isEmpty())
                {
                    String s1 = String.valueOf(s2);
                    if (s1.length() != 0)
                    {
                        s1 = "Missing required properties:".concat(s1);
                    } else
                    {
                        s1 = new String("Missing required properties:");
                    }
                    throw new IllegalStateException(s1);
                } else
                {
                    return new AutoValue_RoomRequest(query, recommendationsParams, listingParams, singleEventTime, recurringTimes, calendarEvent, attendees, selectedRooms, hierarchyNode, buildingId, calendarEventReference);
                }
            }

            public final RoomRequest.Builder setAttendees(ImmutableList immutablelist)
            {
                if (immutablelist == null)
                {
                    throw new NullPointerException("Null attendees");
                } else
                {
                    attendees = immutablelist;
                    return this;
                }
            }

            public final RoomRequest.Builder setBuildingId(String s)
            {
                buildingId = s;
                return this;
            }

            public final RoomRequest.Builder setCalendarEvent(CalendarEvent calendarevent)
            {
                if (calendarevent == null)
                {
                    throw new NullPointerException("Null calendarEvent");
                } else
                {
                    calendarEvent = calendarevent;
                    return this;
                }
            }

            public final RoomRequest.Builder setCalendarEventReference(String s)
            {
                calendarEventReference = s;
                return this;
            }

            public final RoomRequest.Builder setHierarchyNode(RoomHierarchyNode roomhierarchynode)
            {
                hierarchyNode = roomhierarchynode;
                return this;
            }

            public final RoomRequest.Builder setListingParams(RoomListingParams roomlistingparams)
            {
                if (roomlistingparams == null)
                {
                    throw new NullPointerException("Null listingParams");
                } else
                {
                    listingParams = roomlistingparams;
                    return this;
                }
            }

            public final RoomRequest.Builder setQuery(String s)
            {
                if (s == null)
                {
                    throw new NullPointerException("Null query");
                } else
                {
                    query = s;
                    return this;
                }
            }

            public final RoomRequest.Builder setRecommendationsParams(RoomRecommendationsParams roomrecommendationsparams)
            {
                if (roomrecommendationsparams == null)
                {
                    throw new NullPointerException("Null recommendationsParams");
                } else
                {
                    recommendationsParams = roomrecommendationsparams;
                    return this;
                }
            }

            public final RoomRequest.Builder setRecurringTimes(RecurringTimes recurringtimes)
            {
                recurringTimes = recurringtimes;
                return this;
            }

            public final RoomRequest.Builder setSelectedRooms(ImmutableList immutablelist)
            {
                if (immutablelist == null)
                {
                    throw new NullPointerException("Null selectedRooms");
                } else
                {
                    selectedRooms = immutablelist;
                    return this;
                }
            }

            public final RoomRequest.Builder setSingleEventTime(SingleEventTime singleeventtime)
            {
                if (singleeventtime == null)
                {
                    throw new NullPointerException("Null singleEventTime");
                } else
                {
                    singleEventTime = singleeventtime;
                    return this;
                }
            }

            public Builder()
            {
            }

            Builder(RoomRequest roomrequest)
            {
                query = roomrequest.getQuery();
                recommendationsParams = roomrequest.getRecommendationsParams();
                listingParams = roomrequest.getListingParams();
                singleEventTime = roomrequest.getSingleEventTime();
                recurringTimes = roomrequest.getRecurringTimes();
                calendarEvent = roomrequest.getCalendarEvent();
                attendees = roomrequest.getAttendees();
                selectedRooms = roomrequest.getSelectedRooms();
                hierarchyNode = roomrequest.getHierarchyNode();
                buildingId = roomrequest.getBuildingId();
                calendarEventReference = roomrequest.getCalendarEventReference();
            }
        }

        return new Builder(this);
    }

    public String toString()
    {
        String s = query;
        String s1 = String.valueOf(recommendationsParams);
        String s2 = String.valueOf(listingParams);
        String s3 = String.valueOf(singleEventTime);
        String s4 = String.valueOf(recurringTimes);
        String s5 = String.valueOf(calendarEvent);
        String s6 = String.valueOf(attendees);
        String s7 = String.valueOf(selectedRooms);
        String s8 = String.valueOf(hierarchyNode);
        String s9 = buildingId;
        String s10 = calendarEventReference;
        return (new StringBuilder(String.valueOf(s).length() + 192 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length() + String.valueOf(s5).length() + String.valueOf(s6).length() + String.valueOf(s7).length() + String.valueOf(s8).length() + String.valueOf(s9).length() + String.valueOf(s10).length())).append("RoomRequest{query=").append(s).append(", recommendationsParams=").append(s1).append(", listingParams=").append(s2).append(", singleEventTime=").append(s3).append(", recurringTimes=").append(s4).append(", calendarEvent=").append(s5).append(", attendees=").append(s6).append(", selectedRooms=").append(s7).append(", hierarchyNode=").append(s8).append(", buildingId=").append(s9).append(", calendarEventReference=").append(s10).append("}").toString();
    }
}
