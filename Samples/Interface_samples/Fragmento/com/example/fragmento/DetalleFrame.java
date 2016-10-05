package com.example.fragmento;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



public class DetalleFrame extends Fragment {
	private final String LOG_TAG = "test";
	public static final String ARG_ID_ENTRADA_SELECIONADA = "item_id";
	private Lista_Contenido.Lista_entrada mItem;
	public DetalleFrame() {
	}

	@Override
	public void onAttach (Activity activity) {
		super.onAttach(activity);
		Log.v(LOG_TAG, "onAttach");
	}

	@Override
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v(LOG_TAG, "onCreate");
		if (getArguments().containsKey(ARG_ID_ENTRADA_SELECIONADA)) {
			//Cargamos el contenido de la entrada con cierto ID seleccionado en la lista. Se recomiendo usar un Loader para cargar el contenido
			mItem = Lista_Contenido.ENTRADAS_LISTA_HASHMAP.get(getArguments().getString(ARG_ID_ENTRADA_SELECIONADA));
		}
	}

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		Log.v(LOG_TAG, "onCreateView");
		View rootView = inflater.inflate(R.layout.layout_fragment_detalle, container, false);
		if (mItem != null) {
			((TextView)rootView.findViewById(R.id.textView_superior)).setText(mItem.textoEncima);
			((TextView)rootView.findViewById(R.id.textView_inferior)).setText(mItem.textoDebajo);
			((ImageView)rootView.findViewById(R.id.imageView_imagen)).setImageResource(mItem.idImagen);
		}

		/* Aquí podemos seleccionar las Views contenidas en el Layout para trabajar con ellas, por ejemplo con:
		 * TipoView miView = (TipoView) rootView.findViewById(R.id.miViewXML);
		 */
		return rootView;
	}

	@Override
	public void onActivityCreated (Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.v(LOG_TAG, "onActivityCreated");
	}

	@Override
	public void onViewStateRestored (Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
		Log.v(LOG_TAG, "onViewStateRestored");
	}

	@Override
	public void onStart () {
		super.onStart();
		Log.v(LOG_TAG, "onStart");
	}

	@Override
	public void onResume () {
		super.onResume();
		Log.v(LOG_TAG, "onResume");
	}

	@Override
	public void onPause () {
		super.onPause();
		Log.v(LOG_TAG, "onPause");
	}

	@Override
	public void onStop () {
		super.onStop();
		Log.v(LOG_TAG, "onStop");
	}

	@Override
	public void onDestroyView () {
		super.onDestroyView();
		Log.v(LOG_TAG, "onDestroyView");
	}

	@Override
	public void onDestroy () {
		super.onDestroy();
		Log.v(LOG_TAG, "onDestroy");
	}

	@Override
	public void onDetach () {
		super.onDetach();
		Log.v(LOG_TAG, "onDetach");
	}

}
