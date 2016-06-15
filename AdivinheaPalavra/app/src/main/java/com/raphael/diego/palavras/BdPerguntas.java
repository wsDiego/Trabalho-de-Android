package com.raphael.diego.palavras;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Raphael on 08/06/2016.
 */
public class BdPerguntas extends SQLiteOpenHelper{

    public BdPerguntas(Context context) {
        super(context, "banco2", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE perguntas ("
                + " id integer primary key autoincrement, "
                + "pergunta text, "
                + "dica text,"
                + "resposta text"
                + ");";
        db.execSQL(sql);
        sql = "INSERT INTO perguntas VALUES (1, \"É um país da Europa\",\"Terra das flores\",\"holanda\")," +
                "(2, \"Apelido do Chewbacca\",\"Han Solo o chama assim\",\"chewie\")," +
                "(3, \"Carro que aparece no filme 'De volta para o futuro'\",\"80mph\",\"delorean\")," +
                "(4, \"Ator que participou de Star Wars\",\"Interpretou Luke Skywalker\",\"mark hamill\")," +
                "(5, \"Personagem que não apareceu no filme 'Guerra Civil'\",\"É considerado um Deus\",\"thor\")," +
                "(6, \"Motivo da extinção dos dinossauros\",\"Rocha que vem do espaço\",\"meteoro\")," +
                "(7, \"Produtor de 'Indiana Jones'\",\"Também produziu Star Wars\",\"george lucas\")," +
                "(8, \"Lendária nave espacial de um filme de ficção\",\"Nave do filme Star Wars\",\"millenium falcon\")," +
                "(9, \"Jogo conhecido por sua dificuldade\",\"Antecessor de Dark Souls\",\"demon souls\")," +
                "(10, \"Personagem de Star Wars\",\"É um robo\",\"c3po\");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS perguntas";
        db.execSQL(sql);
        onCreate(db);
    }
}
