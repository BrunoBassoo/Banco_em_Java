package model;

/**
 *
 * @author uniebrunosilva
 */
public class Cliente extends Pessoa{
    private double saldo;
    private contas c;
    private int tipoConta;

    public Cliente( String cpf, String senha, contas c) {
        super(cpf, senha);
        this.c = c;
    }

    public contas getC() {
        return c;
    }

    public void setC(contas c) {
        this.c = c;
    }
    
    
   
    public Cliente(String nome,String senha ,String cpf, double saldo) {
        super(nome, cpf, senha);
        this.saldo = saldo;
    }

    public Cliente(String cpf, String senha) {
        super(cpf, senha);
    }

    public Cliente(String cpf) {
        super(cpf);
    }

    public Cliente( String cpf, String senha, double saldo) {
        super(cpf, senha);
        this.saldo = saldo;
    }
    

    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(int tipoConta) {
        this.tipoConta = tipoConta;
    }
    
    

    @Override
    public String toString() {
        return "Cliente{" + "valor=" + saldo + '}';
    }
    
    

    
}
