import java.util.Hashtable;
import java.util.LinkedList;

public class ListaInvertida {
    private Hashtable<String, LinkedList<String>> tabela;
    public ListaInvertida(){
        this.tabela = new Hashtable<String, LinkedList<String>>();
    }

    public boolean insere(String palavra, String documento){
        LinkedList<String> documentos = tabela.get(palavra);
        if(documentos != null){
            if(!documentos.contains(documento)){
                documentos.add(documento);
                return true;
            }
        }else{
            LinkedList<String> novo_documentos = new LinkedList<String>();
            novo_documentos.add(documento);
            tabela.put(palavra,novo_documentos);
            return true;
        }
        return false;
    }

    public LinkedList<String> busca(String palavra){
        return tabela.get(palavra);
    }

    public String toString(){ 
        return tabela.toString();
    }
}
