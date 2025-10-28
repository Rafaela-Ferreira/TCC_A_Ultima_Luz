package main;

import entidade.Entidade;
import objeto.ObjAlma;
import objeto.ObjBarraca;
import objeto.ObjBau;
import objeto.ObjBolaDeFogo;
import objeto.ObjBota;
import objeto.ObjChave;
import objeto.ObjCoracao;
import objeto.ObjEscudoAzul;
import objeto.ObjEscudoMadeira;
import objeto.ObjEspadaNormal;
import objeto.ObjFragmentoCarmesim;
import objeto.ObjFragmentoCoracaoDaLamina;
import objeto.ObjFragmentoDourado;
import objeto.ObjFragmentoFaminto;
import objeto.ObjFragmentoFlamejante;
import objeto.ObjFragmentoOnirico;
import objeto.ObjFragmentoSombrio;
import objeto.ObjTocha;
import objeto.ObjLanterna;
import objeto.ObjMachado;
import objeto.ObjMana;
import objeto.ObjMoedaBronze;
import objeto.ObjPedra;
import objeto.ObjPicareta;
import objeto.ObjPocaoAzul;
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
            case ObjAlma.objNome : objeto = new ObjAlma(painel); break;
            case ObjTocha.objNome : objeto = new ObjTocha(painel); break;

            //iluminação
            case ObjLanterna.objNome : objeto = new ObjLanterna(painel); break;
            case ObjBarraca.objNome : objeto = new ObjBarraca(painel); break;

            //cura
            case ObjPocaoVermelha.objNome : objeto = new ObjPocaoVermelha(painel); break;
            case ObjPocaoAzul.objNome : objeto = new ObjPocaoAzul(painel); break;
            case ObjCoracao.objNome : objeto = new ObjCoracao(painel); break;
            case ObjMana.objNome : objeto = new ObjMana(painel); break;

            //fragmentos da espada do eclipse
            case ObjFragmentoCarmesim.objNome : objeto = new objeto.ObjFragmentoCarmesim(painel); break; //Luxuria
            case ObjFragmentoOnirico.objNome : objeto = new objeto.ObjFragmentoOnirico(painel); break; //Preguiça
            case ObjFragmentoFaminto.objNome : objeto = new objeto.ObjFragmentoFaminto(painel); break; // Gula
            case ObjFragmentoDourado.objNome : objeto = new objeto.ObjFragmentoDourado(painel); break; // Avareza
            case ObjFragmentoFlamejante.objNome : objeto = new objeto.ObjFragmentoFlamejante(painel); break; // ira
            case ObjFragmentoSombrio.objNome : objeto = new objeto.ObjFragmentoSombrio(painel); break; // Inveja
            case ObjFragmentoCoracaoDaLamina.objNome : objeto = new objeto.ObjFragmentoCoracaoDaLamina(painel); break; // Orgulho

            
            
        }
        if(objeto == null){
            System.out.println("Objeto não encontrado: '" + nomeDoItem + "'");
        }

        return objeto;
        
    }
}
