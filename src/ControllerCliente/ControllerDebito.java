package ControllerCliente;
import DB.DB_Cliente;
import DB.conexao_banco;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Cliente;
import views.Debito;
/**
 *
 * @author T-Gamer
 */
public class ControllerDebito {
    private Debito view;

    public ControllerDebito(Debito view) {
        this.view = view;
    }
    public void debitarSalario(){
        String cpf = view.getTxtCpfCliente().getText();
        String senha = view.getTxtSenhaCliente().getText();
        double valor_debito = Double.parseDouble(view.getEntrada_saldo().getText());

        conexao_banco conexao = new conexao_banco();
        try{
            Connection conn = conexao.getConnection();
            DB_Cliente db = new DB_Cliente(conn);
            Cliente res = db.getCliente(cpf,senha);
            if(res.getCpf().equals("") ){
                JOptionPane.showMessageDialog(null, "Cliente inexistente", "Aviso", JOptionPane.ERROR_MESSAGE);
            }
            else{
                boolean resDeb= res.DebitarContaSalario(valor_debito);
                if(resDeb){
                    db.updateCliente(res);
                    double taxa = valor_debito * 0.05;                    
                    JOptionPane.showMessageDialog(view, String.format("Debito efetuado na conta salario! Com uma taxa de: R$ %.2f!",taxa), "Aviso", JOptionPane.INFORMATION_MESSAGE);                    
                }
            }
        } catch( SQLException e){
            JOptionPane.showMessageDialog(view, "Cliente não encontrado, tente novamente!", "Aviso", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        }
    }
    
    public void debitarPoupanca(){
        String cpf = view.getTxtCpfCliente().getText();
        String senha = view.getTxtSenhaCliente().getText();
        double valor_debito = Double.parseDouble(view.getEntrada_saldo().getText());

        conexao_banco conexao = new conexao_banco();
        try{
            Connection conn = conexao.getConnection();
            DB_Cliente db = new DB_Cliente(conn);
            Cliente res = db.getCliente(cpf,senha);
            if(res.getCpf().equals("") ){
                JOptionPane.showMessageDialog(null, "Cliente inexistente", "Aviso", JOptionPane.ERROR_MESSAGE);
            }
            else{
                boolean resDeb= res.DebitarContaPoupanca(valor_debito);
                if(resDeb){
                    db.updateCliente(res);
                    JOptionPane.showMessageDialog(view, "Debito efetuado na conta poupanca sem taxa nenhuma!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch( SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(view, "Cliente não encontrado, tente novamente!", "Aviso", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        }
    }
    
    public void debitarCorrente(){
        String cpf = view.getTxtCpfCliente().getText();
        String senha = view.getTxtSenhaCliente().getText();
        double valor_debito = Double.parseDouble(view.getEntrada_saldo().getText());

        conexao_banco conexao = new conexao_banco();
        try{
            Connection conn = conexao.getConnection();
            DB_Cliente db = new DB_Cliente(conn);
            Cliente res = db.getCliente(cpf,senha);
            if(res.getCpf().equals("") ){
                JOptionPane.showMessageDialog(null, "Cliente inexistente", "Aviso", JOptionPane.ERROR_MESSAGE);
            }
            else{
                boolean resDeb= res.DebitarContaCorrente(valor_debito);
                if(resDeb){
                    db.updateCliente(res);
                    double taxa = valor_debito * 0.01;
                    conn = conexao.getConnection();
                    db = new DB_Cliente(conn);
                    db.salvarTransacao(res, "-", valor_debito, taxa);
                    JOptionPane.showMessageDialog(view, String.format("Debito efetuado na conta corrente! Com uma taxa de: R$ %.2f!",taxa), "Aviso", JOptionPane.INFORMATION_MESSAGE);                       
                }
            }
        } catch( SQLException e){
            JOptionPane.showMessageDialog(view, "Cliente não encontrado, tente novamente!", "Aviso", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        }
    }
}
