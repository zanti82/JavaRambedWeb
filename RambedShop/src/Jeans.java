public class Jeans {

    private String estilo;
    private double price;
    private int id;
  
  
    //constructor
    
    public Jeans(int id, String estilo, double price) {
        this.id = id;
        this.estilo = estilo;
        this.price = price;
                  
}

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getEstilo() {
        return estilo;
    }


    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }




  

    

  

    
    
}
