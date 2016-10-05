package com.example.fragmento;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class Activity_detalle extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_activity_detalle);

		// Comprobamos que previamente no hayamos entrado en esta actividad (por ejemplo, al rotar el dispositivo). Si es así se añade el fragmento al contenedor
		if (savedInstanceState == null) {
			// Crea el fragmento del detalle de la entrada y lo añade a la actividad
			Bundle arguments = new Bundle();
			arguments.putString(DetalleFrame.ARG_ID_ENTRADA_SELECIONADA, getIntent().getStringExtra(DetalleFrame.ARG_ID_ENTRADA_SELECIONADA));
			DetalleFrame fragment = new DetalleFrame();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction().add(R.id.framelayout_contenedor_detalle, fragment).commit();
		}
	}

}