package com.mmidgard.hubestacionamento;

import android.app.Activity;
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

				if (senha.equals(confirmar)) {
					
				} else {
					Toast.makeText(Perfil.this, "Senha e confirmar não conferem", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
