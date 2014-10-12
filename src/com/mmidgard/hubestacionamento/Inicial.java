package com.mmidgard.hubestacionamento;

import internet.WSClient;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.mmidgard.hubestacionamento.models.Carro;

public class Inicial extends GlobalActivity implements OnItemClickListener {

	private AdapterListExercicios adapterExercicios;
	private ListView listViewExercicios;
	private List<Carro> listaCarros;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.inicial);

		WSClient.setAuth("Basic " + Base64.encodeToString(("mmidgard@outlook.com:finadoalbano").getBytes(), Base64.NO_WRAP));

		header();

		listaCarros = new ArrayList<Carro>();
		criarCarrosTeste();
		criarLista(listaCarros);

		adicionarRecarga();
	}

	private void header() {
		TextView titulo = (TextView)findViewById(R.id.titulo);
		Button addCarro = (Button)findViewById(R.id.add_carro);
		titulo.setText("Garagem");
		addCarro.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final Dialog dialog = new Dialog(Inicial.this);
				dialog.setTitle("Adicionar carro");
				dialog.setContentView(R.layout.add_carro);
				dialog.setCancelable(true);
				dialog.show();
			}
		});
	}

	private void criarCarrosTeste() {
		Carro c = new Carro("abc-1234", "gol branco", true);
		Carro c2 = new Carro("abc-1234", "gol branco", true);
		Carro c3 = new Carro("abc-1234", "gol branco", true);
		Carro c4 = new Carro("abc-1234", "gol branco", true);

		listaCarros.add(c);
		listaCarros.add(c2);
		listaCarros.add(c3);
		listaCarros.add(c4);
	}

	private void criarLista(List<Carro> elementList) {
		adapterExercicios = new AdapterListExercicios(this, listaCarros);

		listViewExercicios = (ListView)findViewById(R.id.list_carros);
		listViewExercicios.setAdapter(adapterExercicios);
		listViewExercicios.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent i = new Intent(Inicial.this, CarroSelecionado.class);
		Carro c = (Carro)adapterExercicios.getItem(arg2);
		i.putExtra("carro", c);
		startActivity(i);
	}
}
