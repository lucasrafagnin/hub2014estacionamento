package com.mmidgard.hubestacionamento;

import com.mmidgard.hubestacionamento.dao.MotoristaDAO;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class SplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				MotoristaDAO mdao = new MotoristaDAO(SplashScreen.this);
				if (mdao.getAll().size() > 0)
					startActivity(new Intent(SplashScreen.this, Inicial.class));
				else
					startActivity(new Intent(SplashScreen.this, Perfil.class));
				finish();
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		}, 2000);
	}

}
