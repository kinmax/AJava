import java.util.ArrayList;
/**
 * Write a description of class Paciente here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TS_entry
{
   private String id;
   private ClasseID classe;  
   private TS_entry tipo;
   private String escopo;

   private ArrayList<TS_entry> parametros; // se for método
   private ArrayList<TS_entry> varsLocais; // se for método
   private ArrayList<TS_entry> metodos; // se for classe
   private ArrayList<TS_entry> atributos; // se for classe
   private TS_entry classePai; // se for classe e tiver herdado de alguma outra
   private TS_entry classeDona; // se for método
   private TS_entry tipoClasse; // se for um objeto de alguma classe ou método que retorna um objeto de alguma classe
   private String assinatura; // para metodos
   private TS_entry tipoBase; // para arrays
   private int capacidade; // para arrays
   private boolean foiInstanciado; // para objetos

   // construtor geral
   public TS_entry(String umId, TS_entry umTipo, ClasseID umaClasse, String umEscopo) {      
      this.id = umId;
      this.tipo = umTipo;
      this.classe = umaClasse;
      this.escopo = umEscopo;

      this.parametros = null;
      this.varsLocais = null;
      this.metodos = null;
      this.atributos = null;
      this.classePai = null;
      this.classeDona = null;
      this.tipoClasse = null;
      this.tipoBase = null;
      this.capacidade = 0;
      this.foiInstanciado = false;

      if(classe.equals(ClasseID.Metodo)) {
         this.parametros = new ArrayList<TS_entry>();
         this.varsLocais = new ArrayList<TS_entry>();
      }
      else if(classe.equals(ClasseID.Classe)) {
         this.metodos = new ArrayList<TS_entry>();
         this.atributos = new ArrayList<TS_entry>();
      }
   }

   public TS_entry(String umId, TS_entry umTipo, ClasseID umaClasse) {      
      this.id = umId;
      this.tipo = umTipo;
      this.classe = umaClasse;

      this.parametros = null;
      this.varsLocais = null;
      this.metodos = null;
      this.atributos = null; 
      this.classePai = null;
      this.classeDona = null;
      this.tipoClasse = null;
      this.tipoBase = null;
      this.capacidade = 0;
      this.foiInstanciado = false;

      if(classe.equals(ClasseID.Metodo)) {
         this.parametros = new ArrayList<TS_entry>();
         this.varsLocais = new ArrayList<TS_entry>();
         this.assinatura = this.id;
      }
      else if(classe.equals(ClasseID.Classe)) {
         this.metodos = new ArrayList<TS_entry>();
         this.atributos = new ArrayList<TS_entry>();
      }
   }

   // construtor para classe filha
   public TS_entry(String umId, TS_entry umTipo, ClasseID umaClasse, String umEscopo, TS_entry pai) {      
      this.id = umId;
      this.tipo = umTipo;
      this.classe = umaClasse;
      this.escopo = umEscopo;

      this.parametros = null;
      this.varsLocais = null;
      this.metodos = null;
      this.atributos = null;
      this.classePai = pai;
      this.tipoBase = null;
      this.capacidade = 0;
      this.foiInstanciado = false;

      if(classe.equals(ClasseID.Metodo)) {
         this.parametros = new ArrayList<TS_entry>();
         this.varsLocais = new ArrayList<TS_entry>();
      }
      else if(classe.equals(ClasseID.Classe)) {
         this.metodos = new ArrayList<TS_entry>();
         this.atributos = new ArrayList<TS_entry>();
      }
   }

   public String getEscopo() {
      return escopo;
   }

   public boolean getInstanciado() {
      return this.foiInstanciado;
   }

   public void setInstanciado(boolean inst) {
      this.foiInstanciado = inst;
   }


   public String getId() {
       return id; 
   }

   public TS_entry getTipo() {
       return tipo; 
   }

   public void setTipo(TS_entry tp) {
      this.tipo = tp;
   }

   public int getCapacidade() {
      return this.capacidade;
   }

   public void setCapacidade(int cap) {
      this.capacidade = cap;
   }

   public ClasseID getClasse() {
      return classe;
   }

   public TS_entry getPai() {
      return this.classePai;
   }

   public void setPai(TS_entry pai) {
      this.classePai = pai;
   }

   public TS_entry getTipoBase() {
      return this.tipoBase;
   }

   public void setTipoBase(TS_entry tb) {
      this.tipoBase = tb;
   }

   public TS_entry getClasseDona() {
      return this.classeDona;
   }

   public void setClasseDona(TS_entry dona) {
      this.classeDona = dona;
   }

   public TS_entry getTipoClasse() {
      return this.tipoClasse;
   }

   public void setTipoClasse(TS_entry tipo) {
      this.tipoClasse = tipo;
   }

   public String getAssinatura() {
      return this.assinatura;
   }

   public void setAssinatura(String ass) {
      this.assinatura = ass;
   }

   public void addToEscopo(String esc) {
      this.escopo += " " + esc;
   }

   public void setEscopo(String esc) {
      this.escopo = esc;
   }

   public boolean parametroRepetido(String idParam) {
      for(TS_entry e: this.parametros) {
         if(idParam.equals(e.getId())) {
            return true;
         }
      }
      return false;
   }

   public void addAtributo(TS_entry attr) {
      if(attr.classe == ClasseID.Atributo) {
         this.atributos.add(attr);
      }
   }

   public void addMetodo(TS_entry met) {
      if(met.classe == ClasseID.Metodo) {
         this.metodos.add(met);
      }
   }

   public void addVarLocal(TS_entry var) {
      if(var.classe == ClasseID.VarLocal) {
         this.varsLocais.add(var);
      }
   }

   public void addParametro(TS_entry param) {
      if(param.getClasse() == ClasseID.Parametro) {
         this.parametros.add(param);
      }
      if(param.getTipo().getClasse() == ClasseID.Classe)
      {
         this.assinatura += " " + param.getTipo().getId();
      }
      else
      {
         this.assinatura += " " + param.getTipoStr();
      }
   }

   public ArrayList<TS_entry> getParametros() {
      return this.parametros;
   }

   public TS_entry pesquisaAtributo(String attrid) {
      for(TS_entry e: this.atributos) {
         if(e.getId().equals(attrid))
         {
            return e;
         }
      }
      return null;
   }

   public TS_entry pesquisaMetodo(String ass) {
      for(TS_entry e: this.metodos) {
         if(e.getAssinatura().equals(ass))
         {
            return e;
         }
      }

      return null;
   }

   public TS_entry pesquisaMetodoHeranca(String ass) {
      for(TS_entry e: this.metodos) {
         if(e.getAssinatura().equals(ass))
         {
            return e;
         }
      }

      if(classePai != null)
      {
         return this.classePai.pesquisaMetodo(ass);
      }

      return null;
   }

   public TS_entry pesquisaVarLocal(String varid) {
      for(TS_entry e: this.varsLocais) {
         if(e.getId().equals(varid))
         {
            return e;
         }
      }
      return null;
   }

   public TS_entry pesquisaParametro(String paramid) {
      for(TS_entry e: this.parametros) {
         if(e.getId().equals(paramid))
         {
            return e;
         }
      }
      return null;
   }

   public ArrayList<TS_entry> getMetodos() {
      return this.metodos;
   }
   
    
   public String toString() {
       StringBuilder aux = new StringBuilder("");
       if(this.classe == ClasseID.Metodo) {
          aux.append("\n\nMétodo"); 
       }
       aux.append("\nId: ");
       aux.append(String.format("%-10s", id));

       aux.append("\tClasse: ");
       aux.append(classe);
       aux.append("\tTipo: "); 
       aux.append(tipo2str(this.tipo));
       if(this.classe == ClasseID.Classe) {
          aux.append("\n\nAtributos:\n\t");
          for(TS_entry a: this.atributos) {
            aux.append(a.toString());
          }
          aux.append("\n\nMétodos:\n\t");
          for(TS_entry m: this.metodos) {
            aux.append(m.toString());
          }
       }

       if(this.classe == ClasseID.Metodo) {
         aux.append("\n\nParâmetros:\n\t");
         for(TS_entry p: this.parametros) {
           aux.append(p.toString());
         }
         aux.append("\n\nVariáveis Locais:\n\t");
         for(TS_entry v: this.varsLocais) {
           aux.append(v.toString());
         }
      }
       
      return aux.toString();

   }

  public String getTipoStr() {
       return tipo2str(this); 
   }

   public String tipo2str(TS_entry tipo) {
      if (tipo == null)  return "null"; 
      else if (tipo==Parser.Tp_INT)    return "int"; 
      else if (tipo==Parser.Tp_BOOLEAN)   return "boolean"; 
      else if (tipo==Parser.Tp_DOUBLE)  return "double";
      else if (tipo==Parser.Tp_LITERAL)  return "literal";
      else if (tipo==Parser.Tp_OBJETO)  return "objeto";
      else if (tipo==Parser.Tp_VOID)  return "void";
      else if (tipo==Parser.Tp_ARRAY)  return "array";
      // else if (tipo.getTipo() != null) return  String.format("array(%d,%s)",
      //                                              tipo.nroElementos, 
      //                                                 tipo2str(tipo.tipoBase));
                  
      else if (tipo==Parser.Tp_ERRO)  return  "_erro_";
      else                             return tipo.tipo2str(tipo.getTipo());
   }

   @Override
   public boolean equals(Object o) {
      if(!(o instanceof TS_entry)) {
         return false;
      }

      TS_entry entry = (TS_entry)o;
      if(entry.getId().equals(this.id) && entry.getClasse().equals(this.classe) && entry.getTipo() == this.tipo && entry.getEscopo().equals(this.escopo))
      {
         return true;
      }

      return false;
   }

   public boolean parametrosDeMesmoTipo(TS_entry p) {
      if(this.classe != ClasseID.Parametro || p.getClasse() != ClasseID.Parametro)
      {
         return false;
      }

      if(this.tipo == p.getTipo())
      {
         return true;
      }
      return false;
   }

   public boolean metodosComMesmaAssinatura(TS_entry m) {
      if(this.classe != ClasseID.Metodo || m.getClasse() != ClasseID.Metodo)
      {
         return false;
      }

      if(!(this.id.equals(m.getId())))
      {
         return false;
      }

      if(m.getParametros().size() != this.parametros.size()) {
         return false;
      }

      for(int i = 0; i < this.parametros.size(); i++) {
         if(this.parametros.get(i).tipo != m.getParametros().get(i).tipo) {
            return false;
         }
      }

      return true;
   }

}






