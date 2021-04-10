public class Proprietario {
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
