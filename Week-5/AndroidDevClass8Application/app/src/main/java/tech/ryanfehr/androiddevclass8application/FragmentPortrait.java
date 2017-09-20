package tech.ryanfehr.androiddevclass8application;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

/**
 * Created by Ryan on 9/18/2017.
 */

public class FragmentPortrait extends Fragment {

    OnColorChangeListener colorCallback;

    public interface OnColorChangeListener {
        public void colorChanged( String colorName);
    }

    RadioGroup colorRadioGroup;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.portrait_layout, container, false);
        RadioGroup colorRadioGroup = (RadioGroup) view.findViewById(R.id.colorRadioGroup);
        colorRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switch(checkedId) {
                    case R.id.redRadioButton:
                        colorCallback.colorChanged("Red");
                        break;
                    case R.id.greenRadioButton:
                        colorCallback.colorChanged("Green");
                        break;
                    case R.id.blueRadioButton:
                        colorCallback.colorChanged("Blue");
                        break;
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            colorCallback  = (OnColorChangeListener) getActivity();
        }
        catch (Exception e) {
            throw new ClassCastException(context.toString()+ " must implement OnColorChangeListener");
        }
    }
}
