package com.bw.ymy.project.my.activity1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ymy.project.App.Apis;
import com.bw.ymy.project.R;
import com.bw.ymy.project.mvp.prensenter.IPresenter;
import com.bw.ymy.project.mvp.view.IView;
import com.bw.ymy.project.my.bean.AddBean;
import com.lljjcoder.citypickerview.widget.CityPicker;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewAddressActivity extends AppCompatActivity implements IView {

    @BindView(R.id.address)
    ImageView address;

    @BindView(R.id.dizhi)
    TextView dizhi;

    @BindView(R.id.et_name)
    EditText et_name;

    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_xxdz)
    EditText et_xxdz;

    @BindView(R.id.et_youbian)
    EditText et_youbian;

    @BindView(R.id.ok)
    Button ok;

IPresenter iPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_address);
        ButterKnife.bind(this);
        iPresenter=new IPresenter(this);
    }

    @OnClick({R.id.address,R.id.ok})
    public  void  setlick(View view)
    {
        switch (view.getId())
        {
            case R.id.address:

                initCityPicker();

                break;
            case R.id.ok:
               String name= et_name.getText().toString();
                String phone= et_phone.getText().toString();
                String xxdz=et_xxdz.getText().toString();

                String di= dizhi.getText().toString();
                String you= et_youbian.getText().toString();
                        //拼接
                String qq=String.format(dizhi.getText().toString()+et_xxdz.getText().toString());



               Map<String,String> map=new HashMap<>();
                map.put("realName",name);
                map.put("phone",phone);
                map.put("address",qq);
                map.put("zipCode",you);
            iPresenter.post(Apis.ADD,map,AddBean.class);
                break;
        }
    }

    private void initCityPicker() {
        CityPicker cityPicker = new CityPicker.Builder(NewAddressActivity.this)
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
                dizhi.setText(province.trim() + "-" + city.trim() + "-" + district.trim());
            }

        });

    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof AddBean)
        {
            AddBean addBean= (AddBean) data;
            Toast.makeText(this, addBean.getMessage()+"", Toast.LENGTH_SHORT).show();
        }
    }
}
