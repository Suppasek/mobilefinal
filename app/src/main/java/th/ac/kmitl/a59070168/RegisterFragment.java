package th.ac.kmitl.a59070168;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class RegisterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    void validateRegis() {
        EditText userId = getActivity().findViewById(R.id.register_user_id);
        EditText name = getActivity().findViewById(R.id.register_name);
        EditText age = getActivity().findViewById(R.id.register_age);
        EditText password = getActivity().findViewById(R.id.register_password);

        String userIdStr = userId.getText().toString();
        String nameStr = name.getText().toString();
        int ageInt = Integer.parseInt(age.getText().toString());
        String passwordStr = password.getText().toString();

        if ((userIdStr.length() >= 6 && userIdStr.length() <= 12) &&
                nameStr.contains(" ") &&
                (ageInt >= 10 && ageInt <= 80) &&
                passwordStr.length() > 6) {

            DBHelper dbHelper = new DBHelper(getActivity());
            dbHelper.addUser(userIdStr, nameStr, String.valueOf(age), passwordStr);
        }

    }
}
