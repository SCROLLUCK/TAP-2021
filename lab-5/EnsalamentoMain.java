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
            for(int j=0; j < turma.horarios.size(); j++){
                if(horario == turma.horarios.get(i)){
                    disponivel = false;
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

class Sala {
    int bloco;
    int sala;
    int capacidade;
    boolean acessivel;

    Sala(){
        this(0,0,0,false);
    }
    Sala(int bloco, int sala,int capacidade, boolean acessivel){
        this.bloco = bloco;
        this.sala = sala;
        this.capacidade = capacidade;
        this.acessivel = acessivel;
    }
    String getDescricao(){
        return "Bloco "+bloco+", Sala "+sala+ " ("+capacidade+" lugares, "+(acessivel? "acessível)": "não acessível)");
    }
}

class Turma {
    String nome;
    String professor;
    int numAlunos;
    boolean acessivel;
    ArrayList<Integer> horarios = new ArrayList<Integer>();

    Turma(){
        this("","",0,false);
    }

    Turma(String nome, String professor, int numAlunos, boolean acessivel){
        this.nome = nome;
        this.professor = professor;
        this.numAlunos = numAlunos;
        this.acessivel = acessivel;
    }

    void addHorario(int horario){
        horarios.add(horario);
    }
    
    String getHorariosString(){
        String result = "";
        int[] hour = {20,8,10,12,14,16,18};
        for(int i =0; i< horarios.size(); i++){
            if(horarios.get(i) > 0 && horarios.get(i) < 8){ result += "segunda "; }
            if(horarios.get(i) > 7 && horarios.get(i) < 15){ result += "terça "; }
            if(horarios.get(i) > 14 && horarios.get(i) < 22){ result += "quarta "; }
            if(horarios.get(i) > 21 && horarios.get(i) < 29){ result += "quinta "; }
            if(horarios.get(i) > 28 && horarios.get(i) < 36){ result += "sexta "; }

            result+= hour[horarios.get(i)%7]+"hs";
            if(i < horarios.size()-1){ result += ", "; }
        }
        return result;
    }

    String getDescricao() {
        String result = "";
        result += "Turma: "+nome+"\n";
        result += "Professor: "+professor+"\n";
        result += "Número de Alunos: "+numAlunos+"\n";
        result += "Horário: "+getHorariosString()+"\n";
        result += "Acessível: "+(acessivel? "sim": "não")+"\n";
        return result;
    }
}

class TurmaEmSala {
    Turma turma;
    Sala sala;

    TurmaEmSala(){
        this(null,null);
    }

    TurmaEmSala(Turma turma, Sala sala){
        this.turma = turma;
        this.sala = sala;
    }
}


class EnsalamentoMain {
    
    public static void main(String[] args){
        Ensalamento e1 = new Ensalamento();
        Turma t1 = new Turma();
        Sala s1 = new Sala();
    }
}
