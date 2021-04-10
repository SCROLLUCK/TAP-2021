
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
        String result = "Outros";
        if(this.tipo < 6 && this.tipo > 0){
            result = tipos[this.tipo];
        }else{
            result = tipos[6];
        }
        return result;
    }
    public String getDescricao(){
        return "Motor: tipo="+this.getTipoString()+", capacidade="+this.capacidade+"L, potencia="+this.potencia+"CV.";
    }
}

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

class Proprietario {
    String nome = "Emmett L. Brown";
    int cnh = 98008173;
    int anoNascimento=1920;
    
    Proprietario(){
        this("",0,0);
    }
    Proprietario(String nome, int cnh, int anoNascimento){
        this.nome = nome;
        this.cnh = cnh;
        this.anoNascimento = anoNascimento;
    }
    public int getIdade(int anoReferencia){
        return anoReferencia - this.anoNascimento; }
    public String getDescricao(){
        return "Proprietario: nome="+this.nome+", cnh="+this.cnh+", anoNascimento="+this.anoNascimento+".";
    }
}   

class Carro {
    String marca;
    String modelo;
    Proprietario proprietario;
    Placa placa;
    Motor motor;
    Carro(){
        this("","",new Proprietario(),new Placa(),new Motor());
    }
    Carro (String marca, String modelo, Proprietario proprietario, Placa placa, Motor motor){
        this.marca = marca;
        this.modelo = modelo;
        this.proprietario = proprietario;
        this.placa = placa;
        this.motor = motor;
    }
    public String getDescricao(){
        return "Carro "+this.marca+"/"+this.modelo+". "+this.proprietario.getDescricao()+" "+this.placa.getDescricao()+" "+this.motor.getDescricao();
    }
}
