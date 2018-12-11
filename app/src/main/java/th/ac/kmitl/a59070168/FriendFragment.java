package th.ac.kmitl.a59070168;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FriendFragment extends Fragment {

    ArrayList<Friend> friends = new ArrayList<>();
    FriendAdapter adapter;
    ListView friendList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friend, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        friendList = getActivity().findViewById(R.id.friend_list);

        getJsonData();

        Button backBtn = getActivity().findViewById(R.id.friend_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view,new HomeFragment()).commit();
            }
        });
    }

    void getJsonData() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://jsonplaceholder.typicode.com/users")
                    .build();
            Response responses = client.newCall(request).execute();

            JSONArray jArray = new JSONArray(responses.body().string());
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject object = jArray.getJSONObject(i);

                Friend friend = new Friend();
                friend.setId(String.valueOf(object.getInt("id")));
                friend.setName(String.valueOf(object.getString("name")));
                friend.setEmail(String.valueOf(object.getString("email")));
                friend.setPhone(String.valueOf(object.getString("phone")));
                friend.setWebsite(String.valueOf(object.getString("website")));

                friends.add(friend);
            }

            setAdapter();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    void setAdapter() {
        adapter = new FriendAdapter(getActivity(), R.layout.friend_item, friends);
        friendList.setAdapter(adapter);

        friendList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment myfriend = new Fragment();


                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, myfriend).addToBackStack(null).commit();
            }
        });
    }

}
