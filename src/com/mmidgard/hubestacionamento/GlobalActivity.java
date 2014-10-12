package com.mmidgard.hubestacionamento;

import models.Statuss;

import com.mmidgard.hubestacionamento.dao.MotoristaDAO;
import com.mmidgard.hubestacionamento.models.Motorista;

import internet.ServiceError;
import internet.WSClient;
import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GlobalActivity extends Activity {

	private TextView saldoAtual;
	private TextView novoSaldo;

	public void adicionarRecarga() {
		Button recarregar = (Button)findViewById(R.id.recarregar);
		recarregar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final Dialog dialog = new Dialog(GlobalActivity.this);
				dialog.setTitle("Login Paypal");
				dialog.setContentView(R.layout.recarga);
				dialog.setCancelable(true);
				dialog.show();

				saldoAtual = (TextView)dialog.findViewById(R.id.saldo_atual);
				novoSaldo = (TextView)dialog.findViewById(R.id.novo_saldo);

				consultarCredito();

				Button confirmar = (Button)dialog.findViewById(R.id.efetuar_pagamento);
				confirmar.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						adicionarCredito(Double.parseDouble(novoSaldo.getText().toString()));
					}
				});
			}
		});
	}

	public void adicionarCredito(final double valor) {
		new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				MotoristaDAO mdao = new MotoristaDAO(GlobalActivity.this);
				Motorista m = mdao.getAll().get(0);
				try {
					WSClient.addCredito(m.getId(), valor);
				} catch (ServiceError e) {
					e.printStackTrace();
				}
				return null;
			}

			protected void onPostExecute(Void result) {
				Toast.makeText(GlobalActivity.this, "Créditos inseridos com sucesso!", Toast.LENGTH_SHORT).show();
			};
		}.execute();
	}

	public void consultarCredito() {
		new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				MotoristaDAO mdao = new MotoristaDAO(GlobalActivity.this);
				Motorista m = mdao.getAll().get(0);
				try {
					Statuss s = WSClient.consultaSaldo(m.getId());
					return s.mensagem;
				} catch (ServiceError e) {
					e.printStackTrace();
				}
				return null;
			}

			protected void onPostExecute(String result) {
				saldoAtual.setText(result);
			};
		}.execute();
	}
}
