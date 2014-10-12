package com.mmidgard.hubestacionamento;

import internet.WSClient;
import android.util.Base64;

public class Config {

	public static void configSenha() {
		WSClient.setAuth("Basic " + Base64.encodeToString(("mmidgard@outlook.com:mmidgard").getBytes(), Base64.NO_WRAP));
	}
}
