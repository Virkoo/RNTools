package com.virc.dusan.railnationtools;

import java.lang.reflect.Modifier;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentOne extends Fragment {

	ImageView ivIcon;
	TextView tvItemName;

	public static final String IMAGE_RESOURCE_ID = "iconResourceID";
	public static final String ITEM_NAME = "itemName";
	
	
	EditText etBrojVagona, etBrojVozova, etBonusPoVagonu, etVremeRute;
	TextView tvPrikazRezultata;
	int mBrojVagona, mBrojVozova, mBonusPoVagonu, mVremeRute, mPrikazRezultata;
	Button bIzracunaj;
	private final String FILENAME = "hello_file";
	private final String BROJ_VAGONA = "brojVagona";
	private final String BROJ_VOZOVA = "brojVozova";
	private final String BONUS_PO_VAGONU = "bonusPoVagonu";
	private final String VREME_RUTE = "vremeRute";


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_layout_one, container,
				false);
		
		// dodat kod	
		
		final SharedPreferences pref = getActivity().getSharedPreferences(FILENAME, Modifier.PRIVATE);	
		final SharedPreferences.Editor mojiPodaci = pref.edit();
		
		etBrojVagona = (EditText) view.findViewById(R.id.etBrojVagona);
		etBrojVozova = (EditText) view.findViewById(R.id.etBrojVozova);
		etBonusPoVagonu = (EditText) view.findViewById(R.id.etBonusPoVagonu);
		etVremeRute = (EditText) view.findViewById(R.id.etVremeZaTuru);
		tvPrikazRezultata = (TextView) view
				.findViewById(R.id.tfPrikazRezultata);
		bIzracunaj = (Button) view.findViewById(R.id.bIzracunaj);

		etBonusPoVagonu.setText(String.valueOf(pref.getInt(BONUS_PO_VAGONU, 0)));
		etBrojVagona.setText(String.valueOf(pref.getInt(BROJ_VAGONA, 0)));
		etBrojVozova.setText(String.valueOf(pref.getInt(BROJ_VOZOVA, 0)));
		if (pref.getInt(VREME_RUTE, 0) == 0){
			
		}else etVremeRute.setText(String.valueOf(pref.getInt(VREME_RUTE, 0)));

		
		bIzracunaj.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {
					mBrojVagona = Integer.parseInt(etBrojVagona.getText()
							.toString());
					mojiPodaci.putInt(BROJ_VAGONA, mBrojVagona);
					mBrojVozova = Integer.parseInt(etBrojVozova.getText()
							.toString());
					mojiPodaci.putInt(BROJ_VOZOVA, mBrojVozova);
					mBonusPoVagonu = Integer.parseInt(etBonusPoVagonu.getText()
							.toString());
					mojiPodaci.putInt(BONUS_PO_VAGONU, mBonusPoVagonu);
					mVremeRute = Integer.parseInt(etVremeRute.getText()
							.toString());
					mojiPodaci.putInt(VREME_RUTE, mVremeRute);
					mojiPodaci.commit();
				} catch (Exception e) {
					Toast.makeText(v.getContext(),
							"Unesite samo brojeve u prazna polja",
							Toast.LENGTH_LONG).show();
				}
				
				if (mVremeRute == 0) {
					Toast.makeText(getActivity(), "Vreme rute ne sme biti nula", Toast.LENGTH_LONG).show();
					return;
				}

				mPrikazRezultata = (3600 / mVremeRute) * mBrojVagona
						* mBrojVozova * mBonusPoVagonu;
				String temp = "(" + mPrikazRezultata + "/h) *48 = "
						+ String.valueOf(mPrikazRezultata * 48);
				tvPrikazRezultata.setText(temp);
			}
		});

		// zavrsen dodat kod

		ivIcon = (ImageView) view.findViewById(R.id.frag1_icon);
		tvItemName = (TextView) view.findViewById(R.id.frag1_text);

		tvItemName.setText(getArguments().getString(ITEM_NAME));
		ivIcon.setImageDrawable(view.getResources().getDrawable(
				getArguments().getInt(IMAGE_RESOURCE_ID)));
		return view;
	}

}