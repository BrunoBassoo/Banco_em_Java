
package model;

/**
 *
 * @author T-Gamer
 */
public class ContaPoupanca {
    private double valorPoupanca;

    public ContaPoupanca() {
    }

    public ContaPoupanca(double valorPoupanca) {
        this.valorPoupanca = valorPoupanca;
    }

    // nao estamos usando construtor porque vamos somente instanciar os valores
    // das contas atraves do setValor*****()

    public double getValorPoupanca() {
        return valorPoupanca;
    }

    public void setValorPoupanca(double valorPoupanca) {
        this.valorPoupanca = valorPoupanca;
    }
    
    public void debitar(double valor){
        this.valorPoupanca -= valor;
    }
    
    public void deposito(double valor){
        this.valorPoupanca += valor;
    }
}
