public class Factura {

        private String numFact;
        private double valor;
        
        
        public Factura(String numFact, double valor) {
            this.numFact = numFact;
            this.valor = valor;
        }


        public String getNumFact() {
            return numFact;
        }


        public void setNumFact(String numFact) {
            this.numFact = numFact;
        }


        public double getValor() {
            return valor;
        }


        public void setValor(double valor) {
            this.valor = valor;
        }
        
        
    
}
