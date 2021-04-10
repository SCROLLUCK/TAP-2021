class Motor {
    int tipo;
    String tipoString;
    double capacidade;
    int potencia;
    Motor(){
        this(1,0.0,0);
    }
    Motor(int tipo, double capacidade, int potencia){
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.potencia = potencia;
    }
    public String getTipoString() {
        String[] tipos = {"","Gasolina","Alcool","Flex","Diesel","Eletrico","Outros"};
        if(this.tipo < 6 && this.tipo > 0){
            return tipos[this.tipo];
        }
        return tipos[6];
        
    }
    public String getDescricao(){
        return "Motor: tipo="+this.getTipoString()+", capacidade="+this.capacidade+"L, potencia="+this.potencia+"CV.";
    }
}
