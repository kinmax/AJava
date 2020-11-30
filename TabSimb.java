/*
  KIN MAX PIAMOLINI GUSMÃO - 16104046-4 - kin.gusmao@edu.pucrs.br
  PEDRO FRATINI CHEM       - 18109228   - pedro.chem@edu.pucrs.br
  YAGO DOS ANJOS VIEIRA    - 11203949-0 - yago.vieira@edu.pucrs.br
*/

import java.util.ArrayList;
import java.util.Iterator;


public class TabSimb
{
    private ArrayList<TS_entry> lista;
    private boolean listar;
    
    public TabSimb( )
    {
        this.listar = false;
        this.lista = new ArrayList<TS_entry>();
    }

    public boolean getListar() {
      return this.listar;
    }

    public void setListar(boolean l) {
      this.listar = l;
    }
    
    public void insert(TS_entry nodo) {
      lista.add(nodo);
    }    
    
    public void listar() {
      if(this.listar) {
        int cont = 0;  
        System.out.println("\n\n# Listagem da tabela de simbolos:\n");
        for (TS_entry nodo : lista) {
            if(nodo.getClasse() == ClasseID.Classe) {
              System.out.println("Classe: ");
            }
            System.out.println(nodo);
        }
      }
      
    }
      
    public TS_entry pesquisa(String umId) {
      for (TS_entry nodo : lista) {
          if (nodo.getId().equals(umId)) {
	      return nodo;
            }
      }
      return null;
    }

    public TS_entry pesquisa(String umId, ClasseID umaClasseID) {
      for (TS_entry nodo : lista) {
          if (nodo.getId().equals(umId) && nodo.getClasse().equals(umaClasseID)) {
	      return nodo;
            }
      }
      return null;
    }

    public TS_entry pesquisa(String umId, String escopo) {
      for (TS_entry nodo : lista) {
          if (nodo.getId().equals(umId) && nodo.getEscopo().equals(escopo)) {
	      return nodo;
            }
      }
      return null;
    }

    public TS_entry pesquisa(String umId, ClasseID umaClasseID, String escopo) {
      for (TS_entry nodo : lista) {
          if (nodo.getId().equals(umId) && nodo.getClasse().equals(umaClasseID) && nodo.getEscopo().equals(escopo)) {
	      return nodo;
            }
      }
      return null;
    }

	public void geraGlobais() {
          // assume que todas variáveis são globais e inteiras.
	      for (TS_entry nodo : lista) {
	            	System.out.println("_"+nodo.getId()+":"+"	.zero 4");
	            }
	      }
	     


}



