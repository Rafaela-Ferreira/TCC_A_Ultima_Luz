package entidade;

import java.awt.AlphaComposite;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


import main.PainelDoJogo;
import main.Teclado;
import objeto.ObjBolaDeFogo;
import objeto.ObjChave;
import objeto.ObjEscudoMadeira;
import objeto.ObjEspadaNormal;



public class Jogador extends Entidade {
    // Atributos específicos do Jogador
  
    Teclado teclado; //keyH

    public final int telaX;
    public final int telaY;
    int auxCounter=0;

    int contadorDeSprite = 0;
    boolean movimento = false;
    int contadorDePixels = 0;

    public boolean cancelarAtaque = false;
    public boolean equiparLanterna = false;


    public Jogador(PainelDoJogo painel, Teclado teclado) {
        super(painel); //estamos chamando o construtor da superClass desta class (Entidade)
        
      
        this.teclado = teclado;

        telaX = painel.larguraTela / 2 - (painel.tamanhoDoTile / 2); // Centraliza na tela
        telaY = painel.alturaTela / 2 - (painel.tamanhoDoTile / 2); // Centraliza na tela

        areaSolida = new Rectangle();
        areaSolida.x = 8; 
        areaSolida.y = 16;
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY = areaSolida.y;
        areaSolida.width = 32; 
        areaSolida.height = 32; 

        

        setDefaultValues();
        
    }
    public void setDefaultValues() {
        mundoX = painel.tamanhoDoTile * 23; 
        mundoY = painel.tamanhoDoTile * 21;
        //mundoX = painel.tamanhoDoTile * 12; 
        //mundoY = painel.tamanhoDoTile * 14;
        //painel.mapaAtual = 1;


        velocidadePadrao = 4;
        velocidade = velocidadePadrao;
        direcao = "baixo"; // Direção inicial do jogador

        //estado do jogador
        nivel = 1;
        vidaMaxima = 6;
        vida = vidaMaxima;
        manaMaxima = 4;
        mana = manaMaxima;
        municao = 10;
        forca = 1; //quanto mais força ele tem, mais dano ele dá.
        destreza = 1; //quanto mais destreza ele tem, menos dano ele recebe.
        exp =0;
        proximoNivelExp = 5;
        moeda = 0;
        armaAtual = new ObjEspadaNormal(painel);
        //armaAtual = new ObjMachado(painel);
        escudoAtual = new ObjEscudoMadeira(painel);
        luzAtual = null;
        projetil = new ObjBolaDeFogo(painel);
        //projetil = new ObjPedra(painel); //municao
        ataque = getAtaque();
        defesa = getDefesa();

        getImagem();
        getImagemDeAtaque();
        getImagemDeDefesa();
        setItens();
        setDialogo();
    }

    public void setPosicaoPadrao(){
        painel.mapaAtual = 0;
        mundoX = painel.tamanhoDoTile * 23; 
        mundoY = painel.tamanhoDoTile * 21;
        direcao = "baixo";
    }

    public void setDialogo(){
        dialogo[0][0] = "Você subiu para o nível " + nivel + "!";
    }


    public void restaltarStatus(){
        vida = vidaMaxima;
        mana = manaMaxima;
        velocidade = velocidadePadrao;
        invencivel = false;
        transparente = false;
        atacar = false;
        defender = false;
        empurrao = false;
        equiparLanterna = true; //atualizar a luz
    }

    public void setItens(){
        inventario.clear(); //limpar o inventario - remover essa opção futuramente
        inventario.add(escudoAtual);
        inventario.add(armaAtual);
        inventario.add(new ObjChave(painel));
    }

    public int getAtaque(){
        areaAtaque = armaAtual.areaAtaque;
        direcaoDoMovimento1  = armaAtual.direcaoDoMovimento1;
        direcaoDoMovimento2  = armaAtual.direcaoDoMovimento2;
        return ataque = forca * armaAtual.valorAtaque;
    }

