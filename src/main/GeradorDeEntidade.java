package main;

import entidade.Entidade;
import objeto.ObjBarraca;
import objeto.ObjBau;
import objeto.ObjBolaDeFogo;
import objeto.ObjBota;
import objeto.ObjChave;
import objeto.ObjCoracao;
import objeto.ObjEscudoAzul;
import objeto.ObjEscudoMadeira;
import objeto.ObjEspadaNormal;
import objeto.ObjLanterna;
import objeto.ObjMachado;
import objeto.ObjMana;
import objeto.ObjMoedaBronze;
import objeto.ObjPedra;
import objeto.ObjPicareta;
import objeto.ObjPocaoVermelha;
import objeto.ObjPorta;
import objeto.ObjPortaDeFerro;


public class GeradorDeEntidade {
    PainelDoJogo painel;

    public GeradorDeEntidade(PainelDoJogo painel){
        this.painel = painel;
    }

    public Entidade getObjeto(String nomeDoItem){
        Entidade objeto = null;

        //obs: add todos os objetos criados
        switch(nomeDoItem){
            //armas
            case ObjMachado.objNome : objeto = new ObjMachado(painel); break;
            case ObjEspadaNormal.objNome : objeto = new ObjEspadaNormal(painel); break;
            case ObjBolaDeFogo.objNome : objeto = new ObjBolaDeFogo(painel); break;
            case ObjPedra.objNome : objeto = new ObjPedra(painel); break;
            case ObjPicareta.objNome : objeto = new ObjPicareta(painel); break;

            //escudos
            case ObjEscudoAzul.objNome : objeto = new ObjEscudoAzul(painel); break;
            case ObjEscudoMadeira.objNome : objeto = new ObjEscudoMadeira(painel); break;
            
            //objetos
            case ObjBota.objNome: objeto = new ObjBota(painel); break;
            case ObjChave.objNome : objeto = new ObjChave(painel); break;
            case ObjPorta.objNome : objeto = new ObjPorta(painel); break;
            case ObjPortaDeFerro.objNome : objeto = new ObjPortaDeFerro(painel); break;
            case ObjBau.objNome : objeto = new ObjBau(painel); break;
            case ObjMoedaBronze.objNome : objeto = new ObjMoedaBronze(painel); break;

            //iluminação
            case ObjLanterna.objNome : objeto = new ObjLanterna(painel); break;
            case ObjBarraca.objNome : objeto = new ObjBarraca(painel); break;

            //cura
            case ObjPocaoVermelha.objNome : objeto = new ObjPocaoVermelha(painel); break;
            case ObjCoracao.objNome : objeto = new ObjCoracao(painel); break;
            case ObjMana.objNome : objeto = new ObjMana(painel); break;
            
            
        }
        if(objeto == null){
            System.out.println("Objeto não encontrado: '" + nomeDoItem + "'");
        }

        return objeto;
        
    }
}
