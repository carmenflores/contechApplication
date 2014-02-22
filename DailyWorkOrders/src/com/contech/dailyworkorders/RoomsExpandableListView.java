/* REFERENCE: http://stackoverflow.com/questions/9824074/android-expandablelistview-looking-for-a-tutorial */

package com.contech.dailyworkorders;

import java.util.List;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class RoomsExpandableListView extends BaseExpandableListAdapter {
	private static final class ViewHolder {
		TextView textLabel;
	}

	private final List<Room> rooms;
	private LayoutInflater inflater;

	public RoomsExpandableListView(Context context,
			List<Room> rooms) {
		this.inflater = LayoutInflater.from(context);
		this.rooms = rooms;
	}

	public void setInflater(LayoutInflater inflater) {
		this.inflater = inflater;
	}

	@Override
	public String getChild(int groupPosition, int childPosition) {
		Room room = rooms.get(groupPosition);
		return room.getByIndex(room.getRoom_specs(), childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return rooms.get(groupPosition).getRoom_specs().size();
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, final ViewGroup parent) {
		View resultView = convertView;
		ViewHolder holder;

		if (resultView == null) {

			resultView = inflater.inflate(android.R.layout.simple_list_item_1,
					null);
			holder = new ViewHolder();
			holder.textLabel = (TextView) resultView
					.findViewById(android.R.id.text1);
			resultView.setTag(holder);
		} else
			holder = (ViewHolder) resultView.getTag();

		final String item = getChild(groupPosition, childPosition);

		holder.textLabel.setText("\t\t"+item);

		return resultView;
	}

	@Override
	public Room getGroup(int groupPosition) {
		return rooms.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return rooms.size();
	}

	@Override
	public long getGroupId(final int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			convertView = inflater.inflate(android.R.layout.simple_list_item_1,
					null);
			holder = new ViewHolder();
			holder.textLabel = (TextView) convertView
					.findViewById(android.R.id.text1);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final Room item = getGroup(groupPosition);
		holder.textLabel.setTextSize(21);
		holder.textLabel.setTypeface(null, Typeface.BOLD);
		holder.textLabel.setText(item.toString());

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
    @Override
    public void notifyDataSetChanged()
    {
        // Refresh List rows
        super.notifyDataSetChanged();
    }

}