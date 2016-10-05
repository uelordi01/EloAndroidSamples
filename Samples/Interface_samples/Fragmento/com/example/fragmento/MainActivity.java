package com.example.fragmento;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class MainActivity extends FragmentActivity implements Fragment_Lista.Callbacks {
	private boolean dosFragmentos;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_activity_principal_listado);
		if (findViewById(R.id.framelayout_contenedor_detalle) != null) {
			dosFragmentos = true;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onEntradaSelecionada(String id) {
		if (dosFragmentos) {
			Bundle arguments = new Bundle();
			arguments.putString(DetalleFrame.ARG_ID_ENTRADA_SELECIONADA, id);
			DetalleFrame fragment = new DetalleFrame();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_contenedor_detalle, fragment).commit();

		} else {
			Intent detailIntent = new Intent(this, Activity_detalle.class);
			detailIntent.putExtra(DetalleFrame.ARG_ID_ENTRADA_SELECIONADA, id);
			startActivity(detailIntent);
		}
	}

}
