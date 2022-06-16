package com.thaiduong.myapplication.login_register.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.thaiduong.myapplication.R;
import com.thaiduong.myapplication.data_local.DataLocalManager;
import com.thaiduong.myapplication.login_register.model.AppAccount;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {

    private EditText editName, editAccount, editPasswd, editRePasswd;
    private TextView tvBirthday, tvValidateName, tvValidateAccount, tvValidatePasswd
            , tvValidateRePasswd, tvValidateBirthday;
    private RadioGroup rgGender;
    Button btnRegister;

    String gender;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        addViews(view);

        tvBirthday.setOnClickListener(this);
        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbGender = (RadioButton) view.findViewById(checkedId);
                rbGender.setTextColor(getResources().getColor(R.color.purple_200));
                gender = rbGender.getText().toString();
            }
        });
        btnRegister.setOnClickListener(this);

        return view;
    }

    private void addViews(View view) {
        editName = (EditText) view.findViewById(R.id.edit_name);
        editAccount = (EditText) view.findViewById(R.id.edit_account);
        editPasswd = (EditText) view.findViewById(R.id.edit_passwd);
        editRePasswd = (EditText) view.findViewById(R.id.edit_re_passwd);
        tvBirthday = (TextView) view.findViewById(R.id.tv_birthday);
        tvValidateName = (TextView) view.findViewById(R.id.tv_validate_name);
        tvValidateAccount = (TextView) view.findViewById(R.id.tv_validate_account);
        tvValidatePasswd = (TextView) view.findViewById(R.id.tv_validate_passwd);
        tvValidateRePasswd = (TextView) view.findViewById(R.id.tv_validate_re_passwd);
        tvValidateBirthday = (TextView) view.findViewById(R.id.tv_validate_birthday);
        rgGender = (RadioGroup) view.findViewById(R.id.rgGender);
        btnRegister = (Button) view.findViewById(R.id.btn_register);
        gender = "Nam";
    }

    @SuppressLint("NonConstantResourceId")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_birthday:
                setBirthday();
                break;
            case R.id.btn_register:
                InputMethodManager methodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                methodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                if (validate(editName, editAccount, editPasswd, editRePasswd, tvBirthday)) {
                    AppAccount account = new AppAccount(editName.getText().toString().trim()
                            , editAccount.getText().toString().trim(), editPasswd.getText().toString()
                            , tvBirthday.getText().toString(), gender);
                    notifySuccess("Đăng ký", "Chúc mừng bạn đã đăng ký thành công");
                    DataLocalManager.setAccount(account);
                }
                break;
        }
    }

    private void notifySuccess(String title, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(content);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setBirthday() {
        LocalDate localDate = LocalDate.now();
        DatePickerDialog pickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String strDay = dayOfMonth + "/";
                if (month < 10) {
                    strDay += "0" + (month + 1);
                } else {
                    strDay += (month + 1) + "";
                }
                strDay += "/" + year;
                tvBirthday.setText(strDay);
            }
        }, localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth());
        pickerDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean validate(EditText editName, EditText editAccount, EditText editPasswd, EditText editRePasswd, TextView editBirthday) {
        //Name
        String name = editName.getText().toString().trim();
        if (name.length() < 10) {
            tvValidateName.setText("Họ tên phải ít nhất có độ dài 10 ký tự");
            return false;
        } else {
            tvValidateName.setText("");
        }
        if (!name.matches("^[\\p{L} .'-]+$")) {
            tvValidateName.setText("Họ tên chỉ được gồm các chữ cái");
            return false;
        } else {
            tvValidateName.setText("");
        }
        //Account
        String account = editAccount.getText().toString().trim();
        if (!Patterns.EMAIL_ADDRESS.matcher(account).matches()) {
            tvValidateAccount.setText("Địa chỉ email không hợp lệ");
            return false;
        } else {
            tvValidateAccount.setText("");
        }
        //Password
        String passwd = editRePasswd.getText().toString();
        if (passwd.length() < 8) {
            tvValidatePasswd.setText("Mật khẩu phải có ít nhất 8 ký tự");
            return false;
        } else {
            tvValidatePasswd.setText("");
        }
        if (passwd.matches("[^A-Z]+$")) {
            tvValidatePasswd.setText("Mật khẩu phải có ít nhất một chữ in hoa");
            return false;
        } else {
            tvValidatePasswd.setText("");
        }
        if (passwd.matches("[^\\d]+$")) {
            tvValidatePasswd.setText("Mật khẩu phải có ít nhất một chữ số");
            return false;
        } else {
            tvValidatePasswd.setText("");
        }
        //Re-Password
        String rePasswd = editRePasswd.getText().toString();
        if (!rePasswd.equals(passwd)) {
            tvValidateRePasswd.setText("Mật khẩu không trùng khớp");
            return false;
        } else {
            tvValidateRePasswd.setText("");
        }
        //Birthday
        String birthday = tvBirthday.getText().toString();
        if (birthday.length() == 0) {
            tvValidateBirthday.setText("Ngày sinh không được để trống");
            return false;
        } else {
            tvValidateBirthday.setText("");
        }
        String localDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        int birthdayYear = Integer.parseInt(birthday.split("/")[2]);
        int nowYear = Integer.parseInt(localDate.split("/")[2]);
        if (birthdayYear > nowYear - 10) {
            tvValidateBirthday.setText("Ngày sinh không hợp lệ");
            return false;
        } else {
            tvValidateBirthday.setText("");
        }
        return true;
    }

}