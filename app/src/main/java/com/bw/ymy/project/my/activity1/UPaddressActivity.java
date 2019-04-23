package com.bw.ymy.project.my.activity1;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.ymy.project.App.Apis;
import com.bw.ymy.project.R;
import com.bw.ymy.project.mvp.prensenter.IPresenter;
import com.bw.ymy.project.mvp.view.IView;
import com.bw.ymy.project.my.bean.UPaddressBean;
import com.lljjcoder.citypickerview.widget.CityPicker;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UPaddressActivity extends AppCompatActivity implements IView {

    @BindView(R.id.up_name1)
    EditText up_name1;

    @BindView(R.id.up_phone)
    EditText up_phone;

    @BindView(R.id.up_address)
    EditText up_address;

    @BindView(R.id.up_yb)
    EditText up_yb;

    @BindView(R.id.up_ok)
    Button up_ok;

    private  int id;
    IPresenter iPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upaddress);
        ButterKnife.bind(this);
        iPresenter=new IPresenter(this);
        //接受传值
             Intent intent=getIntent();
             id=intent.getIntExtra("id",0);
            String name=intent.getStringExtra("l1");
            String phone=intent.getStringExtra("l2");
            String address=intent.getStringExtra("l3");
            String code=intent.getStringExtra("l4");

        //赋值给所在的控件

            up_name1.setText(name);
            up_phone.setText(phone);
            up_address.setText(address);
            up_yb.setText(code);


    }

    @OnClick({R.id.up_address,R.id.up_ok})
    public  void setlick(View v)
    {
        switch (v.getId())
        {
            case R.id.up_ok:

                String n1=up_name1.getText().toString();
                String n2=up_phone.getText().toString();
                String n3=up_address.getText().toString();
                String n4=up_yb.getText().toString();

                Map<String,String> map=new HashMap<>();
                map.put("id",id+"");
                map.put("realName",n1+"");
                map.put("phone",n2+"");
                map.put("address",n3+"");
                map.put("zipCode",n4+"");

                iPresenter.put(Apis.UP,map,UPaddressBean.class);
                break;
            case R.id.up_address:
                initCityPicker();

                break;
                default:break;

        }
    }

    private void initCityPicker() {

        CityPicker cityPicker = new CityPicker.Builder(UPaddressActivity.this)
                //滚轮文字的大小
                .textSize(14)
                .title("地址选择")
                .titleBackgroundColor("#FFFFFF")
                .confirTextColor("#696969")
                .cancelTextColor("#696969")
                .province("北京市")
                .city("北京市")
                .district("朝阳区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();
        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String   city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];
                up_address.setText(province.trim() + "-" + city.trim() + "-" + district.trim());
            }

        });


    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof UPaddressBean)
        {
            UPaddressBean bean= (UPaddressBean) data;
            if(bean.getMessage().equals("修改成功"))
            {
                Toast.makeText(this, bean.getMessage()+"", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(UPaddressActivity.this,AddressActivity.class);
                startActivity(intent);
            }else
            {
                Toast.makeText(this, bean.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.detach();
    }
}
