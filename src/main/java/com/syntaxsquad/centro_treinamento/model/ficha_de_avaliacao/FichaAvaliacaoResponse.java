package com.syntaxsquad.centro_treinamento.model.ficha_de_avaliacao;

public class FichaAvaliacaoResponse {
    private Long id;

    // Dados Pessoais
    private double altura;
    private double peso;
    private String profissao;

    // Objetivos ao Praticar Atividade Física
    private boolean convivioSocial;
    private boolean condicionamentoFisico;
    private boolean necessidadeMedica;
    private String outrosObjetivos;

    // Anamnese e Questionário PAR-Q
    private boolean problemaCardiaco;
    private boolean dorPeito;
    private boolean tontura;
    private boolean problemaOsseo;
    private boolean medicacao;
    private String quaisMedicamentos;
    private boolean cirurgia;
    private String tipoCirurgia;
    private int anoCirurgia;
    private boolean gravida;
    private int mesesGravidez;
    private boolean fuma;
    private int cigarrosPorDia;
    private boolean ingereBebidaAlcoolica;
    private String frequenciaAlcool;
    private boolean atividadeFisicaAtual;
    private int frequenciaAtividade;

    // Objetivos de Saúde
    private boolean perderPeso;
    private boolean melhorarAptidaoCardiovascular;
    private boolean melhorarFlexibilidade;
    private boolean melhorarCondicionamentoFisico;
    private boolean reduzirDoresCostas;
    private boolean reduzirEstresse;
    private boolean diminuirColesterol;
    private boolean coordenacaoMotora;
    private String especificoObjetivo;

    // Dados da Avaliação Corporal
    private String sexo;
    private double bracoDireito;
    private double bracoEsquerdo;
    private double coxaDireita;
    private double coxaEsquerda;
    private double peitoralDireito;
    private double peitoralEsquerdo;
    private double cintura;
    private double torax;

    // Índices e Metabólicos
    private double imc;
    private String classificacaoImc;
    private double percentualGorduraCorporal;
    private double percentualMusculoEsqueletico;
    private double metabolismoRepouso;
    private int idadeBiologica;
    private int nivelGorduraVisceral;

    private String aluno_cpf; 

