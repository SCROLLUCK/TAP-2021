class Professor {
    /** Lucas de Lima Castro*/
    String nome;
    int matricula;
    String titulacao;
    Professor(){
        this("","",0);
    }
    /** 
     * Construtor da Classe Professor
    */
    Professor(String titulacao, String nome, int matricula){
        this.nome = nome;
        this.matricula = matricula;
        this.titulacao = titulacao;
    }
    String getDescricao(){
        return "Prof. "+titulacao+" "+nome+ "- mat "+matricula;
    }
}