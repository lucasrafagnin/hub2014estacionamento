package com.mmidgard.hubestacionamento;

import internet.ServiceError;
import internet.WSClient;

import java.util.ArrayList;
import java.util.List;

import models.Statuss;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.style.UpdateLayout;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mmidgard.hubestacionamento.dao.CarroDAO;
import com.mmidgard.hubestacionamento.dao.MotoristaDAO;
import com.mmidgard.hubestacionamento.models.Carro;
import com.mmidgard.hubestacionamento.models.Motorista;

public class Inicial extends GlobalActivity implements OnItemClickListener {

	private AdapterListExercicios adapterExercicios;
	private ListView listViewExercicios;
	private List<Carro> listaCarros;
	private Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.inicial);

		Config.configSenha();
		header();

		listaCarros = new ArrayList<Carro>();
		criarLista();

		adicionarRecarga();
	}

	private void header() {
		TextView titulo = (TextView)findViewById(R.id.titulo);
		Button addCarro = (Button)findViewById(R.id.add_carro);
		titulo.setText("Garagem");
		addCarro.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog = new Dialog(Inicial.this);
				dialog.setTitle("Adicionar carro");
				dialog.setContentView(R.layout.add_carro);
				dialog.setCancelable(true);
				dialog.show();

				final EditText nome = (EditText)dialog.findViewById(R.id.carro_nome);
				final EditText placa = (EditText)dialog.findViewById(R.id.carro_placa);
				Button confirmar = (Button)dialog.findViewById(R.id.carro_adicionar);

				confirmar.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						String stNome = nome.getText().toString();
						String stPlaca = placa.getText().toString();

						if (!stNome.equals("") && !stPlaca.equals("")) {
							enviarCarroProServidor(stNome, stPlaca);
						} else {
							Toast.makeText(Inicial.this, "Os campos são obrigatórios", Toast.LENGTH_SHORT).show();
						}
					}
				});
			}
		});
	}

	private void criarLista() {
		listaCarros = new CarroDAO(Inicial.this).getAll();
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

	private void enviarCarroProServidor(final String nome, final String placa) {

		new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				MotoristaDAO mdao = new MotoristaDAO(Inicial.this);
				Motorista m = mdao.getAll().get(0);
				try {
					Statuss s = WSClient.criarCarro(nome, placa, m.getId());
					gravarCarro(s.id, placa, nome);
				} catch (ServiceError e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				Toast.makeText(Inicial.this, "Carro adicionado com sucesso", Toast.LENGTH_SHORT).show();
				criarLista();
				dialog.dismiss();
			}
		}.execute();
	}

	public void gravarCarro(long id, String placa, String nome) {
		CarroDAO cdao = new CarroDAO(Inicial.this);
		Carro c = new Carro(id, placa, nome);
		cdao.insert(c);
	}
}
