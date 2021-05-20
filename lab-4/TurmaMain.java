import java.util.Calendar;
import java.util.ArrayList;

/** 
 * Classe Aluno - Representa um aluno na aplicação
 * @author Lucas de Lima Castro &lt:llc2@icomp.ufam.edu.br&gt
 * @version 1.0, 2021-04-20
*/
class Aluno {
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

class Professor {
    String nome;
    int matricula;
    String titulacao;
    Professor(){
        this("","",0);
    }
    Professor(String titulacao, String nome, int matricula){
        this.nome = nome;
        this.matricula = matricula;
        this.titulacao = titulacao;
    }
    String getDescricao(){
        return "Prof. "+titulacao+" "+nome+ "- mat "+matricula;
    }
}

class Turma {
    String disciplina;
    int ano;
    int semestre;
    Professor professor;
    ArrayList<Aluno> alunos = new ArrayList<Aluno>();

    Turma(String disciplina, int ano, int semestre, Professor professor){
        this.disciplina = disciplina;
        this.ano = ano;
        this.semestre = semestre;
        this.professor = professor;
    }

    Aluno getAluno(int matricula){
        for (int i = 0; i < alunos.size(); i++){
            if(matricula == alunos.get(i).matricula){
                return alunos.get(i);
            }
        }
        return null;
    }

    void addAluno(Aluno aluno){
        if(getAluno(aluno.matricula) == null){
            alunos.add(aluno);
        }
    }

    double getMediaIdade(){
        double total = 0;
        for(int i = 0; i < alunos.size(); i++){
            total+= alunos.get(i).getIdade();
        }
        return total/alunos.size();
    }

    String getDescricao(){
        String result = "";
        result += ("Turma "+disciplina+" - "+ano+"/"+semestre+"("+this.professor.getDescricao()+"):\n");
        for(int i = 0; i < alunos.size(); i++){
            result += (" - Aluno"+(i+1)+":"+alunos.get(i).getDescricao()+"\n");
        }
        return result;
    }
}

class TurmaMain {
    public static void main(String args[]){
        Aluno aluno = new Aluno();
        Professor professor = new Professor();
        Turma turma = new Turma("TAP", 2021, 2, professor);
        turma.addAluno(aluno);
        turma.getDescricao();

    }    
}
