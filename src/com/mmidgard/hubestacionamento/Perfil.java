package com.mmidgard.hubestacionamento;

import internet.ServiceError;
import internet.WSClient;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

		Button perfil = (Button)findViewById(R.id.criar_perfil);
		perfil.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				nome = (EditText)findViewById(R.id.perfil_nome);
				usuario = (EditText)findViewById(R.id.perfil_usuario);
				senha = (EditText)findViewById(R.id.perfil_senha);
				confirmar = (EditText)findViewById(R.id.perfil_confirmar);

				if ((nome.getText().toString().equals("")) || usuario.getText().toString().equals("")) {
					Toast.makeText(Perfil.this, "Os campos são obrigatórios", Toast.LENGTH_SHORT).show();
				} else if (!senha.getText().toString().equals(confirmar.getText().toString())) {
					Toast.makeText(Perfil.this, "Senha e confirmar não conferem", Toast.LENGTH_SHORT).show();
				} else {
					new AsyncTask<Void, Void, Void>() {
						@Override
						protected Void doInBackground(Void... params) {
							try {
								WSClient.criarPerfil(usuario.getText().toString(), senha.getText().toString(), nome.getText().toString());
							} catch (ServiceError e) {
								e.printStackTrace();
							}
							return null;
						}

						protected void onPostExecute(Void result) {
							Toast.makeText(Perfil.this, "Perfil criado com sucesso!", Toast.LENGTH_SHORT).show();
						};
					}.execute();
				}
			}
		});
	}
}
