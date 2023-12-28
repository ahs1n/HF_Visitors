package edu.aku.dmu.hf_visitors.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    public void filterByHead(String query) {
        if (query.equals("")) {
            // Show original list
            listingMembers.clear();
            listingMembers.addAll(backupItems);
            notifyDataSetChanged();
        } else {
            listingMembers.clear();
            for (ListingMembers listingMembers : backupItems) {
                if (listingMembers.getHead().toLowerCase().contains(query)) {
                    this.listingMembers.add(listingMembers);
                }
            }
            notifyDataSetChanged();
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterByChild(String query) {
        if (query.equals("")) {
            // Show original list
            listingMembers.clear();
            listingMembers.addAll(backupItems);
            notifyDataSetChanged();
        } else {
            listingMembers.clear();
            for (ListingMembers listingMembers : backupItems) {
                if (listingMembers.getChildName().toLowerCase().contains(query)) {
                    this.listingMembers.add(listingMembers);
                }
            }
            notifyDataSetChanged();
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterByCell(String query) {
        if (query.equals("")) {
            // Show original list
            listingMembers.clear();
            listingMembers.addAll(backupItems);
            notifyDataSetChanged();
        } else {
            listingMembers.clear();
            for (ListingMembers listingMembers : backupItems) {
                if (listingMembers.getCellNo().contains(query)) {
                    this.listingMembers.add(listingMembers);
                }
            }
            notifyDataSetChanged();
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterByHHID(String query) {
        if (query.equals("")) {
            // Show original list
            listingMembers.clear();
            listingMembers.addAll(backupItems);
            notifyDataSetChanged();
        } else {
            listingMembers.clear();
            for (ListingMembers listingMembers : backupItems) {
                if (listingMembers.getHhid().contains(query)) {
                    this.listingMembers.add(listingMembers);
                }
            }
            notifyDataSetChanged();
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterByNewMember(String query) {
        if (query.equals("")) {
            // Show original list
            listingMembers.clear();
            listingMembers.addAll(backupItems);
            notifyDataSetChanged();
        } else {
            listingMembers.clear();
            for (ListingMembers listingMembers : backupItems) {
                if (listingMembers.getNewMemberName().toLowerCase().contains(query)) {
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
        LinearLayout headBackClr = viewHolder.headBackClr;
        LinearLayout headBorder = viewHolder.headBorder;

        /*if (listingMembers.getPwName().equals("") && listingMembers.getChildName().equals("")) {
//            headName.setText("Head of HouseHold \n" +listingMembers.getHead());
            String hoh = String.format(mContext.getString(R.string.household_head), listingMembers.getHead());
            SpannableString spannable = new SpannableString(hoh);
            // here we set the color
            spannable.setSpan(new ForegroundColorSpan(Color.GRAY), 0, 17, 0);

            headName.setText(spannable);
        } else*/
        headName.setText(listingMembers.getHead());

        contNo.setText("Cell: " + listingMembers.getCellNo());

        if (!listingMembers.getPwName().equals("")) {
            pwName.setText("PW: " + listingMembers.getPwName());
            headBackClr.setBackgroundColor(ContextCompat.getColor(mContext, R.color.lightPink1));
            headBorder.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorAccent));
            headName.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
            pwName.setTextColor(ContextCompat.getColor(mContext, R.color.orange_dark));
            pwName.setTypeface(null, Typeface.BOLD);
            pwName.setTextSize(15f);
            viewHolder.itemView.setPadding((int) mContext.getResources().getDimension(R.dimen._10sdp), 0, 0, 0);
        } else if (!listingMembers.getChildName().equals("")) {
            pwName.setText("Child: " + listingMembers.getChildName());
            headBackClr.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorAccentLightOverlay));
            headBorder.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorAccent));
            headName.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
            pwName.setTextColor(ContextCompat.getColor(mContext, R.color.redDark));
            pwName.setTypeface(null, Typeface.BOLD);
            pwName.setTextSize(15f);
            viewHolder.itemView.setPadding((int) mContext.getResources().getDimension(R.dimen._10sdp), 0, 0, 0);
        } else if (!listingMembers.getNewMemberName().equals("")) {
            pwName.setText("New Mem: " + listingMembers.getNewMemberName());
            headBackClr.setBackgroundColor(ContextCompat.getColor(mContext, R.color.light_yellow));
            headBorder.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorAccent));
            headName.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
            pwName.setTextColor(ContextCompat.getColor(mContext, R.color.grayDark1));
            pwName.setTypeface(null, Typeface.BOLD);
            pwName.setTextSize(15f);
            viewHolder.itemView.setPadding((int) mContext.getResources().getDimension(R.dimen._10sdp), 0, 0, 0);
        } else {
            pwName.setText("HEAD OF HOUSEHOLD");
            pwName.setTypeface(null, Typeface.NORMAL);
            headName.setTextColor(ContextCompat.getColor(mContext, R.color.purple_700));
            pwName.setTextColor(ContextCompat.getColor(mContext, R.color.grayDark));
            headBackClr.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorAccentLight1));
            headBorder.setBackgroundColor(ContextCompat.getColor(mContext, R.color.purple_700));
            viewHolder.itemView.setPadding(0, 0, 0, 0);
        }

/*        if (listingMembers.getPwName().equals("") && listingMembers.getChildName().equals("")) {
            linearLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorAccentLight1));
        } else linearLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));*/

        hhID.setText("HHID: " + listingMembers.getHhid());
        if (listingMembers.getChildName().equals("") && listingMembers.getPwName().equals("") && listingMembers.getNewMemberName().equals(""))
            mainIcon.setImageResource(R.drawable.adult_male);
        else if (listingMembers.getChildName().length() > 0 && !listingMembers.getChildName().equals(""))
            mainIcon.setImageResource(R.drawable.malebabyicon);
        else if (listingMembers.getPwName().length() > 0 && !listingMembers.getPwName().equals(""))
            mainIcon.setImageResource(R.drawable.mwraicon);
        else mainIcon.setImageResource(R.drawable.newmember);
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
        private final LinearLayout headBackClr;
        private final LinearLayout headBorder;


        public ViewHolder(View v) {
            super(v);
            headName = v.findViewById(R.id.headName);
            contNo = v.findViewById(R.id.contactNo);
            pwName = v.findViewById(R.id.pName);
            cName = v.findViewById(R.id.cName);
            hhID = v.findViewById(R.id.hhID);
            mainIcon = v.findViewById(R.id.mainIcon);
            headBackClr = v.findViewById(R.id.regMem);
            headBorder = v.findViewById(R.id.forHeadBorder);

        }

        public TextView getTextView() {
            return headName;
        }
    }
}
