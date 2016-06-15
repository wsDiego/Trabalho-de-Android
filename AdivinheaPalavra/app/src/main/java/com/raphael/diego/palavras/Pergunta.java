package com.raphael.diego.palavras;

/**
 * Created by Raphael on 08/06/2016.
 */
public class Pergunta {
    private int id;
    private String pergunta;
    private String dica;
    private String resposta;

    public Pergunta(int id, String p, String d, String r) {
        this.setId(id);
        this.setPergunta(p);
        this.setDica(d);
        this.setResposta(r);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getDica() {
        return dica;
    }

    public void setDica(String dica) {
        this.dica = dica;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }


}
