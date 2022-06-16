package com.thaiduong.myapplication.login_register.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.thaiduong.myapplication.R;
import com.thaiduong.myapplication.activities.AccountActivity;
import com.thaiduong.myapplication.data_local.DataLocalManager;
import com.thaiduong.myapplication.login_register.model.AppAccount;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    private EditText editAccount, editPasswd;
    private TextView tvValidate;
    private Button btnLogin;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        addViews(view);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager methodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                methodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                AppAccount account = DataLocalManager.getAccount();
                if (account != null) {
                    if (editAccount.getText().toString().equals(account.getAccount())
                            && editPasswd.getText().toString().equals(account.getPasswd())) {
                        DataLocalManager.setLoginStatus(true);
                        Intent intent = new Intent(getActivity(), AccountActivity.class);
                        startActivity(intent);
                    } else {
                        tvValidate.setText("Tài khoản hoặc mật khẩu không chính xác");
                    }
                } else {
                    tvValidate.setText("Tài khoản không tồn tại");
                }
            }
        });

        return view;
    }

    private void addViews(View view) {
        editAccount = (EditText) view.findViewById(R.id.edit_account);
        editPasswd = (EditText) view.findViewById(R.id.edit_passwd);
        tvValidate = (TextView) view.findViewById(R.id.tv_validate);
        btnLogin = (Button) view.findViewById(R.id.btn_login);
    }
}