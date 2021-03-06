package com.inhand.milk.fragment.user_info_settings;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inhand.milk.R;
import com.inhand.milk.activity.UserInfoSettingsActivity;
import com.inhand.milk.ui.FixInfoBaseFragment;

/**
 * Created by Administrator on 2015/7/3.
 */
public class UserinfoNameFragment extends FixInfoBaseFragment {
    private View.OnClickListener listener;
    public UserinfoNameFragment(View.OnClickListener listener){
        this.listener  = listener;
    }
    public UserinfoNameFragment(){
        this.listener =null;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setTitleview(getResources().getString(R.string.user_info_fix_name_title_text), 2, "完成", listener);
        setHintString(getResources().getString(R.string.user_info_fix_name_hint_text));
        setLeftText(getResources().getString(R.string.user_info_fix_name_left_text));
        String name = ((UserInfoSettingsActivity) getActivity()).getName();
        if (name == null)
            name = "";
        setString(name);
        return mView;
    }
}
