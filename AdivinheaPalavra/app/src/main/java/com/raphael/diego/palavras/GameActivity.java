package com.raphael.diego.palavras;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends ActionBarActivity {
    public final static String MESSSAGE_PONTUACAO = "com.raphael.diego.palavras.MESSAGE";
    private String palavraRodada = "";
    private int pontuacao = 0;
    private int tentativa = 3;
    private int rodadas = 0;
    ArrayList<Pergunta> perguntas = new ArrayList<Pergunta>();
  //  public String dicas1[] = {"É um país da Europa", "Apelido do Chewbacca", "Carro que aparece no filme 'De volta para o futuro'", "Ator que participou de Star Wars", "Personagem que não apareceu no filme 'Guerra Civil'", "Motivo da extinção dos dinossauros", "Produtor de 'Indiana Jones'", "Lendária nave espacial de um filme de ficção", "Jogo conhecido por sua dificuldade", "Personagem de Star Wars"};
  //  public String dicas2[]  = {"Terra das flores", "Han Solo o chama assim", "80mph", "Interpretou Luke Skywalker", "É considerado um Deus", "Rocha que vem do espaço", "Também produziu Star Wars", "Nave do filme Star Wars", "Antecessor de Dark Souls", "É um robo"};
  //  public String palavras[] = {"holanda", "chewie", "delorean", "mark hamill", "thor", "meteoro", "george lucas", "millenium falcon", "demon souls", "c3po"};
    public int jogo[] = new int[6];
    public  boolean flag = false;

    public void setRodadas(int rodadas) {
        this.rodadas = rodadas;
    }

    public int getRodadas() {
        return rodadas;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setTentativa(int tentativa) {
        this.tentativa = tentativa;
    }

    public int getTentativa() {
        return tentativa;
    }

    public String getPalavraRodada() {
        return palavraRodada;
    }

    public void setPalavraRodada(String palavra) {
        this.palavraRodada = palavra;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    @Override
    public void onResume() {
        super.onResume();

        EditText palavraDigitada = (EditText) findViewById(R.id.txtPalavra);
        gerarJogo();

        Button comparar = (Button) findViewById(R.id.btnComparar);
        assert comparar != null;
        comparar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificaPalavra();
            }
        });

        Button dica = (Button) findViewById(R.id.btnDica2);
        assert dica != null;
        dica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desbloquearDica();
            }
        });
    }

    public void gerarJogo() {
        Random rand = new Random();
        PerguntasController crud = new PerguntasController(getBaseContext());
        Cursor cursor = crud.listar();

        do {
            perguntas.add(new Pergunta(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
        } while (cursor.moveToNext());

        int j;
        for (int i = 0; i < 5;) {
            jogo[i] = rand.nextInt(perguntas.size());
            if(i>0) {
                for(j=i-1;j>=0;j--){
                    if(jogo[j] == jogo[i])break;
                }
                if(j>=0) {
                    if(jogo[j]!=jogo[i]) i++;
                } else i++;
            } else i++;
        }

        trocaPalavra();
    }

    public void verificaPalavra() {
        EditText palavraDigitada = (EditText) findViewById(R.id.txtPalavra);
        if(palavraDigitada.getText().toString().toLowerCase().equals(getPalavraRodada())) {
            atribuiPontuacao();
        } else {
            tentativaErrada();
        }
    }

    public void atribuiPontuacao() {
        setPontuacao(getPontuacao() + 50 * getTentativa());
        TextView pontuacaoTotal = (TextView) findViewById(R.id.txtPontuação);
        pontuacaoTotal.setText("Pontuação: " + getPontuacao());

        fimDeRodada();
    }

    public void tentativaErrada() {
        TextView txtTentativa = (TextView) findViewById(R.id.txtTentativas);
        setTentativa(getTentativa() - 1);
        txtTentativa.setText(getTentativa() + "/3");

        if (getTentativa() == 0) {
            fimDeRodada();
        }
    }

    public void trocaPalavra() {
        TextView palavraDica1 = (TextView) findViewById(R.id.txvDica);
        //palavraDica1.setText(dicas1[jogo[getRodadas()]]);
        palavraDica1.setText(perguntas.get(jogo[getRodadas()]).getPergunta());

        //setPalavraRodada(palavras[jogo[getRodadas()]]);
        setPalavraRodada(perguntas.get(jogo[getRodadas()]).getResposta());
    }

    public void fimDeRodada() {
        EditText palavraDigitada = (EditText) findViewById(R.id.txtPalavra);
        palavraDigitada.setText("");

        TextView txtTentativa = (TextView) findViewById(R.id.txtTentativas);
        setTentativa(3);
        txtTentativa.setText("3/3");

        TextView txtRodada = (TextView) findViewById(R.id.txtRodada);
        setRodadas(getRodadas() + 1);

        TextView dica = (TextView) findViewById(R.id.txvDica2);
        dica.setText("Desbloquear");

        if (getRodadas() == 5) {
            fimDeJogo();
        }

        txtRodada.setText(getRodadas() + "/5");
        flag = false;
        trocaPalavra();

    }

    public void fimDeJogo() {
        TextView txtRodada = (TextView) findViewById(R.id.txtRodada);
        txtRodada.setText("0/5");
        String pontuacao = "Pontuação: " + getPontuacao();
        Intent rank = new Intent(GameActivity.this, RankActivity.class);
        rank.putExtra(MESSSAGE_PONTUACAO, pontuacao);
        startActivity(rank);
        finish();
    }

    public void desbloquearDica() {
        TextView dica = (TextView) findViewById(R.id.txvDica2);
        TextView pontuacao = (TextView) findViewById(R.id.txtPontuação);
        String msg = "";

        if (getPontuacao() >= 100 && !flag) {
            setPontuacao(getPontuacao() - 100);
            pontuacao.setText("Pontuação: " + getPontuacao());
            //dica.setText(dicas2[jogo[getRodadas()]]);
            dica.setText(perguntas.get(jogo[getRodadas()]).getDica());
            flag = true;
            msg = perguntas.get(jogo[getRodadas()]).getDica();
        } else if (flag == true){
            msg = "A dica já foi desbloqueada";
        } else {
            msg = "Pontuação insuficiente";
        }

        Toast t = Toast.makeText(GameActivity.this, msg, Toast.LENGTH_SHORT);
        t.show();
    }


}
