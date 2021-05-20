import java.util.ArrayList;

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
