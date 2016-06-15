package com.raphael.diego.palavras;

import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class PontuacoesActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pontuacoes);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    @Override
    protected void onResume() {
        super.onResume();

        BancoController crud = new BancoController(getBaseContext());
        Cursor cursor = crud.listar();

        String[] nomeCampos = new String[]{BdGame.NOME, BdGame.PONTUACAO};
        int[] idViews = new int[] {R.id.txtNome, R.id.txtPontuacao};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.linha, cursor, nomeCampos, idViews, 0);
        ListView lista = (ListView) super.findViewById(R.id.lista);
        lista.setAdapter(adapter);

    }
}
