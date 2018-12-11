package th.ac.kmitl.a59070168;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FriendAdapter extends ArrayAdapter<Friend> {
    List<Friend> friends;
    Context context;

    public FriendAdapter(@NonNull Context context, int resource, List<Friend> friends) {
        super(context, resource, friends);
        this.friends = friends;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View friendItem = LayoutInflater.from(context).inflate(
                R.layout.friend_item,
                parent,
                false);

        TextView id = friendItem.findViewById(R.id.post_item_id);
        TextView name = friendItem.findViewById(R.id.post_item_name);
        TextView phone = friendItem.findViewById(R.id.post_item_phone);
        TextView email = friendItem.findViewById(R.id.post_item_email);
        TextView website = friendItem.findViewById(R.id.post_item_website);

        Friend friend = friends.get(position);
        id.setText(friend.getId());
        name.setText(friend.getName());
        phone.setText(friend.getPhone());
        email.setText(friend.getEmail());
        website.setText(friend.getWebsite());

        

        return friendItem;
    }
}
