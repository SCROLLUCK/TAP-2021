import java.util.ArrayList;

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
