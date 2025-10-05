/* Esta clase permite crear objetos de tipo clientes que tiene atributos de tipo user */

public class Cliente extends User {

    private boolean cliente;

     //declaro el supercontructor
     public Cliente(String id, String name, String mail, String password, String phoneNumber, String address) {
     super(id, name, mail, password, phoneNumber, address);
        this.cliente = true;
    }
    

    public boolean isCliente() {
        return cliente;
    }

    

    public void setCliente(boolean cliente) {
        this.cliente = true;
    }


        
   // verCatalogo();

    //realizarCompra();

    //verFactura();
       
   
    
}
