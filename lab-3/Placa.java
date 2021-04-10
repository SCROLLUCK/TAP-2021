class Placa {
    String placa;
    int tipo;
    String tipoString;
    Placa(){
        this("",1);
    }
    Placa(String placa, int tipo){
        this.placa = placa;
        this.tipo = tipo;

        String[] tipos = {"","Normal","Servico","Oficial","Auto Escola","Prototipo","Colecionador","Outros"};
        String result = "Outros";
        if(this.tipo >= 7 || this.tipo == 0){
            result = tipos[7];
        }else{
            result = tipos[this.tipo];
        }
        this.tipoString = result;
    }
    public String getTipoString(){ return this.tipoString; }
    public boolean temEstacionamentoLivre(){
        boolean result = false;
        if(this.tipo == 2 || this.tipo == 3){
            result = true;
        }
        return result;
    }
    public String getDescricao(){
        return "Placa: placa="+this.placa+", tipo="+this.tipoString+", estacionamentoLivre="+this.temEstacionamentoLivre()+".";
    }
}