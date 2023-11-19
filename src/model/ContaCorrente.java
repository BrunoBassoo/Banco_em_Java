package model;

/**
 *
 * @author T-Gamer
 */
public class ContaCorrente {
    private double valorCorrente;

    public ContaCorrente() {
    }

    public ContaCorrente(double valorCorrente) {
        this.valorCorrente = valorCorrente;
    }
    

    public double getValorCorrente() {
        return valorCorrente;
    }

    public void setValorCorrente(double valorCorrente) {
        this.valorCorrente = valorCorrente;
    }
    
    public void debitar(double valor){
        this.valorCorrente -= (valor * 1.01);
    }
    
    public void deposito(double valor){
        this.valorCorrente += valor;
    }
}
