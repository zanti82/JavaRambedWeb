/*Esta clase va a manejar los arraylist de las refrencia de jeans, los administradores y los clientes.
 * Va a manejar un CRUD para jeans y clientes.
 */

import java.util.ArrayList;

public class TiendaRB{


   private ArrayList<Jeans> listaJeans;
   private ArrayList<Cliente> listaClientes;
   private ArrayList<Admin> listaAdmins;
   private ArrayList<Factura> listafacturas;

   

   public TiendaRB(ArrayList<Jeans> listaJeans, ArrayList<Cliente> listaClientes, ArrayList<Admin> listaAdmins, 
                    ArrayList<Factura> listaFacturas) {
    this.listaJeans = listaJeans;
    this.listaClientes = listaClientes;
    this.listaAdmins = listaAdmins;
    this.listafacturas=listaFacturas;
}



   public ArrayList<Jeans> getListaJeans() {
    return listaJeans;
}



   public void setListaJeans(ArrayList<Jeans> listaJeans) {
    this.listaJeans = listaJeans;
   }



   public void addRef(Jeans referencia){

    listaJeans.add(referencia);


   }

   public void listarJeans(){

    System.out.println("Esta el al lista de las referencias: ");

    for (Jeans jeans : listaJeans) {

        System.out.println(jeans.getId()+" . "+jeans.getEstilo()+" . "+jeans.getPrice()+" $");
        
    }

   }

   public void addAdmin(Admin admin){

    listaAdmins.add(admin);


   }

   public void getListAdmin(){

    for (Admin adm : listaAdmins) {

        System.out.println("Cedula: "+adm.getId()+" ,Nombre: "+adm.getName()+" ,email: "+adm.getMail());
        
    }

   }

   public void addClientes(Cliente client){

    listaClientes.add(client);


   }

   public void getListClientes(){

    for (Cliente cl : listaClientes) {

        System.out.println("Cedula: "+cl.getId()+" ,Nombre: "+cl.getName()+" ,email: "+cl.getMail());
        
    }

   }


   public void addFacturas(Factura fact){

    listafacturas.add(fact);


   }

   public void getListFactura(){

    for (Factura fact : listafacturas) {

        System.out.println(fact.getNumFact()+" . "+fact.getValor()+" $");
        
    }

   }

  
    
}
