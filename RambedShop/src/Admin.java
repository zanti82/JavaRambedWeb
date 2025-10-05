/**
 *  Esta clase permite crear objetos de tipo adminitradores que tiene atributos de tipo user
 * 
 */


public class Admin extends User {

        private boolean admin;

    

    public Admin(String id, String name, String mail, String password, String phoneNumber, String address) {
            super(id, name, mail, password, phoneNumber, address);
            this.admin = true;
        }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = true;
    }

    

    //editarStock()

    //añadirNuevoJean()
    
    //verReporteCompras()
    //verFactura()

}
