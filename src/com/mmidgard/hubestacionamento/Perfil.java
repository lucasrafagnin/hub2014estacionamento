package com.mmidgard.hubestacionamento;

import internet.ServiceError;
import internet.WSClient;
import models.Statuss;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mmidgard.hubestacionamento.dao.MotoristaDAO;
import com.mmidgard.hubestacionamento.models.Motorista;

public class Perfil extends Activity {

	private EditText nome;
	private EditText usuario;
	private EditText senha;
	private EditText confirmar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.perfil);

		Config.configSenha();
		Button perfil = (Button)findViewById(R.id.criar_perfil);
		perfil.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				nome = (EditText)findViewById(R.id.perfil_nome);
				usuario = (EditText)findViewById(R.id.perfil_usuario);
				senha = (EditText)findViewById(R.id.perfil_senha);
				confirmar = (EditText)findViewById(R.id.perfil_confirmar);

				final String stNome = nome.getText().toString();
				final String stUsuario = usuario.getText().toString();
				final String stSenha = senha.getText().toString();

				if ((stNome.equals("")) || stUsuario.equals("")) {
					Toast.makeText(Perfil.this, "Os campos são obrigatórios", Toast.LENGTH_SHORT).show();
				} else {
					if (!stSenha.equals(confirmar.getText().toString())) {
						Toast.makeText(Perfil.this, "Senha e confirmar não conferem", Toast.LENGTH_SHORT).show();
					} else {
						enviarPerfilProServidor(stUsuario, stSenha, stNome);
					}
				}
			}
		});

	}

	private void enviarPerfilProServidor(final String usuario, final String senha, final String nome) {
		new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				try {
					Statuss s = WSClient.criarPerfil(usuario, senha, nome);
					criarMotoristaBD(s.id);
				} catch (ServiceError e) {
					e.printStackTrace();
				}
				return null;
			}

			protected void onPostExecute(Void result) {
				Toast.makeText(Perfil.this, "Perfil criado com sucesso!", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(Perfil.this, Inicial.class);
				startActivity(i);
			}

			private void criarMotoristaBD(Long id) {
				MotoristaDAO mdao = new MotoristaDAO(Perfil.this);
				Motorista m = new Motorista();
				m.setId(id);
				mdao.insert(m);
			};
		}.execute();
	}

}
