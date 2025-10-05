/*Esta clase crea objetos tipo usuarios */

public class User {

    private String id; //name of the user
    private String name; //name of the user
    private String mail; //mail of user
    private String password; // password of user
    private String phoneNumber; //numero tel
    private String address; // direccion
    
    

    //CONSTRUCTOR
    public User(String id, String name, String mail, String password, String phoneNumber, String address) {
        this.id= id;
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    //METODOS GET AND SET
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

   

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        for(int i=0; i<mail.length();i++){
            if(mail.contains("@")){
                this.mail = mail;
            }else{
                this.mail="falta @";
                System.out.println("mail incorrecto");
            }
        }
       
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

   
    public boolean loggin(String mail, String password){

        boolean loggin=false;
        if(this.mail.equals(mail) && this.password.equals(password)){
            System.out.println("bienvenido entrada exitosa");
            loggin=true;
        }else{
            System.out.println("email y password son incorrectos");
            loggin=false;
        }

        return loggin;

    }

    // CAMBIAMOS EL METODO TOSTRING DE LA CLASE TO STRING PARA QUE MUSTRE LA INFO
    @Override
    public String toString() {
        return "Nombre: -" + name + " ,Cedula:- "+id+ ", Email:- "+ mail+ " pass: -"+password+" Address:- "+address+" Phone: -"+phoneNumber;
    }

  
    
}
