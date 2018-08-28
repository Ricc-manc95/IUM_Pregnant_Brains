// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui.roomtile.meetings;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.TintAwareDrawable;
import android.support.v4.graphics.drawable.WrappedDrawableApi21;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.calendar.tiles.view.BadgedIconTile;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.timely.rooms.data.Room;
import com.google.android.calendar.timely.rooms.data.RoomCriteria;
import com.google.android.calendar.timely.rooms.data.RoomFeature;
import com.google.android.calendar.timely.rooms.ui.roomtile.RoomTileFactory;
import com.google.common.base.Platform;
import com.google.common.collect.ImmutableList;
import java.util.Iterator;
import java.util.Locale;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui.roomtile.meetings:
//            RoomTile

public final class StructuredRoomTileFactoryImpl
    implements RoomTileFactory
{

    public final Context context;
    private final LayoutInflater inflater;
    private final boolean isInBuildingContext;
    private final android.view.View.OnClickListener roomInfoClickListener = new .Lambda._cls0();

    public StructuredRoomTileFactoryImpl(Context context1, boolean flag)
    {
        class .Lambda._cls0
            implements android.view.View.OnClickListener
        {

            private final StructuredRoomTileFactoryImpl arg$1;

            public final void onClick(View view)
            {
                Object obj = arg$1;
                Context context2 = ((StructuredRoomTileFactoryImpl) (obj)).context;
                obj = ((StructuredRoomTileFactoryImpl) (obj)).context;
                view = (Room)view.getTag();
                obj = new Intent(((Context) (obj)), com/google/android/calendar/timely/rooms/infoactivity/RoomInfoActivity);
                if (view == null)
                {
                    throw new NullPointerException();
                } else
                {
                    ((Intent) (obj)).putExtra("room", (Parcelable)view);
                    context2.startActivity(((Intent) (obj)));
                    return;
                }
            }

            .Lambda._cls0()
            {
                arg$1 = StructuredRoomTileFactoryImpl.this;
            }
        }

        context = context1;
        inflater = LayoutInflater.from(context);
        isInBuildingContext = flag;
    }

    private final Drawable getIcon(Room room, boolean flag)
    {
        int i;
        if (room.getCategory() == 2)
        {
            i = 0x7f0201e1;
        } else
        if (flag)
        {
            i = 0x7f020216;
        } else
        {
            i = 0x7f020212;
        }
        room = context.getResources().getDrawable(i);
        if (flag)
        {
            room.mutate().setColorFilter(context.getResources().getColor(0x7f0d01f5), android.graphics.PorterDuff.Mode.SRC_ATOP);
        }
        return room;
    }

    private final String locationToString(Room room, boolean flag)
    {
        if (isInBuildingContext) goto _L2; else goto _L1
_L1:
        String s;
        Resources resources;
        String s1;
        String s2;
        resources = context.getResources();
        s = room.getBuildingName();
        s1 = room.getFloorName();
        s2 = room.getFloorSectionName();
        if (!TextUtils.isEmpty(s)) goto _L4; else goto _L3
_L3:
        room = null;
_L6:
        return room;
_L4:
        if (!TextUtils.isEmpty(s1))
        {
            break; /* Loop/switch isn't completed */
        }
        room = s;
        if (TextUtils.isEmpty(s2)) goto _L6; else goto _L5
_L5:
        return resources.getString(0x7f13041c, new Object[] {
            s, Platform.nullToEmpty(s1), Platform.nullToEmpty(s2)
        });
_L2:
        boolean flag1;
        s = String.valueOf(Platform.nullToEmpty(room.getFloorName()));
        room = String.valueOf(Platform.nullToEmpty(room.getFloorSectionName()));
        if (room.length() != 0)
        {
            s = s.concat(room);
        } else
        {
            s = new String(s);
        }
        if (TextUtils.isEmpty(s))
        {
            return "";
        }
        flag1 = TextUtils.isDigitsOnly(s);
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        room = s;
        if (!flag1) goto _L6; else goto _L7
_L7:
        return context.getString(0x7f130241, new Object[] {
            s
        });
    }

    private final View recycleOrCreateRightActionView(RoomTile roomtile, int i)
    {
        View view1 = ((TileView) (roomtile)).rightActionView;
        View view = view1;
        if (view1 == null)
        {
            view = inflater.inflate(0x7f05013f, roomtile, false);
            view.setFocusable(false);
            roomtile.setRightActionView(view);
        }
        ((ImageView)((ViewGroup)view).getChildAt(0)).setImageResource(i);
        roomtile.treatAsButton(false);
        roomtile.setOnClickListener(null);
        roomtile.setClickable(false);
        view.setTag(null);
        view.setOnClickListener(null);
        view.setId(-1);
        view.setVisibility(0);
        return view;
    }

    private final void setupContent(RoomTile roomtile, Room room, boolean flag, String s, RoomCriteria roomcriteria)
    {
        if (room.getShortName() == null || room.getCategory() == 2) goto _L2; else goto _L1
_L1:
        String s1;
        Integer integer;
        Object obj;
        Object obj1;
        String s2;
        Object obj2;
        obj1 = room.getShortName();
        s1 = locationToString(room, true);
        s2 = locationToString(room, false);
        integer = room.getCapacity();
        s = context.getResources();
        obj = room.getCapacity();
        class .Lambda._cls1
            implements Function
        {

            public static final Function $instance = new .Lambda._cls1();

            public final Object apply(Object obj3)
            {
                return ((RoomFeature)obj3).getName();
            }


            private .Lambda._cls1()
            {
            }
        }

        if (obj == null)
        {
            s = null;
        } else
        {
            s = s.getQuantityString(0x7f120008, ((Integer) (obj)).intValue(), new Object[] {
                obj
            });
        }
        obj = room.getProminentFeatures();
        obj2 = .Lambda._cls1..instance;
        if (obj == null)
        {
            throw new NullPointerException();
        }
        if (obj2 == null)
        {
            throw new NullPointerException();
        }
        obj = TextUtils.join(", ", new com.google.common.collect.Iterables._cls5(((Iterable) (obj)), ((Function) (obj2))));
        obj2 = room.getProminentFeatures().iterator();
_L6:
        if (!((Iterator) (obj2)).hasNext()) goto _L4; else goto _L3
_L3:
        if (((RoomFeature)((Iterator) (obj2)).next()).getEquipmentType() != 1) goto _L6; else goto _L5
_L5:
        int i = 1;
_L12:
        room = room.getProminentFeatures().iterator();
_L10:
        if (!room.hasNext()) goto _L8; else goto _L7
_L7:
        if (((RoomFeature)room.next()).getEquipmentType() != 2) goto _L10; else goto _L9
_L9:
        boolean flag1;
        flag1 = true;
        break MISSING_BLOCK_LABEL_226;
_L4:
        i = 0;
        continue; /* Loop/switch isn't completed */
_L8:
        flag1 = false;
        boolean flag2;
        int j;
        int k;
        if (RemoteFeatureConfig.SRB.enabled())
        {
            if (roomtile.srbContent == null)
            {
                room = (ViewGroup)((TileView) (roomtile)).contentView;
                roomtile.srbContent = LayoutInflater.from(roomtile.getContext()).inflate(0x7f05014c, room, false);
                room.addView(roomtile.srbContent);
                roomtile.nameView = (TextView)roomtile.srbContent.findViewById(0x7f100350);
                roomtile.locationSeparator = (TextView)roomtile.srbContent.findViewById(0x7f100355);
                roomtile.locationView = (TextView)roomtile.srbContent.findViewById(0x7f10034a);
                roomtile.secondLine = roomtile.srbContent.findViewById(0x7f100351);
                roomtile.capacityBadge = (ImageView)roomtile.srbContent.findViewById(0x7f100352);
                roomtile.capacityView = (TextView)roomtile.srbContent.findViewById(0x7f100348);
                roomtile.videoView = roomtile.srbContent.findViewById(0x7f100353);
                roomtile.audioView = roomtile.srbContent.findViewById(0x7f100354);
                roomtile.videoSeparator = roomtile.videoView.findViewById(0x7f10034f);
                roomtile.videoIcon = (ImageView)roomtile.videoView.findViewById(0x7f1000d4);
                roomtile.audioSeparator = roomtile.audioView.findViewById(0x7f10034f);
                roomtile.audioIcon = (ImageView)roomtile.audioView.findViewById(0x7f1000d4);
            }
            room = roomtile.srbContent;
            if (room != null)
            {
                room.setVisibility(0);
            }
            room = roomtile.structuredContent;
            if (room != null)
            {
                room.setVisibility(8);
            }
            room = roomtile.unstructuredContent;
            if (room != null)
            {
                room.setVisibility(8);
            }
            if (flag)
            {
                roomtile.nameView.setPaintFlags(roomtile.nameView.getPaintFlags() | 0x10);
                roomtile.nameView.setTextColor(ContextCompat.getColor(roomtile.getContext(), 0x7f0d021a));
            } else
            {
                roomtile.nameView.setPaintFlags(roomtile.nameView.getPaintFlags() & 0xffffffef);
                roomtile.nameView.setTextColor(ContextCompat.getColor(roomtile.getContext(), 0x7f0d0309));
            }
        } else
        {
            if (roomtile.structuredContent == null)
            {
                room = (ViewGroup)((TileView) (roomtile)).contentView;
                roomtile.structuredContent = LayoutInflater.from(roomtile.getContext()).inflate(0x7f05014d, room, false);
                room.addView(roomtile.structuredContent);
                roomtile.nameView = (TextView)roomtile.structuredContent.findViewById(0x7f10038f);
                roomtile.locationView = (TextView)roomtile.structuredContent.findViewById(0x7f100390);
                roomtile.secondLine = roomtile.structuredContent.findViewById(0x7f100351);
                roomtile.capacityBadge = (ImageView)roomtile.structuredContent.findViewById(0x7f100352);
                roomtile.capacityView = (TextView)roomtile.structuredContent.findViewById(0x7f100348);
                roomtile.featuresBadge = roomtile.structuredContent.findViewById(0x7f100356);
                roomtile.featuresView = (TextView)roomtile.structuredContent.findViewById(0x7f100357);
            }
            room = roomtile.srbContent;
            if (room != null)
            {
                room.setVisibility(8);
            }
            room = roomtile.structuredContent;
            if (room != null)
            {
                room.setVisibility(0);
            }
            room = roomtile.unstructuredContent;
            if (room != null)
            {
                room.setVisibility(8);
            }
        }
        roomtile.nameView.setText(((CharSequence) (obj1)));
        roomtile.locationView.setText(s1);
        roomtile.locationView.setContentDescription(s2);
        if (integer == null)
        {
            room = "";
        } else
        {
            room = String.format(Locale.getDefault(), "%d", new Object[] {
                integer
            });
        }
        if (!TextUtils.isEmpty(room))
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (!TextUtils.isEmpty(((CharSequence) (obj))))
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (!TextUtils.isEmpty(s1))
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (RemoteFeatureConfig.SRB.enabled())
        {
            room = roomtile.secondLine;
            if (flag2 || i != 0 || flag1 || k != 0)
            {
                k = 1;
            } else
            {
                k = 0;
            }
            if (room != null)
            {
                if (k != 0)
                {
                    k = 0;
                } else
                {
                    k = 8;
                }
                room.setVisibility(k);
            }
        } else
        {
            room = roomtile.secondLine;
            if (flag2 || j != 0)
            {
                k = 1;
            } else
            {
                k = 0;
            }
            if (room != null)
            {
                if (k != 0)
                {
                    k = 0;
                } else
                {
                    k = 8;
                }
                room.setVisibility(k);
            }
        }
        room = roomtile.capacityBadge;
        if (room != null)
        {
            if (flag2)
            {
                k = 0;
            } else
            {
                k = 8;
            }
            room.setVisibility(k);
        }
        room = roomtile.capacityView;
        if (room != null)
        {
            if (flag2)
            {
                k = 0;
            } else
            {
                k = 8;
            }
            room.setVisibility(k);
        }
        obj1 = roomtile.capacityView;
        if (integer == null)
        {
            room = "";
        } else
        {
            room = String.format(Locale.getDefault(), "%d", new Object[] {
                integer
            });
        }
        ((TextView) (obj1)).setText(room);
        roomtile.capacityView.setContentDescription(s);
        if (RemoteFeatureConfig.SRB.enabled())
        {
            room = roomtile.getResources().getDrawable(0x7f020201);
            if (android.os.Build.VERSION.SDK_INT < 23 && !(room instanceof TintAwareDrawable))
            {
                room = new WrappedDrawableApi21(room);
            }
            if (flag2 && roomcriteria != null && integer.intValue() < roomcriteria.getNumSeats())
            {
                j = ContextCompat.getColor(roomtile.getContext(), 0x7f0d01fc);
                roomtile.capacityView.setTextColor(j);
                room.mutate();
                room.setTint(j);
            } else
            {
                roomtile.capacityView.setTextColor(ContextCompat.getColor(roomtile.getContext(), 0x7f0d030a));
            }
            roomtile.capacityBadge.setImageDrawable(room);
            roomtile.videoIcon.setImageResource(0x7f020244);
            room = roomtile.videoView;
            if (room != null)
            {
                if (i != 0)
                {
                    j = 0;
                } else
                {
                    j = 8;
                }
                room.setVisibility(j);
            }
            room = roomtile.videoSeparator;
            if (room != null)
            {
                if (flag2)
                {
                    j = 0;
                } else
                {
                    j = 8;
                }
                room.setVisibility(j);
            }
            roomtile.audioIcon.setImageResource(0x7f02021e);
            room = roomtile.audioView;
            if (room != null)
            {
                if (flag1)
                {
                    j = 0;
                } else
                {
                    j = 8;
                }
                room.setVisibility(j);
            }
            room = roomtile.audioSeparator;
            if (flag2 || i != 0)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (room != null)
            {
                if (j != 0)
                {
                    j = 0;
                } else
                {
                    j = 8;
                }
                room.setVisibility(j);
            }
            roomtile = roomtile.locationSeparator;
            if ((flag2 || i != 0 || flag1) && !TextUtils.isEmpty(s1))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (roomtile != null)
            {
                if (i != 0)
                {
                    i = 0;
                } else
                {
                    i = 8;
                }
                roomtile.setVisibility(i);
            }
            return;
        }
        room = roomtile.featuresBadge;
        if (room != null)
        {
            if (j != 0)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            room.setVisibility(i);
        }
        room = roomtile.featuresView;
        if (room != null)
        {
            if (j != 0)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            room.setVisibility(i);
        }
        roomtile.featuresView.setText(((CharSequence) (obj)));
        return;
_L2:
        if (flag)
        {
            roomcriteria = context.getString(0x7f130014);
            if (s == null)
            {
                s = roomcriteria;
            } else
            {
                s = String.format(s, new Object[] {
                    roomcriteria
                });
            }
        }
        room = room.getName();
        roomcriteria = roomtile.srbContent;
        if (roomcriteria != null)
        {
            roomcriteria.setVisibility(8);
        }
        roomcriteria = roomtile.structuredContent;
        if (roomcriteria != null)
        {
            roomcriteria.setVisibility(8);
        }
        roomcriteria = roomtile.unstructuredContent;
        if (roomcriteria != null)
        {
            roomcriteria.setVisibility(0);
        }
        roomtile.unstructuredContent.setText(room);
        roomcriteria = roomtile.unstructuredContent;
        if (s == null)
        {
            roomtile = room;
        } else
        {
            roomtile = String.format(s, new Object[] {
                room
            });
        }
        roomcriteria.setContentDescription(roomtile);
        return;
        if (true) goto _L12; else goto _L11
_L11:
    }

    private final void setupInfoButton(RoomTile roomtile, Room room)
    {
        boolean flag;
        if (room.getCapacity() != null || room.getBuildingName() != null || room.getFloorName() != null || !room.getFeatures().isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            if (((TileView) (roomtile)).rightActionView != null)
            {
                ((TileView) (roomtile)).rightActionView.setVisibility(8);
            }
            return;
        } else
        {
            View view = recycleOrCreateRightActionView(roomtile, 0x7f02020b);
            view.setContentDescription(roomtile.getResources().getString(0x7f13041d));
            view.setOnClickListener(roomInfoClickListener);
            view.setTag(room);
            return;
        }
    }

    public final TileView getAddedRoomView(Room room, ViewGroup viewgroup, TileView tileview, boolean flag)
    {
        return getAddedRoomView(room, viewgroup, tileview, true, null);
    }

    public final TileView getAddedRoomView(Room room, ViewGroup viewgroup, TileView tileview, boolean flag, RoomCriteria roomcriteria)
    {
        boolean flag1;
        if (!(tileview instanceof RoomTile))
        {
            tileview = inflater.inflate(0x7f050149, viewgroup, false);
        }
        tileview = (RoomTile)tileview;
        if (RemoteFeatureConfig.SRB.enabled())
        {
            setupInfoButton(tileview, room);
        } else
        if (flag)
        {
            viewgroup = recycleOrCreateRightActionView(tileview, 0x7f0201d8);
            viewgroup.setContentDescription(tileview.getResources().getString(0x7f130416));
            viewgroup.setId(0x7f100031);
        } else
        if (tileview.rightActionView != null)
        {
            tileview.rightActionView.setVisibility(8);
        }
        if (room.getAvailability() == 2)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        setupContent(tileview, room, flag1, context.getString(0x7f130010), roomcriteria);
        if (RemoteFeatureConfig.SRB.enabled())
        {
            viewgroup = context.getResources();
            if (flag)
            {
                room = viewgroup.getDrawable(0x7f0201cf);
                if (android.os.Build.VERSION.SDK_INT < 23 && !(room instanceof TintAwareDrawable))
                {
                    room = new WrappedDrawableApi21(room);
                }
                room.setTint(viewgroup.getColor(0x7f0d01e6));
            } else
            {
                viewgroup = viewgroup.getDrawable(0x7f0201d0);
                room = viewgroup;
                if (flag1)
                {
                    viewgroup.mutate().setAlpha(154);
                    room = viewgroup;
                }
            }
            tileview.setIconDrawable(room, false);
            tileview.setBadge(null);
            return tileview;
        } else
        {
            tileview.setIconDrawable(getIcon(room, false));
            int i;
            if (flag1)
            {
                i = 0x7f020258;
            } else
            {
                i = 0x7f020112;
            }
            tileview.setBadge(Integer.valueOf(i));
            return tileview;
        }
    }

    public final TileView getRoomView(Room room, ViewGroup viewgroup, TileView tileview)
    {
        return getRoomView(room, viewgroup, tileview, null);
    }

    public final TileView getRoomView(Room room, ViewGroup viewgroup, TileView tileview, RoomCriteria roomcriteria)
    {
        boolean flag;
        if (!(tileview instanceof RoomTile))
        {
            tileview = inflater.inflate(0x7f050149, viewgroup, false);
        }
        viewgroup = (RoomTile)tileview;
        setupInfoButton(viewgroup, room);
        if (room.getAvailability() == 2)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        setupContent(viewgroup, room, flag, null, roomcriteria);
        if (RemoteFeatureConfig.SRB.enabled())
        {
            room = context.getResources().getDrawable(0x7f0201d2);
            if (flag)
            {
                room.mutate().setAlpha(154);
            }
            viewgroup.setIconDrawable(room, false);
        } else
        {
            viewgroup.setIconDrawable(getIcon(room, flag));
        }
        viewgroup.setBadge(null);
        return viewgroup;
    }
}
