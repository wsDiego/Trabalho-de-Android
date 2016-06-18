package com.raphael.diego.palavras;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Raphael on 07/06/2016.
 */
public class NovaPerguntaActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novapergunta);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Button btnSalvar = (Button) super.findViewById(R.id.btnSalvarPergunta);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                PerguntasController crud = new PerguntasController(getBaseContext());
                EditText pergunta = (EditText) findViewById(R.id.editPergunta);
                String p = pergunta.getText().toString();

                EditText dica = (EditText) findViewById(R.id.editDica);
                String d = dica.getText().toString();

                EditText resposta = (EditText) findViewById(R.id.editResposta);
                String r = resposta.getText().toString().toLowerCase();

                String resultado = crud.inserirPergunta(p,d,r);
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            }
        });
    }
}
