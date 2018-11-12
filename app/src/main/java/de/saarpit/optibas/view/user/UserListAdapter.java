package de.saarpit.optibas.view.user;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.saarpit.optibas.NewUserActivity;
import de.saarpit.optibas.R;
import de.saarpit.optibas.ValuesActivity;
import de.saarpit.optibas.data.user.User;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    public static final String EXTRA_USER = "de.saarpit.optibas.USER";

    class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView userItemView;

        private UserViewHolder(View itemView) {
            super(itemView);
            userItemView = itemView.findViewById(R.id.userFullNameView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            User selectedUser = mUsers.get(itemPosition);

            Intent userIntent = new Intent(v.getContext(), ValuesActivity.class);

            String fullName = selectedUser.getName();
            userIntent.putExtra(NewUserActivity.EXTRA_FULLNAME, fullName);

            String birthday = selectedUser.getBirthday();
            userIntent.putExtra(NewUserActivity.EXTRA_BIRTHDAY, birthday);

            double insulin = selectedUser.getDailyInsulin();
            userIntent.putExtra(NewUserActivity.EXTRA_INSULIN, insulin);

            int basalRelative = selectedUser.getBasalQuota();
            userIntent.putExtra(NewUserActivity.EXTRA_BASALRELATIVE, basalRelative);

            int weight = selectedUser.getWeight();
            userIntent.putExtra(NewUserActivity.EXTRA_WEIGHT, weight);

            String wakeupTime = selectedUser.getWakeupTime();
            userIntent.putExtra(NewUserActivity.EXTRA_WAKEUPTIME, wakeupTime);

            v.getContext().startActivity(userIntent);
        }
    }

    private final LayoutInflater mInflater;
    private List<User> mUsers; // Cached copy of words

    public UserListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        if (mUsers != null) {
            User current = mUsers.get(position);
            holder.userItemView.setText(current.getName());
        } else {
            // Covers the case of data not being ready yet.
            holder.userItemView.setText("No user found");
        }
    }

    public void setUsers(List<User> users){
        mUsers = users;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mUsers has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mUsers != null)
            return mUsers.size();
        else return 0;
    }
}