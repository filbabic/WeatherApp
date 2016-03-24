package com.example.filip.weatherappmvpfinal.view.location.add;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;
import com.example.filip.weatherappmvpfinal.helpers.database.LocationDatabase;
import com.example.filip.weatherappmvpfinal.presenter.location.add.AddLocationPresenterImpl;
import com.example.filip.weatherappmvpfinal.R;

/**
 * Created by Filip on 10/02/2016.
 */
public class AddLocationFragment extends Fragment implements View.OnClickListener, AddLocationView {
    private AddLocationPresenterImpl presenter;
    private EditText mEnterLocationNameEditText;
    private Button mAddLocationButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_location, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        initPresenter();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.location_added_success_toast_message), Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    @Override
    public void onFailure() {
        Toast.makeText(getActivity().getApplicationContext(), R.string.location_already_exists_toast_message, Toast.LENGTH_SHORT).show();
    }

    private void initUI(View view) {
        mEnterLocationNameEditText = (EditText) view.findViewById(R.id.fragment_add_location_enter_city_edit_text);
        mAddLocationButton = (Button) view.findViewById(R.id.fragment_add_location_button);
        mAddLocationButton.setOnClickListener(this);
    }

    private void initPresenter() {
        LocationDatabase database = new LocationDatabase(getActivity());
        presenter = new AddLocationPresenterImpl(this, database);
    }

    @Override
    public void onClick(View v) {
        presenter.addLocationToDatabase(new LocationWrapper(mEnterLocationNameEditText.getText().toString()));
    }
}
