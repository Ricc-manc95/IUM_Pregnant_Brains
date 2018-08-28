// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui.suggestionsview;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.calendar.tiles.view.HeadlineTileView;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.timely.rooms.ui.roomtile.RoomTileFactory;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.AddedRoom;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.DividerItem;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.LocationHeader;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.MoreInLocationButton;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.NoSuggestionsItem;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.ShortDividerItem;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.SuggestedRoom;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.UiItem;
import com.google.android.calendar.utils.ViewOutlineUtils;
import java.util.ArrayList;
import java.util.Locale;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui.suggestionsview:
//            UiItemList

public final class Adapter extends android.support.v7.widget.RecyclerView.Adapter
    implements UiItemList
{

    private final ArrayList items = new ArrayList();
    private final RoomTileFactory roomsTileFactory;
    private final TypeIdMap typeIdMap = new TypeIdMap();

    public Adapter(RoomTileFactory roomtilefactory)
    {
        roomsTileFactory = roomtilefactory;
    }

    public final void addItem(UiItem uiitem)
    {
        int i = items.size();
        items.add(i, uiitem);
        super.mObservable.notifyItemRangeInserted(i, 1);
    }

    public final void clear()
    {
        items.clear();
        super.mObservable.notifyChanged();
    }

    public final boolean contains(UiItem uiitem)
    {
        return items.contains(uiitem);
    }

    public final int getItemCount()
    {
        return items.size();
    }

    public final int getItemViewType(int i)
    {
        return typeIdMap.getId(((UiItem)items.get(i)).getClass());
    }

    public final void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder viewholder, int i)
    {
        Object obj;
        boolean flag;
        boolean flag1;
        flag = false;
        flag1 = false;
        obj = (UiItem)items.get(i);
        viewholder = viewholder.itemView;
        if (!(obj instanceof AddedRoom)) goto _L2; else goto _L1
_L1:
        obj = (AddedRoom)obj;
        RoomTileFactory roomtilefactory = roomsTileFactory;
        com.google.android.calendar.timely.rooms.data.Room room = ((AddedRoom) (obj)).room;
        TileView tileview = (TileView)viewholder;
        class .Lambda._cls0
            implements android.view.View.OnClickListener
        {

            private final AddedRoom arg$1;

            public final void onClick(View view2)
            {
                view2 = arg$1;
                ((AddedRoom) (view2)).consumer.accept(view2);
            }

            .Lambda._cls0(AddedRoom addedroom)
            {
                arg$1 = addedroom;
            }
        }

        boolean flag2;
        if (((AddedRoom) (obj)).consumer != null)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        roomtilefactory.getAddedRoomView(room, null, tileview, flag2, ((AddedRoom) (obj)).criteria);
        i = ((flag1) ? 1 : 0);
        if (((AddedRoom) (obj)).consumer != null)
        {
            i = 1;
        }
        if (i == 0) goto _L4; else goto _L3
_L3:
        if (!RemoteFeatureConfig.SRB.enabled()) goto _L6; else goto _L5
_L5:
        ((TileView)viewholder).treatAsButton(true);
        viewholder.setOnClickListener(new .Lambda._cls0(((AddedRoom) (obj))));
_L4:
        return;
_L6:
        class .Lambda._cls1
            implements android.view.View.OnClickListener
        {

            private final AddedRoom arg$1;

            public final void onClick(View view2)
            {
                view2 = arg$1;
                ((AddedRoom) (view2)).consumer.accept(view2);
            }

            .Lambda._cls1(AddedRoom addedroom)
            {
                arg$1 = addedroom;
            }
        }

        viewholder.findViewById(0x7f100031).setOnClickListener(new .Lambda._cls1(((AddedRoom) (obj))));
        return;
_L2:
        if (!(obj instanceof LocationHeader))
        {
            break; /* Loop/switch isn't completed */
        }
        Object obj1 = (HeadlineTileView)viewholder;
        obj = (LocationHeader)obj;
        int j;
        if (((LocationHeader) (obj)).hasSuggestions)
        {
            ((TileView) (obj1)).treatAsButton(true);
            ((HeadlineTileView) (obj1)).setOnClickListener(new com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.LocationHeaderHelper..Lambda._cls3(((LocationHeader) (obj))));
        } else
        {
            ((TileView) (obj1)).treatAsButton(false);
            ((HeadlineTileView) (obj1)).setOnClickListener(null);
        }
        viewholder = ((LocationHeader) (obj)).message;
        ((HeadlineTileView) (obj1)).text.setText(viewholder);
        ((HeadlineTileView) (obj1)).text.setTextColor(((HeadlineTileView) (obj1)).getContext().getResources().getColor(0x7f0d0309));
        ((HeadlineTileView) (obj1)).setContentDescription(((LocationHeader) (obj)).contentDescription);
        viewholder = ((TileView) (obj1)).rightActionView;
        j = ((LocationHeader) (obj)).state;
        if (j == 1 || j == 2)
        {
            obj1 = viewholder.findViewById(0x7f100239);
            if (obj1 != null)
            {
                ((View) (obj1)).setVisibility(0);
            }
            obj1 = viewholder.findViewById(0x7f10023f);
            if (obj1 != null)
            {
                ((View) (obj1)).setVisibility(8);
            }
            i = ((LocationHeader) (obj)).requiredSeats;
            obj1 = new com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.LocationHeaderHelper..Lambda._cls0(((LocationHeader) (obj)));
            ((TextView)viewholder.findViewById(0x7f10023c)).setText(String.format(Locale.getDefault(), "%d", new Object[] {
                Integer.valueOf(i)
            }));
            View view = viewholder.findViewById(0x7f10023a);
            ViewOutlineUtils.setViewRoundRectOutline(view);
            view.setOnClickListener(((android.view.View.OnClickListener) (obj1)));
            obj1 = viewholder.findViewById(0x7f10023d);
            if (j == 1)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (obj1 != null)
            {
                ImageButton imagebutton;
                boolean flag3;
                if (i != 0)
                {
                    i = 0;
                } else
                {
                    i = 8;
                }
                ((View) (obj1)).setVisibility(i);
            }
            obj1 = viewholder.findViewById(0x7f10023e);
            if (j == 1)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (obj1 != null)
            {
                if (i != 0)
                {
                    i = ((flag) ? 1 : 0);
                } else
                {
                    i = 8;
                }
                ((View) (obj1)).setVisibility(i);
            }
            if (j == 1)
            {
                flag3 = ((LocationHeader) (obj)).requireVideo;
                obj1 = new com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.LocationHeaderHelper..Lambda._cls1(((LocationHeader) (obj)));
                if (flag3)
                {
                    i = 0x7f020243;
                } else
                {
                    i = 0x7f020246;
                }
                imagebutton = (ImageButton)viewholder.findViewById(0x7f10023d);
                imagebutton.setImageResource(i);
                ViewOutlineUtils.setViewRoundRectOutline(imagebutton);
                imagebutton.setOnClickListener(((android.view.View.OnClickListener) (obj1)));
                flag3 = ((LocationHeader) (obj)).requireAudio;
                obj = new com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.LocationHeaderHelper..Lambda._cls2(((LocationHeader) (obj)));
                if (flag3)
                {
                    i = 0x7f02021d;
                } else
                {
                    i = 0x7f02021f;
                }
                viewholder = (ImageButton)viewholder.findViewById(0x7f10023e);
                viewholder.setImageResource(i);
                ViewOutlineUtils.setViewRoundRectOutline(viewholder);
                viewholder.setOnClickListener(((android.view.View.OnClickListener) (obj)));
                return;
            }
        } else
        {
            if (j == 3)
            {
                View view1 = viewholder.findViewById(0x7f100239);
                if (view1 != null)
                {
                    view1.setVisibility(8);
                }
                view1 = viewholder.findViewById(0x7f10023f);
                if (view1 != null)
                {
                    view1.setVisibility(0);
                }
                viewholder.setContentDescription(((HeadlineTileView) (obj1)).getContext().getResources().getString(0x7f1301e4, new Object[] {
                    ((LocationHeader) (obj)).message
                }));
                return;
            }
            obj = viewholder.findViewById(0x7f100239);
            if (obj != null)
            {
                ((View) (obj)).setVisibility(8);
            }
            viewholder = viewholder.findViewById(0x7f10023f);
            if (viewholder != null)
            {
                viewholder.setVisibility(8);
                return;
            }
        }
        if (true) goto _L4; else goto _L7
_L7:
        if (obj instanceof MoreInLocationButton)
        {
            obj = (MoreInLocationButton)obj;
            ((TextTileView)viewholder).setPrimaryText(new CharSequence[] {
                ((MoreInLocationButton) (obj)).message
            });
            class .Lambda._cls2
                implements android.view.View.OnClickListener
            {

                private final MoreInLocationButton arg$1;

                public final void onClick(View view2)
                {
                    arg$1.clickCallback.run();
                }

            .Lambda._cls2(MoreInLocationButton moreinlocationbutton)
            {
                arg$1 = moreinlocationbutton;
            }
            }

            viewholder.setOnClickListener(new .Lambda._cls2(((MoreInLocationButton) (obj))));
            return;
        }
        if (obj instanceof NoSuggestionsItem)
        {
            obj = (NoSuggestionsItem)obj;
            ((TextTileView)viewholder).setPrimaryText(new CharSequence[] {
                ((NoSuggestionsItem) (obj)).message
            }).setPrimaryTextColor(((NoSuggestionsItem) (obj)).textColor).setIconDrawable(((NoSuggestionsItem) (obj)).icon);
            return;
        }
        if (obj instanceof SuggestedRoom)
        {
            obj = (SuggestedRoom)obj;
            class .Lambda._cls3
                implements android.view.View.OnClickListener
            {

                private final SuggestedRoom arg$1;

                public final void onClick(View view2)
                {
                    view2 = arg$1;
                    ((SuggestedRoom) (view2)).consumer.accept(view2);
                }

            .Lambda._cls3(SuggestedRoom suggestedroom)
            {
                arg$1 = suggestedroom;
            }
            }

            roomsTileFactory.getRoomView(((SuggestedRoom) (obj)).room, null, (TileView)viewholder, ((SuggestedRoom) (obj)).criteria).treatAsButton(true).setOnClickListener(new .Lambda._cls3(((SuggestedRoom) (obj))));
            return;
        }
        if (true) goto _L4; else goto _L8
_L8:
    }

    public final android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewgroup, int i)
    {
        Object obj;
        obj = typeIdMap.getClass(i);
        LayoutInflater layoutinflater = LayoutInflater.from(viewgroup.getContext());
        if (obj == com/google/android/calendar/timely/rooms/ui/suggestionsview/uiitem/LocationHeader)
        {
            i = 0x7f0500af;
        } else
        if (obj == com/google/android/calendar/timely/rooms/ui/suggestionsview/uiitem/AddedRoom || obj == com/google/android/calendar/timely/rooms/ui/suggestionsview/uiitem/SuggestedRoom)
        {
            i = 0x7f0500b1;
        } else
        if (obj == com/google/android/calendar/timely/rooms/ui/suggestionsview/uiitem/NoSuggestionsItem)
        {
            i = 0x7f0500ae;
        } else
        if (obj == com/google/android/calendar/timely/rooms/ui/suggestionsview/uiitem/MoreInLocationButton)
        {
            i = 0x7f0500b2;
        } else
        if (obj == com/google/android/calendar/timely/rooms/ui/suggestionsview/uiitem/DividerItem)
        {
            i = 0x7f0500ad;
        } else
        if (obj == com/google/android/calendar/timely/rooms/ui/suggestionsview/uiitem/ShortDividerItem)
        {
            i = 0x7f0500b3;
        } else
        {
            throw new IllegalArgumentException();
        }
        viewgroup = layoutinflater.inflate(i, viewgroup, false);
        if (obj != com/google/android/calendar/timely/rooms/ui/suggestionsview/uiitem/LocationHeader) goto _L2; else goto _L1
_L1:
        obj = (TileView)viewgroup;
        ((TileView) (obj)).treatAsButton(false);
        ((TileView) (obj)).setOnClickListener(null);
        ((TileView) (obj)).setClickable(false);
_L4:
        return new SingleViewHolder(viewgroup);
_L2:
        if (obj == com/google/android/calendar/timely/rooms/ui/suggestionsview/uiitem/MoreInLocationButton)
        {
            ((TileView)viewgroup).contentView.setImportantForAccessibility(2);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void removeItem(UiItem uiitem)
    {
        int i = items.indexOf(uiitem);
        if (i >= 0)
        {
            boolean flag;
            if (i >= 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException();
            }
            items.remove(i);
            super.mObservable.notifyItemRangeRemoved(i, 1);
        }
    }

    public final void replaceItem(UiItem uiitem, UiItem uiitem1)
    {
        int i = items.indexOf(uiitem);
        if (i >= 0)
        {
            items.set(i, uiitem1);
            super.mObservable.notifyItemRangeChanged(i, 1, null);
        }
    }

    private class TypeIdMap
    {

        private int nextViewType;
        private final BiMap viewTypes = new HashBiMap(16);

        public final Class getClass(int i)
        {
            this;
            JVM INSTR monitorenter ;
            Class class1 = (Class)viewTypes.inverse().get(Integer.valueOf(i));
            this;
            JVM INSTR monitorexit ;
            return class1;
            Exception exception;
            exception;
            throw exception;
        }

        public final int getId(Class class1)
        {
            this;
            JVM INSTR monitorenter ;
            Integer integer1 = (Integer)viewTypes.get(class1);
            Integer integer;
            integer = integer1;
            if (integer1 != null)
            {
                break MISSING_BLOCK_LABEL_54;
            }
            int i = nextViewType;
            nextViewType = i + 1;
            integer = Integer.valueOf(i);
            viewTypes.put(class1, integer);
            int j = integer.intValue();
            this;
            JVM INSTR monitorexit ;
            return j;
            class1;
            throw class1;
        }

        TypeIdMap()
        {
        }
    }


    private class SingleViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder
    {

        public SingleViewHolder(View view)
        {
            super(view);
        }
    }

}
