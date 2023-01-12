package edu.aku.dmu.hf_visitors.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.aku.dmu.hf_visitors.R;
import edu.aku.dmu.hf_visitors.core.MainApp;
import edu.aku.dmu.hf_visitors.models.ListingMembers;


public class RegisteredMembersAdapter extends RecyclerView.Adapter<RegisteredMembersAdapter.ViewHolder> {
    private static final String TAG = "RegisteredMembersAdapter";
    private final Context mContext;
    private final List<ListingMembers> listingMembers;
    private final List<ListingMembers> backupItems = new ArrayList<>();
    private final int completeCount;
    private final OnItemClickListener onItemClickListener;

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param listingMembers List<MembersModel> containing the data to populate views to be used by RecyclerView.
     */
   /* public RegisteredMembersAdapter(Context mContext, List<ListingMembers> members) {
        this.member = members;
        this.mContext = mContext;
        completeCount = 0;

    }*/
    public RegisteredMembersAdapter(Context mContext, List<ListingMembers> listingMembers, OnItemClickListener onItemClickListener) {
        this.listingMembers = listingMembers;
        backupItems.clear();
        backupItems.addAll(listingMembers);
        this.mContext = mContext;
        completeCount = 0;
        this.onItemClickListener = onItemClickListener;

    }

    // Add filter
    @SuppressLint("NotifyDataSetChanged")
    public void filter(String query) {
        if (query.equals("")) {
            // Show original list
            listingMembers.clear();
            listingMembers.addAll(backupItems);
            notifyDataSetChanged();
        } else {
            listingMembers.clear();
            for (ListingMembers listingMembers : backupItems) {
                if (listingMembers.getHead().toLowerCase().contains(query) || listingMembers.getCellNo().contains(query)) {
                    this.listingMembers.add(listingMembers);
                }
            }
            notifyDataSetChanged();
        }
    }


    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
        ListingMembers listingMembers = this.listingMembers.get(position);        // Get element from your dataset at this position and replace the contents of the view
        // with that element

        TextView headName = viewHolder.headName;
        TextView contNo = viewHolder.contNo;
        TextView pwName = viewHolder.pwName;
        TextView cName = viewHolder.cName;
        TextView hhID = viewHolder.hhID;
        ImageView mainIcon = viewHolder.mainIcon;

        headName.setText(listingMembers.getHead());
        contNo.setText("Cell: " + listingMembers.getCellNo());

        if (!listingMembers.getPwName().equals("")) {
            pwName.setText("PW: " + listingMembers.getPwName());
            pwName.setTextColor(ContextCompat.getColor(mContext, R.color.orange_dark));
        }
        if (!listingMembers.getChildName().equals("")) {
            pwName.setText("Child: " + listingMembers.getChildName());
            pwName.setTextColor(ContextCompat.getColor(mContext, R.color.redDark));
        }

        hhID.setText("HHID: " + listingMembers.getHhid());
        if (listingMembers.getChildName().equals("") && listingMembers.getPwName().equals(""))
            mainIcon.setImageResource(R.drawable.adult_male);
        else if (listingMembers.getChildName().length() > 0)
            mainIcon.setImageResource(R.drawable.malebabyicon);
        else mainIcon.setImageResource(R.drawable.mwraicon);
//        mainIcon.setImageResource(listingMembers.getCr_gender().equals("1") ? R.drawable.malebabyicon : R.drawable.femalebabyicon);
        viewHolder.itemView.setOnClickListener(view -> onItemClickListener.onItemClick(this.listingMembers.get(position)));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.registered_member_row, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        MainApp.memberCount = listingMembers.size();
        return listingMembers.size();
    }

    public interface OnItemClickListener {
        void onItemClick(ListingMembers listingMembers);
    }

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView headName;
        private final TextView contNo;
        private final TextView pwName;
        private final TextView cName;
        private final TextView hhID;
        private final ImageView mainIcon;


        public ViewHolder(View v) {
            super(v);
            headName = v.findViewById(R.id.headName);
            contNo = v.findViewById(R.id.contactNo);
            pwName = v.findViewById(R.id.pName);
            cName = v.findViewById(R.id.cName);
            hhID = v.findViewById(R.id.hhID);
            mainIcon = v.findViewById(R.id.mainIcon);

        }

        public TextView getTextView() {
            return headName;
        }
    }
}
