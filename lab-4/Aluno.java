import java.util.Calendar;
/** 
 * Classe Aluno - Representa um aluno na aplicação
 * @author Lucas de Lima Castro &lt:llc2@icomp.ufam.edu.br&gt
 * @version 1.0, 2021-04-20
*/
public class Aluno {
    /** Lucas de Lima Castro */
    String nome;
    int matricula;
    int anoNascimento;

    /**
     * Construtor da classe Aluno.
     * Ao instanciar um novo aluno com este método valores padrões são utilizados.
     * @nome recebe uma string vazia
     * @matricula recebe 0
     * @anoNascimento recebe 0
     */
    Aluno(){
        this("",0,0);
    }
    /**
     * Construtor da classe Aluno
     * @nome nome do aluno
     * @matricula matricula do aluno
     * @anoNascimento data de nascimento do aluno
     */
    Aluno(String nome, int matricula, int anoNascimento){
        this.nome = nome;
        this.matricula = matricula;
        this.anoNascimento = anoNascimento;
    }
    /** 
     * Retorna a idade do aluno
     * @return int idade do aluno
    */
    int getIdade(){
        int ano = Calendar.getInstance().get(Calendar.YEAR);
        return ano - anoNascimento;
    }
    /**
     * Retorna a descrição completa do aluno
     * @return String descricao completa do aluno
    */
    String getDescricao(){
        return nome+ " (mat="+matricula+", idade="+getIdade()+")";
    }
}