    // Construtor para inicializar a resposta a partir da entidade
    public FichaAvaliacaoResponse(FichaAvaliacao ficha) {
        this.id = ficha.getId();
        this.altura = ficha.getAltura();
        this.peso = ficha.getPeso();
        this.profissao = ficha.getProfissao();
        this.convivioSocial = ficha.isConvivioSocial();
        this.condicionamentoFisico = ficha.isCondicionamentoFisico();
        this.necessidadeMedica = ficha.isNecessidadeMedica();
        this.outrosObjetivos = ficha.getOutrosObjetivos();
        this.problemaCardiaco = ficha.isProblemaCardiaco();
        this.dorPeito = ficha.isDorPeito();
        this.tontura = ficha.isTontura();
        this.problemaOsseo = ficha.isProblemaOsseo();
        this.medicacao = ficha.isMedicacao();
        this.quaisMedicamentos = ficha.getQuaisMedicamentos();
        this.cirurgia = ficha.isCirurgia();
        this.tipoCirurgia = ficha.getTipoCirurgia();
        this.anoCirurgia = ficha.getAnoCirurgia();
        this.gravida = ficha.isGravida();
        this.mesesGravidez = ficha.getMesesGravidez();
        this.fuma = ficha.isFuma();
        this.cigarrosPorDia = ficha.getCigarrosPorDia();
        this.ingereBebidaAlcoolica = ficha.isIngereBebidaAlcoolica();
        this.frequenciaAlcool = ficha.getFrequenciaAlcool();
        this.atividadeFisicaAtual = ficha.isAtividadeFisicaAtual();
        this.frequenciaAtividade = ficha.getFrequenciaAtividade();
        this.perderPeso = ficha.isPerderPeso();
        this.melhorarAptidaoCardiovascular = ficha.isMelhorarAptidaoCardiovascular();
        this.melhorarFlexibilidade = ficha.isMelhorarFlexibilidade();
        this.melhorarCondicionamentoFisico = ficha.isMelhorarCondicionamentoFisico();
        this.reduzirDoresCostas = ficha.isReduzirDoresCostas();
        this.reduzirEstresse = ficha.isReduzirEstresse();
        this.diminuirColesterol = ficha.isDiminuirColesterol();
        this.coordenacaoMotora = ficha.isCoordenacaoMotora();
        this.especificoObjetivo = ficha.getEspecificoObjetivo();
        this.sexo = ficha.getSexo();
        this.bracoDireito = ficha.getBracoDireito();
        this.bracoEsquerdo = ficha.getBracoEsquerdo();
        this.coxaDireita = ficha.getCoxaDireita();
        this.coxaEsquerda = ficha.getCoxaEsquerda();
        this.peitoralDireito = ficha.getPeitoralDireito();
        this.peitoralEsquerdo = ficha.getPeitoralEsquerdo();
        this.cintura = ficha.getCintura();
        this.torax = ficha.getTorax();
        this.imc = ficha.getImc();
        this.classificacaoImc = ficha.getClassificacaoImc();
        this.percentualGorduraCorporal = ficha.getPercentualGorduraCorporal();
        this.percentualMusculoEsqueletico = ficha.getPercentualMusculoEsqueletico();
        this.metabolismoRepouso = ficha.getMetabolismoRepouso();
        this.idadeBiologica = ficha.getIdadeBiologica();
        this.nivelGorduraVisceral = ficha.getNivelGorduraVisceral();
        this.aluno_cpf = ficha.getUser().getCpf(); 
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public boolean isConvivioSocial() {
        return convivioSocial;
    }

    public void setConvivioSocial(boolean convivioSocial) {
        this.convivioSocial = convivioSocial;
    }

    public boolean isCondicionamentoFisico() {
        return condicionamentoFisico;
    }

    public void setCondicionamentoFisico(boolean condicionamentoFisico) {
        this.condicionamentoFisico = condicionamentoFisico;
    }

    public boolean isNecessidadeMedica() {
        return necessidadeMedica;
    }

    public void setNecessidadeMedica(boolean necessidadeMedica) {
        this.necessidadeMedica = necessidadeMedica;
    }

    public String getOutrosObjetivos() {
        return outrosObjetivos;
    }

    public void setOutrosObjetivos(String outrosObjetivos) {
        this.outrosObjetivos = outrosObjetivos;
    }

    public boolean isProblemaCardiaco() {
        return problemaCardiaco;
    }

    public void setProblemaCardiaco(boolean problemaCardiaco) {
        this.problemaCardiaco = problemaCardiaco;
    }

    public boolean isDorPeito() {
        return dorPeito;
    }

    public void setDorPeito(boolean dorPeito) {
        this.dorPeito = dorPeito;
    }

    public boolean isTontura() {
        return tontura;
    }

    public void setTontura(boolean tontura) {
        this.tontura = tontura;
    }

    public boolean isProblemaOsseo() {
        return problemaOsseo;
    }

    public void setProblemaOsseo(boolean problemaOsseo) {
        this.problemaOsseo = problemaOsseo;
    }

    public boolean isMedicacao() {
        return medicacao;
    }

    public void setMedicacao(boolean medicacao) {
        this.medicacao = medicacao;
    }

    public String getQuaisMedicamentos() {
        return quaisMedicamentos;
    }

    public void setQuaisMedicamentos(String quaisMedicamentos) {
        this.quaisMedicamentos = quaisMedicamentos;
    }

    public boolean isCirurgia() {
        return cirurgia;
    }

    public void setCirurgia(boolean cirurgia) {
        this.cirurgia = cirurgia;
    }

    public String getTipoCirurgia() {
        return tipoCirurgia;
    }

    public void setTipoCirurgia(String tipoCirurgia) {
        this.tipoCirurgia = tipoCirurgia;
    }

    public int getAnoCirurgia() {
        return anoCirurgia;
    }

    public void setAnoCirurgia(int anoCirurgia) {
        this.anoCirurgia = anoCirurgia;
    }

    public boolean isGravida() {
        return gravida;
    }

    public void setGravida(boolean gravida) {
        this.gravida = gravida;
    }

    public int getMesesGravidez() {
        return mesesGravidez;
    }

    public void setMesesGravidez(int mesesGravidez) {
        this.mesesGravidez = mesesGravidez;
    }

    public boolean isFuma() {
        return fuma;
    }

    public void setFuma(boolean fuma) {
        this.fuma = fuma;
    }

    public int getCigarrosPorDia() {
        return cigarrosPorDia;
    }

    public void setCigarrosPorDia(int cigarrosPorDia) {
        this.cigarrosPorDia = cigarrosPorDia;
    }

    public boolean isIngereBebidaAlcoolica() {
        return ingereBebidaAlcoolica;
    }

    public void setIngereBebidaAlcoolica(boolean ingereBebidaAlcoolica) {
        this.ingereBebidaAlcoolica = ingereBebidaAlcoolica;
    }

    public String getFrequenciaAlcool() {
        return frequenciaAlcool;
    }

    public void setFrequenciaAlcool(String frequenciaAlcool) {
        this.frequenciaAlcool = frequenciaAlcool;
    }

    public boolean isAtividadeFisicaAtual() {
        return atividadeFisicaAtual;
    }

    public void setAtividadeFisicaAtual(boolean atividadeFisicaAtual) {
        this.atividadeFisicaAtual = atividadeFisicaAtual;
    }

    public int getFrequenciaAtividade() {
        return frequenciaAtividade;
    }

    public void setFrequenciaAtividade(int frequenciaAtividade) {
        this.frequenciaAtividade = frequenciaAtividade;
    }

    public boolean isPerderPeso() {
        return perderPeso;
    }

    public void setPerderPeso(boolean perderPeso) {
        this.perderPeso = perderPeso;
    }

    public boolean isMelhorarAptidaoCardiovascular() {
        return melhorarAptidaoCardiovascular;
    }

    public void setMelhorarAptidaoCardiovascular(boolean melhorarAptidaoCardiovascular) {
        this.melhorarAptidaoCardiovascular = melhorarAptidaoCardiovascular;
    }

    public boolean isMelhorarFlexibilidade() {
        return melhorarFlexibilidade;
    }

    public void setMelhorarFlexibilidade(boolean melhorarFlexibilidade) {
        this.melhorarFlexibilidade = melhorarFlexibilidade;
    }

    public boolean isMelhorarCondicionamentoFisico() {
        return melhorarCondicionamentoFisico;
    }

    public void setMelhorarCondicionamentoFisico(boolean melhorarCondicionamentoFisico) {
        this.melhorarCondicionamentoFisico = melhorarCondicionamentoFisico;
    }

    public boolean isReduzirDoresCostas() {
        return reduzirDoresCostas;
    }

    public void setReduzirDoresCostas(boolean reduzirDoresCostas) {
        this.reduzirDoresCostas = reduzirDoresCostas;
    }

    public boolean isReduzirEstresse() {
        return reduzirEstresse;
    }

    public void setReduzirEstresse(boolean reduzirEstresse) {
        this.reduzirEstresse = reduzirEstresse;
    }

    public boolean isDiminuirColesterol() {
        return diminuirColesterol;
    }

    public void setDiminuirColesterol(boolean diminuirColesterol) {
        this.diminuirColesterol = diminuirColesterol;
    }

    public boolean isCoordenacaoMotora() {
        return coordenacaoMotora;
    }

    public void setCoordenacaoMotora(boolean coordenacaoMotora) {
        this.coordenacaoMotora = coordenacaoMotora;
    }

    public String getEspecificoObjetivo() {
        return especificoObjetivo;
    }

    public void setEspecificoObjetivo(String especificoObjetivo) {
        this.especificoObjetivo = especificoObjetivo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public double getBracoDireito() {
        return bracoDireito;
    }

    public void setBracoDireito(double bracoDireito) {
        this.bracoDireito = bracoDireito;
    }

    public double getBracoEsquerdo() {
        return bracoEsquerdo;
    }

    public void setBracoEsquerdo(double bracoEsquerdo) {
        this.bracoEsquerdo = bracoEsquerdo;
    }

    public double getCoxaDireita() {
        return coxaDireita;
    }

    public void setCoxaDireita(double coxaDireita) {
        this.coxaDireita = coxaDireita;
    }

    public double getCoxaEsquerda() {
        return coxaEsquerda;
    }

    public void setCoxaEsquerda(double coxaEsquerda) {
        this.coxaEsquerda = coxaEsquerda;
    }

    public double getPeitoralDireito() {
        return peitoralDireito;
    }

    public void setPeitoralDireito(double peitoralDireito) {
        this.peitoralDireito = peitoralDireito;
    }

    public double getPeitoralEsquerdo() {
        return peitoralEsquerdo;
    }

    public void setPeitoralEsquerdo(double peitoralEsquerdo) {
        this.peitoralEsquerdo = peitoralEsquerdo;
    }

    public double getCintura() {
        return cintura;
    }

    public void setCintura(double cintura) {
        this.cintura = cintura;
    }

    public double getTorax() {
        return torax;
    }

    public void setTorax(double torax) {
        this.torax = torax;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public String getClassificacaoImc() {
        return classificacaoImc;
    }

    public void setClassificacaoImc(String classificacaoImc) {
        this.classificacaoImc = classificacaoImc;
    }

    public double getPercentualGorduraCorporal() {
        return percentualGorduraCorporal;
    }

    public void setPercentualGorduraCorporal(double percentualGorduraCorporal) {
        this.percentualGorduraCorporal = percentualGorduraCorporal;
    }

    public double getPercentualMusculoEsqueletico() {
        return percentualMusculoEsqueletico;
    }

    public void setPercentualMusculoEsqueletico(double percentualMusculoEsqueletico) {
        this.percentualMusculoEsqueletico = percentualMusculoEsqueletico;
    }

    public double getMetabolismoRepouso() {
        return metabolismoRepouso;
    }

    public void setMetabolismoRepouso(double metabolismoRepouso) {
        this.metabolismoRepouso = metabolismoRepouso;
    }

    public int getIdadeBiologica() {
        return idadeBiologica;
    }

    public void setIdadeBiologica(int idadeBiologica) {
        this.idadeBiologica = idadeBiologica;
    }

    public int getNivelGorduraVisceral() {
        return nivelGorduraVisceral;
    }

    public void setNivelGorduraVisceral(int nivelGorduraVisceral) {
        this.nivelGorduraVisceral = nivelGorduraVisceral;
    }

    public String getAluno_cpf() {
        return aluno_cpf;
    }

    public void setAluno_cpf(String aluno_cpf) {
        this.aluno_cpf = aluno_cpf;
    }

}
