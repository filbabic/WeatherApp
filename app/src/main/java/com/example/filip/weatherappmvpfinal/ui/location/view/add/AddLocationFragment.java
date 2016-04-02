package com.example.filip.weatherappmvpfinal.ui.location.view.add;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.filip.weatherappmvpfinal.helpers.database.DataManagerImpl;
import com.example.filip.weatherappmvpfinal.helpers.database.DatabaseHelperImpl;
import com.example.filip.weatherappmvpfinal.helpers.database.LocationDatabase;
import com.example.filip.weatherappmvpfinal.ui.location.presenter.add.AddLocationFragmentPresenter;
import com.example.filip.weatherappmvpfinal.ui.location.presenter.add.AddLocationPresenterImpl;
import com.example.filip.weatherappmvpfinal.R;

/**
 * Created by Filip on 10/02/2016.
 */
public class AddLocationFragment extends Fragment implements View.OnClickListener, AddLocationView {
    private AddLocationFragmentPresenter presenter;
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
    public void onLocationAlreadyExistsError() {
        mEnterLocationNameEditText.setError(getActivity().getString(R.string.location_already_exists_error_message));
    }

    @Override
    public void onEmptyStringRequestError() {
        mEnterLocationNameEditText.setError(getActivity().getString(R.string.empty_location_string_error_message));
    }

    private void initUI(View view) {
        mEnterLocationNameEditText = (EditText) view.findViewById(R.id.fragment_add_location_enter_city_edit_text);
        mAddLocationButton = (Button) view.findViewById(R.id.fragment_add_location_button);
        mAddLocationButton.setOnClickListener(this);
    }

    private void initPresenter() {
        LocationDatabase database = new LocationDatabase(getActivity());
        presenter = new AddLocationPresenterImpl(this, new DatabaseHelperImpl(database, null, new DataManagerImpl()));
    }

    @Override
    public void onClick(View v) {
        if (v == mAddLocationButton) {
            presenter.addLocationToDatabase(mEnterLocationNameEditText.getText().toString());
        }
    }
}
