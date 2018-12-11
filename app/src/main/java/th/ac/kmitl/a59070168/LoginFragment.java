package th.ac.kmitl.a59070168;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginFragment extends Fragment {

    DBHelper dbHelper;
    SharedPreferences shared;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dbHelper = new DBHelper(getActivity());

        shared = getActivity().getSharedPreferences("preference",
                getActivity().MODE_PRIVATE);

        String validate = shared.getString("userId", "not found");

        if (!validate.equals("not found")) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view,new HomeFragment()).addToBackStack(null).commit();
        }

        initLoginBtn();
        initRegisterBtn();
    }

    void initLoginBtn() {
        Button login = getActivity().findViewById(R.id.login_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginValidate();
            }
        });
    }

    void initRegisterBtn() {
        TextView regisbtn = getView().findViewById(R.id.login_register);
        regisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view,new RegisterFragment()).addToBackStack(null).commit();
            }
        });
    }

    void LoginValidate() {
        EditText userId = getActivity().findViewById(R.id.login_userid);
        EditText password = getActivity().findViewById(R.id.login_password);

        String userIdStr = userId.getText().toString();
        String passwordStr = password.getText().toString();

        if(userId.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Please fill out this form", Toast.LENGTH_SHORT).show();
        }
        else {
            if (dbHelper.valueExist(userIdStr, passwordStr)) {
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("userId", userIdStr);
                editor.putString("name", dbHelper.getName(userIdStr));

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new HomeFragment()).addToBackStack(null).commit();
            }
            else {
                Toast.makeText(getActivity(), "Invalid User or Password", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
