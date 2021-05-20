import java.util.ArrayList;
import java.util.Collections;

class Ensalamento {
    ArrayList<Sala> salas;
    ArrayList<Turma> turmas;
    ArrayList<TurmaEmSala> ensalamento;

    Ensalamento(){
        salas = new ArrayList<Sala>();
        turmas = new ArrayList<Turma>();
        ensalamento = new ArrayList<TurmaEmSala>();
    }

    void addSala(Sala sala){ salas.add(sala);}
    void addTurma(Turma turma){ 
        turmas.add(turma); 
    }

    Sala getSala(Turma turma){
        Sala salaEncontrada = null;
        for (int i = 0; i < ensalamento.size() && salaEncontrada == null; i++){
            if(ensalamento.get(i).turma.getDescricao().equals(turma.getDescricao())){
                salaEncontrada = ensalamento.get(i).sala;
            }
        }
        return salaEncontrada;
    }

    boolean salaDisponivel(Sala sala, int horario){
        boolean disponivel = true;
        for(int i=0; i < ensalamento.size(); i++){
            Turma turma = ensalamento.get(i).turma;
            if (sala == ensalamento.get(i).sala) {
                for(int j=0; j < turma.horarios.size(); j++){
                    if(horario == turma.horarios.get(j)){
                        disponivel = false;
                    }
                }
            }
        }
        return disponivel;
    }

    boolean salaDisponivel(Sala sala, ArrayList<Integer> horarios){
        boolean disponivel = true;
        for(int i=0; i < horarios.size() && disponivel; i++){
            disponivel = salaDisponivel(sala, horarios.get(i));
        }
        return disponivel;
    }

    boolean alocar(Turma turma, Sala sala){
        boolean alocou = true; 
        if(sala == null) return !alocou;
        if(turma.acessivel == true & sala.acessivel == false){ return !alocou; }
        if(turma.numAlunos > sala.capacidade){ return !alocou; }

        if(salaDisponivel(sala, turma.horarios)){
            TurmaEmSala novaTurmaEmSala = new TurmaEmSala(turma,sala);
            ensalamento.add(novaTurmaEmSala);
            return alocou;
        }else {
            return !alocou;
        }
    }

    void alocarTodas(){
        for(int i=0; i < turmas.size(); i++){
            boolean alocou = false;
            for(int j=0; j < salas.size() && !alocou; j++){
                alocou = alocar(turmas.get(i),salas.get(j));
            }
        }
    }

    int getTotalTurmasAlocadas(){
        int total = 0;
        for(int i=0; i< ensalamento.size(); i++){
            if (ensalamento.get(i).sala != null) total += 1;
        }
        return total;
    }

    int getTotalEspacoLivre(){
        int total = 0;
        for(int i=0;i < ensalamento.size();i++){
            total += ensalamento.get(i).sala.capacidade - ensalamento.get(i).turma.numAlunos;
        }
        return total;
    }

    String relatorioResumoEnsalamento(){
        String result = "";
        result+= "Total de Salas: "+salas.size()+"\n";
        result+= "Total de Turmas: "+turmas.size()+"\n";
        result+= "Turmas Alocadas: "+getTotalTurmasAlocadas()+"\n";
        result+= "Espaços Livres: "+getTotalEspacoLivre()+"\n";
        return result;
    }

    String relatorioTurmasPorSala(){
        String result = "";
        result += relatorioResumoEnsalamento();
        for(int i=0; i< salas.size(); i++){
            result +="--- "+salas.get(i).getDescricao()+" ---\n";
            for(int j =0; j< ensalamento.size(); j++){
                if(salas.get(i).getDescricao().equals(ensalamento.get(j).sala.getDescricao())){
                    result += ensalamento.get(i).turma.getDescricao();
                }
            }
        }
        return result;
    }

    String relatorioSalasPorTurma(){
        String result = "";
        result += relatorioResumoEnsalamento()+"\n";
        for(int i=0; i< turmas.size(); i++){
            result += turmas.get(i).getDescricao()+"\n";
            Sala sala = getSala(turmas.get(i));
            if(sala != null){
                result +="Sala: "+sala.getDescricao()+"\n";
            }else {
                result += "Sala: SEM SALA\n"; 
            }
        }
        return result;
    }
}