    public int getDefesa(){
        return defesa = destreza * escudoAtual.valorDefesa;
    }

    public int getEspacoArmaAtual(){
        int espacoArmaAtual = 0;
        for(int i = 0; i < inventario.size(); i++){
            if(inventario.get(i) == armaAtual){
                espacoArmaAtual = i;
            }
        }
        return espacoArmaAtual;
    }

    public int getEspacoEscudoAtual(){
        int espacoEscudoAtual = 0;
        for(int i = 0; i < inventario.size(); i++){
            if(inventario.get(i) == escudoAtual){
                espacoEscudoAtual = i;
            }
        }
        return espacoEscudoAtual;
    }

    public void getImagem(){
        
        cima1 = setup("/img/spritesjogador/boy_up_1" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        cima2 = setup("/img/spritesjogador/boy_up_2" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo1 = setup("/img/spritesjogador/boy_down_1" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        baixo2 = setup("/img/spritesjogador/boy_down_2" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda1 = setup("/img/spritesjogador/boy_left_1" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        esquerda2 = setup("/img/spritesjogador/boy_left_2" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita1 = setup("/img/spritesjogador/boy_right_1" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        direita2 = setup("/img/spritesjogador/boy_right_2" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

    public void getImagemDormindo(BufferedImage imagem){
        cima1 = imagem;
        cima2 = imagem;
        baixo1 = imagem;
        baixo2 = imagem;
        esquerda1 = imagem;
        esquerda2 = imagem;
        direita1 = imagem;
        direita2 = imagem;
    }

    public void getImagemDeAtaque(){
        if(armaAtual.tipo == tipoEspada){
            ataqueCima1 = setup("/img/spritesjogador/ataques/boy_attack_up_1" ,painel.tamanhoDoTile, painel.tamanhoDoTile*2);
            ataqueCima2 = setup("/img/spritesjogador/ataques/boy_attack_up_2" ,painel.tamanhoDoTile, painel.tamanhoDoTile*2);
            ataqueBaixo1 = setup("/img/spritesjogador/ataques/boy_attack_down_1" ,painel.tamanhoDoTile, painel.tamanhoDoTile*2);
            ataqueBaixo2 = setup("/img/spritesjogador/ataques/boy_attack_down_2" ,painel.tamanhoDoTile, painel.tamanhoDoTile*2);
            ataqueEsquerda1 = setup("/img/spritesjogador/ataques/boy_attack_left_1" ,painel.tamanhoDoTile*2, painel.tamanhoDoTile);
            ataqueEsquerda2 = setup("/img/spritesjogador/ataques/boy_attack_left_2" ,painel.tamanhoDoTile*2, painel.tamanhoDoTile);
            ataqueDireita1 = setup("/img/spritesjogador/ataques/boy_attack_right_1" ,painel.tamanhoDoTile*2, painel.tamanhoDoTile);
            ataqueDireita2 = setup("/img/spritesjogador/ataques/boy_attack_right_2" ,painel.tamanhoDoTile*2, painel.tamanhoDoTile);

        }

        if(armaAtual.tipo == tipoMachado){
            ataqueCima1 = setup("/img/spritesjogador/ataques/boy_axe_up_1" ,painel.tamanhoDoTile, painel.tamanhoDoTile*2);
            ataqueCima2 = setup("/img/spritesjogador/ataques/boy_axe_up_2" ,painel.tamanhoDoTile, painel.tamanhoDoTile*2);
            ataqueBaixo1 = setup("/img/spritesjogador/ataques/boy_axe_down_1" ,painel.tamanhoDoTile, painel.tamanhoDoTile*2);
            ataqueBaixo2 = setup("/img/spritesjogador/ataques/boy_axe_down_2" ,painel.tamanhoDoTile, painel.tamanhoDoTile*2);
            ataqueEsquerda1 = setup("/img/spritesjogador/ataques/boy_axe_left_1" ,painel.tamanhoDoTile*2, painel.tamanhoDoTile);
            ataqueEsquerda2 = setup("/img/spritesjogador/ataques/boy_axe_left_2" ,painel.tamanhoDoTile*2, painel.tamanhoDoTile);
            ataqueDireita1 = setup("/img/spritesjogador/ataques/boy_axe_right_1" ,painel.tamanhoDoTile*2, painel.tamanhoDoTile);
            ataqueDireita2 = setup("/img/spritesjogador/ataques/boy_axe_right_2" ,painel.tamanhoDoTile*2, painel.tamanhoDoTile);

        }

        if(armaAtual.tipo == tipoPicareta){
            ataqueCima1 = setup("/img/spritesjogador/ataques/boy_pick_up_1" ,painel.tamanhoDoTile, painel.tamanhoDoTile*2);
            ataqueCima2 = setup("/img/spritesjogador/ataques/boy_pick_up_2" ,painel.tamanhoDoTile, painel.tamanhoDoTile*2);
            ataqueBaixo1 = setup("/img/spritesjogador/ataques/boy_pick_down_1" ,painel.tamanhoDoTile, painel.tamanhoDoTile*2);
            ataqueBaixo2 = setup("/img/spritesjogador/ataques/boy_pick_down_2" ,painel.tamanhoDoTile, painel.tamanhoDoTile*2);
            ataqueEsquerda1 = setup("/img/spritesjogador/ataques/boy_pick_left_1" ,painel.tamanhoDoTile*2, painel.tamanhoDoTile);
            ataqueEsquerda2 = setup("/img/spritesjogador/ataques/boy_pick_left_2" ,painel.tamanhoDoTile*2, painel.tamanhoDoTile);
            ataqueDireita1 = setup("/img/spritesjogador/ataques/boy_pick_right_1" ,painel.tamanhoDoTile*2, painel.tamanhoDoTile);
            ataqueDireita2 = setup("/img/spritesjogador/ataques/boy_pick_right_2" ,painel.tamanhoDoTile*2, painel.tamanhoDoTile);

        }
        
    }

    public void getImagemDeDefesa(){
        defesaCima = setup("/img/spritesjogador/defesa/boy_guard_up" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        defesaBaixo = setup("/img/spritesjogador/defesa/boy_guard_down" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        defesaEsquerda = setup("/img/spritesjogador/defesa/boy_guard_left" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
        defesaDireita = setup("/img/spritesjogador/defesa/boy_guard_right" ,painel.tamanhoDoTile, painel.tamanhoDoTile);
    }

    

    public void atualizar(){
        //if(movimento = true){

            if(empurrao == true){
                colisaoComBloco = false;
                painel.colisaoChecked.verificarColisao(this);
                painel.colisaoChecked.verificarObjeto(this, true);
                painel.colisaoChecked.verificarEntidade(this, painel.npc);
                painel.colisaoChecked.verificarEntidade(this, painel.inimigo);
                painel.colisaoChecked.verificarEntidade(this, painel.blocosI);

                if(colisaoComBloco == true){
                    contadoEmpurrao = 0;
                    empurrao = false;
                    velocidade = velocidadePadrao;
                }
            else if(colisaoComBloco == false){
                switch (direcaoDoempurrao) {
                    case "cima": mundoY -= velocidade; break;
                    case "baixo": mundoY += velocidade;break;
                    case "esquerda": mundoX -= velocidade; break;
                    case "direita": mundoX += velocidade; break;
                }
            }

            contadoEmpurrao++;
            if(contadoEmpurrao == 10){
                contadoEmpurrao = 0;
                empurrao = false;
                velocidade = velocidadePadrao;
            }

        }

        else if(atacar == true){
            ataque();
        }
        else if(teclado.precionarEspaco == true){
            defender = true;
            contadorDeGuarda++;
        }

        else if(teclado.precionarCima == true || teclado.precionarBaixo == true || 
            teclado.precionarEsquerda == true || teclado.precionarDireita == true || teclado.precionarEnter == true) {
            // Se alguma tecla de movimento estiver pressionada, atualiza a direção
        
            // Verifica as teclas pressionadas e atualiza a posição do jogador

            if(teclado.precionarCima) direcao = "cima";    
            else if(teclado.precionarBaixo == true) direcao = "baixo";
            else if(teclado.precionarEsquerda == true) direcao = "esquerda";
            else if(teclado.precionarDireita == true) direcao = "direita";

                
            // Verifica colisão com blocos
            colisaoComBloco = false;
            painel.colisaoChecked.verificarColisao(this);

            //verificar colisão com objetos
            int objIndex = painel.colisaoChecked.verificarObjeto(this, true);
            pegarObjeto (objIndex);

            //verificar colisão com npc
            int npcIndice = painel.colisaoChecked.verificarEntidade(this, painel.npc);
            interacaoComNpc(npcIndice);

            //verifica colisão com inimigo
            int indiceInimigo = painel.colisaoChecked.verificarEntidade(this, painel.inimigo);
            contatoComInimigo(indiceInimigo);
            
            //verificar colisão com blocos interativos
            painel.colisaoChecked.verificarEntidade(this, painel.blocosI);


            //verificar evento
            painel.mEventos.verificarEvento();

            //se colição for false,
            if(colisaoComBloco == false && teclado.precionarEnter == false) {
                // Atualiza a posição do jogador
                switch (direcao) {
                    case "cima":
                        mundoY -= velocidade; break;
                    case "baixo":
                        mundoY += velocidade;break;
                    case "esquerda":
                        mundoX -= velocidade; break;
                    case "direita":
                        mundoX += velocidade; break;
                }

            }
            else {
                numeroDoSprite = 1;  
            }

            if(teclado.precionarEnter == true && cancelarAtaque == false){
                painel.iniciarEfeitoSonoro(7);
                atacar = true;
                contadorDeSprite = 0;


                //diminuir a durabilidade
                armaAtual.durabilidade--;
            }

            cancelarAtaque = false;
            painel.teclado.precionarEnter = false;
            defender = false;
            contadorDeGuarda = 0;
            
            // Controle de animação do sprite
            contadorDeSprite++;
            if(contadorDeSprite  > 12) { // A cada 10 atualizações, troca o sprite
                if(numeroDoSprite  == 1) {
                    numeroDoSprite  = 2; // Alterna para o segundo sprite
                } else if(numeroDoSprite  == 2) {
                    numeroDoSprite  = 1; // Alterna para o primeiro sprite
                }
                contadorDeSprite  = 0; // Reseta o contador de sprites
            }

        }
        else {
            auxCounter++;
            if(auxCounter == 20){
                numeroDoSprite = 1;
                auxCounter = 0;
            }
            defender = false;
            contadorDeGuarda = 0;

            
        }
        if(painel.teclado.teclaDeTiroPressionada == true && projetil.vivo == false 
            && contadorDeTiro == 30 && projetil.temRecursos(this) == true){
            
            //definir coordenadas, direção e usuário padrão
            projetil.setAcao(mundoX, mundoY, direcao, true, this);
            
            // subtrai o consumo (mana, munição, etc.)
            projetil.subtrairRecursos(this);

            //adicionar o projetil na lista de projetil
            //painel.listaProjetil.add(projetil);
            
            
            //verificar vaga
            for(int i = 0; i < painel.projetavel[1].length; i++){
                if(painel.projetavel[painel.mapaAtual][i] == null){
                    painel.projetavel[painel.mapaAtual][i] = projetil;
                    break;
                }
            }

            

            contadorDeTiro = 0;

            painel.iniciarEfeitoSonoro(10);
        }


        if(invencivel == true){
            invencivelContador++;
            if(invencivelContador > 60){
                invencivel = false;
                transparente = false;
                invencivelContador = 0;
            }
        }   

        if(contadorDeTiro < 30){
            contadorDeTiro++;
        }
        if(vida > vidaMaxima){
            vida = vidaMaxima;
        }
        
        if(mana > manaMaxima){
            mana = manaMaxima;
        }
        
        if(teclado.modoDebugAtivo == false){
            if(vida <= 0){
                painel.estadoDoJogo = painel.estadoGameOver;
                painel.interfaceDoUsuario.numeroDoComando = -1;
                painel.pararMusica();
                painel.iniciarEfeitoSonoro(12);
            }
        }
        
        
        
    } 
        
        
    //}


    
    

    /*para abrir a porta é necessário ter uma chave
      se tiver, a porta é removida do array de objetos
      se não tiver, nada acontece
      se tiver, a quantidade de chaves diminui */
    public void pegarObjeto(int indice){
        if (indice != 999) {

            //itens somente para retirada
            if(painel.Obj[painel.mapaAtual][indice].tipo == tipoRetirada){
                painel.Obj[painel.mapaAtual][indice].usar(this);
                painel.Obj[painel.mapaAtual][indice]= null;
            }
            //Obstaculo
            else if(painel.Obj[painel.mapaAtual][indice].tipo == tipoObstaculo){
                if(teclado.precionarEnter == true){
                    cancelarAtaque = true;
                    painel.Obj[painel.mapaAtual][indice].interagir();
                }
            }


            //itens do invetario
            else{
                String texto;

                if(podeObterItem(painel.Obj[painel.mapaAtual][indice]) == true){
                    painel.iniciarEfeitoSonoro(1);
                    texto = "Tenho uma " + painel.Obj[painel.mapaAtual][indice].nome + "!";
                }else{
                    texto = "você não pode carregar mais nada!";
                }
                painel.interfaceDoUsuario.adicionarMensagem(texto);
                painel.Obj[painel.mapaAtual][indice] = null; //remover o objeto do mapa
            }

        }
    }

    public void interacaoComNpc(int indice){

        if(indice != 999){

            if (painel.teclado.precionarEnter == true) { 
                cancelarAtaque = true;  
                painel.npc[painel.mapaAtual][indice].falar();

            }

            /*else{
                painel.iniciarEfeitoSonoro(7);
                atacar = true;
            }*/
            
            painel.npc[painel.mapaAtual][indice].mover(direcao);
        }

        
    }

    
    public void verificarNivelAcima(){
        if(exp >= proximoNivelExp){
            nivel++;
            proximoNivelExp = proximoNivelExp * 2;
            vidaMaxima += 2;
            forca++;
            destreza++;
            ataque = getAtaque();
            defesa = getDefesa();
            painel.iniciarEfeitoSonoro(8);
            painel.estadoDoJogo = painel.estadoDoDialogo;

            //dialogo[0][0] = "Você subiu para o nível " + nivel + "!";
            setDialogo();
            iniciarDialogo(this, 0);
        }
    }

    public void selecionarItem(){
        int indeceItem = painel.interfaceDoUsuario.pegarItemSelecionado(painel.interfaceDoUsuario.jogadorEspacoColuna, painel.interfaceDoUsuario.jogadorEspacoLinha);

        if(indeceItem < inventario.size()){

            Entidade itemSelecionado = inventario.get(indeceItem);

            if(itemSelecionado.tipo == tipoEspada || itemSelecionado.tipo == tipoMachado || itemSelecionado.tipo == tipoPicareta){
                armaAtual = itemSelecionado;
                ataque = getAtaque();
                getImagemDeAtaque();

            }
            if(itemSelecionado.tipo == tipoEscudo){
                escudoAtual = itemSelecionado;
                defesa = getDefesa();
            }
            if(itemSelecionado.tipo == tipoIliminacao){
                if(luzAtual == itemSelecionado){
                    luzAtual = null;
                }
                else{
                    luzAtual = itemSelecionado;
                }
                equiparLanterna = true;
            }

            if(itemSelecionado.tipo == tipoConsumivel){
                if(itemSelecionado.usar(this) == true){
                    if(itemSelecionado.quantidade > 1){
                        itemSelecionado.quantidade--;
                    }
                    else{
                        inventario.remove(indeceItem);
                    }

                }
                
            }

            

        }
    }

    public void contatoComInimigo(int i){
        if (i != 999){

            if(invencivel == false && painel.inimigo[painel.mapaAtual][i].morrendo == false){
                painel.iniciarEfeitoSonoro(6);

                int dano = painel.inimigo[painel.mapaAtual][i].ataque - defesa;
                if(dano < 1){
                    dano = 1;

                }
                vida -= dano; //dano do inimigo
                invencivel = true;
                transparente = true;
            }
            
        }
    }

    


    public void danoDoInimigo(int indice, Entidade atacante, int ataque, int poderDoEmpurrao){
        if(indice != 999){

            if(painel.inimigo[painel.mapaAtual][indice].invencivel == false){
                painel.iniciarEfeitoSonoro(5);

                if(poderDoEmpurrao > 0){
                    setEmpurrao(painel.inimigo[painel.mapaAtual][indice], atacante, poderDoEmpurrao);
                }

                if(painel.inimigo[painel.mapaAtual][indice].equilibrioDesativar == true){
                    ataque *= 5;
                }

                int dano = ataque - painel.inimigo[painel.mapaAtual][indice].defesa;
                if(dano < 0){
                    dano = 0;
                }

                painel.inimigo[painel.mapaAtual][indice].vida -= dano;
                painel.interfaceDoUsuario.adicionarMensagem(dano + " de dano!");
                painel.inimigo[painel.mapaAtual][indice].invencivel = true;
                painel.inimigo[painel.mapaAtual][indice].acaoAoDano();

                if(painel.inimigo[painel.mapaAtual][indice].vida <= 0){
                    painel.inimigo[painel.mapaAtual][indice].morrendo = true;
                    painel.interfaceDoUsuario.adicionarMensagem("Você derrotou " + painel.inimigo[painel.mapaAtual][indice].nome + "!");
                    painel.interfaceDoUsuario.adicionarMensagem("Você ganhou " + painel.inimigo[painel.mapaAtual][indice].exp + " de experiência!");
                    exp += painel.inimigo[painel.mapaAtual][indice].exp;
                    verificarNivelAcima();
                }
            }
            
        
        }
    }

    

    public void danoNoBlocoInterativo(int i ){
        if(i!= 999 && painel.blocosI[painel.mapaAtual][i].destruir == true 
            && painel.blocosI[painel.mapaAtual][i].itemCorreto(this) == true && painel.blocosI[painel.mapaAtual][i].invencivel == false){

            painel.blocosI[painel.mapaAtual][i].iniciarEfeitoSonoro();
            painel.blocosI[painel.mapaAtual][i].vida--;
            painel.blocosI[painel.mapaAtual][i].invencivel = true;

            //gerar particula
            geradorParticula(painel.blocosI[painel.mapaAtual][i], painel.blocosI[painel.mapaAtual][i]);
            
            if(painel.blocosI[painel.mapaAtual][i].vida == 0){
                //painel.blocosI[painel.mapaAtual][i].verificarDrop(); caso queira dropar itens das paredes
                painel.blocosI[painel.mapaAtual][i]= painel.blocosI[painel.mapaAtual][i].getDestruir();
            }
            
        }
    }

    public void  danoDoProjetavel(int i){
        if(i != 999){
            Entidade projetavel = painel.projetavel[painel.mapaAtual][i];
            projetavel.vivo = false;
            geradorParticula(projetavel, projetavel);
        }
    }

    public int procurarItemNoInventario(String nomeDoItem){

        int indiceItem = 999;

        for(int i = 0; i < inventario.size(); i++){
            if(inventario.get(i).nome.equals(nomeDoItem)){
                indiceItem = i;
                break;
            }
        }
        return indiceItem;
    }

    public boolean podeObterItem(Entidade item){

        boolean podeObter = false;

        Entidade novoItem = painel.geradorDeEntidade.getObjeto(item.nome);

        //Verificar se é empilhavel
        if(novoItem.empilhavel == true){

            int indice = procurarItemNoInventario(novoItem.nome);

            if(indice != 999){
                inventario.get(indice).quantidade++;
                podeObter = true;
            }
            else {
                if(inventario.size() != tamanhoMaximoInventario){
                    inventario.add(novoItem);
                    podeObter = true;
                }
            }
        }
        //não empilhável, então verifique a vaga
        else{
            if(inventario.size() != tamanhoMaximoInventario){
                inventario.add(novoItem);
                podeObter = true;
            }
        }
        return podeObter;
    }

        
    public void desenhar(Graphics2D g2) {
        //g2.setColor(Color.WHITE);
        //g2.fillRect(x, y, painel.tamanhoDoTile, painel.tamanhoDoTile);

        BufferedImage imagem = null;
        int telaTemporariaX = telaX;
        int telaTemporariaY = telaY;
        
        switch (direcao) {
            case "cima":
                if(atacar == false){
                    if(numeroDoSprite  == 1){ imagem = cima1; }
                    if(numeroDoSprite  == 2){ imagem = cima2; }
                }
                if(atacar == true){
                    telaTemporariaY = telaY - painel.tamanhoDoTile;
                    if(numeroDoSprite  == 1){ imagem = ataqueCima1; }
                    if(numeroDoSprite  == 2){ imagem = ataqueCima2; }
                }
                if(defender == true){
                    imagem = defesaCima;
                }
                
            break;
            case "baixo":
                if(atacar == false){
                    if(numeroDoSprite  == 1){ imagem = baixo1; }
                    if(numeroDoSprite  == 2){ imagem = baixo2; }
                }
                if(atacar == true){
                    if(numeroDoSprite  == 1){ imagem = ataqueBaixo1; }
                    if(numeroDoSprite  == 2){ imagem = ataqueBaixo2; }
                }
                if(defender == true){
                    imagem = defesaBaixo;
                }
            break;
            case "esquerda":
                if(atacar == false){
                    if(numeroDoSprite  == 1){ imagem = esquerda1; }
                    if(numeroDoSprite  == 2){ imagem = esquerda2; }
                }
                if(atacar == true){
                    telaTemporariaX = telaX - painel.tamanhoDoTile;
                    if(numeroDoSprite  == 1){ imagem = ataqueEsquerda1; }
                    if(numeroDoSprite  == 2){ imagem = ataqueEsquerda2; }
                }
                 if(defender == true){
                    imagem = defesaEsquerda;
                }
                
            break;
            case "direita":
            if(atacar == false){
                if(numeroDoSprite  == 1){ imagem = direita1; }
                if(numeroDoSprite  == 2){ imagem = direita2; }
            }
            if(atacar == true){
                if(numeroDoSprite  == 1){ imagem = ataqueDireita1; }
                if(numeroDoSprite  == 2){ imagem = ataqueDireita2; }
            }
            if(defender == true){
                imagem = defesaDireita;
            }
            break;
        }

        if(transparente == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        if(desenho == true){
            g2.drawImage(imagem, telaTemporariaX, telaTemporariaY, null);
        }

        
        //verificar colisão com blocos
        //g2.setColor(Color.red);
        //g2.drawRect(telaX + areaSolida.x, telaY + areaSolida.y, areaSolida.width, areaSolida.height);


        //reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        //debug
        //g2.setFont(new Font("Arial", Font.PLAIN, 26));
        //g2.setColor(Color.white);
        //g2.drawString("Invencivel: "+invencivelContador, 10, 400);
    }
    
}
