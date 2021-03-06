package com.example.greentreeonline.Admin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.greentreeonline.Main.Bill.MainStatusBill;
import com.example.greentreeonline.Main.Login.MainLogin;
import com.example.greentreeonline.Main.Login.MainUpdateProfile;
import com.example.greentreeonline.Main.MainMap;
import com.example.greentreeonline.Navigation;
import com.example.greentreeonline.R;

import org.simple.eventbus.EventBus;

public class admin extends Fragment {

    TextView tk, sp,hd;
    public static TextView hoten1, gioitinh, date, mail, sdt1, dc, cho;
    private TextView btndangxuat, dcs, btndangnhap, update,xemlsdh,like;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    //////////////////////////////////
    private SharedPreferences sharedPreferences1;
    private SharedPreferences.Editor editor1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_admin, container, false);
        tk = view.findViewById(R.id.thongke);
        sp = view.findViewById(R.id.adsanpham);
        hd= view.findViewById(R.id.hoadon);
        hoten1 = view.findViewById(R.id.hotennguoidung);
//        gioitinh = view.findViewById(R.id.gioitinh);
//        date = view.findViewById(R.id.ngaysinh);
//        mail = view.findViewById(R.id.gmail);
//        sdt1 = view.findViewById(R.id.tv_sdt);
//        dc = view.findViewById(R.id.tv_diachi);
        btndangxuat = view.findViewById(R.id.btndangxuat);
        btndangnhap = view.findViewById(R.id.btndangnhap);
        dcs = view.findViewById(R.id.dcshop);
        update= view.findViewById(R.id.updatetk);
        xemlsdh = view.findViewById(R.id.btndonhang);
        like = view.findViewById(R.id.txtlike);
        cho = view.findViewById(R.id.tinh);

        sharedPreferences = getContext().getSharedPreferences("luutaikhoan", getContext().MODE_PRIVATE);
        editor = sharedPreferences.edit();
        ////////////////////////////////////////////
        sharedPreferences1 = getContext().getSharedPreferences("chitiet", getContext().MODE_PRIVATE);
        editor1 = sharedPreferences1.edit();
        click();


CheckData();
        diachishop();
        checkdangnhap();
        dangnhap();
        dangxuat();
        return view;

    }

    public void click() {
        tk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), thongke.class);
                startActivity(intent);
            }
        });
        sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), sanpham.class);
                startActivity(intent);
            }
        });
        hd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainStatusBill.class);
                startActivity(intent);
            }
        });
    }

    public void diachishop() {
        dcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainMap.class);
                startActivity(intent);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainUpdateProfile.class);
                startActivity(intent);
            }
        });
    }

    public void checkdangnhap() {
        String TenTk = sharedPreferences.getString("taikhoan", "");
        if (!TextUtils.isEmpty(TenTk)) {
            btndangxuat.setVisibility(View.VISIBLE);
            btndangnhap.setVisibility(View.INVISIBLE);
        } else {
            Toast.makeText(getContext(), "B???n c???n ????ng nh???p", Toast.LENGTH_SHORT).show();
            //  startActivity(new Intent(getActivity(),dangnhap.class));
            btndangxuat.setVisibility(View.INVISIBLE);
            btndangnhap.setVisibility(View.VISIBLE);

            // finish();
        }
    }
    public void dangnhap() {
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainLogin.class);
                startActivity(intent);
            }
        });
    }

    public void dangxuat() {

        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.clear();
                editor.commit();

                EventBus.getDefault().post(true, "loginSuccess");
                Intent intent = new Intent(getContext(), Navigation.class);
                startActivity(intent);

                Toast.makeText(getContext(), "B???n ???? ????ng Xu???t! ", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void CheckData() {
        String ten = sharedPreferences.getString("taikhoan", "");

        if (!TextUtils.isEmpty(ten)) {
            hoten1.setText(sharedPreferences.getString("hoten", ""));


            //  tt.setImageBitmap(sharedPreferences.getString("imgtk",));
//            Picasso.get().load(sharedPreferences.getString("imgtk",""));

        } else {
            hoten1.setText("T??n ng?????i d??ng");
//            gioitinh.setText("");
//            date.setText("");
//            mail.setText("");
//            sdt1.setText("");
//            dc.setText("");

        }
    }
}