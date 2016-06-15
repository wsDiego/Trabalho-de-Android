package com.raphael.diego.palavras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Raphael on 08/06/2016.
 */
public class PerguntasController {

    private SQLiteDatabase db;
    private BdPerguntas banco;

    public PerguntasController(Context context) {
        banco = new BdPerguntas(context);
    }


    public String inserirPergunta(String pergunta, String dica, String resposta) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("pergunta",pergunta);
        valores.put("dica",dica);
        valores.put("resposta",resposta);

        resultado = db.insert("perguntas", null, valores);

        if(resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro inserido com sucesso";
    }

    public Cursor pesquisarPergunta(int id) {
        Cursor cursor;
        String[] campos = {"id", "pergunta", "dica", "resposta"};
        db = banco.getReadableDatabase();
        String[] args = new String[1];
        args[0] = String.valueOf(id);
        cursor = db.query("perguntas", campos,"id=?s",args , null,null, null);
        if(cursor != null)
            cursor.moveToFirst();
        db.close();

        return cursor;
    }

    public Cursor listar() {
        Cursor cursor;
        String[] campos = {"id", "pergunta", "dica", "resposta"};
        db = banco.getReadableDatabase();
        cursor = db.query("perguntas", campos,null, null, null,null, null);
        if(cursor != null)
            cursor.moveToFirst();
        db.close();

        return cursor;
    }
}
