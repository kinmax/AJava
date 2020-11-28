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

   // construtor geral
   public TS_entry(String umId, TS_entry umTipo, ClasseID umaClasse, String umEscopo) {      
      this.id = umId;
      this.tipo = umTipo;
      this.clasee = umaClasse;
      this.escopo = umEscopo;

      this.parametros = null;
      this.varsLocais = null;
      this.metodos = null;
      this.atributos = null;
      this.classePai = null;

      if(classe.equals(ClasseID.Metodo)) {
         this.parametros = new ArrayList<TS_entry>();
         this.varsLocais = new ArrayList<TS_entry>();
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
      this.clasee = umaClasse;
      this.escopo = umEscopo;

      this.parametros = null;
      this.varsLocais = null;
      this.metodos = null;
      this.atributos = null;
      this.classePai = pai;

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


   public String getId() {
       return id; 
   }

   public TS_entry getTipo() {
       return tipo; 
   }

   public ClasseID getClasse() {
      return classe;
   }

   public void setPai(TS_entry pai) {
      this.pai = pai;
   }

   public void addToEscopo(String esc) {
      this.escopo += " " + esc;
   }

   public void setEscopo(String esc) {
      this.escopo = esc;
   }

   public boolean parametroRepetido(String idParam) {
      for(TS_entry e: this.parametros) {
         if(idParam.equals(e.getId()))
      }
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
      if(param.classe == ClasseID.Parametro) {
         this.parametros.add(param);
      }
      this.addToEscopo(param.getId());
      for(TS_entry e: this.parametros) {
         e.setEscopo(this.escopo);
      }
   }

   public ArrayList<TS_entry> getParametros() {
      return this.parametros;
   }
   
    
   public String toString() {
       StringBuilder aux = new StringBuilder("");
        
       aux.append("Id: ");
       aux.append(String.format("%-10s", id));

       aux.append("\tClasse: ");
       aux.append(classe);
       aux.append("\tTipo: "); 
       aux.append(tipo2str(this.tipo)); 
       aux.append("\tEscopo: ");
       aux.append(this.escopo); 
       
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
      else if (tipo.getTipo() != null) return  String.format("array(%d,%s)",
                                                   tipo.nroElementos, 
                                                      tipo2str(tipo.tipoBase));
                  
      else if (tipo==Parser.Tp_ERRO)  return  "_erro_";
      else                             return "erro/tp";
   }

   @Override
   public boolean equals(Object o) {
      if(!(o instanceof TS_entry)) {
         return false;
      }

      TS_entry entry = (TS_entry)o;
      if(entrgetId().equals(this.id) && entry.getClasse().equals(this.classe) && entry.getTipo() == this.tipo && entry.getEscopo().equals(this.escopo))
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






