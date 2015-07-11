package com.inhand.milk.fragment.person_center;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.inhand.milk.R;
import com.inhand.milk.activity.PersonCenterBabyInfoActivity;
import com.inhand.milk.activity.UserInfoSettingsActivity;
import com.inhand.milk.fragment.TitleFragment;
import com.inhand.milk.fragment.user_info_settings.UserinfoNameFragment;
import com.inhand.milk.utils.PopupWindowSelected;

import java.util.Calendar;

/**
 * Created by Administrator on 2015/7/7.
 */
public class PersonCenterBabyFragment extends TitleFragment {
    private RelativeLayout head,name,sex,birth;
    private int clickColor,unclickColor = Color.WHITE;
    private View.OnTouchListener  onTouchListener;
    private PopupWindowSelected headPopupWiondow,sexPopupWindow;
    private String man,woman;
    private int year,monthOfyear,dayOfmonth;
    private TextView babySexTextView,babynameTextView,babyBirthTextView;
    private DatePickerDialog datePickerDialog;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.person_center_baby_info, container, false);
        setTitleview(getResources().getString(R.string.user_info_title), 2);

        clickColor = getResources().getColor(R.color.common_settings_item_click_bg_color);
        onTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    v.setBackgroundColor(clickColor);
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    v.setBackgroundColor(unclickColor);
                }
                else if (event.getAction() == MotionEvent.ACTION_MOVE){
                    v.setBackgroundColor(unclickColor);
                }
                return false;
            }
        };
        initView(mView);
        initTextViews();
        return mView;
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden == false){
            initTextViews();
        }
    }
    private void initView(View view){
        head = (RelativeLayout)view.findViewById(R.id.person_center_baby_head_container);
        name = (RelativeLayout)view.findViewById(R.id.person_center_name_container);
        sex = (RelativeLayout)view.findViewById(R.id.person_center_sex_container);
        birth = (RelativeLayout)view.findViewById(R.id.person_center_birth_container);
        man = getResources().getString(R.string.user_info_popupwindow_sex_man);
        woman = getResources().getString(R.string.user_info_popupwindow_sex_woman);

        babyBirthTextView = (TextView)view.findViewById(R.id.person_center_baby_birth_textview);
        babynameTextView = (TextView)view.findViewById(R.id.person_center_baby_name_textview);
        babySexTextView = (TextView)view.findViewById(R.id.person_center_baby_info_sex_textview);

        setHead(head);
        setSex(sex);
        setBirth(birth);
        setname(name);
    }
    private void initTextViews(){
        String text;
        String noData = getResources().getString(R.string.user_info_fix_no_data);
        text = ((PersonCenterBabyInfoActivity)getActivity()).getName();
        if(text == null)
            text = noData;
        babynameTextView.setText(text);

        text = ((PersonCenterBabyInfoActivity)getActivity()).getSex();
        if(text == null)
            text = noData;
        babySexTextView.setText(text);

        text = ((PersonCenterBabyInfoActivity)getActivity()).getBirth();
        if(text == null)
            text = noData;
        babyBirthTextView.setText(text);
    }
    private void setHead(final RelativeLayout head){
        headPopupWiondow = new PopupWindowSelected(getActivity());
        headPopupWiondow.setFirstListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((UserInfoSettingsActivity)getActivity()).choseHeadImageFromGallery();
                headPopupWiondow.dismiss();
            }
        });
        headPopupWiondow.setSecondeListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((UserInfoSettingsActivity) getActivity()).choseHeadImageFromCameraCapture();
                headPopupWiondow.dismiss();
            }
        });
        headPopupWiondow.setFirstItemText(getActivity().getResources().getString(R.string.user_info_popupwindow_choose_photo));
        headPopupWiondow.setSecondeItemText(getActivity().getResources().getString(R.string.user_info_popupwindow_create_photo));

        head.setOnTouchListener(onTouchListener);
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headPopupWiondow.show();
            }
        });
    }
    private void setSex(final RelativeLayout sex){
        sexPopupWindow = new PopupWindowSelected(getActivity());
        sexPopupWindow.setFirstItemText(man);
        sexPopupWindow.setSecondeItemText(woman);
        sexPopupWindow.setFirstListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                babySexTextView.setText(man);
                ((PersonCenterBabyInfoActivity)getActivity()).setSex(man);
                sexPopupWindow.dismiss();
            }
        });
        sexPopupWindow.setSecondeListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                babySexTextView.setText(woman);
                ((PersonCenterBabyInfoActivity)getActivity()).setSex(woman);
                sexPopupWindow.dismiss();
            }
        });
        sex.setOnTouchListener(onTouchListener);
        sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sexPopupWindow.show();
            }
        });
    }
    private void setBirth(final RelativeLayout birth){
        birth.setOnTouchListener(onTouchListener);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        monthOfyear = calendar.get(Calendar.MONTH)+1;
        dayOfmonth = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(this.getActivity(),new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String str = String.valueOf(year)+"年"+String.valueOf(monthOfYear)+"月"+String.valueOf(dayOfMonth)+"日";
                babyBirthTextView.setText(str);
            }
        },year,monthOfyear,dayOfmonth);
        birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    datePickerDialog.show();
            }
        });
    }
    private void setname(final RelativeLayout name){
        name.setOnTouchListener(onTouchListener);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoSpecialFragment(new UserinfoNameFragment());
            }
        });
    }
}