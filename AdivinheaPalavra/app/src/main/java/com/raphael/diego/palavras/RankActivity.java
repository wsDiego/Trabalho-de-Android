package com.raphael.diego.palavras;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RankActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }


    protected void onResume() {
        super.onResume();
        Intent pontuacao = getIntent();

        String pontuacaoJogo = pontuacao.getStringExtra(GameActivity.MESSSAGE_PONTUACAO);

        TextView ultimaPontuacao = (TextView) findViewById(R.id.txvPontuacaoRodada);
        ultimaPontuacao.setText(pontuacaoJogo);

        Button newGame = (Button) findViewById(R.id.btnNovoJogo);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent game = new Intent(RankActivity.this, GameActivity.class);
                startActivity(game);
                finish();
            }
        });

        Button btnSalvar = (Button) super.findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                BancoController crud = new BancoController(getBaseContext());
                EditText nome = (EditText) findViewById(R.id.editNome);
                String n = nome.getText().toString();

                TextView pontuacao = (TextView) findViewById(R.id.txvPontuacaoRodada);
                String[] t = pontuacao.getText().toString().split(" ");
                int p = Integer.parseInt(t[1]);
                String resultado = crud.insereDado(n,p);
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            }
        });
    }
}
