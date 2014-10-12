package com.mmidgard.hubestacionamento.dao;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mmidgard.hubestacionamento.models.Carro;
import com.mmidgard.hubestacionamento.models.Estacionamento;
import com.mmidgard.hubestacionamento.models.Motorista;
import com.mmidgard.hubestacionamento.models.Recarga;

public class BDControle<E> extends OrmLiteSqliteOpenHelper {

	private static final String NOME_BD = "estacionamento.db";
	private static int VERSAO_BD = 7;

	public BDControle(Context context) {
		super(context, NOME_BD, null, VERSAO_BD);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource src) {
		try {
			TableUtils.createTable(src, Motorista.class);
			TableUtils.createTable(src, Carro.class);
			TableUtils.createTable(src, Recarga.class);
			TableUtils.createTable(src, Estacionamento.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource src, int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(src, Motorista.class, true);
			TableUtils.dropTable(src, Carro.class, true);
			TableUtils.dropTable(src, Estacionamento.class, true);
			TableUtils.dropTable(src, Recarga.class, true);
			onCreate(db, src);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		super.close();
	}

}
