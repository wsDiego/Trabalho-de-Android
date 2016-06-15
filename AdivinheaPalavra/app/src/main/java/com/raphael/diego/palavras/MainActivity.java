package com.raphael.diego.palavras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button play = (Button) findViewById(R.id.btnJogar);
        assert play != null;
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playGame = new Intent(MainActivity.this, GameActivity.class);
                startActivity(playGame);
            }
        });

        Button rank = (Button) findViewById(R.id.btnRank);
        assert rank != null;
        rank.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent abrirRank = new Intent(MainActivity.this, PontuacoesActivity.class);
                startActivity(abrirRank);
            }
        }
        );

        Button pergunta = (Button) findViewById(R.id.btnNovaPergunta);
        assert pergunta != null;
        pergunta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent abrirNovaPergunta = new Intent(MainActivity.this, NovaPerguntaActivity.class);
                startActivity(abrirNovaPergunta);
            }
        }
        );
    }
}
